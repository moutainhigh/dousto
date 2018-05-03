package com.ibm.oms.service.business.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.IntfReceiveConst;
import com.ibm.oms.dao.constant.IntfSentConst;
import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.dao.constant.OrderStatusAction;
import com.ibm.oms.domain.persist.IntfReceived;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderMain_;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.domain.persist.OrderSub_;
import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.intf.intf.MsgDTO;
import com.ibm.oms.intf.intf.ResponseHeaderDTO;
import com.ibm.oms.intf.intf.ResponseObjectDTO;
import com.ibm.oms.intf.intf.WmsOmsReceiveLogisticsDTO;
import com.ibm.oms.service.IntfReceivedService;
import com.ibm.oms.service.IntfSentService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.oms.service.OrdiErrOptLogService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.OrderStatusService;
import com.ibm.oms.service.business.WmsOmsLogisticsStatusService;
import com.ibm.oms.service.business.trans.TmsOmsLogisticsStatusTransService;
import com.ibm.oms.service.business.trans.WmsOmsLogisticsStatusTransService;
import com.ibm.oms.service.mq.TmsOrderInfoSender;
import com.ibm.oms.service.util.CommonConstService;
import com.ibm.oms.service.util.CommonUtilService;

/**
 * @author liucy
 * 
 */
@Service("wmsOmsLogisticsStatusService")
public class WmsOmsLogisticsStatusServiceImpl implements WmsOmsLogisticsStatusService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	OrderStatusService orderStatusService;
	@Autowired
	OrderSubService orderSubService;
	@Autowired
	OrderMainService orderMainService;
	@Autowired
	WmsOmsLogisticsStatusTransService wmsOmsLogisticsStatusTransService;

    @Autowired
    IntfSentService intfSentService;
    
	@Autowired
	IntfReceivedService intfReceivedService;
	
	@Autowired
	OrdiErrOptLogService ordiErrOptLogService;
	
	@Autowired
	OrderNoService 	orderNoService;
    @Autowired
    TmsOrderInfoSender tmsOrderInfoSender;
    @Autowired
    TmsOmsLogisticsStatusTransService tmsOmsLogisticsStatusTransService;
    @Autowired
    CommonUtilService commonUtilService ;
    
    public final String TMS_TYPE_OS = "os";

    public  ResponseObjectDTO handlerUpdateOrderLogisticsStatus(List<WmsOmsReceiveLogisticsDTO> receive){

		ResponseObjectDTO ro = new ResponseObjectDTO();
		ResponseHeaderDTO rh = new ResponseHeaderDTO();
		
		List<MsgDTO> errors = new ArrayList<MsgDTO>();
		
		MsgDTO msg = null;

		rh.setErrors(errors);
		ro.setResponseHeader(rh);
		
		IntfReceived rec = commonUtilService.writeListIntfReceivedJson("",IntfReceiveConst.Wms_Oms_Receive_Logistics.getCode(), receive);
		intfReceivedService.save(rec);
		String vmsg = commonUtilService.createOrderListValidate(receive);

		if (CommonConstService.OK.equals(vmsg)) {
			rh.setSuccess(CommonConstService.OK);
			rec.setSucceed(CommonConstService.OKLong);
		    intfReceivedService.update(rec);
		} else {
			rec.setSucceed(CommonConstService.FAILEDLong);
			intfReceivedService.update(rec);
			rh.setSuccess(CommonConstService.FAILED);
			msg = new MsgDTO();
			msg.setCode(CommonConstService.FAILED);
			msg.setMessage("数据校验失败:" + vmsg);
			errors.add(msg);
			//返回报文写入发送
			commonUtilService.writeIntfSentJson(null, null, IntfReceiveConst.Wms_Oms_Receive_Logistics.getCode(), ro);
			return ro;
		}

		int len = receive.size();
		
	   	String orderNo = null;
		// 循环更新库存状态信息
		for (int i = 0; i < len; i++) {
		    WmsOmsReceiveLogisticsDTO torlDTO = null;
            try {
                torlDTO = receive.get(i);
            } catch (Exception e) {
                logger.info("{}", e);
            }
            //add by xiaohl 20140727 for 判断订单是否为库存扣减失败，因为WMS库存扣减超时会导致此情况
            boolean statusFlag = false;
            if(!statusFlag){
                try{
                    String orderSubNo = torlDTO.getOrderSubNo();
                    orderNo = orderNoService.getOrderNoBySubNo(orderSubNo);
                    OrderMain main = this.orderMainService.getByField(OrderMain_.orderNo, orderNo);
                    if(null!=main && OrderStatus.ORDER_INVENTORY_DEDUCT_FAILED.getCode().equals(main.getStatusTotal())){
                        orderStatusService.saveProcess(orderSubNo, OrderStatusAction.S015160, null, new Date(), null);
                    }
                }catch(Exception e){
                    logger.info("订单库存状态回传，处理库存失败的订单异常：,{}", e);
                }
                statusFlag = true;
            }
            
			msg = new MsgDTO();
			try {
				CommonOutputDTO output = this.handleSingleStatus(torlDTO);

				msg.setCode(output.getCode());
				msg.setMessage(output.getMsg());
			} catch (Exception e) {
				logger.error("WmsOmsReceiveLogisticsServiceImpl  --> handlerOrderLogisticsStatus {}",e);
				msg.setCode(CommonConstService.FAILED);
				msg.setMessage("系统异常请联系管理员!" + e.getMessage());
			}

            msg.setOrderSubNo(torlDTO == null ? "" : torlDTO.getOrderSubNo());
			errors.add(msg);
		}
		//返回报文写入发送
        commonUtilService.writeIntfSentJson(null, null, IntfReceiveConst.Wms_Oms_Receive_Logistics.getCode(), ro);
		return ro;
	}
	
    
    private CommonOutputDTO handleSingleStatus(WmsOmsReceiveLogisticsDTO wmsOmsReceiveLogisticsDTO) {
        //更新状态
        CommonOutputDTO output = wmsOmsLogisticsStatusTransService.updateOrderLogisticsStatus(wmsOmsReceiveLogisticsDTO);
        if(!CommonConstService.OK.equals(output.getCode())){
            return output;
        }
        //向tms发送订单
        if(CommonConstService.WMS_LogiStatus_80.equals(wmsOmsReceiveLogisticsDTO.getLogisticsStatus())){
            String orderSubNo = wmsOmsReceiveLogisticsDTO.getOrderSubNo();
            String toSendStr = tmsOmsLogisticsStatusTransService.queryToTmsStr(orderSubNo, CommonConstService.TMS_TYPE_OS);
            if(toSendStr != null){
                tmsOrderInfoSender.sendWithTrack(toSendStr, orderSubNo, IntfSentConst.OMS_TMS_ORDER.getCode());
            }
            //更新出库时间字段
            OrderSub orderSub = orderSubService.getByField(OrderSub_.orderSubNo, orderSubNo);
            orderSub.setOutStoreTime(new Date());
            orderSubService.update(orderSub);
        }
        return output;
    }
}

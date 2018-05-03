package com.ibm.oms.service.business.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.IntfReceiveConst;
import com.ibm.oms.domain.persist.IntfReceived;
import com.ibm.oms.domain.persist.OrdiErrOptLog;
import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.intf.intf.MsgDTO;
import com.ibm.oms.intf.intf.ResponseHeaderDTO;
import com.ibm.oms.intf.intf.ResponseObjectDTO;
import com.ibm.oms.intf.intf.WmsReceiveCostPriceDTO;
import com.ibm.oms.service.IntfReceivedService;
import com.ibm.oms.service.OrdiErrOptLogService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.WmsOmsCostPriceService;
import com.ibm.oms.service.business.trans.WmsOmsCostPriceTransService;
import com.ibm.oms.service.util.CommonConstService;
import com.ibm.oms.service.util.CommonUtilService;
import com.ibm.oms.service.util.ExceptionUtil;

@Service("wmsOmsCostPriceService")
public class WmsOmsCostPriceServiceImpl implements WmsOmsCostPriceService {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    WmsOmsCostPriceTransService wmsOmsCostPriceTransService;
	
	@Autowired
	IntfReceivedService intfReceivedService;
	
	@Autowired
	OrdiErrOptLogService ordiErrOptLogService;
	
	@Autowired
	OrderNoService 	orderNoService;
    @Autowired
    CommonUtilService commonUtilService;
	@Override
    public CommonOutputDTO handlerUpdateSingleOrderCostPrice(WmsReceiveCostPriceDTO receiveCostPriceDTO){
    	return wmsOmsCostPriceTransService.updateOrderCostPrice(receiveCostPriceDTO);
    }
	
	public ResponseObjectDTO handlerUpdateOrderCostPrice(List<WmsReceiveCostPriceDTO> receive){
		ResponseObjectDTO ro = new ResponseObjectDTO();
		ResponseHeaderDTO rh = new ResponseHeaderDTO();
		
		List<MsgDTO> errors = new ArrayList<MsgDTO>();
		
		MsgDTO msg = null;

		rh.setErrors(errors);
		ro.setResponseHeader(rh);
		
		IntfReceived rec = commonUtilService.writeListIntfReceivedJson("",IntfReceiveConst.Order_Receive_Cost_Price.getCode(), receive);
		intfReceivedService.save(rec);
		String vmsg = commonUtilService.createOrderListValidate(receive);

		if (CommonConstService.OK.equals(vmsg)) {
			rh.setSuccess(CommonConstService.OK);
			rec.setSucceed(CommonConstService.OKLong);
		} else {
			rec.setSucceed(CommonConstService.FAILEDLong);
			intfReceivedService.update(rec);
			rh.setSuccess(CommonConstService.FAILED);
			msg = new MsgDTO();
			msg.setCode(CommonConstService.FAILED);
			msg.setMessage("数据校验失败:" + vmsg);
			errors.add(msg);
			return ro;
		}
		intfReceivedService.update(rec);
		
    	String orderSubNo = null;
    	String orderNo = null;
    	CommonOutputDTO output = null;
    	int len = receive.size();
    	// 循环更新出库成本信息
		for (int i = 0; i < len; i++) {			
			WmsReceiveCostPriceDTO wrcpDTO = receive.get(i);
			msg = new MsgDTO();
	    	try{
	    		orderSubNo = wrcpDTO.getOrderSubNo();
				output = this.handlerUpdateSingleOrderCostPrice(wrcpDTO);
				msg.setCode(output.getCode());
				msg.setMessage(output.getMsg());

	    	}catch(Exception e){
	    		/*OrdiErrOptLog errLog = new OrdiErrOptLog();
				errLog.setOrderSubNo(wrcpDTO.getOrderSubNo());
				errLog.setErrorCode(IntfReceiveConst.Order_Receive_Cost_Price.getCode());
				errLog.setOrderItemNo(wrcpDTO.getOrderItemNo());
				errLog.setErrorDesc(ExceptionUtil.stackTraceToString(e, 255));
				orderNo = orderNoService.getOrderNoBySubNo(wrcpDTO.getOrderSubNo());
				errLog.setOrderNo(orderNo);
				ordiErrOptLogService.save(errLog);*/
				logger.error("WmsOmsReceiveCostPriceServiceImpl  --> handlerOrderCostPrice {}", e);
				msg.setCode(CommonConstService.FAILED);
				msg.setMessage(orderSubNo+":系统异常请联系管理员!"+e.getMessage());
	    	}
	    	
	    	msg.setOrderItemNo(wrcpDTO.getOrderItemNo());
	    	msg.setOrderSubNo(wrcpDTO.getOrderSubNo());
	    	errors.add(msg);
		}

		return ro;
	}
}

package com.ibm.oms.service.business.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.constant.IntfReceiveConst;
import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.domain.persist.IntfReceived;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.intf.intf.BtcPayDTO;
import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.service.IntfReceivedService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.business.BtcOrderPaymentService;
import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.trans.BtcOrderPaymentTransService;
import com.ibm.oms.service.util.CommonConstService;
import com.ibm.oms.service.util.CommonUtilService;

/**
 * 订单支付接口,包括BTC在线支付，BBC货到付款
 *
 */
@Service("btcOrderPaymentService")
public class BtcOrderPaymentServiceImpl  implements BtcOrderPaymentService {
	private Logger logger =  LoggerFactory.getLogger(getClass());
	@Autowired
	BtcOrderPaymentTransService btcOrderPaymentTransService;
	
    @Autowired
    OrderCreateService orderCreateService;
    @Autowired
    CommonUtilService commonUtilService;
	
	@Autowired
	IntfReceivedService intfReceivedService;
	
	@Autowired
	OrderNoService 	orderNoService;
	@Autowired
	OrderMainService orderMainService;
	
	@Override
	public CommonOutputDTO handlerBtcOrderPayment(BtcPayDTO payDto){
		
		IntfReceived rec = null;
		CommonOutputDTO dto = new CommonOutputDTO();
        rec = commonUtilService.saveIntfReceivedJson(null, null, IntfReceiveConst.Order_Receive_Payment.getCode() , payDto, CommonConstService.WAITLong);   
        // 校验数据
        String msg = CommonUtilService.createOrderValidate(payDto);
        CommonOutputDTO output = new CommonOutputDTO();
        //校验失败
        if (!CommonConstService.OK.equals(msg)) {
            output.setCode(CommonConstService.FAILED);
            rec.setSucceed(-1l);
            intfReceivedService.update(rec);
            return output;
        }  
        OrderMain om = orderMainService.findByOrderNo(payDto.getOrderNo());
        //支付状态检查，如果已经支付，直接返回成功
        if(OrderStatus.Order_PayStatus_Success.getCode().equals(om.getStatusPay())){
            output.setCode(CommonConstService.OK);
            output.setMsg("订单已支付：" + payDto.getOrderNo());
            return output;
        }
        //总状态检查，如果不是30，71状态，直接返回成功，否则虽然失败但仍然会将支付信息写入orderPay表
        boolean waitingPay = OrderStatus.ORDER_PAYING.getCode().equals(om.getStatusTotal()) ||
                OrderStatus.ORDER_POD_SENT.getCode().equals(om.getStatusTotal());
        if(!waitingPay){
            output.setCode(CommonConstService.OK);
            output.setMsg("订单无需支付：" + payDto.getOrderNo());
            return output;
        }
        try {
            dto = btcOrderPaymentTransService.updateBtcOrderPayment(payDto);
            if (!btcOrderPaymentTransService.updateStatusPay(payDto, output)) {
                return output;
            }
			if (CommonConstService.OK.equals(dto.getCode())) {
			    rec.setSucceed(CommonConstService.OKLong);
			}else{
	             rec.setSucceed(CommonConstService.FAILEDLong);
			}
		} catch (Exception e) {
			logger.error("OrderReceivePaymentServiceImpl-->error {}", e);
			rec.setSucceed(CommonConstService.FAILEDLong);
			dto.setCode(CommonConstService.FAILED);
			dto.setMsg("系统异常请联系管理员!"+e.getMessage());
            rec.setSucceed(CommonConstService.FAILEDLong);
		}

		intfReceivedService.update(rec);
        if(CommonConstService.FAILEDLong.equals(rec.getSucceed())){
            return dto;
        }
        if(CommonConst.OrderPay_DateSource_Bbc.getCode().equals(payDto.getDataSource())){
            return dto;
        }
        orderCreateService.callSingleProcess(payDto.getOrderNo());
		return dto;

	}
}

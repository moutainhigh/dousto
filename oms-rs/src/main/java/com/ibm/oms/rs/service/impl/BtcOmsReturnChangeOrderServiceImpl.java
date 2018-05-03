package com.ibm.oms.rs.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.dao.constant.CancelOrderConst;
import com.ibm.oms.intf.intf.BtcOmsReturnChangeDTO;
import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.rs.service.BtcOmsReturnChangeOrderService;
import com.ibm.oms.service.business.ReturnChangeOrderService;
import com.ibm.oms.service.util.CommonConstService;
import com.ibm.sc.rs.bean.ResponseHeader;
import com.ibm.sc.rs.bean.ResponseObject;

/**
 * @author pjsong
 *
 */
@Component("btcOmsReturnChangeOrderService")
public class BtcOmsReturnChangeOrderServiceImpl implements BtcOmsReturnChangeOrderService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private ReturnChangeOrderService returnChangeOrderService;
    
    @Override
    public ResponseObject createReturnOrder(BtcOmsReturnChangeDTO returnOrderDTO) {
        CommonOutputDTO output = returnChangeOrderService.writeReturnOrder(returnOrderDTO);
        ResponseObject ro = new ResponseObject();
        ResponseHeader rh = new ResponseHeader();
        rh.setMessage(output.getMsg());
        rh.setSuccess(output.getCode());
        rh.setToken(output.getOrderSubNo());//子订单号
        ro.setResponseHeader(rh);
        return ro;
    }

    @Override
    public ResponseObject createChangeOrder(BtcOmsReturnChangeDTO changeOrderDTO) {
        CommonOutputDTO output = returnChangeOrderService.writeChangeOrder(changeOrderDTO);
        ResponseObject ro = new ResponseObject();
        ResponseHeader rh = new ResponseHeader();
        rh.setMessage(output.getMsg());
        rh.setSuccess(output.getCode());
        rh.setToken(output.getOrderSubNo());//子订单号
        ro.setResponseHeader(rh);
        return ro;
    }
    
    @Override
    public ResponseObject cancelOrder(String orderSubNo, String operator){
		ResponseObject ro = new ResponseObject();
		ResponseHeader rh = new ResponseHeader();
		ro.setResponseHeader(rh);
    	
    	try {
	        CommonOutputDTO output =returnChangeOrderService.writeCancelOrder(orderSubNo, operator);
	        rh.setMessage(output.getMsg());
	        rh.setSuccess(output.getCode());
	        ro.setResponseHeader(rh);
    	}catch(Exception e){
    		logger.error("BtcOmsReturnChangeOrderService->cancelOrder-->error {}",e);
			rh.setSuccess(CommonConstService.FAILED);
			rh.setMessage("系统异常请连续管理员");
    	}
        
        
        return ro;
    }

}

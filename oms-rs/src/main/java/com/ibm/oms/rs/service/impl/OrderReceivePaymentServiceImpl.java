package com.ibm.oms.rs.service.impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.oms.intf.intf.BtcPayDTO;
import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.intf.intf.inner.PaymentDTO;
import com.ibm.oms.rs.service.OrderReceivePaymentService;
import com.ibm.oms.service.IntfReceivedService;
import com.ibm.oms.service.OrdiErrOptLogService;
import com.ibm.oms.service.business.BtcOrderPaymentService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.util.CommonConstService;
import com.ibm.oms.service.util.CommonUtilService;

@Component("orderReceivePaymentService")
public class OrderReceivePaymentServiceImpl implements OrderReceivePaymentService {

	private Logger logger =  LoggerFactory.getLogger(getClass());
	/**添加处理缓存防止并发，当进入服务方法时，先检查cach是否已经存在该数据，如果存在说明是重复数据，如果不存在将该数据加入cache,服务方法调用完成之后再清除**/
	private int cacheSize = 50;
	private ConcurrentHashMap<String, Date> cacheConcur = new ConcurrentHashMap<String, Date>();
	//@Autowired
	BtcOrderPaymentService btcOrderPaymentService;
	
	@Autowired
	IntfReceivedService intfReceivedService;
	
	@Autowired
	OrderNoService 	orderNoService;
	
	@Autowired
	OrdiErrOptLogService ordiErrOptLogService;

	@Autowired
	CommonUtilService commonUtilService;
	
	@Override
	public synchronized CommonOutputDTO createOrderPayment(BtcPayDTO payDto) {
        CommonOutputDTO output = new CommonOutputDTO();
        try {
            if (checkCache(payDto)) {
                output = btcOrderPaymentService.handlerBtcOrderPayment(payDto);
            } else {
                output.setCode("-1");
                output.setMessage("防重复拦截,单号" + payDto.getOrderNo());
            }
            return output;
        } catch (Exception e) {
            logger.error("BtcOmsReceiveOrderServiceImpl-->exception, {}", e);
            output.setCode(CommonConstService.FAILED);
            output.setMessage(e.getMessage());
            return output;
        }finally{
            removeFromCache(payDto);
        }
    }
	
	/**进入服务方法之前检查缓存**/
	private boolean checkCache(BtcPayDTO payDto){
	    List<PaymentDTO> payList = payDto.getPaymentDTOs();
	    //支付项为空，放行
	    if(payList == null || payList.isEmpty()){
	        return true;
	    }
	    for(PaymentDTO pDto:payList){
	        String key= payDto.getOrderNo() + pDto.getPayCode();
	        //如果缓存中存在此数据，拦截
	        if(cacheConcur.containsKey(key)){
	            return false;
	        }else if(cacheConcur.size() >= cacheSize){
	            //缓存数量已经达到阀值，全部移出，防止内存泄露
	            logger.warn("warning: 调度缓存设置为{},实际 cacheMap size {}",cacheSize, cacheConcur.size());
	            cacheConcur.clear();
	        }
	        cacheConcur.put(key, new Date());
	    }
        return true;
	}
	
    /** 服务方法之后移出缓存 **/
    private void removeFromCache(BtcPayDTO payDto) {
        List<PaymentDTO> payList = payDto.getPaymentDTOs();
        // 支付项为空，放行
        if (payList == null || payList.isEmpty()) {
            return;
        }
        for (PaymentDTO pDto : payList) {
            String key = payDto.getOrderNo() + pDto.getPayCode();
            cacheConcur.remove(key);
        }
    }
	
 }

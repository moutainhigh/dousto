package com.ibm.oms.rs.service.impl;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.intf.intf.BBCLogiDTO;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderDTO;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderOutputDTO;
import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.intf.intf.PosPayOutputDTO;
import com.ibm.oms.rs.service.BtcOmsReceiveOrderService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.oms.service.util.CommonConstService;
import com.ibm.sc.rs.service.impl.BaseRsServiceImpl;

/**
 * @author pjsong
 * 
 */
@Component("btcOmsReceiveOrderService")
public class BtcOmsReceiveOrderServiceImpl extends BaseRsServiceImpl  implements BtcOmsReceiveOrderService{
    private final Logger logger = LoggerFactory.getLogger(getClass());
    /**添加处理缓存防止并发，当进入服务方法时，先检查cach是否已经存在该数据，如果存在说明是重复数据，如果不存在将该数据加入cache,服务方法调用完成之后再清除**/
    private int cacheSize = 50;
    private ConcurrentHashMap<String, Date> cacheConcur = new ConcurrentHashMap<String, Date>();
    @Resource
    private OrderCreateService orderCreateService;
    @Resource
    OrderMainService orderMainService;
    @Override
    public BtcOmsReceiveOrderOutputDTO btcOmsReceiveOrderDTO(BtcOmsReceiveOrderDTO dto1) {
        BtcOmsReceiveOrderOutputDTO output = null;
        try{
        	output = orderCreateService.createOrder(dto1);
	        return output;
        }catch(Exception e){
        	logger.error("BtcOmsReceiveOrderServiceImpl-->exception, {}", e);
        	if(output == null){
        	    output = new BtcOmsReceiveOrderOutputDTO();
        	}
        	output.setSucceed(CommonConstService.FAILED);
        	output.setMessage( e.getMessage());
        	 return output;
        }
    }

	@Override
	public CommonOutputDTO bbcOperateByOrderNo(String orderSubNo, String operateCode, String sys) {
        String result = null;
        try {
            if (checkCache(orderSubNo, operateCode, sys)) {
                result = orderCreateService.bbcOperateToOms(orderSubNo, operateCode, sys);
            }else{
                result = "重复请求,子订单号：" + orderSubNo + "operateCode: " + operateCode + "sys:　" + sys;
            }
        } catch (Exception e) {
        }finally{
            removeFromCache(orderSubNo, operateCode, sys);
        }
        CommonOutputDTO ret = new CommonOutputDTO();
        ret.setCode(CommonConstService.OK.equals(result) ? CommonConstService.OK : CommonConstService.FAILED);
        ret.setMsg(CommonConstService.OK.equals(result) ? "" : result);
        ret.setOrderSubNo(orderSubNo);
        return ret;
	}

    @Override
    public CommonOutputDTO bbcLogistics(BBCLogiDTO bbcLogiDTO) {
        String result = null;
        try{
            result = orderCreateService.bbcLogisticsVerified(bbcLogiDTO);
        }catch(Exception e){
        }
        CommonOutputDTO ret =new CommonOutputDTO(); 
        ret.setCode(CommonConstService.OK.equals(result)?CommonConstService.OK:CommonConstService.FAILED);
        ret.setMsg(CommonConstService.OK.equals(result) ? "" : result);
        ret.setOrderSubNo(bbcLogiDTO.getOrderSubNo());
        return ret;
    }

    @Override
    public PosPayOutputDTO posPayByOrderNo(String orderNo) {
        PosPayOutputDTO output = new PosPayOutputDTO();
        output.setCode(orderNo);
        if(StringUtils.isEmpty(orderNo)){
            output.setTotalPayAmount(null);
            output.setCode(CommonConstService.FAILED);
            return output;
        }
        OrderMain om = orderMainService.findByOrderNo(orderNo);
        if (om == null) {
            output.setTotalPayAmount(null);
            output.setCode(CommonConstService.FAILED);
        } else {
            output.setTotalPayAmount(om.getTotalPayAmount());
            output.setCode(CommonConstService.OK);
        }
        return output;
    }
    
    @Override
	public int isOpen() {
		return 1;
	}
    
    /** 进入服务方法之前检查缓存 **/
    private boolean checkCache(String orderSubNo, String operateCode, String sys) {
        String key = orderSubNo + operateCode + sys;
        // 如果缓存中存在此数据，拦截
        if (cacheConcur.containsKey(key)) {
            return false;
        } else if (cacheConcur.size() >= cacheSize) {
            // 缓存数量已经达到阀值，全部移出，防止内存泄露
            logger.warn("warning: 调度缓存设置为{},实际 cacheMap size {}", cacheSize, cacheConcur.size());
            cacheConcur.clear();
        }
        cacheConcur.put(key, new Date());
        return true;
    }
    
    
    /** 服务方法之后移出缓存 **/
    private void removeFromCache(String orderSubNo, String operateCode, String sys) {
        String key = orderSubNo + operateCode + sys;
        cacheConcur.remove(key);
    }
}

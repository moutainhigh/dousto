package com.ibm.oms.rs.service.impl;


import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.oms.dao.constant.CancelOrderConst;
import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.dao.constant.OrderStatusAction;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderOperateLog;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.domain.persist.OrderSub_;
import com.ibm.oms.intf.intf.BBCBatchRemarkDTO;
import com.ibm.oms.intf.intf.BBCUpdateOrderDTO;
import com.ibm.oms.intf.intf.BBCUpdateOrderStatusDTO;
import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.intf.intf.ResultDTO;
import com.ibm.oms.rs.service.BbcOmsOrderService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderOperateLogService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.OrderStatusService;
import com.ibm.oms.service.business.SaleAfterOrderService;
import com.ibm.oms.service.business.trans.SaleAfterOrderTransService;
import com.ibm.oms.service.util.CommonConstService;
import com.ibm.sc.rs.service.impl.BaseRsServiceImpl;


/**
 * @author JJL
 * 
 */
@Component("bbcOmsOrderService")
public class BbcOmsOrderServiceImpl extends BaseRsServiceImpl  implements BbcOmsOrderService{

	private final Logger logger =  LoggerFactory.getLogger(getClass());
	
	@Resource
    private OrderMainService orderMainService ;
	@Resource
	private OrderCreateService orderCreateService;
	
	@Resource
	protected OrderSubService orderSubService;
	@Resource
	protected OrderNoService orderNoService;
	@Resource
	protected SaleAfterOrderTransService saleAfterOrderTransService;

    @Autowired
    protected SaleAfterOrderService saleAfterOrderService;    
	@Resource
	protected OrderOperateLogService orderOperateLogService;
	@Autowired
	OrderStatusService orderStatusService;
	
	
	
    @Override
    public CommonOutputDTO bbcUpdateOrderInfo(BBCUpdateOrderDTO bbcUpdateOrderDTO){
    	CommonOutputDTO ret = new CommonOutputDTO();
    	try {
    		ret = orderMainService.bbcUpdateOrderInfo(bbcUpdateOrderDTO);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        return ret;
    }

	@Override
	public CommonOutputDTO bbcBatchUpdateRemarkInfo(BBCBatchRemarkDTO bbcBatchRemarkDTO) {
		// TODO Auto-generated method stub
		CommonOutputDTO ret = new CommonOutputDTO();
    	try {
    		ret = orderMainService.bbcBatchUpdateRemarkInfo(bbcBatchRemarkDTO);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        return ret;
	}


	@Override
	public CommonOutputDTO bbcUpdateOrderStatus(BBCUpdateOrderStatusDTO bbcUpdateOrderStatusDTO) {
		// TODO Auto-generated method stub
		CommonOutputDTO ret = new CommonOutputDTO();
    	try {
    		ret = orderMainService.bbcUpdateOrderStatus(bbcUpdateOrderStatusDTO);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        return ret;
	}

    /**
     * BBC订单审核接口
     * @param bbcUpdateOrderStatusDTO
     * @return 
     */
	@Override
	public CommonOutputDTO bbcOrderAudit(String orderNo,String operateName,String flag,String exceptionFlag) {
		// TODO Auto-generated method stub
		CommonOutputDTO ret = new CommonOutputDTO();
		String jdugeFlag = "";
		try {
			jdugeFlag = orderCreateService.inventoryDeductBbc(orderNo,operateName,flag,exceptionFlag); 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.info("bbc订单"+orderNo+"审核失败，原因："+e.getMessage());
		}	
		if(jdugeFlag == null){
			 ret.setCode(CommonConstService.OK);
			 ret.setOrderNo(orderNo);
	  	     ret.setMsg("恭喜您，审核成功！");
		}else{
			 ret.setCode(CommonConstService.FAILED);
			 ret.setOrderNo(orderNo);
	  	     if(StringUtils.isNotBlank(jdugeFlag))
	  	     {
	  	    	 ret.setMsg(jdugeFlag);
	  	     }else{
	  	    	 ret.setMsg("订单审核失败！");
	  	    	 
	  	     }
		}
		return ret;
	}

    /**
     * BBC退货订单已收货
     * @param bbcUpdateOrderStatusDTO
     * @return 
     */
	@Override
	public CommonOutputDTO bbcSaleAfterOrderReceive(String saleAfterSubNo,String operateName) {
		// TODO Auto-generated method stub	       
		CommonOutputDTO ret = new CommonOutputDTO();
    	try {
    		ret = saleAfterOrderTransService.bbcOrderStoreReceive(saleAfterSubNo, operateName);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.info("bbc逆向订单"+saleAfterSubNo+"确认收货失败，原因："+e.getMessage());
		}
        return ret;
	}

	@Override
	public CommonOutputDTO bbcSaleAfterOrderAudit(String returnSubNo,String operateName) {
		// TODO Auto-generated method stub
		CommonOutputDTO ret = new CommonOutputDTO();
    	try {
    		ret = saleAfterOrderTransService.bbcReturnOrderAudit(returnSubNo, operateName);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.info("bbc逆向订单"+returnSubNo+"审核失败，原因："+e.getMessage());
		}
        return ret;
	}

    /**
     * BBC正向订单取消订单
     * @param orderNo
     * @return
     */
	@Override
	public ResultDTO bbcCancleOrder(String orderNo,String operateName) {
		// TODO Auto-generated method stub
		OrderMain order = orderMainService.findByOrderNo(orderNo);
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setResult(-1);
		if(order == null){
			resultDTO.setResultMessage("未找到订单信息");
			return resultDTO;
		}
		OrderOperateLog log = this.createOrderOperateLog(order,operateName);

		OrderMain newOrderMain = orderMainService.findByOrderNo(orderNo);
		
		String totalStauts = newOrderMain.getStatusTotal();
		CancelOrderConst  cancelScene = CancelOrderConst.CancelOrder_Scene_VALIDATED;
		if(OrderStatus.ORDER_VALIDATED.getCode().equals(totalStauts)){
			cancelScene = CancelOrderConst.CancelOrder_Scene_VALIDATED;
		}else if(OrderStatus.ORDER_INVENTORY_DEDUCT_FAILED.getCode().equals(totalStauts)){
		    cancelScene = CancelOrderConst.CancelOrder_Scene_InventoryFail;
		}else if(OrderStatus.ORDER_PAYING.getCode().equals(totalStauts)){
            cancelScene = CancelOrderConst.CancelOrder_Scene_Customer;
        }else{
			cancelScene = CancelOrderConst.CancelOrder_Scene_Saler;
		}

		try {			
			String orderSubNo = orderNoService.getDefaultOrderSubNoByOrderNo(orderNo);
			//调用取消订单
			resultDTO = saleAfterOrderService.cancelOrder(orderSubNo ,cancelScene);
			if(resultDTO.getResult()==-1){//取消失败
				
			}else{
				resultDTO.setResult(1);
				resultDTO.setResultMessage("订单取消成功!");
			}	
		} catch (Exception e) {
			resultDTO.setResult(-1);
			resultDTO.setResultMessage("系统异常：" + e.getMessage());
		}
		try {
			orderOperateLogService.save(log);	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.info("bbc订单"+orderNo+"取消失败，原因："+e.getMessage());
		}

		return resultDTO;
	}
	
	/**
	 * 封装日志
	 * @param order
	 * @param operateName
	 * @return
	 */
    private OrderOperateLog createOrderOperateLog(OrderMain order, String operateName){
    	OrderOperateLog log = new OrderOperateLog();
    	log.setIdOrder(order.getId());
    	log.setOrderNo(order.getOrderNo());
    	String orderSubNo = orderNoService.getDefaultOrderSubNoByOrderNo(order.getOrderNo());
    	log.setOrderSubNo(orderSubNo);
    	log.setDateCreated(new Date());
    	log.setCreatedBy(operateName);
    	log.setOperator(operateName);
		log.setReason("取消订单");
    	
    	return log;
    }

    /**
     * BBC逆向订单取消订单
     * @param orderSubNoR
     * @return
     */
	@Override
	public ResultDTO bbcCancleSaleAfterOrder(String orderSubNoR,String operateName) {
		// TODO Auto-generated method stub
		ResultDTO resultDTO = new ResultDTO();
		try {
			resultDTO = saleAfterOrderTransService.updateBbcCancelSaleAfterOrder(orderSubNoR, operateName);
			if(resultDTO.getResult() ==1){
				resultDTO.setResultMessage("恭喜您，取消成功！");
			}
			return resultDTO;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.info("bbc逆向订单"+orderSubNoR+"取消失败，原因："+e.getMessage());
		}
		resultDTO.setResult(-1);
		return resultDTO;
	}

	@Override
	public boolean updateOrderStatusBySaveProcess(String orderSubNo, String statusActionCode) {
		OrderStatusAction oas[]  = OrderStatusAction.values();
		OrderStatusAction params = null;
		for(OrderStatusAction oa : oas ){
			if(oa.getCode().equalsIgnoreCase(statusActionCode)){
				params = oa;
			}
		}
		if(null == params){
			logger.info("{}没有找到",statusActionCode);
			return false;
		}
		boolean flag = orderStatusService.saveProcess(orderSubNo, params, null, null, null);
		return flag;
	}

	@Override
	public CommonOutputDTO updateOrderStatus(String orderSubNo, String payStatus, String confirmStatus,String totalStatus) {
		// TODO Auto-generated method stub
		CommonOutputDTO ret = new CommonOutputDTO();
		ret.setOrderSubNo(orderSubNo);// 回传订单号
		try {
			orderMainService.updateOrderStatus(orderSubNo, payStatus, confirmStatus, totalStatus);
			ret.setCode(CommonConstService.OK);
			ret.setMsg("恭喜您，修改成功！");
			return ret;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		ret.setCode(CommonConstService.FAILED);
		ret.setMsg("系统异常，请稍后再试！");
		return ret;
	}
    
}


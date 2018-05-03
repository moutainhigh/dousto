package com.ibm.oms.service.business;

/**
 * @author pjsong
 *
 */
public interface OrderNoService {
	/**
	 * don't try to change this String unless big change happen
	 * 7.15日,袁改序列号7位到6位
	 */
	String orderIdAddOn = "000000";
	/**
	 * 订单号长度
	 */
//	int orderNoLength = 15;
	/**
	 * 子订单号长度
	 */
//	int orderSubNoLength = 17;
	/**
	 * 订单行号长度
	 */
//	int orderItemNoLength = 19;
	   /**
     * 历史订单号长度
     */
//    int orderNoLengthHistory = 13;
    /**
     * 历史子订单号长度
     */
//    int orderSubNoLengthHistory = 15;
    /**
     * 历史订单行号长度
     */
//    int orderItemNoLengthHistory = 17;
	/**
	 * 取当前yyyyMMdd时间6位,+orderId后面7位,不足7位前面补0;
	 * @param orderId
	 * @return
	 */
	String getOrderNoByOrderId(String orderId);


	/**
     * 根据主订单orderNo和子订单的当前序列，获取子订单no
     * @param orderNo 主订单No
     * @param index 子订单的当前序列
     * @return
     */
    String getOrderSubNoByOrderNo(String orderNo,int index);
    
    public String getOrderItemNoBySubOrderNo(String subOrderNo,int index);
    
	/**
	 * @param orderItemNo
	 * @param orderSubNo
	 * @return
	 */
	String getOrderNoByItemNo(String orderItemNo);


	/**
	 * @param orderSubNo
	 * @return
	 */
	String getOrderNoBySubNo(String orderSubNo);


	/**
	 * @param orderItemNo
	 * @return
	 */
	String getOrderSubNoByItemNo(String orderItemNo);

	/**创建缺省子订单号，用于在状态流转调用，或虚拟订单子订单号获取
	 * @param orderNo 
	 * @return **/
    String getDefaultOrderSubNoByOrderNo(String orderNo);
	
	
}

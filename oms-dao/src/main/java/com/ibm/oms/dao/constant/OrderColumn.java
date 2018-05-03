package com.ibm.oms.dao.constant;

/**
 * 
 * @author 
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class OrderColumn {
	
	
	/**
	 * 退款单
	 */
	public static final int ORDER_DRAWBACK = -2;
	/**
     * 逆向订单
     */
    public static final int ORDER_REVERSE = -1;

	/**
     * 所有订单
     */
    public static final int ORDER_ALL = 0;
    /**
     * 待确认订单
     */
    public static final int ORDER_NEED_CONFIRMING=1;
    /**
     * 待支付订单
     */
    public static final int ORDER_NEED_PAY=2;
    /**
     * 缺货订单
     */
    public static final int ORDER_STOCKOUT = 3;
    /**
     * 待出库订单
     */
    public static final int ORDER_LACKED = 4;
    /**
     * 待送货订单
     */
    public static final int ORDER_VALIDATED = 5;
    /**
     * 未完成订单
     */
    public static final int ORDER_UN_FINISHED = 6;
    /**
     * 已完成订单
     */
    public static final int ORDER_FINISHED = 7;
    /**
     * 已取消订单
     */
    public static final int ORDER_CALCELED = 8;
    /**
     * 虚拟订单
     */
    public static final int ORDER_VIRTUAL = 9;
    
    /**
     * 挂起订单
     */
    public static final int ORDER_SUSPENDED = 10;
    
    
    
    

}

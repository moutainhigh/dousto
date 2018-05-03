package com.ibm.oms.service.business;


/**
 * 订单拆分
 * @author ChaoWang
 *
 */
public interface OrderSplitService {
	
	//根据sku
	public static String SPLITTYPE_SKU = "sku";
	//根据指定sku 拆分 其他的为一单
	public static String SPLITTYPE_ASSIGN = "assignSku";
	//默认的会员等级
	public static String MEMBERVIPCARDLEVEL_DEFAULT = "1";
	
	/**
	 * @param subOrderNo subOrder订单号
	 * @param splitType  拆单方式
	 * @return
	 * @throws Exception
	 */
	public boolean handleOrderSplit(String subOrderNo,String splitType,String assignSku)throws Exception;
	
	
}

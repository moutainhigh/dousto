package com.ibm.oms.client.intf;

/**
 * 订单对SAP接口
 * @author ChaoWang
 *
 */
public interface IOrderSapClient {
	
	/**订单确认推向SAP
	 * @param o
	 * @return
	 */
	public Object orderConfirmPush2Sap(Object o);
}

package com.ibm.oms.rs.service;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 线下支付（门店）
 * @author GangYang
 *
 */
@Path("/offlinePay")
public interface OfflinePayOrderService {
	/**
	 * 下单支付
	 * @param jsonObj
	 * @throws Exception
	 */
	 @POST
	 @Path("/sendPay")
	 @Produces(MediaType.APPLICATION_JSON)
	public  String sendPay(String jsonObj) throws Exception;
    /**
	 * 下单支付查询
	 * @param jsonObj
	 * @throws Exception
	 */
	 @POST
	 @Path("/sendPayQuery")
	 @Produces(MediaType.APPLICATION_JSON)
	public  String sendPayQuery(String jsonObj) throws Exception;
	/**
	 * 退款
	 * @param jsonObj
	 * @throws Exception
	 */
	 @POST
	 @Path("/refundPay")
	 @Produces(MediaType.APPLICATION_JSON)
	public  String refundPay(String jsonObj) throws Exception;
	/**
	 * 退款查询
	 * @param jsonObj
	 * @throws Exception
	 */
	 @POST
	 @Path("/refundQuery")
	 @Produces(MediaType.APPLICATION_JSON)
	public  String refundQuery(String jsonObj) throws Exception;
	/**
	 * 撤销订单
	 * @param jsonObj
	 * @throws Exception
	 */
	 @POST
	 @Path("/cancelOrder")
	 @Produces(MediaType.APPLICATION_JSON)
	public  String cancelOrder(String jsonObj) throws Exception;
}

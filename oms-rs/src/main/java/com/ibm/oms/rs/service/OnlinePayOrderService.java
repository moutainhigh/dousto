package com.ibm.oms.rs.service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 线上支付（导购App,在线微信商城）
 * @author GangYang
 *
 */
@Path("/onlinePay")
public interface OnlinePayOrderService {
	/**
	 * 预下单支付（导购APP）
	 * @param jsonObj
	 * @throws Exception
	 */
	@POST
	@Path("/sendImprestPay")
	@Produces(MediaType.APPLICATION_JSON)
	public  String sendImprestPay(String jsonObj) throws Exception;
	
	/**
	 * 下单支付（微信在线商城）
	 * @param jsonObj
	 * @throws Exception
	 */
	@POST
	@Path("/sendCreatePay")
	@Produces(MediaType.APPLICATION_JSON)
	public  String sendCreatePay(String jsonObj) throws Exception;
	/**
	 * 下单支付(门店pos)
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
	/**
	 * 支付接收异步通知回调地址
	 * @param xmlObj
	 * @throws Exception
	 */
	 @POST
	 @Path("/payCallback")
	 @Produces(MediaType.APPLICATION_XML)
	public  String payOrderCallbackInfo(String xmlObj) throws Exception;
}

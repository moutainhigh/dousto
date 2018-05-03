
package com.ibm.oms.rs.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.ibm.oms.intf.intf.BtcOmsReturnChangeDTO;
import com.ibm.sc.rs.bean.ResponseObject;


@Path("/oms-retchg")
@Produces(MediaType.APPLICATION_JSON)
public interface BtcOmsReturnChangeOrderService {

    /**
     * btc退货请求处理
     * @param receive
     * @return
     */
    @POST
    @Path("/b2c-oms-receive-return-order")
	public ResponseObject createReturnOrder(BtcOmsReturnChangeDTO receive);
	
    /**
     * btc换货请求处理
     * @param receive
     * @return
     */
    @POST
    @Path("/b2c-oms-receive-change-order")
	public ResponseObject createChangeOrder(BtcOmsReturnChangeDTO receive);
    
    /**
     * btc用户取消订单
     * @param orderSubNo
     * @return
     */
    @GET
    @Path("/b2c-oms-cancel-order")
    public ResponseObject cancelOrder(@QueryParam("orderSubNo") String orderSubNo, @QueryParam("operator") String operator);
    
	
}
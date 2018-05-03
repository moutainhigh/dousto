package com.ibm.oms.rs.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.ibm.oms.intf.intf.BBCLogiDTO;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderDTO;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderOutputDTO;
import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.intf.intf.PosPayOutputDTO;

@Path("/btcoms")
@Produces(MediaType.APPLICATION_JSON)
public interface BtcOmsReceiveOrderService {
	/**接受btc销售订单**/
    @POST
    @Path("/b2-oms-receive-order")
    public BtcOmsReceiveOrderOutputDTO btcOmsReceiveOrderDTO(BtcOmsReceiveOrderDTO receive);

    /**BBC完成订单**/
    @GET
	@Path("/operate")
	public CommonOutputDTO bbcOperateByOrderNo(@QueryParam("orderSubNo") String orderSubNo, @QueryParam("operateCode") String operateCode, @QueryParam("sys") String sys);
    @POST
    @Path("/bbcLogistics")
    public CommonOutputDTO bbcLogistics(BBCLogiDTO bbcLogiDTO);
    @GET
    @Path("/pospay")
    public PosPayOutputDTO posPayByOrderNo(@QueryParam("orderNo") String orderNo);
    @GET
	@Path("/isOpen")
	public int isOpen();
}


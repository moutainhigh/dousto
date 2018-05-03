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
import com.ibm.oms.intf.intf.TmsOrderDTO;

@Path("/oms")
@Produces(MediaType.APPLICATION_JSON)
public interface OmsOrderInfoService {
	/**接受btc销售订单**/
    @GET
    @Path("/orderInfo")
    public TmsOrderDTO btcOmsReceiveOrderDTO(String orderSubNo);

}


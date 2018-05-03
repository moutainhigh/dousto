package com.ibm.oms.rs.service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ibm.oms.intf.intf.BtcPayDTO;
import com.ibm.oms.intf.intf.CommonOutputDTO;

@Path("/btcPayService")
@Produces(MediaType.APPLICATION_JSON)
public interface OrderReceivePaymentService {
	
	@POST
	@Path("/oms-receive-payment")
	public CommonOutputDTO createOrderPayment(BtcPayDTO payDto);

}

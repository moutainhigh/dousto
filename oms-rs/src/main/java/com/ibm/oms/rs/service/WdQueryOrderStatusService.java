package com.ibm.oms.rs.service;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ibm.oms.intf.intf.OMSStatusUpdateDTO;

@Path("/oms-wd-status")
@Produces(MediaType.APPLICATION_JSON)
public interface WdQueryOrderStatusService {
	/**查询订单状态**/
    @GET
    @Path("/QueryOrderStatus")
    public OMSStatusUpdateDTO queryOrderStatusDTO(@FormParam("orderSubNo") String orderSubNo,@FormParam("statusType") String statusType);

}


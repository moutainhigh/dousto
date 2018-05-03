package com.ibm.oms.rs.service;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.ibm.oms.intf.intf.WdOrderDTO;

@Path("/oms-wd")
@Produces(MediaType.APPLICATION_JSON)
public interface OmsQueryOrderInfoService {
	/**查询订单信息**/
    @GET
    @Path("/QueryOrderInfo")
    public WdOrderDTO wdOmsQueryOrderInfoDTO(@FormParam("aliasOrderNo") String aliasOrderNo);

}


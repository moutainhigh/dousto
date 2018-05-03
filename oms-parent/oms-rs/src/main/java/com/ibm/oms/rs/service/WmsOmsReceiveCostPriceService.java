package com.ibm.oms.rs.service;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ibm.oms.intf.intf.ResponseObjectDTO;
import com.ibm.oms.intf.intf.WmsReceiveCostPriceDTO;

@Path("/costPriceService")
@Produces(MediaType.APPLICATION_JSON)
public interface WmsOmsReceiveCostPriceService {

    /**
     * WMS将商品成本价推送订单中台
     * @param receive
     * @return
     */
    @POST
	@Path("/receiveCostPrice")
	public ResponseObjectDTO handlerOrderCostPrice(List<WmsReceiveCostPriceDTO> receive);
}

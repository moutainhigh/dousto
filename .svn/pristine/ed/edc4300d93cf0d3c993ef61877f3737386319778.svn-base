package com.ibm.oms.rs.service;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ibm.oms.intf.intf.ResponseObjectDTO;
import com.ibm.oms.intf.intf.WmsOmsReceiveLogisticsDTO;

@Path("/logisticsService")
@Produces(MediaType.APPLICATION_JSON)
public interface WmsOmsReceiveLogisticsService {

    /**
     * WMS订单库存状态推送订单中台
     * @param receive
     * @return
     */
    @POST
	@Path("/receiveLogistics")
	public ResponseObjectDTO handlerOrderLogisticsStatus(List<WmsOmsReceiveLogisticsDTO> receive);
	
}

package com.ibm.oms.rs.service;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ibm.oms.intf.intf.ResponseObjectDTO;
import com.ibm.oms.intf.intf.WmsOmsReceiveInspectionResultDTO;

@Path("/inspectService")
@Produces(MediaType.APPLICATION_JSON)
public interface WmsOmsReceiveInspectStatusService {
	
    /**
     * WMS商品质检信息推送订单中台
     * @param receive
     * @return
     */
    @POST
	@Path("/receiveInspectStatus")
	public ResponseObjectDTO handlerOrderInspectStatus(List<WmsOmsReceiveInspectionResultDTO> receive);

}

package com.ibm.oms.rs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.oms.intf.intf.ResponseObjectDTO;
import com.ibm.oms.intf.intf.WmsOmsReceiveInspectionResultDTO;
import com.ibm.oms.rs.service.WmsOmsReceiveInspectStatusService;
import com.ibm.oms.service.business.WmsOmsInspectStatusService;

@Component("wmsOmsReceiveInspectStatusService")
public class WmsOmsReceiveInspectStatusServiceImpl implements WmsOmsReceiveInspectStatusService {
	@Autowired
	WmsOmsInspectStatusService wmsOmsInspectStatusService;
	
	
	@Override
	@Deprecated
	public ResponseObjectDTO handlerOrderInspectStatus(List<WmsOmsReceiveInspectionResultDTO> receive) {
	
		return wmsOmsInspectStatusService.handlerUpdateOrderInspectStatus(receive);
	}

}

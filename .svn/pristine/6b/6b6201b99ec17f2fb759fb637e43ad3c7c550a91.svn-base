package com.ibm.oms.rs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.oms.intf.intf.ResponseObjectDTO;
import com.ibm.oms.intf.intf.WmsOmsReceiveLogisticsDTO;
import com.ibm.oms.rs.service.WmsOmsReceiveLogisticsService;
import com.ibm.oms.service.business.WmsOmsLogisticsStatusService;

@Component("wmsOmsReceiveLogisticsService")
public class WmsOmsReceiveLogisticsServiceImpl implements WmsOmsReceiveLogisticsService {


	@Autowired
	WmsOmsLogisticsStatusService wmsOmsLogisticsStatusService;

	@Override
	@Deprecated
	public ResponseObjectDTO handlerOrderLogisticsStatus(List<WmsOmsReceiveLogisticsDTO> receive) {
		
		return wmsOmsLogisticsStatusService.handlerUpdateOrderLogisticsStatus(receive);
	}

}

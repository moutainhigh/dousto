package com.ibm.oms.rs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.oms.intf.intf.ResponseObjectDTO;
import com.ibm.oms.intf.intf.WmsReceiveCostPriceDTO;
import com.ibm.oms.rs.service.WmsOmsReceiveCostPriceService;
import com.ibm.oms.service.business.WmsOmsCostPriceService;

@Component("wmsOmsReceiveCostPriceService")
public class WmsOmsReceiveCostPriceServiceImpl implements WmsOmsReceiveCostPriceService{
	@Autowired
	WmsOmsCostPriceService wmsOmsCostPriceService;
	
	
	@Override
	@Deprecated
	public ResponseObjectDTO handlerOrderCostPrice(List<WmsReceiveCostPriceDTO> receive) {
		return wmsOmsCostPriceService.handlerUpdateOrderCostPrice(receive);
	}
}

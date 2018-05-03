package com.ibm.oms.service.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beans.stock.constant.ClientConstant;
import com.ibm.oms.service.business.OmsWarehouseService;
import com.ibm.sc.service.sys.OptionService;

@Service("omsWarehouseService")
public class OmsWarehouseServiceImpl implements OmsWarehouseService{
	@Autowired
	OptionService optionService;
	
	@Override
	public String getWarehouseSourceByDefaultCode(String defaultCode) {
				return ClientConstant.ESTORE_WAREHOUSE_CODE;
	}

}

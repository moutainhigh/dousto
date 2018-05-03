package com.ibm.oms.rs.service.hessian.impl.order;

import com.ibm.oms.client.dto.order.*;
import com.ibm.oms.client.intf.IOrderWmsClient;
import com.ibm.oms.service.business.WmsOmsService;
import com.ibm.oms.service.business.impl.WmsOmsServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iOrderWmsClient")
public class OrderWmsClientImpl implements IOrderWmsClient{
	@Autowired
	@Qualifier("wmsOmsServiceImpl")
	private WmsOmsService wmsOmsService;
	@Override
	public ResponseBean updateSalesOrderBatch(UpdateOrderBatchBean updateOrderBatchBean) {
		return wmsOmsService.updateSalesOrderBatch(updateOrderBatchBean);
	}

	@Override
	public ResponseBean updateSalesOrder(UpdateReturnPfAsnBean updateReturnPfAsnBean) {
		return wmsOmsService.updateSalesOrder(updateReturnPfAsnBean);
	}

	@Override
	public ResponseBean receiveStatusFromWMS(OmsOrderStatuDTO omsOrderStatuDTO) {
		ResponseBean responseBean = wmsOmsService.receiveStatusFromWMS(omsOrderStatuDTO);
		return responseBean;
	}

	@Override
	public ResponseBean Wms2OmsShipOrderConfirm(OmsDeliveryOrderMain omsDeliveryOrderMain) {
		ResponseBean responseBean = wmsOmsService.Wms2OmsShipOrderConfirm(omsDeliveryOrderMain);
		return responseBean;
	}

	@Override
	public ResponseBean Wms2OmsStorageOrderConfirm(StoreOrderMain storeOrderMain) {
		ResponseBean responseBean = wmsOmsService.Wms2OmsStorageOrderConfirm(storeOrderMain);
		return responseBean;
	}

}

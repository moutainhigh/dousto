package com.ibm.oms.service.business.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import com.ibm.interf.ws.intf.wms.StockWsmHsService;
import com.ibm.interf.ws.intf.wms.dto.StockBean;
import com.ibm.interf.ws.intf.wms.dto.StockBeanItem;
import com.ibm.interf.ws.intf.wms.dto.StockBeanItemList;
import com.ibm.oms.client.dto.order.*;
import com.ibm.oms.domain.persist.*;
import com.ibm.oms.service.TransportAreaService;
import com.ibm.oms.service.util.EmptyUtils;
import com.ibm.stock.dto.WarehouseShopData;
import com.ibm.stock.intf.SkuStockSearchService;
import com.ibm.sup.rs.bean.TransportAreaBean;
import com.ibm.sup.rs.service.TransportAreaRsService;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Synchronize;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ibm.interf.ws.intf.dto.ResponseBean;
import com.ibm.interf.ws.intf.wms.OrderWmsHsService;
import com.ibm.oms.dao.intf.WmsOmsOrderDao;
import com.ibm.oms.domain.bean.hang.StoreInf;
import com.ibm.oms.service.business.WmsOmsService;
import com.ibm.oms.service.util.AsyncHelper;
import com.ibm.sc.service.impl.BaseServiceImpl;
import com.ibm.sc.util.BeanUtils;

import net.sf.json.JSONObject;

@Service("wmsOmsServiceImpl")
public class WmsOmsServiceImpl extends BaseServiceImpl<OrderReport,Long> implements WmsOmsService {
	private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	StockWsmHsService stockWsmHsService;
    @Autowired
	TransportAreaRsService transportAreaRsService;
	@Autowired
	WmsOmsOrderDao wmsOmsOrderDao;
    @Autowired
    SkuStockSearchService skuStockSearchService;
	@Autowired
	AsyncHelper asyncHelper;
	@Autowired
    public void setWmsOmsOrderDao(WmsOmsOrderDao wmsOmsOrderDao) {
        super.setBaseDao(wmsOmsOrderDao);
    }
	@Autowired
	@Qualifier("orderWmsHsService")
	private OrderWmsHsService orderWmsHsService;

	@Override
	@Transactional
	public ResponseBean orderSalesCancel(String orderNo) {
		OrderSalesCancel orderSalesCancel = wmsOmsOrderDao.orderSalesCancel(orderNo);
		StockBean stockBean = new StockBean();
		stockBean = initStockBean(orderSalesCancel,stockBean);
		try{
			ResponseBean responseBean = stockWsmHsService.importStockWmsData(stockBean);
			return responseBean;
		}catch (Exception e){
			logger.info("调用接口 importStockWmsData 出现错误 ===> "+e.getMessage());
			return null;
		}
	}
	@Override
	public com.ibm.oms.client.dto.order.ResponseBean updateSalesOrderBatch(UpdateOrderBatchBean updateOrderBatchBean) {
		com.ibm.oms.client.dto.order.ResponseBean responseBean = new com.ibm.oms.client.dto.order.ResponseBean();
		try{
			JSONObject jsonObject = JSONObject.fromObject(updateOrderBatchBean);
			System.out.println("updateOrderBatchBean===> "+ jsonObject.toString());
			WholesaleOrderStatus wholesaleOrderStatus = copyClass(updateOrderBatchBean);
			Long id = wmsOmsOrderDao.insertWholesaleOrderStatus(wholesaleOrderStatus);
			List<WholesaleCaseCode> wholesaleCaseCodes = null;
			List<WholesaleReturnProduct> wholesaleReturnProducts = null;
			if(EmptyUtils.isNotEmpty(updateOrderBatchBean.getProducts())&&EmptyUtils.isNotEmpty(updateOrderBatchBean.getProducts().getProduct())){
				wholesaleReturnProducts = copyClass(updateOrderBatchBean.getProducts().getProduct(),id);
			}
			if(EmptyUtils.isNotEmpty(updateOrderBatchBean.getCases())&&EmptyUtils.isNotEmpty(updateOrderBatchBean.getCases().getCases()))
			if(EmptyUtils.isNotEmpty(wholesaleCaseCodes)){
				wmsOmsOrderDao.insertWholesaleCaseCodeBatch(wholesaleCaseCodes);
			}
			if(EmptyUtils.isNotEmpty(wholesaleReturnProducts)){
				wmsOmsOrderDao.insertWholesaleReturnProductBatch(wholesaleReturnProducts);
			}
			SalesOrderStatus salesOrderStatus = new SalesOrderStatus();
			if(updateOrderBatchBean.getAsnStatus().equalsIgnoreCase("FULFILLED")){
				salesOrderStatus.setOrderNo(updateOrderBatchBean.getWmsBillCode());
				salesOrderStatus.setOrderStatus("0250");
				wmsOmsOrderDao.updateOrderMainOrderStatus(salesOrderStatus);
			}
			responseBean.setStatus("SUCCASS");
		}catch (Exception e){
			responseBean.setStatus("ERROR");
			responseBean.setMessage(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}finally {
			return responseBean;
		}
	}
	@Override
	public com.ibm.oms.client.dto.order.ResponseBean updateSalesOrder(UpdateReturnPfAsnBean updateReturnPfAsnBean) {
		com.ibm.oms.client.dto.order.ResponseBean responseBean = new com.ibm.oms.client.dto.order.ResponseBean();
		JSONObject jsonObject = JSONObject.fromObject(updateReturnPfAsnBean);
		System.out.println("updateReturnPfAsnBean===> " + jsonObject.toString());
		SalesOrderStatus salesOrderStatus = new SalesOrderStatus();
		salesOrderStatus = copyClass(updateReturnPfAsnBean,salesOrderStatus);
        if(updateReturnPfAsnBean.getOrderStatus().equalsIgnoreCase("DELIVERED")){
        	salesOrderStatus.setOrderStatus("0170");
			wmsOmsOrderDao.updateOrderMainOrderStatus(salesOrderStatus);
		}
		Long id = wmsOmsOrderDao.insertSalesOrderStatus(salesOrderStatus);
		responseBean.setStatus("SUCCESS");
		return responseBean;
	}

	@Override
	public com.ibm.oms.client.dto.order.ResponseBean receiveStatusFromWMS(OmsOrderStatuDTO omsOrderStatuDTO) {
		com.ibm.oms.client.dto.order.ResponseBean responseBean = new com.ibm.oms.client.dto.order.ResponseBean();
		Integer i = wmsOmsOrderDao.receiveStatusFromWMS(omsOrderStatuDTO);
		responseBean.setStatus("SUCCESS");
		return responseBean;
	}
	@Override
	public com.ibm.oms.client.dto.order.ResponseBean Wms2OmsShipOrderConfirm(OmsDeliveryOrderMain omsDeliveryOrderMain) {
		com.ibm.oms.client.dto.order.ResponseBean responseBean = new com.ibm.oms.client.dto.order.ResponseBean();
		Integer i = wmsOmsOrderDao.Wms2OmsShipOrderConfirm(omsDeliveryOrderMain);
		responseBean.setStatus("SUCCESS");
		return responseBean;
	}

	@Override
	public com.ibm.oms.client.dto.order.ResponseBean Wms2OmsStorageOrderConfirm(StoreOrderMain storeOrderMain) {
		com.ibm.oms.client.dto.order.ResponseBean responseBean = new com.ibm.oms.client.dto.order.ResponseBean();
		Integer i= wmsOmsOrderDao.Wms2OmsStorageOrderConfirm(storeOrderMain);
		responseBean.setStatus("SUCCESS");
		return responseBean;
	}

	@Override
	@Transactional
	public ResponseBean oms2WmsShipNotify(String orderNo) {
		com.ibm.oms.domain.bean.hang.OrderBean orderBeanDTO = wmsOmsOrderDao.getOms2WmsShipNotify(orderNo);
		JSONObject js = JSONObject.fromObject(orderBeanDTO);
		System.out.println("orderBeanDTO===> "+ js.toString());
		String warehouseNo =  orderBeanDTO.getBranchCode();
        WarehouseShopData warehouseShopData = skuStockSearchService.getStockWarehouseShopByNo(warehouseNo);
		StoreInf storeInf = new StoreInf();
        storeInf.setProvideAddress(warehouseShopData.getProvideAddress());
        storeInf.setOrderNo(orderBeanDTO.getBillNo());
        storeInf.setShipFromArea(warehouseShopData.getShipFromArea());
        storeInf.setShipFromCity(warehouseShopData.getShipFromCity());
        storeInf.setShipFromToProvince(warehouseShopData.getShipFromToProvince());
        storeInf.setShipStoreName(warehouseShopData.getStockStatus());
        storeInf.setWarehouseNo(warehouseShopData.getWarehouseNo());
		Integer i = wmsOmsOrderDao.upadteOrderItemByOrderNo(storeInf);
		i = wmsOmsOrderDao.upadteOrderSubByOrderNo(storeInf);
		i = wmsOmsOrderDao.upadteOrderMainByOrderNo(storeInf);
		com.ibm.interf.ws.intf.wms.dto.OrderBean orderBean = new com.ibm.interf.ws.intf.wms.dto.OrderBean();
		orderBeanDTO = wmsOmsOrderDao.getOms2WmsShipNotify(orderNo);
		String code = orderBeanDTO.getShipToProvince();
		if(StringUtils.isNotEmpty(code)){
			String s= code.substring(0,1)+"0000";
			TransportAreaBean transportAreaBean = transportAreaRsService.getByCode(s);
			orderBeanDTO.setShipToProvince(transportAreaBean.getAreaName());
			s= code.substring(0,3)+"00";
			transportAreaBean = transportAreaRsService.getByCode(s);
			orderBeanDTO.setShipToCity(transportAreaBean.getAreaName());
			transportAreaBean = transportAreaRsService.getByCode(code);
			orderBeanDTO.setShipToArea(transportAreaBean.getAreaName());
		}
		js = JSONObject.fromObject(orderBeanDTO);
		System.out.println("orderBeanDTO===> "+ js.toString());
		try {
			BeanUtils.copyProperties(orderBean,orderBeanDTO);
			ResponseBean responseBean = orderWmsHsService.importOrderWmsData(orderBean);
			return responseBean;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ResponseBean getOms2WmsCancelOrderNotify(String OrderMainId) {
		com.ibm.interf.ws.intf.wms.dto.OrderCanceBean orderCanceBean =new com.ibm.interf.ws.intf.wms.dto.OrderCanceBean();

		com.ibm.oms.domain.bean.hang.OrderCanceBean orderCanceBeanDTO = wmsOmsOrderDao.getOms2WmsCancelOrderNotify(OrderMainId);
		JSONObject js = JSONObject.fromObject(orderCanceBeanDTO);
		System.out.println("js===> "+ js.toString());
		try {
			BeanUtils.copyProperties(orderCanceBeanDTO, orderCanceBean);
			ResponseBean responseBean = orderWmsHsService.importOrderCancelWmsData(orderCanceBean);
			return responseBean;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public SalesOrderStatus copyClass(UpdateReturnPfAsnBean updateReturnPfAsnBean,SalesOrderStatus salesOrderStatus) {
        salesOrderStatus.setOrderNo(updateReturnPfAsnBean.getOrderCode());
        salesOrderStatus.setOrderStatus(updateReturnPfAsnBean.getOrderStatus());
        salesOrderStatus.setOperator(updateReturnPfAsnBean.getOperator());
        if(StringUtils.isNotEmpty(updateReturnPfAsnBean.getOperatorTime())) {
            try {
                salesOrderStatus.setOperatorTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(updateReturnPfAsnBean.getOperatorTime()));
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("时间转化错误");
            }
        }
        salesOrderStatus.setLogisticsProviderCode(updateReturnPfAsnBean.getLogisticsProviderCode());
        salesOrderStatus.setShippingOrderNo(updateReturnPfAsnBean.getShippingOrderNo());
        salesOrderStatus.setNote(updateReturnPfAsnBean.getNote());
        if(StringUtils.isNotEmpty(updateReturnPfAsnBean.getWeight())){
            salesOrderStatus.setWeight(Integer.parseInt(updateReturnPfAsnBean.getWeight()));
        }
        salesOrderStatus.setVolume(updateReturnPfAsnBean.getVolume());
        salesOrderStatus.setExceptionCode(updateReturnPfAsnBean.getExceptionCode());
	    return salesOrderStatus;
    }
	public WholesaleOrderStatus copyClass(UpdateOrderBatchBean updateOrderBatchBean) {
		WholesaleOrderStatus wholesaleOrderStatus = new WholesaleOrderStatus();
		wholesaleOrderStatus.setWmsBillCode(updateOrderBatchBean.getWmsBillCode());
		wholesaleOrderStatus.setWareHouseCode(updateOrderBatchBean.getWarehouseCode());
		wholesaleOrderStatus.setAsnCode(updateOrderBatchBean.getAsnCode());
		wholesaleOrderStatus.setAsnStatus(updateOrderBatchBean.getAsnStatus());
		wholesaleOrderStatus.setIsAsnFinished(updateOrderBatchBean.getIsAsnFinished());
		wholesaleOrderStatus.setNote(updateOrderBatchBean.getNote());
		wholesaleOrderStatus.setOperator(updateOrderBatchBean.getOperator());
		if(updateOrderBatchBean.getOperatorTime()!=null&&"".equals(updateOrderBatchBean.getOperatorTime())){
			try {
				wholesaleOrderStatus.setOperatorTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(updateOrderBatchBean.getOperatorTime()));
			}catch (Exception e){
				e.printStackTrace();
				throw new RuntimeException("时间转化错误");
			}
		}
		wholesaleOrderStatus.setLogisticsProviderCode(updateOrderBatchBean.getLogisticsProviderCode());
		wholesaleOrderStatus.setShippingOrderNo(updateOrderBatchBean.getLogisticsProviderCode());
		wholesaleOrderStatus.setExceptionCode(updateOrderBatchBean.getExceptionCode());
		wholesaleOrderStatus.setCallBack(updateOrderBatchBean.getExceptionCode());
		return wholesaleOrderStatus;
	}
	public List<WholesaleCaseCode> copyClass(List<Case> cases,Long id,String Code) {
		List<WholesaleCaseCode> wholesaleCaseCodes = new ArrayList<WholesaleCaseCode>();
		for(Case caseOne:cases){
			WholesaleCaseCode wholesaleCaseCode = new WholesaleCaseCode();
			wholesaleCaseCode.setQtySku(caseOne.getQtySku()) ;
			wholesaleCaseCode.setWosId(id) ;
			wholesaleCaseCode.setColorCode(caseOne.getColorCode()) ;
			wholesaleCaseCode.setStyleCode(caseOne.getStyleCode()) ;
			wholesaleCaseCode.setQtyCase(caseOne.getQtyCase()) ;
			wholesaleCaseCode.setAssortmentCode(caseOne.getAssortmentCode()) ;
		}
		return wholesaleCaseCodes;
	}
	public List<WholesaleReturnProduct> copyClass(List<Product> products,Long id) {
		List<WholesaleReturnProduct> wholesaleReturnProducts = new ArrayList<WholesaleReturnProduct>();
		for(Product product:products){
			WholesaleReturnProduct wholesaleReturnProduct = new WholesaleReturnProduct();
			wholesaleReturnProduct.setSkuCode(product.getSkuCode());
			wholesaleReturnProduct.setNormalQuantity(product.getDefectiveQuantity()!=null&&"".equals(product.getDefectiveQuantity())?Integer.parseInt(product.getNormalQuantity()):1);
			wholesaleReturnProduct.setDefectiveQuantity(product.getDefectiveQuantity()!=null&&"".equals(product.getDefectiveQuantity())?Integer.parseInt(product.getDefectiveQuantity()):0);
			wholesaleReturnProduct.setBoxNo(product.getBoxNo());
			wholesaleReturnProduct.setWosId(id);
			wholesaleReturnProducts.add(wholesaleReturnProduct);

		}
		return wholesaleReturnProducts;
	}
	public StockBean initStockBean(OrderSalesCancel orderSalesCancel,StockBean stockBean){
		stockBean.setConsignor(orderSalesCancel.getConsignor());
		stockBean.setBillNo(orderSalesCancel.getBillNo());
		stockBean.setBranchCode(orderSalesCancel.getBranchCode());
		stockBean.setBillType(orderSalesCancel.getBillType());
		stockBean.setPoType(orderSalesCancel.getPoType());
		stockBean.setOperateDate(orderSalesCancel.getOperateDate());
		stockBean.setShipBillID(orderSalesCancel.getShipBillID());
		stockBean.setShipVendorID(orderSalesCancel.getShipVendorID());
		stockBean.setRTReason(orderSalesCancel.getRTReason());
		stockBean.setRemark(orderSalesCancel.getRemark());
		StockBeanItemList stockBeanItemList = new StockBeanItemList();
		List<StockBeanItem> list = new ArrayList<StockBeanItem>();
		List<OrderSalesItemCancel> orderSalesItemCancels = orderSalesCancel.getItems();
		for(OrderSalesItemCancel orderSalesItemCancel:orderSalesItemCancels){
			StockBeanItem stockBeanItem = new StockBeanItem();
			stockBeanItem.setBillNo(orderSalesItemCancel.getBillNo());
			stockBeanItem.setSeqno(orderSalesItemCancel.getSeqno());
			stockBeanItem.setItemId(orderSalesItemCancel.getItemId());
			stockBeanItem.setItemName(orderSalesItemCancel.getItemName());
			stockBeanItem.setItemCode(orderSalesItemCancel.getItemCode());
			stockBeanItem.setBarCode(orderSalesItemCancel.getBarCode());
			stockBeanItem.setQty(orderSalesItemCancel.getQty());
			stockBeanItem.setPrice(orderSalesItemCancel.getPrice());
			list.add(stockBeanItem);
		}
		stockBeanItemList.setOrderItem(list);
		stockBean.setOrderItemList(stockBeanItemList);
		return stockBean;
	}
}

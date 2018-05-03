package com.ibm.oms.rs.service.hessian.impl.order;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.client.constant.InterfaceConst;
import com.ibm.oms.client.dto.CategoryIntfDTO;
import com.ibm.oms.client.dto.QueryCategoryDTO;
import com.ibm.oms.client.dto.order.OrderHeaderClientDTO;
import com.ibm.oms.client.dto.order.OrderInvoiceClientDTO;
import com.ibm.oms.client.dto.order.OrderItemClientDTO;
import com.ibm.oms.client.dto.order.OrderMainClientDTO;
import com.ibm.oms.client.dto.order.OrderPayClientDTO;
import com.ibm.oms.client.dto.order.OrderPromotionClientDTO;
import com.ibm.oms.client.dto.order.OrderQueryClientDTO;
import com.ibm.oms.client.dto.order.OrderSplitTransferReturnClientDTO;
import com.ibm.oms.client.dto.order.OrderSubClientDTO;
import com.ibm.oms.client.dto.order.QmOrderItemDTO;
import com.ibm.oms.client.dto.order.QmOrderMainDTO;
import com.ibm.oms.client.dto.order.QmResponseBean;
import com.ibm.oms.client.dto.order.ReceiveHangOrderOutputDTO;
import com.ibm.oms.client.dto.order.create.OmsReceiveOrderOutputClientDTO;
import com.ibm.oms.client.dto.order.create.OrderMainCreateClientDTO;
import com.ibm.oms.client.dto.order.create.OrderPayCreateClientDTO;
import com.ibm.oms.client.dto.order.create.refactor.ReceiveOrderMainDTO;
import com.ibm.oms.client.dto.result.HessianResult;
import com.ibm.oms.client.intf.IOrderClient;
import com.ibm.oms.domain.persist.HangOrderInvoice;
import com.ibm.oms.domain.persist.HangOrderItem;
import com.ibm.oms.domain.persist.HangOrderMain;
import com.ibm.oms.domain.persist.HangOrderPay;
import com.ibm.oms.domain.persist.HangOrderPromotion;
import com.ibm.oms.domain.persist.HangOrderSub;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderRetChgItem;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.intf.constant.OrderMainConst;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderDTO;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderOutputDTO;
import com.ibm.oms.intf.intf.HangOrderReceiveOrderDTO;
import com.ibm.oms.intf.intf.inner.HangOrderInvoiceDTO;
import com.ibm.oms.intf.intf.inner.HangOrderItemDTO;
import com.ibm.oms.intf.intf.inner.HangOrderMainDTO;
import com.ibm.oms.intf.intf.inner.HangOrderPayDTO;
import com.ibm.oms.intf.intf.inner.HangOrderPromotionDTO;
import com.ibm.oms.intf.intf.inner.HangOrderSubDTO;
import com.ibm.oms.intf.intf.inner.OrderMainDTO;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderRetChgItemService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.oms.service.business.HangOrderCreateService;
import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.oms.service.business.OrderGuideService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.OrderStatusTransferReturnService;
import com.ibm.oms.service.business.SaleAfterOrderService;
import com.ibm.oms.service.business.SubbmitOrderService;
import com.ibm.oms.service.business.SubbmitOrderValidateService;
import com.ibm.oms.service.util.CommonConstService;
import com.ibm.oms.service.util.CommonUtilService;
import com.ibm.oms.service.util.EmptyUtils;
import com.ibm.oms.service.util.ExceptionUtil;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.util.BeanUtils;

import net.sf.json.JSON;

@Repository("iorderClient")
public class OrderClientImpl implements IOrderClient {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	OrderNoService orderNoService;
	@Autowired
	private OrderGuideService orderGuideService;
	@Autowired
	private OrderMainService orderMainService;
	@Autowired
	private HangOrderCreateService hangOrderCreateService;
	@Autowired
	private OrderStatusTransferReturnService orderStatusTransferReturnService;
	@Autowired
	@Qualifier("orderCreateService")
	private OrderCreateService orderCreateService;
	@Autowired
	@Qualifier("orderCreateOffline")
	private OrderCreateService orderCreateOfflineService;
	@Resource
	SaleAfterOrderService saleAfterOrderService;
	@Autowired
	private OrderItemService orderItemService;

	@Autowired
	private SubbmitOrderService subbmitOrderService;
	@Autowired
	SubbmitOrderValidateService subbmitOrderValidateService;

	@Autowired
	private OrderSubService orderSubService;
	@Autowired
	private OrderRetChgItemService orderRetChgItemService;
	@Autowired
	CommonUtilService commonUtilService;
	
	@Override
	public QmResponseBean createQmOrder(QmOrderMainDTO qmOrderMainDTO) {
		QmResponseBean qmResponseBean = new QmResponseBean();
		JSON js  = net.sf.json.JSONObject.fromObject(qmOrderMainDTO);
		logger.info("qmOrderMainDTO ----> " + js.toString());
		if(EmptyUtils.isNotEmpty(qmOrderMainDTO)){
			//判断传入订单为已发货的销售订单 还是已退货完成的退货单
			OrderMain orderMain = new OrderMain();
			if(qmOrderMainDTO.getOrder_type() == 30l){
				orderMain.setStatusTotal("0170");
				Long id = insertOrderMain(orderMain,qmOrderMainDTO);
				qmResponseBean.setOrder_id(id);
				qmResponseBean.setIs_success(true);
				qmResponseBean.setResult_desc("成功");
				return qmResponseBean;
			}
			if(qmOrderMainDTO.getOrder_type() == 30l){
				orderMain.setStatusTotal("0280");
				Long id = insertOrderMain(orderMain,qmOrderMainDTO);
				qmResponseBean.setOrder_id(id);
				qmResponseBean.setIs_success(true);
				qmResponseBean.setResult_desc("成功");
				return qmResponseBean;
			}
		}
		qmResponseBean.setResult_desc("传入失败,传入数据为 null");
		qmResponseBean.setIs_success(false);
		return qmResponseBean;
	}


	@Override
	public CategoryIntfDTO queryCategorySales(QueryCategoryDTO queryCategoryDTO) {
		if(EmptyUtils.isNotEmpty(queryCategoryDTO)){
			CategoryIntfDTO categoryIntfDTO = orderGuideService.queryCategorySales(queryCategoryDTO);
			return categoryIntfDTO;
		}
		return null;
	}
	@Override
	public String createOrder(String receiveOrderClientDTO) {
		commonUtilService.logInfoObjectToJson("IOrderClient-->createOrder[bs]-->params", logger, receiveOrderClientDTO);
		ObjectMapper mapper = new ObjectMapper();
		OmsReceiveOrderOutputClientDTO ooc = new OmsReceiveOrderOutputClientDTO();
		BtcOmsReceiveOrderDTO rc = null;
		String itemSnapshotStr = null;
		BtcOmsReceiveOrderOutputDTO bo = new BtcOmsReceiveOrderOutputDTO();
		try {
			rc = mapper.readValue(receiveOrderClientDTO.trim(), BtcOmsReceiveOrderDTO.class);
			commonUtilService.logInfoObjectToJson("IOrderClient-->createOrder-->params", logger, receiveOrderClientDTO);
			OrderCreateService oc = buildOrderCreateService(rc);
			bo = oc.createOrder(rc);
			// ooc = buildOmsReceiveOrderOutputClientDTO(bo);
			commonUtilService.logInfoObjectToJson("IOrderClient-->createOrder[bs]-->result", logger, buildResponse(mapper, bo));
			return buildResponse(mapper, bo);
		} catch (JsonParseException e) {
			ooc.setMessage(ExceptionUtil.stackTraceToString(e).toString());
			ooc.setSucceed(InterfaceConst.COMMON_RESPONSE_FAIL.getCode());
			logger.info(ExceptionUtil.stackTraceToString(e).toString());
			return buildResponse(mapper, bo);
		} catch (JsonMappingException e) {
			ooc.setMessage(ExceptionUtil.stackTraceToString(e).toString());
			ooc.setSucceed(InterfaceConst.COMMON_RESPONSE_FAIL.getCode());
			logger.info(ExceptionUtil.stackTraceToString(e).toString());
			return buildResponse(mapper, bo);
		} catch (IOException e) {
			ooc.setMessage(ExceptionUtil.stackTraceToString(e).toString());
			ooc.setSucceed(InterfaceConst.COMMON_RESPONSE_FAIL.getCode());
			logger.info(ExceptionUtil.stackTraceToString(e).toString());
			return buildResponse(mapper, bo);
		}

	}

	private String buildResponse(ObjectMapper mapper, BtcOmsReceiveOrderOutputDTO ooc) {
		try {
			return mapper.writeValueAsString(ooc);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block\
			e.printStackTrace();
		}
		return "";
	}

	private OmsReceiveOrderOutputClientDTO buildOmsReceiveOrderOutputClientDTO(BtcOmsReceiveOrderOutputDTO bo) {
		OmsReceiveOrderOutputClientDTO ooc = new OmsReceiveOrderOutputClientDTO();

		org.springframework.beans.BeanUtils.copyProperties(bo, ooc);
		return ooc;
	}

	@Override
	public String updateOrder(String orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delOrder(String orders) {
		// TODO Auto-generated method stub
		return null;
	}

	// 增加订单列表与订单详情的查询 YUSL 2018/2/6
	@Override
	public Pager<OrderMainClientDTO> findOrderList(OrderQueryClientDTO OrderQueryDTO, Pager pager) throws Exception {
		pager = orderMainService.findOrderList(OrderQueryDTO, pager);
		return pager;

	}

	@Override
	public OrderMainClientDTO findOrderDetails(String OrderNo) throws Exception {
		OrderMainClientDTO omc = orderMainService.findOrderDetails(OrderNo);
		return omc;
	}

	@Override
	public String updateOrderStatus(String orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String calculationOrdePrice(String orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String orderHang(String orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String orderHangSearch(String orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String orderReceiptAddressSearch(String orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String returnWMSNo(String orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createReturnOrder(String orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findReturnOrderList(String orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findReturnOrderDetails(String orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findReturnOrderStatus(String orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String returnReason(String orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findRefundHistoryInfo(String orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findReturnOrderCount(String orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String cancelReturnOrder(String orders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findWmsHistoryInfo(String orders) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ibm.oms.client.intf.IOrderClient#OrderSuspension(java.lang.String)
	 */
	@Override
	public void OrderSuspension(String orderNo) {
		orderMainService.updateOrderSuspension(orderNo);
	}

	@Override
	public ReceiveHangOrderOutputDTO TempHangOrderCreate(OrderHeaderClientDTO orderHeaderClientDTO) {
		ReceiveHangOrderOutputDTO r = new ReceiveHangOrderOutputDTO();
		HangOrderReceiveOrderDTO hangOrderReceiveOrderDTO = new HangOrderReceiveOrderDTO();
		// 封装Bean
		List<HangOrderMainDTO> list = getHangOrderMainList(orderHeaderClientDTO);
		hangOrderReceiveOrderDTO.setOmDTO(list);

		BtcOmsReceiveOrderOutputDTO createHangOrder = hangOrderCreateService.createHangOrder(hangOrderReceiveOrderDTO);
		r.setMessage(createHangOrder.getMessage());
		r.setSucceed(createHangOrder.getSucceed());
		return r;

	}
	
	
	
	
	
	
	
	@Override
	public ReceiveHangOrderOutputDTO TempHangOrderUpdate(OrderHeaderClientDTO orderHeaderClientDTO) {
		ReceiveHangOrderOutputDTO r = new ReceiveHangOrderOutputDTO();
		HangOrderReceiveOrderDTO hangOrderReceiveOrderDTO = new HangOrderReceiveOrderDTO();

		// 封装Bean
		List<HangOrderMainDTO> list = getHangOrderMainList(orderHeaderClientDTO);
		hangOrderReceiveOrderDTO.setOmDTO(list);

		BtcOmsReceiveOrderOutputDTO updateHangOrder = hangOrderCreateService.updateHangOrder(hangOrderReceiveOrderDTO);

		r.setSucceed(updateHangOrder.getSucceed());
		r.setMessage(updateHangOrder.getMessage());

		return r;
	}

	// 封装HangOrderMainDto
	public List<HangOrderMainDTO> getHangOrderMainList(OrderHeaderClientDTO orderHeaderClientDTO) {
		List<HangOrderMainDTO> list = new ArrayList<HangOrderMainDTO>();
		HangOrderMainDTO hom = new HangOrderMainDTO();
		try {
			BeanUtils.copyProperties(hom, orderHeaderClientDTO);
			List<OrderSubClientDTO> orderSubDTOS = orderHeaderClientDTO.getOrderSubDTOS();
			// 拷贝子订单
			List<HangOrderSubDTO> oslist = new ArrayList<HangOrderSubDTO>();
			for (OrderSubClientDTO oscDTO : orderSubDTOS) {
				HangOrderSubDTO osDTO = new HangOrderSubDTO();
				BeanUtils.copyProperties(osDTO, oscDTO);

				// 拷贝行订单
				List<HangOrderItemDTO> orderItemDTO = new ArrayList<HangOrderItemDTO>();
				for (OrderItemClientDTO osi : oscDTO.getOrderItemDTO()) {
					HangOrderItemDTO oi = new HangOrderItemDTO();
					BeanUtils.copyProperties(oi, osi);
					orderItemDTO.add(oi);
				}
				osDTO.setOiDTOs(orderItemDTO);
				// 拷贝发票
				HangOrderInvoiceDTO hoiDTO = new HangOrderInvoiceDTO();
				BeanUtils.copyProperties(hoiDTO, oscDTO.getOrderInvoiceDTO());
				osDTO.setHangOrderInvoice(hoiDTO);

				oslist.add(osDTO);
			}
			hom.setOsDTOs(oslist);

			// 拷贝订单支付
			List<OrderPayClientDTO> orderPayDTOS = orderHeaderClientDTO.getOrderPayDTOS();
			List<HangOrderPayDTO> oplist = new ArrayList<HangOrderPayDTO>();
			for (OrderPayClientDTO opDTO : orderPayDTOS) {
				HangOrderPayDTO hop = new HangOrderPayDTO();
				BeanUtils.copyProperties(hop, opDTO);
				oplist.add(hop);
			}
			hom.setOrderPayDTOs(oplist);

			// 拷贝订单促销
			List<OrderPromotionClientDTO> oProDTO = orderHeaderClientDTO.getOrderPromotionDTOS();
			List<HangOrderPromotionDTO> oprolist = new ArrayList<HangOrderPromotionDTO>();
			for (OrderPromotionClientDTO opDTO : oProDTO) {
				HangOrderPromotionDTO hop = new HangOrderPromotionDTO();
				BeanUtils.copyProperties(hop, opDTO);
				oprolist.add(hop);
			}
			hom.setOpDTOs(oprolist);

			List<HangOrderPayDTO> hoplist = new ArrayList<HangOrderPayDTO>();
			for (OrderPayClientDTO hopDTO : orderPayDTOS) {
				HangOrderPayDTO hop = new HangOrderPayDTO();
				BeanUtils.copyProperties(hop, hopDTO);
				hoplist.add(hop);
			}
			hom.setOrderPayDTOs(hoplist);

		} catch (Exception e) {
			logger.info("拷贝HangOrderMainDto错误");
		}
		list.add(hom);
		return list;
	}

	@Override
	public ReceiveHangOrderOutputDTO TempHangOrderDelete(String orderNo) {

		ReceiveHangOrderOutputDTO receiveHangOrderOutputDTO = new ReceiveHangOrderOutputDTO();

		BtcOmsReceiveOrderOutputDTO deleteHangOrderByOrderNo = hangOrderCreateService.deleteHangOrderByOrderNo(orderNo);

		receiveHangOrderOutputDTO.setSucceed(deleteHangOrderByOrderNo.getSucceed());
		receiveHangOrderOutputDTO.setMessage(deleteHangOrderByOrderNo.getMessage());

		return receiveHangOrderOutputDTO;
	}

	@Override
	public List<OrderHeaderClientDTO> TempHangOrderQueryHeader(String shopNo, String startDate, String endDate,
			String status) {
		List<OrderHeaderClientDTO> orderHeaderClientDTO = new ArrayList<OrderHeaderClientDTO>();
		List<HangOrderMain> queryHangOrderMain = hangOrderCreateService.queryHangOrderMain(shopNo, startDate, endDate,
				status);
		try {
			for (HangOrderMain hom : queryHangOrderMain) {
				OrderHeaderClientDTO ohc = new OrderHeaderClientDTO();
				BeanUtils.copyProperties(ohc, hom);
				orderHeaderClientDTO.add(ohc);
			}

		} catch (Exception e) {
			logger.info("挂单列表查询bean拷贝错误");
		}

		return orderHeaderClientDTO;

	}

	@Override
	public List<OrderHeaderClientDTO> TempHangOrderQUeryDetail(String OrderNo) {
		List<OrderHeaderClientDTO> orderHeaderClientDTO = new ArrayList<OrderHeaderClientDTO>();
		List<HangOrderMain> queryHangOrderMainDetail = hangOrderCreateService.queryHangOrderMainDetail(OrderNo);

		List<OrderHeaderClientDTO> list = new ArrayList<OrderHeaderClientDTO>();
		for (HangOrderMain hom : queryHangOrderMainDetail) {
			OrderHeaderClientDTO ohcDTO = new OrderHeaderClientDTO();
			try {
				BeanUtils.copyProperties(ohcDTO, hom);
				List<HangOrderSub> hangOrderSubs = hom.getHangOrderSubs();
				// 拷贝子订单
				List<OrderSubClientDTO> oslist = new ArrayList<OrderSubClientDTO>();
				for (HangOrderSub oscDTO : hangOrderSubs) {
					OrderSubClientDTO osDTO = new OrderSubClientDTO();
					BeanUtils.copyProperties(osDTO, oscDTO);

					// 拷贝行订单
					List<OrderItemClientDTO> orderItemDTO = new ArrayList<OrderItemClientDTO>();
					for (HangOrderItem osi : hom.getHangOrderItems()) {
						OrderItemClientDTO oi = new OrderItemClientDTO();
						BeanUtils.copyProperties(oi, osi);
						orderItemDTO.add(oi);
					}
					osDTO.setOrderItemDTO(orderItemDTO);
					// 拷贝发票
					List<HangOrderInvoice> hangOrderInvoices = oscDTO.getHangOrderInvoices();
					for (HangOrderInvoice hoi : hangOrderInvoices) {
						OrderInvoiceClientDTO hoiDTO = new OrderInvoiceClientDTO();
						BeanUtils.copyProperties(hoiDTO, hoi);
						osDTO.setOrderInvoiceDTO(hoiDTO);
					}

					oslist.add(osDTO);
				}
				ohcDTO.setOrderSubDTOS(oslist);
				// hom.setHangOrderSubs(hangOrderSubs);

				// 拷贝订单支付
				List<HangOrderPay> orderPayDTOS = hom.getHangOrderPays();
				List<OrderPayClientDTO> oplist = new ArrayList<OrderPayClientDTO>();
				for (HangOrderPay opDTO : orderPayDTOS) {
					OrderPayClientDTO hop = new OrderPayClientDTO();
					BeanUtils.copyProperties(hop, opDTO);
					oplist.add(hop);
				}
				ohcDTO.setOrderPayDTOS(oplist);
				// hom.setHangOrderPays(orderPayDTOS);

				// 拷贝订单促销
				List<HangOrderPromotion> oProDTO = hom.getOrderPromotions();
				List<OrderPromotionClientDTO> oprolist = new ArrayList<OrderPromotionClientDTO>();
				for (HangOrderPromotion opDTO : oProDTO) {
					OrderPromotionClientDTO hop = new OrderPromotionClientDTO();
					BeanUtils.copyProperties(hop, opDTO);
					oprolist.add(hop);
				}
				ohcDTO.setOrderPromotionDTOS(oprolist);
				// hom.setOrderPromotions(oProDTO);

			} catch (Exception e) {
				logger.info("拷贝HangOrderMainDto错误");
			}
			list.add(ohcDTO);
		}
		return list;
	}

	@Override
	public Long getMemberIdBySubOrderNo(String subOrderNo) {
		return orderMainService.getMemberIdBySubOrderNo(subOrderNo);
	}

	@Override
	public BigDecimal getOrderAmountByOrderId(Long orderId) {
		return orderMainService.getOrderAmountByOrderId(orderId);
	}

	@Override
	public OrderSplitTransferReturnClientDTO splitOrderTransferReturn(String orderSubNo) {

		return orderStatusTransferReturnService.handleOrderStatusTransferReturn(orderSubNo);
	}

	private OrderCreateService buildOrderCreateService(BtcOmsReceiveOrderDTO receiveOrderClientDTO) {
		OrderCreateService iservice = null;
		OrderMainDTO md = receiveOrderClientDTO.getOmDTO().get(0);
		// 线下
		if (OrderMainConst.OrderMain_Ordersource_LS.getCode().equals(md.getOrderSource())
				|| OrderMainConst.OrderMain_Ordersource_DG.getCode().equals(md.getOrderSource())) {
			iservice = orderCreateOfflineService;
			// 线上
		} else if (OrderMainConst.OrderMain_Ordersource_BS.getCode().equals(md.getOrderSource())
				|| OrderMainConst.OrderMain_Ordersource_WX.getCode().equals(md.getOrderSource())) {
			iservice = orderCreateService;
		}
		return iservice;
	}

	public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
		OrderMainCreateClientDTO od = new OrderMainCreateClientDTO();
		List<OrderPayCreateClientDTO> orderPayClientDTOs = new ArrayList<OrderPayCreateClientDTO>();
		OrderPayCreateClientDTO op = new OrderPayCreateClientDTO();
		op.setBankTypeCode("11111");
		orderPayClientDTOs.add(op);
		od.setOrderPayDTOs(orderPayClientDTOs);
		od.setAliasOrderNo("11111");
		OrderMainDTO bo = new OrderMainDTO();
		// BeanUtils.copyProperties(od, bo);
		org.springframework.beans.BeanUtils.copyProperties(od, bo);
		System.out.println(bo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.oms.client.intf.IOrderClient#getTodayAllOrderGuiderId()
	 */
	@Override
	public List<?> getTodayAllOrderGuiderId() {
		return orderMainService.getTodayAllOrderGuiderId();
	}

	/*
	 * @see
	 * com.ibm.oms.client.intf.IOrderClient#findOrderItemSales(java.lang.String)
	 */
	@Override
	public Long findOrderItemSales(String productNo) {
		return orderItemService.findSalesByCommodityCode(productNo);
	}
	/* (non-Javadoc)
	 * @see com.ibm.oms.client.intf.IOrderClient#CreateOrder(com.ibm.oms.client.dto.order.create.refactor.ReceiveOrderMainDTO)
	 */
	@Override
	public HessianResult<OmsReceiveOrderOutputClientDTO> CreateOrder(ReceiveOrderMainDTO mainDTO) {
		commonUtilService.logInfoObjectToJson("IOrderClient-->CreateOrder[wx,dg,ls]-->params", logger, mainDTO);
		HessianResult<OmsReceiveOrderOutputClientDTO> result = new HessianResult<OmsReceiveOrderOutputClientDTO>();
		Map<String, Object> subbmitResult =   subbmitOrderService.handleSubbmitOrder(mainDTO);
		if(subbmitOrderValidateService.getValidateValue(subbmitResult)){
			BtcOmsReceiveOrderDTO romd =  (BtcOmsReceiveOrderDTO) subbmitOrderValidateService.getResultValue(subbmitResult);
			OrderCreateService oc = buildOrderCreateService(romd);
			BtcOmsReceiveOrderOutputDTO bout = oc.createOrder(romd);
			OmsReceiveOrderOutputClientDTO out =commonUtilService.jsonstrToObject(commonUtilService.ObjectTransJsonstr(bout),OmsReceiveOrderOutputClientDTO.class,new OmsReceiveOrderOutputClientDTO());
			if(CommonConstService.OK.equals(bout.getSucceed())){
				result.setData(out);
				result.setResponse_code(InterfaceConst.COMMON_RESPONSE_SUCCESS_CODE.getCode());
				result.setResponse_msg(bout.getMessage());
				commonUtilService.logInfoObjectToJson("IOrderClient-->CreateOrder[wx,dg,ls]-->result", logger, result);
				return result;
			}
			//result.setData(out);
			result.setResponse_code(InterfaceConst.COMMON_RESPONSE_FAIL_CODE.getCode());
			result.setResponse_msg(bout.getMessage());
			commonUtilService.logInfoObjectToJson("IOrderClient-->CreateOrder[wx,dg,ls]-->result", logger, result);
			return result;
		}
		result.setResponse_code(InterfaceConst.COMMON_RESPONSE_FAIL_CODE.getCode());
		result.setResponse_msg(subbmitOrderValidateService.getResultMessage(subbmitResult));
		commonUtilService.logInfoObjectToJson("IOrderClient-->CreateOrder[wx,dg,ls]-->result", logger, result);
		return result;
	}
	/* (non-Javadoc)
	 * @see com.ibm.oms.client.intf.IOrderClient#cancelOrderByOrderNo(java.lang.String)
	 */
	@Override
	public Long cancelOrderByOrderNo(String orderNo) {
		// TODO Auto-generated method stub
		return null;
	}
	private Long insertOrderMain(OrderMain orderMain,QmOrderMainDTO qmOrderMainDTO){
		//判断是正向订单还是逆向2订单
		Long id = null;
		if(orderMain.getStatusTotal().equals("0170")){
			OrderSub orderSub = new OrderSub();
			//正向订单
			orderMain.setOrderSource("BS");
			orderMain.setOrderType("GENERAL");
			orderMain.setBillType(1l);
			orderMainService.save(orderMain);
			String orderNo = orderNoService.getOrderNoByOrderId(orderMain.getId() + "");
			id = orderMain.getId();
			orderMain.setOrderNo(orderNo);
			orderMainService.update(orderMain);
			orderSub.setDeliveryMerchantNo(qmOrderMainDTO.getCompany_id()+"");
			orderSub.setShippingOrderNo(qmOrderMainDTO.getMail_no());
			orderSub.setProvideName(qmOrderMainDTO.getS_name());
			orderSub.setProvideCode(qmOrderMainDTO.getS_area_id()+"");
			orderSub.setProvideAddr(qmOrderMainDTO.getS_address());
			orderSub.setSZipCode(qmOrderMainDTO.getS_zip_code());
			orderSub.setProvidePhone(qmOrderMainDTO.getS_mobile_phone());
			orderSub.setProvideProvince(qmOrderMainDTO.getS_prov_name());
			orderSub.setProvideCity(qmOrderMainDTO.getS_city_name());
			orderSub.setProvideCounty(qmOrderMainDTO.getS_dist_name());
			orderSub.setUserName(qmOrderMainDTO.getR_name());
			orderSub.setAddressCode(qmOrderMainDTO.getR_area_id()+"");
			orderSub.setAddressDetail(qmOrderMainDTO.getR_address());
			orderSub.setPostCode(qmOrderMainDTO.getR_zip_code());
			orderSub.setPhoneNum(qmOrderMainDTO.getR_mobile_phone());
			orderSub.setMobPhoneNum(qmOrderMainDTO.getR_telephone());
			orderSub.setDeliveredProvince(qmOrderMainDTO.getR_telephone());
			orderSub.setDeliveredCity(qmOrderMainDTO.getR_prov_name());
			orderSub.setDeliveredCounty(qmOrderMainDTO.getR_city_name());
			orderSub.setDeliveredProvince(qmOrderMainDTO.getR_prov_name());
			orderSub.setDeliveredCity(qmOrderMainDTO.getR_city_name());
			orderSub.setDeliveredCounty(qmOrderMainDTO.getR_dist_name());
			orderSub.setOrderNo(orderNo);
			orderSub.setOrderSubNo(orderNoService.getOrderSubNoByOrderNo(orderNo,1));
			orderSub.setIdOrder(id);
			orderSubService.save(orderSub);
			List<QmOrderItemDTO> list =  qmOrderMainDTO.getItem_json_string();
			if(EmptyUtils.isNotEmpty(list)){
				int index = 1;
				for(QmOrderItemDTO qmOrderItemDTO :list){
					OrderItem orderItem = new OrderItem();
					orderItem.setOrderItemNo(orderNoService.getOrderItemNoBySubOrderNo(orderSub.getOrderSubNo(),index));
					orderItem.setIdOrderSub(orderSub.getId());
					orderItem.setCommodityName(qmOrderItemDTO.getItemName());
					orderItem.setSaleNum(qmOrderItemDTO.getItemCount());
					orderItem.setUnitPrice(new BigDecimal(qmOrderItemDTO.getSinglePrice()));
					orderItemService.save(orderItem);
					index++;
				}
			}
		}else if(orderMain.getStatusTotal().equals("0280")){
			OrderSub orderSub = new OrderSub();
			//逆向订单
			orderMain.setOrderSource("BS");
			orderMain.setOrderType("GENERAL");
			orderMain.setBillType(-1l);
			orderMainService.save(orderMain);
			String orderNo = orderNoService.getOrderNoByOrderId(orderMain.getId() + "");
			id = orderMain.getId();
			orderMain.setOrderNo(orderNo);
			orderMainService.update(orderMain);
			orderSub.setDeliveryMerchantNo(qmOrderMainDTO.getCompany_id()+"");
			orderSub.setShippingOrderNo(qmOrderMainDTO.getMail_no());
			orderSub.setProvideName(qmOrderMainDTO.getS_name());
			orderSub.setProvideCode(qmOrderMainDTO.getS_area_id()+"");
			orderSub.setProvideAddr(qmOrderMainDTO.getS_address());
			orderSub.setSZipCode(qmOrderMainDTO.getS_zip_code());
			orderSub.setProvidePhone(qmOrderMainDTO.getS_mobile_phone());
			orderSub.setProvideProvince(qmOrderMainDTO.getS_prov_name());
			orderSub.setProvideCity(qmOrderMainDTO.getS_city_name());
			orderSub.setProvideCounty(qmOrderMainDTO.getS_dist_name());
			orderSub.setUserName(qmOrderMainDTO.getR_name());
			orderSub.setAddressCode(qmOrderMainDTO.getR_area_id()+"");
			orderSub.setAddressDetail(qmOrderMainDTO.getR_address());
			orderSub.setPostCode(qmOrderMainDTO.getR_zip_code());
			orderSub.setPhoneNum(qmOrderMainDTO.getR_mobile_phone());
			orderSub.setMobPhoneNum(qmOrderMainDTO.getR_telephone());
			orderSub.setDeliveredProvince(qmOrderMainDTO.getR_telephone());
			orderSub.setDeliveredCity(qmOrderMainDTO.getR_prov_name());
			orderSub.setDeliveredCounty(qmOrderMainDTO.getR_city_name());
			orderSub.setDeliveredProvince(qmOrderMainDTO.getR_prov_name());
			orderSub.setDeliveredCity(qmOrderMainDTO.getR_city_name());
			orderSub.setDeliveredCounty(qmOrderMainDTO.getR_dist_name());
			orderSub.setOrderNo(orderNo);
			orderSub.setOrderSubNo(orderNoService.getOrderSubNoByOrderNo(orderNo,1));
			orderSub.setIdOrder(id);
			orderSubService.save(orderSub);
			List<QmOrderItemDTO> list =  qmOrderMainDTO.getItem_json_string();
			int index = 1;
			if(EmptyUtils.isNotEmpty(list)) {
				for (QmOrderItemDTO qmOrderItemDTO : list) {
					OrderRetChgItem orderItem = new OrderRetChgItem();
					orderItem.setOrderItemNo(orderNoService.getOrderItemNoBySubOrderNo(orderSub.getOrderSubNo(), index));
					orderItem.setIdOrderSub(orderSub.getId());
					orderItem.setIdOrder(id);
					orderItem.setCommodityName(qmOrderItemDTO.getItemName());
					orderItem.setSaleNum(qmOrderItemDTO.getItemCount());
					orderItem.setUnitPrice(new BigDecimal(qmOrderItemDTO.getSinglePrice()));
					orderRetChgItemService.save(orderItem);
					index++;
				}
			}
		}
		//TODO 以后还会加其他的订单状态
		return orderMain.getId();
	}

	@Override
	public HessianResult<OmsReceiveOrderOutputClientDTO> tempHangOrderCreateNew(ReceiveOrderMainDTO mainDTO) {
		commonUtilService.logInfoObjectToJson("IOrderClient-->CreateOrder[tempOrder]-->params", logger, mainDTO);
		HessianResult<OmsReceiveOrderOutputClientDTO> result = new HessianResult<OmsReceiveOrderOutputClientDTO>();
		Map<String, Object> subbmitResult = subbmitOrderService.handleSubbmitOrder(mainDTO);

		if (subbmitOrderValidateService.getValidateValue(subbmitResult)) {
			BtcOmsReceiveOrderDTO romd = (BtcOmsReceiveOrderDTO) subbmitOrderValidateService.getResultValue(subbmitResult);
			//BtcOmsReceiveOrderDTO romd = genJsonObjFromFile("C:/Users/wangchao/Desktop/BtcOmsReceiveOrderDTO3.txt",BtcOmsReceiveOrderDTO.class);
			HangOrderReceiveOrderDTO hrt = commonUtilService.jsonstrToObject(commonUtilService.ObjectTransJsonstr(romd),HangOrderReceiveOrderDTO.class, new HangOrderReceiveOrderDTO());
			BtcOmsReceiveOrderOutputDTO bout = hangOrderCreateService.createHangOrder(hrt);
			OmsReceiveOrderOutputClientDTO out = commonUtilService.jsonstrToObject(commonUtilService.ObjectTransJsonstr(bout), OmsReceiveOrderOutputClientDTO.class,new OmsReceiveOrderOutputClientDTO());
			if (CommonConstService.OK.equals(bout.getSucceed())) {
				result.setData(out);
				result.setResponse_code(InterfaceConst.COMMON_RESPONSE_SUCCESS_CODE.getCode());
				result.setResponse_msg(bout.getMessage());
				commonUtilService.logInfoObjectToJson("IOrderClient-->CreateOrder[tempOrder]-->result", logger, result);
				return result;
			}
			result.setData(out);
			result.setResponse_code(InterfaceConst.COMMON_RESPONSE_FAIL_CODE.getCode());
			result.setResponse_msg(bout.getMessage());
			commonUtilService.logInfoObjectToJson("IOrderClient-->CreateOrder[tempOrder]-->result", logger, result);
			return result;

		}
		//result.setData(out);
		result.setResponse_code(InterfaceConst.COMMON_RESPONSE_FAIL_CODE.getCode());
		result.setResponse_code(subbmitOrderValidateService.getResultMessage(subbmitResult));
		commonUtilService.logInfoObjectToJson("IOrderClient-->CreateOrder[tempOrder]-->result", logger, result);
		return result;

	}
	
	  public <T> T genJsonObjFromFile(String filePath, Class<T> clazz) {
	        ObjectMapper mapper = new ObjectMapper();
	        File f = new File(filePath);
	        T dto1 = null;
	        try {
	        	
	            dto1 = (T) mapper.readValue(f, clazz);
	            return dto1;
	        } catch (Exception e) {
	            logger.info("{}", e);
	        }
	        return null;
	    }
	
	
	
}

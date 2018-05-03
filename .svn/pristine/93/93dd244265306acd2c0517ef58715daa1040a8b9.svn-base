package com.ibm.oms.admin.action.order.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.constant.OrderReportColumn;
import com.ibm.oms.dao.constant.OrderReportColumnTitle;
import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.dao.constant.PayType;
import com.ibm.oms.domain.persist.DistributeAddress;
import com.ibm.oms.domain.persist.OrderDic;
import com.ibm.oms.domain.persist.OrderDic_;
import com.ibm.oms.domain.persist.OrderInvoice;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.oms.domain.persist.OrderReason;
import com.ibm.oms.domain.persist.OrderRetChgItem;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.domain.persist.StatusDict;
import com.ibm.oms.domain.persist.StatusDict_;
import com.ibm.oms.domain.persist.TransportArea;
import com.ibm.oms.intf.constant.OrderMainConst;
import com.ibm.oms.service.OrderDicService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderPayService;
import com.ibm.oms.service.OrderReasonService;
import com.ibm.oms.service.OrderRetAddService;
import com.ibm.oms.service.OrderRetChangeService;
import com.ibm.oms.service.OrderRetChgItemService;
import com.ibm.oms.service.OrderSearchService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.oms.service.StatusDictService;
import com.ibm.oms.service.business.OrderPaymentService;
import com.ibm.oms.service.business.OrderStatusService;
import com.ibm.oms.service.report.BaseOrderReportService;
import com.ibm.oms.service.report.CMBPaymentReportService;
import com.ibm.oms.service.report.CancelOrderReportService;
import com.ibm.oms.service.report.RefundOrderReportService;
import com.ibm.oms.service.report.ServiceGoodsReportService;
import com.ibm.oms.service.report.ToDoListService;
import com.ibm.oms.service.report.impl.CommonReportUtilService;
import com.ibm.oms.service.util.CommonEnum;
import com.ibm.oms.service.util.SelfTakePointUtil;
import com.ibm.sc.admin.action.BaseAdminAction;
import com.ibm.sc.model.payment.PaymentMode;
import com.ibm.sc.model.shipping.SelfTakePoint;
import com.ibm.sc.model.sys.Option;
import com.ibm.sc.model.sys.Option_;
import com.ibm.sc.model.sys.User;
import com.ibm.sc.service.payment.PaymentModeService;
import com.ibm.sc.service.sys.OptionService;
import com.ibm.sc.service.sys.UserService;

@ParentPackage("admin")
public class AbstractOrderReportAction extends BaseAdminAction {

	private static final long serialVersionUID = 1L;

	@Resource
	protected StatusDictService statusDictService;

	@Resource
	protected OrderMainService orderMainService;

	@Resource
	protected OrderSearchService orderSearchService;
	@Resource
	protected OrderSubService orderSubService;
	@Resource
	protected OrderStatusService orderStatusService;
	@Resource
	protected OrderRetChangeService OrderRetChangeService;
	@Resource
	protected OrderReasonService orderReasonService;
	@Resource
	protected OrderRetAddService orderRetAddService;
	@Resource
	protected OrderPayService orderPayService;
	@Resource
	protected OrderPaymentService orderPaymentService;
	@Resource
	protected UserService userService;
	@Resource
	protected OrderDicService orderDicService;
	@Resource
	protected OrderRetChgItemService orderRetChgItemService;
	@Resource
	private PaymentModeService paymentModeService;
	@Resource
	protected BaseOrderReportService baseOrderReportService;
	@Resource
	protected RefundOrderReportService refundOrderReportService;
	@Resource
	protected OptionService optionService;
	@Resource
	protected CancelOrderReportService cancelOrderReportService;
	@Resource
	protected CommonReportUtilService commonReportUtilService;
	@Resource
	protected ServiceGoodsReportService serviceGoodsReportService;
	@Resource
	protected CMBPaymentReportService cMBPaymentReportService;
	@Resource
	protected ToDoListService toDoListService;
	
	protected OrderMain orderMain = new OrderMain();

	protected OrderItem orderItem = new OrderItem();

	protected OrderPay orderPay = new OrderPay();

	protected OrderSub orderSub = new OrderSub();

	protected OrderInvoice orderInvoice = new OrderInvoice();

	/**
	 * 配送地址
	 */
	protected TransportArea transportArea = new TransportArea();

	/**
	 * 自提点
	 */
	protected SelfTakePoint selfTakePoint = new SelfTakePoint();

	/**
	 * 区域
	 */
	protected DistributeAddress distributeAddress = new DistributeAddress();

	protected OrderMain order;

	protected Integer column;

	protected String orderCategory;

	protected List<OrderItem> orderItemsList = new ArrayList<OrderItem>();

	protected List<OrderPay> orderPayLists = new ArrayList<OrderPay>();

	protected List<OrderRetChgItem> orderRetChgItemsList = new ArrayList<OrderRetChgItem>();

	protected User loginUser;

	@Autowired
	protected SelfTakePointUtil selfTakePointUtil;

	/**
	 * 订单页面标题
	 */
	protected String orderColumnTitle;

	protected Map<String, String> orderCategoryNameMap = new HashMap<String, String>();
	{
		orderCategoryNameMap.put(
				OrderMainConst.OrderMain_OrderCategory_Return.getCode(),
				OrderMainConst.OrderMain_OrderCategory_Return.getDesc());
		orderCategoryNameMap.put(
				OrderMainConst.OrderMain_OrderCategory_Change.getCode(),
				OrderMainConst.OrderMain_OrderCategory_Change.getDesc());
		orderCategoryNameMap.put(
				OrderMainConst.OrderMain_OrderCategory_ChangeOut.getCode(),
				OrderMainConst.OrderMain_OrderCategory_ChangeOut.getDesc());
		orderCategoryNameMap.put(
				OrderMainConst.OrderMain_OrderCategory_Reject.getCode(),
				OrderMainConst.OrderMain_OrderCategory_Reject.getDesc());
	}

	protected Map<String, String> preReasonMap;

	protected Map<String, List<OrderReason>> reasonMap;

	@Autowired
	CommonEnum commonEnum;

	public List<PayType> getOrderPayList() {

		return PayType.getAll();
	}

	public List<PayType> getAddOrderPayList() {
		List<PayType> payList = new ArrayList<PayType>();
		payList.add(null);
		payList.add(PayType.TIANHONG_CARD);
		payList.add(PayType.CASH);
		payList.add(PayType.BANK_CARD);
		return payList;
	}

	public List<OrderStatus> getOrderStatusList() {

		return OrderStatus.getAll();
	}

	public List<StatusDict> getStatusTotalList() {
		List<StatusDict> list = new ArrayList<StatusDict>();
		list.add(null);
		list.addAll(statusDictService.findByField(StatusDict_.statusTypeCode,
				OrderStatus.StatusTypeCode_SaleOrder_StatusTotal.getCode()));
		return list;
	}

	/**
	 * 配送方式
	 * 
	 * @return
	 */
	public List<Option> getDistributeTypeList() {
		// return OrderDeliveryType.getAll();
		List<Option> list = new ArrayList<Option>();
		list.add(null);
		list.addAll(optionService.findByField(Option_.optionGroupId,
				CommonConst.Option_OptionGroupId.getCode()));
		return list;
	}


	public List<StatusDict> getStatusPayList() {
		List<StatusDict> list = new ArrayList<StatusDict>();
		list.add(null);
		list.addAll(statusDictService.findByField(StatusDict_.statusTypeCode,
				OrderStatus.StatusTypeCode_SaleOrder_StatusPay.getCode()));
		return list;
	}

	public List<StatusDict> getStatusConfirmList() {
		List<StatusDict> list = new ArrayList<StatusDict>();
		list.add(null);
		list.addAll(statusDictService.findByField(StatusDict_.statusTypeCode,
				OrderStatus.StatusTypeCode_SaleOrder_StatusReturnMoney
						.getCode()));
		return list;
	}

	public List<StatusDict> getLogisticsStatusList() {
		List<StatusDict> list = new ArrayList<StatusDict>();
		list.addAll(statusDictService.findByField(StatusDict_.statusTypeCode,
				OrderStatus.StatusTypeCode_SaleOrder_StatusLogistics.getCode()));
		return list;
	}

	public List<StatusDict> getStatusFinishList() {
		List<StatusDict> list = new ArrayList<StatusDict>();
		list.add(null);
		list.add(new StatusDict("0", "未完成"));
		list.add(new StatusDict("1", "已完成"));
		return list;
	}

	public List<CommonConst> getDeliveryTimeList() {
		return commonEnum.getDeliveryTimeList();
	}

	protected OrderMain createRetOrderMain(String applySource,
			OrderMain orderOrg) {
		OrderMain retOrder = new OrderMain();
		retOrder.setOrderSource(applySource);
		retOrder.setOrderCategory(orderOrg.getOrderCategory());
		//retOrder.setConfirmerName(orderOrg.getConfirmerName());
		retOrder.setMemberNo(orderOrg.getMemberNo());
		retOrder.setCustomerName(orderOrg.getCustomerName());
		retOrder.setCustomerPhone(orderOrg.getCustomerPhone());
		retOrder.setMerchantNo(orderOrg.getMerchantNo());
		retOrder.setWeight(orderOrg.getWeight());
		retOrder.setTransportFee(orderOrg.getTransportFee());
		retOrder.setTotalProductPrice(orderOrg.getTotalProductPrice());
		retOrder.setDateCreated(orderOrg.getDateCreated());
		retOrder.setBillType(orderOrg.getBillType());
		retOrder.setOrderRelatedOrigin(orderOrg.getOrderNo());
		retOrder.setTotalGivePoints(orderOrg.getTotalGivePoints());
		retOrder.setTotalPayAmount(orderOrg.getTotalPayAmount());

		retOrder.setMemberNo(orderOrg.getMemberNo());
		retOrder.setDateCreated(orderOrg.getDateCreated());
		//retOrder.setCreatedBy(orderOrg.getCreatedBy());
		User user = userService.getLoginUser();
		if(null != user)
		{
		    retOrder.setCreatedBy(user.getUserName());
		}
		retOrder.setIfNeedRefund(orderOrg.getIfNeedRefund());
		retOrder.setRemark(orderOrg.getRemark());
		retOrder.setClientServiceRemark(orderOrg.getClientServiceRemark());

		retOrder.setRefOrderId(orderOrg.getId());
		retOrder.setRefOrderNo(orderOrg.getOrderNo());
		return retOrder;
	}

	protected OrderSub createRetOrderSub(OrderMain orderOrg,
			OrderSub orderSubOrg) {
		OrderSub retSub = new OrderSub();
		retSub.setOrderSubRelatedOrigin(orderSubOrg.getOrderSubNo());
		retSub.setIdOrder(orderOrg.getId());
		retSub.setOrderNo(orderOrg.getOrderNo());
		retSub.setLogisticsOutNo(orderSubOrg.getLogisticsOutNo());
		retSub.setDeliveryMerchantNo(orderSubOrg.getDeliveryMerchantNo());

		retSub.setInvoicePrinted(orderSubOrg.getInvoicePrinted());
		retSub.setDistributeType(orderSubOrg.getDistributeType());
		retSub.setLogisticsOutNo(orderSubOrg.getLogisticsOutNo());
		retSub.setExpressType(orderSubOrg.getExpressType());
		retSub.setAddressCode(orderSubOrg.getAddressCode());
		retSub.setAddressDetail(orderSubOrg.getAddressDetail());
		retSub.setPostCode(orderSubOrg.getPostCode());
		retSub.setUserName(orderSubOrg.getUserName());
		retSub.setPhoneNum(orderSubOrg.getPhoneNum());
		retSub.setMobPhoneNum(orderSubOrg.getMobPhoneNum());
		retSub.setBillType(orderSubOrg.getBillType());
		return retSub;
	}

	protected List<OrderRetChgItem> createRetItem(List<OrderItem> orderItemList) {
		List<OrderRetChgItem> retList = new ArrayList<OrderRetChgItem>();

		for (OrderItem orderItem : orderItemList) {
			OrderRetChgItem retItem = new OrderRetChgItem();
			retItem.setSkuNo(orderItem.getSkuNo());
			retItem.setReason(orderItem.getRefundReason());
			retItem.setRemark(orderItem.getRemark());
			retItem.setRefOrderItemId(orderItem.getId());
			retItem.setRefOrderItemNo(orderItem.getOrderItemNo());
			retItem.setBillType(orderItem.getBillType());
			// retItem.setProductPoint(orderItem.getProductPoint().longValue());

			retItem.setSaleUnit(orderItem.getSaleUnit());
			retItem.setSaleNum(orderItem.getSaleNum());
			retItem.setUnitPrice(orderItem.getUnitPrice());
			retItem.setUnitDiscount(orderItem.getUnitDiscount());
			retItem.setCouponAmount(orderItem.getCouponAmount());
			retItem.setUnitDeductedPrice(orderItem.getUnitDeductedPrice());

			retItem.setItemDiscount(orderItem.getItemDiscount());
			retItem.setCouponTotalMoney(orderItem.getCouponTotalMoney());
			retItem.setPayAmount(orderItem.getPayAmount());

			retItem.setBillType(orderItem.getBillType());

			retItem.setWeight(orderItem.getWeight());

			retList.add(retItem);
		}
		return retList;
	}

	protected OrderPay createRetOrderPay(OrderMain orderOrg,
			OrderPay orderPayOrg) {
		OrderPay retOrderPay = new OrderPay();
		retOrderPay.setBillType(orderOrg.getBillType());
		retOrderPay.setBankTypeCode(orderPayOrg.getBankTypeCode());
		retOrderPay.setPayName(orderPayOrg.getPayName());

		retOrderPay.setPayCode(orderPayOrg.getPayCode());
		retOrderPay.setPayAmount(orderPayOrg.getPayAmount());

		return retOrderPay;
	}

	public List<OrderReason> getPreRefundReasonList() {
		List<OrderReason> list = new ArrayList<OrderReason>();
		list.add(null);
		Map<OrderReason, List<OrderReason>> map = orderReasonService
				.getReasonMap();
		for (Map.Entry<OrderReason, List<OrderReason>> entry : map.entrySet()) {
			// System.out.println(entry.getKey()+"--->"+entry.getValue());
			list.add(entry.getKey());
		}
		return list;
	}

	public List<CommonConst> getAllIfNeedRefundList() {
		List<CommonConst> list = new ArrayList<CommonConst>();
		list.add(null);
		list.add(CommonConst.CommonBooleanFlaseLong);
		list.add(CommonConst.CommonBooleanTrueLong);
		list.add(CommonConst.OrderMain_IfNeedRefund_Yes_Store);
		return list;
	}

	public List<OrderMainConst> getAllDistributeType() {
		List<OrderMainConst> list = new ArrayList<OrderMainConst>();
		list.add(null);
		list.add(OrderMainConst.OrderSub_DistributeType_CustomerSend);
		list.add(OrderMainConst.OrderSub_DistributeType_PickFromDoor);
		list.add(OrderMainConst.OrderSub_DistributeType_ReturnStore);
		return list;

	}

	public List<CommonConst> getAllInvoicePrintedList() {
		List<CommonConst> list = new ArrayList<CommonConst>();
		list.add(null);
		list.add(CommonConst.CommonBooleanFlaseLong);
		list.add(CommonConst.CommonBooleanTrueLong);
		return list;

	}

	public List<OrderMainConst> getAllOrderCategoryList() {
		List<OrderMainConst> list = new ArrayList<OrderMainConst>();
		list.add(null);
		list.add(OrderMainConst.OrderMain_OrderCategory_Return);
		list.add(OrderMainConst.OrderMain_OrderCategory_Change);
		list.add(OrderMainConst.OrderMain_OrderCategory_ChangeOut);
		list.add(OrderMainConst.OrderMain_OrderCategory_Reject);
		return list;

	}

	public String getOrderCategoryName(String code) {
		List<OrderMainConst> list = new ArrayList<OrderMainConst>();
		list.add(OrderMainConst.OrderMain_OrderCategory_Return);
		list.add(OrderMainConst.OrderMain_OrderCategory_Change);
		list.add(OrderMainConst.OrderMain_OrderCategory_ChangeOut);
		list.add(OrderMainConst.OrderMain_OrderCategory_Reject);

		for (OrderMainConst value : list) {
			if (value.getCode().equals(code)) {
				return value.name();
			}
		}
		return null;
	}

	/**
	 * 获取支付方式列表
	 * 
	 * @return
	 */
	public List<PaymentMode> getPaymentModeList() {
		List<PaymentMode> list = new ArrayList<PaymentMode>();
		list.add(null);
		list.addAll(paymentModeService.getAll());
		return list;
	}

	/**
	 * 在线支付or货到付款
	 * 
	 * @return
	 */
	public List<Option> getArrivalPayList() {
		List<Option> list = new ArrayList<Option>();
		list.add(null);
		Option arrivalPay_No = new Option();
		arrivalPay_No.setCode("0");
		arrivalPay_No.setName("货到付款");
		list.add(arrivalPay_No);
		Option arrivalPay_Yes = new Option();
		arrivalPay_Yes.setCode("1");
		arrivalPay_Yes.setName("在线支付");
		list.add(arrivalPay_Yes);
		return list;
	}
	
	/**
	 * 获取订单来源列表
	 * 
	 * @return
	 */
	public List<OrderDic> getOrderSourceList() {
		List<OrderDic> list = new ArrayList<OrderDic>();
		list.add(null);
		list.addAll(orderDicService.findByField(OrderDic_.dicType,
				CommonConst.OrderDic_DicType_OrderSource.getCode()));
		return list;
	}

	/**
	 * 获取订单类型列表
	 * 
	 * @return
	 */
	public List<OrderDic> getOrderTypeList() {
		List<OrderDic> list = new ArrayList<OrderDic>();
		list.add(null);
		list.addAll(orderDicService.findByField(OrderDic_.dicType,
				CommonConst.OrderDic_DicType_OrderType.getCode()));
		return list;
	}

	public List<PayType> getPayTypeList() {
		return PayType.getAll();
	}

	/**
	 * 获取自提点列表
	 * 
	 * @return
	 */
	public List<SelfTakePoint> getSelfTakePointList() {
		List<SelfTakePoint> list = new ArrayList<SelfTakePoint>();
		list.add(null);
		list.addAll(selfTakePointUtil.getAllSelfTakePoint());
		return list;
	}

	/**
	 * 获取自营点列表
	 * 
	 * @return
	 */
	public List<SelfTakePoint> getSelfSalePointList() {
		List<SelfTakePoint> list = new ArrayList<SelfTakePoint>();
		list.add(null);
		list.addAll(selfTakePointUtil.getSelfSalePoint());
		return list;
	}

	public List<SelfTakePoint> getOneSelfTakePointList() {
		List<SelfTakePoint> pointList = new ArrayList<SelfTakePoint>();
		SelfTakePoint point = selfTakePointUtil.getSelfTakePointMap().get(
				orderSub.getSelfFetchAddress());
		pointList.add(point);
		return pointList;
	}
	
	/**
	 * 获得会员等级
	 * 
	 * @return
	 */
	public List<Option> getMemberVipLevelList() {
		List<Option> list = new ArrayList<Option>();
		list.add(null);
		Option level_One = new Option();
		level_One.setCode("1");
		level_One.setName("微卡");
		list.add(level_One);	
		Option level_Two = new Option();
		level_Two.setCode("2");
		level_Two.setName("银卡");
		list.add(level_Two);	
		Option level_Three = new Option();
		level_Three.setCode("3");
		level_Three.setName("金卡");
		list.add(level_Three);
		return list;
	}

	public OrderMain getOrderMain() {
		return orderMain;
	}

	public void setOrderMain(OrderMain orderMain) {
		this.orderMain = orderMain;
	}

	public OrderMain getOrder() {
		return order;
	}

	public void setOrder(OrderMain order) {
		this.order = order;
	}

	public OrderSub getOrderSub() {
		return orderSub;
	}

	public void setOrderSub(OrderSub orderSub) {
		this.orderSub = orderSub;
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}

	public Integer getColumn() {
		return column;
	}

	public void setColumn(Integer column) {
		this.column = column;
	}

	public List<OrderItem> getOrderItemsList() {
		return orderItemsList;
	}

	public void setOrderItemsList(List<OrderItem> orderItemsList) {
		this.orderItemsList = orderItemsList;
	}

	public String getOrderCategory() {
		return orderCategory;
	}

	public void setOrderCategory(String orderCategory) {
		this.orderCategory = orderCategory;
	}

	public Map<String, String> getOrderCategoryNameMap() {
		return orderCategoryNameMap;
	}

	public void setOrderCategoryNameMap(Map<String, String> orderCategoryNameMapP) {
		orderCategoryNameMap = orderCategoryNameMapP;
	}

	public OrderPay getOrderPay() {
		return orderPay;
	}

	public void setOrderPay(OrderPay orderPay) {
		this.orderPay = orderPay;
	}

	public User getLoginUser() {
		if (null == loginUser) {
			loginUser = userService.getLoginUser();
		}
		return loginUser;
	}

	public void setLoginUser(User loginUser) {

		this.loginUser = loginUser;
	}

	public OrderInvoice getOrderInvoice() {
		return orderInvoice;
	}

	public void setOrderInvoice(OrderInvoice orderInvoice) {
		this.orderInvoice = orderInvoice;
	}

	public List<OrderRetChgItem> getOrderRetChgItemsList() {
		return orderRetChgItemsList;
	}

	public void setOrderRetChgItemsList(
			List<OrderRetChgItem> orderRetChgItemsList) {
		this.orderRetChgItemsList = orderRetChgItemsList;
	}

	public Map<String, String> getPreReasonMap() {
		if (null == preReasonMap) {
			preReasonMap = new HashMap<String, String>();
			Map<OrderReason, List<OrderReason>> map = orderReasonService
					.getReasonMap();
			for (Map.Entry<OrderReason, List<OrderReason>> entry : map
					.entrySet()) {
				for (OrderReason reason : entry.getValue()) {
					preReasonMap.put(reason.getReasonNo(), entry.getKey()
							.getReasonNo());
				}
			}
		}
		return preReasonMap;
	}

	public void setPreReasonMap(Map<String, String> preReasonMap) {
		this.preReasonMap = preReasonMap;
	}

	public Map<String, List<OrderReason>> getReasonMap() {
		if (null == reasonMap) {
			reasonMap = new HashMap<String, List<OrderReason>>();
			Map<OrderReason, List<OrderReason>> map = orderReasonService
					.getReasonMap();
			for (Map.Entry<OrderReason, List<OrderReason>> entry : map
					.entrySet()) {
				for (OrderReason reason : entry.getValue()) {
					reasonMap.put(reason.getReasonNo(), entry.getValue());
				}
			}
		}
		return reasonMap;
	}

	public void setReasonMap(Map<String, List<OrderReason>> reasonMap) {
		this.reasonMap = reasonMap;
	}

	public TransportArea getTransportArea() {
		return transportArea;
	}

	public void setTransportArea(TransportArea transportArea) {
		this.transportArea = transportArea;
	}

	public SelfTakePoint getSelfTakePoint() {
		return selfTakePoint;
	}

	public void setSelfTakePoint(SelfTakePoint selfTakePoint) {
		this.selfTakePoint = selfTakePoint;
	}

	public DistributeAddress getDistributeAddress() {
		return distributeAddress;
	}

	public void setDistributeAddress(DistributeAddress distributeAddress) {
		this.distributeAddress = distributeAddress;
	}

	/**
	 * 设置订单页面标题
	 * 
	 * @param columnId
	 */
	public void setOrderColumnTitle(int columnId) {
		this.orderColumnTitle = "";

		switch (columnId) {
		case OrderReportColumn.ORDER_RETURN:
			this.orderColumnTitle = OrderReportColumnTitle.RETURN_TITLE;
			break;
		case OrderReportColumn.ORDER_CHANGE:
			this.orderColumnTitle = OrderReportColumnTitle.CHANGE_TITLE;
			break;
		case OrderReportColumn.ORDER_REJECT:
			this.orderColumnTitle = OrderReportColumnTitle.REJECT_TITLE;
			break;
		}
	}

	public String getOrderColumnTitle() {
		return orderColumnTitle;
	}

	public List<OrderPay> getOrderPayLists() {
		return orderPayLists;
	}

	public void setOrderPayLists(List<OrderPay> orderPayLists) {
		this.orderPayLists = orderPayLists;
	}

}

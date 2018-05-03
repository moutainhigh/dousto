package com.ibm.oms.admin.action.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.member.intf.PointsCommonAccountHsService;
import com.ibm.member.result.HessianResult;
import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.constant.OrderColumn;
import com.ibm.oms.dao.constant.OrderColumnTitle;
import com.ibm.oms.dao.constant.PayType;
import com.ibm.oms.dao.intf.ProductPropertyDao;
import com.ibm.oms.dao.intf.StatusDictDao;
import com.ibm.oms.domain.persist.DistributeAddress;
import com.ibm.oms.domain.persist.IncludeOrNot;
import com.ibm.oms.domain.persist.OrderDic;
import com.ibm.oms.domain.persist.OrderInvoice;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderLogistics;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.oms.domain.persist.OrderPayMode;
import com.ibm.oms.domain.persist.OrderPay_;
import com.ibm.oms.domain.persist.OrderReason;
import com.ibm.oms.domain.persist.OrderRetChgItem;
import com.ibm.oms.domain.persist.OrderSearch;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.domain.persist.StatusDict;
import com.ibm.oms.domain.persist.TransportArea;
import com.ibm.oms.intf.constant.OrderMainConst;
import com.ibm.oms.intf.intf.OmsMemberVipCardOutputDTO;
import com.ibm.oms.service.OrderDicService;
import com.ibm.oms.service.OrderInvoiceService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderOperateLogService;
import com.ibm.oms.service.OrderPayService;
import com.ibm.oms.service.OrderReasonService;
import com.ibm.oms.service.OrderRetAddService;
import com.ibm.oms.service.OrderRetChangeService;
import com.ibm.oms.service.OrderRetChgItemService;
import com.ibm.oms.service.OrderSearchService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.oms.service.SelfTakePointTmpService;
import com.ibm.oms.service.StatusDictService;
import com.ibm.oms.service.TransportAreaCacheService;
import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.OrderPaymentService;
import com.ibm.oms.service.business.OrderStatusService;
import com.ibm.oms.service.business.ReturnChangeOrderService;
import com.ibm.oms.service.business.trans.SaleAfterOrderTransService;
import com.ibm.oms.service.err.ErrConst;
import com.ibm.oms.service.report.BaseOrderReportService;
import com.ibm.oms.service.util.CommonCacheUtil;
import com.ibm.oms.service.util.CommonEnum;
import com.ibm.oms.service.util.CommonUtilService;
import com.ibm.oms.service.util.DateUtil;
import com.ibm.oms.service.util.GlobalUtilB2C;
import com.ibm.oms.service.util.PaymentMethodUtil;
import com.ibm.oms.service.util.PaymentModeUtil;
import com.ibm.oms.service.util.SelfTakeMerchantUtil;
import com.ibm.oms.service.util.SelfTakePointUtil;
import com.ibm.oms.service.util.StatusUtil;
import com.ibm.sc.admin.action.BaseAdminAction;
import com.ibm.sc.beans.sys.OptionBean;
import com.ibm.sc.model.payment.PaymentMethod;
import com.ibm.sc.model.payment.PaymentMode;
import com.ibm.sc.model.shipping.SelfTakePoint;
import com.ibm.sc.model.sys.Option;
import com.ibm.sc.model.sys.Option_;
import com.ibm.sc.model.sys.User;
import com.ibm.sc.service.sys.OptionService;
import com.ibm.sc.service.sys.UserService;
import com.ibm.stock.dto.WarehouseData;

@ParentPackage("admin")
public class AbstractOrderAction extends BaseAdminAction {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 是否是创建意向订单页  true: 是    false：否
	 */
	protected Boolean isCreated = false;
	
	/**
     * 是否为详情页 true:是  false:否
     */
	protected Boolean isDetail = false;
	
	/**
     * 是否为修改页 true:是  false:否
     */
	protected Boolean isModify = false;
	
	@Resource(name = "configMap")
    protected Map<String, String> configMap;
	@Resource
	protected StatusDictDao statusDictDao;
	@Resource
	protected ProductPropertyDao productPropertyDao;
	@Resource
	protected StatusDictService statusDictService;

	@Resource
	protected OrderMainService orderMainService;

	@Resource
	protected OrderSearchService orderSearchService;
	@Resource
	protected OrderSubService orderSubService;
	@Resource
	protected OrderNoService orderNoService;
	@Resource
	protected OrderStatusService orderStatusService;
	@Resource
	protected OrderRetChangeService orderRetChangeService;
	@Resource
	protected OrderReasonService orderReasonService;
	@Resource
	protected SaleAfterOrderTransService saleAfterOrderTransService;
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
	/*@Resource
	private PaymentModeService paymentModeService;*/
	@Resource
	protected BaseOrderReportService baseOrderReportService;
	@Resource
	protected OptionService optionService;
	@Resource
	protected OrderInvoiceService orderInvoiceService;
	
	@Resource
	protected OrderOperateLogService orderOperateLogService;
	
	@Resource
	protected OrderCreateService orderCreateService;
	
	@Resource
	protected SelfTakePointTmpService selfTakePointTmpService;
	@Resource
	protected TransportAreaCacheService transportAreaCacheService;
	@Resource
	protected CommonUtilService commonUtilService;
	@Resource
	protected ReturnChangeOrderService returnChangeOrderService;
    @Autowired
    StatusUtil statusUtil;
    @Autowired
    CommonCacheUtil commonCacheUtil;

	protected OrderLogistics orderLogistics = new OrderLogistics();
    @Autowired
	PointsCommonAccountHsService pointsCommonAccountHsService;

	protected OrderMain orderMain = new OrderMain();

	protected OrderItem orderItem = new OrderItem();

	protected OrderPay orderPay = new OrderPay();

	protected OrderSub orderSub = new OrderSub();
	
	protected OrderInvoice orderInvoice = new OrderInvoice();

	protected  OrderPayMode orderPayMode = new OrderPayMode();
	/**
	 * 物流公司编码
	 */
	protected List<OptionBean> logisticsCodes = new ArrayList<OptionBean>();
	
	/**
	 * 获取仓库编码集合
	 */
	protected List<WarehouseData> wareHouses = new ArrayList<WarehouseData>();
	
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
	

	/**
	 * 包含或不包含
	 */
	protected IncludeOrNot includeOrNot = new IncludeOrNot();
	protected OrderSearch orderSearch;

	protected OrderMain order;

	protected Integer column;

	protected String orderCategory;

	protected List<OrderItem> orderItemsList = new ArrayList<OrderItem>();
	
	protected List<OrderPay> orderPayLists = new ArrayList<OrderPay>();
	
	protected List<OrderRetChgItem> orderRetChgItemsList = new ArrayList<OrderRetChgItem>();
	
	protected User loginUser;
	
	@Autowired
	protected SelfTakePointUtil selfTakePointUtil;
	
	@Autowired
	protected PaymentMethodUtil paymentMethodUtil;
	@Autowired
	PaymentModeUtil paymentModeUtil;
	@Autowired
	SelfTakeMerchantUtil selfTakeMerchantUtil;
	
	protected String blackType;
	
	/**
	 * 订单页面标题
	 */
	protected String orderColumnTitle;
	


	/**
	 * 订单申请类型
	 */
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
		orderCategoryNameMap.put(
				OrderMainConst.OrderMain_OrderCategory_Refund.getCode(),
				OrderMainConst.OrderMain_OrderCategory_Refund.getDesc());
		orderCategoryNameMap.put(
				OrderMainConst.OrderMain_OrderCategory_TransportFee.getCode(),
				OrderMainConst.OrderMain_OrderCategory_TransportFee.getDesc());
	}
	
	protected Map<String, String> preReasonMap;
	
	protected Map<String, List<OrderReason>> reasonMap;
	

    public static Date getStartDate(Date time) {

        time = org.apache.commons.lang.time.DateUtils.setHours(time, 0);
        time = org.apache.commons.lang.time.DateUtils.setMinutes(time, 0);
        time = org.apache.commons.lang.time.DateUtils.setSeconds(time, 0);
        time = org.apache.commons.lang.time.DateUtils.setMilliseconds(time, 0);

        return time;
    }

    public static Date getEndDate(Date time) {

        time = org.apache.commons.lang.time.DateUtils.setHours(time, 23);
        time = org.apache.commons.lang.time.DateUtils.setMinutes(time, 59);
        time = org.apache.commons.lang.time.DateUtils.setSeconds(time, 59);
        time = org.apache.commons.lang.time.DateUtils.setMilliseconds(time, 999);

        return time;
    }
	
	

	@Autowired
	CommonEnum commonEnum;
	
	/**
	 * 取消订单原因
	 * @return
	 */
	public List<Option> getCancelReasonList()
	{
	    List<Option> list = new ArrayList<Option>();
	    list.add(null);
	    list.addAll(commonCacheUtil.getCancelReasonList());
	    return list;
	}
	
	
	/**
	 * 异常查询错误码
	 * @return
	 */
	public List<ErrConst> getErrorCodeList()
	{
	    List<ErrConst> list = new ArrayList<ErrConst>();
	    list.add(null);
	    // 异常查询错误码
        ErrConst[] errorCodes = ErrConst.values();
        list.addAll(Arrays.asList(errorCodes));
	    return list;
	}
	
	/**
     * 逆向单获取原支付方式
     * @param orderRelatedOrigin
     */
    public void getOriginalOrderPays(String orderRelatedOrigin) {
        if(null != orderRelatedOrigin)
        {
            // 获取原销售订单的支付明细
            List<OrderPay> relatedOrderPayList = orderPayService.findByField(OrderPay_.orderNo, orderRelatedOrigin);
            // 设置到OrderMain非持久化属性originalOrderPayList属性中
            order.setOriginalOrderPayList(relatedOrderPayList);
        }
    }

	/**
	 * 物流公司
	 * @return
	 */
	public List<Option> getOptions() {
		List<Option> list = GlobalUtilB2C.findOptionByGroupCode(CommonConst.OptionGroup_Code_EXPRESS_COMPLANY.getCode());
		return list;
	}
	
	/**
	 * 支付方式
	 */
	public List<PayType> getOrderPayList() {

		return PayType.getAll();
	}

	/**
	 * 货到付款支付方式
	 */
	public List<PayType> getAddOrderPayList() {
		List<PayType> payList = new ArrayList<PayType>();
		payList.add(null);
		payList.add(PayType.TIANHONG_CARD);
		payList.add(PayType.CASH);
		payList.add(PayType.BANK_CARD);
		return payList;
	}

	/**
	 * 订单状态
	 */
	public List<StatusDict> getOrderStatusList() {

		return statusUtil.getStatusList();//OrderStatus.getAll();
	}

	/**
	 * 订单处理状态
	 */
	public List<StatusDict> getStatusTotalList() {
		/*List<StatusDict> list = new ArrayList<StatusDict>();
		//list.addAll(statusDictService.findByField(StatusDict_.statusTypeCode,OrderStatus.StatusTypeCode_SaleOrder_StatusTotal.getCode()));
		// 根据displayName排序StatusDict对象List集合
		//List<StatusDict> resultList = commonUtilService.sortStatusDictListbyDisplayName(list);
		list.add(null);
		list.addAll(statusDictDao.findStatusDictByStatusTypeCode(OrderStatus.StatusTypeCode_SaleOrder_StatusTotal.getCode()));
		
		return list;*/
		return commonCacheUtil.getStatusTotalList();
	}

	
	
	/**
	 * 设置省、城市、县区组合的地址
	 * @param addressCode
	 */
	public void setOrderSubCombinedAddress(String addressCode)
	{
		if(addressCode!=null && !"null".equals(addressCode)){
			//String combinedAddress = transportAreaCacheService.findAreaNameById(Long.valueOf(addressCode));
			String combinedAddress = transportAreaCacheService.getCombinedByAreaCode(addressCode);
			orderSub.setCombinedAddress(combinedAddress);
		}
	}
	

	/**
	 * 配送方式
	 * 
	 * @return
	 */
	public List<Option> getDistributeTypeList() {
		/*//return OrderDeliveryType.getAll();
		List<Option> list = new ArrayList<Option>();
		list.addAll(optionService.findByGroupId(Long.valueOf(CommonConst.Option_OptionGroupId.getCode())));
		
		// 根据name排序Option对象List集合
		list = commonUtilService.sortOptionListbyDisplayName(list);
		return list;*/
		
		return commonCacheUtil.getDistributeTypeList();
	}

	/**
	 * 数据库订单支付状态
	 */
	public List<StatusDict> getStatusPayList() {
		/*List<StatusDict> list = new ArrayList<StatusDict>();
		//list.addAll(statusDictService.findByField(StatusDict_.statusTypeCode,OrderStatus.StatusTypeCode_SaleOrder_StatusPay.getCode()));
		
		// 根据displayName排序StatusDict对象List集合
		//list = commonUtilService.sortStatusDictListbyDisplayName(list);
		
		list.add(null);
		list.addAll(statusDictDao.findStatusDictByStatusTypeCode(OrderStatus.StatusTypeCode_SaleOrder_StatusPay.getCode()));
		return list;*/
		return commonCacheUtil.getStatusPayList();
	}

	/**
	 * 订单审批审核状态
	 * @return
	 */
	public List<StatusDict> getStatusConfirmList() {
		/*//List<StatusDict> list = new ArrayList<StatusDict>();
		list.addAll(statusDictService.findByField(StatusDict_.statusCode,
				OrderStatus.StatusTypeCode_Order_StatusConfirm_Wait
						.getCode()));
		list.addAll(statusDictService.findByField(StatusDict_.statusCode,
				OrderStatus.StatusTypeCode_Order_StatusConfirm_AutoPass
				.getCode()));
		list.addAll(statusDictService.findByField(StatusDict_.statusCode,
				OrderStatus.StatusTypeCode_Order_StatusConfirm_Pass
				.getCode()));
		list.addAll(statusDictService.findByField(StatusDict_.statusCode,
				OrderStatus.StatusTypeCode_Order_StatusConfirm_Cancel
				.getCode()));
		List<StatusDict> list = new ArrayList<StatusDict>();
		list.add(null);
		//list.addAll(statusDictService.findByField(StatusDict_.statusTypeCode,OrderStatus.StatusTypeCode_SaleOrder_StatusReturnMoney.getCode()));
		
		// 根据displayName排序StatusDict对象List集合
		//list = commonUtilService.sortStatusDictListbyDisplayName(list);
				
		list.addAll(statusDictDao.findStatusDictByStatusTypeCode(OrderStatus.StatusTypeCode_SaleOrder_StatusReturnMoney.getCode()));		*/
		return commonCacheUtil.getStatusConfirmList();
	}

	/**
	 *物流状态
	 */
	public List<StatusDict> getLogisticsStatusList() {
	/*	List<StatusDict> list = new ArrayList<StatusDict>();
		list.add(null);
		
		//list.addAll(statusDictService.findByField(StatusDict_.statusTypeCode,OrderStatus.StatusTypeCode_SaleOrder_StatusLogistics.getCode()));
		
		list.addAll(statusDictDao.findStatusDictByStatusTypeCode(OrderStatus.StatusTypeCode_SaleOrder_StatusLogistics.getCode()));
		// 根据displayName排序StatusDict对象List集合
		//list = commonUtilService.sortStatusDictListbyDisplayName(list);
	 */		
		return commonCacheUtil.getLogisticsStatusList();
	}

	/**
	 * 完成状态
	 */
	public List<StatusDict> getStatusFinishList() {
		List<StatusDict> list = new ArrayList<StatusDict>();
		list.add(null);
		list.add(new StatusDict("0", "未完成"));
		list.add(new StatusDict("1", "已完成"));
		return list;
	}

	/**
	 * 订单配送时间
	 * @return
	 */
	public List<Option> getDeliveryDateFlagList() {
		//return commonEnum.getDeliveryTimeList();
		return commonCacheUtil.getDeliveryDateFlagList();
	}

	/**
	 * 创建退货单 ordermain
	 * @param applySource
	 * @param orderOrg
	 * @return
	 */
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
		User user = userService.getLoginUser();
        if(null != user)
        {
            if(null != user.getRealname())
            {
                retOrder.setCreatedBy(user.getRealname());
            }
            else
            {
                retOrder.setCreatedBy(user.getUserName());
            }
        }
		retOrder.setIfNeedRefund(orderOrg.getIfNeedRefund());
		retOrder.setRemark(orderOrg.getRemark());
		retOrder.setClientServiceRemark(orderOrg.getClientServiceRemark());

		retOrder.setRefOrderId(orderOrg.getId());
		retOrder.setRefOrderNo(orderOrg.getOrderNo());
		return retOrder;
	}

	/**
	 * 创建退货单 ordersub
	 * @param orderOrg
	 * @param orderSubOrg
	 * @return
	 */
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

	/**
	 * 创建退换货 item
	 * @param orderItemList
	 * @return
	 */
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

	/**
	 * 创建退换货  pay
	 * @param orderOrg
	 * @param orderPayOrg
	 * @return
	 */
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

	/**
	 * 退换货原因
	 * @return
	 */
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
	
	
    /**
     * 是否需要退款
     * @return
     */
    public List<CommonConst> getAllIfNeedRefundList() {
       /* List<CommonConst> list = new ArrayList<CommonConst>();
        list.add(null);
        list.add(CommonConst.CommonBooleanFlaseLong);
        list.add(CommonConst.CommonBooleanTrueLong);
        list.add(CommonConst.OrderMain_IfNeedRefund_Yes_Store);*/
        return commonCacheUtil.getAllIfNeedRefundList();
    }

    /**
     * 入库物流方式
     */
    public List<OrderMainConst> getAllDistributeType() {
    	 /*   List<OrderMainConst> list = new ArrayList<OrderMainConst>();
        list.add(null);
        list.add(OrderMainConst.OrderSub_DistributeType_CustomerSend);
        list.add(OrderMainConst.OrderSub_DistributeType_PickFromDoor);
        list.add(OrderMainConst.OrderSub_DistributeType_ReturnStore);*/
        return commonCacheUtil.getAllDistributeType();

    }

    /**
     * 是否需要打印发票
     */
    public List<CommonConst> getAllInvoicePrintedList() {
       /* List<CommonConst> list = new ArrayList<CommonConst>();
        //list.add(null);
        list.add(CommonConst.CommonBooleanFlaseLong);
        list.add(CommonConst.CommonBooleanTrueLong);
        return list;*/
    	return commonCacheUtil.getAllInvoicePrintedList();

    }
    
    /**
     * 黑名单列表
     */
    public List<CommonConst> getBlackList() {
        /*List<CommonConst> list = new ArrayList<CommonConst>();
        //list.add(null);
        list.add(CommonConst.BlackList_spiteRise);
        list.add(CommonConst.BlackList_spiteIndent);
        list.add(CommonConst.BlackList_spiteComment);
        return list;*/
    	return commonCacheUtil.getBlackList();

    }

    /**
     * 退换货类型
     */
    public List<OrderMainConst> getAllOrderCategoryList() {
       /* List<OrderMainConst> list = new ArrayList<OrderMainConst>();
        list.add(null);
        list.add(OrderMainConst.OrderMain_OrderCategory_Return);
      //  list.add(OrderMainConst.OrderMain_OrderCategory_Change);
      //  list.add(OrderMainConst.OrderMain_OrderCategory_ChangeOut);
        list.add(OrderMainConst.OrderMain_OrderCategory_Reject);
        list.add(OrderMainConst.OrderMain_OrderCategory_Refund);
        list.add(OrderMainConst.OrderMain_OrderCategory_TransportFee);
        return list;*/
    	return commonCacheUtil.getAllOrderCategoryList();
    }
    
    /**
     * 订单大类
     * @return
     */
    public List<OrderMainConst> getOrderCategoryList() {
        /*List<OrderMainConst> list = new ArrayList<OrderMainConst>();
        list.add(null);
        list.add(OrderMainConst.OrderMain_OrderCategory_Sale);
        list.add(OrderMainConst.OrderMain_OrderCategory_ChangeOut);
        return list;*/
    	return commonCacheUtil.getOrderCategoryList();

    }
    

    /**
     * 订单退款类型
     * @return
     */
    public List<OrderMainConst> getOrderRefundTypeList() {
    	return commonCacheUtil.getOrderRefundTypeList();
    }
    
    /**
     * 退换货类型名称
     * @return
     */
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
     * 获取支付类型列表
     * @return
     */
    public List<PaymentMode> getPaymentModeList() {
    	List<PaymentMode> list = new ArrayList<PaymentMode>();
    	list.addAll(paymentModeUtil.getPaymentModeList());
    	
    	// 根据name排序PaymentMode对象List集合
    	list = commonUtilService.sortPaymentModeListbyDisplayName(list);
    	return list;
    }
    
    /**
	 * 获取支付方式列表
	 * @return
	 */
	public List<PaymentMethod> getPaymentMethodList() {
		List<PaymentMethod> list = new ArrayList<PaymentMethod>();
		PaymentMethod paymentMethod = new PaymentMethod();
		paymentMethod.setId(Long.valueOf(PayType.ONLINE.getId()));
		paymentMethod.setName(PayType.ONLINE.getPayName());
		list.add(paymentMethod);
		//list.addAll(paymentModeService.getAll());
		list.addAll(paymentMethodUtil.getPaymentMethodList());
		
		// 根据name排序PaymentMethod对象List集合
		list = commonUtilService.sortPaymentMethodListbyDisplayName(list);
		return list;
	}
	
	/**
	 * 获取退款方式列表
	 * @return
	 */
	public List<PaymentMethod> getRefundMethodList()
	{
		List<PaymentMethod> list = new ArrayList<PaymentMethod>();
		list.add(null);
		
		// 添加PM_PAYMENT_MODE中的所有退款方式
		List<PaymentMode> paymentModes = new ArrayList<PaymentMode>();
		paymentModes = paymentModeUtil.getPaymentModeList();
		
		PaymentMethod paymentMethod;
		for(PaymentMode paymentMode:paymentModes)
		{
			paymentMethod = new PaymentMethod();
			paymentMethod.setId(Long.valueOf(paymentMode.getCode()));
			paymentMethod.setName(paymentMode.getName());
			list.add(paymentMethod);
		}
		
		// 添加PM_PAYMENT_METHOD中的所有退款方式
		list.addAll(paymentMethodUtil.getPaymentMethodList());
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
		arrivalPay_No.setName("在线支付");
		list.add(arrivalPay_No);
		Option arrivalPay_Yes = new Option();
		arrivalPay_Yes.setCode("1");
		arrivalPay_Yes.setName("货到付款");
		list.add(arrivalPay_Yes);
		return list;
	}
    
    /**
	 * 获取订单来源列表
	 * @return
	 */
	public List<OrderDic> getOrderSourceList() {
		/*List<OrderDic> list = new ArrayList<OrderDic>();
		list.addAll(orderDicService.findByField(OrderDic_.dicType,CommonConst.OrderDic_DicType_OrderSource.getCode()));
		
		// 根据dicName排序OrderDic对象List集合
		list = commonUtilService.sortOrderDicListbyDisplayName(list);
		return list;*/
		return commonCacheUtil.getOrderSourceList();
	}
    
	/**
	 * 获取订单类型列表
	 * @return
	 */
	public List<OrderDic> getOrderTypeList() {
		/*List<OrderDic> list = new ArrayList<OrderDic>();
		list.add(null);
		list.addAll(orderDicService.findByField(OrderDic_.dicType,CommonConst.OrderDic_DicType_OrderType.getCode()));
		
		// 根据dicName排序OrderDic对象List集合
		list = commonUtilService.sortOrderDicListbyDisplayName(list);
		return list;*/
		return commonCacheUtil.getOrderTypeList();
	}

    public List<PayType> getPayTypeList() {
        return PayType.getAll();
    }
    
    /**
     * 获取自提商户
     * @return
     */
	public List<Option> getSelfTakeMerchantList() {
		List<Option> selfTakeMerchantList = new ArrayList<Option>();
		selfTakeMerchantList.add(null);
		selfTakeMerchantList.addAll(selfTakeMerchantUtil
				.getSelfTakeMerchantList());
		return selfTakeMerchantList;
	}
    
    
    /**
	 * 获取自提点列表
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
	 * @return
	 */
	public List<SelfTakePoint> getSelfSalePointList() {
		List<SelfTakePoint> list = new ArrayList<SelfTakePoint>();
		list.add(null);
		list.addAll(selfTakePointUtil.getSelfSalePoint());
		return list;
	}
    
    
	public List<SelfTakePoint> getOneSelfTakePointList(){
		List<SelfTakePoint> pointList = new ArrayList<SelfTakePoint>();
		SelfTakePoint point = selfTakePointUtil.getSelfTakePointMap().get(orderSub.getSelfFetchAddress());
		pointList.add(point);
		return pointList;
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

	public OrderSearch getOrderSearch() {
		return orderSearch;
	}

	public void setOrderSearch(OrderSearch orderSearch) {
		this.orderSearch = orderSearch;
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

	public void setOrderCategoryNameMap(Map<String, String> orderCategoryNameMap) {
		this.orderCategoryNameMap = orderCategoryNameMap;
	}

	public OrderPay getOrderPay() {
		return orderPay;
	}

	public void setOrderPay(OrderPay orderPay) {
		this.orderPay = orderPay;
	}

	public User getLoginUser() {
		if (null == loginUser){
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

	public OrderPayMode getOrderPayMode() {
		return orderPayMode;
	}

	public void setOrderPayMode(OrderPayMode orderPayMode) {
		this.orderPayMode = orderPayMode;
	}

	public List<OrderRetChgItem> getOrderRetChgItemsList() {
		return orderRetChgItemsList;
	}

	public void setOrderRetChgItemsList(List<OrderRetChgItem> orderRetChgItemsList) {
		this.orderRetChgItemsList = orderRetChgItemsList;
	}

	public Map<String, String> getPreReasonMap() {
		/*if (null == preReasonMap){
			preReasonMap = new HashMap<String, String>();
			Map<OrderReason, List<OrderReason>> map = orderReasonService
					.getReasonMap();
			for (Map.Entry<OrderReason, List<OrderReason>> entry : map.entrySet()) {
	            for(OrderReason reason: entry.getValue()){
	            	preReasonMap.put(reason.getReasonNo(), entry.getKey().getReasonNo());
	            }
			}
		}
		return preReasonMap;*/
		return  commonCacheUtil.getPreReasonMap();
	}

	public void setPreReasonMap(Map<String, String> preReasonMap) {
		this.preReasonMap = preReasonMap;
	}

	public Map<String, List<OrderReason>> getReasonMap() {
		/*if (null == reasonMap){
			reasonMap = new HashMap<String, List<OrderReason>>();
			Map<OrderReason, List<OrderReason>> map = orderReasonService
					.getReasonMap();
			for (Map.Entry<OrderReason, List<OrderReason>> entry : map.entrySet()) {
	            for(OrderReason reason: entry.getValue()){
	            	reasonMap.put(reason.getReasonNo(), entry.getValue());
	            }
			}
		}
		return reasonMap;*/
		return  commonCacheUtil.getReasonMap();
	}

	/**
	 * 配送方式
	 * 
	 * @return
	 */
	public List<Option> getCompanyList() {
		// return OrderDeliveryType.getAll();
		List<Option> list = new ArrayList<Option>();
		//list.add(null);
		list.addAll(optionService.findByField(Option_.optionGroupId,
				CommonConst.LOGISTICS_COMPANYId.getCode()));
		return list;
	}
    

	/**
	 * 设置剩余积分
	 */
	public void setOrderMainIntegral(String memberNo) {
		HessianResult pointResult =pointsCommonAccountHsService.getMemberUsePoints(memberNo);
		String responseCode = pointResult.getResponse_code();
		BigDecimal integral = new BigDecimal("0");
		//响应成功
		if("S000000".equals(responseCode)){
			if(null != pointResult.getResponse_data()){
				// 获取剩余积分
				integral = new BigDecimal((Integer) pointResult.getResponse_data());				
			}		
		}
		order.setIntegral(integral);
		
		/*// VIP账户查询
		OmsMemberVipCardOutputDTO omsMemberVipCardOutputDTO = null;
		try {
			omsMemberVipCardOutputDTO = returnChangeOrderService.searchMemberVipCard(memberNo);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		if(null == omsMemberVipCardOutputDTO)
		{
			return;
		}
		// 获取剩余积分
		BigDecimal integral = omsMemberVipCardOutputDTO.getIntegral();
		order.setIntegral(integral);*/
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
	
	
	public IncludeOrNot getIncludeOrNot() {
		return includeOrNot;
	}

	public void setIncludeOrNot(IncludeOrNot includeOrNot) {
		this.includeOrNot = includeOrNot;
	}

	/**
	 * 设置订单页面标题
	 * @param columnId
	 */
	public void setOrderColumnTitle(int columnId) {
		this.orderColumnTitle = "";

		switch (columnId) {
		case OrderColumn.ORDER_REVERSE:
			this.orderColumnTitle = OrderColumnTitle.REVERSE;
			break;
		case OrderColumn.ORDER_ALL:
			this.orderColumnTitle = OrderColumnTitle.ALL;
			break;
		case OrderColumn.ORDER_NEED_CONFIRMING: // 待审核
			this.orderColumnTitle = OrderColumnTitle.NEEDCONFIRM;
			break;
		case OrderColumn.ORDER_NEED_PAY: // 未支付
			this.orderColumnTitle = OrderColumnTitle.NEEDPAY;
			break;
		case OrderColumn.ORDER_STOCKOUT: // 缺货
			// ......
			break;
		case OrderColumn.ORDER_LACKED: // 待出库
			this.orderColumnTitle = OrderColumnTitle.LACKED;

			break;
		case OrderColumn.ORDER_VALIDATED: // 待送货
			this.orderColumnTitle = OrderColumnTitle.VALIDATED;
			break;
		case OrderColumn.ORDER_UN_FINISHED: // 未完成
			// .....
			this.orderColumnTitle = OrderColumnTitle.UNFINISHED;
			break;
		case OrderColumn.ORDER_FINISHED: // 已完成
			this.orderColumnTitle = OrderColumnTitle.FINISHED;
			break;
		case OrderColumn.ORDER_CALCELED: // 已取消
			this.orderColumnTitle = OrderColumnTitle.CANCELED;
			break;
		case OrderColumn.ORDER_VIRTUAL: // 虚拟订单
			// ...............
			this.orderColumnTitle = OrderColumnTitle.VIRTUAL;
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

	public Boolean getIsCreated() {
		return isCreated;
	}

	public void setIsCreated(Boolean isCreated) {
		this.isCreated = isCreated;
	}
	
	public String getIpAddr() {
		HttpServletRequest request = getRequest();

		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	

	public String getBlackType() {
		return blackType;
	}

	public void setBlackType(String blackType) {
		this.blackType = blackType;
	}

	public Boolean getIsDetail() {
		return isDetail;
	}

	public void setIsDetail(Boolean isDetail) {
		this.isDetail = isDetail;
	}

	public Boolean getIsModify() {
		return isModify;
	}

	public void setIsModify(Boolean isModify) {
		this.isModify = isModify;
	}
	public Map<String, String> getConfigMap() {
        return configMap;
    }	
	
	public AbstractOrderAction(){
		this.pager.setPageSize(10);
	}
	
	/**
	 * 判断生鲜订单是否能退、换货  true:是   false:否   (订单完成后２４小时内可以发起退换货申请,之后不行)
	 * @param shipCat   990：生鲜商品
	 * @param finishTime 订单完成时间
	 * @return
	 */
	public boolean isfreshFoodCanRetChg(String shipCat,Date finishTime) 
    {
        if(null == shipCat || null == finishTime)
        {
            return true;
        }
        if(CommonConst.OrderSub_ShipCat_fastFood.getCode().equals(shipCat))
        {
            Date now = DateUtil.getNowDateTime();
            Long diffMin = DateUtil.dateDiffMin(finishTime.toString(), now.toString(), "yyyy-MM-dd HH:mm:ss.SSS");
            Long diffHour = diffMin/60L;
            if(diffHour.longValue() > 24L)
            {
                return false;
            }
        }
        return true;
    }

	/**
	 * @return the logisticsCodes
	 */
	public List<OptionBean> getLogisticsCodes() {
		return logisticsCodes;
	}

	/**
	 * @param logisticsCodes the logisticsCodes to set
	 */
	public void setLogisticsCodes(List<OptionBean> logisticsCodes) {
		this.logisticsCodes = logisticsCodes;
	}

	/**
	 * @return the wareHouses
	 */
	public List<WarehouseData> getWareHouses() {
		return wareHouses;
	}

	/**
	 * @param wareHouses the wareHouses to set
	 */
	public void setWareHouses(List<WarehouseData> wareHouses) {
		this.wareHouses = wareHouses;
	}
}
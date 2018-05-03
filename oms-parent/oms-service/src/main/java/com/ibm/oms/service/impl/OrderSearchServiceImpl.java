package com.ibm.oms.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.constant.Global;
import com.ibm.oms.dao.constant.OrderColumn;
import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.dao.constant.PayType;
import com.ibm.oms.dao.impl.OrderMainDaoImpl;
import com.ibm.oms.dao.impl.OrderPayDaoImpl;
import com.ibm.oms.dao.impl.OrderSearchDaoImpl;
import com.ibm.oms.dao.impl.TransportAreaDaoImpl;
import com.ibm.oms.domain.persist.OrderDic;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderItemVirtual;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderMain_;
import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.oms.domain.persist.OrderPayInfo;
import com.ibm.oms.domain.persist.OrderPayMode;
import com.ibm.oms.domain.persist.OrderPayMode_;
import com.ibm.oms.domain.persist.OrderSearch;
import com.ibm.oms.domain.persist.StatusDict;
import com.ibm.oms.intf.constant.OrderMainConst;
import com.ibm.oms.service.OrderDicService;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.OrderItemVirtualService;
import com.ibm.oms.service.OrderPayModeService;
import com.ibm.oms.service.OrderSearchService;
import com.ibm.oms.service.TransportAreaCacheService;
import com.ibm.oms.service.util.CommonCacheUtil;
import com.ibm.oms.service.util.CommonUtilService;
import com.ibm.oms.service.util.PaymentMethodUtil;
import com.ibm.oms.service.util.SelfTakeMerchantUtil;
import com.ibm.oms.service.util.StatusUtil;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.model.payment.PaymentMethod;
import com.ibm.sc.model.shipping.SelfTakePoint;
import com.ibm.sc.model.sys.Option;
import com.ibm.sc.service.impl.BaseServiceImpl;
import com.ibm.sc.service.shipping.SelfTakePointService;
import com.ibm.sup.rs.bean.TransportAreaBean;
import com.ibm.sup.rs.service.TransportAreaRsService;

/**
 * 
 * Creation date:2014-03-14 04:20:47
 * 
 * @author:Yong Hong Luo
 */
@Service("orderSearchService")
public class OrderSearchServiceImpl extends BaseServiceImpl<OrderSearch, Long> implements OrderSearchService {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 顶级区域ID，如：中国
	 */
	private static final Long BASE_AREA_ID = 1602L;

	/**
	 * 顶级区域级别，如：中国
	 */
	private static final Long BASE_AREA_LEVEL = 0L;

	@Resource
	TransportAreaCacheService transportAreaCacheService;
	@Resource
	private SelfTakePointService selfTakePointService;
	@Resource
	private OrderItemService orderItemService;
	@Resource
	private OrderItemVirtualService orderItemVirtualService;
	@Resource
	OrderDicService orderDicService;
	@Resource
	OrderPayModeService orderPayModeService;
	@Resource
	CommonUtilService commonUtilService;
	@Autowired
	PaymentMethodUtil paymentMethodUtil;
	private OrderSearchDaoImpl orderSearchDaoImpl;

	@Autowired
	CommonCacheUtil commonCacheUtil;

	@Autowired
	StatusUtil statusUtil;
	
	@Resource
	private TransportAreaRsService transportAreaRsService;
	
	
	@Autowired
	private SelfTakeMerchantUtil selfTakeMerchantUtil;

	@Autowired
	public void setOrderSearchDao(OrderSearchDaoImpl orderSearchDao) {
		super.setBaseDao(orderSearchDao);
		this.orderSearchDaoImpl = orderSearchDao;
	}

	private OrderMainDaoImpl orderMainDaoImpl;

	@Autowired
	public void setOrderMainDao(OrderMainDaoImpl orderMainDao) {
		this.orderMainDaoImpl = orderMainDao;
	}

	private TransportAreaDaoImpl transportAreaDaoImpl;

	@Autowired
	public void setTransportAreaDao(TransportAreaDaoImpl transportAreaDao) {
		this.transportAreaDaoImpl = transportAreaDao;
	}

	private OrderPayDaoImpl orderPayDaoImpl;

	@Autowired
	public void setOrderPayDao(OrderPayDaoImpl orderPayDao) {
		this.orderPayDaoImpl = orderPayDao;
	}

	/*
	 * 非分页查询
	 * 
	 * @see com.ibm.sc.service.oms.OrderSearchService#findByOrderSearch(int,
	 * com.ibm.sc.oms.persist.OrderSearch)
	 */
	public List<OrderSearch> findByOrderSearch(int columnId, OrderSearch orderSearch) {
		Long t1 = System.currentTimeMillis();
		List<String> statusPayOther = new ArrayList<String>();
		List<String> statusTotalOther = new ArrayList<String>();

		// 查询前的相关操作
		operations4BeforeQuery(columnId, orderSearch, statusPayOther, statusTotalOther);
		Long t2 = System.currentTimeMillis();
		List<String> addressCodes = transportAreaCacheService.setAddressCodeByTransportAreaId(orderSearch.getTransportAreaId());
		List<OrderSearch> list = orderSearchDaoImpl.findByOrderSearch(columnId, orderSearch, statusPayOther, statusTotalOther);
		Long t3 = System.currentTimeMillis();
		// 查询后的相关操作
		operations4AfterQuery(list, columnId);
		Long t4 = System.currentTimeMillis();
		logger.info((t2 - t1) + ":" + (t3 - t2) + ":" + (t4 - t3) + ":");
		return list;
	}

	/*
	 * 分页查询
	 * 
	 * @see com.ibm.sc.service.oms.OrderSearchService#findByOrderSearch(int,
	 * com.ibm.sc.oms.persist.OrderSearch, com.ibm.sc.bean.Pager)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Pager findByOrderSearch(int columnId, OrderSearch orderSearch, Pager pager) {

		List<String> statusPayOther = new ArrayList<String>();
		List<String> statusTotalOther = new ArrayList<String>();

		// 查询前的相关操作
		Long t1 = System.currentTimeMillis();
		operations4BeforeQuery(columnId, orderSearch, statusPayOther, statusTotalOther);
		Long t2 = System.currentTimeMillis();
		if (columnId == 1) {
			statusTotalOther.add("0143");

		}

		Pager page = this.orderSearchDaoImpl.findByOrderSearch(columnId, orderSearch, pager, statusPayOther, statusTotalOther);
		Long t3 = System.currentTimeMillis();
		List<OrderSearch> resultList = (List<OrderSearch>) page.getList();

		// 查询后的相关操作
		operations4AfterQuery(resultList, columnId);
		Long t4 = System.currentTimeMillis();
		page.setList((List<OrderSearch>) resultList);
		logger.info("operations4BeforeQuery:{}, findByOrderSearch:{}, operations4AfterQuery:{}", t2 - t1, t3 - t2, t4 - t3);
		return page;
	}

	/**
	 * 查询前的相关操作
	 * 
	 * @param columnId
	 *            左侧树id
	 * @param orderSearch
	 *            查询对象
	 * @param statusPayOther
	 * @param statusTotalOther
	 */
	private void operations4BeforeQuery(int columnId, OrderSearch orderSearch, List<String> statusPayOther, List<String> statusTotalOther) {

		String statusPay = Global.ALL_STRING;// 主订单：支付状态
		String statusTotal = Global.ALL_STRING;// 主订单：总状态

		// 根据菜单列id设置OrderSearch的属性值
		setOrderSearchAttrByColumnId(columnId, orderSearch, statusPay, statusTotal, statusPayOther, statusTotalOther);

		// 获取用来搜索的transportAreaId
		getTransportAreaQueryId(orderSearch);

		// 获取用来搜索的自提点id（设置id优先级：自提点（取下拉列表）-> 商户）
		getSelfTakePointQueryId(orderSearch);

		// 获取用来搜索的payCode
		getQueryPayCode(orderSearch);
	}

	/**
	 * 查询后的相关操作(对之前查询的结果进行一些处理)
	 * 
	 * @param list
	 *            查询返回的列表
	 */
	private void operations4AfterQuery(List<OrderSearch> list, int columnId) {
		for (OrderSearch orderSearch : list) {
			//添加操作标记
			setOprationFlag(orderSearch);
			// 转换DistributeType的code为name
			changeDistributeCode2Name(orderSearch);

			// 转换OrderStatus的code为name
			changeOrderStatusCode2Name(orderSearch);

			// 转换订单渠道(OrderSource)的code为name
			changeOrderSourceCode2Name(orderSearch);

			// 根据addressCode查询SM_TRANSPORT_AREA中的areaName，将查询出来的areaName设置到orderSearch中
			setTransportAreaName(orderSearch);

			// 设置支付方式和支付金额
			setPayNameAndPayAmount(orderSearch);

			// 设置商品信息
			setOrderItemInfo(orderSearch);

			// 设置虚拟商品信息
			setOrderVirtualItemInfo(orderSearch);

			// 设置退款单的关联原销售单的退款完成时间
			setReverseOrderRelatedFinishTime(orderSearch);
			
			
		}
	}
	
	/**
	 * 设置操作标记
	 * Description:
	 * @param ordersearch
	 */
	private void setOprationFlag(OrderSearch orderSearch){
		List<String> flags = new ArrayList<String>();
		//客户备注
		if(StringUtils.isNotBlank(orderSearch.getClientRemark())){
			flags.add("客户留言");
		}
		if(StringUtils.isNotBlank(orderSearch.getClientServiceRemark())){
			flags.add("备注");
		}
		
		//退款 0全部退款,1部分退款
		if(StringUtils.isNotBlank(orderSearch.getRefundType())){
			if(CommonConst.Order_Refund_Type_All.getCode().equals(orderSearch.getRefundType())){
				flags.add("全部退款");
			}else {
				flags.add("部分退款");
			}
		}
		
		//拆分单  
		if(Integer.valueOf(OrderMainConst.ORDERMAIN_IS_SPLIT_YES.getCode()) == orderSearch.getIsSplit()){
			flags.add("被拆分");
		}
		if(Integer.valueOf(OrderMainConst.ORDERMAIN_IS_SPLIT_NEW_ORDER.getCode()) == orderSearch.getIsSplit()){
			flags.add("拆分单");
		}
		//换货单
		if(null != orderSearch.getIsBarter() && CommonConst.OrderMain_Is_Barter_Yes.getCodeInt() == orderSearch.getIsBarter()){
			flags.add("换货单");
		}
		//退单
		if(orderSearch.getBillType() == CommonConst.OrderMain_BillType_Negative.getCodeLong()){
			flags.add("退");
		}
		//挂起
		if((OrderStatus.ORDER_SUSPENSION.getCode()).equals(orderSearch.getStatusTotal())){
			flags.add("挂起");
		}
		orderSearch.setFlags(flags);
	}




	/**
	 * 获取用来搜索的payCode
	 * 
	 * @param orderSearch
	 */
	private void getQueryPayCode(OrderSearch orderSearch) {
		String payCode = orderSearch.getPayCode();
		if (null == payCode) {
			return;
		}
		// 如果是货到付款
		if (PayType.PAY_ON_ARRIVE.getId().equals(payCode)) {
			List<String> payCodeList = new ArrayList<String>();
			// 货到付款
			payCodeList.add(PayType.PAY_ON_ARRIVE.getId());
			// 现金
			payCodeList.add(PayType.CASH.getId());
			// 银行卡
			payCodeList.add(PayType.BANK_CARD.getId());
			// 天虹购物卡
			payCodeList.add(PayType.SHOPPING_CARD.getId());
			orderSearch.setPayCodeList(payCodeList);
			orderSearch.setPayCode(null);
		}
		// 如果是网银在线支付
		else if (PayType.ONLINE.getId().equals(payCode)) {
			List<String> payCodeList = new ArrayList<String>();
			List<PaymentMethod> list = getPaymentMethodList();
			payCodeList.add(PayType.ONLINE.getId());
			for (PaymentMethod pay : list) {
				payCodeList.add(String.valueOf(pay.getId()));
			}

			// 加上支付宝网银支付的(因为网银在线支付包含网银在线支付)
			payCodeList.add(PayType.ALIPAY_CHINA_BANK.getId());
			payCodeList.add(PayType.ALIPAY_NONGYE_BANK.getId());
			payCodeList.add(PayType.ALIPAY_PUDONG_BANK.getId());
			payCodeList.add(PayType.ALIPAY_XINGYE_BANK.getId());
			payCodeList.add(PayType.ALIPAY_GUANGDONG_BANK.getId());
			payCodeList.add(PayType.ALIPAY_SHENZHENG_BANK.getId());
			payCodeList.add(PayType.ALIPAY_MINSHENG_BANK.getId());
			payCodeList.add(PayType.ALIPAY_ZHONGXIN_BANK.getId());
			payCodeList.add(PayType.ALIPAY_GUANGDA_BANK.getId());
			payCodeList.add(PayType.ALIPAY_YOUZHENG_BANK.getId());
			payCodeList.add(PayType.ALIPAY_BEIJING_BANK.getId());
			orderSearch.setPayCodeList(payCodeList);
			orderSearch.setPayCode(null);
		}
		// 如果是支付宝网银支付
		else if (PayType.ALIPAY_ONLINE.getId().equals(payCode)) {
			List<String> payCodeList = new ArrayList<String>();
			payCodeList.add(PayType.ALIPAY_ONLINE.getId());
			payCodeList.add(PayType.ALIPAY_CHINA_BANK.getId());
			payCodeList.add(PayType.ALIPAY_NONGYE_BANK.getId());
			payCodeList.add(PayType.ALIPAY_PUDONG_BANK.getId());
			payCodeList.add(PayType.ALIPAY_XINGYE_BANK.getId());
			payCodeList.add(PayType.ALIPAY_GUANGDONG_BANK.getId());
			payCodeList.add(PayType.ALIPAY_SHENZHENG_BANK.getId());
			payCodeList.add(PayType.ALIPAY_MINSHENG_BANK.getId());
			payCodeList.add(PayType.ALIPAY_ZHONGXIN_BANK.getId());
			payCodeList.add(PayType.ALIPAY_GUANGDA_BANK.getId());
			payCodeList.add(PayType.ALIPAY_YOUZHENG_BANK.getId());
			payCodeList.add(PayType.ALIPAY_BEIJING_BANK.getId());
			orderSearch.setPayCodeList(payCodeList);
			orderSearch.setPayCode(null);
		}
	}

	/**
	 * 获取支付方式列表
	 * 
	 * @return
	 */
	private List<PaymentMethod> getPaymentMethodList() {
		List<PaymentMethod> list = new ArrayList<PaymentMethod>();
		PaymentMethod paymentMethod = new PaymentMethod();
		paymentMethod.setId(Long.valueOf(PayType.ONLINE.getId()));
		paymentMethod.setName(PayType.ONLINE.getPayName());
		list.add(paymentMethod);
		list.addAll(paymentMethodUtil.getPaymentMethodList());

		return list;
	}

	// 设置退款单的关联原销售单的退款完成时间
	public void setReverseOrderRelatedFinishTime(OrderSearch orderSearch) {
		// 判断是否需退款
		boolean isNeedRefund = commonUtilService.checkIfNeedRefund(orderSearch.getIfNeedRefund());
		if (!isNeedRefund) {
			return;
		}
		// 如果不是逆向单
		if (!Long.valueOf(OrderColumn.ORDER_REVERSE).equals(orderSearch.getBillType())) {
			return;
		}
		// 关联的原销售订单
		OrderMain relatedOrderMain = orderMainDaoImpl.getByField(OrderMain_.orderNo, orderSearch.getOrderRelatedOrigin());
		if (null == relatedOrderMain || null == relatedOrderMain.getFinishTime()) {
			return;
		}
		Date relatedFinishTime = relatedOrderMain.getFinishTime();
		// 将关联的原销售单的退款完成时间放入到退款单
		orderSearch.setRelatedFinishTime(relatedFinishTime);
	}

	/**
	 * 设置商品信息
	 * 
	 * @param resultList
	 */
	public void setOrderItemInfo(OrderSearch orderSearch) {
		String orderMainNo = "";
		List<OrderItem> orderItems = null;
		// 如果是虚拟商品
		if (CommonConst.OrderMain_OrderType_VIRTUAL.getCode().equals(orderSearch.getOrderType())) {
			return;
		}
		orderMainNo = orderSearch.getOrderNo();
		if (null == orderMainNo) {
			return;
		}
		// 根据子订单号加载正向订单明细
		orderItems = orderItemService.getByOrdeNo(orderMainNo);// .getByOrdeSubNo(orderSub.getOrderSubNo());

		if (null != orderItems && orderItems.size() > 0) {
			orderSearch.setOrderItems(orderItems);
		}
	}

	/**
	 * 设置商品信息
	 * 
	 * @param resultList
	 */
	public void setOrderVirtualItemInfo(OrderSearch orderSearch) {
		String orderMainNo = "";
		List<OrderItemVirtual> orderItems = null;
		// 如果不是虚拟商品
		if (!CommonConst.OrderMain_OrderType_VIRTUAL.getCode().equals(orderSearch.getOrderType())) {
			return;
		}
		orderMainNo = orderSearch.getOrderNo();
		if (null == orderMainNo) {
			return;
		}
		/*
		 * orderMain = orderMainDaoImpl.findByOrderNo(orderMainNo); if
		 * (orderMain.getOrderSubs() != null && orderMain.getOrderSubs().size()
		 * > 0) {
		 * 
		 * // orderItems = orderSub.getOrderItems();
		 * 
		 * // 根据子订单号加载正向订单明细 orderItems =
		 * orderItemVirtualService.getByOrdeNo(orderMain .getOrderNo());
		 * 
		 * if (null != orderItems && orderItems.size() > 0) {
		 * orderSearch.setOrderItemVirtuals(orderItems); } }
		 */

		// 根据子订单号加载正向订单明细
		orderItems = orderItemVirtualService.getByOrdeNo(orderMainNo);

		if (null != orderItems && orderItems.size() > 0) {
			orderSearch.setOrderItemVirtuals(orderItems);
		}
	}

	/**
	 * 根据菜单列id设置OrderSearch的属性值
	 * 
	 * @param columnId
	 *            菜单列id
	 * @param orderSearch
	 * @param statusPay
	 *            主订单：支付状态
	 * @param statusTotal
	 *            主订单：总状态
	 * @param statusPayOther
	 *            子订单：支付状态
	 * @param statusTotalOther
	 *            子订单：总状态
	 */
	public void setOrderSearchAttrByColumnId(int columnId, OrderSearch orderSearch, String statusPay, String statusTotal, List<String> statusPayOther,
			List<String> statusTotalOther) {
		switch (columnId) {
		case OrderColumn.ORDER_DRAWBACK: // 退款单
			orderSearch.setBillType(new Long(OrderColumn.ORDER_REVERSE));

			// 查询退款中和退款完成订单
			List<String> statusTotalList = new ArrayList<String>();
			statusTotalList.add(OrderStatus.RET_ORDER_REFUNDING.getCode());
			statusTotalList.add(OrderStatus.RET_ORDER_RETURN_FINISHED.getCode());
			orderSearch.setStatusTotalList(statusTotalList);

			List<Long> ifNeedRefundList = new ArrayList<Long>();
			// 需退款(网上天虹财务)
			ifNeedRefundList.add(CommonConst.OrderMain_IfNeedRefund_Yes.getCodeLong());
			// 门店退款
			ifNeedRefundList.add(CommonConst.OrderMain_IfNeedRefund_Yes_Store.getCodeLong());
			orderSearch.setIfNeedRefundList(ifNeedRefundList);
			break;
		case OrderColumn.ORDER_REVERSE: // 逆向订单
			orderSearch.setBillType(new Long(OrderColumn.ORDER_REVERSE));

			// 只查询退、换、拒收
			/*List<String> orderCategoryList = new ArrayList<String>();
			orderCategoryList.add(CommonConst.OrderMain_OrderCategory_Return.getCode());
			orderCategoryList.add(CommonConst.OrderMain_OrderCategory_Change.getCode());
			orderCategoryList.add(CommonConst.OrderMain_OrderCategory_Reject.getCode());
			orderSearch.setOrderCategoryList(orderCategoryList);*/
			break;
		case OrderColumn.ORDER_ALL:
			orderSearch.setBillType(new Long(OrderColumn.ORDER_NEED_CONFIRMING));
			break;
		case OrderColumn.ORDER_NEED_CONFIRMING: // 待审核
			statusTotal = OrderStatus.ORDER_AUDITING_MANUAL.getCode();
			orderSearch.setStatusTotal(statusTotal);
			break;
		case OrderColumn.ORDER_NEED_PAY: // 未支付
			/*
			 * statusPay = OrderStatus.Order_PayStatus_Cash_Paying.getCode();
			 * orderSearch.setStatusPay(statusPay);
			 */
			if (StringUtils.isBlank(orderSearch.getStatusPay())) {
				statusPayOther.add(OrderStatus.Order_PayStatus_Paying.getCode());
				statusPayOther.add(OrderStatus.Order_PayStatus_Cash_Paying.getCode());
			}

			if (!OrderStatus.Order_PayStatus_Paying.getCode().equals(orderSearch.getStatusPay())
					&& !OrderStatus.Order_PayStatus_Cash_Paying.getCode().equals(orderSearch.getStatusPay())) {
				orderSearch.setStatusPay("null");
			}
			// 待支付时判断某些状态是否作不等条件查询 true: 当做不等条件查询 false:不当做不等条件查询
			orderSearch.setIsNotEqual4OrderNeedPay(true);
			statusTotal = OrderStatus.ORDER_PAY_CANCELLED.getCode();

			if (StringUtils.isBlank(orderSearch.getStatusTotal())) {
				orderSearch.setStatusTotal(statusTotal);
				statusTotalOther.add(OrderStatus.ORDER_AUDIT_MANUAL_FAILED.getCode());
				statusTotalOther.add(OrderStatus.ORDER_PAID_CANCEL.getCode());

				break;
			} else {
				if (OrderStatus.ORDER_AUDIT_MANUAL_FAILED.getCode().equals(orderSearch.getStatusTotal())
						|| OrderStatus.ORDER_PAID_CANCEL.getCode().equals(orderSearch.getStatusTotal())
						|| OrderStatus.ORDER_PAY_CANCELLED.getCode().equals(orderSearch.getStatusTotal())) {

					break;
				}

				orderSearch.setStatusTotal(statusTotal);
				statusTotalOther.add(OrderStatus.ORDER_AUDIT_MANUAL_FAILED.getCode());
				statusTotalOther.add(OrderStatus.ORDER_PAID_CANCEL.getCode());
			}
			break;
		case OrderColumn.ORDER_STOCKOUT: // 缺货
			// ......
			break;
		case OrderColumn.ORDER_LACKED: // 待出库
			statusTotal = OrderStatus.ORDER_VALIDATED.getCode();
			orderSearch.setStatusTotal(statusTotal);
			break;
		case OrderColumn.ORDER_VALIDATED: // 待送货
			statusTotal = OrderStatus.ORDER_SENT.getCode();
			if (StringUtils.isBlank(orderSearch.getStatusTotal())) {
				orderSearch.setStatusTotal(statusTotal);
				statusTotalOther.add(OrderStatus.ORDER_POD_SENT.getCode());
				break;
			} else {
				if (OrderStatus.ORDER_POD_SENT.getCode().equals(orderSearch.getStatusTotal())
						|| OrderStatus.ORDER_SENT.getCode().equals(orderSearch.getStatusTotal())) {

					break;
				}

				orderSearch.setStatusTotal(statusTotal);
				statusTotalOther.add(OrderStatus.ORDER_AUDIT_MANUAL_FAILED.getCode());
				statusTotalOther.add(OrderStatus.ORDER_PAID_CANCEL.getCode());
			}
			break;
		case OrderColumn.ORDER_UN_FINISHED: // 未完成
			statusTotal = OrderStatus.ORDER_PAYING.getCode();
			if (StringUtils.isBlank(orderSearch.getStatusTotal())) {
				orderSearch.setStatusTotal(statusTotal);
				statusTotalOther.add(OrderStatus.ORDER_PROCESSING.getCode());
				statusTotalOther.add(OrderStatus.ORDER_AUDITING.getCode());
				statusTotalOther.add(OrderStatus.ORDER_AUDITING_MANUAL.getCode());
				statusTotalOther.add(OrderStatus.ORDER_INVENTORY_DEDUCTING.getCode());
				statusTotalOther.add(OrderStatus.ORDER_INVENTORY_DEDUCT_FAILED.getCode());
				statusTotalOther.add(OrderStatus.ORDER_VALIDATED.getCode());
				statusTotalOther.add(OrderStatus.ORDER_SENT.getCode());
				statusTotalOther.add(OrderStatus.ORDER_POD_SENT.getCode());
				break;
			} else {
				if (OrderStatus.ORDER_PAYING.getCode().equals(orderSearch.getStatusTotal())
						|| OrderStatus.ORDER_PROCESSING.getCode().equals(orderSearch.getStatusTotal())
						|| OrderStatus.ORDER_AUDITING.getCode().equals(orderSearch.getStatusTotal())
						|| OrderStatus.ORDER_AUDITING_MANUAL.getCode().equals(orderSearch.getStatusTotal())
						|| OrderStatus.ORDER_INVENTORY_DEDUCTING.getCode().equals(orderSearch.getStatusTotal())
						|| OrderStatus.ORDER_INVENTORY_DEDUCT_FAILED.getCode().equals(orderSearch.getStatusTotal())
						|| OrderStatus.ORDER_VALIDATED.getCode().equals(orderSearch.getStatusTotal())
						|| OrderStatus.ORDER_SENT.getCode().equals(orderSearch.getStatusTotal())
						|| OrderStatus.ORDER_POD_SENT.getCode().equals(orderSearch.getStatusTotal())) {

					break;
				}

				orderSearch.setStatusTotal(statusTotal);
				statusTotalOther.add(OrderStatus.ORDER_PROCESSING.getCode());
				statusTotalOther.add(OrderStatus.ORDER_AUDITING.getCode());
				statusTotalOther.add(OrderStatus.ORDER_AUDITING_MANUAL.getCode());
				statusTotalOther.add(OrderStatus.ORDER_INVENTORY_DEDUCTING.getCode());
				statusTotalOther.add(OrderStatus.ORDER_INVENTORY_DEDUCT_FAILED.getCode());
				statusTotalOther.add(OrderStatus.ORDER_VALIDATED.getCode());
				statusTotalOther.add(OrderStatus.ORDER_SENT.getCode());
				statusTotalOther.add(OrderStatus.ORDER_POD_SENT.getCode());
			}

			break;
		case OrderColumn.ORDER_FINISHED: // 已完成
			statusTotal = OrderStatus.ORDER_ACCEPTED_PAID.getCode();
			orderSearch.setStatusTotal(statusTotal);
			break;
		case OrderColumn.ORDER_CALCELED: // 已取消
			statusTotal = OrderStatus.ORDER_PAY_CANCELLED.getCode();
			orderSearch.setStatusTotal(statusTotal);
			statusTotalOther.add(OrderStatus.ORDER_PAID_CANCEL.getCode());
			break;
		case OrderColumn.ORDER_VIRTUAL: // 虚拟订单
			orderSearch.setOrderType(CommonConst.OrderMain_OrderType_VIRTUAL.getCode());
			break;
		case OrderColumn.ORDER_SUSPENDED:
			statusTotal = OrderStatus.ORDER_SUSPENSION.getCode();
			orderSearch.setStatusTotal(statusTotal);
			break;
		}
	}

	/**
	 * 获取用来搜索的transportAreaId
	 * 
	 * @param orderSearch
	 */
	public void getTransportAreaQueryId(OrderSearch orderSearch) {
		// 省/直辖市对应的下拉值
		Long state = orderSearch.getState();

		// 城市对应的下拉值
		Long city = orderSearch.getCity();

		// 县区对应的下拉值
		Long county = orderSearch.getCounty();

		// 街道对应的下拉值
		Long street = orderSearch.getStreet();

		// 获取优先级：街道 -> 县区 -> 城市 -> 省/直辖市，即省/直辖市、城市、县区、街道都选择时，则使用街道的code去搜索
		if (null != street) {
			orderSearch.setTransportAreaId(street);
			return;
		}
		if (null != county) {
			orderSearch.setTransportAreaId(county);
			return;
		}
		if ((null != city)) {
			orderSearch.setTransportAreaId(city);
			return;
		}
		if (null != state) {
			orderSearch.setTransportAreaId(state);
			return;
		}
	}

	/**
	 * 获取用来搜索的自提点id（设置id优先级：自提点（取下拉列表）-> 商户）
	 * 
	 * @param orderSearch
	 */
	public void getSelfTakePointQueryId(OrderSearch orderSearch) {
		Long selfTakePointId = orderSearch.getSelfTakePointId();
		Long pointDeliverPartnerId = orderSearch.getPointDeliverPartnerId();
		// 自提点
		if (null != selfTakePointId) {
			orderSearch.setSelfTakePointId(selfTakePointId);
			return;
		}
		// 商户
		if (null != pointDeliverPartnerId) {
			Map<String, List<SelfTakePoint>> selfTakePointMap = selfTakeMerchantUtil.getSelfTakePointMap();
			List<SelfTakePoint> selfTakePoints = selfTakePointMap.get(pointDeliverPartnerId.toString());
			/*
			 * List<SelfTakePoint> selfTakePoints =
			 * selfTakePointService.findByField(SelfTakePoint_.
			 * pointDeliverPartnerId, pointDeliverPartnerId);
			 */ List<String> selfTakePointIdList = new ArrayList<String>();
			if (selfTakePoints != null && !selfTakePoints.isEmpty()) {
				for (SelfTakePoint selfTakePoint : selfTakePoints) {
					selfTakePointIdList.add(String.valueOf(selfTakePoint.getId()));
				}
			}
			orderSearch.setSelfTakePointIdList(selfTakePointIdList);
		}
	}

	/**
	 * 转换DistributeType的code为name
	 * 
	 * @param resultList
	 */
	private void changeDistributeCode2Name(OrderSearch orderSearch) {
		// 配送类型
		String distributeType = orderSearch.getDistributeType();
		Option option = (Option) commonCacheUtil.getOptionMultiKeyMap().get(CommonConst.Option_OptionGroupId.getCode(), distributeType);
		if (null != option) {
			orderSearch.setDistributeTypeName(option.getName());
		}
	}

	/**
	 * 转换订单渠道(OrderSource)的code为name
	 * 
	 * @param resultList
	 */
	private void changeOrderSourceCode2Name(OrderSearch orderSearch) {
		// 订单渠道
		String orderSource = orderSearch.getOrderSource();
		OrderDic orderDic = commonCacheUtil.getOrderSourceMap().get(orderSource);
		if (null != orderDic) {
			orderSearch.setOrderSourceName(orderDic.getDicName());
		}
	}

	/**
	 * 转换OrderStatus的code为name
	 * 
	 * @param resultList
	 */
	private void changeOrderStatusCode2Name(OrderSearch orderSearch) {

		Map<String, StatusDict> orderStatusMap = statusUtil.getOrderStatusMap();

		// 设置OrderSearch中的处理状态的code
		orderSearch.setStatusTotalCode(orderSearch.getStatusTotal());
		orderSearch.setStatusConfirmCode(orderSearch.getStatusConfirm());
		// 审核状态
		String statusConfirm = orderSearch.getStatusConfirm();
		// 支付状态
		String statusPay = orderSearch.getStatusPay();
		// 物流状态
		String logisticsStatus = orderSearch.getLogisticsStatus();

		StatusDict totalStatusDict = orderStatusMap.get(orderSearch.getStatusTotal());
		if (null != totalStatusDict) {
			orderSearch.setStatusTotalName(totalStatusDict.getDisplayName());
		}

		StatusDict confirmDict = orderStatusMap.get(statusConfirm);
		if (null != confirmDict) {
			orderSearch.setStatusConfirmName(confirmDict.getStatusName());
		}

		StatusDict payDict = orderStatusMap.get(statusPay);
		if (null != payDict) {
			orderSearch.setStatusPayName(payDict.getDisplayName());
		}

		StatusDict logisticsStatusDict = orderStatusMap.get(logisticsStatus);
		if (null != logisticsStatusDict) {
			orderSearch.setLogisticsStatusName(logisticsStatusDict.getStatusName());
		}
	}

	/**
	 * 根据addressCode查询SM_TRANSPORT_AREA中的areaName，将查询出来的areaName设置到orderSearch中
	 * 
	 * @param resultList
	 */
	private void setTransportAreaName(OrderSearch orderSearch) {
		TransportAreaBean provice = transportAreaRsService.getByCode(orderSearch.getDeliveredProvice());
		TransportAreaBean city = transportAreaRsService.getByCode(orderSearch.getDeliveredCity());
		TransportAreaBean county = transportAreaRsService.getByCode(orderSearch.getDeliveredCounty());
		orderSearch.setAreaName(String.format("%s%s%s", provice == null? "" : provice.getAreaName(), city == null? "" : city.getAreaName(), county == null? "" : county.getAreaName()));
		logger.debug(orderSearch.getAreaName());
		// TransportArea transportArea =
		// commonCacheUtil.getTransportAreaMap().get(addressCode);
		// if(transportArea==null){
		// return ;
		// }
		// String transportAreaName = transportArea.getAreaName();
		// orderSearch.setAreaName(transportAreaName);
		// Long parentId = transportArea.getParentId();
		// if (parentId != null) {
		// orderSearch.setAreaName();
		// setParentTransportAreaName(transportAreaName, parentId, orderSearch,
		// transportArea);
		// }
	}

	/**
	 * 根据父id查找父区域，将查询出的areaName设置到orderSearch中
	 * 
	 * @param transportAreaName
	 * @param parentId
	 * @param orderSearch
	 * @param transportArea
	 */
	// private void setParentTransportAreaName(String transportAreaName,
	// Long parentId, OrderSearch orderSearch, TransportArea transportArea) {
	//
	// List<TransportArea> transportAreas =
	// (List<TransportArea>)commonCacheUtil.getTransportIdAreaMap().get(parentId);
	// if((null == transportAreas) || transportAreas.isEmpty())
	// {
	// return;
	// }
	//
	// for (TransportArea newtransportArea : transportAreas) {
	// if((null == newtransportArea) ||
	// BASE_AREA_LEVEL.equals(newtransportArea.getAreaLevel()))
	// {
	// break;
	// }
	//
	// transportAreaName = newtransportArea.getAreaName() + transportAreaName;
	// orderSearch.setAreaName(transportAreaName);
	// setParentTransportAreaName(transportAreaName,
	// newtransportArea.getParentId(), orderSearch, newtransportArea);
	// }
	// }

	/**
	 * 设置支付方式和支付金额
	 * 
	 * @param resultList
	 */
	public void setPayNameAndPayAmount(OrderSearch orderSearch) {
		String order_no = null;
		String statusPay = null;
		// Map<String, OrderPayInfo> payNameAmountMap = null;

		order_no = orderSearch.getOrderNo();
		statusPay = orderSearch.getStatusPay();
		// payNameAmountMap = new HashMap<String, OrderPayInfo>();
		List<OrderPayInfo> orderPayInfoList = new ArrayList<OrderPayInfo>();
		// 货到付款未支付，待支付,支付取消,货到付款无需退款
		if (null != statusPay && (statusPay.equals(OrderStatus.Order_PayStatus_Paying.getCode())
				|| statusPay.equals(OrderStatus.Order_PayStatus_Cash_Paying.getCode())
				|| statusPay.equals(OrderStatus.Order_PayStatus_Cancelled.getCode())
				|| statusPay.equals(OrderStatus.Order_PayStatus_Cash_Noreturn.getCode()))) {
			List<OrderPayMode> orderPayModes = orderPayModeService.findByField(OrderPayMode_.orderNo, order_no);
			for (OrderPayMode orderPayMode : orderPayModes) {
				OrderPayInfo orderPayInfo = new OrderPayInfo();
				orderPayInfo.setPayName(orderPayMode.getPayModeName());
				orderPayInfo.setPayAmount(orderPayMode.getPayAmount());
				orderPayInfoList.add(orderPayInfo);
				// payNameAmountMap.put(orderPayMode.getPayModeName(),
				// orderPayMode.getPayAmount());
			}
		}

		// 已支付...
		if (null == order_no) {
			OrderPayInfo orderPayInfo = new OrderPayInfo();
			orderPayInfo.setPayName("");
			orderPayInfo.setPayAmount(BigDecimal.valueOf(0));
			orderPayInfoList.add(orderPayInfo);
			// payNameAmountMap.put("", (BigDecimal.valueOf(0)));
		} else {
			List<OrderPay> orderPays = this.orderPayDaoImpl.getByOrderMainNo(order_no);
			if (null == orderPays) {
				OrderPayInfo orderPayInfo = new OrderPayInfo();
				orderPayInfo.setPayName("");
				orderPayInfo.setPayAmount(BigDecimal.valueOf(0));
				orderPayInfoList.add(orderPayInfo);
				// payNameAmountMap.put("", (BigDecimal.valueOf(0)));
			} else {
				Map<String, PaymentMethod> paymentMethodMap = paymentMethodUtil.getRefundMethodMap();
				for (OrderPay orderPay : orderPays) {
					String payName = "";
					String payCode = orderPay.getPayCode();
					Date payTime = null;
					if (null != payCode && !"".equals(payCode) && null != paymentMethodMap.get(payCode)) {
						// 根据payCode从PaymentMethod中取name
						payName = paymentMethodMap.get(payCode).getName();
					}

					// 如果orderPay中的payName不为空则取payName值
					if (null != orderPay.getPayName() && !"".equals(orderPay.getPayName())) {
						payName = orderPay.getPayName();
					}
					if (null != orderPay.getPayTime()) {
						payTime = orderPay.getPayTime();
					}

					OrderPayInfo orderPayInfo = new OrderPayInfo();
					orderPayInfo.setPayName(payName);
					orderPayInfo.setPayAmount(orderPay.getPayAmount());
					orderPayInfo.setPayTime(payTime);
					orderPayInfo.setPayNo(orderPay.getPayNo());
					// payNameAmountMap.put(payName, orderPay.getPayAmount());

					orderPayInfoList.add(orderPayInfo);
				}
			}
		}

		// orderSearch.setPayNameAmountMap(payNameAmountMap);
		orderSearch.setOrderPayInfoList(orderPayInfoList);
	}
}

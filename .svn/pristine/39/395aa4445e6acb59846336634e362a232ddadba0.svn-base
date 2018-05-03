package com.ibm.oms.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.client.dto.order.OrderInvoiceClientDTO;
import com.ibm.oms.client.dto.order.OrderItemClientDTO;
import com.ibm.oms.client.dto.order.OrderMainClientDTO;
import com.ibm.oms.client.dto.order.OrderPayClientDTO;
import com.ibm.oms.client.dto.order.OrderPromotionClientDTO;
import com.ibm.oms.client.dto.order.OrderQueryClientDTO;
import com.ibm.oms.client.dto.order.OrderSubClientDTO;
import com.ibm.oms.dao.constant.OrderColumn;
import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.dao.constant.OrderStatusAction;
import com.ibm.oms.dao.intf.OrderItemDao;
import com.ibm.oms.dao.intf.OrderMainDao;
import com.ibm.oms.dao.intf.OrderStatusLogDao;
import com.ibm.oms.domain.persist.OrderAuditConfig;
import com.ibm.oms.domain.persist.OrderAuditMerchantConfig;
import com.ibm.oms.domain.persist.OrderAuditRegionConfig;
import com.ibm.oms.domain.persist.OrderInvoice;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderItem_;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderMain_;
import com.ibm.oms.domain.persist.OrderOperateLog;
import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.oms.domain.persist.OrderPayMode;
import com.ibm.oms.domain.persist.OrderPayMode_;
import com.ibm.oms.domain.persist.OrderPromotion;
import com.ibm.oms.domain.persist.OrderStatusLog;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.domain.persist.OrderSub_;
import com.ibm.oms.intf.intf.BBCBatchRemarkDTO;
import com.ibm.oms.intf.intf.BBCUpdateOrderDTO;
import com.ibm.oms.intf.intf.BBCUpdateOrderStatusDTO;
import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.service.OrderAuditConfigService;
import com.ibm.oms.service.OrderAuditMerchantConfigService;
import com.ibm.oms.service.OrderAuditRegionConfigService;
import com.ibm.oms.service.OrderItemPayService;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderOperateLogService;
import com.ibm.oms.service.OrderPayModeService;
import com.ibm.oms.service.OrderPayService;
import com.ibm.oms.service.OrderPromotionService;
import com.ibm.oms.service.OrderStatusLogService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.OrderStatusService;
import com.ibm.oms.service.util.CommonConstService;
import com.ibm.oms.service.util.CommonString;
import com.ibm.oms.service.util.UserUtil;
import com.ibm.product.dto.SkuOrderBean;
import com.ibm.product.intf.SkuClientService;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.model.sys.User;
import com.ibm.sc.service.impl.BaseServiceImpl;
import com.ibm.sc.service.sys.UserService;
import com.ibm.sc.util.BeanUtils;

/**
 * 
 * Creation date:2014-03-14 04:20:47
 * 
 * @author:Yong Hong Luo
 */
@Service("orderMainService")
public class OrderMainServiceImpl extends BaseServiceImpl<OrderMain, Long> implements OrderMainService {
	private static final String UPDATE_CLIENT_REMARK_CONTENT = "修改客服备注";
	private static final String UPDATE_CLIENT_REMARK_LOG_CONTENT = "等级:%s 备注: %s";
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private String errorMsgMain = "OrderCreateServiceImpl.writeOrderMain cloneBean throws Exception";
	private String errorMsgSub = "OrderCreateServiceImpl.writeOrderSub cloneBean throws Exception";
	private String errorMsgInvoice = "OrderCreateServiceImpl.writeOrderSub, write orderInvoice cloneBean throws Exception";
	private String errorMsgItem = "OrderCreateServiceImpl.writeOrderItem cloneBean throws Exception";
	private String errorMsgPromo = "OrderCreateServiceImpl.writeOrderPromo cloneBean throws Exception";
	private String rsExceptionString = "get url {} throws exception: {}";

	@Resource
	protected UserService userService;
	
	@Autowired
    private UserUtil userUtil;
	@Autowired
	OrderStatusLogService orderStatusLogService;
	@Autowired
	OrderPromotionService orderPromotionService;
	@Autowired
	OrderItemPayService orderItemPayService;
	@Autowired
	OrderPayModeService orderPayModeService;
	@Autowired
	OrderItemService orderItemService;
	@Autowired
	SkuClientService skuClientService;
	private OrderMainDao orderMainDao;
	private OrderItemDao orderItemDao;
	@Autowired
	private OrderStatusLogDao orderStatusLogDao;

	@Autowired
	public void setOrderMainDao(OrderMainDao orderMainDao) {
		super.setBaseDao(orderMainDao);
		this.orderMainDao = orderMainDao;
	}

	@Autowired
	private OrderPayService orderPayService;
	@Autowired
	private OrderSubService orderSubService;
	@Autowired
	OrderNoService orderNoService;
	@Autowired
	protected OrderOperateLogService orderOperateLogService;

	@Autowired
	private OrderAuditMerchantConfigService orderAuditMerchantConfigService;

	@Autowired
	private OrderAuditRegionConfigService orderAuditRegionConfigService;

	@Autowired
	private OrderAuditConfigService orderAuditConfigService;

	@Autowired
	private OrderStatusService orderStatusService;

	public OrderMain getByOrderItemId(Long orderItemId) {
		// ..............
		return null;
	}

	public OrderMain findByOrderNo(String orderMainNo) {
		OrderMain order = this.orderMainDao.findByOrderNo(orderMainNo);
		loadOrderMainOthers(order);
		return order;
	}

	public OrderMain findByOrderNoForReturn(String orderMainNo) {
		OrderMain order = this.orderMainDao.findByOrderNo(orderMainNo);
		loadOrderMainOthersForReturn(order);
		return order;
	}

	/**
	 * 通过销售订单查询逆向订单
	 * 
	 * @param saleId
	 *            销售订单号
	 * @param orderCategory
	 *            意向单类型
	 * @return
	 */
	public List<OrderMain> getBackOrderBySaleNo(Long saleNo, String orderCategory) {
		return this.orderMainDao.getBackOrderBySaleNo(saleNo, orderCategory);
	}

	/**
	 * 通过子订单号查询主订单
	 * 
	 * @param subOrderId
	 * @return
	 */
	public OrderMain getByOrderSubNo(String subOrderNo) {
		String orderNo = this.orderNoService.getOrderNoBySubNo(subOrderNo);
		return this.findByOrderNo(orderNo);
	}

	/*
	 * @Override public Pager findByOrderMain(OrderMain orderMain, Pager pager)
	 * { Pager page = this.orderMainDao.findByOrderMain(orderMain, pager);
	 * List<OrderMain> orderList = page.getList(); for(OrderMain
	 * order:orderList){ this.loadOrderMainOthers(order); } return page; }
	 */

	/**
	 * 加载主订单的其他信息
	 * 
	 * @param orderMian
	 */
	public void loadOrderMainOthers(OrderMain orderMain) {
		if (null == orderMain)
			return;
		String orderMainNo = orderMain.getOrderNo();
		// 加载支付明细
		List<OrderPay> orderPays = orderPayService.getByOrderMainNo(orderMainNo);
		orderMain.setOrderPays(orderPays);
		// 加载支付类型
		List<OrderPayMode> orderPayModes = orderPayModeService.getList(OrderPayMode_.idOrder, orderMain.getId());
		orderMain.setOrderPayModes(orderPayModes);
		// 加载子订单
		List<OrderSub> orderSubs = orderSubService.getByOrderMainNo(orderMainNo);
		orderMain.setOrderSubs(orderSubs);
		// 加载促销
		// ..........

	}

	/**
	 * 加载退款主订单的其他信息
	 * 
	 * @param orderMian
	 */
	public void loadOrderMainOthersForReturn(OrderMain orderMain) {
		if (null == orderMain)
			return;
		String orderMainNo = orderMain.getOrderNo();
		// 加载支付明细
		List<OrderPay> orderPays = orderPayService.getByOrderMainNo(orderMainNo);
		orderMain.setOrderPays(orderPays);
		// 加载子订单
		List<OrderSub> orderSubs = orderSubService.getByOrderMainNoForReturn(orderMainNo);
		orderMain.setOrderSubs(orderSubs);
		// 加载促销
		// ..........

	}

	/**
	 * 通过左侧栏目树查询
	 * 
	 * @param columnId
	 * @param pager
	 * @return
	 */
	public Pager findByColumnId(int columnId, Pager pager) {
		String statusPay;// 主订单：支付状态
		String statusTotal;// 主订单：总状态
		OrderMain order = new OrderMain();

		List<String> statusPayOther = new ArrayList<String>();
		List<String> statusTotalOther = new ArrayList<String>();
		switch (columnId) {
		case OrderColumn.ORDER_REVERSE:
			order.setBillType(new Long(OrderColumn.ORDER_REVERSE));
			break;
		case OrderColumn.ORDER_ALL:
			break;
		case OrderColumn.ORDER_NEED_CONFIRMING: // 待审核
			statusTotal = OrderStatus.ORDER_CREATING.getCode();
			order.setStatusTotal(statusTotal);
			break;
		case OrderColumn.ORDER_NEED_PAY: // 未支付
			statusPay = OrderStatus.Order_PayStatus_Cash_Paying.getCode();
			order.setStatusPay(statusPay);
			statusPayOther.add(OrderStatus.Order_PayStatus_Paying.getCode());
			break;
		case OrderColumn.ORDER_STOCKOUT: // 缺货
			// ......
			break;
		case OrderColumn.ORDER_LACKED: // 待出库
			statusTotal = OrderStatus.ORDER_VALIDATED.getCode();
			order.setStatusTotal(statusTotal);
			break;
		case OrderColumn.ORDER_VALIDATED: // 待送货
			statusTotal = OrderStatus.ORDER_SENT.getCode();
			order.setStatusTotal(statusTotal);
			statusTotalOther.add(OrderStatus.ORDER_POD_SENT.getCode());
			break;
		case OrderColumn.ORDER_UN_FINISHED: // 未完成
			// .....
			break;
		case OrderColumn.ORDER_FINISHED: // 已完成
			statusTotal = OrderStatus.ORDER_ACCEPTED_PAID.getCode();
			order.setStatusTotal(statusTotal);
			break;
		case OrderColumn.ORDER_CALCELED: // 已取消
			statusTotal = OrderStatus.ORDER_PAY_CANCELLED.getCode();
			order.setStatusTotal(statusTotal);
			statusTotalOther.add(OrderStatus.ORDER_AUDIT_MANUAL_FAILED.getCode());
			break;
		case OrderColumn.ORDER_VIRTUAL: // 虚拟订单
			// ...............
			break;
		}
		Pager page = this.orderMainDao.findByOrderMain(order, pager, statusPayOther, statusTotalOther);
		List<OrderMain> orderList = page.getList();
		for (OrderMain ordertemp : orderList) {
			this.loadOrderMainOthers(ordertemp);
		}
		return page;
	}

	// public int queryWithSql(){
	// return orderMainDao.updateWithSql();
	// }

	@Override
	public int updateWithSql() {
		return 0;
	}

	@Override
	public int getOMListByTypeAndPeriod(SingularAttribute<OrderMain, String> type, String value, Long startTime) {
		List<OrderMain> omList = orderMainDao.getOMListByTypeAndPeriod(type, value, startTime);
		if (omList == null || omList.size() == 0) {
			return 0;
		}
		int counter = 0;
		for (OrderMain om : omList) {
			if (orderItemDao.isOrderNoContainLowGross(om.getId())) {
				counter++;
			}
		}
		return counter;
	}

	@Override
	public List<OrderMain> findNoPayOrder(Map<String, Object> params, int count) {

		return orderMainDao.findNoPayOrder(params, count);

	}

	@Override
	public List<OrderMain> findNoPayOrderResendMsg() {

		return orderMainDao.findNoPayOrderResendMsg();

	}

	public OrderItemDao getOrderItemDao() {
		return orderItemDao;
	}

	@Autowired
	public void setOrderItemDao(OrderItemDao orderItemDao) {
		this.orderItemDao = orderItemDao;
	}

	public OrderPayService getOrderPayService() {
		return orderPayService;
	}

	@Autowired
	public void setOrderPayService(OrderPayService orderPayService) {
		this.orderPayService = orderPayService;
	}

	public OrderSubService getOrderSubService() {
		return orderSubService;
	}

	@Autowired
	public void setOrderSubService(OrderSubService orderSubService) {
		this.orderSubService = orderSubService;
	}

	public OrderMainDao getOrderMainDao() {
		return orderMainDao;
	}

	@Override
	public List<OrderMain> findByOrderRelatedOrigin(String OriginOrderNo) {
		return orderMainDao.findByField(OrderMain_.orderRelatedOrigin, OriginOrderNo);
	}

	@Override
	public boolean isOrderMainDuplicated(String batchNum, String orderSource) {
		List<OrderMain> retList = orderMainDao.getOMListByBatchNumAndSource(batchNum, orderSource);
		return retList != null && !retList.isEmpty();
	}

	@Override
	public OrderMain getByOrderRelatedOrigin(String orderRelatedOrigin) {
		return orderMainDao.getByOrderRelatedOrigin(orderRelatedOrigin);
	}

	/**
	 * 通过sql语句查询订单
	 * 
	 * @param sql
	 * @return
	 */
	public List<Object> findOrderMainBySql(String sql) {
		return orderMainDao.findOrderMainBySql(sql);
	}

	@Override
	public List<OrderMain> findFinishedOrder(String date, String endDate) {
		try {
			return orderMainDao.findFinishedOrder(date, endDate);
		} catch (ParseException e) {
			return new ArrayList<OrderMain>();
		}
	}

	@Override
	public List<OrderMain> findFinishedOrderBeforeOnline() throws ParseException {
		return orderMainDao.findFinishedOrderBeforeOnline();
	}

	/**
	 * BBC更新订单信息（收货人信息，配送信息）
	 * 
	 * @param bbcUpdateOrderDTO
	 * @return
	 * @throws Exception
	 */
	public CommonOutputDTO bbcUpdateOrderInfo(BBCUpdateOrderDTO bbcUpdateOrderDTO) {
		CommonOutputDTO ret = new CommonOutputDTO();
		try {

			String orderSubNo = bbcUpdateOrderDTO.getOrderSubNo();
			ret.setOrderSubNo(orderSubNo);// 回传订单号
			OrderMain tempOm = this.findByOrderNo(orderNoService.getOrderNoBySubNo(orderSubNo));

			OrderSub os = orderSubService.getByField(OrderSub_.orderSubNo, orderSubNo);
			if (tempOm == null) {
				ret.setCode(CommonConstService.FAILED);
				ret.setMsg("销售订单不存在！");
				return ret;
			}
			if (!tempOm.getMerchantNo().equals(bbcUpdateOrderDTO.getMerchantNo())) {
				ret.setCode(CommonConstService.FAILED);
				ret.setMsg("您没有权限修改此订单！");
				return ret;
			}
			// 封装保存日志
			createUpdateLog(os, tempOm, bbcUpdateOrderDTO);

			if (StringUtils.isNotBlank(bbcUpdateOrderDTO.getRemark())) {
				tempOm.setClientServiceRemark(bbcUpdateOrderDTO.getRemark());// 客服备注
			}

			if (StringUtils.isNotBlank(bbcUpdateOrderDTO.getUserName())) {
				os.setUserName(bbcUpdateOrderDTO.getUserName());// 姓名
			}
			if (StringUtils.isNotBlank(bbcUpdateOrderDTO.getMobPhoneNum())) {
				os.setMobPhoneNum(bbcUpdateOrderDTO.getMobPhoneNum());// 手机号
			}
			if (StringUtils.isNotBlank(bbcUpdateOrderDTO.getPhoneNum())) {
				os.setPhoneNum(bbcUpdateOrderDTO.getPhoneNum());// 电话
			}
			if (StringUtils.isNotBlank(bbcUpdateOrderDTO.getAddressDetail())) {
				os.setAddressDetail(bbcUpdateOrderDTO.getAddressDetail());// 地址
			}
			if (StringUtils.isNotBlank(bbcUpdateOrderDTO.getPostCode())) {
				os.setPostCode(bbcUpdateOrderDTO.getPostCode());// 邮编
			}
			if (StringUtils.isNotBlank(bbcUpdateOrderDTO.getCombinedAddress())) {
				os.setAddressCode(bbcUpdateOrderDTO.getCombinedAddress());// 地址区域code
			}
			if (StringUtils.isNotBlank(bbcUpdateOrderDTO.getDeliveryMerchantName())) {
				os.setDeliveryMerchantName(bbcUpdateOrderDTO.getDeliveryMerchantName());// 物流商
			}
			if (StringUtils.isNotBlank(bbcUpdateOrderDTO.getLogisticsOutNo())) {
				os.setLogisticsOutNo(bbcUpdateOrderDTO.getLogisticsOutNo());// 运单号
			}

			orderSubService.update(os);

			ret.setCode(CommonConstService.OK);
			ret.setMsg("恭喜您，修改成功！");
			return ret;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		ret.setCode(CommonConstService.FAILED);
		ret.setMsg("系统异常，请稍后再试！");
		return ret;
	}

	/**
	 * 创建日志对象
	 * 
	 * @param orderMain
	 * @return
	 */
	private OrderOperateLog createOrderOperateLog(OrderMain orderMain, OrderSub os) {
		OrderOperateLog log = new OrderOperateLog();

		log.setIdOrder(orderMain.getId());
		log.setOrderNo(orderMain.getOrderNo());
		log.setOrderSubNo(os.getOrderSubNo());
		log.setDateCreated(new Date());

		return log;
	}

	/**
	 * 保存BBC商家修改订单信息日志记录
	 * 
	 * @param tmpOrderSub
	 * @param orderSub
	 * @param orderMain
	 * @param tempOrderMain
	 */
	private void createUpdateLog(OrderSub tmpOrderSub, OrderMain orderMain, Object object) {

		OrderOperateLog log = this.createOrderOperateLog(orderMain, tmpOrderSub);
		StringBuffer oldData = new StringBuffer();
		StringBuffer newData = new StringBuffer();
		StringBuffer content = new StringBuffer();

		if (object instanceof BBCUpdateOrderDTO) {
			BBCUpdateOrderDTO bbcUpdateOrderDTO = (BBCUpdateOrderDTO) object;
			if (!CommonString.equals(tmpOrderSub.getUserName(), bbcUpdateOrderDTO.getUserName())) {
				content.append("收货人姓名：</br>");
				oldData.append(StringUtils.trimToEmpty(tmpOrderSub.getUserName())).append("</br>");
				newData.append(StringUtils.trimToEmpty(bbcUpdateOrderDTO.getUserName())).append("</br>");
			}

			if (!CommonString.equals(tmpOrderSub.getPhoneNum(), bbcUpdateOrderDTO.getPhoneNum())) {
				content.append("收货人手机：</br>");
				oldData.append(StringUtils.trimToEmpty(tmpOrderSub.getPhoneNum())).append("</br>");
				newData.append(StringUtils.trimToEmpty(bbcUpdateOrderDTO.getPhoneNum())).append("</br>");
			}

			if (!CommonString.equals(tmpOrderSub.getMobPhoneNum(), bbcUpdateOrderDTO.getMobPhoneNum())) {
				content.append("收货人电话：</br>");
				oldData.append(StringUtils.trimToEmpty(tmpOrderSub.getMobPhoneNum())).append("</br>");
				newData.append(StringUtils.trimToEmpty(bbcUpdateOrderDTO.getMobPhoneNum())).append("</br>");
			}

			if (!CommonString.equals(tmpOrderSub.getAddressDetail(), bbcUpdateOrderDTO.getAddressDetail())) {
				content.append("收货人地址:</br>");
				oldData.append(StringUtils.trimToEmpty(tmpOrderSub.getAddressDetail())).append("</br>");
				newData.append(StringUtils.trimToEmpty(bbcUpdateOrderDTO.getAddressDetail())).append("</br>");
			}

			if (!CommonString.equals(tmpOrderSub.getPostCode(), bbcUpdateOrderDTO.getPostCode())) {
				content.append("收货人邮编:</br>");
				oldData.append(StringUtils.trimToEmpty(tmpOrderSub.getPostCode())).append("</br>");
				newData.append(StringUtils.trimToEmpty(bbcUpdateOrderDTO.getPostCode())).append("</br>");
			}
			if (!CommonString.equals(tmpOrderSub.getAddressCode(), bbcUpdateOrderDTO.getCombinedAddress())) {
				content.append("区域code:</br>");
				oldData.append(StringUtils.trimToEmpty(tmpOrderSub.getAddressCode())).append("</br>");
				newData.append(StringUtils.trimToEmpty(bbcUpdateOrderDTO.getCombinedAddress())).append("</br>");
			}

			if (!CommonString.equals(tmpOrderSub.getDeliveryMerchantName(), bbcUpdateOrderDTO.getDeliveryMerchantName())) {
				content.append("物流商:</br>");
				oldData.append(StringUtils.trimToEmpty(tmpOrderSub.getDeliveryMerchantName())).append("</br>");
				newData.append(StringUtils.trimToEmpty(bbcUpdateOrderDTO.getDeliveryMerchantName())).append("</br>");
			}
			if (!CommonString.equals(tmpOrderSub.getLogisticsOutNo(), bbcUpdateOrderDTO.getLogisticsOutNo())) {
				content.append("运单号:</br>");
				oldData.append(StringUtils.trimToEmpty(tmpOrderSub.getLogisticsOutNo())).append("</br>");
				newData.append(StringUtils.trimToEmpty(bbcUpdateOrderDTO.getLogisticsOutNo())).append("</br>");
			}
			if (!CommonString.equals(orderMain.getClientServiceRemark(), bbcUpdateOrderDTO.getRemark())) {
				content.append("客服备注:</br>");
				oldData.append(StringUtils.trimToEmpty(orderMain.getClientServiceRemark())).append("</br>");
				newData.append(StringUtils.trimToEmpty(bbcUpdateOrderDTO.getRemark())).append("</br>");
			}
			log.setOperator(bbcUpdateOrderDTO.getOperateName());
			log.setIP(bbcUpdateOrderDTO.getIp());
		} else if (object instanceof BBCBatchRemarkDTO) {
			BBCBatchRemarkDTO bbcBatchRemarkDTO = (BBCBatchRemarkDTO) object;
			if (!CommonString.equals(orderMain.getClientServiceRemark(), bbcBatchRemarkDTO.getRemark())) {
				content.append("批量修改客服备注:</br>");
				oldData.append(StringUtils.trimToEmpty(orderMain.getClientServiceRemark())).append("</br>");
				newData.append(StringUtils.trimToEmpty(bbcBatchRemarkDTO.getRemark())).append("</br>");
			}
			log.setOperator(bbcBatchRemarkDTO.getOperateName());
			log.setIP(bbcBatchRemarkDTO.getIp());
		} else if (object instanceof BBCUpdateOrderStatusDTO) {
			content.append("bbc封闭订单修改订单状态:</br>");
			oldData.append(StringUtils.trimToEmpty(orderMain.getStatusPay())).append("</br>");
			oldData.append(StringUtils.trimToEmpty(tmpOrderSub.getLogisticsStatus())).append("</br>");
			newData.append(OrderStatus.ORDER_ACCEPTED_PAID.getCode()).append("</br>");
			newData.append(OrderStatus.Order_LogisticsStatus_SignFinish.getCode()).append("</br>");
		}

		log.setOldData(oldData.toString());
		log.setContent(content.toString());
		if (object instanceof BBCUpdateOrderStatusDTO) {
			log.setReason("bbc封闭订单修改订单状态 ");
		} else {
			log.setReason("保存收货人信息/客服备注/配送信息 ");
		}
		log.setNewData(newData.toString());

		// 保存日志
		orderOperateLogService.save(log);
	}

	/**
	 * 批量修改客服备注
	 */
	@Override
	public CommonOutputDTO bbcBatchUpdateRemarkInfo(BBCBatchRemarkDTO bbcBatchRemarkDTO) {
		// TODO Auto-generated method stub
		CommonOutputDTO ret = new CommonOutputDTO();
		String orderSubNo = bbcBatchRemarkDTO.getOrderSubNo();
		ret.setOrderSubNo(orderSubNo);// 回传订单号
		OrderSub os = orderSubService.getByField(OrderSub_.orderSubNo, orderSubNo);
		OrderMain om = this.findByOrderNo(orderNoService.getOrderNoBySubNo(orderSubNo));
		if (om == null) {
			ret.setCode(CommonConstService.FAILED);
			ret.setMsg("销售订单不存在！");
			return ret;
		}
		try {

			if (!om.getMerchantNo().equals(bbcBatchRemarkDTO.getMerchantNo())) {
				ret.setCode(CommonConstService.FAILED);
				ret.setMsg("您没有权限修改此订单！");
				return ret;
			}
			// 封装保存日志
			createUpdateLog(os, om, bbcBatchRemarkDTO);

			if (StringUtils.isNotBlank(bbcBatchRemarkDTO.getRemark())) {
				om.setClientServiceRemark(bbcBatchRemarkDTO.getRemark());// 客服备注
			}
			this.update(om);
			orderSubService.update(os);
			ret.setCode(CommonConstService.OK);
			ret.setMsg("恭喜您，修改成功！");
			return ret;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		ret.setCode(CommonConstService.FAILED);
		ret.setMsg("系统异常，请稍后再试！");
		return ret;
	}

	/**
	 * BBC修改订单状态与物流状态，修改后状态如下 物流状态：签收完成 处理状态：已完成
	 * 
	 * @param bbcUpdateOrderStatusDTO
	 * @return
	 */
	@Override
	public CommonOutputDTO bbcUpdateOrderStatus(BBCUpdateOrderStatusDTO bbcUpdateOrderStatusDTO) {
		// TODO Auto-generated method stub
		CommonOutputDTO ret = new CommonOutputDTO();
		String orderSubNo = bbcUpdateOrderStatusDTO.getOrderSubNo();
		ret.setOrderSubNo(orderSubNo);// 回传订单号
		try {

			OrderSub os = orderSubService.getByField(OrderSub_.orderSubNo, orderSubNo);
			OrderMain om = this.findByOrderNo(orderNoService.getOrderNoBySubNo(orderSubNo));
			if (om == null) {
				ret.setCode(CommonConstService.FAILED);
				ret.setMsg("销售订单不存在！");
				return ret;
			}
			// 判断是否有权限
			if (!om.getMerchantNo().equals(bbcUpdateOrderStatusDTO.getMerchantNo())) {
				ret.setCode(CommonConstService.FAILED);
				ret.setMsg("您没有权限修改此订单！");
				return ret;
			}
			/**
			 * 判断订单状态是否是在线支付（且已经完成支付） 处理状态为 订单已发货；否则不允许修改订单状态
			 */
			String statusTotal = OrderStatus.ORDER_SENT.getCode();// 总状态：
																	// 销售订单已发货("0170","在线支付订单已发货"),
			String statuspay = OrderStatus.Order_PayStatus_Success.getCode();// 在线支付且已完成支付
			if (statuspay.equals(om.getStatusPay()) && statusTotal.equals(om.getStatusTotal())) {
				// 封装创建记录日志
				createUpdateLog(os, om, bbcUpdateOrderStatusDTO);
				// 更改出来状态与物流状态
				om.setStatusTotal(OrderStatus.ORDER_ACCEPTED_PAID.getCode());// 已完成(0180)
				os.setLogisticsStatus(OrderStatus.Order_LogisticsStatus_SignFinish.getCode());// 物流状态完成签收
				this.save(om);
				orderSubService.save(os);
			} else {
				ret.setCode(CommonConstService.FAILED);
				ret.setMsg("订单状态不正确，请检查订单！");
				return ret;
			}
			ret.setCode(CommonConstService.OK);
			ret.setMsg("恭喜您，修改成功！");
			return ret;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		ret.setCode(CommonConstService.FAILED);
		ret.setMsg("系统异常，请稍后再试！");
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ibm.oms.service.OrderMainService#findOrdersForAutomaticAudit(com.ibm.
	 * sc.bean.Pager)
	 */
	@Override
	public List<OrderMain> findOrdersForAutomaticAudit(int count) {
		return orderMainDao.findbyStatusTotal(OrderStatus.ORDER_AUDITING.getCode(), count);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ibm.oms.service.OrderMainService#findNoConfirmReceiptOrder(java.util.
	 * Map, int)
	 */
	@Override
	public List<OrderMain> findOrderByStatusAndDate(Map<String, Object> params, int count) {
		// TODO Auto-generated method stub

		return (List<OrderMain>) orderMainDao.findOrderByStatusAndDate(params, count);
	}

	// 增加订单列表和订单查询
	/*
	 * 订单详情查询（对外接口）
	 * 
	 * @see
	 * com.ibm.oms.service.OrderMainService#findOrderDetails(java.lang.String)
	 */
	@Override
	public OrderMainClientDTO findOrderDetails(String orderNo) throws Exception {
		OrderMain om = this.findByOrderNo(orderNo);
		if (om == null) {

			return null;
		}

		String ordersource = om.getOrderSource();
		String source = "1";
		if (ordersource != null) {
			if (ordersource.equals("LS")) {
				source ="1";
			}
			if (ordersource.equals("BS")) {
				source = "4";
			}
			if (ordersource.equals("WX")) {
				source = "3";
			}
			if (ordersource.equals("DG")) {
				source = "2";
			}

		}	
		
		OrderSub os = orderSubService.getByOrderMainNo(orderNo).get(0);
		int productNum = 0;
		OrderMainClientDTO omc = new OrderMainClientDTO();
		omc.setMerchantNo(om.getMerchantNo());
		omc.setOrderNo(orderNo);
		omc.setOrderMainNo(om.getOrderNo());
		omc.setStatusTotal(om.getStatusTotal());
		omc.setOrderType(om.getOrderType());
		omc.setCustomerName(om.getCustomerName());
		omc.setSalesclerkNo(om.getSalesclerkNo());
		omc.setOrderTime(om.getOrderTime());
		omc.setTotalPayAmount(om.getTotalPayAmount());
		omc.setBonus(om.getSalesclerkPerform());
		omc.setTotalPointAmount(om.getTotalPointAmount());
		omc.setTransportFee(om.getTransportFee());
		omc.setTotalProductPrice(om.getTotalProductPrice());
		omc.setTotalPromo(om.getTotalPromo());
		omc.setMemberNo(om.getMemberNo());
		omc.setOrderSource(om.getOrderSource());
		omc.setDiscountTotal(om.getDiscountTotal());
		omc.setTotalGivePoints(om.getTotalGivePoints());
		omc.setNeedInvoince(om.getNeedInvoice());
		omc.setOrderAttribute(om.getBillType());
		omc.setShopNo(om.getSaleStoreCode());
		omc.setMerchantNo(om.getMerchantNo());
		BigDecimal totalProductPrice = new BigDecimal(0);
		totalProductPrice = om.getTotalProductPrice();
		BigDecimal transportFee = new BigDecimal(0);
		transportFee = om.getTransportFee();
		if (totalProductPrice != null) {
			omc.setTotalOrderAmount(totalProductPrice.add(transportFee));
		} else {
			omc.setTotalOrderAmount(new BigDecimal(0));
		}
		if(om.getOrderPays().size()>0){
		omc.setPayTime(om.getOrderPays().get(0).getPayTime());
		}
		omc.setTotalGivePoints(om.getTotalGivePoints());
		
		omc.setOrderSource(ordersource);
		List<OrderItemClientDTO> oics = new ArrayList<OrderItemClientDTO>();

		Integer flag = om.getIsSplit();
		if (flag == null) {
			flag = 0;
		}
		if (flag == 1) {

			List<OrderMain> oms = this.findOrderSplitlist(om.getOrderNo(), om.getOrderNoP());
			for (OrderMain orderMain2 : oms) {

				List<OrderItem> orderItems2 = orderItemService.getByOrdeNo(orderMain2.getOrderNo());
				for (OrderItem orderItem : orderItems2) {
					OrderItemClientDTO oic = new OrderItemClientDTO();
					BeanUtils.copyProperties(oic, orderItem);
					productNum = (int) (orderItem.getSaleNum() + productNum);
					oic.setUnitPrice(orderItem.getUnitPrice().toString());
					oic.setUnitDeductedPrice(orderItem.getUnitDeductedPrice().toString());
					oic.setCouponAmount(orderItem.getCouponAmount());
					oic.setUnitDiscount(orderItem.getUnitDiscount());
					oic.setPayAmount(orderItem.getPayAmount().toString());
					String skuNo = orderItem.getSkuNo();
					SkuOrderBean sob = skuClientService.findSkuBySkuCode(skuNo, source);
					oic.setSize(sob.getSizeName());
					oic.setColor(sob.getColorName());
					oic.setProductImgs(sob.getSkuImages());
					oic.setSales(orderItemService.findSalesByCommodityCode(orderItem.getCommodityCode()));
					oics.add(oic);
				}
			}

		}else{

			List<OrderItem> orderItems = orderItemService.getByOrdeNo(om.getOrderNo());
		for (OrderItem orderItem : orderItems) {
			OrderItemClientDTO oic = new OrderItemClientDTO();
			BeanUtils.copyProperties(oic, orderItem);
			oic.setUnitPrice(orderItem.getUnitPrice().toString());
			oic.setUnitDeductedPrice(orderItem.getUnitDeductedPrice().toString());
			oic.setCouponAmount(orderItem.getCouponAmount());
			oic.setUnitDiscount(orderItem.getUnitDiscount());
			oic.setPayAmount(orderItem.getPayAmount().toString());
			productNum = (int) (orderItem.getSaleNum() + productNum);
			String skuNo = orderItem.getSkuNo();
			SkuOrderBean sob = skuClientService.findSkuBySkuCode(skuNo, source);
			oic.setSize(sob.getSizeName());
			oic.setColor(sob.getColorName());
			oic.setProductImgs(sob.getSkuImages());


			oic.setSales(orderItemService.findSalesByCommodityCode(orderItem.getCommodityCode()));
			oics.add(oic);
		}
		}

		List<OrderInvoiceClientDTO> oivcs = new ArrayList<OrderInvoiceClientDTO>();
		List<OrderInvoice> orderInvoices =om.getOrderInvoices();
		for (OrderInvoice orderInvoice : orderInvoices) {
          OrderInvoiceClientDTO oivc = new OrderInvoiceClientDTO();
			BeanUtils.copyProperties(oivc, orderInvoice);
			oivcs.add(oivc);
		}

		List<OrderPayClientDTO> opcs = new ArrayList<OrderPayClientDTO>();
		List<OrderPay> orderPays =om.getOrderPays();
		for (OrderPay orderPay : orderPays) {
			
			OrderPayClientDTO opc = new OrderPayClientDTO();
				BeanUtils.copyProperties(opc, orderPay);
				opcs.add(opc);
			}

		List<OrderPromotionClientDTO> opcms = new ArrayList<OrderPromotionClientDTO>();
		List<OrderPromotion> orderPromotions =om.getOrderPromotions();
		for (OrderPromotion orderPromotion : orderPromotions) {
			OrderPromotionClientDTO opcm = new OrderPromotionClientDTO();
				BeanUtils.copyProperties(opcm, orderPromotion);
				opcms.add(opcm);
			}
		OrderSubClientDTO osc = new OrderSubClientDTO();
		BeanUtils.copyProperties(osc, os);
		omc.setOrderInvoiceClientDTOs(oivcs);
		omc.setOrderPayDTOs(opcs);
		omc.setOrderPromotionClientDTOs(opcms);
		omc.setProductNum(productNum);
		omc.setOrderItemDTOS(oics);
		omc.setOrderSubDTO(osc);

		return omc;
	}

	/*
	 * 获取订单列表（对外接口）
	 * 
	 * @see
	 * com.ibm.oms.service.OrderMainService#findOrderList(com.ibm.oms.client.dto
	 * .order.OrderQueryClientDTO, com.ibm.sc.bean.Pager)
	 */
	@Override
	public Pager findOrderList(OrderQueryClientDTO orderQueryDTO, Pager pager) throws Exception {
		OrderMain om = new OrderMain();
		String status = orderQueryDTO.getOrderStatus();
		om.setSalesclerkNo(orderQueryDTO.getSalesclerkNo());
		om.setMemberNo(orderQueryDTO.getMemberNo());
		om.setOrderTimeFrom(orderQueryDTO.getOrderTimeFrom());
		om.setOrderTimeTo(orderQueryDTO.getOrderTimeTO());
		om.setMerchantNo(orderQueryDTO.getShopNo());
		om.setIsCommission(orderQueryDTO.getIsBonus());
		om.setStatusTotal(status);
		om.setIsCommission(orderQueryDTO.getIsBonus());
		om.setOrderSource(orderQueryDTO.getOrderSource());
		om.setSaleStoreCode(orderQueryDTO.getShopNo());
		pager = orderMainDao.findOrderList(om, pager);
		List<OrderMainClientDTO> omcs = new ArrayList<OrderMainClientDTO>();
		List<OrderMain> mains = pager.getList();
		if (mains.size() != 0) {
			for (OrderMain orderMain : mains) {
				String ordersource = om.getOrderSource();
				List<OrderItem> orderItems = orderItemService.getByOrdeNo(om.getOrderNo());
				List<OrderItemClientDTO> oics = new ArrayList<OrderItemClientDTO>();
				int productNum = 0;
				OrderMainClientDTO omc = new OrderMainClientDTO();
				omc.setMerchantNo(orderMain.getMerchantNo());
				omc.setOrderNo(orderMain.getOrderNo());
				omc.setOrderMainNo(orderMain.getOrderNo());
				omc.setStatusTotal(orderMain.getStatusTotal());
				omc.setOrderType(orderMain.getOrderType());
				omc.setCustomerName(orderMain.getCustomerName());
				omc.setSalesclerkNo(orderMain.getSalesclerkNo());
				omc.setOrderTime(orderMain.getOrderTime());
				omc.setTotalPayAmount(orderMain.getTotalPayAmount());
				omc.setBonus(orderMain.getSalesclerkPerform());
				omc.setTotalPointAmount(orderMain.getTotalPointAmount());
				omc.setTransportFee(orderMain.getTransportFee());
				omc.setTotalProductPrice(orderMain.getTotalProductPrice());
				omc.setTotalPromo(orderMain.getTotalPromo());
				omc.setMemberNo(orderMain.getMemberNo());
				omc.setNeedInvoince(orderMain.getNeedInvoice());
				omc.setOrderAttribute(orderMain.getBillType());
				omc.setOrderSource(orderMain.getOrderSource());
				omc.setSetDate(orderMain.getBalanceDate());
				omc.setDiscountTotal(orderMain.getDiscountTotal());
                
				BigDecimal totalProductPrice = new BigDecimal(0);
				totalProductPrice = orderMain.getTotalProductPrice();
				BigDecimal transportFee = new BigDecimal(0);
				transportFee = orderMain.getTransportFee();
				if (totalProductPrice != null) {
					omc.setTotalOrderAmount(totalProductPrice.add(transportFee));
				} else {
					omc.setTotalOrderAmount(new BigDecimal(0));
				}
				List<OrderPay> ops = orderMain.getOrderPays();
				if (ops.size() > 0) {
					omc.setPayTime(ops.get(0).getPayTime());
				}

				Integer flag = orderMain.getIsSplit();
				if (flag == null) {
					flag = 0;
				}
				if (flag == 1) {

					List<OrderMain> oms = this.findOrderSplitlist(orderMain.getOrderNo(), orderMain.getOrderNoP());
					for (OrderMain orderMain2 : oms) {

						List<OrderItem> orderItems2 = orderItemService.getByOrdeNo(orderMain2.getOrderNo());
						for (OrderItem orderItem : orderItems2) {
							OrderItemClientDTO oic = new OrderItemClientDTO();
							BeanUtils.copyProperties(oic, orderItem);
							productNum = (int) (orderItem.getSaleNum() + productNum);
							oic.setUnitPrice(orderItem.getUnitPrice().toString());
							oic.setUnitDeductedPrice(orderItem.getUnitDeductedPrice().toString());
							oic.setCouponAmount(orderItem.getCouponAmount());
							oic.setUnitDiscount(orderItem.getUnitDiscount());
							oic.setPayAmount(orderItem.getPayAmount().toString());
							String skuNo = orderItem.getSkuNo();
							SkuOrderBean sob = skuClientService.findSkuBySkuCode(skuNo, orderMain2.getRegionCode());
							oic.setSize(sob.getSizeName());
							oic.setColor(sob.getColorName());
							oic.setProductImgs(sob.getSkuImages());
							oic.setSales(orderItemService.findSalesByCommodityCode(orderItem.getCommodityCode()));
							oics.add(oic);
						}
					}

				} else {
					for (OrderItem orderItem : orderItems) {
						OrderItemClientDTO oic = new OrderItemClientDTO();
						BeanUtils.copyProperties(oic, orderItem);
						productNum = (int) (orderItem.getSaleNum() + productNum);
						oic.setUnitPrice(orderItem.getUnitPrice().toString());
						oic.setUnitDeductedPrice(orderItem.getUnitDeductedPrice().toString());
						oic.setCouponAmount(orderItem.getCouponAmount());
						oic.setUnitDiscount(orderItem.getUnitDiscount());
						oic.setPayAmount(orderItem.getPayAmount().toString());
						String skuNo = orderItem.getSkuNo();
						SkuOrderBean sob = skuClientService.findSkuBySkuCode(skuNo, orderMain.getRegionCode());
						oic.setSize(sob.getSizeName());
						oic.setColor(sob.getColorName());
						oic.setProductImgs(sob.getSkuImages());
						oic.setSales(orderItemService.findSalesByCommodityCode(orderItem.getCommodityCode()));
						oics.add(oic);
					}
				}
				omc.setProductNum(productNum);
				omc.setOrderItemDTOS(oics);
				omcs.add(omc);
			}
			pager.setList(omcs);
			return pager;
		}
		return null;

	}

	/*
	 * 将订单挂起
	 * 
	 * @see
	 * com.ibm.oms.service.OrderMainService#OrderSuspension(java.lang.String,
	 * java.lang.Integer)
	 */

	@Override
	public String updateOrderSuspension(String orderNo) {
		StringBuffer sb = new StringBuffer();
		OrderMain om = orderMainDao.findByOrderNo(orderNo);
		String status = om.getStatusTotal();
		String orderSubNo = orderNoService.getDefaultOrderSubNoByOrderNo(om.getOrderNo());
		OrderSub os = orderSubService.get(OrderSub_.orderSubNo, orderSubNo);
		String loginUserName = "";// 当前登陆者
		User user = userService.getLoginUser();
		if (null != user) {
			loginUserName = user.getUserName();
			if (StringUtils.isEmpty(loginUserName)) {
				loginUserName = String.valueOf(user.getId());
			}
		}
		if (status.equals("0110") || status.equals("0120") || status.equals("0121") || status.equals("0130") || status.equals("0131")
				|| status.equals("0140") || status.equals("0141") || status.equals("0142")) {
			om.setStatusTotal("0143");
			orderMainDao.update(om);
			orderStatusLogService.writeStatusLog(om, os, status, "0143", loginUserName, new Date(), "挂起操作");

			sb.append("订单:" + orderNo + "已挂起");
		} else {

			sb.append("订单:" + orderNo + "的状态无法挂起");

		}
		return sb.toString();

	}

	@Override
	public Long getMemberIdBySubOrderNo(String subOrderNo) {
		return orderMainDao.getMemberIdBySubOrderNo(subOrderNo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ibm.oms.service.OrderMainService#updateOrderRelieveSuspensionon(java.
	 * lang.String)
	 */
	@Override
	public String updateOrderRelieveSuspensionon(String orderNo) {
		StringBuffer sb = new StringBuffer();
		OrderMain om = orderMainDao.findByOrderNo(orderNo);

		String orderSubNo = orderNoService.getDefaultOrderSubNoByOrderNo(om.getOrderNo());
		OrderSub os = orderSubService.get(OrderSub_.orderSubNo, orderSubNo);
		OrderStatusLog osl = orderStatusLogDao.findSuspensionByOrderNo(orderNo);

		String status = osl.getPreviousStatus();
		String loginUserName = "";// 当前登陆者
		User user = userService.getLoginUser();
		if (null != user) {
			loginUserName = user.getUserName();
			if (StringUtils.isEmpty(loginUserName)) {
				loginUserName = String.valueOf(user.getId());
			}
		}
		{
			om.setStatusTotal(status);
			orderMainDao.update(om);
			orderStatusLogService.writeStatusLog(om, os, "0143", status, loginUserName, new Date(), "解挂操作");

			sb.append("订单:" + orderNo + "已解挂");
		}
		return sb.toString();

	}

	@Override
	public BigDecimal getOrderAmountByOrderId(Long orderId) {

		return orderMainDao.getOrderAmountByOrderId(orderId);
	}

	@Override
	public void automaticAuditOrder() {
		OrderAuditConfig orderAuditConfig = orderAuditConfigService.getAll().get(0);
		// 初始化不免审区域
		List<OrderAuditRegionConfig> regionConfigs = orderAuditRegionConfigService.getAll();
		// 初始化不免审sku
		List<String> interceptSkus = null;
		if (StringUtils.isNotBlank(orderAuditConfig.getInterceptSkus())) {
			interceptSkus = Arrays.asList(orderAuditConfig.getInterceptSkus().split(","));
		}

		// 将店铺维度审核配置转为映射key:店铺code value:店铺自动审核配置
		List<OrderAuditMerchantConfig> merchantConfigs = orderAuditMerchantConfigService.getAll();
		Map<String, OrderAuditMerchantConfig> merchantConfigMapping = new HashMap<String, OrderAuditMerchantConfig>();
		for (OrderAuditMerchantConfig merchantConfig : merchantConfigs) {
			merchantConfigMapping.put(merchantConfig.getMerchantCode(), merchantConfig);
		}

		if (orderAuditConfig.getEnabled()) {
			OrderMain orderMain = new OrderMain();
			List<OrderMain> orderMains = this.findOrdersForAutomaticAudit(orderAuditConfig.getAuditCount());

			for (OrderMain order : orderMains) {
				OrderAuditMerchantConfig merchantConfig = merchantConfigMapping.get(order.getMerchantNo());
				if (null == merchantConfig) {
					logger.info("订单号:{} 店铺code:{}审核配置不存在", order.getOrderNo(), order.getMerchantNo());
					continue;
				}

				// 延时免审开关
				if (null != orderAuditConfig.getMinutesDelay() && 0 != orderAuditConfig.getMinutesDelay()) {
					if (merchantConfig.getIsDelay()) {
						if (this.hasDelay(order, orderAuditConfig.getMinutesDelay())) {
						} else {
							logger.info("订单号:{} 未到自动审核时间", order.getOrderNo());
							continue;
						}
					} else {
					}
				} else {
				}

				// 收货地址是否属于不免审区域
				if (null != regionConfigs && this.inInterceptRegions(order, regionConfigs)) {
					logger.info("订单:{} 属于不免审区域", order.getOrderNo());
					updateOrderStausForAotoAudit(order.getOrderNo(), false);
					continue;
				} else {
				}

				// 拆分单免审
				if (orderAuditConfig.getIsApprovedOrderSplit() && merchantConfig.getIsApprovedOrderSplit()) {
				} else {
					if (orderMain.getIsSplit() == 1) {
						logger.info("订单:{} 属于不免审拆分单", order.getOrderNo());
						updateOrderStausForAotoAudit(order.getOrderNo(), false);
						continue;
					} else {
					}
				}

				// 促销单免审
				if (orderAuditConfig.getIsApprovedOrderPromotion()) {
				} else {
					if (!orderMain.getOrderPromotions().isEmpty()) {
						logger.info("订单:{} 属于不免审促销单", order.getOrderNo());
						updateOrderStausForAotoAudit(order.getOrderNo(), false);
						continue;
					}
				}

				// 免审金额区间判断，店铺维度优先，店铺维度配置为空时，以系统配置优先
				if (null != merchantConfig.getMaxAmount() && 1 == merchantConfig.getMaxAmount().compareTo(BigDecimal.valueOf(0))) {
					if (-1 == orderMain.getTotalPayAmount().compareTo(merchantConfig.getMinAmount())
							|| 1 == orderMain.getTotalPayAmount().compareTo(merchantConfig.getMaxAmount())) {
						logger.info("订单:{} 属于不免审金额", order.getOrderNo());
						updateOrderStausForAotoAudit(order.getOrderNo(), false);
						continue;
					} else {
					}
				} else {
					if (-1 == orderMain.getTotalPayAmount().compareTo(orderAuditConfig.getMinAmount())
							|| 1 == orderMain.getTotalPayAmount().compareTo(orderAuditConfig.getMaxAmount())) {
						logger.info("订单:{} 属于不免审金额", order.getOrderNo());
						updateOrderStausForAotoAudit(order.getOrderNo(), false);
						continue;
					} else {
					}
				}

				// 忽略客户留言
				if (orderAuditConfig.getIsIgnoredClientRemark() && merchantConfig.getIsIgnoredClientRemark()) {
				} else {
					if (StringUtils.isNotBlank(orderMain.getClientRemark())) {
						logger.info("订单:{} 属于不免审客户留言", order.getOrderNo());
						updateOrderStausForAotoAudit(order.getOrderNo(), false);
						continue;
					} else {
					}
				}

				// 忽略备注
				if (orderAuditConfig.getIsIgnoredClientServiceRemark() && merchantConfig.getIsIgnoredClientServiceRemark()) {
				} else {
					if (StringUtils.isNotBlank(orderMain.getClientServiceRemark())) {
						logger.info("订单:{} 属于不免审备注", order.getOrderNo());
						updateOrderStausForAotoAudit(order.getOrderNo(), false);
						continue;
					} else {
					}
				}

				// 货到付款免审
				if (orderAuditConfig.getIsApprovedPayOnArrival()) {
				} else {
					if (orderMain.getIfPayOnArrival() == 1) {
						logger.info("订单:{} 属于不免审货到付款", order.getOrderNo());
						updateOrderStausForAotoAudit(order.getOrderNo(), false);
						continue;
					} else {
					}
				}

				// 换货免审
				if (orderAuditConfig.getIsApprovedOrderBarter()) {
				} else {
					if (orderMain.getIsBarter() == 1) {
						logger.info("订单:{} 属于不免审换货", order.getOrderNo());
						updateOrderStausForAotoAudit(order.getOrderNo(), false);
						continue;
					} else {
					}
				}

				// 合并单免审
				if (orderAuditConfig.getIsApprovedOrderMerge()) {
				} else {
					if (orderMain.getIsMerge() == 1) {
						logger.info("订单:{} 属于不免审合并单", order.getOrderNo());
						updateOrderStausForAotoAudit(order.getOrderNo(), false);
						continue;
					} else {
					}
				}

				// 单品免审
				if (orderAuditConfig.getIsApprovedSingleProduct()) {
				} else {
					if (!this.isSingleProduct(orderMain)) {
						logger.info("订单:{} 属于不免审单品", order.getOrderNo());
						updateOrderStausForAotoAudit(order.getOrderNo(), false);
						continue;
					}
				}

				// 指定sku不免审
				if (null == interceptSkus) {
				} else {
					if (this.hasInterceptSku(orderMain, interceptSkus)) {
						logger.info("订单:{} 属于不免审sku", order.getOrderNo());
						updateOrderStausForAotoAudit(order.getOrderNo(), false);
						continue;
					}
				}
				// 审核通过
				updateOrderStausForAotoAudit(order.getOrderNo(), true);
			}

		}
	}

	/**
	 * 自动审核更新订单状态 Description:
	 * 
	 * @param orderNo
	 * @param result
	 *            审核结果 true/false
	 */
	private void updateOrderStausForAotoAudit(String orderNo, boolean result) {
		String defaultSubNo = orderNoService.getDefaultOrderSubNoByOrderNo(orderNo);
		if (result) {
			orderStatusService.saveProcess(defaultSubNo, OrderStatusAction.S014050, null, null, null);
		} else {
			orderStatusService.saveProcess(defaultSubNo, OrderStatusAction.S014041, null, null, null);
		}
	}

	/**
	 * 满足延时审单条件 Description:
	 * 
	 * @param order
	 * @param delayMinutes
	 * @return
	 */
	private boolean hasDelay(OrderMain order, int delayMinutes) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, -delayMinutes);
		if (calendar.getTime().getTime() > order.getDateCreated().getTime()) {
			return true;
		}
		return false;
	}

	/**
	 * 是否在不免审区域 Description:
	 * 
	 * @param order
	 * @param interceptRegions
	 * @return
	 */
	private boolean inInterceptRegions(OrderMain order, List<OrderAuditRegionConfig> regionConfigs) {
		for (OrderAuditRegionConfig regionConfig : regionConfigs) {
			for (OrderSub orderSub : order.getOrderSubs()) {
				String regionPrefix = regionConfig.getCode().substring(0, regionConfig.getLevel() * 2 - 1);
				if (orderSub.getAddressCode().indexOf(regionPrefix) > 0) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断是否是单品 Description:
	 * 
	 * @param order
	 * @return
	 */
	private boolean isSingleProduct(OrderMain order) {
		int sum = 0;
		for (OrderItem orderItem : order.getOrderItems()) {
			sum += orderItem.getSaleNum();
			if (sum > 1) {
				return true;
			}
		}
		return false;
	}

	private boolean hasInterceptSku(OrderMain order, List<String> skus) {
		for (OrderItem orderItem : order.getOrderItems()) {
			if (skus.contains(orderItem.getSkuNo())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public CommonOutputDTO updateSaleAfterOrderStatus(String orderNo, String status) {
		CommonOutputDTO ret = new CommonOutputDTO();
		ret.setOrderNo(orderNo);// 回传订单号
		try {
			OrderMain om = this.findByOrderNo(orderNo);
			if (om == null) {
				ret.setCode("1");
				ret.setMsg("订单:" + orderNo + "不存在！");
				return ret;
			}
			// 更改状态
			om.setStatusTotal(status);
			this.save(om);
			ret.setCode("0");
			ret.setMsg("订单状态修改成功！");
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		ret.setCode("1");
		ret.setMsg("系统异常，请稍后再试！");
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.oms.service.OrderMainService#getTodayAllOrderGuiderId()
	 */
	@Override
	public List<?> getTodayAllOrderGuiderId() {
		return orderMainDao.getTodayAllOrderGuiderId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ibm.oms.service.OrderMainService#findOrderSplitlist(java.lang.String)
	 */
	@Override
	public List<OrderMain> findOrderSplitlist(String orderNo, String ordernoP) {
		// TODO Auto-generated method stub
		return orderMainDao.findOrderSplitlist(orderNo, ordernoP);
	}

	@Override
	public void updateClientServiceRemark(OrderMain orderMain) {
		OrderMain oldOrderMain = this.getByField("orderNo", orderMain.getOrderNo());
  		if (null != oldOrderMain) {
  			OrderOperateLog orderOperateLog = new OrderOperateLog();
  			Date now = new Date();
  			orderOperateLog.setDateCreated(now);
  			orderOperateLog.setDateUpdated(now);
  			orderOperateLog.setOrderNo(oldOrderMain.getOrderNo());
  			orderOperateLog.setContent(UPDATE_CLIENT_REMARK_CONTENT);
  			orderOperateLog.setUpdatedBy(userUtil.getLoginUserRealName());
  			orderOperateLog.setCreatedBy(userUtil.getLoginUserRealName());
  			orderOperateLog.setOperator(userUtil.getLoginUserRealName());
			orderOperateLog.setOldData(String.format(UPDATE_CLIENT_REMARK_LOG_CONTENT, oldOrderMain.getClientServiceRemarkFlag(),
					oldOrderMain.getClientServiceRemark()));
			orderOperateLog.setNewData(
					String.format(UPDATE_CLIENT_REMARK_LOG_CONTENT, orderMain.getClientServiceRemarkFlag(), orderMain.getClientServiceRemark()));
			orderOperateLog.setIP("");

  			oldOrderMain.setUpdatedBy(userUtil.getLoginUserRealName());
  			oldOrderMain.setDateUpdated(new Date());
  			oldOrderMain.setClientServiceRemark(orderMain.getClientServiceRemark());
  			oldOrderMain.setClientServiceRemarkFlag(orderMain.getClientServiceRemarkFlag());
  			this.update(oldOrderMain);
  			
  			orderOperateLogService.save(orderOperateLog);
  		}
	}

	@Override
	public void updateOrderStatus(String orderSubNo, String payStatus, String confirmStatus, String totalStatus) {
		OrderSub os = orderSubService.getByField(OrderSub_.orderSubNo, orderSubNo);
		OrderMain om = this.findByOrderNo(orderNoService.getOrderNoBySubNo(orderSubNo));
		
		if(!"0".equals(payStatus)){
			om.setStatusPay(payStatus);
		}else{
			om.setStatusPay(null);
		}
		
		if(!"0".equals(confirmStatus)){
			om.setStatusConfirm(confirmStatus);
		}else{
			om.setStatusConfirm(null);
		}																				
		
		if(!"0".equals(totalStatus)){
			om.setStatusTotal(totalStatus);
		}else{
			om.setStatusTotal(null);
		}
		
	}
}
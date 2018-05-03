package com.ibm.oms.service.business.abstracts;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.fastjson.JSONObject;
import com.beans.stock.OmsSOInfo;
import com.beans.stock.StockLockByOms;
import com.beans.stock.StockUnLockByOms;
import com.beans.stock.constant.ClientConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.clerk.intf.ClerkInfoHsService;
import com.ibm.common.dto.DefaultOutputDto;
import com.ibm.member.dto.bundle.ResultBean;
import com.ibm.member.intf.PointsTransactionDetailHsService;
import com.ibm.oms.client.constant.OrderMainConstClient;
import com.ibm.oms.dao.constant.CancelOrderConst;
import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.constant.IntfReceiveConst;
import com.ibm.oms.dao.constant.IntfSentConst;
import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.dao.constant.OrderStatusAction;
import com.ibm.oms.dao.constant.OrderStatusType;
import com.ibm.oms.dao.constant.PayMode;
import com.ibm.oms.dao.constant.PayType;
import com.ibm.oms.dao.constant.PointOperator;
import com.ibm.oms.dao.constant.PromotionType;
import com.ibm.oms.domain.persist.IntfReceived;
import com.ibm.oms.domain.persist.IntfVerified;
import com.ibm.oms.domain.persist.MerchantBalanceDate;
import com.ibm.oms.domain.persist.OrderCombineRelation;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderItemVirtual;
import com.ibm.oms.domain.persist.OrderItem_;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderMain_;
import com.ibm.oms.domain.persist.OrderOperateLog;
import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.oms.domain.persist.OrderPayMode;
import com.ibm.oms.domain.persist.OrderPayMode_;
import com.ibm.oms.domain.persist.OrderPay_;
import com.ibm.oms.domain.persist.OrderPromotion;
import com.ibm.oms.domain.persist.OrderPromotion_;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.domain.persist.OrderSub_;
import com.ibm.oms.domain.persist.OrdiErrOptLog;
import com.ibm.oms.domain.persist.ProductProperty;
import com.ibm.oms.intf.constant.OrderMainConst;
import com.ibm.oms.intf.intf.BBCLogiDTO;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderDTO;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderOutputDTO;
import com.ibm.oms.intf.intf.ColorSizeInfo;
import com.ibm.oms.intf.intf.CombineProductDTOList;
import com.ibm.oms.intf.intf.CommodityStockInfoDTO;
import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.intf.intf.CouponPayDTO;
import com.ibm.oms.intf.intf.InventoryLockOutputDTO;
import com.ibm.oms.intf.intf.InventoryResendMsgDTO;
import com.ibm.oms.intf.intf.InventoryResendMsgOutputDTO;
import com.ibm.oms.intf.intf.LogisticsDTO;
import com.ibm.oms.intf.intf.MemberPayDTO;
import com.ibm.oms.intf.intf.OmsMemberRefundDTO;
import com.ibm.oms.intf.intf.OmsMemberRefundOutputDTO;
import com.ibm.oms.intf.intf.OmsMemberVipCardOutputDTO;
import com.ibm.oms.intf.intf.PointsDto;
import com.ibm.oms.intf.intf.PromotionAddDTO;
import com.ibm.oms.intf.intf.ResultDTO;
import com.ibm.oms.intf.intf.SupportResend;
import com.ibm.oms.intf.intf.SupportResendOutputDTO;
import com.ibm.oms.intf.intf.TransportCompParam;
import com.ibm.oms.intf.intf.inner.BtcOmsReceiveDetailDTO;
import com.ibm.oms.intf.intf.inner.BtcOmsReceiveOutputDTO;
import com.ibm.oms.intf.intf.inner.CombineProductDTO;
import com.ibm.oms.intf.intf.inner.CouponDTO;
import com.ibm.oms.intf.intf.inner.MyCardDTO;
import com.ibm.oms.intf.intf.inner.OmsMemberRefundInnerDTO;
import com.ibm.oms.intf.intf.inner.OrderItemDTO;
import com.ibm.oms.intf.intf.inner.OrderMainDTO;
import com.ibm.oms.intf.intf.inner.OrderSubDTO;
import com.ibm.oms.intf.intf.inner.TransportComp;
import com.ibm.oms.service.IntfReceivedService;
import com.ibm.oms.service.IntfSentService;
import com.ibm.oms.service.IntfVerifiedService;
import com.ibm.oms.service.MerchantBalanceDateService;
import com.ibm.oms.service.OrderCombineRelationService;
import com.ibm.oms.service.OrderInvoiceService;
import com.ibm.oms.service.OrderItemPayService;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.OrderItemVirtualService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderOperateLogService;
import com.ibm.oms.service.OrderPayModeService;
import com.ibm.oms.service.OrderPayService;
import com.ibm.oms.service.OrderPromotionService;
import com.ibm.oms.service.OrderStatusSyncLogService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.oms.service.OrdiErrOptLogService;
import com.ibm.oms.service.ProductPropertyService;
import com.ibm.oms.service.business.OmsWarehouseService;
import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.oms.service.business.OrderCreateServiceConfigService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.OrderStatusService;
import com.ibm.oms.service.business.ReturnChangeOrderService;
import com.ibm.oms.service.business.SaleAfterOrderService;
import com.ibm.oms.service.business.trans.ImsOmsTransService;
import com.ibm.oms.service.business.trans.OrderCreateTrans;
import com.ibm.oms.service.business.trans.SaleAfterOrderTransService;
import com.ibm.oms.service.err.ErrConst;
import com.ibm.oms.service.err.OrdiErrResumerVisitor;
import com.ibm.oms.service.intf.ContextBtcOmsReceiveDTO;
import com.ibm.oms.service.util.CommonConstService;
import com.ibm.oms.service.util.CommonUtilService;
import com.ibm.oms.service.util.DateUtil;
import com.ibm.oms.service.util.ExceptionUtil;
import com.ibm.oms.service.util.UserUtil;
import com.ibm.product.dto.product.ProductBriefClientBean;
import com.ibm.product.intf.ProductClientService;
import com.ibm.sc.beans.sys.OptionBean;
import com.ibm.sc.service.sys.OptionService;
import com.ibm.sc.service.sys.UserService;
import com.ibm.sc.util.DateUtils;
import com.ibm.stock.dto.MessageDto;
import com.ibm.stock.dto.SkuStockOperateDto;
import com.ibm.stock.dto.SkuStockOperateHeaderDto;
import com.ibm.stock.dto.SkuStockOperateLineDto;
import com.ibm.stock.intf.StockDeductByOrderService;
import com.ibm.store.dto.output.ClerkInfoOutputDto;

public abstract class OrderCreateServiceAbstract extends OrderCreateServiceCommon implements OrderCreateService, OrderCreateServiceConfigService<BtcOmsReceiveOrderDTO>{
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
    public abstract BtcOmsReceiveOrderOutputDTO createOrder(BtcOmsReceiveOrderDTO orderReceiveDIO);
    //修改快递公司操作日志格式
  	public static final String UPDATE_DELIVERY_CONTENT = "修改物流公司";
      @Autowired
      OrderMainService orderMainService;
      @Autowired
      OrderSubService orderSubService;
      @Autowired
      OrderItemService orderItemService;
      @Autowired
      OrderItemVirtualService orderItemVirtualService;
      @Autowired
      OrderPromotionService orderPromotionService;
      @Autowired
      OrderNoService orderNoService;
      @Autowired
      IntfReceivedService intfReceivedService;
      @Autowired
      IntfSentService intfSentService;
      @Autowired
      IntfVerifiedService intfVerifiedService;
      @Autowired
      OrderStatusService orderStatusService;
      @Autowired
      OrderPayService orderPayService;
      @Autowired
      OrderItemPayService orderItemPayService;
      @Autowired
      OrdiErrOptLogService ordiErrOptLogService;
      @Autowired
      OrderCombineRelationService orderCombineRelationService;
      @Autowired
      OrderInvoiceService orderInvoiceService;
      @Autowired
      private OrderCreateTrans orderCreateTrans;
      @Autowired
      private CommonUtilService commonUtilService;
      @Autowired
      private SaleAfterOrderTransService saleAfterOrderTransService;
      @Autowired
      private ImsOmsTransService imsOmsTransService;
      @Autowired
      OrderPayModeService orderPayModeService;
      @Autowired
      OrderStatusSyncLogService orderStatusSyncLogService;
      @Autowired
      OrdiErrResumerVisitor ordiErrResumerVisitor;
      @Autowired
      ReturnChangeOrderService returnChangeOrderService;
      @Autowired
      UserService userService;
      @Autowired
      ProductPropertyService productPropertyService;
      @Resource
      SaleAfterOrderService saleAfterOrderService;
      @Resource
      UserUtil userUtil;
      @Resource
      private OrderOperateLogService orderOperateLogService;
      @Autowired
      OmsWarehouseService warehouseService;
      
      @Autowired
      private StockDeductByOrderService stockDeductByOrderService;
      //
      @Autowired
      OptionService optionService;
      //默认物流公司
      @Value("#{settings['logistics.code.default']}")
      public String logisticsCode;
      @Autowired
      MerchantBalanceDateService merchantBalanceDateService;
      OrderCreateServiceCommon orderCreateServiceCommon;
      @Autowired
      ClerkInfoHsService clerkInfoHsService;
      @Autowired
      PointsTransactionDetailHsService pointsTransactionDetailHsService;  
      OrderCreateServiceCommon serviceCommon;
      @Autowired
      ProductClientService productClientService;
      
      public OrderCreateServiceCommon getServiceCommon() {
		return serviceCommon;
      }

      @Resource(name = "orderCreateServiceCommon")
      public void setServiceCommon(OrderCreateServiceCommon serviceCommon) {
    	  this.serviceCommon = serviceCommon;
      }
	/** 上下文中的单号对应返回给btc **/
      public void initOutputDTO(BtcOmsReceiveOrderOutputDTO output, ContextBtcOmsReceiveDTO context) {
          Map<String, OrderMain> mainMap = context.getOmMap();
          for (OrderMain om : mainMap.values()){
              // element为挂output中的map元素
              BtcOmsReceiveOutputDTO receiveOutputElement = new BtcOmsReceiveOutputDTO();
              output.getMapList().add(receiveOutputElement);
              //如果不是第三方平台 AliasOrderNo = null 则赋值为主订单号
              if(StringUtils.isAnyEmpty(om.getAliasOrderNo())){
            	  receiveOutputElement.setBtcOrderNo(om.getOrderNo());
              }else{
            	  receiveOutputElement.setBtcOrderNo(om.getAliasOrderNo());
              }
              
              receiveOutputElement.setIdOrder(commonUtilService.Long2Str(om.getId()));
              receiveOutputElement.setOrderNo(om.getOrderNo());
              //receiveOutputElement.setMsg(buildingOrderMainOutMsg(om));
              // 写子订单
              Map<String, List<OrderSub>> osListMap = context.getOsListMap();
              List<OrderSub> osList = null;
              if (!osListMap.isEmpty()) {
                  osList = osListMap.get(om.getOrderNo());
              }
              if (osList != null && !osList.isEmpty()) {
                  HashMap<String, BtcOmsReceiveDetailDTO> subMap = receiveOutputElement.getSubMap();
                  for (OrderSub os : osList) {
                	  //如果不是第三方平台 AliasOrderSubNo = null 则赋值为子订单号
                	  if(StringUtils.isEmpty(os.getAliasOrderSubNo())){
                		  subMap.put(os.getOrderSubNo(), new BtcOmsReceiveDetailDTO(os.getOrderSubNo(),
                                  commonUtilService.Long2Str(os.getId())));
                	  }else{
                		  subMap.put(os.getAliasOrderSubNo(), new BtcOmsReceiveDetailDTO(os.getOrderSubNo(),
                                  commonUtilService.Long2Str(os.getId())));
                	  }
                      
                  }
              }
              // 写订单行
              Map<String, List<OrderItem>> oiListMap = context.getOiListMap();
              List<OrderItem> oiList = null;
              if (!oiListMap.isEmpty()) {
                  oiList = oiListMap.get(om.getOrderNo());
              }
              if (osList != null && !osList.isEmpty()) {
                  HashMap<String, BtcOmsReceiveDetailDTO> itemMap = receiveOutputElement.getItemMap();
                  for (OrderItem oi : oiList) {
                	  //如果不是第三方平台 AliasOrderItemNo = null 则赋值为行订单号
                	  if(StringUtils.isEmpty(oi.getAliasOrderItemNo())){
                		  itemMap.put(oi.getOrderItemNo(), new BtcOmsReceiveDetailDTO(oi.getOrderItemNo(),
                                  commonUtilService.Long2Str(oi.getId())));
                	  }else{
                		  itemMap.put(oi.getAliasOrderItemNo(), new BtcOmsReceiveDetailDTO(oi.getOrderItemNo(),
                                  commonUtilService.Long2Str(oi.getId())));
                	  }
                      
                  }
              }
              // 写虚拟订单行
              Map<String, List<OrderItemVirtual>> oivListMap = context.getOivListMap();
              List<OrderItemVirtual> oivList = null;
              if (!oivListMap.isEmpty()) {
                  oivList = oivListMap.get(om.getOrderNo());
              }
              if (oivList != null && !oivList.isEmpty()) {
                  HashMap<String, BtcOmsReceiveDetailDTO> itemVirtualMap = receiveOutputElement.getItemVirtualMap();
                  for (OrderItemVirtual oiv : oivList) {
                      itemVirtualMap.put(oiv.getAliasOrderItemNo(), new BtcOmsReceiveDetailDTO(oiv.getOrderItemNo(),
                              commonUtilService.Long2Str(oiv.getId())));
                  }
              }
              /** 需考虑是否为APP的订单（1、APP订单通过B2C接口对接到中台，so接口中的AliasOrderNo为B2C的orderId,且此字段需返回给B2C接口），但是中台需记录APP订单号 **/
              if(StringUtils.isNoneBlank(om.getSourceOrderNo())){
                  if (om.getOrderSource().equals(OrderMainConst.OrderMain_Ordersource_App.getCode()) || 
                          om.getOrderSource().equals(OrderMainConst.OrderMain_Ordersource_Wgw.getCode()) ||
                          om.getOrderSource().equals(OrderMainConst.OrderMain_Ordersource_Jd.getCode()) ||
                          om.getOrderSource().equals(OrderMainConst.OrderMain_Ordersource_Wph.getCode())) {
                      om.setAliasOrderNo(om.getSourceOrderNo());
                      orderMainService.update(om);
                  }
              }
          }
      }

      public String buildingOrderMainOutMsg(OrderMain om) {
		String str = String.format("订单头信息---->:"
				+ "[订单总状态:%s]"
				+ "[支付状态:%s]"
				+ "[支付总额:%s]"
				+ "[折扣总额:%s]"
				+ "[商品总价:%s]"
				+ "[优惠券:%s]"
				+ "[积分支付:%s]"
				+ "[使用积分数:%s]", 
				om.getStatusTotal(),
				om.getStatusPay(),
				om.getTotalPayAmount(),
				commonUtilService.bigDecimal2String(om.getDiscountTotal()),
				commonUtilService.bigDecimal2String(om.getTotalProductPrice()),
				commonUtilService.bigDecimal2String(om.getTotalPromo()),
				commonUtilService.bigDecimal2String(om.getTotalPointAmount()),
				commonUtilService.bigDecimal2String(om.getTotalPoint())
			);
		return str;
	}

	@Override
      public void processPostBTC(int count) {
          List<IntfVerified> ivList = intfVerifiedService.findByCriteriaPredicate(
                  IntfReceiveConst.BTC_OMS_RECEIVE.getCode(), count, CommonConstService.PROCESS_WAIT);
          logger.info("to be processed order size" + ivList.size());

          for (IntfVerified ivf : ivList) {
              String msg = ivf.getMsg();
              // 获取信息为空
              if (msg == null) {
                  continue;
              }
              ObjectMapper om = new ObjectMapper();
              ContextBtcOmsReceiveDTO context;
              try {
                  ivf.setProcessFlag(CommonConstService.PROCESSING);
                  intfVerifiedService.update(ivf);
                  context = om.readValue(msg, ContextBtcOmsReceiveDTO.class);
                  /** 货到付款定时任务 **/
                  for (String orderNo : context.getOmMap().keySet()) {
                      // 订单逐个处理
                      logger.info("处理订单单号: {}", orderNo);
                      if (callSingleProcess(orderNo)) {
                          logger.info("处理订单单号完成: {}", orderNo);
                      }
                  }
              } catch (Exception e) {
                  logger.info("处理订单单号异常: batchNum: {}", ivf.getBtcOrderItemNo());
                  logger.info("异常{}", e);
                  // ivf.setProcessFlag(CommonConstService.PROCESS_FAILED);
                  // intfVerifiedService.update(ivf);
                  continue;
              }
              ivf.setProcessFlag(CommonConstService.PROCESS_SUCCEED);
              intfVerifiedService.update(ivf);
          }
      }

      /** 状态成功变更true,否则false **/
      @Override
      public boolean callSingleProcess(String orderNo) {
          OrderMain om = orderMainService.findByOrderNo(orderNo);
          String totalStatus = orderStatusService.getPreviousOrderStatus(om, null,
                  OrderStatusType.Order_Status_Positive.getCode());
          if (!OrderStatus.ORDER_PROCESSING.getCode().equals(totalStatus)
                  && !OrderStatus.ORDER_PROCESS_FAILED.getCode().equals(totalStatus)) {
              logger.info("订单状态{}不是0120或0121", totalStatus);
              return true;
          }
          List<OrderSub> osList = orderSubService.findByField(OrderSub_.orderNo, om.getOrderNo());
          // 更新会员等级
          logger.info("更新会员等级,orderNo: {}, batch_num: {}", orderNo, om.getBatchNum());
          OmsMemberVipCardOutputDTO output = returnChangeOrderService.searchMemberVipCard(om.getMemberNo());
          // if (output != null && !StringUtils.isBlank(output.getVipLevel()) &&
          // StringUtils.isBlank(om.getMemberCardLevel())) {
          // om.setMemberCardLevel(output.getVipLevel());
          // orderMainService.update(om);
          // logger.info("会员等级更新完成,orderNo: {}, batch_num: {}", orderNo, om.getBatchNum());
          // }
          // 会员等级给-1，代表需重新获取会员等级【add by 20140904】
          if (StringUtils.isNotBlank(om.getMemberCardLevel())
                  && om.getMemberCardLevel().equals(CommonConst.CommonBooleanDefault.getCode())) {
              if (output != null && !StringUtils.isBlank(output.getVipLevel())) {
                  om.setMemberCardLevel(output.getVipLevel());
                  orderMainService.update(om);
                  logger.info("会员等级更新完成,orderNo: {}, batch_num: {}", orderNo, om.getBatchNum());
              }
          }

          boolean isDistributePrior = (output != null && output.getIsPriorityOrder());
          for (OrderSub os : osList) {
              if (isDistributePrior) {
                  os.setDeliveryPriority(CommonConst.CommonBooleanTrue.getCode());
              } else {
                  os.setDeliveryPriority(CommonConst.CommonBooleanFalse.getCode());
              }
              orderSubService.update(os);
              logger.info("更新订单{}物流优先级为{}", os.getOrderNo(), os.getDeliveryPriority());
          }
          // 写组合商品
          if (!buildCombineProduct(orderNo)) {
              logger.info("更新组合商品返回,orderNo:{}", orderNo);
              return true;
          }
          // 物流商选择
          if (!callLogistic(orderNo)) {
              logger.info("更新物流商返回,orderNo:{}", orderNo);
              return true;
          }
          //写色码款属性
          try{
              buildSemakuanProduct(orderNo);
          }catch(Exception e){
              logger.info("订单获取色码款属性异常，单号："+orderNo+"，异常信息："+e.getMessage());
          }
          
          // 自动审核通过
          if (!autoAudit(orderNo)) {
              return true;
          }
          // 扣减库存，出库
          if (!inventoryDeduct(orderNo)) {
              return true;
          }
          supportSendEmail(orderNo);
          return true;
      }

      @Override
      public String manualAudit(String orderNo, boolean approved) {
          OrderMain om = orderMainService.findByOrderNo(orderNo);
          if (null != om && null != userService.getLoginUser()) {
              // 设置审核人
              //om.setConfirmerName(userService.getLoginUser().getUserName());
              om.setConfirmerName(userUtil.getLoginUserRealName());
              // 设置审核时间
              om.setConfirmTime(new Date());
          } else {
              return String.format("manualOrdit 订单未找到,单号%s", orderNo);
          }

          String defaultSubNo = orderNoService.getDefaultOrderSubNoByOrderNo(om.getOrderNo());
          // 人工审核通过
          if (approved) {
              orderStatusService.saveProcess(defaultSubNo, OrderStatusAction.S014150, null, null, null);
              boolean inventorySucceed = inventoryDeduct(orderNo);
              if (inventorySucceed && OrderMainConst.OrderMain_OrderCategory_Sale.getCode().equals(om.getOrderCategory())) {
                  supportSendEmail(orderNo);
              }
              return null;
          } else {
              ResultDTO resultDTO = saleAfterOrderService.cancelOrder(defaultSubNo,
                      CancelOrderConst.CancelOrder_Scene_Saler);
              return resultDTO.getResultMessage();
          }
      }

      @Override
      public String addBlackList(String orderNo, String type) {
          OrderMain om = orderMainService.findByOrderNo(orderNo);
          String url = String.format(orderCreateServiceCommon.getMem04(), om.getMemberNo(), type);
          CommonOutputDTO output = commonUtilService.jsonGet(url, CommonOutputDTO.class, null);
          boolean succeed = (output != null && CommonConstService.SUCCEED_LowerCase.equals(output.getCode()));
          if (succeed) {
              return "添加成功";
          } else if (output == null) {
              return "会员返回null";
          } else {
              return "会员返回失败,信息:" + output.getMessage();
          }
      }

      @Override
      public boolean autoAudit(String orderNo) {
          OrderMain om = orderMainService.findByOrderNo(orderNo);
          String defaultSubNo = orderNoService.getDefaultOrderSubNoByOrderNo(om.getOrderNo());
          OrderSub os = orderSubService.get(OrderSub_.orderSubNo, defaultSubNo);
          // 自动审核功能关闭
          boolean go1 = !"1".equals(orderCreateServiceCommon.getAutoAuditSwitch());
          // 虚拟直接进入人工审核
          boolean go2 = CommonConst.OrderMain_OrderType_VIRTUAL.getCode().equals(om.getOrderType());
          // 礼卡直接进入人工审核
          boolean go3 = CommonConst.OrderMain_OrderType_GIFT_CART.getCode().equals(om.getOrderType());
          // 大家电，需要人工审核， 0804卓娅
          boolean go4 = CommonConst.OrderSub_ShipCat_Applicance.getCode().equals(os.getShipCat());
          // 顾客有备注进入人工审核
          boolean go5 = StringUtils.isNotBlank(om.getClientRemark());
          // 换货出库单无需自动审核(由关联的换货单审核状态决定)
          boolean go6 = OrderMainConst.OrderMain_OrderCategory_ChangeOut.getCode().equals(om.getOrderCategory());
          // 黑名单直接人工审核
          boolean go7 = CommonConstService.BOOLEAN_TRUE.equals(om.getIfBlackListMember());
          // 微信订单直接人工审核
          boolean go8 = OrderMainConst.OrderMain_Ordersource_Wgw.getCode().equalsIgnoreCase(om.getOrderSource())
                  || OrderMainConst.OrderMain_Ordersource_App.getCode().equalsIgnoreCase(om.getOrderSource());
          // jira TH-2428大客户，5000以上，人工审核
          boolean go9 = CommonConstService.BOOLEAN_TRUE.equals(om.getIfPriviledgedMember())
                  && new BigDecimal(5000).compareTo(om.getTotalPayAmount()) < 0;
          //使用在线支付及购物券支付订单纳入人工审核,140923,zhuo_ya
          boolean go10 = false;
          //在线支付
          boolean isPayOnline = !CommonConstService.BOOLEAN_TRUE.equals(om.getIfPayOnArrival());
          if (isPayOnline) {
              List<OrderPay> opList = om.getOrderPays();
              if (opList != null && !opList.isEmpty()) {
                  for (OrderPay op : opList) {
                      if (PayMode.COUPON.getCode().equals(op.getPayCode())) {
                          go10 = true;
                          break;
                      }
                  }
              }
          }
          if (go1 || go2 || go3 || go4 || go5 || go6 || go7 || go8 || go9 || go10) {
              String remark = go1 ? "go1" + getAutoAuditSwitch() : go2 ? "go2" : go3 ? "go3" : go4 ? "go4" : go5 ? "go5"
                      : go6 ? "go6" : go7 ? "go7" : go8 ? "go8" : go9 ? "go9" : go10 ? "go10" : "";
              logger.info("autoaudit orderNo {} failed due to {}", orderNo, remark);
              orderStatusService.saveProcess(defaultSubNo, OrderStatusAction.S014041, null, new Date(), remark);
              return false;
          }

          boolean autoAuditSuccess = orderCreateTrans.queryAutoAuditCriteria(orderNo);
          // 自动审核失败
          if (!autoAuditSuccess) {
              // 目前预警订单规则与自动审核规则一致，所以不符合自动审核条件则直接设置为预警订单
              om.setIfWarnOrder(CommonConst.CommonBooleanTrueLong.getCodeLong());// 预警订单
              orderMainService.update(om);
              orderStatusService.saveProcess(defaultSubNo, OrderStatusAction.S014041, null, null, null);
              return false;
          }
          orderStatusService.saveProcess(defaultSubNo, OrderStatusAction.S014050, null, null, null);
          return true;
      }

      /** 会员信息改在下单时调用,合并到my卡支付接口 **/
      // private void writeMemberFlag(String orderNo){
      // OrderMain om = orderMainService.findByOrderNo(orderNo);
      // String memberNo = om.getMemberNo();
      // OmsMemberAuditOutputDTO output = commonUtilService.jsonGetWithTrack(String.format(mem01, memberNo),
      // IntfSentConst.OMS_MEM_01.getCode(), OmsMemberAuditOutputDTO.class, null);
      // boolean priviledagedMember = output != null && output.getPriviledgedMember() != null &&
      // output.getPriviledgedMember();
      // if (priviledagedMember) {
      // om.setIfPriviledgedMember(CommonConstService.BOOLEAN_TRUE);
      // }else{
      // om.setIfPriviledgedMember(CommonConstService.BOOLEAN_FALSE);
      // }
      //
      // boolean blackListIncluded = output != null && output.getBlackListIncluded() != null &&
      // output.getBlackListIncluded();
      // if (blackListIncluded) {
      // om.setIfBlackListMember(CommonConstService.BOOLEAN_TRUE);
      // }else{
      // om.setIfBlackListMember(CommonConstService.BOOLEAN_FALSE);
      // }
      // orderMainService.update(om);
      // }

      /** 自动审核失败返回false **/
      // public boolean queryAutoAuditCriteria(String orderNo) {
      // OrderMain om = orderMainService.findByOrderNo(orderNo);
      // //总购物金额超2000(拆单前)
      // List<OrderMain> omList = orderMainService.findByField(OrderMain_.batchNum, om.getBatchNum());
      // if(omList != null && !omList.isEmpty()){
      // BigDecimal totalMoney = new BigDecimal(0);
      // for(OrderMain omain:omList){
      // totalMoney = totalMoney.add(omain.getTotalPayAmount());
      // }
      // if(totalMoney.compareTo(totalPayAmountLimit) >= 0){
      // return false;
      // }
      // }
      //
      // //单品1000以上(拆单前)
      // if(omList != null && !omList.isEmpty()){
      // for(OrderMain omain:omList){
      // for(OrderSub sub:omain.getOrderSubs()){
      // for(OrderItem item:sub.getOrderItems()){
      // BigDecimal payUnitMoney = new BigDecimal(0);
      // if(null!=item.getUnitDeductedPrice()){
      // payUnitMoney = item.getUnitDeductedPrice();//折后单价
      // }else{
      // if(null!=item.getUnitDiscount()){
      // payUnitMoney = item.getUnitPrice().subtract(item.getUnitDiscount());
      // }else{
      // payUnitMoney = item.getUnitPrice();
      // }
      // }
      // if(payUnitMoney.compareTo(itemPayAmountLimit)>=0){
      // return false;
      // }
      // }
      // }
      // }
      // }

      // 7天内购买低毛利商品超过20单，或者1天购买低毛利商品超过5单
      /*
       * Calendar calendar7DaysBefore = Calendar.getInstance(); Calendar calendar1DaysBefore = Calendar.getInstance();
       * calendar7DaysBefore.add(Calendar.DATE, daysIntervalM7); calendar7DaysBefore.add(Calendar.DATE, daysIntervalM1);
       * Long t7 = calendar7DaysBefore.getTimeInMillis(); Long t1 = calendar1DaysBefore.getTimeInMillis(); int count1 =
       * orderMainService.getOMListByTypeAndPeriod(OrderMain_.memberNo, memberNo, t1); if (count1 >
       * blackListCriteriaDays1) { return false; } int count7 =
       * orderMainService.getOMListByTypeAndPeriod(OrderMain_.memberNo, memberNo, t7); if (count7 >
       * blackListCriteriaDays7) { return false; } int countMobile1 =
       * orderMainService.getOMListByTypeAndPeriod(OrderMain_.customerPhone, mobileNo, t1); if (countMobile1 >
       * blackListCriteriaDays1) { return false; } int countMobile7 =
       * orderMainService.getOMListByTypeAndPeriod(OrderMain_.customerPhone, mobileNo, t7); if (countMobile7 >
       * blackListCriteriaDays7) { return false; }
       */
      // return true;
      // }

	@Override
	public boolean inventoryDeduct(String orderNo) {
		OrderMain orderMain = orderMainService.findByOrderNo(orderNo);
		SkuStockOperateDto stockOperateDto = this.createStockDtoByOrderMain(orderMain);
		
		logger.info("inventoryDeduct  params======{}", stockOperateDto);
		
		MessageDto msgDto = stockDeductByOrderService.stockSubtract(stockOperateDto);
		
		if (msgDto.isSuccess()) {
			orderStatusService.saveProcess(orderNoService.getDefaultOrderSubNoByOrderNo(orderMain.getOrderNo()), OrderStatusAction.S015060, null,
					null, null);
			return true;
		} else {
			orderStatusService.saveProcess(orderNoService.getDefaultOrderSubNoByOrderNo(orderMain.getOrderNo()), OrderStatusAction.S015051, null,
					null, null);
			OrdiErrOptLog err = new OrdiErrOptLog();
			err.setOrderNo(orderMain.getOrderNo());
			err.setOrderSubNo(orderNoService.getDefaultOrderSubNoByOrderNo(orderMain.getOrderNo()));
			err.setOrderStatus(OrderStatus.ORDER_INVENTORY_DEDUCT_FAILED.getDesc());
			err.setErrorCode(ErrConst.IMS_DEDUCT.getCode());
			err.setErrorType(ErrConst.IMS_DEDUCT.getDesc());
			err.setOrderStatus(OrderStatus.ORDER_INVENTORY_DEDUCT_FAILED.getCode());
			err.setOperateTime(new Date());
			ordiErrOptLogService.save(err);
			return false;
		}
	}

//      @Override
//      public boolean inventoryDeduct(String orderNo) {
//          Long t1 = System.currentTimeMillis();
//          OrderMain om = orderMainService.findByOrderNo(orderNo);
//          OmsSOInfo info = imsOmsTransService.queryInventoryDeduct(orderNo);
//          InventoryLockOutputDTO output = commonUtilService.jsonPostWithTrack(serviceCommon.getSim03(),
//                  IntfSentConst.OMS_SIM_DEDUCT.getCode(), info, InventoryLockOutputDTO.class, 8000);
//          logger.info("库存扣减接口耗时{}, 单号{}", System.currentTimeMillis() - t1, orderNo);
//          if (output == null || !NumberUtils.isDigits(output.getReturn_status())
//                  || NumberUtils.toInt(output.getReturn_status()) < 0) {
//              orderStatusService.saveProcess(orderNoService.getDefaultOrderSubNoByOrderNo(om.getOrderNo()),
//                      OrderStatusAction.S015051, null, null, null);
//              OrdiErrOptLog err = new OrdiErrOptLog();
//              err.setOrderNo(om.getOrderNo());
//              err.setOrderSubNo(orderNoService.getDefaultOrderSubNoByOrderNo(om.getOrderNo()));
//              err.setOrderStatus(OrderStatus.ORDER_INVENTORY_DEDUCT_FAILED.getDesc());
//              err.setErrorCode(ErrConst.IMS_DEDUCT.getCode());
//              err.setErrorType(ErrConst.IMS_DEDUCT.getDesc());
//              err.setOrderStatus(OrderStatus.ORDER_INVENTORY_DEDUCT_FAILED.getCode());
//              err.setOperateTime(new Date());
//              ordiErrOptLogService.save(err);
//              return false;
//          } 
//          List<OrderItemVirtual> itemVirtuals = orderItemVirtualService
//                  .findByField(OrderItemVirtual_.idOrder, om.getId());
//          boolean isGiftCardOrVirtual = (itemVirtuals != null && !itemVirtuals.isEmpty());
//          if (isGiftCardOrVirtual) {
//              orderStatusService.saveProcess(orderNoService.getDefaultOrderSubNoByOrderNo(om.getOrderNo()),
//                      OrderStatusAction.S015080, null, null, null);
//              // 卡或虚拟
//              promoResourceAdd(om.getBatchNum(), om.getOrderNo());
//              om.setFinishTime(new Date());
//              orderMainService.update(om);
//          } else {
//              String statusTotal = om.getStatusTotal();
//              if (statusTotal.equals(OrderStatus.ORDER_INVENTORY_DEDUCT_FAILED.getCode())) { // 库存扣减失败后再次发起库存扣减
//                  orderStatusService.saveProcess(orderNoService.getDefaultOrderSubNoByOrderNo(om.getOrderNo()),
//                          OrderStatusAction.S015160, null, null, null);
//              } else {
//                  orderStatusService.saveProcess(orderNoService.getDefaultOrderSubNoByOrderNo(om.getOrderNo()),
//                          OrderStatusAction.S015060, null, null, null);
//              }
//          }
//          return true;
//      }

      @Override
      public String inventoryDeductBbc(String orderNo, String opernateName, String flag, String exceptionFlag) {
          OrderMain om = orderMainService.findByOrderNo(orderNo);
          if (null == om) {
              return String.format("订单未找到");
          }
          // 设置审核人
          om.setConfirmerName(opernateName);
          // 根据flag判断是否需要判断支付状态,Y:需要；N：不需要
          if ("Y".equals(flag)) {
              // 判断在线支付且已完成
              if (!OrderStatus.Order_PayStatus_Success.getCode().equals(om.getStatusPay())) {
                  return String.format("订单未支付完成");
              }
          }
          String defaultSubNo = orderNoService.getDefaultOrderSubNoByOrderNo(om.getOrderNo());
          // exceptionFlag：Y 如果是正常审核
          if ("Y".equals(exceptionFlag)) {
              // 人工审核通过
              orderStatusService.saveProcess(defaultSubNo, OrderStatusAction.S014150, opernateName, new Date(), null);
              boolean inventorySucceed = inventoryDeduct(orderNo);
              if (inventorySucceed && OrderMainConst.OrderMain_OrderCategory_Sale.getCode().equals(om.getOrderCategory())) {
                  supportSendEmail(orderNo);
              }
          } else {// 异常处理
              boolean inventorySucceed = inventoryDeduct(orderNo);

              if (!inventorySucceed) {
                  return "扣减库存失败";
              }
              if (inventorySucceed && OrderMainConst.OrderMain_OrderCategory_Sale.getCode().equals(om.getOrderCategory())) {
                  supportSendEmail(orderNo);
              }
          }
          return null;
      }

      @Override
      public boolean inventoryCancel(String orderNo) {
          OrderMain om = orderMainService.findByOrderNo(orderNo);
          Long t1 = System.currentTimeMillis();
          OmsSOInfo info = imsOmsTransService.queryInventoryDeduct(om.getOrderNo());
          InventoryLockOutputDTO output = commonUtilService.jsonPostWithTrack(orderCreateServiceCommon.getSim04(),
                  IntfSentConst.OMS_SIM_CANCEL.getCode(), info, InventoryLockOutputDTO.class, 8000);
          logger.info("==============={}"+JSONObject.toJSON(output));
          logger.info("库存扣减接口耗时{}, 单号{}", System.currentTimeMillis() - t1, orderNo);
          if (output == null || !NumberUtils.isDigits(output.getReturn_status())
                  || NumberUtils.toInt(output.getReturn_status()) < 0) {
              OrdiErrOptLog err = new OrdiErrOptLog();
              err.setOrderNo(om.getOrderNo());
              err.setOrderSubNo(orderNoService.getDefaultOrderSubNoByOrderNo(om.getOrderNo()));
              err.setOrderStatus(OrderStatus.ORDER_INVENTORY_DEDUCT_FAILED.getDesc());
              err.setErrorCode(ErrConst.IMS_CANCEL_DEDUCT.getCode());
              err.setErrorType(ErrConst.IMS_CANCEL_DEDUCT.getDesc());
              err.setOrderStatus(OrderStatus.ORDER_INVENTORY_DEDUCT_FAILED.getCode());
              err.setOperateTime(new Date());
              ordiErrOptLogService.save(err);
              return false;
          } else {
              return true;
          }

      }

      /**
       * 库存锁定：创建订单时（B2C）
       */
      public boolean inventoryLockBatch(ContextBtcOmsReceiveDTO context) {
          Map<String, OrderMain> omMap = context.getOmMap();
          List<OrderMain> omListToRoll = new ArrayList<OrderMain>();

          boolean successFlag = false;
          for (OrderMain om : omMap.values()) {
              InventoryLockOutputDTO output = inventoryLock(om.getOrderNo());
              if (NumberUtils.toInt(output.getReturn_status()) >= 0) {
                  omListToRoll.add(om);
              } else {
                  context.setMsg(CommonConstService.SimPrefix + output.getReturn_msg());
                  break;
              }
          }
          // 全部锁定成功
          if (omMap.size() == omListToRoll.size()) {
              omListToRoll = null;
              successFlag = true;
          } else {
              // 有锁定失败，回滚锁定
              inventoryUnLock(omListToRoll);
          }
          return successFlag;
      }

      /**
       * 促销资源扣减
       * 
       * @param orderReceiveDIO
       * @param context
       * @return
       */
      public boolean promoResourceDeduct(BtcOmsReceiveOrderDTO orderReceiveDIO, ContextBtcOmsReceiveDTO context) {
          Map<String, OrderMain> omMap = context.getOmMap();
          CouponPayDTO cpDTO = new CouponPayDTO();
          cpDTO.setBatchNum(orderReceiveDIO.getBatchNum());
          List<CouponDTO> clList = new ArrayList<CouponDTO>();
          cpDTO.setCouponList(clList);
          boolean couponUsed = false;
          for (String orderNo : omMap.keySet()) {
              List<OrderPay> opList = orderPayService.findByField(OrderPay_.orderNo, orderNo);
              for (OrderPay op : opList) {
                  if (!PayMode.COUPON.getCode().equals(op.getPayCode())) {
                      continue;
                  }
                  couponUsed = true;
                  CouponDTO cDTO = new CouponDTO();
                  cDTO.setOrderSubNo(orderNoService.getDefaultOrderSubNoByOrderNo(orderNo));
                  cDTO.setCouponAmount(op.getPayAmount());
                  cDTO.setCouponNo(op.getCardNo());
                  clList.add(cDTO);
              }
          }
          if (!couponUsed) {
              return true;
          }
          CommonOutputDTO output = null;
          boolean exceptionThrown = false;
          try {
              output = commonUtilService.jsonPostWithTrack(orderCreateServiceCommon.getPromo01(), IntfSentConst.OMS_PROMO.getCode(), cpDTO,
                      CommonOutputDTO.class, null);
          } catch (Exception e) {
        	  e.printStackTrace();
              logger.info("{}", e);
              exceptionThrown = true;
          }
          if (output == null || exceptionThrown || output.getCode() == null
                  || CommonConstService.FAILED.equals(output.getCode())) {
              String msg = "null";
              if (output != null && output.getMsg() != null) {
                  msg = output.getMsg();
              }
              logger.info(String.format("error:促销资源扣减异常-->[post:%s][params:%s][message:%s]", orderCreateServiceCommon.getPromo01(),commonUtilService.ObjectTransJsonstr(cpDTO),CommonConstService.Promo01Prefix + msg));
              context.setMsg(String.format("error:促销资源扣减异常-->[post:%s][params:%s][message:%s]", orderCreateServiceCommon.getPromo01(),commonUtilService.ObjectTransJsonstr(cpDTO),CommonConstService.Promo01Prefix + msg));
          }
          return true;
      }

      /**
       * 促销资源加回（订单完成）
       */
      public boolean promoResourceAdd(String batchNum, String orderNo) {
          // 批次号为空，如换货出库单
          if (StringUtils.isBlank(batchNum)) {
              return true;
          }
          OrderMain orderMain = orderMainService.findByOrderNo(orderNo);
          // add by 20140730 xiaohl for 赠送顾客账户正常购买商品所得积分
          try {
              // 调用会员积分接口赠送积分
              logger.info("订单{}完成:" + ",赠送积分starting...", orderNo);
              if (orderMain.getTotalGivePoints().compareTo(new BigDecimal(0)) > 0) {
                  ResultDTO result = returnChangeOrderService.handelIntegral("sys", orderNo, true, true);
                  logger.info("订单{}完成:赠送积分结果：{}", orderNo, result.getResultMessage());
              }
          } catch (Exception e) {
              logger.info("{}", e);
          }
          // 所有订单已完成
          boolean allOrderFinished = isAllOrdersFinished(batchNum);
          
          //update by 20140922 for 促销接口调整:返券、返积分等统一为一个接口
          List<OrderPromotion> promoList = orderPromotionService.findByField(OrderPromotion_.orderNo, orderNo);
          if(null==promoList || promoList.isEmpty()){
              return true;
          }
          CommonOutputDTO output = null;
          boolean exceptionThrown = false;
          PromotionAddDTO input = new PromotionAddDTO();
          input.setMemberNo(orderMain.getMemberNo()); //会员编号
          List<Map<String, String>> sendPromoList = new ArrayList<Map<String, String>>(); // 所有的促销规则编号
          for(OrderPromotion op : promoList){
              boolean addFlag = false;
              String promoType = op.getPromoType();
              //订单级别的促销【要求同一批次的订单全部完成才赠送】
              if(PromotionType.TotalCoupon.getCode().equals(promoType) || PromotionType.TotalPoint.getCode().equals(promoType)){
                  if(allOrderFinished){
                      addFlag = true;    
                  }
              }
              //明细行级别的促销
              if(PromotionType.ProductCoupon.getCode().equals(promoType) || PromotionType.ProductPoint.getCode().equals(promoType)){
                  addFlag = true; 
              }
              if(addFlag){
                  logger.info("订单完成进行返券返积分，单号："+orderNo+"，促销编码："+op.getPromoNo());
                  Map<String, String> map = new HashMap<String, String>();
                  map.put(PromotionAddDTO.keyName_promotionCode, op.getPromoNo());
                  map.put(PromotionAddDTO.keyName_promotionType, op.getPromoType());
                  sendPromoList.add(map);
              }
          }
          input.setPromotionList(sendPromoList);
          
          /*// 订单级促销
          List<OrderPromotion> promoList = orderPromotionService.findByOrderNoAndLevel(orderNo,
                  CommonConst.OrderPromotion_Promolevel_Order.getCode());
          // 包含订单级促销
          boolean containPromoOrderFlag = !(promoList == null || promoList.isEmpty());
          // 订单行级促销
          List<OrderPromotion> promoItemList = orderPromotionService.findByOrderNoAndLevel(orderNo,
                  CommonConst.OrderPromotion_Promolevel_Item.getCode());
          boolean containPromoItemFlag = !(promoItemList == null || promoItemList.isEmpty());
          // 不包含订单行级促销，且非 含订单级促销订单全部完成
          if (!containPromoItemFlag && !(allOrderFinished && containPromoOrderFlag)) {
              return true;
          }
          CommonOutputDTO output = null;
          boolean exceptionThrown = false;
          PromotionAddCouponDTO input = new PromotionAddCouponDTO();
          input.setMemberNo(orderMain.getMemberNo());
          List<String> promoIds = new ArrayList<String>(); // 所有的促销规则编号
          // 处理订单行级促销
          if (containPromoItemFlag) {
              for (OrderPromotion op : promoItemList) {
                  if (op.getPromoType().equals(PromotionType.ProductCoupon.getCode())) { // 单品送券
                      promoIds.add(op.getPromoNo());
                  }
              }
          }
          // 处理订单级促销
          if (allOrderFinished && containPromoOrderFlag) {
              for (OrderPromotion op : promoList) {
                  if (op.getPromoType().equals(PromotionType.TotalCoupon.getCode())) { // 订单送券
                      promoIds.add(op.getPromoNo());
                  }
              }
          }
          input.setPromotionId(promoIds);
          try {
              output = commonUtilService.jsonPostWithTrack(promo02, IntfSentConst.OMS_PROMO_ADD.getCode(), input,
                      CommonOutputDTO.class, null);
          } catch (Exception e) {
              logger.info("{}", e);
              exceptionThrown = true;
          }*/
          
          try {
              logger.info("订单完成调用促销返券返积分接口，单号："+orderNo);
              output = commonUtilService.jsonPostWithTrack(orderCreateServiceCommon.getPromo04(), IntfSentConst.OMS_PROMO_ADD.getCode(), input,
                      CommonOutputDTO.class, null);
          } catch (Exception e) {
              logger.info("{}", e);
              exceptionThrown = true;
          }
          if (output == null || exceptionThrown || output.getCode() == null
                  || CommonConstService.FAILED.equals(output.getCode())) {
              doNullIntfRetPromo(orderNoService.getDefaultOrderSubNoByOrderNo(orderMain.getOrderNo()),
                      OrderStatus.ORDER_ACCEPTED_PAID.getCode(), ErrConst.PROMOADD_FAILED.getCode(),
                      ErrConst.PROMOADD_FAILED.getDesc());
              return false;
          }
          return true;
      }

      @Override
      public boolean returnCouponForCancel(String orderNo) {
          OrderMain om = orderMainService.findByOrderNo(orderNo);
          String batchNum = om.getBatchNum();
          if (StringUtils.isBlank(batchNum)) {
              return true;
          }
          
          //boolean allOrderCancelled = isAllOrdersCancelled(batchNum);
          boolean allOrderCancelled = true;
          List<OrderMain> omList = orderMainService.findByField(OrderMain_.batchNum, batchNum);
          if (omList != null && !omList.isEmpty()) {
              for (OrderMain omTmp : omList) {
                  // 如果是正向单
                  if (CommonConst.OrderMain_BillType_Positive.getCodeLong() == omTmp.getBillType().longValue()) {
                      boolean cancelled = OrderStatus.ORDER_PAY_CANCELLED.getCode().equals(omTmp.getStatusTotal())
                              || OrderStatus.ORDER_PAID_CANCEL.getCode().equals(omTmp.getStatusTotal());
                      if (!cancelled) {
                          allOrderCancelled = false;
                          return false;
                      }
                  }
              }
          }
          
          if (!allOrderCancelled) {
              return true;
          }
          //update by 20141022 for 同一批次的所有订单都需调用促销的取消返券接口
          for(OrderMain omTmp : omList){
              CommonOutputDTO output = null;
              boolean exceptionThrown = false;
              try {
            	 //运营平台把请求发方式 更新为get 
                 // output = commonUtilService.jsonPostWithTrack(getPromo03(), IntfSentConst.OMS_PROMO_REMOVE.getCode(),
                 //         orderNoService.getDefaultOrderSubNoByOrderNo(omTmp.getOrderNo()), CommonOutputDTO.class, null);
            	  String ordersubNo = orderNoService.getDefaultOrderSubNoByOrderNo(omTmp.getOrderNo());

                 
            	  output = commonUtilService.jsonGetWithTrack(String.format(serviceCommon.getPromo03(), ordersubNo), IntfSentConst.OMS_PROMO_REMOVE.getCode(),CommonOutputDTO.class, null);
            		logger.info("==============={}"+JSONObject.toJSON(output));

                  output = commonUtilService.jsonGetWithTrack(String.format(orderCreateServiceCommon.getPromo03(), ordersubNo), IntfSentConst.OMS_PROMO_REMOVE.getCode(),CommonOutputDTO.class, null);

              } catch (Exception e) {
                  logger.info("{}", e);
                  exceptionThrown = true;
              }
              if (output == null || exceptionThrown || output.getCode() == null
                      || CommonConstService.FAILED.equals(output.getCode())) {
                  doNullIntfRetPromo(orderNoService.getDefaultOrderSubNoByOrderNo(omTmp.getOrderNo()), om.getStatusTotal(),
                          ErrConst.COUPON_RETURN.getCode(), ErrConst.COUPON_RETURN.getDesc());
              }
          }
          
          /*CommonOutputDTO output = null;
          boolean exceptionThrown = false;
          try {
              output = commonUtilService.jsonPostWithTrack(promo03, IntfSentConst.OMS_PROMO_REMOVE.getCode(),
                      orderNoService.getDefaultOrderSubNoByOrderNo(orderNo), CommonOutputDTO.class, null);
          } catch (Exception e) {
              logger.info("{}", e);
              exceptionThrown = true;
          }
          if (output == null || exceptionThrown || output.getCode() == null
                  || CommonConstService.FAILED.equals(output.getCode())) {
              doNullIntfRetPromo(orderNoService.getDefaultOrderSubNoByOrderNo(orderNo), om.getStatusTotal(),
                      ErrConst.COUPON_RETURN.getCode(), ErrConst.COUPON_RETURN.getDesc());
              return false;
          }*/
          return true;
      }
      
      
      @Override
      public boolean removeCouponForCancel(String orderNo) {
          OrderMain om = orderMainService.findByOrderNo(orderNo);
     
          
          boolean allOrderCancelled = true;
                  // 如果是正向单
                  if (CommonConst.OrderMain_BillType_Positive.getCodeLong() == om.getBillType().longValue()) {
                      boolean cancelled = OrderStatus.ORDER_PAY_CANCELLED.getCode().equals(om.getStatusTotal())
                              || OrderStatus.ORDER_PAID_CANCEL.getCode().equals(om.getStatusTotal());
                      if (!cancelled) {
                          allOrderCancelled = false;
                          return false;
                      }
                  }
              
          
          
          if (!allOrderCancelled) {
              return true;
          }
          //update by 2018/4/47 取消批次的概念 用于中台与定时任务 BY YUSL
              CommonOutputDTO output = null;
              boolean exceptionThrown = false;
              try {
            	  String ordersubNo = orderNoService.getDefaultOrderSubNoByOrderNo(om.getOrderNo());
                 
            	  output = commonUtilService.jsonGetWithTrack(String.format(serviceCommon.getPromo03(), ordersubNo), IntfSentConst.OMS_PROMO_REMOVE.getCode(),CommonOutputDTO.class, null);
            		logger.info("==============={}"+JSONObject.toJSON(output));
              } catch (Exception e) {
                  logger.info("{}", e);
                  exceptionThrown = true;
              }
              if (output == null || exceptionThrown || output.getCode() == null
                      || CommonConstService.FAILED.equals(output.getCode())) {
                  doNullIntfRetPromo(orderNoService.getDefaultOrderSubNoByOrderNo(om.getOrderNo()), om.getStatusTotal(),
                          ErrConst.COUPON_RETURN.getCode(), ErrConst.COUPON_RETURN.getDesc());
              }
          
          
          return true;
      }   
      @Override
      public boolean cancelOrderReturnPoint(String orderNo) {
    	   OrderMain om = orderMainService.findByOrderNo(orderNo);
           
           boolean allOrderCancelled = true;
                   // 如果是正向单
                   if (CommonConst.OrderMain_BillType_Positive.getCodeLong() == om.getBillType().longValue()) {
                       boolean cancelled = OrderStatus.ORDER_PAY_CANCELLED.getCode().equals(om.getStatusTotal())
                               || OrderStatus.ORDER_PAID_CANCEL.getCode().equals(om.getStatusTotal());
                       if (!cancelled) {
                           allOrderCancelled = false;
                           return false;
                       }
                   }
               
           
           
           if (!allOrderCancelled) {
               return true;
           }
    	  
           if(om.getTotalPoint()==null){
        	   return true;
        	   
           }
           try {
        	   pointsTransactionDetailHsService.updateMemberPointsDetail(om.getMemberNo(),om.getOrderNo(),"","0",om.getTotalPoint().intValue(),"1");
           } catch (Exception e) {
               logger.info("{}", e);
           }
           
           
		return false;
	}
      /**
       * 会员资源扣减：MY卡支付（大客户、黑名单查询）、积分支付
       * @param context
       * @return
       */
      @Deprecated
      public boolean memberResourceDeduct(ContextBtcOmsReceiveDTO context) {
          Map<String, OrderMain> omMap = context.getOmMap();
          //1、调用会员帐户扣减积分
          boolean jifenPayFlag = false;
          OmsMemberRefundDTO omDTO = new OmsMemberRefundDTO();
          
          ArrayList<OmsMemberRefundInnerDTO> memList = new ArrayList<OmsMemberRefundInnerDTO>();
          omDTO.setRefundDTOList(memList);
          omDTO.setOperator("sys");
          //2、MY卡
          MemberPayDTO memberPayDTO = new MemberPayDTO();
          List<MyCardDTO> mpList = new ArrayList<MyCardDTO>();
          for (OrderMain om : omMap.values()) {
              memberPayDTO.setMemberId(om.getMemberNo());//需在此处设置memberId
              List<OrderPay> opList = orderPayService.findByField(OrderPay_.orderNo, om.getOrderNo());
              for (OrderPay op : opList) {
                  //MY卡支付
                  if (PayMode.CARD.getCode().equals(op.getPayCode())) {
                      MyCardDTO myCardDTO = new MyCardDTO();
                      myCardDTO.setAmount(op.getPayAmount().doubleValue());
                      myCardDTO.setOrderSubNo(orderNoService.getDefaultOrderSubNoByOrderNo(om.getOrderNo()));
                      mpList.add(myCardDTO);
                  }
                  //积分支付
                  if(PayType.INTEGERAL_PAY.getId().equals(op.getPayCode())){
                      jifenPayFlag = true;
                      OmsMemberRefundInnerDTO inner = new OmsMemberRefundInnerDTO();
                      inner.setAdd(false);//操作类型
                      inner.setAccountType(CommonConstService.MEMPOINT);//账户类型:积分
                      inner.setAmount(op.getPayAmount());
                      memList.add(inner);
                  }
                  //支付的积分
                  if(PayMode.POINT.getCode().equals(op.getPayCode())){
                	  jifenPayFlag = true;
                  }
                  
                  
                  
              }
              if(jifenPayFlag){ //积分
                  omDTO.setMemberId(NumberUtils.toLong(om.getMemberNo())); 
                  omDTO.setOrderNo(om.getOrderNo());
              }
          }
          memberPayDTO.setCardList(mpList);
          
          //1、调用积分扣减接口
          if(jifenPayFlag){
              try {
                  OmsMemberRefundOutputDTO refundDTO = commonUtilService.jsonPostWithTrack(orderCreateServiceCommon.getMem02(), IntfSentConst.OMS_MEM_02.getCode(), omDTO, OmsMemberRefundOutputDTO.class, null);
                  if(refundDTO.getCode().equals(CommonConstService.FAILED)){
                      logger.info("下单调用积分扣减接口失败，订单号："+omDTO.getOrderNo());
                      return false;
                  }
              }catch(Exception e){
                  e.printStackTrace();
                  logger.info("下单调用积分扣减接口失败，订单号："+omDTO.getOrderNo()+"，异常："+e.getMessage());
              }
          }
          
          //2、MY卡接口返回结果
          CommonOutputDTO output = null; 
          boolean exceptionThrown = false;
          try {
              output = commonUtilService.jsonPostWithTrack(orderCreateServiceCommon.getMem03(), IntfSentConst.OMS_MEM_03.getCode(), memberPayDTO,
                      CommonOutputDTO.class, null);
          } catch (Exception e) {
              logger.info("{}", e);
              exceptionThrown = true;
          }
          if (output == null || exceptionThrown || output.getCode() == null
                  || output.getCode().equals(CommonConstService.FAILED)) {
              String msg = "null";
              if (output != null && output.getMsg() != null) {
                  msg = output.getMsg();
              }
              context.setMsg(CommonConstService.Mem03Prefix + msg);
              return false;
          } else {
              boolean priviledagedMember = output != null && output.getPriviledgedMember() != null
                      && output.getPriviledgedMember(); //大客户会员
              for (OrderMain om : omMap.values()) {
                  if (priviledagedMember) {
                      om.setIfPriviledgedMember(CommonConstService.BOOLEAN_TRUE);
                  } else {
                      om.setIfPriviledgedMember(CommonConstService.BOOLEAN_FALSE);
                  }
                  boolean blackListIncluded = output != null && output.getBlackListIncluded() != null
                          && output.getBlackListIncluded(); //黑名单会员
                  if (blackListIncluded) {
                      om.setIfBlackListMember(CommonConstService.BOOLEAN_TRUE);
                  } else {
                      om.setIfBlackListMember(CommonConstService.BOOLEAN_FALSE);
                  }
                  orderMainService.update(om);
              }
          }
          if (CommonConstService.SUCCESS.equals(output.getCode())) {
              return true;
          }
          return false;
      }

      /**
       * 库存锁定
       * 
       * @param orderMain
       * @return succeed true
       */
      public InventoryLockOutputDTO inventoryLock(String orderNo) {
          StockLockByOms rsInput = imsOmsTransService.queryInventoryLock(orderNo);// 构建锁库存的对象
          InventoryLockOutputDTO output = null;
          boolean exceptionThrown = false;
          try {
              output = commonUtilService.jsonPostWithTrack(orderCreateServiceCommon.getSim01(), IntfSentConst.OMS_SIM_LOCK.getCode(), rsInput,
                      InventoryLockOutputDTO.class, null);
          } catch (Exception e) {
              //logger.info("{}", e);
        	  e.printStackTrace();
              //output.setReturn_msg(String.format("库存锁定失败 -->[OrderCreateServiceImpl-->inventoryLockBatch-->url:{}]--[params:{}]--[reason:{}]",orderCreateServiceCommon.getSim01(),commonUtilService.ObjectTransJsonstr(rsInput),ExceptionUtil.stackTraceToString(e, 50)));
              logger.info("库存锁定失败 -->[OrderCreateServiceImpl-->inventoryLockBatch-->[url:{}]--[params:{}]--[reason:{}]",orderCreateServiceCommon.getSim01(),commonUtilService.ObjectTransJsonstr(rsInput),output.getReturn_msg());
              exceptionThrown = true;
          }

          if (output == null) {
              output = new InventoryLockOutputDTO();
              output.setReturn_status("-1");
              output.setReturn_msg(String.format("库存锁定失败 -->[OrderCreateServiceImpl-->inventoryLockBatch-->url:%s]--[params:%s]--[reason:%s]",orderCreateServiceCommon.getSim01(),commonUtilService.ObjectTransJsonstr(rsInput),"库存异常或者返回null"));
              logger.info("库存锁定失败 -->[OrderCreateServiceImpl-->inventoryLockBatch-->url:{}]--[params:{}]--[reason:{}]",orderCreateServiceCommon.getSim01(),commonUtilService.ObjectTransJsonstr(rsInput),"库存异常或者返回null");
              
          }
          if (exceptionThrown || output.getReturn_code() == null || Integer.valueOf(output.getReturn_code()) < 0) {
        	  output.setReturn_msg(String.format("库存锁定失败 -->[OrderCreateServiceImpl-->inventoryLockBatch-->url:%s]--[params:%s]--[reason:%s]",orderCreateServiceCommon.getSim01(),commonUtilService.ObjectTransJsonstr(rsInput),"库存异常或者返回null"));
              logger.info("库存锁定失败 -->[OrderCreateServiceImpl-->inventoryLockBatch-->url:{}]--[params:{}]--[reason:{}]",orderCreateServiceCommon.getSim01(),commonUtilService.ObjectTransJsonstr(rsInput),"库存异常或者返回null");
          }
          return output;
      }

      /**
       * 库存释放：单个订单
       * @param orderNo
       * @return InventoryLockOutputDTO
       */
      public InventoryLockOutputDTO inventoryUnLockByOrderNo(String orderNo){
          StockUnLockByOms rsInput = imsOmsTransService.queryInventoryUnLock(orderNo);
          // 解锁
          boolean exceptionThrown = false;
          InventoryLockOutputDTO output = null;
          try {
              output = commonUtilService.jsonPostWithTrack(orderCreateServiceCommon.getSim02(), IntfSentConst.OMS_SIM_UNLOCK.getCode(), rsInput,
                      InventoryLockOutputDTO.class, null);
          } catch (Exception e) {
              logger.info("{}", e);
              exceptionThrown = true;
          }
          if (output == null) {
              output = new InventoryLockOutputDTO();
              output.setReturn_status("-1");
              output.setReturn_msg("oms:库存解锁返回null");
          }
          if (exceptionThrown || output.getReturn_code() == null) {
              output.setReturn_status("-1");
              output.setReturn_msg("oms:库存异常");
          }
          return output;
      }
      
      /**
       * 库存释放(解锁)
       */
      public void inventoryUnLock(List<OrderMain> omList) {
          for (OrderMain om : omList) {
              StockUnLockByOms rsInput = imsOmsTransService.queryInventoryUnLock(om.getOrderNo());
              // 解锁
              boolean exceptionThrown = false;
              InventoryLockOutputDTO output = null;
              try {
                  output = commonUtilService.jsonPostWithTrack(orderCreateServiceCommon.getSim02(), IntfSentConst.OMS_SIM_UNLOCK.getCode(), rsInput,
                          InventoryLockOutputDTO.class, null);
              } catch (Exception e) {
                  logger.info("{}", e);
                  exceptionThrown = true;
                  logger.info(String.format("error:库存解锁异常-->[post:%s][params:%s][message:%s]", orderCreateServiceCommon.getSim02(),commonUtilService.ObjectTransJsonstr(rsInput),ExceptionUtil.stackTraceToString(e)));
              }
              if (exceptionThrown || output == null || !NumberUtils.isNumber(output.getReturn_code())) {
                  continue;
              }
          }
      }
      
      
      @Override
  	public void inventoryUnLock(List<OrderMain> omList, ContextBtcOmsReceiveDTO context) {
    	  String errorMessage = "";
    	  for (OrderMain om : omList) {
              StockUnLockByOms rsInput = imsOmsTransService.queryInventoryUnLock(om.getOrderNo());
              // 解锁
              boolean exceptionThrown = false;
              InventoryLockOutputDTO output = null;
              try {
                  output = commonUtilService.jsonPostWithTrack(orderCreateServiceCommon.getSim02(), IntfSentConst.OMS_SIM_UNLOCK.getCode(), rsInput,
                          InventoryLockOutputDTO.class, null);
              } catch (Exception e) {
                  logger.info("{}", e);
                  exceptionThrown = true;
                  logger.info(String.format("error:库存解锁异常-->[post:%s][params:%s][message:%s]", orderCreateServiceCommon.getSim02(),commonUtilService.ObjectTransJsonstr(rsInput),ExceptionUtil.stackTraceToString(e)));
                  errorMessage  = errorMessage + ExceptionUtil.stackTraceToString(e,10) + ",";
              }
              if (exceptionThrown || output == null || !NumberUtils.isNumber(output.getReturn_code())) {
                  continue;
              }
          }
    	  context.setMsg(errorMessage + context.getMsg());
  	}
      
      
      
      
      @Override
      public InventoryResendMsgOutputDTO inventoryResendMsg(String orderItemNo) {
          InventoryResendMsgDTO rsInput = imsOmsTransService.queryInventoryResend(orderItemNo);
          InventoryResendMsgOutputDTO output = null;
          try {
              output = commonUtilService.jsonPostWithTrack(orderCreateServiceCommon.getSim05(), IntfSentConst.OMS_SIM_RESEND.getCode(), rsInput,
                      InventoryResendMsgOutputDTO.class, null);
          } catch (Exception e) {
              logger.info("{}", e);
              if (output == null) {
                  output = new InventoryResendMsgOutputDTO();
              }
              output.setCode(CommonConstService.PROCESS_FAILED);
              output.setMessage(CommonConstService.INVENTORY_RESEND_FAILED);
              // exceptionThrown = true;
          }
          return output;
      }

      @Override
      public SupportResendOutputDTO supportResendMsg(String orderSubNo) {
          SupportResend rsInput = buildSupportResend(orderSubNo);
          SupportResendOutputDTO output = new SupportResendOutputDTO();
          if (rsInput == null) {
              output.setCode(CommonConstService.PROCESS_FAILED);
              output.setMessage("接收手机号码为空");
              return output;
          }
          try {
              output = commonUtilService.jsonPostWithTrack(orderCreateServiceCommon.getSup01(), IntfSentConst.OMS_SUP_RESEND.getCode(), rsInput,
                      SupportResendOutputDTO.class, null);
          } catch (Exception e) {
              logger.info("{}", e);
              output.setCode(CommonConstService.PROCESS_FAILED);
              output.setMessage(CommonConstService.SUPPORT_RESEND_FAILED);
              return output;
          }
          if (!CommonConstService.SUCCEED_LowerCase.equals(output.getCode())) {
              output.setCode(CommonConstService.PROCESS_FAILED);
              output.setMessage(output.getMessage());
          }
          return output;
      }

      @Override
      public SupportResendOutputDTO supportResend24Hours(String orderSubNo) {
          SupportResend rsInput = buildSupportResend24Hours(orderSubNo);
          SupportResendOutputDTO output = null;
          try {
              output = commonUtilService.jsonPostWithTrack(orderCreateServiceCommon.getSup01(), IntfSentConst.OMS_SUP_RESEND_24HOURS.getCode(),
                      rsInput, SupportResendOutputDTO.class, null);
          } catch (Exception e) {
              logger.info("{}", e);
              if (output == null) {
                  output = new SupportResendOutputDTO();
              }
              output.setCode(CommonConstService.PROCESS_FAILED);
              output.setMessage(CommonConstService.SUPPORT_RESEND_FAILED);
              return output;
          }
          if (output == null || !CommonConstService.SUCCEED_LowerCase.equals(output.getCode())) {
              if (output == null) {
                  output = new SupportResendOutputDTO();
              }
              output.setCode(CommonConstService.PROCESS_FAILED);
              output.setMessage(output.getMessage());
          }
          return output;
      }

      /**
       * 短信发送
       * @param mobilePhoneNum
       * @param intfCode
       * @param smsModeCode
       * @return
       */
      public SupportResendOutputDTO supportSendSms(String mobilePhoneNum,String intfCode, String smsModeCode){
          SupportResendOutputDTO output = new SupportResendOutputDTO();
          if (StringUtils.isEmpty(mobilePhoneNum)) {
              output.setCode(CommonConstService.PROCESS_FAILED);
              output.setMessage("手机号码为空");
              return output;
          }
          Map<String, Object> data = new HashMap<String, Object>();
          data.put("recipients", mobilePhoneNum);// 手机号码
          SupportResend rsInput = new SupportResend();
          rsInput.setCode(smsModeCode);// 模板code
          rsInput.setType("sms");// 短信
          rsInput.setData(data);
          //发送
          try {
              output = commonUtilService.jsonPostWithTrack(orderCreateServiceCommon.getSup01(), intfCode, rsInput,SupportResendOutputDTO.class, null);
          } catch (Exception e) {
              logger.info("{}", e);
              if (output == null) {
                  output = new SupportResendOutputDTO();
              }
              output.setCode(CommonConstService.PROCESS_FAILED);
              output.setMessage(CommonConstService.SUPPORT_RESEND_FAILED);
              return output;
          }
          if (output == null || !CommonConstService.SUCCEED_LowerCase.equals(output.getCode())) {
              if (output == null) {
                  output = new SupportResendOutputDTO();
              }
              output.setCode(CommonConstService.PROCESS_FAILED);
              output.setMessage(output.getMessage());
          }
          return output;
      }
      
      
      @Override
      public SupportResendOutputDTO supportPostFeeRet(String orderSubNo) {
          SupportResend rsInput = buildSupportPostFeeRet(orderSubNo);
          SupportResendOutputDTO output = new SupportResendOutputDTO();
          if (rsInput == null) {
              output.setCode(CommonConstService.PROCESS_FAILED);
              output.setMessage("邮件接收人为空");
              return output;
          }
          try {
              output = commonUtilService.jsonPostWithTrack(orderCreateServiceCommon.getSup01(), IntfSentConst.CM_OMS_POST_FEE_RET.getCode(), rsInput,
                      SupportResendOutputDTO.class, null);
          } catch (Exception e) {
              logger.info("{}", e);
              if (output == null) {
                  output = new SupportResendOutputDTO();
              }
              output.setCode(CommonConstService.PROCESS_FAILED);
              output.setMessage(CommonConstService.SUPPORT_RESEND_FAILED);
              return output;
          }
          if (output == null || !CommonConstService.SUCCEED_LowerCase.equals(output.getCode())) {
              if (output == null) {
                  output = new SupportResendOutputDTO();
              }
              output.setCode(CommonConstService.PROCESS_FAILED);
              output.setMessage(output.getMessage());
          }
          return output;
      }

      private SupportResend buildSupportPostFeeRet(String orderSubNoR) {
          OrderSub os = orderSubService.get(OrderSub_.orderSubNo, orderSubNoR);
          if (os == null || os.getMobPhoneNum() == null) {// 空手机号
              return null;
          }
          Map<String, Object> data = new HashMap<String, Object>();
          data.put("orderSubNo", orderSubNoR);// 子订单号
          data.put("recipients", os.getMobPhoneNum());// 手机号码
          SupportResend so = new SupportResend();
          so.setCode("CM-OMS-POST-FEE-RET");// 模板code，
          so.setType("sms");// email邮件
          so.setData(data);
          return so;
      }

      @Override
      public SupportResendOutputDTO supportSendEmail(String orderNo) {
          OrderMain om = orderMainService.findByOrderNo(orderNo);
          String orderSubNo = orderNoService.getDefaultOrderSubNoByOrderNo(orderNo);
          SupportResend rsInput = orderCreateTrans.querySupportSendEmail(orderSubNo);
          SupportResendOutputDTO output = new SupportResendOutputDTO();
          if (rsInput == null) {
              output.setCode(CommonConstService.PROCESS_FAILED);
              output.setMessage("邮件接收人为空");
              return output;
          }
          try {
              output = commonUtilService.jsonPostWithTrack(orderCreateServiceCommon.getSup01(), IntfSentConst.OMS_SUP_SEND_EMAIL.getCode(), rsInput,
                      SupportResendOutputDTO.class, null);
          } catch (Exception e) {
              logger.info("{}", e);
              output.setCode(CommonConstService.PROCESS_FAILED);
              output.setMessage(CommonConstService.SUPPORT_RESEND_FAILED);
              return output;
          }
          if (!CommonConstService.SUCCEED_LowerCase.equals(output.getCode())) {
              output.setCode(CommonConstService.PROCESS_FAILED);
              output.setMessage(output.getMessage());
          }
          return output;
      }

      private SupportResend buildSupportResend(String orderSubNo) {
          OrderSub os = orderSubService.get(OrderSub_.orderSubNo, orderSubNo);
          if (os == null || os.getMobPhoneNum() == null) {// 空手机号
              return null;
          }
          Map<String, Object> date = new HashMap<String, Object>();
          date.put("checkCode", os.getCheckCode());// 验证码
          date.put("recipients", os.getMobPhoneNum());// 手机号码

          SupportResend so = new SupportResend();
          so.setCode("CM-BBC-ZITI");// 模板code，
          so.setType("sms");// email邮件
          so.setData(date);
          return so;
      }

      private SupportResend buildSupportResend24Hours(String orderSubNo) {
          OrderSub os = orderSubService.get(OrderSub_.orderSubNo, orderSubNo);
          Map<String, Object> date = new HashMap<String, Object>();
          date.put("orderSubNo", orderSubNo);// 子订单号
          date.put("recipients", os.getMobPhoneNum());// 手机号码

          SupportResend so = new SupportResend();
          so.setCode("CM-OMS-ONLINE_PAY24");// 模板code，
          so.setType("sms");// email邮件
          so.setData(date);
          return so;
      }

      private boolean callLogistic(String orderNo) {
          OrderMain om = orderMainService.findByOrderNo(orderNo);
          List<OrderSub> osList = om.getOrderSubs();
          Date ot = om.getOrderTime();
          String orderTimeStr = ot == null ? "" : commonUtilService.formatDayToHours().format(ot);
          TransportCompParam tcParam = new TransportCompParam();
          String orderSubNo = null;
          for (OrderSub os : osList) {
              tcParam.setOrderHour(orderTimeStr);
              orderSubNo = os.getOrderSubNo();
              tcParam.setOrderSubNo(orderSubNo);

              // BigDecimal totalWeight = new BigDecimal(0);
              // List<OrderItem> oiList = os.getOrderItems();
              // for(OrderItem oi:oiList){
              // BigDecimal singleWeight = oi.getWeight() == null ? new BigDecimal(0) : oi.getWeight();
              // totalWeight = totalWeight.add(singleWeight);
              // }
              tcParam.setWeight(commonUtilService.bigDecimal2String(om.getWeight()));
              // 虚拟订单不选择物流商
              if (CommonConst.OrderSub_Distribute_Type7.getCode().equals(os.getDistributeType())) {
                  break;
              }
              // 商家自配送的订单不选择物流商
              if (CommonConst.OrderSub_Distribute_Type0.getCode().equals(os.getDistributeType())) {
                  break;
              }
              String currentStatus = orderStatusService.getPreviousOrderStatus(om, os,
                      OrderStatusType.Order_Status_Positive.getCode());
              if (os.getDeliveryMerchantNo() != null && os.getDeliveryMerchantName() != null
                      && OrderStatus.ORDER_AUDITING.getCode().equals(currentStatus)) {
                  continue;
              }
              tcParam.setStorageId(os.getStoreNo());
              tcParam.setAreaId(os.getAddressCode());
              tcParam.setCatagoryId(os.getShipCat());
              tcParam.setMechantId(om.getMerchantNo());// 商家id
              tcParam.setDeliverTypeCode(os.getDistributeType());// 正向 ：前台页面选，天虹配送, 门店自提等等 .逆向 ：上门取货，客户寄回，门店代退

              // 非货到付款，写在线支付id
              String paymentModeId = PayMode.ONLINE.getCode().toString();
              if (CommonConstService.BOOLEAN_TRUE.equals(om.getIfPayOnArrival())) {
                  List<OrderPayMode> opmList = orderPayModeService.findByField(OrderPayMode_.orderNo, om.getOrderNo());
                  if (opmList != null && !opmList.isEmpty()) {
                      OrderPayMode opmMode = opmList.get(0);
                      paymentModeId = opmMode.getPayModeCode().toString();
                  }
              }
              tcParam.setPaymentModeId(paymentModeId);
              tcParam.setSelfTakePointId(os.getSelfFetchAddress());
              LogisticsDTO rsData = commonUtilService.jsonPostWithTrack(orderCreateServiceCommon.getLogistics01(),
                      IntfSentConst.OMS_LOGISTICS_01.getCode(), tcParam, LogisticsDTO.class, null);
              if (rsData == null || rsData.getTransportComp() == null
                      || CommonConstService.FAILED.equals(rsData.getStatus())) {
                  if (null != rsData) {
                      logger.error("物流商选择接口异常：{}", rsData.getMessage());
                  }

                  doNullIntfRetProcess(orderNoService.getDefaultOrderSubNoByOrderNo(om.getOrderNo()));
                  return false;
              }
              TransportComp tc = rsData.getTransportComp();
              os.setDeliveryMerchantNo(commonUtilService.Long2Str(tc.getId()));
              os.setDeliveryMerchantName(tc.getName());
              orderSubService.update(os);
          }
          if (OrderStatus.ORDER_PROCESS_FAILED.getCode().equals(om.getStatusTotal())) {
              orderStatusService.saveProcess(orderSubNo, OrderStatusAction.S012120, "", null, null);
          }
          orderStatusService.saveProcess(orderSubNo, OrderStatusAction.S012040, null, null, null);
          return true;
      }

      /** 从主订单中取出所有的组合商品行，将组合明细写入组合关联表. 如果组合商品已经写入，后面再次调用不能改变 **/
      private boolean buildCombineProduct(String orderNo) {
          logger.info("查询组合商品,{}", orderNo);
          List<OrderItem> itemList = orderItemService.findByField(OrderItem_.orderNo, orderNo);
          // 组合商品编码集合
          ArrayList<String> input = new ArrayList<String>();
          // 组合商品编码与订单行对应
          Map<String, OrderItem> codeItemMap = new HashMap<String, OrderItem>();
          for (OrderItem oi : itemList) {
              if (OrderMainConst.OrderItem_OrderItemClass_SUITE.getCode().equals(oi.getOrderItemClass())) {
                  // 组合商品信息已包含
                  List<OrderCombineRelation> combineList = orderCombineRelationService.findByOrderItemNoAndCombineNo(
                          oi.getOrderItemNo(), oi.getCommodityCode());
                  if (combineList != null && !combineList.isEmpty()) {
                      continue;
                  } else {
                      input.add(oi.getCommodityCode());
                      codeItemMap.put(oi.getCommodityCode(), oi);
                  }
              }
          }
          // 不含组合商品
          if (codeItemMap.isEmpty()) {
              return true;
          }

          CombineProductDTOList output = commonUtilService.jsonPostWithTrack(orderCreateServiceCommon.getProduct01(),
                  IntfSentConst.OMS_PRODUCT_COMBINE.getCode(), input, CombineProductDTOList.class, null);
          if (output == null) {
              logger.info("组合商品查询失败,{}", orderNo);
              doNullIntfRetProcess(orderNoService.getDefaultOrderSubNoByOrderNo(orderNo));
              return false;
          }
          Map<String, List<CombineProductDTO>> combineMapList = output.getDtoList();
          orderCombineRelationService.saveBatch(combineMapList, codeItemMap);
          logger.info("更新组合商品完成,{}", orderNo);
          return true;
      }
      
      //写色码款商品的色码款属性
      public void buildSemakuanProduct(String orderNo){
          if(StringUtils.isBlank(orderNo)){
              return;
          }
          logger.info("查询组合商品,{}", orderNo);
          List<OrderItem> itemList = orderItemService.findByField(OrderItem_.orderNo, orderNo);
          ProductProperty proty = null;
          for(OrderItem item:itemList){
              try{
                  if(CommonConst.OrderItem_ProductPropertyFlag_Yes.getCodeLong()==item.getProductPropertyFlag().longValue()){
                      CommodityStockInfoDTO skuInfo = returnChangeOrderService.getSkuInfo(item.getSkuNo());
                      if(null==skuInfo || null==skuInfo.getColorSizeInfos()){
                          continue;
                      }
                      for(ColorSizeInfo colorInfo:skuInfo.getColorSizeInfos()){
                          proty = new ProductProperty();
                          proty.setIdOrder(item.getIdOrder());
                          proty.setIdOrderItem(item.getId());
                          proty.setOrderNo(orderNo);
                          proty.setOrderItemNo(item.getOrderItemNo());
                          proty.setSkuNo(item.getSkuNo());
                          proty.setPropertyName(colorInfo.getColorSizeName());
                          proty.setPropertyValue(colorInfo.getColorSizeValueName());
                          proty.setDateCreated(new Date());
                          productPropertyService.save(proty);
                      }
                  }
              }catch(Exception e){
                  e.printStackTrace();
                  logger.info("处理订单写色码款属性异常，orderItemNo："+item.getOrderItemNo()+"，异常信息："+e.getMessage());
              }
          }
      }

      private void doNullIntfRetProcess(String orderSubNo) {
          orderStatusService.saveProcess(orderSubNo, OrderStatusAction.S012021, null, null, null);
          OrdiErrOptLog oeol = new OrdiErrOptLog();
          oeol.setErrorCode(ErrConst.PROCESS_FAILED.getCode());
          oeol.setOrderSubNo(orderSubNo);
          oeol.setOrderStatus(OrderStatus.ORDER_PROCESS_FAILED.getCode());
          oeol.setErrorType(ErrConst.PROCESS_FAILED.getDesc());
          oeol.setOperateTime(new Date());
          ordiErrOptLogService.save(oeol);
      }

      private void doNullIntfRetPromo(String orderSubNo, String currentOrderStatus, String ErrorCode, String errorType) {
          OrdiErrOptLog oeol = new OrdiErrOptLog();
          oeol.setErrorCode(ErrorCode);
          oeol.setOrderSubNo(orderSubNo);
          oeol.setOrderStatus(currentOrderStatus);
          oeol.setErrorType(errorType);
          oeol.setOperateTime(new Date());
          ordiErrOptLogService.save(oeol);
      }

      @Override
      public boolean isAllOrdersFinished(String batchNum) {
          List<OrderMain> omList = orderMainService.findByField(OrderMain_.batchNum, batchNum);
          if (omList != null && !omList.isEmpty()) {
              for (OrderMain om : omList) {
                  if (CommonConst.OrderMain_OrderCategory_Sale.getCode().equals(om.getOrderCategory())
                          && !OrderStatus.ORDER_ACCEPTED_PAID.getCode().equals(om.getStatusTotal())) {
                      return false;
                  }
              }
          }
          return true;
      }

      @Override
      public boolean isAllOrdersCancelled(String batchNum) {
          List<OrderMain> omList = orderMainService.findByField(OrderMain_.batchNum, batchNum);
          if (omList != null && !omList.isEmpty()) {
              for (OrderMain om : omList) {
                  // 如果是正向单
                  if (CommonConst.OrderMain_BillType_Positive.getCodeLong() == om.getBillType().longValue()) {
                      boolean cancelled = OrderStatus.ORDER_PAY_CANCELLED.getCode().equals(om.getStatusTotal())
                              || OrderStatus.ORDER_PAID_CANCEL.getCode().equals(om.getStatusTotal());
                      if (!cancelled) {
                          return false;
                      }
                  }
              }
          }
          return true;
      }

      @Override
      public String bbcOperateToOms(String orderSubNo, String operateCode, String sys) {
          IntfReceived ir = new IntfReceived();
          ir.setCreateTime(new Date());
          ir.setIntfCode(IntfReceiveConst.BBC_Operate.getCode());
          ir.setOrderSubNo(orderSubNo);
          ir.setMsg("orderSubNo:" + orderSubNo + "; operateCode:" + operateCode + ", sys:" + sys);
          intfReceivedService.save(ir);
          OrderMain om = orderMainService.findByOrderNo(orderNoService.getOrderNoBySubNo(orderSubNo));
          if (om == null) {
              return CommonConstService.FAILED;
          }
          boolean isPayOnArrival = (om.getIfPayOnArrival() != null && om.getIfPayOnArrival().intValue() == 1);

          if (bbcCodeLogi.equals(operateCode)) {
              // 门店物流接收完成,
              // 0728, 已揽收状态可能没到，此时允许bbc直接从0605已出库经过0606走到0607
              String currLogiStatus = om.getOrderSubs().get(0).getLogisticsStatus();
              // 当前物流状态仍然没有揽收0605，亦让进入门店已收货。因为tms给揽收结果的时间依赖于第三方物流。
              if (OrderStatus.Order_LogisticsStatus_Collect.getCode().equals(currLogiStatus)) {
                  orderStatusService.saveProcess(orderSubNo, OrderStatusAction.S060506, null, null, null);
              }
              if (!orderStatusService.saveProcess(orderSubNo, OrderStatusAction.S060607, null, null, null)) {
                  // 更改状态失败
                  ir.setSucceed(CommonConstService.BOOLEAN_FALSE);
                  intfReceivedService.update(ir);
                  return CommonConstService.FAILED;
              }
              ir.setSucceed(CommonConstService.BOOLEAN_TRUE);
              intfReceivedService.update(ir);
          } else if (bbcCodeMsg.equals(operateCode)) {
              // 短信已接收，已收已付
              OrderStatusAction targetAction = isPayOnArrival ? OrderStatusAction.S017180 : OrderStatusAction.S017080;
              if (!orderStatusService.saveProcess(orderSubNo, targetAction, null, null, null)) {
                  // 更改状态失败
                  ir.setSucceed(CommonConstService.BOOLEAN_FALSE);
                  intfReceivedService.update(ir);
                  return CommonConstService.FAILED;
              }
              ;
              // 状态到0180 同步支付明细 ，订单明细
              if (OrderStatusAction.S017080 == targetAction || OrderStatusAction.S017180 == targetAction) {
                  promoResourceAdd(om.getBatchNum(), om.getOrderNo());
                  om.setFinishTime(new Date());
                  orderMainService.update(om);
                  // 订单明细
                  orderCreateTrans.saveOrderToR3(om, null);
                  /*
                   * //("I-OMS-R3-01","订单明细"), orderStatusSyncLogService.saveAndcreate(om, null,
                   * CommonConst.OrderStatusSyncLog_SyncScene_OrderItem.getCode()); //"I-OMS-R3-02","支付明细"
                   * orderStatusSyncLogService.saveAndcreate(om, null,
                   * CommonConst.OrderStatusSyncLog_SyncScene_Pay.getCode());
                   */

              }
              ir.setSucceed(CommonConstService.BOOLEAN_TRUE);
              intfReceivedService.update(ir);
          } else if (bbcRefuse.equals(operateCode)) {
              // 拒收
              OrderStatusAction targetAction = isPayOnArrival ? OrderStatusAction.S017181 : OrderStatusAction.S017081;
              if (!orderStatusService.saveProcess(orderSubNo, targetAction, null, null, null)) {
                  // 更改状态失败
                  ir.setSucceed(CommonConstService.BOOLEAN_FALSE);
                  intfReceivedService.update(ir);
                  return CommonConstService.FAILED;
              }
              ;
              ir.setSucceed(CommonConstService.BOOLEAN_TRUE);
              intfReceivedService.update(ir);
          }
          return CommonConstService.OK;
      }

      @Override
      public String bbcLogisticsVerified(BBCLogiDTO bbcLogiDTO) {
          IntfReceived ir = new IntfReceived();
          ir.setCreateTime(new Date());
          ir.setIntfCode(IntfReceiveConst.BBC_Logistics.getCode());
          ObjectMapper omapper = new ObjectMapper();
          String omStr;
          try {
              omStr = omapper.writeValueAsString(bbcLogiDTO);
              ir.setMsg(omStr);
              intfReceivedService.save(ir);
          } catch (JsonProcessingException e) {
              logger.info("{}", e);
          }
          // 校验数据
          String msg = CommonUtilService.createOrderValidate(bbcLogiDTO);
          if (!CommonConstService.OK.equals(msg)) {
              return msg;
          }
          // 更新物流商
          String orderSubNo = bbcLogiDTO.getOrderSubNo();
          OrderSub os = orderSubService.getByField(OrderSub_.orderSubNo, orderSubNo);
          OrderMain om = orderMainService.findByOrderNo(orderNoService.getOrderNoBySubNo(orderSubNo));
          boolean isPayOnArrival = (om != null && om.getIfPayOnArrival() != null && om.getIfPayOnArrival().intValue() == 1);

          BeanUtils.copyProperties(bbcLogiDTO, os);
          orderSubService.update(os);
          OrderStatusAction targetAction = isPayOnArrival ? OrderStatusAction.S016071 : OrderStatusAction.S016070;
          orderStatusService.saveProcess(orderSubNo, targetAction, null, null, null);
          return CommonConstService.OK;
      }

      @Override
      public ResultDTO handlerOnlineOrderStatusPay(OrderSub orderSub) {
          ResultDTO resultDTO = new ResultDTO();

          String orderSubNo = orderSub.getOrderSubNo();

          // 订单状态转换
          Boolean flag = orderStatusService.saveProcess(orderSubNo, OrderStatusAction.S013020, null, null, null);

          if (!flag) {
              resultDTO.setResultMessage("支付中 --> 处理中 状态扭转失败！");
              return resultDTO;
          }

          OrderMain om = this.orderMainService.getByOrderSubNo(orderSubNo);
          try {
              this.callSingleProcess(om.getOrderNo());
          } catch (Exception e) {
              e.printStackTrace();
              this.logger.error("{}", e.getMessage());
              resultDTO.setResultMessage("处理btc订单失败！");
              return resultDTO;
          }
          // 预付款待同步R3
          try {
              orderStatusSyncLogService
                      .saveAndcreate(om, null, CommonConst.OrderStatusSyncLog_SyncScene_PrePay.getCode());
          } catch (Exception e) {
              e.printStackTrace();
              this.logger.error("{}", e.getMessage());
              resultDTO.setResultMessage("同步R3失败！");
              return resultDTO;
          }

          resultDTO.setResultMessage(CommonConst.Common_Result_OK.getCode());
          return resultDTO;
      }

      @Override
      public String deleteErr(Long id) {
          String checkRet = dealErrAllowed(id);
          if (CommonConstService.OK.equals(checkRet)) {
              return dealErrConst5;
          } else {
              ordiErrOptLogService.delete(id);
              return dealErrConst6;
          }
      }

      private String dealErrAllowed(Long id) {
          OrdiErrOptLog oeol = ordiErrOptLogService.get(id);
          if (oeol == null) {
              return dealErrConst1;
          }
          String orderSubNo = oeol.getOrderSubNo();
          if (orderSubNo == null) {
              return dealErrConst2;
          }
          String code = oeol.getErrorCode();
          if (ErrConst.SaleAfterOrder_ToWMS.getCode().equals(code)
                  || ErrConst.SaleAfterOrder_ToWMS_Cancel.getCode().equals(code)
                  || ErrConst.SaleAfterOrder_Cancel_ReturnIntegral.getCode().equals(code)
                  || ErrConst.CancelOrder_ReturnMyCard.getCode().equals(code)
                  || ErrConst.COUPON_RETURN.getCode().equals(code)) {
              // 此部分跟当前状态无关

          } else {
              String status = oeol.getOrderStatus();
              if (StringUtils.isBlank(status)) {
                  return dealErrConst3;
              }
              String orderNo = orderNoService.getOrderNoBySubNo(orderSubNo);
              OrderMain om = orderMainService.findByOrderNo(orderNo);
              if (!status.equals(om.getStatusTotal())) {
                  return String.format(dealErrConst4, status, om.getStatusTotal());
              }
          }
          return CommonConstService.OK;
      }

      @Override
      public String dealErr(Long id) {
          String checkRet = dealErrAllowed(id);
          if (!CommonConstService.OK.equals(checkRet)) {
              ordiErrOptLogService.delete(id);
              return checkRet;
          }
          OrdiErrOptLog oeol = ordiErrOptLogService.get(id);
          String orderSubNo = oeol.getOrderSubNo();
          String orderNo = orderNoService.getOrderNoBySubNo(orderSubNo);
          OrderSub os = orderSubService.getByOrderSubNo(orderSubNo);
          OrderMain om = orderMainService.findByOrderNo(orderNo);
          return ordiErrResumerVisitor.visit(id, os, om, oeol.getErrorCode());
      }
      
  	@Override
  	public void updateDeliveryByOrderSubNo(OrderSub orderSub) throws Exception {
  		OrderSub oldOrderSub = orderSubService.getByOrderSubNo(orderSub.getOrderSubNo());
  		if (null != oldOrderSub) {
  			OrderOperateLog orderOperateLog = new OrderOperateLog();
  			Date now = new Date();
  			orderOperateLog.setDateCreated(now);
  			orderOperateLog.setDateUpdated(now);
  			orderOperateLog.setOrderSubNo(oldOrderSub.getOrderSubNo());
  			orderOperateLog.setContent(UPDATE_DELIVERY_CONTENT);
  			orderOperateLog.setUpdatedBy(userUtil.getLoginUserRealName());
  			orderOperateLog.setCreatedBy(userUtil.getLoginUserRealName());
  			orderOperateLog.setOperator(userUtil.getLoginUserRealName());
  			orderOperateLog.setOldData(oldOrderSub.getDeliveryMerchantNo());
  			orderOperateLog.setNewData(orderSub.getDeliveryMerchantNo());
  			orderOperateLog.setIP("");
  			
  			oldOrderSub.setUpdatedBy(userUtil.getLoginUserRealName());
  			oldOrderSub.setDateUpdated(new Date());
  			oldOrderSub.setDeliveryMerchantNo(orderSub.getDeliveryMerchantNo());
  			oldOrderSub.setDeliveryMerchantName(orderSub.getDeliveryMerchantName());
  			orderSubService.update(oldOrderSub);
  			
  			orderOperateLogService.save(orderOperateLog);
  		}
  	}
  	
  	/**
  	 * 钩子
  	 */
  	public void hook(){}
  	
  	@Override
	public BtcOmsReceiveOrderDTO conifigDefaultValueBefore(BtcOmsReceiveOrderDTO t,BtcOmsReceiveOrderOutputDTO output) {
		// 1、设置默认仓库编码 --线上电商仓 -8010
		String defaultWarehouseCode = warehouseService.getWarehouseSourceByDefaultCode("");
		// 2、设置默认的发货物流公司
		List<OptionBean> ob = optionService.getOptionByCode(logisticsCode);
		String defaultlogisticsCode = ob.get(0).getCode();
		// String defaultlogisticsName = ob.get(0).getName();
		// 3、设置： 1）、仓库编码设置在订单行，2）物流公司编码在suborder设置
		List<OrderMainDTO> oms = t.getOmDTO();
		logger.info("=====================================conifigDefaultValue--start==================================================");
		for (OrderMainDTO om : oms) {
			//设置订单日期
			settingOrderTime(om);
			//线上和线下的日结日期
			settingMerchantBalanceDate(om);
			//线上和线下的默认仓库和默认物流商设置
			settingDafaultWareHouseCodeAndLogisticsCode(t, defaultWarehouseCode, defaultlogisticsCode, om);
			//获取业绩归属门店 现阶段默认此参数可以从前端获得,如果获取不到 通过此接口更细
			settingPerformStoreCode(om,output);
			//设置销售门店code信息
			settingSaleStoreInfo(om);
			//设置发货门店信息
			settingshipStoreInfo(om);
		}
		logger.info("=====================================conifigDefaultValue--end==================================================");
		return t;
	}
  	
  	
  	
    //设置发货门店信息
	private void settingshipStoreInfo(OrderMainDTO om) {
		//调用门店查询方法 待门店开发完成补上
		String shipCompanyCode = om.getShipStoreCode();
		om.setShipCompanyCode(shipCompanyCode);
		logger.info("conifigDefaultValue-->设置订单发货门店code[订单来源:{}]--> [发货门店:{}]",om.getOrderSource(),om.getShipCompanyCode());
	}
	//设置销售门店code信息
	private void settingSaleStoreInfo(OrderMainDTO om) {
		//调用门店查询方法 待门店开发完成补上
		String saleCompanyCode = om.getSaleStoreCode();
		om.setSaleCompanyCode(saleCompanyCode);
		logger.info("conifigDefaultValue-->设置订单销售门店code[订单来源:{}]--> [销售门店:{}]",om.getOrderSource(),om.getSaleCompanyCode());
	}

	private void settingDafaultWareHouseCodeAndLogisticsCode(BtcOmsReceiveOrderDTO t, String defaultWarehouseCode,
			String defaultlogisticsCode, OrderMainDTO om) {
		//ls 线下订单
		if(isLs(om.getOrderSource())){
			offlineSetting(defaultWarehouseCode, defaultlogisticsCode, om);
		}
		//导购和wx
		if(isWX(om.getOrderSource())|| isDG(om.getOrderSource())){
			olineSetting(defaultWarehouseCode, defaultlogisticsCode, om);
		}
	}

	private void olineSetting(String defaultWarehouseCode, String defaultlogisticsCode, OrderMainDTO om) {
		//配送方式
		String distributeType = getDistributeType(om);
		if(StringUtils.isEmpty(om.getNeedInvoice())){
			//设置成不需要发票
			om.setNeedInvoice(OrderMainConst.ORDERINVOICE_INVOICETYPE_NONEED_NO.getCode());
		};  
		
		for (OrderSubDTO os : om.getOsDTOs()) {
			//设置物流公司编码
			os.setDeliveryMerchantNo(defaultlogisticsCode);
			logger.info("conifigDefaultValue-->设置订单销默认物流公司[订单来源:{}]--> [流公司默认编码:{}::订单线上发货的设置物流公司默认编码]",om.getOrderSource(),defaultlogisticsCode);
			for (OrderItemDTO item : os.getOiDTOs()) {
				item.setWarehouseNo(defaultWarehouseCode);
			}
			logger.info("conifigDefaultValue-->设置订单默认发货的仓库地址[订单来源:{}]-->[配送方式：{}::订单线上发货的仓库地址是默认的仓库][仓库code:{}]",om.getOrderSource(),distributeType,defaultWarehouseCode);
		}
	}

	private void offlineSetting(String defaultWarehouseCode, String defaultlogisticsCode, OrderMainDTO om) {
		if (StringUtils.isEmpty(om.getNeedInvoice())) {
			// 设置成不需要发票
			om.setNeedInvoice(OrderMainConst.ORDERINVOICE_INVOICETYPE_NONEED_NO.getCode());
		}
		//配送方式
		String distributeType = getDistributeType(om);
		for (OrderSubDTO os : om.getOsDTOs()) {
			// 需要判断1、线下下单 ，线下发货，2、线下订单线下发货的仓库地址是门店code 3、结算日期不是订单创建日期，是post上结算日期
			if (OrderMainConstClient.OrderSub_DistributeType_Self_Store.getCode().equals(distributeType)) {
				// 线下订单线下发货的设置物流公司没有 设置为null
				os.setDeliveryMerchantNo("");
				logger.info("conifigDefaultValue-->设置订单销默认物流公司[订单来源:{}]--> [ 订单线下发货的设置物流公司没有 设置为null]",om.getOrderSource());
				for (OrderItemDTO item : os.getOiDTOs()) {
					// 线下订单线下发货的仓库地址是门店code
					item.setWarehouseNo(om.getSaleStoreCode());
				}
				logger.info("conifigDefaultValue-->设置订单默认发货的仓库地址[订单来源:{}]-->[配送方式：{}::订单线下发货的仓库地址是销售门店code][仓库code:{}]",om.getOrderSource(),distributeType,om.getSaleStoreCode());
			}
			// 2、线下下单，线上发货
			if (OrderMainConstClient.OrderSub_DistributeType_Assign_Store.getCode().equals(distributeType)) {
				// 线下订单线上发货的设置物流公司默认编码
				os.setDeliveryMerchantNo(defaultlogisticsCode);
				logger.info("conifigDefaultValue-->设置订单销默认物流公司[订单来源:{}]--> [流公司默认编码:{}::订单线上发货的设置物流公司默认编码]",om.getOrderSource(),defaultlogisticsCode);
				for (OrderItemDTO item : os.getOiDTOs()) {
					// 线下订单线上发货的仓库地址是默认的仓库
					item.setWarehouseNo(defaultWarehouseCode);
				}
				logger.info("conifigDefaultValue-->设置订单默认发货的仓库地址[订单来源:{}]-->[配送方式：{}::订单线上发货的仓库地址是默认的仓库][仓库code:{}]",om.getOrderSource(),distributeType,defaultWarehouseCode);
			}
		}
	}
  	
	/**线上和线下的日结日期
	 * @param orderMainDTO
	 * @return
	 */
	public OrderMainDTO settingMerchantBalanceDate(OrderMainDTO orderMainDTO){
		
		//ls 线下订单 日结时间需要计算
		if(isLs(orderMainDTO.getOrderSource())){
			MerchantBalanceDate date = merchantBalanceDateService.getByMerchantCode(orderMainDTO.getPerformStoreCode());
			Date balanceDate = date.getSetDate();
			orderMainDTO.setBalanceDate(balanceDate);
			//String orderTime = DateUtils.formatGeneralDateTimeFormat(new Date());
			//orderMainDTO.setOrderTime(orderTime);
			logger.info("conifigDefaultValue-->设置日结日期[订单来源:{}]--> [日结日期：{}]",orderMainDTO.getOrderSource(),DateUtils.format(balanceDate, DateUtils.generalDateTimeFormatString));
			return orderMainDTO;
		}
		//导购和wx 线上订单的日结时间就是订单创建的时间
		if(isWX(orderMainDTO.getOrderSource()) || isDG(orderMainDTO.getOrderSource())){
			//Date orderTimeDate = new Date();
			try {
				orderMainDTO.setBalanceDate(DateUtils.convertStringToGeneralDateTime(orderMainDTO.getOrderTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		logger.info("conifigDefaultValue-->设置日结日期[订单来源:{}]--> [日结日期：{}]",orderMainDTO.getOrderSource(),orderMainDTO.getOrderTime());
		return orderMainDTO;
	}
	
	
	/**设置订单日期
	 * @param orderMainDTO
	 * @return
	 */
	public OrderMainDTO settingOrderTime(OrderMainDTO orderMainDTO){
		Date orderTimeDate = new Date();
		//ls 线下订单 微信  导购 订单时间
		if(OrderMainConstClient.OrderMain_Ordersource_LS.getCode().equals(orderMainDTO.getOrderSource())
		   || OrderMainConstClient.OrderMain_Ordersource_DG.getCode().equals(orderMainDTO.getOrderSource())		
		   || OrderMainConstClient.OrderMain_Ordersource_WX.getCode().equals(orderMainDTO.getOrderSource())){
			String orderTime = DateUtils.formatGeneralDateTimeFormat(orderTimeDate);
			orderMainDTO.setOrderTime(orderTime);
			return orderMainDTO;
		}
		//外部订单  订单时间是第三方传递过来的
		if(OrderMainConstClient.OrderMain_Ordersource_BS.getCode().equals(orderMainDTO.getOrderSource())){
			return orderMainDTO;
		}
		logger.info("conifigDefaultValue-->设置订单创建日期[订单来源:{}]--> [订单日期:{}]",orderMainDTO.getOrderSource(),orderMainDTO.getOrderTime());
		return orderMainDTO;
	}
	
	/**获取业绩归属门店
	 * 现阶段默认此参数可以从前端获得,如果获取不到 通过此接口更细
	 * @param orderMainDTO
	 * @param context 
	 * @return
	 */
	public OrderMainDTO settingPerformStoreCode(OrderMainDTO orderMainDTO, BtcOmsReceiveOrderOutputDTO output){
		//1.门店下单，门店出货，不论是否会员或会员归属，业绩归属于门店
		//2.导购代客下单，电商仓发货，业绩归属于导购所属门店
		//3.在线商城下单，电商仓发货，会员存在归属导购，业绩归属于导购所属门店
		//4.在线商城下单，电商仓发货，会员无归属，业绩归属于在线商城（其SAP实体也是一个门店，同样具备门店代码）
		//5.电商平台（天猫，京东等）下单，电商仓发货，业绩归属于电商门店（天猫店、京东店等，SAP中也是门店实体）
	
		//门店下单，门店出货，不论是否会员或会员归属，业绩归属于门店
		String performStoreCode = "";
		String  orderResource = orderMainDTO.getOrderSource();
		String distributeType = getDistributeType(orderMainDTO);
		//门店下单，门店出货，不论是否会员或会员归属，业绩归属于门店
		if(isLs(orderResource)){
//			if(OrderMainConst.OrderSub_DistributeType_Self_Store.getCode().equals(distributeType)){
//				performStoreCode = orderMainDTO.getSaleStoreCode();
//				orderMainDTO.setPerformStoreCode(performStoreCode);
//			}else{
//				
//			}
			performStoreCode = orderMainDTO.getSaleStoreCode();
			orderMainDTO.setPerformStoreCode(performStoreCode);
			logger.info("conifigDefaultValue-->设置订单业绩归属门店[订单来源:{}]--> [归属门店:{}]",orderMainDTO.getOrderSource(),orderMainDTO.getPerformStoreCode());
			//logger.info("conifigDefaultValue-->设置订单业绩归属门店[订单来源:{}]--> {}",orderMainDTO.getOrderSource(),orderMainDTO.setPerformStoreCode(performStoreCode)));
			return orderMainDTO;
		}
		//在线商城下单，电商仓发货，会员存在归属导购，业绩归属于导购所属门店 
		//在线商城下单，电商仓发货，会员无归属，业绩归属于在线商城（其SAP实体也是一个门店，同样具备门店代码）
		if(isWX(orderResource)){
			if(StringUtils.isNotEmpty(orderMainDTO.getMemberNo())){
				performStoreCode = getGuideOwnerStoreCode(orderMainDTO.getMemberShoppingGuide(), output);
			}else{
				//微信平台无会员归属，默认是cc99
				performStoreCode = orderMainDTO.getSaleStoreCode();
			}
			orderMainDTO.setPerformStoreCode(performStoreCode);
			logger.info("conifigDefaultValue-->设置订单业绩归属门店[订单来源:{}]--> [归属门店:{}]",orderMainDTO.getOrderSource(),orderMainDTO.getPerformStoreCode());
			return orderMainDTO;
			
		}
		//导购代客下单，电商仓发货，业绩归属于导购所属门店  否则属于 业绩归属于门店
		if(isDG(orderResource)){
			if(OrderMainConst.OrderSub_DistributeType_Third_Party.getCode().equals(distributeType)){
				
				performStoreCode = getGuideOwnerStoreCode(orderMainDTO.getMemberShoppingGuide(), output);
				//否则属于 业绩归属于门店
			}else{
				performStoreCode = orderMainDTO.getSaleStoreCode();
			}
			orderMainDTO.setPerformStoreCode(performStoreCode);
			logger.info("conifigDefaultValue-->设置订单业绩归属门店[订单来源:{}]--> [归属门店:{}]",orderMainDTO.getOrderSource(),orderMainDTO.getPerformStoreCode());
			return orderMainDTO;
		}
		//电商平台（天猫，京东等）下单，电商仓发货，业绩归属于电商门店（天猫店、京东店等，SAP中也是门店实体）BS 迁移到同步创建得订单接口中
//		if(isBS(orderResource)){
//			return orderMainDTO;
//		}
		return  orderMainDTO;
	}

	private String getGuideOwnerStoreCode(String memberShoppingGuide, BtcOmsReceiveOrderOutputDTO output) {
		String guideOwnerStoreCode="";
		if(StringUtils.isNotEmpty(memberShoppingGuide)){
			DefaultOutputDto dd = clerkInfoHsService.getClerkInfo(memberShoppingGuide);
			if(!"S000000".equals(dd.getResponse_code())){
				output.setSucceed(CommonConstService.FAILED);
				output.setMessage(String.format("订单创建配置默值异常：会员导购归属门店查询异常：conifigDefaultValue-->settingPerformStoreCode--[message:%s]", dd.getResponse_msg()));
				logger.info(String.format("订单创建配置默值异常：会员导购归属门店查询异常：conifigDefaultValue-->settingPerformStoreCode--[message:%s]", dd.getResponse_msg()));
			}
			ClerkInfoOutputDto co = (ClerkInfoOutputDto)dd.getResponse_data();
			guideOwnerStoreCode = co.getStoreCode();
		}
		return guideOwnerStoreCode;
	}
	
	private String getDistributeType(OrderMainDTO orderMainDTO) {
		List<OrderSubDTO> orderSubDTOs = orderMainDTO.getOsDTOs();
		if (orderSubDTOs.size() == 1) {
			return orderSubDTOs.get(0).getDistributeType();
		}
		logger.info(String.format("%s 接受订单OrderMainDTO 下的 OrderSubDTO 大于 1或者为 0", getClass()));
		return new OrderSubDTO().getDistributeType();
	}
	
	/**
	 * 构建操作库存对象
	 * Description:
	 * @param orderMain
	 * @return
	 */
	private SkuStockOperateDto createStockDtoByOrderMain(OrderMain orderMain){
		SkuStockOperateDto stockDto = new SkuStockOperateDto();
		List<SkuStockOperateHeaderDto> listHeaders = new ArrayList<SkuStockOperateHeaderDto>();
        SkuStockOperateHeaderDto stockHeaderDto = new SkuStockOperateHeaderDto();
        stockHeaderDto.setOrderNo(orderMain.getOrderNo());//订单号
        stockHeaderDto.setOrderType("3");//1、调拨单， 2、退库单，3、销售单、4、换货单
        stockHeaderDto.setShopCode(ClientConstant.ESTORE_WAREHOUSE_CODE);//
        stockHeaderDto.setCreator("oms");//

        List<SkuStockOperateLineDto> listLines = new ArrayList<SkuStockOperateLineDto>();
        List<OrderItem> items = orderMain.getOrderSubs().get(0).getOrderItems();
        for (OrderItem item : items) {          
            SkuStockOperateLineDto stockLineDto = new SkuStockOperateLineDto();
            if(item.getSaleNum() != null){
            	stockLineDto.setQty(item.getSaleNum().toString());//数量
            }
            stockLineDto.setSkuCode(item.getSkuNo());//商品SKU
            stockLineDto.setItemNo(item.getOrderItemNo());
            stockLineDto.setStockType("1");//1:正常
            listLines.add(stockLineDto);
        }
        stockHeaderDto.setListLines(listLines);
        listHeaders.add(stockHeaderDto);
        stockDto.setListHeaders(listHeaders);
        
        return stockDto;
	}
	
	  /**true 使用新的方法扣除积分，false 使用老的方法扣除积分
     * @param context
     * @param flag
     * @return
     */
    public boolean memberResourceDeduct(ContextBtcOmsReceiveDTO context,boolean flag) {
    	
    	if(!flag){
    		return memberResourceDeduct(context);
    	}
    	 Map<String, OrderMain> omListMap = context.getOmMap();
    	 
    	 for (Map.Entry<String, OrderMain> entry : omListMap.entrySet()) {  
		   OrderMain om = entry.getValue();  
			   if(isLs(om.getOrderSource()) && StringUtils.isEmpty(om.getMemberNo())){
				   return true;
			   }else if(isWX(om.getOrderSource()) && StringUtils.isEmpty(om.getMemberNo()) || isDG(om.getOrderSource()) && StringUtils.isEmpty(om.getMemberNo())){
				   logger.info("error:前台参数错误--非Ls平台会员编号不能为null，订单号："+om.getOrderNo());
	       			context.setMsg("error:前台参数错误--非Ls平台会员编号不能为null，订单号："+om.getOrderNo());
				   return false;
			   }
		   
    	 }
		   Map<String, OrderMain> omMap = context.getOmMap();
	        //1、调用会员帐户扣减积分
	        boolean jifenPayFlag = false;
	        PointsDto pd =  new PointsDto();
	        // Map<String, List<OrderSub>> osListMap
	        for(List<OrderSub> orderSubs : context.getOsListMap().values()){
	        	for(OrderSub os : orderSubs){
	        		List<OrderPay> opList = orderPayService.findByField(OrderPay_.orderNo, orderNoService.getOrderNoBySubNo(os.getOrderSubNo()));
	        		OrderMain om = omMap.get(orderNoService.getOrderNoBySubNo(os.getOrderSubNo()));
	        		for (OrderPay op : opList) {
	        			//支付的积分
	                    if(PayMode.POINT.getCode().equals(op.getPayCode())){
	                   	  jifenPayFlag = true;
	                   	  pd.setDirectionOpt(PointOperator.POINT_DIRECTIONOPT_SUB.getCode());
	                   	  pd.setMemberNo(om.getMemberNo());
	                   	  pd.setPoints(om.getTotalPoint().intValue());
	                   	  pd.setTransactionType(PointOperator.POINT_TRANSACTION_TYPE_SALE_SUB.getCode());
	                   	  pd.setOrderNo(os.getOrderSubNo());
	                     }
	                }
	        	}
	        }
	        //1、调用积分扣减接口
	        if(jifenPayFlag){
	            try {
	            	ResultBean refundDTO = commonUtilService.jsonPostWithTrack(orderCreateServiceCommon.getMem02(), IntfSentConst.OMS_MEM_02.getCode(), pd, ResultBean.class, null);
	                
	            	if( 200 == refundDTO.getCode()){
	            		return true;
	            	}else{
	            		logger.info("下单调用积分扣减接口失败，订单号："+pd.getOrderNo());
	            		logger.info("下单调用积分扣减接口失败 -->[OrderCreateServiceImpl-->memberResourceDeduct-->url:{}]--[params:{}]--[reason:{}]",orderCreateServiceCommon.getMem02(),commonUtilService.ObjectTransJsonstr(pd),refundDTO.getMsg());
	            		context.setMsg(String.format("下单调用积分扣减接口失败 -->[OrderCreateServiceImpl-->memberResourceDeduct-->url:%s]--[params:%s]--[reason:%s]", orderCreateServiceCommon.getMem02(),commonUtilService.ObjectTransJsonstr(pd),refundDTO.getMsg()));
	            		return false;
	            	}
	            }catch(Exception e){
	                e.printStackTrace();
	                context.setMsg("下单调用积分扣减接口失败，订单号："+pd.getOrderNo()+"，异常："+e.getMessage());
	                logger.info("下单调用积分扣减接口失败，订单号："+pd.getOrderNo()+"，异常："+e.getMessage());
	            }
	        }
	        //支付接口没有积分支付 则返回true,默认调用成功
	        return true;
    }
    
    
    public OrderCreateServiceCommon getOrderCreateServiceCommon() {
  		return orderCreateServiceCommon;
  	}
       
      @Resource
  	public void setOrderCreateServiceCommon(OrderCreateServiceCommon orderCreateServiceCommon) {
  		this.orderCreateServiceCommon = orderCreateServiceCommon;
  	}

	@Override
	public void handleCreateOrderAfter(ContextBtcOmsReceiveDTO context, BtcOmsReceiveOrderOutputDTO output) {
		try {
			Map<String, List<OrderItem>> itemMaps = context.getOiListMap();
			for( Map.Entry<String, List<OrderItem>>  entry:itemMaps.entrySet()){
				List<OrderItem> OrderItems = entry.getValue();
				for(OrderItem item : OrderItems){
					//统计商品销售数量
					ProductBriefClientBean pf =	productClientService.getProductSaleNumByProductCode(item.getCommodityCode());
					Long totalNum = pf.getSaleNum() + item.getSaleNum();
					productClientService.updateProductSaleNumByProductCode(item.getCommodityCode(), totalNum);
				}
			}
		} catch (Exception e) {
			
		}
		
		
	}
    
      
    
	
}

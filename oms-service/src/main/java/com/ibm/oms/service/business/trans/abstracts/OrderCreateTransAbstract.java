package com.ibm.oms.service.business.trans.abstracts;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.constant.IntfReceiveConst;
import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.dao.constant.OrderStatusAction;
import com.ibm.oms.dao.constant.PayMode;
import com.ibm.oms.dao.constant.PromotionType;
import com.ibm.oms.dao.intf.OrderMainDao;
import com.ibm.oms.domain.persist.IntfVerified;
import com.ibm.oms.domain.persist.OrderInvoice;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderItemPay;
import com.ibm.oms.domain.persist.OrderItemVirtual;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderMain_;
import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.oms.domain.persist.OrderPayMode;
import com.ibm.oms.domain.persist.OrderPayMode_;
import com.ibm.oms.domain.persist.OrderPay_;
import com.ibm.oms.domain.persist.OrderPromotion;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.intf.constant.OrderMainConst;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderDTO;
import com.ibm.oms.intf.intf.SupportResend;
import com.ibm.oms.intf.intf.inner.BtcOmsReceiveOutputDTO;
import com.ibm.oms.intf.intf.inner.OrderInvoiceDTO;
import com.ibm.oms.intf.intf.inner.OrderItemDTO;
import com.ibm.oms.intf.intf.inner.OrderItemSnapShotDTO;
import com.ibm.oms.intf.intf.inner.OrderItemVirtualDTO;
import com.ibm.oms.intf.intf.inner.OrderMainDTO;
import com.ibm.oms.intf.intf.inner.OrderPayDTO;
import com.ibm.oms.intf.intf.inner.OrderPromotionDTO;
import com.ibm.oms.intf.intf.inner.OrderSubDTO;
import com.ibm.oms.service.IntfVerifiedService;
import com.ibm.oms.service.OrderInvoiceService;
import com.ibm.oms.service.OrderItemPayService;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.OrderItemVirtualService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderPayModeService;
import com.ibm.oms.service.OrderPayService;
import com.ibm.oms.service.OrderPromotionService;
import com.ibm.oms.service.OrderStatusSyncLogService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.OrderSplitPayMent;
import com.ibm.oms.service.business.OrderStatusService;
import com.ibm.oms.service.business.trans.OrderCreateTrans;
import com.ibm.oms.service.business.trans.SaleAfterOrderTransService;
import com.ibm.oms.service.intf.ContextBtcOmsReceiveDTO;
import com.ibm.oms.service.util.CommonConstService;
import com.ibm.oms.service.util.CommonUtilService;
import com.ibm.sc.service.impl.BaseServiceImpl;
import com.ibm.sc.util.CommonUtil;


public abstract class OrderCreateTransAbstract extends BaseServiceImpl<OrderMain, Long> implements OrderCreateTrans{
	    private final Logger logger = LoggerFactory.getLogger(getClass());
	    String errorMsgMain = "OrderCreateServiceImpl.writeOrderMain cloneBean throws Exception";
	    String errorMsgSub = "OrderCreateServiceImpl.writeOrderSub cloneBean throws Exception";
	    String errorMsgInvoice = "OrderCreateServiceImpl.writeOrderSub, write orderInvoice cloneBean throws Exception";
	    String errorMsgItem = "OrderCreateServiceImpl.writeOrderItem cloneBean throws Exception";
	    String errorMsgPromo = "OrderCreateServiceImpl.writeOrderPromo cloneBean throws Exception";
	    String errorMsgOrderMainDuplicate = "OrderCreateTransImpl.saveOrder 订单已存在";
	    @Autowired
	    OrderStatusService orderStatusService;
	    @Autowired
	    OrderPromotionService orderPromotionService;
	    @Autowired
	    OrderItemPayService orderItemPayService;
	    @Autowired
	    OrderItemService orderItemService;
	    @Autowired
	    OrderItemVirtualService orderItemVirtualService;
	    @Autowired
	    OrderPayModeService orderPayModeService;
	    @Autowired
	    OrderInvoiceService orderInvoiceService;
	    @Autowired
	    OrderStatusSyncLogService orderStatusSyncLogService;
	    @Autowired
	    OrderMainService orderMainService;
	    @Autowired
	    SaleAfterOrderTransService saleAfterOrderTransService;
	    @Autowired
	    OrderSplitPayMent orderSplitPayMent;
	    
	    
	    
		@Resource(name = "prePayModeMap")
		private Map<String, String> prePayModeMap;
	    /**
	     * @param orderMainDao
	     */
	    @Autowired
	    public void setOrderMainDao(OrderMainDao orderMainDao) {
	        super.setBaseDao(orderMainDao);
	    }

	    @Autowired
	    private OrderPayService orderPayService;
	    @Autowired
	    private OrderSubService orderSubService;
	    @Autowired
	    OrderNoService orderNoService;
	    @Autowired
	    IntfVerifiedService intfVerifiedService;
	    @Autowired
	    CommonUtilService commonUtilService;

	    public OrderPayService getOrderPayService() {
	        return orderPayService;
	    }

	    @Autowired
	    public void setOrderPayService(OrderPayService orderPayService) {
	        this.orderPayService = orderPayService;
	    }

	    @Autowired
	    public void setOrderSubService(OrderSubService orderSubService) {
	        this.orderSubService = orderSubService;
	    }

	    @Override
	    public boolean saveOrder(BtcOmsReceiveOrderDTO orderReceiveDIO,
	            ContextBtcOmsReceiveDTO context) {
	        if(!saveOrderMainStart(orderReceiveDIO, context)){
	            //订单已经存在
	            context.setCreateSuccessFlag(false);
	            context.setMsg(errorMsgOrderMainDuplicate);
	            return false;
	        };
	        context.setCreateSuccessFlag(true);
	        return true;
	    }
	    
	    /**是否重复订单
	     * 2018/03/12 更新
	     * 新系统暂时没有批次的概念，所以去掉批次号和订单来源的校验
	     * **/
	    protected boolean isDuplicate(String batchNum, OrderMainDTO om){
	        boolean omList = orderMainService.isOrderMainDuplicated(batchNum, om.getOrderSource());
	        return omList;
	    }

	    /**
	     * 写主订单表，子订单，订单级促销
	     * **/
	    protected boolean saveOrderMainStart(BtcOmsReceiveOrderDTO orderReceiveDIO,
	            ContextBtcOmsReceiveDTO context) {
	        context.setBatchNum(orderReceiveDIO.getBatchNum());
	        List<OrderMainDTO> omDTOList = orderReceiveDIO.getOmDTO();
	        if (omDTOList == null || omDTOList.size() == 0) {
	            return true;
	        }
	        //如果有拆单，第二个订单不做防重处理
	        boolean duplicateDetected = false;
	        for (OrderMainDTO omDTO : omDTOList) {
	        	 /**是否重复订单
	    	     * 2018/03/12 更新
	    	     * 新系统暂时没有批次的概念，所以去掉批次号和订单来源的校验
	    	     * **/
	        	//2018/03/12 注释掉批次和订单来源的校验 --wch
//	            if(!duplicateDetected && isDuplicate(orderReceiveDIO.getBatchNum(), omDTO)){
//	                return false;
//	            }
	            saveOrderMainSingle(omDTO, context);
	            duplicateDetected = true;
	        }
	        return true;
	    }

	    /**
	     * 写主订单，子订单，订单级促销
	     * **/
	    protected BtcOmsReceiveOutputDTO saveOrderMainSingle(OrderMainDTO omDTO, ContextBtcOmsReceiveDTO context) {
	        BtcOmsReceiveOutputDTO ret = new BtcOmsReceiveOutputDTO();
	        // 主订单
	        OrderMain om = saveOrderMain(omDTO, context);
	        // 写子订单,订单行，订单行级促销
	        saveOrderSub(omDTO, om, context);
	        // 写订单级促销，
	        saveOrderPromo(omDTO, om, context);
	        // 订单头支付
	        saveOrderPay(omDTO, om, context);
	        //增加赠送积分计算-测试线去掉
	        //updateGiftPoint(om, context,omDTO);
	        //均摊支付价格到订单hang
	      
	        
	        
	        // 创建销售订单-->处理中
	        ret.setIdOrder(om.getId() + "");
	        ret.setOrderNo(om.getOrderNo());
	        ret.setPayOnArrival(CommonConstService.BOOLEAN_TRUE.equals(om.getIfPayOnArrival()) ? true : false);
	        return ret;
	    }
	    
	    
	  

	

		protected void saveOrderItemVirtual(OrderSubDTO osDTO, OrderSub os, OrderMain om, ContextBtcOmsReceiveDTO context) {
	        List<OrderItemVirtualDTO> virtualList = osDTO.getOivDTOs();
	        if (virtualList != null && !virtualList.isEmpty()) {

	            int index = 1;
	            List<OrderItemVirtual> oivList = new ArrayList<OrderItemVirtual>();
	            for (OrderItemVirtualDTO virtual : virtualList) {
	                OrderItemVirtual oiv = new OrderItemVirtual();
	                try {
	                    Date date = new Date();
	                    BeanUtils.copyProperties(virtual, oiv);
	                    oiv.setIdOrder(os.getIdOrder());
	                    oiv.setIdOrderSub(os.getId());
	                    oiv.setOrderNo(os.getOrderNo());
	                    oiv.setOrderSubNo(os.getOrderSubNo());
	                    oiv.setAliasOrderItemNo(virtual.getAliasOrderItemNo());
	                    oiv.setAliasOrderNo(NumberUtils.toLong(virtual.getAliasOrderNo()));
	                    oiv.setDateCreated(date);
	                    oiv.setDateUpdated(date);
	                    oiv.setCheckCode(CommonUtil.getRandomString(4));
	                    oiv.setIdOrder(om.getId());
	                    String defaultSubNo = orderNoService.getDefaultOrderSubNoByOrderNo(om.getOrderNo());
	                    oiv.setOrderItemNo(orderNoService.getOrderItemNoBySubOrderNo(defaultSubNo, index));
	                    oiv.setOrderNo(om.getOrderNo());
	                    oiv.setSaleNum(NumberUtils.toLong(virtual.getSaleNum()));
	                    oiv.setUnitPrice(commonUtilService.string2BigDecimal(virtual.getUnitPrice()));
	                    oiv.setSaleAmount(commonUtilService.string2BigDecimal(virtual.getSaleAmount()));
	                    oiv.setStockNo(virtual.getStockNo());
	                    oiv.setProductPoint(commonUtilService.string2BigDecimal(virtual.getProductPoint()));
	                    String itemSnapShot = virtual.getItemSnapshot();
	                    ObjectMapper mapper = new ObjectMapper();
	                    OrderItemSnapShotDTO itemSS = mapper.readValue(itemSnapShot, OrderItemSnapShotDTO.class);
	                    BeanUtils.copyProperties(itemSS, oiv);
//	                    oiv.setSkuId(itemSS.getSkuId());
//	                    oiv.setBarCode(itemSS.getBarCode());
//	                    oiv.setSupplierCode(itemSS.getSupplierCode());
//	                    // 商品行所获积分,快照给 8.15,快照给单品所获积分
//	                    oiv.setProductPoint(itemSS.getPoint());
	                    if (StringUtils.isBlank(os.getShipCat())) {
	                        os.setShipCat(itemSS.getShipCat());
	                        orderSubService.update(os);
	                    }
	                    context.setSnapShot(itemSS);
	                } catch (Exception e) {
	                    logger.error(errorMsgItem, e);
	                    throw new RuntimeException(errorMsgItem + e);
	                }
	                orderItemVirtualService.save(oiv);
	                oivList.add(oiv);
	            }
	            context.getOivListMap().put(os.getOrderSubNo(), oivList);
	        }
	    }

	    /** 写my卡，券，积分支付等支付信息 **/
	    /** 180416 算出均摊信息 [积分，优惠券，订单支付]**/
	    protected void saveOrderPay(OrderMainDTO omDTO, OrderMain om, ContextBtcOmsReceiveDTO context) {
	        List<OrderPayDTO> opayList = omDTO.getOrderPayDTOs();
	        if (opayList == null || opayList.isEmpty()) {
	            return;
	        }
	        //如果包含卡券之外的支付，说明支付没有完成，否则卡券已经完全支付
	        boolean containPayMode = false;
	        // 写orderPay 支付详细表，包括待确定的支付方式
	        for (OrderPayDTO opDTO : opayList) {
	            String payCode = opDTO.getPayCode();
	            Date date = new Date();
	            if (PayMode.COUPON.getCode().equals(payCode) || PayMode.INTEGRAL.getCode().equals(payCode) || PayMode.POINT.getCode().equals(payCode)|| PayMode.WAIT.getCode().equals(payCode) ) {
	                OrderPay op = new OrderPay();
	                BeanUtils.copyProperties(opDTO, op);
	                op.setBillType(CommonConst.OrderMain_BillType_Positive.getCodeLong());
	                op.setDateCreated(date);
	                op.setDateUpdated(date);
	                op.setIdOrder(om.getId());
	                op.setOrderNo(om.getOrderNo());
	                op.setPayAmount(commonUtilService.string2BigDecimal(opDTO.getPayAmount()));
	               // op.setPayStatus(0);
//	                if (opDTO.getPayTime() != null) {
//	                    op.setPayTime(CommonUtilService.strToDate(opDTO.getPayTime(),
//	                            CommonConst.OrderMain_OrderTime.getCode()));
//	                }else{
//	                	 op.setPayTime(new Date());
//	                }
	                orderPayService.save(op);
	                List<OrderItem> itemList = context.getOrderItemsByOrderNo(om.getOrderNo());
	                // splitPayMent(itemList, op); 0416--update splitPayMent服务
	                //主表支付信息均摊到各个订单行
	                orderSplitPayMent.splitPayMent(itemList, op);
	            } 
	            
	            if(PayMode.WAIT.getCode().equals(payCode) ){
	            	//保存待确认的支付类型到支付类型表中，待支付回调更新
	                OrderPayMode opm = new OrderPayMode();
	                BeanUtils.copyProperties(opDTO, opm);
	                opm.setPayAmount(commonUtilService.string2BigDecimal(opDTO.getPayAmount()));
	                opm.setPayModeCode(opDTO.getPayCode());
	                opm.setPayModeName(opDTO.getPayName());
	                opm.setDateCreated(date);
	                opm.setDateUpdated(date);
	                opm.setIdOrder(om.getId());
	                opm.setOrderNo(om.getOrderNo());
	                opm.setPayStatus(0L);
	                orderPayModeService.save(opm);
	                if(null!=opm.getPayAmount() && opm.getPayAmount().compareTo(new BigDecimal(0))>0){
	                    containPayMode = true;
	                }
	            }
	            //else {
	            	//保存支付类型到支付类型表中
//	                OrderPayMode opm = new OrderPayMode();
//	                BeanUtils.copyProperties(opDTO, opm);
//	                opm.setPayAmount(commonUtilService.string2BigDecimal(opDTO.getPayAmount()));
//	                opm.setPayModeCode(opDTO.getPayCode());
//	                opm.setPayModeName(opDTO.getPayName());
//	                opm.setDateCreated(date);
//	                opm.setDateUpdated(date);
//	                opm.setIdOrder(om.getId());
//	                opm.setOrderNo(om.getOrderNo());
//	                opm.setPayStatus(0L);
//	                orderPayModeService.save(opm);
//	                if(null!=opm.getPayAmount() && opm.getPayAmount().compareTo(new BigDecimal(0))>0){
//	                    containPayMode = true;
//	                }
	            //}
	        }
	        //不含卡券之外的支付说明已经支付完成
	        context.setTotalPaid(!containPayMode);
	    }

	  

		

		/** 按照所有明细行平摊订单级优惠 **/
	    protected Map<String, OrderItemPay> splitPayMent(List<OrderItem> orderItems, OrderPay op) {
	        BigDecimal total = new BigDecimal(0.0);
	        for (OrderItem oi : orderItems) {
	            // 订单行应付金额:折后总价
	            total = total.add(oi.getPayAmount());
	        }
	        // 没有订单行,是虚拟商品或卡
	        if (total.compareTo(new BigDecimal(0.0)) == 0) {
	            return null;
	        }
	        BigDecimal addedPayment = new BigDecimal(0.0);
	        BigDecimal payAmount = op.getPayAmount();
	        int size = orderItems.size();
	        Map<String, OrderItemPay> ret = new HashMap<String, OrderItemPay>();
	        for (int i = 0; i < size; i++) {
	            OrderItem oi = orderItems.get(i);
	            OrderItemPay orderItemPay = new OrderItemPay();
	            BeanUtils.copyProperties(op, orderItemPay);
	            // last item
	            if (i == (size - 1)) {
	                BigDecimal payment = payAmount.subtract(addedPayment);
	                orderItemPay.setPayAmount(payment);
	            } else {
	                BigDecimal payment = payAmount.multiply(oi.getPayAmount()).divide(total ,2, RoundingMode.HALF_UP);
	                addedPayment = addedPayment.add(payment);
	                orderItemPay.setPayAmount(payment);
	            }
	            Date date = new Date();
	            orderItemPay.setIdOrderItem(oi.getIdOrder());
	            orderItemPay.setIdOrderItem(oi.getId());
	            orderItemPay.setDateUpdated(date);
	            orderItemPay.setDateCreated(date);
	            orderItemPay.setOrderItemNo(oi.getOrderItemNo());
	            orderItemPayService.save(orderItemPay);
	            ret.put(oi.getOrderItemNo(), orderItemPay);
	        }
	        return ret;
	    }

	    /** 写子订单表 **/
	    protected List<OrderSub> saveOrderSub(OrderMainDTO orderMainDTO, OrderMain om, ContextBtcOmsReceiveDTO context) {
	        List<OrderSubDTO> osDTOs = orderMainDTO.getOsDTOs();
	        if (osDTOs == null || osDTOs.size() == 0) {
	            return null;
	        }
	        Date date = new Date();
	        List<OrderSub> osList = new ArrayList<OrderSub>();
	        for (int subIndex = 0; subIndex < osDTOs.size(); subIndex++) {
	            OrderSubDTO osDTO = osDTOs.get(subIndex);
	            // 写子订单
	            OrderSub os = new OrderSub();
	            try {
	                BeanUtils.copyProperties(osDTO, os);
	            } catch (Exception e) {
	                logger.error(errorMsgSub, e);
	                throw new RuntimeException(errorMsgSub + e);
	            }
	            os.setBillType(CommonConst.OrderMain_BillType_Positive.getCodeLong());
	            if(StringUtils.isNotBlank(osDTO.getPickTime())){
	                os.setPickTime(CommonUtilService.strToDate(osDTO.getPickTime(), "yyyy-MM-dd"));
	            }
	            //0413 现阶段没有希望到底日期得选项
	            if(StringUtils.isNotEmpty(osDTO.getHopeArrivalTime())){
	            	os.setHopeArrivalTime(NumberUtils.toLong(osDTO.getHopeArrivalTime()));
	            }
	            os.setAliasOrderNo(osDTO.getAliasOrderNo());
	            os.setAliasOrderSubNo(osDTO.getAliasOrderSubNo());
	            os.setOrderSubNo(orderNoService.getOrderSubNoByOrderNo(om.getOrderNo(), subIndex + 1));
	            os.setDateCreated(date);
	            os.setDateUpdated(date);
	            os.setIdOrder(om.getId());
	            os.setOrderNo(om.getOrderNo());
	            os.setTransportFee(commonUtilService.string2BigDecimal(osDTO.getTransportFee()));
	            if(CommonConst.OrderSub_Distribute_Type2.getCode().equals(os.getDistributeType())){
	                os.setCheckCode(CommonUtil.getRandomString(4));
	            }
	            orderSubService.save(os);
	            // 保存之后添加到context子订单列表
	            osList.add(os);
	            // 写发票
	            OrderInvoiceDTO orderInvoice = osDTO.getOrderInvoice();
	            if (orderInvoice != null) {
	                OrderInvoice oi = new OrderInvoice();
	                try {
	                    BeanUtils.copyProperties(orderInvoice, oi);
	                } catch (Exception e) {
	                    logger.error(errorMsgInvoice, e);
	                    throw new RuntimeException(errorMsgInvoice + e);
	                }
	                oi.setOrderNo(om.getOrderNo());
	                oi.setOrderSubNo(os.getOrderSubNo());
	                oi.setDateCreated(date);
	                oi.setDateUpdated(date);
	                oi.setIdOrder(om.getId());
	                orderInvoiceService.save(oi);
	            }
	            
	            // 写订单行
	            saveOrderItem(osDTO, os, om, context);
	            // 写虚拟行
	            saveOrderItemVirtual(osDTO, os, om, context);
	        }
	        
	     //   om.setOrderSubs(osList);
	        // 子订单列表写入context子订单map
	        context.getOsListMap().put(om.getOrderNo(), osList);
	        List<OrderItem> oiList = new ArrayList<OrderItem>();
	        List<OrderItemVirtual> oivList = new ArrayList<OrderItemVirtual>();
	        // 将所有行加入单号map
	        for (OrderSub os : osList) {
	            List<OrderItem> itemList = context.getOrderItemsByOrderSubNo(os.getOrderSubNo());
	            if (itemList != null && !itemList.isEmpty()) {
	                oiList.addAll(itemList);
	            }
	            List<OrderItemVirtual> itemVirtualList = context.getOrderItemsVirtualByOrderSubNo(os.getOrderSubNo());
	            if (itemVirtualList != null && !itemVirtualList.isEmpty()) {
	                oivList.addAll(itemVirtualList);
	            }
	        }
	        context.getOiListMap().put(om.getOrderNo(), oiList);
	        context.getOivListMap().put(om.getOrderNo(), oivList);
	        return osList;
	    }

	    /** 写订单行表 **/
	    protected void saveOrderItem(OrderSubDTO osDTO, OrderSub os, OrderMain om, ContextBtcOmsReceiveDTO context) {
	        List<OrderItemDTO> oiDTOs = osDTO.getOiDTOs();
	        if (oiDTOs == null || oiDTOs.size() == 0) {
	            return;
	        }
	        int index = 1;
	        List<OrderItem> oiList = new ArrayList<OrderItem>();
	        //针对btc重复给订单行的bug，检查是否该行已保存，如果已经保存则忽略
	        List<String> saveAliasOrderIds = new ArrayList<String>();
	        for (OrderItemDTO oiDTO : oiDTOs) {
	            boolean isPCOrder = CommonConst.OrderMain_OrderSource_PC.getCode().equals(om.getOrderSource());
	            if(isPCOrder && saveAliasOrderIds.contains(oiDTO.getAliasOrderItemNo())){
	                //重复行
	                continue;
	            }
	            OrderItem oi = new OrderItem();
	            try {
	                BeanUtils.copyProperties(oiDTO, oi);
	                oi.setBillType(CommonConst.OrderMain_BillType_Positive.getCodeLong());
	                oi.setOrderItemNo(orderNoService.getOrderItemNoBySubOrderNo(os.getOrderSubNo(), index++));
	                oi.setIdOrder(os.getIdOrder());
	                oi.setIdOrderSub(os.getId());
	                oi.setProductPropertyFlag(commonUtilService.StrToLong(oiDTO.getProductPropertyFlag()));
	                oi.setOrderNo(os.getOrderNo());
	                oi.setOrderSubNo(os.getOrderSubNo());
	                Date date = new Date();
	                oi.setDateCreated(date);
	                oi.setDateUpdated(date);

	                // 折前单价
	                BigDecimal unitPrice = commonUtilService.string2BigDecimal(oiDTO.getUnitPrice());
	                oi.setUnitPrice(unitPrice);
	                // 单件折后价
	                BigDecimal unitDeductedPrice = commonUtilService.string2BigDecimal(oiDTO.getUnitDeductedPrice());
	                oi.setUnitDeductedPrice(unitDeductedPrice);
	                Long saleNum = NumberUtils.toLong(oiDTO.getSaleNum());
	                oi.setSaleNum(saleNum);
	                BigDecimal saleTotalMoney = unitPrice.multiply(new BigDecimal(saleNum));
	                // 折前总价，自己计算 ：单价 * 数量
	                oi.setSaleTotalMoney(saleTotalMoney);
	                // 单件购物券金额:B2C算好
	                oi.setCouponAmount(commonUtilService.string2BigDecimal(oiDTO.getCouponAmount()));
	                // 使用优惠券金额的汇总，作为发票打印里"折扣"部分
	                oi.setCouponTotalMoney(commonUtilService.string2BigDecimal(oiDTO.getCouponTotalMoney()));
	                // 行折扣金额
	                BigDecimal itemDiscount = commonUtilService.string2BigDecimal(oiDTO.getItemDiscount());
	                oi.setItemDiscount(itemDiscount);
	                // 折扣总金额，按比例算出
	                // oi.setDiscountTotalMoney(om.getDiscountOrder().multiply(ratio).add(itemDiscount));
	                oi.setHasGift(oiDTO.getHasGift());
	                // 订单行应付金额:折后总价
	                oi.setPayAmount(commonUtilService.string2BigDecimal(oiDTO.getPayAmount()));
	                // 返券总金额:该行返券金额的汇总
	                oi.setPromoTicketMoney(commonUtilService.string2BigDecimal(oiDTO.getPromoTicketMoney()));
	                // 折后单价,此单价为用户实际支付使用的单价，退款的参考
	                oi.setUnitDeductedPrice(commonUtilService.string2BigDecimal(oiDTO.getUnitDeductedPrice()));
	                // 销售单件折扣:B2C算好
	                oi.setUnitDiscount(commonUtilService.string2BigDecimal(oiDTO.getUnitDiscount() == null? "0" : oiDTO.getUnitDiscount()));
	                oi.setWeight(commonUtilService.string2BigDecimal(oiDTO.getWeight()));
	                oi.setPayAmount(commonUtilService.string2BigDecimal(oiDTO.getPayAmount()));
	                oi.setProductPoint(commonUtilService.string2BigDecimal(oiDTO.getProductPoint()));
	                //20180305 增加均摊积分-- 迁移到支付保存
	                //oi.setSharePoint(getSharePoint(oiDTO, om));
	                //2018 SnapShot注释掉待确认  --start
	                //TODO
	                String itemSnapShot = oiDTO.getItemSnapshot();
	                ObjectMapper mapper = new ObjectMapper();
	                //OrderItemSnapShotDTO itemSS = mapper.readValue(itemSnapShot, OrderItemSnapShotDTO.class);
	                //BeanUtils.copyProperties(itemSS, oi);
//	                oi.setBrandName(itemSS.getBrandName());
	                //oi.setIsLowGross(NumberUtils.toLong(itemSS.getLowGross()));
	               // oi.setProductCategoryName(itemSS.getCategoryName());
	                //2018 SnapShot注释掉待确认  --end
	                //色码款商品标识
//	                if(StringUtils.isNotBlank(oiDTO.getProductPropertyFlag())){
//	                    try{
//	                        Long semakuanFlag = Long.valueOf(oiDTO.getProductPropertyFlag());
//	                        oi.setProductPropertyFlag(semakuanFlag);
//	                    }catch(Exception e){
//	                        logger.info(e.getMessage());
//	                    }
//	                }
	                // 商品行所获积分,快照给,8.15快照给单品
//	                oi.setProductPoint(itemSS.getPoint());
	                //2018 SnapShot注释掉待确认  --start
	               // if(StringUtils.isBlank(os.getShipCat())){
	               //     os.setShipCat(itemSS.getShipCat());
	               //     orderSubService.update(os);
	               // }
	               // context.setSnapShot(itemSS);
	                //2018 SnapShot注释掉待确认  --end
	            } catch (Exception e) {
	                logger.error(errorMsgItem, e);
	                throw new RuntimeException(errorMsgItem + e);
	            }
	            orderItemService.save(oi);
	            saveAliasOrderIds.add(oiDTO.getAliasOrderItemNo());
	            oiList.add(oi);
	            List<OrderPromotionDTO> opDTOs = oiDTO.getOpDTOs();
	            // 订单行促销
	            saveOrderItemPromo(opDTOs, oi);
	        }
	        // 将该子订单所有行加入map
	        context.getOsiListMap().put(os.getOrderSubNo(), oiList);

	    }

	    protected void saveOrderItemPromo(List<OrderPromotionDTO> opDTOs, OrderItem oi) {
	        if (opDTOs == null || opDTOs.size() == 0) {
	            return;
	        }
	        for (OrderPromotionDTO opDTO : opDTOs) {
	            OrderPromotion op = new OrderPromotion();
	            try {
	                BeanUtils.copyProperties(opDTO, op);
	                Date date = new Date();
	                op.setDateCreated(date);
	                op.setDateUpdated(date);
	                op.setIdOrder(oi.getIdOrder());
	                op.setIdOrderItem(oi.getId());
	                op.setOrderNo(oi.getOrderNo());
	                op.setOrderItemNo(oi.getOrderItemNo());
	                op.setPointCount(commonUtilService.string2BigDecimal(opDTO.getPointCount()));
	                op.setTicketAmount(commonUtilService.string2BigDecimal(opDTO.getTicketAmount()));
	            } catch (Exception e) {
	                logger.error(errorMsgPromo, e);
	                throw new RuntimeException(errorMsgPromo + e);
	            }
	            op.setPromoLevel(CommonConst.OrderPromotion_Promolevel_Item.getCode());
	            orderPromotionService.save(op);
	        }
	    }

	    protected void saveOrderPromo(OrderMainDTO omDTO, OrderMain om, ContextBtcOmsReceiveDTO context) {
	        List<OrderPromotionDTO> opDTOs = omDTO.getOpDTOs();
	        if (opDTOs == null || opDTOs.size() == 0) {
	            return;
	        }
	        for (OrderPromotionDTO opDTO : opDTOs) {
	            String orderNo = om.getOrderNo();
	            String orderSubNo = orderNoService.getDefaultOrderSubNoByOrderNo(orderNo);
	            List<OrderItem> items = context.getOrderItemsByOrderNo(orderNo);
	            List<OrderItemVirtual> itemsVirtual = context.getOrderItemsVirtualByOrderSubNo(orderSubNo);
	            
	            //订单级事后积分,活动送积分，满额送积分，不包括单品送积分
//	            promAddTotalPoint(items, opDTO);
//	            promAddTotalPointVirtual(itemsVirtual, opDTO);
	            //整单返积分【订单结束调用促销接口】
	            promAddTotalPoint(om, opDTO);
	            //整单返券【订单结束调用促销接口】
	            promAddTotalTicketAmount(om, opDTO);
	            promAddOther(om, opDTO);

	        }
	    }

		/**订单完成赠送积分**/
	    protected void promAddTotalPoint(OrderMain om, OrderPromotionDTO op){
	        boolean addFlag = false;
	        String promoLevel = "";
	        if(PromotionType.PointDonate.getCode().equals(op.getPromoType()) || PromotionType.TotalPoint.getCode().equals(op.getPromoType())){
	            addFlag = true;
	            promoLevel = CommonConst.OrderPromotion_Promolevel_Order.getCode();
	        }else if(PromotionType.ProductPoint.getCode().equals(op.getPromoType())){
	            addFlag = true;
	            promoLevel = CommonConst.OrderPromotion_Promolevel_Item.getCode(); //单品赠送积分
	        }
	        if(!addFlag){
	            return;
	        }
	        Date date = new Date();
	        OrderPromotion itemPromo = new OrderPromotion();
	        BeanUtils.copyProperties(op, itemPromo);
	        itemPromo.setOrderNo(om.getOrderNo());
	        itemPromo.setIdOrder(om.getId());
	        itemPromo.setDateUpdated(date);
	        itemPromo.setDateCreated(date);
	        itemPromo.setPromoLevel(promoLevel);
	        orderPromotionService.save(itemPromo);
	    }
	    
	    
	    /**订单完成送券**/
	    protected void promAddTotalTicketAmount(OrderMain om, OrderPromotionDTO op){
	        boolean addFlag = false;
	        String promoLevel = "";
	        if(PromotionType.CouponDonate.getCode().equals(op.getPromoType()) || PromotionType.TotalCoupon.getCode().equals(op.getPromoType())){
	            addFlag = true;
	            promoLevel = CommonConst.OrderPromotion_Promolevel_Order.getCode();
	        }else if(PromotionType.ProductCoupon.getCode().equals(op.getPromoType())){
	            addFlag = true;
	            promoLevel = CommonConst.OrderPromotion_Promolevel_Item.getCode(); //单品赠送券
	        }
	        if(!addFlag){
	            return;
	        }
	        Date date = new Date();
	        OrderPromotion itemPromo = new OrderPromotion();
	        BeanUtils.copyProperties(op, itemPromo);
	        itemPromo.setOrderNo(om.getOrderNo());
	        itemPromo.setIdOrder(om.getId());
	        itemPromo.setDateUpdated(date);
	        itemPromo.setDateCreated(date);
	        itemPromo.setPromoLevel(promoLevel);
	        orderPromotionService.save(itemPromo);
	    }
	    
	    
	    private void promAddOther(OrderMain om, OrderPromotionDTO op) {
			// TODO Auto-generated method stub
	    	if(PromotionType.CouponDonate.getCode().equals(op.getPromoType()) 
    			|| PromotionType.TotalCoupon.getCode().equals(op.getPromoType())
    			|| PromotionType.ProductCoupon.getCode().equals(op.getPromoType())
    			|| PromotionType.PointDonate.getCode().equals(op.getPromoType()) 
    			|| PromotionType.TotalPoint.getCode().equals(op.getPromoType())
    			|| PromotionType.ProductPoint.getCode().equals(op.getPromoType())){
	    		return ;
	    	}
	    	
	    	Date date = new Date();
	        OrderPromotion itemPromo = new OrderPromotion();
	        BeanUtils.copyProperties(op, itemPromo);
	        itemPromo.setOrderNo(om.getOrderNo());
	        itemPromo.setIdOrder(om.getId());
	        itemPromo.setDateUpdated(date);
	        itemPromo.setDateCreated(date);
	        itemPromo.setPromoLevel(op.getPromoLevel());
	        itemPromo.setTicketAmount(new BigDecimal(op.getTicketAmount()));
	        itemPromo.setTicketNo(op.getTicketNo());
	        itemPromo.setPromoType(op.getPromoType());
	        orderPromotionService.save(itemPromo);
		}
	    
	    
	    
	    protected OrderMain saveOrderMain(OrderMainDTO omDTO, ContextBtcOmsReceiveDTO context) {
	        OrderMain om = new OrderMain();
	        try {
	            BeanUtils.copyProperties(omDTO, om);
	            om.setBatchNum(context.getBatchNum());
		        //订单创建
		        om.setStatusTotal(OrderStatus.ORDER_CREATING.getCode());
	            Date date = new Date();
	            //正向订单
	            om.setBillType(CommonConst.OrderMain_BillType_Positive.getCodeLong());
	            SimpleDateFormat sdf = new SimpleDateFormat(CommonConst.OrderMain_OrderTime.getCode());
	            //外部订单创建时间  2018 可能为null
	            om.setOrderTime(sdf.parse(omDTO.getOrderTime()));
	            //外部订单号
	            om.setAliasOrderNo(omDTO.getAliasOrderNo());
	            om.setSourceOrderNo(omDTO.getSourceOrderNo());//只有外部渠道订单通过B2C对接到中台才用到
	           // om.setBillType(CommonConst.OrderMain_BillType_Positive.getCodeLong());
	            om.setDateCreated(date);
	            om.setDateUpdated(date);
	            //运费
	            om.setDiscountTransport(commonUtilService.string2BigDecimal(omDTO.getDiscountTransport()));
	            om.setIfPayOnArrival(NumberUtils.toLong(omDTO.getIfPayOnArrival()));
	            //是否货到付款
//	            if (CommonConst.OrderMain_IfPayOnArrival_Yes.getCode().equals(om.getIfPayOnArrival())) {
//	                context.setPayOnArrival(true);
//	            } else {
//	                context.setPayOnArrival(false);
//	            }
	            om.setNeedConfirm(NumberUtils.toLong(omDTO.getNeedConfirm()));
	            om.setNeedInvoice(NumberUtils.toLong(omDTO.getNeedInvoice()));
	            om.setTotalGivePoints(commonUtilService.string2BigDecimal(omDTO.getTotalGivePoints()));
	            om.setTotalPayAmount(commonUtilService.string2BigDecimal(omDTO.getTotalPayAmount()));
	            om.setTotalProductPrice(commonUtilService.string2BigDecimal(omDTO.getTotalProductPrice()));
	            om.setTotalPromo(commonUtilService.string2BigDecimal(omDTO.getTotalPromo()));
	            om.setTransportFee(commonUtilService.string2BigDecimal(omDTO.getTransportFee()));
	            om.setWeight(commonUtilService.string2BigDecimal(omDTO.getWeight()));//下单重量不做限制
	            om.setIfShowPrice(NumberUtils.toLong(omDTO.getIfShowPrice()));
	            om.setOrderCategory(OrderMainConst.OrderMain_OrderCategory_Sale.getCode());
	            //设置订单用积分
	            om.setTotalPoint(commonUtilService.string2BigDecimal(omDTO.getTotalPoint()));
	            //订单积分抵扣
	            om.setTotalPointAmount(commonUtilService.string2BigDecimal(omDTO.getTotalPointAmount()));
	            om.setIp(omDTO.getIp());//下单IP地址
//	            List<OrderItemDTO> oiList = omDTO.getOsDTOs().get(0).getOiDTOs();
//	            BigDecimal discountTotal = new BigDecimal(0);
//	            if(oiList!=null && !oiList.isEmpty()){
//	                for(OrderItemDTO oi:oiList){
//	                    if(NumberUtils.isNumber(oi.getItemDiscount())){
//	                        discountTotal = discountTotal.add(new BigDecimal(oi.getItemDiscount()));
//	                    }
//	                }
//	            }
//	          om.setDiscountTotal(discountTotal);
	            // 订单总折扣, 直接btc给
	            om.setDiscountTotal(commonUtilService.string2BigDecimal(omDTO.getDiscountTotal()));
	            om.setMemberCardLevel(omDTO.getMemberVipCardLevel());
	        } catch (Exception e) {
	            logger.error(errorMsgMain, e);
	            throw new RuntimeException(errorMsgMain + e);
	        }
	        save(om);
	        String orderNo = orderNoService.getOrderNoByOrderId(om.getId() + "");
	        om.setOrderNo(orderNo);
	        update(om);
	        context.getOmMap().put(orderNo, om);
	        return om;
	    }

	    /*
	     * @Transactional public Long save(OrderMain om){ return orderMainDao.save(om); }
	     * @Override public int updateWithSql(){ return orderMainDao.updateWithSql(); }
	     */
	    @Override
	    public boolean savePostInventory(BtcOmsReceiveOrderDTO orderReceiveDIO, ContextBtcOmsReceiveDTO context) {
	        if (context.isTotalPaid()) {
	            // 已经完全支付
	            for (OrderMain om : context.getOmMap().values()) {
	                orderStatusService.saveProcess(orderNoService.getDefaultOrderSubNoByOrderNo(om.getOrderNo()),
	                        OrderStatusAction.S011030, null, null, null);
	                orderStatusService.saveProcess(orderNoService.getDefaultOrderSubNoByOrderNo(om.getOrderNo()),
	                        OrderStatusAction.S013020, null, null, null);
	            }
	            return true;
	        }
	        
	        // 货到付款订单处理成功，待定时任务处理, 如果是在线支付，接受到支付信息后实时处理
	        else if (context.isPayOnArrival()) {
	            //货到付款未完全支付
	            for (OrderMain om : context.getOmMap().values()) {
	                orderStatusService.saveProcess(orderNoService.getDefaultOrderSubNoByOrderNo(om.getOrderNo()),
	                        OrderStatusAction.S011020, null, null, null);
	            }
	            return true;
	        } else {
	            // 在线支付未完全支付
	            for (OrderMain om : context.getOmMap().values()) {
	                orderStatusService.saveProcess(orderNoService.getDefaultOrderSubNoByOrderNo(om.getOrderNo()),
	                        OrderStatusAction.S011030, null, null, null);
	            }
	            return false;
	        }
	    }

	    @Override
	    public IntfVerified saveVerified(BtcOmsReceiveOrderDTO orderReceiveDIO, ContextBtcOmsReceiveDTO context) {
	        // 货到付款订单处理成功，或者卡券支付已经完成, 待定时任务处理, 如果是在线支付，接受到支付信息后实时处理
	        if (context.isPayOnArrival() || context.isTotalPaid()) {
	            IntfVerified iv = new IntfVerified();
	            iv.setBtcOrderItemNo(context.getBatchNum());
	            iv.setCreateTime(new Date());
	            iv.setIntfCode(IntfReceiveConst.BTC_OMS_RECEIVE.getCode());
	            iv.setProcessFlag(CommonConstService.PROCESS_PREFAILED);
	            String msg = commonUtilService.writeIntObjectJson(context);
	            iv.setMsg(msg);
	            intfVerifiedService.save(iv);
	            return iv;
	        } 
	        return null;
	    }
	    
	    @Override
	    public void saveBatchProcess(Map<String, OrderMain> omMap, OrderStatusAction action) {
	        for (OrderMain om : omMap.values()) {
	            String orderSubNoDefault = orderNoService.getDefaultOrderSubNoByOrderNo(om.getOrderNo());
	            orderStatusService.saveProcess(orderSubNoDefault, action, null, null, null);
	        }

	    }
	    
	    @Override
	    public void saveStatusSyncLog(Map<String, OrderMain> omMap) {
	        for (OrderMain om : omMap.values()) {
	            List<OrderPay> orderPayList = orderPayService.findByField("orderNo", om.getOrderNo());
	            if (orderPayList == null || orderPayList.isEmpty()) {
	                continue;
	            }
	            for (OrderPay orderPay : orderPayList) {
	                // 下单的时候 myCard  同步R3
	                if (prePayModeMap.containsValue(orderPay.getPayCode())) {
	                    orderStatusSyncLogService.saveAndcreate(om, null,
	                            CommonConst.OrderStatusSyncLog_SyncScene_PrePay.getCode());
	                    break;
	                }
	            }
	        }
	    }
	    
	    @Override
	    public SupportResend querySupportSendEmail(String orderSubNo) {
	        String orderNo = orderNoService.getOrderNoBySubNo(orderSubNo);
	        OrderMain om = orderMainService.get(OrderMain_.orderNo, orderNo);
	        OrderSub os = om.getOrderSubs().get(0);
	        String recipients = om.getCustomerEmail();
	        if(StringUtils.isBlank(recipients)){
	            return null;
	        }
	        List<OrderItem> ois = os.getOrderItems();
	        List<OrderItemDTO> oisDetached = new ArrayList<OrderItemDTO>();
	        if(ois != null  && !ois.isEmpty()){
	            for(OrderItem oi:ois){
	                OrderItemDTO oiTarget = new OrderItemDTO();
	                BeanUtils.copyProperties(oi, oiTarget);
	                oiTarget.setSaleNum(commonUtilService.Long2Str(oi.getSaleNum()));
	                oisDetached.add(oiTarget);
	            }
	        }
	        List<OrderPayMode> payModes = orderPayModeService.findByField(OrderPayMode_.orderNo, orderNo);
	        List<OrderPay> orderPays = orderPayService.findByField(OrderPay_.orderNo, orderNo);
	        BigDecimal opAmount = new BigDecimal(0);
	        if(orderPays != null && !orderPays.isEmpty()){
	            for(OrderPay op:orderPays){
	                opAmount = opAmount.add(op.getPayAmount());
	            }
	        }
	        Map<String,Object> data = new HashMap<String, Object>();
	        if(payModes != null && !payModes.isEmpty()){
	            OrderPayMode opm = payModes.get(0);
	            data.put("payType", opm.getPayModeName());
	        }
	        //orderNo totalPayAmount discountTotal totalPayAmount payType userName mobPhoneNum 
	        //addressDetail commodityName saleNum paidAmount toBePaid checkCode
	        data.put("orderNo", orderNo);//订单号
	        data.put("memberNo", om.getMemberNo());
	        data.put("totalPayAmount", om.getTotalPayAmount());
	        data.put("discountTotal", om.getDiscountTotal());
	        data.put("transportFee", om.getTransportFee());
	        data.put("userName", os.getUserName());
	        data.put("mobPhoneNum", os.getMobPhoneNum());
	        data.put("addressDetail", os.getAddressDetail());
	        data.put("paidAmount", opAmount);
	        data.put("toBePaid", om.getTotalPayAmount().subtract(opAmount));
	        data.put("customerName",om.getCustomerName());
	        data.put("orderTime",commonUtilService.format24Hours().format(om.getOrderTime()));
	        data.put("items", oisDetached);
	        data.put("orderSubNo", orderSubNo);//子订单号
	        data.put("recipients", recipients);//手机号码
	        
	        SupportResend so=new SupportResend();
	        so.setCode("CM-OMS-STOCK_DEDUCTED_EMAIL");//模板code，
	        so.setType("email");//email邮件
	        so.setData(data);
	        return so;
	    }
	    
	    /**
	     * 销售订单传输R3【进入待同步表】
	     * @param orderMain
	     * @param orderSubNoR
	     */
	    public void saveOrderToR3(OrderMain om, OrderSub os){
	        if(null==om){
	            return;
	        }
	        String orderCategory = om.getOrderCategory();
	        if(null!=orderCategory && orderCategory.equals(OrderMainConst.OrderMain_OrderCategory_ChangeOut.getCode())){
	            saleAfterOrderTransService.saveChgOrderToR3(om, false);
	        }else{
	            //("I-OMS-R3-01","订单明细"),
	            orderStatusSyncLogService.saveAndcreate(om, os,
	                    CommonConst.OrderStatusSyncLog_SyncScene_OrderItem.getCode());
	            //"I-OMS-R3-02","支付明细"
//	            orderStatusSyncLogService.saveAndcreate(om, os, 
//	                    CommonConst.OrderStatusSyncLog_SyncScene_Pay.getCode());
	        }
	    }
	    
	    @Override
	    /** 自动审核失败返回false **/
	    public boolean queryAutoAuditCriteria(String orderNo) {
	        OrderMain om = orderMainService.findByOrderNo(orderNo);
	        //总购物金额超2000(拆单前)
	        List<OrderMain> omList = orderMainService.findByField(OrderMain_.batchNum, om.getBatchNum());
	        if(omList != null && !omList.isEmpty()){
	            BigDecimal totalMoney = new BigDecimal(0);
	            for(OrderMain omain:omList){
	                totalMoney = totalMoney.add(omain.getTotalPayAmount());
	            }
	            if(totalMoney.compareTo(totalPayAmountLimit) >= 0){
	                return false;
	            }
	        }
	        
	        //单品1000以上(拆单前)
	        if(omList != null && !omList.isEmpty()){
	            for(OrderMain omain:omList){
	                for(OrderSub sub:omain.getOrderSubs()){
	                    for(OrderItem item:sub.getOrderItems()){
	                        BigDecimal payUnitMoney = new BigDecimal(0);
	                        if(null!=item.getUnitDeductedPrice()){ 
	                            payUnitMoney = item.getUnitDeductedPrice();//折后单价
	                        }else{
	                            if(null!=item.getUnitDiscount()){
	                                payUnitMoney = item.getUnitPrice().subtract(item.getUnitDiscount());
	                            }else{
	                                payUnitMoney = item.getUnitPrice();
	                            }
	                        }
	                        if(payUnitMoney.compareTo(itemPayAmountLimit)>=0){
	                            return false;
	                        }
	                    }
	                }
	            }
	            
	            //判断订单的下单ip、下单id是否超过三单[当天的订单],超过则进入人工审核
	            java.text.SimpleDateFormat dateSdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
	            String curHourStr = dateSdf.format(new Date());
	            String dateStart = curHourStr+" 00:00:00";
	            String dateEnd = curHourStr+" 23:59:59";
	            StringBuffer sqlSbf = new StringBuffer();
	            sqlSbf.append("select * from order_main where (");
	            sqlSbf.append("  member_no='"+om.getMemberNo()+"' ");    
	            if(StringUtils.isNotBlank(om.getIp())){
	                sqlSbf.append(" or ip='"+om.getIp()+"' ");    
	            }
	            sqlSbf.append(")");
	            sqlSbf.append(" and order_time>to_date('"+dateStart+"', 'yyyy-mm-dd hh24:mi:ss')");
	            sqlSbf.append(" and order_time<to_date('"+dateEnd+"', 'yyyy-mm-dd hh24:mi:ss')");
	            List<Object> list = orderMainService.findOrderMainBySql(sqlSbf.toString());
	            if(null!=list && list.size()>orderCountLimit){
	                return false;
	            }
	            
	        }

	        // 7天内购买低毛利商品超过20单，或者1天购买低毛利商品超过5单
	        /*Calendar calendar7DaysBefore = Calendar.getInstance();
	        Calendar calendar1DaysBefore = Calendar.getInstance();
	        calendar7DaysBefore.add(Calendar.DATE, daysIntervalM7);
	        calendar7DaysBefore.add(Calendar.DATE, daysIntervalM1);
	        Long t7 = calendar7DaysBefore.getTimeInMillis();
	        Long t1 = calendar1DaysBefore.getTimeInMillis();
	        int count1 = orderMainService.getOMListByTypeAndPeriod(OrderMain_.memberNo, memberNo, t1);
	        if (count1 > blackListCriteriaDays1) {
	            return false;
	        }
	        int count7 = orderMainService.getOMListByTypeAndPeriod(OrderMain_.memberNo, memberNo, t7);
	        if (count7 > blackListCriteriaDays7) {
	            return false;
	        }
	        int countMobile1 = orderMainService.getOMListByTypeAndPeriod(OrderMain_.customerPhone, mobileNo, t1);
	        if (countMobile1 > blackListCriteriaDays1) {
	            return false;
	        }
	        int countMobile7 = orderMainService.getOMListByTypeAndPeriod(OrderMain_.customerPhone, mobileNo, t7);
	        if (countMobile7 > blackListCriteriaDays7) {
	            return false;
	        }*/
	        return true;
	    }
	    
	    /**
	     * 钩子方法
	     */
	    public void hook(){
	    	
	    }
	    
	    private BigDecimal getSharePoint(OrderItemDTO oiDTO ,OrderMain om){
	    	//计算均摊
	    	//1、（每个订单行的支付价格除以支付总价格）*订单用积分总量
	    	BigDecimal itemPayAmount = commonUtilService.string2BigDecimal(oiDTO.getPayAmount());
	    	BigDecimal totalPayAumout = om.getTotalPayAmount();
	    	BigDecimal sharePoint = null;
	    	if(om.getTotalPoint() == null ){
	    		sharePoint = new BigDecimal(0);
	    	}else{
	    		sharePoint = itemPayAmount.divide(totalPayAumout).multiply(om.getTotalPoint());
	    	}
	    	//四舍五入
	    	return sharePoint.setScale(0, BigDecimal.ROUND_HALF_UP);
	    }

		public String getErrorMsgMain() {
			return errorMsgMain;
		}

		public void setErrorMsgMain(String errorMsgMain) {
			this.errorMsgMain = errorMsgMain;
		}

		public String getErrorMsgSub() {
			return errorMsgSub;
		}

		public void setErrorMsgSub(String errorMsgSub) {
			this.errorMsgSub = errorMsgSub;
		}

		public String getErrorMsgInvoice() {
			return errorMsgInvoice;
		}

		public void setErrorMsgInvoice(String errorMsgInvoice) {
			this.errorMsgInvoice = errorMsgInvoice;
		}

		public String getErrorMsgItem() {
			return errorMsgItem;
		}

		public void setErrorMsgItem(String errorMsgItem) {
			this.errorMsgItem = errorMsgItem;
		}

		public String getErrorMsgPromo() {
			return errorMsgPromo;
		}

		public void setErrorMsgPromo(String errorMsgPromo) {
			this.errorMsgPromo = errorMsgPromo;
		}

		public String getErrorMsgOrderMainDuplicate() {
			return errorMsgOrderMainDuplicate;
		}

		public void setErrorMsgOrderMainDuplicate(String errorMsgOrderMainDuplicate) {
			this.errorMsgOrderMainDuplicate = errorMsgOrderMainDuplicate;
		}
	    
	    //20180402 增加赠送积分计算
		
		/**增加赠送积分计算
		 * @param om
		 * @param context
		 * @param omDTO 
		 */
		public void updateGiftPoint(OrderMain om,ContextBtcOmsReceiveDTO context, OrderMainDTO omDTO){
			List<OrderItem> oiList = context.getOiListMap().get(om.getOrderNo());
			BigDecimal totalGivePoints =orderItemService.getTotalPoint(om, oiList,omDTO.getInfCart());
			//保存赠送总积分
			om.setTotalGivePoints(totalGivePoints);
			orderMainService.update(om);
			//保存订单行上的积分
			for(OrderItem it : oiList){
				orderItemService.update(it);
			}
		}
	    
}

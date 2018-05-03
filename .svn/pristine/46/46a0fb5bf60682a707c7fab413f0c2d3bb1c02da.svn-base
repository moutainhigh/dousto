package com.ibm.oms.service.business.trans.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.CancelOrderConst;
import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.dao.constant.OrderStatusAction;
import com.ibm.oms.dao.constant.PayMode;
import com.ibm.oms.dao.constant.PayType;
import com.ibm.oms.domain.persist.OrderCombineRelation;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderMain_;
import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.oms.domain.persist.OrderPay_;
import com.ibm.oms.domain.persist.OrderReason;
import com.ibm.oms.domain.persist.OrderRetAdd;
import com.ibm.oms.domain.persist.OrderRetAdd_;
import com.ibm.oms.domain.persist.OrderRetChange;
import com.ibm.oms.domain.persist.OrderRetChange_;
import com.ibm.oms.domain.persist.OrderRetChgItem;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.domain.persist.OrderSub_;
import com.ibm.oms.domain.persist.OrdiErrOptLog;
import com.ibm.oms.domain.persist.OrdiErrOptLog_;
import com.ibm.oms.domain.persist.ProductProperty;
import com.ibm.oms.intf.constant.OrderMainConst;
import com.ibm.oms.intf.constant.SaleAfterOrderErrorConst;
import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.intf.intf.InventoryLockOutputDTO;
import com.ibm.oms.intf.intf.ResultDTO;
import com.ibm.oms.service.OrderCombineRelationService;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderPayService;
import com.ibm.oms.service.OrderRetAddService;
import com.ibm.oms.service.OrderRetChangeService;
import com.ibm.oms.service.OrderRetChgItemService;
import com.ibm.oms.service.OrderStatusSyncLogService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.oms.service.OrdiErrOptLogService;
import com.ibm.oms.service.ProductPropertyService;
import com.ibm.oms.service.TransportAreaCacheService;
import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.OrderStatusService;
import com.ibm.oms.service.business.ReturnChangeOrderService;
import com.ibm.oms.service.business.trans.SaleAfterOrderTransService;
import com.ibm.oms.service.util.CommonConstService;
import com.ibm.oms.service.util.CommonUtilService;
import com.ibm.oms.service.util.OrderReasonUtil;
import com.ibm.oms.service.util.PaymentMethodUtil;
import com.ibm.oms.service.util.UserUtil;
import com.ibm.sc.common.BusinessException;
import com.ibm.sc.model.payment.PaymentMethod;
import com.ibm.sc.model.sys.User;
import com.ibm.sc.service.sys.UserService;
import com.ibm.sc.util.DateUtil;

@Service("saleAfterOrderTransService")
public class SaleAfterOrderTransServiceImpl implements SaleAfterOrderTransService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    OrderMainService orderMainService;
    @Autowired
    OrderSubService orderSubService;
    @Autowired
    OrderRetChgItemService orderRetChgItemService;
    @Autowired
    OrderPayService orderPayService;
    @Autowired
    OrderNoService orderNoService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    OrderRetAddService orderRetAddService;
    @Autowired
    OrderRetChangeService orderRetChangeService;
    @Autowired
    OrderStatusSyncLogService orderStatusSyncLogService;
    @Autowired
    OrderStatusService orderStatusService;
    @Autowired
    OrderCreateService orderCreateService;
    @Autowired
    ReturnChangeOrderService returnChangeOrderService;
    @Autowired
    CommonUtilService commonUtilService;
    @Autowired
    OrdiErrOptLogService ordiErrOptLogService;
    @Resource
    protected UserService userService;
    @Autowired
    OrderReasonUtil orderReasonUtil;
    @Autowired
    PaymentMethodUtil paymentMethodUtil;
    @Autowired
    TransportAreaCacheService transportAreaCacheService;
    @Autowired
    OrderCombineRelationService orderCombineRelationService;
    @Autowired
    UserUtil userUtil;
    @Autowired
    ProductPropertyService productPropertyService;
    
	private static BeanCopier orderMainCopy = BeanCopier.create(OrderMain.class, OrderMain.class, true); 
	
	private static BeanCopier orderSubCopy = BeanCopier.create(OrderSub.class, OrderSub.class, true); 
	
	private static BeanCopier orderPayCopy = BeanCopier.create(OrderPay.class, OrderPay.class, false); 
	
	private static BeanCopier orderItemCopy = BeanCopier.create(OrderItem.class, OrderItem.class, true); 

    /**
     * 创建退、换、拒收货单据 （数据组装可参考单元测试：OrderRetChangeServiceImplTest）
     * 
     * @param catalogryType 退换货类型：退货，换货，拒收
     * @param orderMain 退、换货、拒收主订单
     * @param orderSubs 子订单（需包含明细OrderRetChgItem，且明细需设置refOrderItemId、refOrderItemNo）
     * @param orderItem 明细
     * @param applySource 申请来源：B2C,订单客服
     * @param orderPay 退款明细
     */
    public ResultDTO  saveOrderRetChange(String orderCategory, String applySource, OrderMain orderMain, OrderSub orderSub,
            List<OrderPay> orderPays) {
    	
    	ResultDTO dto = new ResultDTO();
    	
        /** 基本数据校验 start **/
        if (StringUtils.isEmpty(orderCategory)) {
        	dto.setErrorMessage(this.Msg_OrderCategory_Empty);
        	dto.setResultObj(SaleAfterOrderErrorConst.Empty_OrderCategory);
            return dto; // 验证：退换货类型
        }
        if (StringUtils.isEmpty(applySource)) {
        	dto.setErrorMessage(this.Msg_ApplySource_Empty);
        	dto.setResultObj(SaleAfterOrderErrorConst.Empty_ApplySource);
            return dto; // 验证：来源
        }
        if (null == orderSub) {
        	dto.setErrorMessage(this.Msg_OrderSub_Empty);
        	dto.setResultObj(SaleAfterOrderErrorConst.Empty_OrderSub);
            return dto; // 验证：子订单
        }
       /* if (orderCategory.equalsIgnoreCase(OrderMainConst.OrderMain_OrderCategory_Return.getCode())) {
            if (null == orderPays || orderPays.size() == 0) {
            	dto.setErrorMessage(this.Msg_OrderPay_Empty);
            	dto.setResultObj(SaleAfterOrderErrorConst.Empty_OrderPay);
                return dto; // 验证：退款信息(退货)
            }
        }*/
        if(orderCategory.equals(OrderMainConst.OrderMain_OrderCategory_Reject.getCode()) && null!=orderPays && orderPays.size()>0){
            if(null==orderMain.getIfNeedRefund() || orderMain.getIfNeedRefund().longValue()==CommonConst.OrderMain_IfNeedRefund_No.getCodeLong()){
                dto.setErrorMessage(Msg_NeedPay_Empty_JS);
                return dto; // 拒收且含退款信息，必须选择需退款
            }
        }
        String refOrderNo = orderMain.getOrderRelatedOrigin(); // 关联的原订单号，即由哪张销售订单所产生的退换货
        if (StringUtils.isEmpty(refOrderNo)) {
        	dto.setErrorMessage(this.Msg_RefOrderNo_Empty);
        	dto.setResultObj(SaleAfterOrderErrorConst.Empty_RefOrderNo);
            return dto;
        }
        if (null == orderSub.getOrderRetChgItems() || orderSub.getOrderRetChgItems().size() == 0) {
        	dto.setErrorMessage(this.Msg_OrderRetChgItem_Empty);
        	dto.setResultObj(SaleAfterOrderErrorConst.Empty_OrderRetChgItem);
            return dto; // 验证：退换货明细
        }
        // 验证明细中是否有设置原明细的id、no
        for (OrderRetChgItem item : orderSub.getOrderRetChgItems()) {
            if (null==item.getRefOrderItemId()) {
            	dto.setErrorMessage(this.Msg_RefOrderItemId_Empty);
            	dto.setResultObj(SaleAfterOrderErrorConst.Empty_RefOrderItemId);
                return dto;
            }
            if (StringUtils.isEmpty(item.getRefOrderItemNo())) {
            	dto.setErrorMessage(this.Msg_RefOrderItemNo_Empty);
            	dto.setResultObj(SaleAfterOrderErrorConst.Empty_RefOrderItemNo);
                return dto;
            }
        }
        /** 基本数据校验 end **/
        
        // 1、添加主订单表
        orderMain.setOrderTime(DateUtil.getNowDate());
        orderMain.setDateCreated(DateUtil.getNowDate());
        orderMain.setOrderCategory(orderCategory); // 退换货类型
        orderMain.setBatchNum("");
        orderMain.setBillType(CommonConst.OrderMain_BillType_Negative.getCodeLong());//逆向订单
        orderMain.setStatusTotal(OrderStatus.RET_ORDER_CREATING.getCode());// 设置默认总状态
        // 获取关联的原销售主订单
        OrderMain relatedOrderMain = this.orderMainService.findByOrderNo(refOrderNo);
        if(null != relatedOrderMain)
        {
        	// 设置关联销售订单的“大客户”标识
        	orderMain.setIfPriviledgedMember(relatedOrderMain.getIfPriviledgedMember());
        	// 设置关联销售订单的“预警”标识
        	orderMain.setIfWarnOrder(relatedOrderMain.getIfWarnOrder());
        	orderMain.setMemberNo(relatedOrderMain.getMemberNo());//会员编号
        	orderMain.setCustomerName(relatedOrderMain.getCustomerName());
        	orderMain.setMemberCardLevel(relatedOrderMain.getMemberCardLevel());//会员卡等级
        	orderMain.setMerchantNo(relatedOrderMain.getMerchantNo()); //商家编号
        	orderMain.setMerchantType(relatedOrderMain.getMerchantType());
        }
        long orderId = orderMainService.save(orderMain);
        String orderNo = orderNoService.getOrderNoByOrderId(String.valueOf(orderId)); // 获取订单no：通过规则获取
        orderMain.setOrderNo(orderNo);
        orderMainService.update(orderMain);// 更新orderNo至ordermain表

        // 2、添加子订单表
        int subOrderCount = 1;
        orderSub.setOrderNo(orderNo);
        orderSub.setIdOrder(orderId);
        orderSub.setBillType(CommonConst.OrderMain_BillType_Negative.getCodeLong());//逆向订单
        orderSub.setDateCreated(DateUtil.getNowDate());
        String orderSubNo = orderNoService.getOrderSubNoByOrderNo(orderNo, subOrderCount);// 获取子订单单号no
        orderSub.setOrderSubNo(orderSubNo);
        //获取原销售订单的物流商id
        OrderSub refOrderSub = orderSubService.getByField(OrderSub_.orderNo, refOrderNo);
        if(null != refOrderSub){
            orderSub.setDeliveryMerchantNo(refOrderSub.getDeliveryMerchantNo());
            orderSub.setDeliveryMerchantName(refOrderSub.getDeliveryMerchantName());    
        }
        
        long orderSubId = orderSubService.save(orderSub);
        
        List<OrderSub> newOrderSubs = new ArrayList<OrderSub>();
        newOrderSubs.add(orderSub);
        orderMain.setOrderSubs(newOrderSubs);
        // 3、添加明细表
        List<OrderRetChgItem> items = orderSub.getOrderRetChgItems();
        int itemCount = 0;
        for (OrderRetChgItem item : items) {
            itemCount++;
            String itemNo = orderNoService.getOrderItemNoBySubOrderNo(orderSubNo, itemCount);// 获取逆向订单明细no
            item.setOrderItemNo(itemNo);
            item.setOrderNo(orderNo);
            item.setIdOrder(orderId);
            item.setOrderSubNo(orderSubNo);
            item.setIdOrderSub(orderSubId);
            //判断skuNo,barCode,skock_no是否为空，为空无法传输WMS
            //OrderItem orderItem = orderItemService.getByField(OrderItem_.orderItemNo, item.getRefOrderItemNo());
            OrderItem orderItem = orderItemService.getByOrderItemNo(item.getRefOrderItemNo());
            if(null!=orderItem){
                if(StringUtils.isEmpty(item.getSkuNo())){
                    item.setSkuNo(orderItem.getSkuNo());
                }
                if(StringUtils.isEmpty(item.getSkuId())){
                    item.setSkuId(orderItem.getSkuId());
                }
                if(StringUtils.isEmpty(item.getBarCode())){
                    item.setBarCode(orderItem.getBarCode());//条形码
                }
                if(StringUtils.isEmpty(item.getCommodityName())){
                    item.setCommodityName(orderItem.getCommodityName());
                }
                if(StringUtils.isEmpty(item.getStockNo())){
                    item.setStockNo(orderItem.getStockNo());//库区
                }
                if(null==item.getPayAmount() || item.getPayAmount().compareTo(new BigDecimal(0))==0){
                    item.setPayAmount(item.getUnitPrice().multiply(new BigDecimal(item.getSaleNum())));
                }
                if(null==item.getCommodityCode()){
                    item.setCommodityCode(orderItem.getCommodityCode());//商品编码
                }
                if(null==item.getInStoreBarCode()){
                    item.setInStoreBarCode(orderItem.getInStoreBarCode());//店内码
                }
                if(null==item.getSupplierCode()){
                    item.setSupplierCode(orderItem.getSupplierCode());//供应商编码
                }
                item.setIsUnionBiz(orderItem.getIsUnionBiz()); //自营联营
                item.setNormalPrice(orderItem.getNormalPrice()); //原价
                item.setInventoryPrice(orderItem.getInventoryPrice());//出库成本价
                
                //判断是否为组合商品
                if(OrderMainConst.OrderItem_OrderItemClass_SUITE.getCode().equals(orderItem.getOrderItemClass())){
                    item.setOrderItemClass(orderItem.getOrderItemClass());
                    List<OrderCombineRelation> list = orderItem.getOrderCombineRelations();
                    for(OrderCombineRelation combineRela:list){
                        try{
                            OrderCombineRelation ocr = new OrderCombineRelation();
                            BeanUtils.copyProperties(ocr,combineRela);
                            ocr.setOrderItemNo(itemNo);
                            ocr.setCombineNo(combineRela.getCombineNo());
                            orderCombineRelationService.save(ocr);
                        }catch(Exception e){
                            logger.info("逆向订单"+orderNo+"copy组合商品明细失败：{}", e.getMessage());
                        }
                    }
                }
                
            }
            //保存数据
            long orderItemId = orderRetChgItemService.save(item);
            //判断是否为色码款商品，写色码款属性表
            if(null!=orderItem){
                item.setProductPropertyFlag(orderItem.getProductPropertyFlag());
                try{
                    if(CommonConst.OrderItem_ProductPropertyFlag_Yes.getCodeLong()==orderItem.getProductPropertyFlag().longValue()){
                        for(ProductProperty propy: orderItem.getProductPropertys()){
                            ProductProperty newPro = new ProductProperty();
                            newPro.setIdOrder(orderId);
                            //newPro.setIdOrderItem(orderItemId);
                            newPro.setOrderNo(orderNo);
                            newPro.setOrderItemNo(itemNo);
                            newPro.setPropertyName(propy.getPropertyName());
                            newPro.setPropertyValue(propy.getPropertyValue());
                            productPropertyService.save(newPro);
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    logger.info("意向单写色码款属性，itemNo："+itemNo+"，异常："+e.getMessage());
                }
            }
            
            // 4、添加明细与原订单的关联关系
            OrderRetChange orderRetChange = orderRetChangeService.createOrderRetChange(applySource, orderMain, item);
            Long retChangeId = orderRetChangeService.save(orderRetChange);
            item.setChg_retChangeId(retChangeId);// 出库单用到
            if (orderCategory.equalsIgnoreCase(OrderMainConst.OrderMain_OrderCategory_Return.getCode())) {
                // 5、累计退货数量
                String result = saverRetOrderRecord(item, orderRetChange);
                if (!result.equals(this.OK)) {
                	dto.setErrorMessage(result);
                	dto.setResultObj(SaleAfterOrderErrorConst.NotEnough_RemainNum);
                    return dto;
                }
            }
        }
        // 5、如果是退货(已付款)，则需保存明细
        if (orderCategory.equalsIgnoreCase(OrderMainConst.OrderMain_OrderCategory_Return.getCode())) {
            //国贸云店需获取原支付方式
            /*if(CommonConst.OrderMain_MerchantNo_Yundian_GuoMao.getCode().equals(relatedOrderMain.getMerchantNo())
                    && CommonConst.OrderMain_MerchantType_InvoiceOrg.getCode().equals(relatedOrderMain.getMerchantType())){
                List<OrderPay> refPays = this.orderPayService.findByField(OrderPay_.orderNo, relatedOrderMain.getOrderNo());
                for (OrderPay refPay : refPays) {
                    if(refPay.getPayCode().equals(PayType.COUPON.getId())){
                        continue; //购物券不退
                    }
                    OrderPay relPay = new OrderPay();
                    relPay.setIdOrder(orderId);
                    relPay.setOrderNo(orderNo);
                    relPay.setPayTime(new Date());
                    relPay.setBillType(CommonConst.OrderMain_BillType_Negative.getCodeLong());
                    relPay.setPayAmount(refPay.getPayAmount());
                    relPay.setPayCode(refPay.getPayCode());
                    relPay.setPayName(refPay.getPayName());
                    relPay.setPayNo(refPay.getPayNo());
                    relPay.setSerialNo(refPay.getSerialNo());
                    orderPayService.save(relPay);// 创建退款信息
                }
            }else{*/
                for (OrderPay orderPay : orderPays) {
                    orderPay.setIdOrder(orderId);
                    orderPay.setOrderNo(orderNo);
                    orderPay.setPayTime(new Date());
                    orderPay.setBillType(CommonConst.OrderMain_BillType_Negative.getCodeLong());
                    if(StringUtils.isEmpty(orderPay.getPayName())){
                        //如果名称为空则需根据code获取
                        PaymentMethod pMethod = paymentMethodUtil.getRefundMethodMap().get(orderPay.getPayCode());
                        if(null!=pMethod){
                            orderPay.setPayName(pMethod.getName());    
                        }
                    }
                    orderPayService.save(orderPay);// 创建退款信息
                }
//            }
        } else if (orderCategory.equalsIgnoreCase(OrderMainConst.OrderMain_OrderCategory_Change.getCode())) {
            // 换货抵扣(R3传输用到)
            this.orderPayService.saveChangePaidByOrderMain(orderMain);
            //创建换货出库单
            String chgOrderNo = createExcOrder(orderMain, orderSub);
            if(null==chgOrderNo || chgOrderNo.equals("")){
                dto.setErrorMessage(errorMsg_AddExcOrder_Fail);
                dto.setResultObj(SaleAfterOrderErrorConst.Fail_AddExcOrder);
                return dto;
            }
            dto.setResultObj(chgOrderNo);//返回订单号（ 用于出库使用：出库单锁库存、状态流转）
            // 更新换货订单状态扭转（在SaleAfterOrderServiceImpl中统一实现）
            //orderStatusService.saveProcess(orderSubNo, OrderStatusAction.S021020, null, null);
            //将出库单的单号保存到换货单中
            orderMain.setChgOurOrderNo(chgOrderNo);
            this.orderMainService.update(orderMain);
            dto.setResultMessage(this.OK);          
        } else if (orderCategory.equalsIgnoreCase(OrderMainConst.OrderMain_OrderCategory_Reject.getCode())) { // 拒收
            // 判断原订单是否已支付
            OrderMain refOrderMian = orderMainService.getByField(OrderMain_.orderNo, refOrderNo);
            if (OrderStatus.Order_PayStatus_Success.getCode().equals(refOrderMian.getStatusPay())) { // 原订单已支付（在线支付）
                if (null == orderPays || orderPays.size() == 0) {
                	dto.setErrorMessage(this.Msg_OrderPay_Empty_JS);
                    return dto; // 验证：退款信息(退货)
                }
                for (OrderPay orderPay : orderPays) {
                    orderPay.setIdOrder(orderId);
                    orderPay.setOrderNo(orderNo);
                    orderPayService.save(orderPay);// 创建退款信息
                }
            }
        }
        // 更新换货订单状态扭转
        orderStatusService.saveProcess(orderSubNo, OrderStatusAction.S021020, null, null, null);
        dto.setResultMessage(this.OK);
        dto.setOrderNo(orderNo);
        dto.setOrderSubNo(orderSubNo);
        return dto;
    }
    
    /**
     * 创建换货出库单
     * 
     * @param chgOrder 换货意向单的主订单
     * @param chgOrderSub 换货意向单的子订单
     */
    private String createExcOrder(OrderMain chgOrder, OrderSub chgOrderSub) {
    	OrderMain main = new OrderMain();
        try {
            String srcOrderNo = chgOrder.getOrderRelatedOrigin();// OMS原销售订单号
            OrderMain srcMain = this.orderMainService.findByOrderNo(srcOrderNo);
            OrderSub srcSub = srcMain.getOrderSubs().get(0); 
            // 主订单
            main.setOrderSource(srcMain.getOrderSource());
            main.setOrderType(srcMain.getOrderType());
            main.setMerchantType(srcMain.getMerchantType());
            main.setMerchantNo(srcMain.getMerchantNo());
            main.setMemberNo(srcMain.getMemberNo());
            main.setCustomerName(srcMain.getCustomerName());
            main.setCustomerPhone(srcMain.getCustomerPhone());
            main.setMemberCardLevel(srcMain.getMemberCardLevel());
            main.setMerchantNo(srcMain.getMerchantNo()); //商家编号
            main.setMerchantType(srcMain.getMerchantType());
            main.setDeliveryDateFlag(srcMain.getDeliveryDateFlag());
            main.setDeliveryTimeFlag(srcMain.getDeliveryTimeFlag());
            main.setOrderTime(DateUtil.getNowDate());// 产生时间
            main.setDateCreated(DateUtil.getNowDate());
            main.setReceiveAreaId(srcMain.getReceiveAreaId());
            main.setWeight(chgOrder.getWeight());
            main.setTotalProductPrice(chgOrder.getTotalProductPrice());
            main.setClientRemark(chgOrder.getClientRemark());
            main.setClientServiceRemark(chgOrder.getClientServiceRemark());//后台客服留言
            
            main.setTotalPayAmount(chgOrder.getTotalPayAmount());//人民币总计
            main.setDiscountTotal(chgOrder.getDiscountTotal());//折扣金额
            
            main.setTransportFee(chgOrder.getTransportFee());
            main.setOrderRelatedOrigin(chgOrder.getOrderNo());// 这里关联换货意向单的单号
            main.setBillType(CommonConst.OrderMain_BillType_Positive.getCodeLong());
            main.setOrderCategory(OrderMainConst.OrderMain_OrderCategory_ChangeOut.getCode());// 换货出库单
            main.setStatusTotal(OrderStatus.ORDER_CREATING.getCode()); // 设置默认状态
            if (main.getTransportFee().doubleValue() > 0) {
                main.setStatusPay(OrderStatus.Order_PayStatus_Cash_Paying.getCode());
            }else{
                main.setStatusPay(OrderStatus.Order_PayStatus_Success.getCode());
            }
            Long orderId = orderMainService.save(main);
            String orderNo = orderNoService.getOrderNoByOrderId(String.valueOf(orderId));
            main.setOrderNo(orderNo);
            orderMainService.update(main);

            // 子订单
            OrderSub sub = new OrderSub();
            sub.setIdOrder(orderId);
            sub.setOrderNo(orderNo);
            //sub.setDistributeType(OrderMainConst.OrderSub_DistributeType_NormalDeliver.getCode());// 换货出库单默认为天虹配送
            sub.setDistributeType(srcSub.getDistributeType());//默认获取原销售订单的入库方式
            sub.setDeliveryMerchantNo(srcSub.getDeliveryMerchantNo());//默认获取原销售订单的物流商ID
            sub.setDeliveryMerchantName(srcSub.getDeliveryMerchantName());//默认获取原销售订单的物流商名称
            sub.setCheckCode(srcSub.getCheckCode()); //验证码与原销售订单相同
            if(null != srcSub.getSelfFetchAddress())
            {
                sub.setSelfFetchAddress(srcSub.getSelfFetchAddress());//自提点id
            }
            sub.setOrderSubRelatedOrigin(chgOrderSub.getOrderSubNo());
           
            if(null==chgOrderSub.getChgOutUserName() || "".equals(chgOrderSub.getChgOutUserName())){
            	 sub.setUserName(chgOrderSub.getUserName());
            }else{
            	 sub.setUserName(chgOrderSub.getChgOutUserName());
            }
            sub.setPhoneNum(chgOrderSub.getPhoneNum());
            if(null==chgOrderSub.getChgOutAddressCode() || "".equals(chgOrderSub.getChgOutAddressCode())){
            	 sub.setAddressCode(chgOrderSub.getAddressCode());
            }else{
            	 sub.setAddressCode(chgOrderSub.getChgOutAddressCode());
            }
            if(null==chgOrderSub.getChgOutmobPhoneNum() || "".equals(chgOrderSub.getChgOutmobPhoneNum())){
            	 sub.setMobPhoneNum(chgOrderSub.getMobPhoneNum());
		    }else{
		         sub.setAddressCode(chgOrderSub.getChgOutmobPhoneNum());
		    }
            if(null==chgOrderSub.getChgOutAddressDetail() || "".equals(chgOrderSub.getChgOutAddressDetail())){
            	 sub.setAddressDetail(chgOrderSub.getAddressDetail());
		    }else{
		    	 sub.setAddressDetail(chgOrderSub.getChgOutAddressDetail());
		    }
            sub.setDeliveryPriority(chgOrderSub.getDeliveryPriority());//换货：是否需取回原商品
            sub.setTransportFee(main.getTransportFee());
            sub.setDateCreated(DateUtil.getNowDate());
            sub.setBillType(CommonConst.OrderMain_BillType_Positive.getCodeLong());
            String orderSubNo = this.orderNoService.getOrderSubNoByOrderNo(orderNo, 1);
            sub.setOrderSubNo(orderSubNo);
            long orderSubId = this.orderSubService.save(sub);

            // 支付方式:换货抵扣（其中包含运费处理）
            this.orderPayService.saveChangePaidByOrderMain(main);

            // 出库单明细
            OrderItem newItem;
            int count = 0;
            for (OrderRetChgItem item : chgOrderSub.getOrderRetChgItems()) {
                count++;
                newItem = new OrderItem();
                String srcItemNo = item.getRefOrderItemNo();// 原OMS明细行号
                OrderItem srcOrderItem = this.orderItemService.getByOrderItemNo(srcItemNo); // 原OMS明细
                try {
                	orderItemCopy.copy(srcOrderItem, newItem,  new OrderConverter());
                    //BeanUtils.copyProperties(newItem, srcOrderItem);// 复制原明细行的属性
                } catch (Exception e) {
                    throw new RuntimeException("出库单明细copy属性异常");
                }
                newItem.setSaleNum(item.getSaleNum());
                newItem.setPayAmount(item.getPayAmount());
                newItem.setSaleTotalMoney(item.getUnitPrice().multiply(new BigDecimal(item.getSaleNum())));
                
                newItem.setPromotionType(srcOrderItem.getPromotionType());
                newItem.setOrderNo(orderNo);
                newItem.setIdOrder(orderId);
                newItem.setOrderSubNo(orderSubNo);
                newItem.setIdOrderSub(orderSubId);
                newItem.setPromotionType(CommonConst.OrderItem_PromotionType_Normal.getCode());//出库单的促销类型都默认为普通商品
                newItem.setPromotionCode(null);
                String itemNo = orderNoService.getOrderItemNoBySubOrderNo(orderSubNo, count);
                newItem.setOrderItemNo(itemNo);
                if (null != item.getProductPropertyFlag()
                        && item.getProductPropertyFlag() == CommonConst.OrderItem_ProductPropertyFlag_Yes.getCodeLong()) {
                    // 色码款商品换不同款码
                    newItem.setBarCode(item.getChg_barCode());
                    newItem.setCommodityCode(item.getChg_commodityCode());
                    newItem.setCommodityName(item.getChg_commodityName());
                    newItem.setSkuNo(item.getChg_skuNo());
                }
                newItem.setIsUnionBiz(srcOrderItem.getIsUnionBiz()); //自营联营
                newItem.setNormalPrice(srcOrderItem.getNormalPrice()); //原价
                newItem.setInventoryPrice(srcOrderItem.getInventoryPrice());//出库成本价
                
                //判断是否为组合商品
                if(OrderMainConst.OrderItem_OrderItemClass_SUITE.getCode().equals(newItem.getOrderItemClass())){
                    logger.info("换货出库单"+orderNo+"，构建组合明细starting...");
                    List<OrderCombineRelation> list = srcOrderItem.getOrderCombineRelations();
                    for(OrderCombineRelation combineRela:list){
                        try{
                            OrderCombineRelation ocr = new OrderCombineRelation();
                            BeanUtils.copyProperties(ocr,combineRela);
                            ocr.setOrderItemNo(itemNo);
                            ocr.setCombineNo(combineRela.getCombineNo());
                            orderCombineRelationService.save(ocr);
                        }catch(Exception e){
                            logger.info("换货出库单"+orderNo+"copy组合商品明细失败：{}", e.getMessage());
                        }
                    }
                }
                Long orderItemId = this.orderItemService.save(newItem);

                // 更新换货的明细关联（将换货出库单的信息更新至order_ret_change中）
                Long retChangeId = item.getChg_retChangeId();
                OrderRetChange retChg = this.orderRetChangeService.get(retChangeId);
                if (null != retChg) {
                    retChg.setIdNewOrder(orderId);
                    retChg.setIdNewOrderSub(orderSubId);
                    retChg.setIdNewOrderItem(orderItemId);
                    retChg.setNewOrderNo(orderNo);
                    retChg.setNewOrderSubNo(orderSubNo);
                    retChg.setNewOrderItemNo(itemNo);
                    this.orderRetChangeService.update(retChg);
                }
            }
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error(e.getMessage());
            throw new RuntimeException(errorMsg_AddExcOrder_Fail);
        }
        return main.getOrderNo();
    }

    /**
     * 创建退货的剩余可退记录
     * 
     * @param rcItem 退换货明细
     * @param retChange 退换货明细与原订单明细的关联
     * @throws Exception
     */
    public String saverRetOrderRecord(OrderRetChgItem rcItem, OrderRetChange retChange) {
        String result = this.OK;
        // 获取原订单明细
        String refOrderItemNo = rcItem.getRefOrderItemNo();
        OrderItem refOrderItem = orderItemService.getByOrderItemNo(refOrderItemNo);
        // 查询当前商品的已退货数量
        OrderRetAdd orderRetAdd = orderRetAddService.getByOrderItemNo(refOrderItemNo);
        if (null != orderRetAdd) {
            // 更新
            if (rcItem.getSaleNum().intValue() > orderRetAdd.getRemainNum().intValue()) {// 计算数量
                return errorMsgRemainNum;
            }
            int newReturnedNum = orderRetAdd.getReturnedNum().intValue() + rcItem.getSaleNum().intValue();
            int remainNum = refOrderItem.getSaleNum().intValue() - newReturnedNum;// 计算剩余的可退换数量
            orderRetAdd.setReturnedNum((long) newReturnedNum);
            orderRetAdd.setRemainNum((long) remainNum);
            if (rcItem.getPayAmount().compareTo(orderRetAdd.getRemainMoney()) > 0 || (null == orderRetAdd.getRemainMoney())) {// 计算金额
                return errorMsgRemainMoney;
            }
            BigDecimal newReturnedMoney = orderRetAdd.getReturnedMoney().add(rcItem.getPayAmount());
            BigDecimal remainMoney = refOrderItem.getPayAmount().subtract(newReturnedMoney);
            orderRetAdd.setReturnedMoney(newReturnedMoney);
            orderRetAdd.setRemainMoney(remainMoney);
            orderRetAddService.update(orderRetAdd);
        } else {
            // 新增
            int remainNum = refOrderItem.getSaleNum().intValue() - rcItem.getSaleNum().intValue();// 计算剩余的可退换数量
            BigDecimal remainMoney = refOrderItem.getPayAmount().subtract(rcItem.getPayAmount());
            orderRetAdd = new OrderRetAdd();
            orderRetAdd.setIdOrder(retChange.getIdOldOrder());
            orderRetAdd.setIdOrderItem(refOrderItem.getId());
            orderRetAdd.setOrderNo(retChange.getOldOrderNo());
            orderRetAdd.setOrderItemNo(retChange.getOldOrderItemNo());
            orderRetAdd.setReturnedNum(rcItem.getSaleNum());
            orderRetAdd.setRemainNum((long) remainNum);
            orderRetAdd.setReturnedMoney(rcItem.getPayAmount());
            orderRetAdd.setRemainMoney(remainMoney);
            orderRetAddService.save(orderRetAdd);
        }
        return result;
    }

    /**
     * 取消逆向单时，还原可退数量
     * @param orderSubR
     */
    private void saveBackRetOrderRecord(OrderSub orderSubR){
        if(null==orderSubR || null==orderSubR.getOrderRetChgItems()){
            return;
        }
        try{
            for(OrderRetChgItem rcItem :orderSubR.getOrderRetChgItems()){
                // 获取原订单明细
                String refOrderItemNo = rcItem.getRefOrderItemNo();
                OrderItem refOrderItem = orderItemService.getByOrderItemNo(refOrderItemNo);
                // 查询当前商品的已退货数量
                OrderRetAdd orderRetAdd = orderRetAddService.getByOrderItemNo(refOrderItemNo);
                if(null==orderRetAdd){
                    continue;
                }
                if(orderRetAdd.getReturnedNum().longValue()==rcItem.getSaleNum()){
                    //直接删除
                    this.orderRetAddService.delete(orderRetAdd);
                }else{
                    //更新
                    int newReturnedNum = orderRetAdd.getReturnedNum().intValue() - rcItem.getSaleNum().intValue();
                    int newRemainNum = orderRetAdd.getRemainNum().intValue() + rcItem.getSaleNum().intValue();// 计算剩余的可退换数量
                    orderRetAdd.setReturnedNum((long) newReturnedNum);
                    orderRetAdd.setRemainNum((long) newRemainNum);
                    BigDecimal newReturnedMoney = orderRetAdd.getReturnedMoney().subtract(rcItem.getPayAmount());
                    BigDecimal newRemainMoney = orderRetAdd.getRemainMoney().add(rcItem.getPayAmount());
                    orderRetAdd.setReturnedMoney(newReturnedMoney);
                    orderRetAdd.setRemainMoney(newRemainMoney);
                    orderRetAddService.update(orderRetAdd);
                }
            }
        }catch(Exception e){
            logger.info("取消退货单还原可退数量异常：{}", e);
        }
    }
    
    /**
     * 取消库存扣减失败的订单，删除异常记录
     * @param orderSubNo
     */
    private void deleteOrderErrorLogForInventoryFail(String orderSubNo){
        if(StringUtils.isBlank(orderSubNo)){
            return;
        }
        try{
            OrdiErrOptLog ordiErrOptLog = ordiErrOptLogService.getByField(OrdiErrOptLog_.orderSubNo, orderSubNo);
            if(null!=ordiErrOptLog){
                ordiErrOptLogService.delete(ordiErrOptLog);
            }
        }catch(Exception e){
            logger.info("取消库存扣减失败订单，删除异常记录exception：", e);
        }
    }
    
    
   
    /**
     * 取消订单（24小时未支付取消、客服审核时取消订单）
     * 1、订单已支付：会创建退款单
     * 2、订单未支付：会自动加回MY卡
     * @param orderNo
     * @param cancelScene 取消场景：1、顾客取消(未审核、未支付)，2、客服审核时直接取消， 3、未出库客服取消
     */
    public ResultDTO doSaveCancelOrder(String orderSubNo, CancelOrderConst cancelSceneEnum) {
    	ResultDTO resultDTO = new ResultDTO();
    	resultDTO.setResult(-1);
        if (StringUtils.isEmpty(orderSubNo)) {
        	resultDTO.setErrorMessage("子订单号不能为null.");
            return resultDTO;
        }
        // 获取订单
        String orderNo = this.orderNoService.getOrderNoBySubNo(orderSubNo);
        OrderMain orderMain = this.orderMainService.findByOrderNo(orderNo);
        if (null == orderMain) {
        	resultDTO.setErrorMessage("主订单号不能为null.");
            return resultDTO;
        }
        String statusTotal = orderMain.getStatusTotal();
        // 设置审核人
        /*String userName = "";
        if(null!=userService.getLoginUser()){
            userName = userService.getLoginUser().getUserName();
        }else{
            userName = orderMain.getCustomerName();
        }*/
        orderMain.setConfirmerName(userUtil.getLoginUserRealName());
        
        OrderSub orderSub = null;
        for (OrderSub sub : orderMain.getOrderSubs()) {
            if (orderSubNo.equals(sub.getOrderSubNo())) {
                orderSub = sub;
                logger.info(orderSub.getIdOrder()+"");
                break;
            }
        }
        
        OrderStatusAction orderStatusAction = null;
        // 一、获取取消场景
        if (cancelSceneEnum.equals(CancelOrderConst.CancelOrder_Scene_Customer)) { // 1、顾客自己取消订单
            // 需判断是在线支付还是货到付款,在线支付：支付中0130->未支付取消0131；货到付款：人工审核中0141-->人工审核取消0142
            if (OrderStatus.ORDER_PAYING.getCode().equals(statusTotal)) {
                orderStatusAction = OrderStatusAction.S013031;
            } else if (OrderStatus.ORDER_AUDITING_MANUAL.getCode().equals(statusTotal)) {
                orderStatusAction = OrderStatusAction.S014142;
            } else {
                // 错误
                resultDTO.setErrorMessage(String.format("订单状态应该为0130或0141,实际状态%s，无法订单取消.", statusTotal));
                return resultDTO;
            }
        } else if (cancelSceneEnum.equals(CancelOrderConst.CancelOrder_Scene_Saler)
                && OrderStatus.ORDER_AUDITING_MANUAL.getCode().equals(statusTotal)) {
            // 2、客服审核订单时直接取消
            orderStatusAction = OrderStatusAction.S014142;
        } else if (cancelSceneEnum.equals(CancelOrderConst.CancelOrder_Scene_VALIDATED)
                && OrderStatus.ORDER_VALIDATED.getCode().equals(statusTotal)) {
            // 3、未出库取消订单
            orderStatusAction = OrderStatusAction.S016042;
        } else if (cancelSceneEnum.equals(CancelOrderConst.CancelOrder_Scene_InventoryFail)
                && OrderStatus.ORDER_INVENTORY_DEDUCT_FAILED.getCode().equals(statusTotal)) {
            // 4、库存扣减失败取消
            orderStatusAction = OrderStatusAction.S015153;
            deleteOrderErrorLogForInventoryFail(orderSubNo);// 删除库存扣减失败的异常记录
        }
        
        if(orderStatusAction == null){
            resultDTO.setErrorMessage(String.format("没有找到合适状态：场景%s,实际当前状态%s", cancelSceneEnum.getCode(), statusTotal));
            return resultDTO;
        }

        // 二、库存解锁、取消出库
        boolean inventoryResult = true;
        try {
            if(cancelSceneEnum.equals(CancelOrderConst.CancelOrder_Scene_VALIDATED)) { // 库存已扣减未出库取消
                // 取消出库接口
                inventoryResult = this.orderCreateService.inventoryCancel(orderNo);
            } else {//库存未扣减
                // 解锁接口
                List<OrderMain> orderMainList = new ArrayList<OrderMain>();
                orderMainList.add(orderMain);
//                this.orderCreateService.inventoryUnLock(orderMainList); //此接口没有返回信息，故停用
                InventoryLockOutputDTO output = orderCreateService.inventoryUnLockByOrderNo(orderMain.getOrderNo());
                if (NumberUtils.toInt(output.getReturn_status()) >= 0) {
                   logger.info("取消订单库存解锁成功，单号："+orderSubNo); //成功
                } else {
                    logger.info("取消订单库存解锁失败，单号："+orderSubNo+"，库存接口返回信息"+output.getReturn_msg());
                    resultDTO.setErrorMessage("库存解锁失败，库存接口返回信息："+output.getReturn_msg());//失败
                    return resultDTO;
                }
            }
        }catch (Exception e) {
            resultDTO.setErrorMessage("调库存系统异常：" + e.getMessage());
            return resultDTO;
        }
        if(!inventoryResult){
            resultDTO.setErrorMessage("取消出库失败.");
            return resultDTO;
        }
        String loginUserName = "";//当前登陆者
        User user = userService.getLoginUser();
        if(null!=user){
            loginUserName = user.getUserName();
            if(StringUtils.isEmpty(loginUserName)){
                loginUserName = String.valueOf(user.getId());
            }
        }
        // 三、取消订单：更改订单状态
        boolean isStatus = this.orderStatusService.saveProcess(orderSubNo, orderStatusAction, loginUserName, new Date(), null);
        //已生效待发货取消
        if(OrderStatusAction.S016042.equals(orderStatusAction) || OrderStatusAction.S014142.equals(orderStatusAction)){
            isStatus = this.orderStatusService.saveProcess(orderSubNo, OrderStatusAction.S014253, loginUserName, new Date(), null);//连续修改
        }
        if(!isStatus){
        	resultDTO.setErrorMessage(String.format("无法订单取消.单号%s, 场景%s, 实际状态%s", orderNo, cancelSceneEnum.getDesc(), statusTotal));
            return resultDTO;
        }

        // 四、是否有预付款：购物券、MY卡支付
        boolean couponPayFlag = checkOrderPayType(orderMain,PayMode.COUPON.getCode()); // 购物券支付
        boolean myCardPayFlag = checkOrderPayType(orderMain,PayMode.CARD.getCode()); // MY卡 支付
        
        List<OrderPay> opList = orderPayService.findByField(OrderPay_.orderNo, orderMain.getOrderNo());
        boolean r3Flag = false;
		if (opList != null && !opList.isEmpty()) {
			r3Flag = true;
		}
       
        if(couponPayFlag || myCardPayFlag){
            r3Flag = true;
        }
        //判断是否为换货出库单
        if(!OrderMainConst.OrderMain_OrderCategory_ChangeOut.getCode().equals(orderMain.getOrderCategory())){
            // 五、对于已支付订单，创建退款单【剔除换货出库单】
//            OrderSub targetOrderSub = orderSub;
            // 需考虑在线支付订单是否已支付：已付款则需生成退款单
            boolean addRetuenMoneyOrderFlag = OrderStatus.Order_PayStatus_Success.getCode().equals(
                    orderMain.getStatusPay());
            boolean hasPaidAllByMyCard = checkPayAllByMyCard(orderMain);//MY卡全部支付
            if(addRetuenMoneyOrderFlag){
                OrderSub newOrderSub = this.saveRefundOrder(orderMain, orderSub);
                if (null == newOrderSub) {
                    throw new BusinessException("创建退款单失败.");
                }
            }else if(myCardPayFlag || hasPaidAllByMyCard){
                // 六、订单未支付，MY卡已支付 需退MY卡
                this.returnChangeOrderService.returnMyCard(orderNo, "", orderMain, true);
            }
        }
        // 七、往R3同步的中间表写数据[mycard  优惠券  在线支付]
        if (r3Flag) {
        	  orderStatusSyncLogService.saveAndcreate(orderMain, null,
           			CommonConst.OrderStatusSyncLog_SyncScene_Cancel.getCode());
        }
        logger.info(orderSub.getIdOrder()+"");
        resultDTO.setResult(1);
        return resultDTO;
    }

    /**
     * 退款单
     * 
     * @param orderMain
     * @param orderSub
     * @return
     */
    public OrderSub saveRefundOrder(OrderMain orderMain, OrderSub orderSub) {
        OrderSub newSub = null;
        if (null == orderMain || null == orderSub) {
            return null;
        }
        try {
            Long billType = CommonConst.OrderMain_BillType_Negative.getCodeLong();
            // 主订单
            OrderMain newMain = new OrderMain();
            orderMainCopy.copy(orderMain, newMain, new OrderConverter());
            newMain.setOrderNo("");
            newMain.setStatusPay("");
            newMain.setStatusConfirm("");
            newMain.setStatusTotal(OrderStatus.RET_ORDER_CREATING.getCode());
            newMain.setOrderRelatedOrigin(orderMain.getOrderNo());// 关联的原订单no
            newMain.setBillType(billType);
            newMain.setBatchNum("");
            newMain.setTransportFee(new BigDecimal(0));
            
            newMain.setIfNeedRefund(CommonConst.CommonBooleanTrueLong.getCodeLong());// 需退款
            newMain.setOrderCategory(OrderMainConst.OrderMain_OrderCategory_Refund.getCode());// 退款单
        
            Long newMainId = this.orderMainService.save(newMain);
            String newMainNo = this.orderNoService.getOrderNoByOrderId(String.valueOf(newMainId));
            newMain.setOrderNo(newMainNo);
            this.orderMainService.update(newMain);
            // 子订单
            newSub = new OrderSub();
            orderSubCopy.copy(orderSub, newSub,  new OrderConverter());
            String newSubNo = this.orderNoService.getOrderSubNoByOrderNo(newMainNo, 1);
            newSub.setOrderSubNo(newSubNo);
            newSub.setIdOrder(newMainId);
            newSub.setOrderNo(newMainNo);
            newSub.setBillType(billType);
            newSub.setOrderSubRelatedOrigin(orderSub.getOrderSubNo());// 关联的原子订单no
            newSub.setOrderMain(null);
            newSub.setTransportFee(new BigDecimal(0));
            newSub.setId(null);
            Long newSubId = this.orderSubService.save(newSub);
            // 明细
            int count = 0;
            List<OrderItem> itemList  = orderSub.getOrderItems();
            if (itemList != null && !itemList.isEmpty()) {
                for (OrderItem item : itemList) {
                    count++;
                    OrderRetChgItem newItem = new OrderRetChgItem();
                    BeanUtils.copyProperties(newItem, item);
                    //CopyUtil.Copy(newItem, item);
                    String newItemNo = this.orderNoService.getOrderItemNoBySubOrderNo(newSubNo, count);
                    newItem.setIdOrder(newMainId);
                    newItem.setIdOrderSub(newSubId);
                    newItem.setOrderNo(newMainNo);
                    newItem.setOrderSubNo(newSubNo);
                    newItem.setOrderItemNo(newItemNo);
                    newItem.setBillType(billType);
                    this.orderRetChgItemService.save(newItem);
                }
            }
            List<OrderPay> opList = orderMain.getOrderPays();
            if (opList != null && !opList.isEmpty()) {
                for (OrderPay pay : orderMain.getOrderPays()) {
                    if(pay.getPayCode().equals(PayMode.COUPON.getCode())){
                        continue; //购物券不作为退款
                    }
                    OrderPay retPay = new OrderPay();
                    orderPayCopy.copy(pay, retPay, null);
                    retPay.setId(null);
                    retPay.setOrderNo(newMainNo);
                    //retPay.setIsPrePay("");
                    retPay.setIdOrder(newMainId);
                    retPay.setPayTime(null);
                    retPay.setOrderMain(null);
                    this.orderPayService.save(retPay);
                }
            }
            // 最后：订单状态扭转
            logger.info(orderSub.getIdOrder()+"");
            this.orderStatusService.saveProcess(newSubNo, OrderStatusAction.S021020, null, null, null);
            this.orderStatusService.saveProcess(newSubNo, OrderStatusAction.S022070, null, null, null);//对于取消订单的退款单，直接进入退款中
            logger.info(orderSub.getIdOrder()+"");
        } catch (Exception e) {
            logger.info("{}", e);
            return null;
        }
        return newSub;
    }

    /**
     * 更新明细
     * 
     * @param catalogryType 退换货类型：退货，换货
     * @param items
     * @param flag service中有定义取值（update|delete）
     */
    public void updateRetChgDetail(String orderCategory, List<OrderRetChgItem> items, String flag) {
        if (StringUtils.isEmpty(orderCategory) || StringUtils.isEmpty(flag) || null == items || items.size() == 0) {
            return;
        }
        for (OrderRetChgItem item : items) {
            if (flag.equals(this.updateDetailFlag_delete)) {
                this.orderRetChgItemService.delete(item);
            } else if (flag.equals(this.updateDetailFlag_update)) {
                this.orderRetChgItemService.update(item);
            }
            // 如果是退货，则更新累计退货数量
            if (orderCategory.equals(OrderMainConst.OrderMain_OrderCategory_Return.getCode())) {
                BigDecimal saleNum = new BigDecimal(item.getSaleNum());
                BigDecimal payAmount = item.getPayAmount();
                String refOrderItemNo = item.getRefOrderItemNo();
                if (StringUtils.isEmpty(refOrderItemNo)) {
                    // 如果原销售明细no为空，则从明细关联表中获取
                    OrderRetChange orderRetChange = this.orderRetChangeService.getByField(
                            OrderRetChange_.retOrderItemNo, item.getOrderItemNo());
                    if (null == orderRetChange) {
                        return;
                    }
                    refOrderItemNo = orderRetChange.getOldOrderItemNo();
                }

                OrderRetAdd orderRetAdd = this.orderRetAddService.getByField(OrderRetAdd_.orderItemNo, refOrderItemNo);
                if (null == orderRetAdd) {
                    return;
                }
                BigDecimal newRemainNum = (new BigDecimal(orderRetAdd.getRemainNum())).add(saleNum);
                BigDecimal newReturnedNum = (new BigDecimal(orderRetAdd.getReturnedNum())).subtract(saleNum);
                BigDecimal newRemainMoney = (orderRetAdd.getRemainMoney()).add(payAmount);
                BigDecimal newReturnedMoney = (orderRetAdd.getReturnedMoney()).subtract(payAmount);
                orderRetAdd.setRemainNum(newRemainNum.longValue());
                orderRetAdd.setReturnedNum(newReturnedNum.longValue());
                orderRetAdd.setRemainMoney(newRemainMoney);
                orderRetAdd.setReturnedMoney(newReturnedMoney);
                this.orderRetAddService.update(orderRetAdd);
            }
        }
    }

    /**
     * 更新明细
     * 
     * @param catalogryType 退换货类型：退货，换货
     * @param items
     * @param flag service中有定义取值（update|delete）
     */
    public void updateRetChgDetailItem(String orderCategory, OrderRetChgItem item, String flag) {
        if (StringUtils.isEmpty(orderCategory) || StringUtils.isEmpty(flag) || null == item) {
            return;
        }
        if (flag.equals(this.updateDetailFlag_delete)) {
            this.orderRetChgItemService.delete(item);
        } else if (flag.equals(this.updateDetailFlag_update)) {
            this.orderRetChgItemService.update(item);
        }
        // 如果是退货，则更新累计退货数量
        if (orderCategory.equals(OrderMainConst.OrderMain_OrderCategory_Return.getCode())) {
            BigDecimal saleNum = new BigDecimal(item.getSaleNum());
            BigDecimal payAmount = item.getPayAmount();
            OrderRetChange orderRetChange = this.orderRetChangeService.getByField(OrderRetChange_.retOrderItemNo,
                    item.getOrderItemNo());
            if (null == orderRetChange) {
                return;
            }
            OrderRetAdd orderRetAdd = this.orderRetAddService.getByField(OrderRetAdd_.orderItemNo,
                    orderRetChange.getOldOrderNo());
            if (null == orderRetAdd) {
                return;
            }
            BigDecimal newRemainNum = (new BigDecimal(orderRetAdd.getRemainNum())).add(saleNum);
            BigDecimal newReturnedNum = (new BigDecimal(orderRetAdd.getReturnedNum())).subtract(saleNum);
            BigDecimal newRemainMoney = (orderRetAdd.getRemainMoney()).add(payAmount);
            BigDecimal newReturnedMoney = (orderRetAdd.getReturnedMoney()).subtract(payAmount);
            orderRetAdd.setRemainNum(newRemainNum.longValue());
            orderRetAdd.setReturnedNum(newReturnedNum.longValue());
            orderRetAdd.setRemainMoney(newRemainMoney);
            orderRetAdd.setReturnedMoney(newReturnedMoney);
            this.orderRetAddService.update(orderRetAdd);
        }

    }
	
	/**
	 * 审核意向单
	 */
    public ResultDTO updateApproveSaleAfterOrder(String orderSubNoR){
	    ResultDTO result = new ResultDTO();
	    // 获取逆向主订单
	    String orderMainNoR = this.orderNoService.getOrderNoBySubNo(orderSubNoR);
        OrderMain orderMainR = orderMainService.getByField(OrderMain_.orderNo,orderMainNoR);
        // 获取逆向子订单
        OrderSub orderSubR = orderSubService.getByOrderSubNo(orderSubNoR);
        if(null==orderMainR || null==orderSubR){
            result.setErrorMessage("无法找到对应的订单："+orderSubNoR);
            return result;
        }
        /*if(null != userService.getLoginUser())
        {
            // 设置审核人名称
            orderMainR.setConfirmerName(userService.getLoginUser().getUserName());
        }*/
        
        // 设置审核人名称
        orderMainR.setConfirmerName(userUtil.getLoginUserRealName());
        
        String orderCategory = orderMainR.getOrderCategory();//意向单类型
        if(OrderMainConst.OrderMain_OrderCategory_Change.getCode().equals(orderCategory))
        {
            //判断换货出库单的状态是否为：人工审核中0141(原因：创建出库单时可能锁库接口异常)
            OrderMain chgOutOrderMain = orderMainService.getByField(OrderMain_.orderNo, orderMainR.getChgOurOrderNo());// 获取换货出库单
            if(null!=chgOutOrderMain){
                String statusTotal = chgOutOrderMain.getStatusTotal();
                if(OrderStatus.ORDER_AUDITING_MANUAL.getCode().equals(statusTotal) || OrderStatus.ORDER_VALIDATED.getCode().equals(statusTotal)
                        || OrderStatus.ORDER_SENT.getCode().equals(statusTotal) || OrderStatus.ORDER_POD_SENT.getCode().equals(statusTotal)){
                    //人工审核中、已生效待发货 、已发货 直接跳过
                }else{
                    result.setErrorMessage("换货出库单状态异常："+OrderStatus.ORDER_AUDITING_MANUAL.getDesc());
                    return result;
                }
            }
        }
//        boolean isStore = OrderMainConst.OrderSub_DistributeType_ReturnStore.getCode().equals(orderSubR.getDistributeType()); //门店代退
//        boolean isFetch = OrderMainConst.OrderSub_DistributeType_PickFromDoor.getCode().equals(orderSubR.getDistributeType()); //上门取货
        boolean updateIntegralFlag = false;// 是否需更新积分
        if (commonUtilService.longToBoolean(orderMainR.getIfNeedRefund())) { // 是否需退款
            BigDecimal point = orderMainR.getTotalGivePoints();
            if (point!=null && point.compareTo(new BigDecimal(0)) > 0) {
                updateIntegralFlag = true;
            }
        }
        String userName = null; //当前登录用户名
        User user = userService.getLoginUser();
        if(null!=user){
            userName = user.getUserName();
        }
        //1、扣减积分：需退款，且为“退货”(换货和拒收不扣减积分)，则需在审核时扣除积分[调用会员接口]
        if(updateIntegralFlag && OrderMainConst.OrderMain_OrderCategory_Return.getCode().equals(orderMainR.getOrderCategory())){
            ResultDTO integralDTO = returnChangeOrderService.handelIntegral("sys", orderMainR.getOrderNo(), false,false);//扣减积分
            if(integralDTO.getResult()==CommonConst.Common_Succeed_FAIL.getCodeInt()){
                result = integralDTO;
                return result;
            }
        }
        //2、售后意向单传输富勒WMS【不影响主流程】
        boolean wmsResult = returnChangeOrderService.sendOmsToWmsSaleAfterOrder(orderSubNoR); //因为积分已经扣除，so就算异常也不处理
        //3、售后意向单传输TMS【不影响主流程】
        boolean tmsResult = returnChangeOrderService.sendOmsToTmsSaleAfterOrder(orderSubNoR);
        //4、更新审核状态
        OrderStatusAction targetAction = null;
       /* if(isStore){
            targetAction = OrderStatusAction.S022033;
        }else if(isFetch){
            targetAction = OrderStatusAction.S022032;
        }else{*/
            targetAction = OrderStatusAction.S022031;
//        }
        boolean approveFlag = orderStatusService.saveProcess(orderSubNoR, targetAction, userName, new Date(), null);
        //5、非门店代退，直接进入质检
        /*if(!isStore && isFetch){
            orderStatusService.saveProcess(orderSubNoR, OrderStatusAction.S023240, userName, new Date(), null);
        }else if(!isStore && !isFetch){*/
            orderStatusService.saveProcess(orderSubNoR, OrderStatusAction.S023140, userName, new Date(), null);
//        }
        //6、 如果是换货，审核换货出库单
        if(OrderMainConst.OrderMain_OrderCategory_Change.getCode().equals(orderCategory))
        {
            try {
                String auditResult = orderCreateService.manualAudit(orderMainR.getChgOurOrderNo(), true);
            } catch (Exception e) {
                logger.info("{}", e);
                result.setErrorMessage("审核换货出库单异常：" + e.getMessage());
            }
        }else if(OrderMainConst.OrderMain_OrderCategory_Reject.getCode().equals(orderCategory))
        {
            //7、拒收，则需更改原销售订单为"拒收退货"
            try{
                OrderMain srcOrderMain = orderMainService.getByField(OrderMain_.orderNo, orderMainR.getOrderRelatedOrigin());
                if(null!=srcOrderMain){
                    String srcOrderSubNo = orderSubR.getOrderSubRelatedOrigin();
                    if(StringUtils.isEmpty(srcOrderSubNo)){
                        srcOrderSubNo = orderNoService.getDefaultOrderSubNoByOrderNo(orderMainR.getOrderRelatedOrigin());
                    }
                    String srcStatusTotal = srcOrderMain.getStatusTotal();
                    if(srcStatusTotal.equals(OrderStatus.ORDER_SENT.getCode())){
                        orderStatusService.saveProcess(srcOrderSubNo, OrderStatusAction.S017081, userName, new Date(), null);
                    }else if(srcStatusTotal.equals(OrderStatus.ORDER_POD_SENT.getCode())){
                        orderStatusService.saveProcess(srcOrderSubNo, OrderStatusAction.S017181, userName, new Date(), null);
                    }
                }
            }catch(Exception e){
                logger.info("审核拒收单"+orderSubNoR+",更改原销售订单状态异常：{}", e);
                result.setErrorMessage("原销售订单拒收退货异常：" + e.getMessage());
            }
        }
        
	    return result;
	}
    
    /**
     * 取消意向单
     * @param orderSubNoR
     * @return
     */
    public ResultDTO updateCancelSaleAfterOrder(String orderSubNoR){
        ResultDTO result = new ResultDTO();
        // 获取逆向主订单
        String orderMainNoR = orderNoService.getOrderNoBySubNo(orderSubNoR);
        OrderMain orderMainR = orderMainService.getByField(OrderMain_.orderNo,orderMainNoR);
        // 获取逆向子订单
        OrderSub orderSubR = orderSubService.getByOrderSubNo(orderSubNoR);
        if(null==orderMainR || null==orderSubR){
            result.setErrorMessage("无法找到对应的订单："+orderSubNoR);
            return result;
        }
        // 设置审核人
        if(null != userService.getLoginUser())
        {
            orderMainR.setConfirmerName(userService.getLoginUser().getUserName());
        }
        String orderCategory = orderMainR.getOrderCategory();//意向单类型
        String chgOutOrderNo = orderMainR.getChgOurOrderNo();//换货出库单单号
        boolean updateIntegralFlag = false;// 是否需更新积分
        if (commonUtilService.longToBoolean(orderMainR.getIfNeedRefund())) { // 是否需退款
            BigDecimal point = orderMainR.getTotalGivePoints();
            if(point != null){
	            if (point.compareTo(new BigDecimal(0)) > 0) {
	                updateIntegralFlag = true;
	            }
            }
        }
        //判断当前审核状态：如果已审核则已传输至WMS，需将取消动作传输至WMS
        String statusConfirm = orderMainR.getStatusConfirm();
        String statusTotal = orderMainR.getStatusTotal();
        if(statusConfirm.equals(OrderStatus.StatusTypeCode_refundOrder_StatusConfirm_Pass.getCode())){ 
            //已审核
            OrderStatusAction statusActionR = null;
            boolean storeFlag = false;//门店代退、网天退款标识
            /*if(OrderMainConst.OrderSub_DistributeType_ReturnStore.getCode().equals(orderSubR.getDistributeType())){
                if(new Long(CommonConst.OrderMain_IfNeedRefund_Yes.getCodeLong()).equals(orderMainR.getIfNeedRefund())){
                    storeFlag = true;
                }
            }*/
            if(OrderStatus.RET_ORDER_INSPECTION.getCode().equals(statusTotal)){
                if(!storeFlag){
                    statusActionR = OrderStatusAction.S024041; //门店代退("网天退款")在"质检中"是不能取消的(因为此时门店已收货)
                }
            }else if(OrderStatus.RET_ORDER_STORE_RECEIVE.getCode().equals(statusTotal)){
                statusActionR = OrderStatusAction.S023341;
            }else if(OrderStatus.RET_ORDER_INSPECT_FAIL.getCode().equals(statusTotal)){
                statusActionR = OrderStatusAction.S026061;
            }
            if(null == statusActionR){
                if(storeFlag){
                    result.setErrorMessage(String.format("状态异常：已审核,非门店代退中! %s",statusTotal));
                }else{
                    result.setErrorMessage(String.format("状态异常：已审核,非质检中! %s",statusTotal));
                }
                return result;
            }
            //已收货的意向单不能取消
            if(statusTotal.equals(OrderStatus.RET_ORDER_ON_THE_WAY.getCode()) || statusTotal.equals(OrderStatus.RET_ORDER_REFUNDING.getCode())){
                result.setErrorMessage("已收获的逆向订单不能取消");
                return result;
            }
            //如果是换货，则需获取换货出库单的状态【已出库的订单不能取消】
            OrderMain chgOutOrderMain = null;
            String chgOutSubNo = "";
            if(OrderMainConst.OrderMain_OrderCategory_Change.getCode().equals(orderCategory)){
                //获取出库单的状态
                chgOutOrderMain = this.orderMainService.getByField(OrderMain_.orderNo,chgOutOrderNo);
                chgOutSubNo = this.orderNoService.getDefaultOrderSubNoByOrderNo(chgOutOrderNo);
                if(null!=chgOutOrderMain){
                    String outStatusTotal = chgOutOrderMain.getStatusTotal();
                    if(outStatusTotal.equals(OrderStatus.ORDER_SENT.getCode()) || outStatusTotal.equals(OrderStatus.ORDER_POD_SENT.getCode())){
                        result.setErrorMessage("当前换货单所产生的出库订单："+chgOutSubNo+"已出库，不能取消");
                        return result;
                    }
                }
            }
            String userName = null; //当前登录用户名
            User user = userService.getLoginUser();
            if(null!=user){
                userName = user.getUserName();
            }
            
            //1、传输WMS
            boolean wmsFalg = this.returnChangeOrderService.sendOmsToWmsSaleAfterOrderCancel(orderSubNoR);
            if(!wmsFalg){
                result.setErrorMessage("富勒取消失败!");
                return result;
            }
            //2、更改当前审核状态：已审核
            boolean statusResult = orderStatusService.saveProcess(orderSubNoR, statusActionR, userName, new Date(), null);
            if(!statusResult){
                result.setErrorMessage("更新状态失败!");
                return result;
            }
            //3、传输TMS
            boolean tmsFlag = this.returnChangeOrderService.sendOmsToTmsSaleAfterOrderCancel(orderSubNoR);
            //4、补回积分：需退款，且为“退货”（换货和拒收审核时不扣减积分，无需补回），则需将积分还给顾客（因为审核时已将积分扣除）【不影响主流程】
            if(updateIntegralFlag && OrderMainConst.OrderMain_OrderCategory_Return.getCode().equals(orderMainR.getOrderCategory())){
                ResultDTO integralDTO = returnChangeOrderService.handelIntegral("sys", orderMainR.getOrderNo(), true,true);//加回积分
                if(integralDTO.getResult()==CommonConst.Common_Succeed_FAIL.getCodeInt()){
                    /*result = integralDTO; //进入异常日志表，需要后续异常处理
                    return result;*/
                }
            }
            // 5、如果是换货，取消换货出库单
            if(OrderMainConst.OrderMain_OrderCategory_Change.getCode().equals(orderCategory)){
                if(null!=chgOutOrderMain){
                    //根据状态获取对应的场景
                    CancelOrderConst cancelSceneEnum = CancelOrderConst.CancelOrder_Scene_VALIDATED;
                    if(OrderStatus.ORDER_INVENTORY_DEDUCT_FAILED.getCode().equals(chgOutOrderMain.getStatusTotal())){
                        cancelSceneEnum = CancelOrderConst.CancelOrder_Scene_InventoryFail;
                    }
                    ResultDTO dto = doSaveCancelOrder(chgOutSubNo, cancelSceneEnum);
                    if(dto.getResult()!=CommonConst.Common_Succeed_SUCCESS.getCodeInt()){
                        result.setResultMessage("取消出库单失败！"); 
                    }
                }
            }else if(OrderMainConst.OrderMain_OrderCategory_Reject.getCode().equals(orderCategory))
            {
                //6、拒收，则需恢复原销售订单状态，"拒收退货"--》已发货
                try{
                    OrderMain srcOrderMain = orderMainService.getByField(OrderMain_.orderNo, orderMainR.getOrderRelatedOrigin());
                    if(null!=srcOrderMain){
                        String srcOrderSubNo = orderSubR.getOrderSubRelatedOrigin();
                        if(StringUtils.isEmpty(srcOrderSubNo)){
                            srcOrderSubNo = orderNoService.getDefaultOrderSubNoByOrderNo(orderMainR.getOrderRelatedOrigin());
                        }
                        String srcStatusPay = srcOrderMain.getStatusPay();//支付状态(如果已支付则是已发货0170，如果是未支付则是货到付款已发货0171)
                        if(srcStatusPay.equals(OrderStatus.Order_PayStatus_Success.getCode())){
                            srcOrderMain.setStatusTotal(OrderStatus.ORDER_SENT.getCode());
                            this.orderMainService.update(srcOrderMain);
                        }else{
                            srcOrderMain.setStatusTotal(OrderStatus.ORDER_POD_SENT.getCode());
                            this.orderMainService.update(srcOrderMain);
                        }
                        //更改子订单的物流状态(已派单收货中、门店已接收)
                        OrderSub srcOrderSub = orderSubService.getByField(OrderSub_.orderSubNo, srcOrderSubNo);
                        if(null!=srcOrderSub){
                            if(srcOrderSub.getDistributeType().equals(CommonConst.OrderSub_Distribute_Type2.getCode())){
                                srcOrderSub.setLogisticsStatus(OrderStatus.Order_LogisticsStatus_StoreAccept.getCode());
                            }else{
                                srcOrderSub.setLogisticsStatus(OrderStatus.Order_LogisticsStatus_SendOrder.getCode());
                            }
                            orderSubService.update(srcOrderSub);
                        }
                    }
                }catch(Exception e){
                    logger.info("取消拒收单"+orderSubNoR+",更改原销售订单状态异常：{}", e);
                    result.setErrorMessage("取消拒收单后更改原销售订单状态异常：" + e.getMessage());
                }
            }
        }else if(statusConfirm.equals(OrderStatus.StatusTypeCode_refundOrder_StatusConfirm_Wait.getCode())){
            //当前审核状态：未审核
            try {
                orderStatusService.saveProcess(orderSubNoR, OrderStatusAction.S022021, null, null, null);
            } catch (Exception e) {
                logger.info("{}", e);
                result.setErrorMessage(""+e.getMessage());
            }   
            // 如果是换货，取消换货出库单
            if(OrderMainConst.OrderMain_OrderCategory_Change.getCode().equals(orderCategory))
            {
                String chgOutSubNo = this.orderNoService.getDefaultOrderSubNoByOrderNo(chgOutOrderNo);
                ResultDTO dto = doSaveCancelOrder(chgOutSubNo, CancelOrderConst.CancelOrder_Scene_Saler);
            }
        }
        //退货需还原累计退货表的数量
        this.saveBackRetOrderRecord(orderSubR);
        
        return result;
    }
    
    /**
     * BBC取消意向单
     * @param orderSubNoR
     * @return
     */
    public ResultDTO updateBbcCancelSaleAfterOrder(String orderSubNoR,String operateName){
        ResultDTO result = new ResultDTO();
        result.setResult(-1);
        // 获取逆向主订单
        String orderMainNoR = orderNoService.getOrderNoBySubNo(orderSubNoR);
        OrderMain orderMainR = orderMainService.getByField(OrderMain_.orderNo,orderMainNoR);
        // 获取逆向子订单
        OrderSub orderSubR = orderSubService.getByOrderSubNo(orderSubNoR);
        if(null==orderMainR || null==orderSubR){
            result.setErrorMessage("无法找到对应的订单");
            return result;
        }
        // 设置审核人
        orderMainR.setConfirmerName(operateName);
        
        boolean updateIntegralFlag = false;// 是否需更新积分
        if (commonUtilService.longToBoolean(orderMainR.getIfNeedRefund())) { // 是否需退款
            BigDecimal point = orderMainR.getTotalGivePoints();
            if(point != null){
              if (point.compareTo(new BigDecimal(0)) > 0) {
                  updateIntegralFlag = true;
              }
            }
        }
        //判断当前审核状态
        String statusConfirm = orderMainR.getStatusConfirm();
        String statusTotal = orderMainR.getStatusTotal();
        if(statusConfirm.equals(OrderStatus.StatusTypeCode_refundOrder_StatusConfirm_Pass.getCode())){ 
          //已审核
            OrderStatusAction statusActionR = null;
            //RET_ORDER_INSPECTION 订单质检中
            if(OrderStatus.RET_ORDER_INSPECTION.getCode().equals(statusTotal)){
                statusActionR = OrderStatusAction.S024041;//质检中 取消入库
            }
            if(null == statusActionR){
                result.setErrorMessage(String.format("状态异常：已审核,非质检中!"));
                return result;
            }
            
            //2、更改当前审核状态：已审核
            boolean statusResult = orderStatusService.saveProcess(orderSubNoR, statusActionR, operateName, new Date(), null);
            if(!statusResult){
                result.setErrorMessage("更新状态失败!");
                return result;
            }
           //4、补回积分：需退款，且为“退货”（换货和拒收审核时不扣减积分，无需补回），则需将积分还给顾客（因为审核时已将积分扣除）【不影响主流程】
            if(updateIntegralFlag && OrderMainConst.OrderMain_OrderCategory_Return.getCode().equals(orderMainR.getOrderCategory())){
                ResultDTO integralDTO = returnChangeOrderService.handelIntegral("sysbbc", orderMainR.getOrderNo(), true,true);//加回积分
                if(integralDTO.getResult()==CommonConst.Common_Succeed_FAIL.getCodeInt()){
                    /*result = integralDTO; //进入异常日志表，需要后续异常处理
                    return result;*/
                }
            }
        }else if(statusConfirm.equals(OrderStatus.StatusTypeCode_refundOrder_StatusConfirm_Wait.getCode())){
            //当前审核状态：未审核
            try {
                orderStatusService.saveProcess(orderSubNoR, OrderStatusAction.S022021, operateName, new Date(), null);
            } catch (Exception e) {
                logger.info("{}", e);
                result.setErrorMessage(""+e.getMessage());
            }   
        }
        //退货需还原累计退货表的数量
        this.saveBackRetOrderRecord(orderSubR);
        result.setResult(1);//取消成功
        return result;
    }
    
    /**
     * BBC订单已收货
     * @param saleAfterSubNo
     * @param operateName
     * @return 
     */
    public CommonOutputDTO bbcOrderStoreReceive(String saleAfterSubNo,String operateName){
      CommonOutputDTO ret = new CommonOutputDTO();
      ret.setCode(CommonConstService.FAILED);
      ret.setOrderNo(saleAfterSubNo);
      if(!StringUtils.isNotBlank(operateName) || !StringUtils.isNotBlank(saleAfterSubNo)){
           ret.setMsg("操作人或者子订单号为空");
           return ret;
        }       
        String orderNo = this.orderNoService.getOrderNoBySubNo(saleAfterSubNo);
        OrderSub sub = orderSubService.getByField(OrderSub_.orderSubNo, saleAfterSubNo);
        OrderMain orderMain = orderMainService.getByField(OrderMain_.orderNo, orderNo);
        if(null!=sub && null!=orderMain){
            //BBC状态修改状态为订单入库中:质检中--》入库中、  入库中---》退款中
          orderStatusService.saveProcess(saleAfterSubNo, OrderStatusAction.S024050, operateName, new Date(), null);
            OrderStatusAction action = OrderStatusAction.S025070;//BBC状态为订单入库中
            boolean flag = orderStatusService.saveProcess(saleAfterSubNo, action, operateName, new Date(), null);    
            if(flag){
                ret.setCode(CommonConstService.OK);
                ret.setMsg("恭喜您，操作成功！");
                return ret;
            }else{
                ret.setMsg("抱歉，操作失败了！");
                return ret;
            }
        }else{
          ret.setMsg("找不到主订单或者子订单信息");
        return ret;
        }
    }
    
    /**
     * BBC退货订单审核
     * @param saleAfterSubNo
     * @param operateName
     * @return 
     */
    public CommonOutputDTO bbcReturnOrderAudit(String returnSubNo,String operateName){
      CommonOutputDTO ret = new CommonOutputDTO();
      ret.setCode(CommonConstService.FAILED);
      ret.setOrderNo(returnSubNo);
      // 获取逆向主订单
      String orderMainNoR = orderNoService.getOrderNoBySubNo(returnSubNo);
        OrderMain orderMainR = orderMainService.getByField(OrderMain_.orderNo,orderMainNoR);
        // 获取逆向子订单
        OrderSub orderSubR = orderSubService.getByOrderSubNo(returnSubNo);
        if(null==orderMainR || null==orderSubR){
          ret.setMessage("无法找到对应的订单");
            return ret;
        }
        boolean updateIntegralFlag = false;// 是否需更新积分
        if (commonUtilService.longToBoolean(orderMainR.getIfNeedRefund())) { // 是否需退款
            BigDecimal point = orderMainR.getTotalGivePoints();
            if (point!=null && point.compareTo(new BigDecimal(0)) > 0) {
                updateIntegralFlag = true;
            }
        }
        //1、扣减积分：需退款，且为“退货”(换货和拒收不扣减积分)，则需在确认收货时扣除积分[调用会员接口]
        if(updateIntegralFlag && OrderMainConst.OrderMain_OrderCategory_Return.getCode().equals(orderMainR.getOrderCategory())){
            ResultDTO integralDTO = returnChangeOrderService.handelIntegral("sysbbc", orderMainR.getOrderNo(), false,false);//扣减积分
            if(integralDTO.getResult()==CommonConst.Common_Succeed_FAIL.getCodeInt()){
              ret.setMsg("扣减会员积分失败");
              return ret;
            }
        }
        // 设置审核人名称
        orderMainR.setConfirmerName(operateName);
        OrderStatusAction targetAction = OrderStatusAction.S022031;
        //逆向订单修改状态
        orderStatusService.saveProcess(returnSubNo, targetAction, operateName, new Date(), null);     
        boolean approveFlag = orderStatusService.saveProcess(returnSubNo, OrderStatusAction.S023140, operateName, new Date(), null);
        
        if(approveFlag){
        	ret.setCode(CommonConstService.OK);
        	ret.setMessage("恭喜您，审核成功");
            return ret;
        }else{
        	ret.setMessage("抱歉，审核失败");
            return ret;
        }
    }
	
    /**
     * 判断是否包含指定的支付方式
     *  payCode 支付方式code
     */
	public boolean checkOrderPayType(OrderMain orderMain,String payCode){
		boolean flag = false;
		if(null==orderMain) return flag;
		if(payCode.isEmpty()) return flag;
		List<OrderPay> opList = orderPayService.findByField(OrderPay_.orderNo, orderMain.getOrderNo());
		if (opList != null && !opList.isEmpty()) {
			for (OrderPay op : opList) {
			    if(payCode.equals(op.getPayCode())){
			    	flag = true;
			    	break;
			    }
			} 
		}
		return flag;
	}
	
	/**
	 * 判断是否全部由MY卡支付(剔除购物券)
	 * @param orderNo
	 * @return
	 */
	public boolean checkPayAllByMyCard(OrderMain orderMain){
	    boolean flag = false;
	    if(null==orderMain) return flag;
        List<OrderPay> opList = orderPayService.findByField(OrderPay_.orderNo, orderMain.getOrderNo());
        if (opList != null && !opList.isEmpty()) {
            boolean onLinePayFlag = false; //在线支付标识
            boolean myCardFlag = false; //MY卡支付标识
            boolean hasPaidFlag = OrderStatus.Order_PayStatus_Success.getCode().equals(orderMain.getStatusPay()); //在线支付完成
            for (OrderPay op : opList) {
                if(PayMode.COUPON.getCode().equals(op.getPayCode())){
                    continue;
                }else if(PayMode.CARD.getCode().equals(op.getPayCode())){ //MY卡支付
                    myCardFlag = true;
                }else{
                    //其他的支付方式
                    onLinePayFlag = true;
                }
            }
            if(hasPaidFlag && myCardFlag && !onLinePayFlag){
                flag = true;
            }
        }
        return flag;
	}
	
	/**
	 * 审核退款单
	 */
	public ResultDTO updateApproveRefundOrder(String orderSubNoR,String operator){
	    ResultDTO resultDTO = new ResultDTO();
		String orderNoR = this.orderNoService.getOrderNoBySubNo(orderSubNoR);
		OrderMain orderMainR = this.orderMainService.getByField(OrderMain_.orderNo, orderNoR);
		if(null != orderMainR)
		{
		    // 设置审核人
	        //orderMainR.setConfirmerName(operator);
		}else{
            resultDTO.setErrorMessage(String.format("updateApprovedRefundOrder 订单未找到, 单号:%s", orderSubNoR));
            return resultDTO;
		}
		
		// 判断是否需退maycard
		if(checkOrderPayType(orderMainR,PayMode.CARD.getCode())){
			ResultDTO myCardDTO = returnChangeOrderService.returnMyCard(orderMainR.getOrderNo(),operator, orderMainR,false);
			if(resultDTO.getResult()!=CommonConst.Common_Succeed_SUCCESS.getCodeInt()){
			    resultDTO.setErrorMessage(myCardDTO.getResultMessage());
				return resultDTO;
			}
		}
		//运费补款单需发送短信
		if(OrderMainConst.OrderMain_OrderCategory_TransportFee.getCode().equals(orderMainR.getOrderCategory())){
		    orderCreateService.supportPostFeeRet(orderSubNoR);
		}
		// 状态扭转(需判断是否为“门店代退”)
		boolean finishFlag = false;//完成标识
		if(new Long(CommonConst.OrderMain_IfNeedRefund_Yes_Store.getCodeLong()).equals(orderMainR.getIfNeedRefund())){
		    boolean updateStatusFlag1 = orderStatusService.saveProcess(orderSubNoR,OrderStatusAction.S027075, operator, new Date(), null);
		}else{
		    boolean updateStatusFlag2 = orderStatusService.saveProcess(orderSubNoR,OrderStatusAction.S027080, operator, new Date(), null);    
		    if(updateStatusFlag2){
		        finishFlag = true;
		    }
		}
		logger.info("审核退款单:"+orderMainR.getOrderNo()+",结束标识:"+finishFlag);
		if(finishFlag){
		    //退、换、拒收进入待同步R3队列
	        String category = orderMainR.getOrderCategory();
	        if(category.equals(OrderMainConst.OrderMain_OrderCategory_Change.getCode())||category.equals(OrderMainConst.OrderMain_OrderCategory_Return.getCode())
	                || category.equals(OrderMainConst.OrderMain_OrderCategory_Reject.getCode())){
	            saveSaleAfterOrderToR3(orderSubNoR);
	            //进行运费补款操作
	            saveTransportFeeOrder(orderMainR, orderSubNoR);
	        }
		}
		return resultDTO;
	}
	
	/**
	 * 售后意向单传输R3【进入待同步表】
	 * @param orderMain
	 * @param orderSubNoR
	 */
	public void saveSaleAfterOrderToR3(String orderSubNoR){
	    try{
	        if(null==orderSubNoR){
	            return;
	        }
	        String orderNoR = orderNoService.getOrderNoBySubNo(orderSubNoR);
	        OrderMain orderMainR = orderMainService.getByField(OrderMain_.orderNo, orderNoR);
	        if(null==orderMainR || commonUtilService.longToBoolean(orderMainR.getBillType())){
	            return;
	        }
	        logger.info("意向单:{} 写入待同步表 starting...",orderMainR.getOrderNo());
	        String orderCategory = orderMainR.getOrderCategory();
	        if(OrderMainConst.OrderMain_OrderCategory_Reject.getCode().equals(orderCategory)){
	            //拒收则把原订单当作取消传输至R3,前提是原订单有支付明细
	            String srcOrderNo = orderMainR.getOrderRelatedOrigin();
	            String srcOrderSubNo = orderNoService.getDefaultOrderSubNoByOrderNo(srcOrderNo);
	            List<OrderPay> srcPayList = orderPayService.findByField(OrderPay_.orderNo, srcOrderNo);
	            if(null!=srcPayList && srcPayList.size()>0){
	                Long srcOrderId = srcPayList.get(0).getIdOrder();
	                OrderMain om = orderMainService.get(srcOrderId);
	                //支付明细【充预收：原销售订单】
	                orderStatusSyncLogService.saveAndcreate(om, null, 
	                        CommonConst.OrderStatusSyncLog_SyncScene_Cancel.getCode());
	            }
	        } else if(OrderMainConst.OrderMain_OrderCategory_Change.getCode().equals(orderCategory)){
	            //换货需判断出库单是否已完成，换货的入库单、出库单需一起传输R3
	            saveChgOrderToR3(orderMainR,true);
	        }else{
	            logger.info("退货意向单:{} 写入待同步表...",orderMainR.getOrderNo());
                //("I-OMS-R3-01","订单明细"),
                orderStatusSyncLogService.saveAndcreate(orderMainR, null,
                        CommonConst.OrderStatusSyncLog_SyncScene_OrderItem.getCode());
                //"I-OMS-R3-02","支付明细"
//                orderStatusSyncLogService.saveAndcreate(orderMainR, null, 
//                        CommonConst.OrderStatusSyncLog_SyncScene_Pay.getCode());

	        }
	    }catch(Exception e){
	        logger.info("意向单 "+orderSubNoR+"同步R3异常：{}", e);
	    }
	}
	
	/**
	 * 检查换货入库单、出库单是否都已经完成,如果都完成则同时写入R3待同步表
	 * @param order 
	 * @param chgFlag 入库单标识==》true:order是入库单， false:order是出库单
	 * @return
	 */
	public void saveChgOrderToR3(OrderMain order,boolean chgFlag){
	    boolean flag = false; //完成标识
	    if(null == order){
	        return;
	    }
	    OrderMain chgOrderMain = null; //换货入库单
	    OrderMain chgOutOrderMain = null; //换货出库单
	    if(chgFlag){
	        chgOrderMain = order;
	        //入库单
	        if(OrderStatus.RET_ORDER_RETURN_FINISHED.getCode().equals(order.getStatusTotal())){
	            //获取出库单
	            String chgOutOrderNo = order.getChgOurOrderNo();//出库单的单号
	            OrderMain chgOutOrder = orderMainService.getByField(OrderMain_.orderNo, chgOutOrderNo);
	            if(null!=chgOutOrder && OrderStatus.ORDER_ACCEPTED_PAID.getCode().equals(chgOutOrder.getStatusTotal())){
	                chgOutOrderMain = chgOutOrder;
	                flag = true;
	            }
	            logger.info("换货入库单完成："+order.getOrderNo()+",对应出库单"+chgOutOrderNo+"完成状态："+flag);
	        }
	    }else{
	        chgOutOrderMain = order;
	        //出库单
	        if(OrderStatus.ORDER_ACCEPTED_PAID.getCode().equals(order.getStatusTotal())){
	            //获取入库单
	            String chgOrderNo = order.getOrderRelatedOrigin(); //入库单的单号
	            OrderMain chgOrder = orderMainService.getByField(OrderMain_.orderNo, chgOrderNo);
	            if(null!=chgOrder && OrderStatus.RET_ORDER_RETURN_FINISHED.getCode().equals(chgOrder.getStatusTotal())){
	                chgOrderMain = chgOrder;
	                flag = true;
	            }
	            logger.info("换货出库单完成："+order.getOrderNo()+",对应入库单"+chgOrderNo+"完成状态："+flag);
	        }
	    }
	    if(flag){
	        //出库单、入库单一起写R3待同步表
	        if(null!=chgOrderMain && null!=chgOutOrderMain){
	            //一、入库单
	            OrderSub chgOrderSub = orderSubService.getByField(OrderSub_.orderNo, chgOrderMain.getOrderNo());
                orderStatusSyncLogService.saveAndcreate(chgOrderMain, chgOrderSub,
                        CommonConst.OrderStatusSyncLog_SyncScene_OrderItem.getCode()); //("I-OMS-R3-01","订单明细"),
//                orderStatusSyncLogService.saveAndcreate(chgOrderMain, null, 
//                        CommonConst.OrderStatusSyncLog_SyncScene_Pay.getCode());//"I-OMS-R3-02","支付明细"
	            //二、出库单
                OrderSub chgOutOrderSub = orderSubService.getByField(OrderSub_.orderNo, chgOutOrderMain.getOrderNo());
                orderStatusSyncLogService.saveAndcreate(chgOutOrderMain, chgOutOrderSub,
                        CommonConst.OrderStatusSyncLog_SyncScene_OrderItem.getCode());
//                orderStatusSyncLogService.saveAndcreate(chgOutOrderMain,chgOutOrderSub,
//                        CommonConst.OrderStatusSyncLog_SyncScene_Pay.getCode());
	        }
	    }
	}
	
	/**
	 * 查看是否可以创建拒收订单(不包含已取消)
	 * @return  true:已创建且未取消(不可再创建 )  false:未创建或已取消(可以创建)
	 */
    public boolean isHadCreateRefuseOrders(String orderNo) {
        //OrderMain rejOrderMain = orderMainService.getByField(OrderMain_.orderRelatedOrigin, orderNo);
        OrderMain rejOrderMain = orderMainService.getByOrderRelatedOrigin(orderNo);
        if (null != rejOrderMain
                && !OrderStatus.StatusTypeCode_refundOrder_StatusConfirm_Cancel.getCode().equals(
                        rejOrderMain.getStatusConfirm())
                && !OrderStatus.RET_ORDER_INSPECTION_CANCEL.getCode().equals(rejOrderMain.getStatusTotal())
                && !OrderStatus.RET_ORDER_INSPECT_FAIL_RETURN.getCode().equals(rejOrderMain.getStatusTotal())) {
            return true;
        } else {
            return false;
        }
    }
	
	/**
     * 创建运费补款单
     * @param orderMainR 逆向单的主订单
     * @param orderSubNoR 逆向单的子订单号
     * @return
     */
	public ResultDTO saveTransportFeeOrder(OrderMain orderMainR, String orderSubNoR){
	    ResultDTO result = new ResultDTO();
        
	    OrderSub newSub = null;
        if (null == orderMainR || StringUtils.isEmpty(orderSubNoR)) {
            return null;
        }
        //判断是否符合运费补款条件【1.客户寄回，2.我方原因】
        boolean addFlag = false;
        OrderSub orderSubR = orderSubService.getByField(OrderSub_.orderSubNo,orderSubNoR);
        String distributeType = orderSubR.getDistributeType();//入库方式
        if(StringUtils.isNotEmpty(distributeType) && distributeType.equals(OrderMainConst.OrderSub_DistributeType_CustomerSend.getCode())){
            //判断责任归属
            for(OrderRetChgItem item:orderSubR.getOrderRetChgItems()){
                if(null==item.getReason()){
                    continue;
                }
                //获取原因的责任归属方
                OrderReason reason = orderReasonUtil.getOrderReasonMap().get(item.getReason());
                if(null==reason) continue;
                if(CommonConst.OrderRetChangeItem_Responsible_Rainbow.getCode().equals(String.valueOf(reason.getResponsibleParty()))){
                    addFlag = true;
                    break;
                }
            }
        }
        if(!addFlag){
            return result;
        }
        //创建运费补款单
        try {
            Long billType = CommonConst.OrderMain_BillType_Negative.getCodeLong();
            // 主订单
            OrderMain newMain = new OrderMain();
            orderMainCopy.copy(orderMainR, newMain, new OrderConverter());
            newMain.setOrderNo("");
            newMain.setStatusPay("");
            newMain.setStatusConfirm("");
            newMain.setStatusTotal(OrderStatus.RET_ORDER_CREATING.getCode());
            newMain.setOrderRelatedOrigin(orderMainR.getOrderNo());// 关联的原订单no
            newMain.setBillType(billType);
            newMain.setTransportFee(new BigDecimal(0));
            newMain.setIfNeedRefund(CommonConst.CommonBooleanTrueLong.getCodeLong());// 需退款
            newMain.setOrderCategory(OrderMainConst.OrderMain_OrderCategory_TransportFee.getCode());// 运费补款单
        
            Long newMainId = this.orderMainService.save(newMain);
            String newMainNo = this.orderNoService.getOrderNoByOrderId(String.valueOf(newMainId));
            newMain.setOrderNo(newMainNo);
            
            // 子订单
            newSub = new OrderSub();
            orderSubCopy.copy(orderSubR, newSub,  new OrderConverter());
            String newSubNo = this.orderNoService.getOrderSubNoByOrderNo(newMainNo, 1);
            if(StringUtils.isEmpty(newSub.getMobPhoneNum())){
                newSub.setMobPhoneNum(orderSubR.getMobPhoneNum());//发送短信需用到
            }
            newSub.setOrderSubNo(newSubNo);
            newSub.setIdOrder(newMainId);
            newSub.setOrderNo(newMainNo);
            newSub.setBillType(billType);
            newSub.setOrderMain(null);
            newSub.setTransportFee(new BigDecimal(0));
            newSub.setId(null);
            Long newSubId = this.orderSubService.save(newSub);
            // 明细（无需明细）
            /*int count = 0;
            List<OrderItem> itemList  = orderSubR.getOrderItems();
            if (itemList != null && !itemList.isEmpty()) {
                for (OrderItem item : itemList) {
                    count++;
                    OrderRetChgItem newItem = new OrderRetChgItem();
                    BeanUtils.copyProperties(newItem, item);
                    //CopyUtil.Copy(newItem, item);
                    String newItemNo = this.orderNoService.getOrderItemNoBySubOrderNo(newSubNo, count);
                    newItem.setIdOrder(newMainId);
                    newItem.setIdOrderSub(newSubId);
                    newItem.setOrderNo(newMainNo);
                    newItem.setOrderSubNo(newSubNo);
                    newItem.setOrderItemNo(newItemNo);
                    newItem.setBillType(billType);
                    this.orderRetChgItemService.save(newItem);
                }
            }*/
            
            //退款明细
            OrderPay pay = new OrderPay();
            pay.setId(null);
            pay.setOrderNo(newMainNo);
            pay.setIdOrder(newMainId);
            pay.setPayTime(null);
            pay.setPayCode(PayType.MYCARD.getId());
            pay.setPayName(PayType.MYCARD.getPayName());
            if(transportAreaCacheService.isShenzhenAddress(orderSubR.getAddressCode())){
                pay.setPayAmount(transportFeeOrder_ReturnAmount);    
                newMain.setTotalPayAmount(transportFeeOrder_ReturnAmount);
            }else{
                pay.setPayAmount(transportFeeOrder_ReturnAmount_OutArea); //外区域的标准为15元
                newMain.setTotalPayAmount(transportFeeOrder_ReturnAmount_OutArea);
            }
            pay.setBillType(-1l);
            this.orderPayService.save(pay);
            this.orderMainService.update(newMain);
            // 最后：订单状态扭转
            logger.info(newSub.getIdOrder()+"");
            this.orderStatusService.saveProcess(newSubNo, OrderStatusAction.S021020, null, null, null);
            this.orderStatusService.saveProcess(newSubNo, OrderStatusAction.S022070, null, null, null);//对于运费补款单，直接进入退款中
            result.setResultObj(newSub);//设置子订单到返回对象中
            logger.info("运费补款单:"+newSub.getIdOrder()+",创建success!");
        } catch (Exception e) {
            logger.info("{}", e);
            result.setErrorMessage("运费补款单创建失败！");
        }
        return result;
    }
}

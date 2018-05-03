package com.ibm.oms.service.impl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.intf.OrderStatusSyncLogDao;
import com.ibm.oms.domain.persist.OrderCombineRelation;
import com.ibm.oms.domain.persist.OrderCombineRelation_;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderItem_;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderMain_;
import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.oms.domain.persist.OrderPay_;
import com.ibm.oms.domain.persist.OrderRetChgItem;
import com.ibm.oms.domain.persist.OrderRetChgItem_;
import com.ibm.oms.domain.persist.OrderStatusSyncLog;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.domain.persist.SkuR3;
import com.ibm.oms.intf.constant.OrderMainConst;
import com.ibm.oms.service.OrderCombineRelationService;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderPayService;
import com.ibm.oms.service.OrderRetChgItemService;
import com.ibm.oms.service.OrderStatusSyncLogService;
import com.ibm.oms.service.SkuR3Service;
import com.ibm.oms.service.util.DataUtil;
import com.ibm.sc.service.impl.BaseServiceImpl;
/**
 * 
 * Creation date:2014-03-14 04:20:52
 * @author:Yong Hong Luo
 */
@Service("orderStatusSyncLogService")
public class OrderStatusSyncLogServiceImpl extends BaseServiceImpl<OrderStatusSyncLog,Long> implements
		OrderStatusSyncLogService{
    
	private OrderStatusSyncLogDao orderStatusSyncLogDao;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    OrderRetChgItemService orderRetChgItemService;
    @Autowired
    OrderMainService orderMainService;
    @Autowired
    SkuR3Service skuR3Service;
    @Autowired
    OrderPayService orderPayService;
    @Autowired
    private OrderCombineRelationService orderCombineRelationService;
    
	@Autowired
	public void setOrderStatusSyncLogDao(OrderStatusSyncLogDao orderStatusSyncLogDao) {
	    super.setBaseDao(orderStatusSyncLogDao);
		this.orderStatusSyncLogDao = orderStatusSyncLogDao;
	}
	
    public List<OrderStatusSyncLog> getLogListByMap(Map<String, Object> params) {
        List<OrderStatusSyncLog> ret = orderStatusSyncLogDao.findByFields(params, "id");
        if(ret!=null && ret.size()>100){
            return ret.subList(0, 100);
        }
        return ret;
    }

    /**场景，日期，同步标志N**/
    public List<OrderStatusSyncLog> getLogListByDate(String syncScene, String strStartDate, String strEndDate, String orderNo, String syncFlag, Integer size) {
        return orderStatusSyncLogDao.getPager(syncScene, strStartDate, strEndDate, orderNo, syncFlag, size);
    }
	
    public void update(Collection<OrderStatusSyncLog> objs) {
    	orderStatusSyncLogDao.update(objs);
    }
    
    public void saveAndcreate(OrderMain om, OrderSub os, String syncScene){
    	OrderStatusSyncLog log = new OrderStatusSyncLog();
    	log.setIdOrder(om.getId());
    	if(os != null){
    	    log.setIdOrderSub(os.getId());
    	    log.setOrderSubNo(os.getOrderSubNo());
    	}
    	log.setOrderNo(om.getOrderNo());
    	log.setSyncScene(syncScene);
    	String validOrNot = checkDataValid(om, syncScene);
    	if("0".equals(validOrNot)){
    	    log.setSyncFlag(CommonConst.OrderStatusSyncLog_SyncFlag_NeedProcess.getCode());
    	}else{
    	    log.setSyncFlag(CommonConst.OrderStatusSyncLog_SyncFlag_FailedPreProcess.getCode());
    	    log.setRemark(validOrNot);
    	}
    	log.setDateCreated(om.getFinishTime() == null ? new Date():om.getFinishTime());
    	log.setDateUpdated(new Date());
    	log.setCreatedBy("sys");
    	log.setTargetSys(CommonConst.OrderStatusSyncLog_TargetSys_R3.getCode());
    	orderStatusSyncLogDao.save(log);
    }
    
    @Override
    public String checkDataValid(OrderMain om, String syncScene){
        //预收，取消预收不关心会员卡等级
        boolean needVerifyCardLevel = CommonConst.OrderStatusSyncLog_SyncScene_Cancel.getCode().equals(syncScene)
                ||CommonConst.OrderStatusSyncLog_SyncScene_PrePay.getCode().equals(syncScene);
        //国贸云店除外
//        boolean isGuomao = CommonConst.OrderMain_OrderCategory_Return.getCode().equals(om.getOrderCategory()) && OrderMainConst.OrderMain_MerchantNo_Yundian_GuoMao.getCode().equalsIgnoreCase(om.getMerchantNo());
  
        if(needVerifyCardLevel){
            return "0";
        }
        String orderNo = om.getOrderNo();
        //国贸只检查退款,可以是My卡
//        if(needVerifyCardLevel && isGuomao){
//            List<OrderPay> opList = orderPayService.findByField(OrderPay_.orderNo, orderNo);
//            if(opList == null || opList.isEmpty()){
//                return "0";
//            }
//            return "0";
//        }
        if(StringUtils.isBlank(om.getMemberCardLevel())){
            return "member card level is empty";
        }
        if(om.getTotalPayAmount()==null || om.getTotalPayAmount().compareTo(BigDecimal.valueOf(0.01d))<0){
            return "orderMain's totalPayAmount is less than 0.01 or empty";
        }
        //销售单,出库单必须有orderSource
        boolean orderSourceCheck = (OrderMainConst.OrderMain_OrderCategory_Sale.getCode().equals(om.getOrderCategory())
                ||OrderMainConst.OrderMain_OrderCategory_ChangeOut.getCode().equals(om.getOrderCategory())) 
                && StringUtils.isBlank(om.getOrderSource());
        if(orderSourceCheck){
            return "orderMain's orderSource should not be empty";
        }
        List<OrderItem> oiList = orderItemService.findByField(OrderItem_.orderNo, orderNo);
        List<OrderRetChgItem> oiNList = orderRetChgItemService.findByField(OrderRetChgItem_.orderNo, om.getOrderNo());
        List<OrderPay> opList = orderPayService.findByField(OrderPay_.orderNo, orderNo);
        if (CommonConst.OrderMain_OrderCategory_Sale.getCode().equals(om.getOrderCategory())
                ||CommonConst.OrderMain_OrderCategory_ChangeOut.getCode().equals(om.getOrderCategory())) {
            String checkOI = checkOrderItem(om, oiList);
            if(!"0".equals(checkOI)){
                return checkOI;
            }
            String checkPay = checkPay(om, oiList, opList);
            if(!"0".equals(checkPay)){
                return checkPay;
            }
        }
        if (CommonConst.OrderMain_OrderCategory_Return.getCode().equals(om.getOrderCategory())
                || CommonConst.OrderMain_OrderCategory_Change.getCode().equals(om.getOrderCategory())) {
            String checkOI = checkOrderItemNegative(om, oiNList);
            if(!"0".equals(checkOI)){
                return checkOI;
            }
            String checkPay = checkPay(om, oiNList, opList);
            if(!"0".equals(checkPay)){
                return checkPay;
            }
        }
        return "0";
    }

    /**
     * 检查订单头记录的支付金额、订单明细记录的支付金额、订单的实际支付金额是否一致
     *
     * @param om
     * @param oiList
     * @param opList
     * @return
     */
    private String checkPay(OrderMain om, List<?> oiList, List<OrderPay> opList){
        //订单支付为空
        if(opList == null || opList.size() == 0){
            return "orderPay is empty";
        }

        //订单明细记录的支付金额（汇总）
        BigDecimal totalPayOfItem = new BigDecimal(0);
        boolean isOutOrder = false;
        for(Object oi:oiList){
            if(oi instanceof OrderItem){
                totalPayOfItem = totalPayOfItem.add(((OrderItem)oi).getPayAmount());
                isOutOrder=true;
            }
            if(oi instanceof OrderRetChgItem){
                totalPayOfItem = totalPayOfItem.add(((OrderRetChgItem)oi).getPayAmount());
            }
        }

        //订单的实际支付金额（汇总）
        BigDecimal totalPayOfPay = BigDecimal.valueOf(0d);
        for(OrderPay op:opList){
            totalPayOfPay = totalPayOfPay.add(op.getPayAmount());
        }

        //订单头记录的支付金额（含运费）
        BigDecimal transportFee = DataUtil.convertBigDecimal(om.getTransportFee());
        boolean omNotEqualToPay = om.getTotalPayAmount().subtract(totalPayOfPay).abs().compareTo(BigDecimal.valueOf(0.03d))>0;
        if(omNotEqualToPay){
            return "orderMain totalPayAmount notEqualTo sum of orderPay";
        }
        boolean omNotEqualToItem = false;
        //换货入库不管运费
        if(CommonConst.OrderMain_OrderCategory_Change.getCode().equals(om.getOrderCategory())){
            omNotEqualToItem = om.getTotalPayAmount().subtract(totalPayOfItem).abs().compareTo(BigDecimal.valueOf(0.03d))>0; 
        }
        else if(!isOutOrder){
            //退货单 orderMain.totalPayAmount+transportFee=sumOfItemPayAmount
            omNotEqualToItem = om.getTotalPayAmount().subtract(totalPayOfItem).add(transportFee).abs().compareTo(BigDecimal.valueOf(0.03d))>0; 
        }
        else{
            omNotEqualToItem = om.getTotalPayAmount().subtract(totalPayOfItem).subtract(transportFee).abs().compareTo(BigDecimal.valueOf(0.03d))>0; 
        }
        if(omNotEqualToItem){
            return "orderMain totalPayAmount notEqualTo sum of orderItem and TransportFee";
        }
        return "0";
    }
    
    
    private String checkOrderItem(OrderMain om, List<OrderItem> oiList){
        if (oiList == null || oiList.isEmpty()) {
            return "orderNo has no item";
        }
        //云店,供应商,商家不管出库成本价
        boolean isBbcCloudStore = CommonConst.OrderMain_MerchantType_InvoiceOrg.getCode().equals(om.getMerchantType()) 
                || CommonConst.OrderMain_MerchantType_Client.getCode().equals(om.getMerchantType())
                || CommonConst.OrderMain_MerchantType_Bbc.getCode().equals(om.getMerchantType());
        for(OrderItem oi:oiList){
            //是否组合商品
            boolean isCombinationProduct = OrderMainConst.OrderItem_OrderItemClass_SUITE.getCode().equals(oi.getOrderItemClass());

            if(!isCombinationProduct){ //普通商品
                if(StringUtils.isBlank(oi.getSkuNo())){
                    return "orderItem has no skuNo";
                }
                if(oi.getNormalPrice() == null){
                    return "orderItem has no normal price";
                }
                if(oi.getInventoryPrice() == null && !isBbcCloudStore){
                    return "orderItem has no inventory price";
                }
                if(StringUtils.isBlank(oi.getBarCode())){
                    return "orderItem barCode is empty";
                }
                SkuR3 sku = skuR3Service.getR3BySku(oi.getSkuNo());
                if(sku == null){
                    return "skuR3 search failed";
                }
                if(StringUtils.isBlank(sku.getItemnumber())){
                    return "sku ItemNumber is empty";
                }
//                if(StringUtils.isBlank(sku.getField4())){
//                    return "sku field4 stock area is empty";
//                }
                //直接获取下单时的库区
                if(StringUtils.isBlank(oi.getStockNo())){
                    return "orderItem stockNo is empty";
                }
                if(StringUtils.isBlank(sku.getSourceType())){
                    return "sku sourceType, isUnionBiz is empty";
                }
                if(sku.getSupplierId() == null || sku.getSupplierId()==0l){
                    return "sku supplierId is empty or zero";
                }
                if("TianHong".equals(sku.getSourceType())){
                    //
                    BigDecimal totalPayOfItemCalc = oi.getUnitPrice().multiply(new BigDecimal(oi.getSaleNum())).setScale(2,4);
                    if(totalPayOfItemCalc.subtract(oi.getSaleTotalMoney()).abs().compareTo(BigDecimal.valueOf(0.01d))>0){
                        return "unitPrice * saleNum notEqualTo item saleTotalMoney";
                    }
                }
            }else{ //组合商品
                //订单上的价格
                BigDecimal resultGap = oi.getUnitPrice();
                List<OrderCombineRelation> relationList = orderCombineRelationService.findByField(OrderCombineRelation_.orderItemNo, oi.getOrderItemNo());
                for (OrderCombineRelation orderCombineRelation : relationList) {
                    SkuR3 skuInCombination = skuR3Service.getR3BySku(orderCombineRelation.getSkuNo());
                    resultGap = resultGap.subtract(orderCombineRelation.getUnitPrice().multiply(new BigDecimal(orderCombineRelation.getSaleNum())));
                    if(skuInCombination == null){
                        return "skuR3 search failed";
                    }
                    if(StringUtils.isBlank(orderCombineRelation.getSkuNo())){
                        return "orderItem has no skuNo";
                    }
                    if(orderCombineRelation.getUnitPrice() == null){
                        return "orderItem has no normal price";
                    }
                    if(orderCombineRelation.getInventoryPrice() == null && !isBbcCloudStore){
                        return "orderItem has no inventory price";
                    }
                    if(StringUtils.isBlank(skuInCombination.getBarcode())){
                        return "orderItem barCode is empty";
                    }

                    if(StringUtils.isBlank(skuInCombination.getItemnumber())){
                        return "sku ItemNumber is empty";
                    }
//                    if(StringUtils.isBlank(skuInCombination.getField4())){
//                        return "sku field4 stock area is empty";
//                    }
                    if (StringUtils.isBlank(orderCombineRelation.getStockNo())) {
                        return "sku field4 stock area is empty";
                    }
                    if(StringUtils.isBlank(skuInCombination.getSourceType())){
                        return "sku sourceType, isUnionBiz is empty";
                    }
                    if(skuInCombination.getSupplierId() == null || skuInCombination.getSupplierId()==0l){
                        return "sku supplierId is empty or zero";
                    }
                }
                if(resultGap.abs().compareTo(BigDecimal.valueOf(0.01))==1){
                    return "combined Item has unitPrice NotEqual to the detailed sum(unitPrice*saleNum)";
                }
            }
        }
        
        return "0";
    }
    
    private String checkOrderItemNegative(OrderMain om, List<OrderRetChgItem> oiList){
        if (oiList == null || oiList.isEmpty()) {
            return "orderNo has no item";
        }
        //云店,供应商,商家不管出库成本价
        boolean isBbcCloudStore = CommonConst.OrderMain_MerchantType_InvoiceOrg.getCode().equals(om.getMerchantType()) 
                || CommonConst.OrderMain_MerchantType_Client.getCode().equals(om.getMerchantType())
                || CommonConst.OrderMain_MerchantType_Bbc.getCode().equals(om.getMerchantType());

        for(OrderRetChgItem oi:oiList){
            boolean isCombinationProduct = OrderMainConst.OrderItem_OrderItemClass_SUITE.getCode().equals(oi.getOrderItemClass());
            if (!isCombinationProduct) {
                if (StringUtils.isBlank(oi.getSkuNo())) {
                    return "orderItem has no skuNo";
                }
                if (oi.getNormalPrice() == null) {
                    return "orderItem has no normal price";
                }
                if (oi.getInventoryPrice() == null && !isBbcCloudStore) {
                    return "orderItem has no inventory price";
                }
                if (StringUtils.isBlank(oi.getBarCode())) {
                    return "orderItem barCode is empty";
                }
                SkuR3 sku = skuR3Service.getR3BySku(oi.getSkuNo());
                if (sku == null) {
                    return "skuR3 search failed";
                }
                if (StringUtils.isBlank(sku.getItemnumber())) {
                    return "sku ItemNumber is empty";
                }
//                if (StringUtils.isBlank(sku.getField4())) {
//                    return "sku field4 stock area is empty";
//                }
                if (StringUtils.isBlank(oi.getStockNo())) {
                    return "sku field4 stock area is empty";
                }
                if (StringUtils.isBlank(sku.getSourceType())) {
                    return "sku sourceType, isUnionBiz is empty";
                }
                if (sku.getSupplierId() == null || sku.getSupplierId() == 0l) {
                    return "sku supplierId is empty or zero";
                }
//                sale_num*(unit_price-coupon_amount)-item_discount<>pay_amount
                //ignore chg order
                boolean isRet = !CommonConst.OrderMain_OrderCategory_Change.getCode().equals(om.getOrderCategory());
                BigDecimal retItemCheck = BigDecimal.valueOf(oi.getSaleNum()).multiply(oi.getUnitPrice().subtract(oi.getCouponAmount()));
                retItemCheck = retItemCheck.subtract(oi.getItemDiscount()).subtract(oi.getPayAmount());
//                ufa07 (UnitPrice)= payAmount/saleNum + couponAmount  
//                ufb all_order_pay+(sum(itemSaleNum*couponAmount)), ufa07*saleNum = ufb
                if(isRet && retItemCheck.abs().compareTo(BigDecimal.valueOf(0.01d)) > 0){
                    return "retChgItem's sale_num*(unit_price-coupon_amount)-item_discount<>pay_amount";
                } 
            }else{
                BigDecimal resultGap = oi.getUnitPrice();
                List<OrderCombineRelation> relationList = orderCombineRelationService.findByField(OrderCombineRelation_.orderItemNo, oi.getOrderItemNo());
                for (OrderCombineRelation orderCombineRelation : relationList) {
                    SkuR3 skuInCombination = skuR3Service.getR3BySku(orderCombineRelation.getSkuNo());
                    resultGap = resultGap.subtract(orderCombineRelation.getUnitPrice().multiply(new BigDecimal(orderCombineRelation.getSaleNum())));
                    if(skuInCombination == null){
                        return "skuR3 search failed";
                    }
                    if(StringUtils.isBlank(orderCombineRelation.getSkuNo())){
                        return "orderItem has no skuNo";
                    }
                    if(orderCombineRelation.getUnitPrice() == null){
                        return "orderItem has no normal price";
                    }
                    if(orderCombineRelation.getInventoryPrice() == null && !isBbcCloudStore){
                        return "orderItem has no inventory price";
                    }
                    if(StringUtils.isBlank(skuInCombination.getBarcode())){
                        return "orderItem barCode is empty";
                    }

                    if(StringUtils.isBlank(skuInCombination.getItemnumber())){
                        return "sku ItemNumber is empty";
                    }
//                    if(StringUtils.isBlank(skuInCombination.getField4())){
//                        return "sku field4 stock area is empty";
//                    }
                    if (StringUtils.isBlank(orderCombineRelation.getStockNo())) {
                        return "sku field4 stock area is empty";
                    }
                    if(StringUtils.isBlank(skuInCombination.getSourceType())){
                        return "sku sourceType, isUnionBiz is empty";
                    }
                    if(skuInCombination.getSupplierId() == null || skuInCombination.getSupplierId()==0l){
                        return "sku supplierId is empty or zero";
                    }
                }
                if(resultGap.abs().compareTo(BigDecimal.valueOf(0.01))>1){
                    return "combined Item has unitPrice NotEqual to the detailed sum(unitPrice*saleNum)";
                }
            }
        }
        return "0";
    }

    
    /**
     * 订单来源：
         1、网上天虹（不区分PC与WAP）
         2、微店
         3、微信
         4、天虹小店
     *
     * @param orderMain
     * @return
     */
    @Override
    public String getOrderSource(OrderMain orderMain){
        String result = "1";

        //逆向订单取正向订单的来源
        String orderSource = orderMain.getOrderSource();
        if(!(orderMain.getOrderCategory().equals(OrderMainConst.OrderMain_OrderCategory_Sale.getCode())||(orderMain.getOrderCategory().equals(OrderMainConst.OrderMain_OrderCategory_ChangeOut.getCode())))){ //非销售订单或非换货出库单
            OrderMain relatedOriginOrderMain = orderMainService.getByField(OrderMain_.orderNo, orderMain.getOrderRelatedOrigin());
            if(relatedOriginOrderMain != null){
                orderSource = relatedOriginOrderMain.getOrderSource();
            }
        }

        //转换成与R3约定的订单来源
        if(OrderMainConst.OrderMain_Ordersource_App.getCode().equals(orderSource)){
            result = "2";
        }else if(OrderMainConst.OrderMain_Ordersource_Wgw.getCode().equals(orderSource)){
            result = "3";
        }else if(OrderMainConst.OrderMain_Ordersource_Wph.getCode().equals(orderSource)){
            result = "4";
        }

        return result;
    }
    
}

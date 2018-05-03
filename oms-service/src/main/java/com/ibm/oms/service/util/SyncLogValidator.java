package com.ibm.oms.service.util;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.domain.persist.OrderCombineRelation;
import com.ibm.oms.domain.persist.OrderCombineRelation_;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderItemVirtual;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderMain_;
import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.oms.domain.persist.OrderRetChgItem;
import com.ibm.oms.domain.persist.OrderStatusSyncLog;
import com.ibm.oms.domain.persist.SkuR3;
import com.ibm.oms.intf.constant.OrderMainConst;
import com.ibm.oms.service.OrderCombineRelationService;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.OrderItemVirtualService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderPayService;
import com.ibm.oms.service.OrderRetChgItemService;
import com.ibm.oms.service.OrderStatusSyncLogService;
import com.ibm.oms.service.SkuR3Service;

@Component
public class SyncLogValidator {
    @Autowired
    OrderPayService orderPayService;
    @Autowired
    OrderMainService orderMainService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    OrderRetChgItemService orderRetChgItemService;
    @Autowired
    OrderItemVirtualService orderItemVirtualService;
    @Resource
    private SkuR3Service skuR3Service;
    @Autowired
    OrderCombineRelationService orderCombineRelationService;
    @Autowired
    OrderStatusSyncLogService orderStatusSyncLogService;
    
    /**同步之前对每个待同步行作校验, 不合格的row将syncFlag写E**/
    public boolean validateRowOfItem(OrderStatusSyncLog row, String syncScene){
        String orderNo = row.getOrderNo();
        OrderMain om = orderMainService.get(OrderMain_.orderNo, orderNo);
        String validOrNot = orderStatusSyncLogService.checkDataValid(om, syncScene);
        if("0".equals(validOrNot)){
            return true;
        }else{
            row.setRemark(validOrNot);
            row.setSyncFlag(CommonConst.OrderStatusSyncLog_SyncFlag_FailedPreProcess.getCode());
            return false;
        }

//        List<OrderPay> orderPays = orderPayService.findByField(OrderPay_.orderNo, orderNo);
//        if (orderPays == null || orderPays.isEmpty()) {
//            row.setRemark("订单无支付信息!");
//            row.setSyncFlag(CommonConst.OrderStatusSyncLog_SyncFlag_FailedPreProcess.getCode());
//            return false;
//        }
//        OrderMain orderMain = orderMainService.getByField(OrderMain_.orderNo, orderNo);
//        if(StringUtils.isBlank(orderMain.getMemberCardLevel())){
//            row.setRemark("订单无会员等级信息!");
//            row.setSyncFlag(CommonConst.OrderStatusSyncLog_SyncFlag_FailedPreProcess.getCode());
//            return false;
//        }
        // 逆向订单
//        if (CommonConst.OrderMain_BillType_Negative.getCodeLong() == orderMain.getBillType()) {
//
//            List<OrderRetChgItem> itemList = orderRetChgItemService.findByField("orderNo", orderNo);
//            // 逆向订单金额校验
//            boolean bb = checkNegativePaymentAuountList(orderPays, itemList, orderMain);
//            if (!bb) {
//                row.setRemark("逆向订单支付金额与订单明细支付金额不一致");
//                row.setSyncFlag(CommonConst.OrderStatusSyncLog_SyncFlag_FailedPreProcess.getCode());
//                return false;
//            }
//        }
        // 销售单
//        List<OrderItem> itemList = orderItemService.findByField(OrderItem_.orderNo, orderNo);
//
//        List<OrderItemVirtual> virtualList = orderItemVirtualService.findByField("orderNo", orderNo);

//        if (itemList != null && !itemList.isEmpty()) {
//            boolean isR3Amount = checkPaymentAuountList(orderPays, itemList, orderMain);
//            if (!isR3Amount) {
//                row.setRemark("正向支付金额与订单明细支付金额不一致");
//                row.setSyncFlag(CommonConst.OrderStatusSyncLog_SyncFlag_FailedPreProcess.getCode());
//                return false;
//            }
//            for(OrderItem oi: itemList){
//                if(StringUtils.isEmpty(oi.getSkuNo())){
//                    row.setRemark(String.format("skuNo is empty, orderItemNo :%s", oi.getOrderItemNo()));
//                    row.setSyncFlag(CommonConst.OrderStatusSyncLog_SyncFlag_FailedPreProcess.getCode());
//                    return false;
//                }
//            }
//        }

        // 校验 库区与店内码 的数据
//        boolean isR3 = this.checkOrderItemList(itemList);
//
//        boolean isVr3 = this.checkOrderItemVirtualList(virtualList);
//
//        if (!isR3 || !isVr3) {
//            row.setRemark("库区或店内码为null");
//            row.setSyncFlag(CommonConst.OrderStatusSyncLog_SyncFlag_FailedPreProcess.getCode());
//            return false;
//        }
//        row.setRemark(null);
//        row.setSyncFlag(CommonConst.OrderStatusSyncLog_SyncFlag_NeedProcess.getCode());
//        return true;
    }
    /**
     * 校验 库区与店内码 的数据
     * @param virtualList
     * @return
     */
    private boolean checkOrderItemVirtualList(List<OrderItemVirtual> virtualList){
        if(virtualList == null || virtualList.isEmpty()){
            return true;
        }
        for (OrderItemVirtual orderItemVirtual : virtualList) {
            if(!validateSkuR3(skuR3Service.getR3BySku(orderItemVirtual.getSkuNo()), orderItemVirtual.getStockNo())){
                return false;
            }
        }
        
        return true;
        
    }
    private boolean validateSkuR3(SkuR3 r3, String stockNo){
        if(r3==null){
            return false;
        }else{
            //field4 库区, itemNumber 店内码
            if (StringUtils.isBlank(r3.getSourceType()) || StringUtils.isBlank(stockNo) || "001950000000".equals(stockNo)
                    || "0".equals(stockNo) || StringUtils.isBlank(r3.getItemnumber())) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 校验 库区与店内码 的数据
     * @param itemList
     * @return
     */
    private boolean checkOrderItemList(List<OrderItem> itemList){
        if(itemList == null || itemList.isEmpty()){
            return true;
        }
        for (OrderItem orderItem : itemList) {
            
            if(OrderMainConst.OrderItem_OrderItemClass_SUITE.getCode().equals(orderItem.getOrderItemClass())){
                List <OrderCombineRelation> relationList = orderCombineRelationService.findByField(OrderCombineRelation_.orderItemNo, orderItem.getOrderItemNo());
                if(!checkOrderCombineRelationList(relationList)){
                    return false;
                }
            }else{
                if(!validateSkuR3(skuR3Service.getR3BySku(orderItem.getSkuNo()), orderItem.getStockNo())){
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * 校验 库区与店内码 的数据
     * @param relationList
     * @return
     */
    private boolean checkOrderCombineRelationList(List<OrderCombineRelation> relationList){
        for (OrderCombineRelation orderCombineRelation : relationList) {
            if(!validateSkuR3(skuR3Service.getR3BySku(orderCombineRelation.getSkuNo()), orderCombineRelation.getStockNo())){
                return false;
            }
        }
        return true;
    }
    
    /**
     * 校验 库区与店内码 的数据
     * @param chaItemList
     * @return
     */
    private boolean checkOrderRetChgItemList(List<OrderRetChgItem> chaItemList){
        for (OrderRetChgItem orderRetChgItem : chaItemList) {
            if(!validateSkuR3(skuR3Service.getR3BySku(orderRetChgItem.getSkuNo()), orderRetChgItem.getStockNo())){
                return false;
            }
        }
        return true;
    }
    
    /**逆向订单金额校验， 逆向行payamount之和加减运费 = 逆向支付之和**/
    private boolean checkNegativePaymentAuountList(List<OrderPay> orderPays,List<OrderRetChgItem> itemList,OrderMain orderMain ){
        BigDecimal payMoney = new BigDecimal(0);
        BigDecimal itemMoney = new BigDecimal(0);

        for (OrderRetChgItem orderItem : itemList) {
            itemMoney = itemMoney.add( DataUtil.convertBigDecimal(orderItem.getPayAmount()));
            
        }
        if(OrderMainConst.OrderMain_OrderCategory_Return.getCode().equals(orderMain.getOrderCategory())){
            itemMoney = itemMoney.subtract( DataUtil.convertBigDecimal(orderMain.getTransportFee())); 
        }else{
            //换货入库
            itemMoney = itemMoney.add( DataUtil.convertBigDecimal(orderMain.getTransportFee())); 
        }
    
        itemMoney=  itemMoney.setScale(2, BigDecimal.ROUND_HALF_UP);
        for (OrderPay orderPay : orderPays) {
            payMoney = payMoney.add(DataUtil.convertBigDecimal(orderPay.getPayAmount()));
        }
        payMoney= payMoney.setScale(2, BigDecimal.ROUND_HALF_UP);
        if(itemMoney.doubleValue()-payMoney.doubleValue()> 0.02d ||itemMoney.doubleValue()-payMoney.doubleValue()< -0.02d){
            return false;
        }
        
        return true;
    }
    
    /**
     * 支付明细与订单项支付明细比较 不同 不能同步r3
     * @param orderPays
     * @param itemList
     * @return
     */
    private boolean checkPaymentAuountList(List<OrderPay> orderPays,List<OrderItem> itemList,OrderMain orderMain ){
        BigDecimal payMoney = new BigDecimal(0);
        BigDecimal itemMoney = new BigDecimal(0);

        for (OrderItem orderItem : itemList) {
            itemMoney = itemMoney.add( DataUtil.convertBigDecimal(orderItem.getPayAmount()));
            
        }
        itemMoney = itemMoney.add( DataUtil.convertBigDecimal(orderMain.getTransportFee())); 
        itemMoney=  itemMoney.setScale(2, BigDecimal.ROUND_HALF_UP);
        for (OrderPay orderPay : orderPays) {
            payMoney = payMoney.add(DataUtil.convertBigDecimal(orderPay.getPayAmount()));
        }
        payMoney= payMoney.setScale(2, BigDecimal.ROUND_HALF_UP);
        if(itemMoney.doubleValue()-payMoney.doubleValue()> 0.4 ||itemMoney.doubleValue()-payMoney.doubleValue()<-0.4){
            return false;
        }
        
        return true;
    }
}

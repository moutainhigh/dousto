package com.ibm.oms.service.business.trans.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beans.stock.OmsSODetails;
import com.beans.stock.OmsSOFreeDetails;
import com.beans.stock.OmsSOHeader;
import com.beans.stock.OmsSOInfo;
import com.beans.stock.StockLockByOms;
import com.beans.stock.StockLockByOmsDetail;
import com.beans.stock.StockUnLockByOms;
import com.beans.stock.StockUnLockByOmsDetail;
import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.constant.PayMode;
import com.ibm.oms.domain.persist.OrderCombineRelation;
import com.ibm.oms.domain.persist.OrderInvoice;
import com.ibm.oms.domain.persist.OrderInvoice_;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderItemVirtual;
import com.ibm.oms.domain.persist.OrderItemVirtual_;
import com.ibm.oms.domain.persist.OrderItem_;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.oms.domain.persist.OrderPayMode;
import com.ibm.oms.domain.persist.OrderPayMode_;
import com.ibm.oms.domain.persist.OrderPay_;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.intf.constant.OrderMainConst;
import com.ibm.oms.intf.intf.InventoryResendMsgDTO;
import com.ibm.oms.service.OrderCombineRelationService;
import com.ibm.oms.service.OrderInvoiceService;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.OrderItemVirtualService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderPayModeService;
import com.ibm.oms.service.OrderPayService;
import com.ibm.oms.service.OrderPromotionService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.oms.service.business.trans.ImsOmsTransService;
import com.ibm.oms.service.util.CommonCacheUtil;
import com.ibm.oms.service.util.CommonConstService;
import com.ibm.oms.service.util.CommonUtilService;
import com.ibm.oms.service.util.SelfTakeMerchantUtil;
import com.ibm.sc.model.shipping.SelfTakePoint;



/**
 * @author pjsong
 *
 */
@Service("tmsOmsTransService")
public class ImsOmsTransServiceImpl implements ImsOmsTransService {

	@Autowired
	OrderItemVirtualService orderItemVirtualService;
    @Autowired
    OrderMainService orderMainService;
    @Autowired
    OrderPayService orderPayService;
    @Autowired
    OrderInvoiceService orderInvoiceService;
    @Autowired
    OrderPayModeService orderPayModeService;
    @Autowired
    OrderSubService orderSubService;
    @Autowired
    CommonUtilService commonUtilService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    OrderPromotionService orderPromotionService;
    @Autowired
    private SelfTakeMerchantUtil selfTakeMerchantUtil;
    @Autowired
    OrderCombineRelationService orderCombineRelationService;
    @Autowired
    CommonCacheUtil commonCacheUtil;
    
    @Override
    public InventoryResendMsgDTO queryInventoryResend(String orderItemNo) {
        InventoryResendMsgDTO ret = new InventoryResendMsgDTO();
        OrderItemVirtual oiv = orderItemVirtualService.get(OrderItemVirtual_.orderItemNo, orderItemNo);
        String orderNo = oiv.getOrderNo();
        OrderMain om = oiv.getOrderMain();
        ret.setChannelCode(om.getOrderSource());
        ret.setMobilephone(oiv.getReceiveMobile());
        ret.setOrderNo(orderNo);
        ret.setProductType("");
        ret.setPromotionCode("");
        ret.setSkuCode(oiv.getSkuNo());
        ret.setInvoker("oms");
        return ret;
    }


    /** 构建出库对象 
     * @param om 
     * @return OmsSOInfo
     * **/
    @Override
    public OmsSOInfo queryInventoryDeduct(String orderNo) {
        OrderMain om = orderMainService.findByOrderNo(orderNo);
        List<OrderSub> osList = om.getOrderSubs();
        OrderSub os = (osList == null || osList.size() == 0) ? null : osList.get(0);
        // 出库
        OmsSOHeader omsSOHeader = new OmsSOHeader();
        OmsSOInfo soInfo = new OmsSOInfo();
        ArrayList<OmsSOFreeDetails> feeItems = new ArrayList<OmsSOFreeDetails>();

        // 订单费用支付详情
        boolean payOnArrival = CommonConstService.BOOLEAN_TRUE.equals(om.getIfPayOnArrival());
        List<OrderPay> opList = orderPayService.findByField(OrderPay_.orderNo, om.getOrderNo());
        List<OrderPayMode> opmList = orderPayModeService.findByField(OrderPayMode_.orderNo, orderNo);
        
        OrderPayMode payMode = null;
        int counter = 1;
        if (opmList != null && opmList.size() > 0) {
            payMode = opmList.get(0);
            for (OrderPayMode op : opmList) {
                // 货到付款
                boolean isPayOnArrival = (om.getIfPayOnArrival() != null && om.getIfPayOnArrival().intValue() == 1);
                if (isPayOnArrival) {
                    OmsSOFreeDetails omsSOFreeDetails = new OmsSOFreeDetails();
                    // 单号
                    omsSOFreeDetails.setORDERNO(os.getOrderSubNo());
                    // 传行号（自己编号1.2.3.4.5…）
                    omsSOFreeDetails.setLINENUMBER(counter++ + "");
                    // 订单费用：费用；订单支付：支付
                    omsSOFreeDetails.setRATEUOM1("支付");
                    // 费用种类 t_pricerec.name:送货费，物流费，商品总价，积分等等;t_payrec.paymodename:网银在线支付、平安银行支付
                    omsSOFreeDetails.setCHARGETYPE("货到付款");
                    // 各种费用金额,元为单位:t_payrec.amount/100
                    omsSOFreeDetails.setCHARGEFEE(commonUtilService.bigDecimal2String(op.getPayAmount()));
                    // 支付状态rateuom1==费用：1：需要，0：不需要 rateuom1==支付：未支付300，支付成功330，支付失败320
                    omsSOFreeDetails.setEXTENTS_UDF01("300");
                    // 收费方式chargecategory == chargetype
                    omsSOFreeDetails.setCHARGECATEGORY("货到付款");
                    // 货币类型t_pricerec.moneytypeid/ t_payrec.moneytypeid 1:积分，0：RMB
                    omsSOFreeDetails.setCHARGEDESCR("0");
                    feeItems.add(omsSOFreeDetails);
                }
            }
        }
        for (OrderPay op : opList) {
            // MY卡支付
            if (PayMode.CARD.getCode().equals(op.getPayCode())) {
                OmsSOFreeDetails omsSOFreeDetails = new OmsSOFreeDetails();
                // 单号
                omsSOFreeDetails.setORDERNO(os.getOrderSubNo());
                // 传行号（自己编号1.2.3.4.5…）
                omsSOFreeDetails.setLINENUMBER(counter++ + "");
                // 订单费用：费用；订单支付：支付
                omsSOFreeDetails.setRATEUOM1("支付");
                // 费用种类 t_pricerec.name:送货费，物流费，商品总价，积分等等;t_payrec.paymodename:网银在线支付、平安银行支付
                omsSOFreeDetails.setCHARGETYPE("MY卡支付");
                // 各种费用金额,元为单位:t_payrec.amount/100
                omsSOFreeDetails.setCHARGEFEE(commonUtilService.bigDecimal2String(op.getPayAmount()));
                // 支付状态rateuom1==费用：1：需要，0：不需要 rateuom1==支付：未支付300，支付成功330，支付失败320
                omsSOFreeDetails.setEXTENTS_UDF01("330");
                // 收费方式chargecategory == chargetype
                omsSOFreeDetails.setCHARGECATEGORY("MY卡支付");
                // 货币类型t_pricerec.moneytypeid/ t_payrec.moneytypeid 1:积分，0：RMB
                omsSOFreeDetails.setCHARGEDESCR("0");
                feeItems.add(omsSOFreeDetails);
                continue;
            }
            // 购物券支付
            if (PayMode.COUPON.getCode().equals(op.getPayCode())) {
                OmsSOFreeDetails omsSOFreeDetails = new OmsSOFreeDetails();
                // 单号
                omsSOFreeDetails.setORDERNO(os.getOrderSubNo());
                // 传行号（自己编号1.2.3.4.5…）
                omsSOFreeDetails.setLINENUMBER(counter++ + "");
                // 订单费用：费用；订单支付：支付
                omsSOFreeDetails.setRATEUOM1("支付");
                // 费用种类 t_pricerec.name:送货费，物流费，商品总价，积分等等;t_payrec.paymodename:网银在线支付、平安银行支付
                omsSOFreeDetails.setCHARGETYPE("购物券支付");
                // 各种费用金额,元为单位:t_payrec.amount/100
                omsSOFreeDetails.setCHARGEFEE(commonUtilService.bigDecimal2String(op.getPayAmount()));
                // 支付状态rateuom1==费用：1：需要，0：不需要 rateuom1==支付：未支付300，支付成功330，支付失败320
                omsSOFreeDetails.setEXTENTS_UDF01("330");
                // 收费方式chargecategory == chargetype
                omsSOFreeDetails.setCHARGECATEGORY("购物券支付");
                // 货币类型t_pricerec.moneytypeid/ t_payrec.moneytypeid 1:积分，0：RMB
                omsSOFreeDetails.setCHARGEDESCR("0");
                feeItems.add(omsSOFreeDetails);
                continue;
            }
            OmsSOFreeDetails omsSOFreeDetails = new OmsSOFreeDetails();
            // 单号
            omsSOFreeDetails.setORDERNO(os.getOrderSubNo());
            // 传行号（自己编号1.2.3.4.5…）
            omsSOFreeDetails.setLINENUMBER(counter++ + "");
            // 订单费用：费用；订单支付：支付
            omsSOFreeDetails.setRATEUOM1("支付");
            // 费用种类 t_pricerec.name:送货费，物流费，商品总价，积分等等;t_payrec.paymodename:网银在线支付、平安银行支付
            omsSOFreeDetails.setCHARGETYPE(op.getPayName());
            // 各种费用金额,元为单位:t_payrec.amount/100
            omsSOFreeDetails.setCHARGEFEE(commonUtilService.bigDecimal2String(op.getPayAmount()));
            // 支付状态rateuom1==费用：1：需要，0：不需要 rateuom1==支付：未支付300，支付成功330，支付失败320
            omsSOFreeDetails.setEXTENTS_UDF01("330");
            // 收费方式chargecategory == chargetype
            omsSOFreeDetails.setCHARGECATEGORY(op.getPayName());
            // 货币类型t_pricerec.moneytypeid/ t_payrec.moneytypeid 1:积分，0：RMB
            omsSOFreeDetails.setCHARGEDESCR("0");
            feeItems.add(omsSOFreeDetails);
        }

        /** 订单折扣优惠,没有不写 **/
        BigDecimal totalPromo = om.getDiscountTotal();
        if(totalPromo != null && totalPromo.intValue() > 0){
            OmsSOFreeDetails omsSOFreeDetails_Discount = new OmsSOFreeDetails();
            // 子订单Id
            omsSOFreeDetails_Discount.setORDERNO(os.getOrderSubNo());
            // 传行号（自己编号1.2.3.4.5…）
            omsSOFreeDetails_Discount.setLINENUMBER(counter++ + "");
            // 订单费用：费用；订单支付：支付
            omsSOFreeDetails_Discount.setRATEUOM1("费用");
            // 费用种类 t_pricerec.name:送货费，物流费，商品总价，积分等等;t_payrec.paymodename:网银在线支付、平安银行支付
            omsSOFreeDetails_Discount.setCHARGETYPE("订单折扣优惠");
            // 各种费用金额,元为单位:t_payrec.amount/100
            omsSOFreeDetails_Discount.setCHARGEFEE(commonUtilService.bigDecimal2String(totalPromo));
            // 支付状态rateuom1==费用：1：需要支付，0：不需要支付 rateuom1==支付：未支付330，支付成功300，支付失败320
            omsSOFreeDetails_Discount.setEXTENTS_UDF01(payOnArrival ? "1" : "0");
            // 收费方式chargecategory == chargetype
            omsSOFreeDetails_Discount.setCHARGECATEGORY("订单折扣优惠");
            // 货币类型t_pricerec.moneytypeid/ t_payrec.moneytypeid 1:积分，0：RMB
            omsSOFreeDetails_Discount.setCHARGEDESCR("0");
            feeItems.add(omsSOFreeDetails_Discount);
        }
        /** end  订单折扣优惠 **/

        /** 订单积分总计 **/
        BigDecimal totalPoint = new BigDecimal(0);
        List<OrderItem> itemList = om.getOrderItems();
        if (itemList != null && !itemList.isEmpty()) {
            for (OrderItem oi : itemList) {
                if (oi.getProductPoint() != null) {
                    totalPoint = totalPoint.add(oi.getProductPoint());
                }
            }
        }
        OmsSOFreeDetails omsSOFreeDetails_Point = new OmsSOFreeDetails();
        // 子订单Id
        omsSOFreeDetails_Point.setORDERNO(os.getOrderSubNo());
        // 传行号（自己编号1.2.3.4.5…）
        omsSOFreeDetails_Point.setLINENUMBER(counter++ + "");
        // 订单费用：费用；订单支付：支付
        omsSOFreeDetails_Point.setRATEUOM1("费用");
        // 费用种类 t_pricerec.name:送货费，物流费，商品总价，积分等等;t_payrec.paymodename:网银在线支付、平安银行支付
        omsSOFreeDetails_Point.setCHARGETYPE("积分总计");
        // 各种费用金额,元为单位:t_payrec.amount/100
        omsSOFreeDetails_Point.setCHARGEFEE(commonUtilService.bigDecimal2String(totalPoint));
        // 支付状态rateuom1==费用：1：需要支付，0：不需要支付 rateuom1==支付：未支付330，支付成功300，支付失败320
        omsSOFreeDetails_Point.setEXTENTS_UDF01(payOnArrival ? "1" : "0");
        // 收费方式chargecategory == chargetype
        omsSOFreeDetails_Point.setCHARGECATEGORY("积分总计");
        // 货币类型t_pricerec.moneytypeid/ t_payrec.moneytypeid 1:积分，0：RMB
        omsSOFreeDetails_Point.setCHARGEDESCR("1");
        feeItems.add(omsSOFreeDetails_Point);
        /** end 订单积分总计 **/
        
        
        /** 购买商品获得积分总数 **/
        OmsSOFreeDetails omsSOFreeDetails_PointCommodity = new OmsSOFreeDetails();
        // 子订单Id
        omsSOFreeDetails_PointCommodity.setORDERNO(os.getOrderSubNo());
        // 传行号（自己编号1.2.3.4.5…）
        omsSOFreeDetails_PointCommodity.setLINENUMBER(counter++ + "");
        // 订单费用：费用；订单支付：支付
        omsSOFreeDetails_PointCommodity.setRATEUOM1("费用");
        // 费用种类 t_pricerec.name:送货费，物流费，商品总价，积分等等;t_payrec.paymodename:网银在线支付、平安银行支付
        omsSOFreeDetails_PointCommodity.setCHARGETYPE("购买商品获得积分总数");
        // 各种费用金额,元为单位:t_payrec.amount/100
        omsSOFreeDetails_PointCommodity.setCHARGEFEE(commonUtilService.bigDecimal2String(totalPoint));
        // 支付状态rateuom1==费用：1：需要支付，0：不需要支付 rateuom1==支付：未支付330，支付成功300，支付失败320
        omsSOFreeDetails_PointCommodity.setEXTENTS_UDF01(payOnArrival ? "1" : "0");
        // 收费方式chargecategory == chargetype
        omsSOFreeDetails_PointCommodity.setCHARGECATEGORY("购买商品获得积分总数");
        // 货币类型t_pricerec.moneytypeid/ t_payrec.moneytypeid 1:积分，0：RMB
        omsSOFreeDetails_PointCommodity.setCHARGEDESCR("1");
        feeItems.add(omsSOFreeDetails_PointCommodity);
        /** end 购买商品获得积分总数 **/
        
        
        /** 商品总价，必须有 **/
        OmsSOFreeDetails omsSOFreeDetails_commodityAmount = new OmsSOFreeDetails();
        // 子订单Id
        omsSOFreeDetails_commodityAmount.setORDERNO(os.getOrderSubNo());
        // 传行号（自己编号1.2.3.4.5…）
        omsSOFreeDetails_commodityAmount.setLINENUMBER(counter++ + "");
        // 订单费用：费用；订单支付：支付
        omsSOFreeDetails_commodityAmount.setRATEUOM1("费用");
        // 费用种类 t_pricerec.name:送货费，物流费，商品总价，积分等等;t_payrec.paymodename:网银在线支付、平安银行支付
        omsSOFreeDetails_commodityAmount.setCHARGETYPE("商品总价");
        // 各种费用金额,元为单位:t_payrec.amount/100
        omsSOFreeDetails_commodityAmount.setCHARGEFEE(commonUtilService.bigDecimal2String(om.getTotalProductPrice()));
        // 支付状态rateuom1==费用：1：需要支付，0：不需要支付 rateuom1==支付：未支付330，支付成功300，支付失败320
        omsSOFreeDetails_commodityAmount.setEXTENTS_UDF01(payOnArrival ? "1" : "0");
        // 收费方式chargecategory == chargetype
        omsSOFreeDetails_commodityAmount.setCHARGECATEGORY("商品总价");
        // 货币类型t_pricerec.moneytypeid/ t_payrec.moneytypeid 1:积分，0：RMB
        omsSOFreeDetails_commodityAmount.setCHARGEDESCR("0");
        feeItems.add(omsSOFreeDetails_commodityAmount);
        /** end  商品总价，必须有 **/
        
        /** 人民币总计，必须有 **/
        OmsSOFreeDetails omsSOFreeDetails_totalPayAmount = new OmsSOFreeDetails();
        // 子订单Id
        omsSOFreeDetails_totalPayAmount.setORDERNO(os.getOrderSubNo());
        // 传行号（自己编号1.2.3.4.5…）
        omsSOFreeDetails_totalPayAmount.setLINENUMBER(counter++ + "");
        // 订单费用：费用；订单支付：支付
        omsSOFreeDetails_totalPayAmount.setRATEUOM1("费用");
        // 费用种类 t_pricerec.name:送货费，物流费，商品总价，积分等等;t_payrec.paymodename:网银在线支付、平安银行支付
        omsSOFreeDetails_totalPayAmount.setCHARGETYPE("人民币总计");
        // 各种费用金额,元为单位:t_payrec.amount/100
        omsSOFreeDetails_totalPayAmount.setCHARGEFEE(commonUtilService.bigDecimal2String(om.getTotalPayAmount()));
        // 支付状态rateuom1==费用：1：需要支付，0：不需要支付 rateuom1==支付：未支付330，支付成功300，支付失败320
        omsSOFreeDetails_totalPayAmount.setEXTENTS_UDF01(payOnArrival ? "1" : "0");
        // 收费方式chargecategory == chargetype
        omsSOFreeDetails_totalPayAmount.setCHARGECATEGORY("人民币总计");
        // 货币类型t_pricerec.moneytypeid/ t_payrec.moneytypeid 1:积分，0：RMB
        omsSOFreeDetails_totalPayAmount.setCHARGEDESCR("0");
        feeItems.add(omsSOFreeDetails_totalPayAmount);
        /** end  人民币总计 **/
        
        /** 送货费，必须有 **/
        OmsSOFreeDetails omsSOFeeDetailsTransport = new OmsSOFreeDetails();
        // 子订单Id
        omsSOFeeDetailsTransport.setORDERNO(os.getOrderSubNo());
        // 传行号（自己编号1.2.3.4.5…）
        omsSOFeeDetailsTransport.setLINENUMBER(counter++ + "");
        // 订单费用：费用；订单支付：支付
        omsSOFeeDetailsTransport.setRATEUOM1("费用");
        // 费用种类 t_pricerec.name:送货费，物流费，商品总价，积分等等;t_payrec.paymodename:网银在线支付、平安银行支付
        omsSOFeeDetailsTransport.setCHARGETYPE("送货费");
        // 各种费用金额,元为单位:t_payrec.amount/100, 子订单不做运费
        omsSOFeeDetailsTransport.setCHARGEFEE(commonUtilService.bigDecimal2String(om.getTransportFee()));
        // 支付状态rateuom1==费用：1：需要支付，0：不需要支付 rateuom1==支付：未支付330，支付成功300，支付失败320
        omsSOFeeDetailsTransport.setEXTENTS_UDF01(payOnArrival ? "1" : "0");
        // 收费方式chargecategory == chargetype
        omsSOFeeDetailsTransport.setCHARGECATEGORY("送货费");
        // 货币类型t_pricerec.moneytypeid/ t_payrec.moneytypeid 1:积分，0：RMB
        omsSOFeeDetailsTransport.setCHARGEDESCR("0");
        feeItems.add(omsSOFeeDetailsTransport);
        /** end  送货费，必须有 **/
        
        
        ArrayList<OmsSODetails> detailList = new ArrayList<OmsSODetails>();
        // 订单详情
        List<OrderItem> items = os.getOrderItems();
        if (items != null && !items.isEmpty()) {
            for (OrderItem item : items) {
                boolean isCombine =  OrderMainConst.OrderItem_OrderItemClass_SUITE.getCode().equals(item.getOrderItemClass());
                if(!isCombine){
                    OmsSODetails omsSODetails = new OmsSODetails();
                    // 子订单Id
                    omsSODetails.setORDERNO(os.getOrderSubNo());
                    // skuNo
                    omsSODetails.setSKU(item.getSkuNo());
                    String saleNum = commonUtilService.Long2Str(item.getSaleNum());
                    // saleNum购买数量 QTYORDERED==QtyOrdered_Each两个传一样的值
                    omsSODetails.setQTYORDERED(saleNum);
                    omsSODetails.setQTYORDERED_EACH(saleNum);
                    // 商品销售单价
                    omsSODetails.setD_EDI_01(commonUtilService.bigDecimal2String(item.getUnitPrice()));
                    // 订单行总销售价 单价*数量, 总金额
                    omsSODetails.setD_EDI_02(commonUtilService.bigDecimal2String(item.getSaleTotalMoney()));
                    // 订单来源
                    omsSODetails.setD_EDI_03(om.getOrderSource());
                    // 组合商品skuCode,若不是组合则写空串
                    omsSODetails.setUSERDEFINE1("");
                    // 组合商品名称
                    omsSODetails.setUSERDEFINE2("");
                    // 组合商品单价
                    omsSODetails.setUSERDEFINE3("");
                    // 组合商品数量
                    omsSODetails.setUSERDEFINE4("");
                    omsSODetails.setUSERDEFINE5("");
                    omsSODetails.setUSERDEFINE6("");
                    // 活动编码
                    omsSODetails.setPromotionCode(item.getPromotionCode());
                    // 商品类型, 1:普通商品，2：活动商品，3：积分商品
                    omsSODetails.setProductType(item.getPromotionType());
                    // 备注, detail写空串，7.24饶
                    omsSODetails.setNOTES("");
                    omsSODetails.setORDERLINENO(item.getOrderItemNo());
                    //库区
                    omsSODetails.setD_EDI_05(item.getStockNo());
                    detailList.add(omsSODetails);
                }else{
                    List<OrderCombineRelation> ocrList = orderCombineRelationService.findByOrderItemNoAndCombineNo(item.getOrderItemNo(), item.getCommodityCode());
                    if(ocrList != null && !ocrList.isEmpty()){
                        for(OrderCombineRelation ocr:ocrList){
                            OmsSODetails omsSODetails = new OmsSODetails();
                            // 子订单Id
                            omsSODetails.setORDERNO(os.getOrderSubNo());
                            // skuNo
                            omsSODetails.setSKU(ocr.getSkuNo());
                            Long saleNumC = ocr.getSaleNum();
                            String saleNum = commonUtilService.Long2Str(saleNumC * item.getSaleNum());
                            // saleNum购买数量 QTYORDERED==QtyOrdered_Each两个传一样的值
                            omsSODetails.setQTYORDERED(saleNum);
                            omsSODetails.setQTYORDERED_EACH(saleNum);
                            // 商品销售单价
                            omsSODetails.setD_EDI_01(commonUtilService.bigDecimal2String(ocr.getUnitPrice()));
                            // 订单行总销售价 单价*数量, 总金额
                            String totalSaleMoney = null;
                            if(StringUtils.isBlank(saleNum)){
                                totalSaleMoney = "";
                            }else{
                                BigDecimal tsTotal = new BigDecimal(ocr.getSaleNum() * ocr.getUnitPrice().doubleValue());
                                totalSaleMoney = tsTotal.setScale(2, 4).toString();
                            }
                            omsSODetails.setD_EDI_02(totalSaleMoney);
                            // 订单来源
                            omsSODetails.setD_EDI_03(om.getOrderSource());
                            // 组合商品skuCode,若不是组合则写空串
                            omsSODetails.setUSERDEFINE1(item.getSkuNo());
                            // 组合商品名称
                            omsSODetails.setUSERDEFINE2(item.getCommodityName() );
                            // 组合商品单价
                            omsSODetails.setUSERDEFINE3(commonUtilService.bigDecimal2String(item.getUnitPrice()));
                            // 组合商品数量
                            omsSODetails.setUSERDEFINE4(commonUtilService.Long2Str(item.getSaleNum()));
                            omsSODetails.setUSERDEFINE5("");
                            omsSODetails.setUSERDEFINE6("");
                            // 活动编码
                            omsSODetails.setPromotionCode(item.getPromotionCode());
                            // 商品类型, 1:普通商品，2：活动商品，3：积分商品
                            omsSODetails.setProductType(item.getPromotionType());
                            // 备注, detail写空串，7.24饶
                            omsSODetails.setNOTES("");
                            omsSODetails.setORDERLINENO(item.getOrderItemNo() + "G" + ocr.getSkuNo());
                            //库区
                            omsSODetails.setD_EDI_05(item.getStockNo());
                            detailList.add(omsSODetails);
                        }
                    }
                }

            }
        }
        
        // 订单详情
        List<OrderItemVirtual> virtualItems = om.getOrderItemVirtuals();
        if (virtualItems != null && !virtualItems.isEmpty()) {
            for (OrderItemVirtual item : virtualItems) {
                OmsSODetails detailsItem = new OmsSODetails();
                //
                detailsItem.setORDERNO(os.getOrderSubNo());
                // skuNo
                detailsItem.setSKU(item.getSkuNo());
                String saleNum = commonUtilService.Long2Str(item.getSaleNum());
                // saleNum购买数量 QTYORDERED==QtyOrdered_Each两个传一样的值
                detailsItem.setQTYORDERED(saleNum);
                // saleNum购买数量 QTYORDERED==QtyOrdered_Each两个传一样的值
                detailsItem.setQTYORDERED_EACH(saleNum);
                // 商品销售单价
                detailsItem.setD_EDI_01(commonUtilService.bigDecimal2String(item.getUnitPrice()));
                // 订单行总销售价 单价*数量, 总金额
                detailsItem.setD_EDI_02(commonUtilService.bigDecimal2String(item.getSaleAmount()));
                // 订单来源
                detailsItem.setD_EDI_03(om.getOrderSource());
                // 组合商品id
                detailsItem.setUSERDEFINE1("");
                // 组合商品名称
                detailsItem.setUSERDEFINE2("");
                // 组合商品单价
                detailsItem.setUSERDEFINE3("");
                // 组合商品数量
                detailsItem.setUSERDEFINE4("");
                detailsItem.setUSERDEFINE5("");
                detailsItem.setUSERDEFINE6("");
                
                detailsItem.setORDERLINENO(item.getOrderItemNo());

                // 活动编码
                detailsItem.setPromotionCode(item.getPromotionCode());
                // 商品类型, 1:普通商品，2：活动商品，3：积分商品
                detailsItem.setProductType(item.getPromotionType());
                // 备注
                detailsItem.setD_EDI_04(item.getReceiveMobile());
                detailsItem.setNOTES("");
                //库区
                detailsItem.setD_EDI_05(item.getStockNo());
                detailList.add(detailsItem);
            }
        }
        
        omsSOHeader.setDetailsFreeItem((OmsSOFreeDetails[]) feeItems.toArray(new OmsSOFreeDetails[0]));
        omsSOHeader.setDetailsItem((OmsSODetails[]) detailList.toArray(new OmsSODetails[0]));
        // 仓库固定值：0196
        omsSOHeader.setWAREHOUSEID("0196");
        // 
        omsSOHeader.setOrderNo(os.getOrderSubNo());
        // SO:正常订单；TH:换货出库单
        if (OrderMainConst.OrderMain_OrderCategory_ChangeOut.getCode().equals(om.getOrderCategory())) {
            omsSOHeader.setORDERTYPE(OrderCreateService.WMS_OmsSOInfo_OrderType_ChgOut);
        } else {
            omsSOHeader.setORDERTYPE(OrderCreateService.WMS_OmsSOInfo_OrderType_Sale);
        }
        // 订单创建时间YYYY-MM-DD HH24:MI:SS
        omsSOHeader.setORDERTIME(CommonUtilService.dateToStr(om.getOrderTime(), "yyyy-MM-dd HH:mm:ss"));
        // 收货时间YYYY-MM-DD HH24:MI:SS
        omsSOHeader.setREQUIREDDELIVERYTIME((os == null ||os.getHopeArrivalTime()==null) ? "" : CommonUtilService.dateToStr(
                new Date(os.getHopeArrivalTime()), "yyyy-MM-dd HH:mm:ss"));
        // 默认：01
        omsSOHeader.setCUSTOMERID("01");
        // 正常订单：外部渠道如btc单号，换货订单：“”
        omsSOHeader.setSOREFERENCE1(os.getOrderSubNo());
        // 默认传：""。
        //承运人  取第三方物流商 id, 如果为空写0,7.24针对门店自提distributeType==2
        omsSOHeader.setCARRIERID(os.getDeliveryMerchantNo() == null ? "0": os.getDeliveryMerchantNo());
        //订单类型  虚拟  普通 ...
        omsSOHeader.setH_EDI_12(om.getOrderType());
        //H_EDI_13:验证码
        omsSOHeader.setH_EDI_13(os.getCheckCode());
        //H_EDI_14:商家编号
        omsSOHeader.setH_EDI_14(om.getMerchantNo());
        //H_EDI_15:商家类型
        omsSOHeader.setH_EDI_15(om.getMerchantType());
        
        omsSOHeader.setSOREFERENCE2("");
        // 送货前是否需要确认；默认：""
        omsSOHeader.setSOREFERENCE3("");
        // 默认：""Y/N 是否生鲜订单
        boolean isFastFoodOrder = CommonConst.OrderSub_ShipCat_fastFood.getCode().equals(os.getShipCat());
        omsSOHeader.setSOREFERENCE4(isFastFoodOrder ? "Y" : "N");
        // 会员等级 例如：大客户组、银卡会员等
        omsSOHeader.setSOREFERENCE5(getMemberCardLevelChn(om.getMemberCardLevel()));
        // 默认：9
        omsSOHeader.setPRIORITY("9");
        // 第三方物流商 中文名称,没有写0 , 7.24饶
        omsSOHeader.setCARRIERNAME(os.getDeliveryMerchantName() == null ? "0" : os.getDeliveryMerchantName());
        // 收货人编号 默认为：0
        omsSOHeader.setCONSIGNEEID("0");
        // 收货人名称
        omsSOHeader.setCONSIGNEENAME(os == null ? "" : os.getUserName());
        // 收货人地址 顾客自己填写的地址（最小地址）
        omsSOHeader.setC_ADDRESS1(os == null ? "" : os.getAddressDetail());
        // 配送方式中文名称 如：天虹配送、天虹门店自提等
        omsSOHeader.setC_ADDRESS2(os == null ? "" : getDistributeTypeChn(os.getDistributeType()));
        // 区域代码 区域最小编码
        omsSOHeader.setC_ADDRESS3(os == null ? "" : os.getAddressCode());
        //add merchant id by self_point code;
        Long merchant = commonUtilService.StrToLong(os.getSelfFetchAddress());
        SelfTakePoint stp = selfTakeMerchantUtil.getMerchantBySelfTakePointId(merchant);
        omsSOHeader.setC_ADDRESS4((stp == null || stp.getPointDeliverPartnerId() == null) ? "" : stp.getPointDeliverPartnerId().toString());
        // 默认：""
        omsSOHeader.setC_CITY("");
        // 默认：""
        omsSOHeader.setC_PROVINCE("");
        // 商城没有邮编，默认：""
        omsSOHeader.setC_ZIP("");
        // 购买人名称
        omsSOHeader.setC_CONTACT(om.getCustomerName());
        // 收货人移动电话
        omsSOHeader.setC_TEL1(os == null ? "" : os.getMobPhoneNum());
        // 收货人固话
        omsSOHeader.setC_TEL2(os == null ? "" : os.getPhoneNum());
        // 默认：""
        omsSOHeader.setROUTE("");
        // 订单来源中文:商城订单来源
        omsSOHeader.setUSERDEFINE5(om.getOrderSource());
        // 顾客留言 配送方式：送货时间 + 其他要求
        String deliveryDateFlag = om.getDeliveryDateFlag();
        if(StringUtils.isBlank(deliveryDateFlag) || "null".equals(deliveryDateFlag)){
            deliveryDateFlag = "1";
        }
        String notes = os.getDeliveryRemark() == null ? "" : os
                .getDeliveryRemark();
        omsSOHeader.setNOTES(deliveryDateFlag + ";" + notes);
        // 后台客服留言，需打印至拣货单和物流交接单
        String clientServiceRemark = om.getClientServiceRemark();
        if(OrderMainConst.OrderMain_OrderCategory_ChangeOut.getCode().equals(om.getOrderCategory())){
            //换货出库单需考虑是否取回原商品
            Map<String, String> disType2Map = commonCacheUtil.getDistributeTypeLevel2Map();
            String disType2Name = disType2Map.get(os.getDeliveryPriority());
            if(StringUtils.isNoneBlank(disType2Name)){
                clientServiceRemark = clientServiceRemark+"("+disType2Name+")";
            }
        }
        omsSOHeader.setH_EDI_01(clientServiceRemark);
        //配送优先级
        omsSOHeader.setH_EDI_15(os.getDeliveryPriority() == null ? "0" : os.getDeliveryPriority());
        // 配送要求
        omsSOHeader.setH_EDI_02("");
        //  换货出库单,写意向子订单Id,
        if(OrderMainConst.OrderMain_OrderCategory_ChangeOut.getCode().equals(om.getOrderCategory())){
            String relatedOrigin = os.getOrderSubRelatedOrigin();
            omsSOHeader.setH_EDI_03(relatedOrigin);
        }
        // 订单总重量
        //omsSOHeader.setH_EDI_04(commonUtilService.bigDecimal2String(om.getWeight()));
        omsSOHeader.setH_EDI_04(om.getWeight() == null ? "0" : om.getWeight().toString());
        // 货到付款:银行卡、现金、天虹卡.之一; 在线支付：""
        if (CommonConstService.BOOLEAN_TRUE.equals(om.getIfPayOnArrival())) {
        	if(payMode!=null){
        		omsSOHeader.setH_EDI_05(PayMode.getPayName(payMode.getPayModeCode()));
        	}else{
        		omsSOHeader.setH_EDI_05("现金");
        	}
        }else{
        	omsSOHeader.setH_EDI_05("网银在线支付");
        }
        
        List<OrderInvoice> oinvoices = orderInvoiceService.findByField(OrderInvoice_.orderSubNo, os.getOrderSubNo());
        OrderInvoice oinvoice = oinvoices == null || oinvoices.size() == 0 ? null : oinvoices.get(0);
        // 发票抬头（个人、公司名称）
        omsSOHeader.setH_EDI_06(oinvoice == null ? "" : oinvoice.getInvoiceHead());
        // 发票类型0.普通发票1.增值税发票2.无需发票
        omsSOHeader.setH_EDI_07(oinvoice == null ? "" : oinvoice.getInvoiceType());
        // 是否打印发票0:不打印；1：打印
        omsSOHeader.setH_EDI_08(commonUtilService.Long2Str(om.getNeedInvoice()));
        //发票内容
        omsSOHeader.setH_EDI_09(oinvoice == null ? "" : oinvoice.getInvoiceContent());
        // 默认：0
        omsSOHeader.setH_EDI_10(os.getSelfFetchAddress() == null? "0" : os.getSelfFetchAddress());//自提点id
        //优先处理订单，传：0、1;( 0：普通，1：优先),140704饶添加
        omsSOHeader.setH_EDI_11(os.getDeliveryPriority() == null ? "0" : os.getDeliveryPriority());
        OmsSOHeader [] hs = new  OmsSOHeader[1];
        hs[0]=omsSOHeader;
        soInfo.setOmsSOHeaders(hs);
        return soInfo;
    }
    
    @Override
    public StockUnLockByOms queryInventoryUnLock(String orderNo) {
        OrderMain om = orderMainService.findByOrderNo(orderNo);
        OrderSub os = om.getOrderSubs().get(0);
        StockUnLockByOms sbo = new StockUnLockByOms();
        // 
        sbo.setOrderNo(os.getOrderSubNo());
        List<StockUnLockByOmsDetail> stockUnLockByOmsDetails = new ArrayList<StockUnLockByOmsDetail>();
        sbo.setStockUnLockByOmsDetails(stockUnLockByOmsDetails);
        if( om.getOrderItems()!=null && !om.getOrderItems().isEmpty()){
	        for (OrderItem oi : om.getOrderItems()) {
                boolean isCombine = OrderMainConst.OrderItem_OrderItemClass_SUITE.getCode().equals(
                        oi.getOrderItemClass());
                if (isCombine) {
                    List<OrderCombineRelation> ocrList = orderCombineRelationService.findByOrderItemNoAndCombineNo(
                            oi.getOrderItemNo(), oi.getCommodityCode());
                    if (ocrList != null && !ocrList.isEmpty()) {
                        for (OrderCombineRelation ocr : ocrList) {
                            StockUnLockByOmsDetail stockUnLockByOmsDetail = new StockUnLockByOmsDetail();
                            // 行号
                            stockUnLockByOmsDetail.setOrderItemNo(oi.getOrderItemNo());
                            stockUnLockByOmsDetail.setSaleNum(ocr.getSaleNum());
                            // SKU编码
                            stockUnLockByOmsDetail.setSkuNo(ocr.getSkuNo());
                            // 商品条码
                            stockUnLockByOmsDetail.setBarCode(ocr.getBarCode());
                            // 渠道ID
                            stockUnLockByOmsDetail.setChannelCode(om.getOrderSource());
                            // 锁定零数
                            // stockUnLockByOmsDetail.setOdd(1l);
                            // 商品类型, 1:普通商品，2：活动商品，3：积分商品
                            stockUnLockByOmsDetail.setProdType(oi.getPromotionType());
                            // 活动CODE
                            stockUnLockByOmsDetail.setPromotionCode(oi.getPromotionCode());

                            stockUnLockByOmsDetails.add(stockUnLockByOmsDetail);
                        }
                    }
                }else{
                    StockUnLockByOmsDetail stockUnLockByOmsDetail = new StockUnLockByOmsDetail();
                    // 行号
                    stockUnLockByOmsDetail.setOrderItemNo(oi.getOrderItemNo());
                    stockUnLockByOmsDetail.setSaleNum(oi.getSaleNum());
                    // SKU编码
                    stockUnLockByOmsDetail.setSkuNo(oi.getSkuNo());
                    // 商品条码
                    stockUnLockByOmsDetail.setBarCode(oi.getBarCode());
                    // 渠道ID
                    stockUnLockByOmsDetail.setChannelCode(om.getOrderSource());
                    // 锁定零数
        //            stockUnLockByOmsDetail.setOdd(1l);
                    // 商品类型, 1:普通商品，2：活动商品，3：积分商品
                    stockUnLockByOmsDetail.setProdType(oi.getPromotionType());
                    // 活动CODE
                    stockUnLockByOmsDetail.setPromotionCode(oi.getPromotionCode());
        
                    stockUnLockByOmsDetails.add(stockUnLockByOmsDetail); 
                }
	        }
        }
        
        if( om.getOrderItemVirtuals()!=null && !om.getOrderItemVirtuals().isEmpty()){
	        for (OrderItemVirtual oiv : om.getOrderItemVirtuals()) {
	            StockUnLockByOmsDetail stockUnLockByOmsDetail = new StockUnLockByOmsDetail();
	            // 行号
	            stockUnLockByOmsDetail.setOrderItemNo(oiv.getOrderItemNo());
	            stockUnLockByOmsDetail.setSaleNum(oiv.getSaleNum());
	            // SKU编码
	            stockUnLockByOmsDetail.setSkuNo(oiv.getSkuNo());
	            // 商品条码
	            //stockUnLockByOmsDetail.setBarCode(oi.getCheckCode());
	            // 渠道ID
	            stockUnLockByOmsDetail.setChannelCode(om.getOrderSource());
	            // 锁定零数
	//            stockUnLockByOmsDetail.setOdd(1l);
	            // 商品类型, 1:普通商品，2：活动商品，3：积分商品
                stockUnLockByOmsDetail.setProdType(oiv.getPromotionType());
                // 活动CODE
                stockUnLockByOmsDetail.setPromotionCode(oiv.getPromotionCode());	
	            stockUnLockByOmsDetails.add(stockUnLockByOmsDetail);
	        }
        }
        return sbo;
    }
    /**
     * @param om
     * @return StockLockByOms
     */
    @Override
    public StockLockByOms queryInventoryLock(String orderNo) {
        OrderMain om = orderMainService.findByOrderNo(orderNo);
        StockLockByOms sbo = new StockLockByOms();
        OrderSub os = om.getOrderSubs().get(0);
        // 单号,子订单Id, 
        sbo.setOrderNo(os.getOrderSubNo());
        // 锁库人员
        sbo.setCreator("sys");
        // 客户姓名
        sbo.setCustName(om.getMemberNo());//update 20140818 for 第三方会员id过长会导致大朗锁库存失败
        List<StockLockByOmsDetail> stockLockByOmsDetails = new ArrayList<StockLockByOmsDetail>();
        sbo.setStockLockByOmsDetails(stockLockByOmsDetails);
        
        List<OrderItem> itemList =  orderItemService.findByField(OrderItem_.orderNo, om.getOrderNo());
        if (itemList != null && !itemList.isEmpty()) {
            for (OrderItem oi : itemList) {
                //8.19 锁库不给具体组合信息
//                boolean isCombine = OrderMainConst.OrderItem_OrderItemClass_SUITE.getCode().equals(
//                        oi.getOrderItemClass());
//                if (isCombine) {
//                    List<OrderCombineRelation> ocrList = orderCombineRelationService.findByOrderItemNoAndCombineNo(oi.getOrderItemNo(), oi.getCommodityCode());
//                    if(ocrList != null && !ocrList.isEmpty()){
//                        for(OrderCombineRelation ocr:ocrList){
//                            StockLockByOmsDetail stockLockByOmsDetail = new StockLockByOmsDetail();
//                            stockLockByOmsDetail.setSeqNo(NumberUtils.toLong(oi.getOrderItemNo()));
//                            // 商品条码
//                            stockLockByOmsDetail.setBarCode(ocr.getBarCode());
//                            // 渠道ID
//                            stockLockByOmsDetail.setChannelCode(om.getOrderSource());
//                            // 锁定零数
//                            stockLockByOmsDetail.setOdd(1l);
//                            // 包装数量
//                            stockLockByOmsDetail.setPackSize(1l);
//                            // 锁定件数
//                            stockLockByOmsDetail.setPcs(1l);
//                            // 商品类型, 1:普通商品，2：活动商品，3：积分商品
//                            stockLockByOmsDetail.setProdType(NumberUtils.toLong(oi.getPromotionType()));
//
//                            // 活动CODE
//                            stockLockByOmsDetail.setPromotionCode(oi.getPromotionCode());
//                            // 锁定数量
//                            stockLockByOmsDetail.setQty(ocr.getSaleNum());
//                            stockLockByOmsDetail.setSkuCode(ocr.getSkuNo());
//                            // 备注
//                            stockLockByOmsDetail.setRemark("");
//                            // 单据记录数,
//                            stockLockByOmsDetail.setRows(new Long(itemList.size()));
//                            // SKU编码
//                            // stockLockByOmsDetail.setSkuId(CommonUtilService.string2Long(oi.getSkuNo()));
//                            stockLockByOmsDetails.add(stockLockByOmsDetail);
//                        }
//                    }
//                } else {
                    // 行号
                    StockLockByOmsDetail stockLockByOmsDetail = new StockLockByOmsDetail();
                    stockLockByOmsDetail.setSeqNo(NumberUtils.toLong(oi.getOrderItemNo()));
                    // 商品条码
                    stockLockByOmsDetail.setBarCode(oi.getBarCode());
                    // 渠道ID
                    stockLockByOmsDetail.setChannelCode(om.getOrderSource());
                    // 文档没有
                    // stockLockByOmsDetail.setChannelId(1l);
                    // 锁定零数
                    stockLockByOmsDetail.setOdd(1l);
                    // 包装数量
                    stockLockByOmsDetail.setPackSize(1l);
                    // 锁定件数
                    stockLockByOmsDetail.setPcs(1l);
                    // 商品类型, 1:普通商品，2：活动商品，3：积分商品
                    stockLockByOmsDetail.setProdType(NumberUtils.toLong(oi.getPromotionType()));

                    // 活动CODE
                    stockLockByOmsDetail.setPromotionCode(oi.getPromotionCode());
                    // 锁定数量
                    stockLockByOmsDetail.setQty(oi.getSaleNum());
                    stockLockByOmsDetail.setSkuCode(oi.getSkuNo());
                    // 备注
                    stockLockByOmsDetail.setRemark("");
                    // 单据记录数,
                    stockLockByOmsDetail.setRows(new Long(itemList.size()));
                    // SKU编码
                    // stockLockByOmsDetail.setSkuId(CommonUtilService.string2Long(oi.getSkuNo()));

                    stockLockByOmsDetails.add(stockLockByOmsDetail);
//                }
            }
        }
        
        
        List<OrderItemVirtual> itemVirtualList =  orderItemVirtualService.findByField(OrderItemVirtual_.orderNo, om.getOrderNo());
        if (itemVirtualList != null && !itemVirtualList.isEmpty()) {
            for (OrderItemVirtual oiv : itemVirtualList) {
                StockLockByOmsDetail stockLockByOmsDetail = new StockLockByOmsDetail();
                // 行号
                stockLockByOmsDetail.setSeqNo(NumberUtils.toLong(oiv.getOrderItemNo()));
                // 商品条码
//                stockLockByOmsDetail.setBarCode(oiv.getBarCode());
                // SKUID，文档没有
                 stockLockByOmsDetail.setSkuCode(oiv.getSkuNo());
                // 渠道ID
                stockLockByOmsDetail.setChannelCode(om.getOrderSource());
                // 文档没有
                // stockLockByOmsDetail.setChannelId(1l);
                // 锁定零数
                stockLockByOmsDetail.setOdd(1l);
                // 包装数量
                stockLockByOmsDetail.setPackSize(1l);
                // 锁定件数
                stockLockByOmsDetail.setPcs(1l);
                // 商品类型, 1:普通商品，2：活动商品，3：积分商品
                stockLockByOmsDetail.setProdType(NumberUtils.toLong(oiv.getPromotionType()));

                // 活动CODE
                stockLockByOmsDetail.setPromotionCode(oiv.getPromotionCode());
                // 锁定数量
                stockLockByOmsDetail.setQty(oiv.getSaleNum());
                stockLockByOmsDetail.setSkuCode(oiv.getSkuNo());
                // 备注
                stockLockByOmsDetail.setRemark("");
                // 单据记录数,
                stockLockByOmsDetail.setRows(new Long(itemList.size()));
                // SKU编码
                // stockLockByOmsDetail.setSkuId(CommonUtilService.string2Long(oi.getSkuNo()));

                stockLockByOmsDetails.add(stockLockByOmsDetail);
            }
        }
        return sbo;
    }

    private String getMemberCardLevelChn(String id){
        if(CommonConst.OrderMain_MemberCardLevel1.getCode().equals(id)){
            return CommonConst.OrderMain_MemberCardLevel1.getDesc();
        }
        if(CommonConst.OrderMain_MemberCardLevel2.getCode().equals(id)){
            return CommonConst.OrderMain_MemberCardLevel2.getDesc();
        }
        if(CommonConst.OrderMain_MemberCardLevel3.getCode().equals(id)){
            return CommonConst.OrderMain_MemberCardLevel3.getDesc();
        }
        return "";
    }
    
    private String getDistributeTypeChn(String id){
        if(CommonConst.OrderSub_Distribute_Type1.getCode().equals(id)){
            return CommonConst.OrderSub_Distribute_Type1.getDesc();
        }
        if(CommonConst.OrderSub_Distribute_Type2.getCode().equals(id)){
            return CommonConst.OrderSub_Distribute_Type2.getDesc();
        }
        if(CommonConst.OrderSub_Distribute_Type7.getCode().equals(id)){
            return CommonConst.OrderSub_Distribute_Type7.getDesc();
        }
        if(CommonConst.OrderSub_Distribute_Type0.getCode().equals(id)){
            return CommonConst.OrderSub_Distribute_Type0.getDesc();
        }
        return "";
    }
    
}

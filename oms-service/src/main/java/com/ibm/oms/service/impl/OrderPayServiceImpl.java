package com.ibm.oms.service.impl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.constant.PayType;
import com.ibm.oms.dao.intf.OrderPayDao;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.oms.domain.persist.OrderPay_;
import com.ibm.oms.domain.persist.OrderRetChgItem;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.service.OrderPayService;
import com.ibm.sc.service.impl.BaseServiceImpl;

/**
 * 
 * Creation date:2014-03-14 04:20:49
 * 
 * @author:Yong Hong Luo
 */
@Service("orderPayService")
public class OrderPayServiceImpl extends BaseServiceImpl<OrderPay, Long> implements OrderPayService {

    private OrderPayDao orderPayDao;

    @Autowired
    public void setOrderPayDao(OrderPayDao orderPayDao) {
        super.setBaseDao(orderPayDao);
        this.orderPayDao = orderPayDao;
    }

    public List<OrderPay> getByOrderMainNo(String orderMainNo) {
        return this.orderPayDao.getByOrderMainNo(orderMainNo);
    }

    /**
     * 保存逆向订单的换货抵扣支付方式(换货入库单、出库单)
     * 目前没有记录购物券的支付记录
     * @param orderMainR 逆向订单
     */
    public void saveChangePaidByOrderMain(OrderMain orderMain) {
        if (null == orderMain) {
            return;
        }
        BigDecimal payAmountTotal = orderMain.getTotalPayAmount();
        boolean saleOrderFlag = false;//换货出库单的标识
        if (orderMain.getBillType().equals(CommonConst.OrderMain_BillType_Positive.getCodeLong()) ){
            saleOrderFlag = true;
        }
        if(!saleOrderFlag){
            //BigDecimal productPriceTotal = orderMain.getTotalProductPrice();//商品总价
            BigDecimal productPriceTotal = new BigDecimal(0);//商品总价
            BigDecimal discountTotal = new BigDecimal(0);//总折扣优惠
            BigDecimal couponTotal = new BigDecimal(0);//总用券
            payAmountTotal = new BigDecimal(0);//实退金额（已经减掉了折扣优惠）
            OrderSub orderSub = orderMain.getOrderSubs().get(0);
            for(OrderRetChgItem item:orderSub.getOrderRetChgItems()){
                BigDecimal itemDisc = item.getItemDiscount();//折扣优惠
                if(null!=itemDisc){
                    discountTotal = discountTotal.add(itemDisc);
                }
                BigDecimal itemCoupon = item.getCouponTotalMoney();//购物券
                if(null!=itemCoupon){
                    couponTotal = couponTotal.add(itemCoupon);
                }
                payAmountTotal = payAmountTotal.add(item.getPayAmount());//每个明细的payAmount相加
                BigDecimal unitPrice = item.getUnitPrice();//商品折前单价
                if (null != unitPrice) {
                    BigDecimal saleNum = item.getSaleNum() == null ? new BigDecimal(0) : new BigDecimal(
                            item.getSaleNum());
                    BigDecimal unitPriceTotal = unitPrice.multiply(saleNum);
                    productPriceTotal = productPriceTotal.add(unitPriceTotal);// 商品折前总价
                }
            }
            orderMain.setDiscountTotal(discountTotal);
            orderMain.setTotalPromo(couponTotal);
            orderMain.setTotalPayAmount(payAmountTotal);
            orderMain.setTotalProductPrice(productPriceTotal);
        }
        //换货抵扣
        OrderPay pay = new OrderPay();
        pay.setIdOrder(orderMain.getId());
        pay.setOrderNo(orderMain.getOrderNo());
        pay.setPayAmount(payAmountTotal);
        pay.setPayCode(PayType.CHANGE_PAID.getId());
        pay.setPayName(PayType.CHANGE_PAID.getPayName());
        pay.setBillType(CommonConst.OrderMain_BillType_Negative.getCodeLong());
        if(saleOrderFlag){
            pay.setBillType(CommonConst.OrderMain_BillType_Positive.getCodeLong());
        }
        pay.setPayTime(orderMain.getOrderTime());
        super.save(pay);
        // 判断是否包含运费(出库单)
        if (saleOrderFlag && orderMain.getTransportFee().doubleValue() > 0) {
            OrderPay feePay = new OrderPay();
            feePay.setIdOrder(orderMain.getId());
            feePay.setOrderNo(orderMain.getOrderNo());
            feePay.setPayAmount(orderMain.getTransportFee());
            feePay.setPayCode(PayType.PAY_ON_ARRIVE.getId());
            feePay.setPayName(PayType.PAY_ON_ARRIVE.getPayName());
            feePay.setBillType(CommonConst.OrderMain_BillType_Negative.getCodeLong());
            if(saleOrderFlag){
                pay.setBillType(CommonConst.OrderMain_BillType_Positive.getCodeLong());
            }
            feePay.setPayTime(orderMain.getOrderTime());
            super.save(feePay);
        }
    }
    
    public void  update(Collection<OrderPay> objs) {
    	orderPayDao.update(objs);
    }

    @Override
    public BigDecimal gettotalPaiedByOrderNo(String orderNo) {
        List<OrderPay> opList = orderPayDao.findByField(OrderPay_.orderNo, orderNo);
        if(opList == null || opList.isEmpty()){
            return new BigDecimal(0);
        }
        BigDecimal bdRet = new BigDecimal(0);
        for(OrderPay op:opList){
            bdRet = bdRet.add(op.getPayAmount());
        }
        return bdRet;
    }

}

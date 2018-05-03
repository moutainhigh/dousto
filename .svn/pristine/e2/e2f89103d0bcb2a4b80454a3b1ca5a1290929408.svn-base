package com.ibm.oms.service.business.trans.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.constant.OrderStatusAction;
import com.ibm.oms.dao.constant.PayType;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderItemPay;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.intf.intf.TmsPayDTO;
import com.ibm.oms.intf.intf.inner.TmsPayDTOElement;
import com.ibm.oms.service.OrderItemPayService;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderPayService;
import com.ibm.oms.service.OrderStatusSyncLogService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.OrderStatusService;
import com.ibm.oms.service.business.trans.OrderCreateTrans;
import com.ibm.oms.service.business.trans.TmsOmsReceivePaymentTransService;
import com.ibm.oms.service.util.CommonConstService;

/**
 * @author liucy
 * 
 */
@Service("tmsOmsReceivePaymentTransService")
public class TmsOmsReceivePaymentTransServiceImpl implements TmsOmsReceivePaymentTransService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    OrderMainService orderMainService;

    @Autowired
    OrderSubService orderSubService;

    @Autowired
    OrderStatusService orderStatusService;

    @Autowired
    OrderItemPayService orderItemPayService;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    OrderPayService orderPayService;

    @Autowired
    OrderNoService orderNoService;
    
    @Autowired
    OrderCreateService orderCreateService;
    
    @Autowired 
    OrderStatusSyncLogService orderStatusSyncLogService;
    
    @Autowired
    OrderCreateTrans orderCreateTrans;
    
    @Override
    public CommonOutputDTO updateTmsOmsPayment(TmsPayDTO tmsPayDTO) {
        CommonOutputDTO output = new CommonOutputDTO();
        String orderSubNo = tmsPayDTO.getTxLogisticID();
        OrderMain om = orderMainService.getByOrderSubNo(orderSubNo);
        if (om == null) {
            // 校验失败，保存接口调用日志
            output.setCode(CommonConstService.FAILED);
            output.setMsg("无法找到主订单：" + orderSubNo);
            return output;
        }
        List<OrderSub> os = om.getOrderSubs();
        if (os == null || os.isEmpty()) {
            // 校验失败，保存接口调用日志
            output.setCode(CommonConstService.FAILED);
            output.setMsg("无法找到子订单：" + orderSubNo);
            return output;
        }
        return handlerOrderPay(tmsPayDTO, output, om, os.get(0));
    }

    private CommonOutputDTO handlerOrderPay(TmsPayDTO tmsPayDTO, CommonOutputDTO commonDto, OrderMain om, OrderSub os) {
        // // S017180：from 0171(货到付款订单已发货) to 0460(货到付款已支付)
        Boolean statusSaveFlag = orderStatusService.saveProcess(tmsPayDTO.getTxLogisticID(), OrderStatusAction.S017180,
                null, null, null);
        if (statusSaveFlag) {
            List<TmsPayDTOElement> elements = tmsPayDTO.getElement();
            if(elements == null || elements.isEmpty()){
                orderCreateTrans.saveOrderToR3(om, os);
                return commonDto;
            }
            Date nowTime = new Date();
            for(TmsPayDTOElement element:elements){
                OrderPay op = new OrderPay();
                op.setIdOrder(om.getId());
                op.setOrderNo(om.getOrderNo());
                op.setRemark(tmsPayDTO.getRemark());
                op.setOperatorName(tmsPayDTO.getOperator());
                op.setPayAmount(new BigDecimal(element.getMoney()));
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    if(StringUtils.isBlank(tmsPayDTO.getOccurtime())){
                        op.setPayTime(new Date());
                    }else{
                        op.setPayTime(df.parse(tmsPayDTO.getOccurtime()));
                    }
                } catch (ParseException e) {
                    logger.error("{}", e);
                    op.setPayTime(nowTime);
                }
                switch (element.getPaymode()) {
                    case 1:
                        op.setPayCode(PayType.CASH.getId());
                        op.setPayName(PayType.CASH.getPayName());
                        break;
                    case 2:
                        op.setPayCode(PayType.BANK_CARD.getId());
                        op.setPayName(PayType.BANK_CARD.getPayName());
                        break;
                    case 3:
                        op.setPayCode(PayType.SHOPPING_CARD.getId());
                        op.setPayName(PayType.SHOPPING_CARD.getPayName());
                        break;
                }
                op.setBillType(os.getBillType());
                op.setDateCreated(nowTime);
                op.setDateUpdated(nowTime);
                op.setIsDeleted(0l);
                // 写入ORDER_PAY
                orderPayService.save(op);
                // 写入ORDER_ITEM_PAY
                saveOrderItemPayFromTms(om, os, tmsPayDTO);
            }

            orderCreateService.promoResourceAdd(om.getBatchNum(), om.getOrderNo());
            om.setFinishTime(new Date());
            orderMainService.update(om);
            // 保存接口调用日志
            commonDto.setCode(CommonConstService.OK);
            //货到付款 同步明细与支付
            orderCreateTrans.saveOrderToR3(om, os);
        }
        return commonDto;
    }

    // 按照所有明细行平摊订单级支付明细
    private boolean saveOrderItemPayFromTms(OrderMain orderMain, OrderSub orderSub, TmsPayDTO tmsPayDTO) {
        List<OrderItem> orderItems = orderItemService.getByOrdeSubNo(orderSub.getOrderSubNo());
        List<TmsPayDTOElement> elements = tmsPayDTO.getElement();
        if(elements == null || elements.isEmpty()){
            return true;
        }
        for(TmsPayDTOElement element:elements){
            // 订单行应付金额:折后总价
            // 订单行应付金额:折后总价
            BigDecimal itemTotalAmount = new BigDecimal(0.0);
            for (OrderItem oi : orderItems) {
                itemTotalAmount = itemTotalAmount.add(oi.getPayAmount());
            }
            
            // 累计分摊金额
            BigDecimal addedPayAmountDTOTotal = new BigDecimal(0.0);
            // 现金or银行卡or天虹卡的支付金额
            BigDecimal dtoPayAmount = new BigDecimal(element.getMoney()).setScale(2, BigDecimal.ROUND_UP);
            int size = orderItems.size();
            for (int i = 0; i < size; i++) {
                OrderItem oi = orderItems.get(i);
                OrderItemPay orderItemPay = new OrderItemPay();
                orderItemPay.setPayAmount(dtoPayAmount);
                switch(element.getPaymode()){
                case 1:
                    orderItemPay.setPayCode(PayType.CASH.getId());
                    orderItemPay.setPayName(PayType.CASH.getPayName());
                    break;
                case 2:
                    orderItemPay.setPayCode(PayType.BANK_CARD.getId());
                    orderItemPay.setPayName(PayType.BANK_CARD.getPayName());
                    break;
                case 3:
                    orderItemPay.setPayCode(PayType.SHOPPING_CARD.getId());
                    orderItemPay.setPayName(PayType.SHOPPING_CARD.getPayName());
                    break;
                }
                if (i == (size - 1)) {
                    // 订单商品行最后一行（做减法）分摊的支付金额
                    BigDecimal payAmountDTODivided = dtoPayAmount.subtract(addedPayAmountDTOTotal);
                    orderItemPay.setPayAmount(payAmountDTODivided);
                } else {
                    // 订单商品行的分摊的支付金额
                    BigDecimal payAmountDTODivided = dtoPayAmount.multiply(oi.getPayAmount()).divide(itemTotalAmount, 2, RoundingMode.HALF_UP);
                    orderItemPay.setPayAmount(payAmountDTODivided);
                    addedPayAmountDTOTotal = addedPayAmountDTOTotal.add(payAmountDTODivided);
                }
                orderItemPay.setIdOrderItem(oi.getId());
                orderItemPay.setOrderItemNo(oi.getOrderItemNo());
                orderItemPay.setPayType(orderMain.getPayOnArrivalPayType());
                orderItemPay.setBillType(orderSub.getBillType());
                orderItemPay.setPayAmountTotal(dtoPayAmount);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    orderItemPay.setPayTime(df.parse(tmsPayDTO.getOccurtime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                orderItemPay.setDateUpdated(new Date());
                orderItemPay.setDateCreated(new Date());
                orderItemPay.setIsDeleted(0L);
                orderItemPayService.save(orderItemPay);
            }
        }

        return true;
    }

}

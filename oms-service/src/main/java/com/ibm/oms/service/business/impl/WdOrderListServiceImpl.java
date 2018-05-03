/**
 * 
 */
package com.ibm.oms.service.business.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderMain_;
import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.intf.constant.OrderMainConst;
import com.ibm.oms.intf.intf.WdOrderDTO;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.WdOrderListService;

/**
 * @author xiaonanxiang
 * 
 */
@Service("wdOrderListService")
public class WdOrderListServiceImpl implements WdOrderListService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private OrderMainService orderMainService;
    @Resource
    private OrderNoService orderNoService;

    /**
     * 根据外部渠道订单号查询
     * 
     * @param aliasOrderNo 外部渠道订单号
     * @return
     */
    @Override
    public WdOrderDTO findOrderList(String aliasOrderNo) {
        WdOrderDTO wdOrderDTO = new WdOrderDTO();

        if (null == aliasOrderNo) {
            logger.error("oms:外部渠道订单号为null");
            
            wdOrderDTO.setSuccessFlag(FAIL);
            wdOrderDTO.setMessage("oms:外部渠道订单号为null");
            return wdOrderDTO;
        }
        wdOrderDTO.setBuId(0L);// 部门编号，关联th_bu.id；(目前没有此数据)
        wdOrderDTO.setBuShoppeId(0L);// 专柜id
        wdOrderDTO.setBuName("待确认");// 门店名称(如果不是门店自提的则无此数据)
        // 待确认，暂时先设为0
        wdOrderDTO.setOrderType(0L);// 1,实体店2，网上天虹
        wdOrderDTO.setLogisticsCharge(null);// 承运人
        wdOrderDTO.setLogisticsInfo(null);// 物流信息
        wdOrderDTO.setLogisticsNumber(null);// 货运单号
        wdOrderDTO.setOutboundTime(null);// 订单出库时间
        wdOrderDTO.setPickUpTime(null);// 自提时间
        wdOrderDTO.setUpdateTime(null);// 最后更新时间
        // 商品没有此数据，待确认(中台没有字段存放),暂时先设0
        wdOrderDTO.setGoodsStyle(0L);// 商品类型：0百货商品，1超市商品
        wdOrderDTO.setOrderKey(null);// 订单的加密key。要求唯一
        // 待确认...
        wdOrderDTO.setScoreStatus(0L);// 子订单积分是否已经成功记录 成功后状态改为1
        wdOrderDTO.setInvoiceRemark(null);// 开发票备注
        // 待确认...
        wdOrderDTO.setIsPeisong(0L);// 0 配送上门 1到店提货

        // 1.主订单信息
        OrderMain orderMain = orderMainService.getByField(OrderMain_.aliasOrderNo, aliasOrderNo);
        if (null == orderMain) {
            logger.error("oms找不到对应的主订单信息:orderMain为null");
            wdOrderDTO.setSuccessFlag(FAIL);
            wdOrderDTO.setMessage("oms找不到对应的主订单信息:orderMain为null");
            return wdOrderDTO;
        }
        wdOrderDTO.setId(Long.valueOf(aliasOrderNo));// 外部渠道订单号
        wdOrderDTO.setOrderId(Long.valueOf(aliasOrderNo));
        String orderNo = orderMain.getOrderNo();
        String orderSubNo = orderNoService.getDefaultOrderSubNoByOrderNo(orderNo);
        wdOrderDTO.setOrderListNo(orderSubNo);// 子订单号
        wdOrderDTO.setTotalAmount(orderMain.getTotalPayAmount()); // 订单总金额
        wdOrderDTO.setLogisticsAmount(orderMain.getTransportFee());// 运费
        wdOrderDTO.setDoOrderId(orderMain.getId());// order_net或者r3中对应的id；中台的主订单ID
        wdOrderDTO.setTotalAmount(orderMain.getTotalPayAmount());// 订单总金额
        wdOrderDTO.setLogisticsAmount(orderMain.getTransportFee());// 运费
        wdOrderDTO.setOrderIntegral(new BigDecimal(0));// 用户可获取多少积分，（只有r3的才有）；中台消费所得积分推送
        String statusPay = orderMain.getStatusPay();
        // 是否支付标示
        boolean isHadPaidFlag = false;
        // 在线待支付 或 货到付款未支付
        if (OrderStatus.Order_PayStatus_Paying.getCode().equals(statusPay)
                || OrderStatus.Order_PayStatus_Cash_Paying.getCode().equals(statusPay)) {
            wdOrderDTO.setPayStatus(0L);// 支付状态。0未支付
        }
        // 在线支付完成 或 货到付款已支付
        else if (OrderStatus.Order_PayStatus_Success.getCode().equals(statusPay)
                || OrderStatus.Order_PayStatus_Cash_Success.getCode().equals(statusPay)) {
            wdOrderDTO.setPayStatus(1L);// 支付状态。 1已经支付
            isHadPaidFlag = true;
        }
        wdOrderDTO.setIsNotice(orderMain.getNeedConfirm()); // 是否通知对方0未通知，1已经通知
        wdOrderDTO.setNoticeTime(null);// 通知时间；具体的通知时间
        wdOrderDTO.setCompleteTime(orderMain.getFinishTime());// 订单完成时间
        wdOrderDTO.setRemark(orderMain.getClientRemark());// 备注
        wdOrderDTO.setCreateTime(orderMain.getOrderTime());// 创建时间
        wdOrderDTO.setIsInvoice(orderMain.getNeedInvoice());// 是否已开发票，0表示没开，1表示已开发票

        // 逆向主订单
        OrderMain rOrderMain = this.orderMainService.getByField(OrderMain_.orderRelatedOrigin, orderNo);
        if (null != rOrderMain) {
            // 如果退货单已完成
            if (OrderMainConst.OrderMain_OrderCategory_Return.getCode().equals(rOrderMain.getOrderCategory())
                    && OrderStatus.RET_ORDER_RETURN_FINISHED.getCode().equals(rOrderMain.getStatusTotal())) {
                wdOrderDTO.setCancelStatus(1L);// 退货状态1已经退货
                wdOrderDTO.setCancelTime(rOrderMain.getFinishTime());// 退货完成时间
            }
        }

        // 2.子订单信息
        OrderSub orderSub = orderMain.getOrderSubs().get(0);
        if (null == orderSub) {
            logger.error("oms找不到对应的子订单信息:orderSub为null");
            wdOrderDTO.setSuccessFlag(FAIL);
            wdOrderDTO.setMessage("oms找不到对应的子订单信息:orderSub为null");
            return wdOrderDTO;
        }
        wdOrderDTO.setReturnOrderId(String.valueOf(orderSub.getId()));// 返回的id；中台的子订单ID
        String statusTotal = orderMain.getStatusTotal();
        String logisticsStatus = orderSub.getLogisticsStatus();
        // 物流状态10已经支付等待发货，50已经出库或者已经备货等待签收，100用户已经签收
        if (isHadPaidFlag && OrderStatus.ORDER_VALIDATED.getCode().equals(statusTotal)) {
            wdOrderDTO.setLogisticsStatus(10L);
        } else if (OrderStatus.Order_LogisticsStatus_Collect.getCode().equals(logisticsStatus)
                || OrderStatus.Order_LogisticsStatus_SendOrder.getCode().equals(logisticsStatus)) {
            wdOrderDTO.setLogisticsStatus(50L);
        } else if (OrderStatus.Order_LogisticsStatus_SignFinish.getCode().equals(logisticsStatus)) {
            wdOrderDTO.setLogisticsStatus(100L);
        }
        wdOrderDTO.setLogisticsMode(orderSub.getDistributeType());// 配送方式：网上天虹/到店自提；
        wdOrderDTO.setExpresName(orderSub.getDistributeType());// 快递名称
        wdOrderDTO.setExpresNo(orderSub.getLogisticsOutNo());// 快递单号

        // 3.订单明细
        List<OrderItem> orderItemList = orderSub.getOrderItems();
        if (null == orderItemList || orderItemList.size()<=0) {
            logger.error("oms找不到对应的订单明细:orderItemList为空");
            wdOrderDTO.setSuccessFlag(FAIL);
            wdOrderDTO.setMessage("oms找不到对应的订单明细:orderItemList为空");
            return wdOrderDTO;
        }
        BigDecimal itemTotalPayAmount = new BigDecimal(0);
        for (OrderItem orderItem : orderItemList) {
            itemTotalPayAmount = itemTotalPayAmount.add(orderItem.getPayAmount());
        }
        wdOrderDTO.setGoodsAmount(itemTotalPayAmount);// 商品总金额

        // 4.支付明细
        List<OrderPay> orderPayList = orderMain.getOrderPays();
        if (null == orderPayList || orderPayList.size()<=0) {
            logger.error("oms找不到对应的支付明细:orderPayList为空");
            wdOrderDTO.setSuccessFlag(FAIL);
            wdOrderDTO.setMessage("oms找不到对应的支付明细:orderPayList为空");
            return wdOrderDTO;
        }
        Date payTime = null;
        for (OrderPay orderPay : orderPayList) {
            if (null == orderPay.getPayTime()) {
                continue;
            }
            payTime = orderPay.getPayTime(); 
        }
        wdOrderDTO.setPayTime(payTime);//支付成功时间
        wdOrderDTO.setSuccessFlag(SUCCESS);
        wdOrderDTO.setMessage("成功");
        return wdOrderDTO;
    }
}

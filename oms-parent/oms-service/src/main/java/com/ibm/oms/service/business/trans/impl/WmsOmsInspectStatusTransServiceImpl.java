package com.ibm.oms.service.business.trans.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.dao.constant.OrderStatusAction;
import com.ibm.oms.dao.constant.PayType;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderMain_;
import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.intf.constant.OrderMainConst;
import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.intf.intf.WmsOmsReceiveInspectionResultDTO;
import com.ibm.oms.service.IntfReceivedService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderPayService;
import com.ibm.oms.service.OrderStatusLogService;
import com.ibm.oms.service.OrderStatusSyncLogService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.oms.service.business.OrderStatusService;
import com.ibm.oms.service.business.ReturnChangeOrderService;
import com.ibm.oms.service.business.trans.SaleAfterOrderTransService;
import com.ibm.oms.service.business.trans.WmsOmsInspectStatusTransService;
import com.ibm.oms.service.util.CommonConstService;
import com.ibm.oms.service.util.CommonUtilService;

@Service("orderInspectStatusTransService")
public class WmsOmsInspectStatusTransServiceImpl implements WmsOmsInspectStatusTransService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    OrderStatusLogService orderStatusLogService;
    @Autowired
    IntfReceivedService intfReceivedService;
    @Autowired
    OrderStatusService orderStatusService;
    @Autowired
    OrderSubService orderSubService;
    @Autowired
    OrderMainService orderMainService;
    @Autowired
    SaleAfterOrderTransService saleAfterOrderTransService; 
    @Autowired
    CommonUtilService commonUtilService;
    @Autowired
    OrderStatusSyncLogService orderStatusSyncLogService;
    @Autowired
    OrderPayService orderPayService;
    @Autowired
    ReturnChangeOrderService returnChangeOrderService;
    @Override
    public CommonOutputDTO updateOrderInspectStatus(WmsOmsReceiveInspectionResultDTO wmsOmsReceiveInspectionResultDTO) {

        // 校验数据
        CommonOutputDTO output = new CommonOutputDTO();
        String inspection_orstorage = wmsOmsReceiveInspectionResultDTO.getInspectionOrStorage();
        // 获取单据信息
        String orderSubNo = wmsOmsReceiveInspectionResultDTO.getOrderSubNo();
        
        // add by xiaohl 20140724 for 针对老平台的意向单加上"01"处理，因为已传输WMS的意向单子订单号不包含"01"
        if(StringUtils.isNotEmpty(orderSubNo) && orderSubNo.length()<10){
            orderSubNo = orderSubNo+"01";
        }
        OrderSub orderSub = orderSubService.getByOrderSubNo(orderSubNo);

        if (orderSub != null) {
            OrderMain orderMain = orderMainService.getByField(OrderMain_.orderNo, orderSub.getOrderNo());
            String order_category = orderMain.getOrderCategory();//售后类型
            if(inspection_orstorage.equalsIgnoreCase(WMS_SaleAfterOrder_Inspection)){
                // 质检成功(单据状态由质检中->退货入库中)
                if (WMS_SaleAfterOrder_Success.equals(wmsOmsReceiveInspectionResultDTO.getInspectionResult())) {
                    orderStatusService.saveProcess(orderSubNo,OrderStatusAction.S024050, null, null, null);
                // 质检失败(单据状态由质检中->质检失败)
                } else if (WMS_SaleAfterOrder_Fail.equals(wmsOmsReceiveInspectionResultDTO.getInspectionResult())) {
                    orderStatusService.saveProcess(orderSubNo,OrderStatusAction.S024060, null, null, null);
                }
            }else if(inspection_orstorage.equalsIgnoreCase(WMS_SaleAfterOrder_Storage)){ //入库
                //这里需判断是否需退款，因为退款涉及到流程节点走向
                boolean refundFlag = false;
                if(null!=orderMain.getIfNeedRefund()){
                	boolean isNeedRefund = commonUtilService.checkIfNeedRefund(orderMain.getIfNeedRefund());
                    //if(orderMain.getIfNeedRefund()==CommonConst.OrderMain_IfNeedRefund_Yes.getCodeLong()){
                	if(isNeedRefund){
                        refundFlag = true;
                    }else{
                        refundFlag = false;
                    }
                }
                //获取当前的总状态，因为入库场景有三种：1、质检中-->入库；2、质检失败-->入库;3、退货入库中-->入库
                boolean transR3Flag = false;//是否需传输R3【已完成】
                String statusTotal = orderMain.getStatusTotal();
                if(statusTotal.equals(OrderStatus.RET_ORDER_INSPECTION.getCode())){ //质检中
                    orderStatusService.saveProcess(orderSubNo, OrderStatusAction.S024050, null, null, null);//默认先质检通过，然后再入库
                    if(refundFlag){
                        orderStatusService.saveProcess(orderSubNo, OrderStatusAction.S025070, null, null, null);
                    }else{
                        orderStatusService.saveProcess(orderSubNo, OrderStatusAction.S025080, null, null, null);
                        transR3Flag = true;
                    }
                }else if(statusTotal.equals(OrderStatus.RET_ORDER_ON_THE_WAY.getCode())){ //退货入库中
                    if(refundFlag){
                        orderStatusService.saveProcess(orderSubNo, OrderStatusAction.S025070, null, null, null);
                    }else{
                        orderStatusService.saveProcess(orderSubNo, OrderStatusAction.S025080, null, null, null);
                        transR3Flag = true;
                    }
                }else if(statusTotal.equals(OrderStatus.RET_ORDER_INSPECT_FAIL.getCode())){ //质检失败
                    if(refundFlag){
                        orderStatusService.saveProcess(orderSubNo, OrderStatusAction.S026070, null, null, null);
                    }else{
                        orderStatusService.saveProcess(orderSubNo, OrderStatusAction.S026080, null, null, null);
                        transR3Flag = true;
                    }
                }else if(statusTotal.equals(OrderStatus.RET_ORDER_STORE_ON_THE_WAY.getCode())){ //门店退货入库中（门店代退）
                    orderStatusService.saveProcess(orderSubNo, OrderStatusAction.S027580, null, null, null);
                    transR3Flag = true;
                }
                //进入待同步R3表，同步场景I-OMS-R3-01
                if(transR3Flag){
                    saleAfterOrderTransService.saveSaleAfterOrderToR3(orderSubNo);    
                    //进行运费补款操作
                    this.saleAfterOrderTransService.saveTransportFeeOrder(orderMain, orderSubNo);
                    //拒收单需考虑原订单是否MY卡支付，需退MY卡
                    returnMyCardRejectOrder(orderMain);
                }
                //国贸云店的退货单写入待同步表（预退），同步场景I-OMS-R3-03          
                if (CommonConst.OrderMain_OrderCategory_Return.getCode().equals(orderMain.getOrderCategory())
                        && OrderMainConst.OrderMain_MerchantNo_Yundian_GuoMao.getCode().equalsIgnoreCase(orderMain.getMerchantNo())) {
                    orderStatusSyncLogService.saveAndcreate(orderMain, null,
                            CommonConst.OrderStatusSyncLog_SyncScene_PrePay.getCode());
                }
            }
        } else {
            // 根据WMS传递的子订单号无法查找到订单
            output.setCode(CommonConstService.FAILED);
            output.setMsg(wmsOmsReceiveInspectionResultDTO.getOrderSubNo() + "：无法查找到该订单。");
            return output;
        }

        // 校验成功，保存接口调用日志
        output.setCode(CommonConstService.OK);
        return output;
    }
    
    /**
     * 拒收单完成后，原订单如有MY卡支付需返回MY 
     * @param orderMain
     */
    private void returnMyCardRejectOrder(OrderMain orderMain){
        //只有无需退款的拒收单，才判断原订单是否MY卡支付
        if (null == orderMain || !OrderMainConst.OrderMain_OrderCategory_Reject.getCode().equals(orderMain.getOrderCategory())) {
            return;
        }
        //无需退款
        boolean isNeedRefund = commonUtilService.checkIfNeedRefund(orderMain.getIfNeedRefund());
        if(!isNeedRefund){
            //获取原销售订单
            String srcOrderNo = orderMain.getOrderRelatedOrigin();
            OrderMain srcOrderMain = this.orderMainService.getByField(OrderMain_.orderNo, srcOrderNo);
            if(null==srcOrderMain || srcOrderMain.getStatusPay().equals(OrderStatus.Order_PayStatus_Success.getCode())){
                return; //原销售订单如果是在线支付完成，会生成退款单，无需单独退款
            }
            List<OrderPay> pays = orderPayService.getByOrderMainNo(srcOrderNo);
            BigDecimal amount = new BigDecimal(0);
            for(OrderPay pay:pays){
                if(pay.getPayCode().equals(PayType.MYCARD.getId())){
                    amount = pay.getPayAmount();   //MY卡支付金额
                }
            }
            if(amount.intValue()>0){
                //调用会员MY卡退还接口
                this.returnChangeOrderService.returnMyCard(srcOrderNo, "sys", srcOrderMain, true);
            }
        }
        
    }
    
}
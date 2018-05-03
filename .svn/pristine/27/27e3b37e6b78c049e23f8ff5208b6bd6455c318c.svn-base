package com.ibm.oms.service.business.trans.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.IntfReceiveConst;
import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.dao.constant.OrderStatusAction;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderPayMode;
import com.ibm.oms.domain.persist.OrderStatusLog;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.domain.persist.TransportArea;
import com.ibm.oms.intf.constant.OrderMainConst;
import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.intf.intf.TmsOrderDTO;
import com.ibm.oms.intf.intf.TmsStatusDTO;
import com.ibm.oms.service.IntfReceivedService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderStatusLogService;
import com.ibm.oms.service.OrderStatusSyncLogService;
import com.ibm.oms.service.TransportAreaCacheService;
import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.OrderStatusService;
import com.ibm.oms.service.business.trans.OrderCreateTrans;
import com.ibm.oms.service.business.trans.TmsOmsLogisticsStatusTransService;
import com.ibm.oms.service.util.CommonCacheUtil;
import com.ibm.oms.service.util.CommonConstService;
import com.ibm.oms.service.util.CommonUtilService;
import com.ibm.oms.service.util.SelfTakeMerchantUtil;
import com.ibm.oms.service.util.XMLConverter;
import com.ibm.sc.model.shipping.SelfTakePoint;
import com.ibm.sc.util.DateUtil;

/**
 * @author liucy
 * 
 */
@Service("tmsOmsLogisticsStatusTransService")
public class TmsOmsLogisticsStatusTransServiceImpl implements TmsOmsLogisticsStatusTransService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    OrderStatusLogService orderStatusLogService;
    @Autowired
    IntfReceivedService intfReceivedService;
    @Autowired
    OrderStatusService orderStatusService;
    @Autowired
    OrderMainService orderMainService;
    @Autowired
    XMLConverter xmlConverterOrder;
    @Autowired
    TransportAreaCacheService transportAreaCacheService;
    @Autowired
    OrderNoService orderNoService;
    @Autowired 
    OrderStatusSyncLogService orderStatusSyncLogService;
    @Autowired
    OrderCreateService orderCreateService;
    @Autowired
    CommonUtilService commonUtilService;
    @Autowired
    CommonCacheUtil commonCacheUtil;
    @Autowired
    private SelfTakeMerchantUtil selfTakeMerchantUtil;
    @Autowired
    OrderCreateTrans orderCreateTrans;
    
    @Override 
    public void saveThirdTmsLog(TmsStatusDTO tmsStatusDTO){
        OrderStatusLog orderStatusLog = new OrderStatusLog();
        String orderSubNo = tmsStatusDTO.getTxLogisticID();
        String orderNo = orderNoService.getOrderNoBySubNo(orderSubNo);
        OrderMain om = orderMainService.findByOrderNo(orderNo);
        if(om == null){
            logger.info("订单查找失败,单号:{}", orderNo);
            return;
        }
        orderStatusLog.setIdOrder(om.getId());
        OrderSub os = om.getOrderSubs().get(0);
        if(os != null){
            orderStatusLog.setIdOrderSub(os.getId());
            orderStatusLog.setOrderSubNo(os.getOrderSubNo());
        }
        String operateTime = tmsStatusDTO.getAcceptTime();
        orderStatusLog.setOrderNo(om.getOrderNo());
        orderStatusLog.setCreatedBy("ThirdTmsLog");
        orderStatusLog.setRemark(tmsStatusDTO.getRemark());
        orderStatusLog.setCurrentStatus(transTmsLogisticsStatus(tmsStatusDTO.getInfoType()));//当前物流状态
        orderStatusLog.setDateCreated(new Date());
        orderStatusLog.setDateUpdated(new Date());
        try {
            orderStatusLog.setOperateTime(StringUtils.isBlank(operateTime)?new Date(): commonUtilService.format24Hours().parse(operateTime));
        } catch (ParseException e) {
            logger.error("{}", e);
        }
        orderStatusLogService.save(orderStatusLog);
    }
    /**
     * 功能描述: 创建订单的TMS状态
     */
    @Override
    public CommonOutputDTO updateOrderLogisticsStatus(TmsStatusDTO tmsStatusDTO) {

        // 校验数据
        CommonOutputDTO output = new CommonOutputDTO();
        // TMS状态码:GET_SUCCESS（揽收成功, 忽略）、SEND_ORDER（派单）、KEEP_HOUSE（留仓,忽略）、SIGN_SUCCESS（签收成功）、SIGN_FAIL（签收失败）
        String logistics_status = tmsStatusDTO.getInfoType();
        String orderSubNo = tmsStatusDTO.getTxLogisticID();
        OrderMain om = orderMainService.getByOrderSubNo(orderSubNo);
        if (om == null) {
            output.setCode(CommonConstService.FAILED);
            output.setMsg(orderSubNo + ":无法找到相应的订单");
            return output;
        }
        boolean isPayOnArrival = (om.getIfPayOnArrival() != null && om.getIfPayOnArrival().longValue() == 1);
        OrderStatusAction orderStatusAction = getOrderStatusAction(om, logistics_status, isPayOnArrival);
        if(orderStatusAction == null){
          //货到付款已收已付以支付信息为已收已付的标准;或者订单已经达到已收已付。忽略
            output.setCode(CommonConstService.OK);
            output.setMsg(orderSubNo);
            return output;
        }
        // tms到达的是完成签收/拒收
        boolean successFlag = orderStatusService.saveProcess(orderSubNo, orderStatusAction, null, null, null);

        if (successFlag) {
            // 状态到0180 同步支付明细 ，订单明细
            if (IntfReceiveConst.SIGN_SUCCESS.getCode().equals(logistics_status)) {
                orderCreateService.promoResourceAdd(om.getBatchNum(), om.getOrderNo());
                om.setFinishTime(new Date());
                orderMainService.update(om);
                //update by 20140808 for R3待同步表 
                orderCreateTrans.saveOrderToR3(om, null);
                /*//("I-OMS-R3-01","订单明细"),
                orderStatusSyncLogService.saveAndcreate(om, null,
                        CommonConst.OrderStatusSyncLog_SyncScene_OrderItem.getCode());
                //"I-OMS-R3-02","支付明细"
                orderStatusSyncLogService.saveAndcreate(om, null, 
                        CommonConst.OrderStatusSyncLog_SyncScene_Pay.getCode());*/

            }
            output.setCode(CommonConstService.OK);
            logger.debug(orderSubNo + ":接口调用成功。");
        } else {
            output.setCode(CommonConstService.FAILED);
            logger.info(orderSubNo + ":上传状态与前一状态不匹配。");
        }

        return output;
    }

    /**
     * 根据TMS的状态标识转换为订单中台的code
     * @param logistics_status
     * @return
     */
    private String transTmsLogisticsStatus(String logistics_status){
        String logistics_statusCode = "";
        if (IntfReceiveConst.GET_SUCCESS.getCode().equals(logistics_status)){//揽收
            logistics_statusCode =  OrderStatus.Order_LogisticsStatus_Send.getCode();
        }else if (IntfReceiveConst.SEND_ORDER.getCode().equals(logistics_status)){//派单
            logistics_statusCode =  OrderStatus.Order_LogisticsStatus_SendOrder.getCode();
        }else if (IntfReceiveConst.SIGN_SUCCESS.getCode().equals(logistics_status)) { //签收
            logistics_statusCode =  OrderStatus.Order_LogisticsStatus_SignFinish.getCode();
        }else if (IntfReceiveConst.SIGN_FAIL.getCode().equals(logistics_status)){ //拒收
            logistics_statusCode =  OrderStatus.Order_LogisticsStatus_Reject.getCode();
        }
        return logistics_statusCode;
    }
    
    /**
     * 根据TMS传递的状态码，获取orderActionNo
     * 
     * @param logistics_status
     * @return orderStatusAction
     */
    private OrderStatusAction getOrderStatusAction(OrderMain om, String logistics_status, Boolean isPayOnArrival) {
        String statusTotal = om.getStatusTotal();
        if(OrderStatus.ORDER_ACCEPTED_PAID.getCode().equals(statusTotal)){
            return null;
        }
        boolean isChgOut = OrderMainConst.OrderMain_OrderCategory_ChangeOut.getCode().equals(om.getOrderCategory());
        if (IntfReceiveConst.GET_SUCCESS.getCode().equals(logistics_status)){// && !isPayOnArrival) {
            return OrderStatusAction.S060506;
        }if (IntfReceiveConst.SEND_ORDER.getCode().equals(logistics_status)){// && !isPayOnArrival) {
            return OrderStatusAction.S060608;
        }if (IntfReceiveConst.SIGN_SUCCESS.getCode().equals(logistics_status) && (!isPayOnArrival || isChgOut)) {
            //换货或在线支付签收
            return OrderStatusAction.S017080;
        }
        if (IntfReceiveConst.SIGN_SUCCESS.getCode().equals(logistics_status) && isPayOnArrival) {
            // 货到付款已支付
            if (OrderStatus.Order_PayStatus_Success.getCode().equals(om.getStatusPay())) {
                return OrderStatusAction.S017180;
            }
        } else if (IntfReceiveConst.SIGN_FAIL.getCode().equals(logistics_status) && !isPayOnArrival) {
            //在线支付拒收
            return OrderStatusAction.S017081;
        } else if (IntfReceiveConst.SIGN_FAIL.getCode().equals(logistics_status) && isPayOnArrival) {
            //货到付款拒收
            return OrderStatusAction.S017181;
        }
        return null;
        //货到付款未支付，货到付款存在提交时就已经支付的情况
    }
    
    @Override
    public String queryToTmsStr(String orderSubNo, String orderType) {

        if (StringUtils.isEmpty(orderSubNo)) {
            return null;
        }
        OrderMain om = orderMainService.getByOrderSubNo(orderSubNo);
        if (null == om || om.getOrderSubs().isEmpty()) {
            return null;
        }
        OrderSub os = om.getOrderSubs().get(0);

        TmsOrderDTO tDTO = new TmsOrderDTO();
        //在线支付写0,否则写null  邹7.24
        boolean isPayOnLine = om.getIfPayOnArrival() == null || om.getIfPayOnArrival() != 1l;
        tDTO.setPayMode(isPayOnLine ? "0" : null);
        try {
            // 订单创建时间
            String createDateTimeStr = null;
            if(om.getOrderTime() != null){
                createDateTimeStr = DateUtil.getStringFormatByDate(om.getOrderTime(),
                        DateUtil.FORMAT_GENERALDATETIME);
            }else{
                createDateTimeStr = DateUtil.getStringFormatByDate(DateUtil.getNowDate(),
                        DateUtil.FORMAT_GENERALDATETIME);
            }
            //系统当前时间
            String curDateTimeStr = DateUtil.getStringFormatByDate(DateUtil.getNowDate(),
                    DateUtil.FORMAT_GENERALDATETIME);
            
            tDTO.setCreateTime(createDateTimeStr);
            // 订单编号(内部单号)(子订单)
            //tDTO.setOrderid(commonUtilService.Long2Str(os.getIdOrder()));
            tDTO.setOrderid(os.getOrderSubNo());//传输子订单号
            // 物流商家ID
            tDTO.setLogisticCompanyId(os.getDeliveryMerchantNo());
            // 物流订单号(外部编号)
            tDTO.setTxLogisticID(os.getOrderSubNo());
            // 正向销售订单无原销售子订单号
            if (CommonConstService.TMS_TYPE_OS.equals(orderType)) {
                tDTO.setSrcOrderNo("");
            } else {
                tDTO.setSrcOrderNo(os.getOrderSubRelatedOrigin());
            }
            // 订单类型
            tDTO.setType(orderType);
            // 订单标识
            //tDTO.setFlag(CommonConstService.TMS_FLAG_NORMAL);
            try{
                tDTO.setFlag(Integer.parseInt(os.getShipCat()));    
            }catch(Exception e){}
            // 收件人姓名
            tDTO.setName(os.getUserName());
            // 收件人详细地址
            tDTO.setAddress(os.getAddressDetail());
            // 收件人详细地址编号(最末尾的层级栏目编号)
            tDTO.setAddresscode(os.getAddressCode());
            // 获取省、市、区
            Map<String, TransportArea> areasMap = transportAreaCacheService.getAllByAreaId(os.getAddressCode());
            if (areasMap != null && !areasMap.isEmpty()) {
                TransportArea province = areasMap.get(TransportAreaCacheService.Treelevel_Province);
                tDTO.setProv(province == null ? "" : province.getAreaName());
                TransportArea city = areasMap.get(TransportAreaCacheService.Treelevel_City);
                tDTO.setCity(city == null ? "" : city.getAreaName());
                TransportArea county = areasMap.get(TransportAreaCacheService.Treelevel_Area);
                tDTO.setArea(county == null ? "" : county.getAreaName());
            }
            // 收件人移动电话
            tDTO.setMobile(os.getMobPhoneNum());
            // 收件人固定电话
            tDTO.setPhone(os.getPhoneNum());
            // 收件人邮编 
            //tDTO.setPostCode(os.getPostCode());
            // update by 20141022 for TMS的换货出库单、入库单进行关联
            if(OrderMainConst.OrderMain_OrderCategory_Change.getCode().equals(om.getOrderCategory())){
                tDTO.setPostCode(om.getChgOurOrderNo());
            }else if(OrderMainConst.OrderMain_OrderCategory_ChangeOut.getCode().equals(om.getOrderCategory())){
                tDTO.setPostCode(om.getOrderRelatedOrigin());
            }
            // 是否已开发票
            if (null != om.getNeedInvoice()) {
                tDTO.setNeedInvoice(om.getNeedInvoice().intValue());
            }
            // 商品金额
            tDTO.setGoodsValue(commonUtilService.bigDecimalToDouble(om.getTotalProductPrice()));
            // 代收货款金额【 update by xiaohl 20140730 for 从order_pay_mode中获取】
            //tDTO.setItemsValue(commonUtilService.bigDecimalToDouble(om.getTotalPayAmount()));
            BigDecimal realPayAmount = new BigDecimal(0);
            for(OrderPayMode payMode:om.getOrderPayModes()){
                realPayAmount = payMode.getPayAmount();
                break;
            }
            tDTO.setItemsValue(commonUtilService.bigDecimalToDouble(realPayAmount));
            
            // 总件数
            tDTO.setTotalPCS(om.getOrderItems().size());
            // 商品总重量
            tDTO.setTotalWeight(commonUtilService.bigDecimalToDouble(om.getWeight()));
            // 保值金额（暂时没有使用，默认为0.0）
            tDTO.setInsuranceValue(0.0d);
            // 箱签号
            tDTO.setToid(os.getBolNo());
            // 配送方式
            tDTO.setDeliverymode(NumberUtils.toInt(os.getDistributeType()));
            if(null!=os.getOutStoreTime()){
                tDTO.setOuthousetime(DateUtil.getStringFormatByDate(os.getOutStoreTime(),DateUtil.FORMAT_GENERALDATETIME));// 出库时间    
            }else{
                tDTO.setOuthousetime(curDateTimeStr);// 出库时间
            }
            if(null!=om.getConfirmTime()){
                tDTO.setReviewtime(DateUtil.getStringFormatByDate(om.getConfirmTime(),DateUtil.FORMAT_GENERALDATETIME));// 下达仓库时间(先默认审核时间)    
            }else{
                tDTO.setReviewtime(curDateTimeStr);// 下达仓库时间(先默认审核时间)
            }
            
            tDTO.setRemark(commonCacheUtil.getDeliveryDateByCode(om.getDeliveryDateFlag())); //配送时间
            // 物流订单类型
            tDTO.setWmsOrderType(CommonConstService.TMS_FLAG_NORMAL);
            tDTO.setSelfFetchPointId(os.getSelfFetchAddress());
            Long merchant = commonUtilService.StrToLong(os.getSelfFetchAddress());
            SelfTakePoint stp = selfTakeMerchantUtil.getMerchantBySelfTakePointId(merchant);
            tDTO.setSelfFetchMerchantId((stp == null || stp.getPointDeliverPartnerId() == null) ? "" : stp.getPointDeliverPartnerId().toString());
            // 发送至activeMQ
            String output = xmlConverterOrder.convertFromObjectToXMLString(tDTO);
            return output;
        } catch (Exception e) {
            logger.info("{}", e);
        }
        return null;
    }
}

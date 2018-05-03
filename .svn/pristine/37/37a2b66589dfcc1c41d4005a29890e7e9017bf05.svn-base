package com.ibm.oms.integration.order.handler;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.constant.PayMode;
import com.ibm.oms.dao.constant.PayType;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.oms.domain.persist.OrderPayMode;
import com.ibm.oms.domain.persist.OrderPayMode_;
import com.ibm.oms.domain.persist.OrderRetChgItem;
import com.ibm.oms.domain.persist.OrderStatusSyncLog;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.domain.persist.OrderSub_;
import com.ibm.oms.integration.order.constant.R3Constant;
import com.ibm.oms.intf.constant.OrderMainConst;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderPayModeService;
import com.ibm.oms.service.OrderPayService;
import com.ibm.oms.service.OrderRetChgItemService;
import com.ibm.oms.service.OrderStatusSyncLogService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.util.CommonUtilService;
import com.ibm.oms.service.util.DataUtil;
import com.ibm.oms.service.util.PaymentMethodUtil;
import com.ibm.oms.service.util.SyncLogValidator;
import com.ibm.sc.model.payment.PaymentMethod;
import com.ibm.sc.util.DateUtils;

/**
 * 订单支付/取消同步数据到R3
 * syncScene = ("I-OMS-R3-02","支付明细"),
 * @author 
 *<int:service-activator input-channel="settleOrRefundChannel" output-channel="endChannel"
 */
//@Component
public class SettleOrRefundOrderServiceHandler {
    private static Logger log = LoggerFactory.getLogger(SettleOrRefundOrderServiceHandler.class);
    
    @Resource
    private OrderPayService orderPayService;

    @Resource
    private OrderStatusSyncLogService orderStatusSyncLogService;
    
    @Resource
    private OrderPayModeService orderPayModeService;
    
    private String cashier;
    
    @Resource(name = "prePayModeMap")
    private Map<String, String> prePayModeMap;
    
    /**不能算作预收的在线支付payCode**/
    @Resource(name = "prePayExludeCodeMap")
    private Map<String, String> prePayExludeCodeMap;
    
    @Resource
    private OrderMainService orderMainService;
    @Resource
    CommonUtilService commonUtilService;
    
    @Resource
    private OrderSubService orderSubService;
    
    @Resource
    private OrderRetChgItemService orderRetChgItemService;
    
    @Autowired
    private OrderNoService orderNoService;
    
    @Resource
    private OrderItemService orderItemService;
    
    @Autowired
    protected PaymentMethodUtil paymentMethodUtil;
    @Autowired
    SyncLogValidator syncLogValidator;
    @Autowired
    PaidOrCancelOrderServiceHandler paidOrCancelOrderServiceHandler;
    private String env;
    
    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }
    
//  public  Map<String,Object>  query(){
//      
//      if("prd".equals(env)){
//          //log.info("=================SettleOrRefundOrderServiceHandler start=====================");
//          
//          Map<String,Object> params = new HashMap<String, Object>();
////            ("I-OMS-R3-02","支付明细"),
//          params.put("syncScene", CommonConst.OrderStatusSyncLog_SyncScene_Pay.getCode());
//          params.put("syncFlag", "N");
////            params.put("dateCreated", "2014-07-26");
//          Map<String,Object> returnMap = new HashMap<String, Object>();
//           
//          List<OrderStatusSyncLog> logList = orderStatusSyncLogService.getLogListByMap(params);
//          
//          List<Map<String,Object>> dataList =  handlerData(logList);
//          returnMap.put("data", dataList);
//          returnMap.put("updata", logList);
//          
//          //log.info("=================SettleOrRefundOrderServiceHandler end=====================");
//          
//          return  returnMap;
//      }else{
//          Map<String,Object> returnMap = new HashMap<String, Object>();
//          List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
//          
//          List<OrderStatusSyncLog> logList = new ArrayList<OrderStatusSyncLog>();
//          returnMap.put("data", resultList);
//          returnMap.put("updata", logList);
//          
//          return  returnMap;
//      }
//  }
    
    
    
//     public  Map<String,Object>  query(){
//              Map<String,String> params = new HashMap<String, String>();
////              ("I-OMS-R3-02","支付明细"),
//              params.put("syncScene", CommonConst.OrderStatusSyncLog_SyncScene_Pay.getCode());
//              params.put("strStartDate", "2014-07-27");
//              params.put("strEndDate", "2014-07-31");
//              Map<String,Object> returnMap = new HashMap<String, Object>();
//              List<OrderStatusSyncLog> logList = orderStatusSyncLogService.getLogListByDate(params);
//              
//              List<Map<String,Object>> dataList =  handlerData(logList);
//              returnMap.put("data", dataList);
//              returnMap.put("updata", logList);
//              return  returnMap;
//      }
    
    
    
    /**
     * 查询数据同步R3
     * 对于同一个订单号，
     * 1.是预付款的数据 整合到一条里面同步(金额叠加 ，支付方式统一预付编码)
     * 2.非预付款的orderpay,各项数据同步
     * @param logList 最后更新业务数据
     * @return
     */
    public List<Map<String,Object>>  handlerData(List<OrderStatusSyncLog> logList ){
        List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
        OrderPay orderPay = null;
        
        // 预付款总金额
        BigDecimal prePayAmount = null;
        OrderPayMode payMode = null;
        
        for (OrderStatusSyncLog row : logList) {
            //检验不通过， 执行下一条
            if(CommonConst.OrderStatusSyncLog_SyncFlag_FailedPreProcess.getCode().equals(row.getSyncFlag())){
                continue;
            }
            String orderNo = row.getOrderNo();
            OrderMain orderMain = orderMainService.getByField("orderNo", orderNo);
            OrderSub orderSub = orderSubService.get(OrderSub_.orderNo, orderMain.getOrderNo()) ;
            boolean isPositive = CommonConst.OrderMain_OrderCategory_Sale.getCode().equals(orderMain.getOrderCategory());
            prePayAmount = new BigDecimal(0);
            BigDecimal pay206Amount = new BigDecimal(0);
            orderNo = row.getOrderNo();

            List<OrderPay> orderPayList = orderPayService.findByField("orderNo", orderNo);
            orderPayList = combineOP(orderPayList);
            payMode = orderPayModeService.getByField(OrderPayMode_.orderNo, orderNo);

            //门店自提代码 206
            boolean isSale = CommonConst.OrderMain_BillType_Positive.getCodeLong() == orderMain.getBillType().longValue();
            boolean isPayOnArrival = CommonConst.OrderMain_IfPayOnArrival_Yes.getCode().equals(commonUtilService.Long2Str(orderMain.getIfPayOnArrival()));
            boolean isDistributeType2 = CommonConst.OrderSub_Distribute_Type2.getCode().equals(orderSub.getDistributeType());
            boolean not1400 = !"1400".equals(orderSub.getSelfFetchAddress());
            boolean is206 = not1400 && isSale && isPayOnArrival && isDistributeType2;
            // 门店自提代码 206（线上下单线下货到付款特殊处理逻辑，分拣中心自提的除外）

            int len = orderPayList.size();
            boolean hasPrePay = false;
            boolean has206 = false;
            Date payTimeUseForCoupon = null;
            for (int i=0;i < len;i++) {
                boolean isPrePay = false;
                orderPay = orderPayList.get(i);
                payTimeUseForCoupon = orderPay.getPayTime();
                String payCode = orderPay.getPayCode();
                //301，303my卡，购物券 不是属于206的情况
                boolean not206PayCode = "301".equals(payCode) || "303".equals(payCode);
                boolean pay206 = is206 && !not206PayCode;
                //在线支付排除优惠券301，换货抵扣50600
                boolean isExcludedPayCode = prePayExludeCodeMap.containsValue(payCode);
                if(payMode!=null&& !PayType.COUPON.getId().equals(payCode)&&!PayType.CHANGE_PAID.getId().equals(payCode)){
                    isPrePay = prePayModeMap.containsValue(payMode.getPayModeCode()) ;
                    isPrePay = isPrePay && !isExcludedPayCode;
                }
                //正向订单处理
                boolean isPreRefundCloudStore = OrderMainConst.OrderMain_MerchantNo_Yundian_GuoMao.getCode().equalsIgnoreCase(orderMain.getMerchantNo()) && CommonConst.OrderMain_OrderCategory_Return.getCode().equals(orderMain.getOrderCategory());
                if(isPositive){
                    if(prePayModeMap.containsValue(payCode)||isPrePay){
                        //付款方式统一预付 882
                        payCode = R3Constant.PAY_CODE_PREPAY;
                        prePayAmount = prePayAmount.add(orderPay.getPayAmount());
                        hasPrePay = true;
                    }else if(pay206){
                        pay206Amount = pay206Amount.add(orderPay.getPayAmount());
                        has206 = true;
                    }else{
                        resultList.add(this.handlerOneData(orderMain, payCode, null, null, orderPay));
                    }
                    //最后添加预付数据
                    if (hasPrePay && i == (len - 1)) {
                        resultList.add(this.handlerOneData(orderMain, R3Constant.PAY_CODE_PREPAY, prePayAmount, null,orderPay));
                    }
                    //最后添加206数据
                    if (has206 && i == (len - 1)) {
                        resultList.add(this.handlerOneData(orderMain, R3Constant.PAY_CODE_206, null, pay206Amount, orderPay));
                    }
                }else if(isPreRefundCloudStore){
                    //退货单中的国贸云店，同销售算预收的paycode
                    if(!isExcludedPayCode){
                        //付款方式统一预付 882
                        payCode = R3Constant.PAY_CODE_PREPAY;
                        prePayAmount = prePayAmount.add(orderPay.getPayAmount());
                        hasPrePay = true;
                    }
                    //最后添加预付数据
                    if (hasPrePay && i == (len - 1)) {
                        resultList.add(this.handlerOneData(orderMain, R3Constant.PAY_CODE_PREPAY, prePayAmount, null,orderPay));
                    }
                }else{
                    //其他逆向单处理
                    resultList.add(this.handlerOneData(orderMain, payCode, null, null, orderPay));
                }
            }
            
            //支付项遍历完成之后，如果是退货，需要加入用券支付项
            BigDecimal couponAmount = calculateCoupon(orderNo);
            if(couponAmount.compareTo(BigDecimal.valueOf(0))>0){
                orderPay = new OrderPay();
                orderPay.setPayAmount(couponAmount);
                orderPay.setPayCode("301");
                orderPay.setPayTime(payTimeUseForCoupon);
                resultList.add(this.handlerOneData(orderMain, "301", null, null, orderPay));
            }

            row.setSyncFlag("P");
        }
        
        orderStatusSyncLogService.update(logList);
        return resultList;
    }

    /**
     * @param orderMain
     * @param payCode
     * @param prePayAmount
     * @param orderPay
     * @return
     */
    private Map<String, Object> handlerOneData(OrderMain orderMain,String payCode,BigDecimal prePayAmount,BigDecimal pay206Amount, OrderPay orderPay) {
        Map<String, Object> obj = new HashMap<String, Object>();

        boolean isPrePay = (prePayAmount != null);
        boolean is206 = (pay206Amount != null);
        obj = writeUfb001(obj, orderMain);
        BigDecimal minus =new BigDecimal(-1);
        String r3PayCode = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
        boolean isPositive = CommonConst.OrderMain_OrderCategory_Sale.getCode().equals(orderMain.getOrderCategory());
        isPositive = (isPositive || CommonConst.OrderMain_OrderCategory_ChangeOut.getCode().equals(orderMain.getOrderCategory()));
        if(isPrePay){
            obj.put("tc_ufb002", payCode);
            if(isPositive){
                obj.put("tc_ufb003", prePayAmount);
            }else{
                obj.put("tc_ufb003", prePayAmount.multiply(minus));
            }
        }else if(is206){
            obj.put("tc_ufb002", payCode);
            if(isPositive){
                obj.put("tc_ufb003", pay206Amount);
            }else{
                obj.put("tc_ufb003", pay206Amount.multiply(minus));
            }
        }else{
            //或到付款现金支付
            if(PayMode.CASH_PAY_ON_ARRIVE.getCode().equals(orderPay.getPayCode())){
                r3PayCode = R3Constant.PAY_CODE_CASH;
            }else if("880".equals(orderPay.getPayCode())){
                //天虹卡
                r3PayCode = R3Constant.PAY_CODE_TIANHONGCARD;
            }else if(PayMode.CARD_PAY_ON_ARRIVE.getCode().equals(orderPay.getPayCode())){
                r3PayCode = R3Constant.PAY_CODE_BANKCARD;
            }else if(PayMode.RBCARD_PAY_ON_ARRIVE.getCode().equals(orderPay.getPayCode())){
                r3PayCode =  R3Constant.PAY_CODE_TIANHONGCARD;
            }else if(PayType.CHANGE_PAID.getId().equals(orderPay.getPayCode())){
                r3PayCode = R3Constant.PAY_CODE_CASH;
            }else{
                PaymentMethod pm = paymentMethodUtil.getPaymentAllMethodMap().get(payCode);
                if(pm!=null && pm.getName() !=null){
                    if(pm.getName().indexOf("支付宝")>-1){
                        r3PayCode = "200";
                    }else if(pm.getName().indexOf("财付通")>-1){
                        r3PayCode = "50100";
                    }else{
                        r3PayCode = orderPay.getPayCode();
                    }
                }else{
                    r3PayCode =  orderPay.getPayCode();
                }
            }


            obj.put("tc_ufb002", r3PayCode);
            if(isPositive){
                obj.put("tc_ufb003",DataUtil.convertBigDecimal( orderPay.getPayAmount()));
            }else{
                obj.put("tc_ufb003",DataUtil.convertBigDecimal( orderPay.getPayAmount()).multiply(minus));
            }
        }
        
        Date payTime = null;
        if(orderPay.getPayTime()==null){
            obj.put("tc_ufb004", new Date(orderMain.getOrderTime().getTime() + 24 * 3600* 1000));
        }else{
            try {
                 String sPayTime =format.format(orderPay.getPayTime());  
                 payTime =format.parse(sPayTime);
            } catch (ParseException e) {
                log.error("format.parse{}", e);
            }
            obj.put("tc_ufb004", payTime);
        }

        obj.put("trans_crea", orderPay.getDateCreated());

        obj.put("tc_ufb008", orderPay.getPayNo());
        return obj;
    }

    /**
     * 传输时间特殊处理
     *
     * @param date
     * @return
     */
    public Date getTransDate(Date date){
        //temp
        /*try {
            if(date.before(DateUtils.convertStringToGeneralDate("2014-07-26"))){
                //07-26前的算到08-01
                return trimHHmmss(DateUtils.convertStringToGeneralDate("2014-08-01"));
            }else if(date.after(DateUtils.convertStringToGeneralDate("2014-07-26")) && date.before(DateUtils.convertStringToGeneralDate("2014-08-01"))){
                //07-26至07-31的单传到08-21
                return trimHHmmss(DateUtils.convertStringToGeneralDate("2014-08-21"));
            }else if(date.after(DateUtils.convertStringToGeneralDate("2014-08-21")) && date.before(DateUtils.convertStringToGeneralDate("2014-08-22"))){
                //0821的单传到02-22
                return trimHHmmss(DateUtils.convertStringToGeneralDate("2014-08-22"));
            }else{
                return trimHHmmss(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        return trimHHmmss(date);
    }

    public Date trimHHmmss(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String d = sdf.format(date);
        try {
            return sdf.parse(d);
        } catch (ParseException e) {
            return null;
        }
    }
    
    public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }
    
    
    private Map<String, Object> writeUfb001(Map<String, Object> map, OrderMain orderMain){
        String orderSubNo1 = paidOrCancelOrderServiceHandler.getOrderSubNo(orderMain);
//        if(R3Constant.IMMIGRATION.equals(orderMain.getImmigrationVersion())&&CommonConst.OrderMain_BillType_Positive.getCodeLong()==orderMain.getBillType()){
//            orderSubNo1 =  orderMain.getOrderNo();
//        }else{
//            orderSubNo1 = orderNoService.getDefaultOrderSubNoByOrderNo( orderMain.getOrderNo());
//        }
//        orderSubNo1 = orderMain.getOrderNo() + "*9";
        map.put("tc_ufb001", orderSubNo1 );
        // 卡号/票号/账户
        Date finishTime =paidOrCancelOrderServiceHandler.getOrderFinishTime(orderMain);
        map.put("trans_date", getTransDate(finishTime));
        map.put("trans_time", null);
        map.put("trans_flag", "N");
        map.put("tc_ufb005",new Date());
        map.put("tc_ufb006",DateUtils.format(new Date(), "HH:mm:ss"));
        //已上传ERP
        map.put("tc_ufb007", "N");
        //电商用户
        map.put("tc_ufb009", cashier);
        map.put("tc_ufb010", null);
        map.put("tc_ufb011", null);
        map.put("tc_ufb012", null);
        return map;
    }
    
    private boolean isPrePay(OrderPay op, OrderPayMode payMode, OrderMain om){
        boolean ret = false;
        String payCode = op.getPayCode();
        if(payMode!=null&& !PayType.COUPON.getId().equals(payCode)&&!PayType.CHANGE_PAID.getId().equals(payCode)){
            ret = prePayModeMap.containsValue(payMode.getPayModeCode()) ;
        }
        return ret;
    }
    
    /**重写支付行，合并重复paycode的pay_amount**/
    private List<OrderPay> combineOP(List<OrderPay> opList){
        List<OrderPay> ret = new ArrayList<OrderPay>();
        for(OrderPay op:opList){
            OrderPay opay = payCodeListed(ret, op.getPayCode()); 
            if(opay == null){
                //新list不包含该payCode
                ret.add(op);
            }else{
                opay.setPayAmount(opay.getPayAmount().add(op.getPayAmount()));
            }
        }
        return ret;
    }
    
    private OrderPay payCodeListed(List<OrderPay> opList, String payCode) {
        for (OrderPay op : opList) {
            if (payCode.equals(op.getPayCode())) {
                return op;
            }
        }
        return null;
    }

    // 用券在退货中不返还，因此无法在orderpay中找到返券记录，但R3系统在10.28日提出同步时必须加上用券金额，
    // 此时用券金额因为不能确认整单退，只能从退货订单行上取。

    private BigDecimal calculateCoupon(String orderNo) {
        BigDecimal coupon = new BigDecimal(0);
        List<OrderRetChgItem> itemList = orderRetChgItemService.findByField("orderNo", orderNo);
        for (OrderRetChgItem orderRetChgItem : itemList) {
            coupon = coupon.add(DataUtil.convertBigDecimal(orderRetChgItem.getCouponAmount()).multiply(BigDecimal.valueOf(orderRetChgItem.getSaleNum())));
        }
        return coupon;
    }
}

package com.ibm.sc.oms.service.retchg.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.beans.stock.OmsSOInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.dao.constant.CancelOrderConst;
import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.constant.IntfSentConst;
import com.ibm.oms.dao.constant.PayType;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderMain_;
import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.oms.domain.persist.OrderPayMode;
import com.ibm.oms.domain.persist.OrderRetChgItem;
import com.ibm.oms.domain.persist.OrderSearch;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.domain.persist.OrdiErrOptLog;
import com.ibm.oms.domain.persist.OrdiErrOptLog_;
import com.ibm.oms.intf.constant.OrderMainConst;
import com.ibm.oms.intf.intf.CombineProductDTOList;
import com.ibm.oms.intf.intf.CommodityStockInfoOutPutDTO;
import com.ibm.oms.intf.intf.InventoryLockOutputDTO;
import com.ibm.oms.intf.intf.OmsMemberVipCardOutputDTO;
import com.ibm.oms.intf.intf.ResultDTO;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderPayModeService;
import com.ibm.oms.service.OrderPayService;
import com.ibm.oms.service.OrderSearchService;
import com.ibm.oms.service.OrderStatusSyncLogService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.oms.service.OrdiErrOptLogService;
import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.ReturnChangeOrderService;
import com.ibm.oms.service.business.SaleAfterOrderService;
import com.ibm.oms.service.business.trans.ImsOmsTransService;
import com.ibm.oms.service.business.trans.SaleAfterOrderTransService;
import com.ibm.oms.service.business.trans.TmsOmsLogisticsStatusTransService;
import com.ibm.oms.service.mq.TmsOrderInfoSender;
import com.ibm.oms.service.util.CommonConstService;
import com.ibm.oms.service.util.CommonUtilService;
import com.ibm.sc.oms.service.test.BaseTest;
import com.ibm.sc.util.DateUtil;

@SuppressWarnings("javadoc")
public class OrderRetChangeServiceImplTest extends BaseTest {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    SaleAfterOrderTransService saleAfterOrderTransService;
    @Autowired
    SaleAfterOrderService saleAfterOrderService;
    @Autowired
    ReturnChangeOrderService returnChangeOrderService;
    @Autowired
    OrderMainService orderMainService; 
    @Autowired
    OrderSubService orderSubService; 
    @Autowired
    OrderCreateService orderCreateService;
    @Autowired
    OrderPayService orderPayService; 
    @Autowired
    OrderPayModeService orderPayModeService;
    @Autowired
    TmsOrderInfoSender tmsOrderInfoSender;
    @Autowired
    TmsOmsLogisticsStatusTransService tmsOmsLogisticsStatusTransService;
    @Autowired
    private ImsOmsTransService imsOmsTransService;
    @Autowired
    CommonUtilService commonUtilService;
    @Autowired
    OrderStatusSyncLogService orderStatusSyncLogService;
    @Autowired
    OrderNoService orderNoService;
    @Autowired
    OrdiErrOptLogService ordiErrOptLogService;
    @Autowired
    OrderSearchService orderSearchService;
//    
//    public static void main(String[]args){
//        String str = "a,b,c,";
//        System.out.println(str.substring(0, str.length()-1));
//    }
    
    @Test
    public final void insertR3Que(){ //退货单完成后【R3】
        
        String orderNos = "1131045075,1129042995";
        for(String orderNo:orderNos.split(",")){
            OrderMain orderMainR = orderMainService.getByField(OrderMain_.orderNo, orderNo);
            //("I-OMS-R3-01","订单明细")
            orderStatusSyncLogService.saveAndcreate(orderMainR, null,
                    CommonConst.OrderStatusSyncLog_SyncScene_OrderItem.getCode());
        }
        
        
    /*orderStatusSyncLogService.saveAndcreate(om.getId(), os.getId(), om.getOrderNo(), os.getOrderSubNo(),
                CommonConst.OrderStatusSyncLog_SyncScene_Pay.getCode(),
                CommonConst.OrderStatusSyncLog_SyncFlag_NeedProcess.getCode(),
                CommonConst.OrderStatusSyncLog_TargetSys_R3.getCode(), "sys");*/
    }
    
//    @Test
    public void processInventoryUnLock(){ //会员资源扣减失败，库存解锁【库存解锁】
//        List<OrderMain> list = this.orderMainService.findByField(OrderMain_.statusTotal, "0112");
        List<OrderMain> orderMainList = new ArrayList<OrderMain>();
        OrderSearch search = new OrderSearch();
        String dateFormate = "yyyy-MM-dd HH:mm:ss";
        /*String orderTimeFromStr = "2014-07-26 00:00:00";
        String orderTimeToStr = "2014-08-23 23:00:00";*/
        String orderTimeFromStr = "2014-08-30 01:00:00";
        String orderTimeToStr = "2014-08-31 01:00:00";
        search.setStatusTotal("0112");
        search.setOrderTimeFrom(DateUtil.getDateFormatByString(orderTimeFromStr, dateFormate));
        search.setOrderTimeTo(DateUtil.getDateFormatByString(orderTimeToStr, dateFormate));
        List<OrderSearch> list = orderSearchService.findByOrderSearch(0, search);
        for(OrderSearch orderSear:list){
            OrderMain main = new OrderMain();
            main.setOrderNo(orderSear.getOrderNo());
            orderMainList.add(main);
        }
        if(orderMainList.size()>0){
            System.out.println("=============="+orderMainList.size());
            // 解锁接口
            this.orderCreateService.inventoryUnLock(orderMainList);
        }
    }
    
//    @Test
    public void processInventoryDeduct(){ //处理库存扣减失败的订单【库存扣减】
        OrderSearch search = new OrderSearch();
        String dateFormate = "yyyy-MM-dd HH:mm:ss";
        String orderTimeFromStr = "2014-07-28 00:00:00";
        String orderTimeToStr = "2014-08-29 23:00:00";
        search.setStatusTotal("0151");
        search.setOrderTimeFrom(DateUtil.getDateFormatByString(orderTimeFromStr, dateFormate));
        search.setOrderTimeTo(DateUtil.getDateFormatByString(orderTimeToStr, dateFormate));
        List<OrderSearch> list = orderSearchService.findByOrderSearch(0, search);
        for(OrderSearch orderSear:list){
            String orderNo = orderSear.getOrderNo();
            // 解锁接口
            boolean flag = this.orderCreateService.inventoryDeduct(orderNo);
            System.out.println("=================:"+orderNo+","+flag);
            break;
        }
    }
    
    
    //@Test
    public final void combimeQuery(){ //组合商品查询接口
        // 开始调用接口
        CombineProductDTOList output = null;
        boolean exceptionThrown = false;
        ObjectMapper om = new ObjectMapper();
        long timeStart = System.currentTimeMillis();
        String productUrl_sit = "http://192.168.163.237:8080/pdm-rs/productbundle/bundleDetail";
        try {
            List<String> input = new ArrayList();
            input.add("GP00247059");
            output = commonUtilService.jsonPost(productUrl_sit, input, CombineProductDTOList.class, null);
        } catch (Exception e) {
            logger.info("time elapsed {}, {}", (System.currentTimeMillis() - timeStart) / 1000, e);
            exceptionThrown = true;
        }
        logger.info("time elapsed {}", (System.currentTimeMillis() - timeStart));
        String callStr = null;
        try {
            callStr = om.writeValueAsString(output);
            System.out.println("===============================:"+callStr);
        } catch (JsonProcessingException e) {
            logger.info(callStr);
        }
    }
    
//    @Test
    public final void processErrorLog_SaleAfterOrderToWMS(){ //处理传输WMS失败的售后意向单
        List<OrdiErrOptLog> list = ordiErrOptLogService.findByField(OrdiErrOptLog_.errorCode,"SaleAfterOrder_ToWMS");
        for(OrdiErrOptLog log:list){
            boolean flag = this.returnChangeOrderService.sendOmsToWmsSaleAfterOrder(log.getOrderSubNo());
            System.out.println(flag);
        }
    }
    
    
//    @Test
    public final void createOrderPay(){ //直接创建支付明细【order_pay】
        OrderPay pay = new OrderPay();
        pay.setIdOrder(2045075l);
        pay.setOrderNo("1131045075");
        pay.setPayAmount(new BigDecimal(87.7));
        pay.setPayCode(PayType.CASH.getId());
        pay.setPayName(PayType.CASH.getPayName());
        pay.setBillType(CommonConst.OrderMain_BillType_Positive.getCodeLong());
        pay.setPayTime(new Date());
        orderPayService.save(pay);
    }
    
//    @Test
    public final void createOrderPayMode(){ //直接创建支付明细【order_pay_mode】
        OrderPayMode pay = new OrderPayMode();
        pay.setIdOrder(1409518l);
        pay.setOrderNo("1201045978");
        pay.setPayAmount(new BigDecimal(101.4));
        pay.setPayModeCode(PayType.SHOPPING_CARD.getId());
        pay.setPayModeName(PayType.SHOPPING_CARD.getPayName());
        pay.setDateCreated(new Date());
        orderPayModeService.save(pay);
    }
    
//    @Test
    public final void createRefund(){ //创建退款单【订单取消】
        String orderNo = "1028009745";
        OrderMain main = this.orderMainService.findByOrderNo(orderNo);
        OrderSub sub = main.getOrderSubs().get(0);
        OrderSub refundSub = this.saleAfterOrderTransService.saveRefundOrder(main,sub);
        System.out.println("====================="+refundSub.getOrderSubNo());
            
    }
    
//    @Test
    public final void sendOrderToTMS(){ //订单传输TMS【TMS】
        /*String orderSubNo="140725135616101";
        String toSendStr = tmsOmsLogisticsStatusTransService.queryToTmsStr(orderSubNo, CommonConstService.TMS_TYPE_OS);
        if(toSendStr != null){
            tmsOrderInfoSender.sendWithTrack(toSendStr, orderSubNo, IntfSentConst.OMS_TMS_ORDER.getCode());
        }*/
        String orderSubNos="111903103901";
        for(String orderSubNo:orderSubNos.split(",")){
            String toSendStr = tmsOmsLogisticsStatusTransService.queryToTmsStr(orderSubNo, CommonConstService.TMS_TYPE_OS);
            if(toSendStr != null){
                tmsOrderInfoSender.sendWithTrack(toSendStr, orderSubNo, IntfSentConst.OMS_TMS_ORDER.getCode());
                System.out.println("==================================:");
                System.out.println(toSendStr);
            }
        }
    }
    
//    @Test
    public final void sendOrderToWMS(){ //订单传输WMS【WMS】
        //主订单号
        //String orderNos="1029011225,1029011217,1029011214,1029011221,1029011220,1029011224,1029011208,1029011205,1029011206,1029011237,1029011231,1029011227,1029011213,1029011219,1029011207,1029011240,1029011235,1029011229,1029011228,1029011203,1029011248,1029011247,1029011200,1029011250,1029011282,1029011299,1029011298,1029011341,1029011382,1030011501,1030011504,1030011523,1030011559,1029011495,1029011491,1030011565,1029011479,1029011475,1029011474,1029011472,1029011471,1030011575,1030011574,1030011568,1029011036,1029011293,1029011305,1029011300,1029011312,1029011308,1029011294,1028010544,1029011326,1029011335,1029011342,1029011353,1029011372,1029011367,1030011535,1030011532"; //订单号
        String orderNos="1407041335760,";
        //String sim03="http://192.168.1.172:8080/pdm-rs/stockDeductByOrder/stockDeduct";
        String sim03="http://192.168.157.111:8088/pdm-rs/stockDeductByOrder/stockDeduct";
        for(String strNo:orderNos.split(",")){
            OmsSOInfo info = imsOmsTransService.queryInventoryDeduct(strNo);
            InventoryLockOutputDTO output = commonUtilService.jsonPostWithTrack(sim03, IntfSentConst.OMS_SIM_DEDUCT.getCode(),
                    info, InventoryLockOutputDTO.class, 8000); 
            System.out.println(strNo+"：========"+output.getReturn_code()+","+output.getReturn_msg()+","+output.getReturn_status());
        }
    }
    
//    @Test 
    public final void  saveTransportFeeOrder(){
        String orderNoR = "1106003917";
        String orderSubNoR = orderNoR+"01";
        OrderMain orderMainR = this.orderMainService.getByField(OrderMain_.orderNo, orderNoR);
        ResultDTO dto = this.saleAfterOrderTransService.saveTransportFeeOrder(orderMainR, orderSubNoR);
        System.out.println("=======================："+dto.getResult());
        
    }
    
    
//    @Test
    public final void processOrder(){ //订单处理【处理中】
        String orderNo = "1407060003504";
        orderCreateService.callSingleProcess(orderNo);
    }
    
//    @Test
    public final void testSearchOrder(){
        String orderNo = "100327053411";
        OrderMain main = this.orderMainService.getByField(OrderMain_.orderNo, orderNo);
        System.out.println(main.toString());
    }

//    @Test
    public final void testGetStockInfo(){ // 获取商品库存信息【库存】
    	String skuCode = "SKU_030309001";
    	CommodityStockInfoOutPutDTO resultDTO = returnChangeOrderService.getStockInfo(skuCode);
    }
    
//    @Test
    public final void testInventoryDeduct(){ // 库存扣减【库存】
        String orderNo = "1406210002437";
        orderCreateService.inventoryDeduct(orderNo);
    }
    
//    @Test
    public final void TestCancelOrder() { //订单取消
        String orderSubNo = "110401691501";
        //ResultDTO resultDTO = this.saleAfterOrderTransService.saveCancelOrder(orderSubNo,CancelOrderConst.CancelOrder_Scene_VALIDATED);
        ResultDTO resultDTO = saleAfterOrderService.cancelOrder(orderSubNo ,CancelOrderConst.CancelOrder_Scene_VALIDATED);
        System.out.println(resultDTO.toString());
    }
    
//    @Test
    public final void testApproveRefundOrder(){ //审核退款单【退MY卡】
        String orderSubNoR = "112200419201";
        this.saleAfterOrderTransService.updateApproveRefundOrder(orderSubNoR, "");
    }
    
//    @Test
    public final void testSaveTransportFeeOrder(){
        String orderSubNoR = "20415901";
        OrderMain orderMainR = orderMainService.getByField(OrderMain_.orderNo, "204159");
        this.saleAfterOrderTransService.saveTransportFeeOrder(orderMainR, orderSubNoR);
    }
    
    
//    @Test
    public final void testApproveSaleAfterOrder(){ //审核意向单
        
        String orderSubNoR = "140707000351201";
        //ResultDTO resultDTO = this.saleAfterOrderTransService.updateApproveSaleAfterOrder(orderSubNoR);
        ResultDTO resultDTO = this.saleAfterOrderTransService.updateCancelSaleAfterOrder(orderSubNoR);
        System.out.println(resultDTO.toString());
    }
    
//    @Test
    public final void testSearchVIPCard() throws JsonProcessingException { //VIP卡查询【会员】
        String memberNo = "1700";
        OmsMemberVipCardOutputDTO resultDTO = returnChangeOrderService.searchMemberVipCard(memberNo);
        ObjectMapper om = new ObjectMapper();
        logger.info(om.writeValueAsString(resultDTO));
    }
    
//    @Test
    public final void testReturnMyCard() { //MY卡操作【会员】
        String orderNo = "769558";//1406050000285
        OrderMain orderMain = orderMainService.getByField(OrderMain_.orderNo, orderNo);
        ResultDTO resultDTO = returnChangeOrderService.returnMyCard(orderNo, "", orderMain,false);
        logger.info(resultDTO.toString());
        System.out.println("========================:"+resultDTO.toString());
        /*String orderNos = "769249,769559,769879,769364,769728,769805,769890,769558";
        for(String orderNo:orderNos.split(",")){
            OrderMain orderMain = orderMainService.getByField(OrderMain_.orderNo, orderNo);
            ResultDTO resultDTO = returnChangeOrderService.returnMyCard(orderNo, "", orderMain,false);
            logger.info(resultDTO.toString());
            System.out.println("========================:"+orderNo+","+resultDTO.getResult());
        }*/
    }
    
//    @Test
    public final void testHandelIntegral() { //积分操作【会员】
        /*String orderNo = "1406050000285";
        ResultDTO resultDTO = returnChangeOrderService.handelIntegral("", orderNo, false,false);
        logger.info(resultDTO.toString());*/
        //String orderNos = "1407251356794,1407251356791,1407251356790,1407251356788,1407251356786,1407251356784,1407251356771,1407251356769,1407251356767,1407251356766";
        
        /*String orderNos = "1407231354551";
        for(String orderNo:orderNos.split(",")){
            ResultDTO resultDTO = returnChangeOrderService.handelIntegral("sys", orderNo, true,true);
            logger.info(resultDTO.toString());
            System.out.println(orderNo+"==============="+resultDTO.getResult()+"_"+resultDTO.getResultMessage());
        }*/
        
        /*String sql="select * from order_main om where om.status_total='0180' and om.DATE_UPDATED >=to_date('2014-07-30 17:06:54', 'yyyy-mm-dd HH24:mi:ss') and om.DATE_UPDATED <=to_date('2014-07-31 00:00:00', 'yyyy-mm-dd HH24:mi:ss')";
        List<Object> omList = orderMainService.findOrderMainBySql(sql);
        try{
            int count=0;
            for(Object objMap:omList){
                Map map = (Map)objMap;
                Set set = map.entrySet();     
                Iterator i = set.iterator(); 
                while(i.hasNext()){  
                    Map.Entry entry1 = (Map.Entry)i.next(); 
                    System.out.println(entry1.getKey()+"=="+entry1.getValue()); 
                } 
                String memberNo = (String)map.get("MEMBER_NO");
                String orderNo = (String)map.get("ORDER_NO");
                BigDecimal totalPoints = (BigDecimal)map.get("TOTAL_GIVE_POINTS");
                System.out.println(memberNo+"====="+orderNo+"==========="+totalPoints);
                if(null!=totalPoints && totalPoints.compareTo(new BigDecimal(0))>0){
                    count ++;
                    ResultDTO resultDTO = returnChangeOrderService.handelIntegral("sys", orderNo, true,false);
                    logger.info(resultDTO.toString());
                    System.out.println(count+":"+orderNo+"==============="+resultDTO.getResult()+"_"+resultDTO.getResultMessage());
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }*/
        
    }
    
//    @Test
    public final void testSendOmsToTmsSaleAfterOrder(){ //售后意向单发送至TMS【MQ】
        String orderSubNo = "110501814001";
        returnChangeOrderService.sendOmsToTmsSaleAfterOrder(orderSubNo);
    }
    
    //传输失败单据：退14060500  0028501（39770）、换140603000012401（39609）、换140606000032001(39805)
//    @Test
    public final void testSendOmsToWmsSaleAfterOrder(){ //售后意向单发送至WMS【WMS】
        String orderSubNo = "111802969501"; //
        returnChangeOrderService.sendOmsToWmsSaleAfterOrder(orderSubNo);
    }
    
//    @Test
    public final void testOmsToWmsSaleAfterOrderCancel(){ //意向单取消后传输WMS【WMS】
        String orderSubNoR = "140717000372901"; //140611000046601(已完成)  140616000048501(已审核)  140616000049201
        returnChangeOrderService.sendOmsToWmsSaleAfterOrderCancel(orderSubNoR);
    }
    
//    @Test
    public final void testOmsToTmsSaleAfterOrderCancel(){ //意向单取消后传输TMS【MQ】
        String orderSubNoR = "140616000048501"; //140611000046601(已完成)  140616000048501(已审核)  140616000049201
        returnChangeOrderService.sendOmsToTmsSaleAfterOrderCancel(orderSubNoR);
    }
    

//    @Test
    public final void testAddSaleAfterOrder() {
        String orderCategory = OrderMainConst.OrderMain_OrderCategory_Change.getCode();
        String applySource = CommonConst.OrderRetChange_Applysource_Oms.getCode();
        long billType = CommonConst.OrderMain_BillType_Negative.getCodeLong();
        OrderMain orderMain = createOrderMain(billType);
        OrderSub orderSubs = createOrderSub(orderMain);
        List<OrderPay> orderPay = createOrderPay(orderMain);
        // save
        saleAfterOrderTransService.saveOrderRetChange(orderCategory, applySource, orderMain, orderSubs, orderPay);
        
        for(OrderSub sub:orderMain.getOrderSubs()){
            
        }

    }

    private OrderMain createOrderMain(long billType) {
        OrderMain om = new OrderMain();
        String strIndex = String.valueOf(1);
        // 头表
        om.setAliasOrderNo("1");
        om.setOrderSource(OrderMainConst.OrderMain_Ordersource_B2c.getCode());
        om.setConfirmerName("henry00" + strIndex);
        om.setMemberNo(strIndex);
        om.setCustomerName("红亮");
        om.setCustomerPhone("18912345678");
        om.setMerchantNo(strIndex);
        om.setWeight(new BigDecimal(125));
        om.setTransportFee(new BigDecimal(12));
        om.setTotalProductPrice(new BigDecimal(31500));
        om.setDateCreated(new Timestamp(1));
        om.setBillType(billType);
        om.setOrderRelatedOrigin("201404110000181");
        om.setRefOrderId(181l);
        om.setRefOrderNo("201404110000181");
        return om;
    }

    private OrderSub createOrderSub(OrderMain orderMain) {
        OrderSub sub = new OrderSub();
        String strIndex = "1";
        sub.setIdOrder(orderMain.getId());
        sub.setOrderNo(orderMain.getOrderNo());
        sub.setLogisticsOutNo("0000000000" + strIndex);
        sub.setDeliveryMerchantNo(strIndex);
        sub.setOrderRetChgItems(createItem());
        return sub;
    }

    private List<OrderRetChgItem> createItem() {
        List<OrderRetChgItem> list = new ArrayList();
        OrderRetChgItem item = new OrderRetChgItem();
        item.setSkuNo("000000000");
        item.setSaleNum(1l);
        item.setReason("是否看见谁都会分开计算的话");
        item.setRefOrderItemId(2014041100001810101l);
        item.setRefOrderItemId(49l);
        list.add(item);
        return list;
    }

    private List<OrderPay> createOrderPay(OrderMain orderMain) {
        List<OrderPay> list = new ArrayList();
        OrderPay pay = new OrderPay();
        pay.setBillType(orderMain.getBillType());
        pay.setBankTypeCode(PayType.ICBC.getId());
        pay.setPayName(PayType.ICBC.getPayName());
        pay.setPayCode(PayType.ICBC.getId());
        pay.setPayAmount(new BigDecimal(19835));
        list.add(pay);
        return list;
    }

}

package com.ibm.oms.integration.order.handler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.map.MultiKeyMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.domain.persist.OrderCombineRelation;
import com.ibm.oms.domain.persist.OrderCombineRelation_;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderItemVirtual;
import com.ibm.oms.domain.persist.OrderItem_;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderMain_;
import com.ibm.oms.domain.persist.OrderRetChgItem;
import com.ibm.oms.domain.persist.OrderStatusSyncLog;
import com.ibm.oms.domain.persist.SkuR3;
import com.ibm.oms.integration.order.constant.R3Constant;
import com.ibm.oms.intf.constant.OrderMainConst;
import com.ibm.oms.service.OrderCombineRelationService;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.OrderItemVirtualService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderPayService;
import com.ibm.oms.service.OrderPromotionService;
import com.ibm.oms.service.OrderRetChgItemService;
import com.ibm.oms.service.OrderStatusSyncLogService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.oms.service.SkuR3Service;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.trans.OrderCreateTrans;
import com.ibm.oms.service.util.DataUtil;
import com.ibm.oms.service.util.SyncLogValidator;
import com.ibm.pdm.domain.enums.catalog.SourceType;
/**同步订单商品明细,对应R3柜组**/
//@Component
public class PaidOrCancelOrderServiceHandler{
    private static Logger log = LoggerFactory
            .getLogger(PaidOrCancelOrderServiceHandler.class);
    @Resource
    private OrderMainService orderMainService;
	
	@Resource
	private OrderItemService orderItemService;
	
	@Resource
	private OrderItemVirtualService orderItemVirtualService;

	@Resource
	private OrderStatusSyncLogService orderStatusSyncLogService;
	
	@Resource
	private OrderCombineRelationService orderCombineRelationService;
	
	@Resource
	private SkuR3Service skuR3Service;
	
	@Resource
	private OrderPromotionService orderPromotionService;
	
	@Resource
	private OrderRetChgItemService orderRetChgItemService;
	
	@Resource
	private OrderPayService orderPayService;
	
    @Autowired
    private OrderNoService orderNoService;
    @Autowired
    SyncLogValidator syncLogValidator;
    @Autowired
    SettleOrRefundOrderServiceHandler settleOrRefundOrderServiceHandler;

    //temp
    @Autowired
    OrderCreateTrans orderCreateTrans;
    @Autowired
    OrderSubService orderSubService;


    /**maven profile, dev,sit or prd**/
	private String env;
	
	/**
	 * @return dev or sit string
	 */
	public String getEnv() {
		return env;
	}

	/**
	 * @param env
	 */
	public void setEnv(String env) {
		this.env = env;
	}
	
	/**<int:inbound-channel-adapter ref="paidOrCancelOrderService" method="query" channel="paidOrCancelChannel">
	 * @return map**/
	public Map<String,Object> query(){
		log.info("=================PaidOrCancelOrderServiceActivator start=====================");
		if("prd".equals(env)){
		
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("syncScene", CommonConst.OrderStatusSyncLog_SyncScene_OrderItem.getCode());
			params.put("syncFlag", "N");
			Map<String,Object> returnMap = new HashMap<String, Object>();
	
			List<OrderStatusSyncLog> logList = orderStatusSyncLogService.getLogListByMap(params);
	        for (OrderStatusSyncLog row : logList) {
	            syncLogValidator.validateRowOfItem(row, CommonConst.OrderStatusSyncLog_SyncScene_OrderItem.getCode());
	        }
			
	        List<Map<String,Object>> dataList_paidOrCancel =  handlerData(logList);
	        List<Map<String,Object>> dataList_settleOrRefund = settleOrRefundOrderServiceHandler.handlerData(logList);
	        returnMap.put("data_paidOrCancel", dataList_paidOrCancel);
	        returnMap.put("data_settleOrRefund", dataList_settleOrRefund);
			returnMap.put("updata", logList);
			
			//log.info("=================PaidOrCancelOrderServiceActivator end=====================");
			
			return returnMap;
		}
		return null;
	}

    /*@PostConstruct
    public void prepairTempLog() { //temp
        //temp 生成order_status_sync_log记录
        //String orderNos = "1407092000170,1407092000142,1407122000425,1407142000463";
        String orderNos = "1118029199";
        for (String orderNo : orderNos.split(",")) {
            try {
                //记录已存在，忽略
                List<OrderStatusSyncLog> logList = orderStatusSyncLogService.getLogListByDate(CommonConst.OrderStatusSyncLog_SyncScene_OrderItem.getCode(),
                        null, null, orderNo, null, new Integer(1000));
                if(null != logList && logList.size()>0){
                    continue;
                }

                OrderMain orderMain = orderMainService.getByField(OrderMain_.orderNo, orderNo);
                String orderSubNo = getOrderSubNo(orderMain);
                OrderSub orderSub = orderSubService.getByField(OrderSub_.orderNo, orderSubNo.replace("*9", ""));
                orderCreateTrans.saveOrderToR3(orderMain, orderSub);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/

//    public  Map<String,Object>  query_history(){
//        //("I-OMS-R3-02","支付明细"),
//        Map<String,Object> returnMap = new HashMap<String, Object>();
//        List<OrderStatusSyncLog> logList = orderStatusSyncLogService.getLogListByDate(CommonConst.OrderStatusSyncLog_SyncScene_OrderItem.getCode(),
//                null, null, null, CommonConst.OrderStatusSyncLog_SyncFlag_NeedProcess.getCode(), new Integer(100));
//        //对每行作校验，不合格的行syncFlag写E
//        for (OrderStatusSyncLog row : logList) {
//            syncLogValidator.validateRowOfItem(row, CommonConst.OrderStatusSyncLog_SyncScene_OrderItem.getCode());
//        }
//        List<Map<String,Object>> dataList_paidOrCancel =  handlerData(logList);
//        List<Map<String,Object>> dataList_settleOrRefund = settleOrRefundOrderServiceHandler.handlerData(logList);
//        returnMap.put("data_paidOrCancel", dataList_paidOrCancel);
//        returnMap.put("data_settleOrRefund", dataList_settleOrRefund);
//        returnMap.put("updata", logList);
//        return  returnMap;
//    }
    
	/**处理逆向订单**/
	private boolean doNegative(OrderStatusSyncLog row, List<Map<String, Object>> resultList){
        String orderNo = row.getOrderNo();
        OrderMain orderMain = orderMainService.getByField(OrderMain_.orderNo, orderNo);
        // 逆向订单
        if (CommonConst.OrderMain_BillType_Negative.getCodeLong() == orderMain.getBillType()) {
            List<Map<String, Object>> negativeList = this.handlerNegativeOrderData(orderMain);
            if (negativeList != null && !negativeList.isEmpty()) {
                resultList.addAll(negativeList);
            }
            return false;
        }
	    return true;
	}
	
	
    /** 准备待同步数据, **/
    public List<Map<String, Object>> handlerData(List<OrderStatusSyncLog> logList) {
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        String orderNo = null;
        for (OrderStatusSyncLog row : logList) {
            //检验不通过， 执行下一条
            if(CommonConst.OrderStatusSyncLog_SyncFlag_FailedPreProcess.getCode().equals(row.getSyncFlag())){
                continue;
            }
            MultiKeyMap ufa123Map = new MultiKeyMap();
            orderNo = row.getOrderNo();
            OrderMain orderMain = orderMainService.getByField(OrderMain_.orderNo, orderNo);
            if(!doNegative(row, resultList)){
                continue;
            }
            // 折扣
            List<OrderItem> itemList = orderItemService.findByField(OrderItem_.orderNo, orderNo);
            List<OrderItemVirtual> virtualList = orderItemVirtualService.findByField("orderNo", orderNo);
            //订单来源
            String orderSource = orderStatusSyncLogService.getOrderSource(orderMain);
            
            for (OrderItem orderItem : itemList) {
              //订单类型 2014/10/15需求，积分商城订单所有价格用原价
                //2014/10/23需求，积分商城订单判别使用订单行itemClass区分
                boolean isIntegralOrder = CommonConst.OrderMain_OrderType_INTEGRAL.getCode().equals(orderItem.getOrderItemClass());
                /**店内码**/
                String skuNo = null;
                /**销售库区**/
                String stockNo = null;
                /**经营类型**/
                String isUnionBiz = null;
                String orderSubNo1 = getOrderSubNo(orderMain);
                SkuR3 sku = skuR3Service.getR3BySku(orderItem.getSkuNo());
                if(sku==null){
                    log.info("orderNo:{},skuNo{}", orderNo, orderItem.getSkuNo());
                    continue;
                }
                skuNo = sku.getItemnumber();
                stockNo = orderItem.getStockNo();
                isUnionBiz = sku.getSourceType();
                if (OrderMainConst.OrderItem_OrderItemClass_GIFT.getCode().equals(orderItem.getOrderItemClass())) {
                    if (orderItem.getUnitPrice() == null || orderItem.getUnitPrice().compareTo(new BigDecimal(0)) <= 1) {
                        stockNo = "001950500100";

                    } else {
                        stockNo = "001950500600";
                    }
                }

                //原价，取普通会员价
                BigDecimal normalPrice = null != orderItem.getNormalPrice() ? orderItem.getNormalPrice() : new BigDecimal(0);
                //原价比单价还低，为避免负折让，取单价
                if(normalPrice.compareTo(orderItem.getUnitPrice()) < 0){
                    normalPrice = orderItem.getUnitPrice();
                }
                //应收价
                //BigDecimal receivablePrice = new BigDecimal(0);
                //成交价
                //购买数量
                Long itemSaleNum = orderItem.getSaleNum() == null ? 0L : orderItem.getSaleNum();

                /**老平台组合,新平台团购枪购有重复sku的item,**/
                boolean containDuplicatedItem = false;
                //如果已经有重复的商品行，根据经营类型进行合并
                if (ufa123Map.containsKey(orderSubNo1, stockNo, skuNo)) {
                    Map<String, Object> obj = (HashMap<String, Object>) ufa123Map.get(orderSubNo1, stockNo, skuNo);

                    Long lastSaleNum = (Long) obj.get("tc_ufa005");
                    BigDecimal lastSaleNumB = new BigDecimal(lastSaleNum);

                    BigDecimal last007B = (BigDecimal) obj.get("tc_ufa007");
                    BigDecimal oldUfa013 = (BigDecimal) obj.get("tc_ufa013");

                    // 联营 自营区分
                    if (SourceType.Self.toString().equals(isUnionBiz)) {
                        //总价
                        BigDecimal lastGross007 = ((BigDecimal)obj.get("tc_ufa007")).multiply(lastSaleNumB);
                        BigDecimal thisGross007 = orderItem.getUnitPrice().multiply(new BigDecimal(itemSaleNum));
                        //原价
                        BigDecimal lastGross006 = ((BigDecimal)obj.get("tc_ufa006")).multiply(lastSaleNumB);
                        BigDecimal thisGross006 = normalPrice.multiply(new BigDecimal(itemSaleNum));
                        //数量累加
                        Long totalSaleNum = itemSaleNum + lastSaleNum;
                        obj.put("tc_ufa005", totalSaleNum);
                        BigDecimal avgGross007 = lastGross007.add(thisGross007).divide(new BigDecimal(totalSaleNum), 4, 2);
                        //原价平均
                        BigDecimal avgGross006 = (lastGross006.add(thisGross006)).divide(new BigDecimal(totalSaleNum), 4, 2);
                        //成交价平均
                        obj.put("tc_ufa007", avgGross007);
                        obj.put("tc_ufa006", avgGross006);
                        //折扣累加 , 5*(6-7)
                        BigDecimal newUfa0013 = normalPrice.subtract(orderItem.getUnitPrice());
                        if (itemSaleNum > 0) {
                            newUfa0013 = newUfa0013.multiply(new BigDecimal(itemSaleNum));
                        } else {
                            newUfa0013 = DataUtil.convertBigDecimal(null);
                        }
                        BigDecimal ufa0013 = oldUfa013.add(newUfa0013);
                        obj.put("tc_ufa013", ufa0013);
                        //出库成本取平均
                        BigDecimal cufa014 = (BigDecimal) obj.get("tc_ufa014");
                        BigDecimal allPay14 = cufa014.multiply(lastSaleNumB);
                        BigDecimal afterPay14 = allPay14.add(DataUtil.convertBigDecimal(orderItem.getInventoryPrice())
                                .multiply(new BigDecimal(itemSaleNum)));
                        BigDecimal ufa014 = afterPay14.divide(lastSaleNumB.add(new BigDecimal(itemSaleNum)),4,
                                BigDecimal.ROUND_HALF_UP);
                        obj.put("tc_ufa014", ufa014);

                    } else if (SourceType.Joint.toString().equals(isUnionBiz)) {
                        BigDecimal afterPay6 = null;
                        BigDecimal afterPay7 = null;
                        BigDecimal cufa006 = (BigDecimal) obj.get("tc_ufa006");
                        if (itemSaleNum > 0) {
                            if(!containDuplicatedItem){
                                afterPay6 = cufa006.multiply(lastSaleNumB).add(normalPrice.multiply(new BigDecimal(itemSaleNum)));
                                afterPay7 = last007B.multiply(lastSaleNumB).add(orderItem.getPayAmount());
                            } else{
                                afterPay6 = cufa006.add(normalPrice.multiply(new BigDecimal(itemSaleNum)));
                                afterPay7 = last007B.add(orderItem.getPayAmount());
                            }
                        } else {
                            afterPay6 = DataUtil.convertBigDecimal(null);
                            afterPay7 = DataUtil.convertBigDecimal(null);
                        }
                        obj.put("tc_ufa005", 1L);
                        obj.put("tc_ufa006", afterPay6);
                        obj.put("tc_ufa007", afterPay7);
                        obj.put("tc_ufa008", afterPay7);
                        BigDecimal ufa0013 = DataUtil.convertBigDecimal(afterPay6).subtract(afterPay7);
                        obj.put("tc_ufa013", ufa0013);
                        BigDecimal cufa014 = (BigDecimal) obj.get("tc_ufa014");
                        BigDecimal allPay14 = cufa014.multiply(lastSaleNumB);
                        BigDecimal afterPay14 = allPay14.add(DataUtil.convertBigDecimal(orderItem.getInventoryPrice())
                                .multiply(new BigDecimal(itemSaleNum)));
                        obj.put("tc_ufa014", afterPay14);
                    } else {
                        continue;
                    }
                    containDuplicatedItem = true;
                    if(isIntegralOrder){
                        //积分商城订单是用原价20141015需求
                        obj.put("tc_ufa007", obj.get("tc_ufa006"));
                        obj.put("tc_ufa008", obj.get("tc_ufa006"));
                    }
                    ufa123Map.put(orderSubNo1, stockNo, skuNo, obj);
                } else {
                    /** ! ufa123Map.containsKey(orderSubNo1, stockNo,skuNo) **/

                    //是否组合商品
                    boolean isCombinationProduct = false;
                    if(OrderMainConst.OrderItem_OrderItemClass_SUITE.getCode().equals(orderItem.getOrderItemClass())){
                        isCombinationProduct = true;
                    }

                    //组合商品要取出具体的明细商品
                    if(isCombinationProduct){
                        List<OrderCombineRelation> relationList = orderCombineRelationService.findByField(OrderCombineRelation_.orderItemNo, orderItem.getOrderItemNo());
                        for (OrderCombineRelation orderCombineRelation : relationList) {
                            //SKU
                            SkuR3 skuInCombination = skuR3Service.getR3BySku(orderCombineRelation.getSkuNo());
                            //经营类型（自营or联营）
                            String isUnionBizInCombination = skuInCombination.getSourceType();
                            //购买数量（组合数量*组合商品的数量）
                            Long itemSaleNumInCombination = orderItem.getSaleNum() * orderCombineRelation.getSaleNum();
                            //店内码
                            String itemNumberInCombination = skuInCombination.getItemnumber();
                            //SKU编码
                            String stockNoInCombination = orderCombineRelation.getStockNo();
                            //条码
                            String barcodeInCombination = skuInCombination.getBarcode();

                            Map<String, Object> obj = new HashMap<String, Object>();
                            obj.put("tc_ufa001", orderSubNo1);
                            obj.put("tc_ufa002", stockNoInCombination);
                            obj.put("tc_ufa003", itemNumberInCombination);
                            obj.put("tc_ufa004", barcodeInCombination);
                            obj.put("tc_ufa005", this.getSaleNum(orderMain.getBillType(), itemSaleNumInCombination));
                            // 原价, max(normal_price, unit_price)
                            BigDecimal np = (null != orderCombineRelation.getUnitPrice() ? orderCombineRelation.getUnitPrice() : new BigDecimal(0));
                            obj.put("tc_ufa006", np);
                            BigDecimal ufa07 = null;
                            if (SourceType.Self.toString().equals(isUnionBizInCombination)) {
                                ufa07 = DataUtil.convertBigDecimal(orderCombineRelation.getUnitPrice());
                            } else  if (SourceType.Joint.toString().equals(isUnionBizInCombination)) {
                                if (itemSaleNum > 0) {
                                    ufa07 = orderCombineRelation.getUnitPrice();
                                } else {
                                    ufa07 = DataUtil.convertBigDecimal(null);
                                }
                            }else{
                                continue;
                            }
                            obj.put("tc_ufa007", ufa07);
                            obj.put("tc_ufa008", ufa07);
                            obj.put("tc_ufa009", skuInCombination.getSupplierId());
                            // VIP卡号
                            obj.put("tc_ufa010", "");
                            // VIP 等级,如果为空，写1
                            String memberCardLevel = StringUtils.isBlank(orderMain.getMemberCardLevel()) ? "1":orderMain.getMemberCardLevel();
                            obj.put("tc_ufa011", memberCardLevel);
                            // 积分 叠加
                            obj.put("tc_ufa012", new BigDecimal(0));
                            // 折扣
                            BigDecimal ufa0013 = DataUtil.convertBigDecimal(np).subtract(ufa07);
                            if (itemSaleNum > 0) {
                                obj.put("tc_ufa013", ufa0013.multiply(new BigDecimal(itemSaleNumInCombination)));
                            } else {
                                obj.put("tc_ufa013", DataUtil.convertBigDecimal(null));
                            }
                            BigDecimal ip = orderCombineRelation.getInventoryPrice();
                            obj.put("tc_ufa014", DataUtil.convertBigDecimal(ip));
                            obj.put("tc_ufa015", orderSource);
                            Date finishTime = getOrderFinishTime(orderMain);
                            obj.put("trans_date", settleOrRefundOrderServiceHandler.getTransDate(finishTime));
                            obj.put("trans_time", null);
                            obj.put("trans_flag", "N");
                            obj.put("trans_crea", orderCombineRelation.getDateCreated());
                            obj.put("tc_ufa016", this.getBillType(orderMain.getBillType()));
                            obj.put("tc_ufa017", null);
                            obj.put("tc_ufa018", null);
                            obj.put("tc_ufa019", null);
                            obj.put("tc_ufa020", null);
                            obj.put("tc_ufa021", null);
                            obj.put("tc_ufa022", null);
                            obj.put("tc_ufa023", null);
                            obj.put("tc_ufa024", null);

                            //合并
                            if(!mergeUfa(ufa123Map, obj, isUnionBizInCombination)){
                                if(isIntegralOrder){
                                    //积分商城订单是用原价20141015需求
                                    obj.put("tc_ufa007", obj.get("tc_ufa006"));
                                    obj.put("tc_ufa008", obj.get("tc_ufa006"));
                                }
                                ufa123Map.put(orderSubNo1, stockNoInCombination, itemNumberInCombination, obj);
                            }
                        }
                    }else{
                        Map<String, Object> obj = new HashMap<String, Object>();
                        obj.put("tc_ufa001", orderSubNo1);
                        obj.put("tc_ufa002", stockNo);
                        obj.put("tc_ufa003", skuNo);
                        obj.put("tc_ufa004", orderItem.getBarCode());
                        obj.put("tc_ufa005", this.getSaleNum(orderMain.getBillType(), itemSaleNum));
                        // 原价, max(normal_price, unit_price)
                        BigDecimal np = (normalPrice.compareTo(orderItem.getUnitPrice()) < 0) ? orderItem.getUnitPrice() : orderItem
                                .getNormalPrice();
                        obj.put("tc_ufa006", DataUtil.convertBigDecimal(np));
                        BigDecimal ufa07 = null;
                        if (SourceType.Self.toString().equals(isUnionBiz)) {
                            ufa07 = DataUtil.convertBigDecimal(orderItem.getUnitPrice());
                        } else  if (SourceType.Joint.toString().equals(isUnionBiz)) {
                            if (itemSaleNum > 0) {
                                ufa07 = orderItem.getPayAmount().divide(new BigDecimal(itemSaleNum), 4,
                                        BigDecimal.ROUND_HALF_UP);

                            } else {
                                ufa07 = DataUtil.convertBigDecimal(null);
                            }
                        }else{
                            continue;
                        }
                        obj.put("tc_ufa007", ufa07);
                        obj.put("tc_ufa008", ufa07);
                        obj.put("tc_ufa009", sku.getSupplierId());
                        // VIP卡号
                        obj.put("tc_ufa010", "");
                        // VIP 等级,如果为空，写1
                        String memberCardLevel = StringUtils.isBlank(orderMain.getMemberCardLevel()) ? "1":orderMain.getMemberCardLevel();
                        obj.put("tc_ufa011", memberCardLevel);
                        // 积分 叠加
                        obj.put("tc_ufa012", DataUtil.convertBigDecimal(orderItem.getProductPoint()));
                        // 折扣
                        BigDecimal ufa0013 = DataUtil.convertBigDecimal(np).subtract(ufa07);
                        if (itemSaleNum > 0) {
                            obj.put("tc_ufa013", ufa0013.multiply(new BigDecimal(itemSaleNum)));
                        } else {
                            obj.put("tc_ufa013", DataUtil.convertBigDecimal(null));
                        }
                        BigDecimal ip = orderItem.getInventoryPrice();
                        obj.put("tc_ufa014", DataUtil.convertBigDecimal(ip));
                        obj.put("tc_ufa015", orderSource);
                        Date finishTime = getOrderFinishTime(orderMain);
                        obj.put("trans_date", settleOrRefundOrderServiceHandler.getTransDate(finishTime));
                        obj.put("trans_time", null);
                        obj.put("trans_flag", "N");
                        obj.put("trans_crea", orderItem.getDateCreated());
                        obj.put("tc_ufa016", this.getBillType(orderMain.getBillType()));
                        obj.put("tc_ufa017", null);
                        obj.put("tc_ufa018", null);
                        obj.put("tc_ufa019", null);
                        obj.put("tc_ufa020", null);
                        obj.put("tc_ufa021", null);
                        obj.put("tc_ufa022", null);
                        obj.put("tc_ufa023", null);
                        obj.put("tc_ufa024", null);
                        if(isIntegralOrder){
                            //积分商城订单是用原价20141015需求
                            obj.put("tc_ufa007", obj.get("tc_ufa006"));
                            obj.put("tc_ufa008", obj.get("tc_ufa006"));
                        }
                        ufa123Map.put(orderSubNo1, stockNo, skuNo, obj);
                    }
                }
            }
            if (virtualList != null && virtualList.size() > 0) {
                String skuNo = null;
                String stockNo = null;
//                String isUnionBiz = null;
                for (OrderItemVirtual orderItemVirtual : virtualList) {
                    Map<String, Object> obj = new HashMap<String, Object>();
                    String orderSubNo1 = getOrderSubNo(orderMain);
                    SkuR3 sku = skuR3Service.getR3BySku(orderItemVirtual.getSkuNo());
                    if (OrderMainConst.OrderItem_OrderItemClass_GIFT.getCode().equals(
                            orderItemVirtual.getOrderItemClass())) {
                        if (orderItemVirtual.getUnitPrice() == null || orderItemVirtual.getUnitPrice().compareTo(new BigDecimal(0)) <= 1) {
                            stockNo = "001950500100";
                        } else {
                            stockNo = "001950500600";
                        }
                    } else {
                        stockNo = orderItemVirtual.getStockNo();
//                        isUnionBiz = sku.getSourceType();
                    }
                    skuNo = sku.getItemnumber();
                    obj.put("tc_ufa001", orderSubNo1);
                    obj.put("tc_ufa002", stockNo);
                    obj.put("tc_ufa003", skuNo);
                    obj.put("tc_ufa004", orderItemVirtual.getBarCode());
                    obj.put("tc_ufa005", this.getSaleNum(orderMain.getBillType(), orderItemVirtual.getSaleNum()));
                    obj.put("tc_ufa006", DataUtil.convertBigDecimal(orderItemVirtual.getUnitPrice()));
                    obj.put("tc_ufa007", DataUtil.convertBigDecimal(orderItemVirtual.getUnitPrice()));
                    obj.put("tc_ufa008", DataUtil.convertBigDecimal(orderItemVirtual.getUnitPrice()));
                    obj.put("tc_ufa009", orderItemVirtual.getSupplierCode());
                    // VIP卡号
                    obj.put("tc_ufa010", "");
                    // VIP 等级
                    String memberCardLevel = StringUtils.isBlank(orderMain.getMemberCardLevel()) ? "1":orderMain.getMemberCardLevel();
                    obj.put("tc_ufa011", memberCardLevel);
                    // 积分 叠加
                    obj.put("tc_ufa012", DataUtil.convertBigDecimal(orderItemVirtual.getProductPoint()));
                    obj.put("tc_ufa013", DataUtil.convertBigDecimal(null));
                    obj.put("tc_ufa014", DataUtil.convertBigDecimal(null));
                    obj.put("tc_ufa015", orderSource);
                    Date finishTime = getOrderFinishTime(orderMain);
                    obj.put("trans_date", settleOrRefundOrderServiceHandler.getTransDate(finishTime));                    obj.put("trans_time", null);
                    obj.put("trans_flag", "N");
                    obj.put("trans_crea", orderItemVirtual.getDateCreated());
                    obj.put("tc_ufa016", this.getBillType(orderMain.getBillType()));
                    obj.put("tc_ufa017", null);
                    obj.put("tc_ufa018", null);
                    obj.put("tc_ufa019", null);
                    obj.put("tc_ufa020", null);
                    obj.put("tc_ufa021", null);
                    obj.put("tc_ufa022", null);
                    obj.put("tc_ufa023", null);
                    obj.put("tc_ufa024", null);

                    resultList.add(obj);
                }
            }

            if (!ufa123Map.isEmpty()) {
                Iterator iterator = ufa123Map.keySet().iterator();
                while (iterator.hasNext()) {
                    resultList.add((Map<String, Object>) ufa123Map.get(iterator.next()));

                }
            }

            // 折扣费用
            Map<String, Object> discountMap = this.handlerDiscount(orderMain);
            if (discountMap != null) {
                resultList.add(discountMap);
            }
            // 物流费用
            Map<String, Object> transFeeMap = this.handlerTransFee(orderMain);
            if (transFeeMap != null) {
                resultList.add(transFeeMap);
            }
//            row.setSyncFlag("P");
        }
//        orderStatusSyncLogService.update(logList);
        return resultList;
    }
	
	/**
	 * @return
	 */
	private Map<String, Object>	handlerDiscount(OrderMain orderMain){
//		BigDecimal minus =new BigDecimal(-1);
		List<OrderItem> itemList = orderItemService.getByOrdeNo(orderMain.getOrderNo());
		Date date = null  ;
		BigDecimal itemDiscount = new BigDecimal(0);
		if(itemList!=null && !itemList.isEmpty()){
			OrderItem orderItem= null;
			int len = itemList.size();
			for (int i=0;i < len;i++) {
				orderItem = itemList.get(i);
				SkuR3 sku = skuR3Service.getR3BySku(orderItem.getSkuNo());
                String isUnionBiz = sku.getSourceType();
				//自营商品
				if(SourceType.Self.toString().equals(isUnionBiz)){ 
					//折扣叠加
					itemDiscount = itemDiscount.add(DataUtil.convertBigDecimal(orderItem.getSaleTotalMoney()).subtract(DataUtil.convertBigDecimal(orderItem.getPayAmount())));
				}
			}

			if(itemDiscount.compareTo(new BigDecimal(0))>0){
                //订单来源
                String orderSource = orderStatusSyncLogService.getOrderSource(orderMain);

				Map<String, Object> obj = new HashMap<String, Object>();
				String orderSubNo1 = getOrderSubNo(orderMain);
				obj.put("tc_ufa001", orderSubNo1);
				obj.put("tc_ufa002", "001951015800");
				obj.put("tc_ufa003", "a040403565");
				obj.put("tc_ufa004", "009010239");
				obj.put("tc_ufa005", -1L);
				obj.put("tc_ufa006", itemDiscount);
				obj.put("tc_ufa007", itemDiscount);
				obj.put("tc_ufa008", itemDiscount);
				obj.put("tc_ufa009", "188888");
				//VIP卡号
				obj.put("tc_ufa010", "");
				//VIP 等级 
                String memberCardLevel = StringUtils.isBlank(orderMain.getMemberCardLevel()) ? "1":orderMain.getMemberCardLevel();
                obj.put("tc_ufa011", memberCardLevel);
				//积分   叠加
				obj.put("tc_ufa012", DataUtil.convertBigDecimal(null));
				obj.put("tc_ufa013", DataUtil.convertBigDecimal(null));
				obj.put("tc_ufa014",DataUtil.convertBigDecimal(null) );
				obj.put("tc_ufa015", orderSource);
	            Date finishTime = getOrderFinishTime(orderMain);
	            obj.put("trans_date", settleOrRefundOrderServiceHandler.getTransDate(finishTime));				obj.put("trans_time", null);
				obj.put("trans_flag", "N");
				obj.put("trans_crea", orderItem.getDateCreated());
				obj.put("tc_ufa016", this.getBillType(orderMain.getBillType()));
				obj.put("tc_ufa017",  null);
				obj.put("tc_ufa018", null);
				obj.put("tc_ufa019", null);
				obj.put("tc_ufa020", null);
				obj.put("tc_ufa021", null);
				obj.put("tc_ufa022", null);
				obj.put("tc_ufa023", null);
				obj.put("tc_ufa024", null);
				return obj;
			}
		}
		
		return null;
		
	}
	
	/**
	 * 处理逆向单的折扣项
	 * @return
	 */
	private Map<String, Object>	handlerNegativeDiscount(OrderMain orderMain){
		Map<String, Object> obj = null;
		//List<OrderItem> itemList = orderItemService.getByOrdeNo(orderMain.getOrderNo());
		List<OrderRetChgItem> itemList = orderRetChgItemService.findByField("orderNo", orderMain.getOrderNo());
		boolean isChg = CommonConst.OrderMain_OrderCategory_Change.getCode().equals(orderMain.getOrderCategory());
		Date date = null  ;
		BigDecimal itemDiscount = new BigDecimal(0);
		if(itemList!=null && !itemList.isEmpty()){
			OrderRetChgItem orderItem= null;
			int len = itemList.size();
			for (int i=0;i < len;i++) {
				orderItem = itemList.get(i);
	             SkuR3 sku = skuR3Service.getR3BySku(orderItem.getSkuNo());
	                String isUnionBiz = sku.getSourceType();
	                //自营商品，
	                if(SourceType.Self.toString().equals(isUnionBiz)){ 
	                    //折扣叠加
	                    if(isChg){
	                      //换货入库的逆向单折扣算法
	                        BigDecimal saleTotalMoney = new BigDecimal(orderItem.getSaleNum()==null ? 0l : orderItem.getSaleNum()).multiply(DataUtil.convertBigDecimal(orderItem.getUnitPrice()));
	                        itemDiscount = itemDiscount.add(DataUtil.convertBigDecimal(saleTotalMoney).subtract(DataUtil.convertBigDecimal(orderItem.getPayAmount())));
	                    }else{
	                        //退货逆向单的折扣算法sale_num*(unit_price-coupon_amount)-item_discount<>pay_amount
	                        itemDiscount = itemDiscount.add(orderItem.getItemDiscount());
	                    }
	                }
			}

			if(itemDiscount.compareTo(new BigDecimal(0))>0){
                //订单来源
                String orderSource = orderStatusSyncLogService.getOrderSource(orderMain);

				obj = new HashMap<String, Object>();
				obj = new HashMap<String, Object>();
				String orderSubNo1 = getOrderSubNo(orderMain);
				obj.put("tc_ufa001", orderSubNo1);
				obj.put("tc_ufa002", "001951015800");
				obj.put("tc_ufa003", "a040403565");
				obj.put("tc_ufa004", "009010239");
				obj.put("tc_ufa005", 1L);
				obj.put("tc_ufa006", itemDiscount);
				obj.put("tc_ufa007", itemDiscount);
				obj.put("tc_ufa008", itemDiscount);
				obj.put("tc_ufa009", "188888");
				//VIP卡号
				obj.put("tc_ufa010", "");
				//VIP 等级 
                String memberCardLevel = StringUtils.isBlank(orderMain.getMemberCardLevel()) ? "1":orderMain.getMemberCardLevel();
                obj.put("tc_ufa011", memberCardLevel);
				//积分   叠加
				obj.put("tc_ufa012", DataUtil.convertBigDecimal(null));
				obj.put("tc_ufa013", DataUtil.convertBigDecimal(null));
				obj.put("tc_ufa014",DataUtil.convertBigDecimal(null) );
				obj.put("tc_ufa015", orderSource);
	            Date finishTime = getOrderFinishTime(orderMain);
	            obj.put("trans_date", settleOrRefundOrderServiceHandler.getTransDate(finishTime));				obj.put("trans_time", null);
				obj.put("trans_flag", "N");
				obj.put("trans_crea", orderItem.getDateCreated());
				obj.put("tc_ufa016", this.getBillType(orderMain.getBillType()));
				obj.put("tc_ufa017",  null);
				obj.put("tc_ufa018", null);
				obj.put("tc_ufa019", null);
				obj.put("tc_ufa020", null);
				obj.put("tc_ufa021", null);
				obj.put("tc_ufa022", null);
				obj.put("tc_ufa023", null);
				obj.put("tc_ufa024", null);
			}
		}
		
		return obj;
		
	}
	
	private  Map<String, Object> handlerTransFee(OrderMain orderMain){
		Map<String, Object> obj = null;
		Date date = null  ;
		if(orderMain.getTransportFee()!=null && orderMain.getTransportFee().compareTo(new BigDecimal(0))>0){
            //订单来源
            String orderSource = orderStatusSyncLogService.getOrderSource(orderMain);

			obj = new HashMap<String, Object>();
			obj = new HashMap<String, Object>();
			String orderSubNo1 = getOrderSubNo(orderMain);
			obj.put("tc_ufa001", orderSubNo1);
			//国贸云店运费使用001240000800库区
			if(OrderMainConst.OrderMain_MerchantNo_Yundian_GuoMao.getCode().equalsIgnoreCase(orderMain.getMerchantNo())){
			    obj.put("tc_ufa002", "001240000800");
			}else{
			    obj.put("tc_ufa002", "001950000800");
			}
			obj.put("tc_ufa003", "a040403565");
			obj.put("tc_ufa004", "009010239");
			obj.put("tc_ufa005",  1l);
			obj.put("tc_ufa006",  DataUtil.convertBigDecimal(orderMain.getTransportFee()));
			obj.put("tc_ufa007", DataUtil.convertBigDecimal(orderMain.getTransportFee()));
			obj.put("tc_ufa008", DataUtil.convertBigDecimal(orderMain.getTransportFee()));
			obj.put("tc_ufa009", "188888");
			//VIP卡号
			obj.put("tc_ufa010", "");
			//VIP 等级 
            String memberCardLevel = StringUtils.isBlank(orderMain.getMemberCardLevel()) ? "1":orderMain.getMemberCardLevel();
            obj.put("tc_ufa011", memberCardLevel);
			//积分   叠加
			obj.put("tc_ufa012", DataUtil.convertBigDecimal(null));
			obj.put("tc_ufa013", DataUtil.convertBigDecimal(null));
			obj.put("tc_ufa014",DataUtil.convertBigDecimal(null) );
			obj.put("tc_ufa015", orderSource);
            Date finishTime = getOrderFinishTime(orderMain);
            obj.put("trans_date", settleOrRefundOrderServiceHandler.getTransDate(finishTime));
            obj.put("trans_time", null);
			obj.put("trans_flag", "N");
			obj.put("trans_crea", orderMain.getDateCreated());
			obj.put("tc_ufa016", this.getBillType(orderMain.getBillType()));
			obj.put("tc_ufa017",  null);
			obj.put("tc_ufa018", null);
			obj.put("tc_ufa019", null);
			obj.put("tc_ufa020", null);
			obj.put("tc_ufa021", null);
			obj.put("tc_ufa022", null);
			obj.put("tc_ufa023", null);
			obj.put("tc_ufa024", null);
		}
		
		return obj;
	}
	
//	private  Map<String, Object> handlerChangeTransFee(OrderMain orderMain){
//		BigDecimal minus =new BigDecimal(-1);
//		Map<String, Object> obj = null;
//		Date date = null  ;
//		if(orderMain.getTransportFee()!=null && orderMain.getTransportFee().compareTo(new BigDecimal(0))>0){
//            //订单来源
//            String orderSource = orderStatusSyncLogService.getOrderSource(orderMain);
//
//			obj = new HashMap<String, Object>();
//			obj = new HashMap<String, Object>();
//			String orderSubNo1 = getOrderSubNo(orderMain);
//
//			obj.put("tc_ufa001", orderSubNo1);
//            if("y00124".equalsIgnoreCase(orderMain.getMerchantNo())){
//                obj.put("tc_ufa002", "001240000800");
//            }else{
//                obj.put("tc_ufa002", "001950000800");
//            }
//			obj.put("tc_ufa003", "a040403565");
//			obj.put("tc_ufa004", "009010239");
//			obj.put("tc_ufa005",  1l);
//			obj.put("tc_ufa006",  DataUtil.convertBigDecimal(orderMain.getTransportFee()).multiply(minus));
//			obj.put("tc_ufa007", DataUtil.convertBigDecimal(orderMain.getTransportFee()).multiply(minus));
//			obj.put("tc_ufa008", DataUtil.convertBigDecimal(orderMain.getTransportFee()).multiply(minus));
//			obj.put("tc_ufa009", "188888");
//			//VIP卡号
//			obj.put("tc_ufa010", "");
//			//VIP 等级 
//            String memberCardLevel = StringUtils.isBlank(orderMain.getMemberCardLevel()) ? "1":orderMain.getMemberCardLevel();
//            obj.put("tc_ufa011", memberCardLevel);
//			//积分   叠加
//			obj.put("tc_ufa012", DataUtil.convertBigDecimal(null));
//			obj.put("tc_ufa013", DataUtil.convertBigDecimal(null));
//			obj.put("tc_ufa014",DataUtil.convertBigDecimal(null) );
//			obj.put("tc_ufa015", orderSource);
//            Date finishTime = getOrderFinishTime(orderMain);
//            obj.put("trans_date", settleOrRefundOrderServiceHandler.getTransDate(finishTime));
//            obj.put("trans_time", null);
//			obj.put("trans_flag", "N");
//			obj.put("trans_crea", orderMain.getDateCreated());
//			obj.put("tc_ufa016", this.getBillType(orderMain.getBillType()));
//			obj.put("tc_ufa017",  null);
//			obj.put("tc_ufa018", null);
//			obj.put("tc_ufa019", null);
//			obj.put("tc_ufa020", null);
//			obj.put("tc_ufa021", null);
//			obj.put("tc_ufa022", null);
//			obj.put("tc_ufa023", null);
//			obj.put("tc_ufa024", null);
//		}
//		
//		return obj;
//	}
	
	/**
	 * 逆向订单  数量正数 金额负数
	 * @param orderMain
	 * @return
	 */
	private List<Map<String,Object>>  handlerNegativeOrderData(OrderMain orderMain ){
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		Map<String, Object> obj = null;
		List<OrderRetChgItem> itemList = orderRetChgItemService.findByField("orderNo", orderMain.getOrderNo());
        //订单来源
        String orderSource = orderStatusSyncLogService.getOrderSource(orderMain);
		for (OrderRetChgItem orderItem : itemList) {
			obj = new HashMap<String, Object>();
			obj = new HashMap<String, Object>();
			String orderSubNo1 = getOrderSubNo(orderMain);

			SkuR3 sku = skuR3Service.getR3BySku(orderItem.getSkuNo());
			if(sku == null){
			    log.info(orderItem.getOrderItemNo());
			    return null;
			}
			String	skuNo = sku.getItemnumber();
			String stockNo = orderItem.getStockNo();
            String isUnionBiz = sku.getSourceType();
			
			obj.put("tc_ufa001", orderSubNo1);
			obj.put("tc_ufa002", stockNo);
			obj.put("tc_ufa003", skuNo);
			obj.put("tc_ufa004", orderItem.getBarCode());
			obj.put("tc_ufa005", orderItem.getSaleNum()*(-1));
			obj.put("tc_ufa006", DataUtil.convertBigDecimal(orderItem.getUnitPrice()));
            BigDecimal ufa07 = null;
            if (SourceType.Self.toString().equals(isUnionBiz)) {
                ufa07 = DataUtil.convertBigDecimal(orderItem.getUnitPrice());
            } else if(SourceType.Joint.toString().equals(isUnionBiz)){
                if (orderItem.getSaleNum() > 0) {
                    //联营单价取
                    //sale_num*(unit_price-coupon_amount)-item_discount<>pay_amount
                    ufa07 = orderItem.getPayAmount().divide(new BigDecimal(orderItem.getSaleNum()), 4,
                            BigDecimal.ROUND_HALF_UP).add(orderItem.getCouponAmount());
                } else {
                    ufa07 = DataUtil.convertBigDecimal(null);
                }
            }else{
                return null;
            }
			obj.put("tc_ufa007", ufa07);
			obj.put("tc_ufa008", ufa07);
			
			obj.put("tc_ufa009", sku.getSupplierId());
			//VIP卡号
			obj.put("tc_ufa010", "");
			//VIP 等级 
            String memberCardLevel = StringUtils.isBlank(orderMain.getMemberCardLevel()) ? "1":orderMain.getMemberCardLevel();
            obj.put("tc_ufa011", memberCardLevel);
			//积分   叠加
			obj.put("tc_ufa012", DataUtil.convertBigDecimal(orderItem.getProductPoint()));
			
            // 折扣
            // 原价, max(normal_price, unit_price)
            BigDecimal np = (DataUtil.convertBigDecimal(orderItem.getUnitPrice()));
            BigDecimal ufa0013 = DataUtil.convertBigDecimal(np).subtract(ufa07);
            if (orderItem.getSaleNum() > 0) {
                obj.put("tc_ufa013", ufa0013.multiply(new BigDecimal(-1)).multiply(new BigDecimal(orderItem.getSaleNum())));
            } else {
                obj.put("tc_ufa013", DataUtil.convertBigDecimal(null));
            }
			obj.put("tc_ufa014", DataUtil.convertBigDecimal(orderItem.getInventoryPrice()) );
			obj.put("tc_ufa015", orderSource);
			Date finishTime = getOrderFinishTime(orderMain);
			obj.put("trans_date", settleOrRefundOrderServiceHandler.getTransDate(finishTime));
			obj.put("trans_time", null);
			obj.put("trans_flag", "N");
			obj.put("trans_crea", orderItem.getDateCreated());
			obj.put("tc_ufa016", this.getBillType(orderMain.getBillType()));
			obj.put("tc_ufa017",  null);
			obj.put("tc_ufa018", null);
			obj.put("tc_ufa019", null);
			obj.put("tc_ufa020", null);
			obj.put("tc_ufa021", null);
			obj.put("tc_ufa022", null);
			obj.put("tc_ufa023", null);
			obj.put("tc_ufa024", null);
			resultList.add(obj);
		}
		
		//折扣费用
		 Map<String, Object> discountMap = this.handlerNegativeDiscount(orderMain);
		 if(discountMap!=null){
			 resultList.add(discountMap);
		 }
		 if(CommonConst.OrderMain_OrderCategory_Return.getCode().equals( orderMain.getOrderCategory())){
			//换货入库不管物流费用,退货才管
			 Map<String, Object> transFeeMap = this.handlerTransFee(orderMain);
			 if(transFeeMap !=null){
				 resultList.add(transFeeMap);
			 }
		 }
//		 else{
//			 Map<String, Object> transFeeMap = this.handlerTransFee(orderMain);
//			 if(transFeeMap !=null){
//				 resultList.add(transFeeMap);
//			 }
//		 }
		return resultList;
	}
	
/*	private BigDecimal calOrderOtemPonit(String orderItemNo){
		List<OrderPromotion> pList = orderPromotionService.findByField("orderItemNo", orderItemNo);
		BigDecimal date  = new BigDecimal(0);
		
		for (OrderPromotion orderPromotion : pList) {
			orderPromotion.getPromoType();
			date=date.add(orderPromotion.getPointCount());
		}
		
		return date;
	}*/
	
	private Long getBillType(Long billType){
		if(billType==null){
			return 0l;
		}
		if(CommonConst.OrderMain_BillType_Positive.getCodeLong()==billType){
			return 0L;
		}else if(CommonConst.OrderMain_BillType_Negative.getCodeLong()==billType){
			return 6L;
		}
		return 0l;
	}
	
	private Long getSaleNum(Long billType,Long saleNum){
		if(saleNum==null){
			return 0l;
		}
		if(CommonConst.OrderMain_BillType_Positive.getCodeLong()== billType){
			return saleNum;
		}else if(CommonConst.OrderMain_BillType_Negative.getCodeLong()==billType){
			return saleNum*(-1);
		}
		return 0l;
	}
	
	public String getOrderSubNo(OrderMain om){
	    String orderSubNo1 = null;
	    String orderNo = om.getOrderNo();
	    if (R3Constant.IMMIGRATION.equals(om.getImmigrationVersion())) {
            orderSubNo1 = orderNo;
        } else {
            orderSubNo1 = orderNoService.getDefaultOrderSubNoByOrderNo(orderNo);
        }
        //orderSubNo1 = orderNo + "*9";  //temp
        return orderSubNo1;
	}
	
	Date getOrderFinishTime(OrderMain om){
	    if(om == null || om.getFinishTime() == null){
	        return new Date();
	    }else if(CommonConst.OrderMain_OrderCategory_Sale.getCode().equals(om.getOrderCategory())
	            || CommonConst.OrderMain_OrderCategory_Return.getCode().equals(om.getOrderCategory())){
	        //销售或者退货
	        return om.getFinishTime();
	    }else if(CommonConst.OrderMain_OrderCategory_Change.getCode().equals(om.getOrderCategory())){
	        //换货入库
	        //当前单为入库单，查找出库单
	        OrderMain chgOut = orderMainService.getByField(OrderMain_.orderRelatedOrigin, om.getOrderNo());
	        if(chgOut == null || chgOut.getFinishTime() == null){
	            return om.getFinishTime();
	        }else{
	            return chgOut.getFinishTime().after(om.getFinishTime()) ? chgOut.getFinishTime():om.getFinishTime();
	        }
	    }else if(CommonConst.OrderMain_OrderCategory_ChangeOut.getCode().equals(om.getOrderCategory())){
            //换货出库
            //当前单为出库单，查找入库单
            OrderMain chg = orderMainService.getByField(OrderMain_.orderNo, om.getOrderRelatedOrigin());
            if(chg == null || chg.getFinishTime() == null){
                return om.getFinishTime();
            }else{
                return chg.getFinishTime().after(om.getFinishTime()) ? chg.getFinishTime():om.getFinishTime();
            }
	    }
	    return new Date();
	}

    /**
     * 合并柜组明细
     *
     * @param ufa123Map 所有明细
     * @param newUfa 新的柜组明细
     * @param isUnionBiz 自营or联营
     * @return
     */
    private boolean mergeUfa(MultiKeyMap ufa123Map, Map<String, Object> newUfa, String isUnionBiz){
        boolean merged = false;

        String orderSubNo1 = (String)newUfa.get("tc_ufa001"); //单号
        String stockNo = (String)newUfa.get("tc_ufa002"); //销售库区
        String skuNo = (String)newUfa.get("tc_ufa003"); //店内码

        //如果已经有重复的商品行，根据经营类型进行合并
        if (ufa123Map.containsKey(orderSubNo1, stockNo, skuNo)) {
            Map<String, Object> obj = (HashMap<String, Object>) ufa123Map.get(orderSubNo1, stockNo, skuNo);

            Long lastSaleNum = (Long) obj.get("tc_ufa005");
            BigDecimal lastSaleNumB = new BigDecimal(lastSaleNum);

            BigDecimal last007B = (BigDecimal) obj.get("tc_ufa007");
            BigDecimal oldUfa013 = (BigDecimal) obj.get("tc_ufa013");

            //购买数量
            BigDecimal itemSaleNum = new BigDecimal((Long) newUfa.get("tc_ufa005"));

            // 联营 自营区分
            if (SourceType.Self.toString().equals(isUnionBiz)) {
                //总价
                BigDecimal lastGross007 = ((BigDecimal)obj.get("tc_ufa007")).multiply(lastSaleNumB);
                BigDecimal thisGross007 = ((BigDecimal)newUfa.get("tc_ufa007")).multiply(itemSaleNum);
                //原价
                BigDecimal lastGross006 = ((BigDecimal)obj.get("tc_ufa006")).multiply(lastSaleNumB);
                BigDecimal thisGross006 = ((BigDecimal)newUfa.get("tc_ufa006")).multiply(itemSaleNum);
                //数量累加
                Long totalSaleNum = itemSaleNum.longValue() + lastSaleNum;
                obj.put("tc_ufa005", totalSaleNum);
                BigDecimal avgGross007 = lastGross007.add(thisGross007).divide(new BigDecimal(totalSaleNum), 4, 2);
                //原价平均
                BigDecimal avgGross006 = (lastGross006.add(thisGross006)).divide(new BigDecimal(totalSaleNum), 4, 2);
                //成交价平均
                obj.put("tc_ufa007", avgGross007);
                obj.put("tc_ufa008", avgGross007);
                obj.put("tc_ufa006", avgGross006);
                //折扣累加 , 5*(6-7)
                BigDecimal newUfa0013 = (avgGross006.subtract(avgGross007)).multiply(new BigDecimal(totalSaleNum));
                obj.put("tc_ufa013", newUfa0013);
                //出库成本取平均
                BigDecimal cufa014 = (BigDecimal) obj.get("tc_ufa014");
                BigDecimal allPay14 = cufa014.multiply(lastSaleNumB);
                BigDecimal afterPay14 = allPay14.add(DataUtil.convertBigDecimal((BigDecimal) newUfa.get("tc_ufa014")).multiply(itemSaleNum));
                BigDecimal ufa014 = afterPay14.divide(lastSaleNumB.add(itemSaleNum),4, BigDecimal.ROUND_HALF_UP);
                obj.put("tc_ufa014", ufa014);

                merged = true;
            } else if (SourceType.Joint.toString().equals(isUnionBiz)) { //联营处理
                BigDecimal afterPay6 = null;
                BigDecimal afterPay7 = null;
                BigDecimal cufa006 = (BigDecimal) obj.get("tc_ufa006");
                //原价、应收价、出库成本求和，购买数量为1
                if (itemSaleNum.longValue() > 0) {
                    afterPay6 = cufa006.add(((BigDecimal) newUfa.get("tc_ufa006")).multiply(itemSaleNum));
                    afterPay7 = last007B.add(((BigDecimal) newUfa.get("tc_ufa007")).multiply(itemSaleNum));
                } else {
                    afterPay6 = DataUtil.convertBigDecimal(null);
                    afterPay7 = DataUtil.convertBigDecimal(null);
                }
                obj.put("tc_ufa005", 1L);
                obj.put("tc_ufa006", afterPay6);
                obj.put("tc_ufa007", afterPay7);
                obj.put("tc_ufa008", afterPay7);
                BigDecimal ufa0013 = DataUtil.convertBigDecimal(afterPay6).subtract(afterPay7);
                obj.put("tc_ufa013", ufa0013);
                BigDecimal cufa014 = (BigDecimal) obj.get("tc_ufa014");
                BigDecimal allPay14 = cufa014.multiply(lastSaleNumB);
                BigDecimal afterPay14 = allPay14.add(DataUtil.convertBigDecimal((BigDecimal) newUfa.get("tc_ufa014")).multiply(itemSaleNum));
                obj.put("tc_ufa014", afterPay14);

                merged = true;
            }
        }

        return merged;
    }


}

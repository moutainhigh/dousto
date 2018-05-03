/**
 * 
 */
package com.ibm.oms.service.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.collections.map.MultiKeyMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.dao.intf.StatusDictDao;
import com.ibm.oms.domain.persist.OrderDic;
import com.ibm.oms.domain.persist.OrderDic_;
import com.ibm.oms.domain.persist.OrderReason;
import com.ibm.oms.domain.persist.StatusDict;
import com.ibm.oms.domain.persist.TransportArea;
import com.ibm.oms.intf.constant.OrderMainConst;
import com.ibm.oms.service.OrderDicService;
import com.ibm.oms.service.OrderReasonService;
import com.ibm.oms.service.TransportAreaService;
import com.ibm.sc.dao.sys.OptionDao;
import com.ibm.sc.model.sys.Option;
import com.ibm.sc.service.sys.OptionService;

/**
 * 公共缓存工具类
 * @author xiaonanxiang
 *
 */
@Component
public class CommonCacheUtil {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * 顶级区域级别，如：中国
     */
    private static final Long BASE_AREA_LEVEL = 0L;
    
    public static final Long MAX_AREA_LEVEL = 3L;
    
    @Autowired
    StatusDictDao statusDictDao;
    
    @Resource
    protected OptionService optionService;
    
    @Resource
    protected OrderDicService orderDicService;
    
    @Resource
    protected CommonUtilService commonUtilService;
    
    @Resource
    protected OrderReasonService orderReasonService;
    
    @Resource
    protected OptionDao optionDao;
    
    @Resource
    protected TransportAreaService transportAreaService;
    
    
    private List<StatusDict> statusTotalList = new ArrayList<StatusDict>();
    
    private List<Option> distributeTypeList = new ArrayList<Option>();
    
    private List<StatusDict> statusPayList = new ArrayList<StatusDict>();
    
    
    private List<StatusDict> statusConfirmList = new ArrayList<StatusDict>();
    
    private List<StatusDict> logisticsStatusList = new ArrayList<StatusDict>();
    
    private List<CommonConst> allIfNeedRefundList = new ArrayList<CommonConst>();
    
    private List<OrderMainConst> allDistributeType = new ArrayList<OrderMainConst>();
    private Map<String,String> allDistributeTypeMap = new HashMap<String,String>();
    
    private List<CommonConst> allInvoicePrintedList = new ArrayList<CommonConst>();
    
    private List<CommonConst> blackList = new ArrayList<CommonConst>();
    
    private List<OrderMainConst> allOrderCategoryList = new ArrayList<OrderMainConst>();
    
    private List<OrderMainConst> orderCategoryList = new ArrayList<OrderMainConst>();
    private List<OrderMainConst> orderRefundTypeList = new ArrayList<OrderMainConst>();
    
    private List<OrderDic> orderSourceList =  new ArrayList<OrderDic>();
    
    private List<OrderDic> orderTypeList =  new ArrayList<OrderDic>();
    
    private Map<String, String> preReasonMap = new HashMap<String, String>();
    
    private Map<String, List<OrderReason>> reasonMap = new HashMap<String,  List<OrderReason>>();
    
    private MultiKeyMap optionMultiKeyMap = new MultiKeyMap();
    
    private Map<String, OrderDic> orderSourceMap = new HashMap<String, OrderDic>();
    
    private Map<String, TransportArea> transportAreaMap = new HashMap<String, TransportArea>();
    
    private  Map<Long, List<TransportArea>> transportParentAreaMap = new HashMap<Long, List<TransportArea>> ();
    private  Map<Long, List<TransportArea>> transportIdAreaMap = new HashMap<Long, List<TransportArea>> ();
    private  Map<Long, List<TransportArea>> provinceAreaMap = new HashMap<Long, List<TransportArea>> ();
    
    private MultiKeyMap transportMultiAreaMap = new MultiKeyMap();
    
    private  Map<Long, TransportArea> transportIdMap = new HashMap<Long, TransportArea> ();
    
    private  Map<Long,  List<String>> addressCodesMap = new HashMap<Long, List<String>> ();
    /**
     * 配送完整地址
     */
    private Map<String, String> transportAreaFullNameMap = new HashMap<String, String>();
    /**
     * 订单配送时间段
     */
    private List<Option> deliveryDateFlagList = new ArrayList<Option>();
    /**
     * 入库方式 门店代退二级选择
     */
    private Map<String, String> distributeTypeLevel2Map = new HashMap<String, String>();
    
    /**
     * 取消订单原因
     */
    private List<Option> cancelReasonList = new ArrayList<Option>();
    
    @PostConstruct
    public void initialize() {
        logger.info("CommonCacheUtil --> initialize --> start");
        
        // 取消订单原因
        cancelReasonList = optionService.findByGroupId(Long.valueOf(CommonConst.OptionGroup_OptionGroupId_CancelOrderReason.getCode()));
        
        // 入库方式 门店代退二级选择
        distributeTypeLevel2Map.put("1", "门店寄回");
        distributeTypeLevel2Map.put("2", "物流到店取回");
        distributeTypeLevel2Map.put("3", "原商品需取回");
        distributeTypeLevel2Map.put("4", "原商品无需取回");
        
        // 订单配送时间段
        deliveryDateFlagList = this.optionService.findByGroupId(Long.valueOf(CommonConst.OptionGroup_OptionGroupId_DeliveryDateFlag.getCode()));
        
        //处理状态
        statusTotalList.add(null);
        statusTotalList.addAll(statusDictDao.findStatusDictByStatusTypeCode(OrderStatus.StatusTypeCode_SaleOrder_StatusTotal.getCode()));
        
        //配送方式
        distributeTypeList.addAll(optionService.findByGroupId(Long.valueOf(CommonConst.Option_OptionGroupId.getCode())));
        distributeTypeList = commonUtilService.sortOptionListbyDisplayName(distributeTypeList);
        
        //支付状态
        statusPayList.add(null);
        statusPayList.addAll(statusDictDao.findStatusDictByStatusTypeCode(OrderStatus.StatusTypeCode_SaleOrder_StatusPay.getCode()));
        
        //审批状态
        statusConfirmList.add(null);
        statusConfirmList.addAll(statusDictDao.findStatusDictByStatusTypeCode(OrderStatus.StatusTypeCode_SaleOrder_StatusReturnMoney.getCode()));       
        
        //物流状态
        logisticsStatusList.addAll(statusDictDao.findStatusDictByStatusTypeCode(OrderStatus.StatusTypeCode_SaleOrder_StatusLogistics.getCode()));
        
        // 是否需要退款
        allIfNeedRefundList.add(null);
        allIfNeedRefundList.add(CommonConst.CommonBooleanFlaseLong);
        allIfNeedRefundList.add(CommonConst.CommonBooleanTrueLong);
//        allIfNeedRefundList.add(CommonConst.OrderMain_IfNeedRefund_Yes_Store);
        
        //入库物流方式
        allDistributeType.add(null);
        allDistributeType.add(OrderMainConst.OrderSub_DistributeType_CustomerSend);
//        allDistributeType.add(OrderMainConst.OrderSub_DistributeType_PickFromDoor);
//        allDistributeType.add(OrderMainConst.OrderSub_DistributeType_ReturnStore);
        
        // 入库物流方式map
        allDistributeTypeMap.put(OrderMainConst.OrderSub_DistributeType_CustomerSend.getCode(), OrderMainConst.OrderSub_DistributeType_CustomerSend.getDesc());
//        allDistributeTypeMap.put(OrderMainConst.OrderSub_DistributeType_PickFromDoor.getCode(), OrderMainConst.OrderSub_DistributeType_PickFromDoor.getDesc());
//        allDistributeTypeMap.put(OrderMainConst.OrderSub_DistributeType_ReturnStore.getCode(), OrderMainConst.OrderSub_DistributeType_ReturnStore.getDesc());
        allDistributeTypeMap.put(OrderMainConst.OrderSub_DistributeType_ReturnLogistics.getCode(), OrderMainConst.OrderSub_DistributeType_ReturnLogistics.getDesc());
        
        //是否需要打印发票
        allInvoicePrintedList.add(CommonConst.CommonBooleanFlaseLong);
        allInvoicePrintedList.add(CommonConst.CommonBooleanTrueLong);
        
        //黑名单列表
        blackList.add(CommonConst.BlackList_spiteRise);
        blackList.add(CommonConst.BlackList_spiteIndent);
        blackList.add(CommonConst.BlackList_spiteComment);
        
        //退换货类型
        allOrderCategoryList.add(null);
        allOrderCategoryList.add(OrderMainConst.OrderMain_OrderCategory_Return);
        allOrderCategoryList.add(OrderMainConst.OrderMain_OrderCategory_Reject);
        allOrderCategoryList.add(OrderMainConst.OrderMain_OrderCategory_Refund);
        allOrderCategoryList.add(OrderMainConst.OrderMain_OrderCategory_TransportFee);
        
        //退款类型
        orderRefundTypeList.add(null);
        orderRefundTypeList.add(OrderMainConst.OrderMain_RefundType_All);
        orderRefundTypeList.add(OrderMainConst.OrderMain_RefundType_Part);
        
        //订单大类
        orderCategoryList.add(null);
        orderCategoryList.add(OrderMainConst.OrderMain_OrderCategory_Sale);
        orderCategoryList.add(OrderMainConst.OrderMain_OrderCategory_ChangeOut);
        
        //订单来源列表
        orderSourceList.addAll(orderDicService.findByField(OrderDic_.dicType,CommonConst.OrderDic_DicType_OrderSource.getCode()));
        
        // 根据dicName排序OrderDic对象List集合
        orderSourceList = commonUtilService.sortOrderDicListbyDisplayName(orderSourceList);
        
        //获取订单类型列表 
        orderTypeList.add(null);
        orderTypeList.addAll(orderDicService.findByField(OrderDic_.dicType,CommonConst.OrderDic_DicType_OrderType.getCode()));
        // 根据dicName排序OrderDic对象List集合
        orderTypeList = commonUtilService.sortOrderDicListbyDisplayName(orderTypeList);
        
        Map<OrderReason, List<OrderReason>> map = orderReasonService.getReasonMap();
        for (Map.Entry<OrderReason, List<OrderReason>> entry : map.entrySet()) {
            for(OrderReason reason: entry.getValue()){
                preReasonMap.put(reason.getReasonNo(), entry.getKey().getReasonNo());
            }
        }
        
        Map<OrderReason, List<OrderReason>> map1 = orderReasonService
                .getReasonMap();
        for (Map.Entry<OrderReason, List<OrderReason>> entry : map1.entrySet()) {
            for(OrderReason reason: entry.getValue()){
                reasonMap.put(reason.getReasonNo(), entry.getValue());
            }
        }
        
        //配送方式
        List<Option> optionList = optionDao.findByGroupId(Long.valueOf(CommonConst.Option_OptionGroupId.getCode()));
        
        if(optionList!=null && !optionList.isEmpty()){
            for (Option option : optionList) {
                optionMultiKeyMap.put(CommonConst.Option_OptionGroupId.getCode(), option.getCode(), option);
            }
        }
        
        //订单来源
        List<OrderDic> allOrderDicList  = orderDicService.getAll();
        for (OrderDic orderDic : allOrderDicList) {
            orderSourceMap.put(orderDic.getDicCode(), orderDic);
        }
        
        // 配送地址
        List<TransportArea> transportAreas= transportAreaService.getAll();
        if(transportAreas!=null && !transportAreas.isEmpty()){
            for (TransportArea transportArea : transportAreas) {
                transportAreaMap.put(transportArea.getAreaCode(), transportArea);
                transportIdMap.put(transportArea.getId(), transportArea);
                List<TransportArea> bhs = null;
                if(transportParentAreaMap.containsKey(transportArea.getParentId())){
                    bhs = (List<TransportArea>) transportParentAreaMap.get(transportArea.getParentId());
                }else{
                    bhs = new ArrayList<TransportArea>();
                    transportParentAreaMap.put(transportArea.getParentId(), bhs);
                }
                bhs.add(transportArea);
                
                List<TransportArea> ids = null;
                if(transportIdAreaMap.containsKey(transportArea.getId())){
                    ids = (List<TransportArea>) transportIdAreaMap.get(transportArea.getId());
                }else{
                    ids = new ArrayList<TransportArea>();
                    transportIdAreaMap.put(transportArea.getId(), ids);
                }
                ids.add(transportArea);
                
                List<TransportArea> provinces = null;
                if(provinceAreaMap.containsKey(transportArea.getAreaLevel())){
                    provinces = (List<TransportArea>) provinceAreaMap.get(transportArea.getAreaLevel());
                }else{
                    provinces = new ArrayList<TransportArea>();
                    provinceAreaMap.put(transportArea.getAreaLevel(), provinces);
                }
                provinces.add(transportArea);
        
                if(!transportMultiAreaMap.containsKey(transportArea.getId(),transportArea.getAreaLevel())){
                    transportMultiAreaMap.put(transportArea.getId(),transportArea.getAreaLevel(), transportArea);
                }
                
                List<String> addressCodes = null;
                if(addressCodesMap.containsKey(transportArea.getId())){
                    addressCodes = (List<String>) addressCodesMap.get(transportArea.getAreaLevel());
                }else{
                    addressCodes = new ArrayList<String>();
                    addressCodesMap.put(transportArea.getId(), addressCodes);
                }
                addressCodes.add(transportArea.getAreaCode());
                //TransportArea  transportArean = transportIdMap.get(transportArea.getId());
        
                addSubAreaCode(transportArea.getId(), addressCodes);
            
            
            }
        }
        
        
        logger.info("CommonCacheUtil --> initialize  -->  end");
    }

    /**
     * 根据入库物流方式code获取name
     * @param code
     * @return
     */
    public String getDistributeTypeNameByCode(String code) {
        if(StringUtils.isEmpty(code)){
            return "";
        }
        return allDistributeTypeMap.get(code);
    }
    
    /**
     * 获取配送时间的名称
     * @param code
     * @return
     */
    public String getDeliveryDateByCode(String code){
        String name="";
        if(StringUtils.isEmpty(code)){
            return "";
        }
        for(Option op:deliveryDateFlagList){
            if(op.getCode().equals(code)){
                name = op.getName();
                break;
            }
        }
        return name;
    }

    public Map<Long, List<String>> getAddressCodesMap() {
        return addressCodesMap;
    }



    public void setAddressCodesMap(Map<Long, List<String>> addressCodesMap) {
        this.addressCodesMap = addressCodesMap;
    }



    public Map<Long, TransportArea> getTransportIdMap() {
        return transportIdMap;
    }


    public void setTransportIdMap(Map<Long, TransportArea> transportIdMap) {
        this.transportIdMap = transportIdMap;
    }


    public MultiKeyMap getTransportMultiAreaMap() {
        return transportMultiAreaMap;
    }


    public void setTransportMultiAreaMap(MultiKeyMap transportMultiAreaMap) {
        this.transportMultiAreaMap = transportMultiAreaMap;
    }


    public Map<Long, List<TransportArea>> getProvinceAreaMap() {
        return provinceAreaMap;
    }


    public void setProvinceAreaMap(Map<Long, List<TransportArea>> provinceAreaMap) {
        this.provinceAreaMap = provinceAreaMap;
    }


    public Map<Long, List<TransportArea>> getTransportIdAreaMap() {
        return transportIdAreaMap;
    }


    public void setTransportIdAreaMap(
            Map<Long, List<TransportArea>> transportIdAreaMap) {
        this.transportIdAreaMap = transportIdAreaMap;
    }


    public Map<Long, List<TransportArea>> getTransportParentAreaMap() {
        return transportParentAreaMap;
    }


    public void setTransportParentAreaMap(
            Map<Long, List<TransportArea>> transportParentAreaMap) {
        this.transportParentAreaMap = transportParentAreaMap;
    }


    public Map<String, TransportArea> getTransportAreaMap() {
        return transportAreaMap;
    }

    public void setTransportAreaMap(Map<String, TransportArea> transportAreaMap) {
        this.transportAreaMap = transportAreaMap;
    }

    public Map<String, List<OrderReason>> getReasonMap() {
        return reasonMap;
    }

    public void setReasonMap(Map<String, List<OrderReason>> reasonMap) {
        this.reasonMap = reasonMap;
    }

    public Map<String, String> getPreReasonMap() {
        return preReasonMap;
    }

    public void setPreReasonMap(Map<String, String> preReasonMap) {
        this.preReasonMap = preReasonMap;
    }

    public List<OrderDic> getOrderTypeList() {
        return orderTypeList;
    }

    public void setOrderTypeList(List<OrderDic> orderTypeList) {
        this.orderTypeList = orderTypeList;
    }

    public List<OrderDic> getOrderSourceList() {
        return orderSourceList;
    }

    public void setOrderSourceList(List<OrderDic> orderSourceList) {
        this.orderSourceList = orderSourceList;
    }

    public List<OrderMainConst> getOrderCategoryList() {
        return orderCategoryList;
    }

    public void setOrderCategoryList(List<OrderMainConst> orderCategoryList) {
        this.orderCategoryList = orderCategoryList;
    }

    public List<OrderMainConst> getAllOrderCategoryList() {
        return allOrderCategoryList;
    }

    public void setAllOrderCategoryList(List<OrderMainConst> allOrderCategoryList) {
        this.allOrderCategoryList = allOrderCategoryList;
    }

    public List<CommonConst> getBlackList() {
        return blackList;
    }

    public void setBlackList(List<CommonConst> blackList) {
        this.blackList = blackList;
    }

    public List<CommonConst> getAllInvoicePrintedList() {
        return allInvoicePrintedList;
    }

    public void setAllInvoicePrintedList(List<CommonConst> allInvoicePrintedList) {
        this.allInvoicePrintedList = allInvoicePrintedList;
    }

    public List<StatusDict> getStatusTotalList() {
        return statusTotalList;
    }

    public void setStatusTotalList(List<StatusDict> statusTotalList) {
        this.statusTotalList = statusTotalList;
    }

    public List<Option> getDistributeTypeList() {
        return distributeTypeList;
    }

    public void setDistributeTypeList(List<Option> distributeTypeList) {
        this.distributeTypeList = distributeTypeList;
    }

    public List<StatusDict> getStatusPayList() {
        return statusPayList;
    }

    public void setStatusPayList(List<StatusDict> statusPayList) {
        this.statusPayList = statusPayList;
    }

    public List<StatusDict> getStatusConfirmList() {
        return statusConfirmList;
    }

    public void setStatusConfirmList(List<StatusDict> statusConfirmList) {
        this.statusConfirmList = statusConfirmList;
    }

    public List<StatusDict> getLogisticsStatusList() {
        return logisticsStatusList;
    }

    public void setLogisticsStatusList(List<StatusDict> logisticsStatusList) {
        this.logisticsStatusList = logisticsStatusList;
    }

    public List<CommonConst> getAllIfNeedRefundList() {
        return allIfNeedRefundList;
    }

    public void setAllIfNeedRefundList(List<CommonConst> allIfNeedRefundList) {
        this.allIfNeedRefundList = allIfNeedRefundList;
    }

    public List<OrderMainConst> getAllDistributeType() {
        return allDistributeType;
    }

    public void setAllDistributeType(List<OrderMainConst> allDistributeType) {
        this.allDistributeType = allDistributeType;
    }

    public MultiKeyMap getOptionMultiKeyMap() {
        return optionMultiKeyMap;
    }

    public void setOptionMultiKeyMap(MultiKeyMap optionMultiKeyMap) {
        this.optionMultiKeyMap = optionMultiKeyMap;
    }

    public Map<String, OrderDic> getOrderSourceMap() {
        return orderSourceMap;
    }

    public void setOrderSourceMap(Map<String, OrderDic> orderSourceMap) {
        this.orderSourceMap = orderSourceMap;
    }

    public Map<String, String> getTransportAreaFullNameMap() {
        return transportAreaFullNameMap;
    }

    public void setTransportAreaFullNameMap(Map<String, String> transportAreaFullNameMap) {
        this.transportAreaFullNameMap = transportAreaFullNameMap;
    }
    
    public Map<String, String> getAllDistributeTypeMap() {
        return allDistributeTypeMap;
    }

    public void setAllDistributeTypeMap(Map<String, String> allDistributeTypeMap) {
        this.allDistributeTypeMap = allDistributeTypeMap;
    }
    
    

    public List<Option> getCancelReasonList() {
        return cancelReasonList;
    }

    public void setCancelReasonList(List<Option> cancelReasonList) {
        this.cancelReasonList = cancelReasonList;
    }

    public List<Option> getDeliveryDateFlagList() {
        return deliveryDateFlagList;
    }

    public void setDeliveryDateFlagList(List<Option> deliveryDateFlagList) {
        this.deliveryDateFlagList = deliveryDateFlagList;
    }

    private void addSubAreaCode(Long transportAreaId, List<String> addressCodes) {

         List<TransportArea> subTransportAreas = transportParentAreaMap.get(transportAreaId);
        if (null == subTransportAreas)
            return;

        for (TransportArea transportArea : subTransportAreas) {
            if (this.MAX_AREA_LEVEL.equals(transportArea.getAreaLevel())) {
                addressCodes.add(transportArea.getAreaCode());
            }
            transportAreaId = transportArea.getId();

            // 递归查询子areaCode
            addSubAreaCode(transportAreaId, addressCodes);
        }
    }

    public Map<String, String> getDistributeTypeLevel2Map() {
        return distributeTypeLevel2Map;
    }

    public void setDistributeTypeLevel2Map(Map<String, String> distributeTypeLevel2Map) {
        this.distributeTypeLevel2Map = distributeTypeLevel2Map;
    }

	/**
	 * @return the orderRefundTypeList
	 */
	public List<OrderMainConst> getOrderRefundTypeList() {
		return orderRefundTypeList;
	}

	/**
	 * @param orderRefundTypeList the orderRefundTypeList to set
	 */
	public void setOrderRefundTypeList(List<OrderMainConst> orderRefundTypeList) {
		this.orderRefundTypeList = orderRefundTypeList;
	}

    
}

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
import com.ibm.oms.dao.constant.PayType;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderMain_;
import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.oms.domain.persist.OrderPayMode;
import com.ibm.oms.domain.persist.OrderPayMode_;
import com.ibm.oms.domain.persist.OrderStatusSyncLog;
import com.ibm.oms.integration.order.constant.R3Constant;
import com.ibm.oms.intf.constant.OrderMainConst;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderPayModeService;
import com.ibm.oms.service.OrderPayService;
import com.ibm.oms.service.OrderStatusSyncLogService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.util.PaymentMethodUtil;
import com.ibm.sc.model.payment.PaymentMethod;
import com.ibm.sc.util.DateUtils;

/**
 * 订单支付/取消同步数据到R3
 * 
 * @author
 * 
 */
//@Component
public class PrePayOrderServiceHandler {
	private static Logger log = LoggerFactory
			.getLogger(PrePayOrderServiceHandler.class);

	@Resource
	private OrderPayService orderPayService;
	
	@Resource
	private OrderPayModeService orderPayModeService;

	@Resource
	private OrderStatusSyncLogService orderStatusSyncLogService;
	
    @Autowired
    private OrderNoService orderNoService;
    
	@Resource
	private OrderMainService orderMainService;
	
	@Autowired
	protected PaymentMethodUtil paymentMethodUtil;
	
	private String preCashier;
	
	private String env;
	
	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	@Autowired
	@Resource(name = "prePayModeMap")
	private Map<String, String> prePayModeMap;
    /**不能算作预收的在线支付payCode**/
    @Resource(name = "prePayExludeCodeMap")
    private Map<String, String> prePayExludeCodeMap;
	
    @Autowired
    OrderSubService orderSubService;
    @Autowired
    PaidOrCancelOrderServiceHandler paidOrCancelOrderServiceHandler;
    @Autowired
    SettleOrRefundOrderServiceHandler settleOrRefundOrderServiceHandler;
    @Autowired
    PrePayOrderCancelServiceHandler prePayOrderCancelServiceHandler;
    /*@PostConstruct
    public void prepairTempLog() {
        //temp 生成order_status_sync_log记录
        //String orderNos = "1407092000170,1407092000142,1407122000425,1407142000463";
        String orderNos = "1029011388,1029011494,1030011613,1030011702,1030011707,1030011709,1030011724,1030011728,1030011736,1030011764,1030011766,1030011767,1030011770,1030011777,1030011778,1030011779,1030011795,1030011796,1030011800,1030011802,1030011803,1030011806,1030011808,1030011825,1030011844,1030011851,1030011854,1030011855,1030011857,1030011866,1030011867,1030011887,1030011891,1030011893,1030011898,1030011903,1030011931,1030011940,1030011955,1030011990,1030011992,1030012009,1030012011,1030012013,1030012014,1030012039,1030012047,1030012049,1030012078,1030012093,1030012106,1030012154,1104016830,1116027540,1119030631,1407111342635,1407161348380,1407191350803,1407231354281,1407241355481,1407241355482,1407241355497,1407241355542,1407241355696,1407251356250,1128042115,1129042363,1407251356641";
        for (String orderNo : orderNos.split(",")) {
            try {
                OrderMain orderMain = orderMainService.getByField(OrderMain_.orderNo, orderNo);
                String orderSubNo = paidOrCancelOrderServiceHandler.getOrderSubNo(orderMain);
                OrderSub orderSub = orderSubService.getByField(OrderSub_.orderNo, orderSubNo.replace("*9", ""));
                orderStatusSyncLogService.saveAndcreate(orderMain, orderSub, CommonConst.OrderStatusSyncLog_SyncScene_PrePay.getCode());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/

	public Map<String, Object> query() {

		//log.info("=================PrePayOrderServiceHandler start=====================");
		if("prd".equals(env)){
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("syncScene", CommonConst.OrderStatusSyncLog_SyncScene_PrePay.getCode());
			params.put("syncFlag", "N");
			
			Map<String, Object> returnMap = new HashMap<String, Object>();
	
			List<OrderStatusSyncLog> logList = orderStatusSyncLogService
					.getLogListByMap(params);
	
			List<Map<String, Object>> dataList = handlerData(logList);
			returnMap.put("data", dataList);
			returnMap.put("updata", logList);
	
			//log.info("=================PrePayOrderServiceHandler end=====================");
	
			return returnMap;
		}else{
			Map<String,Object> returnMap = new HashMap<String, Object>();
			List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
			
			List<OrderStatusSyncLog> logList = new ArrayList<OrderStatusSyncLog>();
			returnMap.put("data", resultList);
			returnMap.put("updata", logList);
			
			return  returnMap;
		}
	}

	/**
	 * 查询同步预付信息
	 * 预付：金额正值
	 * 订单取消:金额负值
	 * @param logList
	 * @return
	 */
	public List<Map<String, Object>> handlerData(
			List<OrderStatusSyncLog> logList) {

		List<Map<String, Object>> prePayList = new ArrayList<Map<String, Object>>();

		Map<String, Object> prePayObj = null;

		String orderNo = null;
		Date date = new Date();
		String payCode = null;
		BigDecimal  payAmount = null;
		
		OrderPayMode payMode = null;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");  
		String dd =format.format(date);  
		Date newdate = null;
		try {
			newdate =format.parse(dd);
		} catch (ParseException e) {
			log.error("format.parse{}", e);
		}
		String orderSubNo = null;
		for (OrderStatusSyncLog row : logList) {
			orderNo = row.getOrderNo();
			OrderMain om = orderMainService.get(OrderMain_.orderNo, orderNo);
			payMode = orderPayModeService.getByField(OrderPayMode_.orderNo, orderNo);
			List<OrderPay> orderPayList = orderPayService.findByField("orderNo", orderNo);
            //国贸云店退货算预退
            boolean isGuoMao = CommonConst.OrderMain_OrderCategory_Return.getCode().equals(om.getOrderCategory()) 
                    && OrderMainConst.OrderMain_MerchantNo_Yundian_GuoMao.getCode().equalsIgnoreCase(om.getMerchantNo());
            
			for (OrderPay orderPay : orderPayList) {
	            boolean isPrePay = false;
				payCode = orderPay.getPayCode();
				boolean isExcludedPayCode = prePayExludeCodeMap.containsValue(payCode);
				if(payMode!=null&& !PayType.COUPON.getId().equals(payCode)&&!PayType.CHANGE_PAID.getId().equals(payCode)){
					isPrePay = prePayModeMap.containsValue(payMode.getPayModeCode()) ;
					isPrePay = isPrePay && !isExcludedPayCode;
				}
				if(prePayModeMap.containsValue(payCode) || isPrePay || (isGuoMao && !isExcludedPayCode)){
					prePayObj = new HashMap<String, Object>();
					OrderMain orderMain = orderMainService.getByField(OrderMain_.orderNo, orderNo);
                    //订单来源
                    String orderSource = orderStatusSyncLogService.getOrderSource(orderMain);
					if(R3Constant.IMMIGRATION.equals(orderMain.getImmigrationVersion())){
						orderSubNo =  orderNo;
					}else{
						orderSubNo = orderNoService.getDefaultOrderSubNoByOrderNo( orderNo);
					}
					prePayObj.put("tc_ufn001", orderSubNo);
					PaymentMethod pm = paymentMethodUtil.getPaymentAllMethodMap().get(payCode);
					if(pm!=null && pm.getName() !=null){
						if(pm.getName().indexOf("支付宝")>-1){
							prePayObj.put("tc_ufn002", "200");
						}else if(pm.getName().indexOf("财付通")>-1){
							prePayObj.put("tc_ufn002", "50100");
						}else{
							prePayObj.put("tc_ufn002", payCode);
						}
					}else{
							prePayObj.put("tc_ufn002", payCode);
					}
					// 预付款数据
					payAmount = orderPay.getPayAmount();
					if(isGuoMao){
					    payAmount = payAmount.multiply(new BigDecimal(-1));
					}
					prePayObj.put("tc_ufn003",payAmount);
					
					Date payTime = null;
					if(orderPay.getPayTime()==null){
						prePayObj.put("tc_ufn004", newdate);
					}else{
						try {
							 String sPayTime =format.format(orderPay.getPayTime());  
							
							 payTime =format.parse(sPayTime);
						} catch (ParseException e) {
							log.error("format.parse{}", e);
						}
						prePayObj.put("tc_ufn004", payTime);
					}
					
					//prePayObj.put("tc_ufn004", orderPay.getPayTime());
					prePayObj.put("tc_ufn005", newdate);
					prePayObj.put("tc_ufn006", DateUtils.format(date, "HH:mm:ss"));
					prePayObj.put("tc_ufn007", "N");
					prePayObj.put("tc_ufn008", orderPay.getCardNo());
					prePayObj.put("tc_ufn009", preCashier);
					// 订单类型
					prePayObj.put("tc_ufn010", "1");// 1预收销售单,2订单取消
					prePayObj.put("tc_ufn011", null);
					prePayObj.put("tc_ufn012", prePayOrderCancelServiceHandler.getUfn012(om));
					prePayObj.put("tc_ufn013", orderSource);
					prePayObj.put("tc_ufn014", null);
					prePayObj.put("tc_ufn015", null);
					prePayObj.put("tc_ufn016", null);
					prePayObj.put("trans_date", settleOrRefundOrderServiceHandler.getTransDate(date));
					prePayObj.put("trans_time", null);
					prePayObj.put("trans_flag", "N");
	
					prePayList.add(prePayObj);
				}
			}
			
			row.setSyncFlag("P");
		}
		orderStatusSyncLogService.update(logList);

		return prePayList;
	}

	public String getPreCashier() {
		return preCashier;
	}

	public void setPreCashier(String preCashier) {
		this.preCashier = preCashier;
	}
}

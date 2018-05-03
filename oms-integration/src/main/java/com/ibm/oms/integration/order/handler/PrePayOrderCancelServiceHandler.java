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
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderPayModeService;
import com.ibm.oms.service.OrderPayService;
import com.ibm.oms.service.OrderStatusSyncLogService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.util.PaymentMethodUtil;
import com.ibm.sc.model.payment.PaymentMethod;
import com.ibm.sc.util.DateUtils;

/**
 * 同步已取消订单的预收款到R3
 * 待同步条件：1，订单被取消， 2，订单被拒收后添加意向单
 * @author
 * 
 */
//@Component
public class PrePayOrderCancelServiceHandler {
	private static Logger log = LoggerFactory
			.getLogger(PrePayOrderCancelServiceHandler.class);
	@Resource
	private OrderPayService orderPayService;
	
	@Resource
	private OrderPayModeService orderPayModeService;
	
    /**
     * prePayMode.myCard=303 prePayMode.onLinePay=830 true
     * prePayMode.coupon=301 false
     **/
	@Resource(name = "prePayModeMap")
	private Map<String, String> prePayModeMap;
	
	/**不能算作预收的在线支付payCode**/
    @Resource(name = "prePayExludeCodeMap")
    private Map<String, String> prePayExludeCodeMap;
    
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

    @Autowired
    PaidOrCancelOrderServiceHandler paidOrCancelOrderServiceHandler;
	
	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	public Map<String, Object> query() {

		//log.info("=================PrePayOrderCancelServiceHandler start=====================");
		if("prd".equals(env)){
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("syncScene", CommonConst.OrderStatusSyncLog_SyncScene_Cancel.getCode());
			params.put("syncFlag", "N");
			
			Map<String, Object> returnMap = new HashMap<String, Object>();
	
			List<OrderStatusSyncLog> logList = orderStatusSyncLogService.getLogListByMap(params);
	
			List<Map<String, Object>> dataList = handlerData(logList);
			returnMap.put("data", dataList);
			returnMap.put("updata", logList);
	
			//log.info("=================PrePayOrderCancelServiceHandler end=====================");
	
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
	 * 订单取消:金额负值
	 * @param logList
	 * @return
	 */
	private List<Map<String, Object>> handlerData(
			List<OrderStatusSyncLog> logList) {

		List<Map<String, Object>> prePayList = new ArrayList<Map<String, Object>>();

		Map<String, Object> prePayObj = null;

		String orderNo = null;
		Date date = new Date();
		String payCode = null;
		BigDecimal minus =new BigDecimal(-1);
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
		for (OrderStatusSyncLog row : logList) {
			orderNo = row.getOrderNo();
			OrderMain om = orderMainService.get(OrderMain_.orderNo, orderNo);
			/** 1货到付款， 830在线支付 **/
			payMode = orderPayModeService.getByField(OrderPayMode_.orderNo, orderNo);
			List<OrderPay> orderPayList = orderPayService.findByField(
					"orderNo", orderNo);
			for (OrderPay orderPay : orderPayList) {
		        boolean isPrePay = false;
				date = new Date();
				payCode = orderPay.getPayCode();
                boolean isExcludedPayCode = prePayExludeCodeMap.containsValue(payCode);
				/**301优惠券 50600现金, 在线支付且支付项非现金，优惠券**/
				if(payMode!=null&& !PayType.COUPON.getId().equals(payCode)&&!PayType.CHANGE_PAID.getId().equals(payCode)){
					isPrePay = prePayModeMap.containsValue(payMode.getPayModeCode()) ;
					isPrePay = isPrePay && !isExcludedPayCode;
				}
				/**支付项包含my卡**/
				if(prePayModeMap.containsValue(payCode) || isPrePay ){
					prePayObj = new HashMap<String, Object>();
					OrderMain orderMain = orderMainService.getByField(OrderMain_.orderNo, orderNo);
                    //订单来源
                    String orderSource = orderStatusSyncLogService.getOrderSource(orderMain);
					String orderSubNo = null;
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
						if("880".equals(payCode)){
							prePayObj.put("tc_ufn002", R3Constant.PAY_CODE_TIANHONGCARD);
						}else{
							prePayObj.put("tc_ufn002", payCode);
						}
					}
					// 预付款数据
					payAmount = orderPay.getPayAmount();
					
					payAmount = payAmount.multiply(minus);
					
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
					prePayObj.put("tc_ufn006",
							DateUtils.format(date, "HH:mm:ss"));
					prePayObj.put("tc_ufn007", "N");
					prePayObj.put("tc_ufn008", orderPay.getCardNo());
					prePayObj.put("tc_ufn009", preCashier);
					// 订单类型
					prePayObj.put("tc_ufn010", "2");// 1预收销售单,2订单取消
					prePayObj.put("tc_ufn011", null);
					prePayObj.put("tc_ufn012", getUfn012(om));
					prePayObj.put("tc_ufn013", orderSource);
					prePayObj.put("tc_ufn014", null);
					prePayObj.put("tc_ufn015", null);
					prePayObj.put("tc_ufn016", null);
					prePayObj.put("trans_date", date);
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

	/**
	 * 
	 * @param om
	 * @return ufn012
	 */
	public String getUfn012(OrderMain om){
//	    if(CommonConst.OrderMain_MerchantType_InvoiceOrg.getCode().equals(om.getMerchantType())){
//	        String merchantNo = om.getMerchantNo();
//	        if(StringUtils.isNotBlank(merchantNo) && merchantNo.matches("[yY].*")){
//	            return merchantNo.substring(1);
//	        }
//	        return merchantNo;
//	    }
	    return "00195";
	}
	
	public String getPreCashier() {
		return preCashier;
	}

	public void setPreCashier(String preCashier) {
		this.preCashier = preCashier;
	}
}

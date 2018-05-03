/**
 * 
 */
package com.ibm.oms.service.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.sc.model.payment.PaymentMode;
import com.ibm.sc.service.payment.PaymentModeService;

/**
 * 支付类型工具类
 * @author xiaonanxiang
 *
 */
public class PaymentModeUtil {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	PaymentModeService paymentModeService;
	
	private List<PaymentMode> paymentModeList;
	
	private Map<String,PaymentMode> paymentModeMap = new HashMap<String,PaymentMode>(); 
	
	@PostConstruct
	public void initialize() {
		logger.info("PaymentModeUtil --> initialize --> start");
		paymentModeList = paymentModeService.getAll();
		
		for(PaymentMode paymentMode:paymentModeList)
		{
			paymentModeMap.put(paymentMode.getCode(), paymentMode);
		}
		
		logger.info("PaymentModeUtil --> initialize  -->  end");
	}

	public List<PaymentMode> getPaymentModeList() {
		return paymentModeList;
	}

	public void setPaymentModeList(List<PaymentMode> paymentModeList) {
		this.paymentModeList = paymentModeList;
	}

	public Map<String, PaymentMode> getPaymentModeMap() {
		return paymentModeMap;
	}

	public void setPaymentModeMap(Map<String, PaymentMode> paymentModeMap) {
		this.paymentModeMap = paymentModeMap;
	}

}

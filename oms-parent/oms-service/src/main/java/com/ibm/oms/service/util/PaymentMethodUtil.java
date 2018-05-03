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

import com.ibm.sc.model.payment.PaymentMethod;
import com.ibm.sc.model.payment.PaymentMethod_;
import com.ibm.sc.model.payment.PaymentMode;
import com.ibm.sc.service.payment.PaymentMethodService;

/**
 * 支付方式工具类
 * @author xiaonanxiang
 *
 */
public class PaymentMethodUtil {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	PaymentMethodService paymentMethodService;
	@Autowired
	PaymentModeUtil paymentModeUtil;
	private List<PaymentMethod> paymentMethodList;
	
	private Map<String,PaymentMethod> paymentMethodMap = new HashMap<String,PaymentMethod>(); 
	
	private Map<String,PaymentMethod> paymentAllMethodMap = new HashMap<String,PaymentMethod>(); 
	
	@PostConstruct
	public void initialize() {
		logger.info("PaymentMethodUtil --> initialize --> start");
		paymentMethodList = paymentMethodService.findByField(PaymentMethod_.bankName, "1");
		
		for(PaymentMethod paymentMethod:paymentMethodList)
		{
			paymentMethodMap.put(String.valueOf(paymentMethod.getId()), paymentMethod);
		}
		
		List<PaymentMethod> pmethodList = paymentMethodService.getAll();
		
		for(PaymentMethod paymentMethod:pmethodList)
		{
			paymentAllMethodMap.put(String.valueOf(paymentMethod.getId()), paymentMethod);
		}
		
		logger.info("PaymentMethodUtil --> initialize  -->  end");
	}

	public List<PaymentMethod> getPaymentMethodList() {
		return paymentMethodList;
	}

	public void setPaymentMethodList(List<PaymentMethod> paymentMethodList) {
		this.paymentMethodList = paymentMethodList;
	}

	public Map<String, PaymentMethod> getPaymentMethodMap() {
		return paymentMethodMap;
	}

	public void setPaymentMethodMap(Map<String, PaymentMethod> paymentMethodMap) {
		this.paymentMethodMap = paymentMethodMap;
	}
	
    /**
     * 获取退款方式列表(PM_PAYMENT_MODE表 + PM_PAYMENT_METHOD表)
     * @return
     */
    public Map<String,PaymentMethod> getRefundMethodMap()
    {
        Map<String,PaymentMethod> map = new HashMap<String,PaymentMethod>();
        
        // 添加PM_PAYMENT_MODE中的所有退款方式
        Map<String,PaymentMode> paymentModeMap = new HashMap<String,PaymentMode>();
        paymentModeMap = paymentModeUtil.getPaymentModeMap();
        
        PaymentMethod paymentMethod;
        if(!paymentModeMap.isEmpty())
        {
            for(String key:paymentModeMap.keySet())
            {
                paymentMethod = new PaymentMethod();
                paymentMethod.setId(Long.valueOf(key));
                paymentMethod.setName(paymentModeMap.get(key).getName());
                map.put(key, paymentMethod);
            }
        }
        
        // 添加PM_PAYMENT_METHOD中的所有退款方式
        map.putAll(getPaymentMethodMap());
        return map;
    }

	public Map<String, PaymentMethod> getPaymentAllMethodMap() {
		return paymentAllMethodMap;
	}

	public void setPaymentAllMethodMap(
			Map<String, PaymentMethod> paymentAllMethodMap) {
		this.paymentAllMethodMap = paymentAllMethodMap;
	}
    
    
	
}

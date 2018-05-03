package com.ibm.oms.service.business.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.clerk.dto.input.CalculateBonusInputDto;
import com.ibm.clerk.intf.ClerkBonusHsService;
import com.ibm.common.dto.DefaultOutputDto;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.service.business.OrderPerformanceService;

@Service("orderPerformanceService")
public class OrderPerformanceServiceImpl implements OrderPerformanceService {

	@Autowired
	ClerkBonusHsService clerkBonusHsService;
	/* 获取提成
	 * @see com.ibm.oms.service.business.OrderPerformanceService#getOrderPerformance(com.ibm.oms.domain.persist.OrderMain)
	 */
	@Override
	public BigDecimal getOrderPerformance(OrderMain om) {
		CalculateBonusInputDto input = new CalculateBonusInputDto();
		input.setBilltype(String.valueOf(om.getBillType()));
		input.setClerkCode(om.getSalesclerkNo());
		input.setOrderCode(om.getOrderNo());
		input.setOrderType(om.getOrderType());
		input.setSalesAmount(om.getTotalPayAmount());
		input.setMemberCode(om.getMemberNo());
		input.setBonusDate(om.getDateUpdated());
		DefaultOutputDto output=clerkBonusHsService.calculateBonus(input);
		BigDecimal bonus =new BigDecimal(0);
		if("成功".equals(output.getResponse_msg())){
			Object data =  output.getResponse_data();
           Map map;
		try {
			map = objectToMap(data);

			 bonus = (BigDecimal) map.get("bonus");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return bonus;
	}
	 public static Map<String, Object> objectToMap(Object obj) throws Exception {    
	        if(obj == null){    
	            return null;    
	        }   
	        //获取关联的所有类，本类以及所有父类
	        boolean ret = true;
	        Class oo = obj.getClass();
	        List<Class> clazzs = new ArrayList<Class>();
	        while(ret){
	            clazzs.add(oo);
	            oo = oo.getSuperclass();
	            if(oo == null || oo == Object.class)break;
	        }
	         
	        Map<String, Object> map = new HashMap<String, Object>();    
	    
	       for(int i=0;i<clazzs.size();i++){
	           Field[] declaredFields = clazzs.get(i).getDeclaredFields();    
	           for (Field field : declaredFields) {  
	               int mod = field.getModifiers();  
	               //过滤 static 和 final 类型
	               if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){    
	                   continue;    
	               }    
	               field.setAccessible(true);  
	               map.put(field.getName(), field.get(obj));  
	           }
	       }
	    
	        return map;  
	    } 
}

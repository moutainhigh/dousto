package com.ibm.oms.service.util;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import com.alibaba.fastjson.JSON;
import com.ibm.oms.service.pay.dto.PayOnLineADto;
public class MiyaPayUtil {
	public static String createSign(String key,SortedMap<Object,Object> parameters){ 
        StringBuffer sb = new StringBuffer();  
        Set es = parameters.entrySet();//所有参与传参的参数按照accsii排序（升序）  
        Iterator it = es.iterator();  
        while(it.hasNext()) {  
            Map.Entry entry = (Map.Entry)it.next();  
            String k = (String)entry.getKey();  
            Object v = entry.getValue();  
            if(null != v && !"".equals(v)&& !"sign".equals(k) && !"key".equals(k)) {  
                sb.append(k + "=" + v + "&");   
            }  
        }  
        sb.append("KEY=" + key);  
        String sign = MD5Util.MD5Encode("&"+sb.toString(), "UTF-8").toUpperCase();  
        return sign;  
    }  
    /**  
     * Map转换成Xml  
     * @param map  
     * @return  
     */  
    public static String map2Xmlstring(Map<String,Object> map){  
        StringBuffer sb = new StringBuffer("");  
        sb.append("<xml>");  
        Set<String> set = map.keySet();  
        for(Iterator<String> it=set.iterator(); it.hasNext();){  
            String key = it.next();  
            Object value = map.get(key);  
            sb.append("<").append(key).append(">");  
            sb.append(value);  
            sb.append("</").append(key).append(">");  
        }  
        sb.append("</xml>");  
        return sb.toString();  
    }  
    
	/* 测试 */
	public static void main(String[] args) {
		// 发送的数据内容
//		String jsonObj = "{\"A1\":\"A\", \"A2\":\"miya\",\"A3\":F004,\"A4\":001,\"A5\":001,\"A6\":G,\"A7\":1.5,\"A8\":,\"A11\":H5,\"A12\":1,\"B1\":20180314,\"B3\":顾客打印小票,\"B4\":20.00,\"B5\":20,\"B11\":20,\"B12\":20,\"B13\":20,\"B14\":2102}";
		String jsonObj = "{\"A1\":\"A\", \"A2\":\"miya\",\"A3\":\"miya\"}";
		//		String[] resp2 = e3Request(data2);
		PayOnLineADto parseObj = JSON.parseObject(jsonObj, PayOnLineADto.class);
		SortedMap<Object,Object> tlvmap = new TreeMap<Object,Object>();  
		 tlvmap.put("A1", parseObj.getA1());//接口类型  默认 A-交易
		 tlvmap.put("A2", parseObj.getA2());//商户号 米雅提供的商户号 
		 tlvmap.put("A3", parseObj.getA3());//门店账号 通常为商户门店号
		 tlvmap.put("A4", parseObj.getA4());//设备号 通常为商户门店 pos 机编号
		 tlvmap.put("A5", parseObj.getA5());//收银编号 通常为商户门店收银员编号 
		 tlvmap.put("A6", parseObj.getA6());//操作类型  默认 F-预下单 
		 tlvmap.put("A7", parseObj.getA7());//版本号 默认 1.5
		 tlvmap.put("A10",parseObj.getA10());//发票号
		 tlvmap.put("A12",parseObj.getA12());//支付平台类型 1-微信  3-支付宝
        
		 String javaBeanToXml = JaxbUtil.convertToXml(parseObj);
		 System.out.println(javaBeanToXml);
//        String upperCase = MD5Util.MD5Encode("&A1=A&A11=B&A2=miya&A3=001&A4=YWTEST001&A5=001&A6=A&A7=1.5&B1=18020917060019749340010701&B2=134632140529029932&B3=TMZF&B4=1&KEY=nbz9ww27sx4ou6dkr61mf63tth3s6e2d", "UTF-8").toUpperCase();
//        System.out.println("createSign:"+upperCase);
	}

}

package com.ibm.oms.service.util;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
public class MiyaPayUtil {
	public static String newCreateSign(String signkey,SortedMap<Object,Object> params) {
			String[] keyArray = params.keySet().toArray(new String[0]);
			StringBuffer targetSign = new StringBuffer();
			targetSign.append("&");
			Arrays.sort(keyArray);
			for (int i = 0; i < keyArray.length; ++i) {
				String key = keyArray[i];
				if(null!=params.get(key)&&!"".equals(params.get(key))){
					targetSign.append(key);
					targetSign.append("=");
					targetSign.append(params.get(key));
				    targetSign.append("&");
				}
			}
			targetSign.append("KEY=" + signkey);
			try {
				byte[] bytes = targetSign.toString().getBytes("utf-8");
				return MD5Util.MD5(bytes);
			} catch (Exception ex) {
			}
			return "";
		}
	/**
	 * @param key
	 * @param parameters
	 * @return
	 */
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
		SortedMap<Object,Object> tlvmap = new TreeMap<Object,Object>(); 
		 tlvmap.put("A1", "21");//接口类型  默认 A-交易
		 tlvmap.put("A2", "22");//商户号 米雅提供的商户号 
		 tlvmap.put("A10","");//发票号
		 tlvmap.put("A11","2");//重码分类 默认 A
		 
		 tlvmap.put("B1", "3");//商户订单号 商户侧生成的订单号，不可重复
		 tlvmap.put("B3", "");//商户订单号 商户侧生成的订单号，不可重复
		 tlvmap.put("B2", "33");//商户订单号 商户侧生成的订单号，不可重复
		 newCreateSign("nbz9ww27sx4ou6dkr61mf63tth3s6e2d",tlvmap);
	}

}

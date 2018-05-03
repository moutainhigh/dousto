package com.ibm.oms.service.util;



import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSON;
import com.ibm.oms.service.pay.dto.MiyaPayOnLineOutputDto;
import com.ibm.oms.service.pay.dto.PayOnLineGoodsDto;
import net.sf.json.JSONObject;
/**
 * Jaxb2工具类
 * 
 */
public class JaxbUtil {
	private static String url = "http://120.26.133.63/miya/miyapay_response.action";
	private static String xmlPrefix = "<?xml version = '1.0' encoding = 'UTF-8' ?><xml><request>";
	private static String xmlSuffix = "</data></xml>";
	/**
	 * JavaBean转换成xml 默认编码UTF-8
	 * 
	 * @param obj
	 * @param writer
	 * @return
	 */
	public static String convertToXml(Object obj) {
		return convertToXml(obj, "UTF-8");
	}
	/**
	 * JavaBean转换成xml
	 * 
	 * @param obj
	 * @param encoding
	 * @return
	 */
	public static String convertToXml(Object obj, String encoding) {
		String result = null;
		try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
			StringWriter writer = new StringWriter();
			marshaller.marshal(obj, writer);
			result = writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * xml转换成JavaBean
	 * 
	 * @param xml
	 * @param c
	 * @return
	 */
	public static <T> T converyToJavaBean(String xml, Class<T> c) {
		T t = null;
		try {
			JAXBContext context = JAXBContext.newInstance(c);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			t = (T) unmarshaller.unmarshal(new StringReader(xml));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return t;
	}
	/**
	 * JavaBean转换成xml
	 * 
	 * @param obj
	 * @param encoding
	 * @return
	 */
	public static String javaBeanToXml(Object obj) {
		String result = null;
		try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			StringWriter writer = new StringWriter();
			marshaller.marshal(obj, writer);
			result = writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public static String createHttpClient(String urlStr) throws  IOException {
		String returnStr = null;
	    // 创建客户端对象
		CloseableHttpClient client = HttpClients.createDefault();
		// 创建post请求 
		HttpPost post = new HttpPost(url);
		post.addHeader("Content-type","application/xhtml+xml; charset=utf-8");
		post.setHeader("Accept", "application/xhtml+xml");
		post.setEntity(new StringEntity(urlStr.toString(), Charset.forName("UTF-8")));
		// 使用客户端的对象发送post请求
		CloseableHttpResponse response = client.execute(post);
		// 获取到响应的内容，状态行 200 ok
		StatusLine s = response.getStatusLine();
		// 获取到的响应内容
		HttpEntity entity2 = response.getEntity();   
		// 封装返回数据
		returnStr = EntityUtils.toString(entity2,"UTF-8");
		// 选择关闭
		response.close();
		client.close();
		return returnStr;
    }     
	/**
	 * List 对象转换JSON
	 * @param obj
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String objConverJson(List<PayOnLineGoodsDto> obj) throws ClientProtocolException, IOException {
		String returnStr = null;
		if(obj.size()>0){
			returnStr = JSON.toJSONString(obj);
		}
		return returnStr;
	}
	/**  
    * Map转换成Xml  
    * @param map  
    * @return  
	 * @throws UnsupportedEncodingException 
    */  
   public static String mapToXml(SortedMap<Object,Object> map) throws UnsupportedEncodingException{  
       StringBuffer sb = new StringBuffer("");  
       sb.append(xmlPrefix);  
       Set es = map.keySet();
       Iterator it = es.iterator();
       int bsun = 0;
       while(it.hasNext()) {  
    	   Object key = it.next();
           Object val = map.get(key);  
           if(null != val && !"".equals(val)) { 
        	   String fir = ((String) key).substring(0,1);//获取字符串的第一个字符
        	   if("A".equals(fir)){
        		   sb.append("<").append(key).append(">");  
    	           sb.append(val);  
    	           sb.append("</").append(key).append(">"); 
        	   }else{
        		   if(bsun == 0){
        			   sb.append("</request><data>");  
        		   }
        		   sb.append("<").append(key).append(">");  
    	           sb.append(val);  
    	           sb.append("</").append(key).append(">"); 
    	           bsun ++;
        	   }
           }
       }  
       sb.append(xmlSuffix);  
       String str = new String(sb.toString().getBytes("UTF-8"),"UTF-8");  
       return str.toString();  
   }  
   /** 
    * 将字符编码转换成UTF-8码 
    */  
   public static String toUTF_8(String str) throws UnsupportedEncodingException{   
	   return changeCharset(str, "UTF-8");  
   }
   /** 
    * 字符串编码转换的实现方法 
    * @param str  待转换编码的字符串 
    * @param newCharset 目标编码 
    * @return 
    * @throws UnsupportedEncodingException 
    */  
   public static String changeCharset(String str, String newCharset) throws UnsupportedEncodingException {  
	    if (str != null) {  
		     //用默认字符编码解码字符串。  
		     byte[] bs = str.getBytes();  
		     //用新的字符编码生成字符串  
		     return new String(bs, newCharset);  
	    }  
	    return null;  
   }  
   /** 
    * 将字符编码转换成UTF-8码 
    */  
   public static String payResponse(String respXml) throws Exception{
	   Map<String,Object> mapResp = new HashMap<String,Object>();
	   Map<String,String> map = new HashMap<String,String>();
	   JSONObject jsonObject = new JSONObject();
	   if(null != respXml){
		   MiyaPayOnLineOutputDto beans = CommonUtilService.converyToJavaBean(respXml, MiyaPayOnLineOutputDto.class);
			   map.put("orderNo", beans.getC5());//支付订单
			   map.put("status", beans.getC2());//支付状态  //订单状态 PAYSUCCESS-交易成功，PAYWAIT-待确认付款，PAYFAIL-交易 失败，PAYCANCEL-已撤销或退款 
			   map.put("message", beans.getC4());//支付描述
			   if(null != beans.getC26()&&!"".equals(beans.getC26())){
				   map.put("paymentCode", beans.getC26());//支付返回串
			   }
			   map.put("payType", beans.getC8());//支付方式 1微信/3支付宝
			   map.put("payName", beans.getC24());//支付名称
			   if(null != beans.getC12()&&!"".equals(beans.getC12())){
				   map.put("createDate", beans.getC12());//下单日期
			   }else{
				   map.put("createDate", beans.getC13());//下单日期
			   }
			   map.put("payAmount", beans.getC7());//支付金额
			   if(null != beans.getC9()&&!"".equals(beans.getC9())){
				   map.put("appid", beans.getC9());    //微信服务商 appid 
			   }
			   if(null != beans.getC10()&&!"".equals(beans.getC10())){
				   map.put("payParames", beans.getC10());//微信公众号支付所需 参数{"status":"00","trade_no":"wx20170920160038f91c2855f70898884919","wxsend":{"appId":"wx273772ce 26499402","timeStamp":"1505894451225","signType":"MD5","package":"prepay_id=wx20170920160038f91c2855f7 0898884919","nonceStr":"JZQYA5xxMRsUs0TWpkhBNOaunnua6z6e","paySign":"C0B36C306D18417B6B99E9113 CDCC420"},"payserial":"20170517102930121217"}
			   }
			   if("PAYFAIL".equals(beans.getC2())||"FAIL".equals(beans.getC1())){ 
				   mapResp.put("response_code", "E000001");
				   mapResp.put("response_msg", beans.getC4());
			   }else{
				   mapResp.put("response_code", "S000000");
			   }
			   mapResp.put("response_time", DateUtil.getStringDate());
			   mapResp.put("response_data", map);
	   }else{
		   mapResp.put("response_code", "E000001");
		   mapResp.put("response_data", null);
		   mapResp.put("response_msg", "订单号/支付渠道不能为空!");
		   mapResp.put("response_time", DateUtil.getStringDate());
	   }
	   jsonObject = JSONObject.fromObject(mapResp);
	   return jsonObject.toString();    
   }
   public static void main(String[] args) throws UnsupportedEncodingException {
		SortedMap<Object,Object> tlvmap = new TreeMap<Object,Object>();  
		 tlvmap.put("A1", "11");//接口类型  默认 A-交易
		 tlvmap.put("A2", "22");//商户号 米雅提供的商户号 
		 tlvmap.put("A3", "33");//门店账号 通常为商户门店号
		 tlvmap.put("A4", "44");//设备号 通常为商户门店 pos 机编号
		 tlvmap.put("A5", "55");//收银编号 通常为商户门店收银员编号 
		 tlvmap.put("A6", "66");//操作类型  默认 B-支付
		 tlvmap.put("A7", "77");//版本号 默认 1.5 
		 tlvmap.put("B1", "78");//商户订单号 商户侧生成的订单号，不可重复
		 tlvmap.put("B2", "79");//商户订单号 商户侧生成的订单号，不可重复
		 tlvmap.put("B3", "74");//商户订单号 商户侧生成的订单号，不可重复
		 
		 mapToXml(tlvmap);
		 String utf_8 = toUTF_8("中文测试");
		 System.out.println(utf_8);
//		 String str = "adadsauo";//定义一个字符串
//		 String fir = str.substring(0,1);//获取字符串的第一个字符
//		 System.out.println(fir);
}

}
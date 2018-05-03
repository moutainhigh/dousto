package com.ibm.oms.service.util;



import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.ibm.oms.intf.intf.OrderMsg;

/**
 * Jaxb2工具类
 * 
 */
public class JaxbUtil {
	private static String url = "http://120.26.133.63/miya/miyapay_response.action";
	private static String xmlPrefix = "<?xml version = '1.0' encoding = 'UTF-8' ?><xml>";
	private static String xmlSuffix = "</xml>";
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

	public static void main(String[] args) {
		OrderMsg  s= new OrderMsg();
		s.setChannelId("23");
		s.setArriveDays("23");
		s.setFailContent("sdfsd");
		System.out.println(javaBeanToXml(s));
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
	/**
	 * 定制组装xml数据
	 * @param parseObj
	 * @param parseObjItem
	 * @return
	 */
	public static String getAssemblyXml(Object parseObj,Object parseObjItem) {
		String result = null;
		try {
			String toXml = JaxbUtil.javaBeanToXml(parseObj);  
			 String toXmlItem = JaxbUtil.javaBeanToXml(parseObjItem);
			 result = xmlPrefix+toXml+toXmlItem+xmlSuffix; 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public static String createHttpClient(String urlStr) throws ClientProtocolException, IOException {
		// 返回的结果
		String[] body = new String[2];
	    // 创建客户端对象
		CloseableHttpClient client = HttpClients.createDefault();
	    // 创建请求内容
		StringEntity entity = new StringEntity(urlStr.toString(), ContentType.APPLICATION_XML);
		// 创建post请求 
		HttpPost post = new HttpPost(url);
		post.setEntity(entity);
		// 使用客户端的对象发送post请求
		CloseableHttpResponse response = client.execute(post);
		// 获取到响应的内容，状态行 200 ok
		StatusLine s = response.getStatusLine();
		// 获取到的响应内容
		HttpEntity entity2 = response.getEntity(); 
		// 封装返回数据
		int statusCode = s.getStatusCode();
		String returnStr = EntityUtils.toString(entity2,"UTF-8");
		// 选择关闭
		response.close();
		client.close();
		return returnStr;
	}
}
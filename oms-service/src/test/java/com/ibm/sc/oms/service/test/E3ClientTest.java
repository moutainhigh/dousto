package com.ibm.sc.oms.service.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.intf.intf.E3ClientDTO;
import com.ibm.oms.intf.intf.E3ResultDTO;

/**
 * Description: //模块目的、功能描述
 * 
 * @author YanYanZhang Date: 2018年4月24日
 */
public class E3ClientTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(E3ClientTest.class);
	private static final String SIGN_FORMAT = "key=%s&requestTime=%s&secret=%s&version=%s&serviceType=%s&data=%s";

	private String e3Key = "test";
	private String e3Secret = "1a2b3c4d5e6f7g8h9i10j11k12l";

	private String e3StockUrl = "http://121.199.178.7/e3test/webopm/web/?app_act=api/wms_api/wms&app_mode=func";
	private String e3StockVersion = "2.0";
	
	private String e3OrderUrl = "http://121.199.178.7/e3test/webopm/web/?app_act=api/ec&app_mode=func";
	private String e3OrderVersion = "0.1";
	
	
	

	private ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void lockStock() throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("wmsBillCode", "10000001");
		data.put("warehouseCode", "C000");
		data.put("billDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		data.put("opType", "SD");// sd

		Map<String, Object> products = new HashMap<String, Object>();
		List<Map<String, Object>> product = new ArrayList<Map<String, Object>>();
		Map<String, Object> prod = new HashMap<String, Object>();
		prod.put("skuCode", "FAPIAO000000");
		prod.put("normalQuantity", 1);
		product.add(prod);
		products.put("product", product);

		data.put("products", products);

		E3ClientDTO e3ClientDTO = new E3ClientDTO();
		e3ClientDTO.setKey(e3Key);
		e3ClientDTO.setRequestTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		e3ClientDTO.setSecret(e3Secret);
		e3ClientDTO.setServiceType("SendSdd");
		e3ClientDTO.setVersion(e3StockVersion);
		e3ClientDTO.setData(data);
		e3ClientDTO.setUrl(e3StockUrl);
		e3ClientDTO.setSign(this.getSign(e3ClientDTO));

		this.sendToE3(e3ClientDTO);
	}
	
	@Test
	public void UnlockStock() throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("opType", "SF");
		data.put("originalCode", "10000001");
		data.put("billDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		data.put("warehouseCode", "C000");// sd

		Map<String, Object> products = new HashMap<String, Object>();
		List<Map<String, Object>> product = new ArrayList<Map<String, Object>>();
		Map<String, Object> prod = new HashMap<String, Object>();
		prod.put("skuCode", "FAPIAO000000");
		prod.put("normalQuantity", 1);
		product.add(prod);
		products.put("product", product);

		data.put("products", products);

		E3ClientDTO e3ClientDTO = new E3ClientDTO();
		e3ClientDTO.setKey(e3Key);
		e3ClientDTO.setRequestTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		e3ClientDTO.setSecret(e3Secret);
		e3ClientDTO.setServiceType("SendSdd");
		e3ClientDTO.setVersion(e3StockVersion);
		e3ClientDTO.setData(data);
		e3ClientDTO.setUrl(e3StockUrl);
		e3ClientDTO.setSign(this.getSign(e3ClientDTO));

		this.sendToE3(e3ClientDTO);
	}
	
	
	
	
	
	
	
	//order.cancel.delivery
	@Test
	public void cancelDelivery() throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("deal_code", "10000001");
		data.put("ly_type", "houtai");
		
		E3ClientDTO e3ClientDTO = new E3ClientDTO();
		e3ClientDTO.setKey(e3Key);
		e3ClientDTO.setRequestTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
//		e3ClientDTO.setRequestTime("20180428142849");
		e3ClientDTO.setSecret(e3Secret);
		e3ClientDTO.setServiceType("order.cancel.delivery");
		e3ClientDTO.setVersion(e3OrderVersion);
		e3ClientDTO.setData(data);
		e3ClientDTO.setUrl(e3OrderUrl);
		e3ClientDTO.setSign(this.getSign(e3ClientDTO));
		this.sendToE3(e3ClientDTO);
	}

//	public void createE3Order(OrderMain orderMain) throws Exception {
//		OrderSub orderSub = orderMain.getOrderSubs().get(0);
//		Map<String, Object> data = new HashMap<String, Object>();
//		data.put("deal_code", orderMain.getOrderNo());
//		data.put("sd_code", orderMain.getMerchantNo());// 店铺编码
//		data.put("pay_code ", "");// 待确认支付方式编码
//		data.put("user_name", orderMain.getMemberNo());
//		data.put("receiver_name", orderSub.getUserName());
//		data.put("receiver_country", "中国");
//		data.put("receiver_province", orderSub.getDeliveredProvince());
//		data.put("receiver_city", orderSub.getDeliveredCity());
//		data.put("receiver_district", orderSub.getDeliveredCounty());
//		data.put("receiver_address", orderSub.getAddressDetail());
//		data.put("receiver_tel", orderSub.getPhoneNum());
//		data.put("receiver_mobile", orderSub.getMobPhoneNum());
//		data.put("pay_status", 1);
//		data.put("shipping_fee", orderSub.getTransportFee());
//		data.put("order_amount", orderMain.getTotalProductPrice());
//		data.put("payment", orderMain.getTotalPayAmount());
//		data.put("source", "from");// 待确认传什么
//
//		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
//		for (OrderItem orderItem : orderSub.getOrderItems()) {
//			Map<String, Object> item = new HashMap<String, Object>();
//			item.put("deal_code", orderItem.getOrderNo());
//			item.put("sku", orderItem.getSkuNo());
//			item.put("goods_sn", orderItem.getCommodityCode());
//			item.put("goods_name", orderItem.getCommodityName());
//			item.put("shop_price", orderItem.getNormalPrice());
//			item.put("goods_price", orderItem.getUnitDeductedPrice());// 折后单价
//			item.put("goods_number", orderItem.getSaleNum());
//
//			items.add(item);
//		}
//
//		data.put("items", items);
//
//		E3ClientDTO e3ClientDTO = new E3ClientDTO();
//		e3ClientDTO.setKey(e3Key);
//		e3ClientDTO.setRequestTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
//		e3ClientDTO.setSecret(e3Secret);
//		e3ClientDTO.setServiceType("order.add");
//		e3ClientDTO.setVersion(e3OrderVersion);
//		e3ClientDTO.setData(data);
//		e3ClientDTO.setUrl(e3OrderUrl);
//		e3ClientDTO.setSign(this.getSign(e3ClientDTO));
//		this.sendToE3(e3ClientDTO);
//	}
	//ORDER.ADD
	@Test
	public void createE3OrderT() throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("deal_code", "10000004");
		//data.put("sd_code", "TM001");// 店铺编码
		data.put("sd_code", "QZY");
		data.put("pay_code", "12222");// 待确认支付方式编码
		data.put("user_name", "member");
		data.put("receiver_name", "王大");
		data.put("receiver_country", "中国");
		data.put("receiver_province", "浙江省");
		data.put("receiver_city", "温州市");
		data.put("receiver_district", "瑞安");
		data.put("receiver_address", "塘下镇罗山大道");
		data.put("receiver_tel", "40082088888");
		data.put("receiver_mobile", "18230089868");
		data.put("pay_status", "1");// 0未付款 1已付款
		data.put("shipping_fee", "9.0");
		data.put("order_amount", "80");
		data.put("payment", "89");
		data.put("source", "zhongtai");// 待确认传什么
		//data.put("lylx", "zhongtai");// 待确认传什么
		data.put("lylx", "0");
		data.put("receiver_addr", "文档里都没这个");// 待确认传什么

		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		Map<String, Object> item = new HashMap<String, Object>();
		item.put("deal_code", "10000001");
		item.put("sku", "FAPIAO000000");
		item.put("goods_sn", "FAPIAO000000");
		item.put("goods_name", "漂亮的女鞋");
		item.put("shop_price", "100");
		item.put("goods_price", "80");// 折后单价
		item.put("goods_number", "1");

		items.add(item);

		data.put("items", items);

		E3ClientDTO e3ClientDTO = new E3ClientDTO();
		e3ClientDTO.setKey(e3Key);
		e3ClientDTO.setRequestTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
//		e3ClientDTO.setRequestTime("20180428142849");
		e3ClientDTO.setSecret(e3Secret);
		e3ClientDTO.setServiceType("order.add");
		e3ClientDTO.setVersion(e3OrderVersion);
		e3ClientDTO.setData(data);
		e3ClientDTO.setUrl(e3OrderUrl);
		e3ClientDTO.setSign(this.getSign(e3ClientDTO));
		
		this.sendToE3(e3ClientDTO);
	}
	
	/**
	 * 进行签名加密 Description:
	 * 
	 * @param e3ClientDTO
	 * @return
	 * @throws JsonProcessingException
	 * @throws NoSuchAlgorithmException
	 * @throws Exception
	 */
	private String getSign(E3ClientDTO e3ClientDTO) throws JsonProcessingException, NoSuchAlgorithmException, Exception {
		String signParam = String.format(SIGN_FORMAT, e3ClientDTO.getKey(), e3ClientDTO.getRequestTime(), e3ClientDTO.getSecret(),
				e3ClientDTO.getVersion(), e3ClientDTO.getServiceType(), objectMapper.writeValueAsString(e3ClientDTO.getData()));
		LOGGER.info("Sign param:{}", signParam);
		return md5Encryption(signParam);
	}

	private E3ResultDTO sendToE3(E3ClientDTO e3ClientDTO) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		E3ResultDTO e3ResultDTO = new E3ResultDTO();
		
		try {
			HttpPost httpPost = new HttpPost(e3ClientDTO.getUrl());
			
			LOGGER.info("send to e3 ClientDto:::{} ", objectMapper.writeValueAsString(e3ClientDTO));
			
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("key", e3ClientDTO.getKey()));
			nvps.add(new BasicNameValuePair("requestTime", e3ClientDTO.getRequestTime()));
			nvps.add(new BasicNameValuePair("secret", e3ClientDTO.getSecret()));
			nvps.add(new BasicNameValuePair("version", e3ClientDTO.getVersion()));
			nvps.add(new BasicNameValuePair("serviceType", e3ClientDTO.getServiceType()));
			nvps.add(new BasicNameValuePair("data", objectMapper.writeValueAsString(e3ClientDTO.getData())));
			nvps.add(new BasicNameValuePair("format", "json"));
			nvps.add(new BasicNameValuePair("sign", e3ClientDTO.getSign()));
			
			LOGGER.info("Send to E3 param:{}", objectMapper.writeValueAsString(nvps));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
			
			CloseableHttpResponse response = httpclient.execute(httpPost);
			
			try {
				LOGGER.info("E3 {} response status:{}", e3ClientDTO.getServiceType(), response.getStatusLine());
				HttpEntity entity = response.getEntity();
				
				String message = EntityUtils.toString(entity, "utf-8");
				
				e3ResultDTO = objectMapper.readValue(message, E3ResultDTO.class);
				
				LOGGER.info("E3 {} response message:{}", e3ClientDTO.getServiceType(), objectMapper.writeValueAsString(e3ResultDTO));
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
		
		return e3ResultDTO;
	}

	/**
	 * MD5加密 Description:
	 * 
	 * @param params
	 * @return
	 */
	private String md5Encryption(String params) {
		String result = new String();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(params.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}

			result = buf.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}

}

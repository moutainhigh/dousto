package com.ibm.oms.service.impl;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.intf.intf.E3ClientDTO;
import com.ibm.oms.intf.intf.E3ResultDTO;
import com.ibm.oms.service.E3OrderService;

/**
 * Description: //模块目的、功能描述  
 * @author YanYanZhang
 * Date:   2018年4月28日 
 */
public class E3OrderServiceImpl implements E3OrderService{
	private static final Logger LOGGER = LoggerFactory.getLogger(E3OrderService.class);

	@Value("#{settings['e3.sign.format']}")
	private String SIGN_FORMAT;
	@Value("#{settings['e3.utf']}")
	private String UTF8;
	@Value("#{settings['e3.e3Key']}")
	private String e3Key;
	@Value("#{settings['e3.e3Secret']}")
	private String e3Secret;
	@Value("#{settings['e3.e3StockUrl']}")
	private String e3StockUrl;
	@Value("#{settings['e3.e3StockVersion']}")
	private String e3StockVersion;
	@Value("#{settings['e3.e3OrderUrl']}")
	private String e3OrderUrl;
	@Value("#{settings['e3.e3OrderVersion']}")
	private String e3OrderVersion;
	
	private ObjectMapper objectMapper = new ObjectMapper();


	@Override
	public E3ResultDTO lockStock(OrderMain orderMain) {
		try {
			return this.operateE3Stock(orderMain, "SD");
		} catch (Exception e) {
			e.printStackTrace();
			return this.getErrorE3Result();
		}	}

	@Override
	public E3ResultDTO resumeStock(OrderMain orderMain) {
		try {
			return this.operateE3Stock(orderMain, "SF");
		} catch (Exception e) {
			e.printStackTrace();
			return this.getErrorE3Result();
		}
	}

	@Override
	public E3ResultDTO delivery(OrderMain orderMain) {
		OrderSub orderSub = orderMain.getOrderSubs().get(0);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("deal_code", orderMain.getOrderNo());
		data.put("sd_code", orderMain.getMerchantNo());// 店铺编码
		data.put("pay_code ", "1111");// 待确认支付方式编码
		data.put("user_name", orderMain.getMemberNo());
		data.put("receiver_name", orderSub.getUserName());
		data.put("receiver_country", "中国");
		data.put("receiver_province", orderSub.getDeliveredProvince());
		data.put("receiver_city", orderSub.getDeliveredCity());
		data.put("receiver_district", orderSub.getDeliveredCounty());
		data.put("receiver_address", orderSub.getAddressDetail());
		data.put("receiver_tel", orderSub.getPhoneNum());
		data.put("receiver_mobile", orderSub.getMobPhoneNum());
		data.put("pay_status", "1");
		data.put("shipping_fee", orderSub.getTransportFee());
		data.put("order_amount", orderMain.getTotalProductPrice());
		data.put("payment", orderMain.getTotalPayAmount());
		data.put("source", orderMain.getOrderSource());// 待确认传什么

		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		for (OrderItem orderItem : orderSub.getOrderItems()) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("deal_code", orderItem.getOrderNo());
			item.put("sku", orderItem.getSkuNo());
			item.put("goods_sn", orderItem.getCommodityCode());
			item.put("goods_name", orderItem.getCommodityName());
			item.put("shop_price", orderItem.getNormalPrice());
			item.put("goods_price", orderItem.getUnitDeductedPrice());// 折后单价
			item.put("goods_number", orderItem.getSaleNum());

			items.add(item);
		}

		data.put("items", items);

		E3ClientDTO e3ClientDTO = new E3ClientDTO();
		e3ClientDTO.setKey(e3Key);
		e3ClientDTO.setRequestTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		e3ClientDTO.setSecret(e3Secret);
		e3ClientDTO.setServiceType("order.add");
		e3ClientDTO.setVersion(e3OrderVersion);
		e3ClientDTO.setData(data);
		e3ClientDTO.setUrl(e3OrderUrl);
		
		try {
			e3ClientDTO.setSign(this.getSign(e3ClientDTO));
			return this.sendToE3(e3ClientDTO);
		} catch (Exception e) {
			e.printStackTrace();
			return this.getErrorE3Result();
		}
	}

	@Override
	public E3ResultDTO cancelDelivery(String orderNo) {
		return null;
	}
	
	/**
	 * 进行E3库存锁定，解锁操作
	 * Description:
	 * @param orderMain
	 * @param type  锁定SD，解锁SF
	 * @return
	 * @throws Exception
	 */
	private E3ResultDTO operateE3Stock(OrderMain orderMain, String type) throws Exception{
		String warehouseCode = orderMain.getOrderSubs().get(0).getOrderItems().get(0).getWarehouseNo();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("wmsBillCode", orderMain.getOrderNo());
		data.put("warehouseCode",warehouseCode);
		data.put("billDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		data.put("opType", type);//锁定：SD 释放SF
		
		Map<String, Object> products = new HashMap<String, Object>();
		
		List<Map<String, Object>> product = new ArrayList<Map<String, Object>>();
		for (OrderItem orderItem : orderMain.getOrderSubs().get(0).getOrderItems()) {
			Map<String, Object> prod = new HashMap<String, Object>();
			prod.put("skuCode", orderItem.getSkuNo());
			prod.put("normalQuantity", orderItem.getSaleNum());
			product.add(prod);
		}
		
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
		
		return this.sendToE3(e3ClientDTO);
	}
	
	/**
	 * 进行签名加密
	 * Description:
	 * @param e3ClientDTO
	 * @return
	 * @throws JsonProcessingException
	 * @throws NoSuchAlgorithmException
	 * @throws Exception
	 */
	private String getSign(E3ClientDTO e3ClientDTO) throws JsonProcessingException, NoSuchAlgorithmException, Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String signParam = String.format(SIGN_FORMAT, e3ClientDTO.getKey(),e3ClientDTO.getRequestTime(), e3ClientDTO.getSecret(),
				e3ClientDTO.getVersion(), e3ClientDTO.getServiceType(), objectMapper.writeValueAsString(e3ClientDTO.getData()));
		LOGGER.info("Sign param:{}", signParam);
		return md5Encryption(signParam);
	}

	/**
	 * 调用E3接口
	 * Description:
	 * @param e3ClientDTO
	 * @return
	 * @throws Exception
	 */
	private E3ResultDTO sendToE3(E3ClientDTO e3ClientDTO) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		E3ResultDTO e3ResultDTO = new E3ResultDTO();

		try {
			HttpPost httpPost = new HttpPost(e3ClientDTO.getUrl());
			
			LOGGER.info("send to e3 ClientDto:::{} ", objectMapper.writeValueAsString(e3ClientDTO));
			
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("key", e3ClientDTO.getKey()));
			nvps.add(new BasicNameValuePair("sign", e3ClientDTO.getSign()));
			nvps.add(new BasicNameValuePair("requestTime", e3ClientDTO.getRequestTime()));
			nvps.add(new BasicNameValuePair("version", e3ClientDTO.getVersion()));
			nvps.add(new BasicNameValuePair("serviceType", e3ClientDTO.getServiceType()));
			nvps.add(new BasicNameValuePair("secret", e3ClientDTO.getSecret()));
			nvps.add(new BasicNameValuePair("data", objectMapper.writeValueAsString(e3ClientDTO.getData())));
			
			LOGGER.info("Send to E3 param:{}", objectMapper.writeValueAsString(nvps));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, UTF8));
			
			CloseableHttpResponse response = httpclient.execute(httpPost);

			try {
				LOGGER.info("E3 {} response status:{}", e3ClientDTO.getServiceType() ,response.getStatusLine());
				HttpEntity entity = response.getEntity();
				
				String message = EntityUtils.toString(entity, UTF8);
				
				LOGGER.info("E3 {} response message:{}", e3ClientDTO.getServiceType(), message);
				objectMapper.readValue(message, E3ResultDTO.class);
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}

		return e3ResultDTO;
	}
	
	/**
	 * MD5加密
	 * Description:
	 * @param params
	 * @return
	 */
	private String md5Encryption(String params){
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
	
	/**
	 * 创建一个系统异常的结果
	 * Description:
	 * @return
	 */
	private E3ResultDTO getErrorE3Result (){
		E3ResultDTO e3ResultDTO = new E3ResultDTO();
		e3ResultDTO.setStatus("FAIL");
		e3ResultDTO.setMessage("系统异常");
		return e3ResultDTO;
	}

}

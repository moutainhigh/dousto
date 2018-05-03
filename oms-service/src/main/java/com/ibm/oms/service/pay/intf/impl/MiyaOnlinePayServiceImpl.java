package com.ibm.oms.service.pay.intf.impl;
import java.util.SortedMap;
import java.util.TreeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.ibm.oms.service.pay.dto.PayJsonDto;
import com.ibm.oms.service.pay.dto.MiyaPayOnLineInputDto;
import com.ibm.oms.service.pay.intf.OnlinePayService;
import com.ibm.oms.service.pay.intf.PayDataService;
import com.ibm.oms.service.util.JaxbUtil;
import com.ibm.oms.service.util.MiyaPayUtil;

import net.sf.json.JSONObject;
@Service 
public class MiyaOnlinePayServiceImpl implements OnlinePayService{
	private final Logger logger = LoggerFactory.getLogger(MiyaOnlinePayServiceImpl.class);
	@Value("${miya.pay.keyValue}")
	private  String keyValue;
	@Value("${miya.pay.payTitle}")
	private  String payTitle;
	@Value("${miya.pay.backUrkl}")
	private  String backUrkl;
	@Autowired
	PayDataService payDataService;
	/** 
	 * 预下单支付(导购APP) MY-C-01
	 * @param jsonObj
	 * @throws Exception 
	 */
	@Override
	public String sendImprestPay(String jsonObj) throws Exception {
		String interfN="【订单  MY-C-01 】预下单支付（导购APP）接口";
		logger.info("{}接收报文：{}",interfN,jsonObj);
		JSONObject json =  JSONObject.fromObject(jsonObj);
		PayJsonDto payJsonDto = JSON.parseObject(json.getString("request_data"), PayJsonDto.class);//输入参数
		String returnStr = null;
		String urlStrJson = null;
		if((null != payJsonDto.getOrderNo()&&!"".equals(payJsonDto.getOrderNo())) && (null != payJsonDto.getPayType() && !"".equals(payJsonDto.getPayType()))){
			//获取订单信息
			MiyaPayOnLineInputDto orderInfo = payDataService.getOrderInfo(payJsonDto.getOrderNo(), payJsonDto.getPayType());
			orderInfo.setB2(payJsonDto.getPaymentCode());
			SortedMap<Object,Object> tlvmap = new TreeMap<Object,Object>();  
			 if(orderInfo.getA1()==null||orderInfo.getA1().equals("")){
				 orderInfo.setA1("A");
			 }if(orderInfo.getA6()==null||orderInfo.getA6().equals("")){
				 orderInfo.setA6("F");
			 }if(orderInfo.getA7()==null||orderInfo.getA7().equals("")){
				 orderInfo.setA7("1.5");
			 }
			 tlvmap.put("A1", orderInfo.getA1());//接口类型  默认 A-交易
			 tlvmap.put("A2", orderInfo.getA2());//商户号 米雅提供的商户号 
			 tlvmap.put("A3", orderInfo.getA3());//门店账号 通常为商户门店号
			 tlvmap.put("A4", orderInfo.getA4());//设备号 通常为商户门店 pos 机编号
			 tlvmap.put("A5", orderInfo.getA5());//收银编号 通常为商户门店收银员编号 
			 tlvmap.put("A6", orderInfo.getA6());//操作类型  默认 F-预下单 
			 tlvmap.put("A7", orderInfo.getA7());//版本号 默认 1.5
			 tlvmap.put("A10",orderInfo.getA10());//发票号
			 tlvmap.put("A12",payJsonDto.getPayType());//支付平台类型 1-微信  3-支付宝
			 tlvmap.put("B1", orderInfo.getB1());//商户订单号 商户侧生成的订单号，不可重复
			 tlvmap.put("B3", payTitle);//手机小票标题 顾客手机小票显示的标题
			 tlvmap.put("B4", orderInfo.getB4());//金额 单位分，1 分为 1,1 元为 100 
			 tlvmap.put("B5", orderInfo.getB5());//商品信息 商品信息
			 tlvmap.put("B13",backUrkl);//通知地址 接收异步通知回调地址
			 String sign = MiyaPayUtil.newCreateSign(keyValue, tlvmap);//生成签名
			 tlvmap.put("A8", sign);//设置签名值 
			 //map 转换 xml
			 String urlStr = null;
			 urlStr = JaxbUtil.mapToXml(tlvmap);
			 logger.info("inputStr:"+urlStr);
			 //创建httpclient
			 returnStr = JaxbUtil.createHttpClient(urlStr);
			 logger.info("returnStr:"+returnStr);
			 //更新订单
			 payDataService.writeOrderLogs(returnStr);
		}else{
			urlStrJson = JaxbUtil.payResponse(returnStr);
			logger.info("{}回传报文：{}",interfN,urlStrJson);
			return urlStrJson;
		}
		urlStrJson = JaxbUtil.payResponse(returnStr);
		logger.info("{}回传报文：{}",interfN,urlStrJson);
		return urlStrJson;
	}
	/**
	 * 下单支付(微信在线商城) MY-C-02
	 * @param jsonObj
	 * @throws Exception
	 */
	@Override
	public String sendCreatePay(String jsonObj) throws Exception {
		String interfN="【订单  MY-C-02 】下单支付(微信在线商城) 接口";
		logger.info("{}接收报文：{}",interfN,jsonObj);
		JSONObject json =  JSONObject.fromObject(jsonObj);
		PayJsonDto payJsonDto = JSON.parseObject(json.getString("request_data"), PayJsonDto.class);//输入参数
		String returnStr = null;
		String urlStrJson = null;
		if((null!=payJsonDto.getOrderNo()&&!"".equals(payJsonDto.getOrderNo())) && (null != payJsonDto.getPayType()&&!"".equals(payJsonDto.getPayType()))){
			//获取订单信息
			MiyaPayOnLineInputDto orderInfo = payDataService.getOrderInfo(payJsonDto.getOrderNo(), payJsonDto.getPayType());
			orderInfo.setB2(payJsonDto.getPaymentCode());
			SortedMap<Object,Object> tlvmap = new TreeMap<Object,Object>();  
			 if(orderInfo.getA1()==null||orderInfo.getA1().equals("")){
				 orderInfo.setA1("A");
			 }
			 if(orderInfo.getA6()==null||orderInfo.getA6().equals("")){
				 orderInfo.setA6("G");
			 }if(orderInfo.getA7()==null||orderInfo.getA7().equals("")){
				 orderInfo.setA7("1.5");
			 }
			 tlvmap.put("A1", orderInfo.getA1());//接口类型  默认 A-交易
			 tlvmap.put("A2", orderInfo.getA2());//商户号 米雅提供的商户号 
			 tlvmap.put("A3", orderInfo.getA3());//门店账号 通常为商户门店号
			 tlvmap.put("A4", orderInfo.getA4());//设备号 通常为商户门店 pos 机编号
			 tlvmap.put("A5", orderInfo.getA5());//收银编号 通常为商户门店收银员编号 
			 tlvmap.put("A6", orderInfo.getA6());//操作类型  默认 G-支付
			 tlvmap.put("A7", orderInfo.getA7());//版本号 默认 1.5
			 tlvmap.put("A11",payJsonDto.getPayMode());//支付方式 JSAPI、APP、H5、WXA(小程序)
			 tlvmap.put("A12",payJsonDto.getPayType());//支付平台类型 1-微信  3-支付宝  
			 tlvmap.put("B1", orderInfo.getB1());//商户订单号 商户侧生成的订单号，不可重复
			 tlvmap.put("B3", payTitle);//手机小票标题 顾客手机小票显示的标题
			 tlvmap.put("B4", orderInfo.getB4());//金额 单位分，1 分为 1,1 元为 100 
			 tlvmap.put("B5", orderInfo.getB5());//商品信息 商品信息
			 tlvmap.put("B11", payJsonDto.getOpenId());//openid/userid 微信 openid 或支付宝 userid。支付宝必填， 微信 openid 和 sub_openid 不能同时为空， JSAPI、WXA 必传
			 tlvmap.put("B12", payJsonDto.getSub_openid());//sub_openid  微信 sub_openid, JSAPI、WXA 必传 
			 tlvmap.put("B13", backUrkl);//通知地址  接收异步通知回调地址 
			 tlvmap.put("B14", payJsonDto.getPayIp());//用户 ip 用户终端 ip。微信 H5、APP 支付必填 
			 tlvmap.put("B16", payJsonDto.getOpenId());//appid  商户微信 APPID
			 tlvmap.put("B17", payJsonDto.getStart_time());//start_time 订单开始时间，格式 yyyy-MM-dd HH:mm:ss 
			 tlvmap.put("B18", payJsonDto.getExpire());//expire 订单过期时间，格式 yyyy-MM-dd HH:mm:ss
			 String sign = MiyaPayUtil.newCreateSign(keyValue, tlvmap);//生成签名
			 tlvmap.put("A8", sign);//设置签名值 
			 
			 //map 转换 xml
			 String urlStr = null;
			 urlStr = JaxbUtil.mapToXml(tlvmap);
			 logger.info("inputStr:"+urlStr);
			 ///创建httpclient
			 returnStr = JaxbUtil.createHttpClient(urlStr);
			 logger.info("outputStr:"+returnStr);
			 //更新订单
			 payDataService.writeOrderLogs(returnStr);
		}else{
			urlStrJson = JaxbUtil.payResponse(returnStr);
			logger.info("{}回传报文：{}",interfN,urlStrJson);
			return urlStrJson;
		}
		urlStrJson = JaxbUtil.payResponse(returnStr);
		logger.info("{}回传报文：{}",interfN,urlStrJson);
		return urlStrJson;
	}
	
	/**
	 * 下单支付（门店pos）MY-C-03
	 * @param jsonObj
	 * @throws Exception
	 */
	@Override
	public String sendPay(String jsonObj) throws Exception {
		String interfN="【订单  MY-C-03 】下单支付（门店pos） 接口";
		logger.info("{}接收报文：{}",interfN,jsonObj);
		JSONObject json =  JSONObject.fromObject(jsonObj);
		PayJsonDto payJsonDto = JSON.parseObject(json.getString("request_data"), PayJsonDto.class);//输入参数
		String returnStr = null;
		String urlStrJson = null;
		if((null!=payJsonDto.getOrderNo()&&!"".equals(payJsonDto.getOrderNo())) && (null != payJsonDto.getPayType()&&!"".equals(payJsonDto.getPayType()))){
			//获取订单信息
			MiyaPayOnLineInputDto orderInfo = payDataService.getOrderInfo(payJsonDto.getOrderNo(), payJsonDto.getPayType());
			orderInfo.setB2(payJsonDto.getPaymentCode());
			SortedMap<Object,Object> tlvmap = new TreeMap<Object,Object>();  
			 if(orderInfo.getA1()==null||orderInfo.getA1().equals("")){
				 orderInfo.setA1("A");
			 }
			 if(orderInfo.getA6()==null||orderInfo.getA6().equals("")){
				 orderInfo.setA6("A");
			 }
			 if(orderInfo.getA7()==null||orderInfo.getA7().equals("")){
				 orderInfo.setA7("1.5");
			 }
			 if(orderInfo.getA11()==null||orderInfo.getA11().equals("")){
				 orderInfo.setA11("A");
			 }
			
			 tlvmap.put("A1", orderInfo.getA1());//接口类型  默认 A-交易
			 tlvmap.put("A2", orderInfo.getA2());//商户号 米雅提供的商户号 
			 tlvmap.put("A3", orderInfo.getA3());//门店账号 通常为商户门店号
			 tlvmap.put("A4", orderInfo.getA4());//设备号 通常为商户门店 pos 机编号
			 tlvmap.put("A5", orderInfo.getA5());//收银编号 通常为商户门店收银员编号 
			 tlvmap.put("A6", orderInfo.getA6());//操作类型  默认 A-支付 
			 tlvmap.put("A7", orderInfo.getA7());//版本号 默认 1.5 
			 tlvmap.put("A10",orderInfo.getA10());//发票号
			 tlvmap.put("A11",orderInfo.getA11());//重码分类 默认 A
			 
			 tlvmap.put("B1", orderInfo.getB1());//商户订单号 商户侧生成的订单号，不可重复
			 tlvmap.put("B2", orderInfo.getB2());//支付码 扫描顾客手机 APP 上的条码获取的值 
			 tlvmap.put("B3", payTitle);//手机小票标题 顾客手机小票显示的标题
			 tlvmap.put("B4", orderInfo.getB4());//金额 单位分，1 分为 1,1 元为 100 
			 tlvmap.put("B5", orderInfo.getB5());//商品信息 商品信息
			 tlvmap.put("B6", orderInfo.getB6());//花呗分期信息 格式为分期数:值;卖家承担手续费百分比: 值。 hb_fq_num 可选，最大长度 5，使用花呗分期 要进行的分期数 hb_fq_seller_percent 可选，最大长度 3 使 用花呗分期需要卖家承担的手续费比例的百 分值，传入 100 代表 100% hb_fq_num:10;hb_fq_seller_percent:100 
			 tlvmap.put("B7", orderInfo.getB7());//支付宝不可优惠金 额
			 tlvmap.put("B8", orderInfo.getB8());//支付宝禁用渠道
			 tlvmap.put("B15", orderInfo.getB15());//整单流水号
		
			 String sign = MiyaPayUtil.newCreateSign(keyValue, tlvmap);//生成签名
			 tlvmap.put("A8", sign);//设置签名值 
			 
			 //map 转换 xml
			 String urlStr = null;
			 urlStr = JaxbUtil.mapToXml(tlvmap);
			 logger.info("inputStr:"+urlStr);
			 ///创建httpclient
			 returnStr = JaxbUtil.createHttpClient(urlStr);
			 logger.info("returnStr:"+returnStr);
			 //更新订单
			 payDataService.writeOrderLogs(returnStr);
		}else{
			urlStrJson = JaxbUtil.payResponse(returnStr);
			logger.info("{}回传报文：{}",interfN,urlStrJson);
			return urlStrJson;
		}
		urlStrJson = JaxbUtil.payResponse(returnStr);
		logger.info("{}回传报文：{}",interfN,urlStrJson);
		return urlStrJson;
	}
	/**
	 * 订单查询 MY-C-04
	 * @param jsonObj
	 * @throws Exception
	 */
	@Override
	public String sendPayQuery(String jsonObj) throws Exception {
		String interfN="【订单  MY-C-04 】支付订单查询  接口";
		logger.info("{}接收报文：{}",interfN,jsonObj);
		JSONObject json =  JSONObject.fromObject(jsonObj);
		PayJsonDto payJsonDto = JSON.parseObject(json.getString("request_data"), PayJsonDto.class);//输入参数
		//获取订单信息
		MiyaPayOnLineInputDto orderInfo = payDataService.getOrderInfo(payJsonDto.getOrderNo(), payJsonDto.getPayType());
		SortedMap<Object,Object> tlvmap = new TreeMap<Object,Object>();  
		 if(orderInfo.getA1()==null||orderInfo.getA1().equals("")){
			 orderInfo.setA1("A");
		 }if(orderInfo.getA6()==null||orderInfo.getA6().equals("")){
			 orderInfo.setA6("B");
		 }if(orderInfo.getA7()==null||orderInfo.getA7().equals("")){
			 orderInfo.setA7("1.5");
		 }
		 tlvmap.put("A1", orderInfo.getA1());//接口类型  默认 A-交易
		 tlvmap.put("A2", orderInfo.getA2());//商户号 米雅提供的商户号 
		 tlvmap.put("A3", orderInfo.getA3());//门店账号 通常为商户门店号
		 tlvmap.put("A4", orderInfo.getA4());//设备号 通常为商户门店 pos 机编号
		 tlvmap.put("A5", orderInfo.getA5());//收银编号 通常为商户门店收银员编号 
		 tlvmap.put("A6", orderInfo.getA6());//操作类型  默认 B-支付
		 tlvmap.put("A7", orderInfo.getA7());//版本号 默认 1.5 
		 tlvmap.put("B1", orderInfo.getB1());//商户订单号 商户侧生成的订单号，不可重复
		 
		 String sign = MiyaPayUtil.newCreateSign(keyValue, tlvmap);//生成签名
		 tlvmap.put("A8", sign);//设置签名值 
		 //map 转换 xml
		 String urlStr = null;
		 String urlStrJson = null;
		 urlStr = JaxbUtil.mapToXml(tlvmap);
		 logger.info("inputStr:"+urlStr);
		 ///创建httpclient
		 String returnStr = JaxbUtil.createHttpClient(urlStr);
		 logger.info("outputStr:",interfN,returnStr);
		 urlStrJson = JaxbUtil.payResponse(returnStr);
		 logger.info("{}回传报文：{}",interfN,urlStrJson);
		return urlStrJson;
	}
	/**
	 * 退款 MY-C-05
	 * @param jsonObj
	 * @throws Exception
	 */
	@Override
	public String refundPay(String jsonObj) throws Exception {
		String interfN="【订单   MY-C-05 】支付退款  接口";
		logger.info("{}接收报文：{}",interfN,jsonObj);
		JSONObject json =  JSONObject.fromObject(jsonObj);
		PayJsonDto payJsonDto = JSON.parseObject(json.getString("request_data"), PayJsonDto.class);//输入参数
		//获取订单信息
		MiyaPayOnLineInputDto orderInfo = payDataService.getOrderInfo(payJsonDto.getOrderNo(), payJsonDto.getPayType());
		SortedMap<Object,Object> tlvmap = new TreeMap<Object,Object>();  
		 if(orderInfo.getA1()==null||orderInfo.getA1().equals("")){
			 orderInfo.setA1("A");
		 }if(orderInfo.getA6()==null||orderInfo.getA6().equals("")){
			 orderInfo.setA6("C");
		 }if(orderInfo.getA7()==null||orderInfo.getA7().equals("")){
			 orderInfo.setA7("1.5");
		 }
		 tlvmap.put("A1", orderInfo.getA1());//接口类型  默认 A-交易
		 tlvmap.put("A2", orderInfo.getA2());//商户号 米雅提供的商户号 
		 tlvmap.put("A3", orderInfo.getA3());//门店账号 通常为商户门店号
		 tlvmap.put("A4", orderInfo.getA4());//设备号 通常为商户门店 pos 机编号
		 tlvmap.put("A5", orderInfo.getA5());//收银编号 通常为商户门店收银员编号 
		 tlvmap.put("A6", orderInfo.getA6());//操作类型  默认 C-退款
		 tlvmap.put("A7", orderInfo.getA7());//版本号 默认 1.5 
		 tlvmap.put("B1", orderInfo.getB1());//商户订单号 商户侧生成的订单号，不可重复
		 tlvmap.put("B2", orderInfo.getB1());//退款单号
		 tlvmap.put("B4", orderInfo.getB4());//金额 单位分，1 分为 1,1 元为 100 
		
		 String sign = MiyaPayUtil.newCreateSign(keyValue, tlvmap);//生成签名
		 tlvmap.put("A8", sign);//设置签名值 
		//转换xml
		 String urlStr = null;
		 String urlStrJson = null;
		 urlStr = JaxbUtil.mapToXml(tlvmap);
		 logger.info("inputStr:"+urlStr);
		 ///创建httpclient
		 String returnStr = JaxbUtil.createHttpClient(urlStr);
		 logger.info("outputStr:",interfN,returnStr);
		 urlStrJson = JaxbUtil.payResponse(returnStr);
		 logger.info("{}回传报文：{}",interfN,urlStrJson);
		 return urlStrJson;
	}
	
	
	/**
	 * 退款查询  MY-C-06
	 * @param jsonObj
	 * @throws Exception
	 */
	@Override
	public String refundQuery(String jsonObj) throws Exception {
		String interfN="【订单   MY-C-06】退款查询接口";
		logger.info("{}接收报文：{}",interfN,jsonObj);
		JSONObject json =  JSONObject.fromObject(jsonObj); 
		PayJsonDto payJsonDto = JSON.parseObject(json.getString("request_data"), PayJsonDto.class);//输入参数
		//获取订单信息
		MiyaPayOnLineInputDto orderInfo = payDataService.getOrderInfo(payJsonDto.getOrderNo(), payJsonDto.getPayType());
	
		
		SortedMap<Object,Object> tlvmap = new TreeMap<Object,Object>();  
		 if(orderInfo.getA1()==null||orderInfo.getA1().equals("")){
			 orderInfo.setA1("A");
		 }if(orderInfo.getA6()==null||orderInfo.getA6().equals("")){
			 orderInfo.setA6("D");
		 }if(orderInfo.getA7()==null||orderInfo.getA7().equals("")){
			 orderInfo.setA7("1.5");
		 }
		 tlvmap.put("A1", orderInfo.getA1());//接口类型  默认 A-交易
		 tlvmap.put("A2", orderInfo.getA2());//商户号 米雅提供的商户号 
		 tlvmap.put("A3", orderInfo.getA3());//门店账号 通常为商户门店号
		 tlvmap.put("A4", orderInfo.getA4());//设备号 通常为商户门店 pos 机编号
		 tlvmap.put("A5", orderInfo.getA5());//收银编号 通常为商户门店收银员编号 
		 tlvmap.put("A6", orderInfo.getA6());//默认 D-退款查询 
		 tlvmap.put("A7", orderInfo.getA7());//版本号 默认 1.5 
		 
		 tlvmap.put("B1", orderInfo.getB1());//商户订单号 商户侧生成的订单号，不可重复
		 tlvmap.put("B2", orderInfo.getB1());//退款单号 原商户侧生成的退款订单号
		 String sign = MiyaPayUtil.newCreateSign(keyValue, tlvmap);//生成签名
		 tlvmap.put("A8", sign);//设置签名值 
		 //map 转换 xml
		 //map 转换 xml
		 String urlStr = null;
		 String urlStrJson = null;
		 urlStr = JaxbUtil.mapToXml(tlvmap);
		 logger.info("inputStr:"+urlStr);
		 ///创建httpclient
		 String returnStr = JaxbUtil.createHttpClient(urlStr);
		 logger.info("outputStr:",interfN,returnStr);
		 urlStrJson = JaxbUtil.payResponse(returnStr);
		 logger.info("{}回传报文：{}",interfN,urlStrJson);
		 return urlStrJson;
	}
	/**
	 * 撤销订单 MY-C-07
	 * @param jsonObj
	 * @throws Exception
	 */
	@Override
	public String cancelOrder(String jsonObj) throws Exception {
		String interfN="【订单   MY-C-07】撤销订单 接口";
		logger.info("{}接收报文：{}",interfN,jsonObj);
		JSONObject json =  JSONObject.fromObject(jsonObj);
		PayJsonDto payJsonDto = JSON.parseObject(json.getString("request_data"), PayJsonDto.class);//输入参数
		//获取订单信息
		MiyaPayOnLineInputDto orderInfo = payDataService.getOrderInfo(payJsonDto.getOrderNo(), payJsonDto.getPayType());
		SortedMap<Object,Object> tlvmap = new TreeMap<Object,Object>();  
		 if(orderInfo.getA1()==null||orderInfo.getA1().equals("")){
			 orderInfo.setA1("A");
		 }if(orderInfo.getA6()==null||orderInfo.getA6().equals("")){
			 orderInfo.setA6("E");
		 }if(orderInfo.getA7()==null||orderInfo.getA7().equals("")){
			 orderInfo.setA7("1.5");
		 }
		 tlvmap.put("A1", orderInfo.getA1());//接口类型  默认 A-交易
		 tlvmap.put("A2", orderInfo.getA2());//商户号 米雅提供的商户号 
		 tlvmap.put("A3", orderInfo.getA3());//门店账号 通常为商户门店号
		 tlvmap.put("A4", orderInfo.getA4());//设备号 通常为商户门店 pos 机编号
		 tlvmap.put("A5", orderInfo.getA5());//收银编号 通常为商户门店收银员编号 
		 tlvmap.put("A6", orderInfo.getA6());//默认 E-撤销
		 tlvmap.put("A7", orderInfo.getA7());//版本号 默认 1.5 
	
		 tlvmap.put("B1", orderInfo.getB1());//商户订单号 商户侧生成的订单号，不可重复
		 String sign = MiyaPayUtil.newCreateSign(keyValue, tlvmap);//生成签名
		 tlvmap.put("A8", sign);//签名 其它参数签名生成的摘要 
		 //map 转换 xml
		 String urlStr = null;
		 String urlStrJson = null;
		 urlStr = JaxbUtil.mapToXml(tlvmap);
		 logger.info("inputStr:"+urlStr);
		 ///创建httpclient
		 String returnStr = JaxbUtil.createHttpClient(urlStr);
		 logger.info("outputStr:",interfN,returnStr);
		 urlStrJson = JaxbUtil.payResponse(returnStr);
		 logger.info("{}回传报文：{}",interfN,urlStrJson);
		 return urlStrJson;
	}
	/**
	 *  支付接收异步通知回调地址 MY-C-08 
	 */
	@Override
	public String payOrderCallbackInfo(String xmlObj) throws Exception {
		 String interfN="【订单   MY-C-08】异步通知回调  接口";
		 logger.info("{}接收报文：{}",interfN,xmlObj);
		 String writeOrderLogs = payDataService.writeOrderLogs(xmlObj);
		 logger.info("{}回传报文：{}",interfN,writeOrderLogs);
		return writeOrderLogs;
	}
}

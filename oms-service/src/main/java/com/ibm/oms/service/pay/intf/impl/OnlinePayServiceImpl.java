package com.ibm.oms.service.pay.intf.impl;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import com.ibm.oms.dao.constant.OrderStatusAction;
import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.oms.service.OrderPayService;
import com.ibm.oms.service.business.OrderStatusService;
import com.ibm.oms.service.util.CommonUtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.ibm.oms.service.pay.dto.PayOnLineADto;
import com.ibm.oms.service.pay.dto.PayOnLineBDto;
import com.ibm.oms.service.pay.intf.OnlinePayService;
import com.ibm.oms.service.util.JaxbUtil;
import com.ibm.oms.service.util.MiyaPayUtil;
@Service
public class OnlinePayServiceImpl implements OnlinePayService{
	private final Logger logger = LoggerFactory.getLogger(OnlinePayServiceImpl.class);
	private static String keyValue = "nbz9ww27sx4ou6dkr61mf63tth3s6e2d";
	@Autowired
	OrderStatusService orderStatusService;
	@Autowired
	CommonUtilService commonUtilService;
	@Autowired
	OrderPayService orderPayService;
	/**
	 * 预下单支付(导购APP)
	 * @param jsonObj
	 * @throws Exception 
	 */
	@Override
	public String sendImprestPay(String jsonObj) throws Exception {
		PayOnLineADto parseObj = JSON.parseObject(jsonObj, PayOnLineADto.class);
		PayOnLineBDto parseObjItem = JSON.parseObject(jsonObj, PayOnLineBDto.class);
		SortedMap<Object,Object> tlvmap = new TreeMap<Object,Object>();  
		 if(parseObj.getA1()==null||parseObj.getA1().equals("")){
			 parseObj.setA1("A");
		 }if(parseObj.getA6()==null||parseObj.getA6().equals("")){
			 parseObj.setA6("F");
		 }if(parseObj.getA7()==null||parseObj.getA7().equals("")){
			 parseObj.setA7("1.5");
		 }
		 tlvmap.put("A1", parseObj.getA1());//接口类型  默认 A-交易
		 tlvmap.put("A2", parseObj.getA2());//商户号 米雅提供的商户号 
		 tlvmap.put("A3", parseObj.getA3());//门店账号 通常为商户门店号
		 tlvmap.put("A4", parseObj.getA4());//设备号 通常为商户门店 pos 机编号
		 tlvmap.put("A5", parseObj.getA5());//收银编号 通常为商户门店收银员编号 
		 tlvmap.put("A6", parseObj.getA6());//操作类型  默认 F-预下单 
		 tlvmap.put("A7", parseObj.getA7());//版本号 默认 1.5
		 tlvmap.put("A10",parseObj.getA10());//发票号
		 tlvmap.put("A12",parseObj.getA12());//支付平台类型 1-微信  3-支付宝
		 tlvmap.put("B1", parseObjItem.getB1());//商户订单号 商户侧生成的订单号，不可重复
		 tlvmap.put("B3", parseObjItem.getB3());//手机小票标题 顾客手机小票显示的标题
		 tlvmap.put("B4", parseObjItem.getB4());//金额 单位分，1 分为 1,1 元为 100 
		 tlvmap.put("B5", parseObjItem.getB5());//商品信息 商品信息
		 tlvmap.put("B13", parseObjItem.getB13());//通知地址 接收异步通知回调地址
		 String sign = MiyaPayUtil.createSign(keyValue, tlvmap);//生成签名
		 tlvmap.put("A8", sign);//签名 其它参数签名生成的摘要 
		 parseObj.setA8(sign);//=========设置签名值==============
		 
		//转换xml
		 String urlStr = null;
		 urlStr = JaxbUtil.getAssemblyXml(parseObj,parseObjItem);
		 logger.info("urlStr:"+urlStr);
		 //创建httpclient
		 String returnStr = JaxbUtil.createHttpClient(urlStr);
		return returnStr;
	}
	/**
	 * 下单支付(微信在线商城)
	 * @param jsonObj
	 * @throws Exception
	 */
	@Override
	public String sendCreatePay(String jsonObj) throws Exception {
		PayOnLineADto parseObj = JSON.parseObject(jsonObj, PayOnLineADto.class);
		PayOnLineBDto parseObjItem = JSON.parseObject(jsonObj, PayOnLineBDto.class);
		SortedMap<Object,Object> tlvmap = new TreeMap<Object,Object>();  
		 if(parseObj.getA1()==null||parseObj.getA1().equals("")){
			 parseObj.setA1("A");
		 }
		 if(parseObj.getA6()==null||parseObj.getA6().equals("")){
			 parseObj.setA6("G");
		 }if(parseObj.getA7()==null||parseObj.getA7().equals("")){
			 parseObj.setA7("1.5");
		 }
		 tlvmap.put("A1", parseObj.getA1());//接口类型  默认 A-交易
		 tlvmap.put("A2", parseObj.getA2());//商户号 米雅提供的商户号 
		 tlvmap.put("A3", parseObj.getA3());//门店账号 通常为商户门店号
		 tlvmap.put("A4", parseObj.getA4());//设备号 通常为商户门店 pos 机编号
		 tlvmap.put("A5", parseObj.getA5());//收银编号 通常为商户门店收银员编号 
		 tlvmap.put("A6", parseObj.getA6());//操作类型  默认 G-支付
		 tlvmap.put("A7", parseObj.getA7());//版本号 默认 1.5
		 tlvmap.put("A11",parseObj.getA11());//支付方式 JSAPI、APP、H5、WXA(小程序)
		 tlvmap.put("A12",parseObj.getA12());//支付平台类型 1-微信  3-支付宝
		 tlvmap.put("B1", parseObjItem.getB1());//商户订单号 商户侧生成的订单号，不可重复
		 tlvmap.put("B3", parseObjItem.getB3());//手机小票标题 顾客手机小票显示的标题
		 tlvmap.put("B4", parseObjItem.getB4());//金额 单位分，1 分为 1,1 元为 100 
		 tlvmap.put("B5", parseObjItem.getB5());//商品信息 商品信息
		 tlvmap.put("B11", parseObjItem.getB11());//openid/userid 微信 openid 或支付宝 userid。支付宝必填， 微信 openid 和 sub_openid 不能同时为空， JSAPI、WXA 必传
		 tlvmap.put("B12", parseObjItem.getB12());//sub_openid  微信 sub_openid, JSAPI、WXA 必传 
		 tlvmap.put("B13", parseObjItem.getB13());//通知地址  接收异步通知回调地址 
		 tlvmap.put("B14", parseObjItem.getB14());//用户 ip 用户终端 ip。微信 H5、APP 支付必填 
		 tlvmap.put("B16", parseObjItem.getB16());//appid  商户微信 APPID
		 tlvmap.put("B17", parseObjItem.getB17());//start_time 订单开始时间，格式 yyyy-MM-dd HH:mm:ss 
		 tlvmap.put("B18", parseObjItem.getB18());//expire 订单过期时间，格式 yyyy-MM-dd HH:mm:ss
		 
		 String sign = MiyaPayUtil.createSign(keyValue, tlvmap);//生成签名
		 tlvmap.put("A8", sign);//签名 其它参数签名生成的摘要 
		 parseObj.setA8(sign);//=========设置签名值==============
		 
		//转换xml
		 String urlStr = null;
		 urlStr = JaxbUtil.getAssemblyXml(parseObj,parseObjItem);
		 logger.info("urlStr:"+urlStr);
		 ///创建httpclient
		 String returnStr = JaxbUtil.createHttpClient(urlStr);
		 return returnStr;
	}
	
	/**
	 * 下单支付（门店pos）
	 * @param jsonObj
	 * @throws Exception
	 */
	@Override
	public String sendPay(String jsonObj) throws Exception {
		PayOnLineADto parseObj = JSON.parseObject(jsonObj, PayOnLineADto.class);
		PayOnLineBDto parseObjItem = JSON.parseObject(jsonObj, PayOnLineBDto.class);
		SortedMap<Object,Object> tlvmap = new TreeMap<Object,Object>();  
		 if(parseObj.getA1()==null||parseObj.getA1().equals("")){
			 parseObj.setA1("A");
		 }
		 if(parseObj.getA6()==null||parseObj.getA6().equals("")){
			 parseObj.setA6("A");
		 }
		 if(parseObj.getA7()==null||parseObj.getA7().equals("")){
			 parseObj.setA7("1.5");
		 }
		 tlvmap.put("A1", parseObj.getA1());//接口类型  默认 A-交易
		 tlvmap.put("A2", parseObj.getA2());//商户号 米雅提供的商户号 
		 tlvmap.put("A3", parseObj.getA3());//门店账号 通常为商户门店号
		 tlvmap.put("A4", parseObj.getA4());//设备号 通常为商户门店 pos 机编号
		 tlvmap.put("A5", parseObj.getA5());//收银编号 通常为商户门店收银员编号 
		 tlvmap.put("A6", parseObj.getA6());//操作类型  默认 A-支付 
		 tlvmap.put("A7", parseObj.getA7());//版本号 默认 1.5 
		 tlvmap.put("A10",parseObj.getA10());//发票号
		 tlvmap.put("A11",parseObj.getA11());//重码分类 默认 A
		 tlvmap.put("B1", parseObjItem.getB1());//商户订单号 商户侧生成的订单号，不可重复
		 tlvmap.put("B2", parseObjItem.getB2());//支付码 扫描顾客手机 APP 上的条码获取的值 
		 tlvmap.put("B3", parseObjItem.getB3());//手机小票标题 顾客手机小票显示的标题
		 tlvmap.put("B4", parseObjItem.getB4());//金额 单位分，1 分为 1,1 元为 100 
		 tlvmap.put("B5", parseObjItem.getB5());//商品信息 商品信息
		 tlvmap.put("B6", parseObjItem.getB6());//花呗分期信息 格式为分期数:值;卖家承担手续费百分比: 值。 hb_fq_num 可选，最大长度 5，使用花呗分期 要进行的分期数 hb_fq_seller_percent 可选，最大长度 3 使 用花呗分期需要卖家承担的手续费比例的百 分值，传入 100 代表 100% hb_fq_num:10;hb_fq_seller_percent:100 
		 tlvmap.put("B7", parseObjItem.getB7());//支付宝不可优惠金 额
		 tlvmap.put("B8", parseObjItem.getB8());//支付宝禁用渠道
		 tlvmap.put("B15", parseObjItem.getB15());//整单流水号
	
		 String sign = MiyaPayUtil.createSign(keyValue, tlvmap);//生成签名
		 tlvmap.put("A8", sign);//签名 其它参数签名生成的摘要 
		 parseObj.setA8(sign);//=========设置签名值==============
		 
		//转换xml
		 String urlStr = null;
		 urlStr = JaxbUtil.getAssemblyXml(parseObj,parseObjItem);
		 logger.info("urlStr:"+urlStr);
		 ///创建httpclient
		 String returnStr = JaxbUtil.createHttpClient(urlStr);
		 return returnStr;
	}
	/**
	 * 订单查询
	 * @param jsonObj
	 * @throws Exception
	 */
	@Override
	public String sendPayQuery(String jsonObj) throws Exception {
		PayOnLineADto parseObj = JSON.parseObject(jsonObj, PayOnLineADto.class);
		PayOnLineBDto parseObjItem = JSON.parseObject(jsonObj, PayOnLineBDto.class);
		SortedMap<Object,Object> tlvmap = new TreeMap<Object,Object>();  
		 if(parseObj.getA1()==null||parseObj.getA1().equals("")){
			 parseObj.setA1("A");
		 }if(parseObj.getA6()==null||parseObj.getA6().equals("")){
			 parseObj.setA6("B");
		 }if(parseObj.getA7()==null||parseObj.getA7().equals("")){
			 parseObj.setA7("1.5");
		 }
		 tlvmap.put("A1", parseObj.getA1());//接口类型  默认 A-交易
		 tlvmap.put("A2", parseObj.getA2());//商户号 米雅提供的商户号 
		 tlvmap.put("A3", parseObj.getA3());//门店账号 通常为商户门店号
		 tlvmap.put("A4", parseObj.getA4());//设备号 通常为商户门店 pos 机编号
		 tlvmap.put("A5", parseObj.getA5());//收银编号 通常为商户门店收银员编号 
		 tlvmap.put("A6", parseObj.getA6());//操作类型  默认 B-支付
		 tlvmap.put("A7", parseObj.getA7());//版本号 默认 1.5 
		 tlvmap.put("B1", parseObjItem.getB1());//商户订单号 商户侧生成的订单号，不可重复
		 
		 String sign = MiyaPayUtil.createSign(keyValue, tlvmap);//生成签名
		 tlvmap.put("A8", sign);//签名 其它参数签名生成的摘要 
		 parseObj.setA8(sign);//=========设置签名值==============
		 
		//转换xml
		 String urlStr = null;
		 urlStr = JaxbUtil.getAssemblyXml(parseObj,parseObjItem);
		 logger.info("urlStr:"+urlStr);
		 ///创建httpclient
		 String returnStr = JaxbUtil.createHttpClient(urlStr);
		 return returnStr;
	}
	/**
	 * 退款
	 * @param jsonObj
	 * @throws Exception
	 */
	@Override
	public String refundPay(String jsonObj) throws Exception {
		PayOnLineADto parseObj = JSON.parseObject(jsonObj, PayOnLineADto.class);
		PayOnLineBDto parseObjItem = JSON.parseObject(jsonObj, PayOnLineBDto.class);
		SortedMap<Object,Object> tlvmap = new TreeMap<Object,Object>();  
		 if(parseObj.getA1()==null||parseObj.getA1().equals("")){
			 parseObj.setA1("A");
		 }if(parseObj.getA6()==null||parseObj.getA6().equals("")){
			 parseObj.setA6("C");
		 }if(parseObj.getA7()==null||parseObj.getA7().equals("")){
			 parseObj.setA7("1.5");
		 }
		 tlvmap.put("A1", parseObj.getA1());//接口类型  默认 A-交易
		 tlvmap.put("A2", parseObj.getA2());//商户号 米雅提供的商户号 
		 tlvmap.put("A3", parseObj.getA3());//门店账号 通常为商户门店号
		 tlvmap.put("A4", parseObj.getA4());//设备号 通常为商户门店 pos 机编号
		 tlvmap.put("A5", parseObj.getA5());//收银编号 通常为商户门店收银员编号 
		 tlvmap.put("A6", parseObj.getA6());//操作类型  默认 C-退款
		 tlvmap.put("A7", parseObj.getA7());//版本号 默认 1.5 
		 tlvmap.put("B1", parseObjItem.getB1());//商户订单号 商户侧生成的订单号，不可重复
		 tlvmap.put("B2", parseObjItem.getB2());//退款单号
		 tlvmap.put("B4", parseObjItem.getB4());//金额 单位分，1 分为 1,1 元为 100 
		 tlvmap.put("B5", parseObjItem.getB5());//商品信息 商品信息
		
		 String sign = MiyaPayUtil.createSign(keyValue, tlvmap);//生成签名
		 tlvmap.put("A8", sign);//签名 其它参数签名生成的摘要 
		 parseObj.setA8(sign);//=========设置签名值==============
		 
		//转换xml
		 String urlStr = null;
		 urlStr = JaxbUtil.getAssemblyXml(parseObj,parseObjItem);
		 logger.info("urlStr:"+urlStr);
	    ///创建httpclient
		 String returnStr = JaxbUtil.createHttpClient(urlStr);
		 return returnStr;
	}
	
	
	/**
	 * 退款查询
	 * @param jsonObj
	 * @throws Exception
	 */
	@Override
	public String refundQuery(String jsonObj) throws Exception {
		PayOnLineADto parseObj = JSON.parseObject(jsonObj, PayOnLineADto.class);
		PayOnLineBDto parseObjItem = JSON.parseObject(jsonObj, PayOnLineBDto.class);
		SortedMap<Object,Object> tlvmap = new TreeMap<Object,Object>();  
		 if(parseObj.getA1()==null||parseObj.getA1().equals("")){
			 parseObj.setA1("A");
		 }if(parseObj.getA6()==null||parseObj.getA6().equals("")){
			 parseObj.setA6("D");
		 }if(parseObj.getA7()==null||parseObj.getA7().equals("")){
			 parseObj.setA7("1.5");
		 }
		 tlvmap.put("A1", parseObj.getA1());//接口类型  默认 A-交易
		 tlvmap.put("A2", parseObj.getA2());//商户号 米雅提供的商户号 
		 tlvmap.put("A3", parseObj.getA3());//门店账号 通常为商户门店号
		 tlvmap.put("A4", parseObj.getA4());//设备号 通常为商户门店 pos 机编号
		 tlvmap.put("A5", parseObj.getA5());//收银编号 通常为商户门店收银员编号 
		 tlvmap.put("A6", parseObj.getA6());//默认 D-退款查询 
		 tlvmap.put("A7", parseObj.getA7());//版本号 默认 1.5 
		 
		 tlvmap.put("B1", parseObjItem.getB1());//商户订单号 商户侧生成的订单号，不可重复
		 tlvmap.put("B2", parseObjItem.getB2());//退款单号 原商户侧生成的退款订单号
		 String sign = MiyaPayUtil.createSign(keyValue, tlvmap);//生成签名
		 tlvmap.put("A8", sign);//签名 其它参数签名生成的摘要 
		 parseObj.setA8(sign);//=========设置签名值==============
		 
		//转换xml
		 String urlStr = null;
		 urlStr = JaxbUtil.getAssemblyXml(parseObj,parseObjItem);
		 logger.info("urlStr:"+urlStr);
		 ///创建httpclient
		 String returnStr = JaxbUtil.createHttpClient(urlStr);
		 return returnStr;
	}
	/**
	 * 撤销订单
	 * @param jsonObj
	 * @throws Exception
	 */
	@Override
	public String cancelOrder(String jsonObj) throws Exception {
		PayOnLineADto parseObj = JSON.parseObject(jsonObj, PayOnLineADto.class);
		PayOnLineBDto parseObjItem = JSON.parseObject(jsonObj, PayOnLineBDto.class);
		SortedMap<Object,Object> tlvmap = new TreeMap<Object,Object>();  
		 if(parseObj.getA1()==null||parseObj.getA1().equals("")){
			 parseObj.setA1("A");
		 }if(parseObj.getA6()==null||parseObj.getA6().equals("")){
			 parseObj.setA6("E");
		 }if(parseObj.getA7()==null||parseObj.getA7().equals("")){
			 parseObj.setA7("1.5");
		 }
		 tlvmap.put("A1", parseObj.getA1());//接口类型  默认 A-交易
		 tlvmap.put("A2", parseObj.getA2());//商户号 米雅提供的商户号 
		 tlvmap.put("A3", parseObj.getA3());//门店账号 通常为商户门店号
		 tlvmap.put("A4", parseObj.getA4());//设备号 通常为商户门店 pos 机编号
		 tlvmap.put("A5", parseObj.getA5());//收银编号 通常为商户门店收银员编号 
		 tlvmap.put("A6", parseObj.getA6());//默认 E-撤销
		 tlvmap.put("A7", parseObj.getA7());//版本号 默认 1.5 
	
		 tlvmap.put("B1", parseObjItem.getB1());//商户订单号 商户侧生成的订单号，不可重复
		 String sign = MiyaPayUtil.createSign(keyValue, tlvmap);//生成签名
		 tlvmap.put("A8", sign);//签名 其它参数签名生成的摘要 
		 parseObj.setA8(sign);//=========设置签名值==============
		//转换xml
		 String urlStr = null;
		 urlStr = JaxbUtil.getAssemblyXml(parseObj,parseObjItem);
		 logger.info("urlStr:"+urlStr);
		 ///创建httpclient
		 String returnStr = JaxbUtil.createHttpClient(urlStr);
		 return returnStr;
	}
	/**
	 * 回调异步通知接口 
	 * @param jsonObj
	 * @throws Exception
	 */
	@Override
	public String callback(String jsonObj) throws Exception {
		System.out.println("jsonObj:"+jsonObj);
		orderStatusService.saveProcess("订单号码",OrderStatusAction.S013031,"操作人",new Date(),"备注");
		commonUtilService.saveIntfReceivedJson("订单号码",null,"sendPay",jsonObj,Long.parseLong(OrderStatusAction.S013031.getCode()));
		OrderPay orderPay = new OrderPay();
		orderPayService.save(orderPay);
		return jsonObj;
	}
	
}

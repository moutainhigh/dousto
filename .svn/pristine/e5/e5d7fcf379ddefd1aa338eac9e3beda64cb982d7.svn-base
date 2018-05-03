package com.ibm.oms.service.pay.intf.impl;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ibm.oms.service.pay.dto.PayOffLineDto;
import com.ibm.oms.service.pay.intf.OfflinePayService;
import com.miya.TcpSend;

import net.sf.json.JSONObject;
@Service
public class OfflinePayServiceImpl implements OfflinePayService{
	/**
	 * 下单支付
	 * @param jsonObj
	 * @throws Exception
	 */
	@Override
	public String sendPay(String jsonObj) throws Exception {
		PayOffLineDto parseObj = JSON.parseObject(jsonObj, PayOffLineDto.class);
		 Map<String, Object> tlvmap = new HashMap<String, Object>();
		    tlvmap.put("VERSION", "1.5");                                 //版本号      默认值 
	   		tlvmap.put("NUMID", parseObj.getNUMID());                     //门店号      pos传入参数
	   		tlvmap.put("USERID", parseObj.getUSERID());                   //pos机号  pos传入参数
	   		tlvmap.put("OPERATOR_ID",parseObj.getOPERATOR_ID());          //收银员ID pos传入参数
	   		tlvmap.put("PAYMENTPLATFORM", "A");                           //平台类型    默认值      
	   		tlvmap.put("SERVEICETYPE", "A");                              //操作类型    默认值  支付-A,查询-B，退货 -C,退货查询-D 撤销-E 
	   		tlvmap.put("OUT_TRADE_NO", parseObj.getOUT_TRADE_NO());       //商家订单号     pos传入参数
	   		tlvmap.put("TRADE_NO", parseObj.getTRADE_NO());               //POS 整单流水 号 
		    tlvmap.put("TOTAL_FEE", parseObj.getTOTAL_FEE());             //金额（分）  pos传入参数
		    tlvmap.put("GOODSDETAIL", parseObj.getGOODSDETAIL());         //格式：      商品码1,名称1,金额1,数量1|商品码2,名称2,金额2,数量2...两个商品之间用|符合分开，商品信息以,隔开。商品名称中请确保无 ,|两种符号。 例：690000000,大鹏牌啤酒,10.00,1|690000000,大鹏牌香烟（支）,100.00,1
		    tlvmap.put("PAYMENT_TYPE", "A");         					  //重码分类 :京东传B,大众点评传A,其他支付方式默认A或不传
		    tlvmap.put("F1", parseObj.getF1());                           //支付条码    pos传入参数
			tlvmap.put("path","c:\\miyajpos\\");			     		  //自定义目录  可空,默认c:\miyajpos\
			tlvmap.put("UN_DISCOUNTABLE","1");							  //不可优惠金额
			
			
			//{'NUMID':'F004','USERID':'miya','OPERATOR_ID':'','OUT_TRADE_NO':'','TRADE_NO':'','TOTAL_FEE':'','GOODSDETAIL':'690000000,大鹏牌啤酒,0.1,1','F1':''}
		Map requsetMap = TcpSend.sendMiya(tlvmap,null);
		String jsonObjData = OfflinePayServiceImpl.getJsonObjData(requsetMap);
		return jsonObjData;
	}
	/**
	 * 下单支付查询
	 * @param jsonObj
	 * @throws Exception
	 */
	@Override
	public String sendPayQuery(String jsonObj) throws Exception {
		PayOffLineDto parseObj = JSON.parseObject(jsonObj, PayOffLineDto.class);
		 Map<String, Object> tlvmap = new HashMap<String, Object>();
		    tlvmap.put("VERSION", "1.5");                                 //版本号      默认值 
	   		tlvmap.put("NUMID", parseObj.getNUMID());                     //门店号      pos传入参数
	   		tlvmap.put("USERID", parseObj.getUSERID());                   //pos机号  pos传入参数
	   		tlvmap.put("OPERATOR_ID",parseObj.getOPERATOR_ID());          //收银员ID pos传入参数
	   		tlvmap.put("PAYMENTPLATFORM", "A");                           //平台类型    默认值      
	   		tlvmap.put("SERVEICETYPE", "B");                              //操作类型    默认值  支付-A,查询-B，退货 -C,退货查询-D 撤销-E 
	   		tlvmap.put("OUT_TRADE_NO", parseObj.getOUT_TRADE_NO());       //商家订单号     pos传入参数
	   		tlvmap.put("TRADE_NO", parseObj.getTRADE_NO());               //POS 整单流水 号 
		    tlvmap.put("TOTAL_FEE", parseObj.getTOTAL_FEE());             //金额（分）  pos传入参数
			tlvmap.put("path","c:\\miyajpos\\");			     		  //自定义目录  可空,默认c:\miyajpos\
		Map requsetMap = TcpSend.sendMiya(tlvmap,null);
		String jsonObjData = OfflinePayServiceImpl.getJsonObjData(requsetMap);
		return jsonObjData;
	}
	/**
	 * 退款
	 * @param jsonObj
	 * @throws Exception
	 */
	@Override
	public String refundPay(String jsonObj) throws Exception {
		PayOffLineDto parseObj = JSON.parseObject(jsonObj, PayOffLineDto.class);
		 Map<String, Object> tlvmap = new HashMap<String, Object>();
		    tlvmap.put("VERSION", "1.5");                                 //版本号      默认值 
	   		tlvmap.put("NUMID", parseObj.getNUMID());                     //门店号      pos传入参数
	   		tlvmap.put("USERID", parseObj.getUSERID());                   //pos机号  pos传入参数
	   		tlvmap.put("OPERATOR_ID",parseObj.getOPERATOR_ID());          //收银员ID pos传入参数
	   		tlvmap.put("PAYMENTPLATFORM", "A");                           //平台类型    默认值      
	   		tlvmap.put("SERVEICETYPE", "C");                              //操作类型    默认值  支付-A,查询-B，退货 -C,退货查询-D 撤销-E 
	   		tlvmap.put("OUT_TRADE_NO", parseObj.getOUT_TRADE_NO());       //商家订单号     pos传入参数
	   		tlvmap.put("TRADE_NO", parseObj.getTRADE_NO());               //POS 整单流水 号 
		    tlvmap.put("TOTAL_FEE", parseObj.getTOTAL_FEE());             //金额（分）  pos传入参数
		    tlvmap.put("GOODSDETAIL", parseObj.getGOODSDETAIL());         //格式：      商品码1,名称1,金额1,数量1|商品码2,名称2,金额2,数量2...两个商品之间用|符合分开，商品信息以,隔开。商品名称中请确保无 ,|两种符号。 例：690000000,大鹏牌啤酒,10.00,1|690000000,大鹏牌香烟（支）,100.00,1
		    tlvmap.put("OUT_REQUEST_NO", parseObj.getOUT_REQUEST_NO());   //退货单号     pos传入参数
			tlvmap.put("path","c:\\miyajpos\\");			     		  //自定义目录  可空,默认c:\miyajpos\
			tlvmap.put("UN_DISCOUNTABLE","1");							  //不可优惠金额
		Map requsetMap = TcpSend.sendMiya(tlvmap,null);
		String jsonObjData = OfflinePayServiceImpl.getJsonObjData(requsetMap);
		return jsonObjData;
	}
	/**
	 * 退款查询
	 * @param jsonObj
	 * @throws Exception
	 */
	@Override
	public String refundQuery(String jsonObj) throws Exception {
		PayOffLineDto parseObj = JSON.parseObject(jsonObj, PayOffLineDto.class);
		 Map<String, Object> tlvmap = new HashMap<String, Object>();
		    tlvmap.put("VERSION", "1.5");                                 //版本号      默认值 
	   		tlvmap.put("NUMID", parseObj.getNUMID());                     //门店号      pos传入参数
	   		tlvmap.put("USERID", parseObj.getUSERID());                   //pos机号  pos传入参数
	   		tlvmap.put("OPERATOR_ID",parseObj.getOPERATOR_ID());          //收银员ID pos传入参数
	   		tlvmap.put("PAYMENTPLATFORM", "A");                           //平台类型    默认值      
	   		tlvmap.put("SERVEICETYPE", "D");                              //操作类型    默认值  支付-A,查询-B，退货 -C,退货查询-D 撤销-E 
	   		tlvmap.put("OUT_TRADE_NO", parseObj.getOUT_TRADE_NO());       //商家订单号     pos传入参数
	   		tlvmap.put("TRADE_NO", parseObj.getTRADE_NO());               //POS 整单流水 号 
		    tlvmap.put("TOTAL_FEE", parseObj.getTOTAL_FEE());             //金额（分）  pos传入参数
		    tlvmap.put("OUT_REQUEST_NO", parseObj.getOUT_REQUEST_NO());   //退货单号     pos传入参数
			tlvmap.put("path","c:\\miyajpos\\");			     		  //自定义目录  可空,默认c:\miyajpos\
			tlvmap.put("UN_DISCOUNTABLE","1");							  //不可优惠金额
		Map requsetMap = TcpSend.sendMiya(tlvmap,null);
		String jsonObjData = OfflinePayServiceImpl.getJsonObjData(requsetMap);
		return jsonObjData;
	}
	/**
	 * 撤销订单
	 * @param jsonObj
	 * @throws Exception
	 */
	@Override
	public String cancelOrder(String jsonObj) throws Exception {
		PayOffLineDto parseObj = JSON.parseObject(jsonObj, PayOffLineDto.class);
		 Map<String, Object> tlvmap = new HashMap<String, Object>();
		    tlvmap.put("VERSION", "1.5");                                 //版本号      默认值 
	   		tlvmap.put("NUMID", parseObj.getNUMID());                     //门店号      pos传入参数
	   		tlvmap.put("USERID", parseObj.getUSERID());                   //pos机号  pos传入参数
	   		tlvmap.put("OPERATOR_ID",parseObj.getOPERATOR_ID());          //收银员ID pos传入参数
	   		tlvmap.put("PAYMENTPLATFORM", "A");                           //平台类型    默认值      
	   		tlvmap.put("SERVEICETYPE", "E");                              //操作类型    默认值  支付-A,查询-B，退货 -C,退货查询-D 撤销-E 
	   		tlvmap.put("OUT_TRADE_NO", parseObj.getOUT_TRADE_NO());       //商家订单号     pos传入参数
	   		tlvmap.put("TRADE_NO", parseObj.getTRADE_NO());               //POS 整单流水 号 
		    tlvmap.put("TOTAL_FEE", parseObj.getTOTAL_FEE());             //金额（分）  pos传入参数
		    tlvmap.put("PAYMENT_TYPE", "A");          					  //重码分类  支付时特别注意大众 点评--A、京东—B 其他 支付方式可为空。因为 大众和京东付款码开 头 2 位相同，无法区 分，需要 POS 提示给 收银员区分. 券交易时，上送 Q 
			tlvmap.put("path","c:\\miyajpos\\");			     		  //自定义目录  可空,默认c:\miyajpos\
			tlvmap.put("UN_DISCOUNTABLE","1");							  //不可优惠金额
		 Map requsetMap = TcpSend.sendMiya(tlvmap,null);
		 String jsonObjData = OfflinePayServiceImpl.getJsonObjData(requsetMap);
		return jsonObjData;
	}

	public static String getJsonObjData(Map requsetMap) throws Exception {
			Map<String, Object> map = new HashMap<String, Object> ();
			map.put("RETCODE", requsetMap.get("RETCODE"));//说明 
			map.put("RETMSG", requsetMap.get("RETMSG"));//返回信息 
			map.put("VERSION", requsetMap.get("VERSION"));//版本号 
			map.put("TRCODE", requsetMap.get("TRCODE"));//交易代码 SAASID 
			map.put("SAASID", requsetMap.get("SAASID"));//商户编码 
			map.put("NUMID", requsetMap.get("NUMID"));//门店编码 
			map.put("USERID", requsetMap.get("USERID"));//机具编码 
			map.put("OPERATOR_ID", requsetMap.get("OPERATOR_ID"));//操作员 ID 
			map.put("PAYMENTPLATFORM", requsetMap.get("PAYMENTPLATFORM"));//操作平台 应答渠道平台标志 1-微信,3-支付宝, 4-百度,5-翼支付, 6-QQ 钱包,A-大众点评 C-京东 K-工行 L-飞凡 M-华润银行 N银联钱包  
			map.put("SERVEICETYPE", requsetMap.get("SERVEICETYPE"));//操作类型 支付-A,查询-B， 退货-C,退货查询-D 
			map.put("OUT_TRADE_NO", requsetMap.get("OUT_TRADE_NO"));//商家订单号 
			map.put("SUBJECT", requsetMap.get("SUBJECT"));//应答返回买家账号 
			map.put("TOTAL_FEE", requsetMap.get("TOTAL_FEE"));//支付总金额 
			map.put("OUT_REQUEST_NO", requsetMap.get("OUT_REQUEST_NO"));//退货单号. 支付宝或者查询时，支付宝返回 buyer_user_id， 大众为 800 码 
			map.put("TRADE_NO", requsetMap.get("TRADE_NO"));//交易号 
			map.put("TRANSTIME", requsetMap.get("TRANSTIME"));//交易时间 
			map.put("GOODSTAG", requsetMap.get("GOODSTAG"));//返回平台名称 
			map.put("SINGLE_PRODUCT", requsetMap.get("SINGLE_PRODUCT"));//单品优惠返回格式： 商品 69 码, 商品名称,优惠金额,voucher_id(券码),[券 名称,券类型(1-单品券 2-品牌满减券；3-全场抵用券), 券总面额,......]数据可能为空，字段可能扩展 返回为 690034333040432,商品 1,1.00,2015102600073002039000002D5O,超市啤酒 券,2,3.00|      690034333040434,商品 2,2.00,2015102600073002039000002D5O,超市啤酒 券,2,3.00|, ,,2015102600073002039000001234,全场 代金券,3,15.00 如果数据包含多组用“|”分开。 
			map.put("F1", requsetMap.get("F1"));//订单描述 
			map.put("F2", requsetMap.get("F2"));//应答复用 fundbilllist 消费费用来源（格式：[实 扣金额|商户优惠金额|平台优惠金额|预留|预留| 预留]） 
			JSONObject jsonObject = JSONObject.fromObject(requsetMap);  
	        System.out.println("输出的结果是：" + jsonObject);  
	        String result = jsonObject.toString();  
        return result;
	}

	

}

package com.ibm.oms.dao.constant;

import java.util.ArrayList;
import java.util.List;

public enum IntfReceiveConst {
    Order_Receive_Payment("RECEIVE_PAYMENT_BTC", "btc/bbc支付接收"),
	Order_Receive_Payment_Btc("BTC_RECEIVE_PAYMENT_BTC", "btc支付"),
	Order_Receive_Payment_Bbc("BBC_RECEIVE_PAYMENT_BBC", "bbc支付"),
	Order_Receive_Payment_Error("BTC_Receive_Payment_Error", "异常支付"), 
	Order_Receive_Cost_Price("WMS_RECEIVE_COST_PRICE", "接收WMS出库成本"),
	Order_Receive_Inspect_Status("WMS_INSPECT_STATUS", "接收WMS质检信息"), 
	Wms_Oms_Receive_Logistics("WMS_OMS_LOGISTICS", "接收WMS库存状态"),
	Tms_Pay_to_Oms("TMS_PAY_TO_OMS", "接收TMS付款信息"),
    TMS_STATUS_TO_OMS("TMS-STATUS-TO-OMS","tms状态到OMS"),
    TMS_THIRD_STATUS_TO_OMS("TMS-THIRD-STATUS-TO-OMS","tms第三方状态到OMS"),
    TMS_STATUS_TO_OMS_T("TMS-STATUS-TO-OMS-T","第三方tms状态到OMS"),
    BBC_Operate ("I-BBC-OMS-01","BBC操作1门店收货/2已收/3拒收"),
    BBC_Logistics ("I-BBC-OMS-02","BBC已发货"),
    /**
     * b2c退货订单接收接口编号code
     */
    BTC_OMS_RETURN_ORDER ("BTC_OMS_RETURN_ORDER","b2c退货订单"),
    /**
     * b2c换货订单接收接口编号code
     */
    BTC_OMS_CHANGE_ORDER ("BTC_OMS_CHANGE_ORDER","b2c换货订单接收接口"),
    /**
     * b2c取消订单
     */
    BTC_OMS_CANCEL_ORDER ("BTC_OMS_CANCEL_ORDER","b2c取消订单"),
    BTC_OMS_RECEIVE("I-BTC-OMS-01","btc下单"), 
    OMS_RECEIVE("I-BTC-OMS-%s","%s下单"),
   
    
    GET_SUCCESS("GET_SUCCESS","TMS揽收成功"),
    SEND_ORDER("SEND_ORDER","TMS已派单送货中"),
    SIGN_SUCCESS("SIGN_SUCCESS","TMS成功签收"),
    SIGN_FAIL("SIGN_FAIL","TMS拒收"),
    //中台-百胜-拆单回传物流单号接口
    ORDER_STATUS_RETURN_BS("BS-C-05","中台-百胜-拆单回传物流单号");
    
	private String code;
	private String desc;

	private IntfReceiveConst(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public static List<IntfReceiveConst>  getAll(){  
		List<IntfReceiveConst> allList = new ArrayList<IntfReceiveConst>();
		allList.add(null);
        for (IntfReceiveConst s : IntfReceiveConst.values()){  
            allList.add(s);
        }    
	    return allList;
	}  

}

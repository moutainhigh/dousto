package com.ibm.oms.dao.constant;

import java.util.ArrayList;
import java.util.List;

public enum IntfSentConst {
    /**
     * OMS传输售后意向单至TMS
     */
    OMS_TMS_SaleAfterOrder("OMS_TMS_SaleAfterOrder","OMS传输售后意向单至TMS"),//OMS-TMS-SALEAFTER-ORDER
   
   /**
    * OMS传输售后意向单至WMS
    */
    OMS_WMS_SaleAfterOrder("OMS_WMS_SaleAfterOrder","OMS传输售后意向单至WMS"), //OMS_WMS-SALEAFTER_ORDER
   
    /**
     * OMS传输取消的售后意向单至WMS
     */
   OMS_WMS_SaleAfterOrder_Cancel("OMS_WMS_SaleAfterOrder_Cancel","OMS将取消入库的意向单传输至WMS"),
   
   /**
    * OMS传输取消的售后意向单至TMS
    */
   OMS_TMS_SaleAfterOrder_Cancel("OMS_TMS_SaleAfterOrder_Cancel","OMS将取消入库的意向单传输至TMS"),
   
   TMS_PAY_SENDER("TMS-PAY-SENDER","测试用:TMS支付发送"),
   TMS_STATUS_SENDER("TMS-STATUS-SENDER","测试用:TMS状态发送"),
   OMS_TMS_ORDER("I-OMS-TMS_03","oms订单发送tms"),
   OMS_LOGISTICS_01("I-OMS-LOGISTICS-01","物流商选择"),
   
   OMS_SIM_LOCK("I-OMS-SIM-01","锁定库存"),
   OMS_SIM_UNLOCK("I-OMS-SIM-02","释放库存"),
   OMS_SIM_DEDUCT("I-OMS-SIM-03","库存扣减/出库"),
   OMS_SIM_CANCEL("I-OMS-SIM-04","取消出库"),
   OMS_SIM_SEARCH("I-OMS-SIM-06","库存查询"),
   OMS_PRODUCT_COMBINE("I-OMS-PRODUCT-01","组合商品查询"),

    OMS_MEM_01("I_OMS_MEM_01","会员黑名单查询"), 
    OMS_MEM_02("I_OMS_MEM_02","会员帐户my卡加回(积分加回|扣减)"), 
    OMS_MEM_03("I_OMS_MEM_03","会员帐户my卡扣减"),
    OMS_MEM_04("I_OMS_MEM_04","会员黑名单添加"),
    OMS_MEM_VIPCARD("OMS_MEM_VIPCARD","会员VIP账户查询"), 
    OMS_PROMO_01("I_OMS_PROMO_01","促销资源扣减"), 
    OMS_SIM_RESEND("I-OMS-SIM-05","虚拟商品重发短信"), 
    OMS_PROMO("I-OMS-PROMO-01","购物券使用扣减"), 
    OMS_PROMO_ADD("I-OMS-PROMO-02","促销送购物券"), 
    OMS_PROMO_REMOVE("I-OMS-PROMO-03","因订单取消返还购物券"), 
    OMS_SUP_RESEND("I-OMS-SUP-01","自提商品重发短信"),
    OMS_SUP_RESEND_24HOURS("I-OMS-SUP-02","24小时未支付提醒短信"),
    CM_OMS_POST_FEE_RET("I-OMS-SUP-04","运费补款已生效"),
    OMS_SUP_SEND_EMAIL("I-OMS-SUP-03","库存扣减之后发送邮件"),
    OMS_SUP_SEND_SMS("I-OMS-SUP-SEND-SMS","发送订单短信"),
    OMS_STATUS_UPDATE("I-OMS-STATUS-UPDATE","订单状态更新主题"),
    
    OMS_STATUS_SENDTO_WEIDIAN("I-OMS-STATUS-SENDTO-WEIDIAN","订单状态发送给微店");
	private String code;
	private String desc;

	private IntfSentConst(String code,String desc) {
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
	public static List<IntfSentConst>  getAll(){  
		List<IntfSentConst> allList = new ArrayList<IntfSentConst>();
		allList.add(null);
        for (IntfSentConst s : IntfSentConst.values()){  
            allList.add(s);
        }    
	    return allList;
	}  
	
}

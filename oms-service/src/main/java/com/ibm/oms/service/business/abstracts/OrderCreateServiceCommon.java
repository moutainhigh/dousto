package com.ibm.oms.service.business.abstracts;

import org.springframework.stereotype.Component;

import com.ibm.oms.client.constant.OrderMainConstClient;

@Component
public class OrderCreateServiceCommon  {
	   // 会员黑名单查询
	
    private String mem01;
    //会员积分扣减
    private String mem02;
    // 会员资源扣减
    private String mem03;
    // 添加会员黑名单
    private String mem04;
    private String sup01;
    //
    private String wms04;
    /** lock **/
    private String sim01;
    /** unlock **/
    private String sim02;
    /** deduct **/
    private String sim03;
    /** cancel **/
    private String sim04;
    /** virtual order resend message **/
    private String sim05;
    /** promo deduct **/
    private String promo01;
    /** promo add **/
    private String promo02;
    /** promo remove **/
    private String promo03;
    /** promo send finishOrder **/
    private String promo04;
    /** combine **/
    private String product01;
    /** logistic merchant selection **/
    private String logistics01;
    private String autoAuditSwitch;
    
    
	
	
	

	
	public String getMem01() {
		return mem01;
	}

	public void setMem01(String mem01) {
		this.mem01 = mem01;
	}

	public String getMem02() {
		return mem02;
	}

	public void setMem02(String mem02) {
		this.mem02 = mem02;
	}

	public String getMem03() {
		return mem03;
	}

	public void setMem03(String mem03) {
		this.mem03 = mem03;
	}

	public String getMem04() {
		return mem04;
	}

	public void setMem04(String mem04) {
		this.mem04 = mem04;
	}

	public String getSup01() {
		return sup01;
	}

	public void setSup01(String sup01) {
		this.sup01 = sup01;
	}

	public String getWms04() {
		return wms04;
	}

	public void setWms04(String wms04) {
		this.wms04 = wms04;
	}

	public String getSim01() {
		return sim01;
	}

	public void setSim01(String sim01) {
		this.sim01 = sim01;
	}

	public String getSim02() {
		return sim02;
	}

	public void setSim02(String sim02) {
		this.sim02 = sim02;
	}

	public String getSim03() {
		return sim03;
	}

	public void setSim03(String sim03) {
		this.sim03 = sim03;
	}

	public String getSim04() {
		return sim04;
	}

	public void setSim04(String sim04) {
		this.sim04 = sim04;
	}

	public String getSim05() {
		return sim05;
	}

	public void setSim05(String sim05) {
		this.sim05 = sim05;
	}

	public String getPromo01() {
		return promo01;
	}

	public void setPromo01(String promo01) {
		this.promo01 = promo01;
	}

	public String getPromo02() {
		return promo02;
	}

	public void setPromo02(String promo02) {
		this.promo02 = promo02;
	}

	public String getPromo03() {
		return promo03;
	}

	public void setPromo03(String promo03) {
		this.promo03 = promo03;
	}

	public String getPromo04() {
		return promo04;
	}

	public void setPromo04(String promo04) {
		this.promo04 = promo04;
	}

	public String getProduct01() {
		return product01;
	}

	public void setProduct01(String product01) {
		this.product01 = product01;
	}

	public String getLogistics01() {
		return logistics01;
	}

	public void setLogistics01(String logistics01) {
		this.logistics01 = logistics01;
	}

	public String getAutoAuditSwitch() {
		return autoAuditSwitch;
	}

	public void setAutoAuditSwitch(String autoAuditSwitch) {
		this.autoAuditSwitch = autoAuditSwitch;
	}

	public boolean isWX(String orderResource){
		if(OrderMainConstClient.OrderMain_Ordersource_WX.getCode().equals(orderResource)){
			return true;
		}
		return false;
	}
	
	public boolean isLs(String orderResource){
		if(OrderMainConstClient.OrderMain_Ordersource_LS.getCode().equals(orderResource)){
			return true;
		}
		return false;
	}
	
	public boolean isDG(String orderResource){
		if(OrderMainConstClient.OrderMain_Ordersource_DG.getCode().equals(orderResource)){
			return true;
		}
		return false;
	}
	
	@Deprecated
	public boolean isBS(String orderResource){
		if(OrderMainConstClient.OrderMain_Ordersource_BS.getCode().equals(orderResource)){
			return true;
		}
		return false;
	}
	
}

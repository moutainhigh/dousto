package com.ibm.oms.intf.intf;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.NotBlank;


/**
 * @author JJL
 * BBC定时自动修改订单状态和 物流状态（对于超过一定时间的订单需要自动封闭订单）
 */
@XmlType
@XmlRootElement(name = "Order")  
@XmlAccessorType(XmlAccessType.PROPERTY)
public class BBCUpdateOrderStatusDTO implements Serializable {


	private static final long serialVersionUID = -1078711606338312919L;

	@NotBlank(message = "orderSubNo is compulsory")
    String orderSubNo;//订单号

    String operateName; //操作人姓名
    
    String ip;//ip
    
    String merchantNo;//商家编号

      
	public String getOrderSubNo() {
		return orderSubNo;
	}

	public void setOrderSubNo(String orderSubNo) {
		this.orderSubNo = orderSubNo;
	}


	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}
     	
	
}

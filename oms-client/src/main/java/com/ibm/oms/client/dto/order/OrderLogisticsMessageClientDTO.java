package com.ibm.oms.client.dto.order;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

/**
 * Description: //模块目的、功能描述  
 * @author Yusl
 * Date:   2018年3月1日 
 */
public class OrderLogisticsMessageClientDTO implements Serializable{

    private java.lang.String orderNo;
    //订单号
   
    

    private java.util.Date wmsTime;
    //时间点
    
    

    private java.lang.String wmsDesc;
    //物流描述    



    //物流单号
    private java.lang.String teackingNumber;

    //物流状态
    private java.lang.String state;
    

    //该信息物流状态
    private java.lang.String status;
	public java.lang.String getOrderNo() {
		return orderNo;
	}



	public void setOrderNo(java.lang.String orderNo) {
		this.orderNo = orderNo;
	}



	public Date getWmsTime() {
		return wmsTime;
	}



	public void setWmsTime(Date wmsTime) {
		this.wmsTime = wmsTime;
	}



	public java.lang.String getWmsDesc() {
		return wmsDesc;
	}



	public void setWmsDesc(java.lang.String wmsDesc) {
		this.wmsDesc = wmsDesc;
	}



	public java.lang.String getTeackingNumber() {
		return teackingNumber;
	}



	public void setTeackingNumber(java.lang.String teackingNumber) {
		this.teackingNumber = teackingNumber;
	}



	public java.lang.String getState() {
		return state;
	}



	public void setState(java.lang.String state) {
		this.state = state;
	}



	public java.lang.String getStatus() {
		return status;
	}



	public void setStatus(java.lang.String status) {
		this.status = status;
	}
    
    
}

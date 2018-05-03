package com.ibm.oms.domain.persist;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * Description: //物流信息
 * @author Yusl
 * Date:   2018年3月1日 
 */

@Entity
@Table(name = "ORDER_LOGISTICS_MESSAGE")
public class OrderLogisticsMessage implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @TableGenerator(name = "order_logistics_message", table = "sys_id", pkColumnName = "key_id", valueColumnName = "key_value", pkColumnValue = "order_logistics_message_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "order_logistics_message")
    @Column(unique = true, nullable = false, precision = 22)
    private java.lang.Long id;
    
    @Column(name = "ORDER_NO")
    private java.lang.String orderNo;
    

    @Column(name = "ORDER_SUB_NO")
    private java.lang.String orderSubNo;
    
    //时间点
    @Column(name = "WMS_TIME")
    private java.util.Date wmsTime;
    
    
    //物流描述    
    @Column(name = "WMS_DESC")
    private java.lang.String wmsDesc;


    //物流单号
    @Column(name = "TRACKING_NUMBER")
    private java.lang.String teackingNumber;

    //物流状态
    @Column(name = "STATE")
    private java.lang.String state;
    

    //物流状态名
    @Column(name = "STATUS")
    private java.lang.String status;
    
	public java.lang.Long getId() {
		return id;
	}


	public void setId(java.lang.Long id) {
		this.id = id;
	}


	public java.lang.String getOrderNo() {
		return orderNo;
	}


	public void setOrderNo(java.lang.String orderNo) {
		this.orderNo = orderNo;
	}


	public java.lang.String getOrderSubNo() {
		return orderSubNo;
	}


	public void setOrderSubNo(java.lang.String orderSubNo) {
		this.orderSubNo = orderSubNo;
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

package com.ibm.oms.domain.persist;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * Description: //模块目的、功能描述  
 * @author Yusl
 * Date:   2018年3月15日 
 */
@Entity
@Table(name = "ORDER_LOGISTICS")
public class OrderLogistics implements Serializable{
	

    private static final long serialVersionUID = 1L;
    @Id
    @TableGenerator(name = "order_logistics", table = "sys_id", pkColumnName = "key_id", valueColumnName = "key_value", pkColumnValue = "order_logistics_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "order_logistics")
    @Column(unique = true, nullable = false, precision = 22)
    private java.lang.Long id;
    

    @Column(name = "ORDER_NO")
    private java.lang.String orderNo;

    @Column(name = "ID_ORDER")
    private java.lang.Long idOrder;

    @Column(name = "ORDER_SUB_NO")
    private java.lang.String orderSubNo;
    
    //快递单号
    @Column(name = "TRACKING_NUMBER")
    private java.lang.String trackingNumber;
    
    
    //监控状态
    @Column(name = "MONITORING_STATUS")
    private java.lang.String monitoringStatus;
    //监控状态相关消息
    @Column(name = "MONITORING_MESSAGE")
    private java.lang.String monitoringMessage;
    
    //快递公司编码是否出错 0=正确 1=错误
    @Column(name = "AUTO_CHECK")
    private java.lang.String autoCheck;
    
    //大东提交的原始的快递公司编码
    @Column(name = "COM_OLD")
    private java.lang.String comOld;
    
    //快递100纠正后的新的快递公司编码
    @Column(name = "COM_NEW")
    private java.lang.String comNew;
    
    //快递单当前签收状态
    @Column(name = "STATE")
    private java.lang.String state;
    
    //快递单明细状态标记    
    @Column(name = "CONDITIONS")
    private java.lang.String conditions;
    
    //是否签收标记    
    @Column(name = "ISCHECK")
    private java.lang.String ischeck;

    //快递公司编码
    @Column(name = "COM")
    private java.lang.String com;

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

	public java.lang.String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(java.lang.String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public java.lang.String getMonitoringStatus() {
		return monitoringStatus;
	}

	public void setMonitoringStatus(java.lang.String monitoringStatus) {
		this.monitoringStatus = monitoringStatus;
	}

	public java.lang.String getMonitoringMessage() {
		return monitoringMessage;
	}

	public void setMonitoringMessage(java.lang.String monitoringMessage) {
		this.monitoringMessage = monitoringMessage;
	}

	public java.lang.String getAutoCheck() {
		return autoCheck;
	}

	public void setAutoCheck(java.lang.String autoCheck) {
		this.autoCheck = autoCheck;
	}

	public java.lang.String getComOld() {
		return comOld;
	}

	public void setComOld(java.lang.String comOld) {
		this.comOld = comOld;
	}

	public java.lang.String getComNew() {
		return comNew;
	}

	public void setComNew(java.lang.String comNew) {
		this.comNew = comNew;
	}

	public java.lang.String getState() {
		return state;
	}

	public void setState(java.lang.String state) {
		this.state = state;
	}


	public java.lang.String getConditions() {
		return conditions;
	}

	public void setConditions(java.lang.String conditions) {
		this.conditions = conditions;
	}

	public java.lang.String getIscheck() {
		return ischeck;
	}

	public void setIscheck(java.lang.String ischeck) {
		this.ischeck = ischeck;
	}

	public java.lang.String getCom() {
		return com;
	}

	public void setCom(java.lang.String com) {
		this.com = com;
	}

	public java.lang.Long getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(java.lang.Long idOrder) {
		this.idOrder = idOrder;
	}
	
	
}

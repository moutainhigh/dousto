package com.ibm.oms.domain.persist;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "wholesale_order_status")
public class WholesaleOrderStatus implements Serializable {

    private static final long serialVersionUID = -6516180780077341576L;
    @Id
    @TableGenerator(name = "wholesale_order_status", table = "sys_id", pkColumnName = "key_id", valueColumnName = "key_value", pkColumnValue = "wholesale_order_status_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "wholesale_order_status")
    @Column(unique = true, nullable = false, precision = 22)
    private Long id;
    @Column(name = "WMS_BILL_CODE")
    private String wmsBillCode;
    @Column(name = "WARE_HOUSE_CODE")
    private String wareHouseCode;
    @Column(name = "ASN_CODE")
    private String asnCode;
    @Column(name = "ASN_STATUS")
    private String asnStatus;
    @Column(name = "IS_ASN_FINISHED")
    private String isAsnFinished;
    @Column(name = "NOTE")
    private String note;
    @Column(name = "OPERATOR")
    private String operator;
    @Column(name = "OPERATOR_TIME")
    private Date operatorTime;
    @Column(name = "LOGISTICS_PROVIDER_CODE")
    private String logisticsProviderCode;
    @Column(name = "SHIPPING_ORDER_NO")
    private String shippingOrderNo;
    @Column(name = "EXCEPTION_CODE")
    private String exceptionCode;
    @Column(name = "CALL_BACK")
    private String callBack;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWmsBillCode() {
        return wmsBillCode;
    }

    public void setWmsBillCode(String wmsBillCode) {
        this.wmsBillCode = wmsBillCode == null ? null : wmsBillCode.trim();
    }

    public String getWareHouseCode() {
        return wareHouseCode;
    }

    public void setWareHouseCode(String wareHouseCode) {
        this.wareHouseCode = wareHouseCode == null ? null : wareHouseCode.trim();
    }

    public String getAsnCode() {
        return asnCode;
    }

    public void setAsnCode(String asnCode) {
        this.asnCode = asnCode == null ? null : asnCode.trim();
    }

    public String getAsnStatus() {
        return asnStatus;
    }

    public void setAsnStatus(String asnStatus) {
        this.asnStatus = asnStatus == null ? null : asnStatus.trim();
    }

    public String getIsAsnFinished() {
        return isAsnFinished;
    }

    public void setIsAsnFinished(String isAsnFinished) {
        this.isAsnFinished = isAsnFinished == null ? null : isAsnFinished.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    public String getLogisticsProviderCode() {
        return logisticsProviderCode;
    }

    public void setLogisticsProviderCode(String logisticsProviderCode) {
        this.logisticsProviderCode = logisticsProviderCode == null ? null : logisticsProviderCode.trim();
    }

    public String getShippingOrderNo() {
        return shippingOrderNo;
    }

    public void setShippingOrderNo(String shippingOrderNo) {
        this.shippingOrderNo = shippingOrderNo == null ? null : shippingOrderNo.trim();
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode == null ? null : exceptionCode.trim();
    }

    public String getCallBack() {
        return callBack;
    }

    public void setCallBack(String callBack) {
        this.callBack = callBack == null ? null : callBack.trim();
    }
}
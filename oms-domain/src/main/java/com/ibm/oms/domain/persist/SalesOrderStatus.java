package com.ibm.oms.domain.persist;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import java.io.Serializable;
import java.util.Date;

/**
 * @author: mr.kai
 * @Description: 销售订单状态(oms同步数据)
 * @create: 2018-03-15 20:08
 **/
@Entity
@Table(name = "sales_order_status")
public class SalesOrderStatus implements Serializable {
    @Id
    @TableGenerator(name = "sales_order_status", table = "sys_id", pkColumnName = "key_id", valueColumnName = "key_value", pkColumnValue = "sales_order_status_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "sales_order_status")
    @Column(unique = true, nullable = false, precision = 22)
    private java.lang.Long id;
    @Column(name = "ORDER_NO")
    private String orderNo;//发货单号
    @Column(name = "ORDER_STATUS")
    private String orderStatus;//发货单物流状态：WMS_ACCEPT-接单成功（异步接口回传）WMS_REJECT-接单失败（异步接口回传）WMS_PRINT-打印 WMS_PICK-分拣成功 WMS_CHECK-验货成功
    //DELIVERED-已发货 WMS_WEIGHT-已称重 EXCEPTION-异常 CLOSED-关闭 WMS_OUT_STOCK–拣货缺货 WMS_CHANGE_SHIPPING-修改快递 WMS_CANCEL_SHIPPING-快递取消交接
    @Column(name = "OPERATOR")
    private String operator;//操作人
    @Column(name = "OPERATOR_TIME")
    private Date operatorTime;//操作时间（Y-m-d H:i:s）
    @Column(name = "LOGISTICS_PROVIDER_CODE")
    private String logisticsProviderCode;//物流公司代码
    @Column(name = "SHIPPING_ORDER_NO")
    private String shippingOrderNo;//运单号
    @Column(name = "NOTE")
    private String note;//备注或失败原因
    @Column(name = "WEIGHT")
    private Integer weight;//称重重量
    @Column(name = "VOLUME")
    private String volume;//称重体积
    @Column(name = "EXCEPTION_CODE")
    private String exceptionCode;//发货异常编码：INSUFFICIENT_INVENTORY-库存不足 OTHER-其他异常
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getWeight() {
        return weight;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getLogisticsProviderCode() {
        return logisticsProviderCode;
    }

    public void setLogisticsProviderCode(String logisticsProviderCode) {
        this.logisticsProviderCode = logisticsProviderCode;
    }

    public String getShippingOrderNo() {
        return shippingOrderNo;
    }

    public void setShippingOrderNo(String shippingOrderNo) {
        this.shippingOrderNo = shippingOrderNo;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
}

package com.ibm.oms.domain.persist;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * 
 * This class is used to represent available ORDER_INVOICE_PRINT in the database.</p>
 * 
 * 
 */
@Entity
@Table(name = "ORDER_INVOICE_PRINT")
public class OrderInvoicePrint implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @TableGenerator(name = "order_invoice_print", table = "sys_id", pkColumnName = "key_id", valueColumnName = "key_value", pkColumnValue = "order_invoice_print_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "order_invoice_print")
    @Column(unique = true, nullable = false, precision = 22)
    private java.lang.Long id;
    @Column(name = "ID_ORDER")
    private java.lang.Long idOrder;
    @Column(name = "ORDER_NO")
    private java.lang.String orderNo;
    @Column(name = "ID_ORDER_SUB")
    private java.lang.Long idOrderSub;
    @Column(name = "ORDER_SUB_NO")
    private java.lang.String orderSubNo;
    @Column(name = "INVOICE_NO")
    private java.lang.String invoiceNo;
    @Column(name = "TAX_NO")
    private java.lang.String taxNo;
    @Column(name = "INVOICE_PRINT_COUNT")
    private java.lang.Long invoicePrintCount;
    @Column(name = "INVOICE_PRINT_SHOP")
    private java.lang.String invoicePrintShop;
    @Column(name = "INVOICE_PRINT_TIME")
    private java.util.Date invoicePrintTime;
    @Column(name = "INVOICE_PRINT_PERSON_NO")
    private java.lang.String invoicePrintPersonNo;
    @Column(name = "INVOICE_PRINT_PERSON_NAME")
    private java.lang.String invoicePrintPersonName;
    @Column(name = "INVOICE_PRINT_TYPE")
    private java.lang.String invoicePrintType;
    @Column(name = "INVOICE_HEAD")
    private java.lang.String invoiceHead;
    @Column(name = "INVOICE_TRADER")
    private java.lang.String invoiceTrader;
    @Column(name = "CASHIER")
    private java.lang.String CASHIER;
    @Column(name = "INVOICE_PROJECT")
    private java.lang.String invoiceProject;
    @Column(name = "UNIT_PRICE")
    private BigDecimal unitPrice;
    @Column(name = "QUANTITY")
    private BigDecimal QUANTITY;
    @Column(name = "AMOUNT")
    private BigDecimal AMOUNT;
    @Column(name = "TOTAL_AMOUNT")
    private BigDecimal totalAmount;
    @Column(name = "CREATE_BY")
    private java.lang.String createBy;
    @Column(name = "CREATE_TIME")
    private java.util.Date createTime;
    @Column(name = "LAST_UPDATE_BY")
    private java.lang.String lastUpdateBy;
    @Column(name = "LAST_UPDATE_TIME")
    private java.util.Date lastUpdateTime;
    @Column(name = "MARK_FOR_DELETE")
    private java.lang.Long markForDelete;
    @Column(name = "REMARK")
    private java.lang.String remark;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ORDER", insertable = false, updatable = false)
    private OrderMain orderMain;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ORDER_SUB", insertable = false, updatable = false)
    private OrderSub orderSub;

    /**
     * default constructor
     */
    public OrderInvoicePrint() {
    }

    /**
     * The value of the id association.
     * 
     * @return java.lang.Long Return the value of the id column.
     */
    public java.lang.Long getId() {
        return id;
    }

    /**
     * Set the value of the id.
     * 
     * @param id
     */
    public void setId(java.lang.Long newId) {
        this.id = newId;
    }

    public java.lang.Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(java.lang.Long idOrder) {
        this.idOrder = idOrder;
    }

    public java.lang.String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(java.lang.String orderNo) {
        this.orderNo = orderNo;
    }

    public java.lang.String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(java.lang.String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public java.lang.String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(java.lang.String taxNo) {
        this.taxNo = taxNo;
    }

    public java.lang.Long getInvoicePrintCount() {
        return invoicePrintCount;
    }

    public void setInvoicePrintCount(java.lang.Long invoicePrintCount) {
        this.invoicePrintCount = invoicePrintCount;
    }

    public java.lang.String getInvoicePrintShop() {
        return invoicePrintShop;
    }

    public void setInvoicePrintShop(java.lang.String invoicePrintShop) {
        this.invoicePrintShop = invoicePrintShop;
    }

    public java.util.Date getInvoicePrintTime() {
        return invoicePrintTime;
    }

    public void setInvoicePrintTime(java.util.Date invoicePrintTime) {
        this.invoicePrintTime = invoicePrintTime;
    }

    public java.lang.String getInvoicePrintPersonNo() {
        return invoicePrintPersonNo;
    }

    public void setInvoicePrintPersonNo(java.lang.String invoicePrintPersonNo) {
        this.invoicePrintPersonNo = invoicePrintPersonNo;
    }

    public java.lang.String getInvoicePrintPersonName() {
        return invoicePrintPersonName;
    }

    public void setInvoicePrintPersonName(java.lang.String invoicePrintPersonName) {
        this.invoicePrintPersonName = invoicePrintPersonName;
    }

    public java.lang.String getInvoicePrintType() {
        return invoicePrintType;
    }

    public void setInvoicePrintType(java.lang.String invoicePrintType) {
        this.invoicePrintType = invoicePrintType;
    }

    public java.lang.String getInvoiceHead() {
        return invoiceHead;
    }

    public void setInvoiceHead(java.lang.String invoiceHead) {
        this.invoiceHead = invoiceHead;
    }

    public java.lang.String getInvoiceTrader() {
        return invoiceTrader;
    }

    public void setInvoiceTrader(java.lang.String invoiceTrader) {
        this.invoiceTrader = invoiceTrader;
    }

    public java.lang.String getCASHIER() {
        return CASHIER;
    }

    public void setCASHIER(java.lang.String cASHIER) {
        CASHIER = cASHIER;
    }

    public java.lang.String getInvoiceProject() {
        return invoiceProject;
    }

    public void setInvoiceProject(java.lang.String invoiceProject) {
        this.invoiceProject = invoiceProject;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getQUANTITY() {
        return QUANTITY;
    }

    public void setQUANTITY(BigDecimal qUANTITY) {
        QUANTITY = qUANTITY;
    }

    public BigDecimal getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(BigDecimal aMOUNT) {
        AMOUNT = aMOUNT;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public java.lang.String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(java.lang.String createBy) {
        this.createBy = createBy;
    }

    public java.util.Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public java.lang.String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(java.lang.String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public java.util.Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(java.util.Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public java.lang.Long getMarkForDelete() {
        return markForDelete;
    }

    public void setMarkForDelete(java.lang.Long markForDelete) {
        this.markForDelete = markForDelete;
    }

    public java.lang.String getRemark() {
        return remark;
    }

    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }

    public OrderMain getOrderMain() {
        return orderMain;
    }

    public void setOrderMain(OrderMain orderMain) {
        this.orderMain = orderMain;
    }

    public OrderSub getOrderSub() {
        return orderSub;
    }

    public void setOrderSub(OrderSub orderSub) {
        this.orderSub = orderSub;
    }

    public java.lang.Long getIdOrderSub() {
        return idOrderSub;
    }

    public void setIdOrderSub(java.lang.Long idOrderSub) {
        this.idOrderSub = idOrderSub;
    }

    public java.lang.String getOrderSubNo() {
        return orderSubNo;
    }

    public void setOrderSubNo(java.lang.String orderSubNo) {
        this.orderSubNo = orderSubNo;
    }

}

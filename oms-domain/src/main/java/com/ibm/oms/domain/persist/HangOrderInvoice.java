package com.ibm.oms.domain.persist;

import java.io.Serializable;

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
 * This class is used to represent available ORDER_INVOICE in the database.</p>
 * 
 * 
 */
@Entity
@Table(name = "TEMP_ORDER_INVOICE")
public class HangOrderInvoice implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @TableGenerator(name = "temp_order_invoice", table = "sys_id", pkColumnName = "key_id", valueColumnName = "key_value", pkColumnValue = "temp_order_invoice_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "temp_order_invoice")
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
    @Column(name = "ALIAS_ORDER_NO")
    private java.lang.Long aliasOrderNo;
    @Column(name = "ORDER_SOURCE")
    private java.lang.String orderSource;
    @Column(name = "INVOICE_TYPE")
    private java.lang.String invoiceType;
    @Column(name = "INVOICE_HEAD")
    private java.lang.String invoiceHead;
    @Column(name = "INVOICE_CONTENT")
    private java.lang.String invoiceContent;
    @Column(name = "INVOICE_ADDITION_INFO")
    private java.lang.String invoiceAdditionInfo;
    @Column(name = "INVOICE_NUM")
    private java.lang.String invoiceNum;
    @Column(name = "BILL_TYPE")
    private java.lang.Long billType;
    @Column(name = "REMARK")
    private java.lang.String remark;
    @Column(name = "IS_DELETED")
    private java.lang.Long isDeleted;
    @Column(name = "CREATED_BY")
    private java.lang.String createdBy;
    @Column(name = "UPDATED_BY")
    private java.lang.String updatedBy;
    @Column(name = "DATE_CREATED")
    private java.util.Date dateCreated;
    @Column(name = "DATE_UPDATED")
    private java.util.Date dateUpdated;

    @Column(name = "INVOICE_COMPANY")
    private java.lang.String invoiceCompany;
    @Column(name = "INVOICE_TAXPAYER")
    private java.lang.String invoiceTaxpayer;
    @Column(name = "REGISTRY_ADDRESS")
    private java.lang.String registryAddress;
    @Column(name = "INVOICE_TELEPHONE")
    private java.lang.String invoiceTelephone;
    @Column(name = "INVOICE_BANK_NAME")
    private java.lang.String invoiceBankName;
    @Column(name = "INVOICE_BANK_ACCOUNT")
    private java.lang.String invoiceBankAccount;
    @Column(name = "INVOICE_ADDRESS")
    private java.lang.String invoiceAddress;
    @Column(name = "INVOICE_TO_NAME")
    private java.lang.String invoiceToName;
    @Column(name = "INVOICE_TO_TELEPHONE")
    private java.lang.String invoiceToTelephone;
    
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_ORDER", insertable = false, updatable = false)
    private HangOrderMain hangOrderMain;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_ORDER_SUB", insertable = false, updatable = false)
    private HangOrderSub hangOrderSub;

    
    
    /**
     * default constructor
     */
    public HangOrderInvoice() {
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

    /**
     * The value of the idOrder association.
     * 
     * @return java.lang.Long Return the value of the idOrder column.
     */
    public java.lang.Long getIdOrder() {
        return idOrder;
    }

    /**
     * Set the value of the idOrder.
     * 
     * @param idOrder
     */
    public void setIdOrder(java.lang.Long newIdOrder) {
        this.idOrder = newIdOrder;
    }

    /**
     * The value of the orderNo association.
     * 
     * @return java.lang.String Return the value of the orderNo column.
     */
    public java.lang.String getOrderNo() {
        return orderNo;
    }

    /**
     * Set the value of the orderNo.
     * 
     * @param orderNo
     */
    public void setOrderNo(java.lang.String newOrderNo) {
        this.orderNo = newOrderNo;
    }

    /**
     * The value of the aliasOrderNo association.
     * 
     * @return java.lang.Long Return the value of the aliasOrderNo column.
     */
    public java.lang.Long getAliasOrderNo() {
        return aliasOrderNo;
    }

    /**
     * Set the value of the aliasOrderNo.
     * 
     * @param aliasOrderNo
     */
    public void setAliasOrderNo(java.lang.Long newAliasOrderNo) {
        this.aliasOrderNo = newAliasOrderNo;
    }

    /**
     * The value of the orderSource association.
     * 
     * @return java.lang.String Return the value of the orderSource column.
     */
    public java.lang.String getOrderSource() {
        return orderSource;
    }

    /**
     * Set the value of the orderSource.
     * 
     * @param orderSource
     */
    public void setOrderSource(java.lang.String newOrderSource) {
        this.orderSource = newOrderSource;
    }

    /**
     * The value of the invoiceType association.
     * 
     * @return java.lang.String Return the value of the invoiceType column.
     */
    public java.lang.String getInvoiceType() {
        return invoiceType;
    }

    /**
     * Set the value of the invoiceType.
     * 
     * @param invoiceType
     */
    public void setInvoiceType(java.lang.String newInvoiceType) {
        this.invoiceType = newInvoiceType;
    }

    /**
     * The value of the invoiceHead association.
     * 
     * @return java.lang.String Return the value of the invoiceHead column.
     */
    public java.lang.String getInvoiceHead() {
        return invoiceHead;
    }

    /**
     * Set the value of the invoiceHead.
     * 
     * @param invoiceHead
     */
    public void setInvoiceHead(java.lang.String newInvoiceHead) {
        this.invoiceHead = newInvoiceHead;
    }

    /**
     * The value of the invoiceContent association.
     * 
     * @return java.lang.String Return the value of the invoiceContent column.
     */
    public java.lang.String getInvoiceContent() {
        return invoiceContent;
    }

    /**
     * Set the value of the invoiceContent.
     * 
     * @param invoiceContent
     */
    public void setInvoiceContent(java.lang.String newInvoiceContent) {
        this.invoiceContent = newInvoiceContent;
    }

    /**
     * The value of the invoiceAdditionInfo association.
     * 
     * @return java.lang.String Return the value of the invoiceAdditionInfo column.
     */
    public java.lang.String getInvoiceAdditionInfo() {
        return invoiceAdditionInfo;
    }

    /**
     * Set the value of the invoiceAdditionInfo.
     * 
     * @param invoiceAdditionInfo
     */
    public void setInvoiceAdditionInfo(java.lang.String newInvoiceAdditionInfo) {
        this.invoiceAdditionInfo = newInvoiceAdditionInfo;
    }

    /**
     * The value of the invoiceNum association.
     * 
     * @return java.lang.String Return the value of the invoiceNum column.
     */
    public java.lang.String getInvoiceNum() {
        return invoiceNum;
    }

    /**
     * Set the value of the invoiceNum.
     * 
     * @param invoiceNum
     */
    public void setInvoiceNum(java.lang.String newInvoiceNum) {
        this.invoiceNum = newInvoiceNum;
    }

    /**
     * The value of the billType association.
     * 
     * @return java.lang.Long Return the value of the billType column.
     */
    public java.lang.Long getBillType() {
        return billType;
    }

    /**
     * Set the value of the billType.
     * 
     * @param billType
     */
    public void setBillType(java.lang.Long newBillType) {
        this.billType = newBillType;
    }

    /**
     * The value of the remark association.
     * 
     * @return java.lang.String Return the value of the remark column.
     */
    public java.lang.String getRemark() {
        return remark;
    }

    /**
     * Set the value of the remark.
     * 
     * @param remark
     */
    public void setRemark(java.lang.String newRemark) {
        this.remark = newRemark;
    }

    /**
     * The value of the isDeleted association.
     * 
     * @return java.lang.Long Return the value of the isDeleted column.
     */
    public java.lang.Long getIsDeleted() {
        return isDeleted;
    }

    /**
     * Set the value of the isDeleted.
     * 
     * @param isDeleted
     */
    public void setIsDeleted(java.lang.Long newIsDeleted) {
        this.isDeleted = newIsDeleted;
    }

    /**
     * The value of the createdBy association.
     * 
     * @return java.lang.String Return the value of the createdBy column.
     */
    public java.lang.String getCreatedBy() {
        return createdBy;
    }

    /**
     * Set the value of the createdBy.
     * 
     * @param createdBy
     */
    public void setCreatedBy(java.lang.String newCreatedBy) {
        this.createdBy = newCreatedBy;
    }

    /**
     * The value of the updatedBy association.
     * 
     * @return java.lang.String Return the value of the updatedBy column.
     */
    public java.lang.String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * Set the value of the updatedBy.
     * 
     * @param updatedBy
     */
    public void setUpdatedBy(java.lang.String newUpdatedBy) {
        this.updatedBy = newUpdatedBy;
    }

    /**
     * The value of the dateCreated association.
     * 
     * @return java.util.Date Return the value of the dateCreated column.
     */
    public java.util.Date getDateCreated() {
        return dateCreated;
    }

    /**
     * Set the value of the dateCreated.
     * 
     * @param dateCreated
     */
    public void setDateCreated(java.util.Date newDateCreated) {
        this.dateCreated = newDateCreated;
    }

    /**
     * The value of the dateUpdated association.
     * 
     * @return java.util.Date Return the value of the dateUpdated column.
     */
    public java.util.Date getDateUpdated() {
        return dateUpdated;
    }

    /**
     * Set the value of the dateUpdated.
     * 
     * @param dateUpdated
     */
    public void setDateUpdated(java.util.Date newDateUpdated) {
        this.dateUpdated = newDateUpdated;
    }

    

    public HangOrderMain getHangOrderMain() {
		return hangOrderMain;
	}

	public void setHangOrderMain(HangOrderMain hangOrderMain) {
		this.hangOrderMain = hangOrderMain;
	}

	public HangOrderSub getHangOrderSub() {
		return hangOrderSub;
	}

	public void setHangOrderSub(HangOrderSub hangOrderSub) {
		this.hangOrderSub = hangOrderSub;
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

	public java.lang.String getInvoiceCompany() {
		return invoiceCompany;
	}

	public void setInvoiceCompany(java.lang.String invoiceCompany) {
		this.invoiceCompany = invoiceCompany;
	}

	public java.lang.String getInvoiceTaxpayer() {
		return invoiceTaxpayer;
	}

	public void setInvoiceTaxpayer(java.lang.String invoiceTaxpayer) {
		this.invoiceTaxpayer = invoiceTaxpayer;
	}

	public java.lang.String getRegistryAddress() {
		return registryAddress;
	}

	public void setRegistryAddress(java.lang.String registryAddress) {
		this.registryAddress = registryAddress;
	}

	public java.lang.String getInvoiceTelephone() {
		return invoiceTelephone;
	}

	public void setInvoiceTelephone(java.lang.String invoiceTelephone) {
		this.invoiceTelephone = invoiceTelephone;
	}

	public java.lang.String getInvoiceBankName() {
		return invoiceBankName;
	}

	public void setInvoiceBankName(java.lang.String invoiceBankName) {
		this.invoiceBankName = invoiceBankName;
	}

	public java.lang.String getInvoiceBankAccount() {
		return invoiceBankAccount;
	}

	public void setInvoiceBankAccount(java.lang.String invoiceBankAccount) {
		this.invoiceBankAccount = invoiceBankAccount;
	}

	public java.lang.String getInvoiceAddress() {
		return invoiceAddress;
	}

	public void setInvoiceAddress(java.lang.String invoiceAddress) {
		this.invoiceAddress = invoiceAddress;
	}

	public java.lang.String getInvoiceToName() {
		return invoiceToName;
	}

	public void setInvoiceToName(java.lang.String invoiceToName) {
		this.invoiceToName = invoiceToName;
	}

	public java.lang.String getInvoiceToTelephone() {
		return invoiceToTelephone;
	}

	public void setInvoiceToTelephone(java.lang.String invoiceToTelephone) {
		this.invoiceToTelephone = invoiceToTelephone;
	}
	

}

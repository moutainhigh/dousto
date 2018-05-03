package com.ibm.oms.domain.persist;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;



/**
 * 
 * This class is used to represent available CL_SKU in the database.</p>
 * 
 * 
 */
@Entity
@Table(name = "VIEW_SKU_R3")
public class SkuR3 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name = "sku_gen", table = "sys_id", pkColumnName = "key_id", valueColumnName = "key_value", pkColumnValue = "sku_id", allocationSize = 1)
	@Column(name = "ID", unique = true, nullable = false)
	private java.lang.Long id;
	@Column(name = "PRODUCT_ID")
	private java.lang.Long productId;
	@Column(name = "SKU_CODE")
	private java.lang.String skuCode;
	@Column(name = "BARCODE")
	private java.lang.String barcode;
	@Column(name = "NAME")
	private java.lang.String name;
	@Column(name = "DESCRIPTION")
	private java.lang.String description;
	@Column(name = "LONG_DESCRIPTION")
	private java.lang.String longDescription;
	@Column(name = "START_DATE")
	private java.util.Date startDate;
	@Column(name = "END_DATE")
	private java.util.Date endDate;
	@Column(name = "DISCOUNTABLE")
	private java.lang.String discountable;
	@Column(name = "AVAILABLE")
	private java.lang.String available;
	@Column(name = "TAXABLE")
	private java.lang.Long taxable;
	@Column(name = "IS_DELETED")
	private java.lang.Long isDeleted;

	@Column(name = "OPT_COUNTER")
	private java.lang.Long optCounter;
	@Column(name = "ITEMNUMBER")
	private java.lang.String itemnumber;
	@Column(name = "SUPPLIER_ID")
	private java.lang.Long supplierId;

	@Column(name = "FIELD1")
	private java.lang.Long field1;
	@Column(name = "FIELD2")
	private java.lang.Long field2;
	@Column(name = "FIELD3")
	private java.util.Date field3;
	@Column(name = "FIELD4")
	private java.lang.String field4;
	@Column(name = "FIELD5")
	private java.lang.String field5;
    @Column(name = "SOURCE_TYPE")
    private java.lang.String sourceType;
	/**
	 * default constructor
	 */
	public SkuR3() {
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
	 * The value of the productId association.
	 * 
	 * @return java.lang.Long Return the value of the productId column.
	 */
	public java.lang.Long getProductId() {
		return productId;
	}

	/**
	 * Set the value of the productId.
	 * 
	 * @param productId
	 */
	public void setProductId(java.lang.Long newProductId) {
		this.productId = newProductId;
	}

	/**
	 * The value of the skuCode association.
	 * 
	 * @return java.lang.String Return the value of the skuCode column.
	 */
	public java.lang.String getSkuCode() {
		return skuCode;
	}

	/**
	 * Set the value of the skuCode.
	 * 
	 * @param skuCode
	 */
	public void setSkuCode(java.lang.String newSkuCode) {
		this.skuCode = newSkuCode;
	}

	/**
	 * The value of the barcode association.
	 * 
	 * @return java.lang.String Return the value of the barcode column.
	 */
	public java.lang.String getBarcode() {
		return barcode;
	}

	/**
	 * Set the value of the barcode.
	 * 
	 * @param barcode
	 */
	public void setBarcode(java.lang.String newBarcode) {
		this.barcode = newBarcode;
	}

	/**
	 * The value of the name association.
	 * 
	 * @return java.lang.String Return the value of the name column.
	 */
	public java.lang.String getName() {
		return name;
	}

	/**
	 * Set the value of the name.
	 * 
	 * @param name
	 */
	public void setName(java.lang.String newName) {
		this.name = newName;
	}

	/**
	 * The value of the description association.
	 * 
	 * @return java.lang.String Return the value of the description column.
	 */
	public java.lang.String getDescription() {
		return description;
	}

	/**
	 * Set the value of the description.
	 * 
	 * @param description
	 */
	public void setDescription(java.lang.String newDescription) {
		this.description = newDescription;
	}

	/**
	 * The value of the longDescription association.
	 * 
	 * @return java.lang.String Return the value of the longDescription column.
	 */
	public java.lang.String getLongDescription() {
		return longDescription;
	}

	/**
	 * Set the value of the longDescription.
	 * 
	 * @param longDescription
	 */
	public void setLongDescription(java.lang.String newLongDescription) {
		this.longDescription = newLongDescription;
	}

	/**
	 * The value of the startDate association.
	 * 
	 * @return java.util.Date Return the value of the startDate column.
	 */
	public java.util.Date getStartDate() {
		return startDate;
	}

	/**
	 * Set the value of the startDate.
	 * 
	 * @param startDate
	 */
	public void setStartDate(java.util.Date newStartDate) {
		this.startDate = newStartDate;
	}

	/**
	 * The value of the endDate association.
	 * 
	 * @return java.util.Date Return the value of the endDate column.
	 */
	public java.util.Date getEndDate() {
		return endDate;
	}

	/**
	 * Set the value of the endDate.
	 * 
	 * @param endDate
	 */
	public void setEndDate(java.util.Date newEndDate) {
		this.endDate = newEndDate;
	}

	/**
	 * The value of the discountable association.
	 * 
	 * @return java.lang.String Return the value of the discountable column.
	 */
	public java.lang.String getDiscountable() {
		return discountable;
	}

	/**
	 * Set the value of the discountable.
	 * 
	 * @param discountable
	 */
	public void setDiscountable(java.lang.String newDiscountable) {
		this.discountable = newDiscountable;
	}

	/**
	 * The value of the available association.
	 * 
	 * @return java.lang.String Return the value of the available column.
	 */
	public java.lang.String getAvailable() {
		return available;
	}

	/**
	 * Set the value of the available.
	 * 
	 * @param available
	 */
	public void setAvailable(java.lang.String newAvailable) {
		this.available = newAvailable;
	}

	/**
	 * The value of the taxable association.
	 * 
	 * @return java.lang.Long Return the value of the taxable column.
	 */
	public java.lang.Long getTaxable() {
		return taxable;
	}

	/**
	 * Set the value of the taxable.
	 * 
	 * @param taxable
	 */
	public void setTaxable(java.lang.Long newTaxable) {
		this.taxable = newTaxable;
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
	 * The value of the optCounter association.
	 * 
	 * @return java.lang.Long Return the value of the optCounter column.
	 */
	public java.lang.Long getOptCounter() {
		return optCounter;
	}

	/**
	 * Set the value of the optCounter.
	 * 
	 * @param optCounter
	 */
	public void setOptCounter(java.lang.Long newOptCounter) {
		this.optCounter = newOptCounter;
	}

	/**
	 * The value of the itemnumber association.
	 * 
	 * @return java.lang.String Return the value of the itemnumber column.
	 */
	public java.lang.String getItemnumber() {
		return itemnumber;
	}

	/**
	 * Set the value of the itemnumber.
	 * 
	 * @param itemnumber
	 */
	public void setItemnumber(java.lang.String newItemnumber) {
		this.itemnumber = newItemnumber;
	}

	/**
	 * The value of the supplierId association.
	 * 
	 * @return java.lang.Long Return the value of the supplierId column.
	 */
	public java.lang.Long getSupplierId() {
		return supplierId;
	}

	/**
	 * Set the value of the supplierId.
	 * 
	 * @param supplierId
	 */
	public void setSupplierId(java.lang.Long newSupplierId) {
		this.supplierId = newSupplierId;
	}

	

	/**
	 * The value of the field1 association.
	 * 
	 * @return java.lang.Long Return the value of the field1 column.
	 */
	public java.lang.Long getField1() {
		return field1;
	}

	/**
	 * Set the value of the field1.
	 * 
	 * @param field1
	 */
	public void setField1(java.lang.Long newField1) {
		this.field1 = newField1;
	}

	/**
	 * The value of the field2 association.
	 * 
	 * @return java.lang.Long Return the value of the field2 column.
	 */
	public java.lang.Long getField2() {
		return field2;
	}

	/**
	 * Set the value of the field2.
	 * 
	 * @param field2
	 */
	public void setField2(java.lang.Long newField2) {
		this.field2 = newField2;
	}

	/**
	 * The value of the field3 association.
	 * 
	 * @return java.util.Date Return the value of the field3 column.
	 */
	public java.util.Date getField3() {
		return field3;
	}

	/**
	 * Set the value of the field3.
	 * 
	 * @param field3
	 */
	public void setField3(java.util.Date newField3) {
		this.field3 = newField3;
	}

	/**
	 * The value of the field4 association.
	 * 
	 * @return java.lang.String Return the value of the field4 column.
	 */
	public java.lang.String getField4() {
		return field4;
	}

	/**
	 * Set the value of the field4.
	 * 
	 * @param field4
	 */
	public void setField4(java.lang.String newField4) {
		this.field4 = newField4;
	}

	/**
	 * The value of the field5 association.
	 * 
	 * @return java.lang.String Return the value of the field5 column.
	 */
	public java.lang.String getField5() {
		return field5;
	}

	/**
	 * Set the value of the field5.
	 * 
	 * @param field5
	 */
	public void setField5(java.lang.String newField5) {
		this.field5 = newField5;
	}

    public java.lang.String getSourceType() {
        return sourceType;
    }

    public void setSourceType(java.lang.String sourceType) {
        this.sourceType = sourceType;
    }

	
}

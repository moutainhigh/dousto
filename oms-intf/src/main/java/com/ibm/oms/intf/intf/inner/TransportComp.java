package com.ibm.oms.intf.intf.inner;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * I-oms-logistics-01 outputDTO
 * 
 * 
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class TransportComp implements Serializable {
    String createdBy;
    String updatedBy;
    String dateCreated;
    String dateUpdated;
    String optCounter;
    boolean deleted;
    // 物流公司ID
    private Long id;
    // 物流公司名称
    private String name;
    private String description;
    private boolean isPrint;
    private Long limitedWeight;
    private String supportTime;
    private String orderComfTime;
    private boolean isDefault;
    private String supportPayMode;
    private String supportDeliverMode;
    private boolean isAvailable;
    private String status;//success or error
    private String message;
    private boolean isDeliverSat;
    private boolean isDeliverSun;

    private BigDecimal compLevel;

    public BigDecimal getCompLevel() {
        return compLevel;
    }

    public void setCompLevel(BigDecimal compLevel) {
        this.compLevel = compLevel;
    }

    public boolean getIsDeliverSat() {
        return isDeliverSat;
    }

    public void setIsDeliverSat(boolean isDeliverSat) {
        this.isDeliverSat = isDeliverSat;
    }

    public boolean getIsDeliverSun() {
        return isDeliverSun;
    }

    public void setIsDeliverSun(boolean isDeliverSun) {
        this.isDeliverSun = isDeliverSun;
    }

    /**
     * default constructor
     */
    public TransportComp() {
    }

    /**
     * The value of the id association.
     * 
     * @return Long Return the value of the id column.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the value of the id.
     * 
     * @param id
     */
    public void setId(Long newId) {
        this.id = newId;
    }

    /**
     * The value of the name association.
     * 
     * @return String Return the value of the name column.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of the name.
     * 
     * @param name
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * The value of the description association.
     * 
     * @return String Return the value of the description column.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the value of the description.
     * 
     * @param description
     */
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * The value of the isPrint association.
     * 
     * @return boolean Return the value of the isPrint column.
     */
    public boolean getIsPrint() {
        return isPrint;
    }

    /**
     * Set the value of the isPrint.
     * 
     * @param isPrint
     */
    public void setIsPrint(boolean newIsPrint) {
        this.isPrint = newIsPrint;
    }

    /**
     * The value of the limitedWeight association.
     * 
     * @return Long Return the value of the limitedWeight column.
     */
    public Long getLimitedWeight() {
        return limitedWeight;
    }

    /**
     * Set the value of the limitedWeight.
     * 
     * @param limitedWeight
     */
    public void setLimitedWeight(Long newLimitedWeight) {
        this.limitedWeight = newLimitedWeight;
    }

    /**
     * The value of the supportTime association.
     * 
     * @return String Return the value of the supportTime column.
     */
    public String getSupportTime() {
        return supportTime;
    }

    /**
     * Set the value of the supportTime.
     * 
     * @param supportTime
     */
    public void setSupportTime(String newSupportTime) {
        this.supportTime = newSupportTime;
    }

    /**
     * The value of the orderComfTime association.
     * 
     * @return String Return the value of the orderComfTime column.
     */
    public String getOrderComfTime() {
        return orderComfTime;
    }

    /**
     * Set the value of the orderComfTime.
     * 
     * @param orderComfTime
     */
    public void setOrderComfTime(String newOrderComfTime) {
        this.orderComfTime = newOrderComfTime;
    }

    /**
     * The value of the isDefault association.
     * 
     * @return boolean Return the value of the isDefault column.
     */
    public boolean getIsDefault() {
        return isDefault;
    }

    /**
     * Set the value of the isDefault.
     * 
     * @param isDefault
     */
    public void setIsDefault(boolean newIsDefault) {
        this.isDefault = newIsDefault;
    }

    /**
     * The value of the supportPayMode association.
     * 
     * @return String Return the value of the supportPayMode column.
     */
    public String getSupportPayMode() {
        return supportPayMode;
    }

    /**
     * Set the value of the supportPayMode.
     * 
     * @param supportPayMode
     */
    public void setSupportPayMode(String newSupportPayMode) {
        this.supportPayMode = newSupportPayMode;
    }

    /**
     * The value of the supportDeliverMode association.
     * 
     * @return String Return the value of the supportDeliverMode column.
     */
    public String getSupportDeliverMode() {
        return supportDeliverMode;
    }

    /**
     * Set the value of the supportDeliverMode.
     * 
     * @param supportDeliverMode
     */
    public void setSupportDeliverMode(String newSupportDeliverMode) {
        this.supportDeliverMode = newSupportDeliverMode;
    }

    /**
     * The value of the isAvailable association.
     * 
     * @return boolean Return the value of the isAvailable column.
     */
    public boolean getIsAvailable() {
        return isAvailable;
    }

    /**
     * Set the value of the isAvailable.
     * 
     * @param isAvailable
     */
    public void setIsAvailable(boolean newIsAvailable) {
        this.isAvailable = newIsAvailable;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getOptCounter() {
        return optCounter;
    }

    public void setOptCounter(String optCounter) {
        this.optCounter = optCounter;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void setPrint(boolean isPrint) {
        this.isPrint = isPrint;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public void setDeliverSat(boolean isDeliverSat) {
        this.isDeliverSat = isDeliverSat;
    }

    public void setDeliverSun(boolean isDeliverSun) {
        this.isDeliverSun = isDeliverSun;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}

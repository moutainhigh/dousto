package com.ibm.oms.domain.persist;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.ibm.sc.model.base.VersionEntity;


/**
 * 
 * This class is used to represent available SM_TRANSPORT_AREA in the database.</p>
 *
 * 
 */
@Entity
@Table(name="SM_TRANSPORT_AREA")
public class TransportArea extends VersionEntity {
    private static final long serialVersionUID = 1L;
    
    @Id
	@TableGenerator(name = "transportArea_gen", table = "sys_id", pkColumnName = "key_id", valueColumnName = "key_value", pkColumnValue = "transportArea_id")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "transportArea_gen")
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;
	@Column(name = "PARENT_ID")
	private Long parentId;
	@Column(name = "AREA_NAME")
	private String areaName;
	@Column(name = "AREA_ABBREVIATION")
	private String areaAbbreviation;
	@Column(name = "AREA_CODE")
	private String areaCode;
	@Column(name = "AREA_LEVEL")
	private Long areaLevel;
	@Column(name = "AREA_SEQUENCE")
	private Long areaSequence;
	@Column(name = "IS_AVAILABLE")
	private Boolean isAvailable;







	/** 
	 * default constructor 
	 */
    public TransportArea() {
    }
    
								
    /** 
	 * The value of the id association. 
	 * 
     * @return  Long
     * Return the value of the id column.
     */
    public Long getId() {
    	return id;
    }
    /**
     * Set the value of the id.
     * @param id
     */
    public void setId(Long newId){
    	this.id=newId;
    }

    /** 
	 * The value of the parentId association. 
	 * 
     * @return  Long
     * Return the value of the parentId column.
     */
    public Long getParentId() {
    	return parentId;
    }
    /**
     * Set the value of the parentId.
     * @param parentId
     */
    public void setParentId(Long newParentId){
    	this.parentId=newParentId;
    }

    /** 
	 * The value of the areaName association. 
	 * 
     * @return  String
     * Return the value of the areaName column.
     */
    public String getAreaName() {
    	return areaName;
    }
    /**
     * Set the value of the areaName.
     * @param areaName
     */
    public void setAreaName(String newAreaName){
    	this.areaName=newAreaName;
    }

    /** 
	 * The value of the areaAbbreviation association. 
	 * 
     * @return  String
     * Return the value of the areaAbbreviation column.
     */
    public String getAreaAbbreviation() {
    	return areaAbbreviation;
    }
    /**
     * Set the value of the areaAbbreviation.
     * @param areaAbbreviation
     */
    public void setAreaAbbreviation(String newAreaAbbreviation){
    	this.areaAbbreviation=newAreaAbbreviation;
    }

    /** 
	 * The value of the areaCode association. 
	 * 
     * @return  String
     * Return the value of the areaCode column.
     */
    public String getAreaCode() {
    	return areaCode;
    }
    /**
     * Set the value of the areaCode.
     * @param areaCode
     */
    public void setAreaCode(String newAreaCode){
    	this.areaCode=newAreaCode;
    }

    /** 
	 * The value of the areaLevel association. 
	 * 
     * @return  Long
     * Return the value of the areaLevel column.
     */
    public Long getAreaLevel() {
    	return areaLevel;
    }
    /**
     * Set the value of the areaLevel.
     * @param areaLevel
     */
    public void setAreaLevel(Long newAreaLevel){
    	this.areaLevel=newAreaLevel;
    }

    /** 
	 * The value of the areaSequence association. 
	 * 
     * @return  Long
     * Return the value of the areaSequence column.
     */
    public Long getAreaSequence() {
    	return areaSequence;
    }
    /**
     * Set the value of the areaSequence.
     * @param areaSequence
     */
    public void setAreaSequence(Long newAreaSequence){
    	this.areaSequence=newAreaSequence;
    }

    /** 
	 * The value of the isAvailable association. 
	 * 
     * @return  Boolean
     * Return the value of the isAvailable column.
     */
    public Boolean getIsAvailable() {
    	return isAvailable;
    }
    /**
     * Set the value of the isAvailable.
     * @param isAvailable
     */
    public void setIsAvailable(Boolean newIsAvailable){
    	this.isAvailable=newIsAvailable;
    }


}

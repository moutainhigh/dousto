package com.ibm.oms.domain.persist;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "CL_CATEGORY")
@SecondaryTables(
		{ @SecondaryTable(name = "CL_CATEGORY_I18N", pkJoinColumns = { @PrimaryKeyJoinColumn(name = "CATEGORY_ID") }),
		  @SecondaryTable(name = "CL_CATEGORY_REL", pkJoinColumns = { @PrimaryKeyJoinColumn(name = "CATEGORY_ID") }) 
		})
public class OrderCategory implements Serializable {

	private static final long serialVersionUID = 1L;

	// CL_CATEGORY
	@Id
    @Column(table = "CL_CATEGORY", name = "ID")
    private java.lang.Long id;
	@Column(table = "CL_CATEGORY",name = "IS_DELETED")
    private java.lang.Long isDeleted;
	
	// CL_CATEGORY_I18N
	@Column(table = "CL_CATEGORY_I18N",name = "NAME")
	private java.lang.String name;
	
	// CL_CATEGORY_REL
    @Column(table = "CL_CATEGORY_REL", name = "PARENT_ID")
    private java.lang.Long parentId;
    @Column(table = "CL_CATEGORY_REL", name = "TREE_LEVEL")
    private java.lang.Long treeLevel;
    @Column(table = "CL_CATEGORY_REL", name = "CATALOG_ID")
    private java.lang.Long catalogId;
    
    @Transient
	private boolean checked;
    
	/**
	 * default constructor
	 */
	public OrderCategory() {

	}
	
	public java.lang.Long getId() {
		return id;
	}
	public void setId(java.lang.Long id) {
		this.id = id;
	}
	public java.lang.Long getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(java.lang.Long isDeleted) {
		this.isDeleted = isDeleted;
	}
	public java.lang.String getName() {
		return name;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
	public java.lang.Long getParentId() {
		return parentId;
	}
	public void setParentId(java.lang.Long parentId) {
		this.parentId = parentId;
	}
	public java.lang.Long getTreeLevel() {
		return treeLevel;
	}
	public void setTreeLevel(java.lang.Long treeLevel) {
		this.treeLevel = treeLevel;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public java.lang.Long getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(java.lang.Long catalogId) {
		this.catalogId = catalogId;
	}   
	
}


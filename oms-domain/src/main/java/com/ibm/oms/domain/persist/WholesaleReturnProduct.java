package com.ibm.oms.domain.persist;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "wholesale_return_product")
public class WholesaleReturnProduct implements Serializable {
    @Id
    @TableGenerator(name = "wholesale_return_product", table = "sys_id", pkColumnName = "key_id", valueColumnName = "key_value", pkColumnValue = "wholesale_return_product_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "wholesale_return_product")
    @Column(unique = true, nullable = false, precision = 22)
    private Long id;
    @Column(name = "SKU_CODE")
    private String skuCode;
    @Column(name = "NORMAL_QUANTITY")
    private Integer normalQuantity;
    @Column(name = "DEFECTIVE_QUANTITY")
    private Integer defectiveQuantity;
    @Column(name = "BOX_NO")
    private String boxNo;
    @Column(name = "WOS_ID")
    private Long wosId;

    private static final long serialVersionUID = -4797639569578195745L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode == null ? null : skuCode.trim();
    }

    public Integer getNormalQuantity() {
        return normalQuantity;
    }

    public void setNormalQuantity(Integer normalQuantity) {
        this.normalQuantity = normalQuantity;
    }

    public Integer getDefectiveQuantity() {
        return defectiveQuantity;
    }

    public void setDefectiveQuantity(Integer defectiveQuantity) {
        this.defectiveQuantity = defectiveQuantity;
    }

    public String getBoxNo() {
        return boxNo;
    }

    public void setBoxNo(String boxNo) {
        this.boxNo = boxNo == null ? null : boxNo.trim();
    }

    public Long getWosId() {
        return wosId;
    }

    public void setWosId(Long wosId) {
        this.wosId = wosId;
    }
}
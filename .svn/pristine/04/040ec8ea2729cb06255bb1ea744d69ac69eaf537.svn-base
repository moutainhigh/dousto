package com.ibm.oms.domain.persist;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "wholesale_case_code")
public class WholesaleCaseCode implements Serializable {
    private static final long serialVersionUID = -7182091645068657882L;
    @Id
    @TableGenerator(name = "wholesale_case_code", table = "sys_id", pkColumnName = "key_id", valueColumnName = "key_value", pkColumnValue = "wholesale_case_code_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "wholesale_case_code")
    @Column(unique = true, nullable = false, precision = 22)
    private Long id;
    @Column(name = "ASSORTMENT_CODE")
    private String assortmentCode;
    @Column(name = "STYLE_CODE")
    private String styleCode;
    @Column(name = "COLOR_CODE")
    private String colorCode;
    @Column(name = "QTY_CASE")
    private String qtyCase;
    @Column(name = "QTY_SKU")
    private String qtySku;
    @Column(name = "WOS_ID")
    private Long wosId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWosId() {
        return wosId;
    }

    public void setWosId(Long wosId) {
        this.wosId = wosId;
    }

    public String getAssortmentCode() {
        return assortmentCode;
    }

    public void setAssortmentCode(String assortmentCode) {
        this.assortmentCode = assortmentCode == null ? null : assortmentCode.trim();
    }

    public String getStyleCode() {
        return styleCode;
    }

    public void setStyleCode(String styleCode) {
        this.styleCode = styleCode == null ? null : styleCode.trim();
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode == null ? null : colorCode.trim();
    }

    public String getQtyCase() {
        return qtyCase;
    }

    public void setQtyCase(String qtyCase) {
        this.qtyCase = qtyCase == null ? null : qtyCase.trim();
    }

    public String getQtySku() {
        return qtySku;
    }

    public void setQtySku(String qtySku) {
        this.qtySku = qtySku == null ? null : qtySku.trim();
    }

}
package com.ibm.oms.client.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: mr.kai
 * @Description: 客户销售金额汇总
 * @create: 2018-03-26 19:44
 **/
public class CategoryMoney implements Serializable{
    private String attrName;
    private BigDecimal totalCount;

    public CategoryMoney() {
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public BigDecimal getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(BigDecimal totalCount) {
        this.totalCount = totalCount;
    }
}

package com.ibm.oms.client.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: mr.kai
 * @Description: 门店销售情况出参实体类
 * @create: 2018-03-26 15:28
 **/
public class CategorySalesVO implements Serializable{
    //属性类型名
    private String attrName;
    //总数
    private String totalCount;
    //数量
    private String count;
    //占比
    private BigDecimal percent;

    public CategorySalesVO() {
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }
}

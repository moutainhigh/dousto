package com.ibm.oms.domain.bean.hang;

import java.io.Serializable;
import java.util.List;

/**
 * @author: mr.kai
 * @Description: 销售实收接口实体类( 中台 - > sap表头)
 * @create: 2018-03-19 9:32
 **/
public class SalesReceiptsOrder implements Serializable {
    //业绩店铺代号
    private String cusno;
    //日结日期
    private String outDate;
    //批次(店铺代号+日结日期)
    private String cusnoOutdate;
    //单据类型
    private String billType;

    private List<SalesReceiptsOrderItem> salesReceiptsOrderItems;

    public SalesReceiptsOrder() {
    }
    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }
    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    public String getCusnoOutdate() {
        return cusnoOutdate;
    }

    public void setCusnoOutdate(String cusnoOutdate) {
        this.cusnoOutdate = cusnoOutdate;
    }

    public List<SalesReceiptsOrderItem> getSalesReceiptsOrderItems() {
        return salesReceiptsOrderItems;
    }

    public String getCusno() {
        return cusno;
    }

    public void setCusno(String cusno) {
        this.cusno = cusno;
    }

    public void setSalesReceiptsOrderItems(List<SalesReceiptsOrderItem> salesReceiptsOrderItems) {
        this.salesReceiptsOrderItems = salesReceiptsOrderItems;
    }
}

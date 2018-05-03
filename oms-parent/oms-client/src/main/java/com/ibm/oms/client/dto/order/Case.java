package com.ibm.oms.client.dto.order;

import java.io.Serializable;

/**
 * @author: mr.kai
 * @Description: WMS->中台 (销售订单状态更新通知)（发货订单确认接口）配码明细(单个)
 * @create: 2018-03-12 14:42
 **/
public class Case implements Serializable {
    private static final long serialVersionUID = 1L;
    private String assortmentCode;//配码代码
    private String styleCode;//商品货号
    private String colorCode;//颜色代码
    private String qtyCase;//箱码件数
    private String qtySku;//Sku总数量

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAssortmentCode() {
        return assortmentCode;
    }

    public void setAssortmentCode(String assortmentCode) {
        this.assortmentCode = assortmentCode;
    }

    public String getStyleCode() {
        return styleCode;
    }

    public void setStyleCode(String styleCode) {
        this.styleCode = styleCode;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getQtyCase() {
        return qtyCase;
    }

    public void setQtyCase(String qtyCase) {
        this.qtyCase = qtyCase;
    }

    public String getQtySku() {
        return qtySku;
    }

    public void setQtySku(String qtySku) {
        this.qtySku = qtySku;
    }
}

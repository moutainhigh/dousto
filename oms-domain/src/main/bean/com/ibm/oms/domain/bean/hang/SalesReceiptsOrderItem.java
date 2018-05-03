package com.ibm.oms.domain.bean.hang;

import java.io.Serializable;

/**
 * @author: mr.kai
 * @Description: 销售实收接口实体类(中台 - > sap 行项目)
 * @create: 2018-03-19 9:47
 **/
public class SalesReceiptsOrderItem implements Serializable {

    //产品信息
    private String sku;
    //牌价
    private String price;
    //折扣
    private String pers;
    //结算金额
    private String nowReal;
    //数量
    private String nb;
    //积分金额
    private String vipPoints;
    //优惠券金额
    private String coupon;
    //发货/退货地点如果为门店：4位门店编码+0001 如果为仓库：8位仓库编码
    private String dlSite;

    public String getVipPoints() {
        return vipPoints;
    }

    public void setVipPoints(String vipPoints) {
        this.vipPoints = vipPoints;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getDlSite() {
        return dlSite;
    }

    public void setDlSite(String dlSite) {
        this.dlSite = dlSite;
    }

    public SalesReceiptsOrderItem() {
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPers() {
        return pers;
    }

    public void setPers(String pers) {
        this.pers = pers;
    }

    public String getNowReal() {
        return nowReal;
    }

    public void setNowReal(String nowReal) {
        this.nowReal = nowReal;
    }

    public String getNb() {
        return nb;
    }

    public void setNb(String nb) {
        this.nb = nb;
    }
}

package com.ibm.oms.client.dto.order;

import java.io.Serializable;

/**
 * @author: mr.kai
 * @Description: WMS->中台-发货订单确认接口实体类(订单包裹信息)
 * @create: 2018-03-10 8:52
 **/
public class OmsDeliveryOrderSub implements Serializable{
    //快递公司服务编码 y
    private String shipvendorid;
    //运单编码,包裹上 贴的运单的编码 y
    private String shipbillno;
    //包裹号，仓库管理 包裹的 id  n
    private String ContainerID;
    //包裹重量，单位： KG  y
    private String SumGoodsWeight;
    //包裹长度，单位立 方厘米 n
    private String SumGoodsVol;
    //包材商品 ID  n
    private String PackageItemID;
    //运单号 y
    private String ShipBillNo;
    //商品id n
    private String itemId ;
    //此运单里面该商品数量 n
    private String Qty ;

    public String getShipBillNo() {
        return ShipBillNo;
    }

    public void setShipBillNo(String shipBillNo) {
        ShipBillNo = shipBillNo;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public String getShipvendorid() {
        return shipvendorid;
    }

    public void setShipvendorid(String shipvendorid) {
        this.shipvendorid = shipvendorid;
    }

    public String getShipbillno() {
        return shipbillno;
    }

    public void setShipbillno(String shipbillno) {
        this.shipbillno = shipbillno;
    }

    public String getContainerID() {
        return ContainerID;
    }

    public void setContainerID(String containerID) {
        ContainerID = containerID;
    }

    public String getSumGoodsWeight() {
        return SumGoodsWeight;
    }

    public void setSumGoodsWeight(String sumGoodsWeight) {
        SumGoodsWeight = sumGoodsWeight;
    }

    public String getSumGoodsVol() {
        return SumGoodsVol;
    }

    public void setSumGoodsVol(String sumGoodsVol) {
        SumGoodsVol = sumGoodsVol;
    }

    public String getPackageItemID() {
        return PackageItemID;
    }

    public void setPackageItemID(String packageItemID) {
        PackageItemID = packageItemID;
    }
}

package com.ibm.oms.client.dto.order;

import java.io.Serializable;

/**
 * @author: mr.kai
 * @Description: 销售订单退货行项目表
 * @create: 2018-04-02 11:05
 **/
public class OrderSalesItemCancel  implements Serializable {

    private String BillNo;//  入库单号
    private String Seqno;//  行序号
    private String itemId;//  商品 ID
    private String itemName;//  商品名称
    private String itemCode;//  商家对商品的编码
    private String barCode;//  条形码，多条码请 用”;”分隔；仓库 入库扫码使用
    private String StockStatus;//库存类型（1 可销售 库存(正品) 101 残 次 102 机损 103 箱损 301 在途库存 201 冻结库存） 注意：采购入库单下 发的库存类型是301
    private String Qty;//	商品数量
    private String Price;//单价
    private String Revision;//商品版本
    private String ColorCode;    //  颜色码
    private String SizeCode;//尺码
    private String PCH;//批次号
    private String LotNo; //  批号
    private String PDateFrom;    //  到效日期,保质期商 品信息，如果商品启 用了保质期管理，需 要仓库按指定保质 期生产
    private String PDateTo;//生产日期,保质期商 品信息，如果商品启 用了保质期管理，需 要仓库按指定保质 期生产
    private String VPoNo; //  采购单号
    private String LineRemark;//批次备注

    public OrderSalesItemCancel() {
    }

    public String getBillNo() {
        return BillNo;
    }

    public void setBillNo(String billNo) {
        BillNo = billNo;
    }

    public String getSeqno() {
        return Seqno;
    }

    public void setSeqno(String seqno) {
        Seqno = seqno;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getStockStatus() {
        return StockStatus;
    }

    public void setStockStatus(String stockStatus) {
        StockStatus = stockStatus;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getRevision() {
        return Revision;
    }

    public void setRevision(String revision) {
        Revision = revision;
    }

    public String getColorCode() {
        return ColorCode;
    }

    public void setColorCode(String colorCode) {
        ColorCode = colorCode;
    }

    public String getSizeCode() {
        return SizeCode;
    }

    public void setSizeCode(String sizeCode) {
        SizeCode = sizeCode;
    }

    public String getPCH() {
        return PCH;
    }

    public void setPCH(String PCH) {
        this.PCH = PCH;
    }

    public String getLotNo() {
        return LotNo;
    }

    public void setLotNo(String lotNo) {
        LotNo = lotNo;
    }

    public String getPDateFrom() {
        return PDateFrom;
    }

    public void setPDateFrom(String PDateFrom) {
        this.PDateFrom = PDateFrom;
    }

    public String getPDateTo() {
        return PDateTo;
    }

    public void setPDateTo(String PDateTo) {
        this.PDateTo = PDateTo;
    }

    public String getVPoNo() {
        return VPoNo;
    }

    public void setVPoNo(String VPoNo) {
        this.VPoNo = VPoNo;
    }

    public String getLineRemark() {
        return LineRemark;
    }

    public void setLineRemark(String lineRemark) {
        LineRemark = lineRemark;
    }
}

package com.ibm.oms.domain.bean.hang;

/**
 * @author: mr.kai
 * @Description: 中台->wms销售订单通知Dao层调用Bean
 * @create: 2018-03-09 8:05
 **/
public class OrderItem implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String BillNo;
    private String SeqNo;
    private String itemId;
    private String itemName;
    private String itemCode;
    private String LotNo;
    private String PCH;
    private String StockStatus;
    private String Qty;
    private String Price;
    private String OrginPrice;
    private String PriceDiscount;
    private String Revision;
    private String SizeCode;
    private String ColorCode;
    private String D_Remark;

    public OrderItem() {
    }

    public String getBillNo() {
        return this.BillNo;
    }

    public void setBillNo(String billNo) {
        this.BillNo = billNo;
    }

    public String getSeqNo() {
        return this.SeqNo;
    }

    public void setSeqNo(String seqNo) {
        this.SeqNo = seqNo;
    }

    public String getItemId() {
        return this.itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCode() {
        return this.itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getLotNo() {
        return this.LotNo;
    }

    public void setLotNo(String lotNo) {
        this.LotNo = lotNo;
    }

    public String getPCH() {
        return this.PCH;
    }

    public void setPCH(String pCH) {
        this.PCH = pCH;
    }

    public String getStockStatus() {
        return this.StockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.StockStatus = stockStatus;
    }

    public String getQty() {
        return this.Qty;
    }

    public void setQty(String qty) {
        this.Qty = qty;
    }

    public String getPrice() {
        return this.Price;
    }

    public void setPrice(String price) {
        this.Price = price;
    }

    public String getOrginPrice() {
        return this.OrginPrice;
    }

    public void setOrginPrice(String orginPrice) {
        this.OrginPrice = orginPrice;
    }

    public String getPriceDiscount() {
        return this.PriceDiscount;
    }

    public void setPriceDiscount(String priceDiscount) {
        this.PriceDiscount = priceDiscount;
    }

    public String getRevision() {
        return this.Revision;
    }

    public void setRevision(String revision) {
        this.Revision = revision;
    }

    public String getSizeCode() {
        return this.SizeCode;
    }

    public void setSizeCode(String sizeCode) {
        this.SizeCode = sizeCode;
    }

    public String getColorCode() {
        return this.ColorCode;
    }

    public void setColorCode(String colorCode) {
        this.ColorCode = colorCode;
    }

    public String getD_Remark() {
        return this.D_Remark;
    }

    public void setD_Remark(String d_Remark) {
        this.D_Remark = d_Remark;
    }
}

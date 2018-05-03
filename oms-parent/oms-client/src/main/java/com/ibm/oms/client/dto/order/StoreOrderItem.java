package com.ibm.oms.client.dto.order;

import java.io.Serializable;

/**
 * @author: mr.kai
 * @Description: WMS-中台-入库订单确认接口 (订单行项目)
 * @create: 2018-03-10 9:48
 **/
public class StoreOrderItem  implements Serializable {
    //序号 y
    private String SeqNo;
    //商品id y
    private String ItemId;;
    //商品编码 n
    private String ItemCode;
    //库存类型（1 可销 售库存(正品) 101 残次 102 机损 103 箱损 201 冻 结库存 ) y
    private String StockStatus;
    //数量 y
    private String Qty;
    //批次号 n
    private String PCH;
    //批号 n
    private String LotNo;
    //到期时间 n
    private String ExpDate;
    //生产日期 n
    private String yieldDate;

    public String getYieldDate() {
        return yieldDate;
    }

    public void setYieldDate(String yieldDate) {
        this.yieldDate = yieldDate;
    }

    public String getSeqNo() {
        return SeqNo;
    }

    public void setSeqNo(String seqNo) {
        SeqNo = seqNo;
    }

    public String getItemId() {
        return ItemId;
    }

    public void setItemId(String itemId) {
        ItemId = itemId;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
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

    public String getExpDate() {
        return ExpDate;
    }

    public void setExpDate(String expDate) {
        ExpDate = expDate;
    }
}

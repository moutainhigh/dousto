package com.ibm.oms.client.dto.order;

import java.io.Serializable;
import java.util.List;

/**
 * @author: mr.kai
 * @Description: WMS->中台-发货订单确认接口实体类 (主订单包括包裹和行项目)
 * @create: 2018-03-10 8:41
 **/
public class OmsDeliveryOrderMain implements Serializable {
    //仓储中心订单编码 y
    private String BranchCode;
    //订单类型(201 一 般交易出库单、 502 换货出库单) y
    private String BillType;
    //订单编号 n
    private String BillNo;
    //仓库订单完成时间 y
    private String BillDate;
    //主运单号 n
    private String ShipBillNo;
    //承运方代号
    private String ShipVendorID;
    //商品行项目
    private List<OmsDeliveryOrderItem> omsDeliveryOrderItems;
    //商品包裹信息
    private  List<OmsDeliveryOrderSub> omsDeliveryOrderSubs;

    public List<OmsDeliveryOrderItem> getOmsDeliveryOrderItems() {
        return omsDeliveryOrderItems;
    }

    public void setOmsDeliveryOrderItems(List<OmsDeliveryOrderItem> omsDeliveryOrderItems) {
        this.omsDeliveryOrderItems = omsDeliveryOrderItems;
    }

    public List<OmsDeliveryOrderSub> getOmsDeliveryOrderSubs() {
        return omsDeliveryOrderSubs;
    }

    public void setOmsDeliveryOrderSubs(List<OmsDeliveryOrderSub> omsDeliveryOrderSubs) {
        this.omsDeliveryOrderSubs = omsDeliveryOrderSubs;
    }

    public String getBranchCode() {
        return BranchCode;
    }

    public void setBranchCode(String branchCode) {
        BranchCode = branchCode;
    }

    public String getBillType() {
        return BillType;
    }

    public void setBillType(String billType) {
        BillType = billType;
    }

    public String getBillNo() {
        return BillNo;
    }

    public void setBillNo(String billNo) {
        BillNo = billNo;
    }

    public String getBillDate() {
        return BillDate;
    }

    public void setBillDate(String billDate) {
        BillDate = billDate;
    }

    public String getShipBillNo() {
        return ShipBillNo;
    }

    public void setShipBillNo(String shipBillNo) {
        ShipBillNo = shipBillNo;
    }

    public String getShipVendorID() {
        return ShipVendorID;
    }

    public void setShipVendorID(String shipVendorID) {
        ShipVendorID = shipVendorID;
    }
}

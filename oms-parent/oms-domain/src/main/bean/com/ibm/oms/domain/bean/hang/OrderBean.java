package com.ibm.oms.domain.bean.hang;

import java.util.List;

/**
 * @author: mr.kai
 * @Description: 中台->wms销售订单发货通知调用Bean
 * @create: 2018-03-09 8:04
 **/
public class OrderBean implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Consignor;
    private String BranchCode;
    private String BillNo;
    private String BillType;
    private String CompanyCode;
    private String CompanyName;
    private String DoType;
    private String SD_ChannelID;
    private String CreateDate;
    private String BillDate;
    private String TotSaleAmt;
    private String DiscountAmt;
    private String TotAmt;
    private String PayedAmt;
    private String postfee;
    private String serviceFee;
    private String ShipVendorCode;
    private String ShipBillID;
    private String CBIssueBillID;
    private String DeliverscheduleType;
    private String REQArrivvalDate;
    private String BookTimeSection;
    private String AddressPostCode;
    private String ShipToProvince;
    private String ShipToCity;
    private String ShipToArea;
    private String ShipToTown;
    private String Deliver_lxr;
    private String Deliver_Nick;
    private String Deliver_TEL;
    private String FromAddressPostCode;
    private String ShipFromProvince;
    private String ShipFromCity;
    private String ShipFromArea;
    private String ShipFromTown;
    private String ShipFromAddress;
    private String ShipFrom_LXR;
    private String ShipFrom_TEL;
    private String CustRemark;

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    private List<OrderItem> orderItemList;

    public OrderBean() {
    }

    public String getConsignor() {
        return this.Consignor;
    }

    public void setConsignor(String consignor) {
        this.Consignor = consignor;
    }

    public String getBranchCode() {
        return this.BranchCode;
    }

    public void setBranchCode(String branchCode) {
        this.BranchCode = branchCode;
    }

    public String getBillNo() {
        return this.BillNo;
    }

    public void setBillNo(String billNo) {
        this.BillNo = billNo;
    }

    public String getBillType() {
        return this.BillType;
    }

    public void setBillType(String billType) {
        this.BillType = billType;
    }

    public String getCompanyCode() {
        return this.CompanyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.CompanyCode = companyCode;
    }

    public String getCompanyName() {
        return this.CompanyName;
    }

    public void setCompanyName(String companyName) {
        this.CompanyName = companyName;
    }

    public String getDoType() {
        return this.DoType;
    }

    public void setDoType(String doType) {
        this.DoType = doType;
    }

    public String getSD_ChannelID() {
        return this.SD_ChannelID;
    }

    public void setSD_ChannelID(String sD_ChannelID) {
        this.SD_ChannelID = sD_ChannelID;
    }

    public String getCreateDate() {
        return this.CreateDate;
    }

    public void setCreateDate(String createDate) {
        this.CreateDate = createDate;
    }

    public String getBillDate() {
        return this.BillDate;
    }

    public void setBillDate(String billDate) {
        this.BillDate = billDate;
    }

    public String getTotSaleAmt() {
        return this.TotSaleAmt;
    }

    public void setTotSaleAmt(String totSaleAmt) {
        this.TotSaleAmt = totSaleAmt;
    }

    public String getDiscountAmt() {
        return this.DiscountAmt;
    }

    public void setDiscountAmt(String discountAmt) {
        this.DiscountAmt = discountAmt;
    }

    public String getTotAmt() {
        return this.TotAmt;
    }

    public void setTotAmt(String totAmt) {
        this.TotAmt = totAmt;
    }

    public String getPayedAmt() {
        return this.PayedAmt;
    }

    public void setPayedAmt(String payedAmt) {
        this.PayedAmt = payedAmt;
    }

    public String getPostfee() {
        return this.postfee;
    }

    public void setPostfee(String postfee) {
        this.postfee = postfee;
    }

    public String getServiceFee() {
        return this.serviceFee;
    }

    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getShipVendorCode() {
        return this.ShipVendorCode;
    }

    public void setShipVendorCode(String shipVendorCode) {
        this.ShipVendorCode = shipVendorCode;
    }

    public String getShipBillID() {
        return this.ShipBillID;
    }

    public void setShipBillID(String shipBillID) {
        this.ShipBillID = shipBillID;
    }

    public String getCBIssueBillID() {
        return this.CBIssueBillID;
    }

    public void setCBIssueBillID(String cBIssueBillID) {
        this.CBIssueBillID = cBIssueBillID;
    }

    public String getDeliverscheduleType() {
        return this.DeliverscheduleType;
    }

    public void setDeliverscheduleType(String deliverscheduleType) {
        this.DeliverscheduleType = deliverscheduleType;
    }

    public String getREQArrivvalDate() {
        return this.REQArrivvalDate;
    }

    public void setREQArrivvalDate(String rEQArrivvalDate) {
        this.REQArrivvalDate = rEQArrivvalDate;
    }

    public String getBookTimeSection() {
        return this.BookTimeSection;
    }

    public void setBookTimeSection(String bookTimeSection) {
        this.BookTimeSection = bookTimeSection;
    }

    public String getAddressPostCode() {
        return this.AddressPostCode;
    }

    public void setAddressPostCode(String addressPostCode) {
        this.AddressPostCode = addressPostCode;
    }

    public String getShipToProvince() {
        return this.ShipToProvince;
    }

    public void setShipToProvince(String shipToProvince) {
        this.ShipToProvince = shipToProvince;
    }

    public String getShipToCity() {
        return this.ShipToCity;
    }

    public void setShipToCity(String shipToCity) {
        this.ShipToCity = shipToCity;
    }

    public String getShipToArea() {
        return this.ShipToArea;
    }

    public void setShipToArea(String shipToArea) {
        this.ShipToArea = shipToArea;
    }

    public String getShipToTown() {
        return this.ShipToTown;
    }

    public void setShipToTown(String shipToTown) {
        this.ShipToTown = shipToTown;
    }

    public String getDeliver_lxr() {
        return this.Deliver_lxr;
    }

    public void setDeliver_lxr(String deliver_lxr) {
        this.Deliver_lxr = deliver_lxr;
    }

    public String getDeliver_Nick() {
        return this.Deliver_Nick;
    }

    public void setDeliver_Nick(String deliver_Nick) {
        this.Deliver_Nick = deliver_Nick;
    }

    public String getDeliver_TEL() {
        return this.Deliver_TEL;
    }

    public void setDeliver_TEL(String deliver_TEL) {
        this.Deliver_TEL = deliver_TEL;
    }

    public String getFromAddressPostCode() {
        return this.FromAddressPostCode;
    }

    public void setFromAddressPostCode(String fromAddressPostCode) {
        this.FromAddressPostCode = fromAddressPostCode;
    }

    public String getShipFromProvince() {
        return this.ShipFromProvince;
    }

    public void setShipFromProvince(String shipFromProvince) {
        this.ShipFromProvince = shipFromProvince;
    }

    public String getShipFromCity() {
        return this.ShipFromCity;
    }

    public void setShipFromCity(String shipFromCity) {
        this.ShipFromCity = shipFromCity;
    }

    public String getShipFromArea() {
        return this.ShipFromArea;
    }

    public void setShipFromArea(String shipFromArea) {
        this.ShipFromArea = shipFromArea;
    }

    public String getShipFromTown() {
        return this.ShipFromTown;
    }

    public void setShipFromTown(String shipFromTown) {
        this.ShipFromTown = shipFromTown;
    }

    public String getShipFromAddress() {
        return this.ShipFromAddress;
    }

    public void setShipFromAddress(String shipFromAddress) {
        this.ShipFromAddress = shipFromAddress;
    }

    public String getShipFrom_LXR() {
        return this.ShipFrom_LXR;
    }

    public void setShipFrom_LXR(String shipFrom_LXR) {
        this.ShipFrom_LXR = shipFrom_LXR;
    }

    public String getShipFrom_TEL() {
        return this.ShipFrom_TEL;
    }

    public void setShipFrom_TEL(String shipFrom_TEL) {
        this.ShipFrom_TEL = shipFrom_TEL;
    }

    public String getCustRemark() {
        return this.CustRemark;
    }

    public void setCustRemark(String custRemark) {
        this.CustRemark = custRemark;
    }

    public List<OrderItem> getOrderItemList() {
        return this.orderItemList;
    }
}

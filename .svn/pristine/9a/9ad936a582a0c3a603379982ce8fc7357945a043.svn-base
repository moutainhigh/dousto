package com.ibm.oms.client.dto.order;

import java.io.Serializable;
import java.util.List;

/**
 * @author: mr.kai
 * @Description: 销售订单退回表
 * @create: 2018-04-02 9:53
 **/
public class OrderSalesCancel implements Serializable {
    public OrderSalesCancel() {
    }
    private String Consignor;//货主 ID
    private String BranchCode;//仓储编码
    private String BillNo;//仓储中心入库订单 编码
    private String BillType;//"单据类型： 302 调 拨入库单 501 销 退入库单 601 采 购入库单 904 普 通入库单 306 B2B 入库单 604 B2B 干线退货入库  704 库存状态调整 入库
    private String PoType;//PoType "
    private String SourceChannelID;//仓库订单需要履行 服务标识 适用如下场景： 退货入库: 8:退换货 9:上门服务 13: 退货收取发票 31：退货入库 36：退货时同时换 货 其他单据忽略
    private String OperateDate;//适用如下场景： 退货入库: 订单来源（213 天 猫，201 淘宝，214 京东，202 1688 阿 里中文站 ，203 苏 宁在线，204 亚马 逊中国，205 当当， 208 1 号店，207 唯 品会，209 国美在 线，210 拍拍，206 易贝 ebay，211 聚 美优品，212 乐蜂 网，215 邮乐，216 凡客，217 优购， 218 银泰，219 易 讯，221 聚尚网， 222 蘑菇街，223 POS 门店，301 其 他 不在范围之内 请忽略） 其他单据忽略
    private String VendorCode;// 订单创建时间(格 式为 YYYY-MM-DD HH:mm:ss)
    private String VendorName;//供应商编码，往来 单位编码 适用如下场景： 采购入库单
    private String ShipVendorID;//供应商名称，往来 单位名称 适用如下场景： 采购入库单
    private String ShipBillID;//配送公司编码 适用如下场景：销 退买家拒签：原发 货单的配送公司编 码； 销退上门取件，消 费者退货，商家下 单：新生成的运单 号所属配送公司的 编码
    private String RefBillNo;//运单号 适用如下场景： 销退买家拒签：原 发货单的运单号 销退上门取件，消 费者退货，商家下 单：新生成的运单 号
    private String RTReason;//原仓储作业单号 适用如下场景： 销退入库单：原发 货单号，注意：原 发货单可能是其他 仓库发出
    private String ShipFrom;//退货原因：销退场 景下，如可能请提 供退货的原因，多 个退货原因用；号 分开
    private String Vendor_LXR;//交货方地址
    private String Vendor_Contact;//交货方联系人
    private String Vendor_Tel;//交货方联系电话 (手机)
    private String ShipFromProvince;//交货方联系电话 (座机)
    private String ShipFromCity;//交货省份
    private String ShipFromArea;//交货城市
    private String ShipFromTown;//交货区县
    private String remark;//交货镇/街道
    private List<OrderSalesItemCancel> items;

    public String getConsignor() {
        return Consignor;
    }

    public void setConsignor(String consignor) {
        Consignor = consignor;
    }

    public String getBranchCode() {
        return BranchCode;
    }

    public void setBranchCode(String branchCode) {
        BranchCode = branchCode;
    }

    public String getBillNo() {
        return BillNo;
    }

    public void setBillNo(String billNo) {
        BillNo = billNo;
    }

    public String getBillType() {
        return BillType;
    }

    public void setBillType(String billType) {
        BillType = billType;
    }

    public String getPoType() {
        return PoType;
    }

    public void setPoType(String poType) {
        PoType = poType;
    }

    public String getSourceChannelID() {
        return SourceChannelID;
    }

    public void setSourceChannelID(String sourceChannelID) {
        SourceChannelID = sourceChannelID;
    }

    public String getOperateDate() {
        return OperateDate;
    }

    public void setOperateDate(String operateDate) {
        OperateDate = operateDate;
    }

    public String getVendorCode() {
        return VendorCode;
    }

    public void setVendorCode(String vendorCode) {
        VendorCode = vendorCode;
    }

    public String getVendorName() {
        return VendorName;
    }

    public void setVendorName(String vendorName) {
        VendorName = vendorName;
    }

    public String getShipVendorID() {
        return ShipVendorID;
    }

    public void setShipVendorID(String shipVendorID) {
        ShipVendorID = shipVendorID;
    }

    public String getShipBillID() {
        return ShipBillID;
    }

    public void setShipBillID(String shipBillID) {
        ShipBillID = shipBillID;
    }

    public String getRefBillNo() {
        return RefBillNo;
    }

    public void setRefBillNo(String refBillNo) {
        RefBillNo = refBillNo;
    }

    public String getRTReason() {
        return RTReason;
    }

    public void setRTReason(String RTReason) {
        this.RTReason = RTReason;
    }

    public String getShipFrom() {
        return ShipFrom;
    }

    public void setShipFrom(String shipFrom) {
        ShipFrom = shipFrom;
    }

    public String getVendor_LXR() {
        return Vendor_LXR;
    }

    public void setVendor_LXR(String vendor_LXR) {
        Vendor_LXR = vendor_LXR;
    }

    public String getVendor_Contact() {
        return Vendor_Contact;
    }

    public void setVendor_Contact(String vendor_Contact) {
        Vendor_Contact = vendor_Contact;
    }

    public String getVendor_Tel() {
        return Vendor_Tel;
    }

    public void setVendor_Tel(String vendor_Tel) {
        Vendor_Tel = vendor_Tel;
    }

    public String getShipFromProvince() {
        return ShipFromProvince;
    }

    public void setShipFromProvince(String shipFromProvince) {
        ShipFromProvince = shipFromProvince;
    }

    public String getShipFromCity() {
        return ShipFromCity;
    }

    public void setShipFromCity(String shipFromCity) {
        ShipFromCity = shipFromCity;
    }

    public String getShipFromArea() {
        return ShipFromArea;
    }

    public void setShipFromArea(String shipFromArea) {
        ShipFromArea = shipFromArea;
    }

    public String getShipFromTown() {
        return ShipFromTown;
    }

    public void setShipFromTown(String shipFromTown) {
        ShipFromTown = shipFromTown;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<OrderSalesItemCancel> getItems() {
        return items;
    }

    public void setItems(List<OrderSalesItemCancel> items) {
        this.items = items;
    }
}

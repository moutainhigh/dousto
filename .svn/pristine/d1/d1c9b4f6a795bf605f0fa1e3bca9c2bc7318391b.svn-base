package com.ibm.oms.intf.intf;
//package com.ibm.sc.service.oms.intf;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.util.List;
//
//import com.ibm.sc.service.oms.intf.inner.InventoryDeductDetail;
//
///**
// * @author liucy
// * 
// *         商品行商品出库成本价
// */
//public class InventoryDeductDTO implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    /** 子订单行号 **/
//    /** 仓库 **/
//    private String warehouseID;
//    /** 仓库 **/
//    private String toWarehouseID;
//    /** 订单号 order.id **/
//    private String orderNo;
//    /** 订单类型 SO:正常订单；TH:换货出库单 **/
//    private String orderType;
//    /** 订单创建时间 订单创建时间YYYY-MM-DD HH24:MI:SS **/
//    private String orderime;
//    /** 要求交货时间 收货时间YYYY-MM-DD HH24:MI:SS **/
//    private String RequiredDeliveryTime;
//    /** 货主 默认：01 **/
//    private String CustomerID;
//    /** 参考编号1 正常订单：外部渠道如btc单号，换货订单：“” **/
//    private String soReference1;
//    /**
//     * 参考编号2 默认传：""。
//     **/
//    private String soReference2;
//    /** 参考编号3 送货前是否需要确认；默认："" **/
//    private String soReference3;
//    /** 参考编号4 默认："" **/
//    private String soReference4;
//    /** 参考编号5 会员等级 例如：大客户组、银卡会员等 **/
//    private String soReference5;
//    /** 订单优先级别 默认：9 **/
//    private String priority;
//    /** 承运人 第三方物流商 id **/
//    private String carrierID;
//    /** 承运商名称（新增） 第三方物流商 中文名称 **/
//    private String carrierName;
//    /** 收货人 收货人编号 默认为：0 **/
//    private String consigneeID;
//    /** 收货人名称 收货人名称 **/
//    private String consigneeName;
//    /** 地址1 收货人地址 顾客自己填写的地址（最小地址） **/
//    private String c_Address1;
//    /** 地址2 配送方式中文名称 如：天虹配送、天虹门店自提等 **/
//    private String c_Address2;
//    /** 地址3 区域代码 区域最小编码 **/
//    private String c_Address3;
//    /** 地址4 默认："" **/
//    private String c_Address4;
//    /** 城市默认："" **/
//    private String c_City;
//    /** 省默认："" **/
//    private String c_Province;
//    /** 邮编 商城没有邮编，默认："" **/
//    private String c_ZIP;
//    /** 联系人 购买人名称 **/
//    private String c_Contact;
//    /** 电话1 收货人移动电话 **/
//    private String c_Tel1;
//    /** 电话1 收货人固话 **/
//    private String c_Tel2;
//    /** 路径 默认："" **/
//    private String route;
//    /**
//     * 订单来源 订单来源中文:商城订单来源
//     **/
//    private String userDefine5;
//    /** 备注 顾客留言 配送方式：送货时间 + 其他要求.顾客留言 配送方式：送货时间 + 其他要求 **/
//    private String notes;
//    /** EDI信息 后台客服留言，需打印至拣货单和物流交接单.后台客服留言，需打印至拣货单和物流交接单 **/
//    private String h_EDI_01;
//    /** EDI信息 配送要求, 配送要求 **/
//    private String h_EDI_02;
//    /** EDI信息 orderNo **/
//    private String h_EDI_03;
//    /** EDI信息 订单总重量 **/
//    private String h_EDI_04;
//    /** EDI信息 货到付款:银行卡、现金、天虹卡.之一; 在线支付："" **/
//    private String h_EDI_05;
//    /** EDI信息 发票抬头（个人、公司名称） **/
//    private String h_EDI_06;
//    /** EDI信息 发票类型0.普通发票1.增值税发票2.无需发票 **/
//    private String h_EDI_07;
//    /** EDI信息 是否打印发票0:不打印；1：打印 **/
//    private String h_EDI_08;
//    /** EDI信息 默认："" **/
//    private String h_EDI_09;
//    /** EDI信息 默认：0 **/
//    private String h_EDI_10;
//    /** EDI信息 **/
//    private String h_EDI_11;
//    /** EDI信息 **/
//    private String h_EDI_12;
//    /** EDI信息 **/
//    private String h_EDI_13;
//    /** EDI信息 **/
//    private String h_EDI_14;
//    /** EDI信息 **/
//    private String h_EDI_15;
//    /** EDI信息 **/
//    private String h_EDI_16;
//    /** EDI信息 **/
//    private String h_EDI_17;
//    /** EDI信息 **/
//    private String h_EDI_18;
//    /** EDI信息 **/
//    private String h_EDI_19;
//    /** EDI信息 **/
//    private String h_EDI_20;
//
//    private List<InventoryDeductDetail> soDetails;
//
//    public String getWarehouseID() {
//        return warehouseID;
//    }
//
//    public void setWarehouseID(String warehouseID) {
//        this.warehouseID = warehouseID;
//    }
//
//    public String getToWarehouseID() {
//        return toWarehouseID;
//    }
//
//    public void setToWarehouseID(String toWarehouseID) {
//        this.toWarehouseID = toWarehouseID;
//    }
//
//    public String getOrderNo() {
//        return orderNo;
//    }
//
//    public void setOrderNo(String orderNo) {
//        this.orderNo = orderNo;
//    }
//
//    public String getOrderType() {
//        return orderType;
//    }
//
//    public void setOrderType(String orderType) {
//        this.orderType = orderType;
//    }
//
//    public String getOrderime() {
//        return orderime;
//    }
//
//    public void setOrderime(String orderime) {
//        this.orderime = orderime;
//    }
//
//    public String getRequiredDeliveryTime() {
//        return RequiredDeliveryTime;
//    }
//
//    public void setRequiredDeliveryTime(String requiredDeliveryTime) {
//        RequiredDeliveryTime = requiredDeliveryTime;
//    }
//
//    public String getCustomerID() {
//        return CustomerID;
//    }
//
//    public void setCustomerID(String customerID) {
//        CustomerID = customerID;
//    }
//
//    public String getSoReference1() {
//        return soReference1;
//    }
//
//    public void setSoReference1(String soReference1) {
//        this.soReference1 = soReference1;
//    }
//
//    public String getSoReference2() {
//        return soReference2;
//    }
//
//    public void setSoReference2(String soReference2) {
//        this.soReference2 = soReference2;
//    }
//
//    public String getSoReference3() {
//        return soReference3;
//    }
//
//    public void setSoReference3(String soReference3) {
//        this.soReference3 = soReference3;
//    }
//
//    public String getSoReference4() {
//        return soReference4;
//    }
//
//    public void setSoReference4(String soReference4) {
//        this.soReference4 = soReference4;
//    }
//
//    public String getSoReference5() {
//        return soReference5;
//    }
//
//    public void setSoReference5(String soReference5) {
//        this.soReference5 = soReference5;
//    }
//
//    public String getPriority() {
//        return priority;
//    }
//
//    public void setPriority(String priority) {
//        this.priority = priority;
//    }
//
//    public String getCarrierID() {
//        return carrierID;
//    }
//
//    public void setCarrierID(String carrierID) {
//        this.carrierID = carrierID;
//    }
//
//    public String getCarrierName() {
//        return carrierName;
//    }
//
//    public void setCarrierName(String carrierName) {
//        this.carrierName = carrierName;
//    }
//
//    public String getConsigneeID() {
//        return consigneeID;
//    }
//
//    public void setConsigneeID(String consigneeID) {
//        this.consigneeID = consigneeID;
//    }
//
//    public String getConsigneeName() {
//        return consigneeName;
//    }
//
//    public void setConsigneeName(String consigneeName) {
//        this.consigneeName = consigneeName;
//    }
//
//    public String getC_Address1() {
//        return c_Address1;
//    }
//
//    public void setC_Address1(String c_Address1) {
//        this.c_Address1 = c_Address1;
//    }
//
//    public String getC_Address2() {
//        return c_Address2;
//    }
//
//    public void setC_Address2(String c_Address2) {
//        this.c_Address2 = c_Address2;
//    }
//
//    public String getC_Address3() {
//        return c_Address3;
//    }
//
//    public void setC_Address3(String c_Address3) {
//        this.c_Address3 = c_Address3;
//    }
//
//    public String getC_Address4() {
//        return c_Address4;
//    }
//
//    public void setC_Address4(String c_Address4) {
//        this.c_Address4 = c_Address4;
//    }
//
//    public String getC_City() {
//        return c_City;
//    }
//
//    public void setC_City(String c_City) {
//        this.c_City = c_City;
//    }
//
//    public String getC_Province() {
//        return c_Province;
//    }
//
//    public void setC_Province(String c_Province) {
//        this.c_Province = c_Province;
//    }
//
//    public String getC_ZIP() {
//        return c_ZIP;
//    }
//
//    public void setC_ZIP(String c_ZIP) {
//        this.c_ZIP = c_ZIP;
//    }
//
//    public String getC_Contact() {
//        return c_Contact;
//    }
//
//    public void setC_Contact(String c_Contact) {
//        this.c_Contact = c_Contact;
//    }
//
//    public String getC_Tel1() {
//        return c_Tel1;
//    }
//
//    public void setC_Tel1(String c_Tel1) {
//        this.c_Tel1 = c_Tel1;
//    }
//
//    public String getC_Tel2() {
//        return c_Tel2;
//    }
//
//    public void setC_Tel2(String c_Tel2) {
//        this.c_Tel2 = c_Tel2;
//    }
//
//    public String getRoute() {
//        return route;
//    }
//
//    public void setRoute(String route) {
//        this.route = route;
//    }
//
//    public String getUserDefine5() {
//        return userDefine5;
//    }
//
//    public void setUserDefine5(String userDefine5) {
//        this.userDefine5 = userDefine5;
//    }
//
//    public String getNotes() {
//        return notes;
//    }
//
//    public void setNotes(String notes) {
//        this.notes = notes;
//    }
//
//    public String getH_EDI_01() {
//        return h_EDI_01;
//    }
//
//    public void setH_EDI_01(String h_EDI_01) {
//        this.h_EDI_01 = h_EDI_01;
//    }
//
//    public String getH_EDI_02() {
//        return h_EDI_02;
//    }
//
//    public void setH_EDI_02(String h_EDI_02) {
//        this.h_EDI_02 = h_EDI_02;
//    }
//
//    public String getH_EDI_03() {
//        return h_EDI_03;
//    }
//
//    public void setH_EDI_03(String h_EDI_03) {
//        this.h_EDI_03 = h_EDI_03;
//    }
//
//    public String getH_EDI_04() {
//        return h_EDI_04;
//    }
//
//    public void setH_EDI_04(String h_EDI_04) {
//        this.h_EDI_04 = h_EDI_04;
//    }
//
//    public String getH_EDI_05() {
//        return h_EDI_05;
//    }
//
//    public void setH_EDI_05(String h_EDI_05) {
//        this.h_EDI_05 = h_EDI_05;
//    }
//
//    public String getH_EDI_06() {
//        return h_EDI_06;
//    }
//
//    public void setH_EDI_06(String h_EDI_06) {
//        this.h_EDI_06 = h_EDI_06;
//    }
//
//    public String getH_EDI_07() {
//        return h_EDI_07;
//    }
//
//    public void setH_EDI_07(String h_EDI_07) {
//        this.h_EDI_07 = h_EDI_07;
//    }
//
//    public String getH_EDI_08() {
//        return h_EDI_08;
//    }
//
//    public void setH_EDI_08(String h_EDI_08) {
//        this.h_EDI_08 = h_EDI_08;
//    }
//
//    public String getH_EDI_09() {
//        return h_EDI_09;
//    }
//
//    public void setH_EDI_09(String h_EDI_09) {
//        this.h_EDI_09 = h_EDI_09;
//    }
//
//    public String getH_EDI_10() {
//        return h_EDI_10;
//    }
//
//    public void setH_EDI_10(String h_EDI_10) {
//        this.h_EDI_10 = h_EDI_10;
//    }
//
//    public String getH_EDI_11() {
//        return h_EDI_11;
//    }
//
//    public void setH_EDI_11(String h_EDI_11) {
//        this.h_EDI_11 = h_EDI_11;
//    }
//
//    public String getH_EDI_12() {
//        return h_EDI_12;
//    }
//
//    public void setH_EDI_12(String h_EDI_12) {
//        this.h_EDI_12 = h_EDI_12;
//    }
//
//    public String getH_EDI_13() {
//        return h_EDI_13;
//    }
//
//    public void setH_EDI_13(String h_EDI_13) {
//        this.h_EDI_13 = h_EDI_13;
//    }
//
//    public String getH_EDI_14() {
//        return h_EDI_14;
//    }
//
//    public void setH_EDI_14(String h_EDI_14) {
//        this.h_EDI_14 = h_EDI_14;
//    }
//
//    public String getH_EDI_15() {
//        return h_EDI_15;
//    }
//
//    public void setH_EDI_15(String h_EDI_15) {
//        this.h_EDI_15 = h_EDI_15;
//    }
//
//    public String getH_EDI_16() {
//        return h_EDI_16;
//    }
//
//    public void setH_EDI_16(String h_EDI_16) {
//        this.h_EDI_16 = h_EDI_16;
//    }
//
//    public String getH_EDI_17() {
//        return h_EDI_17;
//    }
//
//    public void setH_EDI_17(String h_EDI_17) {
//        this.h_EDI_17 = h_EDI_17;
//    }
//
//    public String getH_EDI_18() {
//        return h_EDI_18;
//    }
//
//    public void setH_EDI_18(String h_EDI_18) {
//        this.h_EDI_18 = h_EDI_18;
//    }
//
//    public String getH_EDI_19() {
//        return h_EDI_19;
//    }
//
//    public void setH_EDI_19(String h_EDI_19) {
//        this.h_EDI_19 = h_EDI_19;
//    }
//
//    public String getH_EDI_20() {
//        return h_EDI_20;
//    }
//
//    public void setH_EDI_20(String h_EDI_20) {
//        this.h_EDI_20 = h_EDI_20;
//    }
//
//    public List<InventoryDeductDetail> getSoDetails() {
//        return soDetails;
//    }
//
//    public void setSoDetails(List<InventoryDeductDetail> soDetails) {
//        this.soDetails = soDetails;
//    }
//    
//    
//}

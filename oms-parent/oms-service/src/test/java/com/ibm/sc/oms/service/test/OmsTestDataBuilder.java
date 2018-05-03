package com.ibm.sc.oms.service.test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.beans.stock.OmsSODetails;
import com.beans.stock.OmsSOFreeDetails;
import com.beans.stock.OmsSOHeader;
import com.beans.stock.OmsSOInfo;
import com.beans.stock.StockLockByOms;
import com.beans.stock.StockLockByOmsDetail;
import com.beans.stock.StockUnLockByOms;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.constant.IntfReceiveConst;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.domain.persist.OrderSub_;
import com.ibm.oms.intf.constant.OrderMainConst;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderDTO;
import com.ibm.oms.intf.intf.BtcPayDTO;
import com.ibm.oms.intf.intf.CouponPayDTO;
import com.ibm.oms.intf.intf.InventoryPriceDTO;
import com.ibm.oms.intf.intf.InventoryResendMsgDTO;
import com.ibm.oms.intf.intf.MemberPayDTO;
import com.ibm.oms.intf.intf.OMSStatusUpdateDTO;
import com.ibm.oms.intf.intf.SupportResend;
import com.ibm.oms.intf.intf.TmsOrderDTO;
import com.ibm.oms.intf.intf.TmsPayDTO;
import com.ibm.oms.intf.intf.TmsStatusDTO;
import com.ibm.oms.intf.intf.TransportCompParam;
import com.ibm.oms.intf.intf.WmsOmsReceiveInspectionResultDTO;
import com.ibm.oms.intf.intf.WmsOmsReceiveLogisticsDTO;
import com.ibm.oms.intf.intf.WmsReceiveCostPriceDTO;
import com.ibm.oms.intf.intf.inner.CouponDTO;
import com.ibm.oms.intf.intf.inner.MyCardDTO;
import com.ibm.oms.intf.intf.inner.OrderInvoiceDTO;
import com.ibm.oms.intf.intf.inner.OrderItemDTO;
import com.ibm.oms.intf.intf.inner.OrderItemSnapShotDTO;
import com.ibm.oms.intf.intf.inner.OrderItemTms;
import com.ibm.oms.intf.intf.inner.OrderItemVirtualDTO;
import com.ibm.oms.intf.intf.inner.OrderMainDTO;
import com.ibm.oms.intf.intf.inner.OrderPayDTO;
import com.ibm.oms.intf.intf.inner.OrderPromotionDTO;
import com.ibm.oms.intf.intf.inner.OrderSubDTO;
import com.ibm.oms.intf.intf.inner.PaymentDTO;
import com.ibm.oms.intf.intf.inner.TmsOrderItemsDTO;
import com.ibm.oms.intf.intf.inner.TmsPayDTOElement;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderPayService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.oms.service.business.trans.ImsOmsTransService;
import com.ibm.oms.service.business.trans.impl.ImsOmsTransServiceImpl;
import com.ibm.oms.service.util.CommonUtilService;
import com.ibm.sc.util.CommonUtil;

@Component
public class OmsTestDataBuilder {
    static String dateTime = "2014-04-10 01:01:01";
    static String memberNo = "700";
    static String memberNo_false = "900";
    private static String combineTestNo1 = "1396962681402";
    private static String combineTestNo2 = "ZH1397187791203";
    @Autowired
    ImsOmsTransService imsOmsTransService;
    @Autowired
    OrderSubService orderSubService;
    @Autowired
    CommonUtilService commonUtilService;
    @Autowired
    ImsOmsTransServiceImpl imsOmsTransServiceImpl;
    @Autowired
    OrderMainService orderMainService;
    @Autowired
    OrderPayService orderPayService;
    
    /** 出库 **/
    public OmsSOInfo buildInventoryDeduct() {
        //出库
        OmsSOHeader[] OmsSOHeaders = new OmsSOHeader[1];
        OmsSOHeader OmsSOHeader = new OmsSOHeader();
        OmsSOHeaders[0] = OmsSOHeader;
        OmsSOInfo soInfo = new OmsSOInfo();
        soInfo.setOmsSOHeaders(OmsSOHeaders);
        OmsSOFreeDetails[] detailsFreeItems = new OmsSOFreeDetails[1];
        OmsSODetails[] detailsItems = new OmsSODetails[1];
        
        OmsSOFreeDetails detailsFreeItem = new OmsSOFreeDetails();//订单费用支付详情
        detailsFreeItem.setORDERNO("ORDERNO201403142");
        detailsFreeItem.setLINENUMBER("1");
        detailsFreeItem.setRATEUOM1("支付");
        detailsFreeItem.setCHARGETYPE("网银在线支付");
        detailsFreeItem.setCHARGEFEE("52.00");
        detailsFreeItem.setEXTENTS_UDF01("300");
        detailsFreeItem.setCHARGECATEGORY("网银在线支付");
        detailsFreeItem.setCHARGEDESCR("0");
        
        OmsSODetails detailsItem = new OmsSODetails();//订单详情
        detailsItem.setORDERNO("ORDERNO201403142");
        detailsItem.setSKU("SKU_030309001");
        detailsItem.setQTYORDERED("2");
        detailsItem.setQTYPICKED_EACH("2");
        detailsItem.setD_EDI_02("52.00");//总金额
        detailsItem.setUSERDEFINE1("");
        detailsItem.setUSERDEFINE2("");
        detailsItem.setUSERDEFINE3("");
        detailsItem.setUSERDEFINE4("");
        detailsItem.setD_EDI_01("26.00");
        detailsFreeItems[0] = detailsFreeItem;
        detailsItems[0] = detailsItem;
        OmsSOHeader.setDetailsFreeItem(detailsFreeItems);
        OmsSOHeader.setDetailsItem(detailsItems);
        OmsSOHeader.setWAREHOUSEID("0196");
        OmsSOHeader.setOrderNo(CommonTestConst.targetOrderSubNo);
        OmsSOHeader.setORDERTYPE("SO");
        OmsSOHeader.setORDERTIME("2014-04-25 15:35:50");
        OmsSOHeader.setREQUIREDDELIVERYTIME("2014-04-27 15:35:50");
        OmsSOHeader.setCUSTOMERID("01");
        OmsSOHeader.setSOREFERENCE1("13168721397321");
        OmsSOHeader.setSOREFERENCE2("");
        OmsSOHeader.setSOREFERENCE3("");
        OmsSOHeader.setSOREFERENCE4("");
        OmsSOHeader.setSOREFERENCE5("大客户组");
        OmsSOHeader.setPRIORITY("9");
        OmsSOHeader.setCARRIERID("98650");
        OmsSOHeader.setCARRIERNAME("飞远物流");
        OmsSOHeader.setCONSIGNEEID("0");
        OmsSOHeader.setCONSIGNEENAME("王天虹");
        OmsSOHeader.setC_ADDRESS1("网上天虹分拣中心");
        OmsSOHeader.setC_ADDRESS2("天虹配送");
        OmsSOHeader.setC_ADDRESS3("10000019");
        OmsSOHeader.setC_ADDRESS4("");
        OmsSOHeader.setC_CITY("");
        OmsSOHeader.setC_PROVINCE("");
        OmsSOHeader.setC_ZIP("");
        OmsSOHeader.setC_CONTACT("王天虹");
        OmsSOHeader.setC_TEL1("13100000000");
        OmsSOHeader.setC_TEL2("07550000000");
        OmsSOHeader.setROUTE("");
        OmsSOHeader.setUSERDEFINE5("");
        OmsSOHeader.setNOTES("只在白天送货");
        OmsSOHeader.setH_EDI_01("客服留言");
        OmsSOHeader.setH_EDI_02("请小心货物");
        OmsSOHeader.setH_EDI_03("请小心货物");
        OmsSOHeader.setH_EDI_04("25.68");
        OmsSOHeader.setH_EDI_05("");
        OmsSOHeader.setH_EDI_06("个人");
        OmsSOHeader.setH_EDI_07("0");
        OmsSOHeader.setH_EDI_08("1");
        OmsSOHeader.setH_EDI_09("");
        OmsSOHeader.setH_EDI_10("");
        return soInfo;
    }

    /**锁库**/
    public StockLockByOms buildInventoryLock() {
        StockLockByOms sbo = new StockLockByOms();
        sbo.setBookDate(new Date());
        sbo.setCreator("test");
        sbo.setCustName("test");
        sbo.setOrderNo(CommonTestConst.targetOrderSubNo);
        List<StockLockByOmsDetail> stockLockByOmsDetails = new ArrayList<StockLockByOmsDetail>();
        StockLockByOmsDetail stockLockByOmsDetail = new StockLockByOmsDetail();
        stockLockByOmsDetail.setBarCode("a");
        stockLockByOmsDetail.setChannelCode("PC");
//        stockLockByOmsDetail.setChannelId(1l);
        stockLockByOmsDetail.setOdd(1l);
        stockLockByOmsDetail.setPackSize(1l);
        stockLockByOmsDetail.setPcs(1l);
        stockLockByOmsDetail.setProdType(1l);
        stockLockByOmsDetail.setPromotionCode("001");
        stockLockByOmsDetail.setQty(10l);
        stockLockByOmsDetail.setRemark("");
        stockLockByOmsDetail.setRows(1l);
        stockLockByOmsDetail.setSeqNo(1l);
        stockLockByOmsDetail.setSkuCode("SKU_030309001");
//        stockLockByOmsDetail.setSkuId(1l);

//        SkuStock skuStock = new SkuStock();
//        stockLockByOmsDetail.setSkuStock(skuStock);
        stockLockByOmsDetails.add(stockLockByOmsDetail);
        sbo.setStockLockByOmsDetails(stockLockByOmsDetails);
        return sbo;
    }
    
    /**锁库**/
    public InventoryResendMsgDTO buildResendInventory() {
        return imsOmsTransService.queryInventoryResend(CommonTestConst.targetOrderItemVirtualNo);
    }
    public SupportResend buildSupportResend() {
        OrderSub os = orderSubService.get(OrderSub_.orderSubNo, CommonTestConst.targetOrderSubNo);
        Map<String,Object> date=new HashMap<String, Object>();
        date.put("checkCode", os.getCheckCode());//验证码
        date.put("recipients", os.getMobPhoneNum());//手机号码
        
        SupportResend so=new SupportResend();
        so.setCode("CM-BBC-ZITI");
        so.setType("sms");
        so.setData(date);
        return so;
    }
    public BtcOmsReceiveOrderDTO buildBtcInput() {
        BtcOmsReceiveOrderDTO ret = new BtcOmsReceiveOrderDTO();
        ret.setBatchNum("11111111");
        OrderMainDTO omDTO = new OrderMainDTO();

        omDTO.setAliasOrderNo("11");
        omDTO.setCustomerName("11");
        omDTO.setCustomerPhone("11");
        omDTO.setDeliveryDateFlag("11");
        omDTO.setDeliveryTimeFlag("11");
        omDTO.setDiscountTotal("11");
        omDTO.setDiscountTransport("11");
        omDTO.setIfPayOnArrival("1");
        omDTO.setIfShowPrice("1");
        omDTO.setMemberNo(memberNo);
        omDTO.setMerchantNo("11");
        omDTO.setMerchantType("11");
        omDTO.setNeedConfirm("1");
        omDTO.setNeedInvoice("1");
        omDTO.setOrderSource("PC");
        omDTO.setOrderTime(dateTime);
        omDTO.setOrderType("11");
        omDTO.setReceiveAreaId("11");
        omDTO.setClientRemark("11");
        omDTO.setTotalGivePoints("11");
        omDTO.setTotalPayAmount("11");
        omDTO.setTotalProductPrice("11");
        omDTO.setTotalPromo("11");
        omDTO.setTransportFee("11");
        omDTO.setWeight("11");
        
        omDTO.setOsDTOs(buildOSDTO());
        omDTO.setOpDTOs(buildOPDTO(CommonConst.OrderPromotion_Promolevel_Order.getCode()));
        omDTO.setOrderPayDTOs(buildOrderPayDTO());

        List<OrderMainDTO> omDTOList = new ArrayList<OrderMainDTO>();
        omDTOList.add(omDTO);
        ret.setOmDTO(omDTOList);
        return ret;
    }
    
    private static List<OrderItemDTO> buildOIDTO() {
        List<OrderItemDTO> listOIDTO = new ArrayList<OrderItemDTO>();
        OrderItemDTO oiDTO = new OrderItemDTO();
//        ProductBean pb = new ProductBean();
//        pb.setIsLowGross(true);
//        oiDTO.setProductBean(pb);
        oiDTO.setAdsPage("11");
        oiDTO.setAliasOrderItemNo("11");
        oiDTO.setBarCode("11");
        oiDTO.setBrand("11");
        oiDTO.setCommodityCode(combineTestNo1);
        oiDTO.setCommodityName("11");
        oiDTO.setCouponAmount("11");
        oiDTO.setGiftOriginItem("11");
        oiDTO.setHasGift(1);
        oiDTO.setInfoSource("11");
        oiDTO.setOpDTOs(buildOPDTO(CommonConst.OrderPromotion_Promolevel_Item.getCode()));
        oiDTO.setOrderItemClass(OrderMainConst.OrderItem_OrderItemClass_SUITE.getCode());
        oiDTO.setOrderItemRemark("11");
        oiDTO.setPartnerNo("11");
        oiDTO.setPayAmount("11");
        oiDTO.setProductCategory("11");
        oiDTO.setProductGroup("11");
        oiDTO.setProductPropertyFlag("0");
        oiDTO.setPromoId("11");
        oiDTO.setPromoTicketMoney("11");
        oiDTO.setPromoType("11");
        oiDTO.setPromotionCode("11");
        oiDTO.setPromotionType("11");
        oiDTO.setSaleNum("11");
        oiDTO.setSaleUnit("11");
        oiDTO.setSkuNo("SKU-00");
        oiDTO.setStockNo("11");
        oiDTO.setSupplierCode("11");
        oiDTO.setUnitDeductedPrice("11");
        oiDTO.setUnitDiscount("11");
        oiDTO.setUnitPrice("11");
        oiDTO.setWeight("11");
        oiDTO.setCombineName("11");
//        oiDTO.setCombineNo(combineTestNo1);
//        oiDTO.setCombineNo("ZH1397187791203");
        oiDTO.setCombineRule("11");
        oiDTO.setCombineType("11");
        oiDTO.setAliasOrderNo("11");
        oiDTO.setAliasOrderSubNo("11");
        oiDTO.setAliasOrderItemNo("11");
        oiDTO.setItemSource("11");
        oiDTO.setCouponTotalMoney("11");
        oiDTO.setItemDiscount("0.5");
        OrderItemSnapShotDTO itemSnapshot = new OrderItemSnapShotDTO();
        itemSnapshot.setBrandCode("11");
        itemSnapshot.setBrandName("11");
        itemSnapshot.setCategoryCode("11");
        itemSnapshot.setCategoryName("11");
//        itemSnapshot.setShipCat("992");online
        itemSnapshot.setShipCat("992");
        itemSnapshot.setPoint(new BigDecimal(11));
        ObjectMapper mapper = new ObjectMapper();
        String itemSnapshotStr = null;
        try {
            itemSnapshotStr = mapper.writeValueAsString(itemSnapshot);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        oiDTO.setItemSnapshot(itemSnapshotStr);
        listOIDTO.add(oiDTO);
        return listOIDTO;
    }
    
    private static List<OrderPromotionDTO> buildOPDTO(String level) {
        List<OrderPromotionDTO> opList = new ArrayList<OrderPromotionDTO>();
        OrderPromotionDTO op = new OrderPromotionDTO();
        op.setIdOrderItem("11");
        op.setMemberNo("11");
        op.setPointCount("11");
        op.setPromoLevel(level);
        op.setPromoName("11");
        op.setPromoNo("11");
        op.setPromoType("11");
        op.setTicketAmount("11");
        op.setTicketBundleNo("11");
        op.setTicketNo("11");
        opList.add(op);
        return opList;
    }
    


    private static List<OrderSubDTO> buildOSDTO() {
        OrderSubDTO os = new OrderSubDTO();
        os.setDeliveryMerchantNo("11");
        os.setDeliveryPriority("11");
        os.setDeliveryRemark("11");
//        os.setDistributeType("self_take");online
        os.setDistributeType("1");
        os.setHopeArrivalTime("11");
        os.setLogisticsOutNo("11");
        os.setOiDTOs(buildOIDTO());
        os.setPackageDetail("11");
        os.setPickTime("2014-01-01");
        os.setProvideAddress("11");
        os.setSelfFetchAddress("11");
//        os.setStoreNo("1"); online
        os.setStoreNo("1");
        os.setTransportFee("11");
        os.setAliasOrderNo("11");
        os.setAliasOrderSubNo("11");
        os.setUserName("11");
        os.setPhoneNum("11");
        os.setMobPhoneNum("11");
        os.setPostCode("11");
//        os.setAddressCode("120629");online
        os.setAddressCode("120629");
        os.setAddressDetail("11");
        os.setOivDTOs(buildOrderItemVirtualDTO());
        List<OrderSubDTO> los = new ArrayList<OrderSubDTO>();
        los.add(os);
        OrderInvoiceDTO oinvoiceDTO = new OrderInvoiceDTO();
//        oinvoiceDTO.setInvoice_bankAccounet("11");
//        oinvoiceDTO.setInvoiceAddition_info("11");
        oinvoiceDTO.setInvoiceAddress("11");
        oinvoiceDTO.setInvoiceBankName("11");
        oinvoiceDTO.setInvoiceCategory("11");
        oinvoiceDTO.setInvoiceCompany("11");
        oinvoiceDTO.setInvoiceContent("11");
        oinvoiceDTO.setInvoiceHead("11");
        oinvoiceDTO.setInvoiceNum(CommonUtil.getRandomString(10));
        oinvoiceDTO.setInvoiceTaxpayer("11");
        oinvoiceDTO.setInvoiceTelephone("11");
        oinvoiceDTO.setInvoiceToName("11");
        oinvoiceDTO.setInvoiceToTelephone("11");
        oinvoiceDTO.setInvoiceType("11");
        oinvoiceDTO.setRegistryAddress("11");
        os.setOrderInvoice(oinvoiceDTO);
        return los;
    }

    private static List<OrderPayDTO> buildOrderPayDTO() {
        List<OrderPayDTO> opList = new ArrayList<OrderPayDTO>();
        OrderPayDTO op = new OrderPayDTO();
        op.setBankTypeCode("11");
        op.setBankTypeName("11");
        op.setOrderNo("11");
        op.setPayAmount("11");
        op.setPayCode("11");
        op.setPayName("11");
        op.setPayOnArrivalPayType("11");
        op.setPayTime(dateTime);
        opList.add(op);
        return opList;
    }
    
//    private static List<OrderItemGiftInfoDTO> buildOrderItemGiftInfoDTO() {
//        List<OrderItemGiftInfoDTO> opList = new ArrayList<OrderItemGiftInfoDTO>();
//        OrderItemGiftInfoDTO op = new OrderItemGiftInfoDTO();
//        op.setCardAmount("11");
//        op.setCardNo("11");
//        op.setCardPassword("11");
//        op.setCardType("11");
//        op.setOrderItemNo("11");
//        op.setOrderNo("11");
//        op.setSaleNum("11");
//        op.setRemark("11");
//        op.setTotalPrice("11");
//        op.setUnitPrice("11");
//        op.setAliasOrderItemNo("11");
//        op.setAliasOrderNo("11");
//        opList.add(op);
//        return opList;
//    }
    
    private static List<OrderItemVirtualDTO> buildOrderItemVirtualDTO() {
        List<OrderItemVirtualDTO> opList = new ArrayList<OrderItemVirtualDTO>();
        OrderItemVirtualDTO op = new OrderItemVirtualDTO();
        op.setPromotionCode("1001");
        op.setPromotionType("xx");
        op.setOrderItemClass("11");
        op.setProductCode("11");
        op.setReceiveMobile("11");
        op.setReceiveName("11");
        op.setReceiveStatus("11");
        op.setRemark("11");
        op.setSaleAmount("11");
        op.setSaleNum("11");
        op.setUnitPrice("11");
        op.setAliasOrderItemNo("11");
        op.setAliasOrderNo("11");
        op.setSkuNo("SKU-00");
        opList.add(op);
        return opList;
    }

    public BtcPayDTO buildBtcPay() {
        BtcPayDTO btcDTO = new BtcPayDTO();
        ArrayList<PaymentDTO> payList = new ArrayList<PaymentDTO>(); 
        btcDTO.setPaymentDTOs(payList);
        PaymentDTO payment = new PaymentDTO();
        payList.add(payment);
        payment.setPayAmount(new BigDecimal(11));
        btcDTO.setOccurtime("2014-01-09 01:01:01");
        btcDTO.setDataSource("BTC");
        btcDTO.setOperator("11");
        payment.setPayMode("1");//1在线支付
        btcDTO.setRemark("11");
        payment.setPayCode("11");
        payment.setSerialNo("11");
        return btcDTO;
    }

    public TmsPayDTO buildTmsPay() {
        TmsPayDTO tpDTO = new TmsPayDTO();
        tpDTO.setLogisticCompanyId("11");
        TmsPayDTOElement e = new TmsPayDTOElement();
        List<TmsPayDTOElement> eList = new ArrayList<TmsPayDTOElement>();
        eList.add(e);
        e.setMoney(110d);
        Date date = new Date();
        SimpleDateFormat sdf = commonUtilService.format24Hours();
        tpDTO.setOccurtime(sdf.format(date));
        tpDTO.setOperator("11");
        e.setPaymode(1);
        tpDTO.setRemark("11");
        tpDTO.setTxLogisticID(CommonTestConst.targetOrderNo);
        tpDTO.setElement(eList);
        return tpDTO;
    }

    public TmsStatusDTO buildTmsStatus() {
        TmsStatusDTO tsDTO = new TmsStatusDTO();
        Date date = new Date();
        SimpleDateFormat sdf = commonUtilService.format24Hours();
        tsDTO.setAcceptTime(sdf.format(date));
        tsDTO.setAcceptTime("11");
        tsDTO.setInfoType(IntfReceiveConst.SIGN_SUCCESS.getCode());
        tsDTO.setLogisticCompanyId("11");
        tsDTO.setMailNo("11");
        tsDTO.setName("11");
        tsDTO.setRemark("11");
        tsDTO.setTxLogisticID(CommonTestConst.targetOrderNo);
        return tsDTO;
    }
    
    public TmsStatusDTO buildTmsStatusUpdateTopic() {
        TmsStatusDTO tsDTO = new TmsStatusDTO();
        Date date = new Date();
        SimpleDateFormat sdf = commonUtilService.format24Hours();
        tsDTO.setAcceptTime(sdf.format(date));
        tsDTO.setAcceptTime("11");
        tsDTO.setInfoType(IntfReceiveConst.SIGN_SUCCESS.getCode());
        tsDTO.setLogisticCompanyId("90000");
        tsDTO.setMailNo("140630000332001");
        tsDTO.setName("11");
        tsDTO.setRemark("分拣中心：已入库，操作人：数据组分拣，联系电话：13000000000");
        tsDTO.setTxLogisticID(CommonTestConst.targetOrderSubNo);
        return tsDTO;
    }
    
    
    public WmsReceiveCostPriceDTO buildWmsCostDTO() {
        WmsReceiveCostPriceDTO ret = new WmsReceiveCostPriceDTO();
        InventoryPriceDTO ipDTO  = new InventoryPriceDTO();
        ipDTO.setInventoryPrice(new BigDecimal(10));
        ipDTO.setOrderItemNo("11");
        List<InventoryPriceDTO> invList = new ArrayList<InventoryPriceDTO>();
        invList.add(ipDTO);
        ret.setBackup1("11");
        ret.setBackup2("11");
        //ret.setIpDTOs(invList);
        ret.setOperateTime("11");
        ret.setOperator("11");
        ret.setOrderSubNo("11");
        return ret;
    }
    public List<WmsOmsReceiveInspectionResultDTO> buildWmsInspect() {
        List<WmsOmsReceiveInspectionResultDTO> retList = new ArrayList<WmsOmsReceiveInspectionResultDTO>();
        WmsOmsReceiveInspectionResultDTO ret = new WmsOmsReceiveInspectionResultDTO();
        ret.setInspectionOrStorage("11");
        ret.setInspectionResult("11");
        ret.setOperateTime("11");
        ret.setOperator("11");
        ret.setOrderSubNo("11");
        ret.setRemark("11");
        ret.setStorageResult("11");
        retList.add(ret);
        return retList;
    }
    
   /**00,40,50,62,80
    * 00 订单下达仓库（00）
    40 订单分配库存（40）
    50 订单在拣货（50）
    62 订单打包中（62）
    63 订单装箱完成（63）
    "80 订单已发出（80）
    GET_SUCCESS（揽收成功）
    SEND_ORDER（派单）
    KEEP_HOUSE（留仓）"
    * **/

    public List<WmsOmsReceiveLogisticsDTO> buildWmsStatusDTO() {
        List<WmsOmsReceiveLogisticsDTO> listDto = new ArrayList();
        WmsOmsReceiveLogisticsDTO dto = new WmsOmsReceiveLogisticsDTO();
//        WmsOmsReceiveLogisticsDTO dto1 = new WmsOmsReceiveLogisticsDTO();
        dto.setOrderSubNo(CommonTestConst.targetOrderNo+"01");
        dto.setLogisticsStatus("00");
        dto.setLogisticsDesc("订单下达仓库（00）");
        dto.setOperateTime("2014-04-14");
        dto.setOperator("sys");

        listDto.add(dto);
        return listDto;
    }
    
    public MemberPayDTO buildMem03DTO() {
        OrderMain om = orderMainService.findByOrderNo(CommonTestConst.targetOrderNo);
        MemberPayDTO dto = new MemberPayDTO();
        dto.setMemberId(om.getMemberNo());
        MyCardDTO myCardDTO = new MyCardDTO();
        myCardDTO.setAmount(1d);
        myCardDTO.setOrderSubNo(CommonTestConst.targetOrderSubNo);
        List<MyCardDTO> listDto = new ArrayList<MyCardDTO>();
       listDto.add(myCardDTO);
        dto.setCardList(listDto);
        return dto;
    }
    
    public CouponPayDTO buildPromo01DTO(){
        CouponPayDTO cpDTO =  new CouponPayDTO();
        cpDTO.setBatchNum("11111111");
        String orderNo = CommonTestConst.targetOrderNo;
        OrderMain om = orderMainService.findByOrderNo(orderNo);
        cpDTO.setBatchNum(om.getBatchNum());
        List<CouponDTO> clList = new ArrayList<CouponDTO>();
        cpDTO.setCouponList(clList);
        for (int i=0;i<2;i++) {
            CouponDTO cDTO = new CouponDTO();
            cDTO.setOrderSubNo(CommonTestConst.targetOrderSubNo);
            cDTO.setCouponAmount(new BigDecimal(10));
            cDTO.setCouponNo("1234567"+i);
            clList.add(cDTO);
        }
        return cpDTO;
    }
    
    public TmsOrderDTO buildTmsOrder() {
        Date date = new Date();
        TmsOrderDTO tDTO = new TmsOrderDTO();
        SimpleDateFormat sdf = commonUtilService.format24Hours();
        tDTO.setCreateTime(sdf.format(date));
        tDTO.setAddress("11");
        tDTO.setAddresscode("4511360");
        tDTO.setArea("11");
        tDTO.setCity("11");
        tDTO.setDeliverymode(1);
        tDTO.setFlag(11);
        tDTO.setGoodsValue(1.1d);
        tDTO.setInsuranceValue(1.1d);
        tDTO.setItemsValue(1.1d);
        tDTO.setLogisticCompanyId("11");
        tDTO.setMobile("11");
        tDTO.setName("11");
        tDTO.setNeedInvoice(11);
        tDTO.setOrderid("11");
        tDTO.setPayMode("11");
        tDTO.setPhone("11");
        tDTO.setPostCode("11");
        tDTO.setProv("11");
        tDTO.setRemark("11");
        tDTO.setToid("11");
        tDTO.setTotalPCS(11);
        tDTO.setTotalWeight(1.1d);
        tDTO.setTxLogisticID("11");
        tDTO.setType("os");
        tDTO.setWmsOrderType(11);
        tDTO.setOuthousetime(sdf.format(date));
        tDTO.setReviewtime(sdf.format(date));
        tDTO.setSrcOrderNo("123");
        tDTO.setSelfFetchMerchantId("11");
        tDTO.setSelfFetchPointId("123");
        TmsOrderItemsDTO toi = new TmsOrderItemsDTO();
        tDTO.setItems(toi);
        
        OrderItemTms oit = new OrderItemTms();
        oit.setBarCode("11");
        oit.setItemId("11");
        oit.setItemName("11");
        oit.setItemValue("11");
        oit.setNumber("11");
        oit.setSkuId("SKU-00");
        oit.setSpecial("11");
        OrderItemTms oit2 = new OrderItemTms();
        oit2.setBarCode("22");
        oit2.setItemId("22");
        oit2.setItemName("22");
        oit2.setItemValue("22");
        oit2.setNumber("22");
        oit2.setSkuId("SKU-00");
        oit2.setSpecial("22");

        ArrayList<OrderItemTms> oitList = new ArrayList<OrderItemTms>();
        oitList.add(oit);
        oitList.add(oit2);
        toi.setItem(oitList);
        return tDTO;
    }
    
    public List<String> buildCombineNoStr(){
        ArrayList<String> ret = new ArrayList<String>();
        ret.add(combineTestNo1);
        ret.add(combineTestNo2);
        return ret;
    }

    public TransportCompParam buildLogisticsSelection(){
        String storageId = "1";
        String areaId = "120629";
        String catagoryId = "992";
        String deliverTypeCode = "2";//"self_take";
        String paymentModeId = "2";
        
        TransportCompParam tcParam = new TransportCompParam();
        tcParam.setWeight("12");
        tcParam.setOrderHour("13");
        tcParam.setStorageId(storageId);
        tcParam.setAreaId(areaId);
        tcParam.setCatagoryId(catagoryId);
        tcParam.setSelfTakePointId("");
        tcParam.setDeliverTypeCode(deliverTypeCode);//正向 ：前台页面选，天虹配送, 门店自提等等 .逆向 ：上门取货，客户寄回，门店代退
        
        //非货到付款，写在线支付id
        tcParam.setPaymentModeId(paymentModeId);
        return tcParam;
    }

    public StockUnLockByOms buildInventoryUnLock() {
        return imsOmsTransServiceImpl.queryInventoryUnLock(CommonTestConst.targetOrderNo);
    }

    public OMSStatusUpdateDTO buildOmsStatusUpdate() {
        OMSStatusUpdateDTO ret = new OMSStatusUpdateDTO();
        ret.setNewStatus("0180");
        ret.setOrderNo("1400000000123");
        ret.setOrderSubNo("140000000012301");
        ret.setStatusType("01");
        ret.setUpdateTime(new Date());
        return ret;
    }
    
}

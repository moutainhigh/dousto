package com.ibm.oms.service.business.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.dao.constant.CancelOrderConst;
import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.constant.IntfReceiveConst;
import com.ibm.oms.dao.constant.IntfSentConst;
import com.ibm.oms.dao.constant.PayMode;
import com.ibm.oms.domain.persist.IntfReceived;
import com.ibm.oms.domain.persist.IntfSent;
import com.ibm.oms.domain.persist.OrderCombineRelation;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderItem_;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderMain_;
import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.oms.domain.persist.OrderPay_;
import com.ibm.oms.domain.persist.OrderRetChgItem;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.domain.persist.OrderSub_;
import com.ibm.oms.domain.persist.OrdiErrOptLog;
import com.ibm.oms.domain.persist.TransportArea;
import com.ibm.oms.intf.constant.OrderMainConst;
import com.ibm.oms.intf.constant.SaleAfterOrderErrorConst;
import com.ibm.oms.intf.intf.BtcOmsReturnChangeDTO;
import com.ibm.oms.intf.intf.CommodityStockInfoDTO;
import com.ibm.oms.intf.intf.CommodityStockInfoOutPutDTO;
import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.intf.intf.OmsMemberRefundDTO;
import com.ibm.oms.intf.intf.OmsMemberRefundOutputDTO;
import com.ibm.oms.intf.intf.OmsMemberVipCardOutputDTO;
import com.ibm.oms.intf.intf.ResultDTO;
import com.ibm.oms.intf.intf.TmsCancelOrderDTO;
import com.ibm.oms.intf.intf.TmsOrderDTO;
import com.ibm.oms.intf.intf.inner.ChangeOrderReciveInfoDTO;
import com.ibm.oms.intf.intf.inner.ExchangeOrderItemDTO;
import com.ibm.oms.intf.intf.inner.OmsMemberRefundInnerDTO;
import com.ibm.oms.intf.intf.inner.OrderItemTms;
import com.ibm.oms.intf.intf.inner.RcOrderItemDTO;
import com.ibm.oms.intf.intf.inner.RcOrderSubDTO;
import com.ibm.oms.intf.intf.inner.TmsOrderItemsDTO;
import com.ibm.oms.service.IntfReceivedService;
import com.ibm.oms.service.IntfSentService;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderPayModeService;
import com.ibm.oms.service.OrderPayService;
import com.ibm.oms.service.OrderRetAddService;
import com.ibm.oms.service.OrderRetChangeService;
import com.ibm.oms.service.OrderRetChgItemService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.oms.service.OrdiErrOptLogService;
import com.ibm.oms.service.TransportAreaCacheService;
import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.OrderStatusService;
import com.ibm.oms.service.business.ReturnChangeOrderService;
import com.ibm.oms.service.business.trans.SaleAfterOrderTransService;
import com.ibm.oms.service.err.ErrConst;
import com.ibm.oms.service.intf.ContextBtcOmsRetChgDTO;
import com.ibm.oms.service.mq.TmsOrderInfoSender;
import com.ibm.oms.service.util.CommonCacheUtil;
import com.ibm.oms.service.util.CommonConstService;
import com.ibm.oms.service.util.CommonUtilService;
import com.ibm.oms.service.util.DataUtil;
import com.ibm.oms.service.util.PaymentMethodUtil;
import com.ibm.oms.service.util.XMLConverter;
import com.ibm.sc.model.payment.PaymentMethod;
import com.ibm.sc.util.DateUtil;

import datahub.ws.data.CERPWSService2ServiceSoapBindingStub;
import datahub.ws.data.WmsASNDetails;
import datahub.ws.data.WmsASNHeader;
import datahub.ws.data.WmsASNInfo;
import datahub.ws.data.WmsParamInfo;
import datahub.ws.data.WmsResultInfo;
import datahub.ws.data.WmsSecurityInfo;

/**
 * @author xiaohl
 * 
 */
// @Service("returnChangeOrderService")
public class ReturnChangeOrderServiceImpl implements ReturnChangeOrderService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    // 会员
    private String mem01;
    private String mem02;
    private String memVipCard;
    // WMS
    private String wms04;
    // 库存锁定
    private String sim01;
    // 库存释放
    private String sim02;
    // 库存扣减
    private String sim03;
    // 库存查询
    private String sim06;
    @Autowired
    CommonUtilService commonUtilService;
    @Autowired
    OrderMainService orderMainService;
    @Autowired
    OrderSubService orderSubService;
    @Autowired
    OrderPayService orderPayService;
    @Autowired
    OrderRetChgItemService orderRetChgItemService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    OrderRetAddService orderRetAddService;
    @Autowired
    OrderNoService orderNoService;
    @Autowired
    IntfReceivedService intfReceivedService;
    @Autowired
    IntfSentService IntfSentService;
    @Autowired
    OrderRetChangeService orderRetChangeService;
    @Autowired
    SaleAfterOrderServiceImpl saleAfterOrderService;
    @Autowired
    SaleAfterOrderTransService saleAfterOrderTransService;
    @Autowired
    OrderStatusService orderStatusService;
    @Autowired
    OrdiErrOptLogService ordiErrOptLogService;
    @Autowired
    XMLConverter xmlConverterOrder;
    @Autowired
    XMLConverter xmlConverterStatus;
    @Autowired
    TmsOrderInfoSender tmsRetChgOrderInfoSender;
    @Autowired
    TmsOrderInfoSender tmsRetChgOrderCancelInfoSender;
    @Autowired
    TransportAreaCacheService transportAreaCacheService;
    @Autowired
    OrderCreateService orderCreateService;
    @Autowired
    OrderPayModeService orderPayModeService;
    @Autowired
    CommonCacheUtil commonCacheUtil;
    @Autowired
    PaymentMethodUtil paymentMethodUtil;

    /**
     * 获取商品库存信息
     * @param skuCode sku编码
     * @return
     */
    public CommodityStockInfoOutPutDTO getStockInfo(String skuCode)
    {
    	CommodityStockInfoOutPutDTO output = commonUtilService.jsonGetWithTrack(String.format(sim06, skuCode), IntfSentConst.OMS_SIM_SEARCH.getCode(), CommodityStockInfoOutPutDTO.class, null);
    	return output;
    }
    
    /**
     * 功能描述：获取sku信息
     * @param skuCode
     * @return
     */
    public CommodityStockInfoDTO getSkuInfo(String skuCode){
        CommodityStockInfoDTO result = null;
        CommodityStockInfoOutPutDTO output = getStockInfo(skuCode);
        if(null==output) return null;
        for(CommodityStockInfoDTO stockInfo:output.getCommodityStockInfo()){
            if(stockInfo.getSkuCode().equals(skuCode)){
                result = stockInfo;
                break;
            }
        }
        return result;
    }
    
    /**
     * 功能描述: 创建退货订单
     */
    public CommonOutputDTO writeReturnOrder(BtcOmsReturnChangeDTO retChgOrderDTO) {
        // 记录接口接收数据
        IntfReceived rec = commonUtilService.saveIntfReceivedJson(retChgOrderDTO.getOrderRelatedOrigin(), null, IntfReceiveConst.BTC_OMS_RETURN_ORDER.getCode(),
                retChgOrderDTO, null);
        CommonOutputDTO output = new CommonOutputDTO();
        // 校验数据
        String msg = CommonUtilService.createOrderValidate(retChgOrderDTO);
        //校验失败
        if (!CommonConstService.OK.equals(msg)) {
            rec.setSucceed(-1l);
            intfReceivedService.update(rec);
            output.setCode(CommonConstService.PROCESS_FAILED);
            output.setMessage(msg);
            return output;
        }
        // 校验成功
        output.setCode(CommonConstService.PROCESS_SUCCEED);
        // 原销售订单
        OrderMain srcOrderMain = this.orderMainService.findByOrderNo(retChgOrderDTO.getOrderRelatedOrigin());
        if (null == srcOrderMain) {
            output.setMsg("OMS原销售订单为空！");
            output.setCode(CommonConstService.FAILED);
            rec.setSucceed(-1l);
            return output;
        }
        ContextBtcOmsRetChgDTO contextDTO = new ContextBtcOmsRetChgDTO();// 上下文环境
        contextDTO.setOmsSrcOrderMain(srcOrderMain);
        try {
              // 对订单进行复制操作
	          OrderMain orderMain = new OrderMain();
	          orderMain.setRefOrderNo(srcOrderMain.getOrderNo());
	          orderMain.setRefOrderId(srcOrderMain.getId());
	          this.createOrderMain(retChgOrderDTO, orderMain);
	          orderMain.setIfNeedRefund(CommonConst.OrderMain_IfNeedRefund_Yes.getCodeLong());//设置为需退款(网天退款)
	          
	          //构建其他信息：计算金额、重量...
	          OrderSub orderSub = new OrderSub();
	          this.setReturnAmountAndWeight(orderSub,retChgOrderDTO,orderMain);
	          //设置退款金额
	          List<OrderPay> orderPays = new ArrayList<OrderPay>();
              OrderPay orderpay = new OrderPay();
              orderpay.setPayCode(retChgOrderDTO.getPayCode());
              orderpay.setPayAmount(orderMain.getTotalPayAmount());//此金额通过setReturnAmountAndWeight()计算获取
              orderPays.add(orderpay);
              ResultDTO resultDTO = saleAfterOrderService.createOrderRetChange(retChgOrderDTO.getOrderCategory(),
                      retChgOrderDTO.getOrderSource(), orderMain, orderSub, orderPays);
              if(resultDTO.getResult()!=CommonConst.Common_Succeed_SUCCESS.getCodeInt()){
                  Object obj = resultDTO.getResultObj();
                  if(obj instanceof SaleAfterOrderErrorConst){
                      SaleAfterOrderErrorConst errorConst = (SaleAfterOrderErrorConst)obj;
                      output.setCode(errorConst.getCode());
                      msg = errorConst.getDesc();
                  }
              }
              output.setOrderNo(resultDTO.getOrderNo());
              output.setOrderSubNo(resultDTO.getOrderSubNo());
        } catch (Exception e) {
            logger.error("{}", e);
            output.setMsg("系统错误！");
            output.setCode(CommonConstService.PROCESS_FAILED);
            rec.setSucceed(-1l);
            return output;
        }

        rec.setSucceed(1l);
        intfReceivedService.update(rec);
        output.setMsg(msg);
        return output;
    }

    /**
     * 功能描述: 创建换货订单
     */
    public CommonOutputDTO writeChangeOrder(BtcOmsReturnChangeDTO retChgOrderDTO) {
        // 记录接口接收数据
        IntfReceived rec = commonUtilService.saveIntfReceivedJson(retChgOrderDTO.getOrderRelatedOrigin(), null, IntfReceiveConst.BTC_OMS_CHANGE_ORDER.getCode(),
                retChgOrderDTO, null);
        CommonOutputDTO output = new CommonOutputDTO();
        // 校验数据
        String msg = CommonUtilService.createOrderValidate(retChgOrderDTO);
        //校验失败
        if (!CommonConstService.OK.equals(msg)) {
            rec.setSucceed(-1l);
            intfReceivedService.update(rec);
            output.setCode(CommonConstService.PROCESS_FAILED);
            output.setMessage(msg);
            return output;
        }
        // 校验成功
        output.setCode(CommonConstService.PROCESS_SUCCEED);
        // 原销售订单
        OrderMain srcOrderMain = this.orderMainService.findByOrderNo(retChgOrderDTO.getOrderRelatedOrigin());
        if (null == srcOrderMain) {
            rec.setSucceed(-1l);
            intfReceivedService.update(rec);
            output.setMsg("OMS原销售订单为空！");
            output.setCode(CommonConstService.PROCESS_FAILED);
            return output;
        }
        ContextBtcOmsRetChgDTO contextDTO = new ContextBtcOmsRetChgDTO();// 上下文环境
        contextDTO.setOmsSrcOrderMain(srcOrderMain);
        try {
          //对订单进行复制操作
          OrderMain orderMain = new OrderMain();
          orderMain.setRefOrderNo(srcOrderMain.getOrderNo());
          orderMain.setRefOrderId(srcOrderMain.getId());
          this.createOrderMain(retChgOrderDTO, orderMain);
          List<OrderPay> orderPays = new ArrayList<OrderPay>();
          orderPays.add(new OrderPay());//换货无需退款，直接设置空的对象即可
          //构建其他信息：子订单、计算金额、重量...
          OrderSub orderSub = new OrderSub();
          this.setReturnAmountAndWeight(orderSub,retChgOrderDTO,orderMain);
          ResultDTO resultDTO = saleAfterOrderService.createOrderRetChange(retChgOrderDTO.getOrderCategory(),
               retChgOrderDTO.getOrderSource(), orderMain, orderSub, orderPays);
   	       if(resultDTO.getResult()!=CommonConst.Common_Succeed_SUCCESS.getCodeInt()){
               Object obj = resultDTO.getResultObj();
               if(obj instanceof SaleAfterOrderErrorConst){
                   SaleAfterOrderErrorConst errorConst = (SaleAfterOrderErrorConst)obj;
                   output.setCode(errorConst.getCode());
                   msg = errorConst.getDesc();
               }
           }
       	    output.setOrderNo(resultDTO.getOrderNo());
            output.setOrderSubNo(resultDTO.getOrderSubNo());
		} catch (Exception e) {
			 output.setMsg("系统错误！");
             output.setCode(CommonConstService.PROCESS_FAILED);
             rec.setSucceed(-1l);
             return output;
		}

        rec.setSucceed(1l);
        intfReceivedService.save(rec);
        output.setMsg(msg);
        return output;
    }
    
    //构建主订单
    private void createOrderMain(BtcOmsReturnChangeDTO retChgOrderDTO,OrderMain orderMain){
        orderMain.setOrderRelatedOrigin(retChgOrderDTO.getOrderRelatedOrigin());
        orderMain.setOrderCategory(retChgOrderDTO.getOrderCategory());
        orderMain.setOrderSource(retChgOrderDTO.getOrderSource());
        orderMain.setTransportFee(new BigDecimal(0));
        orderMain.setRemark(retChgOrderDTO.getRemark());
   }
    
    //构建意向单对象信息:子订单、计算金额、重量...
    private void setReturnAmountAndWeight(OrderSub orderSub,BtcOmsReturnChangeDTO retChgOrderDTO,OrderMain ordermain){
        RcOrderSubDTO orderSubDTO=retChgOrderDTO.getOrderSub();//子订单DTO
        ChangeOrderReciveInfoDTO changeReciveDTO = retChgOrderDTO.getReciveinfo();//换货：收货人信息
        String srcOrderSubNo = orderSubDTO.getOrderSubRelatedOrigin();// OMS原子订单号
    	List<OrderItem> srcOrderItems = this.orderItemService.getByOrdeSubNo(srcOrderSubNo);// 获取OMS原子订单明细
    	List<RcOrderItemDTO> itemsDTO = orderSubDTO.getOrderItems();  
        BigDecimal returnAmountTotal = new BigDecimal(0); // 退款总金额
        BigDecimal weightTotal = new BigDecimal(0); // 商品总重
        BigDecimal productPriceTotal = new BigDecimal(0); // 商品总价,折前
        List<OrderRetChgItem> orderRetChgItems = new ArrayList<OrderRetChgItem>();
        for (OrderItem srcItem : srcOrderItems) {
        	boolean flag = false;// 匹配成功标识
        	 OrderRetChgItem orderRetChgItem = null;
        	for (RcOrderItemDTO itemDTO : itemsDTO) {
        		String rcItemNo = itemDTO.getRefOrderItemNo();
        		String rcSkuNo = itemDTO.getSkuNo();
        		//找到原销售订单明细
        		if (srcItem.getOrderItemNo().equalsIgnoreCase(rcItemNo)||srcItem.getSkuNo().equals(rcSkuNo)) { 
        			 flag = true;
        			 orderRetChgItem = new OrderRetChgItem();//换货入库单
        			 //原订单明细的信息
        			 orderRetChgItem.setRefOrderItemId(srcItem.getId());
        			 orderRetChgItem.setUnitPrice(srcItem.getUnitPrice());
        			 
        			 // 原销售数量
        			 int srcSalNum = srcItem.getSaleNum().intValue();
        			 // 逆向单单品优惠金额
        			 BigDecimal retChgUnitDiscount = new BigDecimal(0);
        			 // 逆向单单品劵使用金额
        			 BigDecimal retChgCouponAmount = new BigDecimal(0);
        			 if(0 != srcSalNum)
        			 {
        			     BigDecimal srcSaleTotalMoney = DataUtil.convertBigDecimal(srcItem.getSaleTotalMoney());
        			     BigDecimal srcPayAmount = DataUtil.convertBigDecimal(srcItem.getPayAmount());
        			     BigDecimal srcCouponAmount = DataUtil.convertBigDecimal(srcItem.getCouponAmount());
        			     // 逆向单单品优惠金额= (原销售总金额 - 原实付总金额)/原销售数量
        			     retChgUnitDiscount = srcSaleTotalMoney.subtract(srcPayAmount).divide(new BigDecimal(srcSalNum), 2, RoundingMode.HALF_UP).setScale(2, RoundingMode.FLOOR);
        			     // 逆向单单品劵使用金额=原销售总劵使用金额/原销售数量
        			     retChgCouponAmount = srcCouponAmount.divide(new BigDecimal(srcSalNum), 2, RoundingMode.HALF_UP).setScale(2, RoundingMode.FLOOR);
        			 }
        			 else
        			 {
        			     this.logger.error("原销售数量salNum为0");
        			 }
        			 orderRetChgItem.setUnitDiscount(retChgUnitDiscount);
        			 orderRetChgItem.setCouponAmount(retChgCouponAmount);
        			 //B2C传过来的信息
      	        	 //orderRetChgItem.setRefOrderItemNo(itemDTO.getRefOrderItemNo());
        			 orderRetChgItem.setRefOrderItemNo(srcItem.getOrderItemNo());
      	  	         orderRetChgItem.setSaleNum(NumberUtils.toLong(itemDTO.getSaleNum()));
      	  	         orderRetChgItem.setReason(itemDTO.getReason());
      	  	         //计算金额、重量(因为商品A购买2个，可能只退一个)
      	  	         BigDecimal number = new BigDecimal(itemDTO.getSaleNum());
      	  	         
      	  	         //判断是否单种商品所有数量都退、换，则无需单独计算
      	  	         /*if(srcItem.getSaleNum().intValue()==number.intValue()){
          	  	         orderRetChgItem.setPayAmount(srcItem.getPayAmount());
                         orderRetChgItem.setWeight(srcItem.getWeight());
                         productPriceTotal = productPriceTotal.add(srcItem.getSaleTotalMoney());
      	  	         }else{*/
      	  	             //实际支付金额(折前单价-折扣单价-购物券单价)
      	  	             //BigDecimal realUnitPrice = srcItem.getUnitPrice().subtract(srcItem.getUnitDiscount()).subtract(srcItem.getCouponAmount());
      	  	             BigDecimal realUnitPrice = srcItem.getUnitPrice().subtract(retChgUnitDiscount).subtract(retChgCouponAmount);
      	  	             BigDecimal realUnitWeight =  srcItem.getWeight().divide(new BigDecimal(srcItem.getSaleNum()), 2, RoundingMode.HALF_UP).setScale(2, RoundingMode.FLOOR);
          	  	         orderRetChgItem.setPayAmount(realUnitPrice.multiply(number));
                         orderRetChgItem.setWeight(realUnitWeight.multiply(number));
                         //折后价
                         orderRetChgItem.setUnitDeductedPrice(realUnitPrice);
                         //计算商品总价
                         productPriceTotal = productPriceTotal.add(realUnitPrice.multiply(number));
      	  	         //}
      	  	         //如果是色码款的换货，则需获取换货的信息
                     String semakuanFlag = itemDTO.getProductPropertyFlag();
                     if(StringUtils.isNotBlank(semakuanFlag)){
                         orderRetChgItem.setProductPropertyFlag(Long.valueOf(semakuanFlag));        
                     }
      	  	         if(OrderMainConst.OrderMain_OrderCategory_Change.getCode().equals(ordermain.getOrderCategory())){
      	  	             if(CommonConst.CommonBooleanTrue.getCode().equals(semakuanFlag)){
      	  	                 ExchangeOrderItemDTO exchDTO = itemDTO.getExchangeOrderItemDTO();//
      	  	                 orderRetChgItem.setChg_barCode(exchDTO.getBarCode());
      	  	                 orderRetChgItem.setChg_commodityCode(exchDTO.getCommodityCode());
      	  	                 orderRetChgItem.setChg_commodityName(exchDTO.getCommodityName());
      	  	                 orderRetChgItem.setChg_skuNo(exchDTO.getSkuNo());
      	  	                 orderRetChgItem.setAdsPage(exchDTO.getCommodityName());// 换货单中显示出库单的色码信息
      	  	                 
      	  	             }
      	  	         }
      	  	         
      	        	 orderRetChgItems.add(orderRetChgItem);
        			 break;
        		}
            }
            if (flag) { //计算总金额、总重量、商品总价
                 if (srcItem.getWeight() != null) {
                     weightTotal = weightTotal.add(orderRetChgItem.getWeight());
                 }
                 if (srcItem.getPayAmount() != null) {
                     returnAmountTotal = returnAmountTotal.add(orderRetChgItem.getPayAmount());
                 }
             }
        }
        
        orderSub.setOrderRetChgItems(orderRetChgItems);
        orderSub.setOrderSubRelatedOrigin(orderSubDTO.getOrderSubRelatedOrigin());
        orderSub.setDistributeType(orderSubDTO.getDistributeType());
        orderSub.setStoreNo(orderSubDTO.getStoreNo());
        orderSub.setLogisticsOutNo(orderSubDTO.getLogisticsOutNo());//快递单号
        orderSub.setUserName(orderSubDTO.getUserName());
        orderSub.setMobPhoneNum(orderSubDTO.getMobPhoneNum());
        orderSub.setAddressDetail(orderSubDTO.getAddressDetail());
        orderSub.setInvoicePrinted(NumberUtils.toLong(orderSubDTO.getInvoicePrinted()));
        orderSub.setAddressCode(orderSubDTO.getAddressCode());
        //收货人信息
        if(null!=changeReciveDTO){
            orderSub.setChgOutUserName(changeReciveDTO.getUserName());
            orderSub.setAddressCode(changeReciveDTO.getAddressCode());
            orderSub.setAddressDetail(changeReciveDTO.getAddressDetail());
            orderSub.setMobPhoneNum(changeReciveDTO.getMobPhoneNum());
            orderSub.setPhoneNum(changeReciveDTO.getPhoneNum());
        }
        
        ordermain.setWeight(weightTotal);
        ordermain.setTotalPayAmount(returnAmountTotal);
        ordermain.setTotalProductPrice(productPriceTotal);
    }

    /**
     * btc调用接口取消订单
     */
    @Override
    public CommonOutputDTO writeCancelOrder(String orderSubNo, String operator) {
        // 记录接口接收数据
        IntfReceived rec = commonUtilService.saveIntfReceivedJson(null, orderSubNo, IntfReceiveConst.BTC_OMS_CANCEL_ORDER.getCode(), orderSubNo, null);
        CommonOutputDTO output = new CommonOutputDTO();
        OrderSub orderSub = this.orderSubService.getByField(OrderSub_.orderSubNo, orderSubNo);// 原销售子订单
        String orderNo = orderNoService.getOrderNoBySubNo(orderSubNo);
        OrderMain orderMain = orderMainService.getByField(OrderMain_.orderNo, orderNo);// 原销售主订单
        if (null == orderSub) {
            output.setMsg("OMS原销售子订单为空！");
            output.setCode(CommonConstService.FAILED);
            rec.setMsg("OMS原销售子订单为空！");
            rec.setSucceed(-1l);
            intfReceivedService.update(rec);
            return output;
        }
        if(OrderMainConst.OrderMain_OrderCategory_ChangeOut.getCode().equals(orderMain.getOrderCategory())){
            output.setMsg("B2C取消订单：换货出库单不能取消！");
            output.setCode(CommonConstService.FAILED);
            rec.setMsg("换货出库单不能取消！");
            rec.setSucceed(-1l);
            intfReceivedService.update(rec);
            return output;
        }
        try {
            ResultDTO resultDTO = saleAfterOrderService.cancelOrder(orderSubNo, CancelOrderConst.CancelOrder_Scene_Customer);
            if(resultDTO.getResult()!=CommonConst.Common_Succeed_SUCCESS.getCodeInt()){
                output.setCode(CommonConstService.PROCESS_FAILED); //取消订单失败
                output.setMessage(resultDTO.getResultMessage());
                rec.setMsg("B2C取消订单失败:"+resultDTO.getResultMessage());
                rec.setSucceed(-1l);
                intfReceivedService.update(rec);
                return output;
            }
            output.setCode(CommonConstService.PROCESS_SUCCEED);
            rec.setSucceed(1l);
            intfReceivedService.update(rec);
        } catch (Exception e) {
            logger.info("B2C取消订单接口异常,{}",e);
            output.setMessage("B2C取消订单接口异常:"+e.getMessage());
            output.setCode(CommonConstService.FAILED);
            //接口接收结果
            rec.setMsg("B2C取消订单接口异常:"+e.getMessage());
            rec.setSucceed(-1l);
            intfReceivedService.update(rec);
        }
        /*// 六、调用库存中台：释放商品库存【saveCancelOrder中实现】
        if (cancelScene.equals(CancelOrderConst.CancelOrder_Scene_VALIDATED.getCode())) { // 未出库取消
            // 取消出库接口
        	 if(!orderCreateService.inventoryCancel(orderMain)){
                 output.setMsg("取消出库失败！");
                 output.setCode(CommonConstService.FAILED);
                 rec.setSucceed(-1l);
                 return output;
        	 }
        } else {
            // 解锁接口
            List<OrderMain> orderMainList = new ArrayList<OrderMain>();
            orderMainList.add(orderMain);
            orderCreateService.inventoryUnLock(orderMainList);
        }*/
        
        
        return output;
    }
    
    /**
     * 加回MY卡（取消订单、退款单）
     */
    public ResultDTO returnMyCard(String orderNo,String operator,OrderMain orderMain,boolean isAddErrLog){
        ResultDTO resultDTO = new ResultDTO();
		List<OrderPay> opList = orderPayService.findByField(OrderPay_.orderNo, orderNo);
		if (opList == null || opList.isEmpty()) {
		     return resultDTO;
		}
		  //调用会员帐户加回my卡支付部分
		for (OrderPay op : opList) {
		    if(PayMode.CARD.getCode().equals(op.getPayCode())){
		        OmsMemberRefundDTO omDTO = new OmsMemberRefundDTO();
		        ArrayList<OmsMemberRefundInnerDTO> memList = new ArrayList<OmsMemberRefundInnerDTO>();
		        omDTO.setRefundDTOList(memList);
		        OmsMemberRefundInnerDTO inner = new OmsMemberRefundInnerDTO();
		        memList.add(inner);
		        inner.setAdd(true);
		        inner.setAccountType(CommonConstService.MEMCARD);
		        inner.setAmount(op.getPayAmount());
		        omDTO.setMemberId(NumberUtils.toLong(orderMain.getMemberNo()));
		        omDTO.setOperator(operator);
		        omDTO.setOrderNo(orderMain.getOrderNo());
		        OmsMemberRefundOutputDTO refundDTO = commonUtilService.jsonPostWithTrack(mem02, IntfSentConst.OMS_MEM_02.getCode(), omDTO, OmsMemberRefundOutputDTO.class, null);
		        if(refundDTO.getCode().equals(CommonConstService.FAILED)){
		            logger.info("加回MY卡失败："+refundDTO.getMsg());
		            resultDTO.setErrorMessage(refundDTO.getMsg());
		            resultDTO.setResultObj(refundDTO.getLeftAmount());//可操作金额
		            if(isAddErrLog){
		                //进入异常日志表，需要后续异常处理
		                OrdiErrOptLog errLog = new OrdiErrOptLog();
		                errLog.setOrderNo(orderMain.getOrderNo());
		                errLog.setOrderSource(orderMain.getOrderSource());
		                errLog.setAliasOrderNo(orderMain.getAliasOrderNo());
		                errLog.setOrderSubNo(this.orderNoService.getDefaultOrderSubNoByOrderNo(orderNo));
		                errLog.setResultDesc(refundDTO.getMsg());
		                errLog.setOperateType(ErrConst.CancelOrder_ReturnMyCard.getCode());
		                errLog.setOperateTime(new Date());
		                ordiErrOptLogService.save(errLog);
		            }
		        }
		        logger.info("加回MY卡成功，单号："+orderMain.getOrderNo());
		        break;
		    }
		}
		return resultDTO;
    }
    
    /**
     * 操作账户积分（审核需退款的意向单需扣减积分）
     * @param operator 操作者
     * @param orderNo 订单号
     * @param isAddFlag  true:添加，false：扣减
     * @param isAddErrLog 异常时是否添加至异常记录表
     * @return
     */
    public ResultDTO handelIntegral(String operator,String orderNo,boolean isAddFlag,boolean isAddErrLog){
        ResultDTO resultDTO = new ResultDTO();
        OrderMain orderMain = this.orderMainService.getByField(OrderMain_.orderNo, orderNo);
        if (orderMain == null) {
             return resultDTO;
        }
        BigDecimal totalPoint = orderMain.getTotalGivePoints();
        if(totalPoint.compareTo(new BigDecimal(0))==0){
            return resultDTO;
        }
        //调用会员帐户扣减、加回积分
        OmsMemberRefundDTO omDTO = new OmsMemberRefundDTO();
        ArrayList<OmsMemberRefundInnerDTO> memList = new ArrayList<OmsMemberRefundInnerDTO>();
        omDTO.setRefundDTOList(memList);
        OmsMemberRefundInnerDTO inner = new OmsMemberRefundInnerDTO();
        memList.add(inner);
        inner.setAdd(isAddFlag);//操作类型
        inner.setAccountType(CommonConstService.MEMPOINT);//账户类型
        inner.setAmount(totalPoint);
        omDTO.setMemberId(NumberUtils.toLong(orderMain.getMemberNo()));
        omDTO.setOperator(operator);
        omDTO.setOrderNo(orderMain.getOrderNo());
        OmsMemberRefundOutputDTO refundDTO = commonUtilService.jsonPostWithTrack(mem02, IntfSentConst.OMS_MEM_02.getCode(), omDTO, OmsMemberRefundOutputDTO.class, null);
        if(refundDTO.getCode().equals(CommonConstService.FAILED)){
            resultDTO.setErrorMessage(refundDTO.getMsg());
            resultDTO.setResultObj(refundDTO.getLeftAmount());//可退积分
            if(isAddErrLog){
              //进入异常日志表，需要后续异常处理
                OrdiErrOptLog errLog = new OrdiErrOptLog();
                errLog.setOrderNo(orderMain.getOrderNo());
                errLog.setOrderSource(orderMain.getOrderSource());
                errLog.setAliasOrderNo(orderMain.getAliasOrderNo());
                errLog.setOrderSubNo(this.orderNoService.getDefaultOrderSubNoByOrderNo(orderNo));
                errLog.setResultDesc(refundDTO.getMsg());
                errLog.setOperateType(ErrConst.SaleAfterOrder_Cancel_ReturnIntegral.getCode());
                errLog.setOperateTime(new Date());
                ordiErrOptLogService.save(errLog);
            }
        }
        return resultDTO;
    }

    /**
     * VIP账户查询
     */
    public OmsMemberVipCardOutputDTO searchMemberVipCard(String memberNo){
        OmsMemberVipCardOutputDTO vipCardDTO = null;
        if(StringUtils.isEmpty(memberNo)){
            return vipCardDTO;
        }
        vipCardDTO = commonUtilService.jsonGetWithTrack(String.format(memVipCard, memberNo), IntfSentConst.OMS_MEM_VIPCARD.getCode(), OmsMemberVipCardOutputDTO.class, null);
        return vipCardDTO;
    }
    
    
    
    /**
     * 退、换、拒收订单传输富勒（WMS）
     * 
     * @param orderSubNoR
     */
    public boolean sendOmsToWmsSaleAfterOrder(String orderSubNoR) {
        boolean flag = true;
        OrderMain orderMainR = null;
        try {
            java.net.URL url = new java.net.URL(this.wms04);
            org.apache.axis.client.Service service = new org.apache.axis.client.Service();
            CERPWSService2ServiceSoapBindingStub wmsService = new CERPWSService2ServiceSoapBindingStub(url, service);
            WmsSecurityInfo sec = new WmsSecurityInfo();
            sec.setUsername(ReturnChangeOrderService.WMS_WmsSecurityInfo_UserName);
            sec.setPassword(ReturnChangeOrderService.WMS_WmsSecurityInfo_Password);
            // 获取原订单信息
            if (StringUtils.isEmpty(orderSubNoR)) {
                return false;
            }
            String orderNoR = this.orderNoService.getOrderNoBySubNo(orderSubNoR);
            orderMainR = this.orderMainService.findByOrderNo(orderNoR);
            if (orderMainR == null) {
                return false;
            }else if(CommonConst.OrderMain_MerchantType_InvoiceOrg.getCode().equals(orderMainR.getMerchantType())){ 
                return false; //云店的逆向单不用传输富勒
            }
            OrderSub orderSubR = orderMainR.getOrderSubs().get(0);

            // 构建入库信息
            WmsASNInfo ansInfo = new WmsASNInfo();
            // 构建单头
            WmsASNHeader[] asnHeaderArr = new WmsASNHeader[1];
            WmsASNHeader header = new WmsASNHeader();// 单头(头信息)
            String orderMainId = String.valueOf(orderMainR.getId());
            String orderSubId = String.valueOf(orderSubR.getId());
            header.setWAREHOUSEID(WMS_WarehouseID);
            header.setASNNO(orderSubNoR);// 子单id（子订单号）
            header.setASNREFERENCE4(orderSubR.getOrderSubNo());//子单号
            String orderCategory = orderMainR.getOrderCategory();
            String asnType = "";
            //RT=换货，RET=退货入库,REJ=拒收
            if (orderCategory.equals(OrderMainConst.OrderMain_OrderCategory_Return.getCode())) {
                asnType = WMS_AsnType_RET;
            } else if (orderCategory.equals(OrderMainConst.OrderMain_OrderCategory_Reject.getCode())) {
                asnType = WMS_AsnType_REJ;// TH=换货入库单
            }else if (orderCategory.equals(OrderMainConst.OrderMain_OrderCategory_Change.getCode())) {
                asnType = WMS_AsnType_RT;// RT=换货入库单
            }
            header.setASNTYPE(asnType);
            header.setCUSTOMERID(ReturnChangeOrderService.WMS_CustomerID);
            header.setMODIFLAG(ReturnChangeOrderService.WMS_ModiFlag);
            if (null != orderMainR.getOrderTime()) {
                String dateTime = DateUtils.formatDate(orderMainR.getOrderTime(), WMS_DateFormate);
                header.setASNCREATIONTIME(dateTime);
                header.setEXPECTEDARRIVETIME1(dateTime);
            }
            header.setASNREFERENCE1(orderSubR.getOrderSubRelatedOrigin());// 凭证号：订单中台的rowId(原销售定子订单no)
            if(asnType.equals(WMS_AsnType_RT) && StringUtils.isNoneEmpty(orderMainR.getChgOurOrderNo())){
                //换货出库单的单号
                header.setASNREFERENCE2(orderNoService.getDefaultOrderSubNoByOrderNo(orderMainR.getChgOurOrderNo()));
            }
            //header.setASNREFERENCE3(orderMainR.getRemark());// 原因描述
            header.setNOTES(orderMainR.getClientServiceRemark());//客服备注
            header.setSUPPLIERID(ReturnChangeOrderService.WMS_SupplierId);// 供应商id
            header.setISSUEPARTYID(orderMainR.getMemberNo());
            header.setISSUEPARTYNAME(orderSubR.getUserName());
            if(OrderMainConst.OrderSub_DistributeType_ReturnStore.getCode().equals(orderSubR.getDistributeType())){
                header.setI_ADDRESS1(orderSubR.getProvideAddress());// 如果是门店代退，则传输门店地址
            }else{
                header.setI_ADDRESS1(orderSubR.getAddressDetail());// 客户地址（暂只获取addressDetail）
            }
            header.setI_ADDRESS2(orderSubR.getAddressCode());//地址编码
            header.setI_ADDRESS3(orderSubR.getMobPhoneNum());//手机号码
            
            header.setH_EDI_01(orderMainR.getTotalProductPrice().toString());// 商品总价格
            String distributeTypeName = commonCacheUtil.getDistributeTypeNameByCode(orderSubR.getDistributeType());//入库物流方式
            if(StringUtils.isNoneBlank(orderSubR.getDeliveryPriority())){
                //门店代退：门店寄回、物流到店取回、换货无需取回原商品
                Map<String, String> disType2Map = commonCacheUtil.getDistributeTypeLevel2Map();
                String disType2Name = disType2Map.get(orderSubR.getDeliveryPriority());
                if(StringUtils.isNoneBlank(disType2Name)){
                    distributeTypeName = distributeTypeName+"("+disType2Name+")";
                }
            }
            header.setH_EDI_02(distributeTypeName);//入库物流方式
            header.setH_EDI_06(String.valueOf(orderMainR.getWeight()));//订单重量
            
            if(null==orderMainR.getTransportFee()){
                header.setH_EDI_09("0");//配送费
            }else{
                header.setH_EDI_09(orderMainR.getTransportFee().toString());//配送费
            }
            header.setH_EDI_10(orderMainR.getTotalPayAmount().toString());//需退款金额
            //获取退款方式
            if(commonUtilService.checkIfNeedRefund(orderMainR.getIfNeedRefund())){
                if(null!=orderMainR.getOrderPays()){
                    String payNames = "";
                    for(OrderPay pay:orderMainR.getOrderPays()){
                        String payName = pay.getPayName();
                        if(StringUtils.isEmpty(payName)){
                            PaymentMethod pMethod = paymentMethodUtil.getRefundMethodMap().get(pay.getPayCode());
                            if(null!=pMethod){
                                payName = pMethod.getName();
                            }
                        }
                        payNames = payNames+payName+",";
                    }
                    if(!"".equals(payNames)){
                        header.setH_EDI_08(payNames.substring(0, payNames.length()-1));    
                    }
                }
            }
            
            asnHeaderArr[0] = header;

            // 构建明细
            WmsASNDetails[] asnDetailArr = new WmsASNDetails[99];
            int count = 0;
            for (OrderRetChgItem item : orderSubR.getOrderRetChgItems()) {
                
                // 判断是否为组合商品
                if (CommonConst.OrderItem_OrderItemClass_Suite.getCode().equals(item.getOrderItemClass())) {
                    /** 组合商品详情（出库一样） start **/
                    // 获取组合的明细
                    List<OrderCombineRelation> combList = item.getOrderCombineRelations();
                    for (OrderCombineRelation combItem : combList) {
                        WmsASNDetails asnDetail = new WmsASNDetails();
                        asnDetail.setCUSTOMERID(ReturnChangeOrderService.WMS_WmsParamInfo_Customerid);
                        asnDetail.setASNNO(orderSubNoR);// 意向单id（orderSubNo）
                        
                        // 明细
                        asnDetail.setASNLINENO(String.valueOf(combItem.getId()));// 明细id
                        asnDetail.setSKU(combItem.getBarCode());
                        asnDetail.setD_EDI_02(combItem.getSkuNo());
                        String saleNum = commonUtilService.Long2Str(combItem.getSaleNum() * item.getSaleNum());
                        asnDetail.setEXPECTEDQTY(saleNum); //组合数量*明细数量
                        asnDetail.setD_EDI_01(combItem.getUnitPrice().toString());// 销售单价
                        BigDecimal payAmount = combItem.getUnitPrice().multiply(new BigDecimal(combItem.getSaleNum()));
                        asnDetail.setTOTALPRICE(payAmount.toString());// 订单行总价：明细应退金额

                        // 父商品信息
                        asnDetail.setUSERDEFINE1(item.getCommodityCode());// 父商品编码
                        asnDetail.setUSERDEFINE2(item.getChg_commodityName());// 父商品名称
                        asnDetail.setUSERDEFINE3(item.getUnitPrice().toString());// 父单价
                        asnDetail.setUSERDEFINE4(item.getSaleNum().toString());// 父商品数量
                        
                        asnDetailArr[count] = asnDetail;
                        count++;
                    }
                    /** 组合商品详情 end **/
                } else {
                    WmsASNDetails asnDetail = new WmsASNDetails();
                    asnDetail.setCUSTOMERID(ReturnChangeOrderService.WMS_WmsParamInfo_Customerid);
                    asnDetail.setASNNO(orderSubNoR);// 意向单id（orderSubNo）
                    
                    asnDetail.setASNLINENO(String.valueOf(item.getId()));// 明细id
                    String skuNo = item.getSkuNo();
                    String barCode = item.getBarCode();
                    if(StringUtils.isEmpty(item.getSkuNo())){
                       //如果逆向明细的sku为null，则获取原销售明细的sku（理论上：在添加逆向订单时应该控制避免此情况） 
                        String refItemNo = item.getRefOrderItemNo();
                        if(StringUtils.isNotEmpty(refItemNo)){
                            OrderItem orderItem = orderItemService.getByField(OrderItem_.orderItemNo, refItemNo);
                            if(null!=orderItem){
                                skuNo = orderItem.getSkuNo();
                            }
                        }
                    }
                    asnDetail.setSKU(barCode);
                    asnDetail.setD_EDI_02(skuNo);
                    asnDetail.setEXPECTEDQTY(item.getSaleNum().toString());
                    String payAmountStr = "";
                    if(null==item.getPayAmount()){
                        BigDecimal payAmount = item.getUnitPrice().multiply(new BigDecimal(item.getSaleNum()));
                        payAmountStr = String.valueOf(payAmount);
                    }else{
                        payAmountStr = item.getPayAmount().toString();
                    }
                    asnDetail.setTOTALPRICE(payAmountStr);// 订单行总价：明细应退金额    
                    
                    asnDetail.setD_EDI_01(item.getUnitPrice().toString());// 销售单价
                    
                    asnDetailArr[count] = asnDetail;
                    count++;
                }
            }
            header.setDetailsItem(asnDetailArr);// 设置明细至头中
            ansInfo.setWmsASNHeaders(asnHeaderArr);// 设置头到接口信息中

            // 构建WmsParamInfo
            WmsParamInfo parmInfo = new WmsParamInfo();
            parmInfo.setCustomerid(ReturnChangeOrderService.WMS_WmsParamInfo_Customerid);
            parmInfo.setMessageid(ReturnChangeOrderService.WMS_WmsParamInfo_Messageid);
            parmInfo.setWarehouseid(ReturnChangeOrderService.WMS_WmsParamInfo_Warehouseid);
            parmInfo.setStdno(ReturnChangeOrderService.WMS_WmsParamInfo_Stdno);

            //记录发送报文
            ObjectMapper mapper = new ObjectMapper();
            String sendMsg = mapper.writeValueAsString(ansInfo);//发送的报文
            IntfSent msg = new IntfSent();
            msg.setIntfCode(IntfSentConst.OMS_WMS_SaleAfterOrder.getCode());
            //msg.setMsg("returnCode:" + resultInfo.getReturnCode() + ";returnDesc:" + resultInfo.getReturnDesc());// 返回参数描述
            msg.setMsg(sendMsg);
            msg.setOrderNo(orderMainR.getOrderNo());
            msg.setOrderSubNo(orderSubNoR);
            msg.setCreateTime(new Date());
            this.IntfSentService.save(msg);
            
            WmsResultInfo resultInfo = wmsService.putASNData(sec, ansInfo, parmInfo, WMS_timeOut);// 调用接口处理

            //记录接口发送结果
            msg.setSucceedFlag(Long.valueOf(resultInfo.getReturnFlag()));
            this.IntfSentService.update(msg);

            // 如果没有传输成功，则需记录进入异常处理订单
            if (!ReturnChangeOrderService.WMS_ReturnFlag_Success.equals(resultInfo.getReturnFlag())) {
                flag = false;
                String mesage="code:"+resultInfo.getReturnCode()+",msg:"+resultInfo.getReturnDesc();
                logger.error("意向单"+orderSubNoR+"传输WMS失败：{}",mesage);
                //记录异常操作记录
                savaSaleAfterOrderErrLog(orderMainR,orderSubNoR,mesage);
            }
            //记录接收报文
            IntfReceived received  =new IntfReceived();
            received.setCreateTime(new Date());
            received.setOrderNo(orderMainR.getOrderNo());
            received.setOrderSubNo(orderSubNoR);
            received.setMsg(mapper.writeValueAsString(resultInfo));//返回结果
            received.setSucceed(1l);
            received.setIntfCode(IntfSentConst.OMS_WMS_SaleAfterOrder.getCode());
            this.intfReceivedService.save(received);
        } catch (Exception ex) {
            flag = false;
            logger.error("{}",ex);
            //记录异常操作记录
            savaSaleAfterOrderErrLog(orderMainR,orderSubNoR,ex.getMessage());
        }
        return flag;
    }
    
    /**
     * 传输WMS失败的意向单记录异常操作表
     * @param orderMainR
     * @param orderSubNoR
     * @param message
     */
    private void savaSaleAfterOrderErrLog(OrderMain orderMainR,String orderSubNoR,String message){
        //判断是否已存在
        Map<String,String> map = new HashMap();
        map.put("errorCode", ErrConst.SaleAfterOrder_ToWMS.getCode());
        map.put("orderSubNo", orderSubNoR);
        List<OrdiErrOptLog> list = ordiErrOptLogService.findByFields(map);
        if(null==list || list.size()==0){
            OrdiErrOptLog errLog = new OrdiErrOptLog();
            errLog.setOrderNo(orderMainR.getOrderNo());
            errLog.setOrderSource(orderMainR.getOrderSource());
            errLog.setAliasOrderNo(orderMainR.getAliasOrderNo());
            errLog.setOrderSubNo(orderSubNoR);
            errLog.setOperateCount(new BigDecimal(0));
            errLog.setResultDesc("WMS入库单接口调用异常:"+message);
            errLog.setErrorDesc("WMS入库单接口调用异常:"+message);
            errLog.setErrorCode(ErrConst.SaleAfterOrder_ToWMS.getCode());
            errLog.setErrorType(ErrConst.SaleAfterOrder_ToWMS.getDesc());
            errLog.setOperateTime(new Date());
            this.ordiErrOptLogService.save(errLog);
        }else{
            OrdiErrOptLog errLog = list.get(0);
            BigDecimal operateCount = errLog.getOperateCount();
            if(null==operateCount){
                operateCount = new BigDecimal(0);
            }
            errLog.setOperateCount(operateCount.add(new BigDecimal(1)));
            errLog.setOperateTime(new Date());
            this.ordiErrOptLogService.update(errLog);
        }
    }
    
    /**
     * 取消入库单传输至WMS
     * @param orderSubNo
     * @return
     */
    public boolean sendOmsToWmsSaleAfterOrderCancel(String orderSubNoR){
        boolean result = true;
        try {
            java.net.URL url = new java.net.URL(this.wms04);
            org.apache.axis.client.Service service = new org.apache.axis.client.Service();
            CERPWSService2ServiceSoapBindingStub wmsService = new CERPWSService2ServiceSoapBindingStub(url, service);
            // 获取原订单信息
            if (StringUtils.isEmpty(orderSubNoR)) {
                return false;
            }
            String orderNoR = this.orderNoService.getOrderNoBySubNo(orderSubNoR);
            OrderMain orderMainR = this.orderMainService.getByField(OrderMain_.orderNo, orderNoR);
            if (orderMainR == null) {
                return false;
            }
            // 入参1:安全检查
            WmsSecurityInfo ansInfo = new WmsSecurityInfo();
            ansInfo.setUsername(WMS_WmsSecurityInfo_UserName);
            ansInfo.setPassword(WMS_WmsSecurityInfo_Password);
            // 入参2:
            WmsParamInfo wmsParam = new WmsParamInfo();
            wmsParam.setWarehouseid(WMS_WmsParamInfo_Warehouseid_Cancel);
            wmsParam.setCustomerid(WMS_WmsParamInfo_Customerid_Cancel);
            wmsParam.setMessageid(WMS_WmsParamInfo_Messageid_Cancel);
            wmsParam.setStdno(WMS_WmsParamInfo_Stdno_Cancel);
            String[] param = { WMS_CustomerID, WMS_WarehouseID, orderSubNoR, WMS_WmsParamInfo_Stdno};
            wmsParam.setParam(param);
            
            //记录发送报文
            ObjectMapper mapper = new ObjectMapper();
            String sendMsg = mapper.writeValueAsString(wmsParam);//发送的报文
            IntfSent msg = new IntfSent();
            msg.setIntfCode(IntfSentConst.OMS_WMS_SaleAfterOrder_Cancel.getCode());
            msg.setMsg(sendMsg);
            msg.setOrderNo(orderMainR.getOrderNo());
            msg.setOrderSubNo(orderSubNoR);
            msg.setCreateTime(new Date());
            this.IntfSentService.save(msg);
            //调用WMS接口
            WmsResultInfo wmsResultInf = wmsService.processSP(ansInfo, wmsParam);
            
            //记录接口发送结果
            msg.setSucceedFlag(Long.valueOf(wmsResultInf.getReturnFlag()));
            this.IntfSentService.update(msg);

            // 如果没有传输成功，则需记录进入异常处理订单
            if (!WMS_ReturnFlag_Success.equals(wmsResultInf.getReturnFlag())) {
                result = false;
                /*if(!wmsResultInf.getReturnCode().equals(WMS_ReturnCode_ReCancel)){ //重复取消不用进入异常操作【失败不会取消意向单】
                    OrdiErrOptLog errLog = new OrdiErrOptLog();
                    errLog.setOrderNo(orderMainR.getOrderNo());
                    errLog.setOrderSource(orderMainR.getOrderSource());
                    errLog.setAliasOrderNo(orderMainR.getAliasOrderNo());
                    errLog.setOrderSubNo(orderSubNoR);
                    errLog.setResultDesc("code:"+wmsResultInf.getReturnCode()+",msg:"+wmsResultInf.getReturnDesc());
                    errLog.setOperateType(ErrConst.SaleAfterOrder_ToWMS.getCode());
                    this.ordiErrOptLogService.save(errLog);
                }*/
                //如果返回失败，则记录接收报文
                IntfReceived received  =new IntfReceived();
                received.setCreateTime(new Date());
                received.setOrderNo(orderMainR.getOrderNo());
                received.setOrderSubNo(orderSubNoR);
                received.setMsg(mapper.writeValueAsString(wmsResultInf));//返回结果
                received.setSucceed(-1l);
                received.setIntfCode(IntfSentConst.OMS_WMS_SaleAfterOrder_Cancel.getCode());
                this.intfReceivedService.save(received);
            }
            // 如果富勒已取消，继续往下走
            if(wmsResultInf.getReturnCode().equals(WMS_ReturnCode_ReCancel)){ 
                result = true;
            }
        } catch (Exception e) {
            logger.info("{}", e);
            throw new RuntimeException(e.getMessage());
        }
        
        return result;
    }

    /**
     * 售后意向单传输TMS(出库后：WMS物流出库状态传输至OMS时，调用此接口)
     * 
     * @param orderSubNo
     */
    public boolean sendOmsToTmsSaleAfterOrder(String orderSubNo) {
        boolean flag = true;
        if (StringUtils.isEmpty(orderSubNo)) {
            return false;
        }
        OrderMain orderMain = this.orderMainService.getByOrderSubNo(orderSubNo);
        if (null == orderMain) {
            return false;
        }
        OrderSub orderSub = orderMain.getOrderSubs().get(0);
        long sendResult = 1l;
        String outputMsg = "";
        try {
            // 构建TMS数据对象
            TmsOrderDTO tmsOrderDTO = new TmsOrderDTO();
            String curDateTimeStr = DateUtil.getStringFormatByDate(DateUtil.getNowDate(),
                    DateUtil.FORMAT_GENERALDATETIME);
            // 获取省、市、区
            Map<String, TransportArea> areasMap = transportAreaCacheService.getAllByAreaId(orderSub.getAddressCode());

            tmsOrderDTO.setLogisticCompanyId(orderSub.getDeliveryMerchantNo());// 物流商
            tmsOrderDTO.setTxLogisticID(orderSubNo);// 子订单no
            //tmsOrderDTO.setOrderid(orderSub.getId().toString());// 子订单id[调整为orderSubNo]
            tmsOrderDTO.setOrderid(orderSubNo);
            tmsOrderDTO.setSrcOrderNo(orderSub.getOrderSubRelatedOrigin());// 原销售子订单号
            tmsOrderDTO.setType(getTmsOrderType(orderMain.getOrderCategory()));
            tmsOrderDTO.setFlag(ReturnChangeOrderService.TMS_FLAG_NORMAL);
            tmsOrderDTO.setName(orderSub.getUserName());
            //tmsOrderDTO.setPostCode(orderSub.getPostCode());
            // update by 20141022 for TMS的换货出库单、入库单进行关联
            if(OrderMainConst.OrderMain_OrderCategory_Change.getCode().equals(orderMain.getOrderCategory())){
                tmsOrderDTO.setPostCode(orderMain.getChgOurOrderNo());
            }else if(OrderMainConst.OrderMain_OrderCategory_ChangeOut.getCode().equals(orderMain.getOrderCategory())){
                tmsOrderDTO.setPostCode(orderMain.getOrderRelatedOrigin());
            }
            tmsOrderDTO.setPhone(orderSub.getPhoneNum());
            tmsOrderDTO.setMobile(orderSub.getMobPhoneNum());
            try {
                if(null!=areasMap.get(TransportAreaCacheService.Treelevel_Province)){
                    tmsOrderDTO.setProv(areasMap.get(TransportAreaCacheService.Treelevel_Province).getAreaName());// 省    
                }
                if(null!=areasMap.get(TransportAreaCacheService.Treelevel_City)){
                    tmsOrderDTO.setCity(areasMap.get(TransportAreaCacheService.Treelevel_City).getAreaName());// 市
                }
                if(null!=areasMap.get(TransportAreaCacheService.Treelevel_Area)){
                    tmsOrderDTO.setArea(areasMap.get(TransportAreaCacheService.Treelevel_Area).getAreaName());// 区
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            tmsOrderDTO.setAddresscode(orderSub.getAddressCode());
            tmsOrderDTO.setAddress(orderSub.getAddressDetail());
            tmsOrderDTO.setGoodsValue(orderMain.getTotalProductPrice().doubleValue());// 商品金额
            if(OrderMainConst.OrderMain_OrderCategory_Change.getCode().equals(orderMain.getOrderCategory())){
                tmsOrderDTO.setItemsValue(0);// 代收货款金额(换货的代收金额为0)
            }else{
                tmsOrderDTO.setItemsValue(orderMain.getTotalPayAmount().doubleValue());// 代收货款金额
            }
            tmsOrderDTO.setTotalPCS(orderSub.getOrderRetChgItems().size());// 总件数
            tmsOrderDTO.setTotalWeight(orderMain.getWeight().doubleValue());
            tmsOrderDTO.setRemark(orderMain.getRemark());
            tmsOrderDTO.setInsuranceValue(0.0d);// 保值金额（暂时没有使用，默认为0.0）
            tmsOrderDTO.setCreateTime(curDateTimeStr);
            if (null != orderMain.getNeedInvoice()) {
                tmsOrderDTO.setNeedInvoice(orderMain.getNeedInvoice().intValue());
            }
            //tmsOrderDTO.setToid(orderSub.getBolNo());// 箱签号
            String distributeTypeName = commonCacheUtil.getDistributeTypeNameByCode(orderSub.getDistributeType());//入库物流方式
            tmsOrderDTO.setToid(distributeTypeName);// 入库物流方式（意向单）
            //获取退款方式
            String payNames = "";
            if(commonUtilService.checkIfNeedRefund(orderMain.getIfNeedRefund())){
                if(null!=orderMain.getOrderPays()){
                    
                    for(OrderPay pay:orderMain.getOrderPays()){
                        String payName = pay.getPayName();
                        if(StringUtils.isEmpty(payName)){
                            PaymentMethod pMethod = paymentMethodUtil.getRefundMethodMap().get(pay.getPayCode());
                            if(null!=pMethod){
                                payName = pMethod.getName();
                            }
                        }
                        payNames = payNames+payName+",";
                    }
                    if(!"".equals(payNames)){
                        payNames = payNames.substring(0, payNames.length()-1);    
                    }
                }
            }
            tmsOrderDTO.setPayMode(payNames);
            
            tmsOrderDTO.setDeliverymode(Integer.parseInt(orderSub.getDistributeType()));
            tmsOrderDTO.setOuthousetime(curDateTimeStr);// 出库时间
            tmsOrderDTO.setReviewtime(curDateTimeStr);// 订单复核时间
            tmsOrderDTO.setWmsOrderType(ReturnChangeOrderService.TMS_FLAG_NORMAL);
            // 构建明细
            TmsOrderItemsDTO itemDTO = new TmsOrderItemsDTO();
            ArrayList<OrderItemTms> items = new ArrayList();
            for (OrderRetChgItem item : orderSub.getOrderRetChgItems()) {
                OrderItemTms itemTMS = new OrderItemTms();
                itemTMS.setItemId(item.getId().toString());
                itemTMS.setItemName(item.getCommodityName());
                itemTMS.setItemValue(item.getUnitPrice().toString());// 商品金额
                itemTMS.setSkuId(item.getSkuNo());
                itemTMS.setBarCode(item.getBarCode());
                itemTMS.setNumber(item.getSaleNum().toString());
                itemTMS.setSpecial(item.getProductCategoryName());// 物料类型（商品分类）
                items.add(itemTMS);
            }
            itemDTO.setItem(items);
            tmsOrderDTO.setItems(itemDTO);
            // 发送至activeMQ
            outputMsg = xmlConverterOrder.convertFromObjectToXMLString(tmsOrderDTO);
            tmsRetChgOrderInfoSender.send(outputMsg);
        } catch (Exception ex) {
            flag = false;
            sendResult = -1l;
            ex.printStackTrace();
        }
        // 记录接口发送的报文
        IntfSent msg = new IntfSent();
        msg.setIntfCode(IntfSentConst.OMS_TMS_SaleAfterOrder.getCode());
        msg.setSucceedFlag(sendResult);
        msg.setMsg(outputMsg);//返回参数描述
        msg.setOrderNo(orderMain.getOrderNo());
        msg.setOrderSubNo(orderSubNo);
        msg.setCreateTime(new Date());
        this.IntfSentService.save(msg);
        return flag; 

    }

    /**
     * 根据意向单类型，获取TMS所对应的type
     * 
     * @param orderCategory
     * @return
     */
    private String getTmsOrderType(String orderCategory) {
        String type = "";
        if (StringUtils.isEmpty(orderCategory)) {
            return type;
        }
        if (orderCategory.equals(OrderMainConst.OrderMain_OrderCategory_Change.getCode())) {
            type = ReturnChangeOrderService.TMS_TYPE_HH; // 换货
        } else if (orderCategory.equals(OrderMainConst.OrderMain_OrderCategory_Return.getCode())) {
            type = ReturnChangeOrderService.TMS_TYPE_TH; // 退货
        } else if (orderCategory.equals(OrderMainConst.OrderMain_OrderCategory_Reject.getCode())) {
            type = ReturnChangeOrderService.TMS_TYPE_JS; // 拒收
        }
        return type;
    }
    
    /**
     * 取消入库单传输至TMS
     * @param orderSubNo
     * @return
     */
    public boolean sendOmsToTmsSaleAfterOrderCancel(String orderSubNo){
        boolean result = true;
        if (StringUtils.isEmpty(orderSubNo)) {
            return false;
        }
        String orderNo = orderNoService.getOrderNoBySubNo(orderSubNo);
        OrderMain orderMain = orderMainService.getByField(OrderMain_.orderNo, orderNo);
        OrderSub orderSub = orderSubService.getByField(OrderSub_.orderSubNo, orderSubNo);
        if (null == orderSub) {
            return false;
        }
        try {
            String curDateTimeStr = DateUtil.getStringFormatByDate(DateUtil.getNowDate(),
                    DateUtil.FORMAT_GENERALDATETIME);
            // 构建TMS数据对象
            TmsCancelOrderDTO tmsCancelDTO = new TmsCancelOrderDTO();
            tmsCancelDTO.setOrderid(String.valueOf(orderSub.getId()));
            tmsCancelDTO.setTxLogisticID(orderSubNo);
            tmsCancelDTO.setCancelTime(curDateTimeStr);
            tmsCancelDTO.setType(getTmsOrderType(orderMain.getOrderCategory()));
            tmsCancelDTO.setRemark("");
            // 发送至activeMQ
            ObjectMapper mapper = new ObjectMapper();
            String sendMsg = mapper.writeValueAsString(tmsCancelDTO);//发送的报文
            tmsRetChgOrderCancelInfoSender.sendWithTrack(sendMsg, orderNo, orderSubNo, IntfSentConst.OMS_TMS_SaleAfterOrder_Cancel.getCode());
        }catch(Exception e){
            logger.info("{}", e);
            result = false;
        }
        return result;
    }
    

    public String getMem01() {
        return mem01;
    }

    public void setMem01(String mem01) {
        this.mem01 = mem01;
    }

    public String getSim01() {
        return sim01;
    }

    public void setSim01(String sim01) {
        this.sim01 = sim01;
    }

    public String getSim02() {
        return sim02;
    }

    public void setSim02(String sim02) {
        this.sim02 = sim02;
    }

    public String getSim03() {
        return sim03;
    }

    public void setSim03(String sim03) {
        this.sim03 = sim03;
    }

    public String getWms04() {
        return wms04;
    }

    public void setWms04(String wms04) {
        this.wms04 = wms04;
    }

    public String getMem02() {
        return mem02;
    }

    public void setMem02(String mem02) {
        this.mem02 = mem02;
    }

	public String getSim06() {
		return sim06;
	}

	public void setSim06(String sim06) {
		this.sim06 = sim06;
	}

    public String getMemVipCard() {
        return memVipCard;
    }

    public void setMemVipCard(String memVipCard) {
        this.memVipCard = memVipCard;
    }
    

	
}

package com.ibm.sc.oms.service.impl;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.caucho.hessian.client.HessianProxyFactory;
import com.ibm.oms.client.dto.order.create.refactor.ReceiveOrderMainDTO;
import com.ibm.promo.dto.promotion.InfCart;
import com.ibm.promo.dto.promotion.InfCartAdjust;
import com.ibm.promo.dto.promotion.InfCartItem;
import com.ibm.promo.intf.OrderPromotionCalculateService;
import com.ibm.sc.oms.service.test.BaseTest;
import com.ibm.sc.oms.service.test.CommonTestConst;
import com.ibm.sc.oms.service.test.CommonTestUtil;

/**
 * Description: //模块目的、功能描述  
 * @author lvzhijun
 * Date:   2018年1月11日 
 */
public class CartHessianTest { 
	//private final Logger logger = LoggerFactory.getLogger(getClass());
    public static void main(String[] args) {
//        BigDecimal d1 = new BigDecimal("1");
//        BigDecimal d2 = new BigDecimal("2");
//        System.out.println(d1.compareTo(d2));
        testpromotion();
    }
    
    public static void testpromotion() {
    	 Logger logger = LoggerFactory.getLogger(CartHessianTest.class);
    	CommonTestUtil commonTestUtil = new CommonTestUtil();
//      String url = "http://localhost:8080/promo-rs/remoting/PromotionRsService";  
    	//http://10.0.4.15:8081/
      //String url = "http://10.1.42.88:8080/promo-rs/remoting/OrderPromotionCalculateService";
      String url = "http://10.0.4.15:8081/promo-rs/remoting/OrderPromotionCalculateService";  
       
      HessianProxyFactory factory = new HessianProxyFactory();  
      try {
//          ProductDetailPromotionInfoService bundleService = (ProductDetailPromotionInfoService) factory.create(ProductDetailPromotionInfoService.class, url);
//          List<ProductPromotionInfoDto> dd=  bundleService.productPromotionInfo("22", "1", "1104", "1");
//          for (ProductPromotionInfoDto dto : dd) {
//              System.out.println(dto.getPromotionId());
//          }
//          PromotionRsService bundleService = (PromotionRsService) factory.create(PromotionRsService.class, url);
//          bundleService.findCouponDonate(1);
          OrderPromotionCalculateService bundleService = (OrderPromotionCalculateService) factory.create(OrderPromotionCalculateService.class, url);
          InfCart infCart = new InfCart();
//          infCart.setStoreId(1111L);
//          infCart.setZoneId(331100L);
//          infCart.setMemberId(2709L);
//          infCart.setChannelId(1L);
//          List<InfCartItem> cartItemList = new ArrayList<InfCartItem>();
//          
//          
//          InfCartItem it  = new InfCartItem();
////          it.setActivityCode("XDPZZ");
////          SINGLE 单品 ，GIFT赠品 BUNDLE 绑定销售
//          it.setItemType("SINGLE");
//          it.setSkuId(60786L);
//          it.setChecked(true);
//          it.setCount(1);
//          it.setAmount(new BigDecimal(100));
//          it.setPrice(new BigDecimal("100.00"));
//          it.setPromotionId(14L);
//          it.setProductId(60786L);
//          List<InfCartItem> bundleItemList = new ArrayList<InfCartItem>();
//          InfCartItem bundleItem  = new InfCartItem();
//          bundleItem.setItemType("GIFT");
//          bundleItem.setSkuId(60786L);
//          bundleItem.setProductId(60786L);
//          bundleItem.setChecked(true);
//          bundleItem.setCount(1);
//          bundleItem.setAmount(new BigDecimal(100));
//          bundleItem.setPrice(new BigDecimal("100.00"));
//          bundleItemList.add(bundleItem);
//          it.setBundleItemList(bundleItemList);
//          cartItemList.add(it);
//          infCart.setCartItemList(cartItemList);
          
          InfCart InfCartparams = commonTestUtil.genJsonObjFromFile(CommonTestConst.shopCart,InfCart.class);
          
          //InfCart cc =   bundleService.calculateShoppingCart(InfCartparams);
          InfCart cc = null;
          BaseTest.logInfoObjectToJson("shopcartResult", logger, cc);
          
          
          System.out.println("totalAdjust="+cc.getTotalAdjust());
          System.out.println("totalAmount="+cc.getTotalAmount());
          System.out.println("totalProduct="+cc.getTotalProduct());
          List<InfCartItem> itemList =   cc.getCartItemList();
          for (InfCartItem infCartItem : itemList) {
              List<InfCartAdjust> ca1  =infCartItem.getItemAdjustList();
              for (InfCartAdjust infCartAdjust : ca1) {
                  System.out.println(infCartAdjust.toString());
              }
        }
//          List<InfCartAdjust> ca =  cc.getCartAdjustList();
//          for (InfCartAdjust infCartAdjust : ca) {
//            System.out.println(infCartAdjust.toString());
//        }
//          System.out.println("point="+cc.getp);
      } catch (MalformedURLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }  
  
    }
}

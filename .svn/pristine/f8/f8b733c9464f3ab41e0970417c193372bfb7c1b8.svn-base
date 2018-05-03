package com.ibm.oms.service.pay.intf.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.common.dto.DefaultOutputDto;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.pay.dto.PayOnLineGoodsDto;
import com.ibm.oms.service.pay.dto.MiyaPayOnLineInputDto; 
import com.ibm.oms.service.pay.dto.MiyaPayOnLineOutputDto;
import com.ibm.oms.service.pay.intf.PayDataService;
import com.ibm.oms.service.util.CommonUtilService;
import com.ibm.oms.service.util.EmptyUtils;
import com.ibm.oms.service.util.JaxbUtil;
import com.ibm.store.intf.StoreInfoHsService;
@Service
public class PayDataServiceImpl implements PayDataService{
	private final Logger logger = LoggerFactory.getLogger(PayDataServiceImpl.class);
	@Autowired
	private OrderMainService orderMainService;//获取订单信息
	@Autowired
	private StoreInfoHsService storeInfoHsService;//获取门店支付平台
	@Override
	public MiyaPayOnLineInputDto getOrderInfo(String orderNo,String payType) throws Exception {
		 MiyaPayOnLineInputDto listDato = new MiyaPayOnLineInputDto();
		 List<PayOnLineGoodsDto> goodsList  = new ArrayList<PayOnLineGoodsDto>();
		 OrderMain findByOrderNo = orderMainService.findByOrderNo(orderNo);//订单信息
		 if(null != findByOrderNo){//订单信息不为空
			 DefaultOutputDto payMerchant = storeInfoHsService.getPayMerchant(findByOrderNo.getSaleStoreCode(), payType);//支付平台
			 if(findByOrderNo.getOrderItems().size()>0){ 
				 for(int i =0;i<findByOrderNo.getOrderItems().size();i++){
					 PayOnLineGoodsDto goodsDto = new PayOnLineGoodsDto();
					 OrderItem orderItem = findByOrderNo.getOrderItems().get(i);
					 goodsDto.setGoodsId(orderItem.getCommodityCode());//货号
					 goodsDto.setGoodsName(orderItem.getCommodityName());//商品名称
					 goodsDto.setPrice(String.valueOf(orderItem.getUnitDeductedPrice().multiply(new BigDecimal(100)).intValue()));//折后价格
					 goodsDto.setQuantity(String.valueOf(orderItem.getSaleNum()));//数量
					 goodsList.add(goodsDto);
				 }
			 } 
			 String objConverJson = JaxbUtil.objConverJson(goodsList);//商品列表 
			 listDato.setA2(String.valueOf(payMerchant.getResponse_data()));///商户号 米雅提供的商户号 
			 listDato.setA3(findByOrderNo.getSaleStoreCode());//门店账号 通常为商户门店号
			 listDato.setA4(findByOrderNo.getMerchantNo());//设备号 通常为商户门店 pos 机编号
			 listDato.setA5(findByOrderNo.getSalesclerkNo());//收银编号 通常为商户门店收银员编号
			 if(findByOrderNo.getOrderInvoices().size()>0){
				 listDato.setA10(findByOrderNo.getOrderInvoices().get(0).getInvoiceNum());//发票号
			 }
			 listDato.setB12(payType);//支付平台类型 1-微信  3-支付宝
			 listDato.setB1(orderNo);//商户订单号 商户侧生成的订单号，不可重复
			 listDato.setB4(String.valueOf(findByOrderNo.getTotalPayAmount().multiply(new BigDecimal(100)).intValue()));
			 listDato.setB5(objConverJson);////商品信息 商品信息
		 }
		return listDato;
	}
	@Override
	public String writeOrderLogs(String respXml) throws Exception {
		MiyaPayOnLineOutputDto beans = CommonUtilService.converyToJavaBean(respXml, MiyaPayOnLineOutputDto.class);
		if("PAYSUCCESS".equals(beans.getC2())){
			OrderMain orderMain = orderMainService.getByField("orderNo",beans.getC5());
			if(EmptyUtils.isNotEmpty(orderMain)){
				orderMain.setStatusPay("0420");
				orderMain.setStatusTotal("0140");
				orderMain.setTotalPayAmount(new BigDecimal(beans.getC7()));//TOTAL_PAY_AMOUNT
				orderMainService.update(orderMain);
				return "SUCCESS";
			}
			return "FAIL";
		}
		return "FAIL";
	}	
}

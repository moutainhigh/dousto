package com.ibm.sc.oms.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.client.dto.order.create.refactor.ReceiveOrderMainDTO;
import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.dao.constant.PayType;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.intf.constant.OrderMainConst;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderDTO;
import com.ibm.oms.intf.intf.inner.OrderPromotionDTO;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderPayService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.OrderSplitService;
import com.ibm.oms.service.business.SubbmitOrderService;
import com.ibm.promo.dto.promotion.InfCart;
import com.ibm.sc.oms.service.test.BaseTest;

public class OrderMainServiceImplTest extends BaseTest{
    private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	OrderMainService orderMainService;
	@Autowired
	OrderSubService orderSubService;
	@Autowired
	OrderPayService orderPayService;
	@Autowired
	OrderNoService orderNoService;
	@Autowired
	OrderItemService orderItemService;
	@Autowired
	OrderSplitService orderSplitService ;
	@Autowired
	SubbmitOrderService subbmitOrderService;
	
	@Resource(name="orderCreateService")
	public void setCreateService(OrderCreateService createService) {
		this.createService = createService;
	}

	OrderCreateService createService;
	
	@Autowired
	@Qualifier("orderCreateOffline")
	OrderCreateService offlineCreateService;
	
	
	//@Test
    public void testMemberWithNoValues() {
        BtcOmsReceiveOrderDTO dto = new BtcOmsReceiveOrderDTO();
        // validate the input
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<BtcOmsReceiveOrderDTO>> violations = validator.validate(dto);
        if(violations.size()>0){
            for(ConstraintViolation<BtcOmsReceiveOrderDTO> v:violations){
                System.out.println(v.getMessage());
                System.out.println(v.getMessageTemplate());
                System.out.println(v.getInvalidValue());
            }
        }
    }
	@Test
	public void testInsertOrderMain() {
		OrderMain om;
		OrderSub sub;
		for(int i=1;i<3;i++){
			om = new OrderMain();
			sub = new OrderSub();
			String strIndex = String.valueOf(i);
			long billType = CommonConst.OrderMain_BillType_Positive.getCodeLong();
			//头表
			om.setOrderSource(OrderMainConst.OrderMain_Ordersource_B2c.getCode());
			om.setConfirmerName("henry00"+strIndex);
			om.setMemberNo(strIndex);
			om.setCustomerName("肖红亮");
			om.setCustomerPhone("18982533683");
			om.setMerchantNo(strIndex);
			om.setWeight(new BigDecimal(125));
			om.setTransportFee(new BigDecimal(12));
			om.setTotalProductPrice(new BigDecimal(31500));
			om.setDateCreated(new Timestamp(i));
			om.setBillType(billType);
			om.setStatusConfirm(OrderStatus.Order_ConfirmStatus_AutoSuccess.getCode());
			om.setStatusTotal(OrderStatus.ORDER_PROCESSING.getCode());
			om.setStatusPay(OrderStatus.Order_PayStatus_Success.getCode());
			long orderId = orderMainService.save(om);
			String orderNo = orderNoService.getOrderNoByOrderId(String.valueOf(orderId));
			om.setOrderNo(orderNo);
			
			
			orderMainService.update(om);
			//支付
			OrderPay pay = new OrderPay();
			pay.setBillType(billType);
			pay.setBankTypeCode(PayType.PINGAN_BANK.getId());
			pay.setPayName(PayType.PINGAN_BANK.getPayName());
			pay.setPayCode(PayType.PINGAN_BANK.getId());
			pay.setOrderNo(orderNo);
			pay.setIdOrder(orderId);
			pay.setPayAmount(new BigDecimal(31512));
			orderPayService.save(pay);
			//子表
			String subOrderNo = orderNoService.getOrderSubNoByOrderNo(orderNo, i);
			sub.setIdOrder(orderId);
			sub.setOrderNo(orderNo);
			sub.setOrderSubNo(subOrderNo);
			sub.setLogisticsOutNo("0000000000"+strIndex);
			sub.setDeliveryMerchantNo(strIndex);
			long orderSubId = orderSubService.save(sub);
			for(int j=1;j<3;j++){
			    //订单明细
			    String itemNo = orderNoService.getOrderItemNoBySubOrderNo(subOrderNo, j);
	            OrderItem item = new OrderItem();
	            item.setOrderNo(orderNo);
	            item.setOrderSubNo(subOrderNo);
	            item.setOrderItemNo(itemNo);
	            item.setIdOrder(orderId);
	            item.setIdOrderSub(orderSubId);
	            item.setSaleNum((long)3);
	            item.setWeight(new BigDecimal(10));
	            item.setUnitPrice(new BigDecimal(120));
	            item.setUnitDiscount(new BigDecimal(15));
	            item.setPayAmount(new BigDecimal(315));
	            item.setSkuNo("sku"+j);    
	            orderItemService.save(item);
			}
			
		}
		log.info("success");
	}

	private OrderPromotionDTO BtcOmsReceiveItemPromo(){
	    OrderPromotionDTO op = new OrderPromotionDTO();
	    op.setPromoLevel(CommonConst.OrderPromotion_Promolevel_Item.getCode());
	    op.setTicketAmount("123.23");
	    op.setPointCount("9");
	    return op;
	}
	
	private String getRandomStr(){
	    return (System.currentTimeMillis()+"").substring(9);
	}
	
//	@Test
//	public Pager testListOrderMain(){
//		CriteriaBuilder cb = getCriteriaBuilder();
//    	CriteriaQuery<MemberLevelRuleDesc> c = cb.createQuery(MemberLevelRuleDesc.class);
//    	Root<MemberLevelRuleDesc> root = c.from(MemberLevelRuleDesc.class);
//    	c.select(root);
//    	List<Predicate> predicates = new ArrayList<Predicate>();
//    	Predicate predicate = cb.isFalse(root.get(MemberLevelRuleDesc_.deleted));
//    	predicates.add(predicate);
//    	c.where(predicates.toArray(new Predicate[0]));
//    	c.orderBy(cb.asc(root.get(MemberLevelRuleDesc_.levelSequenceNo)));
//        Pager newPager = this.findByPager(c, pager);
//    	
//		return new Pager();
//	}
	
	@Test
    public void createOrder(){
		BtcOmsReceiveOrderDTO  bd =GenerateBtcOmsReceiveOrderDTOJson.buildBtcOmsReceiveOrderDTO();
		
		
		ObjectMapper mapper = new ObjectMapper();
        String itemSnapshotStr = null;
        try {
        	mapper.setSerializationInclusion(Include.NON_EMPTY);  
            itemSnapshotStr = mapper.writeValueAsString(createService.createOrder(bd));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(itemSnapshotStr);
		
    }
	//下线订单创建
	@Test
    public void createOfflineOrder(){
		BtcOmsReceiveOrderDTO  bd =GenerateBtcOmsReceiveOrderDTOJson.buildBtcOmsReceiveOrderDTO();
		offlineCreateService.createOrder(bd);
    }
	
	@Test
	public void splitOrder() throws Exception{
		orderSplitService.handleOrderSplit("542938067701", orderSplitService.SPLITTYPE_SKU,null);
	}
	
	@Test
	public void subbmitTest(){
		ReceiveOrderMainDTO rm = GenerateReceiveOrderMainDTO.buildReceiveOrderMainDTO();
		InfCart calculateAftercart = subbmitOrderService.omsReceiveOrderDTOGeneraceInfCartParam(rm);
		subbmitOrderService.transBtcOmsReceiveOrderDTO(rm, calculateAftercart);
		
	}
	
}

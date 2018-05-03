package order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.domain.persist.OrderSearch;
import com.ibm.oms.service.OrderSearchService;
import com.ibm.sc.bean.Pager;

/**
 * Description: 订单查询测试
 * 
 * @author YanYanZhang Date: 2018年1月15日
 */
public class OrderSearchTest extends BaseTest {

	private final Logger logger = LoggerFactory.getLogger(OrderSearchTest.class);
	@Autowired
	OrderSearchService oderSearchService;
	
	ObjectMapper objectMapper = new ObjectMapper();

//	AND ordersearc0_.BILL_TYPE = 1
//			AND ordersearc0_.CLIENT_REMARK LIKE '%测试%' #客户备注 买家留言
//			AND ordersearc0_.REMARK = '测试留言' #备注
//			AND ordersearc0_.MERCHANT_NO = '00195' #商家编号 店铺
//			AND ordersearc0_.DELIVERY_TYPE = 'peisong'# 配送方式
//			AND ordersearc0_.refund_type = 0 #退款申请 全部退款 部分退款
//			AND ordersearc0_.seller_message ='测试卖家留言' #卖家留言
//			AND ordersearc0_.is_suspension = 0 #是否挂起
//			AND ordersearc0_.is_merge = '' #合并单
//			and ordersearc0_.is_split = '' #拆分单
//			AND ordersearc0_.is_barter = '' #是否换货单
//			-- AND oi.product_year = NOW() #商品年份
//			AND oi.SKU_NO = 'P2500000111317S1' #商品sku
//			AND oi.COMMODITY_CODE = 'P2500000111317S' #商品货号
//			AND oi.COMMODITY_NAME = '宾格幼儿枕P005(7*28*45)' #商品名称
	@Test
	public void testOrderSearch() throws JsonProcessingException, ParseException {
		OrderSearch orderSearch = new OrderSearch();
		orderSearch.setBillType(1L);
		orderSearch.setClientRemark("测试");
		orderSearch.setRemark("测试留言");
		orderSearch.setMerchantNo("00195");
		orderSearch.setDeliveryType("peisong");
		orderSearch.setRefundType("ALL");
		orderSearch.setSellerMessage("测试卖家留言");
		orderSearch.setIsSuspension(0);
		orderSearch.setIsMerge(0);
		orderSearch.setIsSplit(0);
		orderSearch.setIsBarter(0);
		orderSearch.setSkuNo("P2500000111317S1");
		orderSearch.setCommodityCode("P2500000111317S");
		orderSearch.setCommodityName("宾格幼儿枕P005(7*28*45)");
		orderSearch.setProductYearStart(new Date());
		orderSearch.setProductYearEnd(new SimpleDateFormat("yyyy-MM-dd").parse("2015-01-01"));
		
//		orderSearch.setOrderNo("1230005333");
		Pager<OrderSearch> pager = new Pager<OrderSearch>(0, 10);
		pager = oderSearchService.findByOrderSearch(0, orderSearch, pager);
		logger.info("====================================result======={}", pager.getTotalCount());
	}
	
	@Test
	public void test() throws JsonProcessingException, ParseException{
		Pager pager = new Pager();
		OrderSearch orderSearch = new OrderSearch();
		orderSearch.setBillType(1L);
		orderSearch.setClientRemark("测试");
		orderSearch.setRemark("测试留言");
		orderSearch.setMerchantNo("00195");
		orderSearch.setDeliveryType("peisong");
		orderSearch.setRefundType("ALL");
		orderSearch.setSellerMessage("测试卖家留言");
		orderSearch.setIsSuspension(0);
		orderSearch.setIsMerge(0);
		orderSearch.setIsSplit(0);
		orderSearch.setIsBarter(0);
		orderSearch.setSkuNo("P2500000111317S1");
		orderSearch.setCommodityCode("P2500000111317S");
		orderSearch.setCommodityName("宾格幼儿枕P005(7*28*45)");
		orderSearch.setProductYearStart(new Date());
		orderSearch.setProductYearEnd(new SimpleDateFormat("yyyy-MM-dd").parse("2015-01-01"));
		logger.info("==============={}", objectMapper.writeValueAsString(pager));
		logger.info("==============={}", objectMapper.writeValueAsString(orderSearch));
	}
}

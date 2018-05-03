package com.ibm.oms.admin.hessan.test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ibm.oms.service.business.SynSalesReceptsOrderService;
import com.ibm.oms.service.business.WmsOmsService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-test.xml" })
public class DeleteServiceImplTest{
//	@Autowired
//	WmsOmsService wmsOmsService;
	@Autowired
	private SynSalesReceptsOrderService synSalesReceiptsOrderService;
	
	@Test
	public void Test5In5terFace(){
		/*		OmsOrderStatuDTO omsOrderStatuDTO = new OmsOrderStatuDTO();
				omsOrderStatuDTO.setBillNo("1202");
				com.ibm.oms.client.dto.order.ResponseBean responseBean = wmsOmsService.receiveStatusFromWMS(omsOrderStatuDTO);
				JSONObject js = JSONObject.fromObject(responseBean);
				System.out.println("responseBean===> "+ js.toString());
				System.out.println("--------------------------------------");*/
				//synSalesReceiptsOrderService.synSalesReceptsOrder();
		/*		QueryCategoryDTO queryCategoryDTO = new QueryCategoryDTO();
				queryCategoryDTO.setShopNo("wz002");
				orderGuideService.queryCategorySales(queryCategoryDTO);*/
				synSalesReceiptsOrderService.synSalesReceptsOrder();
//				wmsOmsService.orderSalesCancel("523606");
	}
}
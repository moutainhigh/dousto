package com.ibm.sc.oms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.dao.intf.HangOrderItemDao;
import com.ibm.oms.domain.persist.HangOrderItem;
import com.ibm.oms.domain.persist.HangOrderMain;
import com.ibm.oms.domain.persist.HangOrderSub;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderOutputDTO;
import com.ibm.oms.intf.intf.HangOrderReceiveOrderDTO;
import com.ibm.oms.service.business.HangOrderCreateService;
import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.sc.oms.service.test.BaseTest;

/**
 * @author wangqc
 * @date 2018年2月7日 上午10:01:20
 * 
 */
public class HangOrderCreateTest extends BaseTest{
	@Autowired
	HangOrderCreateService orderCreateHangService;
	
	@Resource(name="hangOrderCreateService")
	public void setOrderCreateHangService(HangOrderCreateService orderCreateHangService) {
		this.orderCreateHangService = orderCreateHangService;
	}
	
	@Test
    public void createHangOrder(){
		try{
		HangOrderReceiveOrderDTO  bd =GenerateOmsReceiveHangOrderDTOJson.buildBtcOmsReceiveOrderDTO();
		BtcOmsReceiveOrderOutputDTO createHangOrder = orderCreateHangService.createHangOrder(bd);
		System.out.println(createHangOrder.getMessage());
		System.out.println(createHangOrder.getSucceed());
		} catch(Exception e){
			e.printStackTrace();
		}
    }
	
	/*@Test
    public void queryHangOrderMain(){
		
        try {
    		List<HangOrderMain> queryHangOrderMain = orderCreateHangService.queryHangOrderMain("1", "2018-02-07 01:00:00", "2018-02-28 23:00:00", "1");
    		
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }*/
	
	/*@Test
    public void queryHangOrderMain(){
		
        try {
    		List<HangOrderMain> queryHangOrderMain = orderCreateHangService.queryHangOrderMainDetail("test");
    		for(HangOrderMain hom :queryHangOrderMain){
    			System.out.println("homID="+hom.getId());
    			for(HangOrderSub hos:hom.getHangOrderSubs()){
    				System.out.println("SubId="+hos.getId());
    				for(HangOrderItem hoi :hos.getHangOrderItems()){
    					System.out.println("ITemId"+hoi.getId());
    				}
    			}
    			System.out.println("ItemId="+hom.getHangOrderSubs().get(0).getHangOrderItems().get(0).getId());
    			System.out.println("PayId="+hom.getHangOrderPays().get(0).getId());
    			System.out.println("PromId="+hom.getOrderPromotions().get(0).getId());
    		}
    		
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }*/


	@Test
	public void delete(){
		try{
			BtcOmsReceiveOrderOutputDTO deleteHangOrderByOrderNo = orderCreateHangService.deleteHangOrderByOrderNo("test");
			System.out.println(deleteHangOrderByOrderNo.getMessage());
			System.out.println(deleteHangOrderByOrderNo.getSucceed());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/*@Test
	public void update(){
		try{
			HangOrderReceiveOrderDTO  bd =GenerateOmsReceiveHangOrderDTOJson.buildBtcOmsReceiveOrderDTO();
			BtcOmsReceiveOrderOutputDTO updateHangOrder = orderCreateHangService.updateHangOrder(bd);
			System.out.println(updateHangOrder.getMessage());
			System.out.println(updateHangOrder.getSucceed());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}*/

}

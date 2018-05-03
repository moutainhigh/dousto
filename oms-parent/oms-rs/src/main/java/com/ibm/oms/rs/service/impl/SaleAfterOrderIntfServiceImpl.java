package com.ibm.oms.rs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.oms.client.dto.saleAfterOrder.MessageDto;
import com.ibm.oms.client.dto.saleAfterOrder.ReturnHeaderOrderFromLasaDto;
import com.ibm.oms.client.dto.saleAfterOrder.ReturnHeaderOrderList;
import com.ibm.oms.client.dto.saleAfterOrder.ReturnItemOrderFromLasaDto;
import com.ibm.oms.client.dto.saleAfterOrder.SaleAfterOrderDetail;
import com.ibm.oms.client.dto.saleAfterOrder.SaleAfterOrderFromLasaDto;
import com.ibm.oms.client.dto.saleAfterOrder.SaleAfterOrderInfoData;
import com.ibm.oms.client.dto.saleAfterOrder.SaleAfterOrderInfoDto;
import com.ibm.oms.client.dto.saleAfterOrder.SaleAfterOrderLogisticsDto;
import com.ibm.oms.client.dto.saleAfterOrder.ShopSaleAfterOrderQueryDto;
import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.intf.intf.ResultDTO;
import com.ibm.oms.rs.Constants;
import com.ibm.oms.rs.service.SaleAfterOrderIntfService;
import com.ibm.oms.service.business.saleAfter.NewSaleAfterOrderService;
import com.ibm.oms.service.business.saleAfter.SaleAfterService;

import net.sf.json.JSONObject;

@Component("saleAfterOrderIntfService")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class SaleAfterOrderIntfServiceImpl implements SaleAfterOrderIntfService{

	@Resource
	private NewSaleAfterOrderService newSaleAfterOrderService;
	
	@Resource
	private SaleAfterService saleAfterService;

	@Override
	public JSONObject requestCreate() {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		return this.getJSONObject(Constants.ERROR, Constants.ERROR, "错误数据测试", json);
	}

	private JSONObject getJSONObject(String status, String code, String msg, JSONObject json) {
		json.put(Constants.RETURN_STATUS, status);
		json.put(Constants.RETURN_CODE, status);
		json.put(Constants.RETURN_MSG, msg);
		return json;
	}

	@Override
	public MessageDto createReturnOrder(SaleAfterOrderFromLasaDto frontDto) {
		String applySource = CommonConst.OrderRetChange_Applysource_B2c.getCode();//订单来源
		MessageDto message = new MessageDto();
		try{
			ResultDTO dto = newSaleAfterOrderService.receiveSaleAfterOrder(applySource,"ret",frontDto);
			if(dto != null && dto.getResultMessage() == "OK"){
				message.setSuccess("0");//0：成功       1：失败
				message.setOrderNo(dto.getOrderNo());
			}else if(dto != null){
				message.setSuccess("1");//0：成功       1：失败
				message.setMsg(dto.getResultMessage());
			}else{
				message.setSuccess("1");//0：成功       1：失败
			}
			return message;
		}catch(Exception e){
			System.out.println(e.getMessage()+e.getStackTrace());
			message.setMsg(e.getMessage());
			message.setSuccess("1");//0：成功       1：失败
			return message;
		}
	}
	
	@Override
	public MessageDto createChangeOrder(SaleAfterOrderFromLasaDto frontDto) {
		String applySource = CommonConst.OrderRetChange_Applysource_B2c.getCode();//订单来源
		MessageDto message = new MessageDto();
		try{
			ResultDTO dto = newSaleAfterOrderService.receiveSaleAfterOrder(applySource,"chg",frontDto);
			if(dto != null && dto.getResultMessage() == "OK"){
				message.setSuccess("0");
				message.setOrderNo(dto.getOrderNo());
			}else if(dto != null){
				message.setSuccess("1");//0：成功       1：失败
				message.setMsg(dto.getResultMessage());
			}else{
				message.setSuccess("1");//0：成功       1：失败
			}
			return message;
		}catch(Exception e){
			System.out.println(e.getMessage()+e.getStackTrace());
			message.setMsg(e.getMessage());
			message.setSuccess("1");//0：成功       1：失败
			return message;
		}
	}
	
	@Override
	public MessageDto createRefundOrder(SaleAfterOrderFromLasaDto dto){		
		MessageDto message = new MessageDto();
		try{
			ResultDTO resultDto = newSaleAfterOrderService.createRefundOrder(dto);
			if(resultDto != null && resultDto.getResultMessage() == "OK"){
				message.setSuccess("0");
				message.setOrderNo(resultDto.getOrderNo());
			}else if(resultDto != null){
				message.setSuccess("1");//0：成功       1：失败
				message.setMsg(resultDto.getResultMessage());
			}else{
				message.setSuccess("1");//0：成功       1：失败
			}
			return message;
		}catch(Exception e){
			System.out.println(e.getMessage()+e.getStackTrace());
			message.setMsg(e.getMessage());
			message.setSuccess("1");//0：成功       1：失败
			return message;
		}
	}
	
	@Override
	public MessageDto receiveReturnOrderFromPos(SaleAfterOrderFromLasaDto dto) {
		String applySource = CommonConst.OrderRetChange_Applysource_LS.getCode();//订单来源
		MessageDto message = new MessageDto();
		try{
			//数据校验
			
			ResultDTO result = newSaleAfterOrderService.receiveSaleAfterOrder(applySource,"ret",dto);
			if(result != null && result.getResultMessage() == "OK"){
				message.setSuccess("0");
				message.setOrderNo(result.getOrderNo());
			}else if(result != null){
				message.setSuccess("1");//0：成功       1：失败
				message.setMsg(result.getResultMessage());
			}else{
				message.setSuccess("1");//0：成功       1：失败
			}
			return message;
		}catch(Exception e){
			System.out.println(e.getMessage()+e.getStackTrace());
			message.setMsg(e.getMessage());
			message.setSuccess("1");//0：成功       1：失败
			return message;
		}
	}
	
	@Override
	public MessageDto receiveChangeOrderFromPos(SaleAfterOrderFromLasaDto dto) {
		String applySource = CommonConst.OrderRetChange_Applysource_LS.getCode();//订单来源
		MessageDto message = new MessageDto();
		try{
			ResultDTO result = newSaleAfterOrderService.receiveSaleAfterOrder(applySource,"chg",dto);
			if(result != null && result.getResultMessage() == "OK"){
				message.setSuccess("0");
				message.setOrderNo(result.getOrderNo());
			}else if(result != null){
				message.setSuccess("1");//0：成功       1：失败
				message.setMsg(result.getResultMessage());
			}else{
				message.setSuccess("1");//0：成功       1：失败
			}
			return message;
		}catch(Exception e){
			message.setMsg(e.getMessage());
			message.setSuccess("1");//0：成功       1：失败
			return message;
		}
	}
	
	@Override
	public ReturnHeaderOrderList querySaleAfterOrderList(ShopSaleAfterOrderQueryDto dto) {
		MessageDto message = new MessageDto();
		ReturnHeaderOrderList retDto = new ReturnHeaderOrderList();
		try{
			List<ReturnHeaderOrderFromLasaDto> dtoList = saleAfterService.findSaleAfterOrder(dto);
			retDto.setDtoList(dtoList);
			message.setMsg("成功取得数据");
			message.setSuccess("0");//0：成功       1：失败			
			retDto.setMsgDto(message);
			return retDto;
		}catch(Exception e){			
			message.setMsg(e.getMessage());
			message.setSuccess("1");//0：成功       1：失败
			retDto.setMsgDto(message);
			return retDto;
		}
	}
	
	@Override
	public SaleAfterOrderFromLasaDto querySaleAfterOrderDetail(ShopSaleAfterOrderQueryDto dto){
		SaleAfterOrderFromLasaDto afterDto = new SaleAfterOrderFromLasaDto();
		MessageDto message = new MessageDto();
		try{
			List<ReturnHeaderOrderFromLasaDto> headList = saleAfterService.findSaleAfterOrder(dto);//退货单列表查询
			List<ReturnItemOrderFromLasaDto> itemList = saleAfterService.findSaleAfterOrderDetail(dto.getReturnNo());//退货单明细查询
			//MSG			
			message.setSuccess("0");//0：成功       1：失败
			message.setMsg("退货单明细查询");
			afterDto.setMsgDto(message);
			//退货单抬头
			if(headList != null && headList.size() > 0){
				ReturnHeaderOrderFromLasaDto headDto = headList.get(0);
				afterDto.setHeaderDto(headDto);
			}
			//退货单行项目
			afterDto.setItemDto(itemList);
			
			return afterDto;
		}catch(Exception e){			
			message.setMsg(e.getMessage());
			message.setSuccess("1");//0：成功       1：失败
			afterDto.setMsgDto(message);
			return afterDto;
		}
	}
	
	@Override
	public SaleAfterOrderFromLasaDto queryShopSaleAfterOrderNum(ShopSaleAfterOrderQueryDto dto){
		int num = saleAfterService.countSaleAfterOrder(dto);
		SaleAfterOrderFromLasaDto numDto = new SaleAfterOrderFromLasaDto();
		MessageDto message = new MessageDto();
		numDto.setSaleAfterOrderNum(new Long(num));
		message.setSuccess("0");
		message.setMsg("门店退换货数量："+num);
		numDto.setMsgDto(message);
		return numDto;
	}
	
	@Override
	public MessageDto updateSaleAfterOrderStatus(String orderNo,String status){
		MessageDto message = new MessageDto();
		if("1".equals(status)){
			status = OrderStatus.RET_ORDER_RETURN_FINISHED.getCode();//1：确认 2：拒绝  3 : 撤销  4： 确认收货
		}else if("2".equals(status)){
			status = OrderStatus.RET_ORDER_STORE_REJECT.getCode();//1：确认 2：拒绝  3 : 撤销  4： 确认收货
		}else if("3".equals(status)){
			status = OrderStatus.StatusTypeCode_refundOrder_StatusConfirm_Cancel.getCode();//1：确认 2：拒绝  3 : 撤销  4： 确认收货
		}else if("4".equals(status)){
			status = OrderStatus.ORDER_ACCEPTED_PAID.getCode();//1：确认 2：拒绝  3 : 撤销  4： 确认收货
		}
		CommonOutputDTO dto = newSaleAfterOrderService.updateSaleAfterOrderStatus(orderNo, status);
		message.setOrderNo(dto.getOrderNo());
		message.setSuccess(dto.getCode());
		message.setMsg(dto.getMsg());
		return message;
	}
	
	@Override
	public SaleAfterOrderInfoData findOrderStatusProcessByOrderNo(ShopSaleAfterOrderQueryDto dto){
		SaleAfterOrderInfoData data = new SaleAfterOrderInfoData();
		MessageDto message = new MessageDto();
		try{
			if(dto != null){			
				List<SaleAfterOrderInfoDto> dtoList = saleAfterService.findByOrderNo(dto.getReturnNo());
				data.setDto(dtoList);
				message.setMsg("成功取得数据");
				message.setSuccess("0");//0：成功       1：失败				
			}else{
				message.setMsg("传入参数不对");
				message.setSuccess("1");//0：成功       1：失败	
			}
			data.setMsgDto(message);
			return data;
		}catch(Exception e){
			message.setMsg(e.getMessage());
			message.setSuccess("1");//0：成功       1：失败	
			data.setMsgDto(message);
			return data;
		}
	}
	
	@Override
	public SaleAfterOrderInfoDto findSaleAfterOrderInfoByOrderNo(ShopSaleAfterOrderQueryDto dto){		
		MessageDto message = new MessageDto();		
		SaleAfterOrderInfoDto retDto = new SaleAfterOrderInfoDto();
		try{
			retDto =  saleAfterService.findByOrderItemNo(dto);
			message.setMsg("成功取得数据");
			message.setSuccess("0");//0：成功       1：失败			
			retDto.setMsgDto(message);			
			return retDto;
		}catch(Exception e){			
			message.setMsg(e.getMessage());
			message.setSuccess("1");//0：成功       1：失败			
			retDto.setMsgDto(message);
			return retDto;
		}		
	}
	
	@Override
	public SaleAfterOrderDetail findSaleAfterDetailInfo(String orderNo){
		MessageDto message = new MessageDto();		
		SaleAfterOrderDetail retDto = new SaleAfterOrderDetail();
		try{
			retDto =  saleAfterService.findSaleAfterDetailInfo(orderNo);
			message.setMsg("成功取得数据");
			message.setSuccess("0");//0：成功       1：失败			
			retDto.setMsgDto(message);			
			return retDto;
		}catch(Exception e){			
			message.setMsg(e.getMessage());
			message.setSuccess("1");//0：成功       1：失败			
			retDto.setMsgDto(message);
			return retDto;
		}
	}
	
	@Override
	public MessageDto updateSaleAfterOrderApply(ReturnHeaderOrderFromLasaDto headerDto){
		MessageDto message = new MessageDto();
//		SaleAfterOrderDetail retDto = new SaleAfterOrderDetail();
		try{
//			retDto =  saleAfterService.findSaleAfterDetailInfo(orderNo);
			message.setMsg("成功取得数据");
			message.setSuccess("0");//0：成功       1：失败		
			return message;
		}catch(Exception e){			
			message.setMsg(e.getMessage());
			message.setSuccess("1");//0：成功       1：失败
			return message;
		}
	}
	
	@Override
	public MessageDto submitReturnLogisticsInfo(SaleAfterOrderLogisticsDto dto){
		MessageDto message = new MessageDto();
		try{
			ResultDTO result = newSaleAfterOrderService.submitReturnLogisticsInfo(dto);
			if(result != null && result.getResultMessage() == "OK"){
				message.setSuccess("0");
			}else if(result != null){
				message.setSuccess("1");//0：成功       1：失败
				message.setMsg(result.getResultMessage());
			}else{
				message.setSuccess("1");//0：成功       1：失败
			}
			return message;
		}catch(Exception e){
			message.setMsg(e.getMessage());
			message.setSuccess("1");//0：成功       1：失败
			return message;
		}
	}
}
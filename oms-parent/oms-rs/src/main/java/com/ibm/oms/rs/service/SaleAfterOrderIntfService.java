package com.ibm.oms.rs.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ibm.oms.client.dto.saleAfterOrder.MessageDto;
import com.ibm.oms.client.dto.saleAfterOrder.ReturnHeaderOrderFromLasaDto;
import com.ibm.oms.client.dto.saleAfterOrder.ReturnHeaderOrderList;
import com.ibm.oms.client.dto.saleAfterOrder.SaleAfterOrderDetail;
import com.ibm.oms.client.dto.saleAfterOrder.SaleAfterOrderFromLasaDto;
import com.ibm.oms.client.dto.saleAfterOrder.SaleAfterOrderInfoData;
import com.ibm.oms.client.dto.saleAfterOrder.SaleAfterOrderInfoDto;
import com.ibm.oms.client.dto.saleAfterOrder.SaleAfterOrderLogisticsDto;
import com.ibm.oms.client.dto.saleAfterOrder.ShopSaleAfterOrderQueryDto;

import net.sf.json.JSONObject;

/**
 * @Description: 统一服务接口
 * @author LH
 */
@Path("/saleAfterOrderIntf")
@Produces({ MediaType.APPLICATION_JSON })
public interface SaleAfterOrderIntfService {

	@POST
	@Path("/requestCreate")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject requestCreate();
	
	/**
	 * 生成退货单，调用方：前端
	 * 
	 * @Title createReturnOrder
	 * @param orderNo
	 * @param orderItemNo
	 * @return MessageDto
	 */
	public MessageDto createReturnOrder(SaleAfterOrderFromLasaDto dto);
	
	/**
	 * 生成换货单，调用方：前端
	 * 
	 * @Title createChangeOrder
	 * @param orderNo
	 * @param orderItemNo
	 * @return MessageDto
	 */
	public MessageDto createChangeOrder(SaleAfterOrderFromLasaDto dto);
	
	/**
	 * 仅退款，调用方：前端
	 * 
	 * @param dto
	 * @return
	 */
	public MessageDto createRefundOrder(SaleAfterOrderFromLasaDto dto);
	
	/**
	 * 生成退货单，调用方：浪沙
	 * 
	 * @Title receiveReturnOrderFromPos
	 * @param SaleAfterOrderFromLasaDto
	 * @return MessageDto
	 */
	public MessageDto receiveReturnOrderFromPos(SaleAfterOrderFromLasaDto dto);
	
	/**
	 * 生成换货单，调用方：浪沙
	 * 
	 * @Title receiveChangeOrderFromPos
	 * @param SaleAfterOrderFromLasaDto
	 * @return MessageDto
	 */
	public MessageDto receiveChangeOrderFromPos(SaleAfterOrderFromLasaDto dto);
	
	/**
	 * 退货单列表查询
	 * 
	 * @Title querySaleAfterOrderList
	 * @param ShopSaleAfterOrderQueryDto
	 * @return ReturnHeaderOrderList
	 */
	public ReturnHeaderOrderList querySaleAfterOrderList(ShopSaleAfterOrderQueryDto dto);
	
	/**
	 * 退货单详细查询
	 * 
	 * @Title querySaleAfterOrderDetail
	 * @param dto
	 * @return
	 */
	public SaleAfterOrderFromLasaDto querySaleAfterOrderDetail(ShopSaleAfterOrderQueryDto dto);
	
	/**
	 * 退货单数查询
	 * 
	 * @Title queryShopSaleAfterOrderNum
	 * @param dto
	 * @return
	 */
	public SaleAfterOrderFromLasaDto queryShopSaleAfterOrderNum(ShopSaleAfterOrderQueryDto dto);
	
	/**
	 * 退\换货状态调整接口
	 * 
	 * @Title updateSaleAfterOrderStatus
	 * @param orderNo
	 * @param status
	 * @return
	 */
	public MessageDto updateSaleAfterOrderStatus(String orderNo,String status);
	
	/**
	 * 退款历史记录信息 (退换货单状态流转日志)
	 * 
	 * @param orderNo
	 * @return
	 */
	public SaleAfterOrderInfoData findOrderStatusProcessByOrderNo(ShopSaleAfterOrderQueryDto dto);
	
	/**
	 * 退换货信息查询
	 * 
	 * @param dto
	 * @return
	 */
	public SaleAfterOrderInfoDto findSaleAfterOrderInfoByOrderNo(ShopSaleAfterOrderQueryDto dto);
	
    /**
     * 售后详情
     * 
     * @param orderNo
     * @return
     */
    public SaleAfterOrderDetail findSaleAfterDetailInfo(String orderNo);
    
    /**
	 * 修改售后申请
	 * 
	 * @param ReturnHeaderOrderFromLasaDto headerDto
	 * @return
	 */
	public MessageDto updateSaleAfterOrderApply(ReturnHeaderOrderFromLasaDto headerDto);
	
	/**
	 * 退换货单审批通过后，提交退换货单物流信息,调用方：前端
	 * 
	 * @param dto
	 * @return
	 */
	public MessageDto submitReturnLogisticsInfo(SaleAfterOrderLogisticsDto dto);
}
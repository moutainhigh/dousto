package com.ibm.oms.client.intf;

import com.ibm.oms.client.dto.saleAfterOrder.MessageDto;
import com.ibm.oms.client.dto.saleAfterOrder.ReturnHeaderOrderFromLasaDto;
import com.ibm.oms.client.dto.saleAfterOrder.SaleAfterOrderDetail;
import com.ibm.oms.client.dto.saleAfterOrder.SaleAfterOrderFromLasaDto;
import com.ibm.oms.client.dto.saleAfterOrder.SaleAfterOrderInfoData;
import com.ibm.oms.client.dto.saleAfterOrder.SaleAfterOrderInfoDto;
import com.ibm.oms.client.dto.saleAfterOrder.SaleAfterOrderLogisticsDto;
import com.ibm.oms.client.dto.saleAfterOrder.ShopSaleAfterOrderQueryDto;

/**
 * Description: 退换货订单 调用接口
 * @author lh
 * Date:   2018年2月6日 
 */
public interface ReturnOrderFrontService {
	/**
	 * 生成退货单，调用方：前端
	 */
	public MessageDto createReturnOrder(SaleAfterOrderFromLasaDto dto);
	
	/**
	 * 生成换货单，调用方：前端
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
	 * 退换货单状态历史记录信息，调用方：前端
	 * 
	 * @param dto
	 * @return
	 */
	public SaleAfterOrderInfoData findOrderStatusProcessByOrderNo(ShopSaleAfterOrderQueryDto dto);
	
	/**
	 * 退换货信息查询，调用方：前端
	 * 
	 * @param dto
	 * @return
	 */
	public SaleAfterOrderInfoDto findSaleAfterOrderInfoByOrderNo(ShopSaleAfterOrderQueryDto dto);
	
	/**
	 * 售后详情，调用方：前端导购APP
	 * 
	 * @param orderNo 售后单号
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
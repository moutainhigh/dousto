package com.ibm.oms.client.intf;

import com.ibm.oms.client.dto.saleAfterOrder.MessageDto;
import com.ibm.oms.client.dto.saleAfterOrder.ReturnHeaderOrderList;
import com.ibm.oms.client.dto.saleAfterOrder.SaleAfterOrderFromLasaDto;
import com.ibm.oms.client.dto.saleAfterOrder.ShopSaleAfterOrderQueryDto;

/**
 * Description: 退换货订单 调用接口
 * @author lh
 * Date:   2018年2月6日 
 */
public interface ReturnOrderInService {
    
    /**
     * 接收退货信息,pos
     * 
     * @param dto
     * @return
     */
    public MessageDto receiveReturnOrderFromPos(SaleAfterOrderFromLasaDto dto);
    
    /**
     * 接收换货信息,pos
     * 
     * @param dto
     * @return
     */
    public MessageDto receiveChangeOrderFromPos(SaleAfterOrderFromLasaDto dto);
    
    /**
     * 退货单列表查询,pos
     * 
     * @param dto
     * @return
     */
    public ReturnHeaderOrderList querySaleAfterOrderList(ShopSaleAfterOrderQueryDto dto);
    
    /**
     * 退货单详细查询,pos
     * 
     * @param dto
     * @return SaleAfterOrderFromLasaDto
     */
    public SaleAfterOrderFromLasaDto querySaleAfterOrderDetail(ShopSaleAfterOrderQueryDto dto);
    
    /**
     * 退货单数查询
     * 
     * @param dto
     * @return
     */
    public SaleAfterOrderFromLasaDto queryShopSaleAfterOrderNum(ShopSaleAfterOrderQueryDto dto);
    
    /**
     * 退\换货状态调整接口
     * 
     * @param returnNo 退\换货单号
     * @param returnStatus 状态
     * @return
     */
    public MessageDto updateSaleAfterOrderStatus(String returnNo,String returnStatus);
    
    /**
     * WMS返回质检状态
     *//*
    public void receiveWMSCheckStatus();
    
    *//**
     * 发送是否可以退款状态
     *//*
    public void sendReturnMoneyStatus();
    
    *//**
     * 退换货订单发送到SAP
     *//*
    public void sendReturnOrderToSAP();
    
    *//**
     * 退换货订单发送到WMS
     *//*
    public void sendReturnOrderToWMS();*/

}
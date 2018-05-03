package com.ibm.oms.service.business.saleAfter;

import java.text.ParseException;
import java.util.List;

import com.ibm.oms.client.dto.saleAfterOrder.ReturnHeaderOrderFromLasaDto;
import com.ibm.oms.client.dto.saleAfterOrder.ReturnItemOrderFromLasaDto;
import com.ibm.oms.client.dto.saleAfterOrder.SaleAfterOrderDetail;
import com.ibm.oms.client.dto.saleAfterOrder.SaleAfterOrderInfoDto;
import com.ibm.oms.client.dto.saleAfterOrder.ShopSaleAfterOrderQueryDto;
import com.ibm.sc.bean.Pager;

/**
 *退换货数据 查询 服务 
 *
 *
 *
 */
public interface SaleAfterService{

	/**
     * 退货单列表查询
     * 
     * @param dto
     * @param pager
     * @return 分页
     */
    public Pager findSaleAfterOrderPage(ShopSaleAfterOrderQueryDto dto) throws ParseException;
    
    /**
     * 退货单列表查询
     * 
     * @param dto
     * @return list
     */
    public List<ReturnHeaderOrderFromLasaDto> findSaleAfterOrder(ShopSaleAfterOrderQueryDto dto) throws ParseException;
    
    /**
     * 退货单详细查询
     * 
     * @param orderNo
     * @return
     */
    public List<ReturnItemOrderFromLasaDto> findSaleAfterOrderDetail(String orderNo);
    
    /**
     * 退货单数查询
     * 
     * @param dto
     * @return
     */
    public int countSaleAfterOrder(ShopSaleAfterOrderQueryDto dto);
    
    /**
     * 退款历史记录信息 (退换货单状态流转日志)
     * 
     * @param orderNo
     * @return
     */
    public List<SaleAfterOrderInfoDto> findByOrderNo(String orderNo);
    
    /**
     * 退款信息查询
     * 
     * @param dto
     * @return
     */
    public SaleAfterOrderInfoDto findByOrderItemNo(ShopSaleAfterOrderQueryDto dto);
    
    /**
     * 售后详情
     * 
     * @param orderNo
     * @return
     */
    public SaleAfterOrderDetail findSaleAfterDetailInfo(String orderNo);
}
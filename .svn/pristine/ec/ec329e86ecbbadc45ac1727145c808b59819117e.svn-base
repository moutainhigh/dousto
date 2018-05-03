package com.ibm.oms.dao.saleAfterOrder;

import java.text.ParseException;
import java.util.List;

import com.ibm.oms.domain.persist.OrderSearch;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.dao.BaseDao;
import com.ibm.oms.client.dto.saleAfterOrder.SaleAfterOrderFromLasaDto;
import com.ibm.oms.client.dto.saleAfterOrder.ShopSaleAfterOrderQueryDto;

/**
 * 退换货单信息查询
 * 
 * @author LiHan
 *
 */
public interface SaleAfterOrderDao extends BaseDao<OrderSearch, Long> {
	/**
	 * 退货单数查询
	 * 
	 * @param dto
	 * @return
	 */
	public int countSaleAfterOrder(ShopSaleAfterOrderQueryDto dto);
	
	/**
	 * 退货单列表查询
	 * 
	 * @param dto
	 * @param pager
	 * @return
	 */
	public Pager<?> findSaleAfterOrder(ShopSaleAfterOrderQueryDto dto, Pager<?> pager) throws ParseException;
	
	/**
	 * 退货单列表查询
	 * 
	 * @param dto
	 * @return
	 */
	public List<OrderSearch> findSaleAfterOrder(ShopSaleAfterOrderQueryDto dto) throws ParseException;	
	
	/**
	 * 退货单详细查询
	 * 
	 * @param retOrderNo
	 * @return
	 */
	public SaleAfterOrderFromLasaDto findSaleAfterOrderDetail(String retOrderNo);
}
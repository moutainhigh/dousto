package com.ibm.oms.service.business.saleAfter;

import java.util.List;

import com.ibm.oms.intf.intf.CommodityStockInfoDTO;

/**
 *退换货数据 其他 服务 
 *
 *
 *
 */
public interface SaleAfterOtherService{

	/**
	 * 获取库存信息，调用库存接口
	 * 
	 * @param skuNo
	 * @return
	 */
	public List<CommodityStockInfoDTO> getStockInfoBySkuNo(String skuNo);
    


}
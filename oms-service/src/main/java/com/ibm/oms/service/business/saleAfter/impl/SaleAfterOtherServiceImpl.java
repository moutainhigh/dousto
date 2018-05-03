package com.ibm.oms.service.business.saleAfter.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beans.stock.SkuStockInfoOms;
import com.beans.stock.SkuStockInfoOmsColorSizeInfo;
import com.beans.stock.constant.ClientConstant;
import com.ibm.oms.intf.intf.ColorSizeInfo;
import com.ibm.oms.intf.intf.CommodityStockInfoDTO;
import com.ibm.oms.service.business.saleAfter.SaleAfterOtherService;
import com.ibm.stock.intf.SkuStockSearchService;

/**
 * 退换货数据 其他 服务 
 * 
 * 
 */
@Service("saleAfterOtherService")
public class SaleAfterOtherServiceImpl implements SaleAfterOtherService {
	
	@Autowired 
	SkuStockSearchService skuStockSearchService;

	public List<CommodityStockInfoDTO> getStockInfoBySkuNo(String skuNo){
        // 获取库存信息
        SkuStockInfoOms skuStockInfoOms = skuStockSearchService.getStockBySkuAndShopCode(skuNo,ClientConstant.ESTORE_WAREHOUSE_CODE);
        CommodityStockInfoDTO commodityStockInfo = new CommodityStockInfoDTO();
        commodityStockInfo.setSkuCode(skuStockInfoOms.getSkuCode());
        commodityStockInfo.setPartNumber(skuStockInfoOms.getPartNumber());
        commodityStockInfo.setBarCode(skuStockInfoOms.getBarCode());
        commodityStockInfo.setSkuStockNum(String.valueOf(skuStockInfoOms.getSkuStockNum()));
        
        List<ColorSizeInfo> colorSize = new ArrayList<ColorSizeInfo>();
        List<SkuStockInfoOmsColorSizeInfo> colorSizeInfoList = skuStockInfoOms.getColorSizeInfos();
        if(colorSizeInfoList != null && colorSizeInfoList.size() > 0){
        	for(SkuStockInfoOmsColorSizeInfo info : colorSizeInfoList){
        		ColorSizeInfo colorSizeInfo = new ColorSizeInfo();
        		colorSizeInfo.setColorSizeName(info.getColorSizeName());
        		colorSizeInfo.setColorSizeValueName(info.getColorSizeValueName());
        		colorSize.add(colorSizeInfo);
        	}
        	commodityStockInfo.setColorSizeInfos(colorSize);
        }                
        
        List<CommodityStockInfoDTO> commodityStockInfoDTOs = new ArrayList<CommodityStockInfoDTO>();
        commodityStockInfoDTOs.add(commodityStockInfo);
		return commodityStockInfoDTOs;
	}
}
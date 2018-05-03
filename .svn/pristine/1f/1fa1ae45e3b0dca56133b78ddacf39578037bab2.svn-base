package com.ibm.oms.service.business.saleAfter.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beans.stock.SkuStockInfoOms;
import com.beans.stock.SkuStockInfoOmsColorSizeInfo;
import com.beans.stock.SkuStockInfoOmsDetail;
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
		List<CommodityStockInfoDTO> commodityStockInfoDTOs = new ArrayList<CommodityStockInfoDTO>();
		SkuStockInfoOms skuStockInfoOms = skuStockSearchService.getStockBySkuAndShopCode("P2500000215342S3",ClientConstant.ESTORE_WAREHOUSE_CODE);
        if(skuStockInfoOms != null){
        	       
        	List<SkuStockInfoOmsDetail> detailList = skuStockInfoOms.getDetails();
        	if(detailList != null && detailList.size() > 0){
		        for(SkuStockInfoOmsDetail detail : detailList){
		        	CommodityStockInfoDTO commodityStockInfo = new CommodityStockInfoDTO(); 
			        commodityStockInfo.setSkuCode(detail.getSkuCode());
			        commodityStockInfo.setPartNumber(skuStockInfoOms.getPartNumber());
			        commodityStockInfo.setBarCode(detail.getBarCode());
			        commodityStockInfo.setSkuStockNum(String.valueOf(detail.getSkuStockNum()));
			        
			        List<ColorSizeInfo> colorSize = new ArrayList<ColorSizeInfo>();
			        List<SkuStockInfoOmsColorSizeInfo> colorSizeInfoList = detail.getColorSizeInfos();
			        if(colorSizeInfoList != null && colorSizeInfoList.size() > 0){
			        	for(SkuStockInfoOmsColorSizeInfo info : colorSizeInfoList){
			        		ColorSizeInfo colorSizeInfo = new ColorSizeInfo();
			        		colorSizeInfo.setColorSizeName(info.getColorSizeName());
			        		colorSizeInfo.setColorSizeValueName(info.getColorSizeValueName());
			        		colorSize.add(colorSizeInfo);
			        	}
			        	commodityStockInfo.setColorSizeInfos(colorSize);
			        }                
			        commodityStockInfoDTOs.add(commodityStockInfo);
		        }
        	}
        }
		return commodityStockInfoDTOs;
	}
}
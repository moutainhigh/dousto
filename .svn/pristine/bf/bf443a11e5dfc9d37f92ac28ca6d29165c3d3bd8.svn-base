package com.ibm.oms.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.intf.intf.ColorSizeInfo;
import com.ibm.oms.intf.intf.CommodityStockInfoDTO;
import com.ibm.oms.intf.intf.ResultDTO;
import com.ibm.oms.service.ColorSizeService;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.business.ReturnChangeOrderService;

/**
 * 获取色码款信息服务类
 * @author xiaonanxiang
 *
 */
@Service("colorSizeService")
public class ColorSizeServiceImpl implements ColorSizeService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ReturnChangeOrderService returnChangeOrderService;
    @Autowired
    OrderItemService orderItemService;

    @Override
    public ResultDTO getColorSizeInfoBySkuNo(String skuNo) {
        ResultDTO resultDTO = new ResultDTO();
        
        if(null == skuNo)
        {
            resultDTO.setErrorMessage("系统异常：" + skuNo +"为null！");
            return resultDTO;
        }
        
        // 获取库存信息
        List<CommodityStockInfoDTO> commodityStockInfoDTOs = returnChangeOrderService.getStockInfo(skuNo)
                .getCommodityStockInfo();
        if (null == commodityStockInfoDTOs || commodityStockInfoDTOs.size() <= 0) {
            resultDTO.setErrorMessage("系统异常：" + skuNo +"的库存信息为null！");
            return resultDTO;
        }
        List<ColorSizeInfo> colorSizeInfos = new ArrayList<ColorSizeInfo>();
        // 色码款信息
        for(CommodityStockInfoDTO commodityStockInfoDTO:commodityStockInfoDTOs)
        {
            colorSizeInfos.addAll(commodityStockInfoDTO.getColorSizeInfos());
        }
        if (null == colorSizeInfos || colorSizeInfos.size() <= 0) {
            resultDTO.setErrorMessage("系统异常：" + skuNo +"的色码款信息为null！");
            return resultDTO;
        }
        
        
        /*Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (ColorSizeInfo colorSizeInfo : colorSizeInfos) {
            if (null == colorSizeInfo)
                continue;
            String name = colorSizeInfo.getColorSizeName();
            List<String> values = null;
            if (map.containsKey(name)) {
                values = map.get(name);
                values.add(colorSizeInfo.getColorSizeValueName());
            } else {
                values = new ArrayList<String>();
                values.add(colorSizeInfo.getColorSizeValueName());
                map.put(name, values);
            }
        }*/
        resultDTO.setResult(1);
        resultDTO.setResultObj(commodityStockInfoDTOs);
        return resultDTO;
    }
    
    private List<ColorSizeInfo> getColorSizeInfos()
    {
        List<ColorSizeInfo> colorSizeInfos = new ArrayList<ColorSizeInfo>();
        ColorSizeInfo colorSizeInfo1 = new ColorSizeInfo();
        colorSizeInfo1.setColorSizeName("尺码-女装");
        colorSizeInfo1.setColorSizeValueName("S");
        
        ColorSizeInfo colorSizeInfo2 = new ColorSizeInfo();
        colorSizeInfo2.setColorSizeName("尺码-女装");
        colorSizeInfo2.setColorSizeValueName("M");
        
        /*ColorSizeInfo colorSizeInfo3 = new ColorSizeInfo();
        colorSizeInfo3.setColorSizeName("尺码-男装");
        colorSizeInfo3.setColorSizeValueName("S");
        
        ColorSizeInfo colorSizeInfo4 = new ColorSizeInfo();
        colorSizeInfo4.setColorSizeName("尺码-男装");
        colorSizeInfo4.setColorSizeValueName("M");*/
        
        ColorSizeInfo colorSizeInfo3 = new ColorSizeInfo();
        colorSizeInfo3.setColorSizeName("颜色-女装");
        colorSizeInfo3.setColorSizeValueName("黑色");
        
        ColorSizeInfo colorSizeInfo4 = new ColorSizeInfo();
        colorSizeInfo4.setColorSizeName("颜色-女装");
        colorSizeInfo4.setColorSizeValueName("白色");
        
        /*ColorSizeInfo colorSizeInfo7 = new ColorSizeInfo();
        colorSizeInfo7.setColorSizeName("颜色-男装");
        colorSizeInfo7.setColorSizeValueName("黑色");
        
        ColorSizeInfo colorSizeInfo8 = new ColorSizeInfo();
        colorSizeInfo8.setColorSizeName("颜色-男装");
        colorSizeInfo8.setColorSizeValueName("白色");*/
        
        colorSizeInfos.add(colorSizeInfo1);
        colorSizeInfos.add(colorSizeInfo2);
        colorSizeInfos.add(colorSizeInfo3);
        colorSizeInfos.add(colorSizeInfo4);
        /*colorSizeInfos.add(colorSizeInfo5);
        colorSizeInfos.add(colorSizeInfo6);
        colorSizeInfos.add(colorSizeInfo7);
        colorSizeInfos.add(colorSizeInfo8);*/
        return colorSizeInfos;
    }

}

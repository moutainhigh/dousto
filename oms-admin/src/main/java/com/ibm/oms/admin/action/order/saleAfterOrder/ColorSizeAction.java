package com.ibm.oms.admin.action.order.saleAfterOrder;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.oms.admin.action.order.AbstractOrderAction;
import com.ibm.oms.intf.intf.CommodityStockInfoDTO;
import com.ibm.oms.intf.intf.ResultDTO;
import com.ibm.oms.service.ColorSizeService;

/**
 * 色码款选择
 * @author xiaonanxiang
 *
 */
@ParentPackage("admin")
@SuppressWarnings("all")
public class ColorSizeAction extends AbstractOrderAction {

    private static final long serialVersionUID = 1L;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ColorSizeService colorSizeService;

    List<CommodityStockInfoDTO> commodityStockInfoDTOs = new ArrayList<CommodityStockInfoDTO>();

    public String execute() throws Exception {
        //String orderItemId = this.getParameter("orderItemId");
        // skuCode
        String skuNo = this.getParameter("skuNo");

        StringBuffer sb = new StringBuffer();
        // 获取色码款信息
        ResultDTO resultDTO = colorSizeService.getColorSizeInfoBySkuNo(skuNo);
        if (resultDTO.getResult() != 1) {
            sb.append(resultDTO.getResultMessage());
            return ajaxJsonSuccessMessage(sb.toString());
        }


        commodityStockInfoDTOs = (List<CommodityStockInfoDTO>) resultDTO.getResultObj();
        System.out.println(JSONArray.fromObject(commodityStockInfoDTOs));
        sb.append(JSONArray.fromObject(commodityStockInfoDTOs));

        return ajaxJsonSuccessMessage(sb.toString());
    }



    public List<CommodityStockInfoDTO> getCommodityStockInfoDTOs() {
        return commodityStockInfoDTOs;
    }

    public void setCommodityStockInfoDTOs(List<CommodityStockInfoDTO> commodityStockInfoDTOs) {
        this.commodityStockInfoDTOs = commodityStockInfoDTOs;
    }

}
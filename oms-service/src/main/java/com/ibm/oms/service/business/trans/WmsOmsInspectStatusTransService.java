package com.ibm.oms.service.business.trans;

import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.intf.intf.WmsOmsReceiveInspectionResultDTO;

/**
 * @author liucy
 * 
 */
public interface WmsOmsInspectStatusTransService {

    /**
     * 入库单质检
     */
    public final String WMS_SaleAfterOrder_Inspection = "I";
    /**
     * 入库单入库
     */
    public final String WMS_SaleAfterOrder_Storage = "S";
    
    /**
     * 状态：成功
     */
    public final String WMS_SaleAfterOrder_Success = "S";
    /**
     * 状态：失败
     */
    public final String WMS_SaleAfterOrder_Fail = "F";

    /**
     * 
     * 功能描述: WMS的质检信息和入库信息上传至OMS
     * 
     * @param wmsOmsReceiveInspectionResultDTO
     * @return CommonOutputDTO
     */
    public CommonOutputDTO updateOrderInspectStatus(WmsOmsReceiveInspectionResultDTO wmsOmsReceiveInspectionResultDTO);
}

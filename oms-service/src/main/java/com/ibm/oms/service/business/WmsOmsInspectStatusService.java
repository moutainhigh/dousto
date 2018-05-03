package com.ibm.oms.service.business;

import java.util.List;

import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.intf.intf.ResponseObjectDTO;
import com.ibm.oms.intf.intf.WmsOmsReceiveInspectionResultDTO;

/**
 * @author liucy
 * 
 */
@Deprecated
public interface WmsOmsInspectStatusService {

	/**
	 * 
	 * 功能描述: WMS的质检信息和入库信息上传至OMS
	 * 
	 * @param wmsOmsReceiveInspectionResultDTO
	 * @return CommonOutputDTO
	 */
	 @Deprecated
	 CommonOutputDTO handlerUpdateOrderInspectStatus(WmsOmsReceiveInspectionResultDTO wmsOmsReceiveInspectionResultDTO);
	 @Deprecated
	 public  ResponseObjectDTO handlerUpdateOrderInspectStatus(List<WmsOmsReceiveInspectionResultDTO> receive);
	/**
	 * 
	 * 功能描述: WMS的质检信息和入库信息上传至OMS
	 * 
	 * @param wmsOmsReceiveInspectionResultDTO
	 * @return CommonOutputDTO
	 */
	/*public CommonOutputDTO updateOrderInspectStatus(WmsOmsReceiveInspectionResultDTO wmsOmsReceiveInspectionResultDTO);*/
}

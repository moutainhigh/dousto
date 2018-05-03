package com.ibm.oms.service.business;

import java.util.List;

import com.ibm.oms.domain.persist.HangOrderMain;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderOutputDTO;
import com.ibm.oms.intf.intf.HangOrderReceiveOrderDTO;
import com.ibm.oms.intf.intf.inner.HangOrderMainDTO;

/**
 * @author wangqc
 * @date 2018年2月6日 上午9:50:13
 * 
 */
public interface HangOrderCreateService {
	//挂单创建
	BtcOmsReceiveOrderOutputDTO createHangOrder(HangOrderReceiveOrderDTO hangOrderReceiveOrderDTO);
	//挂单列表查询
	List<HangOrderMain> queryHangOrderMain(String shopNo,String startDate,String endDate,String status);
	//挂单列表详情查询
	List<HangOrderMain> queryHangOrderMainDetail(String orderNo);
	//挂单删除
	BtcOmsReceiveOrderOutputDTO deleteHangOrderByOrderNo(String orderNO);
	//挂单修改
	BtcOmsReceiveOrderOutputDTO updateHangOrder(HangOrderReceiveOrderDTO hangOrderReceiveOrderDTO);
     
}

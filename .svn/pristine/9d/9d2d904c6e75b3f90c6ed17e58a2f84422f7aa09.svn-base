package com.ibm.oms.service;

import java.util.List;

import com.ibm.oms.domain.persist.HangOrderMain;
import com.ibm.oms.intf.intf.inner.HangOrderMainDTO;
import com.ibm.sc.service.BaseService;

/**
 * @author wangqc
 * @date 2018年2月5日 下午2:54:58
 * 
 */
public interface HangOrderMainService extends BaseService<HangOrderMain, Long>{

	//查询挂单头
	List<HangOrderMain> queryHangOrderMain(String shopNo, String startDate, String endDate, String status);
    //查询挂单详情信息
	List<HangOrderMain> queryHangOrderMainDetail(String orderNo);
	//删除挂单
	int deleteHangOrderByOrderNo(String orderNo);
	//修改挂单
	void updateHangOrder(HangOrderMain hangOrderMain);
	//根据OrderNo查找Id
	long queryIdByOrderNo(String orderNo);
	
}

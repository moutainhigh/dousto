package com.ibm.oms.dao.intf;

import java.util.List;

import com.ibm.oms.domain.persist.HangOrderMain;
import com.ibm.sc.dao.BaseDao;
/**
 * @author wangqc
 * @date 2018年2月7日 下午2:21:14
 * 
 */
public interface HangOrderMainDao extends BaseDao<HangOrderMain,Long>{

	List<HangOrderMain> queryHangOrderMain(String shopNo, String startDate, String endDate, String status);

	List<HangOrderMain> queryHangOrderMainDetail(String orderNo);

	void deleteHangOrderByOrderNo(String orderNo);

	void updateHangOrder(HangOrderMain hangOrderMain);

	long queryIdByOrderNo(String orderNo);
	
}

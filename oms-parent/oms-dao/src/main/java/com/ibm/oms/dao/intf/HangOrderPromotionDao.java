package com.ibm.oms.dao.intf;

import com.ibm.oms.domain.persist.HangOrderPromotion;
import com.ibm.sc.dao.BaseDao;
/**
 * @author wangqc
 * @date 2018年2月7日 下午2:21:14
 * 
 */
public interface HangOrderPromotionDao extends BaseDao<HangOrderPromotion,Long>{

	void deleteByIdOrder(long orderId);
	
}

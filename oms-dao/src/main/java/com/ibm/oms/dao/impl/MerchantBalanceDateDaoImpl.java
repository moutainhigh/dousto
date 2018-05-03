package com.ibm.oms.dao.impl;

import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.MerchantBalanceDateDao;
import com.ibm.oms.domain.persist.MerchantBalanceDate;
import com.ibm.sc.dao.impl.BaseDaoImpl;

/**
 * Description: //模块目的、功能描述  
 * @author YanYanZhang
 * Date:   2018年3月8日 
 */
@Repository("merchantBalanceDateDao")
public class MerchantBalanceDateDaoImpl extends BaseDaoImpl<MerchantBalanceDate, Integer> implements MerchantBalanceDateDao{
}

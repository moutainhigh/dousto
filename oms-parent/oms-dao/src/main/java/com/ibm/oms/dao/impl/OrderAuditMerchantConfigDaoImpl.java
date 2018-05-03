package com.ibm.oms.dao.impl;

import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.OrderAuditMerchantConfigDao;
import com.ibm.oms.domain.persist.OrderAuditMerchantConfig;
import com.ibm.sc.dao.impl.BaseDaoImpl;

/**
 * Description: 订单店铺自动审核配置
 * @author YanYanZhang
 * Date:   2018年2月1日 
 */
@Repository("orderAuditMerchantConfigDao")
public class OrderAuditMerchantConfigDaoImpl extends BaseDaoImpl<OrderAuditMerchantConfig, Integer> implements OrderAuditMerchantConfigDao{
}

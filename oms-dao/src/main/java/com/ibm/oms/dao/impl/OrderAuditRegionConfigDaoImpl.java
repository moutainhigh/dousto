package com.ibm.oms.dao.impl;

import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.OrderAuditRegionConfigDao;
import com.ibm.oms.domain.persist.OrderAuditRegionConfig;
import com.ibm.sc.dao.impl.BaseDaoImpl;

/**
 * Description: 自动审核配置-审核地区
 * @author YanYanZhang
 * Date:   2018年2月1日 
 */
@Repository("orderAuditRegionConfigDaoImpl")
public class OrderAuditRegionConfigDaoImpl extends BaseDaoImpl<OrderAuditRegionConfig, Integer> implements OrderAuditRegionConfigDao{
}

package com.ibm.oms.dao.impl;

import com.ibm.oms.dao.intf.OrderStatusSyncLogDao;
import com.ibm.oms.domain.persist.OrderStatusSyncLog;
import com.ibm.oms.domain.persist.OrderStatusSyncLog_;
import com.ibm.sc.dao.impl.BaseDaoImpl;
import com.ibm.sc.util.DateUtils;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
/**
 * DAOģʽ,
 * 
 * Creation date:2014-03-14 04:20:52
 * @author:Yong Hong Luo
 */
@Repository("orderStatusSyncLogDao")
public class OrderStatusSyncLogDaoImpl extends BaseDaoImpl<OrderStatusSyncLog,Long> implements OrderStatusSyncLogDao{
     
	public void update(Collection<OrderStatusSyncLog> objs){
		super.update(objs);
	}


    /**
     * 通过指定条件获取OrderStatusSyncLog
     *
     * @param syncScene 同步场景
     * @param strStartDate log生成时间
     * @param strEndDate　log生成时间
     * @param orderNo 订单号
     * @param syncFlag 同步状态
     * @param size 记录数
     * @return
     */
    @Override
    public List<OrderStatusSyncLog> getPager(String syncScene, String strStartDate, String strEndDate, String orderNo, String syncFlag, Integer size) {
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<OrderStatusSyncLog> c = cb.createQuery(OrderStatusSyncLog.class);
        Root<OrderStatusSyncLog> root = c.from(OrderStatusSyncLog.class);
        c.select(root);
        List<Predicate> predicates = new ArrayList<Predicate>();

        if(!StringUtils.isBlank((syncScene))){
            Predicate equal = cb.equal(root.get(OrderStatusSyncLog_.syncScene), syncScene);//订单号
            predicates.add(equal);
        }

        if(!StringUtils.isBlank((strStartDate))){
            try {
                Date startDate = DateUtils.convertStringToGeneralDateTime(strStartDate+" 00:00:00");
            
                Predicate equal = cb.greaterThanOrEqualTo(root.get(OrderStatusSyncLog_.dateCreated),startDate);
                 predicates.add(equal);
            } catch (ParseException e) {
                
            }
        }
        
        if(!StringUtils.isBlank((strEndDate))){
            try {
                Date endDate = DateUtils.convertStringToGeneralDateTime(strEndDate+" 23:59:59");
            
                Predicate equal = cb.lessThanOrEqualTo(root.get(OrderStatusSyncLog_.dateCreated),endDate);
                predicates.add(equal);
            } catch (ParseException e) {
                
            }
        }

        //指定订单号
        if(StringUtils.isNotBlank(orderNo)){
            Predicate predicate = cb.equal(root.get(OrderStatusSyncLog_.orderNo), orderNo); //订单号
            predicates.add(predicate);
        }

        //指定同步状态
        if(StringUtils.isNotBlank(syncFlag)){
            Predicate predicate = cb.equal(root.get(OrderStatusSyncLog_.syncFlag), syncFlag); //同步状态
            predicates.add(predicate);
        }

        /*Predicate syncFlag = cb.equal(root.get(OrderStatusSyncLog_.syncFlag), "N");
        predicates.add(syncFlag);*/
        c.where(predicates.toArray(new Predicate[0]));
        List<OrderStatusSyncLog> totalRet = super.findByCriteria(c);
        if (totalRet != null && size !=null && totalRet.size() > size.intValue()) {
            return totalRet.subList(0, size.intValue());
        }
        return totalRet;
    }
    
}

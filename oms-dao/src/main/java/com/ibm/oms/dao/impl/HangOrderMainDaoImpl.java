package com.ibm.oms.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.HangOrderMainDao;
import com.ibm.oms.domain.persist.HangOrderMain;
import com.ibm.oms.domain.persist.HangOrderMain_;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderMain_;
import com.ibm.sc.bean.Pageable;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.dao.impl.BaseDaoImpl;

/**
 * @author wangqc
 * @date 2018年2月7日 下午2:31:18
 * 
 */
@Repository
public class HangOrderMainDaoImpl extends BaseDaoImpl<HangOrderMain, Long> implements HangOrderMainDao {
	

	@Override
	public List<HangOrderMain> queryHangOrderMain(String shopNo, String startDate, String endDate, String status) {
	 	CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<HangOrderMain> c = cb.createQuery(HangOrderMain.class);
        Root<HangOrderMain> rootMain = c.from(HangOrderMain.class);
        c.select(rootMain);
        Path<Date> ot = rootMain.<Date> get(HangOrderMain_.orderTime);
        
        List<Predicate> predicates=new ArrayList<Predicate>();
        		
        Date dateStart = this.strToDate(startDate, "yyyy-MM-dd HH:mm:ss");
        Date Dateend = this.strToDate(endDate, "yyyy-MM-dd HH:mm:ss");
        
        predicates.add(cb.between(ot, dateStart, Dateend));
        
        if(shopNo != null && shopNo != ""){
        	predicates.add(cb.equal(rootMain.get(HangOrderMain_.merchantNo), shopNo));// 门店ID
        }
       
        if(status != null && !"".equals(status)){
            predicates.add(cb.equal(rootMain.get(HangOrderMain_.isSuspension), status));// 订单状态
        }
        
        c.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        
        List<HangOrderMain> ret = findByCriteria(c);
		return ret;
	}
	
	@Override
	public List<HangOrderMain> queryHangOrderMainDetail(String orderNo) {
		CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<HangOrderMain> c = cb.createQuery(HangOrderMain.class);
        Root<HangOrderMain> rootMain = c.from(HangOrderMain.class);
        c.select(rootMain);
        
        List<Predicate> predicates=new ArrayList<Predicate>();
        		
        predicates.add(cb.equal(rootMain.get(HangOrderMain_.orderNo), orderNo));
        
        c.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        
        List<HangOrderMain> ret = findByCriteria(c);
		return ret;
	}
	
	@Override
	public void deleteHangOrderByOrderNo(String orderNo) {
		CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<HangOrderMain> c = cb.createQuery(HangOrderMain.class);
        Root<HangOrderMain> rootMain = c.from(HangOrderMain.class);
        c.select(rootMain);
        
        List<Predicate> predicates=new ArrayList<Predicate>();
		
        predicates.add(cb.equal(rootMain.get(HangOrderMain_.orderNo), orderNo));
        
        c.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        
        List<HangOrderMain> ret = findByCriteria(c);
        
		String mainId = ret.get(0).getId().toString();
		System.out.println("mainId="+mainId);
		
		
		//删除挂单促销实体
		String sql1 = "DELETE FROM temp_order_promotion WHERE ID_ORDER = ?";
		SQLQuery query1 = getSession().createSQLQuery(sql1);
		query1.setParameter(0,mainId);
		query1.executeUpdate();
		
		//删除挂单支付
		String sql2 = "DELETE FROM temp_order_pay WHERE ID_ORDER = ?";
		SQLQuery query2 = getSession().createSQLQuery(sql2);
		query2.setParameter(0,mainId);
		query2.executeUpdate();
		
		//删除发票
		String sql3 = "DELETE FROM temp_order_invoice WHERE ID_ORDER = ?";
		SQLQuery query3 = getSession().createSQLQuery(sql3);
		query3.setParameter(0,mainId);
		query3.executeUpdate();
		
		//删除挂单行
		String sql4 = "DELETE FROM temp_order_item WHERE ID_ORDER = ?";
		SQLQuery query4 = getSession().createSQLQuery(sql4);
		query4.setParameter(0,mainId);
		query4.executeUpdate();		

		//删除子挂单
		String sql5 = "DELETE FROM temp_order_sub WHERE ID_ORDER = ?";
		SQLQuery query5 = getSession().createSQLQuery(sql5);
		query5.setParameter(0,mainId);
		query5.executeUpdate();	
		
		//删除主挂单
		String sql6 = "DELETE FROM temp_order_main WHERE ID = ?";
		SQLQuery query6 = getSession().createSQLQuery(sql6);
		query6.setParameter(0,mainId);
		query6.executeUpdate();
		
	}
	
	@Override
	public void updateHangOrder(HangOrderMain hom) {
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE temp_order_main ");
		sb.append("SET ORDER_NO = ? , ");
		sb.append("ALIAS_ORDER_NO = ? ,");
		sb.append("ORDER_SOURCE = ? ,");
		sb.append("ORDER_TYPE = ? ,");
		sb.append("IS_SUSPENSION = ? ,");
		sb.append("MEMBER_NO = ? ,");
		sb.append("CUSTOMER_NAME = ? ,");
		sb.append("CUSTOMER_PHONE = ? ,");
		sb.append("TOTAL_PRODUCT_PRICE = ? ,");
		sb.append("DISCOUNT_TOTAL = ? ,");
		sb.append("MERCHANT_NO = ? ,");
		sb.append("MERCHANT_TYPE = ? ,");
		sb.append("TOTAL_GIVE_POINTS = ? ,");
		sb.append("NEED_INVOICE = ? ,");
		sb.append("ORDER_TIME = ? ,");
		sb.append("IP = ? ,");
		sb.append("BILL_TYPE = ? ,");
		sb.append("DATE_UPDATED = ? , ");
		sb.append("TOTAL_PAY_AMOUNT = ? , ");
		sb.append("TOTAL_PROMO = ? ");
		sb.append("WHERE ORDER_NO= ?");

		SQLQuery query1 = getSession().createSQLQuery(sb.toString());
		query1.setParameter(0,hom.getOrderNo());
		query1.setParameter(1,hom.getAliasOrderNo());
		query1.setParameter(2,hom.getOrderSource());
		query1.setParameter(3,hom.getOrderType());
		query1.setParameter(4,hom.getIsSuspension());
		query1.setParameter(5,hom.getMemberNo());
		query1.setParameter(6,hom.getCustomerName());
		query1.setParameter(7,hom.getCustomerPhone());
		query1.setParameter(8,hom.getTotalProductPrice());
		query1.setParameter(9,hom.getDiscountTotal());
		query1.setParameter(10,hom.getMerchantNo());
		query1.setParameter(11,hom.getMerchantType());
		query1.setParameter(12,hom.getTotalGivePoints());
		query1.setParameter(13,hom.getNeedInvoice());
		query1.setParameter(14,hom.getOrderTime());
		query1.setParameter(15,hom.getIp());
		query1.setParameter(16,hom.getBillType());
		query1.setParameter(17,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		query1.setParameter(18,hom.getTotalPayAmount());
		query1.setParameter(19,hom.getTotalPromo());
		query1.setParameter(20,hom.getOrderNo());
		query1.executeUpdate();
	}
	
	
	private static Date strToDate(String str, String format) {
        if (str == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            return null;
        }
       
    }

	@Override
	public long queryIdByOrderNo(String orderNo) {
		String sql = "SELECT id FROM temp_order_main WHERE ORDER_NO = ?";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.setParameter(0,orderNo);
		List list = query.list();
		long count = 0;
		for(int i = 0 ; i<list.size();i++){
			count = Long.parseLong(list.get(i).toString());
		}
		return count;
	}

	

	

	

	
}

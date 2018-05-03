package com.ibm.oms.dao.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.constant.Global;
import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.dao.intf.OrderMainDao;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderMain_;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.dao.impl.BaseDaoImpl;
import com.ibm.sc.util.DateUtils;

/**
 * DAOģʽ,
 * 
 * Creation date:2014-03-14 04:20:47
 * 
 * @author:Yong Hong Luo
 */
@Repository("orderMainDao")
public class OrderMainDaoImpl extends BaseDaoImpl<OrderMain, Long> implements OrderMainDao {

	public int updateWithSql() {
		String ql = "update OrderMain set confirmerName=? where orderId = ?";
		Query query = getEntityManager().createQuery(ql);
		query.setParameter(1, "fff");
		query.setParameter(2, "111");
		return query.executeUpdate();
	}

	@Override
	public List<OrderMain> getBackOrderBySaleNo(Long saleNo, String orderCategory) {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<OrderMain> c = cb.createQuery(OrderMain.class);
		Root<OrderMain> root = c.from(OrderMain.class);
		c.select(root);
		if (saleNo != Global.ALL_INT) {
			Predicate equal01 = cb.equal(root.get(OrderMain_.orderRelatedOrigin), saleNo);// 关联订单id
			c.where(equal01);
		}
		if (StringUtils.isNotEmpty(orderCategory) && !orderCategory.equals(Global.ALL_STRING)) {
			Predicate equal02 = cb.equal(root.get(OrderMain_.orderCategory), orderCategory);// 逆向订单类型：退货
			c.where(equal02);
		}
		return super.findByCriteria(c);
	}

	@Override
	public List<OrderMain> getOMListByTypeAndPeriod(SingularAttribute<OrderMain, String> type, String value, Long startTime) {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<OrderMain> qDef = cb.createQuery(OrderMain.class);
		Root<OrderMain> rootMain = qDef.from(OrderMain.class);
		qDef.select(rootMain);
		Path<Date> ot = rootMain.<Date>get(OrderMain_.orderTime);
		Predicate pMemberNo = cb.equal(rootMain.get(type), value);
		Date dateStart = new Date(startTime);
		Date dateEnd = new Date();
		Predicate period = cb.between(ot, dateStart, dateEnd);
		Predicate finalCondition = cb.and(pMemberNo, period);
		qDef.where(finalCondition);
		List<OrderMain> ret = findByCriteria(qDef);
		// List<OrderItem> retItem = ret.get(0).getOrderItems();
		// for(OrderItem oi:retItem){
		// System.out.println(oi.getOrderItemNo());
		// }
		return ret;
	}

	@Override
	public List<OrderMain> getOMListByBatchNumAndSource(String batchNum, String orderSource) {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<OrderMain> qDef = cb.createQuery(OrderMain.class);
		Root<OrderMain> rootMain = qDef.from(OrderMain.class);
		qDef.select(rootMain);
		Predicate pBatchNum = cb.equal(rootMain.get(OrderMain_.batchNum), batchNum);
		Predicate pOrderSource = cb.equal(rootMain.get(OrderMain_.orderSource), orderSource);
		Predicate finalCondition = cb.and(pBatchNum, pOrderSource);
		qDef.where(finalCondition);
		List<OrderMain> ret = findByCriteria(qDef);
		return ret;
	}

	@Override
	public OrderMain getByOrderItemId(Long orderItemId) {
		// TODO Auto-generated method stub
		return null;
	}

	public OrderMain findByOrderNo(String orderNo) {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<OrderMain> c = cb.createQuery(OrderMain.class);
		Root<OrderMain> root = c.from(OrderMain.class);
		c.select(root);
		List<Predicate> terms = new ArrayList<Predicate>();
		Predicate equal01 = cb.equal(root.get(OrderMain_.orderNo), orderNo);
		terms.add(equal01);
		c.where(terms.toArray(new Predicate[0]));
		return super.getByCriteria(c);
	}

	public Pager findByOrderMain(OrderMain order, Pager pager, List<String> statusPayOther, List<String> statusTotalOther) {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<OrderMain> c = cb.createQuery(OrderMain.class);
		Root<OrderMain> root = c.from(OrderMain.class);
		c.select(root);
		List<Predicate> terms = new ArrayList<Predicate>();
		if (isNotAllString(order.getOrderNo())) {
			Predicate equal = cb.equal(root.get(OrderMain_.orderNo), order.getOrderNo());// 订单号
			terms.add(equal);
		}
		if (isNotAllString(order.getAliasOrderNo())) {
			Predicate equal = cb.equal(root.get(OrderMain_.aliasOrderNo), order.getAliasOrderNo());//
			terms.add(equal);
		}
		if (isNotAllString(order.getOrderCategory())) {
			Predicate equal = cb.equal(root.get(OrderMain_.orderCategory), order.getOrderCategory());//
			terms.add(equal);
		}
		if (isNotAllString(order.getOrderSource())) {
			Predicate equal = cb.equal(root.get(OrderMain_.orderSource), order.getOrderSource());//
			terms.add(equal);
		}
		if (isNotAllString(order.getOrderType())) {
			Predicate equal = cb.equal(root.get(OrderMain_.orderType), order.getOrderType());//
			terms.add(equal);
		}
		if (isNotAllString(order.getMerchantType())) {
			Predicate equal = cb.equal(root.get(OrderMain_.merchantType), order.getMerchantType());//
			terms.add(equal);
		}
		if (isNotAllString(order.getMerchantNo())) {
			Predicate equal = cb.equal(root.get(OrderMain_.merchantNo), order.getMerchantNo());//
			terms.add(equal);
		}
		if (isNotAllInt(order.getIfShowPrice())) {
			Predicate equal = cb.equal(root.get(OrderMain_.ifShowPrice), order.getIfShowPrice());//
			terms.add(equal);
		}
		if (isNotAllInt(order.getNeedConfirm())) {
			Predicate equal = cb.equal(root.get(OrderMain_.needConfirm), order.getNeedConfirm());//
			terms.add(equal);
		}
		if (isNotAllString(order.getFinishUserNo())) {
			Predicate equal = cb.equal(root.get(OrderMain_.finishUserNo), order.getFinishUserNo());//
			terms.add(equal);
		}
		if (isNotAllString(order.getMemberNo())) {
			Predicate equal = cb.equal(root.get(OrderMain_.memberNo), order.getMemberNo());//
			terms.add(equal);
		}
		if (isNotAllString(order.getCustomerName())) {
			Predicate equal = cb.equal(root.get(OrderMain_.customerName), order.getCustomerName());//
			terms.add(equal);
		}
		if (isNotAllString(order.getCustomerPhone())) {
			Predicate equal = cb.equal(root.get(OrderMain_.customerPhone), order.getCustomerPhone());//
			terms.add(equal);
		}
		if (isNotAllString(order.getReceiveAreaId())) {
			Predicate equal = cb.equal(root.get(OrderMain_.receiveAreaId), order.getReceiveAreaId());//
			terms.add(equal);
		}
		if (isNotAllInt(order.getNeedInvoice())) {
			Predicate equal = cb.equal(root.get(OrderMain_.needInvoice), order.getNeedInvoice());//
			terms.add(equal);
		}
		if (isNotAllString(order.getConfirmerNo())) {
			Predicate equal = cb.equal(root.get(OrderMain_.confirmerNo), order.getConfirmerNo());//
			terms.add(equal);
		}
		if (isNotAllString(order.getConfirmerName())) {
			Predicate equal = cb.equal(root.get(OrderMain_.confirmerName), order.getConfirmerName());//
			terms.add(equal);
		}
		if (isNotAllString(order.getStatusConfirm())) {
			Predicate equal = cb.equal(root.get(OrderMain_.statusConfirm), order.getStatusConfirm());//
			terms.add(equal);
		}
		if (isNotAllString(order.getStatusPay())) {
			Predicate equal = cb.equal(root.get(OrderMain_.statusPay), order.getStatusPay());//
			if (statusPayOther != null && statusPayOther.size() > 0) {
				Predicate equalTemp = null;
				for (String statusPay : statusPayOther) {
					equalTemp = cb.equal(root.get(OrderMain_.statusPay), statusPay);
					equal = cb.or(equal, equalTemp);
				}
			}
			terms.add(equal);
		}
		if (isNotAllString(order.getStatusTotal())) {
			Predicate equal = cb.equal(root.get(OrderMain_.statusTotal), order.getStatusTotal());//
			if (statusTotalOther != null && statusTotalOther.size() > 0) {
				Predicate equalTemp = null;
				for (String statusTotal : statusTotalOther) {
					equalTemp = cb.equal(root.get(OrderMain_.statusTotal), statusTotal);
					equal = cb.or(equal, equalTemp);
				}
			}
			terms.add(equal);
		}
		if (isNotAllInt(order.getIfPayOnArrival())) {
			Predicate equal = cb.equal(root.get(OrderMain_.ifPayOnArrival), order.getIfPayOnArrival());//
			terms.add(equal);
		}
		if (isNotAllString(order.getPayOnArrivalPayType())) {
			Predicate equal = cb.equal(root.get(OrderMain_.payOnArrivalPayType), order.getPayOnArrivalPayType());//
			terms.add(equal);
		}
		if (isNotAllString(order.getOrderCategory())) {
			Predicate equal = cb.equal(root.get(OrderMain_.orderCategory), order.getOrderCategory());//
			terms.add(equal);
		}
		if (isNotAllString(order.getOrderRelatedOrigin())) {
			Predicate equal = cb.equal(root.get(OrderMain_.orderRelatedOrigin), order.getOrderRelatedOrigin());//
			terms.add(equal);
		}
		if (order.getBillType() != null && order.getBillType() == -1) {
			Predicate equal = cb.equal(root.get(OrderMain_.billType), order.getBillType());// 单据类型：逆向
			terms.add(equal);
		} else {
			Predicate equal = cb.equal(root.get(OrderMain_.billType), 1);// 单据类型：正向
			terms.add(equal);
		}
		if (isNotAllInt(order.getIsDeleted())) {
			Predicate equal = cb.equal(root.get(OrderMain_.isDeleted), order.getIsDeleted());// 删除标识
			terms.add(equal);
		}

		c.where(terms.toArray(new Predicate[0]));
		return super.findByPager(c, pager);
	}

	private boolean isNotAllInt(java.lang.Long value) {
		boolean flag = false;
		if (null != value && value != Global.ALL_INT) {
			flag = true;
		}
		return flag;
	}

	private boolean isNotAllString(String value) {
		boolean flag = false;
		if (StringUtils.isNotEmpty(value) && !value.equals(Global.ALL_STRING)) {
			flag = true;
		}
		return flag;
	}

	public List<String> findNoPayOrder(Map<String, Object> params, int count) {

		String statusPay = (String) params.get("statusPay");
		if (statusPay == null) {
			statusPay = OrderStatus.Order_PayStatus_Paying.getCode();
		}

		String statusTotal = (String) params.get("statusTotal");
		if (statusTotal == null) {
			statusTotal = OrderStatus.ORDER_PAYING.getCode();
		}

		Date startDate = (Date) params.get("startDate");
		if (startDate == null) {
			
		}
		 StringBuffer sb = new StringBuffer();
		   sb.append(" SELECT ");
			
		   sb.append("ordersearc0_.ORDER_NO                 as col_0_0_");
		   sb.append(" from ORDER_MAIN ordersearc0_");
		   
		   
		   sb.append(" where ordersearc0_.STATUS_PAY = '"+ statusPay + "'");

		   sb.append("AND  ordersearc0_.STATUS_TOTAL = '"+ statusTotal + "'");

		   sb.append("AND  ordersearc0_.DATE_UPDATED   <= " + "str_to_date('" + DateUtils.formatGeneralDateTimeFormat(new Date(System.currentTimeMillis() - 1 * 1800 * 1000)) + "','%Y-%m-%d %H:%i:%s')");
			SQLQuery recordQuery = getSession().createSQLQuery(sb.toString());
		return recordQuery.list();

	}

	@Override
	public List<OrderMain> findNoPayOrderResendMsg() {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<OrderMain> c = cb.createQuery(OrderMain.class);
		Root<OrderMain> root = c.from(OrderMain.class);
		c.select(root);
		// 待支付0130
		List<Predicate> predicates = new ArrayList<Predicate>();
		Predicate pstatusTotal = cb.equal(root.get(OrderMain_.statusTotal), OrderStatus.ORDER_PAYING.getCode());
		predicates.add(pstatusTotal);

		// 10小时之外
		Predicate orderTime = cb.lessThanOrEqualTo(root.get(OrderMain_.orderTime), new Date(System.currentTimeMillis() - 10 * 3600 * 1000));
		predicates.add(orderTime);
		// 24小时之内
		Predicate orderTimeEnd = cb.greaterThanOrEqualTo(root.get(OrderMain_.orderTime), new Date(System.currentTimeMillis() - 24 * 3600 * 1000));
		predicates.add(orderTimeEnd);
		// 还没发送， null或者0
		Predicate remindMsgSentNull = cb.isNull(root.get(OrderMain_.remindSent));
		Predicate remindMsgSentZero = cb.equal(root.get(OrderMain_.remindSent), 0l);
		Predicate msgP = cb.or(remindMsgSentNull, remindMsgSentZero);
		predicates.add(msgP);

		c.where(predicates.toArray(new Predicate[0]));

		return super.findByCriteria(c);

	}

	@Override
	public OrderMain getByOrderRelatedOrigin(String orderRelatedOrigin) {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<OrderMain> c = cb.createQuery(OrderMain.class);
		Root<OrderMain> root = c.from(OrderMain.class);
		c.select(root);
		List<Predicate> terms = new ArrayList<Predicate>();
		Predicate equal = cb.equal(root.get(OrderMain_.orderRelatedOrigin), orderRelatedOrigin);
		c.orderBy(cb.desc(root.get(OrderMain_.orderTime)));
		terms.add(equal);
		c.where(terms.toArray(new Predicate[0]));
		return getByCriteria(c);
	}

	public List<Object> findOrderMainBySql(String sql) {
		SQLQuery recordQuery = getSession().createSQLQuery(sql);
		recordQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Object> list = recordQuery.list();
		return list;
	}

	@Override
	public List<OrderMain> findFinishedOrder(String date, String endDate) throws ParseException {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<OrderMain> c = cb.createQuery(OrderMain.class);
		Root<OrderMain> root = c.from(OrderMain.class);
		c.select(root);
		List<Predicate> predicates = new ArrayList<Predicate>();
		Predicate categorySale = cb.equal(root.get(OrderMain_.orderCategory), "sale");
		Predicate categoryRet = cb.equal(root.get(OrderMain_.orderCategory), "ret");
		Predicate category = cb.or(categorySale, categoryRet);
		predicates.add(category);

		Predicate finishStatusSale = cb.equal(root.get(OrderMain_.statusTotal), "0180");
		Predicate finishStatusRet = cb.equal(root.get(OrderMain_.statusTotal), "0280");
		Predicate finishStatus = cb.or(finishStatusSale, finishStatusRet);
		predicates.add(finishStatus);
		Date startDate = DateUtils.convertStringToGeneralDateTime(date + " 00:00:00");
		Predicate ge = cb.greaterThanOrEqualTo(root.get(OrderMain_.finishTime), startDate);
		predicates.add(ge);
		Date endD = null;
		if (endDate == null) {
			endD = DateUtils.convertStringToGeneralDateTime(date + " 23:59:59");
		} else {
			endD = DateUtils.convertStringToGeneralDateTime(endDate + " 23:59:59");
		}
		Predicate le = cb.lessThanOrEqualTo(root.get(OrderMain_.finishTime), endD);
		predicates.add(le);
		c.where(predicates.toArray(new Predicate[0]));
		return super.findByCriteria(c);
	}

	@Override
	public List<OrderMain> findFinishedOrderBeforeOnline() throws ParseException {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<OrderMain> c = cb.createQuery(OrderMain.class);
		Root<OrderMain> root = c.from(OrderMain.class);
		c.select(root);
		List<Predicate> predicates = new ArrayList<Predicate>();
		Predicate categorySale = cb.equal(root.get(OrderMain_.orderCategory), "sale");
		Predicate categoryRet = cb.equal(root.get(OrderMain_.orderCategory), "ret");
		Predicate category = cb.or(categorySale, categoryRet);

		predicates.add(category);

		Predicate finishStatusSale = cb.equal(root.get(OrderMain_.statusTotal), "0180");
		Predicate finishStatusRet = cb.equal(root.get(OrderMain_.statusTotal), "0280");
		Predicate finishStatus = cb.or(finishStatusSale, finishStatusRet);
		predicates.add(finishStatus);

		Predicate immiVerZ = cb.equal(root.get(OrderMain_.immigrationVersion), "0");
		Predicate immiVerN = cb.isNull(root.get(OrderMain_.immigrationVersion));
		Predicate immiVer = cb.or(immiVerZ, immiVerN);
		predicates.add(immiVer);

		Date startDate = DateUtils.convertStringToGeneralDateTime("2014-07-15 00:00:00");
		Predicate ge = cb.greaterThanOrEqualTo(root.get(OrderMain_.finishTime), startDate);
		predicates.add(ge);
		Date endDate = DateUtils.convertStringToGeneralDateTime("2014-07-25 23:59:59");
		Predicate le = cb.lessThanOrEqualTo(root.get(OrderMain_.finishTime), endDate);
		predicates.add(le);

		c.where(predicates.toArray(new Predicate[0]));
		return super.findByCriteria(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ibm.oms.dao.intf.OrderMainDao#findbyStatusTotal(java.lang.String,
	 * com.ibm.sc.bean.Pager)
	 */
	@Override
	public List<OrderMain> findbyStatusTotal(String statusTotal, int count) {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<OrderMain> cq = cb.createQuery(OrderMain.class);
		Root<OrderMain> root = cq.from(OrderMain.class);

		cq.select(root);
		Predicate predicate = cb.equal(root.get("statusTotal"), statusTotal);
		cq.where(predicate);
		cq.orderBy(cb.asc(root.get("dateCreated")));
		return super.findByCriteria(cq, 0, count);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ibm.oms.dao.intf.OrderMainDao#findNoConfirmReceiptOrder(java.util.
	 * Map, int)
	 */
	@Override
	public List<String> findOrderByStatusAndDate(Map<String, Object> params, int count)  {

		Object statusCode = params.get("statusCode");
		Date startDate = (Date) params.get("startDate");
		String Date =DateUtils.formatGeneralDateTimeFormat(startDate);
		 StringBuffer sb = new StringBuffer();
		   sb.append(" SELECT ");
			
		   sb.append("ordersearc0_.ORDER_NO                 as col_0_0_");
		   sb.append(" from ORDER_MAIN ordersearc0_          ");
		   
		   sb.append("where  ordersearc0_.STATUS_TOTAL = '"+ statusCode + "'");

		   sb.append("AND  ordersearc0_.DATE_UPDATED   <= " + "str_to_date('" + DateUtils.formatGeneralDateTimeFormat(startDate) + "','%Y-%m-%d %H:%i:%s')");
			SQLQuery recordQuery = getSession().createSQLQuery(sb.toString());
		return recordQuery.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ibm.oms.dao.intf.OrderMainDao#orderQuery(com.ibm.oms.client.dto.order
	 * .OrderQueryDTO)
	 */
	@Override
	public Pager findOrderList(OrderMain om, Pager pager) {

		String salesclerkNo = om.getSalesclerkNo();
		String shopno = om.getSaleStoreCode();
		String orderStatus = om.getStatusTotal();
		String orderType = om.getOrderType();
		String memberNo = om.getMemberNo();
		Date orderTimeFrom = om.getOrderTimeFrom();
		Date orderTimeTo = om.getOrderTimeTo();
		Integer isSuspension = om.getIsSuspension();
		String orderSource = om.getOrderSource();
		Integer isCommission = om.getIsCommission();
		// 第一部判断线上商城还是导购APP或者其他渠道

		// 如果是导购端 则根据导购编号 以及查看的状态来查询中
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<OrderMain> c = cb.createQuery(OrderMain.class);
		Root<OrderMain> root = c.from(OrderMain.class);
		c.select(root);
		// 暂定APP
		if (StringUtils.isNotEmpty(salesclerkNo) && StringUtils.isNotEmpty(shopno)) {

			Predicate psalesclerkNo = cb.equal(root.get(OrderMain_.salesclerkNo), salesclerkNo);
			List<Predicate> predicates = new ArrayList<Predicate>();
			if (orderStatus != null) {

				Predicate pstatus = getStatus(orderStatus, cb, root);

				predicates.add(pstatus);
			}

			if (shopno != null) {
				Predicate pshop = cb.equal(root.get(OrderMain_.saleStoreCode), shopno);

				predicates.add(pshop);
			}

			if (orderSource != null) {
				Predicate posource = cb.equal(root.get(OrderMain_.orderSource), orderSource);

				predicates.add(posource);
			}
			if ("1".equals(isCommission)) {
				cb.isNotNull(root.get(OrderMain_.salesclerkPerform));

			}

			if ("0".equals(isCommission)) {
				cb.isNull(root.get(OrderMain_.salesclerkPerform));

			}

			if (orderType != null) {
				Predicate ptype = cb.equal(root.get(OrderMain_.orderType), orderType);
				predicates.add(ptype);
			}
			if (isSuspension != null) {
				Predicate pSuspension = cb.equal(root.get(OrderMain_.isSuspension), isSuspension);
				predicates.add(pSuspension);
			}
			if (orderTimeFrom != null) {
				Predicate pdate = cb.greaterThanOrEqualTo(root.get(OrderMain_.orderTime), orderTimeFrom);
				predicates.add(pdate);
			}
			if (orderTimeTo != null) {
				Predicate pdate = cb.lessThanOrEqualTo(root.get(OrderMain_.orderTime), orderTimeTo);
				predicates.add(pdate);
			}
			predicates.add(psalesclerkNo);

			c.where(predicates.toArray(new Predicate[0]));

			return super.findByPager(c, pager);
		}

		// 如果是线上商城 以及查看的状态和会员编号查询来查询
		// 暂定FUN
		if (StringUtils.isNotEmpty(memberNo)) {

			Predicate pmemberNo = cb.equal(root.get(OrderMain_.memberNo), memberNo);

			List<Predicate> predicates = new ArrayList<Predicate>();

			predicates.add(pmemberNo);
			if (orderStatus != null) {
				Predicate pstatus = cb.equal(root.get(OrderMain_.statusTotal), orderStatus);

				predicates.add(pstatus);

			}

			if (orderType != null) {
				Predicate ptype = cb.equal(root.get(OrderMain_.orderType), orderType);
				predicates.add(ptype);
			}
			if (isSuspension != null) {
				Predicate pSuspension = cb.equal(root.get(OrderMain_.isSuspension), isSuspension);
				predicates.add(pSuspension);
			}

			c.where(predicates.toArray(new Predicate[0]));

			return super.findByPager(c, pager);

		}
		if (StringUtils.isNotEmpty(shopno) && orderTimeFrom != null && orderTimeTo != null) {

			Predicate pmerchant = cb.equal(root.get(OrderMain_.saleStoreCode), shopno);

			List<Predicate> predicates = new ArrayList<Predicate>();

			predicates.add(pmerchant);
			if (orderStatus != null) {
				Predicate pstatus = cb.equal(root.get(OrderMain_.statusTotal), orderStatus);

				predicates.add(pstatus);

			}

			if (orderType != null) {
				Predicate ptype = cb.equal(root.get(OrderMain_.orderType), orderType);
				predicates.add(ptype);
			}
			if (isSuspension != null) {
				Predicate pSuspension = cb.equal(root.get(OrderMain_.isSuspension), isSuspension);
				predicates.add(pSuspension);
			}
			if (orderTimeFrom != null) {
				Predicate pdate = cb.greaterThanOrEqualTo(root.get(OrderMain_.balanceDate), orderTimeFrom);
				predicates.add(pdate);
			}
			if (orderTimeTo != null) {
				Predicate pdate = cb.lessThanOrEqualTo(root.get(OrderMain_.balanceDate), orderTimeTo);
				predicates.add(pdate);
			}

			c.where(predicates.toArray(new Predicate[0]));

			return super.findByPager(c, pager);

		}
		return null;

	}

	@Override
	public BigDecimal getOrderAmountByOrderId(Long orderId) {
		EntityManager em = this.entityManager.getEntityManagerFactory().createEntityManager();
		Query q = em.createNativeQuery("select TOTAL_PAY_AMOUNT from order_main where order_no = ?");
		q.setParameter(1, orderId);
		q.setMaxResults(1);
		BigDecimal amount = (BigDecimal) q.getSingleResult();
		return amount;
	}

	@Override
	public Long getMemberIdByOrderNo(String orderNo) {
		EntityManager em = this.entityManager.getEntityManagerFactory().createEntityManager();
		Query q = em.createNativeQuery("select member_no from order_main where order_no = ?");
		q.setParameter(1, orderNo);
		q.setMaxResults(1);
		String memberId = (String) q.getSingleResult();
		return Long.valueOf(memberId);
	}

	@Override
	public Long getMemberIdBySubOrderNo(String subOrderNo) {
		int length = subOrderNo.length();
		String memberId = "";
		if (length > 2) {
			String orderNo = subOrderNo.substring(0, length - 2);
			EntityManager em = this.entityManager.getEntityManagerFactory().createEntityManager();
			Query q = em.createNativeQuery("select member_no from order_main where order_no = ?");
			q.setParameter(1, orderNo);
			q.setMaxResults(1);
			memberId = (String) q.getSingleResult();
		}
		return Long.valueOf(memberId);
	}

	/*
	 * 通过会员号查询订单
	 * 
	 * @see
	 * com.ibm.oms.dao.intf.OrderMainDao#findOrderByMemberNo(java.lang.String)
	 */
	@Override
	public List<OrderMain> findOrderByMemberNo(String memberNo) {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<OrderMain> c = cb.createQuery(OrderMain.class);
		Root<OrderMain> root = c.from(OrderMain.class);
		c.select(root);

		Predicate pstatusCode = cb.equal(root.get(OrderMain_.memberNo), memberNo);

		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(pstatusCode);
		c.where(predicates.toArray(new Predicate[0]));
		return super.findByCriteria(c);
	}

	@Override
	public List<OrderMain> getSaleAfterMainOrderList(String orderCategory, String shopNo, String startDateStr, String endDateStr, String status,
			String channelSource) throws ParseException {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<OrderMain> c = cb.createQuery(OrderMain.class);
		Root<OrderMain> root = c.from(OrderMain.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		c.select(root);
		if (StringUtils.isNotEmpty(shopNo)) {
			Predicate equal01 = cb.equal(root.get(OrderMain_.merchantNo), shopNo);//
			predicates.add(equal01);
		}
		if (StringUtils.isNotEmpty(orderCategory) && !orderCategory.equals(Global.ALL_STRING)) {
			Predicate equal02 = cb.equal(root.get(OrderMain_.orderCategory), orderCategory);// 逆向订单类型：退货
			predicates.add(equal02);
		}
		if (StringUtils.isNotEmpty(status)) {
			Predicate equal03 = cb.equal(root.get(OrderMain_.statusTotal), status);//
			predicates.add(equal03);
		}
		if (StringUtils.isNotEmpty(channelSource)) {
			Predicate equal04 = cb.equal(root.get(OrderMain_.orderSource), channelSource);//
			predicates.add(equal04);
		}
		Predicate equal05 = cb.equal(root.get(OrderMain_.billType), CommonConst.OrderMain_BillType_Negative.getCodeLong());// 逆向订单类型：退货
		predicates.add(equal05);

		Date startDate = DateUtils.convertStringToGeneralDateTime(startDateStr);
		Predicate ge = cb.greaterThanOrEqualTo(root.get(OrderMain_.orderTime), startDate);
		predicates.add(ge);
		Date endDate = DateUtils.convertStringToGeneralDateTime(endDateStr);
		Predicate le = cb.lessThanOrEqualTo(root.get(OrderMain_.orderTime), endDate);
		predicates.add(le);

		c.where(predicates.toArray(new Predicate[0]));

		// int cnt = getCount();

		return super.findByCriteria(c);
	}

	/*
	 * 获取相应状态的Predicate
	 * 
	 * @see com.ibm.oms.dao.intf.OrderMainDao#getStatus(java.lang.String)
	 */
	private Predicate getStatus(String Status, CriteriaBuilder cb, Root<OrderMain> root) {
		if (Status == "1" || Status == "2" || Status == "3" || Status == "4" || Status == "5" || Status == "6") {
			if ("1".equals(Status)) {

				Predicate pstatus1 = cb.equal(root.get(OrderMain_.statusTotal), "110");
				Predicate pstatus2 = cb.equal(root.get(OrderMain_.statusTotal), "120");
				Predicate pstatus3 = cb.equal(root.get(OrderMain_.statusTotal), "130");
				Predicate pstatus = cb.or(pstatus1, pstatus2, pstatus3);
				return pstatus;
			} else if ("2".equals(Status)) {

				Predicate pstatus = cb.equal(root.get(OrderMain_.statusTotal), "170");

				return pstatus;

			} else if ("3".equals(Status)) {

				Predicate pstatus = cb.equal(root.get(OrderMain_.statusTotal), "210");

				return pstatus;
			} else if ("4".equals(Status)) {
				Predicate pstatus = cb.equal(root.get(OrderMain_.statusTotal), "280");

				return pstatus;
			} else if ("5".equals(Status)) {

				Predicate pstatus1 = cb.equal(root.get(OrderMain_.statusTotal), "310");
				Predicate pstatus2 = cb.equal(root.get(OrderMain_.statusTotal), "320");
				Predicate pstatus = cb.or(pstatus1, pstatus2);
				return pstatus;
			} else if ("6".equals(Status)) {

				Predicate pstatus = cb.equal(root.get(OrderMain_.statusTotal), "190");

				return pstatus;

			}

		} else {
			Predicate pstatus = cb.equal(root.get(OrderMain_.statusTotal), Status);

			return pstatus;
		}
		return cb.equal(root.get(OrderMain_.statusTotal), Status);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.oms.dao.intf.OrderMainDao#getTodayAllOrderGuiderId()
	 */
	@Override
	public List<?> getTodayAllOrderGuiderId() {
		StringBuilder sql = new StringBuilder();
//		sql.append(" SELECT MEMBER_NO,SALESCLERK_NO FROM ( ");
//		sql.append(" SELECT COUNT(MEMBER_NO) ccount,MEMBER_NO,GROUP_CONCAT(SALESCLERK_NO ORDER BY SALESCLERK_NO DESC) SALESCLERK_NO FROM ( ");
//		sql.append(" SELECT om.`MEMBER_NO`,om.`SALESCLERK_NO`,om.`FINISH_TIME` FROM order_main om  WHERE 5> ");
//		sql.append(" (SELECT COUNT(1) FROM order_main b WHERE b.MEMBER_NO=om.MEMBER_NO AND b.FINISH_TIME>om.FINISH_TIME) ");
//		sql.append(" AND om.`MEMBER_NO` IN  ");
//		sql.append(" ( ");
//		sql.append(" SELECT DISTINCT `MEMBER_NO` FROM order_main  ");
//		sql.append(" WHERE `ORDER_SOURCE`='DG' AND `BILL_TYPE`='1' AND DATE_FORMAT(`FINISH_TIME`,'%Y-%m-%d') =CURDATE() ");
//		sql.append(" ) ");
//		sql.append(" AND om.`ORDER_SOURCE`='DG' AND OM.`BILL_TYPE`='1'  ");
//		sql.append(" ) c GROUP BY c.MEMBER_NO  ");
//		sql.append(" ) d WHERE ccount>=5 ");
		sql.append("  SELECT MEMBER_NO,GROUP_CONCAT(SALESCLERK_NO ORDER BY SALESCLERK_NO DESC) SALESCLERK_NO, ");
		sql.append("   GROUP_CONCAT(c1 ORDER BY c1 DESC) c1, ");
		sql.append("   GROUP_CONCAT(c2 ORDER BY c2 DESC) c2 ");
		sql.append("  FROM (  ");
		sql.append("   SELECT MEMBER_NO, SALESCLERK_NO,c1,c2 FROM ( ");
		sql.append("     SELECT a.MEMBER_NO,a.SALESCLERK_NO,COUNT(a.SALESCLERK_NO) c1,SUM(a.TOTAL_PAY_AMOUNT) c2 FROM ( ");
		sql.append("       SELECT om.`MEMBER_NO`,om.`SALESCLERK_NO`,om.TOTAL_PAY_AMOUNT,om.`FINISH_TIME` FROM order_main om  WHERE  ");
		sql.append("         om.`ORDER_SOURCE`='DG' AND OM.`BILL_TYPE`='1' AND DATE_CREATED >= ADDDATE(CURDATE(),-730)  ");
		sql.append("         AND MEMBER_NO IN (SELECT MEMBER_NO FROM (SELECT COUNT(MEMBER_NO) c,MEMBER_NO FROM order_main om ");
		sql.append("           WHERE om.`ORDER_SOURCE`='DG' AND OM.`BILL_TYPE`='1' AND om.DATE_CREATED >= ADDDATE(CURDATE(),-730)  ");
		sql.append("           GROUP BY MEMBER_NO) aa WHERE aa.c>=5) ");
		sql.append("     ) a GROUP BY a.MEMBER_NO,a.SALESCLERK_NO ");
		sql.append("   ) b ORDER BY MEMBER_NO,c1 DESC,c2 DESC ");
		sql.append("  ) c GROUP BY c.MEMBER_NO   ");
		SQLQuery recordQuery = getSession().createSQLQuery(sql.toString());
		return recordQuery.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ibm.oms.dao.intf.OrderMainDao#findOrderSplitlist(java.lang.String)
	 */
	@Override
	public List<OrderMain> findOrderSplitlist(String orderNo,String ordernoP) {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<OrderMain> c = cb.createQuery(OrderMain.class);
		
	
		Root<OrderMain> root = c.from(OrderMain.class);
		
		c.select(root);
		Predicate pordernop = cb.equal(root.get(OrderMain_.orderNoP), ordernoP);

		Predicate porderno = cb.notEqual(root.get(OrderMain_.orderNo), orderNo);
		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(pordernop);

		predicates.add(porderno);
		c.where(predicates.toArray(new Predicate[0]));
		return super.findByCriteria(c);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ibm.oms.dao.intf.OrderMainDao#findOrderSplitlist(java.lang.String)
	 */
	@Override
	public List<?> findOrderForPay(String orderNo) {
		 StringBuffer sb = new StringBuffer();
		   sb.append(" SELECT ");
			
		   sb.append("ordersearc0_.SALE_STORE_CODE                 as col_0_0_,");
		   sb.append("ordersearc0_.SALESCLERK_NO                   as col_1_0_,");
		   sb.append("ordersearc0_.MERCHANT_NO                     as col_2_0_,");
		   sb.append("ordersearc0_.TOTAL_PAY_AMOUNT                as col_3_0_,");
		   sb.append("ordersearc0_1_.COMMODITY_NAME                as col_4_0_,");
		   sb.append("ordersearc0_1_.COMMODITY_CODE                as col_5_0_,");
		   sb.append("ordersearc0_1_.UNIT_DEDUCTED_PRICE           as col_6_0_,");
		   sb.append("ordersearc0_1_.SALE_NUM                      as col_7_0_,");
		   sb.append("ordersearc0_2_.INVOICE_NUM                   as col_8_0_,");
		   sb.append("ordersearc0_.ORDER_SOURCE                    as col_9_0_");
		   sb.append(" from ORDER_MAIN ordersearc0_");
		   sb.append(" left join order_invoice ordersearc0_2_ on ordersearc0_.id = ordersearc0_2_.ID_ORDER");
		   sb.append(" left join  ORDER_ITEM ordersearc0_1_  on ordersearc0_.id = ordersearc0_1_.ID_ORDER");
		   
		   
		   sb.append(" where ordersearc0_.ORDER_NO = '"+ orderNo + "'");

			SQLQuery recordQuery = getSession().createSQLQuery(sb.toString());
		return recordQuery.list();
	}

	/* (non-Javadoc)
	 * @see com.ibm.oms.dao.intf.OrderMainDao#findNoGetPoint(java.util.Map)
	 */
	@Override
	public List<OrderMain> findNoGetPoint(Map<String, Object> params) {
		Date start =(Date) params.get("startDate");
		Date end =(Date) params.get("endDate");
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<OrderMain> c = cb.createQuery(OrderMain.class);
		Root<OrderMain> root = c.from(OrderMain.class);
		c.select(root);
		List<Predicate> predicates = new ArrayList<Predicate>();
		Predicate pstatusTotal = cb.equal(root.get(OrderMain_.statusPay), OrderStatus.Order_PayStatus_Success.getCode());
		predicates.add(pstatusTotal);

		if (start != null) {
		Predicate orderTime = cb.lessThanOrEqualTo(root.get(OrderMain_.orderTime),start);
		predicates.add(orderTime);
		}
		if (end != null) {
		Predicate orderTimeEnd = cb.greaterThanOrEqualTo(root.get(OrderMain_.orderTime), end);
		predicates.add(orderTimeEnd);
		}				

		c.where(predicates.toArray(new Predicate[0]));
		return null;
	}

}

package com.ibm.oms.dao.test;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.oms.dao.base.BaseTest;
import com.ibm.oms.dao.impl.OrderMainDaoImpl;
import com.ibm.oms.domain.persist.OrderMain_;

public class OrderMainDaoImplTest extends BaseTest{
//	@Autowired
//	@Qualifier ( "baseDaoImpl" )  
//	BaseDaoImpl baseDAO;
	
	@Autowired
//	@Qualifier ( "orderMainDaoImpl" )   
	OrderMainDaoImpl orderMainDaoImpl;

//	@Test
//	public final void testBaseDaoImpl() {
//		orderMainDaoImpl.updateWithSql();
//	}

//	@Test
/*	public final void testBuildPredicate() {
	    OrderMain order = new OrderMain();
	    order.setMemberNo("111");
		Pager pager = new Pager();
		pager.setOrderBy("id");
		pager.setPageSize(2);
		pager.setPageNumber(3);
		Pager page = orderMainDaoImpl.findByOrderMain(order, pager);
		List retList = page.getList();
		for(Object e:retList){
			OrderMain ome = (OrderMain)e;
			log.debug(ome.getId());
		}
		pager.setPageSize(2);
		pager.setPageNumber(1);
		pager = orderMainDaoImpl.findByOrderMain(order, pager);
		retList = page.getList();
		for(Object e:retList){
			OrderMain ome = (OrderMain)e;
			log.debug(ome.getId());
		}
	}*/

	@Test
	public final void testClear() {
	    Long startTime = new Date().getTime()-1000*24*3600*10;
	    orderMainDaoImpl.getOMListByTypeAndPeriod(OrderMain_.memberNo,"11", startTime);
	}

}

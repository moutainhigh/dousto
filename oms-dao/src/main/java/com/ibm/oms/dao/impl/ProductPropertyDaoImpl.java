package com.ibm.oms.dao.impl;

import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.ProductPropertyDao;
import com.ibm.oms.domain.persist.ProductProperty;
import com.ibm.sc.dao.impl.BaseDaoImpl;
/**
 * DAOģʽ,
 * 
 * Creation date:2014-03-24 11:55:41
 * @author:Yong Hong Luo
 */
@Repository("productPropertyDao")
public class ProductPropertyDaoImpl extends BaseDaoImpl<ProductProperty,Long> implements ProductPropertyDao{
       
	
}

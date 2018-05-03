package com.ibm.oms.dao.intf;

import com.ibm.oms.domain.persist.SkuR3;
import com.ibm.sc.dao.BaseDao;
/**
 * DAOģʽ,
 * 
 * Creation date:2014-03-14 04:17:59
 * @author:Yong Hong Luo
 */
public interface SkuR3Dao extends BaseDao<SkuR3,Long>{
       public String getR3BySkuNo(String skuNo);
	
}

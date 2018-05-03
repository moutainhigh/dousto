package com.ibm.oms.dao.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.LockModeType;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.HangOrderPayModeDao;
import com.ibm.oms.domain.persist.HangOrderPayMode;
import com.ibm.sc.bean.Pageable;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.dao.impl.BaseDaoImpl;

/**
 * @author wangqc
 * @date 2018年2月7日 下午2:31:18
 * 
 */
@Repository
public class HangOrderPayModeDaoImpl extends BaseDaoImpl<HangOrderPayMode, Long> implements HangOrderPayModeDao {

}

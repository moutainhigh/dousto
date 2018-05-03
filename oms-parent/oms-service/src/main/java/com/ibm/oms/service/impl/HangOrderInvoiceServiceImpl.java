package com.ibm.oms.service.impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.intf.HangOrderInvoiceDao;
import com.ibm.oms.dao.intf.HangOrderItemDao;
import com.ibm.oms.domain.persist.HangOrderInvoice;
import com.ibm.oms.domain.persist.HangOrderItem;
import com.ibm.oms.service.HangOrderInvoiceService;
import com.ibm.sc.bean.Pageable;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.service.impl.BaseServiceImpl;

@Service("hangOrderInvoiceService")
public class HangOrderInvoiceServiceImpl extends BaseServiceImpl<HangOrderInvoice, Long> implements HangOrderInvoiceService {
	@Autowired
	private HangOrderInvoiceDao hangOrderInvoiceDao;
	
	@Autowired
    public void setHangOrderInvoiceDao(HangOrderInvoiceDao hangOrderInvoiceDao) {
        super.setBaseDao(hangOrderInvoiceDao);
    }

	@Override
	public void deleteByIdOrder(long orderId) {
		hangOrderInvoiceDao.deleteByIdOrder(orderId);
	}
	

}

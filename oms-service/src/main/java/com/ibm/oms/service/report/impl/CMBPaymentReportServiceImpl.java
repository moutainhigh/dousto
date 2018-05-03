package com.ibm.oms.service.report.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.report.CMBPaymentReportDao;
import com.ibm.oms.domain.persist.OrderReport;
import com.ibm.oms.service.report.CMBPaymentReportService;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.service.impl.BaseServiceImpl;

@Service("cMBPaymentReportService")
public class CMBPaymentReportServiceImpl extends BaseServiceImpl<OrderReport, Long> implements CMBPaymentReportService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
	@Autowired
	CMBPaymentReportDao cMBPaymentReportDao;
	
	@Override
	public Pager<?> findByCMBPaymentReport(OrderReport orderReport, Pager<?> pager) {
		try {
			pager = cMBPaymentReportDao.findByCMBPaymentReport(orderReport, pager);
			List<OrderReport> list = (List<OrderReport>) pager.getList();
		} catch (Exception e) {
			logger.info("{}", e);
		}
		return pager;
	}

	@Override
	public List<OrderReport> findByCMBPaymentReport(OrderReport orderReport) {
		List<OrderReport> list = new ArrayList<OrderReport>();
		try {
			list = cMBPaymentReportDao.findByCMBPaymentReport(orderReport);
			return list;
		} catch (Exception e) {
			logger.info("{}", e);
		}
		return list;
	}

}

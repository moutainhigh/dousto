package com.ibm.oms.service.report.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.report.ToDoListDao;
import com.ibm.oms.domain.persist.OrderReport;
import com.ibm.oms.service.report.ToDoListService;
import com.ibm.sc.service.impl.BaseServiceImpl;

@Service("toDoListService")
public class ToDoListServiceImpl extends BaseServiceImpl<OrderReport, Long> implements ToDoListService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
	@Autowired
	ToDoListDao toDoListDao;
	
	@Override
	public OrderReport findToDoList(OrderReport orderReport) {
		try {
			orderReport = toDoListDao.findToDoList(orderReport);
		} catch (Exception e) {
			logger.info("{}", e);
		}

		return orderReport;
	}

}

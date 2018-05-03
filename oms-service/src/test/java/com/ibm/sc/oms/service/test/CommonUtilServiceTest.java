package com.ibm.sc.oms.service.test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.beans.stock.OmsSOInfo;
import com.beans.stock.StockLockByOms;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderDTO;
import com.ibm.oms.intf.intf.BtcPayDTO;
import com.ibm.oms.intf.intf.TmsOrderDTO;
import com.ibm.oms.intf.intf.TmsPayDTO;
import com.ibm.oms.intf.intf.TmsStatusDTO;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.util.CommonUtilService;
import com.ibm.oms.service.util.XMLConverter;

/**
 * @author pjsong
 *
 */
@Component
public class CommonUtilServiceTest extends BaseTest{
    private static final Logger logger = LoggerFactory.getLogger(CommonUtilServiceTest.class);
    @Autowired
    CommonUtilService commonUtilService;
    @Autowired
    OrderMainService orderMainService;
    @Autowired
    OrderNoService orderNoService;
    @Test
    public  void genSampleJsonAll() throws IllegalAccessException, InvocationTargetException {

    }
    
}

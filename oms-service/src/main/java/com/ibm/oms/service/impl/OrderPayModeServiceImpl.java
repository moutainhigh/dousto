package com.ibm.oms.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.intf.OrderPayModeDao;
import com.ibm.oms.domain.persist.OrderPayMode;
import com.ibm.oms.domain.persist.OrderPayMode_;
import com.ibm.oms.service.OrderPayModeService;
import com.ibm.sc.service.impl.BaseServiceImpl;

/**
 * 
 * Creation date:2014-05-08 09:28:37
 * 
 * @author:Yong Hong Luo
 */
@Service("orderPayModeService")
public class OrderPayModeServiceImpl extends BaseServiceImpl<OrderPayMode, Long> implements OrderPayModeService {

    private OrderPayModeDao orderPayModeDao;

    @Autowired
    public void setOrderPayModeDao(OrderPayModeDao orderPayModeDao) {
        super.setBaseDao(orderPayModeDao);
        this.orderPayModeDao = orderPayModeDao;
    }

    @Override
    public BigDecimal getTotalPayByOrderNo(String orderNo) {
        List<OrderPayMode> opmList = orderPayModeDao.findByField(OrderPayMode_.orderNo, orderNo);
        if (opmList == null || opmList.isEmpty()) {
            return new BigDecimal(0);
        }
        BigDecimal bdRet = new BigDecimal(0);
        for (OrderPayMode opm : opmList) {
            bdRet = bdRet.add(opm.getPayAmount());
        }
        return bdRet;
    }
}

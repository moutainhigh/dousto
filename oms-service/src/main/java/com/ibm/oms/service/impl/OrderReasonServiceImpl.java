package com.ibm.oms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.intf.OrderReasonDao;
import com.ibm.oms.domain.persist.OrderReason;
import com.ibm.oms.domain.persist.OrderReason_;
import com.ibm.oms.service.OrderReasonService;
import com.ibm.sc.service.impl.BaseServiceImpl;

/**
 * 
 * Creation date:2014-04-16 03:07:06
 * 
 * @author:Yong Hong Luo
 */
@Service("orderReasonService")
public class OrderReasonServiceImpl extends BaseServiceImpl<OrderReason, Long> implements OrderReasonService {

    private OrderReasonDao orderReasonDao;

    @Autowired
    public void setOrderReasonDao(OrderReasonDao orderReasonDao) {
        super.setBaseDao(orderReasonDao);
        this.orderReasonDao = orderReasonDao;
    }

    /**
     * 获取售后原因的列表(包含二级列表)
     */
    public Map<OrderReason, List<OrderReason>> getReasonMap() {
        Map<OrderReason, List<OrderReason>> reasonMap = new HashMap<OrderReason, List<OrderReason>>();// kye为一级原因，value为二级原因
        List<OrderReason> allReasons = this.getList(OrderReason_.iseffected, isEffected);// 所有已生效的原因
        List<OrderReason> oneReasons = new ArrayList<OrderReason>(); // 一级原因（所有）
        List<OrderReason> twoReasons = new ArrayList<OrderReason>(); // 二级原因（所有）
        // 给所有已生效的原因进行分类
        for (OrderReason reason : allReasons) {
            if (rootReasonNo.equals(reason.getParentReasonNo())) {
                oneReasons.add(reason);
            } else {
                twoReasons.add(reason);
            }
        }
        // 设置一级、二级原因的对应关系
        for (OrderReason reason : oneReasons) {
            String reasonNo = reason.getReasonNo();
            List<OrderReason> childList = new ArrayList<OrderReason>();
            for (OrderReason childReason : twoReasons) {
                if (reasonNo.equals(childReason.getParentReasonNo())) {
                    childList.add(childReason);
                }
            }
            reasonMap.put(reason, childList);
        }
        return reasonMap;
    }
}

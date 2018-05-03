package com.ibm.oms.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.intf.OrdiErrOptLogDao;
import com.ibm.oms.domain.persist.OrdiErrOptLog;
import com.ibm.oms.domain.persist.OrdiErrOptLog_;
import com.ibm.oms.service.OrdiErrOptLogService;
import com.ibm.oms.service.business.ReturnChangeOrderService;
import com.ibm.oms.service.err.ErrConst;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.service.impl.BaseServiceImpl;

/**
 * 
 * Creation date:2014-03-14 04:20:54
 * 
 * @author:Yong Hong Luo
 */
@Service("ordiErrOptLogService")
public class OrdiErrOptLogServiceImpl extends BaseServiceImpl<OrdiErrOptLog, Long> implements OrdiErrOptLogService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private OrdiErrOptLogDao ordiErrOptLogDao;

    @Autowired
    ReturnChangeOrderService returnChangeOrderService;

    @Autowired
    public void setOrdiErrOptLogDao(OrdiErrOptLogDao ordiErrOptLogDao) {
        super.setBaseDao(ordiErrOptLogDao);
        this.ordiErrOptLogDao = ordiErrOptLogDao;
    }

    public Pager getPagerByMap(Map<String, String> map, Pager pager) {
        return ordiErrOptLogDao.getPagerByMap(map, pager);

    }

    @Override
    public List<OrdiErrOptLog> findByFields(Map<String, String> map) {

        Map<String, Object> objMap = new HashMap<String, Object>();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (StringUtils.isNotBlank(entry.getValue())) {
                Object obj = entry.getValue();
                objMap.put(entry.getKey(), obj);
            }
        }
        return ordiErrOptLogDao.findByFields(objMap);
    }

    public void processSaleAfterOrderToWms(int count) {
        List<OrdiErrOptLog> list = this.findByField(OrdiErrOptLog_.errorCode, ErrConst.SaleAfterOrder_ToWMS.getCode()); // 获取传输失败的意向单
        for (OrdiErrOptLog errLog : list) {
            BigDecimal operateCount = errLog.getOperateCount();
            if (null == operateCount) {
                operateCount = new BigDecimal(0);
            }
            if (operateCount.intValue() < count) { // 最大重复执行三次
                String orderSubNo = errLog.getOrderSubNo();
                boolean flag = returnChangeOrderService.sendOmsToWmsSaleAfterOrder(orderSubNo);// 传输WMS(如失败会进行错误次数累计)
                logger.info("逆向单 "+orderSubNo+" 传输WMS-job执行结果："+flag);
                if (flag) {
                    //this.delete(errLog.getId());
                    deleteByField(OrdiErrOptLog_.orderSubNo, orderSubNo);
                }
            }
        }
    }

}

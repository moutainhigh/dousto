package com.ibm.oms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.IntfReceiveConst;
import com.ibm.oms.dao.intf.IntfReceivedDao;
import com.ibm.oms.domain.persist.IntfReceived;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderDTO;
import com.ibm.oms.service.IntfReceivedService;
import com.ibm.oms.service.intf.ContextBtcOmsReceiveDTO;
import com.ibm.oms.service.util.CommonConstService;
import com.ibm.oms.service.util.CommonUtilService;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.service.impl.BaseServiceImpl;
/**
 * 
 * Creation date:2014-03-14 04:17:59
 * @author:Yong Hong Luo
 */
@Service("intfReceivedService")
public class IntfReceivedServiceImpl extends BaseServiceImpl<IntfReceived,Long> implements
		IntfReceivedService{
	private IntfReceivedDao intfReceivedDao;
    @Autowired
    CommonUtilService commonUtilService;
    @Autowired
	@SuppressWarnings("javadoc")
    public void setIntfReceivedDao(IntfReceivedDao intfReceivedDao) {
	    super.setBaseDao(intfReceivedDao);
		this.intfReceivedDao = intfReceivedDao;
	}


	@Override
	public IntfReceived findByFields(Map<String, Object> params) {
		// TODO Auto-generated method stub
		List<IntfReceived> intfList =  intfReceivedDao.findByFields(params);
		/*if(intfList.isEmpty()){
			return null; 
		}*/
		return (intfList.isEmpty()?null:intfList.get(0)) ;
	}
	
	@Override
	public void handlerBussiness(IntfReceived rInfo){
		rInfo =	intfReceivedDao.get(rInfo.getId());
		//
		//TODO bussiness
		
		rInfo.setSucceed(0l);
		intfReceivedDao.update(rInfo);
		
	}
	

    @Override
    public boolean saveBtcReceived(BtcOmsReceiveOrderDTO orderReceiveDIO, ContextBtcOmsReceiveDTO context) {
        // 记录接口接收数据
        IntfReceived rec = commonUtilService.saveIntfReceivedJson(null, null, IntfReceiveConst.BTC_OMS_RECEIVE.getCode(),
                orderReceiveDIO, null);
        // 校验数据
        String msg = CommonUtilService.createOrderValidate(orderReceiveDIO);
        if (msg.equals(CommonConstService.OK)) {
            rec.setSucceed(1l);
            update(rec);
            context.setCreateSuccessFlag(true);
            return true;
        } else {
            // 校验失败
            rec.setSucceed(-1l);
            update(rec);
            context.setCreateSuccessFlag(false);
            context.setMsg(msg);
            return false;
        }
    }
    
    public Pager getPagerByMap(Map<String, String> map ,Pager pager){
		return intfReceivedDao.getPagerByMap(map, pager);
		
	}

}

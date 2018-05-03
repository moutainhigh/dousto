
package com.ibm.oms.service;

import java.util.Map;

import com.ibm.oms.domain.persist.IntfReceived;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderDTO;
import com.ibm.oms.service.intf.ContextBtcOmsReceiveDTO;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.service.BaseService;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 * Creation date:2014-03-14 04:17:59
 * @author:Yong Hong Luo
 */
public interface IntfReceivedService extends BaseService<IntfReceived,Long>{
	//IntfReceived get
	IntfReceived findByFields(Map<String, Object> params);
	
	void handlerBussiness(IntfReceived rInfo);

    boolean saveBtcReceived(BtcOmsReceiveOrderDTO orderReceiveDIO, ContextBtcOmsReceiveDTO context);
    
	public Pager getPagerByMap(Map<String, String> map ,Pager pager);
}

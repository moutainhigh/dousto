package com.ibm.oms.service.business.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.OrderStatusAction;
import com.ibm.oms.domain.persist.HangOrderMain;
import com.ibm.oms.domain.persist.IntfVerified;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderDTO;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderOutputDTO;
import com.ibm.oms.intf.intf.HangOrderReceiveOrderDTO;
import com.ibm.oms.intf.intf.inner.BtcOmsReceiveOutputDTO;
import com.ibm.oms.intf.intf.inner.HangOrderMainDTO;
import com.ibm.oms.service.IntfReceivedService;
import com.ibm.oms.service.IntfVerifiedService;
import com.ibm.oms.service.business.HangOrderCreateService;
import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.oms.service.business.abstracts.OrderCreateServiceAbstract;
import com.ibm.oms.service.business.trans.HangOrderCreateTrans;
import com.ibm.oms.service.business.trans.OrderCreateTrans;
import com.ibm.oms.service.business.trans.abstracts.HangOrderCreateTransAbstract;
import com.ibm.oms.service.intf.ContextBtcOmsReceiveDTO;
import com.ibm.oms.service.util.CommonConstService;
import com.ibm.oms.service.util.ExceptionUtil;


/**
 * 
 * 线下创建挂起订单
 * @author ChaoWang
 *
 */
@Service("hangOrderCreateService")
public class HangOrderCreateServiceImpl extends HangOrderCreateTransAbstract  implements HangOrderCreateService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private HangOrderCreateTrans hangOrderCreateTrans;
    /*@Autowired
    IntfReceivedService intfReceivedService;*/
    /*@Autowired
    IntfVerifiedService intfVerifiedService;*/
    //导购APP 待客下单  下单1、线下下单 ，线下发货 -->已支付，确认收货   2、线下下单，线上发货 -->已支付，待收货   -->线下订单无需审核
    
	@Override
	public BtcOmsReceiveOrderOutputDTO createHangOrder(HangOrderReceiveOrderDTO hangOrderReceiveOrderDTO) {
		
		BtcOmsReceiveOrderOutputDTO output = new BtcOmsReceiveOrderOutputDTO();
		try{
			List<HangOrderMain> handOrders = hangOrderCreateTrans.saveOrder(hangOrderReceiveOrderDTO);
			List<BtcOmsReceiveOutputDTO> mapList = new ArrayList<BtcOmsReceiveOutputDTO>();
			for (HangOrderMain hangOrder : handOrders) {
				BtcOmsReceiveOutputDTO outPut = new BtcOmsReceiveOutputDTO();
				outPut.setOrderNo(hangOrder.getOrderNo());
				outPut.setIdOrder(String.valueOf(hangOrder.getId()));
				
				mapList.add(outPut);
			}
			output.setMapList(mapList);
			output.setSucceed(CommonConstService.OK);
            output.setMessage("挂单保存成功");
		}catch(Exception e){
			logger.info("{}",ExceptionUtil.stackTraceToString(e));
			output.setSucceed(CommonConstService.FAILED);
            output.setMessage("挂单保存失败"+e.toString());
		}
		return output;
	}

	@Override
	public List<HangOrderMain> queryHangOrderMain(String shopNo, String startDate, String endDate, String status) {
		List<HangOrderMain> hom = hangOrderCreateTrans.queryHangOrderMain(shopNo,startDate,endDate,status);
		return hom;
	}

	@Override
	public List<HangOrderMain> queryHangOrderMainDetail(String OrderNo) {
		List<HangOrderMain> hom = hangOrderCreateTrans.queryHangOrderMainDetail(OrderNo);
		return hom;
	}

	@Override
	public BtcOmsReceiveOrderOutputDTO deleteHangOrderByOrderNo(String orderNO) {
		
	    BtcOmsReceiveOrderOutputDTO output = hangOrderCreateTrans.deleteHangOrderByOrderNo(orderNO);
		return output;
	}

	@Override
	public BtcOmsReceiveOrderOutputDTO updateHangOrder(HangOrderReceiveOrderDTO hangOrderReceiveOrderDTO) {
		BtcOmsReceiveOrderOutputDTO output = hangOrderCreateTrans.updateHangOrder(hangOrderReceiveOrderDTO);
		return output;
	}
}

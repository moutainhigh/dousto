package com.ibm.oms.service.business.trans.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.dao.intf.OrderMainDao;
import com.ibm.oms.domain.persist.HangOrderMain;
import com.ibm.oms.domain.persist.HangOrderSub;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderItemPay;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.intf.constant.OrderMainConst;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderDTO;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderOutputDTO;
import com.ibm.oms.intf.intf.HangOrderReceiveOrderDTO;
import com.ibm.oms.intf.intf.inner.BtcOmsReceiveOutputDTO;
import com.ibm.oms.intf.intf.inner.OrderMainDTO;
import com.ibm.oms.intf.intf.inner.OrderPromotionDTO;
import com.ibm.oms.intf.intf.inner.OrderSubDTO;
import com.ibm.oms.service.HangOrderInvoiceService;
import com.ibm.oms.service.HangOrderMainService;
import com.ibm.oms.service.HangOrderPayService;
import com.ibm.oms.service.HangOrderPromotionService;
import com.ibm.oms.service.HangOrderSubService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.OrderStatusService;
import com.ibm.oms.service.business.trans.HangOrderCreateTrans;
import com.ibm.oms.service.business.trans.SaleAfterOrderTransService;
import com.ibm.oms.service.business.trans.abstracts.HangOrderCreateTransAbstract;
import com.ibm.oms.service.business.trans.abstracts.OrderCreateTransAbstract;
import com.ibm.oms.service.intf.ContextBtcOmsReceiveDTO;
import com.ibm.oms.service.util.CommonConstService;
import com.ibm.oms.service.util.CommonUtilService;

/**
 * 
 * Creation date:2018-2-7
 * 
 * @author:wangqc
 */
@Service("orderCreateTransHang")
public class HangOrderCreateTransImpl extends HangOrderCreateTransAbstract{
    private final Logger logger = LoggerFactory.getLogger(getClass());    
    
    @Autowired
    private HangOrderMainService hangOrderMainService;
    @Autowired
    private HangOrderSubService hangOrderSubService;
    @Autowired
    private HangOrderInvoiceService hangOrderInvoiceService;
    @Autowired
    private HangOrderPayService hangOrderPayService;
    @Autowired
    private HangOrderPromotionService hangOrderPromotionService;
    
    
    @Autowired
    CommonUtilService commonUtilService;
    
    @Autowired
    OrderNoService orderNoService;//生成订单号


    @Override
    public List<HangOrderMain> saveOrder(HangOrderReceiveOrderDTO hangOrderReceiveDIO) {
    	return super.saveOrder(hangOrderReceiveDIO);
    }


	@Override
	public List<HangOrderMain> queryHangOrderMain(String shopNo, String startDate, String endDate, String status) {
		// TODO Auto-generated method stub
		return super.queryHangOrderMain(shopNo, startDate, endDate, status);
	}


	@Override
	public List<HangOrderMain> queryHangOrderMainDetail(String orderNo) {
		// TODO Auto-generated method stub
		return super.queryHangOrderMainDetail(orderNo);
	}


	@Override
	public BtcOmsReceiveOrderOutputDTO deleteHangOrderByOrderNo(String orderNO) {
		// TODO Auto-generated method stub
		return super.deleteHangOrderByOrderNo(orderNO);
	}


	@Override
	public BtcOmsReceiveOrderOutputDTO updateHangOrder(HangOrderReceiveOrderDTO hangOrderReceiveOrderDTO) {
		// TODO Auto-generated method stub
		return super.updateHangOrder(hangOrderReceiveOrderDTO);
	}
    
    /**是否重复订单**/
    /*protected boolean isDuplicate(String batchNum, OrderMainDTO om){
       return super.isDuplicate(batchNum, om);
    }*/

    /**
     * 写主订单表，子订单，订单级促销
     * **/
    /*protected boolean saveOrderMainStart(BtcOmsReceiveOrderDTO orderReceiveDIO, BtcOmsReceiveOrderOutputDTO output,
            ContextBtcOmsReceiveDTO context) {
	        return super.saveOrderMainStart(orderReceiveDIO, output, context);
        
    }*/

    /**
     * 写主订单，子订单，订单级促销
     * **/
    /*protected BtcOmsReceiveOutputDTO saveHangOrderMainSingle(OrderMainDTO omDTO,BtcOmsReceiveOrderOutputDTO output) {
    	BtcOmsReceiveOutputDTO ret = new BtcOmsReceiveOutputDTO();
        // 主订单
        HangOrderMain om = saveHangOrderMain(omDTO);
        // 写子订单,订单行，订单行级促销
        saveHangOrderSub(omDTO, om);
        // 写订单级促销，
        saveHangOrderPromo(omDTO, om);
        // 订单头支付
        saveHangOrderPay(omDTO, om);
        // 创建销售订单-->处理中
        ret.setIdOrder(om.getId() + "");
        ret.setOrderNo(om.getOrderNo());
        ret.setPayOnArrival(CommonConstService.BOOLEAN_TRUE.equals(om.getIfPayOnArrival()) ? true : false);
        return ret;
    }*/
    
    /** 写子订单表 **/
    /*protected List<HangOrderSub> saveHangOrderSub(OrderMainDTO orderMainDTO, HangOrderMain om) {
        return null;
    }*/

    /** 写订单行表 **/
    /*protected void saveOrderItem(OrderSubDTO osDTO, OrderSub os, OrderMain om, ContextBtcOmsReceiveDTO context) {
    	 super.saveOrderItem(osDTO, os, om, context);

    }*/

    /**保存订单行级促销信息
     * @param opDTOs
     * @param oi
     */
    /*protected void saveHangOrderItemPromo(List<OrderPromotionDTO> opDTOs, OrderItem oi) {
    	super.saveOrderItemPromo(opDTOs, oi);
    }*/
    
    /**保存订单级促销信息
     * @param opDTOs
     * @param oi
     */
   /* protected void saveHangOrderPromo(OrderMainDTO omDTO, HangOrderMain om ) {
    	
    }
*/    

    /** 写my卡，券，积分支付等支付信息 **/
    /*protected void saveHangOrderPay(OrderMainDTO omDTO, HangOrderMain om) {
       
    }*/

    
 

}

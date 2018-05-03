package com.ibm.oms.rs.service.mqListener;
//package com.ibm.sc.rs.service.oms.mqListener;
//
//import java.math.BigDecimal;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.ibm.sc.dao.oms.constant.IntfReceiveConst;
//import com.ibm.sc.dao.oms.constant.OrderStatusAction;
//import com.ibm.sc.dao.oms.constant.PayType;
//import com.ibm.sc.oms.persist.IntfReceived;
//import com.ibm.sc.oms.persist.OrderItem;
//import com.ibm.sc.oms.persist.OrderItemPay;
//import com.ibm.sc.oms.persist.OrderMain;
//import com.ibm.sc.oms.persist.OrderPay;
//import com.ibm.sc.oms.persist.OrderSub;
//import com.ibm.sc.service.oms.IntfReceivedService;
//import com.ibm.sc.service.oms.OrderItemPayService;
//import com.ibm.sc.service.oms.OrderItemService;
//import com.ibm.sc.service.oms.OrderMainService;
//import com.ibm.sc.service.oms.OrderPayService;
//import com.ibm.sc.service.oms.OrderSubService;
//import com.ibm.sc.service.oms.business.OrderStatusService;
//import com.ibm.sc.service.oms.business.trans.TmsOmsReceivePaymentTransService;
//import com.ibm.sc.service.oms.intf.CommonOutputDTO;
//import com.ibm.sc.service.oms.intf.TmsPayDTO;
//import com.ibm.sc.service.oms.intf.TmsStatusDTO;
//import com.ibm.sc.service.oms.mq.QueueListenerAbstract;
//import com.ibm.sc.service.oms.util.CommonConstService;
//import com.ibm.sc.service.oms.util.CommonUtilService;
//import com.ibm.sc.service.oms.util.XMLConverter;
//
///**
// * @author pjsong
// * 
// */
//@Component
//public class TmsPayToOmsListener1 extends QueueListenerAbstract {
//    private Logger logger = LoggerFactory.getLogger(getClass());
//	@Autowired
//	IntfReceivedService intfReceivedService;
//	@Autowired
//	OrderStatusService orderStatusService;
//	@Autowired
//	OrderPayService orderPayService;
//	@Autowired
//	OrderSubService orderSubService;
//	@Autowired
//	OrderItemPayService orderItemPayService;
//	@Autowired
//	OrderItemService orderItemService;
//	@Autowired
//	OrderMainService orderMainService;
//	@Autowired
//    XMLConverter xmlConverterPay;
//	@Autowired
//	TmsOmsReceivePaymentTransService tmsOmsReceivePaymentTransService;
//	@Override
//	protected boolean doProcess(String reqXml) {
//        TmsPayDTO dto = (TmsPayDTO)xmlConverterPay.convertFromXMLStringToObject(reqXml);
//        String msg = CommonUtilService.createOrderValidate(dto);
//        if (!msg.equals(CommonConstService.OK)) {
//            logger.info(msg);
//            return false;
//        }
//        try {
//            CommonOutputDTO output = tmsOmsReceivePaymentTransService.updateTmsOmsPayment(dto);
//            if(!CommonConstService.OK.equals(output.getCode())){
//                logger.info(output.getMsg());
//                return false;
//            }
//            return true;
//        } catch (Exception e) {
//            logger.error("TmsStatusListener  --> {}", e);
//            return false;
//        }
//	}
//
//	@Override
//	protected boolean doProcess(Object reqObj) {
//		return false;
//	}
//
//
//
//    @Override
//    protected <T> IntfReceived saveTrack(T reqObject) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    protected IntfReceived saveTrack(String reqXml) {
//        IntfReceived ir = new IntfReceived();
//        ir.setIntfCode(IntfReceiveConst.Tms_Pay_to_Oms.getCode());
//        ir.setMsg(reqXml);
//        ir.setSucceed(1l);
//        ir.setCreateTime(new Date());
//        intfReceivedService.save(ir);
//        return ir;
//    }
//
//    @Override
//    protected void updateTrack(IntfReceived intfReceived) {
//        intfReceivedService.update(intfReceived);
//    }
//}
//

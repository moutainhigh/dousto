package com.ibm.oms.service.business;

import java.math.BigDecimal;
import java.util.List;

import com.ibm.oms.dao.constant.CancelOrderConst;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.intf.intf.BBCLogiDTO;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderDTO;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderOutputDTO;
import com.ibm.oms.intf.intf.InventoryLockOutputDTO;
import com.ibm.oms.intf.intf.InventoryResendMsgOutputDTO;
import com.ibm.oms.intf.intf.ResultDTO;
import com.ibm.oms.intf.intf.SupportResendOutputDTO;
import com.ibm.oms.service.intf.ContextBtcOmsReceiveDTO;

/**
 * @author pjsong
 * 
 */
public interface OrderCreateService {
    String dealErrConst1 = "根据id查找异常失败";

    String dealErrConst2 = "该id对应的子订单号不存在";
    String dealErrConst3 = "该id对应的订单状态不存在";
    String dealErrConst4 = "该id对应的订单状态于db实际状态不一致，id:%s;db:%s";
    String dealErrConst5 = "该id对应的订单允许重新处理，不允许删除，id:%s;db:%s";
    String dealErrConst6 = "订单删除成功，id:%s;db:%s";
    /**
     * 1天前
     */
    int daysIntervalM1 = -1;
    /**
     * 7天前
     */
    int daysIntervalM7 = -7;
    /**
     * 1天5单
     */
    int blackListCriteriaDays1 = 5;
    /**
     * 7天20单
     */
    int blackListCriteriaDays7 = 20;

    /**总金额大于2000**/
    BigDecimal totalPayAmountLimit = new BigDecimal(2000);
    
    /**单品大于1000**/
    BigDecimal itemPayAmountLimit = new BigDecimal(1000);
    
    /**
     * WMS出库单的订单类型
     */
    String WMS_OmsSOInfo_OrderType_Sale = "SO"; // 正常销售订单
    String WMS_OmsSOInfo_OrderType_ChgOut = "TH";// 换货出库单
    /**1物流状态**/
    String bbcCodeLogi = "1";
    /**2已短信验证**/
    String bbcCodeMsg = "2";
    /**3拒收**/
    String bbcRefuse = "3";
    /**
     * 
     * 功能描述: 创建在线销售订单
     * 
     * @param orderReceiveDIO
     * @return CommonOutputDTO
     */
    BtcOmsReceiveOrderOutputDTO createOrder(BtcOmsReceiveOrderDTO orderReceiveDIO);

    /**
     * 
     * 功能描述: 支付结果接收
     * 
     * @param receivePaymentDTO
     * @return CommonOutputDTO
     */
    // public CommonOutputDTO saveOrderPay(ReceivePaymentDTO receivePaymentDTO);

    /** 自动审核 **/
    boolean autoAudit(String orderNo);

    /**
     * btc返回后的订单调度，定时任务入口
     * 
     * @param count 每次捞出多少条
     * **/
    void processPostBTC(int count);
    /**
     * 库存锁定：创建订单(B2C)
     * 
     * @param context
     * @return
     */
    boolean inventoryLockBatch(ContextBtcOmsReceiveDTO context);

    /**
     * 库存扣减
     * 
     * @param context
     * @return
     */
    boolean inventoryDeduct(String orderNo);
    
    /**
     * BBC库存扣减、人工审核
     * 
     * @param context
     * @param flag 是否在线支付：Y 在线支付，N 否
     * @param exceptionFlag 是否人工审核 ：   Y 审核订单 ，N 库存扣减(库存扣减失败操作)
     * @return
     */
    String inventoryDeductBbc(String orderNo,String opernateName,String flag,String exceptionFlag);
    
    /**
     * 库存取消
     * @param om
     * @return
     */
    boolean inventoryCancel(String orderNo);

    /**
     * 库存解锁
     * 
     * @param omList
     */
    void inventoryUnLock(List<OrderMain> omList);
    
    /**库存解锁 -创建订单使用
     * @param omList
     * @param context
     */
    void inventoryUnLock(List<OrderMain> omList,ContextBtcOmsReceiveDTO context);

    /**
     * 库存锁定
     * 
     * @param orderMain
     * @return
     */
    InventoryLockOutputDTO inventoryLock(String  orderNo);
    
    /**
     * 单个订单库存解锁
     * @param orderNo
     * @return
     */
    InventoryLockOutputDTO inventoryUnLockByOrderNo(String orderNo);


    /**
     * 1门店物流接收完成2短信已接收，已收已付3拒收
     * @param orderSubNo
     * @param operateCode
     * @return
     */
    String bbcOperateToOms(String orderSubNo, String operateCode, String sys);

    /**
     * 逐条处理btc订单
     * @param orderNo
     * @return 
     */
    boolean callSingleProcess(String orderNo);

    
    /** 人工审核订单
     * @param om
     * @param approved
     * @return
     */
    String manualAudit(String orderNo, boolean approved);

    /**bbc传回第三方物流信息**/
    String bbcLogisticsVerified(BBCLogiDTO bbcLogiDTO);
    /**
     * 虚拟商品重发短信
     */
    InventoryResendMsgOutputDTO inventoryResendMsg(String orderItemNo);
    /**
     * 自提商品重发短信
     */
    SupportResendOutputDTO supportResendMsg(String orderSubNo);

    /** 是否btc过来的该批次订单全都到达0180已收已付。如果是：调用促销接口返券返积分；否则：不调 **/
    boolean isAllOrdersFinished(String batchNum);

    boolean promoResourceAdd(String batchNum, String orderNo);

    /** 在线支付订单的支付状态，因为btc未回传，由订单后台手工处理**/
    ResultDTO handlerOnlineOrderStatusPay(OrderSub orderSub);
    /**异常处理**/
    String dealErr(Long id);
    /**24小时未支付取消提醒**/
    SupportResendOutputDTO supportResend24Hours(String orderSubNo);

    /**订单已出库发送邮件**/
    SupportResendOutputDTO supportSendEmail(String orderNo);
    /**添加会员到黑名单**/
    String addBlackList(String orderNo, String type);
    /**
     * 运费补贴10元已生效
     * @param orderSubNo 
     * @return 
     * */
    SupportResendOutputDTO supportPostFeeRet(String orderSubNo);
    
    /**
     * 发送订单短信(未出库订单)
     * @param mobilePhoneNum
     * @param smsModeCode
     * @return
     */
    SupportResendOutputDTO supportSendSms(String mobilePhoneNum,String intfCode, String smsModeCode);

    /**
     * @param id
     * @return
     */
    String deleteErr(Long id);

    /**
     * @param orderNo
     * @return
     */
    boolean returnCouponForCancel(String orderNo);

    
    /**
     * @param batchNum
     * @return
     */
    boolean isAllOrdersCancelled(String batchNum);
    
    /**
     * 根据订单号写色码款商品属性
     * @param orderNo
     */
    void buildSemakuanProduct(String orderNo);
    
    /**
     * 
     * Description:
     * @param orderSub.orderSubNo
     * @param orderSub.deliveryMerchantNo 物流公司编码
     * @return
     */
    void updateDeliveryByOrderSubNo (OrderSub orderSub) throws Exception;

	/**
	 * Description: 取消订单是回退购物券
	 * @param orderNo
	 * @return
	 */
	boolean removeCouponForCancel(String orderNo);

	/**
	 * Description:取消订单是回退积分
	 * @param orderNoBySubNo
	 */
	boolean cancelOrderReturnPoint(String orderNoBySubNo);
}
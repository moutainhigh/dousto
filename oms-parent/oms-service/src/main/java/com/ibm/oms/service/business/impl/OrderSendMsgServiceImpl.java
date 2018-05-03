package com.ibm.oms.service.business.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.constant.IntfSentConst;
import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderMain_;
import com.ibm.oms.domain.persist.OrderSearch;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderSearchService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.oms.service.business.OrderSendMsgService;
import com.ibm.oms.service.util.DateUtil;

@Service("orderSendMsgService")
public class OrderSendMsgServiceImpl implements OrderSendMsgService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    /** 未出库订单发送短信相关参数 start **/
    static final String inStoreOrder_sendMsgTimeStr_14 = "14"; // 触发短信的时间点
    static final String inStoreOrder_sendMsgTimeStr_9 = "09";
    static final String outStoreTime_13 = " 13:00:00"; // 出库时间
    static final String outStoreTime_23 = " 23:00:00";
    static final String smsModeCode_ConfirmTimeOut = "CM-OMS-ORDER-NOSEND-CONFIRMTIME"; // 短信模版code
    static final String smsModeCode_Collection = "CM-OMS-ORDER-NOSEND-COLLECTION";
    static final String smsModeCode_Default = "CM-OMS-ORDER-NOSEND-DEFAULT";
    /** 未出库订单发送短信相关参数 end **/

    @Autowired
    OrderMainService orderMainService;
    @Autowired
    OrderSubService orderSubService;
    @Autowired
    OrderSearchService orderSearchService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    OrderCreateService orderCreateService;

    /**
     * 1）前一日20：30后——当日11点前产生的有效订单，若在当日中午13：00时订单状态仍未更改为“订单已出库”时，则系统在当日14：00 发送短信
     * 2）当日11：00后——当日20：30前产生的有效订单，若在当日晚上23：00时订单状态仍未更改为“订单已出库”时，则系统在次日早上9：00 发送短信
     */
    public void sendMsgInStoreOrder() {
        java.text.SimpleDateFormat dateSdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH"); // 这里只获取“小时”
        String curHourStr = sdf.format(new Date());
        String curDateStr = dateSdf.format(new Date());
        String searchDateFromStr = ""; // 订单创建开始时间
        String searchDateToStr = ""; // 订单创建结束时间
        String outStoreTimeMax = ""; // 出库时间限制
        String curDateBeforeOneDay = DateUtil.getBeforeDate(new Date(), 1, "yyyy-MM-dd"); // 前一天的日期
        if (curHourStr.equals(inStoreOrder_sendMsgTimeStr_14)) {
            searchDateFromStr = curDateBeforeOneDay + " 20:30:00"; // 前一天的20:30
            searchDateToStr = curDateStr + " 11:00:00"; // 当天的11:00
            outStoreTimeMax = curDateStr + outStoreTime_13; // 最晚出库时间，当前13：00
        } else if (curHourStr.equals(inStoreOrder_sendMsgTimeStr_9)) {
            // 这里需获取前一天的时间，因为定时任务执行时间为早上9点
            searchDateFromStr = curDateBeforeOneDay + " 11:00:00";
            searchDateToStr = curDateBeforeOneDay + " 20:30:00";
            outStoreTimeMax = curDateBeforeOneDay + outStoreTime_23;
        } else {
            return;// 其他时间点暂不处理
        }
        // 1、查询指定时间段内的订单(已审核、未出库)
        String dateFormate = "yyyy-MM-dd HH:mm:ss";
        OrderSearch search = new OrderSearch();
        // 下单时间
        // search.setOrderTimeFrom(DateUtil.getDateFormatByString(searchDateFromStr, dateFormate));
        // search.setOrderTimeTo(DateUtil.getDateFormatByString(searchDateToStr, dateFormate));
        // 审核时间
        search.setConfirmTimeFrom(DateUtil.getDateFormatByString(searchDateFromStr, dateFormate));
        search.setConfirmTimeTo(DateUtil.getDateFormatByString(searchDateToStr, dateFormate));
        search.setMerchantNo(CommonConst.OrderMain_MerchantNo.getCode());// 网天发货的订单（自营商品、联营集货）
        search.setAddressCode(CommonConst.OrderSub_AddressCode_Shenzhen.getCode()); // 深圳区域的订单
        List<String> statusTotalList = new ArrayList();
        statusTotalList.add(OrderStatus.ORDER_INVENTORY_DEDUCTING.getCode()); // 库存扣减中
        statusTotalList.add(OrderStatus.ORDER_INVENTORY_DEDUCT_FAILED.getCode());// 库存扣减失败
        statusTotalList.add(OrderStatus.ORDER_VALIDATED.getCode());// 已生效待发货
        search.setStatusTotalList(statusTotalList);
        List<OrderSearch> list = orderSearchService.findByOrderSearch(0, search);
        List<OrderSearch> result = new ArrayList();
        for (OrderSearch order : list) {
            // 网天自提的订单不发送短信
            if (null != order.getSelfTakePointId()
                    && CommonConst.OrderSub_selfFetchAddress_RainBow.getCode().equals(order.getSelfTakePointId().toString())) {
                continue;
            }
            Date outTime = order.getOutStoreTime(); // 出库时间
            if (null == outTime) {
                result.add(order); // 情况1、未出库
                continue;
            }
            // 判断是否超时规定的出库时间
            int compResult = DateUtil.compareDateTime(outTime,
                    DateUtil.getDateFormatByString(outStoreTimeMax, dateFormate));
            if (compResult >= 0) {
                result.add(order); // 情况2、已出库但是超出预计的出库时间
            }
        }
        // 2、对于符合条件的订单需区分以下三种情况：1、下单时间与确认时间大于30分钟的；2、商品的存放地非“网天仓库”的集货订单；3、除1、2以外的
        long standTime = 30 * 60 * 1000; // 30分钟折算成毫秒
        for (OrderSearch order : result) {
            if (StringUtils.isBlank(order.getMobPhoneNum())) {
                continue;
            }
            String smsModeCode = ""; // 短信模版名称
            String orderNo = order.getOrderNo();
            String orderSubNo = order.getOrderSubNo();
            OrderMain main = orderMainService.getByField(OrderMain_.orderNo, orderNo);
            long discTime = main.getConfirmTime().getTime() - main.getOrderTime().getTime();
            if (discTime > standTime) {
                smsModeCode = smsModeCode_ConfirmTimeOut;
            } else {
                // 判断是否包含集货商品
                boolean collecFlag = orderItemService.checkStoreTypeByOrderSubNo(orderSubNo,
                        CommonConst.OrderItem_StoreType_Collection.getCode());
                if (collecFlag) {
                    smsModeCode = smsModeCode_Collection;
                } else {
                    smsModeCode = smsModeCode_Default;
                }
            }
            logger.info("未及时出库订单:" + orderSubNo + "发送短信,模版为:" + smsModeCode);
            // 3、发送短信
            orderCreateService.supportSendSms(order.getMobPhoneNum(), IntfSentConst.OMS_SUP_SEND_SMS.getCode(),
                    smsModeCode);
        }
    }

}

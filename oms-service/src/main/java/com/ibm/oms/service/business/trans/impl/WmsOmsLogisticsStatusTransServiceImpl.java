package com.ibm.oms.service.business.trans.impl;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.OrderStatusAction;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.domain.persist.OrderSub_;
import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.intf.intf.WmsOmsReceiveLogisticsDTO;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.OrderStatusService;
import com.ibm.oms.service.business.trans.WmsOmsLogisticsStatusTransService;
import com.ibm.oms.service.util.CommonConstService;
import com.ibm.oms.service.util.CommonUtilService;
import com.ibm.oms.service.util.XMLConverter;

/**
 * @author liucy
 * 
 */
@Service("wmsOmsLogisticsStatusTransService")
public class WmsOmsLogisticsStatusTransServiceImpl implements WmsOmsLogisticsStatusTransService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	OrderStatusService orderStatusService;
	@Autowired
	OrderSubService orderSubService;
    @Autowired
    OrderMainService orderMainService;
    @Autowired
    OrderNoService orderNoService;
    @Autowired
    XMLConverter xmlConverterOrder;
 
    @Autowired
    CommonUtilService commonUtilService;
	/**
	 * 功能描述: 创建订单的WMS状态
	 */
	@Override
	public CommonOutputDTO updateOrderLogisticsStatus(WmsOmsReceiveLogisticsDTO wmsOmsReceiveLogisticsDTO) {
		
		CommonOutputDTO output = new CommonOutputDTO();
		// WMS状态码:00 订单下达仓库（00）;40 订单分配库存（40）;50 订单在拣货（50）;
		// 60 订单拣货完成（60）;62 订单打包中（62）;63 订单装箱完成（63）;80 订单已发出（80）
		String logistics_status = wmsOmsReceiveLogisticsDTO.getLogisticsStatus();
		String orderSubNo = wmsOmsReceiveLogisticsDTO.getOrderSubNo();
		OrderMain om = orderMainService.findByOrderNo(orderNoService.getOrderNoBySubNo(orderSubNo));
		if(om == null){
	          // 无法找到订单
            output.setCode(CommonConstService.FAILED);
            output.setMsg(":无法找到订单orderSubNo["+ orderSubNo + "]。");
            return output;
		}
		boolean isPayOnArrival = (om.getIfPayOnArrival() != null && 1L == om.getIfPayOnArrival().longValue());
		// 获取orderActionNo
		OrderStatusAction orderStatusAction = getOrderStatusAction(logistics_status, isPayOnArrival);
		if (orderStatusAction == null) {
			// 无法找到相应的状态码
			output.setCode(CommonConstService.FAILED);
			output.setMsg(wmsOmsReceiveLogisticsDTO.getOrderSubNo() + ":无法找到相应的状态码["+ wmsOmsReceiveLogisticsDTO.getLogisticsStatus() + "]。");
			return output;
		}

		// 调用公共service，更新订单物流状态
		String operator = wmsOmsReceiveLogisticsDTO.getOperator();
		Date operateTime = null;
        try {
            operateTime = commonUtilService.format24Hours().parse(wmsOmsReceiveLogisticsDTO.getOperateTime());
        } catch (ParseException e) {
            logger.info("{}",e);
        }
		boolean flag = orderStatusService.saveProcess(wmsOmsReceiveLogisticsDTO.getOrderSubNo(), orderStatusAction, operator, operateTime, null);
		if (flag) {
			// 更新物流商
			if (CommonConstService.WMS_LogiStatus_80.equals(logistics_status)) {
				OrderSub os = orderSubService.get(OrderSub_.orderSubNo, wmsOmsReceiveLogisticsDTO.getOrderSubNo());
				String deliMercNo = wmsOmsReceiveLogisticsDTO.getDeliveryMerchantNo();
				String logiOutNo = wmsOmsReceiveLogisticsDTO.getLogisticsOutNo();
                if (!StringUtils.isBlank(deliMercNo)) {
                    os.setDeliveryMerchantNo(wmsOmsReceiveLogisticsDTO.getDeliveryMerchantNo());//物流商
                }
                if (!StringUtils.isBlank(logiOutNo)) {
                    os.setLogisticsOutNo(logiOutNo);//运单号
                }
                //箱号
                String bolNo = wmsOmsReceiveLogisticsDTO.getBolNo();
                if(!StringUtils.isBlank(bolNo)){
                    if(bolNo.length()>500){
                        bolNo = bolNo.substring(0,500); //长度过长，则截取500
                    }
                    os.setBolNo(bolNo);
                }
                
				orderSubService.save(os);
			}
			// 接口调用成功
			output.setCode(CommonConstService.OK);
			output.setMsg(wmsOmsReceiveLogisticsDTO.getOrderSubNo() + ":接口调用成功。");

		} else {
			// 接口调用失败
			output.setCode(CommonConstService.FAILED);
			output.setMsg(wmsOmsReceiveLogisticsDTO.getOrderSubNo() + ":上传状态与前一状态不匹配。");
		}

		
		return output;
	}

	/**
	 * 根据WMS或者TMS传递的状态码，获取orderActionNo
	 * 
	 * @param logistics_status
	 * @return orderStatusAction
	 */
	private OrderStatusAction getOrderStatusAction(String logistics_status, boolean isPayOnArrival) {

		// 00订单下达仓库
		if (logistics_status.equals(CommonConstService.WMS_LogiStatus_00)) {
			// return OrderStatusAction.S060102;
			// 40 订单分配库存完成
		} else if (logistics_status.equals(CommonConstService.WMS_LogiStatus_40)) {
			return OrderStatusAction.S060102;
			// 60 订单在拣货完成
		} else if (logistics_status.equals(CommonConstService.WMS_LogiStatus_60)) {
			return OrderStatusAction.S060203;
			// 63 订单装箱完成
		} else if (logistics_status.equals(CommonConstService.WMS_LogiStatus_63)) {
			return OrderStatusAction.S060304;
			// 80 订单已发出
		} else if (logistics_status.equals(CommonConstService.WMS_LogiStatus_80)) {
			if(isPayOnArrival){
			    return OrderStatusAction.S016071;
			}else{
			    return OrderStatusAction.S016070;
			}
		    
		}
		return null;
	}
	
//    @Override
//    public String queryToTmsStr(String orderSubNo) {
//
//        if (StringUtils.isEmpty(orderSubNo)) {
//            return null;
//        }
//        OrderMain om = orderMainService.getByOrderSubNo(Long.valueOf(orderSubNo));
//        if (null == om || om.getOrderSubs().isEmpty()) {
//            return null;
//        }
//        OrderSub os = om.getOrderSubs().get(0);
//
//        TmsOrderDTO tDTO = new TmsOrderDTO();
//        try {
//            // 订单创建时间
//            String curDateTimeStr = DateUtil.getStringFormatByDate(DateUtil.getNowDate(),
//                    DateUtil.FORMAT_GENERALDATETIME);
//            tDTO.setCreateTime(curDateTimeStr);
//
//            // 订单编号(内部单号)(子订单)
//            tDTO.setOrderid(CommonUtilService.Long2Str(os.getIdOrder()));
//            // 物流商家ID
//            tDTO.setLogisticCompanyId(os.getDeliveryMerchantNo());
//            // 物流订单号(外部编号)
//            tDTO.setTxLogisticID(os.getOrderSubNo());
//            // 正向销售订单无原销售子订单号
//            tDTO.setSrcOrderNo("");
//            // 正常销售订单类型
//            tDTO.setType(CommonConstService.TMS_TYPE_OS);
//            // 订单标识
//            tDTO.setFlag(CommonConstService.TMS_FLAG_NORMAL);
//            // 收件人姓名
//            tDTO.setName(os.getUserName());
//            // 收件人详细地址
//            tDTO.setAddress(os.getAddressDetail());
//            // 收件人详细地址编号(最末尾的层级栏目编号)
//            tDTO.setAddresscode(os.getAddressCode());
//            // 获取省、市、区
//            Map<String, TransportArea> areasMap = transportAreaService.getAllByAreaId(os.getAddressCode());
//            if (areasMap != null && !areasMap.isEmpty()) {
//                TransportArea province = areasMap.get(TransportAreaService.Treelevel_Province);
//                tDTO.setProv(province == null ? "" : province.getAreaName());
//                TransportArea city = areasMap.get(TransportAreaService.Treelevel_City);
//                tDTO.setCity(city == null ? "" : city.getAreaName());
//                TransportArea county = areasMap.get(TransportAreaService.Treelevel_Area);
//                tDTO.setArea(county == null ? "" : county.getAreaName());
//            }
//            // 收件人移动电话
//            tDTO.setMobile(os.getMobPhoneNum());
//            // 收件人固定电话
//            tDTO.setPhone(os.getPhoneNum());
//            // 收件人邮编
//            tDTO.setPostCode(os.getPostCode());
//            // 是否已开发票
//            if (null != om.getNeedInvoice()) {
//                tDTO.setNeedInvoice(om.getNeedInvoice().intValue());
//            }
//            // 商品金额
//            tDTO.setGoodsValue(om.getTotalProductPrice().doubleValue());
//            // 代收货款金额
//            tDTO.setItemsValue(om.getTotalPayAmount().doubleValue());
//            // 总件数
//            tDTO.setTotalPCS(om.getOrderItems().size());
//            // 商品总重量
//            tDTO.setTotalWeight(om.getWeight().doubleValue());
//            // 保值金额（暂时没有使用，默认为0.0）
//            tDTO.setInsuranceValue(0.0d);
//            // 箱签号
//            tDTO.setToid("");
//            // 配送方式
//            tDTO.setDeliverymode(Integer.parseInt(os.getDistributeType()));
//            tDTO.setOuthousetime(curDateTimeStr);// 出库时间
//            tDTO.setReviewtime(curDateTimeStr);// 订单复核时间
//            // 物流订单类型
//            tDTO.setWmsOrderType(CommonConstService.TMS_FLAG_NORMAL);
//            // 发送至activeMQ
//            String output = xmlConverterOrder.convertFromObjectToXMLString(tDTO);
//            return output;
//        } catch (Exception e) {
//            logger.info("{}", e);
//        }
//        return null;
//    }
}

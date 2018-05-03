package com.ibm.oms.rs.service.hessian.impl.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ibm.oms.client.dto.order.OrderLogisticsMessageClientDTO;
import com.ibm.oms.client.dto.order.OrderSplitLogisticsClientDTO;
import com.ibm.oms.client.dto.order.ProductImg;
import com.ibm.oms.client.dto.order.UpdateReturnPfAsnBean;
import com.ibm.oms.client.intf.OrderLogisticsClient;
import com.ibm.oms.dao.constant.LogisticsMessage;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderLogisticsMessage;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.OrderLogisticsMessageService;
import com.ibm.oms.service.OrderLogisticsService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.util.CommonUtilService;
import com.ibm.oms.service.util.MD5Util;
import com.ibm.product.dto.SkuOrderBean;
import com.ibm.product.intf.SkuClientService;

/**
 * Description: //模块目的、功能描述
 * 
 * @author Yusl Date: 2018年3月1日
 */

@Repository("orderLogisticsClient")
public class OrderLogisticsClientImpl implements OrderLogisticsClient {

	@Autowired
	private OrderLogisticsMessageService orderLogisticsMessageService;

	@Autowired
	private OrderLogisticsService orderLogisticsService;

	@Autowired
	SkuClientService skuClientService;
	@Autowired
	private OrderMainService orderMainService;

	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private CommonUtilService commonUtilService;

	/*
	 * 通过子订单号查询物流信息
	 * 
	 * @see
	 * com.ibm.oms.client.intf.IOrderClient#findOrderLogisticsMessageByOrderNo(
	 * java.lang.String)
	 */
	@Override
	public List<OrderLogisticsMessageClientDTO> findOrderLogisticsMessageByOrderNo(String orderId) {
		return orderLogisticsMessageService.findOrderLogisticsMessageByOrderSubNo(orderId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.oms.client.intf.OrderLogisticsClient#
	 * createOrderLogisticsMessageByorderNo(java.util.Map)
	 */
	@Override
	public String createOrderLogisticsMessage(String sign, String param) {
		String key = MD5Util.MD5Encode(param + "Dusto", "UTF-8");

		if (1 == 1) {
			orderLogisticsMessageService.saveOrderLogisticsMessage(param);
			orderLogisticsService.saveOrderLogistic(param);
			Map ret = new HashMap();
			ret.put("result", "true");
			ret.put("returnCode", "200");
			ret.put("message", "成功");
			return JSONObject.toJSONString(ret);
		} else {

			Map ret = new HashMap();
			ret.put("result", "false");
			ret.put("returnCode", "300");
			ret.put("message", "失败，密钥对比错误");

			return JSONObject.toJSONString(ret);
		}
	}

	/*
	 * 快递100订阅接口
	 * 
	 * @see com.ibm.oms.client.intf.OrderLogisticsClient#createOrderLogistics
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public String createOrderLogistics(UpdateReturnPfAsnBean upd) {
		String number = upd.getShippingOrderNo();
		String logisticsProviderCode = upd.getLogisticsProviderCode();

		Map param = new HashMap();
		Map parameters = new HashMap();
		param.put("company", logisticsProviderCode);
		param.put("number", number);
		param.put("key", "JnHkSDSX8845");
		parameters.put("callbackurl", "http://122.228.147.82:8083/interf-rf/kuaidi100/wmsinfo");
		parameters.put("salt", "Dusto");
		parameters.put("resultv2", "1");
		parameters.put("autoCom", "1");
		param.put("parameters", parameters);

		String message = commonUtilService.jsonPost("http://10.0.4.15:8083/interf-rf/kuaidi100/wmsno", JSON.toJSONString(param), String.class, null);
		return message;
	}

	/*
	 * 导购端物流信息查询接口
	 * 
	 * @see
	 * com.ibm.oms.client.intf.OrderLogisticsClient#findOrderLogisticsByOrderNo(
	 * java.lang.String)
	 */
	@Override
	public List<OrderSplitLogisticsClientDTO> findOrderLogisticsByOrderNo(String orderNo) {
		OrderMain om = orderMainService.findByOrderNo(orderNo);
		List<OrderSplitLogisticsClientDTO> OrderSplitLogisticsClientDTOs= new ArrayList<OrderSplitLogisticsClientDTO>();
		Integer flag = om.getIsSplit();
		if (flag == 1) {
			List<OrderMain> oms = orderMainService.findOrderSplitlist(orderNo, om.getOrderNoP());
			for (OrderMain orderMain : oms) {
				OrderSplitLogisticsClientDTO osLogisticsClientDTO = new OrderSplitLogisticsClientDTO();
				if (orderMain.getOrderSubs().size() != 0)

				{
					OrderSub os = orderMain.getOrderSubs().get(0);
					List<OrderLogisticsMessage> olms = orderLogisticsMessageService.findOrderLogisticsMessageByTeackingNumber(os.getLogisticsOutNo());
					List<OrderLogisticsMessageClientDTO> OrderLogisticsMessageClientDTOs = new ArrayList<OrderLogisticsMessageClientDTO>();
					
					for (OrderLogisticsMessage orderLogisticsMessage : olms) {
						OrderLogisticsMessageClientDTO olm = new OrderLogisticsMessageClientDTO();
						olm.setOrderNo(orderLogisticsMessage.getOrderSubNo());
						olm.setWmsDesc(orderLogisticsMessage.getWmsDesc());
						olm.setWmsTime(orderLogisticsMessage.getWmsTime());
						olm.setTeackingNumber(orderLogisticsMessage.getTeackingNumber());
						olm.setState(LogisticsMessage.getDesc(orderLogisticsMessage.getState()));
						OrderLogisticsMessageClientDTOs.add(olm);
					}
					List<OrderItem> ots = orderItemService.getByOrdeSubNo(os.getOrderSubNo());
					String ordersource = om.getOrderSource();
				

					List<ProductImg> productImgs = new ArrayList<ProductImg>();
					for (OrderItem orderItem : ots) {
						ProductImg productImg = new ProductImg();
						SkuOrderBean sob = skuClientService.findSkuBySkuCode(orderItem.getSkuNo(),orderMain.getRegionCode());
						productImg.setProductID(sob.getProductNo());
						productImg.setProductImgUrl(sob.getSkuImages());
						productImg.setProductName(sob.getProductName());
						productImgs.add(productImg);
					}
					osLogisticsClientDTO.setWmsInfoList(OrderLogisticsMessageClientDTOs);
					osLogisticsClientDTO.setAddressDetail(os.getAddressDetail());
					osLogisticsClientDTO.setWmsName(os.getDeliveryMerchantName());
					osLogisticsClientDTO.setWmsNo(os.getShippingOrderNo());
					osLogisticsClientDTO.setProductImgs(productImgs);
				}
				OrderSplitLogisticsClientDTOs.add(osLogisticsClientDTO);
			}

		}else{

			OrderSplitLogisticsClientDTO osLogisticsClientDTO = new OrderSplitLogisticsClientDTO();
			OrderSub os = om.getOrderSubs().get(0);
			List<OrderLogisticsMessage> olms = orderLogisticsMessageService
					.findOrderLogisticsMessageByTeackingNumber(os.getLogisticsOutNo());

			List<OrderLogisticsMessageClientDTO> OrderLogisticsMessageClientDTOs = new ArrayList<OrderLogisticsMessageClientDTO>();
			for (OrderLogisticsMessage orderLogisticsMessage : olms) {
				OrderLogisticsMessageClientDTO olm = new OrderLogisticsMessageClientDTO();
				olm.setOrderNo(orderLogisticsMessage.getOrderSubNo());
				olm.setWmsDesc(orderLogisticsMessage.getWmsDesc());
				olm.setWmsTime(orderLogisticsMessage.getWmsTime());
				olm.setTeackingNumber(orderLogisticsMessage.getTeackingNumber());
				olm.setState(LogisticsMessage.getDesc(orderLogisticsMessage.getState()));
				OrderLogisticsMessageClientDTOs.add(olm);
			}
			List<OrderItem> ots = orderItemService.getByOrdeSubNo(os.getOrderSubNo());
			String ordersource = om.getOrderSource();

			List<ProductImg> productImgs = new ArrayList<ProductImg>();
			for (OrderItem orderItem : ots) {
				ProductImg productImg = new ProductImg();
				SkuOrderBean sob = skuClientService.findSkuBySkuCode(orderItem.getSkuNo(), om.getRegionCode());
				productImg.setProductID(sob.getProductNo());
				productImg.setProductImgUrl(sob.getSkuImages());
				productImgs.add(productImg);
			}
			osLogisticsClientDTO.setWmsInfoList(OrderLogisticsMessageClientDTOs);
			osLogisticsClientDTO.setAddressDetail(os.getAddressDetail());
			osLogisticsClientDTO.setWmsName(os.getDeliveryMerchantName());
			osLogisticsClientDTO.setWmsNo(os.getLogisticsOutNo());
			osLogisticsClientDTO.setProductImgs(productImgs);
		
			

			OrderSplitLogisticsClientDTOs.add(osLogisticsClientDTO);
		}

		return OrderSplitLogisticsClientDTOs;
	}

}

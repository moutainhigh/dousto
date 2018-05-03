/**
 * 
 */
package com.ibm.oms.admin.action.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.ibm.oms.dao.constant.OrderColumn;
import com.ibm.oms.domain.persist.DistributeAddress;
import com.ibm.oms.domain.persist.IncludeOrNot;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.oms.domain.persist.OrderPayInfo;
import com.ibm.oms.domain.persist.OrderPayMode;
import com.ibm.oms.domain.persist.OrderSearch;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.domain.persist.TransportArea;
import com.ibm.oms.intf.constant.OrderMainConst;
import com.ibm.oms.service.OrderSearchService;
import com.ibm.sc.model.shipping.SelfTakePoint;

/**
 * @author xiaonanxiang
 * 
 */
@ParentPackage("admin")
public class OrderExcelAction extends ExportExcelAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 713657284207289329L;

	private static final Long ONE = 1L;

	private Integer column;

	@Resource
	private OrderSearchService orderSearchService;

	private OrderSearch orderSearch;

	private OrderMain orderMain = new OrderMain();

	private OrderItem orderItem = new OrderItem();

	private OrderPay orderPay = new OrderPay();

	private OrderSub orderSub = new OrderSub();
	private OrderPayMode orderPayMode =new OrderPayMode();
	/**
	 * 配送地址
	 */
	protected TransportArea transportArea = new TransportArea();

	/**
	 * 自提点
	 */
	protected SelfTakePoint selfTakePoint = new SelfTakePoint();

	/**
	 * 区域
	 */
	protected DistributeAddress distributeAddress = new DistributeAddress();
	protected IncludeOrNot includeOrNot = new IncludeOrNot();
	protected static Map<String, String> orderCategoryNameMap = new HashMap<String, String>();
	{
		orderCategoryNameMap.put(
				OrderMainConst.OrderMain_OrderCategory_Return.getCode(),
				OrderMainConst.OrderMain_OrderCategory_Return.getDesc());
		orderCategoryNameMap.put(
				OrderMainConst.OrderMain_OrderCategory_Change.getCode(),
				OrderMainConst.OrderMain_OrderCategory_Change.getDesc());
		orderCategoryNameMap.put(
				OrderMainConst.OrderMain_OrderCategory_ChangeOut.getCode(),
				OrderMainConst.OrderMain_OrderCategory_ChangeOut.getDesc());
		orderCategoryNameMap.put(
				OrderMainConst.OrderMain_OrderCategory_Reject.getCode(),
				OrderMainConst.OrderMain_OrderCategory_Reject.getDesc());
	}


	@Override
	public String[] getTitiles() {
		String[] title = null;
		// 逆向订单
		if (OrderColumn.ORDER_REVERSE == column) {
			// 写头
			title = new String[] { "编号", "主订单号", "子订单号", "类型", "关联订单编号",
					"下单日期", "审核状态", "退款状态", "物流状态", "处理状态",  "经手人",
					"审核人", "外部渠道订单号","订单审核时间" };
		} 
		// 退款单
		else if (OrderColumn.ORDER_DRAWBACK == column) {
		    // 写头
            title = new String[] { "单号", "类型", "关联销售单号", "下单时间", "退款完成时间", "原订单完成时间", "审核状态", "退款状态", "处理状态", "退款明细",
                    "操作人" };
        }
		else // 非逆向订单
		{
			// 写头
			title = new String[] { "编号", "主订单号", "子订单号", "下单日期", "收件人","地址", /*"商品总价",
					"折扣优惠金额", "运费总额",*/ "审核状态", "支付状态", "物流状态", "处理状态", "支付方式",
					"配送方式", "审核人", "外部渠道订单号", "订单渠道 ", "预警", "大客户", "商品","订单审核时间" };
		}
		return title;
	}

	@Override
	public List<String[]> getDataList() {
		List<String[]> dataList = new ArrayList<String[]>();
		String[] rec = null;
		// 逆向订单
		if (OrderColumn.ORDER_REVERSE == column) {
			orderSearch = new OrderSearch(orderMain, orderSub, orderItem,
					orderPay,orderPayMode );
			List<OrderSearch> orderSearchList = (List<OrderSearch>) orderSearchService
					.findByOrderSearch(column, orderSearch);
			// 使用条件
			for (OrderSearch c : orderSearchList) {
				rec = new String[14];
				rec[0] = c.getOrderSubId() + "";
				rec[1] = "\t" + c.getOrderNo();
				rec[2] = "\t" + c.getOrderSubNo();
				rec[3] = orderCategoryNameMap.get(c.getOrderCategory());
				rec[4] = "\t" + c.getOrderRelatedOrigin();
				rec[5] = "\t" + c.getOrderTime();
				rec[6] = c.getStatusConfirmName();
				rec[7] = c.getStatusPayName();
				rec[8] = c.getLogisticsStatusName();
				rec[9] = c.getStatusTotalName();
				/*String ifNeedRefund = "";
				if (ONE.equals(c.getIfNeedRefund())) {
					ifNeedRefund = "是";
				} else {
					ifNeedRefund = "否";
				}
				rec[10] = ifNeedRefund;*/
				rec[10] = c.getCreatedBy();
				rec[11] = c.getConfirmerName();
				rec[12] = c.getAliasOrderNo() + "";
				rec[13] = "\t" +  c.getConfirmTime();

				for (int i = 0; i < rec.length; i++) {
					if ((null == rec[i]) || ("".equals(rec[i]))
							|| ("null").equalsIgnoreCase(rec[i])
							|| "￥null".equals(rec[i])
							|| "\tnull".equals(rec[i])) {
						rec[i] = "";
					}
				}
				dataList.add(rec);
			}
		}
		// 退款单
        else if (OrderColumn.ORDER_DRAWBACK == column) {
            orderSearch = new OrderSearch(orderMain, orderSub, orderItem,
                    orderPay, transportArea, distributeAddress, selfTakePoint,includeOrNot,orderPayMode);
            List<OrderSearch> orderSearchList = (List<OrderSearch>) orderSearchService
                    .findByOrderSearch(column, orderSearch);
            // 使用条件
            for (OrderSearch c : orderSearchList) {
                rec = new String[11];
                rec[0] = "\t" + c.getOrderSubNo();
                rec[1] = orderCategoryNameMap.get(c.getOrderCategory());
                rec[2] = "\t" + c.getOrderSubRelatedOrigin();
                rec[3] = "\t" + c.getOrderTime();
                rec[4] = "\t" + c.getFinishTime();
                rec[5] = "\t" + c.getRelatedFinishTime();
                rec[6] = c.getStatusConfirmName();
                rec[7] = c.getStatusPayName();
                rec[8] = c.getStatusTotalName();
                String payType = "";
                List<OrderPayInfo> orderPayInfoList = c.getOrderPayInfoList();
                if(null != orderPayInfoList && orderPayInfoList.size() > 0)
                {
                    for (int i = 0; i < orderPayInfoList.size(); i++) {
                        String payName = orderPayInfoList.get(i).getPayName();
                        BigDecimal payAmountTmp = orderPayInfoList.get(i).getPayAmount();
                        payAmountTmp = payAmountTmp == null ? new BigDecimal(0) : payAmountTmp;
                        BigDecimal payAmount = payAmountTmp.setScale(2, BigDecimal.ROUND_HALF_UP);
                        if (i == orderPayInfoList.size()-1) {
                            payType += payName + ":￥" + payAmount;
                        } else {
                            payType += payName + ":￥" + payAmount + " , ";
                        }
                    }
                }
                rec[9] = payType;
                rec[10] = c.getUpdatedBy();
                dataList.add(rec);
            }
        }
		else // 销售单
		{
			orderSearch = new OrderSearch(orderMain, orderSub, orderItem,
					orderPay, transportArea, distributeAddress, selfTakePoint,includeOrNot,orderPayMode);

			System.out.println("=================================="
					+ orderSearch + "==============================");

			try {
				List<OrderSearch> orderSearchList = (List<OrderSearch>) orderSearchService
						.findByOrderSearch(column, orderSearch);

				// 使用条件
				for (OrderSearch c : orderSearchList) {
					rec = new String[19];
					rec[0] = c.getOrderSubId() + "";
					rec[1] = "\t" + c.getOrderNo();
					rec[2] = "\t" + c.getOrderSubNo();
					rec[3] = "\t" + c.getOrderTime();
					rec[4] = "姓名：" + c.getUserName() + "    手机：" + c.getMobPhoneNum();
					rec[5] = c.getAreaName() + c.getAddressDetail();
					/*rec[5] = "￥" + c.getTotalProductPrice();
					rec[6] = "￥" + c.getDiscountTotal();
					rec[7] = "￥" + c.getTransportFee();*/
					rec[6] = c.getStatusConfirmName();
					rec[7] = c.getStatusPayName();
					rec[8] = c.getLogisticsStatusName();
					rec[9] = c.getStatusTotalName();

					String payType = "";
					List<OrderPayInfo> orderPayInfoList = c.getOrderPayInfoList();
					if(null != orderPayInfoList && orderPayInfoList.size() > 0)
	                {
	                    for (int i = 0; i < orderPayInfoList.size(); i++) {
	                        String payName = orderPayInfoList.get(i).getPayName();
	                        BigDecimal payAmountTmp = orderPayInfoList.get(i).getPayAmount();
	                        payAmountTmp = payAmountTmp == null ? new BigDecimal(0) : payAmountTmp;
	                        BigDecimal payAmount = payAmountTmp.setScale(2, BigDecimal.ROUND_HALF_UP);
	                        if (i == orderPayInfoList.size()-1) {
	                            payType += payName + ":￥" + payAmount;
	                        } else {
	                            payType += payName + ":￥" + payAmount + " , ";
	                        }
	                    }
	                }
					
					/*Map<String, BigDecimal> map = c.getPayNameAmountMap();
					if (!map.isEmpty()) {
						Iterator<String> iterator = null;
						if (null != c.getPayNameAmountMap().keySet()
								&& (c.getPayNameAmountMap().keySet().size() > 0)) {
							iterator = c.getPayNameAmountMap().keySet()
									.iterator();
						}

						if (null != iterator) {
							String key = "";
							while (iterator.hasNext()) {
								key = iterator.next();
								if ("".equals(key))
									continue;
								payType = key + ":￥" + map.get(key) + "  ";
							}
						}
					}*/
					rec[10] = payType;

					rec[11] = c.getDistributeTypeName();
					rec[12] = c.getConfirmerName();
					rec[13] = c.getAliasOrderNo() + "";
					rec[14] = c.getOrderSource();
					String ifWarnOrder = "";
					if (ONE.equals(c.getIfWarnOrder())) {
						ifWarnOrder = "是";
					} else {
						ifWarnOrder = "否";
					}
					rec[15] = ifWarnOrder;

					String ifPriviledgedMember = "";
					if (ONE.equals(c.getIfPriviledgedMember())) {
						ifPriviledgedMember = "是";
					} else {
						ifPriviledgedMember = "否";
					}
					rec[16] = ifPriviledgedMember;
					String item = "";
					List<OrderItem> orderItems = c.getOrderItems();
					if (null != orderItems && orderItems.size() > 0) {
						for (OrderItem orderItem : orderItems) {
							item += "[" + orderItem.getCommodityName() + "  "
									+ orderItem.getSaleNum() + "  " + "￥"
									+ orderItem.getUnitPrice() + "] ";
						}
					}
					rec[17] = item; // 商品
					rec[18] = "\t" + c.getConfirmTime(); // 审核时间
					for (int i = 0; i < rec.length; i++) {
						if ((null == rec[i]) || ("".equals(rec[i]))
								|| ("null").equalsIgnoreCase(rec[i])
								|| "￥null".equals(rec[i])
								|| "\tnull".equals(rec[i])) {
							rec[i] = "";
						}
					}
					dataList.add(rec);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dataList;
	}

	// getter and setter
	public OrderSearch getOrderSearch() {
		return orderSearch;
	}

	public void setOrderSearch(OrderSearch orderSearch) {
		this.orderSearch = orderSearch;
	}

	public OrderMain getOrderMain() {
		return orderMain;
	}

	public void setOrderMain(OrderMain orderMain) {
		this.orderMain = orderMain;
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}

	public OrderPay getOrderPay() {
		return orderPay;
	}

	public void setOrderPay(OrderPay orderPay) {
		this.orderPay = orderPay;
	}

	public OrderSub getOrderSub() {
		return orderSub;
	}

	public void setOrderSub(OrderSub orderSub) {
		this.orderSub = orderSub;
	}

	public Integer getColumn() {
		return column;
	}

	public void setColumn(Integer column) {
		this.column = column;
	}

	public static Map<String, String> getOrderCategoryNameMap() {
		return orderCategoryNameMap;
	}

	public static void setOrderCategoryNameMap(
			Map<String, String> orderCategoryNameMap) {
		OrderExcelAction.orderCategoryNameMap = orderCategoryNameMap;
	}

	public TransportArea getTransportArea() {
		return transportArea;
	}

	public void setTransportArea(TransportArea transportArea) {
		this.transportArea = transportArea;
	}

	public SelfTakePoint getSelfTakePoint() {
		return selfTakePoint;
	}

	public void setSelfTakePoint(SelfTakePoint selfTakePoint) {
		this.selfTakePoint = selfTakePoint;
	}

	public DistributeAddress getDistributeAddress() {
		return distributeAddress;
	}

	public void setDistributeAddress(DistributeAddress distributeAddress) {
		this.distributeAddress = distributeAddress;
	}

	@Override
	public String getFileName() {
		return "order.xls";
	}

	public IncludeOrNot getIncludeOrNot() {
		return includeOrNot;
	}

	public void setIncludeOrNot(IncludeOrNot includeOrNot) {
		this.includeOrNot = includeOrNot;
	}

}

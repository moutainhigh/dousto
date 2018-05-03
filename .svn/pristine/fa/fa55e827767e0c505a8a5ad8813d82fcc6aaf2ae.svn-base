package order;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.oms.dao.constant.OrderStatusAction;
import com.ibm.oms.domain.persist.OrderAuditConfig;
import com.ibm.oms.domain.persist.OrderAuditMerchantConfig;
import com.ibm.oms.domain.persist.OrderAuditRegionConfig;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.service.OrderAuditConfigService;
import com.ibm.oms.service.OrderAuditMerchantConfigService;
import com.ibm.oms.service.OrderAuditRegionConfigService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.OrderStatusService;

/**
 * Description: //模块目的、功能描述
 * 
 * @author YanYanZhang Date: 2018年2月1日
 */
public class AuditConfigTest extends BaseTest {
	// public class AuditConfigTest {
	@Autowired
	private OrderAuditMerchantConfigService orderAuditMerchantConfigService;
	
	@Autowired
	private OrderAuditRegionConfigService orderAuditRegionConfigService;

	@Autowired
	private OrderAuditConfigService orderAuditConfigService;

	@Autowired
	private OrderMainService orderMainService;

	@Autowired
	private OrderStatusService orderStatusService;

	@Autowired
	private OrderNoService orderNoService;

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Test
	public void testc(){
		orderMainService.get(1L);
	}

	@Test
	public void testAudit() {
		OrderAuditConfig orderAuditConfig = orderAuditConfigService.get(1);
		// 初始化不免审区域
		List<OrderAuditRegionConfig> regionConfigs = orderAuditRegionConfigService.getAll();
		// 初始化不免审sku
		List<String> interceptSkus = null;
		if (StringUtils.isNotBlank(orderAuditConfig.getInterceptSkus())) {
			interceptSkus = Arrays.asList(orderAuditConfig.getInterceptSkus().split(","));
		}

		// 将店铺维度审核配置转为映射key:店铺code value:店铺自动审核配置
		List<OrderAuditMerchantConfig> merchantConfigs = orderAuditMerchantConfigService.getAll();
		Map<String, OrderAuditMerchantConfig> merchantConfigMapping = new HashMap<String, OrderAuditMerchantConfig>();
		for (OrderAuditMerchantConfig merchantConfig : merchantConfigs) {
			merchantConfigMapping.put(merchantConfig.getMerchantCode(), merchantConfig);
		}

		if (orderAuditConfig.getEnabled()) {
			OrderMain orderMain = new OrderMain();
			List<OrderMain> orderMains = orderMainService.findOrdersForAutomaticAudit(1000);

			for (OrderMain order : orderMains) {
				OrderAuditMerchantConfig merchantConfig = merchantConfigMapping.get(order.getMerchantNo());
				if (null == merchantConfig) {
					logger.info("订单号:{} 店铺code:{}审核配置不存在", order.getOrderNo(), order.getMerchantNo());
					continue;
				}

				// 延时免审开关
				if (null != orderAuditConfig.getMinutesDelay() && 0 != orderAuditConfig.getMinutesDelay()) {
					if (merchantConfig.getIsDelay()) {
						if (this.hasDelay(order, orderAuditConfig.getMinutesDelay())) {
						} else {
							logger.info("订单号:{} 未到自动审核时间", order.getOrderNo());
							continue;
						}
					} else {
					}
				} else {
				}

				// 收货地址是否属于不免审区域
				if (null != regionConfigs && this.inInterceptRegions(order, regionConfigs)) {
					logger.info("订单:{} 属于不免审区域", order.getOrderNo());
					updateOrderStausForAotoAudit(order.getOrderNo(), false);
					continue;
				} else {
				}

				// 拆分单免审
				if (orderAuditConfig.getIsApprovedOrderSplit() && merchantConfig.getIsApprovedOrderSplit()) {
				} else {
					if (orderMain.getIsSplit() == 1) {
						logger.info("订单:{} 属于不免审拆分单", order.getOrderNo());
						updateOrderStausForAotoAudit(order.getOrderNo(), false);
						continue;
					} else {
					}
				}

				// 促销单免审
				if (orderAuditConfig.getIsApprovedOrderPromotion()) {
				} else {
					if (!orderMain.getOrderPromotions().isEmpty()) {
						logger.info("订单:{} 属于不免审促销单", order.getOrderNo());
						updateOrderStausForAotoAudit(order.getOrderNo(), false);
						continue;
					}
				}

				// 免审金额区间判断，店铺维度优先，店铺维度配置为空时，以系统配置优先
				if (null != merchantConfig.getMaxAmount() && 1 == merchantConfig.getMaxAmount().compareTo(BigDecimal.valueOf(0))) {
					if (-1 == orderMain.getTotalPayAmount().compareTo(merchantConfig.getMinAmount())
							|| 1 == orderMain.getTotalPayAmount().compareTo(merchantConfig.getMaxAmount())) {
						logger.info("订单:{} 属于不免审金额", order.getOrderNo());
						updateOrderStausForAotoAudit(order.getOrderNo(), false);
						continue;
					} else {
					}
				} else {
					if (-1 == orderMain.getTotalPayAmount().compareTo(orderAuditConfig.getMinAmount())
							|| 1 == orderMain.getTotalPayAmount().compareTo(orderAuditConfig.getMaxAmount())) {
						logger.info("订单:{} 属于不免审金额", order.getOrderNo());
						updateOrderStausForAotoAudit(order.getOrderNo(), false);
						continue;
					} else {
					}
				}

				// 忽略客户留言
				if (orderAuditConfig.getIsIgnoredClientRemark() && merchantConfig.getIsIgnoredClientRemark()) {
				} else {
					if (StringUtils.isNoneBlank(orderMain.getClientRemark())) {
						logger.info("订单:{} 属于不免审客户留言", order.getOrderNo());
						updateOrderStausForAotoAudit(order.getOrderNo(), false);
						continue;
					} else {
					}
				}

				// 忽略备注
				if (orderAuditConfig.getIsIgnoredClientServiceRemark() && merchantConfig.getIsIgnoredClientServiceRemark()) {
				} else {
					if (StringUtils.isNoneBlank(orderMain.getClientServiceRemark())) {
						logger.info("订单:{} 属于不免审备注", order.getOrderNo());
						updateOrderStausForAotoAudit(order.getOrderNo(), false);
						continue;
					} else {
					}
				}

				// 货到付款免审
				if (orderAuditConfig.getIsApprovedPayOnArrival()) {
				} else {
					if (orderMain.getIfPayOnArrival() == 1) {
						logger.info("订单:{} 属于不免审货到付款", order.getOrderNo());
						updateOrderStausForAotoAudit(order.getOrderNo(), false);
						continue;
					} else {
					}
				}

				// 换货免审
				if (orderAuditConfig.getIsApprovedOrderBarter()) {
				} else {
					if (orderMain.getIsBarter() == 1) {
						logger.info("订单:{} 属于不免审换货", order.getOrderNo());
						updateOrderStausForAotoAudit(order.getOrderNo(), false);
						continue;
					} else {
					}
				}

				// 合并单免审
				if (orderAuditConfig.getIsApprovedOrderMerge()) {
				} else {
					if (orderMain.getIsMerge() == 1) {
						logger.info("订单:{} 属于不免审合并单", order.getOrderNo());
						updateOrderStausForAotoAudit(order.getOrderNo(), false);
						continue;
					} else {
					}
				}

				// 单品免审
				if (orderAuditConfig.getIsApprovedSingleProduct()) {
				} else {
					if (!this.isSingleProduct(orderMain)) {
						logger.info("订单:{} 属于不免审单品", order.getOrderNo());
						updateOrderStausForAotoAudit(order.getOrderNo(), false);
						continue;
					}
				}

				// 指定sku不免审
				if (null == interceptSkus) {
				} else {
					if (this.hasInterceptSku(orderMain, interceptSkus)) {
						logger.info("订单:{} 属于不免审sku", order.getOrderNo());
						updateOrderStausForAotoAudit(order.getOrderNo(), false);
						continue;
					}
				}
				// 审核通过
				updateOrderStausForAotoAudit(order.getOrderNo(), true);
			}

		}

	}

	@Test
	public void testAddDate() {
		Calendar calendar = Calendar.getInstance();

		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime()));
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime()));
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime()));
	}

	/**
	 * 自动审核更新订单状态 Description:
	 * 
	 * @param orderNo
	 * @param result
	 *            审核结果 true/false
	 */
	private void updateOrderStausForAotoAudit(String orderNo, boolean result) {
		String defaultSubNo = orderNoService.getDefaultOrderSubNoByOrderNo(orderNo);
		if (result) {
			orderStatusService.saveProcess(defaultSubNo, OrderStatusAction.S014050, null, null, null);
		} else {
			orderStatusService.saveProcess(defaultSubNo, OrderStatusAction.S014041, null, null, null);
		}
	}

	/**
	 * 满足延时审单条件 Description:
	 * 
	 * @param order
	 * @param delayMinutes
	 * @return
	 */
	private boolean hasDelay(OrderMain order, int delayMinutes) {
		Calendar calendar = Calendar.getInstance();
		calendar.roll(Calendar.MINUTE, -delayMinutes);
		if (calendar.getTime().getTime() > order.getDateCreated().getTime()) {
			return true;
		}
		return false;
	}

	/**
	 * 是否在不免审区域 Description:
	 * 
	 * @param order
	 * @param interceptRegions
	 * @return
	 */
	private boolean inInterceptRegions(OrderMain order, List<OrderAuditRegionConfig> regionConfigs) {
		for (OrderAuditRegionConfig regionConfig : regionConfigs) {
			for (OrderSub orderSub : order.getOrderSubs()) {
				String regionPrefix = regionConfig.getCode().substring(0, regionConfig.getLevel()*2-1);
				if (orderSub.getAddressCode().indexOf(regionPrefix) > 0) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断是否是单品 Description:
	 * 
	 * @param order
	 * @return
	 */
	private boolean isSingleProduct(OrderMain order) {
		int sum = 0;
		for (OrderItem orderItem : order.getOrderItems()) {
			sum += orderItem.getSaleNum();
			if (sum > 1) {
				return true;
			}
		}
		return false;
	}

	private boolean hasInterceptSku(OrderMain order, List<String> skus) {
		for (OrderItem orderItem : order.getOrderItems()) {
			if (skus.contains(orderItem.getSkuNo())) {
				return true;
			}
		}
		return false;
	}
}

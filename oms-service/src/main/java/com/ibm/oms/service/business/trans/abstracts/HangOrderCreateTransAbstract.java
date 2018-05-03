package com.ibm.oms.service.business.trans.abstracts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.dao.intf.HangOrderMainDao;
import com.ibm.oms.domain.persist.HangOrderInvoice;
import com.ibm.oms.domain.persist.HangOrderItem;
import com.ibm.oms.domain.persist.HangOrderMain;
import com.ibm.oms.domain.persist.HangOrderPay;
import com.ibm.oms.domain.persist.HangOrderPromotion;
import com.ibm.oms.domain.persist.HangOrderSub;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderOutputDTO;
import com.ibm.oms.intf.intf.HangOrderReceiveOrderDTO;
import com.ibm.oms.intf.intf.inner.HangOrderInvoiceDTO;
import com.ibm.oms.intf.intf.inner.HangOrderItemDTO;
import com.ibm.oms.intf.intf.inner.HangOrderMainDTO;
import com.ibm.oms.intf.intf.inner.HangOrderPayDTO;
import com.ibm.oms.intf.intf.inner.HangOrderPromotionDTO;
import com.ibm.oms.intf.intf.inner.HangOrderSubDTO;
import com.ibm.oms.service.HangOrderInvoiceService;
import com.ibm.oms.service.HangOrderItemService;
import com.ibm.oms.service.HangOrderMainService;
import com.ibm.oms.service.HangOrderPayService;
import com.ibm.oms.service.HangOrderPromotionService;
import com.ibm.oms.service.HangOrderSubService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.trans.HangOrderCreateTrans;
import com.ibm.oms.service.util.CommonConstService;
import com.ibm.oms.service.util.CommonUtilService;
import com.ibm.sc.service.impl.BaseServiceImpl;

public abstract class HangOrderCreateTransAbstract extends BaseServiceImpl<HangOrderMain, Long> implements HangOrderCreateTrans {

	@Autowired
	CommonUtilService commonUtilService;

	@Autowired
	private HangOrderSubService hangOrderSubService;

	@Autowired
	private HangOrderInvoiceService hangOrderInvoiceService;

	@Autowired
	private HangOrderItemService hangOrderItemService;

	@Autowired
	private HangOrderPayService hangOrderPayService;

	@Autowired
	private HangOrderPromotionService hangOrderPromotionService;

	@Autowired
	private HangOrderMainService hangOrderMainService;
	
	@Autowired
	OrderNoService orderNoService;
	
	/**
	 * @param hangOrderMainDao
	 */
	@Autowired
	public void setHangOrderMainDao(HangOrderMainDao hangOrderMainDao) {
		super.setBaseDao(hangOrderMainDao);
	}

	@Override
	public List<HangOrderMain> saveOrder(HangOrderReceiveOrderDTO hangOrderReceiveDIO) {
		List<HangOrderMainDTO> homDTOList = hangOrderReceiveDIO.getOmDTO();
		List<HangOrderMain> hangOrders = new ArrayList<HangOrderMain>();
		for (HangOrderMainDTO homDTO : homDTOList) {
			hangOrders.add(saveHangOrderMainSingle(homDTO));
		}
		return hangOrders;
	}

	// 查询挂单头
	@Override
	public List<HangOrderMain> queryHangOrderMain(String shopNo, String startDate, String endDate, String status) {
		List<HangOrderMain> hom = hangOrderMainService.queryHangOrderMain(shopNo, startDate, endDate, status);
		return hom;
	}

	// 查询挂单详情信息
	@Override
	public List<HangOrderMain> queryHangOrderMainDetail(String OrderNo) {
		List<HangOrderMain> hom = hangOrderMainService.queryHangOrderMainDetail(OrderNo);
		return hom;
	}

	// 删除挂单
	@Override
	public BtcOmsReceiveOrderOutputDTO deleteHangOrderByOrderNo(String OrderNo) {
		BtcOmsReceiveOrderOutputDTO output = new BtcOmsReceiveOrderOutputDTO();
		try {
			hangOrderMainService.deleteHangOrderByOrderNo(OrderNo);
			output.setSucceed(CommonConstService.OK);
			output.setMessage("挂单删除成功");
		} catch (Exception e) {
			output.setSucceed(CommonConstService.FAILED);
			output.setMessage("挂单删除失败" + e.toString());
		}
		return output;
	}

	// 修改挂单
	@Override
	public BtcOmsReceiveOrderOutputDTO updateHangOrder(HangOrderReceiveOrderDTO hangOrderReceiveDIO) {
		BtcOmsReceiveOrderOutputDTO output = new BtcOmsReceiveOrderOutputDTO();
		try {
			// 挂单主表添加信息
			HangOrderMain hom = addHangOrderMain(hangOrderReceiveDIO.getOmDTO().get(0));
			hangOrderMainService.updateHangOrder(hom);
			String OrderNo = hangOrderReceiveDIO.getOmDTO().get(0).getOrderNo();

			// 根据挂单主表OrderNo查找id
			long orderId = hangOrderMainService.queryIdByOrderNo(OrderNo);

			hom.setId(orderId);

			// 删除子表
			hangOrderPromotionService.deleteByIdOrder(orderId);
			hangOrderPayService.deleteByIdOrder(orderId);
			hangOrderInvoiceService.deleteByIdOrder(orderId);
			hangOrderItemService.deleteByIdOrder(orderId);
			hangOrderSubService.deleteByIdOrder(orderId);

			List<HangOrderMainDTO> homDTO = hangOrderReceiveDIO.getOmDTO();
			// 写子订单,订单行，订单行级促销
			saveHangOrderSub(homDTO.get(0), hom);
			// 写订单级促销，
			saveHangOrderPromo(homDTO.get(0), hom);
			// 订单头支付
			saveHangOrderPay(homDTO.get(0), hom);
			output.setSucceed(CommonConstService.OK);
			output.setMessage("挂单修改成功");
		} catch (Exception e) {
			output.setSucceed(CommonConstService.FAILED);
			output.setMessage("挂单修改失败" + e.toString());
		}
		return output;
	}

	/*
	 * private void save(HangOrderMainDTO hangOrderMainDTO) { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 */

	/**
	 * 写主订单，子订单，订单级促销
	 **/
	protected HangOrderMain saveHangOrderMainSingle(HangOrderMainDTO omDTO) {
		// 主订单
		HangOrderMain om = saveHangOrderMain(omDTO);
		// 写子订单,订单行，订单行级促销
		om.setHangOrderSubs(saveHangOrderSub(omDTO, om));
		// 写订单级促销，
		//saveHangOrderPromo(omDTO, om);
		// 订单头支付
		//saveHangOrderPay(omDTO, om);
		return om;
	}

	protected HangOrderMain saveHangOrderMain(HangOrderMainDTO homDTO) {
		HangOrderMain hom = addHangOrderMain(homDTO);
		save(hom);
		// 订单编号
		hom.setOrderNo(orderNoService.getOrderNoByOrderId(hom.getId() + ""));
        update(hom);
		return hom;
	}

	/** 写子订单表 **/
	protected List<HangOrderSub> saveHangOrderSub(HangOrderMainDTO orderMainDTO, HangOrderMain hom) {
		List<HangOrderSubDTO> osDTOs = orderMainDTO.getOsDTOs();
		if (osDTOs == null || osDTOs.size() == 0) {
			return null;
		}
		Date date = new Date();
		List<HangOrderSub> hosList = new ArrayList<HangOrderSub>();
		for (int subIndex = 0; subIndex < osDTOs.size(); subIndex++) {
			HangOrderSubDTO osDTO = osDTOs.get(subIndex);
			// 写子订单
			HangOrderSub hos = new HangOrderSub();
			hos.setIdOrder(hom.getId());
			hos.setOrderNo(hom.getOrderNo());
			hos.setOrderSubNo(orderNoService.getOrderSubNoByOrderNo(hom.getOrderNo(), subIndex + 1));
			hos.setDeliveryMerchantNo(osDTO.getDeliveryMerchantNo());
			// 配送方式
			hos.setDistributeType(osDTO.getDistributeType());
			// 物流订单外部编号
			hos.setLogisticsOutNo(osDTO.getLogisticsOutNo());
			// 期望送达日期
			hos.setHopeArrivalTime(CommonUtilService.strToDate(osDTO.getHopeArrivalTime(), "yyyy-MM-dd HH:mm:ss"));
			// 配送优先级
			hos.setDeliveryPriority(osDTO.getDeliveryPriority());
			// 运费
			hos.setTransportFee(commonUtilService.string2BigDecimal(osDTO.getTransportFee()));
			// 供应地点
			hos.setProvideAddress(osDTO.getProvideAddress());
			// 自提点
			hos.setSelfFetchAddress(osDTO.getSelfFetchAddress());
			// 自提时间
			hos.setPickTime(CommonUtilService.strToDate(osDTO.getPickTime(), "yyyy-MM-dd"));
			// 门店代码
			hos.setStoreNo(osDTO.getStoreNo());
			// 送货备注
			hos.setDeliveryRemark(osDTO.getDeliveryRemark());
			// 外部系统订单号
			hos.setAliasOrderSubNo(osDTO.getAliasOrderSubNo());
			// 收货人姓名
			hos.setUserName(osDTO.getUserName());
			// 收货人电话
			hos.setPhoneNum(osDTO.getPhoneNum());
			// 收货人邮编
			hos.setPostCode(osDTO.getPostCode());
			// 收货人邮箱
			hos.setEmail(osDTO.getPostCode());
			// 收货人地址信息编码
			hos.setAddressCode(osDTO.getAddressCode());
			// 收货人具体地址
			hos.setAddressDetail(osDTO.getAddressDetail());

			ObjectMapper mapper = new ObjectMapper();
			String itemSnapshotStr = null;
			try {
				itemSnapshotStr = mapper.writeValueAsString(hos);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			hangOrderSubService.save(hos);
			// 写发票
			HangOrderInvoiceDTO orderInvoice = osDTO.getHangOrderInvoice();
			if (orderInvoice != null) {
				HangOrderInvoice hoi = new HangOrderInvoice();
				hoi.setOrderNo(hom.getOrderNo());
				hoi.setOrderSubNo(hos.getOrderSubNo());
				hoi.setIdOrderSub(hos.getId());
				hoi.setInvoiceType(orderInvoice.getInvoiceType());
				hoi.setInvoiceHead(orderInvoice.getInvoiceHead());
				hoi.setInvoiceContent(orderInvoice.getInvoiceContent());
				hoi.setInvoiceAdditionInfo(orderInvoice.getInvoiceAdditionInfo());
				hoi.setInvoiceNum(orderInvoice.getInvoiceNum());
				hoi.setInvoiceCompany(orderInvoice.getInvoiceCompany());
				hoi.setInvoiceTaxpayer(orderInvoice.getInvoiceTaxpayer());
				hoi.setRegistryAddress(orderInvoice.getRegistryAddress());
				hoi.setInvoiceTelephone(orderInvoice.getInvoiceTelephone());
				hoi.setInvoiceBankName(orderInvoice.getInvoiceBankName());
				hoi.setInvoiceBankAccount(orderInvoice.getInvoiceBankAccount());
				hoi.setInvoiceAddress(orderInvoice.getInvoiceAddress());
				hoi.setInvoiceToName(orderInvoice.getInvoiceToName());
				hoi.setInvoiceToTelephone(orderInvoice.getInvoiceToTelephone());
				hoi.setDateCreated(date);
				hoi.setDateUpdated(date);
				hoi.setIdOrder(hom.getId());
				hangOrderInvoiceService.save(hoi);
			}
			// 写订单行
			saveHangOrderItem(osDTO, hos, hom);
		}
		return hosList;
	}

	/** 写订单行表 **/
	protected void saveHangOrderItem(HangOrderSubDTO hosDTO, HangOrderSub os, HangOrderMain om) {
		List<HangOrderItemDTO> hoiDTOs = hosDTO.getOiDTOs();
		if (hoiDTOs == null || hoiDTOs.size() == 0) {
			return;
		}
		int index = 1;
		for (HangOrderItemDTO hoiDTO : hoiDTOs) {
			HangOrderItem hoi = new HangOrderItem();
			hoi.setOrderNo(om.getOrderNo());
			hoi.setOrderItemNo(orderNoService.getOrderItemNoBySubOrderNo(os.getOrderSubNo(), index++));
			hoi.setIdOrder(os.getIdOrder());
			hoi.setIdOrderSub(os.getId());
			hoi.setAliasOrderNo(hoiDTO.getAliasOrderNo());
			hoi.setAliasOrderSubNo(hoiDTO.getAliasOrderSubNo());
			hoi.setAliasOrderItemNo(hoiDTO.getAliasOrderItemNo());
			hoi.setCommodityCode(hoiDTO.getCommodityCode());
			hoi.setBarCode(hoiDTO.getBarCode());
			hoi.setSkuNo(hoiDTO.getSkuNo());
			hoi.setCommodityName(hoiDTO.getSkuNo());
			hoi.setProductCategory(hoiDTO.getProductCategory());
			hoi.setSupplierCode(hoiDTO.getSupplierCode());
			hoi.setSaleNum(Long.parseLong(hoiDTO.getSaleNum()));
			hoi.setUnitPrice(commonUtilService.string2BigDecimal(hoiDTO.getUnitPrice()));
			hoi.setUnitDeductedPrice(commonUtilService.string2BigDecimal(hoiDTO.getUnitDeductedPrice()));
			hoi.setPayAmount(commonUtilService.string2BigDecimal(hoiDTO.getPayAmount()));
			hoi.setStockNo(hoiDTO.getSkuNo());
			hoi.setWarehouseNo(hoiDTO.getWarehouseNo());
			hoi.setHasGift(hoiDTO.getHasGift());
			hoi.setPromotionType(hoiDTO.getPromotionType());
			hoi.setPromotionCode(hoiDTO.getPromotionCode());
			if (hoiDTO.getProductPoint() != null && !"".equals(hoiDTO.getProductPoint())) {
				hoi.setProductPoint(Integer.parseInt(hoiDTO.getProductPoint()));
			}
			hoi.setInfoSource(hoiDTO.getInfoSource());
			hoi.setAdsPage(hoiDTO.getAdsPage());
			hoi.setOrderItemRemark(hoiDTO.getOrderItemRemark());
			Date date = new Date();
			hoi.setDateCreated(date);
			hoi.setDateUpdated(date);
			hangOrderItemService.save(hoi);
		}

	}

	/** 订单支付 **/
	protected void saveHangOrderPay(HangOrderMainDTO homDTO, HangOrderMain om) {
		List<HangOrderPayDTO> opayList = homDTO.getOrderPayDTOs();
		if (opayList == null || opayList.isEmpty()) {
			return;
		}
		// 写orderPay
		for (HangOrderPayDTO opDTO : opayList) {
			Date date = new Date();
			HangOrderPay opm = new HangOrderPay();
			opm.setPayAmount(commonUtilService.string2BigDecimal(opDTO.getPayAmount()));
			opm.setIdOrder(om.getId());
			opm.setOrderNo(om.getOrderNo());
			opm.setPayCode(opDTO.getPayCode());
			opm.setPayName(opDTO.getPayName());
			opm.setCardNo(opDTO.getBankTypeCode());
			opm.setPayTime(commonUtilService.strToDate(opDTO.getPayTime(), "YYYY-MM-DD"));
			opm.setCardNo(opDTO.getBankTypeCode());
			opm.setDateCreated(date);
			opm.setDateUpdated(date);
			hangOrderPayService.save(opm);
		}

	}

	protected void saveHangOrderPromo(HangOrderMainDTO omDTO, HangOrderMain om) {
		List<HangOrderPromotionDTO> opDTOs = omDTO.getOpDTOs();
		if (opDTOs == null || opDTOs.size() == 0) {
			return;
		}
		for (HangOrderPromotionDTO opDTO : opDTOs) {
			HangOrderPromotion hop = new HangOrderPromotion();
			hop.setIdOrder(om.getId());
			hop.setOrderNo(om.getOrderNo());
			hop.setPromoLevel(opDTO.getPromoLevel());
			hop.setOrderItemNo(opDTO.getIdOrderItem());
			hop.setPromoNo(opDTO.getPromoNo());
			hop.setPromoName(opDTO.getPromoName());
			hop.setPromoType(opDTO.getPromoType());
			hop.setTicketBundleNo(opDTO.getTicketBundleNo());
			hop.setTicketNo(opDTO.getTicketNo());
			hop.setTicketAmount(commonUtilService.string2BigDecimal(opDTO.getTicketAmount()));
			hop.setPointCount(Integer.parseInt(opDTO.getPointCount()));
			hop.setMemberNo(opDTO.getMemberNo());
			hangOrderPromotionService.save(hop);
		}
	}

	private HangOrderMain addHangOrderMain(HangOrderMainDTO homDTO) {
		HangOrderMain hom = new HangOrderMain();
		// 订单创建
		Date date = new Date();
		// 外部订单号
		hom.setAliasOrderNo(homDTO.getAliasOrderNo());
		// 订单类型
		hom.setOrderType(homDTO.getOrderType());
		// 订单来源
		hom.setOrderSource(homDTO.getOrderSource());
		// 订单状态
		if (homDTO.getIsSuspension() != null && !"".equals(homDTO.getIsSuspension())) {
			hom.setIsSuspension(Integer.parseInt(homDTO.getIsSuspension()));
		}
		// 会员ID
		hom.setMemberNo(homDTO.getMemberNo());
		// 会员名称
		hom.setCustomerName(homDTO.getCustomerName());
		// 会员电话
		hom.setCustomerPhone(homDTO.getCustomerPhone());
		// 会员email
		hom.setCustomerEmail(homDTO.getCustomerEmail());
		// 商品金额总计
		hom.setTotalProductPrice(commonUtilService.string2BigDecimal(homDTO.getTotalProductPrice()));
		// 折扣总额
		hom.setDiscountTotal(commonUtilService.string2BigDecimal(homDTO.getDiscountTotal()));
		// 优惠券总额
		hom.setTotalPromo(commonUtilService.string2BigDecimal(homDTO.getTotalPromo()));
		// 运费
		hom.setTransportFee(commonUtilService.string2BigDecimal(homDTO.getTransportFee()));
		// 订单实际应付金额总计
		hom.setTotalPayAmount(commonUtilService.string2BigDecimal(homDTO.getTotalPayAmount()));
		// 商家类型d
		hom.setMerchantType(homDTO.getMerchantType());
		// 商家编号
		hom.setMerchantNo(homDTO.getShopNo());
		// 赠送总积分
		if (homDTO.getTotalGivePoints() != null && !"".equals(homDTO.getTotalGivePoints())) {
			hom.setTotalGivePoints(Integer.parseInt(homDTO.getTotalGivePoints()));
		}
		// 是否有发票
		if (homDTO.getNeedInvoice() != null && !"".equals(homDTO.getNeedInvoice())) {
			hom.setNeedInvoice(Integer.parseInt(homDTO.getNeedInvoice()));
		}
		// hom.setNeedInvoice(Integer.parseInt(homDTO.getNeedInvoice()));
		// 订单产生时间
		hom.setOrderTime(CommonUtilService.strToDate(homDTO.getOrderTime(), "yyyy-MM-dd HH:mm:ss"));
		// ip地址
		hom.setIp(homDTO.getIp());
		// 订单属性
		if (homDTO.getBillType() != null && !"".equals(homDTO.getBillType())) {
			hom.setBillType(Integer.parseInt(homDTO.getBillType()));
		}
		return hom;
	}

}

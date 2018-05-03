package com.ibm.oms.service.business.saleAfter.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.client.dto.saleAfterOrder.ReturnHeaderOrderFromLasaDto;
import com.ibm.oms.client.dto.saleAfterOrder.ReturnItemOrderFromLasaDto;
import com.ibm.oms.client.dto.saleAfterOrder.SaleAfterOrderDetail;
import com.ibm.oms.client.dto.saleAfterOrder.SaleAfterOrderInfoDto;
import com.ibm.oms.client.dto.saleAfterOrder.SaleAfterProductInfo;
import com.ibm.oms.client.dto.saleAfterOrder.ShopSaleAfterOrderQueryDto;
import com.ibm.oms.dao.intf.OrderMainDao;
import com.ibm.oms.dao.intf.OrderRetChgItemDao;
import com.ibm.oms.dao.saleAfterOrder.SaleAfterOrderDao;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderRetChgItem;
import com.ibm.oms.domain.persist.OrderSearch;
import com.ibm.oms.domain.persist.OrderStatusLog;
import com.ibm.oms.service.OrderStatusLogService;
import com.ibm.oms.service.business.saleAfter.SaleAfterService;
import com.ibm.product.dto.ImageDto;
import com.ibm.product.dto.SkuOrderBean;
import com.ibm.product.intf.SkuClientService;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.util.DateUtils;

/**
 * 退换货数据 查询 服务 
 * 
 * 
 */
@Service("saleAfterService")
public class SaleAfterServiceImpl implements SaleAfterService {
	@Autowired
    private SaleAfterOrderDao saleAfterOrderDao;
	
	@Autowired
    private OrderRetChgItemDao orderRetChgItemDao;
	
	@Autowired
	private OrderStatusLogService orderStatusLogService;
	
	@Autowired
	private OrderMainDao orderMainDao;
	
	@Autowired 
    SkuClientService skuClientService;

    /**
     * 退货单列表查询
     * 
     * @param dto
     * @param pager
     * @return 分页
     */
    public Pager findSaleAfterOrderPage(ShopSaleAfterOrderQueryDto dto) throws ParseException{
    	Pager<?> pager = new Pager();
		pager.setPageSize(10);
    	return saleAfterOrderDao.findSaleAfterOrder(dto, pager);
    }
    
    /**
     * 退货单列表查询
     * 
     * @param dto
     * @return list
     */
    public List<ReturnHeaderOrderFromLasaDto> findSaleAfterOrder(ShopSaleAfterOrderQueryDto dto) throws ParseException{
    	List<ReturnHeaderOrderFromLasaDto> headDataList = new ArrayList<ReturnHeaderOrderFromLasaDto>();
    	List<OrderSearch> list = saleAfterOrderDao.findSaleAfterOrder(dto);
    	if(list != null && list.size() > 0){
    		for(OrderSearch o : list){
    			ReturnHeaderOrderFromLasaDto headData = new ReturnHeaderOrderFromLasaDto();
    			headData.setReturnNo(o.getOrderNo());//退货单号
    			headData.setReturnType(o.getOrderCategory());//退货类型
//    			headData.setReturnReason(o.get);//退货原因
    			headData.setReturnMode(o.getDistributeTypeName());//退货方式
    			headData.setReturnStatus(o.getStatusTotal());//退货单状态
    			headData.setReturnShopNo(o.getMerchantNo());//退货门店编号
    			headData.setOrderSalesNo(o.getSalesClerkNo());//下单营业员编号
//    			returnSalesNo//退货受理导购员编号
//    			headData.setReturnStartTime(DateUtils.formatDate(o.getRetPreStartTime()));//退货预约开始时间
//    			headData.setReturnEndTime(DateUtils.formatDate(o.getRetPreEndTime()));//退货预约结束时间
    			headData.setReturnAddr(o.getAddressDetail());//退货详细地址
    			headData.setReceiverName(o.getUserName());//接收人姓名
    			headData.setReceiverPhone(o.getPhoneNum());//接收人电话
    			headData.setMemberNo(o.getMemberNo());//会员代号   			
    			if(o.getTotalPayAmount() != null){
    				headData.setTotalReturnAmount(o.getTotalPayAmount().toString());//退款金额合计
    			}
//    			memberAccountName//会员账户姓名
    			headData.setOrderNo(o.getOrderRelatedOrigin());//原订单号
//    			wmsID//库存ID
//    			headData.setMemberName(o.getM);memberName//会员名称
//    			headData.setSubmitDate(DateUtils.formatDate(o.getDateCreated()));//退货申请时间
    			headData.setRemark(o.getRemark());//备注
//    			attatch//附件
    			if(o.getDiscountTotal() != null){
    				headData.setTotalDiscountAmount(o.getDiscountTotal().toString());//订单折扣总金额
    			}
//    			headData.setTotalCouponsAmount(o.get);//订单优惠券总金额
    			//积分
//    			headData.setExpressNo(o.get);//快递单号
    			headData.setChannelSource(o.getOrderSource());//渠道来源
    			
    			headDataList.add(headData);	
    		}
    	}    	
        return headDataList;
    }
    
    /**
     * 退货单详细查询
     * 
     * @param orderNo
     * @return list
     */
    public List<ReturnItemOrderFromLasaDto> findSaleAfterOrderDetail(String orderNo){
    	List<ReturnItemOrderFromLasaDto> itemList = new ArrayList<ReturnItemOrderFromLasaDto>();
    	List<OrderRetChgItem> retItemList = orderRetChgItemDao.getByOrdeNo(orderNo);
    	if(retItemList != null && retItemList.size() > 0){
    		for(OrderRetChgItem i : retItemList){
    			ReturnItemOrderFromLasaDto dto = new ReturnItemOrderFromLasaDto();   			
    			dto.setReturnNo(i.getOrderNo());//退货单号
    			dto.setProductNo(i.getCommodityCode());//商品编码
    			dto.setProductName(i.getCommodityName());//商品名称
    			dto.setProductType(i.getProductCategory());//商品类型
    			dto.setProductSKU(i.getSkuNo());//商品SKU
    			if(i.getProductPoint() != null){
    				dto.setProductPoint(i.getProductPoint().toString());//商品积分
    			}
    			if(i.getNormalPrice() != null){
    				dto.setProductAmount(i.getNormalPrice());//商品原价
    			}
    			if(i.getUnitDeductedPrice() != null){
    				dto.setDiscountAmount(i.getUnitDeductedPrice());//商品折扣金额
    			}
    			if(i.getCouponTotalMoney() != null){
    				dto.setCouponsAmount(i.getCouponTotalMoney());//优惠券金额
    			}
    			if(i.getSaleNum() != null){
    				dto.setReturnQuantity(i.getSaleNum().intValue());//本次退货数量
    			}
    			if(i.getPayAmount() != null){
    				dto.setConfirmAmount(i.getPayAmount());//确认退款金额
    			}
//    			dto.setHasGift(i.getHasGift());//是否是赠品
//   			giftProductNo	//	赠品主商品编码
    			
    			itemList.add(dto);
    		}
    	}
    	return itemList;
    }

    /**
     * 退货单数查询
     * 
     * @param dto
     * @return
     */
    public int countSaleAfterOrder(ShopSaleAfterOrderQueryDto dto){
    	return saleAfterOrderDao.countSaleAfterOrder(dto);
    }
    
    /**
     * 退款历史记录信息 (退换货单状态流转日志)
     * 
     * @param orderNo
     * @return
     */
    public List<SaleAfterOrderInfoDto> findByOrderNo(String orderNo){
    	List<SaleAfterOrderInfoDto> infoList = new ArrayList<SaleAfterOrderInfoDto>();
    	List<OrderStatusLog> logList = orderStatusLogService.findByOrderNo(orderNo);
    	if(logList != null && logList.size() > 0){
    		for(OrderStatusLog o : logList){
    			SaleAfterOrderInfoDto dto = new SaleAfterOrderInfoDto();    			
    			dto.setOrderStatusTime(DateUtils.formatDate(o.getDateCreated()));//退款时间
    			dto.setOrderStatusDesc(o.getCurrentStatus());//退款信息描述
    			dto.setOperatorNm(o.getOperator());//处理人姓名
    			dto.setOperatorNo(o.getOperatorNo());//处理人账号
    			dto.setOperatorRole(o.getOperatorRole());//处理人角色
    			infoList.add(dto);
    		}
    	}
    	return infoList;
    }

    /**
     * 退款信息查询
     * 
     * @param dto
     * @return
     */
    public SaleAfterOrderInfoDto findByOrderItemNo(ShopSaleAfterOrderQueryDto dto){
    	SaleAfterOrderInfoDto infoDto = new SaleAfterOrderInfoDto();
    	OrderRetChgItem retItem = orderRetChgItemDao.getByOrderRetChgItemNo(dto.getReturnItemNo());
    	if(retItem != null){
    		
//    		explain//注意事项
//    		infoDto.setRefundLogList();//退款信息
//    		infoDto.setRefundMsg();//退款信息提示
//    		infoDto.setReturnType();//退货方式选项
//    		infoDto.setRefundStatus();//退款状态
    		infoDto.setRefundReason(retItem.getReason());//退款原因
    		if(retItem.getPayAmount() != null){
    			infoDto.setRefundAmount(retItem.getPayAmount().toString());//退款金额
    		}
    		if(retItem.getProductPoint() != null){
    			infoDto.setDeductionPoint(retItem.getProductPoint().toString());//扣除积分
    		}
//    		deductionPointDesc//扣除积分描述
    		infoDto.setApplyCount(retItem.getSaleNum());//申请件数
    		if(retItem.getDateCreated() != null){
    			infoDto.setApplyDate(DateUtils.formatDate(retItem.getDateCreated()));//申请时间
    		}
//    		refundNo//退款编号 
    	}
    	return infoDto;
    }
    
    /**
     * 售后详情
     * 
     * @param orderNo
     * @return
     */
    public SaleAfterOrderDetail findSaleAfterDetailInfo(String orderNo){
    	SaleAfterOrderDetail detail = new SaleAfterOrderDetail();
    	ShopSaleAfterOrderQueryDto dto = new ShopSaleAfterOrderQueryDto();
    	dto.setReturnNo(orderNo);
    	try{
    		OrderMain orderMain = orderMainDao.findByOrderNo(orderNo);
    		//退换货单信息
    		List<ReturnHeaderOrderFromLasaDto> headDataList = findSaleAfterOrder(dto);
    		if(headDataList != null && headDataList.size() > 0){
    			ReturnHeaderOrderFromLasaDto headDto = headDataList.get(0);
    			detail.setSerNo(headDto.getReturnNo());//售后单号
    			detail.setOrderNo(headDto.getOrderNo());//订单编号
    			detail.setMemberNo(headDto.getMemberNo());//会员编号
    			if(orderMain != null){
    				detail.setBuyDate(DateUtils.formatDate(orderMain.getDateCreated()));//购买时间
    			}
    			detail.setApplyDate(headDto.getSubmitDate());//申请售后时间
    			detail.setChannel(headDto.getChannelSource());//购买渠道
    			detail.setHandleResult(headDto.getReturnStatus());//处理情况
    			detail.setHandleType(headDto.getReturnType());//处理方式
    			detail.setApplyReason(headDto.getReturnReason());//申请原因
    		}
    		//退换货单明细信息
    		List<SaleAfterProductInfo> productList = new ArrayList<SaleAfterProductInfo>();
    		List<ReturnItemOrderFromLasaDto> itemList = findSaleAfterOrderDetail(orderNo);
    		if(itemList != null && itemList.size() > 0){
    			for(ReturnItemOrderFromLasaDto itemDto : itemList){
    				SaleAfterProductInfo product = new SaleAfterProductInfo();
    				product.setProductNo(itemDto.getProductNo());//货号
    				if(itemDto.getConfirmAmount() != null){
    					product.setBuyPrice(itemDto.getConfirmAmount().toString());//购买价格
    				}
    				product.setProductSKU(itemDto.getProductSKU());//SKU    				
    				//图片（数组）
    				List<String> suoImgList = new ArrayList<String>();
    				SkuOrderBean sob=skuClientService.findSkuBySkuCode(itemDto.getProductSKU(), orderMain.getRegionCode());
    				if(sob != null){
    					List<ImageDto> imageList = sob.getSkuImages();
    					if(imageList != null){
    						for(ImageDto imageDto : imageList){
    							if("1".equals(imageDto.getImgType())){//图片类型：1,缩略图;2,详情图;3,搭配图
    								suoImgList.add(imageDto.getImgSource());
    							}
    						}
    					}   					
    				}
    				product.setImgList(suoImgList);//商品图片 缩略图   				
    				productList.add(product);
    			}
    			detail.setDto(productList);
    		}
    		return detail;
    	}catch(Exception e){
    		e.getMessage();
    	}
    	return detail;
    }
}
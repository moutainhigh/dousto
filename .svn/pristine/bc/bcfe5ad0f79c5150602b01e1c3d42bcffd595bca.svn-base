package com.ibm.oms.service.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beans.stock.constant.ClientConstant;
import com.ibm.oms.dao.constant.CancelOrderConst;
import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.dao.constant.OrderStatusAction;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.domain.persist.OrderMain_;
import com.ibm.oms.domain.persist.OrderPay;
import com.ibm.oms.domain.persist.OrderRetChgItem;
import com.ibm.oms.domain.persist.OrderSub;
import com.ibm.oms.intf.constant.OrderMainConst;
import com.ibm.oms.intf.intf.InventoryLockOutputDTO;
import com.ibm.oms.intf.intf.ResultDTO;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.business.OrderCreateService;
import com.ibm.oms.service.business.OrderNoService;
import com.ibm.oms.service.business.OrderStatusService;
import com.ibm.oms.service.business.SaleAfterOrderService;
import com.ibm.oms.service.business.trans.SaleAfterOrderTransService;
import com.ibm.oms.service.util.CommonConstService;
import com.ibm.stock.dto.MessageDto;
import com.ibm.stock.dto.SkuStockOperateDto;
import com.ibm.stock.dto.SkuStockOperateHeaderDto;
import com.ibm.stock.dto.SkuStockOperateLineDto;
import com.ibm.stock.intf.StockDeductByOrderService;
import com.ibm.stock.intf.StockLockByOrderService;

@Service("saleAfterOrderService")
public class SaleAfterOrderServiceImpl implements SaleAfterOrderService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    OrderNoService orderNoService;
    @Autowired
    SaleAfterOrderTransService saleAfterOrderTransService;
    @Autowired
    OrderCreateService orderCreateService;
    @Autowired
    OrderStatusService orderStatusService;
    @Autowired
    OrderMainService orderMainService;
    @Autowired
    StockLockByOrderService stockLockByOrderService;
    @Autowired
    StockDeductByOrderService stockDeductByOrderService;

    /**
     * 创建退、换、拒收货单据 （数据组装可参考单元测试：OrderRetChangeServiceImplTest）
     * 
     * @param catalogryType 退换货类型：退货，换货，拒收
     * @param orderMain 退、换货、拒收主订单
     * @param orderSubs 子订单（需包含明细OrderRetChgItem，且明细需设置refOrderItemId、refOrderItemNo）
     * @param orderItem 明细
     * @param applySource 申请来源：B2C,订单客服
     * @param orderPay 退款明细
     */
    public ResultDTO createOrderRetChange(String orderCategory, String applySource, OrderMain orderMain, OrderSub orderSub,
            List<OrderPay> orderPays) {
    	
        //添加意向订单
    	ResultDTO dto = saleAfterOrderTransService.saveOrderRetChange(orderCategory, applySource, orderMain, orderSub, orderPays);
        
    	//换货意向单：需对出库单进行库存锁定
    	if(orderCategory.equals(OrderMainConst.OrderMain_OrderCategory_Change.getCode())){
    	    if(dto.getResultObj()!=null && (dto.getResultObj() instanceof String)){
    	        String orderNo = (String)dto.getResultObj(); //换货出库单的主订单号
                //锁定库存（换货出库单）
                //InventoryLockOutputDTO output = orderCreateService.inventoryLock(orderNo);
                
                SkuStockOperateDto stockDto = new SkuStockOperateDto();
                List<SkuStockOperateHeaderDto> listHeaders = new ArrayList<SkuStockOperateHeaderDto>();
                SkuStockOperateHeaderDto stockHeaderDto = new SkuStockOperateHeaderDto();
                stockHeaderDto.setOrderNo(orderNo);//订单号
                //stockHeaderDto.setOrderNo("5512380746");
                stockHeaderDto.setOrderType("4");//1、调拨单， 2、退库单，3、销售单、4、换货单
                stockHeaderDto.setShopCode(ClientConstant.ESTORE_WAREHOUSE_CODE);//
                stockHeaderDto.setCreator("oms");//

                List<SkuStockOperateLineDto> listLines = new ArrayList<SkuStockOperateLineDto>();
                List<OrderRetChgItem> items = orderSub.getOrderRetChgItems();
                for (OrderRetChgItem item : items) {          
                    SkuStockOperateLineDto stockLineDto = new SkuStockOperateLineDto();
                    if(item.getSaleNum() != null){
                    	stockLineDto.setQty(item.getSaleNum().toString());//数量
                    }
                    stockLineDto.setSkuCode(item.getSkuNo());//商品SKU
                    //stockLineDto.setSkuCode("P2500000215342S3");//商品SKU
                    stockLineDto.setItemNo(item.getRefOrderItemNo());
                    //stockLineDto.setItemNo("55123807450101");
                    stockLineDto.setStockType("1");//1:正常
                    listLines.add(stockLineDto);
                }
                stockHeaderDto.setListLines(listLines);
                listHeaders.add(stockHeaderDto);
                stockDto.setListHeaders(listHeaders);
                
                MessageDto msgDto = stockLockByOrderService.stockFrozenLock(stockDto);
                /*if(msgDto.isSuccess()){
                //MessageDto msgDto = stockLockByOrderService.stockReleaseLock(stockDto);
                	
                	MessageDto msgDto2 = stockDeductByOrderService.stockSubtract(stockDto);
                	if(msgDto2.isSuccess()){
                		//MessageDto msgDto3 = stockDeductByOrderService.stockIncrease(stockDto);
                		
                	}
                }*/
                if(msgDto.isSuccess()){
                //if(NumberUtils.toInt(output.getReturn_status())>=0){
                    String orderSubNo = orderNoService.getDefaultOrderSubNoByOrderNo(orderNo);
                    // 更新订单状态扭转
                    orderStatusService.saveProcess(orderSubNo, OrderStatusAction.S011020, null, null, null);
                    orderStatusService.saveProcess(orderSubNo, OrderStatusAction.S012040, null, null, null);
                    orderStatusService.saveProcess(orderSubNo, OrderStatusAction.S014041, null, null, null);
                    //因为S011020会将支付状态修改为货到付款未支付，这里需根据是否有物流费用来修改支付状态
                    OrderMain mainOut = orderMainService.getByField(OrderMain_.orderNo, orderNo);
                    if (null==mainOut.getTransportFee() || mainOut.getTransportFee().doubleValue() <= 0) {
                        mainOut.setStatusPay(OrderStatus.Order_PayStatus_Success.getCode());
                        orderMainService.update(mainOut);
                    }
                }else{
                    //锁库存失败
                    //dto.setErrorMessage(CommonConstService.SimPrefix + output.getReturn_msg());
                    dto.setErrorMessage(CommonConstService.SimPrefix + msgDto.getMsg());
                }
            }else{
                dto.setErrorMessage(errorMsg_AddExcOrder_Fail);//出库单单号为空
            }
    	}
    	
        return dto;
    }
    
    public ResultDTO cancelOrder(String orderSubNo, CancelOrderConst cancelSceneEnum){
        ResultDTO ret = saleAfterOrderTransService.doSaveCancelOrder(orderSubNo, cancelSceneEnum);
        //取消失败
       if(ret.getResult() == -1){
            return ret;
       }
        boolean dealCoupon = orderCreateService.returnCouponForCancel(orderNoService.getOrderNoBySubNo(orderSubNo));
        if(!dealCoupon){
            //购物券处理成功
            ret.setResult(-1);
            ret.setErrorMessage("取消成功,购物券处理异常,子单号:"+ orderSubNo);
        }
        return ret;
    }

	/* (non-Javadoc)
	 * @see com.ibm.oms.service.business.SaleAfterOrderService#cancelOrderByService(java.lang.String, com.ibm.oms.dao.constant.CancelOrderConst)
	 */
	@Override
	public ResultDTO cancelOrderByService(String orderSubNo, CancelOrderConst cancelScene) {

        ResultDTO ret = saleAfterOrderTransService.doSaveCancelOrderByService(orderSubNo, cancelScene);
        //取消失败
       if(ret.getResult() == -1){
            return ret;
       }
        boolean dealCoupon = orderCreateService.removeCouponForCancel(orderNoService.getOrderNoBySubNo(orderSubNo));
        if(!dealCoupon){
            //购物券处理成功
            ret.setResult(-1);
            ret.setErrorMessage("取消成功,购物券处理异常,子单号:"+ orderSubNo);
        }
        orderCreateService.cancelOrderReturnPoint(orderNoService.getOrderNoBySubNo(orderSubNo));
        return ret;
	}
}

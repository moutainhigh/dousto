package com.ibm.oms.service.business.trans.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.domain.persist.OrderCombineRelation;
import com.ibm.oms.domain.persist.OrderItem;
import com.ibm.oms.intf.intf.CombinePriceDTO;
import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.intf.intf.WmsReceiveCostPriceDTO;
import com.ibm.oms.service.IntfReceivedService;
import com.ibm.oms.service.OrderCombineRelationService;
import com.ibm.oms.service.OrderItemService;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.business.trans.WmsOmsCostPriceTransService;
import com.ibm.oms.service.util.CommonConstService;

@Service("wmsOmsCostPriceTransService")
public class WmsOmsCostPriceTransServiceImpl implements WmsOmsCostPriceTransService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    OrderItemService orderItemService;
    @Resource
    IntfReceivedService intfReceived;

    @Resource
    OrderCombineRelationService orderCombineRelationService;
    @Resource
    OrderMainService orderMainService;

    @Override
    public CommonOutputDTO updateOrderCostPrice(WmsReceiveCostPriceDTO receiveCostPriceDTO) {
        // 记录接口接收的WMS上传数据

        CommonOutputDTO output = new CommonOutputDTO();

        // 根据子订单号获取该订单的订单行信息
        List<OrderItem> orderItemList = orderItemService.getByOrdeSubNo(receiveCostPriceDTO.getOrderSubNo());
        if (orderItemList == null || orderItemList.isEmpty()) {
            // 根据WMS传递的子订单号无法查找到订单
            output.setCode(CommonConstService.FAILED);
            output.setMsg(receiveCostPriceDTO.getOrderSubNo() + ":无法找到该订单。");
            return output;
        }

        String skuNo = null;
        OrderItem orderItem = null;
        OrderCombineRelation comBin = null;
        Map<String, Object> params = null;
        Map<String, Object> orderItemParams = new HashMap<String, Object>();
        ;

        String orderSubNo = receiveCostPriceDTO.getOrderSubNo();
        String barCode = receiveCostPriceDTO.getCommodityCode();

        List<CombinePriceDTO> compDTOs = receiveCostPriceDTO.getCompDTOs();// 判断是否为组合商品
        if (null != compDTOs && compDTOs.size() > 0) {
            orderItemParams.put("orderSubNo", orderSubNo);
            orderItemParams.put("skuNo", barCode);// 组合商品对应商品的skuNo
            orderItem = orderItemService.findByFields(orderItemParams);
        } else {
            orderItemParams.put("orderSubNo", orderSubNo);
            orderItemParams.put("barCode", barCode);// 普通商品对应商品的barcode
            orderItem = orderItemService.findByFields(orderItemParams);
        }

        if (orderItem == null) {
            output.setCode(CommonConstService.FAILED);
            output.setMsg("订单号：" + orderSubNo + ",barCode：" + barCode + "，:无法找到该订单行。");
            return output;
        }
        String orderItemNo = orderItem.getOrderItemNo();

        BigDecimal orderItemInventoryPrice = receiveCostPriceDTO.getInventoryPrice(); // 成本单价
        if (compDTOs != null) {
            BigDecimal totalInvPrice = new BigDecimal(0);
            // 处理组合商品
            for (CombinePriceDTO combinePriceDTO : compDTOs) {
                BigDecimal combPrice = combinePriceDTO.getInventoryPrice();
                params = new HashMap<String, Object>();
                params.put("orderItemNo", orderItemNo);
                skuNo = combinePriceDTO.getSkuNo();
                params.put("skuNo", skuNo);
                comBin = orderCombineRelationService.findByFields(params);
                if (comBin == null) {
                    output.setCode(CommonConstService.FAILED);
                    output.setMsg(receiveCostPriceDTO.getOrderSubNo() + ",skuNo:" + skuNo + ":无法找到该组合产品。");
                    return output;
                }
                comBin.setDateUpdated(new Date());
                comBin.setInventoryPrice(combinePriceDTO.getInventoryPrice());
                totalInvPrice = totalInvPrice.add(combPrice.multiply(new BigDecimal(comBin.getSaleNum())));
                orderCombineRelationService.update(comBin);
            }
            if (totalInvPrice.compareTo(new BigDecimal(0)) > 0) {
                try{
                    //取平均值（组合明细汇总/组合数量）
                    orderItemInventoryPrice = totalInvPrice.divide(new BigDecimal(orderItem.getSaleNum()),2,BigDecimal.ROUND_HALF_UP);    
                }catch(Exception e){
                    logger.info("组合商品"+orderItemNo+"计算组合出库成本价异常："+e.getMessage());
                }
            }
        }
        orderItem.setDateCreated(new Date());
        orderItem.setInventoryPrice(orderItemInventoryPrice);
        orderItemService.update(orderItem);

        // 校验成功，保存接口调用日志
        output.setCode(CommonConstService.OK);

        return output;
    }
}

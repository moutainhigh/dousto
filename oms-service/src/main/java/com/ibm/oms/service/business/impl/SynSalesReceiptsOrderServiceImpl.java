package com.ibm.oms.service.business.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.interf.ws.intf.sap.OrderSapHsService;
import com.ibm.interf.ws.intf.sap.dto.MT_DAILYSALESSHEET;
import com.ibm.interf.ws.intf.sap.dto.MT_DAILYSALESSHEETHEAD;
import com.ibm.interf.ws.intf.sap.dto.MT_DAILYSALESSHEETITEM;
import com.ibm.oms.dao.intf.SynSalesReceiptsOrderSapDao;
import com.ibm.oms.domain.bean.hang.SalesReceiptsOrder;
import com.ibm.oms.domain.bean.hang.SalesReceiptsOrderItem;
import com.ibm.oms.domain.persist.SendSapIntef;
import com.ibm.oms.service.business.SynSalesReceptsOrderService;
import com.ibm.oms.service.util.EmptyUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author: mr.kai
 * @Description: 每天同步销售实收汇总定时serviceImpl
 * @create: 2018-03-19 13:40
 **/
@Service("synSalesReceptsOrderService")
public class SynSalesReceiptsOrderServiceImpl implements SynSalesReceptsOrderService{
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    OrderSapHsService orderSapHsService;
    @Autowired
    SynSalesReceiptsOrderSapDao synSalesReceiptsOrderSapDao;
    @Override
    @Transactional
    public void synSalesReceptsOrder() {
        List<SalesReceiptsOrder> salesReceiptsOrders = synSalesReceiptsOrderSapDao.querySalesReceiptsOrder();
        MT_DAILYSALESSHEET[] s = null ;
        if(EmptyUtils.isNotEmpty(salesReceiptsOrders)){
        	s = new MT_DAILYSALESSHEET[salesReceiptsOrders.size()];
            int i = 0;
            for(SalesReceiptsOrder salesReceiptsOrder:salesReceiptsOrders){
                logger.info("salesReceiptsOrder"+i+" ===>" + JSONObject.fromObject(salesReceiptsOrder).toString());
                MT_DAILYSALESSHEET mt_dailysalessheet = new MT_DAILYSALESSHEET();
                MT_DAILYSALESSHEETHEAD mt_dailysalessheethead = new MT_DAILYSALESSHEETHEAD();
                mt_dailysalessheethead.setCusno2(salesReceiptsOrder.getCusno());
                mt_dailysalessheethead.setOutdate(salesReceiptsOrder.getOutDate());
                mt_dailysalessheethead.setNos(salesReceiptsOrder.getCusnoOutdate());
                mt_dailysalessheet.setHEAD(mt_dailysalessheethead);
                if(EmptyUtils.isNotEmpty(salesReceiptsOrder) && EmptyUtils.isNotEmpty(salesReceiptsOrder.getBillType())&&"-1".equals(salesReceiptsOrder.getBillType()) ){
                    List<SalesReceiptsOrderItem> list =  synSalesReceiptsOrderSapDao.queryReturnSalesReceiptsOrderItem(salesReceiptsOrder);
                    MT_DAILYSALESSHEETITEM[] ITEM = new MT_DAILYSALESSHEETITEM[list.size()];
                    int y = 0;
                    for(SalesReceiptsOrderItem salesReceiptsOrderItem:list){
                        logger.info("salesReceiptsOrderItem"+y+" ===>   " + JSONObject.fromObject(salesReceiptsOrderItem).toString());
                        MT_DAILYSALESSHEETITEM mt_dailysalessheetitem = new MT_DAILYSALESSHEETITEM();
                        mt_dailysalessheetitem.setSku(salesReceiptsOrderItem.getSku());
                        mt_dailysalessheetitem.setPers(salesReceiptsOrderItem.getPers());
                        mt_dailysalessheetitem.setNow_real(salesReceiptsOrderItem.getNowReal());
                        mt_dailysalessheetitem.setNb(salesReceiptsOrderItem.getNb());
                        mt_dailysalessheetitem.setVip_points(salesReceiptsOrderItem.getVipPoints());
                        mt_dailysalessheetitem.setCoupon(salesReceiptsOrderItem.getCoupon());
                        mt_dailysalessheetitem.setDl_site(salesReceiptsOrderItem.getDlSite());
                        ITEM[y] = mt_dailysalessheetitem;
                        y++;
                    }
                    mt_dailysalessheet.setITEM(ITEM);
                }
                if(EmptyUtils.isNotEmpty(salesReceiptsOrder) && EmptyUtils.isNotEmpty(salesReceiptsOrder.getBillType())&&"1".equals(salesReceiptsOrder.getBillType())){
                    List<SalesReceiptsOrderItem> list =  synSalesReceiptsOrderSapDao.querySalesReceiptsOrderItem(salesReceiptsOrder);
                    MT_DAILYSALESSHEETITEM[] ITEM = new MT_DAILYSALESSHEETITEM[list.size()];
                    int z = 0;
                    for(SalesReceiptsOrderItem salesReceiptsOrderItem:list){
                        logger.info("salesReceiptsOrderItem"+z+" ===>   " + JSONObject.fromObject(salesReceiptsOrderItem).toString());
                        MT_DAILYSALESSHEETITEM mt_dailysalessheetitem = new MT_DAILYSALESSHEETITEM();
                        mt_dailysalessheetitem.setPers(salesReceiptsOrderItem.getPers());
                        mt_dailysalessheetitem.setSku(salesReceiptsOrderItem.getSku());
                        mt_dailysalessheetitem.setNow_real(salesReceiptsOrderItem.getNowReal());
                        mt_dailysalessheetitem.setNb(salesReceiptsOrderItem.getNb());
                        mt_dailysalessheetitem.setVip_points(salesReceiptsOrderItem.getVipPoints());
                        mt_dailysalessheetitem.setCoupon(salesReceiptsOrderItem.getCoupon());
                        mt_dailysalessheetitem.setDl_site(salesReceiptsOrderItem.getDlSite());
                        ITEM[z] = mt_dailysalessheetitem;
                        z++;
                    }
                    mt_dailysalessheet.setITEM(ITEM);
                }
                s[i] = mt_dailysalessheet;
                i++;
            }
        }
        Integer i = 0;
        if(EmptyUtils.isNotEmpty(s)){
            try{
                orderSapHsService.importSalesSumData(s);
            }catch (Exception e){
                logger.info("定时同步sap数据出现错误===>  "+ e.getMessage());
                throw new RuntimeException("接口出现错误逻辑终止");
            }
            i = synSalesReceiptsOrderSapDao.updateOrderMain();
        }
        SendSapIntef sendSapIntef = new SendSapIntef();
        sendSapIntef.setCreateDate(new Date());
        sendSapIntef.setDesccription(JSONArray.fromObject(s).toString());
        synSalesReceiptsOrderSapDao.insertSendSapIntf(sendSapIntef);
        logger.info("执行成功====> 中台--->sap 订单统计 推送数据===>   " + JSONArray.fromObject(s).toString() + i + "条数据" );
    }
}

package com.ibm.oms.dao.impl;

import com.ibm.oms.dao.intf.SynSalesReceiptsOrderSapDao;
import com.ibm.oms.domain.bean.hang.PayManner;
import com.ibm.oms.domain.bean.hang.SalesReceiptsOrder;
import com.ibm.oms.domain.bean.hang.SalesReceiptsOrderItem;
import com.ibm.oms.domain.persist.SendSapIntef;
import com.ibm.oms.domain.persist.StatusTransDict;
import com.ibm.sc.dao.impl.BaseDaoImpl;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: mr.kai
 * @Description: 销售实收汇总dao层
 * @create: 2018-03-19 17:02
 **/
@Repository("synSalesReceiptsOrderSapDao")
public class SynSalesReceiptsOrderSapDaoImpl  extends BaseDaoImpl<StatusTransDict,Long> implements SynSalesReceiptsOrderSapDao {
    @Override
    public Long insertSendSapIntf(SendSapIntef sendSapIntef) {
        Long id = (Long)this.getSession().save(sendSapIntef);
        return id;
    }

    @Override
    public Integer updateOrderMain() {
        String sql = "UPDATE order_main om " +
                "SET om.SEND_SAP = '1' " +
                " WHERE " +
                " (om.SEND_SAP = 0 or om.SEND_SAP is null)  " +
                " AND CASE " +
                " WHEN om.BILL_TYPE = 1  " +
                " THEN " +
                " LOCATE('1', om.STATUS_TOTAL) > 0  " +
                " ELSE " +
                " LOCATE('2', om.STATUS_TOTAL) > 0  " +
                "END";
        Query query = getSession().createSQLQuery(sql);
        Integer i = query.executeUpdate();
        return i;
    }

    @Override
    public List<SalesReceiptsOrder> querySalesReceiptsOrder() {
        String sql = "SELECT " +
                " om.PERFORM_STORE_CODE  " +
                ",date_format(om.BALANCE_DATE, '%Y-%m-%d')  " +
                ",concat( " +
                "  om.PERFORM_STORE_CODE, " +
                "  date_format(om.BALANCE_DATE, '%Y-%m-%d'), " +
                "  CEILING(RAND() * 500 + 100) " +
                " ) AS nos  " +
                ",om.BILL_TYPE " +
                "FROM " +
                " order_main om " +
                "WHERE " +
                " (om.SEND_SAP = 0 or om.SEND_SAP is null) " +
                "AND CASE " +
                "WHEN om.BILL_TYPE = 1  " +
                "THEN " +
                " LOCATE('17', om.STATUS_TOTAL) > 0  " +
                "ELSE " +
                " LOCATE('28', om.STATUS_TOTAL) > 0  " +
                "END " +
                "GROUP BY " +
                " om.PERFORM_STORE_CODE, " +
                " date_format(om.BALANCE_DATE, '%Y-%m-%d'), " +
                " om.BILL_TYPE";
        SQLQuery sqlQuery = getSession().createSQLQuery(sql);
        List list = sqlQuery.list();
        List<SalesReceiptsOrder> salesReceiptsOrders = initSalesReceiptsOrder(list);
        return salesReceiptsOrders;
    }

    @Override
    public List<SalesReceiptsOrderItem> querySalesReceiptsOrderItem(SalesReceiptsOrder salesReceiptsOrder) {
        String sql ="SELECT  " +
                " count(0), " +
                " oi.SKU_NO AS 产品sku  " +
                " , " +
                " sum(oi.UNIT_PRICE) AS 折前价  " +
                " , " +
                " sum(oi.UNIT_DISCOUNT) AS 折扣  " +
                " , " +
                " sum(oi.UNIT_DEDUCTED_PRICE) AS 结算金额  " +
                " , " +
                " sum(oi.SALE_NUM)  AS 数量   " +
                " , " +
                " sum(oi.COUPON_AMOUNT) 优惠券金额   " +
                " , " +
                " oi.WAREHOUSE_NO 仓库编码  " +
                " from order_item oi, " +
                "(SELECT " +
                " order_no " +
                "FROM " +
                " order_main " +
                "WHERE " +
                " (SEND_SAP = 0 or SEND_SAP is null) " +
                "AND date_format(BALANCE_DATE, '%Y-%m-%d') = '"+ salesReceiptsOrder.getOutDate()+
                "' AND PERFORM_STORE_CODE = " + salesReceiptsOrder.getCusno()+
                "   AND BILL_TYPE = '1') om " +
                "where  " +
                "oi.order_no = om.order_no " +
                "group by oi.SKU_NO";
        SQLQuery sqlQuery = getSession().createSQLQuery(sql);
        List list = sqlQuery.list();
        List<SalesReceiptsOrderItem> salesReceiptsOrderItems = initSalesReceiptsOrderItem(list);
        return salesReceiptsOrderItems;
    }
    @Override
    public List<SalesReceiptsOrderItem> queryReturnSalesReceiptsOrderItem(SalesReceiptsOrder salesReceiptsOrder) {
        String sql = "SELECT  " +
                " count(0), " +
                " oi.SKU_NO AS 产品sku  " +
                " , " +
                " sum(oi.UNIT_PRICE) AS 折前价  " +
                " , " +
                " sum(oi.UNIT_DISCOUNT) AS 折扣  " +
                " , " +
                " sum(oi.UNIT_DEDUCTED_PRICE)*-1 AS 结算金额  " +
                " , " +
                " sum(oi.SALE_NUM)*-1  AS 数量   " +
                " , " +
                " sum(oi.COUPON_AMOUNT) 优惠券金额   " +
                " , " +
                " om.SHIP_STORE_CODE 仓库编码  " +
                "from order_ret_chg_item oi, " +
                "(SELECT " +
                " order_no, " +
                " SHIP_STORE_CODE " +
                "FROM " +
                " order_main " +
                "WHERE " +
                " (SEND_SAP = 0 or SEND_SAP is null)" +
                " AND date_format(BALANCE_DATE, '%Y-%m-%d') = '" +salesReceiptsOrder.getOutDate()+
                "'  AND PERFORM_STORE_CODE = " + salesReceiptsOrder.getCusno()+
                "   AND BILL_TYPE = '-1') om " +
                " where  " +
                " oi.order_no = om.order_no " +
                "group by oi.SKU_NO";
        SQLQuery sqlQuery = getSession().createSQLQuery(sql);
        List list = sqlQuery.list();
        List<SalesReceiptsOrderItem> salesReceiptsOrderItems = initSalesReceiptsOrderItem(list);
        return salesReceiptsOrderItems;
    }
    List<SalesReceiptsOrderItem> initSalesReceiptsOrderItem(List list){
        List<SalesReceiptsOrderItem> salesReceiptsOrderItems = new ArrayList<SalesReceiptsOrderItem>();
        for(Object objArray:list){
            SalesReceiptsOrderItem salesReceiptsOrderItem = new SalesReceiptsOrderItem();
            Object[] object =(Object[])objArray;
            salesReceiptsOrderItem.setSku(object[1] != null ? object[1].toString():"");
            salesReceiptsOrderItem.setPrice(object[2] != null ? object[2].toString():"");
            salesReceiptsOrderItem.setPers(object[3] != null ? object[3].toString():"");
            salesReceiptsOrderItem.setNowReal(object[4] != null ? object[4].toString():"");
            salesReceiptsOrderItem.setNb(object[5] != null ? object[5].toString():"");
            salesReceiptsOrderItem.setVipPoints("0");
            salesReceiptsOrderItem.setCoupon(object[6] != null ? object[6].toString():"");
            salesReceiptsOrderItem.setDlSite(object[7] != null ? object[7].toString():"");
            salesReceiptsOrderItems.add(salesReceiptsOrderItem);
        }
        return salesReceiptsOrderItems;
    }
    List<SalesReceiptsOrder> initSalesReceiptsOrder(List list){
        List<SalesReceiptsOrder> salesReceiptsOrders = new ArrayList<SalesReceiptsOrder>();
        for(Object objArray : list){
            Object[] object =(Object[])objArray;
            SalesReceiptsOrder salesReceiptsOrder = new SalesReceiptsOrder();
            salesReceiptsOrder.setCusno(object[0]!=null?object[0].toString():"");
            salesReceiptsOrder.setOutDate(object[1]!=null?object[1].toString():"");
            salesReceiptsOrder.setCusnoOutdate(object[2]!=null?object[2].toString():"");
            salesReceiptsOrder.setBillType(object[3]!=null?object[3].toString():"");
            salesReceiptsOrders.add(salesReceiptsOrder);
        }
        return salesReceiptsOrders;
    }
    List<PayManner> initPayManner(List list){
        List<PayManner> payManners = new ArrayList<PayManner>();
        for(Object objArray : list){
            PayManner payManner = new PayManner();
            Object[] object =(Object[])objArray;
            payManner.setPayCode(object[1]!=null?object[1].toString():"");
            payManner.setPaymoney(object[2]!=null?object[2].toString():"");
            payManners.add(payManner);
        }
        return payManners;
    }
}

package com.ibm.oms.dao.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ibm.oms.client.dto.order.*;
import com.ibm.oms.domain.persist.*;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.ibm.oms.dao.intf.WmsOmsOrderDao;
import com.ibm.oms.domain.bean.hang.OrderBean;
import com.ibm.oms.domain.bean.hang.OrderCanceBean;
import com.ibm.oms.domain.bean.hang.OrderItem;
import com.ibm.oms.domain.bean.hang.StoreInf;
import com.ibm.sc.dao.impl.BaseDaoImpl;

/**
 * @author: mr.kai
 * @Description: Wms接口调用实现
 * @create: 2018-03-08 11:24
 **/
@Repository("wmsOmsOrderImpl")
public class WmsOmsOrderImpl extends BaseDaoImpl<OrderReport, Long> implements WmsOmsOrderDao {
    @Override
    public OrderSalesCancel orderSalesCancel(String orderNo) {
        String sql = "SELECT " +
                " om.ORDER_NO " +
                " ,om.SHIP_STORE_CODE " +
                " ,CASE " +
                "WHEN om.ORDER_TIME IS NOT NULL THEN " +
                " DATE_FORMAT( " +
                "  om.ORDER_TIME, " +
                "  '%Y:%m:%d %H:%i:%s' " +
                " ) " +
                "ELSE " +
                " om.ORDER_TIME " +
                "END " +
                " ,os.SHIPPING_ORDER_NO  " +
                " ,os.DELIVERY_MERCHANT_NO  " +
                " ,orr.REASON_NAME  " +
                " ,oi.REMARK  " +
                " ,oi.ORDER_ITEM_NO   " +
                " ,oi.ID   " +
                " ,oi.SKU_NO    " +
                " ,oi.COMMODITY_NAME  " +
                " ,oi.INSTORE_BAR_CODE  " +
                " ,oi.BAR_CODE   " +
                " ,oi.SALE_NUM  " +
                " ,oi.UNIT_PRICE  " +
                "FROM " +
                " order_ret_chg_item oi " +
                "LEFT JOIN order_main om ON oi.order_no = om.order_no " +
                "LEFT JOIN order_sub os ON os.order_no = om.ORDER_NO " +
                "LEFT JOIN order_reason orr on oi.REASON = orr.REASON_NO where om.order_no = " + orderNo;
        SQLQuery sqlQuery = getSession().createSQLQuery(sql);
        List list = sqlQuery.list();
        OrderSalesCancel orderSalesCancel = initOrderSalesCalcel(list);
        return orderSalesCancel;
    }

    @Override
    public Integer updateOrderMainOrderStatus(SalesOrderStatus salesOrderStatus) {
        String sql = "UPDATE order_main SET STATUS_TOTAL = " + salesOrderStatus.getOrderStatus() + " where order_no = " + salesOrderStatus.getOrderNo();
        Query query = getSession().createSQLQuery(sql);
        Integer i = query.executeUpdate();
        return i;
    }

    @Override
    public void insertWholesaleReturnProductBatch(List<WholesaleReturnProduct> wholesaleReturnProducts) {
        for (WholesaleReturnProduct wholesaleReturnProduct : wholesaleReturnProducts) {
            this.getSession().save(wholesaleReturnProduct);
        }
    }

    @Override
    public void insertWholesaleCaseCodeBatch(List<WholesaleCaseCode> wholesaleCaseCodes) {
        for (WholesaleCaseCode wholesaleCaseCode : wholesaleCaseCodes) {
            this.getSession().save(wholesaleCaseCode);
        }
    }

    @Override
    public Long insertWholesaleOrderStatus(WholesaleOrderStatus wholesaleOrderStatus) {
        Long id = (Long) this.getSession().save(wholesaleOrderStatus);
        return id;
    }

    @Override
    public Long querySalesOrderStatusCount(SalesOrderStatus salesOrderStatus) {
        String hql = "SELECT count(*) from SalesOrderStatus s WHERE s.orderNo = " + salesOrderStatus.getOrderNo();
        Long count = (Long) this.getSession()
                .createQuery("select count(*) from Employee")
                .uniqueResult();
        return count;
    }

    @Override
    public Long insertSalesOrderStatus(SalesOrderStatus salesOrderStatus) {
        Long id = (Long) this.getSession().save(salesOrderStatus);
        return id;
    }

    @Override
    public Integer upadteOrderSubByOrderNo(StoreInf storeInf) {
        String updateOrderSub = "UPDATE OrderSub os set os.provideProvince = " + storeInf.getShipFromToProvince() +
                ",os.provideCity = " + storeInf.getShipFromCity() +
                ",os.provideCounty = " + storeInf.getShipFromArea() +
                ",os.provideAddress = " + storeInf.getProvideAddress() +
                " WHERE os.orderNo = " + storeInf.getOrderNo();
        Query queryupdate = this.getSession().createQuery(updateOrderSub);
        Integer i = queryupdate.executeUpdate();
        return i;
    }

    @Override
    public Integer upadteOrderMainByOrderNo(StoreInf storeInf) {
        String updateOrderMain = "UPDATE OrderMain oh set oh.shipStoreName = " + storeInf.getShipStoreName() + " " +
                "where oh.orderNo=" + storeInf.getOrderNo();
        Query queryupdate = this.getSession().createQuery(updateOrderMain);
        Integer i = queryupdate.executeUpdate();
        return i;
    }

    @Override
    public Integer upadteOrderItemByOrderNo(StoreInf storeInf) {
        String updateOrderItem = "UPDATE OrderItem oi set oi.warehouseNo = " + storeInf.getWarehouseNo() +
                " WHERE oi.orderNo = " + storeInf.getOrderNo();
        Query queryupdate = this.getSession().createQuery(updateOrderItem);
        Integer i = queryupdate.executeUpdate();
        return i;
    }

    @Override
    public Integer receiveStatusFromWMS(OmsOrderStatuDTO omsOrderStatuDTO) {
        String updateOrderMain = "UPDATE OrderMain om set om.CONFIRMER_NAME = " + omsOrderStatuDTO.getOperator() + " , set om.CONFIRM_TIME = STR_TO_DATE('" + omsOrderStatuDTO.getOperateDate() + "','%Y-%m-%d %H:%i:%s')" + "WHERE om.ORDER_NO = " + omsOrderStatuDTO.getBillNo();
        Query queryupdate = this.getSession().createQuery(updateOrderMain);
        Integer i = queryupdate.executeUpdate();
        return i;
    }

    @Override
    public Integer Wms2OmsShipOrderConfirm(OmsDeliveryOrderMain omsDeliveryOrderMain) {
        return 1;
    }

    @Override
    public Integer Wms2OmsStorageOrderConfirm(StoreOrderMain storeOrderMain) {
        return 1;
    }

    @Override
    public OrderBean getOms2WmsShipNotify(String orderMainId) {
        String sql = "SELECT " +
                "om.DATE_CREATED AS CreateDate," +
                "CASE WHEN om.BILL_TYPE=1 THEN 201 " +
                "WHEN om.BILL_TYPE IS NULL " +
                "THEN 201 WHEN om.BILL_TYPE=2 THEN '逆向订单' " +
                "ELSE '201' END AS BillType," +
                "om.MERCHANT_NO AS Consignor," +
                "om.CUSTOMER_NAME AS Deliver_Nick," +
                "om.CUSTOMER_PHONE AS Deliver_TEL," +
                "oi.WAREHOUSE_NO AS BranchCode," +
                "oi.ORDER_NO AS BillNo," +
                "oi.ALIAS_ORDER_ITEM_NO AS SeqNo," +
                "oi.SALE_NUM AS Qty," +
                "oi.SKU_NO AS itemId," +
                "os.USER_NAME AS Deliver_lxr," +
                "os.ADDRESS_CODE AS ShipToProvince," +
                "os.PROVIDE_ADDRESS AS ShipFromAddress," +
                "om.TOTAL_PAY_AMOUNT, " +
                "oi.COMMODITY_NAME,  " +
                "om.SHIP_STORE_NAME, " +
                "os.PROVIDE_PROVINCE, " +
                "os.PROVIDE_CITY," +
                "os.PROVIDE_COUNTY," +
                "oi.STORE_TYPE  " +
                "FROM order_item oi LEFT JOIN " +
                "order_sub os ON os.id = oi.ID_ORDER_SUB " +
                "LEFT JOIN ORDER_MAIN om ON os.ID_ORDER = om.id " +
                "WHERE om.ORDER_NO = " + orderMainId;
        SQLQuery sqlQuery = getSession().createSQLQuery(sql);
        List list = sqlQuery.list();
        OrderBean orderBean = initWmsOrderBean(list);
        return orderBean;
    }

    @Override
    public OrderCanceBean getOms2WmsCancelOrderNotify(String orderMainId) {
        String sql = "SELECT om.MERCHANT_NO AS Consignor" +
                ",om.ORDER_NO AS BillNo,om.BILL_TYPE FROM order_main om " +
                "WHERE om.order_no = " + orderMainId;
        SQLQuery sqlQuery = getSession().createSQLQuery(sql);
        List list = sqlQuery.list();
        OrderCanceBean orderCanceBean = initWmsOrderCanceBean(list);
        return orderCanceBean;
    }

    private OrderCanceBean initWmsOrderCanceBean(List list) {
        OrderCanceBean orderCanceBean = new OrderCanceBean();
        for (Object objArray : list) {
            Object[] object = (Object[]) objArray;
            orderCanceBean.setConsignor("ZDD");
            orderCanceBean.setBillNo(object[1] != null ? object[1].toString() : "");
            orderCanceBean.setBillType(object[2] != null ? object[2].toString() : "");
        }
        return orderCanceBean;
    }

    private OrderBean initWmsOrderBean(List list) {
        OrderBean orderBean = new OrderBean();
        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        for (Object objArray : list) {
            OrderItem orderItem = new OrderItem();
            Object[] object = (Object[]) objArray;
            orderBean.setCreateDate((object[0] != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) object[0]) : null));
            orderBean.setBillType(object[1] != null ? object[1].toString() : "");
            orderBean.setConsignor("ZDD");
            orderBean.setDeliver_Nick(object[3] != null ? object[3].toString() : "");
            orderBean.setDeliver_TEL(object[4] != null ? object[4].toString() : "");
            orderBean.setBranchCode(object[5] != null ? object[5].toString() : "");
            orderBean.setBillNo(object[6] != null ? object[6].toString() : "");
            orderBean.setDeliver_lxr(object[10] != null ? object[10].toString() : "");
            orderBean.setShipToProvince(object[11] != null ? object[11].toString() : "");
            orderBean.setShipFromAddress(object[12] != null ? object[12].toString() : "");
            orderItem.setSeqNo(object[7] != null ? object[7].toString() : "");
            orderItem.setQty(object[8] != null ? object[8].toString() : "");
            orderItem.setItemId(object[9] != null ? object[9].toString() : "");
            orderItem.setBillNo(object[6] != null ? object[6].toString() : "");
            orderItem.setItemName(object[14] != null ? object[14].toString() : "");
            orderBean.setTotSaleAmt(object[13] != null ? object[13].toString() : "");
            orderBean.setShipFrom_LXR(object[15] != null ? object[15].toString() : "");
            orderBean.setShipFromProvince(object[16] != null ? object[16].toString() : "");
            orderBean.setShipFromCity(object[17] != null ? object[17].toString() : "");
            orderBean.setShipFromArea(object[18] != null ? object[18].toString() : "");
            orderItem.setStockStatus("1");
            orderItems.add(orderItem);
        }
        orderBean.setOrderItemList(orderItems);
        return orderBean;
    }

    private OrderSalesCancel initOrderSalesCalcel(List list) {
        OrderSalesCancel orderSalesCancel = new OrderSalesCancel();
        List<OrderSalesItemCancel> orderSalesItemCancels = new ArrayList<OrderSalesItemCancel>();
        for (Object objArray : list) {
            OrderSalesItemCancel orderSalesItemCancel = new OrderSalesItemCancel();
            Object[] object = (Object[]) objArray;
            orderSalesCancel.setConsignor("ZDD");
            orderSalesCancel.setBillNo(object[0] != null ? object[0].toString() : "");
            orderSalesCancel.setBranchCode(object[1] != null ? object[1].toString() : "");
            orderSalesCancel.setBillType("501");
            orderSalesCancel.setPoType("31");
            orderSalesCancel.setOperateDate(object[2] != null ? object[2].toString() : "");
            orderSalesCancel.setShipBillID(object[3] != null ? object[3].toString() : "");
            orderSalesCancel.setShipVendorID(object[4] != null ? object[4].toString() : "");
            orderSalesCancel.setRTReason(object[5] != null ? object[5].toString() : "");
            orderSalesCancel.setRemark(object[6] != null ? object[6].toString() : "");
            orderSalesItemCancel.setBillNo(object[7] != null ? object[7].toString() : "");
            orderSalesItemCancel.setSeqno(object[8] != null ? object[8].toString() : "");
            orderSalesItemCancel.setItemId(object[9] != null ? object[9].toString() : "");
            orderSalesItemCancel.setItemName(object[10] != null ? object[10].toString() : "");
            orderSalesItemCancel.setItemCode(object[11] != null ? object[11].toString() : "");
            orderSalesItemCancel.setBarCode(object[12] != null ? object[12].toString() : "");
            orderSalesItemCancel.setQty(object[13] != null ? object[13].toString() : "");
            orderSalesItemCancel.setPrice(object[14] != null ? object[14].toString() : "");
            orderSalesItemCancels.add(orderSalesItemCancel);
        }
        orderSalesCancel.setItems(orderSalesItemCancels);
        return orderSalesCancel;
    }
}

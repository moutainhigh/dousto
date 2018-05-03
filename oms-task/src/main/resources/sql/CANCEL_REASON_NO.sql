--新增取消订单原因字段
alter table order_main add (CANCEL_REASON_NO VARCHAR2(10));
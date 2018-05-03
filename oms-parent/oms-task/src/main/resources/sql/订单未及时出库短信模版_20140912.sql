--运营支撑增加短信模版:未及时出库的订单
INSERT INTO CM_TEMPLATE (ID, CODE, TYPE, NAME, STATUS, SUBJECT, DESCRIPTION, CONTENT, ORDER_DISPLAY, TEMPLATE_FILE_PATH, HTML_FILE_PATH) VALUES 
(6059, 'CM-OMS-ORDER-NOSEND-CONFIRMTIME', 'sms', '未及时出库订单短信通知_审核时间', null, null, '下单时间与确认时间大于30分钟', '尊敬的顾客，由于您的订单存在异常而未能及时确认，不满足一日两配服务，配送时效稍有延误，请您耐心等待，感谢您的理解与支持！', null, null, null);
commit;

INSERT INTO CM_TEMPLATE (ID, CODE, TYPE, NAME, STATUS, SUBJECT, DESCRIPTION, CONTENT, ORDER_DISPLAY, TEMPLATE_FILE_PATH, HTML_FILE_PATH) VALUES 
(6070, 'CM-OMS-ORDER-NOSEND-COLLECTION', 'sms', '未及时出库订单短信通知_集货', null, null, '集货订单', '尊敬的顾客，由于您的订单含非现货商品，我们将尽快协调货源出库，配送时效稍有延误，请您耐心等待，感谢您的理解与支持！', null, null, null);
commit;

INSERT INTO CM_TEMPLATE (ID, CODE, TYPE, NAME, STATUS, SUBJECT, DESCRIPTION, CONTENT, ORDER_DISPLAY, TEMPLATE_FILE_PATH, HTML_FILE_PATH) VALUES 
(6071, 'CM-OMS-ORDER-NOSEND-DEFAULT', 'sms', '未及时出库订单短信通知', null, null, '物流订单积压或系统故障等内部原因', '尊敬的顾客，由于近期订单量最激增，您的订单将会有所延迟，我们将尽快完成订单处理出库，请您耐心等待，感谢您的理解与支持！', null, null, null);
commit;

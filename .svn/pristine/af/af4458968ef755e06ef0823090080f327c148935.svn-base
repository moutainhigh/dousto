package com.ibm.oms.service.business.trans;

import com.beans.stock.OmsSOInfo;
import com.beans.stock.StockLockByOms;
import com.beans.stock.StockUnLockByOms;
import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.intf.intf.InventoryResendMsgDTO;

public interface ImsOmsTransService {
    InventoryResendMsgDTO queryInventoryResend(String orderItemNo);

    OmsSOInfo queryInventoryDeduct(String orderNo);

    StockUnLockByOms queryInventoryUnLock(String orderNo);

	StockLockByOms queryInventoryLock(String orderNo);
}

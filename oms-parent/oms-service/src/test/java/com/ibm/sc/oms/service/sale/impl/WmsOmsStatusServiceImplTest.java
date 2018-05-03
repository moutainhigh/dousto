package com.ibm.sc.oms.service.sale.impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.intf.intf.ResponseObjectDTO;
import com.ibm.oms.intf.intf.WmsOmsReceiveInspectionResultDTO;
import com.ibm.oms.intf.intf.WmsOmsReceiveLogisticsDTO;
import com.ibm.oms.intf.intf.WmsReceiveCostPriceDTO;
import com.ibm.oms.service.business.WmsOmsLogisticsStatusService;
import com.ibm.oms.service.business.impl.WmsOmsLogisticsStatusServiceImpl;
import com.ibm.oms.service.business.trans.WmsOmsCostPriceTransService;
import com.ibm.oms.service.business.trans.WmsOmsInspectStatusTransService;
import com.ibm.oms.service.impl.TransportAreaServiceImpl;
import com.ibm.sc.oms.service.test.BaseTest;
import com.ibm.sc.oms.service.test.CommonTestConst;
import com.ibm.sc.oms.service.test.CommonTestUtil;

/**
 * @author pjsong
 *
 */
public class WmsOmsStatusServiceImplTest extends BaseTest{
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    CommonTestUtil commonTestUtil;
    @Autowired
    WmsOmsLogisticsStatusServiceImpl wmsOmsLogisticsStatusService;
    @Autowired
    WmsOmsInspectStatusTransService wmsOmsInspectStatusTransService;
    @Autowired
    WmsOmsCostPriceTransService wmsOmsCostPriceTransService;
    @Autowired
    TransportAreaServiceImpl transportAreaServiceImpl;
//    @Test
    public final void testWmsInspectReceive() {
        WmsOmsReceiveInspectionResultDTO dto1 = commonTestUtil.genJsonObjFromFile(CommonTestConst.fpWmsInspec, WmsOmsReceiveInspectionResultDTO.class);
        CommonOutputDTO output = wmsOmsInspectStatusTransService.updateOrderInspectStatus(dto1);
        logger.info(output.getCode());
    }
    @Test
    public final void testWmsLogistics() {
//        transportAreaServiceImpl.getAllByAreaId("120647");
//        wmsOmsLogisticsStatusService.queryToTmsStr("20140523003942901");
        List<WmsOmsReceiveLogisticsDTO> dto1 = commonTestUtil.genWmsJsonObjListFromFile(CommonTestConst.fpWmsStatus);
        ResponseObjectDTO output = wmsOmsLogisticsStatusService.handlerUpdateOrderLogisticsStatus(dto1);
        logger.info(output.getResponseHeader().getSuccess());
    }
//    @Test
    public final void testWmsCost() {

        WmsReceiveCostPriceDTO dto1 = commonTestUtil.genJsonObjFromFile(CommonTestConst.fpWmsCost, WmsReceiveCostPriceDTO.class);
        CommonOutputDTO output = wmsOmsCostPriceTransService.updateOrderCostPrice(dto1);
        logger.info(output.getCode());
    }

}

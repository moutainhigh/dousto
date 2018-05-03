package com.ibm.sc.oms.service.retchg.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.intf.constant.OrderMainConst;
import com.ibm.oms.intf.intf.BtcOmsReturnChangeDTO;
import com.ibm.oms.intf.intf.inner.ChangeOrderReciveInfoDTO;
import com.ibm.oms.intf.intf.inner.ExchangeOrderItemDTO;
import com.ibm.oms.intf.intf.inner.RcOrderItemDTO;
import com.ibm.oms.intf.intf.inner.RcOrderSubDTO;
import com.ibm.oms.service.OrderMainService;
import com.ibm.oms.service.OrderSubService;
import com.ibm.oms.service.business.ReturnChangeOrderService;
import com.ibm.sc.oms.service.test.BaseTest;

@SuppressWarnings("javadoc")
public class BtcOmsChangeDTOTest extends BaseTest {

    @Autowired
    OrderMainService orderMainService;
    @Autowired
    OrderSubService orderSubService;
    @Autowired
    ReturnChangeOrderService returnChangeOrderService;

    String filePath = "src/test/resources/intf/BtcOmsChangeDTO.json";

    @Test
    public void genSampleJson() {
        BtcOmsReturnChangeDTO dto = buildInput();
        ObjectMapper mapper = new ObjectMapper();
        File f = new File(filePath);
        if (!f.exists()) {
            try {
                mapper.writeValue(new File(filePath), dto);
                System.out.println("yy");
            } catch (Exception e) {
                System.out.println("xx");
            }
        }
        BtcOmsReturnChangeDTO dto1;
        try {
            dto1 = mapper.readValue(new File(filePath), BtcOmsReturnChangeDTO.class);
            RcOrderSubDTO osDTO = dto1.getOrderSub();
            returnChangeOrderService.writeChangeOrder(dto1);
            System.out.println("yy");
        } catch (Exception e) {
            System.out.println("xx");
        }

    }

    //@Test
    public void testMemberWithNoValues() {
        BtcOmsReturnChangeDTO dto = new BtcOmsReturnChangeDTO();
        // validate the input
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<BtcOmsReturnChangeDTO>> violations = validator.validate(dto);
        if (violations.size() > 0) {
            for (ConstraintViolation<BtcOmsReturnChangeDTO> v : violations) {
                System.out.println(v.getMessage());
                System.out.println(v.getMessageTemplate());
                System.out.println(v.getInvalidValue());
            }
        }
    }

    // 构建主订单信息
    public BtcOmsReturnChangeDTO buildInput() {
        BtcOmsReturnChangeDTO bean = new BtcOmsReturnChangeDTO();
        bean.setOrderCategory("");
        bean.setOrderRelatedOrigin("");
        bean.setTransportFee("");
        bean.setOrderSource(OrderMainConst.OrderMain_Ordersource_B2c.getCode());
        bean.setRemark("");
        bean.setReciveinfo(buildReciveInfo());
        bean.setOrderSub(buildOrderSub());
        return bean;
    }

    // 构建换货出库单的收货人信息
    public ChangeOrderReciveInfoDTO buildReciveInfo() {
        ChangeOrderReciveInfoDTO info = new ChangeOrderReciveInfoDTO();
        info.setAddressCode("");
        info.setAddressDetail("");
        info.setMobPhoneNum("");
        info.setPhoneNum("");
        info.setUserName("");
        return info;
    }

    // 构建子订单
    public RcOrderSubDTO buildOrderSub() {
        RcOrderSubDTO bean = new RcOrderSubDTO();
        bean.setInvoicePrinted("");
        bean.setDistributeType("");
        bean.setExpressType("");
        bean.setOrderSubRelatedOrigin("");
        bean.setOrderItems(buildItems());
        bean.setStoreNo("");
        bean.setUserName("");
        bean.setAddressCode("");
        bean.setAddressDetail("");
        bean.setPhoneNum("");
        bean.setMobPhoneNum("");
        bean.setOrderItems(buildItems());
        return bean;
    }

    // 构建明细
    public List<RcOrderItemDTO> buildItems() {
        List<RcOrderItemDTO> list = new ArrayList<RcOrderItemDTO>();
        RcOrderItemDTO item = new RcOrderItemDTO();
        item.setRefOrderItemNo("");
        item.setReason("");
        item.setSaleNum("");
        item.setSkuNo("");
        item.setProductPropertyFlag("");
        item.setExchangeOrderItemDTO(buildExchangeItems());
        list.add(item);
        return list;
    }

    // 构建换货商品的信息（色码款）
    public ExchangeOrderItemDTO buildExchangeItems() {
        ExchangeOrderItemDTO excItem = new ExchangeOrderItemDTO();
        excItem.setBarCode("");
        excItem.setSkuNo("");
        excItem.setCommodityCode("");
        excItem.setCommodityName("");
        return excItem;
    }

}

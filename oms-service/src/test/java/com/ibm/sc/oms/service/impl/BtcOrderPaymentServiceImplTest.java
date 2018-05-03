package com.ibm.sc.oms.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.intf.intf.BtcOmsReturnChangeDTO;
import com.ibm.oms.intf.intf.BtcPayDTO;
import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.intf.intf.inner.PaymentDTO;
import com.ibm.oms.intf.intf.inner.RcOrderSubDTO;
import com.ibm.oms.service.business.impl.BtcOrderPaymentServiceImpl;
import com.ibm.sc.oms.service.test.BaseTest;

//在线察看编辑：
//http://www.jsoneditoronline.org/

@SuppressWarnings("javadoc")
public class BtcOrderPaymentServiceImplTest extends BaseTest {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    BtcOrderPaymentServiceImpl btcOrderPaymentServiceImpl;
	
    String filePath = "src/test/resources/intf/ReceivePaymentDTO.json";
    
//    @Test
    public final void test() { 
    
        
        BtcPayDTO payDto = this.newBtcPayDTO();
        
       CommonOutputDTO output = btcOrderPaymentServiceImpl.handlerBtcOrderPayment(payDto);
       logger.debug(output.getMsg());
        logger.debug(output.getCode());
   }
    
    private BtcPayDTO newBtcPayDTO(){
    	BtcPayDTO dto = new BtcPayDTO();
    	dto.setOrderNo("1222073793");
    	dto.setOperator("BOBO");
    	dto.setRemark("测试数据");
    	
    	 List<PaymentDTO> payList = new ArrayList<PaymentDTO>();
    	 PaymentDTO payDto = new PaymentDTO();
    	 payDto.setBankTypeCode("11");
    	 payDto.setCardNo("200");
    	 payDto.setPayAmount(new BigDecimal(100));
    	 payDto.setPayCode("11");
    	// payDto.getPayTime(new Date());
    	 payList.add(payDto);
    	 dto.setPaymentDTOs(payList);
    	 dto.setOccurtime("2014-04-10 01:01:01");
    	 return dto;
    }
    
    @SuppressWarnings("unused")
    @Test
    public void genSampleJson() {
        BtcPayDTO dto = new BtcPayDTO();
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
        BtcPayDTO dto1;
        try {
            dto1 = mapper.readValue(new File(filePath), BtcPayDTO.class);
            CommonOutputDTO output = btcOrderPaymentServiceImpl.handlerBtcOrderPayment(dto1);
            System.out.println("yy");
        } catch (Exception e) {
            System.out.println("xx");
        }

    }
}

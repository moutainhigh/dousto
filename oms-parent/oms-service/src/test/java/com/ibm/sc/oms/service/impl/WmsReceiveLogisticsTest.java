package com.ibm.sc.oms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.ibm.oms.intf.intf.WmsOmsReceiveLogisticsDTO;
import com.ibm.oms.service.business.impl.OrderCreateServiceImpl;
import com.ibm.sc.oms.service.test.Env;


public class WmsReceiveLogisticsTest {
		@Autowired
		Env env;
	    @Test
		public final void test() {
			try {
				RestTemplate client = new RestTemplate();
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<String> request = new HttpEntity(buildDto(),headers);
				String returnedOutput = client.postForObject(env.getWms01(), request,String.class);
				System.out.println(returnedOutput);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		private List<WmsOmsReceiveLogisticsDTO> buildDto() {
			
			List<WmsOmsReceiveLogisticsDTO> listDto = new ArrayList();
			WmsOmsReceiveLogisticsDTO dto = new WmsOmsReceiveLogisticsDTO();
			WmsOmsReceiveLogisticsDTO dto1 = new WmsOmsReceiveLogisticsDTO();
			
			dto.setOrderSubNo("20140410000017301");
			dto.setLogisticsStatus("62");
			dto.setLogisticsDesc("打包中");
			dto.setOperateTime("2014-04-14");
			dto.setOperator("sys");
			
			dto1.setOrderSubNo("20140410000017301");
			dto1.setLogisticsStatus("63");
			dto1.setLogisticsDesc("打包中");
			dto1.setOperateTime("2014-04-14");
			dto1.setOperator("sys");
	
			listDto.add(dto);
			listDto.add(dto1);
			return listDto;
		}

        public Env getEnv() {
            return env;
        }

        public void setEnv(Env env) {
            this.env = env;
        }
		
		
		
	}


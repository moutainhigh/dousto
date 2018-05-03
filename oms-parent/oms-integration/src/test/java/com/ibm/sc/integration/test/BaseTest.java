package com.ibm.sc.integration.test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ibm.oms.intf.intf.ResultDTO;
import com.ibm.oms.service.OrderMainService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring-integration-test.xml" })
public class BaseTest extends AbstractJUnit4SpringContextTests {
	protected static Log log = LogFactory.getLog(BaseTest.class);
	
	@Autowired
	OrderMainService orderMainService;

	@Test
	public void testProductPrice() {
		Calendar now = Calendar.getInstance();
		log.error("开始时间：" + now.getTime().toLocaleString());
		log.error("结束时间：" + Calendar.getInstance().getTime().toLocaleString());
	}
	
//	@Test
	public void getSyncByDate() {
	    String sql="select * from order_status_sync_log om where om.DATE_CREATED >=to_date('2014-07-30', 'yyyy-mm-dd') and om.DATE_CREATED <=to_date('2014-07-31', 'yyyy-mm-dd')";
        List<Object> omList = orderMainService.findOrderMainBySql(sql);
        try{
            int count=0;
            for(Object objMap:omList){
                Map map = (Map)objMap;
                Set set = map.entrySet();     
                Iterator i = set.iterator(); 
                while(i.hasNext()){  
                    Map.Entry entry1 = (Map.Entry)i.next(); 
                    System.out.println(entry1.getKey()+"=="+entry1.getValue()); 
                } 
            }
        }catch(Exception e){
            e.printStackTrace();
        }
	}
	

}

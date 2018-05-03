package order;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.oms.dao.constant.OrderStatusAction;
import com.ibm.oms.service.business.OrderStatusService;

/**
 * Description: //模块目的、功能描述  
 * @author YanYanZhang
 * Date:   2018年1月24日 
 */
public class OrderStatusServiceTest extends BaseTest{
	@Autowired
	OrderStatusService orderStatusService;
	
	@Test
	public void testUpdateStatus(){
	boolean result = orderStatusService.saveProcess("132900673801", OrderStatusAction.S014150, null, null, null);
	}
}

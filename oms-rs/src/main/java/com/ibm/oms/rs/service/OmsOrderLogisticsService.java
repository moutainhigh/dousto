package com.ibm.oms.rs.service;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ibm.oms.intf.intf.ResponseObjectDTO;
import com.ibm.oms.intf.intf.WmsReceiveCostPriceDTO;

/**
 * Description: //模块目的、功能描述  
 * @author Yusl
 * Date:   2018年3月19日 
 */
@Path("/oms-logistics")
@Produces(MediaType.APPLICATION_JSON)
public interface OmsOrderLogisticsService {


    /**
     * 快递100回调接口
     * @param receive
     * @return
     */
    @POST
	@Path("/createOrderLogistics")
	public String createOrderLogistics(String param,String sign);
}

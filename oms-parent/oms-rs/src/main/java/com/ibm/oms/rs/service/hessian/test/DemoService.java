package com.ibm.oms.rs.service.hessian.test;

import com.ibm.oms.client.dto.bundle.BundleGroupBean;
import com.ibm.oms.client.dto.order.CommonOutputClientDTO;

/**
 * Description: //模块目的、功能描述  
 * @author lvzhijun
 * Date:   2018年1月12日 
 */
public interface DemoService {
    
    CommonOutputClientDTO query(BundleGroupBean bean);

}

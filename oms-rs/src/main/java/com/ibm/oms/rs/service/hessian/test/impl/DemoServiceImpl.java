package com.ibm.oms.rs.service.hessian.test.impl;

import org.springframework.stereotype.Service;

import com.ibm.oms.client.dto.bundle.BundleGroupBean;
import com.ibm.oms.client.dto.order.CommonOutputClientDTO;
import com.ibm.oms.client.intf.DemoService;

@Service("demoService")
public class DemoServiceImpl implements DemoService {

	@Override
	public CommonOutputClientDTO query(BundleGroupBean bean) {
		CommonOutputClientDTO co = new  CommonOutputClientDTO();
		co.setMsg("success");
		System.out.println("调用成功");
		return co;
	}

}

package com.ibm.oms.admin.hessan.test;

import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;
import com.ibm.oms.client.dto.bundle.BundleGroupBean;
import com.ibm.oms.client.intf.DemoService;

public class HessianTest {
	 public static void main(String[] args) {
	        String url = "http://localhost:8080/oms-rs/remoting/DemoService";  
	        
	        HessianProxyFactory factory = new HessianProxyFactory();  
	        try {
	        	DemoService bundleService = (DemoService) factory.create(DemoService.class, url);
	        	BundleGroupBean entity = new BundleGroupBean();
	        	bundleService.query(entity);
	        } catch (MalformedURLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }  
	    }
}

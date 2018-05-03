package com.ibm.oms.admin.hessan.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.caucho.hessian.client.HessianProxyFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.client.dto.bundle.BundleGroupBean;
import com.ibm.oms.client.dto.order.create.ReceiveOrderClientDTO;
import com.ibm.oms.client.intf.DemoService;
import com.ibm.oms.client.intf.IOrderClient;

public class HessianOrderClientTest {
	
	@Test
	public void orderCreateOnline() throws JsonParseException, JsonMappingException, IOException{
		String url = "http://localhost:8080/oms-rs/remoting/IOrderClient";  
		 HessianProxyFactory factory = new HessianProxyFactory();  
	        try {
	        	IOrderClient OrderClient = (IOrderClient) factory.create(IOrderClient.class, url);
	        	//String content = readToString("C:/d/dusto/oms-news/oms-parent/oms-admin/src/test/java/com/ibm/oms/admin/hessan/test/createOrderFormat.txt");
	        	String content1 =  FileUtils.readFileToString(new File("C:/Users/ChaoWang/Desktop/createOrderjson.json"), "utf-8");
	        	//类型转换
	        	//ObjectMapper mapper = new ObjectMapper();
	        	//ReceiveOrderClientDTO rc = mapper.readValue(content1.trim(), ReceiveOrderClientDTO.class);
	        	OrderClient.createOrder(content1);
	        } catch (MalformedURLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } 
	}
	
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
	
	
	public String readToString(String fileName) {  
		
		
		
        String encoding = "UTF-8";  
        File file = new File(fileName);  
        Long filelength = file.length();  
        byte[] filecontent = new byte[filelength.intValue()];  
        try {  
            FileInputStream in = new FileInputStream(file);  
            in.read(filecontent);  
            in.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        try {  
            return new String(filecontent, encoding);  
        } catch (UnsupportedEncodingException e) {  
            System.err.println("The OS does not support " + encoding);  
            e.printStackTrace();  
            return null;  
        }  
    } 
	
	
}

package com.ibm.sc.oms.service.hessian;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenerateJsonFromInete {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	public static String generateJsonFromIneter(String path){
		try {
			return FileUtils.readFileToString(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}

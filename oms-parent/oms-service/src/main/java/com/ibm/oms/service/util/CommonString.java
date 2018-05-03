package com.ibm.oms.service.util;

import org.apache.commons.lang.StringUtils;
/**
 * @author 
 * 
 */
public class CommonString {
	public static boolean equals(String str1,String str2){
		return StringUtils.equals(StringUtils.trimToEmpty(str1), StringUtils.trimToEmpty(str2));
	}
}

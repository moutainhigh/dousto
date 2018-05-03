package com.ibm.oms.service.util;

import java.math.BigDecimal;

public class DataUtil {

	/**
	 * @param data
	 * @return
	 */
	public static BigDecimal convertBigDecimal(BigDecimal data){
		return data==null?(new BigDecimal(0)):data;
	}
}

package com.ibm.oms.service.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtil {
	public static  StringBuffer stackTraceToString(Throwable throwable) {
		StringWriter stackTrace = new StringWriter();
		throwable.printStackTrace(new PrintWriter(stackTrace));

		return stackTrace.getBuffer();
	}
	public static  String stackTraceToString(Throwable throwable,int len) {
		String ms = stackTraceToString(throwable).toString();
		if(ms.length() <= len)
			return ms;
		else
			return ms.substring(0,len);
	}
}

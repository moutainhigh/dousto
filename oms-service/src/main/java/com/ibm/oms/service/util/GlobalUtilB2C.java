package com.ibm.oms.service.util;


import java.util.List;

import javax.annotation.Resource;

import com.ibm.sc.model.sys.Option;
import com.ibm.sc.service.sys.OptionService;
import com.ibm.sc.util.SpringUtil;

/**
 * B2C 公用类，B2C模块可以把一些常用的方法写在这里 提供大家共用
 * 
 * @author JJL
 * 
 */
public class GlobalUtilB2C {
	
	public static String B2C_FRONT_PARAMETERS= "B2CSYS_FRONT_PARAMETERS";//B2C系统前台参数,父栏目 

	@Resource
	private static OptionService optionService = (OptionService) SpringUtil
			.getBean("optionService");;// 取数据字典service

	/**
	 * B2C取数据字典公用方法
	 * 
	 * @param optionGroupCode
	 *            数据字典组编码
	 * @param optionCode
	 *            数据字典项编码
	 * @return 数据字典项Option对象，其中code为编码，name为值或者description为值，后续会加一个字段存储值；目前根据需要选择其中一个作为值
	 */
	public static Option getByGroupCodeAndOptionCode(String optionGroupCode,
			String optionCode) {
		return optionService.getByGroupCodeAndOptionCode(optionGroupCode,
				optionCode);
	}
	
	/**
	 * 通过属性组code获取所有的option
	 * @param groupCode
	 * @return
	 */
	public static List<Option> findOptionByGroupCode(String groupCode){
		
		return optionService.findByGroupCode(groupCode);
	}

}

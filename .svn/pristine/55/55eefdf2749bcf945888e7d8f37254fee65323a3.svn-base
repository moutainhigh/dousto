package com.ibm.oms.client.dto.result;

import java.io.Serializable;

/**
 * Description: //模块目的、功能描述  
 * @author Haozw
 * Date:   2018年2月9日 
 */
public class HessianResult<T> implements Serializable{
	

	
	/* 属性说明 */
	private static final long serialVersionUID = -1087961879659020710L;
	/**
	 * 	response_code	响应编码	string	Y	"Exxxxxx：错误，Sxxxxxx：成功
		示例：成功响应码为OK
		响应编码参照如下表格"
		response_time	响应时间	string		时间戳，格式为yyyy-MM-dd HH:mm:ss，时区为GMT+8，如：2018-01-01 19:01:01
		response_msg	响应消息	string	Y	响应消息描述
		response_data	响应业务数据实体	Object	Y	业务参数
		pageSize	页大小		N	用于列表查询
		currentPage	当前页		N	用于列表查询
		totalRecord	总记录数		N	用于列表查询
	 */
	private String response_code;
	private String response_time;
	private String response_msg;
	private  T data;
	private String pageSize;
	private String currentPage;
	private String totalRecord;
	/**
	 * @return the response_code
	 */
	public String getResponse_code() {
		return response_code;
	}
	/**
	 * @param response_code the response_code to set
	 */
	public void setResponse_code(String response_code) {
		this.response_code = response_code;
	}
	/**
	 * @return the response_time
	 */
	public String getResponse_time() {
		return response_time;
	}
	/**
	 * @param response_time the response_time to set
	 */
	public void setResponse_time(String response_time) {
		this.response_time = response_time;
	}
	/**
	 * @return the response_msg
	 */
	public String getResponse_msg() {
		return response_msg;
	}
	/**
	 * @param response_msg the response_msg to set
	 */
	public void setResponse_msg(String response_msg) {
		this.response_msg = response_msg;
	}

	/**
	 * @return the pageSize
	 */
	public String getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * @return the currentPage
	 */
	public String getCurrentPage() {
		return currentPage;
	}
	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	/**
	 * @return the totalRecord
	 */
	public String getTotalRecord() {
		return totalRecord;
	}
	/**
	 * @param totalRecord the totalRecord to set
	 */
	public void setTotalRecord(String totalRecord) {
		this.totalRecord = totalRecord;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
}

package com.ibm.oms.intf.intf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ResponseHeaderDTO implements Serializable {

	private String terminalType;
	private String  channel;
	private String  expire;
	private String  requestId;

	private String  sessionId;

	private String  sessionExpires;

	private String  token;

	private String  cookieMemberId;

	private String  success;

	private String  message;

	private String  errorCode;

	private String  errorMsg;

	private List<MsgDTO> errors = new ArrayList<MsgDTO>();

	private List<MsgDTO> warnings = new ArrayList<MsgDTO>();

	public String getChannel() {
		return channel;
	}

	public String getCookieMemberId() {
		return cookieMemberId;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	@XmlElement(name = "error")
	public List<MsgDTO> getErrors() {
		return errors;
	}

	public String getExpire() {
		return expire;
	}

	public String getMessage() {
		return message;
	}

	public String getRequestId() {
		return requestId;
	}

	public String getSessionExpires() {
		return sessionExpires;
	}

	public String getSessionId() {
		return sessionId;
	}

	public String getSuccess() {
		return success;
	}

	@XmlElement(name = "terminalType",required=true,nillable=false)
	public String getTerminalType() {
		return terminalType;
	}

	public String getToken() {
		return token;
	}

	@XmlElement(name = "warning")
	public List<MsgDTO> getWarnings() {
		return warnings;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public void setCookieMemberId(String cookieMemberId) {
		this.cookieMemberId = cookieMemberId;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public void setErrors(List<MsgDTO> errors) {
		this.errors = errors;
	}
	public void setExpire(String expire) {
		this.expire = expire;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public void setSessionExpires(String sessionExpires) {
		this.sessionExpires = sessionExpires;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}


	public void setToken(String token) {
		this.token = token;
	}

	public void setWarnings(List<MsgDTO> warnings) {
		this.warnings = warnings;
	}
}

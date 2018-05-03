package com.ibm.oms.intf.intf;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ResponseObjectDTO implements Serializable{
	private ResponseHeaderDTO responseHeader = new ResponseHeaderDTO();

	public ResponseHeaderDTO getResponseHeader() {
		return responseHeader;
	}

	public void setResponseHeader(ResponseHeaderDTO responseHeader) {
		this.responseHeader = responseHeader;
	}

}

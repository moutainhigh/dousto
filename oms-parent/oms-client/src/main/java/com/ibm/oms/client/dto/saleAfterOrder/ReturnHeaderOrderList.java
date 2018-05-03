package com.ibm.oms.client.dto.saleAfterOrder;

import java.util.List;

/**
 * 退换货信息列表，pos使用
 */
public class ReturnHeaderOrderList implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8195808724298654097L;
	
	//接口信息
	private MessageDto msgDto;

	private List<ReturnHeaderOrderFromLasaDto> dtoList;

	public MessageDto getMsgDto() {
		return msgDto;
	}

	public void setMsgDto(MessageDto msgDto) {
		this.msgDto = msgDto;
	}

	public List<ReturnHeaderOrderFromLasaDto> getDtoList() {
		return dtoList;
	}

	public void setDtoList(List<ReturnHeaderOrderFromLasaDto> dtoList) {
		this.dtoList = dtoList;
	}	
}
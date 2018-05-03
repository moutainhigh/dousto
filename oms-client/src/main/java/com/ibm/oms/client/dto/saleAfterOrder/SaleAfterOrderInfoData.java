package com.ibm.oms.client.dto.saleAfterOrder;

import java.util.List;

/**
 * 退换货信息，前端使用
 */
public class SaleAfterOrderInfoData implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8195808724298654097L;
	
	//接口信息
	private MessageDto msgDto;

	private List<SaleAfterOrderInfoDto> dto;

	public MessageDto getMsgDto() {
		return msgDto;
	}

	public void setMsgDto(MessageDto msgDto) {
		this.msgDto = msgDto;
	}

	public List<SaleAfterOrderInfoDto> getDto() {
		return dto;
	}

	public void setDto(List<SaleAfterOrderInfoDto> dto) {
		this.dto = dto;
	}
}
package com.ibm.oms.client.dto.saleAfterOrder;

import java.util.ArrayList;
import java.util.List;

import com.ibm.oms.client.dto.saleAfterOrder.ReturnHeaderOrderFromLasaDto;
import com.ibm.oms.client.dto.saleAfterOrder.ReturnItemOrderFromLasaDto;

/**
 * 退换货数据信息
 */
public class SaleAfterOrderFromLasaDto implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8195606702987654097L;
	
	//接口信息
	private MessageDto msgDto;

	//退货单抬头
	private ReturnHeaderOrderFromLasaDto headerDto;
	
	//退货单行项目
	private List<ReturnItemOrderFromLasaDto> itemDto = new ArrayList<ReturnItemOrderFromLasaDto>();
	
	//退货单数
	private Long saleAfterOrderNum;
	
	public ReturnHeaderOrderFromLasaDto getHeaderDto() {
        return headerDto;
    }

    public void setHeaderDto(ReturnHeaderOrderFromLasaDto headerDto) {
        this.headerDto = headerDto;
    }
	
    public List<ReturnItemOrderFromLasaDto> getItemDto() {
        return itemDto;
    }

    public void setItemDto(List<ReturnItemOrderFromLasaDto> itemDto) {
        this.itemDto = itemDto;
    }

	public MessageDto getMsgDto() {
		return msgDto;
	}

	public void setMsgDto(MessageDto msgDto) {
		this.msgDto = msgDto;
	}

	public Long getSaleAfterOrderNum() {
		return saleAfterOrderNum;
	}

	public void setSaleAfterOrderNum(Long saleAfterOrderNum) {
		this.saleAfterOrderNum = saleAfterOrderNum;
	}	
}
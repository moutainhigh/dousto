package com.ibm.oms.client.dto.order;

import java.io.Serializable;
import java.util.List;

/**
 * Description: //模块目的、功能描述  
 * @author Yusl
 * Date:   2018年3月27日 
 */
public class OrderSplitLogisticsClientDTO implements Serializable{


    /* 属性说明 */
	private static final long serialVersionUID = 1L;

	private List<ProductImg> productImgs;//商品图片
	
    private String  wmsName;//物流公司

    private String  addressDetail;//收货人具体地址

    private List<OrderLogisticsMessageClientDTO> wmsInfoList;//物流信息

    private String  wmsNo;//物流单号

	public List<ProductImg> getProductImgs() {
		return productImgs;
	}

	public void setProductImgs(List<ProductImg> productImgs) {
		this.productImgs = productImgs;
	}

	public String getWmsName() {
		return wmsName;
	}

	public void setWmsName(String wmsName) {
		this.wmsName = wmsName;
	}

	public List<OrderLogisticsMessageClientDTO> getWmsInfoList() {
		return wmsInfoList;
	}

	public void setWmsInfoList(List<OrderLogisticsMessageClientDTO> wmsInfoList) {
		this.wmsInfoList = wmsInfoList;
	}

	public String getWmsNo() {
		return wmsNo;
	}

	public void setWmsNo(String wmsNo) {
		this.wmsNo = wmsNo;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
    

} 

/**
 * 
 */
package com.ibm.oms.domain.persist;

/**
 * 自提点（存储SelfTakePoint中的部分属性）
 * @author xiaonanxiang
 *
 */
public class SelfTakePointTmp {
	private String id;
	private String pointDeliverPartnerId;
	private String pointName;
	private boolean checked;
	
	private String provice;
	private String city;
	private String address;
	
	private String detailAddress;
	
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public String getProvice() {
		return provice;
	}
	public void setProvice(String provice) {
		this.provice = provice;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPointDeliverPartnerId() {
		return pointDeliverPartnerId;
	}
	public void setPointDeliverPartnerId(String pointDeliverPartnerId) {
		this.pointDeliverPartnerId = pointDeliverPartnerId;
	}
	public String getPointName() {
		return pointName;
	}
	public void setPointName(String pointName) {
		this.pointName = pointName;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
}

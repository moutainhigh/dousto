/**
 * 
 */
package com.ibm.oms.domain.persist;

import java.io.Serializable;

/**
 * 区域
 * @author xiaonanxiang
 *
 */
public class DistributeAddress implements Serializable {
	/**
	 * 省/直辖市
	 */
	private Long state;
	
	/**
	 * 城市
	 */
	private Long city;
	
	/**
	 * 县区
	 */
	private Long county;
	
	/**
	 * 街道
	 */
	private Long street;

	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}

	public Long getCity() {
		return city;
	}

	public void setCity(Long city) {
		this.city = city;
	}

	public Long getCounty() {
		return county;
	}

	public void setCounty(Long county) {
		this.county = county;
	}

	public Long getStreet() {
		return street;
	}

	public void setStreet(Long street) {
		this.street = street;
	}
	
}

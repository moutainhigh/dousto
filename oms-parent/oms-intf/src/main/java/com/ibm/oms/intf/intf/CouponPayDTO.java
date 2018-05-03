package com.ibm.oms.intf.intf;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.intf.intf.inner.CouponDTO;

@XmlType
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CouponPayDTO implements Serializable {
    String batchNum;
    List<CouponDTO> couponList;
    public String getBatchNum() {
        return batchNum;
    }
    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum;
    }
    public List<CouponDTO> getCouponList() {
        return couponList;
    }
    public void setCouponList(List<CouponDTO> couponList) {
        this.couponList = couponList;
    }

    public static void main(String[] args) {
    	CouponPayDTO cpd = new CouponPayDTO();
    	cpd.setBatchNum("test-2018-02-28");
    	CouponDTO cd =  new  CouponDTO();
    	cd.setCouponAmount(new BigDecimal("10.8").setScale(2));
    	cd.setCouponNo("X1111");
    	cd.setOrderSubNo("102100374601");
    	List<CouponDTO> couponList = new ArrayList<CouponDTO>();
    	couponList.add(cd);
    	cpd.setCouponList(couponList);
    	ObjectMapper mapper = new ObjectMapper();
        String itemSnapshotStr = null;
        try {
            itemSnapshotStr = mapper.writeValueAsString(cpd);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(itemSnapshotStr);
    	
	}
    
  
    
}

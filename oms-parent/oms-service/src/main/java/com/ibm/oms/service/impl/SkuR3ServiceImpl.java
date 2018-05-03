package com.ibm.oms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.intf.SkuR3Dao;
import com.ibm.oms.domain.persist.SkuR3;
import com.ibm.oms.service.SkuR3Service;
import com.ibm.sc.service.impl.BaseServiceImpl;
/**
 * 
 * Creation date:2014-03-14 04:17:59
 * @author:Yong Hong Luo
 */
@Service("skuR3Service")
public class SkuR3ServiceImpl extends BaseServiceImpl<SkuR3,Long> implements
		SkuR3Service{
	private SkuR3Dao skuR3Dao;
    @Autowired
	@SuppressWarnings("javadoc")
    public void setSkuR3Dao(SkuR3Dao skuR3Dao) {
	    super.setBaseDao(skuR3Dao);
		this.skuR3Dao = skuR3Dao;
	}

    public String getR3BySkuNo(String skuNo){
    	SkuR3 sku = skuR3Dao.getByField("skuCode", skuNo);
    	if(sku!=null){
    		return sku.getItemnumber();
    	}
    	
    	return null;
    	//return skuR3Dao.getR3BySkuNo(skuNo);
    }
    public SkuR3 getR3BySku(String skuNo){
        SkuR3 ret = skuR3Dao.getByField("skuCode", skuNo);
    	return ret;
    }

}

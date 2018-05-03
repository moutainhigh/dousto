package com.ibm.oms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.intf.OrderItemDao;
import com.ibm.oms.dao.intf.ProductPropertyDao;
import com.ibm.oms.domain.persist.ProductProperty;
import com.ibm.oms.service.ProductPropertyService;
import com.ibm.sc.service.impl.BaseServiceImpl;

/**
 * 
 * Creation date:2014-03-14 04:20:01
 * 
 * @author:Yong Hong Luo
 */
@Service("productPropertyService")
public class ProductPropertyServiceImpl extends BaseServiceImpl<ProductProperty, Long> implements ProductPropertyService {

    private ProductPropertyDao productPropertyDao;

    @Autowired
    public void setProductPropertyDao(ProductPropertyDao productPropertyDao) {
        super.setBaseDao(productPropertyDao);
        this.productPropertyDao = productPropertyDao;
    }
    
}

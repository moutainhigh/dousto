package com.ibm.oms.service.business.trans.impl;

import java.util.List;

import org.springframework.cglib.core.Converter;

/**
 * 
 * @author Administrator
 * 
 */
public class OrderConverter implements Converter {

    /**
     * 防止list拷贝
     */
    @Override
    public Object convert(Object pojo, Class arg1, Object arg2) {
        if (pojo instanceof List) {
            return null;
        }
        return pojo;
    }

}

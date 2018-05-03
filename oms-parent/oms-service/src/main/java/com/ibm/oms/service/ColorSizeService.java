package com.ibm.oms.service;


import com.ibm.oms.intf.intf.ResultDTO;


/**
 * 色码款单操作类
 * 
 * @author xiaonanxiang
 * 
 */
public interface ColorSizeService {

    public final String OK = "OK";
    public final String FAILED = "FAILED";
    
    /**
     * 根据skuNo获取色码款信息
     * @param skuNo
     * @return  色码款信息为：resultDTO.getResultObj()
     */
    public ResultDTO getColorSizeInfoBySkuNo(String skuNo);
}
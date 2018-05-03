package com.ibm.oms.client.dto;

import java.io.Serializable;

/**
 * @author: mr.kai
 * @Description: 查询店铺销售情况参数实体类
 * @create: 2018-03-26 15:25
 **/
public class QueryCategoryDTO implements Serializable{
    //门店代码
    private String shopNo;
    //统计类型1：消费金额 2：消费品类
    private String memberNo;

    public QueryCategoryDTO() {

    }

    public String getShopNo() {
        return shopNo;
    }

    public void setShopNo(String shopNo) {
        this.shopNo = shopNo;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }
}

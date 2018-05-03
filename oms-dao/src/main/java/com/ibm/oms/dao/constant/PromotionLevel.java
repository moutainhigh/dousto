package com.ibm.oms.dao.constant;


/**
 * 促销等级枚举
 * 
 * @author wangchao
 *
 */
public enum PromotionLevel {
	//ORDER/ITEM/OTHER
	Promotion_Level_ORDER("0","ORDER"),
	Promotion_Level_ITEM("1","ITEM");

    private final String code;
    private final String name;

    private PromotionLevel(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}

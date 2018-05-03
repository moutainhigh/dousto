package com.ibm.oms.client.dto.order;

import java.io.Serializable;

/**
 * @author: mr.kai
 * @Description: 奇门创建订单响应实体类
 * @create: 2018-04-26 15:40
 **/
public class QmCreateOrderResponseBean implements Serializable {
    private String flag;//	String	success|failure	响应结果
    private String code;//	String	0	响应码
    private String message;//ERP业务单据;PA3000499;LA1000149创建成功!对应的电商的订单号:BOIWMS01160617000005

    public QmCreateOrderResponseBean() {
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

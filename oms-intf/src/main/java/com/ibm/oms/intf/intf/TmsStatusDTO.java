package com.ibm.oms.intf.intf;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 订单状态信息
 *
 * 2014-4-25 下午03:35:45
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@JsonIgnoreProperties(ignoreUnknown=true)
public class TmsStatusDTO implements Serializable{
    private String txLogisticID;//外部订单号(正常订单传递过去的都是外部订单号)
    @NotBlank(message = "outhousetime is compulsory")
    @Pattern(regexp = "((19|20|21)\\d{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])\\s((0?[0-9])|(1[0-9])|(2[0-3])):([0-5][0-9]):([0-5][0-9])", message = "dateTime formatted as yyyy-MM-dd HH:mm:ss")
    private String acceptTime;//时间
    private String remark;//备注
    private String mailNo;//运单号（只针对揽收成功，揽收成功才会产生对方系统的运单号）
    private String infoType;//通知类型
    private String name;//执行人，操作人
    private String logisticCompanyId;//物流公司ID
    public String getTxLogisticID() {
        return txLogisticID;
    }
    public void setTxLogisticID(String txLogisticId) {
        this.txLogisticID = txLogisticId;
    }

    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getMailNo() {
        return mailNo;
    }
    public void setMailNo(String mailNo) {
        this.mailNo = mailNo;
    }
    public String getInfoType() {
        return infoType;
    }
    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLogisticCompanyId() {
        return logisticCompanyId;
    }
    public void setLogisticCompanyId(String logisticCompanyId) {
        this.logisticCompanyId = logisticCompanyId;
    }
    public String getAcceptTime() {
        return acceptTime;
    }
    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }
    
    
    
}

package com.ibm.oms.intf.intf;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.ibm.oms.intf.intf.inner.TmsPayDTOElement;

/**
 * 支付信息
 *
 * 2014-4-25 下午03:35:50
 */
public class TmsPayDTO implements Serializable{
    @NotBlank(message = "txLogisticID is compulsory")
    private String txLogisticID;//物流订单号(外部编号)
    private List<TmsPayDTOElement> element;
    @NotBlank(message = "outhousetime is compulsory")
    @Pattern(regexp = "((19|20|21)\\d{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])\\s((0?[0-9])|(1[0-9])|(2[0-3])):([0-5][0-9]):([0-5][0-9])", message = "dateTime formatted as yyyy-MM-dd HH:mm:ss")
    private String occurtime;//时间
    private String remark;//备注
    private String operator;//  操作者
    private String logisticCompanyId;//物流公司编号
    
    
    public String getTxLogisticID() {
        return txLogisticID;
    }
    public void setTxLogisticID(String txLogisticId) {
        this.txLogisticID = txLogisticId;
    }

    
    
    public List<TmsPayDTOElement> getElement() {
        return element;
    }
    public void setElement(List<TmsPayDTOElement> element) {
        this.element = element;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getOperator() {
        return operator;
    }
    public void setOperator(String operator) {
        this.operator = operator;
    }
    
    public String getLogisticCompanyId() {
        return logisticCompanyId;
    }
    public void setLogisticCompanyId(String logisticCompanyId) {
        this.logisticCompanyId = logisticCompanyId;
    }
    public String getOccurtime() {
        return occurtime;
    }
    public void setOccurtime(String occurtime) {
        this.occurtime = occurtime;
    }
    
    
}

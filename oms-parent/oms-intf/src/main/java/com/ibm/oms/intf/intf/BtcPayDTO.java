package com.ibm.oms.intf.intf;

import java.io.Serializable;
import java.util.List;




import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ibm.oms.intf.intf.inner.PaymentDTO;

/**
 * 支付信息
 *
 * 2014-4-25 下午03:35:50
 */
@XmlRootElement
@XmlType
@XmlAccessorType(XmlAccessType.PROPERTY)
@JsonIgnoreProperties(ignoreUnknown=true)
public class BtcPayDTO implements Serializable{
    @Valid
    private List<PaymentDTO> paymentDTOs;
	private String btcOrderNo;
	@NotBlank(message = "orderNo is compulsory")
	private String orderNo;
	@NotBlank(message = "occurtime is compulsory")
    @Pattern(regexp = "((19|20|21)\\d{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])\\s((0?[0-9])|(1[0-9])|(2[0-3])):([0-5][0-9]):([0-5][0-9])", message = "dateTime formatted as yyyy-MM-dd hh:mm:ss")
    private String occurtime;//时间
    private String remark;//备注
    private String operator;//  操作者
    
    private String payNo;//支付号
    
    /**
     * 系统来源:[bBwWTA]其中的一个字符，D? 可有可无
     */
    @NotBlank(message = "dataSource is compulsory")
    @Pattern(regexp="[bBwWTAJ][tTbBgGHPD][cCwWXP]D?", message="datasource must be BTC or BBC or WAP or WGW or THXD or APP or JD")
    private String dataSource;

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
    
    public String getOccurtime() {
        return occurtime;
    }
    public void setOccurtime(String occurtime) {
        this.occurtime = occurtime;
    }
  
	public String getBtcOrderNo() {
		return btcOrderNo;
	}
	public void setBtcOrderNo(String btcOrderNo) {
		this.btcOrderNo = btcOrderNo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public List<PaymentDTO> getPaymentDTOs() {
		return paymentDTOs;
	}
	public void setPaymentDTOs(List<PaymentDTO> paymentDTOs) {
		this.paymentDTOs = paymentDTOs;
	}
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	public String getPayNo() {
		return payNo;
	}
	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}

}

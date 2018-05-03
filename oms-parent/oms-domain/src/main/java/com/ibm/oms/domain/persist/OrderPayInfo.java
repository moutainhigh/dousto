/**
 * 
 */
package com.ibm.oms.domain.persist;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xiaonanxiang
 *
 */
public class OrderPayInfo {
    private String payName;
    private BigDecimal payAmount;
    private String payNo;
    private Date payTime;
    public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public BigDecimal getPayAmount() {
        return payAmount;
    }
    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }
    public String getPayNo() {
        return payNo;
    }
    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }
    public String getPayName() {
        return payName;
    }
    public void setPayName(String payName) {
        this.payName = payName;
    }
    
}

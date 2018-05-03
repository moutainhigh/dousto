package com.ibm.oms.intf.intf;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;


/**
 * @author pjsong
 * WMS的质检信息传输至OMS
 */
public class WmsOmsReceiveInspectionResultDTO implements Serializable {

	/** 逆向子订单号 **/
    @NotBlank(message = "orderSubNo is compulsory")
    @Length(max = 17,message = "orderSubNo: length must be less than 17")
	String orderSubNo;

	/** 质检状态or入库状态 **/
    @NotBlank(message = "inspectionOrStorage is compulsory")
    @Length(max = 1,message = "inspectionOrStorage: length must be less than 1")
	String inspectionOrStorage;
    
	/** 质检状态 **/
    @Length(max = 1,message = "inspectionResult: length must be less than 1")
	String inspectionResult;
    
	/** 入库状态 **/
    @Length(max = 1,message = "storageResult: length must be less than 1")
	String storageResult;
	
	/** 质检状态描述 **/
    @Length(max = 255,message = "remark: length must be less than 255")
	String remark;
	
	/** 操作时间 **/
	String operateTime;
	
	/** 操作人 **/
	String operator;

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

    public String getOrderSubNo() {
        return orderSubNo;
    }

    public void setOrderSubNo(String orderSubNo) {
        this.orderSubNo = orderSubNo;
    }

    public String getInspectionOrStorage() {
        return inspectionOrStorage;
    }

    public void setInspectionOrStorage(String inspectionOrStorage) {
        this.inspectionOrStorage = inspectionOrStorage;
    }

    public String getInspectionResult() {
        return inspectionResult;
    }

    public void setInspectionResult(String inspectionResult) {
        this.inspectionResult = inspectionResult;
    }

    public String getStorageResult() {
        return storageResult;
    }

    public void setStorageResult(String storageResult) {
        this.storageResult = storageResult;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }
	
	

}

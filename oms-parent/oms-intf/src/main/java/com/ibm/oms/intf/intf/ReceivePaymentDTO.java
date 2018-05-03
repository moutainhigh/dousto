package com.ibm.oms.intf.intf;
//package com.ibm.sc.service.oms.intf;
//
//import java.io.Serializable;
//import java.util.List;
//
//import javax.validation.constraints.Pattern;
//
//import org.hibernate.validator.constraints.Length;
//import org.hibernate.validator.constraints.NotBlank;
//import org.hibernate.validator.constraints.NotEmpty;
//import org.hibernate.validator.constraints.Range;
//
///**
// * @author pjsong
// * 外部系统(B2C、TMS)的支付状态传输至OMS
// */
//public class ReceivePaymentDTO implements Serializable {
//
//	/** OMS订单号 **/
//    @NotBlank(message = "orderNo is compulsory")
//    @Length(max = 17,message = "orderNo: length must be less than 17")
//	String orderNo;
//    
//	/** 数据来源 tms btc bbc**/
//    @NotBlank(message = "dataSource is compulsory")
//    @Pattern(regexp = "(0|1|2)")
//	String dataSource;
//    
//	/** 货到付款支付方式 **/
//    @Length(max = 32,message = "payOnArrivalPayType: length must be less than 32")
//	String payOnArrivalPayType;
//    
//	/** 操作员 **/
//    @Length(max = 32,message = "operatorName: length must be less than 32")
//    String operatorName;
//	
//	/** 备注 **/
//    @Length(max = 255,message = "remark: length must be less than 255")
//    String remark;
//    
//	/** 支付明细 **/	
////    @NotEmpty(message = "ipDTOs is compulsory")
//	List<PaymentDTO> ipDTOs;
//    
//	public String getOrderNo() {
//		return orderNo;
//	}
//	public void setOrderNo(String orderNo) {
//		this.orderNo = orderNo;
//	}
//	public String getPayOnArrivalPayType() {
//		return payOnArrivalPayType;
//	}
//	public void setPayOnArrivalPayType(String payOnArrivalPayType) {
//		this.payOnArrivalPayType = payOnArrivalPayType;
//	}
//	public String getOperatorName() {
//		return operatorName;
//	}
//	public void setOperatorName(String operatorName) {
//		this.operatorName = operatorName;
//	}
//	public String getRemark() {
//		return remark;
//	}
//	public void setRemark(String remark) {
//		this.remark = remark;
//	}
//	public List<PaymentDTO> getIpDTOs() {
//		return ipDTOs;
//	}
//	public void setIpDTOs(List<PaymentDTO> ipDTOs) {
//		this.ipDTOs = ipDTOs;
//	}
//	public String getDataSource() {
//		return dataSource;
//	}
//	public void setDataSource(String dataSource) {
//		this.dataSource = dataSource;
//	}
//
//}

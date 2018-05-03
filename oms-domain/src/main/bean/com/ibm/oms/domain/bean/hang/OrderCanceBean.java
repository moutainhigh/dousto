package com.ibm.oms.domain.bean.hang;

/**
 * @author: mr.kai
 * @Description: 中台->wms单据取消通知接口Dao层Bean
 * @create: 2018-03-09 8:11
 **/
public class OrderCanceBean implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Consignor;
    private String BillNo;
    private String BillType;

    public OrderCanceBean() {
    }

    public String getConsignor() {
        return this.Consignor;
    }

    public void setConsignor(String consignor) {
        this.Consignor = consignor;
    }

    public String getBillNo() {
        return this.BillNo;
    }

    public void setBillNo(String billNo) {
        this.BillNo = billNo;
    }

    public String getBillType() {
        return this.BillType;
    }

    public void setBillType(String billType) {
        this.BillType = billType;
    }
}

package com.ibm.oms.intf.intf.inner;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author xiaohl
 * 换货意向单的收获人信息
 */
public class ChangeOrderReciveInfoDTO implements Serializable {
   
    /** 收货联系人姓名 **/
    @NotBlank(message = "user_name is compulsory")
    @Length(max = 128,message = "user_name: length must be less than 128")
    String userName;
    
    /** 收货联系人电话1（固话） **/
    @Length(max = 32,message = "phoneNum: length must be less than 128")
    String phoneNum;
    
    /** 收货联系人电话2（移动） **/
    @Length(max = 32,message = "mob_phoneNum: length must be less than 32")
    String mobPhoneNum;
    
    /** 收货地址信息编码 **/
    @Length(max = 255,message = "address_code: length must be less than 255")
    String addressCode;
    
    /** 收货具体地址 **/
    @NotBlank(message = "address_detail is compulsory")
    @Length(max = 255,message = "address_detail: length must be less than 255")
    String addressDetail;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getMobPhoneNum() {
        return mobPhoneNum;
    }

    public void setMobPhoneNum(String mobPhoneNum) {
        this.mobPhoneNum = mobPhoneNum;
    }

    public String getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    
}

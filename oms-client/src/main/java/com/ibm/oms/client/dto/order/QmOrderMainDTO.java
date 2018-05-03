package com.ibm.oms.client.dto.order;

import java.io.Serializable;
import java.util.List;

/**
 * @author: mr.kai
 * @Description: 奇门创建订单实体类
 * @create: 2018-04-09 15:26
 **/
public class QmOrderMainDTO implements Serializable {
    private Long user_id;//Number  必须	100000		用户ID
    private Long order_source;//Number	必须	1		订单来源，值选择：30
    private Long order_type;//Number	必须	0		订单类型，值固定选择：30
    private Long logis_type;//Number	必须	1		物流订单物流类型，值固定选择：2
    private Long company_id;//Number	必须	500		物流公司ID
    private Long trade_id;//Number	必须	100000		交易流水号，淘外订单号或者商家内部交易流水号
    private String mail_no;//String	可选	v12143		运单号
    private String shipping;//String	可选	1		费用承担方式 1买家承担运费 2卖家承担运费
    private String s_name;//String	必须	小兴		发件人名称
    private Long s_area_id;//Number	必须	1000		发件人区域ID
    private String s_address;//String	必须	西湖区华星时代		发件人街道地址
    private String s_zip_code;//String	必须	112		发件人出编
    private String s_mobile_phone;//String	可选	15658873040		手机号码
    private String s_telephone;//String	可选	0793-2943359-008		电话号码
    private String s_prov_name;//String	必须	浙江		省
    private String s_city_name;//String	必须	杭州		市
    private String  s_dist_name;//String	可选	西湖区		区
    private String  r_name;//String	必须	小兴		收件人名称
    private Long  r_area_id;//Number	必须	23423		收件人区域ID
    private String  r_address;//String	必须	西湖区华星时代		收件人街道地址
    private String  r_zip_code;//String	必须	3423		收件人邮编
    private String  r_mobile_phone;//String	可选	15658873040		手机号码
    private String  r_telephone;//String	可选	0793-2943359-008		电话号码
    private String  r_prov_name;//String	必须	汽水		省
    private String  r_city_name;//String	必须	杭州		市
    private String  r_dist_name;//String	可选	西湖区		区
    private List<QmOrderItemDTO> item_json_string;//订单中包含的商品信息

    public QmOrderMainDTO() {
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getOrder_source() {
        return order_source;
    }

    public void setOrder_source(Long order_source) {
        this.order_source = order_source;
    }

    public Long getOrder_type() {
        return order_type;
    }

    public void setOrder_type(Long order_type) {
        this.order_type = order_type;
    }

    public Long getLogis_type() {
        return logis_type;
    }

    public void setLogis_type(Long logis_type) {
        this.logis_type = logis_type;
    }

    public Long getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Long company_id) {
        this.company_id = company_id;
    }

    public Long getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(Long trade_id) {
        this.trade_id = trade_id;
    }

    public String getMail_no() {
        return mail_no;
    }

    public void setMail_no(String mail_no) {
        this.mail_no = mail_no;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public Long getS_area_id() {
        return s_area_id;
    }

    public void setS_area_id(Long s_area_id) {
        this.s_area_id = s_area_id;
    }

    public String getS_address() {
        return s_address;
    }

    public void setS_address(String s_address) {
        this.s_address = s_address;
    }

    public String getS_zip_code() {
        return s_zip_code;
    }

    public void setS_zip_code(String s_zip_code) {
        this.s_zip_code = s_zip_code;
    }

    public String getS_mobile_phone() {
        return s_mobile_phone;
    }

    public void setS_mobile_phone(String s_mobile_phone) {
        this.s_mobile_phone = s_mobile_phone;
    }

    public String getS_telephone() {
        return s_telephone;
    }

    public void setS_telephone(String s_telephone) {
        this.s_telephone = s_telephone;
    }

    public String getS_prov_name() {
        return s_prov_name;
    }

    public void setS_prov_name(String s_prov_name) {
        this.s_prov_name = s_prov_name;
    }

    public String getS_city_name() {
        return s_city_name;
    }

    public void setS_city_name(String s_city_name) {
        this.s_city_name = s_city_name;
    }

    public String getS_dist_name() {
        return s_dist_name;
    }

    public void setS_dist_name(String s_dist_name) {
        this.s_dist_name = s_dist_name;
    }

    public String getR_name() {
        return r_name;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }

    public Long getR_area_id() {
        return r_area_id;
    }

    public void setR_area_id(Long r_area_id) {
        this.r_area_id = r_area_id;
    }

    public String getR_address() {
        return r_address;
    }

    public void setR_address(String r_address) {
        this.r_address = r_address;
    }

    public String getR_zip_code() {
        return r_zip_code;
    }

    public void setR_zip_code(String r_zip_code) {
        this.r_zip_code = r_zip_code;
    }

    public String getR_mobile_phone() {
        return r_mobile_phone;
    }

    public void setR_mobile_phone(String r_mobile_phone) {
        this.r_mobile_phone = r_mobile_phone;
    }

    public String getR_telephone() {
        return r_telephone;
    }

    public void setR_telephone(String r_telephone) {
        this.r_telephone = r_telephone;
    }

    public String getR_prov_name() {
        return r_prov_name;
    }

    public void setR_prov_name(String r_prov_name) {
        this.r_prov_name = r_prov_name;
    }

    public String getR_city_name() {
        return r_city_name;
    }

    public void setR_city_name(String r_city_name) {
        this.r_city_name = r_city_name;
    }

    public String getR_dist_name() {
        return r_dist_name;
    }

    public void setR_dist_name(String r_dist_name) {
        this.r_dist_name = r_dist_name;
    }

    public List<QmOrderItemDTO> getItem_json_string() {
        return item_json_string;
    }

    public void setItem_json_string(List<QmOrderItemDTO> item_json_string) {
        this.item_json_string = item_json_string;
    }
}

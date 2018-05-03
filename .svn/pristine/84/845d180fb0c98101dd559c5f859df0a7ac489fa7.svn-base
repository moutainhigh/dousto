package com.ibm.oms.intf.intf;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author pjsong
 *
 */
@XmlRootElement
@XmlType
@XmlAccessorType(XmlAccessType.PROPERTY)
/**库存返回，同时供锁定，扣减等使用**/
public class InventoryLockOutputDTO implements Serializable {

	private static final long serialVersionUID = 1L;
    private String return_status;
    
    private String return_code;
    private String return_msg;
    public String getReturn_status() {
        return return_status;
    }
    public void setReturn_status(String return_status) {
        this.return_status = return_status;
    }
    public String getReturn_code() {
        return return_code;
    }
    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }
    public String getReturn_msg() {
        return return_msg;
    }
    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

}

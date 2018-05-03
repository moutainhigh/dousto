package com.ibm.oms.domain.persist;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "send_sap_intf")
public class SendSapIntef implements Serializable {
    @Id
    @TableGenerator(name = "send_sap_intf", table = "sys_id", pkColumnName = "key_id", valueColumnName = "key_value", pkColumnValue = "send_sap_intf_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "send_sap_intf")
    @Column(unique = true, nullable = false, precision = 22)
    private Long id;
    @Column(name = "CREATE_DATE")
    private Date createDate;
    @Column(name = "DESCCRIPTION")
    private String desccription;

    private static final long serialVersionUID = -2545311091311885932L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDesccription() {
        return desccription;
    }

    public void setDesccription(String desccription) {
        this.desccription = desccription == null ? null : desccription.trim();
    }
}
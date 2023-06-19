package com.kevin.springdata.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 类名: Person<br/>
 * 包名：com.kevin.springdata.entity<br/>
 * 作者：kevin<br/>
 * 时间：2019/8/19 20:05<br/>
 * 版本：1.0<br/>
 * 描述：<br/>
 */
@Entity
@Table(name = "T_PERSON")
@DynamicInsert
@DynamicUpdate
public class Person {

    private Long id;
    private String lastName;
    private String email;
    private Date birth;
    private Address address;
    private Integer addressId;
    private String auditStauts;
    private Integer processStatus;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @ManyToOne
    @JoinColumn(name = "ADDRESS_ID")
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Column(name = "ADDR_ID")
    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getAuditStauts() {
        return auditStauts;
    }

    public void setAuditStauts(String auditStauts) {
        this.auditStauts = auditStauts;
    }

    public Integer getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(Integer processStatus) {
        this.processStatus = processStatus;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                ", address=" + address +
                ", addressId=" + addressId +
                ", auditStauts='" + auditStauts + '\'' +
                ", processStatus=" + processStatus +
                '}';
    }
}

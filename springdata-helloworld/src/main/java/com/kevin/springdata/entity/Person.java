package com.kevin.springdata.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
public class Person {

    private Integer id;
    private String lastName;
    private String email;
    private Date birth;
    private Address address;
    private int addressId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Column(name = "ADD_ID")
    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                ", address=" + address +
                '}';
    }
}

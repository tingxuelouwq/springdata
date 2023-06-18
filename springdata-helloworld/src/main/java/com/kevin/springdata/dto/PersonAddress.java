package com.kevin.springdata.dto;

import com.kevin.springdata.entity.Address;

/**
 * @类名: PersonAddress<br />
 * @包名：com.kevin.springdata.dto<br/>
 * @作者：kevin<br/>
 * @时间：2019/8/28 22:11<br/>
 * @版本：1.0<br/>
 * @描述：<br/>
 */
public class PersonAddress {
    private String lastName;
    private Address address;

    public PersonAddress(String lastName, Address address) {
        this.lastName = lastName;
        this.address = address;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

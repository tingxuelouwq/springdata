package com.kevin.springdata.dto;

import com.kevin.springdata.entity.Address;

/**
 * 类名: PerAddr<br/>
 * 包名：com.kevin.springdata.dto<br/>
 * 作者：kevin<br/>
 * 时间：2019/9/11 15:14<br/>
 * 版本：1.0<br/>
 * 描述：<br/>
 */
public class PerAddr {

    private String lastName;
    private Address address;

    public PerAddr(String lastName, Integer id, String province, String city) {
        this.lastName = lastName;
        this.address = new Address(id, province, city);
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

    @Override
    public String toString() {
        return "PerAddr{" +
                "lastName='" + lastName + '\'' +
                ", address=" + address +
                '}';
    }
}

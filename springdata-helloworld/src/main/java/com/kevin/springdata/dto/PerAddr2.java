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
public class PerAddr2 {

    private String name;
    private Address address;

    public PerAddr2(String name, Integer id, String province, String city) {
        this.name = name;
        this.address = new Address(id, province, city);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}

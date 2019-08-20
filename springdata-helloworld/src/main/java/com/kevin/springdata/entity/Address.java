package com.kevin.springdata.entity;

import javax.persistence.*;

/**
 * 类名: Address<br/>
 * 包名：com.kevin.springdata.entity<br/>
 * 作者：kevin<br/>
 * 时间：2019/8/20 10:46<br/>
 * 版本：1.0<br/>
 * 描述：<br/>
 */
@Entity
@Table(name = "t_address")
public class Address {

    private Integer id;
    private String province;
    private String city;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

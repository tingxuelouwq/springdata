package com.kevin.springdata.dto;

/**
 * 类名: PersonQuery2<br/>
 * 包名：com.kevin.springdata.dto<br/>
 * 作者：kevin<br/>
 * 时间：2019/9/6 14:32<br/>
 * 版本：1.0<br/>
 * 描述：<br/>
 */
public class PersonQuery2 {

    private String lastName;
    private String city;

    public PersonQuery2(String lastName, String city) {
        this.lastName = lastName;
        this.city = city;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

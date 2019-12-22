package com.kevin.springdata.dto;

/**
 * @类名: PersonAddress4<br />
 * @包名：com.kevin.springdata.dto<br/>
 * @作者：kevin<br/>
 * @时间：2019/12/22 12:06<br/>
 * @版本：1.0<br/>
 * @描述：<br/>
 */
public class PersonAddress4 {

    private String lastName;
    private String province;
    private String city;

    public PersonAddress4() {
    }

    public PersonAddress4(String lastName, String province, String city) {
        this.lastName = lastName;
        this.province = province;
        this.city = city;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

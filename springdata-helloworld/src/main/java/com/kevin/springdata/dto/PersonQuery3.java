package com.kevin.springdata.dto;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 类名: PersonQuery3<br/>
 * 包名：com.kevin.springdata.dto<br/>
 * 作者：kevin<br/>
 * 时间：2019/9/6 14:32<br/>
 * 版本：1.0<br/>
 * 描述：<br/>
 */
public class PersonQuery3 {

    private String lastName;
    private Date birth;

    public PersonQuery3(String lastName, Date birth) {
        this.lastName = lastName;
        this.birth = birth;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "PersonQuery3{" +
                "lastName='" + lastName + '\'' +
                ", birth='" + birth + '\'' +
                '}';
    }
}

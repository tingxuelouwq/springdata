package com.kevin.springdata.dto;

import java.util.Date;

/**
 * kevin<br/>
 * 2023/6/18 21:23<br/>
 */
public class PersonTransDTO {

    private Long id;
    private String lastName;
    private String email;
    private Date birth;

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
}

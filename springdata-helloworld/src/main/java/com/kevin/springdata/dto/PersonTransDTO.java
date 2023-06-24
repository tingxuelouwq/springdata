package com.kevin.springdata.dto;

import java.math.BigDecimal;

/**
 * kevin<br/>
 * 2023/6/18 21:23<br/>
 */
public class PersonTransDTO {

    private Long id;
    private String lastName;
    private String email;
    private Long birth;
    private String auditStatus;
    private Integer processStatus;
    private Integer auditStatusInt;
    private BigDecimal happenPlaceGISLon;

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

    public Long getBirth() {
        return birth;
    }

    public void setBirth(Long birth) {
        this.birth = birth;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(Integer processStatus) {
        this.processStatus = processStatus;
    }

    public Integer getAuditStatusInt() {
        return auditStatusInt;
    }

    public void setAuditStatusInt(Integer auditStatusInt) {
        this.auditStatusInt = auditStatusInt;
    }

    public BigDecimal getHappenPlaceGISLon() {
        return happenPlaceGISLon;
    }

    public void setHappenPlaceGISLon(BigDecimal happenPlaceGISLon) {
        this.happenPlaceGISLon = happenPlaceGISLon;
    }
}

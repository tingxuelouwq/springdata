package com.kevin.springdata.service;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * kevin<br/>
 * 2023/6/23 18:26<br/>
 */
public class QueryObject {

    private List<Integer> ids;
    @JsonIgnore
    private String param1;

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}

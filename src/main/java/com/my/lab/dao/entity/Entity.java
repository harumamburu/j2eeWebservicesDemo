package com.my.lab.dao.entity;

public interface Entity {

    Integer getId();
    void setId(Integer id);
    <T extends Object> T getNaturalId();
}

package com.my.lab.dao.entity;

import com.my.lab.core.Identifiable;

import java.util.List;

public interface JPAEntity extends Identifiable {

    <T extends Object> T getNaturalId();
    <K extends JPAEntity> List<K> getNestedEntities();
}

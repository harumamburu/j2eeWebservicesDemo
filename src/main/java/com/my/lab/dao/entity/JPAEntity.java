package com.my.lab.dao.entity;

import com.my.lab.core.Identifiable;

import java.util.List;
import java.util.Map;

public interface JPAEntity extends Identifiable, Replicateable<JPAEntity> {

    Map<String, ?> getNaturalId();
    <K extends JPAEntity> List<K> getNestedEntities();
}

package com.my.lab.dao.entity;

import com.my.lab.core.Identifiable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public interface JPAEntity extends Identifiable, Replicateable<JPAEntity> {

    default Map<String, ?> getNaturalId() {
        return new HashMap<>(0);
    }
    default <K extends JPAEntity> List<K> getNestedEntities() {
        return new ArrayList<>(0);
    }
}

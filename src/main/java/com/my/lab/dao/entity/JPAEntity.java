package com.my.lab.dao.entity;

import com.my.lab.core.Identifiable;

public interface JPAEntity extends Identifiable {

    <T extends Object> T getNaturalId();
}

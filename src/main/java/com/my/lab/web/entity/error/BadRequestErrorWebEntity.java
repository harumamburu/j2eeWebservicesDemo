package com.my.lab.web.entity.error;

import javax.ws.rs.core.Response.Status;

public class BadRequestErrorWebEntity extends ErrorWebEntity {

    private final static Status STATUS = Status.BAD_REQUEST;

    public BadRequestErrorWebEntity() {
        super(STATUS);
    }

    public BadRequestErrorWebEntity(String message) {
        super(STATUS, message);
    }
}

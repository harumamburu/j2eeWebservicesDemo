package com.my.lab.web.entity.error;

import javax.ws.rs.core.Response.Status;

public class NotFoundErrorWebEntity extends ErrorWebEntity {

    private final static Status STATUS = Status.NOT_FOUND;

    public NotFoundErrorWebEntity() {
        super(STATUS);
    }

    public NotFoundErrorWebEntity(String message) {
        super(STATUS, message);
    }
}

package com.my.lab.web.entity.error.exception;

import javax.ws.rs.core.Response.Status;

public class AlreadyExistsException extends  ExceptionWebEntity {

    public AlreadyExistsException(Throwable cause) {
        super(Status.CONFLICT, cause);
    }
}

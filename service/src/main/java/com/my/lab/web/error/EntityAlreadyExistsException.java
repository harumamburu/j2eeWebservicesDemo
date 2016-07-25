package com.my.lab.web.error;


import javax.ws.rs.core.Response.Status;

public class EntityAlreadyExistsException extends WebException {

    public EntityAlreadyExistsException() {
        super();
    }

    public EntityAlreadyExistsException(String message) {
        super(message);
    }

    public EntityAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public EntityAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public Status getStatus() {
        return Status.CONFLICT;
    }
}

package com.my.lab.web.error;

import javax.ws.rs.core.Response.Status;

public class EntityNotFoundException extends WebException {

    public EntityNotFoundException() {
        super();
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public Status getStatus() {
        return Status.NOT_FOUND;
    }
}

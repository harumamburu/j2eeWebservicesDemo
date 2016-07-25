package com.my.lab.web.error;

import javax.ws.rs.core.Response.Status;

public class EntityMisformattedException extends WebException {

    public EntityMisformattedException() {
        super();
    }

    public EntityMisformattedException(String message) {
        super(message);
    }

    public EntityMisformattedException(Throwable cause) {
        super(cause);
    }

    public EntityMisformattedException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public Status getStatus() {
        return Status.BAD_REQUEST;
    }
}

package com.my.lab.web.error;

import javax.ws.rs.core.Response.Status;

public class EntityIdMisformatException extends WebException {

    public EntityIdMisformatException() {
        super();
    }

    public EntityIdMisformatException(String message) {
        super(message);
    }

    public EntityIdMisformatException(Throwable cause) {
        super(cause);
    }

    public EntityIdMisformatException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public Status getStatus() {
        return Status.BAD_REQUEST;
    }
}

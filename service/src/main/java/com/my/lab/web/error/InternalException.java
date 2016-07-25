package com.my.lab.web.error;

import javax.ws.rs.core.Response.Status;

public class InternalException extends WebException {

    public InternalException() {
        super();
    }

    public InternalException(String message) {
        super(message);
    }

    public InternalException(Throwable cause) {
        super(cause);
    }

    public InternalException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public Status getStatus() {
        return Status.INTERNAL_SERVER_ERROR;
    }
}

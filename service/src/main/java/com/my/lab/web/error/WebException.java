package com.my.lab.web.error;

import javax.ejb.ApplicationException;
import javax.ws.rs.core.Response.Status;

@ApplicationException
public abstract class WebException extends RuntimeException {

    public WebException() {
        super();
    }

    public WebException(String message) {
        super(message);
    }

    public WebException(Throwable cause) {
        super(cause.getMessage(), cause);
    }

    public WebException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract Status getStatus();
}

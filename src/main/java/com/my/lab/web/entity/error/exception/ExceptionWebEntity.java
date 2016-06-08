package com.my.lab.web.entity.error.exception;

import com.my.lab.web.entity.error.ErrorWebEntity;

import javax.ws.rs.core.Response.Status;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionWebEntity extends ErrorWebEntity {

    protected Throwable cause;
    protected String stackTrace;

    public ExceptionWebEntity(Status status, Throwable cause) {
        super(status, cause.getMessage());
        this.cause = cause;
        try(StringWriter sw = new StringWriter()) {
            cause.printStackTrace(new PrintWriter(sw));
            stackTrace = sw.toString();
        } catch (IOException exc) {
        }
    }

    public ExceptionWebEntity(Throwable cause) {
        this(Status.INTERNAL_SERVER_ERROR, cause);
    }
}

package com.my.lab.web.entity.error;

import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
public abstract class ErrorWebEntity {

    protected String status;
    protected int code;
    protected String message;

    public ErrorWebEntity(Status status) {
        this.status = status.getReasonPhrase();
        code = status.getStatusCode();
    }

    public ErrorWebEntity(Status status, String message) {
        this(status);
        this.message = message;
    }
}

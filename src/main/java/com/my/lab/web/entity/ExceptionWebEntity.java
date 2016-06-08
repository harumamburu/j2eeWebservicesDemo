package com.my.lab.web.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.my.lab.web.error.WebException;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "error")
@JsonPropertyOrder({ "message", "status", "code" })
public class ExceptionWebEntity implements WebEntity {

    private String status;
    private int code;
    private String message;

    public ExceptionWebEntity() {
    }

    public ExceptionWebEntity(WebException exc) {
        status = exc.getStatus().getReasonPhrase();
        code = exc.getStatus().getStatusCode();
        message = exc.getMessage();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

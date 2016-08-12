package com.my.lab.properties.exception;

public class UnmarshallingException extends PropertiesLoadingException {

    public UnmarshallingException(String message, Throwable cause) {
        super(message, cause);
    }
}

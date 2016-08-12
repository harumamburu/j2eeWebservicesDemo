package com.my.lab.properties.exception;

public class PropertiesReadingException extends PropertiesLoadingException {

    public PropertiesReadingException(String message, Throwable cause) {
        super(message, cause);
    }
}

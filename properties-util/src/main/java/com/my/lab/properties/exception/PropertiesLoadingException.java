package com.my.lab.properties.exception;

public abstract class PropertiesLoadingException extends Exception {

    public PropertiesLoadingException(String message, Throwable cause) {
        super(message, cause);
    }
}

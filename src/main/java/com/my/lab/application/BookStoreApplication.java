package com.my.lab.application;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/bookstore/api")
public class BookStoreApplication extends ResourceConfig {

    public BookStoreApplication() {
        packages("com.my.lab");
        register(CustomJacksonDateMappingProvider.class);
    }
}

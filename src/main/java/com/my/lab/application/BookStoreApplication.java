package com.my.lab.application;

import io.swagger.jaxrs.config.BeanConfig;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/bookstore/api")
public class BookStoreApplication extends ResourceConfig {

    public BookStoreApplication() {
        BeanConfig swaggerConfig = new BeanConfig();
        swaggerConfig.setSchemes(new String[]{"http", "https"});
        swaggerConfig.setHost("localhost:8080");
        swaggerConfig.setBasePath("/bookstore/api");
        swaggerConfig.setResourcePackage("com.my.lab");
        swaggerConfig.setScan(true);

        packages("com.my.lab","io.swagger.jaxrs.json", "io.swagger.jaxrs.listing",
                "com.fasterxml.jackson.jaxrs");
        register(CustomJacksonDateMappingProvider.class);
    }
}

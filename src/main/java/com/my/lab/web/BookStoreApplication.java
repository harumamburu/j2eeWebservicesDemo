package com.my.lab.web;

import com.my.lab.web.setting.json.CustomJacksonDateMappingProvider;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.models.Info;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

// TODO: make deployment-agnostic (extend application)
@ApplicationPath("/bookstore/api")
public class BookStoreApplication extends ResourceConfig {

    public BookStoreApplication() {
        initSwagger();
        packages("com.my.lab","io.swagger.jaxrs.json", "io.swagger.jaxrs.listing");
        register(CustomJacksonDateMappingProvider.class);
        property("jersey.config.disableMoxyJson", true);
    }

    private void initSwagger() {
        BeanConfig swaggerConfig = new BeanConfig();
        swaggerConfig.setSchemes(new String[]{"http", "https"});
        swaggerConfig.setHost("localhost:8080");
        swaggerConfig.setBasePath("/jaxrsws/bookstore/api");
        swaggerConfig.setResourcePackage("com.my.lab");
        swaggerConfig.setInfo(new Info().title("Book store web API"));
        swaggerConfig.setScan(true);
    }
}

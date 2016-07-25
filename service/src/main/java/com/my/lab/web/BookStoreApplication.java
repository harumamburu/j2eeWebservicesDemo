package com.my.lab.web;

import com.my.lab.web.error.mapper.ErrorToResponseMapper;
import com.my.lab.web.resource.AuthorResource;
import com.my.lab.web.resource.BookResource;
import com.my.lab.web.setting.json.CustomJacksonDateMappingProvider;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.models.Info;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@ApplicationPath("/bookstore/api")
public class BookStoreApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        initSwagger();

        final Set<Class<?>> classes = new HashSet<>();
        classes.add(BookResource.class);
        classes.add(AuthorResource.class);
        classes.add(CustomJacksonDateMappingProvider.class);
        classes.add(ErrorToResponseMapper.class);
        classes.add(io.swagger.jaxrs.json.JacksonJsonProvider.class);
        classes.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        classes.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
        return classes;
    }

    @Override
    public Map<String, Object> getProperties() {
        final Map<String, Object> properties = new HashMap<>();
        properties.put("jersey.config.disableMoxyJson", true);
        return  properties;
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

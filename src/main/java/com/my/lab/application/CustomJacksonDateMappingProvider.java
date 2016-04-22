package com.my.lab.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.my.lab.data.json.BirthDateDeserializer;
import com.my.lab.data.json.BirthDateSerializer;
import com.my.lab.data.json.PublishingDateDeserializer;
import com.my.lab.data.json.PublishingDateSerializer;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import java.util.Date;

@Provider
public class CustomJacksonDateMappingProvider implements ContextResolver<ObjectMapper> {

    private static final String SERIALIZATION_MODULE_NAME = "Custom serialization module";

    private final ObjectMapper mapper;

    public CustomJacksonDateMappingProvider() {
        mapper = new ObjectMapper();
        SimpleModule serializationModule = new SimpleModule(SERIALIZATION_MODULE_NAME);

        serializationModule.addDeserializer(Date.class, new PublishingDateDeserializer());
        serializationModule.addDeserializer(Date.class, new BirthDateDeserializer());
        serializationModule.addSerializer(Date.class, new PublishingDateSerializer());
        serializationModule.addSerializer(Date.class, new BirthDateSerializer());

        mapper.registerModule(serializationModule);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .configure(SerializationFeature.INDENT_OUTPUT, true);
    }

    @Override
    public ObjectMapper getContext(Class<?> aClass) {
        return mapper;
    }
}

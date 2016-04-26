package com.my.lab.web.setting.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.my.lab.business.DateFormats;

import java.io.IOException;
import java.util.Date;

public class BirthDateSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeString(DateFormats.BIRTH_DATE_FORMAT.fromDate(date));
    }
}

package com.my.lab.web.setting.json.deserialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.my.lab.business.entity.format.DateFormats;

import java.io.IOException;
import java.util.Date;

public class PublishingDateSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeString(DateFormats.YEAR_DATE_FORMAT.fromDate(date));
    }
}

package com.my.lab.web.setting.json.deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.my.lab.business.entity.format.DateFormats;

import java.io.IOException;
import java.util.Date;

public class BirthDateDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        return DateFormats.BIRTH_DATE_FORMAT.fromSting(jsonParser.getText());
    }
}

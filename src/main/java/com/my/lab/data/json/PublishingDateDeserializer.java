package com.my.lab.data.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.my.lab.data.DateFormats;

import java.io.IOException;
import java.util.Date;

public class PublishingDateDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        return DateFormats.YEAR_DATE_FORMAT.fromSting(jsonParser.getText());
    }
}

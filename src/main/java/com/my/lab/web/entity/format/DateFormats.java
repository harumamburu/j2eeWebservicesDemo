package com.my.lab.web.entity.format;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum DateFormats {

    YEAR_DATE_FORMAT(new SimpleDateFormat("yyyy")),
    BIRTH_DATE_FORMAT(new SimpleDateFormat("MMMM d, yyyy"));

    private final SimpleDateFormat format;

    DateFormats(SimpleDateFormat format) {
        this.format = format;
    }

    public Date fromSting(String date) {
        try {
            // TODO: fix \\d4 leading to birth dates being misparsed
            Matcher matcher = Pattern.compile("\\d{4}").matcher(date);//([A-Za-z]+ \d{1,2}, \d{4}|\d{4})
            return matcher.find() ? format.parse(matcher.group()) : null;
        } catch (ParseException | NullPointerException exc) {
            return null;
        }
    }

    public String fromDate(Date date) {
        try {
            return format.format(date);
        } catch (NullPointerException exc) {
            return null;
        }
    }
}

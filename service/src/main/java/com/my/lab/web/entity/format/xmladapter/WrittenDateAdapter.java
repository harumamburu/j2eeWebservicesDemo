package com.my.lab.web.entity.format.xmladapter;

import com.my.lab.web.entity.format.DateFormats;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Date;

public class WrittenDateAdapter extends XmlAdapter<String, Date> {

    @Override
    public Date unmarshal(String v) throws Exception {
        return DateFormats.YEAR_DATE_FORMAT.fromSting(v);
    }

    @Override
    public String marshal(Date v) throws Exception {
        return DateFormats.YEAR_DATE_FORMAT.fromDate(v);
    }
}

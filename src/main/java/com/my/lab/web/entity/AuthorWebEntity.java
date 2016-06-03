package com.my.lab.web.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.my.lab.web.entity.format.xmladapter.BirthDateAdapter;
import com.my.lab.web.setting.json.deserialization.BirthDateDeserializer;
import com.my.lab.web.setting.json.deserialization.BirthDateSerializer;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

public class AuthorWebEntity implements WebEntity {

    private Integer authorId;
    private String name;
    @JsonDeserialize(using = BirthDateDeserializer.class)
    @JsonSerialize(using = BirthDateSerializer.class)
    private Date birth;

    public AuthorWebEntity() {
        // Default empty constructor JSON data binding
    }

    @Override
    public Integer getId() {
        return authorId;
    }

    @Override
    @XmlAttribute(name = "id", required = true)
    public void setId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    @XmlElement(required = true)
    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    @XmlJavaTypeAdapter(BirthDateAdapter.class)
    public void setBirth(Date birth) {
        this.birth = birth;
    }
}

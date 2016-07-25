package com.my.lab.web.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.my.lab.core.Identifiable;
import com.my.lab.web.entity.format.xmladapter.BirthDateAdapter;
import com.my.lab.web.setting.json.deserialization.BirthDateDeserializer;
import com.my.lab.web.setting.json.deserialization.BirthDateSerializer;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

@XmlRootElement(name = "author")
public class AuthorWebEntity implements WebEntity, Identifiable {

    @JsonProperty(value = "id", required = true)
    private Integer authorId;
    @JsonProperty(required = true)
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

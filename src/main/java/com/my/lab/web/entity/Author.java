package com.my.lab.web.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.my.lab.web.setting.json.deserialization.BirthDateDeserializer;
import com.my.lab.web.setting.json.deserialization.BirthDateSerializer;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Author {

    @NotNull
    private Integer authorId;
    @NotNull
    private String name;
    @JsonDeserialize(using = BirthDateDeserializer.class)
    @JsonSerialize(using = BirthDateSerializer.class)
    private Date birth;

    public Author() {
        // Default empty constructor JSON data binding
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}

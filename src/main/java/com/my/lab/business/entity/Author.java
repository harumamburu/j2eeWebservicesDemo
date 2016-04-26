package com.my.lab.business.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.my.lab.business.DateFormats;
import com.my.lab.web.setting.json.BirthDateDeserializer;
import com.my.lab.web.setting.json.BirthDateSerializer;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Author {

    private Integer authorId;
    private String name;
    @JsonDeserialize(using = BirthDateDeserializer.class)
    @JsonSerialize(using = BirthDateSerializer.class)
    private Date birth;

    public Author(String name) {
        this.name = name;
    }

    public Author() {
        // Default empty constuctor for JSON data bindingor for JSON data binding
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

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", name='" + name + '\'' +
                ", birth=" + DateFormats.BIRTH_DATE_FORMAT.fromDate(birth) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (authorId != null ? !authorId.equals(author.authorId) : author.authorId != null) return false;
        if (!name.equals(author.name)) return false;
        return !(birth != null ? !birth.equals(author.birth) : author.birth != null);

    }

    @Override
    public int hashCode() {
        int result = authorId != null ? authorId.hashCode() : 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + (birth != null ? birth.hashCode() : 0);
        return result;
    }
}

package com.my.lab.business.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.my.lab.business.Constants;
import com.my.lab.business.entity.format.DateFormats;
import com.my.lab.web.setting.json.deserialization.BirthDateDeserializer;
import com.my.lab.web.setting.json.deserialization.BirthDateSerializer;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@javax.persistence.Entity(name = Constants.AUTHOR_TABLE_NAME)
public class Author implements Entity {

    @Id
    @GeneratedValue(generator = "author_counter")
    @GenericGenerator(name = "author_counter", strategy = "increment")
    @Column(name = Constants.AUTHOR_COLUMN_ID)
    private Integer authorId;

    @NotNull
    @Column(name = Constants.AUTHOR_COLUMN_NAME, nullable = false)
    private String name;

    @JsonDeserialize(using = BirthDateDeserializer.class)
    @JsonSerialize(using = BirthDateSerializer.class)
    @Temporal(TemporalType.DATE)
    private Date birth;

    public Author(String name) {
        this.name = name;
    }

    public Author() {
        // Default empty constructor for JSON data bindings
    }

    public Integer getId() {
        return authorId;
    }

    public void setId(Integer authorId) {
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

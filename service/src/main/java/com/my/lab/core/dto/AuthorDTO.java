package com.my.lab.core.dto;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class AuthorDTO implements DTO {

    @NotNull
    private Integer authorId;
    @NotNull
    private String name;
    private Date birth;

    @Override
    public Integer getId() {
        return authorId;
    }

    @Override
    public void setId(Integer id) {
        this.authorId = id;
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
        return "Author : { " +
                "authorId=" + authorId +
                ", name='" + name + "'" +
                ", birth=" + birth.toString() +
                " }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthorDTO author = (AuthorDTO) o;

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

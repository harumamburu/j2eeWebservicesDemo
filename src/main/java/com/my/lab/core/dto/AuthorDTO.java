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
}

package com.my.lab.core.dto;

import com.my.lab.core.dto.enumeration.Genre;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class BookDTO implements DTO {

    @NotNull
    private Integer bookId;
    @NotNull
    private String name;
    @NotNull
    private List<AuthorDTO> authors;
    private List<Genre> genres;
    private Date written;

    @Override
    public Integer getId() {
        return bookId;
    }

    @Override
    public void setId(Integer id) {
        bookId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AuthorDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorDTO> authors) {
        this.authors = authors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public Date getWritten() {
        return written;
    }

    public void setWritten(Date written) {
        this.written = written;
    }
}
package com.my.lab.web.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.my.lab.core.dto.enumeration.Genre;
import com.my.lab.web.entity.format.adapters.WrittenDateAdapter;
import com.my.lab.web.setting.json.deserialization.PublishingDateDeserializer;
import com.my.lab.web.setting.json.deserialization.PublishingDateSerializer;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "book")
public class Book implements WebEntity {

    private Integer bookId;
    private String name;
    private List<Author> authors;
    private List<Genre> genres;
    @JsonDeserialize(using = PublishingDateDeserializer.class)
    @JsonSerialize(using = PublishingDateSerializer.class)
    private Date written;

    public Book() {
        // Default empty constructor for JSON data binding
    }

    @Override
    public Integer getId() {
        return bookId;
    }

    @Override
    @XmlAttribute(name = "id", required = true)
    public void setId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    @XmlElement(required = true)
    public void setName(String name) {
        this.name = name;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    @XmlElement(required = true)
    public void setAuthors(List<Author> authors) {
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

    @XmlJavaTypeAdapter(WrittenDateAdapter.class)
    public void setWritten(Date written) {
        this.written = written;
    }
}

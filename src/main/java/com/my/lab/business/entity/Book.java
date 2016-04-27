package com.my.lab.business.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.my.lab.business.entity.format.DateFormats;
import com.my.lab.web.setting.json.deserialization.PublishingDateDeserializer;
import com.my.lab.web.setting.json.deserialization.PublishingDateSerializer;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class Book implements Entity {

    private Integer id;
    @NotNull
    private String name;
    private Author author;
    private List<Genre> genres;
    @JsonDeserialize(using = PublishingDateDeserializer.class)
    @JsonSerialize(using = PublishingDateSerializer.class)
    private Date written;

    public Book(String name) {
        this.name = name;
    }

    public Book() {
        // Default empty constuctor for JSON data binding
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
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

    @Override
    public String toString() {
        String result =  "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + author +
                ", genres={ ";
        if (genres == null) {
            result = result + "null";
        } else {
            for (Genre genre : genres) {
                result = result + " " + genre.name() + ", ";
            }
        }
        return result.substring(0, result.length() - 2) +
                " }, written=" + DateFormats.YEAR_DATE_FORMAT.fromDate(written) + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != null ? !id.equals(book.id) : book.id != null) return false;
        if (!name.equals(book.name)) return false;
        if (author != null ? !author.equals(book.author) : book.author != null) return false;
        if (genres != null ? !genres.equals(book.genres) : book.genres != null) return false;
        return !(written != null ? !written.equals(book.written) : book.written != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        result = 31 * result + (written != null ? written.hashCode() : 0);
        return result;
    }
}

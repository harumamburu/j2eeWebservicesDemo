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

    @Override
    public String toString() {
        String result =  "Book : { " +
                "bookId=" + bookId +
                ", name='" + name + '\'' +
                ", authors=[";
        for (AuthorDTO author : authors) {
            result += " " + author.toString() + ",";
        }
        result = result.substring(0, result.length() - 1) + " ], genres=[";
        if (genres == null) {
            result += " null  ";
        } else {
            for (Genre genre : genres) {
                result += " " + genre.name() + ",";
            }
        }
        return result.substring(0, result.length() - 1) +
                " ], written=" + written.toString() + " }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookDTO book = (BookDTO) o;

        if (bookId != null ? !bookId.equals(book.bookId) : book.bookId != null) return false;
        if (!name.equals(book.name)) return false;
        if (authors != null ? !authors.equals(book.authors) : book.authors != null) return false;
        if (genres != null ? !genres.equals(book.genres) : book.genres != null) return false;
        return !(written != null ? !written.equals(book.written) : book.written != null);

    }

    @Override
    public int hashCode() {
        int result = bookId != null ? bookId.hashCode() : 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        result = 31 * result + (written != null ? written.hashCode() : 0);
        return result;
    }
}

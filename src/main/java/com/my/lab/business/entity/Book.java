package com.my.lab.business.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.my.lab.business.Constants;
import com.my.lab.business.entity.format.DateFormats;
import com.my.lab.dao.db.Queries;
import com.my.lab.web.setting.json.deserialization.PublishingDateDeserializer;
import com.my.lab.web.setting.json.deserialization.PublishingDateSerializer;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@javax.persistence.Entity
@Table(name = Constants.BOOK_TABLE_NAME)
@NamedQueries({@NamedQuery(name = Queries.BOOK_DELETE_BYID_QUERYNAME, query = Queries.BOOK_DELETE_BYID_QUERY),
        @NamedQuery(name = Queries.BOOK_CHECK_BYID_QUERYNAME, query = Queries.BOOK_CHECK_BYID_QUERY)})
public class Book implements Entity {

    @NotNull
    @Id
    @GeneratedValue(generator = "book_counter")
    @GenericGenerator(name = "book_counter", strategy = "increment")
    @Column(name = Constants.BOOK_COLUMN_ID)
    private Integer bookId;

    @NotNull
    @Column(name = Constants.BOOK_COLUMN_NAME, nullable = false)
    @Basic(optional = false)
    private String name;

    @NotNull
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = Constants.JOIN_TABLE_BOOKS_AUTHORS,
            joinColumns = @JoinColumn(name = Constants.BOOK_COLUMN_ID, nullable = false),
            inverseJoinColumns = @JoinColumn(name = Constants.AUTHOR_COLUMN_ID, nullable = false))
    private List<Author> authors;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = Constants.COLLECTION_TABLE_BOOK_GENRES,
            joinColumns = @JoinColumn(name = Constants.GENRE_COLUMN_ID))
    @Enumerated(EnumType.STRING)
    private List<Genre> genres;

    @JsonDeserialize(using = PublishingDateDeserializer.class)
    @JsonSerialize(using = PublishingDateSerializer.class)
    @Temporal(TemporalType.DATE)
    private Date written;


    public Book(String name) {
        this.name = name;
    }

    public Book() {
        // Default empty constructor for hibernate and JSON data binding
    }

    public Integer getId() {
        return bookId;
    }

    public void setId(Integer id) {
        this.bookId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void setAuthor(Author author) {
        if (!authors.contains(author)) {
            authors.add(author);
        }
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
        for (Author author : authors) {
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
                " ], written=" + DateFormats.YEAR_DATE_FORMAT.fromDate(written) + " }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

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

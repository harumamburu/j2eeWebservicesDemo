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

    @Id
    @GeneratedValue(generator = "book_counter")
    @GenericGenerator(name = "book_counter", strategy = "increment")
    @Column(name = Constants.BOOK_COLUMN_ID)
    private Integer bookId;

    @NotNull
    @Column(name = Constants.BOOK_COLUMN_NAME)
    @Basic(optional = false)
    private String name;

    //@Column(name = Constants.BOOK_COLUMN_AUTHOR)
    /*@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = Constants.JOIN_TABLE_BOOKS_AUTHORS,
            joinColumns = @JoinColumn(name = Constants.BOOK_COLUMN_ID),
            inverseJoinColumns = @JoinColumn(name = Constants.AUTHOR_COLUMN_ID))*/
    @ManyToOne
    @JoinColumn(name = Constants.BOOK_COLUMN_AUTHOR, foreignKey = @ForeignKey(name = Constants.BOOK_COLUMN_AUTHOR + "_FK"))
    private Author author;

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
                "bookId=" + bookId +
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

        if (bookId != null ? !bookId.equals(book.bookId) : book.bookId != null) return false;
        if (!name.equals(book.name)) return false;
        if (author != null ? !author.equals(book.author) : book.author != null) return false;
        if (genres != null ? !genres.equals(book.genres) : book.genres != null) return false;
        return !(written != null ? !written.equals(book.written) : book.written != null);

    }

    @Override
    public int hashCode() {
        int result = bookId != null ? bookId.hashCode() : 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        result = 31 * result + (written != null ? written.hashCode() : 0);
        return result;
    }
}

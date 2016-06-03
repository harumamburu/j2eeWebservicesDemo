package com.my.lab.dao.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.my.lab.core.dto.enumeration.Genre;
import com.my.lab.web.entity.format.DateFormats;
import com.my.lab.dao.db.Queries;
import com.my.lab.web.setting.json.deserialization.PublishingDateDeserializer;
import com.my.lab.web.setting.json.deserialization.PublishingDateSerializer;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@javax.persistence.Entity
@Table(name = Constants.BOOK_TABLE_NAME)
@NamedQueries({@NamedQuery(name = Queries.BOOK_DELETE_BYID_QUERYNAME, query = Queries.BOOK_DELETE_BYID_QUERY),
        @NamedQuery(name = Queries.BOOK_CHECK_BYID_QUERYNAME, query = Queries.BOOK_CHECK_BYID_QUERY),
        @NamedQuery(name = Queries.BOOK_CHECK_BYNATURALID_QUERYNAME, query = Queries.BOOK_CHECK_BYNATURALID_QUERY)})
public class BookJPAEntity implements Entity {

    @Id
    @GeneratedValue(generator = "book_counter", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "book_counter", sequenceName = "book_seq", allocationSize = 1)
    @Column(name = Constants.BOOK_COLUMN_ID, nullable = false)
    private Integer bookId;

    @NaturalId
    @Column(name = Constants.BOOK_COLUMN_NAME, nullable = false)
    @Basic(optional = false)
    private String name;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = Constants.JOIN_TABLE_BOOKS_AUTHORS,
            joinColumns = @JoinColumn(name = Constants.BOOK_COLUMN_ID, nullable = false),
            inverseJoinColumns = @JoinColumn(name = Constants.AUTHOR_COLUMN_ID, nullable = false))
    private List<AuthorJPAEntity> authors;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = Constants.JOIN_TABLE_BOOK_GENRES,
            joinColumns = @JoinColumn(name = Constants.BOOK_COLUMN_ID))
    @Enumerated(EnumType.STRING)
    private List<Genre> genres;

    @JsonDeserialize(using = PublishingDateDeserializer.class)
    @JsonSerialize(using = PublishingDateSerializer.class)
    @Temporal(TemporalType.DATE)
    private Date written;


    public BookJPAEntity(String name) {
        this.name = name;
    }

    public BookJPAEntity() {
        // Default empty constructor for hibernate
    }

    @Override
    public Integer getId() {
        return bookId;
    }

    @Override
    public void setId(Integer id) {
        this.bookId = id;
    }

    public String getName() {
        return name;
    }

    @Override
    @SuppressWarnings("unchecked")
    public String getNaturalId() {
        return getName();
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AuthorJPAEntity> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorJPAEntity> authors) {
        this.authors = authors;
    }

    public void setAuthor(AuthorJPAEntity author) {
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
        for (AuthorJPAEntity author : authors) {
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

        BookJPAEntity book = (BookJPAEntity) o;

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
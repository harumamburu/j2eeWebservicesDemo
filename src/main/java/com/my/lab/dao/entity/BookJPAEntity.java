package com.my.lab.dao.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.my.lab.core.dto.enumeration.Genre;
import com.my.lab.dao.db.Queries;
import com.my.lab.web.setting.json.deserialization.PublishingDateDeserializer;
import com.my.lab.web.setting.json.deserialization.PublishingDateSerializer;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.persistence.Entity
@Table(name = Constants.BOOK_TABLE_NAME)
@NamedQueries({@NamedQuery(name = Queries.BOOK_DELETE_BYID_QUERYNAME, query = Queries.BOOK_DELETE_BYID_QUERY),
        @NamedQuery(name = Queries.BOOK_CHECK_BYID_QUERYNAME, query = Queries.BOOK_CHECK_BYID_QUERY),
        @NamedQuery(name = Queries.BOOK_CHECK_BYNATURALID_QUERYNAME, query = Queries.BOOK_CHECK_BYNATURALID_QUERY)})
public class BookJPAEntity implements JPAEntity {

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
    public Map<String, String> getNaturalId() {
        Map<String, String> naturalIds = new HashMap<>(1);
        naturalIds.put("name", name);
        return naturalIds;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AuthorJPAEntity> getAuthors() {
        return authors;
    }

    @Override
    public List<AuthorJPAEntity> getNestedEntities() {
        return authors;
    }

    public void setAuthors(List<AuthorJPAEntity> authors) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookJPAEntity that = (BookJPAEntity) o;

        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

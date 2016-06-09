package com.my.lab.dao.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.my.lab.dao.db.Queries;
import com.my.lab.web.setting.json.deserialization.BirthDateDeserializer;
import com.my.lab.web.setting.json.deserialization.BirthDateSerializer;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.persistence.Entity
@Table(name = Constants.AUTHOR_TABLE_NAME)
@NamedQueries({@NamedQuery(name = Queries.AUTHOR_DELETE_BYID_QUERYNAME, query = Queries.AUTHOR_DELETE_BYID_QUERY),
        @NamedQuery(name = Queries.AUTHOR_CHECK_BYID_QUERYNAME, query = Queries.AUTHOR_CHECK_BYID_QUERY),
        @NamedQuery(name = Queries.AUTHOR_CHECK_BYNATURALID_QUERYNAME, query = Queries.AUTHOR_CHECK_BYID_QUERY)})
public class AuthorJPAEntity implements JPAEntity, Replicateable<AuthorJPAEntity> {

    @Id
    @GeneratedValue(generator = "author_counter", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "author_counter", sequenceName = "author_seq", allocationSize = 1)
    @Column(name = Constants.AUTHOR_COLUMN_ID, nullable = false)
    private Integer authorId;

    @NaturalId
    @Column(name = Constants.AUTHOR_COLUMN_NAME, nullable = false)
    private String name;

    @JsonDeserialize(using = BirthDateDeserializer.class)
    @JsonSerialize(using = BirthDateSerializer.class)
    @Temporal(TemporalType.DATE)
    private Date birth;

    public AuthorJPAEntity(String name) {
        this.name = name;
    }

    public AuthorJPAEntity() {
        // Default empty constructor for hibernate
    }

    @Override
    public void replicate(AuthorJPAEntity entity) {
        name = entity.getName();
        birth = entity.getBirth();
    }

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

    @Override
    public Map<String, String> getNaturalId() {
        Map<String, String> naturalIds = new HashMap<>(1);
        naturalIds.put("name", name);
        return naturalIds;
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
    public <K extends JPAEntity> List<K> getNestedEntities() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthorJPAEntity that = (AuthorJPAEntity) o;

        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

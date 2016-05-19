package com.my.lab.business.entity;

import com.my.lab.business.Constants;
import org.hibernate.annotations.Tuplizer;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.tuple.Instantiator;
import org.hibernate.tuple.entity.EntityMetamodel;
import org.hibernate.tuple.entity.PojoEntityTuplizer;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = Constants.GENRE_TABLE_NAME)
@Tuplizer(impl = Genre.GenreEnumTuplizer.class)
public enum Genre {

    DRAMA,
    CLASSIC,
    COMIC,
    GRAPHIC_NOVEL,
    CRIME,
    DETECTIVE,
    FABLE,
    FAIRY_TALE,
    FANFICTION,
    FANTASY,
    FICTION_NARRATIVE,
    FICTION_IN_VERSE,
    FOLKLORE,
    HISTORICAL_FICTION,
    HORROR,
    HUMOUR,
    LEGEND,
    MAGICAL_REALISM,
    METAFICTION,
    MYSTERY,
    MYTHOLOGY,
    MYTHOPOEIA,
    REALISTIC_FICTION,
    SCIENCE_FICTION,
    SHORT_STORY,
    SUSPENSE,
    THRILLER,
    TALL_TALE,
    WESTERN,
    BIOGRAPHY,
    AUTOBIOGRAPHY,
    ESSAY,
    NARRATIVE_NONFICTION,
    PERSONAL_NARRATIVE,
    MEMOIR,
    SPEECH,
    LABORATORY_REPORT,
    TEXTBOOK,
    REFERENCE_BOOK,
    SELFHELP_BOOK,
    JOURNALISM;

    @Id
    @Column(name = Constants.GENRE_COLUMN_ID, nullable = false)
    public int id = ordinal();
    @Column
    public String genre = toString();

    /**
    Used for "instantiating" Genre enum instances via its id
     */
    static class GenreEnumTuplizer extends PojoEntityTuplizer {
        public GenreEnumTuplizer(EntityMetamodel entityMetamodel, PersistentClass mappedEntity) {
            super(entityMetamodel, mappedEntity);
        }

        @Override
        protected Instantiator buildInstantiator(final PersistentClass persistentClass) {
            return new Instantiator() {
                @Override
                public Object instantiate(Serializable id) {
                    return Genre.values()[(int) id];
                }

                @Override
                public Object instantiate() {
                    throw new UnsupportedOperationException();
                }

                @Override
                public boolean isInstance(Object object) {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
}

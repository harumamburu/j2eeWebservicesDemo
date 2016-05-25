package com.my.lab.dao.db;

import com.my.lab.business.entity.Author;

import javax.ejb.Stateless;

@Stateless
public class DbAuthorDao extends AbstractDbDao<Author> {

    @Override
    protected Class<Author> getEntityClass() {
        return Author.class;
    }

    @Override
    public Boolean contains(Integer id) {
        return executeQuerySingleResult(Queries.AUTHOR_CHECK_BYID_QUERYNAME,
                getSingleParamMap(Queries.QUERY_PARAM_ID, id)) != null;
    }

    @Override
    public <String> Author getEntityByNaturalId(String naturalId) {
        return executeQuerySingleResult(Queries.BOOK_CHECK_BYNATURALID_QUERYNAME,
                getSingleParamMap(Queries.QUERY_PARAM_NAME, naturalId));
    }
}

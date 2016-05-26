package com.my.lab.dao.db;

import com.my.lab.dao.entity.Book;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class DbBookDao extends AbstractDbDao<Book> {

    @Override
    protected Class<Book> getEntityClass() {
        return Book.class;
    }

    @Override
    public Boolean contains(Integer id) {
        return executeQuerySingleResult(Queries.BOOK_CHECK_BYID_QUERYNAME,
                getSingleParamMap(Queries.QUERY_PARAM_ID, id)) != null;
    }

    @Override
    public <String> Book getEntityByNaturalId(String naturalId) {
        return executeQuerySingleResult(Queries.BOOK_CHECK_BYNATURALID_QUERYNAME,
                getSingleParamMap(Queries.QUERY_PARAM_NAME, naturalId));
    }
}

package com.my.lab.dao.db;

import com.my.lab.business.entity.Book;

public class BookDao extends AbstractDbDao<Book> {

    @Override
    protected Class<Book> getEntityClass() {
        return Book.class;
    }

    @Override
    public Book delete(Integer id) {
        return executeNamedQuerySingleResult(Queries.BOOK_DELETE_BYID_QUERYNAME,
                getSingleParamMap(Queries.QUERY_PARAM_ID, id));
    }

    @Override
    public Boolean contains(Integer id) {
        return executeNamedQuerySingleResult(Queries.BOOK_CHECK_BYID_QUERYNAME,
                getSingleParamMap(Queries.QUERY_PARAM_ID, id)) != null;
    }
}

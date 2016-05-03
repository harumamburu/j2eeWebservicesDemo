package com.my.lab.dao.db;

import com.my.lab.business.entity.Book;

import javax.ejb.Stateless;

@Stateless(name = "BookDbDaoBean")
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
}

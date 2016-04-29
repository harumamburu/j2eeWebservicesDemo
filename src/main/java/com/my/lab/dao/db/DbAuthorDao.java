package com.my.lab.dao.db;

import com.my.lab.business.entity.Author;

/*@Stateless
@LocalBean*/
public class DbAuthorDao extends AbstractDbDao<Author> {

    @Override
    protected Class<Author> getEntityClass() {
        return Author.class;
    }

    @Override
    public Author delete(Integer id) {
        return executeNamedQuerySingleResult(Queries.AUTHOR_DELETE_BYID_QUERYNAME,
                getSingleParamMap(Queries.QUERY_PARAM_ID, id));
    }

    @Override
    public Boolean contains(Integer id) {
        return executeNamedQuerySingleResult(Queries.AUTHOR_CHECK_BYID_QUERYNAME,
                getSingleParamMap(Queries.QUERY_PARAM_ID, id)) != null;
    }
}

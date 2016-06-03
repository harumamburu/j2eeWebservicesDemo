package com.my.lab.dao.db;

import com.my.lab.dao.entity.AuthorJPAEntity;

import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless
@Local
public class DbAuthorDao extends AbstractDbDao<AuthorJPAEntity> {

    @Override
    protected Class<AuthorJPAEntity> getEntityClass() {
        return AuthorJPAEntity.class;
    }

    @Override
    public Boolean contains(Integer id) {
        return executeQuerySingleResult(Queries.AUTHOR_CHECK_BYID_QUERYNAME,
                getSingleParamMap(Queries.QUERY_PARAM_ID, id)) != null;
    }

    @Override
    public <String> AuthorJPAEntity getEntityByNaturalId(String naturalId) {
        return executeQuerySingleResult(Queries.BOOK_CHECK_BYNATURALID_QUERYNAME,
                getSingleParamMap(Queries.QUERY_PARAM_NAME, naturalId));
    }
}

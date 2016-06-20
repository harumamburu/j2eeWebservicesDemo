package com.my.lab.dao.db;

import com.my.lab.dao.entity.AuthorJPAEntity;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.Collections;
import java.util.Map;

@Stateless
@LocalBean
public class DbAuthorDao extends AbstractDbDao<AuthorJPAEntity> {

    @Override
    public Class<AuthorJPAEntity> getEntityClass() {
        return AuthorJPAEntity.class;
    }

    @Override
    public Boolean contains(Integer id) {
        return executeQuerySingleResult(Queries.AUTHOR_CHECK_BYID_QUERYNAME,
                Collections.singletonMap(Queries.QUERY_PARAM_ID, id)) != null;
    }

    @Override
    public AuthorJPAEntity getEntityByNaturalId(Map<String, ?> naturalId) {
        return executeQuerySingleResult(Queries.AUTHOR_CHECK_BYNATURALID_QUERYNAME, naturalId);
    }
}

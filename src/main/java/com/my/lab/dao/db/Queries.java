package com.my.lab.dao.db;

public interface Queries {

    String QUERY_PARAM_ID = "id";
    String QUERY_PARAM_NAME = "name";

    
    String BOOK_DELETE_BYID_QUERYNAME = "Book.DeleteById";
    String BOOK_DELETE_BYID_QUERY = "delete Book b where b.bookId = :" + QUERY_PARAM_ID;
    
    String BOOK_CHECK_BYID_QUERYNAME = "Book.CheckById";
    String BOOK_CHECK_BYID_QUERY = "select 1 from Book b where b.bookId = :" + QUERY_PARAM_ID;

    String BOOK_CHECK_BYNATURALID_QUERYNAME = "Book.CheckByNaturalId";
    String BOOK_CHECK_BYNATURALID_QUERY = "select b from Book b where b.name = :" + QUERY_PARAM_NAME;

    
    String AUTHOR_DELETE_BYID_QUERYNAME = "Author.DeleteById";
    String AUTHOR_DELETE_BYID_QUERY = "delete Author a where a.authorId = :" + QUERY_PARAM_ID;
    
    String AUTHOR_CHECK_BYID_QUERYNAME = "Author.CheckById";
    String AUTHOR_CHECK_BYID_QUERY = "select 1 from  Author a where a.authorId = :" + QUERY_PARAM_ID;

    String AUTHOR_CHECK_BYNATURALID_QUERYNAME = "Author.CheckByNaturalId";
    String AUTHOR_CHECK_BYNATURALID_QUERY = "select a from  Author a where a.name = :" + QUERY_PARAM_NAME;
}

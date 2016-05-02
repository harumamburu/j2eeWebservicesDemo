package com.my.lab.dao.db;

public interface Queries {

    String QUERY_PARAM_ID = "id";

    
    String BOOK_DELETE_BYID_QUERYNAME = "Book.DeleteById";
    String BOOK_DELETE_BYID_QUERY = "delete Book b where b.bookId = :" + QUERY_PARAM_ID;
    
    String BOOK_CHECK_BYID_QUERYNAME = "Book.CheckById";
    String BOOK_CHECK_BYID_QUERY = "select 1 from Book b where b.bookId = :" + QUERY_PARAM_ID;

    
    String AUTHOR_DELETE_BYID_QUERYNAME = "Author.DeleteById";
    String AUTHOR_DELETE_BYID_QUERY = "delete Author a where a.authorId = :" + QUERY_PARAM_ID;
    
    String AUTHOR_CHECK_BYID_QUERYNAME = "Author.CheckById";
    String AUTHOR_CHECK_BYID_QUERY = "select 1 from  Author a where a.authorId = :" + QUERY_PARAM_ID;
}

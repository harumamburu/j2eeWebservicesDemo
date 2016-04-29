package com.my.lab.dao.db;

import com.my.lab.business.Constants;

public interface Queries {

    String QUERY_PARAM_ID = "id";

    
    String BOOK_DELETE_BYID_QUERYNAME = "Book.DeleteById";
    String BOOK_DELETE_BYID_QUERY = "delete " + Constants.BOOK_TABLE_NAME +
            " b where b." + Constants.BOOK_COLUMN_ID + " = :" + QUERY_PARAM_ID;
    
    String BOOK_CHECK_BYID_QUERYNAME = "Book.CheckById";
    String BOOK_CHECK_BYID_QUERY = "select 1 from " + Constants.BOOK_TABLE_NAME +
            " b where b." + Constants.BOOK_COLUMN_ID + " = :" + QUERY_PARAM_ID;

    
    String AUTHOR_DELETE_BYID_QUERYNAME = "Author.DeleteById";
    String AUTHOR_DELETE_BYID_QUERY = "delete " + Constants.AUTHOR_TABLE_NAME +
            " a where a." + Constants.AUTHOR_COLUMN_ID + " = :" + QUERY_PARAM_ID;
    
    String AUTHOR_CHECK_BYID_QUERYNAME = "Author.CheckById";
    String AUTHOR_CHECK_BYID_QUERY = "select 1 from  " + Constants.AUTHOR_TABLE_NAME +
            " a where a." + Constants.AUTHOR_COLUMN_ID + " = :" + QUERY_PARAM_ID;
}

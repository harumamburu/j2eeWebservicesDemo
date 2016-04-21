package com.my.lab.resource;

import com.my.lab.entity.Book;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/book")
public class BookResource {

    private static final Map<Integer, Book> books = new HashMap<>();

    private static final String PARAM_BOOK_ID = "bookId";

    @GET
    @Produces("text/plain"/*{MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}*/)
    public String/*Response*/ getBookById(@QueryParam(PARAM_BOOK_ID) String bookId) {
        try {
            Book book = books.get(Integer.valueOf(bookId));
            if (book == null) {
                return "No book found by id = " + bookId;
            }
            return book.toString();
        } catch (NullPointerException exc) {
            return "Oh no! Books storage weren't initialized!";
        }
    }

    @POST
    //@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces("text/plain"/*{MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}*/)
    public String saveBook(String book) {
        try {
            String[] bookDetails = book.split("=");
            Book bookObj = new Book(bookDetails[1]);
            bookObj.setId(Integer.valueOf(bookDetails[0]));
            books.put(bookObj.getId(), bookObj);
            return bookObj.toString();
        } catch (NullPointerException exc) {
            return "Oh no! Books storage weren't initialized!";
        } catch (IndexOutOfBoundsException exc) {
            return "PLease provide a book record in the id=book_name format";
        }
    }

    @PUT
    public String saveOrUpdateBook(String book) {
        return saveBook(book);
    }

    @DELETE
    public String deleteBook(@QueryParam(PARAM_BOOK_ID) String bookId) {
        try {
            Book bookObj = books.remove(Integer.valueOf(bookId));
            return bookObj == null
                    ? "No book found with id = " + bookId : bookObj.toString() + "\r\nDeleted";
        } catch (NullPointerException exc) {
            return "Oh no! Books storage weren't initialized!";
        }
    }
}

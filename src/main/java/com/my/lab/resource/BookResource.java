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
    @Produces({/*MediaType.APPLICATION_XML, */MediaType.APPLICATION_JSON})
    public Response getBookById(@QueryParam(PARAM_BOOK_ID) String bookId) {
        try {
            if (bookId.equals("null") || bookId == null) {
                throw new BadRequestException(PARAM_BOOK_ID + " can't be null!");
            }

            Book book = books.get(Integer.valueOf(bookId));
            if (book == null) {
                return Response.serverError().entity("No book found by id = " + bookId).build();
            }
            return Response.ok(book).build();
        } catch (NullPointerException exc) {
            return Response.serverError().entity("Oh no! Books storage weren't initialized!").build();
        }
    }

    @POST
    @Consumes({/*MediaType.APPLICATION_XML, */MediaType.APPLICATION_JSON})
    @Produces({/*MediaType.APPLICATION_XML, */MediaType.APPLICATION_JSON})
    public Response saveBook(Book book) {
        return Response.ok().entity(book).build();
    }

    @PUT
    public String saveOrUpdateBook(String book) {
        return null;//saveBook(book);
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

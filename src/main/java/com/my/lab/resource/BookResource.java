package com.my.lab.resource;

import com.my.lab.data.storage.BookStorage;
import com.my.lab.entity.Book;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/book")
public class BookResource {

    private static final String PARAM_BOOK_ID = "bookId";

    @GET
    @Produces({/*MediaType.APPLICATION_XML, */MediaType.APPLICATION_JSON})
    public Response getBookById(@QueryParam(PARAM_BOOK_ID) String bookId) {
        if (bookId == null || bookId.equals("null")) {
            throw new BadRequestException(PARAM_BOOK_ID + " can't be null!");
        }

        Book book = BookStorage.getBook(Integer.valueOf(bookId));
        if (book == null) {
            return Response.serverError().entity("No book found by id = " + bookId).build();
        }
        return Response.ok(book).build();
    }

    @POST
    @Consumes({/*MediaType.APPLICATION_XML, */MediaType.APPLICATION_JSON})
    @Produces({/*MediaType.APPLICATION_XML, */MediaType.APPLICATION_JSON})
    public Response saveBook(Book book) {
        return Response.ok().entity(BookStorage.addBook(book)).build();
    }

    @PUT
    @Consumes({/*MediaType.APPLICATION_XML, */MediaType.APPLICATION_JSON})
    @Produces({/*MediaType.APPLICATION_XML, */MediaType.APPLICATION_JSON})
    public Response saveOrUpdateBook(Book book) {
        return saveBook(book);
    }

    @DELETE
    @Produces({/*MediaType.APPLICATION_XML, */MediaType.APPLICATION_JSON})
    public String deleteBook(@QueryParam(PARAM_BOOK_ID) String bookId) {
        Book book = BookStorage.deleteBook(Integer.valueOf(bookId));
        return book == null
                ? "No book found with id = " + bookId : book.toString() + "\r\nDeleted";
    }
}

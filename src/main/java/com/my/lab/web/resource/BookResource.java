package com.my.lab.web.resource;

import com.my.lab.web.entity.BookWebEntity;
import com.my.lab.web.error.EntityIdMisformatException;
import com.my.lab.web.error.EntityNotFoundException;
import com.my.lab.web.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/book")
@Produces({/*MediaType.APPLICATION_XML, */MediaType.APPLICATION_JSON})
@Api(value = "book")
public class BookResource {

    private static final String PARAM_BOOK_ID = "bookId";

    @EJB
    private BookService bookService;

    @GET
    @ApiOperation(value = "get a book by id", response = BookWebEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Book id is null or misformatted"),
            @ApiResponse(code = 404, message = "No book found"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public Response getBookById(@QueryParam(PARAM_BOOK_ID) String bookId) {
        if (bookId == null) {
            throw new EntityIdMisformatException(PARAM_BOOK_ID + " can't be null!");
        }
        if (!bookId.matches("\\d+")) {
            throw new EntityIdMisformatException(PARAM_BOOK_ID + " should be an integer only!");
        }

        return Response.ok(bookService.onGet(Integer.valueOf(bookId))).build();
    }

    @POST
    @Consumes({/*MediaType.APPLICATION_XML, */MediaType.APPLICATION_JSON})
    @ApiOperation(value = "Post a book", response = BookWebEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 409, message = "A book with such id is already exist"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public Response saveNewBook(BookWebEntity book) {
        return Response.status(Response.Status.CREATED).entity(bookService.onPost(book)).build();
    }

    @PUT
    @Consumes({/*MediaType.APPLICATION_XML, */MediaType.APPLICATION_JSON})
    @ApiOperation(value = "Post or update a book", response = BookWebEntity.class, notes = "Post a book (no id required), " +
            "or check if a book exists (an id required) and either update it's entry or just post it")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal server error")})
    public Response saveOrUpdateBook(BookWebEntity book) {
        book = bookService.onPut(book);
        return Response.ok(book).build();
    }

    @DELETE
    @ApiOperation(value = "Delete a book")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "book has been deleted"),
            @ApiResponse(code = 404, message = "no book found")})
    public Response deleteBook(@QueryParam(PARAM_BOOK_ID) String bookId) {
        BookWebEntity book = bookService.onDelete(Integer.valueOf(bookId));
        if (book == null) {
            throw new EntityNotFoundException("No book found with id = " + bookId);
        }
        return Response.ok().entity(book).build();
    }
}

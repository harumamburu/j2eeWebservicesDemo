package com.my.lab.web.resource;

import com.my.lab.web.entity.BookWebEntity;
import com.my.lab.web.service.BookService;
import com.my.lab.web.service.Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/book")
@Produces({/*MediaType.APPLICATION_XML, */MediaType.APPLICATION_JSON})
@Api(value = "book")
public class BookResource {

    private static final String PARAM_BOOK_ID = "bookId";

    private final Service<BookWebEntity> bookService = new BookService();

    @GET
    @ApiOperation(value = "get a book by id", response = BookWebEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Book id is null or misformatted"),
            @ApiResponse(code = 404, message = "No book found"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public Response getBookById(@QueryParam(PARAM_BOOK_ID) String bookId) {
        if (bookId == null) {
            throw new BadRequestException(PARAM_BOOK_ID + " can't be null!");
        }
        if (!bookId.matches("\\d+")) {
            throw new BadRequestException(PARAM_BOOK_ID + " should be an integer only!");
        }

        BookWebEntity book = bookService.onGet(Integer.valueOf(bookId));
        if (book == null) {
            throw new NotFoundException("No book found by id = " + bookId);
        }
        return Response.ok(book).build();
    }

    @POST
    @Consumes({/*MediaType.APPLICATION_XML, */MediaType.APPLICATION_JSON})
    @ApiOperation(value = "Post a book", response = BookWebEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 409, message = "A book with such id is already exist"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public Response saveNewBook(BookWebEntity book) {
        Integer id;
        // TODO: add catch block for processing error messages
        book = bookService.onPost(book);
        return Response.status(Response.Status.CREATED).entity(book).build();
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
            throw new NotFoundException("No book found with id = " + bookId);
        }
        return Response.ok().entity(book).build();
    }
}

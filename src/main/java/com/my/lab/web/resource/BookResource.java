package com.my.lab.web.resource;

import com.my.lab.web.entity.BookWebEntity;
import com.my.lab.web.entity.ExceptionWebEntity;
import com.my.lab.web.error.EntityMisformattedException;
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
    @ApiOperation(value = "get a book by id")
    @ApiResponses(value = {
            @ApiResponse(response = ExceptionWebEntity.class, code = 400, message = "Book id is null or misformatted"),
            @ApiResponse(response = ExceptionWebEntity.class, code = 404, message = "No book found"),
            @ApiResponse(response = ExceptionWebEntity.class, code = 500, message = "Internal server error"),
            @ApiResponse(response = BookWebEntity.class, code = 200, message = "Book found")})
    public Response getBookById(@QueryParam(PARAM_BOOK_ID) String bookId) {
        checkIdParameter(bookId);
        return Response.ok(bookService.onGet(Integer.valueOf(bookId))).build();
    }

    @POST
    @Consumes({/*MediaType.APPLICATION_XML, */MediaType.APPLICATION_JSON})
    @ApiOperation(value = "Post a book")
    @ApiResponses(value = {
            @ApiResponse(response = ExceptionWebEntity.class, code = 409, message = "A book with such id is already exist"),
            @ApiResponse(response = ExceptionWebEntity.class, code = 500, message = "Internal Server Error"),
            @ApiResponse(response = BookWebEntity.class, code = 201, message = "Book has been posted")})
    public Response saveNewBook(BookWebEntity book) {
        return Response.status(Response.Status.CREATED).entity(bookService.onPost(book)).build();
    }

    @PUT
    @Consumes({/*MediaType.APPLICATION_XML, */MediaType.APPLICATION_JSON})
    @ApiOperation(value = "Post or update a book", notes = "Post a book (no id required), " +
            "or check if a book exists (an id required) and either update it's entry or just post it")
    @ApiResponses(value = {
            @ApiResponse(response = ExceptionWebEntity.class, code = 500, message = "Internal server error"),
            @ApiResponse(response = BookWebEntity.class, code = 201, message = "Book has been saved")})
    public Response saveOrUpdateBook(BookWebEntity book) {
        book = bookService.onPut(book);
        return Response.ok(book).build();
    }

    @DELETE
    @ApiOperation(value = "Delete a book")
    @ApiResponses(value = {
            @ApiResponse(response = ExceptionWebEntity.class, code = 404, message = "No book found"),
            @ApiResponse(response = ExceptionWebEntity.class, code = 500, message = "Internal server error"),
            @ApiResponse(response = BookWebEntity.class, code = 200, message = "Book has been deleted")})
    public Response deleteBook(@QueryParam(PARAM_BOOK_ID) String bookId) {
        checkIdParameter(bookId);
        BookWebEntity book = bookService.onDelete(Integer.valueOf(bookId));
        return Response.ok().entity(book).build();
    }

    private void checkIdParameter(String id) {
        if (id == null) {
            throw new EntityMisformattedException(PARAM_BOOK_ID + " can't be null!");
        }
        if (!id.matches("\\d+")) {
            throw new EntityMisformattedException(PARAM_BOOK_ID + " should be an integer only!");
        }
    }
}

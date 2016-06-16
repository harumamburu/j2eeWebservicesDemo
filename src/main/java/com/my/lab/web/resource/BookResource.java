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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/book")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Api(value = "book")
public class BookResource implements Resource<BookWebEntity> {

    private static final String PARAM_BOOK_ID = "bookId";

    @EJB
    private BookService bookService;

    @Override
    @GET // @QueryParam annotation overrides those from the interface
    @ApiOperation(value = "Get a book by id")
    @ApiResponses(value = {
            @ApiResponse(response = ExceptionWebEntity.class, code = 400, message = "Book id is null or misformatted"),
            @ApiResponse(response = ExceptionWebEntity.class, code = 404, message = "No book found"),
            @ApiResponse(response = ExceptionWebEntity.class, code = 500, message = "Internal server error"),
            @ApiResponse(response = BookWebEntity.class, code = 200, message = "Book found")})
    public Response getById(@QueryParam(PARAM_BOOK_ID) String bookId) {
        checkIdParameter(bookId, PARAM_BOOK_ID);
        return Response.ok(bookService.onGet(Integer.valueOf(bookId))).build();
    }

    @Override
    @ApiOperation(value = "Post a book")
    @ApiResponses(value = {
            @ApiResponse(response = ExceptionWebEntity.class, code = 409, message = "A book with such id already exist"),
            @ApiResponse(response = ExceptionWebEntity.class, code = 400, message = "Book id is not allowed"),
            @ApiResponse(response = ExceptionWebEntity.class, code = 500, message = "Internal Server Error"),
            @ApiResponse(response = BookWebEntity.class, code = 201, message = "Book has been posted")})
    public Response saveNew(BookWebEntity book) {
        return Response.status(Status.CREATED).entity(bookService.onPost(book)).build();
    }

    @Override
    @ApiOperation(value = "Post or update a book", notes = "Post a book (no id required), " +
            "or check if a book exists (an id required) and either update it's entry or just post it")
    @ApiResponses(value = {
            @ApiResponse(response = ExceptionWebEntity.class, code = 409, message = "A book with such id already exist"),
            @ApiResponse(response = ExceptionWebEntity.class, code = 500, message = "Internal server error"),
            @ApiResponse(response = BookWebEntity.class, code = 201, message = "Book has been saved")})
    public Response saveOrUpdate(BookWebEntity book) {
        return Response.ok(bookService.onPut(book)).build();
    }

    @Override
    @DELETE // @QueryParam annotation overrides those from the interface
    @ApiOperation(value = "Delete a book by id")
    @ApiResponses(value = {
            @ApiResponse(response = ExceptionWebEntity.class, code = 404, message = "No book found"),
            @ApiResponse(response = ExceptionWebEntity.class, code = 500, message = "Internal server error"),
            @ApiResponse(response = BookWebEntity.class, code = 200, message = "Book has been deleted")})
    public Response deleteByID(@QueryParam(PARAM_BOOK_ID) String bookId) {
        checkIdParameter(bookId, PARAM_BOOK_ID);
        return Response.ok(bookService.onDelete(Integer.valueOf(bookId))).build();
    }
}

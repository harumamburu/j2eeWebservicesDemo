package com.my.lab.web.resource;

import com.my.lab.dao.DAO;
import com.my.lab.dao.db.DbBookDao;
import com.my.lab.business.entity.Book;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/book")
@Api(value = "book")
@Produces({/*MediaType.APPLICATION_XML, */MediaType.APPLICATION_JSON})
public class BookResource {

    private static final String PARAM_BOOK_ID = "bookId";
    private static final DAO<Book> bookDao = new DbBookDao();

    @GET
    @ApiOperation(value = "get a book by id", response = Book.class)
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

        Book book = bookDao.getEntity(Integer.valueOf(bookId));
        if (book == null) {
            throw new NotFoundException("No book found by id = " + bookId);
        }
        return Response.ok(book).build();
    }

    @POST
    @Consumes({/*MediaType.APPLICATION_XML, */MediaType.APPLICATION_JSON})
    @ApiOperation(value = "Post a book", response = Book.class)
    @ApiResponses(value = {
            @ApiResponse(code = 409, message = "A book with such id is already exist"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public Response saveNewBook(Book book) {
        Integer id;
        if ((id = book.getId()) != null && bookDao.contains(id)) {
            throw new WebApplicationException(String.format("A book with id %d is already exist", id),
                    Response.Status.CONFLICT);
        }
        return saveBook(book);
    }

    private Response saveBook(Book book) {
        return Response.status(Response.Status.CREATED).entity(bookDao.saveEntity(book)).build();
    }

    @PUT
    @Consumes({/*MediaType.APPLICATION_XML, */MediaType.APPLICATION_JSON})
    @ApiOperation(value = "Post or update a book", response = Book.class, notes = "Post a book (no id required), " +
            "or check if a book exists (an id required) and either update it's entry or just post it")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal server error")})
    public Response saveOrUpdateBook(Book book) {
        return saveBook(book);
    }

    @DELETE
    @ApiOperation(value = "Delete a book")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "book has been deleted"),
            @ApiResponse(code = 404, message = "no book found")})
    public Response deleteBook(@QueryParam(PARAM_BOOK_ID) String bookId) {
        Book book = bookDao.deleteEntity(Integer.valueOf(bookId));
        if (book == null) {
            throw new NotFoundException("No book found with id = " + bookId);
        }
        return Response.ok().entity(book).build();
    }
}

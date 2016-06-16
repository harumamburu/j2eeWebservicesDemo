package com.my.lab.web.resource;

import com.my.lab.web.entity.AuthorWebEntity;
import com.my.lab.web.entity.BookWebEntity;
import com.my.lab.web.entity.ExceptionWebEntity;
import com.my.lab.web.service.AuthorService;
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

@Path("/authors")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Api(value = "authors")
public class AuthorResource implements Resource<AuthorWebEntity> {

    private static final String PARAM_AUTHOR_ID = "authorId";

    @EJB
    private AuthorService authorService;

    @Override
    @GET // @QueryParam annotation overrides those from the interface
    @ApiOperation(value = "Get an author by id")
    @ApiResponses(value = {
            @ApiResponse(response = ExceptionWebEntity.class, code = 400, message = "Author id is null or misformatted"),
            @ApiResponse(response = ExceptionWebEntity.class, code = 404, message = "No author found"),
            @ApiResponse(response = ExceptionWebEntity.class, code = 500, message = "Internal server error"),
            @ApiResponse(response = BookWebEntity.class, code = 200, message = "Book found")})
    public Response getById(@QueryParam(PARAM_AUTHOR_ID) String id) {
        checkIdParameter(id, PARAM_AUTHOR_ID);
        return Response.ok(authorService.onGet(Integer.valueOf(id))).build();
    }

    @Override
    @ApiOperation(value = "Post an author")
    @ApiResponses(value = {
            @ApiResponse(response = ExceptionWebEntity.class, code = 409, message = "An author with such id already exist"),
            @ApiResponse(response = ExceptionWebEntity.class, code = 400, message = "Author id is not allowed"),
            @ApiResponse(response = ExceptionWebEntity.class, code = 500, message = "Internal Server Error"),
            @ApiResponse(response = BookWebEntity.class, code = 201, message = "Author has been posted")})
    public Response saveNew(AuthorWebEntity author) {
        return Response.status(Status.CREATED).entity(authorService.onPost(author)).build();
    }

    @Override
    @ApiOperation(value = "Post or update an author", notes = "Post an author (no id required), " +
            "or check if an author exists (an id required) and either update it's entry or just post it")
    @ApiResponses(value = {
            @ApiResponse(response = ExceptionWebEntity.class, code = 409, message = "Фn author with such id already exist"),
            @ApiResponse(response = ExceptionWebEntity.class, code = 500, message = "Internal server error"),
            @ApiResponse(response = BookWebEntity.class, code = 201, message = "Author has been saved")})
    public Response saveOrUpdate(AuthorWebEntity entity) {
        return Response.ok(authorService.onPut(entity)).build();
    }

    @Override
    @DELETE // @QueryParam annotation overrides those from the interface
    @ApiOperation(value = "Delete an author by id")
    @ApiResponses(value = {
            @ApiResponse(response = ExceptionWebEntity.class, code = 404, message = "No author found"),
            @ApiResponse(response = ExceptionWebEntity.class, code = 500, message = "Internal server error"),
            @ApiResponse(response = BookWebEntity.class, code = 200, message = "Author has been deleted")})
    public Response deleteByID(@QueryParam(PARAM_AUTHOR_ID) String id) {
        checkIdParameter(id, PARAM_AUTHOR_ID);
        return Response.ok(authorService.onDelete(Integer.valueOf(id))).build();
    }
}

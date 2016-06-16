package com.my.lab.web.resource;

import com.my.lab.web.entity.WebEntity;
import com.my.lab.web.error.EntityMisformattedException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface Resource<T extends WebEntity> {

    @GET
    Response getById(String id);

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    Response saveNew(T entity);

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    Response saveOrUpdate(T entity);

    @DELETE
    Response deleteByID(String id);

    default void checkIdParameter(String id, String idRequestParameterName) {
        if (id == null) {
            throw new EntityMisformattedException(idRequestParameterName + " can't be null!");
        }
        if (!id.matches("\\d+")) {
            throw new EntityMisformattedException(idRequestParameterName + " should be an integer only!");
        }
    }
}

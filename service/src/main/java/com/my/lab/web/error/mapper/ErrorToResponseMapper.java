package com.my.lab.web.error.mapper;

import com.my.lab.web.entity.ExceptionWebEntity;
import com.my.lab.web.error.WebException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ErrorToResponseMapper implements ExceptionMapper<WebException> {

    @Override
    public Response toResponse(WebException e) {
        return Response.status(e.getStatus()).entity(new ExceptionWebEntity(e)).build();
    }
}

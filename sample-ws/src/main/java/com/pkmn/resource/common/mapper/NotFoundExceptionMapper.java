package com.pkmn.resource.common.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.pkmn.exception.NotFoundException;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    public NotFoundExceptionMapper() {

    }

    @Override
    public Response toResponse(NotFoundException arg0) {
        return Response.status(Status.NOT_FOUND).build();
    }

}

package com.pkmn.resource.common.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.pkmn.exception.ValidationException;
import com.pkmn.resource.response.ResponseError;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    public ValidationExceptionMapper() {

    }

    @Override
    public Response toResponse(ValidationException e) {
        return Response.status(Status.FORBIDDEN).entity(new ResponseError(e.getMessage())).build();
    }

}

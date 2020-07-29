package edu.sharif.surveyBackend.api.rest.upload.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.sharif.surveyBackend.api.rest.upload.error.ErrorCodes;
import edu.sharif.surveyBackend.api.rest.upload.error.Error;
import edu.sharif.surveyBackend.api.rest.upload.error.ErrorResponse;
import edu.sharif.surveyBackend.api.rest.upload.exception.ImageUploadException;
import edu.sharif.surveyBackend.api.rest.upload.exception.ResourceNotFoundException;
@Provider
public class ResourceNotFoundExceptionMapper implements ExceptionMapper<ResourceNotFoundException> {

    private Logger log = LoggerFactory.getLogger(ResourceNotFoundExceptionMapper.class);

    @Override
    public Response toResponse(ResourceNotFoundException e) {
        log.error(String.format("No resource found exception occurred: %s ", e.getMessage()));

        ErrorResponse error = new ErrorResponse();
        error.getErrors().add(
            new Error(
                ErrorCodes.ERR_RESOURCE_NOT_FOUND,
                "Resource not found",
                e.getMessage()
            )
        );

        return Response.status(Response.Status.NOT_FOUND).entity(error).build();
    }
}

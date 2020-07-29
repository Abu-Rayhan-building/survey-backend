package edu.sharif.surveyBackend.api.rest.upload.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import edu.sharif.surveyBackend.api.rest.upload.error.ErrorCodes;
import edu.sharif.surveyBackend.api.rest.upload.error.Error;
import edu.sharif.surveyBackend.api.rest.upload.error.ErrorResponse;
import edu.sharif.surveyBackend.api.rest.upload.exception.ImageUploadException;
@Provider
public class InvalidFormatExceptionMapper implements ExceptionMapper<InvalidFormatException> {

    @Override
    public Response toResponse(InvalidFormatException e) {
        ErrorResponse error = new ErrorResponse();

        error.getErrors().add(
            new Error(
                ErrorCodes.ERR_REQUEST_PARAMS_BODY_VALIDATION_FAILED,
                "Invalid request format. Please verify your request body and try again !!",
                e.getMessage()
            )
        );
        return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
    }
}

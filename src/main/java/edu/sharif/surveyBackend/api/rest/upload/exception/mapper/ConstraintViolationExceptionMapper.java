package edu.sharif.surveyBackend.api.rest.upload.exception.mapper;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.sharif.surveyBackend.api.rest.upload.error.ErrorCodes;
import edu.sharif.surveyBackend.api.rest.upload.error.Error;
import edu.sharif.surveyBackend.api.rest.upload.error.ErrorResponse;
import edu.sharif.surveyBackend.api.rest.upload.exception.ImageUploadException;	
@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException e) {
        ErrorResponse error = new ErrorResponse();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            error.getErrors().add(
                new Error(
                    ErrorCodes.ERR_CONSTRAINT_CHECK_FAILED,
                    violation.getPropertyPath().toString(),
                    violation.getMessage())
            );
        }

        return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
    }
}

package edu.sharif.surveyBackend.api.rest.survey;

import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@RequestScoped
@Path("/api/reply")
@Tag(name = "reply", description = "reply endpoint")
public class ReplyAPI {

    @Provider
    public static class ErrorMapper implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(final Exception exception) {
	    int code = 500;
	    if (exception instanceof WebApplicationException) {
		code = ((WebApplicationException) exception).getResponse()
			.getStatus();
	    }
	    return Response.status(code)
		    .entity(Json.createObjectBuilder()
			    .add("error", exception.getMessage())
			    .add("code", code).build())
		    .build();
	}

    }

}

package edu.sharif.surveyBackend.api.user;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import edu.sharif.surveyBackend.model.user.User;
import lombok.extern.slf4j.Slf4j;

@RequestScoped
@Path("/user")
@Tag(name = "user", description = "user endpoint")
@Slf4j
public class UserAPI {

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

    @POST
    @Transactional
    @Consumes("application/json")
    @Produces("application/json")
    @RolesAllowed(value = { "admin" })
    public Response create(@Valid final User question) {
	if (question.id != null) {
	    throw new WebApplicationException(
		    "Id was invalidly set on request.", 422);
	}

	question.persist();
	return Response.ok(question).status(201).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    @RolesAllowed(value = { "admin" })
    public Response delete(@PathParam(value = "id") final Long id) {
	final User entity = User.findById(id);
	if (entity == null) {
	    throw new WebApplicationException(
		    "Fruit with id of " + id + " does not exist.", 404);
	}
	entity.delete();
	return Response.status(204).build();
    }

    @Produces("application/json")
    @APIResponse(description = "The greeting message", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @Operation(description = "Will return a greeting message.", operationId = "helloendpoint_get")
    @GET
    @Path("{id}")
    public User getSingle(@PathParam(value = "id") final Long id) {
	final User entity = User.findById(id);
	if (entity == null) {
	    throw new WebApplicationException(
		    " Reply with id of " + id + " does not exist.", 404);
	}
	return entity;
    }

    @PUT
    @Path("{id}")
    @Transactional
    @RolesAllowed(value = { "admin" })
    public User update(@PathParam(value = "id") final Long id,
	    final User survey) {
	if (survey.name == null) {
	    throw new WebApplicationException(
		    "Fruit Name was not set on request.", 422);
	}

	final User entity = User.findById(id);

	if (entity == null) {
	    throw new WebApplicationException(
		    "Fruit with id of " + id + " does not exist.", 404);
	}

	entity.name = survey.name;

	return entity;
    }

}

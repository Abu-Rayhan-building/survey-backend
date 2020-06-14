package edu.sharif.surveyBackend.api.survey;

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

import edu.sharif.surveyBackend.model.survey.reply.Reply;
import lombok.extern.slf4j.Slf4j;

@RequestScoped
@Path("/reply")
@Tag(name = "reply", description = "reply endpoint")
@Slf4j
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

    @PUT
    @Path("{id}")
    @Transactional
    @RolesAllowed(value = { "admin" })
    public Reply update(@PathParam(value = "id") final Long id,
	    final Reply survey) {
	

	final Reply entity = Reply.findById(id);

	if (entity == null) {
	    throw new WebApplicationException(
		    "Fruit with id of " + id + " does not exist.", 404);
	}

//	entity.setCourse(survey.getCourse());

	return entity;
    }

    @POST
    @Transactional
    @Consumes("application/json")
    @Produces("application/json")
    @RolesAllowed(value = { "admin" })
    public Response create(@Valid final Reply question) {
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
	final Reply entity = Reply.findById(id);
	if (entity == null) {
	    throw new WebApplicationException(
		    "reply with id of " + id + " does not exist.", 404);
	}
	entity.delete();
	return Response.status(204).build();
    }

    @Produces("application/json")
    @APIResponse(description = "The greeting message", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @Operation(description = "Will return a greeting message.", operationId = "helloendpoint_get")
    @GET
    @Path("{id}")
    public Reply getSingle(@PathParam(value = "id") final Long id) {
	final Reply entity = Reply.findById(id);
	if (entity == null) {
	    throw new WebApplicationException(
		    " Reply with id of " + id + " does not exist.", 404);
	}
	return entity;
    }

}

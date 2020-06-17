package edu.sharif.surveyBackend.api.rest.survey;

import java.security.Principal;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import edu.sharif.surveyBackend.mgr.survey.SurveyMgr;
import edu.sharif.surveyBackend.mgr.survey.question.MultiChoiceQuestionMgr;
import edu.sharif.surveyBackend.mgr.survey.question.QuestionMgr;
import edu.sharif.surveyBackend.mgr.university.DepartmentMgr;
import edu.sharif.surveyBackend.mgr.university.UniversityMgr;
import edu.sharif.surveyBackend.mgr.user.UserMgr;
import edu.sharif.surveyBackend.model.survey.Survey;
import edu.sharif.surveyBackend.model.survey.SurveyResponse;
import edu.sharif.surveyBackend.model.user.User;

@RequestScoped
@Path("api/survey")
@Tag(name = "survey", description = "survey endpoint")
public class SurveyAPI {

    @Inject
    UniversityMgr universityMgr;
    @Inject
    DepartmentMgr departmentMgr;

    @Inject
    MultiChoiceQuestionMgr multiChoiceQuestionMgr;
    @Inject
    SurveyMgr surveyMgr;
    @Inject
    QuestionMgr questionMgr;
    
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
    @Path("/items")
    public Response create(@Valid final Survey question) {
	if (question.id != null) {
	    throw new WebApplicationException(
		    "Id was invalidly set on request.", 422);
	}

	question.persist();
	return Response.ok(question).status(201).build();
    }

    @DELETE
    @Path("/items/{id}")
    @Transactional
    @RolesAllowed(value = { "admin" })
    public Response delete(@PathParam(value = "id") final Long id) {
	final Survey entity = Survey.findById(id);
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
    @Path("/items/{id}")
    public Survey getSingle(@PathParam(value = "id") final Long id) {
	final Survey entity = Survey.findById(id);
	if (entity == null) {
	    throw new WebApplicationException(
		    " Reply with id of " + id + " does not exist.", 404);
	}
	return entity;
    }

    @PUT
    @Path("/items/{id}")
    @Transactional
    @RolesAllowed(value = { "admin" })
    public Survey update(@PathParam(value = "id") final Long id,
	    final Survey survey) {
	if (survey.getCourse() == null) {
	    throw new WebApplicationException(
		    "Fruit Name was not set on request.", 422);
	}

	final Survey entity = Survey.findById(id);

	if (entity == null) {
	    throw new WebApplicationException(
		    "Fruit with id of " + id + " does not exist.", 404);
	}

	entity.setCourse(survey.getCourse());

	return entity;
    }

    @Produces("application/json")
    @GET
    @Path("/available")
    public Survey[] availableSurvies(@Context final SecurityContext sec) {
	final Principal user = sec.getUserPrincipal();
	User u = UserMgr.findByUsername(user.getName());
	return surveyMgr.availableSurveies(u);
    }

    @Produces("application/json")
    @GET
    @Path("/old")
    public Survey[] oldSurvies(@Context final SecurityContext sec) {
	final Principal user = sec.getUserPrincipal();
	User u = UserMgr.findByUsername(user.getName());
	return surveyMgr.oldSurveies(u);
    }

    @POST
    @Transactional
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/items/{id}/submit")
    public Response sunbmit(@Valid final SurveyResponse surveyResponse, @Context final SecurityContext sec) {
	if (surveyResponse.id != null) {
	    throw new WebApplicationException(
		    "Id was invalidly set on request.", 422);
	}

	final Principal user = sec.getUserPrincipal();
	User u = UserMgr.findByUsername(user.getName());
	surveyMgr.submit(u, surveyResponse);
	
	return Response.ok(surveyResponse).status(201).build();
    }

}

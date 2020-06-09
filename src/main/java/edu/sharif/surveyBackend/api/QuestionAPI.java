package edu.sharif.surveyBackend.api;

import java.security.Principal;
import java.util.Set;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.Logger;

import edu.sharif.surveyBackend.model.Student;

@Path("/q")
@Slf4j
public class QuestionAPI {

    @Inject
    Validator validator;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {

        return "hello";
    }

    @Path("/manual-validation")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(Student name) {
        Set<ConstraintViolation<Student>> violations = validator.validate(name);
        if (violations.isEmpty()) {
            return null;
        } else {
            return null;
        }
    }

    @GET
    @Path("secured")
    @RolesAllowed("user")
    public String getSubjectSecured(@Context SecurityContext sec) {
        if (false)
            throw new ForbiddenException();
        Principal user = sec.getUserPrincipal();
        String name = user != null ? user.getName() : "anonymous";
        return name;
    }

    @GET
    @Path("unsecured")
    @PermitAll
    public String getSubjectUnsecured(@Context SecurityContext sec) {
        Principal user = sec.getUserPrincipal();
        String name = user != null ? user.getName() : "anonymous";
        return name;
    }

}

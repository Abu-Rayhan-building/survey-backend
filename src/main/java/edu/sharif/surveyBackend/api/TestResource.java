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
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.sharif.surveyBackend.model.user.User;
import io.vertx.ext.web.RoutingContext;

class CookieBuilder {

}

@Path("test")
@Tag(name = "test", description = "test endpoint")
@PermitAll
public class TestResource {

    private static final Logger log = LoggerFactory.getLogger("AuthResource");
    @Inject
    Validator validator;
    @Inject
    JsonWebToken jwt;

    public Response authorize2(@Context final RoutingContext routingContext) {
	final NewCookie cookie = new NewCookie("cookie name", "cookie value",
		"path", "domain", 1, "cooment", 1000_000, null, false, true);
	final Response.ResponseBuilder rb = Response.ok("myStrCookie, "
		+ "myDateCookie and myIntCookie sent to the browser");
	final Response response = rb.cookie(cookie).build();
	return response;
    }

    @GET
    @Path("secured")
    @RolesAllowed("user")
    public String getSubjectSecured(@Context final SecurityContext sec) {
	if (false) {
	    throw new ForbiddenException();
	}
	final Principal user = sec.getUserPrincipal();
	final String name = user != null ? user.getName() : "anonymous";
	return name;
    }

    @GET
    @Path("unsecured")
    @PermitAll
    public String getSubjectUnsecured(@Context final SecurityContext sec) {
	final Principal user = sec.getUserPrincipal();
	final String name = user != null ? user.getName() : "anonymous";
	return name;
    }

    @GET()
    @Path("permit-all")
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@Context final SecurityContext ctx) {
	final Principal caller = ctx.getUserPrincipal();
	final String name = caller == null ? "anonymous" : caller.getName();
	final boolean hasJWT = this.jwt.getClaimNames() != null;
	final String helloReply = String.format(
		"hello + %s, isSecure: %s, authScheme: %s, hasJWT: %s", name,
		ctx.isSecure(), ctx.getAuthenticationScheme(),
		hasJWT + " " + this.jwt);
	return helloReply;
    }

    @Path("/manual-validation")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(final User name) {
	final Set<ConstraintViolation<User>> violations = this.validator
		.validate(name);
	if (violations.isEmpty()) {
	    return null;
	} else {
	    return null;
	}
    }

}
package edu.sharif.surveyBackend.utill;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

@Provider
public class LoggingFilter
        implements ContainerRequestFilter {

    @Context
    UriInfo info;

    @Context
    HttpServletRequest request;

    @Override
    public void filter(ContainerRequestContext context) {
//        final String method = context.getMethod();
//        final String path = info.getPath();
//        final String address = request.getRemoteAddr();
//        System.out.println("Request %s %s from IP %s",
//                method, path, address);
    }

}
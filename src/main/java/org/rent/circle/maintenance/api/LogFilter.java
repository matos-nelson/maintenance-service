package org.rent.circle.maintenance.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Provider
@Slf4j
class LogFilter implements ContainerRequestFilter {

    @Inject
    JsonWebToken jwt;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        log.info("User: {}", jwt.getName());
        log.info("METHOD: {}", requestContext.getRequest().getMethod());
        log.info("URI: {}", requestContext.getUriInfo().getPath(true));
        log.info("QUERY PARAMS: {}", requestContext.getUriInfo().getQueryParameters());
    }
}

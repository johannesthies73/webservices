package de.hszg.webservices.ts05.auth;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Priority(Priorities.AUTHENTICATION)
//@JwtTokenRequired
@Slf4j
public class JwtTokenRequiredRequestFilter implements ContainerRequestFilter {
    protected static final String BEARER_HEADER_NAME = "Bearer";
    @Inject
    private JwtKeyPairStore keyStore;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
//get the HTTP Authorization header from the request
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith(BEARER_HEADER_NAME + " ")) {
            log.warn("Endpoint which requires JWT token was requested without valid token.");
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        } else {
//extract the token from the HTTP Authorization header
            String token = authorizationHeader.substring(BEARER_HEADER_NAME.length()).trim();
//check integrity of token
            try {
                String email = Jwts.parser()
                        .setSigningKey(this.keyStore.getPublicKey())
                        .parseClaimsJws(token)
                        .getBody()
                        .getSubject();
                log.info("User with email {} requested resource.", email);
            } catch (JwtException e) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
                log.error("JWT validation error while requesting resource.", e);
            }
        }
    }
}
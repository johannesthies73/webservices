package de.hszg.webservices.ts05.auth;

import de.hszg.webservices.ts05.user.Email;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.*;
import java.util.Optional;

@Path("/auth")
public class AuthenticationRestController {

    @Inject
    private AuthenticationService authenticationService;

    @Context
    private UriInfo uriInfo;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response login(
            @NotNull @FormParam("email") String email,
            @NotNull @FormParam("password") String password
    ) {
        Optional<String> authToken = this.authenticationService.loginAndGetToken(
                Email.of(email),
                password,
                this.uriInfo.getAbsolutePath().toString()
        );
        if (authToken.isPresent()) {
            return Response
                    .ok()
                    .header(HttpHeaders.AUTHORIZATION,
                            JwtTokenRequiredRequestFilter.BEARER_HEADER_NAME
                                    + " "
                                    + authToken.get()).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
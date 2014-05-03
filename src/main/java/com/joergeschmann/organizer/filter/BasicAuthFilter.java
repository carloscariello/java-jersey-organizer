package com.joergeschmann.organizer.filter;

import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response.Status;

import com.joergeschmann.organizer.authentification.AuthUtil;
import com.joergeschmann.organizer.controller.AuthController;

/**
 * Filter to activate the authentication for every request
 * 
 */
public class BasicAuthFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {

		final String headerAuth = requestContext
				.getHeaderString(FilterConstants.AUTH_HEADER);

		final String[] splittedAuth = AuthUtil
				.parseClientAuthHeader(headerAuth);

		if (splittedAuth == null || splittedAuth.length != 2) {
			throw new WebApplicationException(Status.UNAUTHORIZED);
		}

		boolean isAuthenticated = AuthController.getInstance()
				.compareClientAndServerAuthHash(splittedAuth[0],
						splittedAuth[1]);

		if (!isAuthenticated) {
			throw new WebApplicationException(Status.UNAUTHORIZED);
		}

		requestContext.setProperty(FilterConstants.USER_ID_CONTEXT,
				splittedAuth[0]);
	}
}

package com.joergeschmann.organizer.helper;

import javax.ws.rs.container.ContainerRequestContext;

import com.joergeschmann.organizer.filter.FilterConstants;

/**
 * Helper class for Requests
 * 
 */
public class RequestHelper {

	/**
	 * Gets the user id from the context
	 * 
	 * @param requestContext
	 * @return the user id or 0
	 */
	public static long getUserIdFromContext(
			final ContainerRequestContext requestContext) {
		final Object ownerId = requestContext
				.getProperty(FilterConstants.USER_ID_CONTEXT);
		if (ownerId == null) {
			return 0;
		}
		return Long.valueOf(ownerId.toString());
	}

}

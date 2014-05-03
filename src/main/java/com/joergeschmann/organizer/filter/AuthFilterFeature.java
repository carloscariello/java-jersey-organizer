package com.joergeschmann.organizer.filter;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

import com.joergeschmann.organizer.api.Authentification;

/**
 * Feature to activate filters for specific requests
 * 
 */
@Provider
public class AuthFilterFeature implements DynamicFeature {

	@Override
	public void configure(ResourceInfo resourceInfo, FeatureContext context) {

		if (resourceInfo.getResourceClass().getName()
				.startsWith(FilterConstants.AUTH_FILTER_RESOURCE_PREFIX)
				&& !Authentification.class.equals(resourceInfo
						.getResourceClass())) {
			context.register(BasicAuthFilter.class);
		}

	}

}

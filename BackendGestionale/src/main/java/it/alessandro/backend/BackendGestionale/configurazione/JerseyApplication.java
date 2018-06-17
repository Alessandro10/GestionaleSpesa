package it.alessandro.backend.BackendGestionale.configurazione;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("services")
public class JerseyApplication extends ResourceConfig {
	public JerseyApplication() {
		packages("it.alessandro.backend.BackendGestionale.services");

	}
}
package it.alessandro.backend.BackendGestionale.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello2")
public class RestfulApplicationPathAnnotationExample {
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response sayHello() {

		String str = "<H1>Hi!!! welcome to @ApplicationPath Annotation Example in Restful webservices  </H1> ";
		;

		return Response.status(200).entity(str).build();

	}

}

package org.stocksrin.app;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/demo")
public class Demo {

	@GET
	@Path("/test")
	public String test() {
		System.out.println("hi");
		return "Hi";

	}
}

package org.stocksrin.app;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/json/product")
public class JSONService {

	////localhost:8080/restDemo/json/product/test
	@GET
	@Path("/test")
	public String test() {
		System.out.println(" JSONService hi");
		return " JSONService Hi";

	}
	
}
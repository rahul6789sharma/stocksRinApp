package org.stocksrin.oi.future;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/oiService")
public class OIRestService {
	
		//http://localhost:8080/rest/oiService/oiData
		@GET
		@Path("/oiData")
		@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
		public  Map<String, NiftyOIDataModle>getExpiryList() {
			return NiftyOIDataModleMap.getOiDataMap();
		}

}

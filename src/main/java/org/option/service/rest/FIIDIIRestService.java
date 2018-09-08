package org.option.service.rest;

import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.stocksrin.fiidii.FIIDIIDataModle;
import org.stocksrin.fiidii.FIIDIIDataYrModle;
import org.stocksrin.fiidii.FIIDIIdataModelMap;
import org.stocksrin.nifty.indices.NSEIndice;
import org.stocksrin.oi.allparticapent.ParticapentOIData;
import org.stocksrin.oi.allparticapent.ParticipantOIModle;

@Path("/fiiDiiService")
public class FIIDIIRestService {

	// http://localhost:8080/rest/fiiDiiService/fiiData
	@GET
	@Path("/fiiData")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Map<String, FIIDIIDataModle> getfiiData() {
		return FIIDIIdataModelMap.getfIIDIIDataModleData();
	}

	// http://localhost:8080/rest/fiiDiiService/fiiData
	@GET
	@Path("/fiiDataPreviousMonth")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Map<String, FIIDIIDataModle> getFiiDataPreviousMonth() {
		return FIIDIIdataModelMap.getfIIDIIPreviousMOnthData();
	}

	// http://localhost:8080/rest/fiiDiiService/fiiDataYear
	@GET
	@Path("/fiiDataYear")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Map<String, FIIDIIDataYrModle> getFiiDataYearly() {
		return FIIDIIdataModelMap.getfIIDIIYearlyData();
	}

	// http://localhost:8080/rest/fiiDiiService/participantFNOData

	@GET
	@Path("/participantFNOData")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Map<String, List<ParticipantOIModle>> getParticipantFutureData() {
		return ParticapentOIData.data;

	}

	 //http://localhost:8080/rest/fiiDiiService/niftyIndices
	@GET
	@Path("/niftyIndices")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Map<String, NSEIndice> getNiftyIndicesData() {
		return org.stocksrin.nifty.indices.Data.niftyData;

	}

	@GET
	@Path("/bankNiftyIndices")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Map<String, NSEIndice> getBankNiftyIndicesData() {
		return org.stocksrin.nifty.indices.Data.bankNIftyData;

	}

}

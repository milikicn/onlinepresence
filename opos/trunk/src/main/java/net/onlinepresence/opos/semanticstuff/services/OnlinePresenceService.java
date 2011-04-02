package net.onlinepresence.opos.semanticstuff.services;

import java.util.Collection;

import net.onlinepresence.ontmodel.opo.OnlinePresence;
import net.onlinepresence.opos.semanticstuff.rdfpersistance.query.ontmodel.OntModelQueryService;
import net.onlinepresence.opos.semanticstuff.rdfpersistance.query.ontmodel.OntModelQueryServiceImpl;
import net.onlinepresence.opos.semanticstuff.util.Constants;

public class OnlinePresenceService extends AbstractServiceImpl {
	
	private OntModelQueryService queryService = new OntModelQueryServiceImpl();

	public OnlinePresence getLastOnlinePresence(String userUri) throws Exception {
		String queryString = 
			"PREFIX rdf: <"+Constants.RDF_NS+"> \n" + 
			"PREFIX opo: <"+Constants.OPO_NS+"> \n" + 
			"SELECT ?onlinePresence \n" + 
			"WHERE  {\n" + 
				"<"+userUri+"> opo:declaresOnlinePresence ?onlinePresence . \n" +
				"?onlinePresence rdf:type opo:OnlinePresence ; \n" +
						"opo:startTime ?startTime . \n" +
			"} \n" +
			"ORDER BY ?startTime DESC \n" +
			"LIMIT 1";
		
		System.out.println(queryString);

		Collection<String> onlinePresenceUris = queryService
				.executeOneVariableSelectSparqlQuery(queryString, "onlinePresence",
						getDataModel());

		if (onlinePresenceUris != null && !onlinePresenceUris.isEmpty()){
			return loadResourceByURI(OnlinePresence.class, onlinePresenceUris.iterator().next(), false);
		}
		return null;
	}
}

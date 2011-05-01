package net.onlinepresence.opos.semanticstuff.services;

import java.util.Collection;

import net.onlinepresence.ontmodel.opo.OnlinePresence;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.semanticstuff.rdfpersistance.query.ontmodel.OntModelQueryService;
import net.onlinepresence.opos.semanticstuff.rdfpersistance.query.ontmodel.OntModelQueryServiceImpl;
import net.onlinepresence.opos.semanticstuff.util.Constants;

public class OnlinePresenceService extends AbstractServiceImpl {
	
	private OntModelQueryService queryService = new OntModelQueryServiceImpl();

	public OnlinePresence getLastOnlinePresence(Membership memb) throws Exception {
		String username = memb.getUsername();
		String appWebAddress = memb.getApplication().getWebAddress();
		
		String queryString1 = 
			"PREFIX rdf: <"+Constants.RDF_NS+"> \n" + 
			"PREFIX opo: <"+Constants.OPO_NS+"> \n" + 
			"PREFIX sioc: <"+Constants.SIOC_NS+"> \n" + 
			"PREFIX foaf: <"+Constants.FOAF_NS+"> \n" + 
			"SELECT ?onlinePresence \n" + 
			"WHERE  {\n" +
				"?onlinePresence rdf:type opo:OnlinePresence . \n" + 
			"}";
		
		System.out.println(queryString1);

		Collection<String> onlinePresenceUris1 = queryService
				.executeOneVariableSelectSparqlQuery(queryString1, "onlinePresence",
						getDataModel());
		System.out.println("onlinePresenceUris0: "+onlinePresenceUris1);
		
		String queryString = 
			"PREFIX rdf: <"+Constants.RDF_NS+"> \n" + 
			"PREFIX opo: <"+Constants.OPO_NS+"> \n" + 
			"PREFIX sioc: <"+Constants.SIOC_NS+"> \n" + 
			"PREFIX foaf: <"+Constants.FOAF_NS+"> \n" + 
			"SELECT ?onlinePresence \n" + 
			"WHERE  {\n" +
				"?onlinePresence rdf:type opo:OnlinePresence ; \n" +
						"opo:startTime ?startTime ; \n" +
						"opo:declaredOn ?userAccount. " +
				"?userAccount rdf:type sioc:UserAccount; \n" +
						"foaf:accountServiceHomepage <"+appWebAddress+"> ; \n" +
						"foaf:accountName \""+username+"\" . \n" + 
			"} \n" +
			"ORDER BY DESC(?startTime)  \n" +
			"LIMIT 1";
		
		System.out.println(queryString);

		Collection<String> onlinePresenceUris = queryService
				.executeOneVariableSelectSparqlQuery(queryString, "onlinePresence",
						getDataModel());
		System.out.println("onlinePresenceUris: "+onlinePresenceUris);

		if (onlinePresenceUris != null && !onlinePresenceUris.isEmpty()){
			return loadResourceByURI(OnlinePresence.class, onlinePresenceUris.iterator().next(), false);
		}
		return null;
	}
	
	public OnlinePresence getLastOnlinePresence(String userUri) throws Exception {
		String queryString = 
			"PREFIX rdf: <"+Constants.RDF_NS+"> \n" + 
			"PREFIX opo: <"+Constants.OPO_NS+"> \n" + 
			"SELECT ?onlinePresence \n" + 
			"WHERE  {\n" + 
				"?onlinePresence rdf:type opo:OnlinePresence ; \n" +
						"opo:startTime ?startTime . \n" +
				"{<"+userUri+"> opo:declaresOnlinePresence ?onlinePresence . } \n" +
				"UNION \n" +
				"{?onlinePresence opo:declaredBy <"+userUri+"> . } \n" +
			"} \n" +
			"ORDER BY DESC(?startTime)  \n" +
			"LIMIT 1";
		
		Collection<String> onlinePresenceUris = queryService
				.executeOneVariableSelectSparqlQuery(queryString, "onlinePresence",
						getDataModel());

		if (onlinePresenceUris != null && !onlinePresenceUris.isEmpty()){
			return loadResourceByURI(OnlinePresence.class, onlinePresenceUris.iterator().next(), false);
		}
		return null;
	}
}

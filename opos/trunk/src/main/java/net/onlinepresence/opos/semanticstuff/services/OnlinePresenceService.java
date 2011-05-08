package net.onlinepresence.opos.semanticstuff.services;

import java.net.URI;
import java.util.Collection;

import org.apache.log4j.Logger;

import net.onlinepresence.jopo.ontmodel.foaf.Person;
import net.onlinepresence.jopo.ontmodel.opo.OnlinePresence;
import net.onlinepresence.jopo.ontmodel.sioc.UserAccount;
import net.onlinepresence.jopo.services.spring.ResourceFactory;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.domain.User;
import net.onlinepresence.opos.semanticstuff.rdfpersistance.query.ontmodel.OntModelQueryService;
import net.onlinepresence.opos.semanticstuff.rdfpersistance.query.ontmodel.OntModelQueryServiceImpl;
import net.onlinepresence.opos.semanticstuff.util.Constants;

public class OnlinePresenceService extends AbstractServiceImpl {
	
	private Logger logger = Logger.getLogger(OnlinePresenceService.class);
	
	private ResourceFactory resourceFactory = new ResourceFactory();
	private OntModelQueryService queryService = new OntModelQueryServiceImpl();

	public OnlinePresence getLastOnlinePresence(Membership memb) throws Exception {
		String username = memb.getUsername();
		String appWebAddress = memb.getApplication().getWebAddress();
		
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
						"foaf:accountName ?accountName . \n" + 
				"FILTER ( ?accountName = \""+username+"\") \n" +
			"} \n" +
			"ORDER BY DESC(?startTime)  \n" +
			"LIMIT 1";
		
//		System.out.println(queryString);

		Collection<String> onlinePresenceUris = queryService
				.executeOneVariableSelectSparqlQuery(queryString, "onlinePresence",
						getDataModel());
//		System.out.println("onlinePresenceUris: "+onlinePresenceUris);

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
				"UNION \n" +
				"{}" +
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
	
	public Person getPersonInstance(User user) {
		String email = user.getEmail();
		
		if (email == null) {
			logger.error("User doesn't have email set");
			return null;
		}
		
		String queryString = 
			"PREFIX rdf: <"+Constants.RDF_NS+"> \n" + 
			"PREFIX foaf: <"+Constants.FOAF_NS+"> \n" + 
			"SELECT ?person \n" + 
			"WHERE  {\n" + 
				"?person rdf:type foaf:Person ; \n" +
						"foaf:mbox <mailto:"+email+"> . \n" +
			"}";
		
		Collection<String> personUris = queryService
				.executeOneVariableSelectSparqlQuery(queryString, "person",
						getDataModel());

		if (personUris != null && !personUris.isEmpty()){
			try {
				return loadResourceByURI(Person.class, personUris.iterator().next(), false);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return null;
	}

	public UserAccount getUserAccount(Membership userMembership) {
		String username = userMembership.getUsername();
		String applicationWebAddress = userMembership.getApplication().getWebAddress();
		
		if (username == null || applicationWebAddress == null) {
			logger.error("Membership username and application web address can not be null.");
			return null;
		}
		
		String queryString = 
			"PREFIX rdf: <"+Constants.RDF_NS+"> \n" + 
			"PREFIX sioc: <"+Constants.SIOC_NS+"> \n" + 
			"PREFIX foaf: <"+Constants.FOAF_NS+"> \n" + 
			"SELECT ?userAccount \n" + 
			"WHERE  {\n" + 
				"?userAccount rdf:type sioc:UserAccount ; \n" +
						"foaf:accountName ?accountName ; \n" +
						"foaf:accountServiceHomepage <"+applicationWebAddress+"> . \n" +
				"FILTER ( ?accountName = \""+username+"\") \n" +
			"}";
		
		Collection<String> userAccountUris = queryService
				.executeOneVariableSelectSparqlQuery(queryString, "userAccount",
						getDataModel());

		if (userAccountUris != null && !userAccountUris.isEmpty()){
			try {
				return loadResourceByURI(UserAccount.class, userAccountUris.iterator().next(), false);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return null;
	}

	public void registerUserAndOposAccount(User user) {
//		UserAccount oposUserAccount = null;
//		try {
//			oposUserAccount = resourceFactory.createResource(UserAccount.class);
//			oposUserAccount.setAccountName(user.getUsername());
//			oposUserAccount.setAccountServiceHomepage(Settings.getInstance().config.oposWebsite);
//			oposUserAccount = saveResource(oposUserAccount, false);
//		} catch (Exception e) {
//			logger.error(e.getMessage());
//		}
		
		try {
			Person person = resourceFactory.createResource(Person.class);
			person.setName(user.getName());
			person.setMbox(URI.create("mailto:"+user.getEmail()));
//			person.addAccount(oposUserAccount);
			person = saveResource(person, false);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
	}
	
	public Person getPerson(User user) {
		String queryString = 
			"PREFIX rdf: <"+Constants.RDF_NS+"> \n" + 
			"PREFIX foaf: <"+Constants.FOAF_NS+"> \n" + 
			"SELECT ?person \n" + 
			"WHERE  { \n" + 
				"?person rdf:type foaf:Person ; \n" +
						"foaf:mbox <mailto:"+user.getEmail()+"> . \n" +
			"}";
		
		Collection<String> personUris = queryService
				.executeOneVariableSelectSparqlQuery(queryString, "person",
						getDataModel());

		if (personUris != null && !personUris.isEmpty()){
			try {
				return loadResourceByURI(Person.class, personUris.iterator().next(), false);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return null;
	}
}

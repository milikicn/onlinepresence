package org.goodoldai.demo.twitt2opo.converter.exporter;

import org.goodoldai.demo.twitt2opo.converter.model.TwitterUser;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

public class Exporter {

	private String baseUrl;
	private Model model;
	private TwitterUser user;
	private String time;
	private final static String FOAF_NS = "http://xmlns.com/foaf/0.1/";
	private final static String WSG84_NS = "http://www.w3.org/2003/01/geo/wgs84_pos#";
	private final static String OPO_NS = "http://online-presence.net/opo/ns#";
	private final static String RDF_NS = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
	private final static String SIOC_NS = "http://rdfs.org/sioc/ns#";

	public Exporter(TwitterUser user, String baseUrl, String time) {
		this.model = ModelFactory.createDefaultModel();
		model.setNsPrefix("foaf", FOAF_NS);
		model.setNsPrefix("wgs84_pos", WSG84_NS);
		model.setNsPrefix("opo", OPO_NS);
		model.setNsPrefix("rdf", RDF_NS);
		model.setNsPrefix("sioc", SIOC_NS);
		this.user = user;
		this.baseUrl = baseUrl;
		this.time = time;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Model export2OPO(TwitterUser user) throws Exception {
		addTriples();

		return model;
	}

	private void addTriples() throws Exception{
		model.createResource(baseUrl + user.getNickname()).addProperty(
				model.createProperty(OPO_NS + "declaresOnlinePresence"),
				getOnlinePresenceRes());
	}

	private Resource getOnlinePresenceRes() {
		Resource onlinePresenceRes = model.createResource(baseUrl
				+ "OnlinePresence");

		onlinePresenceRes.addProperty(RDF.type, model.createResource(OPO_NS + "OnlinePresence"));
		onlinePresenceRes.addProperty(model.createProperty(OPO_NS + "avatar"),user.getImgUrl().toString());
		onlinePresenceRes.addProperty(model.createProperty(OPO_NS + "startTime"), time);

		onlinePresenceRes.addProperty(model.createProperty(OPO_NS + "declaredBy"), getAgent());
		onlinePresenceRes.addProperty(model.createProperty(OPO_NS + "customMessage"), getSiocPost());
		onlinePresenceRes.addProperty(model.createProperty(OPO_NS + "declaredOn"), getUser());
		if(user.getLocationUrl() != null){
			onlinePresenceRes.addProperty(model.createProperty(OPO_NS + "currentLocation"), model.createResource(user.getLocationUrl().toString()));
		}

		return onlinePresenceRes;
	}

	private Resource getAgent() {
		Resource agentRes = model.createResource(baseUrl + "FoafAgent");

		agentRes.addProperty(RDF.type, model.createResource(FOAF_NS + "Agent"));
		if(user.getName() != null)
			agentRes.addProperty(model.createProperty(FOAF_NS + "name"), user.getName());
		agentRes.addProperty(model.createProperty(FOAF_NS + "nick"), user.getNickname());
		if(user.getHomepage() != null)
			agentRes.addProperty(model.createProperty(FOAF_NS + "homepage"), model.createResource(user.getHomepage().toString()));

		return agentRes;
	}

	private Resource getSiocPost() {
		Resource statusMessageRes = model.createResource(baseUrl
				+ "SiocPost");

		statusMessageRes.addProperty(RDF.type, model.createResource(SIOC_NS + "Post"));
		statusMessageRes.addProperty(model.createProperty(SIOC_NS + "content"), user.getCurrentStatus().getText());
		statusMessageRes.addProperty(model.createProperty(FOAF_NS + "primaryTopicOf"), model.createResource(user.getCurrentStatus().getStatusUrl().toString()));

		if(user.getCurrentStatus().getReplyOfStatusUrl() != null){
			statusMessageRes.addProperty(model.createProperty(SIOC_NS + "reply_of"),	getReplyStatus());
		}
		
		return statusMessageRes;
	}

	private Resource getReplyStatus() {
		Resource replyPostRes = model.createResource(baseUrl + "ReplyPost");
		
		replyPostRes.addProperty(RDF.type, model.createResource(SIOC_NS + "Post"));
		replyPostRes.addProperty(model.createProperty(FOAF_NS + "primaryTopicOf"), model.createResource(user.getCurrentStatus().getReplyOfStatusUrl().toString()));
			
		return replyPostRes;
	}

	private Resource getUser() {
		Resource userRes = model.createResource(baseUrl + "SiocUser");

		userRes.addProperty(RDF.type, model.createResource(SIOC_NS + "User"));
		userRes.addProperty(model.createProperty(FOAF_NS + "accountServiceHomepage"), model.createResource("http://www.twitter.com/"));
		userRes.addProperty(model.createProperty(FOAF_NS + "accountName"), user.getNickname());

		return userRes;
	}
}

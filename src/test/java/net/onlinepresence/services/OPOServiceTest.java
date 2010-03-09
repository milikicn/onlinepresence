package net.onlinepresence.services;

import net.onlinepresence.domainmodel.doap.Project;
import net.onlinepresence.domainmodel.doap.interfaces.ProjectBean;
import net.onlinepresence.domainmodel.foaf.interfaces.AgentBean;
import net.onlinepresence.domainmodel.foaf.pojos.Agent;
import net.onlinepresence.domainmodel.foaf.pojos.Document;
import net.onlinepresence.domainmodel.opo.interfaces.OnlinePresenceBean;
import net.onlinepresence.domainmodel.opo.interfaces.presencecomponents.OnlineStatusBean;
import net.onlinepresence.domainmodel.opo.pojos.OnlinePresence;
import net.onlinepresence.domainmodel.opo.pojos.presencecomponents.Findability;
import net.onlinepresence.domainmodel.opo.pojos.presencecomponents.Notifiability;
import net.onlinepresence.domainmodel.opo.pojos.presencecomponents.OnlineStatus;
import net.onlinepresence.domainmodel.opo.pojos.statuscomponents.Activity;
import net.onlinepresence.domainmodel.opo.pojos.statuscomponents.Contactability;
import net.onlinepresence.domainmodel.opo.pojos.statuscomponents.Disturbability;
import net.onlinepresence.domainmodel.opo.pojos.statuscomponents.Visibility;
import net.onlinepresence.domainmodel.opoactions.Chatting;
import net.onlinepresence.domainmodel.opoactions.WorkingOnProject;
import net.onlinepresence.domainmodel.sioc.interfaces.PostBean;
import net.onlinepresence.domainmodel.sioc.pojos.Post;

public class OPOServiceTest {

	public static void testExoport(){
		
		OnlinePresenceBean onlinePresence = new OnlinePresence();
		
		AgentBean agentFilip = new Agent();
		agentFilip.setName("Filip Radulovic");
		agentFilip.setHomepage("http://filip.milistan.net");

		onlinePresence.setAgent(agentFilip);
		
		OnlineStatusBean onlineStatus = new OnlineStatus();
		onlineStatus.addStatusComponent(Activity.ACTIVE);
		onlineStatus.addStatusComponent(Contactability.FREELY_CONTACTABLE);
		onlineStatus.addStatusComponent(Disturbability.AVAILABLE);
		onlineStatus.addStatusComponent(Visibility.VISIBLE);
		
		onlinePresence.addPresenceComponent(Findability.PUBLICLY_FINDABLE);
		onlinePresence.addPresenceComponent(Notifiability.ALL_NOTIFICATIONS_PASS);
		onlinePresence.addPresenceComponent(onlineStatus);
		
//		ItemBean statusMessage = new StatusMessage();
//		statusMessage.setContent("Testing new library.");		
//		onlinePresence.setStatusMessage(statusMessage);
		
		PostBean twitterStatus = new Post();
		twitterStatus.setContent("Neki twitter status");
		twitterStatus.setPrimaryTopicOf("http://twitter.com/post/432432");
		PostBean mojStatus = new Post();
		mojStatus.setContent("Moj odgovor na twitter status status");
		mojStatus.setReplyOf(twitterStatus);
		
		onlinePresence.setStatusMessage(mojStatus);
		
		
		
		AgentBean agentNikola = new Agent();
		agentNikola.setName("Nikola Milikic");
		agentNikola.setHomepage("http://nikola.milikic.info");
		onlinePresence.setAction(new Chatting(agentNikola));
		
//		ProjectBean project = new Project();
//		project.setDateCreated("2010-03-08");
//		project.setHomepage("http://www.online-presence.net/api");
//		project.setName("OPO API");
//		project.setShortDescription("This is a short descripton of the OPO API project.");
//		onlinePresence.setAction(new WorkingOnProject(project));
		
		OPOService.export(onlinePresence, "exportedXML.rdf", "RDF/XML");
		
		OPOService.export(onlinePresence, "exportedTURTLE.rdf", "TURTLE");
		
		OPOService.export(onlinePresence, "exportedN3.rdf", "N3");
		
	}
	
	public static void main(String[] args) {
		OPOServiceTest.testExoport();
	}
}

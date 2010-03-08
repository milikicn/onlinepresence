package net.onlinepresence.services;

import java.net.MalformedURLException;
import java.net.URL;

import net.onlinepresence.domainmodel.doap.Project;
import net.onlinepresence.domainmodel.foaf.interfaces.AgentBean;
import net.onlinepresence.domainmodel.foaf.pojos.Agent;
import net.onlinepresence.domainmodel.foaf.pojos.Document;
import net.onlinepresence.domainmodel.opo.interfaces.OnlinePresenceBean;
import net.onlinepresence.domainmodel.opo.interfaces.presencecomponents.OnlineStatusBean;
import net.onlinepresence.domainmodel.opo.pojos.OnlinePresence;
import net.onlinepresence.domainmodel.opo.pojos.StatusMessage;
import net.onlinepresence.domainmodel.opo.pojos.presencecomponents.Findability;
import net.onlinepresence.domainmodel.opo.pojos.presencecomponents.Notifiability;
import net.onlinepresence.domainmodel.opo.pojos.presencecomponents.OnlineStatus;
import net.onlinepresence.domainmodel.opo.pojos.statuscomponents.Activity;
import net.onlinepresence.domainmodel.opo.pojos.statuscomponents.Contactability;
import net.onlinepresence.domainmodel.opo.pojos.statuscomponents.Disturbability;
import net.onlinepresence.domainmodel.opo.pojos.statuscomponents.Visibility;
import net.onlinepresence.domainmodel.opoactions.Chatting;
import net.onlinepresence.domainmodel.opoactions.WorkingOnProject;
import net.onlinepresence.domainmodel.sioc.interfaces.ItemBean;

public class OPOServiceTest {

	public static void testExoport(){
		
		OnlinePresenceBean onlinePresence = new OnlinePresence();
		
		AgentBean agentFilip = new Agent();
		agentFilip.setName("Filip Radulovic");
		try {
			agentFilip.setHomepage(new URL("http://filip.milstan.net"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		onlinePresence.setAgent(agentFilip);
		
		OnlineStatusBean onlineStatus = new OnlineStatus();
		onlineStatus.addStatusComponent(Activity.ACTIVE);
		onlineStatus.addStatusComponent(Contactability.FREELY_CONTACTABLE);
		onlineStatus.addStatusComponent(Disturbability.AVAILABLE);
		onlineStatus.addStatusComponent(Visibility.VISIBLE);
		
		onlinePresence.addPresenceComponent(Findability.PUBLICLY_FINDABLE);
		onlinePresence.addPresenceComponent(Notifiability.ALL_NOTIFICATIONS_PASS);
		onlinePresence.addPresenceComponent(onlineStatus);
		
		ItemBean statusMessage = new StatusMessage();
		statusMessage.setContent("Testing new library.");		
		onlinePresence.setStatusMessage(statusMessage);
		
		AgentBean agentNikola = new Agent();
		agentNikola.setName("Nikola Milikic");
		try {
			agentNikola.setHomepage(new URL("http://nikola.milikic.info"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		onlinePresence.setAction(new Chatting(agentNikola));
		
//		Project project = new Project();
//		project.setDateCreated("2010-03-08");
//		project.setHomepage(new Document("http://www.online-presence.net/api"));
//		project.setName("OPO API");
//		project.setShortDescription("This is a short descripton of the OPO API project.");
//
//		onlinePresence.setAction(new WorkingOnProject(project));
		
		OPOService.export(onlinePresence, "exportedXML.rdf", "RDF/XML");
		
		OPOService.export(onlinePresence, "exportedTURTLE.rdf", "TURTLE");
		
		OPOService.export(onlinePresence, "exportedN3.rdf", "N3");
		
	}
	
	public static void main(String[] args) {
		OPOServiceTest.testExoport();
	}
}

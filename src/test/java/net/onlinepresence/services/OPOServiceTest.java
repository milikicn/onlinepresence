package net.onlinepresence.services;

import net.onlinepresence.domainmodel.foaf.interfaces.AgentBean;
import net.onlinepresence.domainmodel.foaf.pojos.Agent;
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
import net.onlinepresence.domainmodel.sioc.interfaces.ItemBean;

public class OPOServiceTest {

	public static void testExoport(){
		
		OnlinePresenceBean onlinePresence = new OnlinePresence();
		onlinePresence.setURI("http://nekiURIzaOnlinePresence.com");
		
		AgentBean agent = new Agent();		
		agent.setURI("http://nekiURIzaAgenta.com");		
		onlinePresence.setAgent(agent);
		
		OnlineStatusBean onlineStatus = new OnlineStatus();
		onlineStatus.setURI("http://nekiURIzaOnlineStatus.com");
		onlineStatus.addStatusComponent(Activity.ACTIVE);
		onlineStatus.addStatusComponent(Contactability.FREELY_CONTACTABLE);
		onlineStatus.addStatusComponent(Disturbability.AVAILABLE);
		onlineStatus.addStatusComponent(Visibility.VISIBLE);
		
		onlinePresence.addPresenceComponent(Findability.PUBLICLY_FINDABLE);
		onlinePresence.addPresenceComponent(Notifiability.ALL_NOTIFICATIONS_PASS);
		onlinePresence.addPresenceComponent(onlineStatus);
		
		ItemBean statusMessage = new StatusMessage();
		statusMessage.setURI("http://nekiURIzaStatusMessage.com");
		statusMessage.setContent("Testiramo novu biblioteku");		
		onlinePresence.setStatusMessage(statusMessage);
		
		OPOService.export(onlinePresence, "exported.rdf", "RDF/XML");
		
	}
	
	public static void main(String[] args) {
		OPOServiceTest.testExoport();
	}
}

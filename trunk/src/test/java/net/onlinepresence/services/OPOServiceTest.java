package net.onlinepresence.services;

import net.onlinepresence.domainmodel.doap.Project;
import net.onlinepresence.domainmodel.doap.beans.ProjectBean;
import net.onlinepresence.domainmodel.foaf.Agent;
import net.onlinepresence.domainmodel.foaf.beans.AgentBean;
import net.onlinepresence.domainmodel.opo.OnlinePresence;
import net.onlinepresence.domainmodel.opo.beans.OnlinePresenceBean;
import net.onlinepresence.domainmodel.opo.beans.presencecomponents.OnlineStatusBean;
import net.onlinepresence.domainmodel.opo.presencecomponents.Findability;
import net.onlinepresence.domainmodel.opo.presencecomponents.Notifiability;
import net.onlinepresence.domainmodel.opo.presencecomponents.OnlineStatus;
import net.onlinepresence.domainmodel.opo.statuscomponents.Activity;
import net.onlinepresence.domainmodel.opo.statuscomponents.Contactability;
import net.onlinepresence.domainmodel.opo.statuscomponents.Disturbability;
import net.onlinepresence.domainmodel.opo.statuscomponents.Visibility;
import net.onlinepresence.domainmodel.opoactions.Chatting;
import net.onlinepresence.domainmodel.opoactions.WorkingOnProject;
import net.onlinepresence.domainmodel.sioc.Post;
import net.onlinepresence.domainmodel.sioc.beans.PostBean;

public class OPOServiceTest {

	public static void testExoport(){
		
		OnlinePresenceBean onlinePresence = new OnlinePresence();
		
		AgentBean agentFilip = new Agent();
		agentFilip.setName("Filip Radulovic");
		agentFilip.setHomepage("http://filip.milistan.net");

		onlinePresence.setAgent(agentFilip);
		
		OnlineStatusBean onlineStatus = new OnlineStatus();
		onlineStatus.setActivity(Activity.ACTIVE);
		onlineStatus.setContactability(Contactability.FREELY_CONTACTABLE);
		onlineStatus.setDisturbability(Disturbability.AVAILABLE);
		onlineStatus.setVisibility(Visibility.VISIBLE);
		
		onlinePresence.setFindability(Findability.PUBLICLY_FINDABLE);
		onlinePresence.setNotifiability(Notifiability.ALL_NOTIFICATIONS_PASS);
		onlinePresence.setOnlineStatus(onlineStatus);
		
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
		
		ProjectBean project = new Project();
		project.setDateCreated("2010-03-08");
		project.setHomepage("http://www.online-presence.net/api");
		project.setName("OPO API");
		project.setShortDescription("This is a short descripton of the OPO API project.");
		onlinePresence.setAction(new WorkingOnProject(project));
		
		
		FileOPOService foe = new FileOPOService();
		foe.saveOnlinePresenceToFile(onlinePresence, "exportedTURTLE.rdf", "TURTLE");
		
	}
	
	public static void testImport(){
		FileOPOService foe = new FileOPOService();
		OnlinePresence op = foe.loadOnlinePresenceFromFile("exportedTURTLE.rdf", "TURTLE");
		System.out.println(op.getOnlineStatus().getActivity().toString());
	}
	
	public static void main(String[] args) {
		OPOServiceTest.testExoport();
		OPOServiceTest.testImport();
	}
}

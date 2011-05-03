package net.onlinepresence.services;

import java.util.List;

import net.onlinepresence.jopo.ontmodel.foaf.Agent;
import net.onlinepresence.jopo.ontmodel.opo.OnlinePresence;
import net.onlinepresence.jopo.ontmodel.opo.StatusMessage;
import net.onlinepresence.jopo.ontmodel.opo.beans.presencecomponents.FindabilityBean;
import net.onlinepresence.jopo.ontmodel.opo.beans.presencecomponents.NotifiabilityBean;
import net.onlinepresence.jopo.ontmodel.opo.beans.statuscomponents.ActivityBean;
import net.onlinepresence.jopo.ontmodel.opo.beans.statuscomponents.ContactabilityBean;
import net.onlinepresence.jopo.ontmodel.opo.beans.statuscomponents.DisturbabilityBean;
import net.onlinepresence.jopo.ontmodel.opo.beans.statuscomponents.VisibilityBean;
import net.onlinepresence.jopo.ontmodel.opo.presencecomponents.OnlineStatus;
import net.onlinepresence.jopo.services.FileOPOService;
import net.onlinepresence.jopo.services.spring.ResourceFactory;

public class OPOServiceTest {

	public static void testExoport(){
		
		ResourceFactory factory = new ResourceFactory("http://namespace.com/");
		
		OnlinePresence onlinePresence = (OnlinePresence) factory.createResource(OnlinePresence.class);
		
		Agent agentFilip = (Agent) factory.createResource(Agent.class);
		agentFilip.setName("Filip Radulovic");
		agentFilip.setHomepage("http://filip.milistan.net");

		onlinePresence.setAgent(agentFilip);
				
		OnlineStatus onlineStatus = (OnlineStatus) factory.createResource(OnlineStatus.class);
		onlineStatus.setUri("http://www.123.com");
		onlineStatus.setActivity(ActivityBean.ACTIVE);
		onlineStatus.setContactability(ContactabilityBean.FREELY_CONTACTABLE);
		onlineStatus.setDisturbability(DisturbabilityBean.AVAILABLE);
		onlineStatus.setVisibility(VisibilityBean.VISIBLE);
		
		onlinePresence.setFindability(FindabilityBean.PUBLICLY_FINDABLE);
		onlinePresence.setNotifiability(NotifiabilityBean.ALL_NOTIFICATIONS_PASS);
		onlinePresence.setOnlineStatus(onlineStatus);
		
		StatusMessage statusMessage = (StatusMessage) factory.createResource(StatusMessage.class);
		statusMessage.setUri("http://www.status.com");
		statusMessage.setContent("Testing new library.");		
		onlinePresence.setStatusMessage(statusMessage);
		
//		Post twitterStatus = (Post) factory.createResource(Post.class);
//		twitterStatus.setContent("Neki twitter status");
//		twitterStatus.setPrimaryTopicOf("http://twitter.com/post/432432");
//		Post mojStatus = (Post) factory.createResource(Post.class);
//		mojStatus.setContent("Moj odgovor na twitter status status");
//		mojStatus.setReplyOf(twitterStatus);
		
//		onlinePresence.setStatusMessage(mojStatus);
//		
//		Agent agentNikola = factory.getResource(Resource.class);
//		agentNikola.setName("Nikola Milikic");
//		agentNikola.setHomepage("http://nikola.milikic.info");
//		onlinePresence.setAction(new ChattingBean(agentNikola));
//		
//		Project project = factory.getResource(Resource.class);
//		project.setDateCreated("2010-03-08");
//		project.setHomepage("http://www.online-presence.net/api");
//		project.setName("OPO API");
//		project.setShortDescription("This is a short descripton of the OPO API project.");
//		onlinePresence.setAction(new WorkingOnProjectBean(project));
		
		
		FileOPOService foe = new FileOPOService("exportedTURTLE.rdf", "TURTLE");
		foe.saveResource(onlinePresence, true);
		
	}
	
	public static void testImport(){
		FileOPOService foe = new FileOPOService("exportedTURTLE.rdf", "TURTLE");
		OnlinePresence op = ((List<OnlinePresence>)foe.loadAllResources(OnlinePresence.class, true)).get(0);
		System.out.println(op.getUri());
	}
	
	public static void main(String[] args) {
		OPOServiceTest.testExoport();
		//OPOServiceTest.testImport();
	}
}

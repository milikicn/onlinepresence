package net.onlinepresence.services;

import java.util.List;

import net.onlinepresence.jopo.ontmodel.opo.OnlinePresence;
import net.onlinepresence.jopo.services.FileOPOService;

public class OPOServiceTest {

	public static void testExoport(){
		
//		ResourceFactory factory = new ResourceFactory("http://namespace.com/");
//		
//		OnlinePresence onlinePresence = (OnlinePresence) factory.createResource(OnlinePresence.class);
//		
//		Agent agentFilip = (Agent) factory.createResource(Agent.class);
//		agentFilip.setName("Filip Radulovic");
//		agentFilip.setHomepage("http://filip.milistan.net");
//
//		onlinePresence.setAgent(agentFilip);
//				
//		OnlineStatus onlineStatus = (OnlineStatus) factory.createResource(OnlineStatus.class);
//		onlineStatus.setUri("http://www.123.com");
//		onlineStatus.setActivity(Activity.ACTIVE);
//		onlineStatus.setContactability(Contactability.FREELY_CONTACTABLE);
//		onlineStatus.setDisturbability(Disturbability.AVAILABLE);
//		onlineStatus.setVisibility(Visibility.VISIBLE);
//		
//		onlinePresence.setFindability(Findability.PUBLICLY_FINDABLE);
//		onlinePresence.setNotifiability(Notifiability.ALL_NOTIFICATIONS_PASS);
//		onlinePresence.setOnlineStatus(onlineStatus);
//		
//		StatusMessage statusMessage = (StatusMessage) factory.createResource(StatusMessage.class);
//		statusMessage.setUri("http://www.status.com");
//		statusMessage.setContent("Testing new library.");
//		statusMessage.setDateCreated(new Date());
//		onlinePresence.setStatusMessage(statusMessage);
//		
////		Post twitterStatus = (Post) factory.createResource(Post.class);
////		twitterStatus.setContent("Neki twitter status");
////		twitterStatus.setPrimaryTopicOf("http://twitter.com/post/432432");
////		Post mojStatus = (Post) factory.createResource(Post.class);
////		mojStatus.setContent("Moj odgovor na twitter status status");
////		mojStatus.setReplyOf(twitterStatus);
//		
////		onlinePresence.setStatusMessage(mojStatus);
////		
////		Agent agentNikola = factory.getResource(Resource.class);
////		agentNikola.setName("Nikola Milikic");
////		agentNikola.setHomepage("http://nikola.milikic.info");
////		onlinePresence.setAction(new ChattingBean(agentNikola));
////		
////		Project project = factory.getResource(Resource.class);
////		project.setDateCreated("2010-03-08");
////		project.setHomepage("http://www.online-presence.net/api");
////		project.setName("OPO API");
////		project.setShortDescription("This is a short descripton of the OPO API project.");
////		onlinePresence.setAction(new WorkingOnProjectBean(project));
//		
//		
//		FileOPOService foe = new FileOPOService("exportedTURTLE.rdf", "TURTLE");
//		foe.saveResource(onlinePresence, true);
		
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

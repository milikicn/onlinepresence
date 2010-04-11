package net.onlinepresence.services;

import net.onlinepresence.domainmodel.doap.Project;
import net.onlinepresence.domainmodel.doap.beans.ProjectBean;
import net.onlinepresence.domainmodel.foaf.Agent;
import net.onlinepresence.domainmodel.foaf.beans.AgentBean;
import net.onlinepresence.domainmodel.opo.OnlinePresence;
import net.onlinepresence.domainmodel.opo.beans.OnlinePresenceBean;
import net.onlinepresence.domainmodel.opo.beans.presencecomponents.FindabilityBean;
import net.onlinepresence.domainmodel.opo.beans.presencecomponents.NotifiabilityBean;
import net.onlinepresence.domainmodel.opo.beans.presencecomponents.OnlineStatusBean;
import net.onlinepresence.domainmodel.opo.beans.statuscomponents.ActivityBean;
import net.onlinepresence.domainmodel.opo.beans.statuscomponents.ContactabilityBean;
import net.onlinepresence.domainmodel.opo.beans.statuscomponents.DisturbabilityBean;
import net.onlinepresence.domainmodel.opo.beans.statuscomponents.VisibilityBean;
import net.onlinepresence.domainmodel.opo.presencecomponents.OnlineStatus;
import net.onlinepresence.domainmodel.opoactions.beans.ChattingBean;
import net.onlinepresence.domainmodel.opoactions.beans.WorkingOnProjectBean;
import net.onlinepresence.domainmodel.sioc.Post;
import net.onlinepresence.domainmodel.sioc.beans.PostBean;

public class OPOServiceTest {

	public static void testExoport(){
		
		OnlinePresence onlinePresence = new OnlinePresenceBean();
		
		Agent agentFilip = new AgentBean();
		agentFilip.setName("Filip Radulovic");
		agentFilip.setHomepage("http://filip.milistan.net");

		onlinePresence.setAgent(agentFilip);
		
		OnlineStatus onlineStatus = new OnlineStatusBean();
		onlineStatus.setActivity(ActivityBean.ACTIVE);
		onlineStatus.setContactability(ContactabilityBean.FREELY_CONTACTABLE);
		onlineStatus.setDisturbability(DisturbabilityBean.AVAILABLE);
		onlineStatus.setVisibility(VisibilityBean.VISIBLE);
		
		onlinePresence.setFindability(FindabilityBean.PUBLICLY_FINDABLE);
		onlinePresence.setNotifiability(NotifiabilityBean.ALL_NOTIFICATIONS_PASS);
		onlinePresence.setOnlineStatus(onlineStatus);
		
//		ItemBean statusMessage = new StatusMessage();
//		statusMessage.setContent("Testing new library.");		
//		onlinePresence.setStatusMessage(statusMessage);
		
		Post twitterStatus = new PostBean();
		twitterStatus.setContent("Neki twitter status");
		twitterStatus.setPrimaryTopicOf("http://twitter.com/post/432432");
		Post mojStatus = new PostBean();
		mojStatus.setContent("Moj odgovor na twitter status status");
		mojStatus.setReplyOf(twitterStatus);
		
		onlinePresence.setStatusMessage(mojStatus);
		
		Agent agentNikola = new AgentBean();
		agentNikola.setName("Nikola Milikic");
		agentNikola.setHomepage("http://nikola.milikic.info");
		onlinePresence.setAction(new ChattingBean(agentNikola));
		
		Project project = new ProjectBean();
		project.setDateCreated("2010-03-08");
		project.setHomepage("http://www.online-presence.net/api");
		project.setName("OPO API");
		project.setShortDescription("This is a short descripton of the OPO API project.");
		onlinePresence.setAction(new WorkingOnProjectBean(project));
		
		
		FileOPOService foe = new FileOPOService();
		foe.saveOnlinePresenceToFile(onlinePresence, "exportedTURTLE.rdf", "TURTLE");
		
	}
	
	public static void testImport(){
		FileOPOService foe = new FileOPOService();
		OnlinePresenceBean op = foe.loadOnlinePresenceFromFile("exportedTURTLE.rdf", "TURTLE");
		System.out.println(op.getOnlineStatus().getActivity().toString());
	}
	
	public static void main(String[] args) {
		OPOServiceTest.testExoport();
		OPOServiceTest.testImport();
	}
}

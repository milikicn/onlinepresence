package net.onlinepresence.services;

import net.onlinepresence.domainmodel.foaf.Agent;
import net.onlinepresence.domainmodel.opo.OnlinePresence;
import net.onlinepresence.domainmodel.opo.beans.OnlinePresenceBean;
import net.onlinepresence.services.spring.OPOResourceFactory;

public class CopyOfOPOServiceTest1 {

	public static void testExoport(){
		
		OPOResourceFactory factory = new OPOResourceFactory("http://online-presence.net/triplestore/store#");
		
		OnlinePresence op = (OnlinePresence) factory.getResource(OnlinePresence.class);
		
		Agent ag = (Agent) factory.getResource(Agent.class);
		ag.setNick("Filip");
		
		op.setAgent(ag);
		
//		FileOPOService foe = new FileOPOService();
//		foe.saveOnlinePresenceToFile(op, "exportedTURTLESpring.rdf", "TURTLE");
	}
	
	public static void testImport(){
		FileOPOService foe = new FileOPOService();
		OnlinePresenceBean op = foe.loadOnlinePresenceFromFile("exportedTURTLE.rdf", "TURTLE");
		System.out.println(op.getOnlineStatus().getActivity().toString());
	}
	
	public static void main(String[] args) {
		CopyOfOPOServiceTest1.testExoport();
//		CopyOfOPOServiceTest1.testImport();
	}
}

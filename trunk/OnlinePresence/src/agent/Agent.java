package agent;

import java.net.URI;

import presence.OnlinePresence;
import presence.OntologyConcept;

public class Agent extends OntologyConcept{

	OnlinePresence onlinePresence;
	
	public static Agent AGENT = new Agent();

	{
		this.setUri(URI.create("http://xmlns.com/foaf/spec/#term_Agent"));
	}
	
	//Sets Agent's OnlinePresence properties
	public void setOnlinePresence(OnlinePresence OnlinePresence){
		this.onlinePresence = OnlinePresence;
	}
}

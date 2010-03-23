package net.onlinepresence.domainmodel.foaf.interfaces;

import java.net.URI;

import net.onlinepresence.domainmodel.opo.interfaces.OnlinePresenceBean;

public interface AgentBean extends ThingBean{
	
	String getNick();
	void setNick(String nick);

	void setOnlinePresence(OnlinePresenceBean onlinePresence);
	OnlinePresenceBean getOnlinePresence();
	
	URI getMbox();
	void setMbox(URI mbox);
}

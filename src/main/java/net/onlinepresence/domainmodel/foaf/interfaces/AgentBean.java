package net.onlinepresence.domainmodel.foaf.interfaces;

import net.onlinepresence.domainmodel.opo.interfaces.OnlinePresenceBean;

public interface AgentBean extends ThingBean{
	
	String getNick();
	void setNick(String nick);

	void setOnlinePresence(OnlinePresenceBean onlinePresence);
	OnlinePresenceBean getOnlinePresence();
}

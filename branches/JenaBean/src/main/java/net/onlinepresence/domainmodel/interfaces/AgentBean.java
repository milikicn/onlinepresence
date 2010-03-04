package net.onlinepresence.domainmodel.interfaces;

import net.onlinepresence.domainmodel.general.ResourceBean;

public interface AgentBean extends ResourceBean{

	public void setOnlinePresence(OnlinePresenceBean onlinePresence);
	public OnlinePresenceBean getOnlinePresence();
}

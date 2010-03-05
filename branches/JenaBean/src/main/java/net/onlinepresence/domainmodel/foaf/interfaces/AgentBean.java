package net.onlinepresence.domainmodel.foaf.interfaces;

import net.onlinepresence.domainmodel.general.ResourceBean;
import net.onlinepresence.domainmodel.opo.interfaces.OnlinePresenceBean;

public interface AgentBean extends ResourceBean{

	public void setOnlinePresence(OnlinePresenceBean onlinePresence);
	public OnlinePresenceBean getOnlinePresence();
}

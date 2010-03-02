package net.onlinepresence.domainmodel.interfaces;

import net.onlinepresence.domainmodel.general.Resource;

public interface Agent extends Resource{

	public void setOnlinePresence(OnlinePresence onlinePresence);
	public OnlinePresence getOnlinePresence();
}

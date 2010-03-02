package net.onlinepresence.domainmodel.interfaces;

import net.onlinepresence.domainmodel.general.Resource;

public interface OnlinePresenceComponent extends Resource{

	public void setComponent(OnlinePresence component);
	public OnlinePresence getComponent();
}

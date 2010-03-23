package net.onlinepresence.domainmodel.opo.interfaces;

import net.onlinepresence.domainmodel.general.ResourceBean;

/**
 * A component of OnlinePresence used to represent one of its dimensions.
 *
 */
public interface OnlinePresenceComponentBean extends ResourceBean{

	public void setComponent(OnlinePresenceBean component);
	public OnlinePresenceBean getComponent();
}

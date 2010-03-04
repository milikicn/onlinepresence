package net.onlinepresence.domainmodel.interfaces;

import net.onlinepresence.domainmodel.general.ResourceBean;

public interface OnlinePresenceComponentBean extends ResourceBean{

	public void setComponent(OnlinePresenceBean component);
	public OnlinePresenceBean getComponent();
}

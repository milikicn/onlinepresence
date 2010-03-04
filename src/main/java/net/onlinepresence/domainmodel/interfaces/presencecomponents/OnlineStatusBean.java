package net.onlinepresence.domainmodel.interfaces.presencecomponents;

import java.util.Collection;

import net.onlinepresence.domainmodel.interfaces.OnlinePresenceComponentBean;
import net.onlinepresence.domainmodel.interfaces.OnlineStatusComponentBean;

public interface OnlineStatusBean extends OnlinePresenceComponentBean{

	public void setStatusComponents(Collection<OnlineStatusComponentBean> statusComponents);
	public Collection<OnlineStatusComponentBean> getOnlineStatusComponents();
	public void addStatusComponent(OnlineStatusComponentBean statusComponent);
	
	public void setName(String statusName);
	public String getName();
}

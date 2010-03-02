package net.onlinepresence.domainmodel.interfaces.presencecomponents;

import java.util.Collection;

import net.onlinepresence.domainmodel.interfaces.OnlinePresenceComponent;
import net.onlinepresence.domainmodel.interfaces.OnlineStatusComponent;

public interface OnlineStatus extends OnlinePresenceComponent{

	public void setStatusComponents(Collection<OnlineStatusComponent> statusComponents);
	public Collection<OnlineStatusComponent> getOnlineStatusComponents();
	public void addStatusComponent(OnlineStatusComponent statusComponent);
	
	public void setName(String statusName);
	public String getName();
}

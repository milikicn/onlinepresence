package net.onlinepresence.domainmodel.opo.interfaces.presencecomponents;

import java.util.Collection;

import net.onlinepresence.domainmodel.opo.interfaces.OnlinePresenceComponentBean;
import net.onlinepresence.domainmodel.opo.interfaces.OnlineStatusComponentBean;

/**
 * The OnlinePresenceComponent used to represent the attitude of an Agent towards 
 * the possibility of communication with other Agents. The OnlineStatus is defined 
 * by its components.
 *
 */
public interface OnlineStatusBean extends OnlinePresenceComponentBean{

	public void setOnlineStatusComponents(Collection<OnlineStatusComponentBean> statusComponents);
	public Collection<OnlineStatusComponentBean> getOnlineStatusComponents();
	public void addStatusComponent(OnlineStatusComponentBean statusComponent);
	
	public void setName(String statusName);
	public String getName();
}

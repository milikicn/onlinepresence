package net.onlinepresence.domainmodel.opo.interfaces.statuscomponents;

import net.onlinepresence.domainmodel.opo.interfaces.OnlineStatusComponentBean;

/**
 * OnlineStatusComponent used to represent the state of Activity of the Agent 
 * (e.g., Active, Inactive,	ProlongedInactive).
 *
 */
public interface ActivityBean extends OnlineStatusComponentBean{

	public void setInactivityPeriod(int inactivityPeriod);
	public int getInactivityPeriod();
}

package net.onlinepresence.domainmodel.interfaces.statuscomponents;

import net.onlinepresence.domainmodel.interfaces.OnlineStatusComponentBean;

public interface ActivityBean extends OnlineStatusComponentBean{

	public void setInactivityPeriod(int inactivityPeriod);
	public int getInactivityPeriod();
}

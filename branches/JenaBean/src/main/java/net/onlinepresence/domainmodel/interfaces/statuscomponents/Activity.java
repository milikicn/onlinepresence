package net.onlinepresence.domainmodel.interfaces.statuscomponents;

import net.onlinepresence.domainmodel.interfaces.OnlineStatusComponent;

public interface Activity extends OnlineStatusComponent{

	public void setInactivityPeriod(int inactivityPeriod);
	public int getInactivityPeriod();
}

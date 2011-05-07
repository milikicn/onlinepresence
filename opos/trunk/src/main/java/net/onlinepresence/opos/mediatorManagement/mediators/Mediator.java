package net.onlinepresence.opos.mediatorManagement.mediators;

import net.onlinepresence.jopo.ontmodel.opo.OnlinePresence;
import net.onlinepresence.opos.domain.ApplicationNames;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.exceptions.OPOSException;

public interface Mediator {

	void sendOnlinePresenceToUser(OnlinePresence op, Membership membership);
	
	void propagateOnlinePresence(OnlinePresence onlinePresence);
	
	ApplicationNames getMediatorName();
}

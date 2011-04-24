package net.onlinepresence.opos.mediators.mediators;

import net.onlinepresence.ontmodel.opo.OnlinePresence;
import net.onlinepresence.opos.domain.ApplicationNames;
import net.onlinepresence.opos.domain.Membership;

public interface Mediator {

	void sendOnlinePresenceToUser(OnlinePresence op, Membership membership);
	
	void propagateOnlinePresence(OnlinePresence onlinePresence);
	
	ApplicationNames getMediatorName();
}

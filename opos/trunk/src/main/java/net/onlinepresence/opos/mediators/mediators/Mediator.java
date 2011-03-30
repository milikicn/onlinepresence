package net.onlinepresence.opos.mediators.mediators;

import net.onlinepresence.ontmodel.opo.OnlinePresence;
import net.onlinepresence.opos.domain.Membership;

public interface Mediator {

	MediatorNames getMediatorName();
	
	void sendOnlinePresenceToUser(OnlinePresence op, Membership membership);
	
	void propagateOnlinePresence(OnlinePresence onlinePresence);
}

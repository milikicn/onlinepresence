package net.onlinepresence.opos.mediators.mediators.facebook;

import java.util.List;

import net.onlinepresence.ontmodel.opo.OnlinePresence;
import net.onlinepresence.opos.domain.ApplicationNames;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.mediators.MediatorManager;
import net.onlinepresence.opos.mediators.mediators.Mediator;

public class FacebookMediator implements Mediator {
	
	private List<Membership> users = null;
	
	public FacebookMediator(List<Membership> users) {
		this.users = users;
		
		initThreadsForFacebookUsers(users);
	}

	private void initThreadsForFacebookUsers(List<Membership> users2) {
		// TODO Auto-generated method stub
		
	}

	public void sendOnlinePresenceToUser(OnlinePresence op,
			Membership membership) {
		// TODO Auto-generated method stub
	}

	public void propagateOnlinePresence(OnlinePresence onlinePresence) {
		MediatorManager.getInstance().propagateOnlinePresence(onlinePresence);
	}
	
	public ApplicationNames getMediatorName() {
		return ApplicationNames.FACEBOOK;
	}

}

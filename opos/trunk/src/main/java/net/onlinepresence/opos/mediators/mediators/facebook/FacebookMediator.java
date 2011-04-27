package net.onlinepresence.opos.mediators.mediators.facebook;

import java.util.List;

import net.onlinepresence.ontmodel.opo.OnlinePresence;
import net.onlinepresence.opos.domain.ApplicationNames;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.mediators.MediatorManager;
import net.onlinepresence.opos.mediators.mediators.Mediator;
import net.onlinepresence.opos.mediators.mediators.ProfileCheckerThread;
import net.onlinepresence.opos.mediators.mediators.twitter.IntervalPullMediator;

public class FacebookMediator extends IntervalPullMediator {
	
	public FacebookMediator(List<Membership> users) {
		super(users);
	}

	public void sendOnlinePresenceToUser(OnlinePresence op,	Membership membership) {
	}

	public ApplicationNames getMediatorName() {
		return ApplicationNames.FACEBOOK;
	}

	@Override
	public ProfileCheckerThread spawnNewProfileCheckerThread(Membership userMembership) {
		return null;
	}

}

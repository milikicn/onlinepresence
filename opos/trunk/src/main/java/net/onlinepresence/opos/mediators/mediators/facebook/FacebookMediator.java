package net.onlinepresence.opos.mediators.mediators.facebook;

import net.onlinepresence.ontmodel.opo.OnlinePresence;
import net.onlinepresence.opos.domain.ApplicationNames;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.mediators.mediators.IntervalPullMediator;
import net.onlinepresence.opos.mediators.mediators.ProfileCheckerThread;

public class FacebookMediator extends IntervalPullMediator {
	
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

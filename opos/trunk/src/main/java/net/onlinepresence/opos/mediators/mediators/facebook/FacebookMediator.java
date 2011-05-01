package net.onlinepresence.opos.mediators.mediators.facebook;

import org.apache.log4j.Logger;

import net.onlinepresence.ontmodel.opo.OnlinePresence;
import net.onlinepresence.opos.domain.ApplicationNames;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.mediators.mediators.IntervalPullMediator;

public class FacebookMediator extends IntervalPullMediator {
	
	private Logger logger = Logger.getLogger(FacebookMediator.class);
	
	private static FacebookMediator INSTANCE;
	
	private FacebookMediator() { }
	
	public static FacebookMediator getInstance() {
		if (INSTANCE == null)
			INSTANCE = new FacebookMediator();
		
		else if (!instantiated)
			throw new RuntimeException("FacebookMediator should be instantiated first.");
		
		return INSTANCE;
	}
	
	public void sendOnlinePresenceToUser(OnlinePresence op,	Membership membership) {
	}

	public ApplicationNames getMediatorName() {
		return ApplicationNames.FACEBOOK;
	}

	@Override
	public void spawnAndAddNewProfileCheckerThread(Membership userMembership) {

	}

}

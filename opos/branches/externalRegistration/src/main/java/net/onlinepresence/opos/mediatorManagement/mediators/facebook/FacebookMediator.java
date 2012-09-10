package net.onlinepresence.opos.mediatorManagement.mediators.facebook;

import org.apache.log4j.Logger;

import net.onlinepresence.jopo.ontmodel.opo.OnlinePresence;
import net.onlinepresence.opos.domain.ApplicationName;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.exceptions.OPOSException;
import net.onlinepresence.opos.mediatorManagement.mediators.IntervalPullMediator;
import net.onlinepresence.opos.mediatorManagement.mediators.ProfileCheckerThread;

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
	
	@Override
	public void spawnAndAddNewProfileCheckerThread(Membership userMembership) throws OPOSException {
		logger.debug("TwitterMediator: instantiating TwitterProfileCheckerThread for username " + userMembership.getUsername());
		
		ProfileCheckerThread tpc = new FacebookProfileCheckerThread(userMembership);
		tpc.start();
		getProfileCheckers().add(tpc);
	}

	@Override
	public void sendOnlinePresenceToUser(OnlinePresence op, Membership membership) {
		// TODO Auto-generated method stub
	}
	
	public String getMediatorName() {
		return ApplicationName.FACEBOOK;
	}

}

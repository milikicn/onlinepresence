package net.onlinepresence.opos.mediatorManagement.mediators.foursquare;

import org.apache.log4j.Logger;

import net.onlinepresence.jopo.ontmodel.opo.OnlinePresence;
import net.onlinepresence.opos.domain.ApplicationName;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.exceptions.OPOSException;
import net.onlinepresence.opos.mediatorManagement.mediators.IntervalPullMediator;
import net.onlinepresence.opos.mediatorManagement.mediators.ProfileCheckerThread;

public class FoursquareMediator extends IntervalPullMediator {
	
	private Logger logger = Logger.getLogger(FoursquareMediator.class);
	
	private static FoursquareMediator INSTANCE;
	
	private FoursquareMediator() { }
	
	public static FoursquareMediator getInstance() {
		if (INSTANCE == null)
			INSTANCE = new FoursquareMediator();
		
		else if (!instantiated)
			throw new RuntimeException("FoursquareMediator should be instantiated first.");
		
		return INSTANCE;
	}
	
	@Override
	public void spawnAndAddNewProfileCheckerThread(Membership userMembership) throws OPOSException {
		logger.debug("FoursquareMediator: instantiating FoursquareProfileCheckerThread for username " + userMembership.getUsername());
		
		ProfileCheckerThread tpc = new FoursquareProfileCheckerThread(userMembership);
		tpc.start();
		getProfileCheckers().add(tpc);
	}

	@Override
	public void sendOnlinePresenceToUser(OnlinePresence op, Membership membership) {
		// TODO Auto-generated method stub
	}
	
	public String getMediatorName() {
		return ApplicationName.FOURSQUARE;
	}

}

package net.onlinepresence.opos.mediators.mediators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import net.onlinepresence.ontmodel.opo.OnlinePresence;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.mediators.MediatorManager;
import net.onlinepresence.opos.mediators.mediators.twitter.exceptions.OPOSException;

public abstract class IntervalPullMediator implements Mediator {

	private Logger logger = Logger.getLogger(this.getClass());
	
	private List<ProfileCheckerThread> profileCheckers = new ArrayList<ProfileCheckerThread>();
	protected static boolean instantiated;
	
	public void init(List<Membership> users) throws OPOSException {
		instantiated = true;
		
		initProfileCheckerThreads(users);
	}

	private void initProfileCheckerThreads(List<Membership> users) throws OPOSException {
		Iterator<Membership> iterator = users.iterator();

		while (iterator.hasNext()) {
			Membership userMembership = (Membership) iterator.next();
			ProfileCheckerThread tpc = spawnNewProfileCheckerThread(userMembership);
			tpc.start();
			profileCheckers.add(tpc);
		}
	}

	public void propagateOnlinePresence(OnlinePresence onlinePresence) {
		logger.debug("Propagating Online Presence to the MediatorManager.");
		MediatorManager.getInstance().propagateOnlinePresence(onlinePresence);
	}
	
	public abstract ProfileCheckerThread spawnNewProfileCheckerThread(Membership userMembership) throws OPOSException;
	public abstract void sendOnlinePresenceToUser(OnlinePresence op, Membership membership);

	public List<ProfileCheckerThread> getProfileCheckers() {
		return profileCheckers;
	}
}

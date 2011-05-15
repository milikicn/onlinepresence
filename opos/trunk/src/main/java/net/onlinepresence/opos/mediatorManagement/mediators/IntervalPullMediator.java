package net.onlinepresence.opos.mediatorManagement.mediators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import net.onlinepresence.jopo.ontmodel.opo.OnlinePresence;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.mediatorManagement.MediatorManager;
import net.onlinepresence.opos.exceptions.OPOSException;

public abstract class IntervalPullMediator implements Mediator {

	private Logger logger = Logger.getLogger(this.getClass());
	
	private List<ProfileCheckerThread> profileCheckers = new ArrayList<ProfileCheckerThread>();
	protected static boolean instantiated;
	
	/**
	 * This method will be called from MediatorManager.
	 * 
	 * @param users
	 * @throws OPOSException
	 */
	public void init(List<Membership> users) throws OPOSException {
		instantiated = true;
		
		initProfileCheckerThreads(users);
	}

	private void initProfileCheckerThreads(List<Membership> users) throws OPOSException {
		Iterator<Membership> iterator = users.iterator();

		while (iterator.hasNext()) {
			Membership userMembership = (Membership) iterator.next();
			spawnAndAddNewProfileCheckerThread(userMembership);
		}
	}

	public void propagateOnlinePresence(OnlinePresence onlinePresence) {
		logger.debug("Propagating Online Presence from "+this.getClass().getCanonicalName()+" instance to the MediatorManager.");
		
		MediatorManager.getInstance().propagateOnlinePresence(onlinePresence);
	}

	public List<ProfileCheckerThread> getProfileCheckers() {
		return profileCheckers;
	}
	
	public void shutDownProfileCheckerThread(Membership userMembership) throws OPOSException {
		for (ProfileCheckerThread profileChecker : profileCheckers) {
			if (profileChecker.getUserMembership().equals(userMembership)) {
				logger.debug("Shutting down profile checker thread for membership of a user "
						+ userMembership.getUsername() + " on "
						+ userMembership.getApplication().getName()	+ " application.");
				profileChecker.shutDown();
				profileCheckers.remove(profileChecker);
				break;
			}
		}
	}
	public abstract void spawnAndAddNewProfileCheckerThread(Membership userMembership) throws OPOSException;
	public abstract void sendOnlinePresenceToUser(OnlinePresence op, Membership membership);
}

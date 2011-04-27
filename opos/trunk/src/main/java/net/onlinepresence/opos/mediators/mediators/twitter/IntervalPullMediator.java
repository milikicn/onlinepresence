package net.onlinepresence.opos.mediators.mediators.twitter;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import net.onlinepresence.ontmodel.opo.OnlinePresence;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.mediators.MediatorManager;
import net.onlinepresence.opos.mediators.mediators.Mediator;
import net.onlinepresence.opos.mediators.mediators.ProfileCheckerThread;

public abstract class IntervalPullMediator implements Mediator {

	private Logger logger = Logger.getLogger(this.getClass());
	
	private List<ProfileCheckerThread> profileCheckers;
	private List<Membership> users = null;
	
	public IntervalPullMediator(List<Membership> users) {
		this.users = users;
		profileCheckers = new LinkedList<ProfileCheckerThread>();
		initProfileCheckerThreads(users);
	}

	private void initProfileCheckerThreads(List<Membership> users) {
		Iterator<Membership> iterator = users.iterator();

		while (iterator.hasNext()) {
			Membership userMembership = (Membership) iterator.next();
			ProfileCheckerThread tpc = spawnNewProfileCheckerThread(userMembership);
			tpc.start();
			profileCheckers.add(tpc);
		}
	}
	
	public abstract ProfileCheckerThread spawnNewProfileCheckerThread(Membership userMembership);

	public void sendOnlinePresenceToUser(OnlinePresence op, Membership membership) {
		logger.debug("Sending OnlinePresence ("+op+") to the user "+membership.getUser().getName());
		ProfileCheckerThread tpcThread = findTwitterProfileCheckerThread(membership);
		
//		logger.debug("Pokrecem TwitterProfileSubmitThread");
//		new TwitterProfileSubmitThread(op, tpcThread).start();
	}

	public void propagateOnlinePresence(OnlinePresence onlinePresence) {
		logger.debug("Propagating Online Presence to the MediatorManager.");
		MediatorManager.getInstance().propagateOnlinePresence(onlinePresence);
	}
	
	private ProfileCheckerThread findTwitterProfileCheckerThread(Membership membership){
//		Iterator<ProfileCheckerThread> iterator = profileCheckers.iterator();
//		
//		while (iterator.hasNext()) {
//			ProfileCheckerThread profileCheckerThread = (ProfileCheckerThread) iterator
//					.next();
//			
//			if(profileCheckerThread.getUser().equals(membership)){
//				logger.debug("Pronasao sam twitterProfileCheckerThread koji ima usera " + membership);
//				logger.debug("a twitterStuff mu je: " + profileCheckerThread.getTwitterStuff());
//				return profileCheckerThread;
//			}
//		}
		
		return null;
	}
}

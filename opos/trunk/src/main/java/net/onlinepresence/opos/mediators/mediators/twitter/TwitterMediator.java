package net.onlinepresence.opos.mediators.mediators.twitter;

import org.apache.log4j.Logger;

import net.onlinepresence.jopo.ontmodel.opo.OnlinePresence;
import net.onlinepresence.opos.domain.ApplicationNames;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.mediators.mediators.IntervalPullMediator;
import net.onlinepresence.opos.mediators.mediators.ProfileCheckerThread;
import net.onlinepresence.opos.exceptions.OPOSException;
import net.onlinepresence.opos.mediators.mediators.twitter.threads.TwitterProfileCheckerThread;

public class TwitterMediator extends IntervalPullMediator {
	
	private Logger logger = Logger.getLogger(TwitterMediator.class);
	
	private static TwitterMediator INSTANCE;
	
	private TwitterMediator() { }
	
	public static TwitterMediator getInstance() {
		if (INSTANCE == null)
			INSTANCE = new TwitterMediator();
		
		else if (!instantiated)
			throw new RuntimeException("TwitterMediator should be instantiated first.");
		
		return INSTANCE;
	}
	
	public void spawnAndAddNewProfileCheckerThread(Membership userMembership) throws OPOSException {
		logger.debug("TwitterMediator: instantiating TwitterProfileCheckerThread for username " + userMembership.getUsername());
		
		ProfileCheckerThread tpc = new TwitterProfileCheckerThread(userMembership);
		tpc.start();
		getProfileCheckers().add(tpc);
	}
	
	public void sendOnlinePresenceToUser(OnlinePresence op, Membership membership) {
		logger.debug("sendOnlinePresenceToUser");
		ProfileCheckerThread tpcThread = findTwitterProfileCheckerThread(membership);
		
//		logger.debug("Starting TwitterProfileSubmitThread");
//		new TwitterProfileSubmitThread(op, tpcThread).start();
	}

	private TwitterProfileCheckerThread findTwitterProfileCheckerThread(Membership membership){
//		Iterator<TwitterProfileCheckerThread> iterator = twitterProfileCheckers.iterator();
//		
//		while (iterator.hasNext()) {
//			TwitterProfileCheckerThread twitterProfileCheckerThread = (TwitterProfileCheckerThread) iterator
//					.next();
//			
//			if(twitterProfileCheckerThread.getUserMembership().equals(membership)){
//				logger.debug("Pronasao sam twitterProfileCheckerThread koji ima usera " + membership);
//				return twitterProfileCheckerThread;
//			}
//		}
		
		return null;
	}
	
	public ApplicationNames getMediatorName() {
		return ApplicationNames.TWITTER;
	}
}

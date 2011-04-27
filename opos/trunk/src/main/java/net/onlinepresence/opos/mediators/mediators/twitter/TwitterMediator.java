package net.onlinepresence.opos.mediators.mediators.twitter;

import org.apache.log4j.Logger;

import twitter4j.Twitter;
import twitter4j.auth.AccessToken;

import net.onlinepresence.ontmodel.opo.OnlinePresence;
import net.onlinepresence.opos.domain.ApplicationNames;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.mediators.mediators.IntervalPullMediator;
import net.onlinepresence.opos.mediators.mediators.ProfileCheckerThread;
import net.onlinepresence.opos.mediators.mediators.twitter.exceptions.OPOSException;
import net.onlinepresence.opos.mediators.mediators.twitter.threads.TwitterProfileCheckerThread;
import net.onlinepresence.opos.mediators.mediators.twitter.threads.TwitterProfileSubmitThread;
import net.onlinepresence.opos.mediators.mediators.twitter.util.Util;

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
	
	public ProfileCheckerThread spawnNewProfileCheckerThread(Membership userMembership) throws OPOSException {
		logger.debug("TwitterMediator: instantiating TwitterProfileCheckerThread for username " + userMembership.getUsername());
		AccessToken accessToken = Util.loadAccessToken(userMembership);
		if (accessToken == null)
			throw new OPOSException("Error resembling Twitter sccess token.");
		
		Twitter twitter = TwitterCommunication.getInstance().getTwitterFactory().getInstance(accessToken);
		
		return new TwitterProfileCheckerThread(userMembership, twitter);
	}
	
	public void createNewProfileCheckerThread(Membership userMembership, Twitter twitter) throws OPOSException {
		TwitterProfileCheckerThread tpc = new TwitterProfileCheckerThread(userMembership, twitter);
		tpc.start();
		getProfileCheckers().add(tpc);
	}
	
	public void sendOnlinePresenceToUser(OnlinePresence op, Membership membership) {
		logger.debug("sendOnlinePresenceToUser");
		TwitterProfileCheckerThread tpcThread = findTwitterProfileCheckerThread(membership);
		
		logger.debug("Pokrecem TwitterProfileSubmitThread");
		new TwitterProfileSubmitThread(op, tpcThread).start();
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

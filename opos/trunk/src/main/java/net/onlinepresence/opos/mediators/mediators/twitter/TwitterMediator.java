package net.onlinepresence.opos.mediators.mediators.twitter;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import twitter4j.Twitter;

import net.onlinepresence.ontmodel.opo.OnlinePresence;
import net.onlinepresence.opos.domain.ApplicationNames;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.mediators.MediatorManager;
import net.onlinepresence.opos.mediators.mediators.Mediator;
import net.onlinepresence.opos.mediators.mediators.twitter.exceptions.TwitterOPOSException;
import net.onlinepresence.opos.mediators.mediators.twitter.threads.TwitterProfileCheckerThread;
import net.onlinepresence.opos.mediators.mediators.twitter.threads.TwitterProfileSubmitThread;

public class TwitterMediator implements Mediator {
	
	private Logger logger = Logger.getLogger(TwitterMediator.class);
	
	private List<TwitterProfileCheckerThread> twitterProfileCheckers = new LinkedList<TwitterProfileCheckerThread>();
//	private List<Membership> users;
	private static boolean instantiated;
	
	private static TwitterMediator INSTANCE;
	
	private TwitterMediator() { }
	
	public static TwitterMediator getInstance() {
		if (INSTANCE == null)
			INSTANCE = new TwitterMediator();
		
		else if (!instantiated)
			throw new RuntimeException("TwitterMediator should be instantiated first.");
		
		return INSTANCE;
	}
	
	public void init(List<Membership> users) {
		instantiated = true;
		
//		this.users = users;
		initThreadsForTwitterUsers(users);
	}

	private void initThreadsForTwitterUsers(List<Membership> users) {
		Iterator<Membership> iterator = users.iterator();

		while (iterator.hasNext()) {
			Membership userMembership = (Membership) iterator.next();
			spawnNewProfileCheckerThread(userMembership, null);
		}
	}
	
	public void spawnNewProfileCheckerThread(Membership userMembership, Twitter twitter){
		logger.debug("TwitterMediator: instantiating TwitterProfileCheckerThread for username " + userMembership.getUsername());
		TwitterProfileCheckerThread tpc;
		try {
			tpc = new TwitterProfileCheckerThread(userMembership, twitter);
			tpc.start();
			twitterProfileCheckers.add(tpc);
		} catch (TwitterOPOSException e) {
			logger.error(e.getMessage());
		}
	}

	public ApplicationNames getMediatorName() {
		return ApplicationNames.TWITTER;
	}
	
	public void sendOnlinePresenceToUser(OnlinePresence op, Membership membership) {
		logger.debug("sendOnlinePresenceToUser");
		TwitterProfileCheckerThread tpcThread = findTwitterProfileCheckerThread(membership);
		
		logger.debug("Pokrecem TwitterProfileSubmitThread");
		new TwitterProfileSubmitThread(op, tpcThread).start();
	}

	public void propagateOnlinePresence(OnlinePresence onlinePresence) {
		logger.debug("Propagating OP");
		MediatorManager.getInstance().propagateOnlinePresence(onlinePresence);
	}
	
	private TwitterProfileCheckerThread findTwitterProfileCheckerThread(Membership membership){
		Iterator<TwitterProfileCheckerThread> iterator = twitterProfileCheckers.iterator();
		
		while (iterator.hasNext()) {
			TwitterProfileCheckerThread twitterProfileCheckerThread = (TwitterProfileCheckerThread) iterator
					.next();
			
			if(twitterProfileCheckerThread.getUserMembership().equals(membership)){
				logger.debug("Pronasao sam twitterProfileCheckerThread koji ima usera " + membership);
				return twitterProfileCheckerThread;
			}
		}
		
		return null;
	}
}

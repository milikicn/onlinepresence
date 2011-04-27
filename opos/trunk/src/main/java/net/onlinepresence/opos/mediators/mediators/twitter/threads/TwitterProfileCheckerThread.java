package net.onlinepresence.opos.mediators.mediators.twitter.threads;

import org.apache.log4j.Logger;

import twitter4j.Twitter;
import net.onlinepresence.ontmodel.opo.OnlinePresence;
import net.onlinepresence.opos.config.Settings;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.mediators.mediators.ProfileCheckerThread;
import net.onlinepresence.opos.mediators.mediators.twitter.TwitterMediator;
import net.onlinepresence.opos.mediators.mediators.twitter.exceptions.OPOSException;
import net.onlinepresence.opos.mediators.mediators.twitter.service.builder.TwitterOnlinePresenceBuilder;
import net.onlinepresence.opos.mediators.mediators.twitter.util.TwitterOnlinePresenceUtil;

public class TwitterProfileCheckerThread extends ProfileCheckerThread {
	
	private Logger logger = Logger.getLogger(TwitterProfileCheckerThread.class);
	
	private static final long TIMEOUT = Settings.getInstance().config.twitterMediatorConfig.checkTimeout;
	private Membership userMembership;
	private OnlinePresence currentOnlinePresence;
	private boolean wait = false;
	private boolean checking = false;
	
	private TwitterOnlinePresenceBuilder topBuilder = null;
	
	public TwitterProfileCheckerThread(Membership userMembership, Twitter twitter) throws OPOSException {
		this.userMembership = userMembership;
		
		topBuilder = new TwitterOnlinePresenceBuilder(twitter);
		
		// TODO: retrieve OnlinePresence instance from the repository if exist
	}

	/**
	 * @return the userMembership
	 */
	public Membership getUserMembership() {
		return userMembership;
	}

	/**
	 * @return the currentOnlinePresence
	 */
	public OnlinePresence getCurrentOnlinePresence() {
		return currentOnlinePresence;
	}

	/**
	 * @param currentOnlinePresence the currentOnlinePresence to set
	 */
	public void setCurrentOnlinePresence(OnlinePresence currentOnlinePresence) {
		this.currentOnlinePresence = currentOnlinePresence;
	}

	public void setWait(boolean wait) {
		this.wait = wait;
	}

	/**
	 * @return the checking
	 */
	public boolean isChecking() {
		return checking;
	}

	/**
	 * @param checking the checking to set
	 */
	public void setChecking(boolean checking) {
		this.checking = checking;
	}

	@Override
	public void run() {
		logger.debug("Started checking of the Twitter profile.");
		
		while (true) {
			setChecking(true);
			if(!wait){
				try {
					OnlinePresence newOnlinePresence = topBuilder.build();
					
					if (currentOnlinePresence != null) {
						if(!TwitterOnlinePresenceUtil.equalOnlinePresenceData(currentOnlinePresence, newOnlinePresence)){
							TwitterMediator.getInstance().propagateOnlinePresence(newOnlinePresence);
							currentOnlinePresence = newOnlinePresence;
						}
					}else {
						currentOnlinePresence = newOnlinePresence;
					}
				} catch (OPOSException e) {
					e.printStackTrace();
				}	
				
				try {
					setChecking(false);
					sleep(TIMEOUT);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

package net.onlinepresence.opos.mediators.mediators;

import net.onlinepresence.ontmodel.opo.OnlinePresence;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.mediators.mediators.twitter.exceptions.OPOSException;
import net.onlinepresence.opos.mediators.mediators.twitter.service.builder.TwitterOnlinePresenceBuilder;
import net.onlinepresence.opos.mediators.mediators.twitter.util.OnlinePresenceUtil;
import net.onlinepresence.opos.semanticstuff.services.OnlinePresenceService;

import org.apache.log4j.Logger;

import twitter4j.Twitter;

public abstract class ProfileCheckerThread extends Thread {

	public abstract long getTimeoutMilis();
	public abstract Mediator getProfileCheckerMediator();
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private OnlinePresence currentOnlinePresence;
	private boolean wait = false;
	private boolean checking = false;
	
	protected TwitterOnlinePresenceBuilder onlinePresenceBulder = null;
	
	public ProfileCheckerThread(Membership userMembership, Twitter twitter) throws OPOSException {
		onlinePresenceBulder = new TwitterOnlinePresenceBuilder(twitter);
		
		// TODO: retrieve OnlinePresence instance from the repository if exist
		OnlinePresenceService opService = new OnlinePresenceService();
		
		OnlinePresence lastOnlinePresence = null;
		try {
			lastOnlinePresence = opService.getLastOnlinePresence(userMembership);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		if (lastOnlinePresence != null)
			setCurrentOnlinePresence(lastOnlinePresence);
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
		logger.debug("Started checking of the "+getProfileCheckerMediator().getMediatorName()+" profile.");
		
		while (true) {
			setChecking(true);
			if(!wait){
				try {
					OnlinePresence newOnlinePresence = onlinePresenceBulder.build();
					
					if (currentOnlinePresence != null) {
						if(!OnlinePresenceUtil.equalOnlinePresenceData(currentOnlinePresence, newOnlinePresence)){
							getProfileCheckerMediator().propagateOnlinePresence(newOnlinePresence);
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
					sleep(getTimeoutMilis());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

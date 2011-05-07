package net.onlinepresence.opos.mediators.mediators;

import net.onlinepresence.jopo.ontmodel.opo.OnlinePresence;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.exceptions.OPOSException;
import net.onlinepresence.opos.mediators.mediators.twitter.service.builder.TwitterOnlinePresenceBuilder;
import net.onlinepresence.opos.mediators.mediators.twitter.util.OnlinePresenceUtil;
import net.onlinepresence.opos.semanticstuff.services.OnlinePresenceService;

import org.apache.log4j.Logger;

public abstract class ProfileCheckerThread extends Thread {

	public abstract long getTimeoutMilis();
	public abstract Mediator getProfileCheckerMediator();
	public abstract TwitterOnlinePresenceBuilder createOnlinePresenceBuilder();
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private OnlinePresence currentOnlinePresence;
	private Membership userMembership;
	private boolean wait = false;
	private boolean checking = false;
	
	protected TwitterOnlinePresenceBuilder onlinePresenceBulder = null;
	
	public void initialize(Membership userMembership) throws OPOSException {
		this.userMembership = userMembership;
		this.onlinePresenceBulder = createOnlinePresenceBuilder();
		
		// Retrieving OnlinePresence instance from the repository if exist
		logger.debug("Retrieving last OnlinePresence instance for username "
				+ userMembership.getUsername()+" on service "
				+ userMembership.getApplication().getName());
		OnlinePresenceService opService = new OnlinePresenceService();
		
		OnlinePresence lastOnlinePresence = null;
		try {
			lastOnlinePresence = opService.getLastOnlinePresence(userMembership);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		if (lastOnlinePresence != null) {
			logger.debug("OnlinePresence for username "+userMembership.getUsername()
					+ " on service "+userMembership.getApplication().getName()
					+ " exists in the repository and is loaded.");
			setCurrentOnlinePresence(lastOnlinePresence);
		}
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
	
	public Membership getUserMembership() {
		return userMembership;
	}
	
	public void setUserMembership(Membership userMembership) {
		this.userMembership = userMembership;
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
						logger.debug("First Online Presence instance for this account on "+getProfileCheckerMediator().getMediatorName()+" service.");
						try {
							new OnlinePresenceService().saveResource(newOnlinePresence, false);
						} catch (Exception e) {
							logger.error(e.getMessage());
						}
						currentOnlinePresence = newOnlinePresence;
					}
				} catch (OPOSException e) {
					logger.error(e.getMessage());
				}	
				
				try {
					setChecking(false);
					sleep(getTimeoutMilis());
				} catch (InterruptedException e) {
					logger.error(e.getMessage());
				}
			}
		}
	}

}

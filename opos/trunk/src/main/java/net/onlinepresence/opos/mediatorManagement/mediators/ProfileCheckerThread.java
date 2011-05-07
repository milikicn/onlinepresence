package net.onlinepresence.opos.mediatorManagement.mediators;

import net.onlinepresence.jopo.ontmodel.opo.OnlinePresence;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.exceptions.OPOSException;
import net.onlinepresence.opos.mediatorManagement.util.OnlinePresenceEquality;
import net.onlinepresence.opos.semanticstuff.services.OnlinePresenceService;

import org.apache.log4j.Logger;

public abstract class ProfileCheckerThread extends Thread {

	private Logger logger = Logger.getLogger(this.getClass());

	private long timeoutMillis;
	private OnlinePresence currentOnlinePresence;
	private Membership userMembership;
	private boolean wait = false;
	private boolean checking = false;

	protected OnlinePresenceBuilder onlinePresenceBulder = null;

	/**
	 * Returns the Mediator instance this ProfileCheckerThread is runinng
	 * within.
	 * 
	 * @return the Mediator instance this thread is part of
	 */
	public abstract Mediator getProfileCheckerMediator();

	/**
	 * Returns OnlinePresenceBuilder instance able to create OnlinePresence
	 * within the external service this thread's mediator is created for.
	 * 
	 * @return OnlinePresenceBuilder instance
	 */
	public abstract OnlinePresenceBuilder createOnlinePresenceBuilder();

	/**
	 * Construcor of this abstract class which just sets the number of
	 * miliseconds (stating the time interval this thread shoud run on).
	 * 
	 * @param timeoutMillis
	 *            number of miliseconds stating the tie interval this thread
	 *            should check for the OnlinePresence data change on the
	 *            application this treads serves for. This is usually read from
	 *            the configuration file and should be carefull when chosing
	 *            this number in order not to step over the limit of the number
	 *            of API calls for this application.
	 */
	public ProfileCheckerThread(long timeoutMillis) {
		this.timeoutMillis = timeoutMillis;
	}

	/**
	 * Should be called at the end the construcor in order to set user
	 * membership instance. In this method abstract method
	 * createOnlinePresenceBuilder() will be called in order to set the
	 * OnlinePresenceBuilder isntance needed for constructing the
	 * OnlinePresences form the apllication this class serves for.
	 * 
	 * @param userMembership
	 * @throws OPOSException
	 */
	public void initialize(Membership userMembership) throws OPOSException {
		this.userMembership = userMembership;
		this.onlinePresenceBulder = createOnlinePresenceBuilder();

		// Retrieving OnlinePresence instance from the repository if exist
		logger.debug("Retrieving last OnlinePresence instance for username "
				+ userMembership.getUsername() + " on service "
				+ userMembership.getApplication().getName());
		OnlinePresenceService opService = new OnlinePresenceService();

		OnlinePresence lastOnlinePresence = null;
		try {
			lastOnlinePresence = opService
					.getLastOnlinePresence(userMembership);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		if (lastOnlinePresence != null) {
			logger.debug("OnlinePresence for username "
					+ userMembership.getUsername() + " on service "
					+ userMembership.getApplication().getName()
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
	 * @param currentOnlinePresence
	 *            the currentOnlinePresence to set
	 */
	public void setCurrentOnlinePresence(OnlinePresence currentOnlinePresence) {
		this.currentOnlinePresence = currentOnlinePresence;
	}

	public Membership getUserMembership() {
		return userMembership;
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
	 * @param checking
	 *            the checking to set
	 */
	public void setChecking(boolean checking) {
		this.checking = checking;
	}

	@Override
	public void run() {
		logger.debug("Started checking of the "
				+ getProfileCheckerMediator().getMediatorName() + " profile.");

		while (true) {
			setChecking(true);
			if (!wait) {
				try {
					OnlinePresence newOnlinePresence = onlinePresenceBulder
							.build();

					if (currentOnlinePresence != null) {
						if (!OnlinePresenceEquality.equalOnlinePresenceData(
								currentOnlinePresence, newOnlinePresence)) {
							getProfileCheckerMediator()
									.propagateOnlinePresence(newOnlinePresence);
							currentOnlinePresence = newOnlinePresence;
						}
					} else {
						logger.debug("First Online Presence instance for this account on "
								+ getProfileCheckerMediator().getMediatorName()
								+ " service.");
						try {
							new OnlinePresenceService().saveResource(
									newOnlinePresence, false);
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
					sleep(timeoutMillis);
				} catch (InterruptedException e) {
					logger.error(e.getMessage());
				}
			}
		}
	}

}

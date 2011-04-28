package net.onlinepresence.opos.mediators.mediators.twitter.threads;

import twitter4j.Twitter;
import net.onlinepresence.opos.config.Settings;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.mediators.mediators.Mediator;
import net.onlinepresence.opos.mediators.mediators.ProfileCheckerThread;
import net.onlinepresence.opos.mediators.mediators.twitter.TwitterMediator;
import net.onlinepresence.opos.mediators.mediators.twitter.exceptions.OPOSException;

public class TwitterProfileCheckerThread extends ProfileCheckerThread {
	
	public TwitterProfileCheckerThread(Membership userMembership,
			Twitter twitter) throws OPOSException {
		super(userMembership, twitter);
	}

	@Override
	public long getTimeoutMilis() {
		return Settings.getInstance().config.twitterMediatorConfig.checkTimeout;
	}

	@Override
	public Mediator getProfileCheckerMediator() {
		return TwitterMediator.getInstance();
	}

}

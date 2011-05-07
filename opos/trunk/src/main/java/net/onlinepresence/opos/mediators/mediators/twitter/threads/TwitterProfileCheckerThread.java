package net.onlinepresence.opos.mediators.mediators.twitter.threads;

import twitter4j.Twitter;
import twitter4j.auth.AccessToken;
import net.onlinepresence.opos.config.Settings;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.mediators.mediators.Mediator;
import net.onlinepresence.opos.mediators.mediators.ProfileCheckerThread;
import net.onlinepresence.opos.mediators.mediators.twitter.TwitterCommunication;
import net.onlinepresence.opos.mediators.mediators.twitter.TwitterMediator;
import net.onlinepresence.opos.exceptions.OPOSException;
import net.onlinepresence.opos.mediators.mediators.twitter.service.builder.TwitterOnlinePresenceBuilder;
import net.onlinepresence.opos.mediators.mediators.twitter.util.Util;

public class TwitterProfileCheckerThread extends ProfileCheckerThread {
	
	private Twitter twitterCommunicator;
	
	public TwitterProfileCheckerThread(Membership userMembership) throws OPOSException {
		AccessToken accessToken = Util.loadTwitterAccessToken(userMembership);
		if (accessToken == null)
			throw new OPOSException("Error resembling Twitter sccess token.");
		
		twitterCommunicator = TwitterCommunication.getInstance().getTwitterFactory().getInstance(accessToken);
		
		super.initialize(userMembership);
	}


	@Override
	public TwitterOnlinePresenceBuilder createOnlinePresenceBuilder() {
		return new TwitterOnlinePresenceBuilder(twitterCommunicator, getUserMembership());
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

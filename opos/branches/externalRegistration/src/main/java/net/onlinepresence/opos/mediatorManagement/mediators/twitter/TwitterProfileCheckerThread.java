package net.onlinepresence.opos.mediatorManagement.mediators.twitter;

import twitter4j.Twitter;
import twitter4j.auth.AccessToken;
import net.onlinepresence.opos.config.Settings;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.mediatorManagement.mediators.Mediator;
import net.onlinepresence.opos.mediatorManagement.mediators.OnlinePresenceBuilder;
import net.onlinepresence.opos.mediatorManagement.mediators.ProfileCheckerThread;
import net.onlinepresence.opos.mediatorManagement.mediators.twitter.builder.TwitterOnlinePresenceBuilder;
import net.onlinepresence.opos.mediatorManagement.mediators.twitter.util.Util;
import net.onlinepresence.opos.exceptions.OPOSException;

public class TwitterProfileCheckerThread extends ProfileCheckerThread {
	
	private Twitter twitterCommunicator;
	
	public TwitterProfileCheckerThread(Membership userMembership) throws OPOSException {
		super(Settings.getInstance().config.twitterMediatorConfig.checkTimeout);
		
		AccessToken accessToken = Util.loadTwitterAccessToken(userMembership);
		if (accessToken == null)
			throw new OPOSException("Error resembling Twitter sccess token.");
		
		twitterCommunicator = TwitterCommunication.getInstance().getTwitterFactory().getInstance(accessToken);
		
		super.initialize(userMembership);
	}

	@Override
	public OnlinePresenceBuilder createOnlinePresenceBuilder() {
		return new TwitterOnlinePresenceBuilder(twitterCommunicator, getUserMembership());
	}

	@Override
	public Mediator getProfileCheckerMediator() {
		return TwitterMediator.getInstance();
	}
}

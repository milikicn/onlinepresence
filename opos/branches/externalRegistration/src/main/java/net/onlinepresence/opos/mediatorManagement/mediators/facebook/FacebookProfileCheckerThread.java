package net.onlinepresence.opos.mediatorManagement.mediators.facebook;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;

import net.onlinepresence.opos.config.Settings;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.mediatorManagement.mediators.Mediator;
import net.onlinepresence.opos.mediatorManagement.mediators.OnlinePresenceBuilder;
import net.onlinepresence.opos.mediatorManagement.mediators.ProfileCheckerThread;
import net.onlinepresence.opos.mediatorManagement.mediators.facebook.builder.FacebookOnlinePresenceBuilder;
import net.onlinepresence.opos.exceptions.OPOSException;

public class FacebookProfileCheckerThread extends ProfileCheckerThread {
	
	private FacebookClient facebookClient;
	
	public FacebookProfileCheckerThread(Membership userMembership) throws OPOSException {
		super(Settings.getInstance().config.facebookMediatorConfig.checkTimeout);
		
		facebookClient = new DefaultFacebookClient(userMembership.getAccessToken());
		
		super.initialize(userMembership);
	}

	@Override
	public OnlinePresenceBuilder createOnlinePresenceBuilder() {
		return new FacebookOnlinePresenceBuilder(getUserMembership(), facebookClient);
	}

	@Override
	public Mediator getProfileCheckerMediator() {
		return FacebookMediator.getInstance();
	}
}

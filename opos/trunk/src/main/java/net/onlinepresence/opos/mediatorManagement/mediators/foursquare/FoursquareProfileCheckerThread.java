package net.onlinepresence.opos.mediatorManagement.mediators.foursquare;

import foursquare4j.oauth.FoursquareOAuthImpl;
import foursquare4j.oauth.OAuthConsumer;
import net.onlinepresence.opos.config.Settings;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.mediatorManagement.mediators.Mediator;
import net.onlinepresence.opos.mediatorManagement.mediators.OnlinePresenceBuilder;
import net.onlinepresence.opos.mediatorManagement.mediators.ProfileCheckerThread;
import net.onlinepresence.opos.exceptions.OPOSException;

public class FoursquareProfileCheckerThread extends ProfileCheckerThread {
	
	private FoursquareOAuthImpl foursquareService;
	
	public FoursquareProfileCheckerThread(Membership userMembership) throws OPOSException {
		super(Settings.getInstance().config.foursquarekMediatorConfig.checkTimeout);
		
		OAuthConsumer o = new OAuthConsumer(
				Settings.getInstance().config.foursquarekMediatorConfig.apiKey,
				Settings.getInstance().config.foursquarekMediatorConfig.apiSecret);
		foursquareService = new FoursquareOAuthImpl(o);
		
		super.initialize(userMembership);
	}

	@Override
	public OnlinePresenceBuilder createOnlinePresenceBuilder() {
		return new FoursquareOnlinePresenceBuilder(getUserMembership(), foursquareService);
	}

	@Override
	public Mediator getProfileCheckerMediator() {
		return FoursquareMediator.getInstance();
	}
}

package net.onlinepresence.opos.mediatorManagement.mediators.foursquare;

import org.apache.log4j.Logger;

import foursquare4j.exception.FoursquareException;
import foursquare4j.oauth.FoursquareOAuthImpl;
import foursquare4j.type.User;

import net.onlinepresence.jopo.ontmodel.opo.OnlinePresence;
import net.onlinepresence.jopo.ontmodel.sioc.UserAccount;
import net.onlinepresence.jopo.services.spring.ResourceFactory;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.exceptions.OPOSException;
import net.onlinepresence.opos.mediatorManagement.mediators.OnlinePresenceBuilder;
import net.onlinepresence.opos.mediatorManagement.mediators.foursquare.model.FoursquareLastLocation;
import net.onlinepresence.opos.mediatorManagement.mediators.foursquare.model.FoursquareUserDetails;
import net.onlinepresence.opos.semanticstuff.services.OnlinePresenceService;

public class FoursquareOnlinePresenceBuilder implements OnlinePresenceBuilder {
	
	private Logger logger = Logger.getLogger(FoursquareOnlinePresenceBuilder.class);
	
	private Membership userMembership;
	private FoursquareOAuthImpl foursquareService;

	public FoursquareOnlinePresenceBuilder(Membership userMembership, FoursquareOAuthImpl foursquareService) {
		this.userMembership = userMembership;
		this.foursquareService = foursquareService;
	}

	public OnlinePresence build() throws OPOSException {
		logger.error("Building OnlinePresence instance on Foursquare for user "+userMembership.getUsername());

		try {
			foursquareService.authentication(
					"nikola.milikic@gmail.com", "nikolaopos");
			User u = foursquareService.user(null, null, null);
			FoursquareUserDetails ud = new FoursquareUserDetails();
			ud.setFirstName(u.getFirstname());
			ud.setLastName(u.getLastname());
			ud.setAvatarURL(u.getPhoto());
			ud.setLastLocation(new FoursquareLastLocation(u.getCheckin().getDisplay(), u
					.getCheckin().getCreated()));

			System.out.println(ud.toString());

		} catch (FoursquareException e) {
			e.printStackTrace();
		}
		
		ResourceFactory factory = new ResourceFactory();
		
		OnlinePresence foursquareOnlinePresence = (OnlinePresence) factory.createResource(OnlinePresence.class);
		
		//location
		
		//retrieving sioc:UserAccount instance
		OnlinePresenceService opService = new OnlinePresenceService();
		UserAccount userAccount = opService.getUserAccount(userMembership);
		foursquareOnlinePresence.setUserAccount(userAccount);
		

		return foursquareOnlinePresence;
	}

}

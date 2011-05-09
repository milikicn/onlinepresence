package net.onlinepresence.opos.mediatorManagement.mediators.foursquare;

import org.apache.log4j.Logger;

import foursquare4j.exception.FoursquareException;
import foursquare4j.oauth.FoursquareOAuthImpl;
import foursquare4j.type.User;

import net.onlinepresence.jopo.ontmodel.foaf.Person;
import net.onlinepresence.jopo.ontmodel.geo.SpatialThing;
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

		FoursquareUserDetails userDetails = null;
		try {
			User fsUser = foursquareService.user(null, null, null);
			userDetails = new FoursquareUserDetails();
			userDetails.setFirstName(fsUser.getFirstname());
			userDetails.setLastName(fsUser.getLastname());
			userDetails.setAvatarURL(fsUser.getPhoto());
			userDetails.setLastLocation(new FoursquareLastLocation(fsUser.getCheckin().getDisplay(), fsUser
					.getCheckin().getCreated(), fsUser.getCheckin().getVenue()
					.getGeolat(), fsUser.getCheckin().getVenue().getGeolong()));

			System.out.println(userDetails.toString());

		} catch (FoursquareException e) {
			logger.error(e.getMessage());
		}
		
		ResourceFactory factory = new ResourceFactory();
		
		OnlinePresence foursquareOnlinePresence = factory.createResource(OnlinePresence.class);
		
		//location
		// TODO: persist this
		SpatialThing geoLocation = factory.createResource(SpatialThing.class);
		geoLocation.setLatitude(userDetails.getLastLocation().getLatitude());
		geoLocation.setLongitude(userDetails.getLastLocation().getLongitude());
		foursquareOnlinePresence.setLocation(geoLocation);
		
		OnlinePresenceService opService = new OnlinePresenceService();
		
		// setting the agent
		Person agent = opService.getPersonInstance(userMembership.getUser());
		foursquareOnlinePresence.setAgent(agent);
		
		//retrieving sioc:UserAccount instance
		UserAccount userAccount = opService.getUserAccount(userMembership);
		foursquareOnlinePresence.setUserAccount(userAccount);
		

		return foursquareOnlinePresence;
	}

}

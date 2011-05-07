package net.onlinepresence.opos.mediatorManagement.mediators.twitter.builder;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;
import net.onlinepresence.jopo.ontmodel.foaf.Image;
import net.onlinepresence.jopo.ontmodel.foaf.Person;
import net.onlinepresence.jopo.ontmodel.geo.SpatialThing;
import net.onlinepresence.jopo.ontmodel.opo.OnlinePresence;
import net.onlinepresence.jopo.ontmodel.sioc.UserAccount;
import net.onlinepresence.jopo.services.spring.ResourceFactory;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.exceptions.OPOSException;
import net.onlinepresence.opos.mediatorManagement.mediators.OnlinePresenceBuilder;
import net.onlinepresence.opos.mediatorManagement.mediators.twitter.builder.wrappers.Twitter4jUserWrapper;
import net.onlinepresence.opos.mediatorManagement.mediators.twitter.util.Geo;
import net.onlinepresence.opos.semanticstuff.services.OnlinePresenceService;

public class TwitterOnlinePresenceBuilder implements OnlinePresenceBuilder {
	
	private Logger logger = Logger.getLogger(TwitterOnlinePresenceBuilder.class);
	
	private Twitter twitter;
	private Membership memb;

	/**
	 * @param twitterStuff
	 */
	public TwitterOnlinePresenceBuilder(Twitter twitter, Membership memb) {
		this.twitter = twitter;
		this.memb = memb;
	}

	public OnlinePresence build() throws OPOSException {
		logger.debug("Building Twitter OnlinePresence instance for the user "+memb.getUsername());
		
		User user;
		try {
			user = twitter.verifyCredentials();
		} catch (TwitterException e) {
			logger.error(e.getMessage());
			throw new OPOSException("Error retrieving Twitter User instance.");
		}
		
		Twitter4jUserWrapper userWrapper = new Twitter4jUserWrapper(user);
		
		ResourceFactory factory = new ResourceFactory();
	
		OnlinePresence twitterOnlinePresence = (OnlinePresence) factory.createResource(OnlinePresence.class);

		//creating SpatialThing instance representing users location
		Geo userLocation = userWrapper.getUserLocation();
		if(userLocation != null){
			SpatialThing location = (SpatialThing) factory.createResource(SpatialThing.class);
			location.setUri(userLocation.getGeonamesUrl().toString());
			logger.debug("TwitterOnlinePresenceBuilder: location.setURI " + location.getUri());
			
			twitterOnlinePresence.setLocation(location);
		}
		
		//creating Agent instance
		Person agent = (Person) factory.createResource(Person.class);
		agent.setName(userWrapper.getName());
		logger.debug("TwitterOnlinePresenceBuilder: agent.setName " + agent.getName());
		if(userWrapper.getURL() != null){
			agent.setHomepage(userWrapper.getURL().toString());
			logger.debug("TwitterOnlinePresenceBuilder: agent.setHomepage " + agent.getHomepage());
		}
//		if(userWrapper.getMbox() != null){
//			agent.setHomepage(userWrapper.getURL().toString());
//			System.out.println("TwitterOnlinePresenceBuilder: agent.setHomepage " + agent.getHomepage());
//		}
		
		// TODO: how to know whether should I update the avatar of the user if it is attached 
		// directly to the foaf:Person instance when every application gives us new avatar URL?
		if(userWrapper.getProfileImageURL() != null){
			Image image = (Image) factory.createResource(Image.class);
			image.setUri(userWrapper.getProfileImageURL().toString());
			logger.debug("TwitterOnlinePresenceBuilder: image.setURI " + image.getUri());
			
			agent.setImg(image);
		}
		twitterOnlinePresence.setAgent(agent);
		
		//retrieving sioc:UserAccount instance
		OnlinePresenceService opService = new OnlinePresenceService();
		UserAccount userAccount = opService.getUserAccount(memb);
		twitterOnlinePresence.setUserAccount(userAccount);
		
		//creating status instance
		// TODO: should be persisted
		TwitterOnlineStatusBuilder statusBuilder = new TwitterOnlineStatusBuilder(twitter, user);
		twitterOnlinePresence.setStatusMessage(statusBuilder.buildStatus());
		
		twitterOnlinePresence.setStartTime(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date()));
		
		return twitterOnlinePresence;
	}

}

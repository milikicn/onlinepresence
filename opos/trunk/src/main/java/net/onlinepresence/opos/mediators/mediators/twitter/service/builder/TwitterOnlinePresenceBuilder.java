package net.onlinepresence.opos.mediators.mediators.twitter.service.builder;

import java.net.URI;

import org.apache.log4j.Logger;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;
import net.onlinepresence.ontmodel.foaf.Image;
import net.onlinepresence.ontmodel.foaf.Person;
import net.onlinepresence.ontmodel.geo.SpatialThing;
import net.onlinepresence.ontmodel.opo.OnlinePresence;
import net.onlinepresence.ontmodel.sioc.UserAccount;
import net.onlinepresence.opos.mediators.mediators.twitter.exceptions.OPOSException;
import net.onlinepresence.opos.mediators.mediators.twitter.service.builder.wrappers.Twitter4jUserWrapper;
import net.onlinepresence.opos.mediators.mediators.twitter.util.Geo;
import net.onlinepresence.services.spring.ResourceFactory;

public class TwitterOnlinePresenceBuilder {
	
	private Logger logger = Logger.getLogger(TwitterOnlinePresenceBuilder.class);
	
	private Twitter twitter;

	/**
	 * @param twitterStuff
	 */
	public TwitterOnlinePresenceBuilder(Twitter twitter) {
		this.twitter = twitter;
	}

	public OnlinePresence build() throws OPOSException {
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
		
		if(userWrapper.getProfileImageURL() != null){
			Image image = (Image) factory.createResource(Image.class);
			image.setUri(userWrapper.getProfileImageURL().toString());
			logger.debug("TwitterOnlinePresenceBuilder: image.setURI " + image.getUri());
			
			agent.setImg(image);
		}
		twitterOnlinePresence.setAgent(agent);
		
		//creating SIOC UserAccount
		UserAccount userAccount = (UserAccount) factory.createResource(UserAccount.class);
		userAccount.setAccountName(userWrapper.getScreenName());
		logger.debug("TwitterOnlinePresenceBuilder: userAccount.setAccountName " + userAccount.getAccountName());
		userAccount.setAccountServiceHomepage(URI.create("http://www.twitter.com"));
		logger.debug("TwitterOnlinePresenceBuilder: userAccount.setAccountServiceHomepage " + userAccount.getAccountServiceHomepage());
		twitterOnlinePresence.setUserAccount(userAccount);
		
		//creating status instance
		TwitterOnlineStatusBuilder statusBuilder = new TwitterOnlineStatusBuilder(twitter, user);
		twitterOnlinePresence.setStatusMessage(statusBuilder.buildStatus());

		return twitterOnlinePresence;
	}

}

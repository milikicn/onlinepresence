package net.onlinepresence.opos.dataManager.mediators.twitter.service.builder;

import java.net.URI;

import twitter4j.User;
import net.onlinepresence.ontmodel.foaf.Image;
import net.onlinepresence.ontmodel.foaf.Person;
import net.onlinepresence.ontmodel.geo.SpatialThing;
import net.onlinepresence.ontmodel.opo.OnlinePresence;
import net.onlinepresence.ontmodel.sioc.UserAccount;
import net.onlinepresence.opos.dataManager.mediators.twitter.exceptions.TwitterOPOSException;
import net.onlinepresence.opos.dataManager.mediators.twitter.service.builder.wrappers.Twitter4jUserWrapper;
import net.onlinepresence.opos.dataManager.mediators.twitter.util.Geo;
import net.onlinepresence.opos.dataManager.mediators.twitter.util.TwitterStuff;
import net.onlinepresence.services.spring.ResourceFactory;

public class TwitterOnlinePresenceBuilder {
	
	private TwitterStuff twitterStuff;

	/**
	 * @param twitterStuff
	 */
	public TwitterOnlinePresenceBuilder(TwitterStuff twitterStuff) {
		this.twitterStuff = twitterStuff;
	}

	public OnlinePresence build() throws TwitterOPOSException {
		User user = twitterStuff.getTwitterUser();
		
		Twitter4jUserWrapper userWrapper = new Twitter4jUserWrapper(user);
		
		ResourceFactory factory = new ResourceFactory();
	
		OnlinePresence twitterOnlinePresence = (OnlinePresence) factory.createResource(OnlinePresence.class);

		//creating SpatialThing instance representing users location
		Geo userLocation = userWrapper.getUserLocation();
		if(userLocation != null){
			SpatialThing location = (SpatialThing) factory.createResource(SpatialThing.class);
			location.setUri(userLocation.getGeonamesUrl().toString());
			System.out.println("TwitterOnlinePresenceBuilder: location.setURI " + location.getUri());
			
			twitterOnlinePresence.setLocation(location);
		}
		
		//creating Agent instance
		Person agent = (Person) factory.createResource(Person.class);
		agent.setName(userWrapper.getName());
		System.out.println("TwitterOnlinePresenceBuilder: agent.setName " + agent.getName());
		if(userWrapper.getURL() != null){
			agent.setHomepage(userWrapper.getURL().toString());
			System.out.println("TwitterOnlinePresenceBuilder: agent.setHomepage " + agent.getHomepage());
		}
//		if(userWrapper.getMbox() != null){
//			agent.setHomepage(userWrapper.getURL().toString());
//			System.out.println("TwitterOnlinePresenceBuilder: agent.setHomepage " + agent.getHomepage());
//		}
		
		if(userWrapper.getProfileImageURL() != null){
			Image image = (Image) factory.createResource(Image.class);
			image.setUri(userWrapper.getProfileImageURL().toString());
			System.out.println("TwitterOnlinePresenceBuilder: image.setURI " + image.getUri());
			
			agent.setImg(image);
		}
		twitterOnlinePresence.setAgent(agent);
		
		//creating SIOC UserAccount
		UserAccount userAccount = (UserAccount) factory.createResource(UserAccount.class);
		userAccount.setAccountName(userWrapper.getScreenName());
		System.out.println("TwitterOnlinePresenceBuilder: userAccount.setAccountName " + userAccount.getAccountName());
		userAccount.setAccountServiceHomepage(URI.create("http://www.twitter.com"));
		System.out.println("TwitterOnlinePresenceBuilder: userAccount.setAccountServiceHomepage " + userAccount.getAccountServiceHomepage());
		twitterOnlinePresence.setUserAccount(userAccount);
		
		//creating status instance
		TwitterOnlineStatusBuilder statusBuilder = new TwitterOnlineStatusBuilder(twitterStuff);
		twitterOnlinePresence.setStatusMessage(statusBuilder.buildStatus());

		return twitterOnlinePresence;
	}

}

package org.goodoldai.demo.twitt2opo.converter.util;

import org.goodoldai.demo.twitt2opo.converter.model.TwitterUser;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

public class TwitterUserBuilder {

	private Twitter twitter;
	private User user;
	
	public TwitterUserBuilder(Twitter twitter, User user){
		this.twitter = twitter;
		this.user = user;
	}
	
	public TwitterUser buildUser() throws TwitterException {
		
		Twitter4jUserWrapper userWrapper = new Twitter4jUserWrapper(user);
		
		TwitterUser twitterUser = new TwitterUser();
		
		twitterUser.setId(userWrapper.getId());		
		twitterUser.setLocationUrl(userWrapper.getUserLocation());		
		twitterUser.setName(userWrapper.getName());		
		twitterUser.setTwitterAccountName(userWrapper.getScreenName());		
		twitterUser.setImgUrl(userWrapper.getProfileImageURL());		
		twitterUser.setHomepage(userWrapper.getURL());
		
		TwitterStatusBuilder statusBuider = new TwitterStatusBuilder(twitter, user);
		
		twitterUser.setCurrentStatus(statusBuider.buildStatus());

		return twitterUser;
	}

}

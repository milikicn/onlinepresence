package net.onlinepresence.opos.mediators.mediators.twitter;

import net.onlinepresence.opos.config.Settings;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterCommunication {

	private TwitterFactory twitterFactory;
	
	private static TwitterCommunication INSTANCE;
	
	private TwitterCommunication() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		Configuration conf = cb.
			setOAuthConsumerKey(Settings.getInstance().config.twitterMediatorConfig.apiKey).
			setOAuthConsumerSecret(Settings.getInstance().config.twitterMediatorConfig.apiSecret).
			build();
		twitterFactory = new TwitterFactory(conf);
	}
	
	public static TwitterCommunication getInstance() {
		if (INSTANCE == null)
			INSTANCE = new TwitterCommunication();
		
		return INSTANCE;
	}

	public TwitterFactory getTwitterFactory() {
		return twitterFactory;
	}

	public void setTwitterFactory(TwitterFactory twitterFactory) {
		this.twitterFactory = twitterFactory;
	}

}

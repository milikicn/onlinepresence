package net.onlinepresence.opos.mediatorManagement.mediators.twitter;

import net.onlinepresence.opos.config.Settings;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterCommunication {

	private TwitterFactory twitterFactory;
	
	private static TwitterCommunication INSTANCE;
	
	private TwitterCommunication() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb = cb.
			setOAuthConsumerKey(Settings.getInstance().config.twitterMediatorConfig.apiKey).
			setOAuthConsumerSecret(Settings.getInstance().config.twitterMediatorConfig.apiSecret);
		
		String proxyUrl = Settings.getInstance().config.proxySettingsConfig.hostUrl;
		if (proxyUrl != null && proxyUrl.length() > 0) {
			cb = cb.setHttpProxyHost(proxyUrl);
		}
		
		int proxyPort = Settings.getInstance().config.proxySettingsConfig.port;
		if (proxyPort > 0) {
			cb = cb.setHttpProxyPort(proxyPort);
		}
		
		String proxyUsername = Settings.getInstance().config.proxySettingsConfig.username;
		if (proxyUsername != null && proxyUsername.length() > 0) {
			cb = cb.setHttpProxyUser(proxyUsername);
		}
		
		String proxyPassword = Settings.getInstance().config.proxySettingsConfig.password;
		if (proxyPassword != null && proxyPassword.length() > 0) {
			cb = cb.setHttpProxyPassword(proxyPassword);
		}
			
		Configuration conf = cb.build();
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

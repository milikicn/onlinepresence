package org.goodoldai.demo.twitt2opo.converter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.goodoldai.demo.twitt2opo.converter.exceptions.Twitt2opoException;
import org.goodoldai.demo.twitt2opo.converter.exporter.Exporter;
import org.goodoldai.demo.twitt2opo.converter.model.TwitterUser;
import org.goodoldai.demo.twitt2opo.converter.util.FileWriter;
import org.goodoldai.demo.twitt2opo.converter.util.Service;
import org.goodoldai.demo.twitt2opo.converter.util.TwitterUserBuilder;
import org.goodoldai.demo.twitt2opo.pages.Redirection;

import com.hp.hpl.jena.rdf.model.Model;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.http.AccessToken;
import twitter4j.http.RequestToken;

public class Converter {

	private Twitter twitter = null;
	private User user = null;
	private RequestToken requestToken = null;
	private AccessToken accessToken = null;
	private TwitterUser twitterUser = null;
	private Properties prop;
	private String absoluteUrl;
	private String screenName;
	private boolean userIsPrivate;
	private boolean userIsPrivateChecked;
	private boolean userExists;
	private boolean userExistsChecked;

	public Converter() throws Twitt2opoException {
		TwitterFactory twf = new TwitterFactory();
		twitter = twf.getInstance();
		this.prop = loadPropertyFile("parameters.properties");

		initializeApp();
	}

	private void initializeApp() throws Twitt2opoException {
		Properties appProp = loadPropertyFile("app-parameters.properties");
		twitter.setOAuthConsumer(appProp.getProperty("consumerKey"), appProp
				.getProperty("consumerSecret"));
	}

	public String getAbsoluteUrl() {
		return absoluteUrl;
	}

	public RequestToken getRequestToken() {
		return requestToken;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getOAuthAuthorizationURL() throws Twitt2opoException {
		try {
			requestToken = twitter.getOAuthRequestToken();
		} catch (TwitterException e) {
			throw new Twitt2opoException("There was an error in making connection to the Twitter API. Please try again later.");
		}

		return requestToken.getAuthorizationURL();
	}

	public Object checkIfProtectedAndReturnUrl() throws Twitt2opoException {
		if (userIsPrivate()) {
			String oAuthUrlString = null;

			oAuthUrlString = getOAuthAuthorizationURL();

			URL oAuthUrl = null;
			try {
				oAuthUrl = new URL(oAuthUrlString);
			} catch (MalformedURLException e) {
				throw new Twitt2opoException("There was an error in forming the address for Twitter oAuth authentication. Please try again.");
			}
				return oAuthUrl;
		} else
			return Redirection.class;
	}

	public void convert() throws Twitt2opoException {

		if(userIsPrivate){
			getAccessToken();
			try {
				user = twitter.verifyCredentials();
			} catch (TwitterException e) {
				throw new Twitt2opoException("There was an error in making connection to the Twitter API. Please try again later.");
			}
		}
		
		Service service = new Service(user.getScreenName(), prop);

		TwitterUserBuilder userBuilder = new TwitterUserBuilder(twitter, user);

		try {
			twitterUser = userBuilder.buildUser();
		} catch (TwitterException e1) {
			throw new Twitt2opoException("There was an error in making connection to the Twitter API. Please try again later.");
		}

		Model model = new Exporter(twitterUser, service.getServerUrl(), service.getTime())
				.export2OPO();

		FileWriter writer = new FileWriter(model);

		try {
			writer.write(service.getSavingPath());
		} catch (FileNotFoundException e) {
			throw new Twitt2opoException("There was an error in writing to the file on the server. Please try again.");
		} catch (IOException e) {
			throw new Twitt2opoException("There was an error in writing to the file on the server. Please try again.");
		}
		absoluteUrl = service.getServerUrl();
	}

	@SuppressWarnings("deprecation")
	private void getAccessToken() throws Twitt2opoException {
		try {
			accessToken = twitter.getOAuthAccessToken();

			twitter.setOAuthAccessToken(accessToken);
		} catch (TwitterException te) {
			throw new Twitt2opoException("There was an error in making connection to the Twitter API. Please try again later.");
		}
	}

	private Properties loadPropertyFile(String propertyName) throws Twitt2opoException {
		InputStream stream = null;
		Properties prop = new Properties();
		String propertyFile = propertyName;

		stream = this.getClass().getResourceAsStream(propertyFile);

		try {
			prop.load(stream);
		} catch (IOException e) {
			throw new Twitt2opoException("An error occured on the server. Please try again.");
		}
		return prop;
	}

	public boolean userExists() throws Twitt2opoException {
		if(!userExistsChecked){
			try {
				user = twitter.showUser(screenName);
			} catch (TwitterException e) {
				String errorMessage = "";
				
				if(e.getStatusCode() == 404)
					errorMessage = "The user you have requested the conversion for doesn't exist. Please check the profile name and try again.";
				else
					errorMessage = "There was an error in making connection to the Twitter API. Please try again later.";
				throw new Twitt2opoException(errorMessage);
			}
			userExists = user.getId() > 0;
		}
		return userExists;
	}

	public boolean userIsPrivate() throws Twitt2opoException {
		if(!userIsPrivateChecked){
			try {
				user = twitter.showUser(screenName);
			} catch (TwitterException e) {
				throw new Twitt2opoException("There was an error in making connection to the Twitter API. Please try again later.");
			}
			userIsPrivate = user.isProtected();
		}
		return userIsPrivate;
	}
	
	public boolean checkIfRateLimitExceeded() throws Twitt2opoException{
		int remainingHits;
		try {
			remainingHits = twitter.getRateLimitStatus().getRemainingHits();
		} catch (TwitterException e) {
			throw new Twitt2opoException("There was an error in making connection to the Twitter API. Please try again later.");
		}
		
		if(remainingHits > 0)
			return false;
		
		return true;
	}
}

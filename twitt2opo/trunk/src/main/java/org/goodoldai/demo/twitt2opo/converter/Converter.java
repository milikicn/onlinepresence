package org.goodoldai.demo.twitt2opo.converter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import org.goodoldai.demo.twitt2opo.converter.exporter.Exporter;
import org.goodoldai.demo.twitt2opo.converter.model.TwitterUser;
import org.goodoldai.demo.twitt2opo.converter.util.FileWriter;
import org.goodoldai.demo.twitt2opo.converter.util.Service;
import org.goodoldai.demo.twitt2opo.converter.util.TwitterUserBuilder;

import com.hp.hpl.jena.rdf.model.Model;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.http.AccessToken;
import twitter4j.http.RequestToken;

public class Converter {

	private Twitter twitter = null;
	private RequestToken requestToken = null;
	private AccessToken accessToken = null;
	private String pin = null;
	private TwitterUser twitterUser = null;
	private Properties prop;
	private String absoluteUrl;

	public Converter() {
		TwitterFactory twf = new TwitterFactory();
		twitter = twf.getInstance();
		this.prop = loadPropertyFile("parameters.properties");

		try {
			initializeApp();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

	private void initializeApp() throws TwitterException {
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

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getOAuthAuthorizationURL() {
		try {
			requestToken = twitter.getOAuthRequestToken();
		} catch (TwitterException e1) {
			e1.printStackTrace();
		}

		return requestToken.getAuthorizationURL();
	}

	public void convert() {
		Service service = new Service();
		getAccessToken();

		try {
			twitterUser = TwitterUserBuilder.buildUser(twitter);
		} catch (TwitterException e1) {
			e1.printStackTrace();
		}

		User user = null;
		try {
			user = twitter.verifyCredentials();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File("/var/lib/tomcat6/webapps/twitt2opo/tmp/user.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pw.write(user.getScreenName());
		pw.close();

		// persist to the accessToken for future reference.
		// storeAccessToken(user.getId(), accessToken);

		absoluteUrl = service.constructBaseUrl(user.getScreenName(), prop);

		Model model = null;
		try {
			model = new Exporter(twitterUser, absoluteUrl, service.getTime())
					.export2OPO(twitterUser);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		FileWriter writer = new FileWriter(model);
		try {
			String savingPath = service.constructSavingPath(user.getScreenName(), prop);
			writer.write(savingPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getAccessToken() {
		try {
			if (pin != null && pin.length() > 0) {
				accessToken = twitter.getOAuthAccessToken(requestToken, pin);
			} else {
				accessToken = twitter.getOAuthAccessToken();
			}
			
			PrintWriter pw = null;
			try {
				pw = new PrintWriter(new File("/var/lib/tomcat6/webapps/twitt2opo/tmp/token.txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pw.write(accessToken.getToken());
			pw.close();

			twitter.setOAuthAccessToken(accessToken);
		} catch (TwitterException te) {
			if (401 == te.getStatusCode()) {
				System.out.println("Unable to get the access token.");
			} else {
				te.printStackTrace();
			}
		}
	}

//	private void storeAccessToken(int useId, AccessToken at) {
//		// store at.getToken()
//		// store at.getTokenSecret()
//	}

	private Properties loadPropertyFile(String propertyName) {
		InputStream stream = null;
		Properties prop = new Properties();
		String propertyFile = propertyName;

		stream = this.getClass().getResourceAsStream(propertyFile);

		try {
			prop.load(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
}

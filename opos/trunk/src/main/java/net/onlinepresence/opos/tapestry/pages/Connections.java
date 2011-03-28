package net.onlinepresence.opos.tapestry.pages;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.Set;

import net.onlinepresence.opos.core.spring.SpringBean;
import net.onlinepresence.opos.domain.Application;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.domain.beans.LoggedUserBean;
import net.onlinepresence.opos.domain.beans.MembershipBean;
import net.onlinepresence.opos.domain.service.Applications;
import net.onlinepresence.opos.domain.service.Users;
import net.onlinepresence.opos.util.Util;

import org.apache.tapestry5.annotations.IncludeJavaScriptLibrary;
import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

@IncludeStylesheet( { 
//	"context:css/style.css", 
//	"context:css/front.css",
	"context:css/jquery.fancybox-1.3.0.css" })
@IncludeJavaScriptLibrary( { 
	"context:js/jquery.min.js", 
	"context:js/login.js",
	"context:js/jquery.fancybox-1.3.0.js",
	"context:js/jquery.fancybox-1.3.0.activation.js" })
public class Connections {

	@Inject @Property
	@SpringBean("net.onlinepresence.opos.domain.service.Applications")
	private Applications applications;

	@Inject
	@SpringBean("net.onlinepresence.opos.domain.service.Users")
	private Users users;

	@Property @SuppressWarnings("unused")
	private Application currentApplication;

	@SessionState
	private LoggedUserBean loggedUser;
	private boolean loggedUserExists;
	
	@SuppressWarnings("unused")
	@SessionState
	private Twitter twitter;

	@SuppressWarnings("unused")
	@Property
	private String twitterUsername;

	@SuppressWarnings("unused")
	@Property
	private String twitterPassword;

	@Property
	private String sparkUsername;

	@SuppressWarnings("unused")
	@Property
	private boolean twitterSendTo;

	@SuppressWarnings("unused")
	@Property
	private boolean twitterReceiveFrom;

	@Property
	private boolean sparkSendTo;

	@Property
	private boolean sparkReceiveFrom;

	@Property @SuppressWarnings("unused")
	private boolean twitterExists;

	@Property @SuppressWarnings("unused")
	private boolean sparkExists;

	Object onActivate() {
		if (!loggedUserExists)
			return Login.class;
		loadMembershipInformation();
		
		return null;
	}

	URL onSubmitFromTwitterForm() {
		Twitter twitter = new TwitterFactory().getInstance();
		Properties appProp = Util.loadPropertyFile("/mediator_params.properties");
		System.out.println(appProp);
	    twitter.setOAuthConsumer(appProp.getProperty("twitter-app-API-key"), appProp.getProperty("twitter-app-API-secret"));
	    RequestToken requestToken;
		try {
			requestToken = twitter.getOAuthRequestToken();
			String autorizationUrl = requestToken.getAuthorizationURL();
			System.out.println("////////autorizationUrl: "+autorizationUrl);
			try {
				URL authorizationUrl = new URL(autorizationUrl);
				this.twitter= twitter;
				return authorizationUrl;
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return null;
	}

	Object onSubmitFromSparkForm() {
		Membership memb = new MembershipBean(
				applications
						.getApplication("http://www.igniterealtime.org/projects/spark/"),
				loggedUser.getUser(), sparkUsername, null, sparkSendTo,
				sparkReceiveFrom, null, null);
		
		return submitForm(memb);
	}

	public Object submitForm(Membership memb) {
		if (!loggedUser.getUser().hasMembership(memb)) {
			loggedUser.getUser().addApplicationMembership(memb);
			users.update(loggedUser.getUser());
		} else {
			loggedUser.getUser().updateMembership(memb);
			users.update(loggedUser.getUser());
		}
		
		return Connections.class;
	}

	private void loadMembershipInformation() {
		
		Set<Membership> memberships = (Set<Membership>) loggedUser.getUser()
				.getApplicationMemberships();
		
		for (Membership mem : memberships) {
			String appName = mem.getApplication().getName();
			try {
				Connections.class.getMethod("set" + appName + "Exists",
						boolean.class).invoke(this, true);
				Connections.class.getMethod("set" + appName + "Username",
						String.class).invoke(this, mem.getUsername());
				Connections.class.getMethod("set" + appName + "SendTo",
						boolean.class).invoke(this, mem.isSendTo());
				Connections.class.getMethod("set" + appName + "ReceiveFrom",
						boolean.class).invoke(this, mem.isReceiveFrom());
				// for the password fields that do not exists an exception will
				// be thrown
				Connections.class.getMethod("set" + appName + "Password",
						String.class).invoke(this, mem.getPassword());
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
	}

	@OnEvent(component = "deleteTwitter")
	void deleteTwitter() {
		Membership m = loggedUser.getUser().deleteApplicationMembership(
				"http://www.twitter.com");
		users.deleteApplicationMemberhsip(m);
	}
	
//	@OnEvent(component = "signUpTwitter")
//	URL onSubmitFromTwitterForm() {
//		Twitter twitter = new TwitterFactory().getInstance();
//		Properties appProp = Util.loadPropertyFile("/mediator_params.properties");
//		System.out.println(appProp);
//	    twitter.setOAuthConsumer(appProp.getProperty("twitter-app-API-key"), appProp.getProperty("twitter-app-API-secret"));
//	    RequestToken requestToken;
//		try {
//			requestToken = twitter.getOAuthRequestToken();
//			String autorizationUrl = requestToken.getAuthorizationURL();
//			System.out.println("////////autorizationUrl: "+autorizationUrl);
//			try {
//				URL authorizationUrl = new URL(autorizationUrl);
//				this.twitter= twitter;
//				return authorizationUrl;
//			} catch (MalformedURLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} catch (TwitterException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

	@OnEvent(component = "deleteSpark")
	void deleteSpark() {
		Membership m = loggedUser.getUser().deleteApplicationMembership(
				"http://www.igniterealtime.org/projects/spark/");
		users.deleteApplicationMemberhsip(m);
	}

}

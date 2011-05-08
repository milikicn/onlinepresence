package net.onlinepresence.opos.tapestry.pages;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import net.onlinepresence.opos.config.Permission;
import net.onlinepresence.opos.config.Settings;
import net.onlinepresence.opos.core.spring.SpringBean;
import net.onlinepresence.opos.domain.Application;
import net.onlinepresence.opos.domain.ApplicationNames;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.domain.beans.LoggedUserBean;
import net.onlinepresence.opos.domain.beans.MembershipBean;
import net.onlinepresence.opos.domain.service.ApplicationManager;
import net.onlinepresence.opos.domain.service.UserManager;
import net.onlinepresence.opos.exceptions.OPOSException;
import net.onlinepresence.opos.mediatorManagement.MediatorManager;
import net.onlinepresence.opos.mediatorManagement.mediators.foursquare.FoursquareMediator;
import net.onlinepresence.opos.mediatorManagement.mediators.twitter.TwitterCommunication;
import net.onlinepresence.opos.tapestry.appconfig.UserAppSettings;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.RequestToken;

@Import(library= { 
	"context:js/jquery.min.js", 
	"context:js/login.js",
	"context:js/jquery.fancybox-1.3.0.js",
	"context:js/jquery.fancybox-1.3.0.activation.js" },
	stylesheet= { 
//		"context:css/style.css", 
//		"context:css/front.css",
		"context:css/jquery.fancybox-1.3.0.css" })
public class Connections {

	@Inject @Property
	@SpringBean("net.onlinepresence.opos.domain.service.ApplicationManager")
	private ApplicationManager applications;

	@Inject
	@SpringBean("net.onlinepresence.opos.domain.service.UserManager")
	private UserManager userManager;

	@Property @SuppressWarnings("unused")
	private Application currentApplication;

	@SessionState
	private LoggedUserBean loggedUser;
	private boolean loggedUserExists;

	Object onActivate() {
		if (!loggedUserExists)
			return Login.class;
		
		// refreshing Hibernate session
		loggedUser.setUser(userManager.findUser(loggedUser.getUser().getUsername()));
		
		twitterAppSettings = new UserAppSettings(ApplicationNames.TWITTER);
		facebookAppSettings = new UserAppSettings(ApplicationNames.FACEBOOK);
		sparkAppSettings = new UserAppSettings(ApplicationNames.SPARK);
		foursquareAppSettings = new UserAppSettings(ApplicationNames.FOURSQUARE);
		
		loadMembershipInformation();
		
		return null;
	}

	private void loadMembershipInformation() {
		
		Set<Membership> memberships = (Set<Membership>) loggedUser.getUser().getApplicationMemberships();
		
		for (Membership mem : memberships) {
			ApplicationNames appName = mem.getApplication().getName();
			
			switch (appName) {
			case TWITTER:
				configureUserAppSettings(twitterAppSettings, mem);
				break;
			case FACEBOOK:
				configureUserAppSettings(facebookAppSettings, mem);
				break;
			case SPARK:
				configureUserAppSettings(sparkAppSettings, mem);
				break;
			case FOURSQUARE:
				configureUserAppSettings(foursquareAppSettings, mem);
				break;
			}
		}
	}
	
	private void configureUserAppSettings(UserAppSettings appSettings, Membership mem) {
		appSettings.setUserUssesApp(true);
		appSettings.setSendDataToApp(mem.isSendTo());
		appSettings.setReceiveDataFromApp(mem.isReceiveFrom());
		appSettings.setUsername(mem.getUsername());
		appSettings.setPassword(mem.getPassword());
	}

	//Twitter
	@SessionState
	private Twitter twitter;
	
	@Property
	private UserAppSettings twitterAppSettings;
	
	@OnEvent(component = "twitterForm")
	URL onSubmitFromTwitterForm() {
		twitter = TwitterCommunication.getInstance().getTwitterFactory().getInstance();
	    RequestToken requestToken;
		try {
			requestToken = twitter.getOAuthRequestToken();
			String autorizationUrl = requestToken.getAuthorizationURL();
			try {
				URL authorizationUrl = new URL(autorizationUrl);
				return authorizationUrl;
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return null;
	}

	@OnEvent(component = "deleteTwitter")
	void deleteTwitter() {
		Membership m = loggedUser.getUser().deleteApplicationMembership(ApplicationNames.TWITTER);
		userManager.deleteApplicationMemberhsip(m);
	}
	
	//Facebook
	@Property
	private UserAppSettings facebookAppSettings;
	
	@OnEvent(component = "facebookForm")
	URL onSubmitFromFacebookForm() {
		StringBuffer autorizationUrlBuffer = new StringBuffer();
		autorizationUrlBuffer.append("https://www.facebook.com/dialog/oauth?client_id=");
		autorizationUrlBuffer.append(Settings.getInstance().config.facebookMediatorConfig.applicationId);
		autorizationUrlBuffer.append("&redirect_uri=");
		autorizationUrlBuffer.append(Settings.getInstance().config.facebookMediatorConfig.redirectUrl);
//		autorizationUrlBuffer.append("&response_type=token");
		autorizationUrlBuffer.append("&scope=");
		
		for (Permission permission : Settings.getInstance().config.facebookMediatorConfig.permissions) {
			autorizationUrlBuffer.append(permission.value+",");
		}
		
		//https://www.facebook.com/dialog/oauth?client_id=22915582764&redirect_uri=http://localhost:8080/&scope=read_stream,user_online_presence,user_location,offline_access&response_type=token
		try {
			URL authorizationUrl = new URL(autorizationUrlBuffer.toString());
			System.out.println("/////////////Facebook authorizationUrl: "+authorizationUrl);
			return authorizationUrl;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@OnEvent(component = "deleteFacebook")
	void deleteFacebook() {
		Membership m = loggedUser.getUser().deleteApplicationMembership(ApplicationNames.FACEBOOK);
		userManager.deleteApplicationMemberhsip(m);
	}
	
	// Spark
	@Property
	private UserAppSettings sparkAppSettings;

	Object onSubmitFromSparkForm() {
		// refreshing Hibernate session
		loggedUser.setUser(userManager.findUser(loggedUser.getUser().getUsername()));
		Application sparkApp = applications.getApplication(ApplicationNames.SPARK);
		
		Membership memb = new MembershipBean(
				sparkApp,
				loggedUser.getUser(), sparkAppSettings.getUsername(), null, sparkAppSettings.isSendDataToApp(),
				sparkAppSettings.isReceiveDataFromApp(), null, null);
		
		userManager.createOrUpdateNewMembership(loggedUser.getUser(), memb);
		
		return Connections.class;
	}
	
	@OnEvent(component = "deleteSpark")
	void deleteSpark() {
		Membership m = loggedUser.getUser().deleteApplicationMembership(ApplicationNames.SPARK);
		userManager.deleteApplicationMemberhsip(m);
	}
	
	// Foursquare
	@Property
	private UserAppSettings foursquareAppSettings;
	
	@OnEvent(component = "foursquareForm")
	Object onSubmitFromFoursquareForm() {
		// refreshing Hibernate session
		loggedUser.setUser(userManager.findUser(loggedUser.getUser().getUsername()));
		Application foursquareApp = applications.getApplication(ApplicationNames.FOURSQUARE);
		
		Membership memb = new MembershipBean(
				foursquareApp,
				loggedUser.getUser(), foursquareAppSettings.getUsername(), foursquareAppSettings.getPassword(), foursquareAppSettings.isSendDataToApp(),
				foursquareAppSettings.isReceiveDataFromApp(), null, null);
		
		userManager.createOrUpdateNewMembership(loggedUser.getUser(), memb);
		
		FoursquareMediator foursquareMediator = (FoursquareMediator) MediatorManager.getInstance().getMediator(ApplicationNames.FOURSQUARE);
		try {
			foursquareMediator.spawnAndAddNewProfileCheckerThread(memb);
		} catch (OPOSException e) {
			// TODO
		}
		
		return Connections.class;
	}

	@OnEvent(component = "deleteFoursquare")
	void deleteFoursquare() {
		Membership m = loggedUser.getUser().deleteApplicationMembership(ApplicationNames.FOURSQUARE);
		userManager.deleteApplicationMemberhsip(m);
	}
	
}

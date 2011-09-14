package net.onlinepresence.opos.tapestry.pages;

import java.net.URL;
import java.util.Set;

import net.onlinepresence.opos.core.spring.SpringBean;
import net.onlinepresence.opos.domain.Application;
import net.onlinepresence.opos.domain.ApplicationNames;
import net.onlinepresence.opos.domain.LoggedUser;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.domain.service.ApplicationManager;
import net.onlinepresence.opos.domain.service.UserManager;
import net.onlinepresence.opos.exceptions.OPOSException;
import net.onlinepresence.opos.mediatorManagement.MediatorManager;
import net.onlinepresence.opos.mediatorManagement.mediators.facebook.FacebookMediator;
import net.onlinepresence.opos.mediatorManagement.mediators.foursquare.FoursquareMediator;
import net.onlinepresence.opos.mediatorManagement.mediators.twitter.TwitterCommunication;
import net.onlinepresence.opos.mediatorManagement.mediators.twitter.TwitterMediator;
import net.onlinepresence.opos.service.RegistrationService;
import net.onlinepresence.opos.tapestry.appconfig.UserAppSettings;

import org.apache.log4j.Logger;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

import twitter4j.Twitter;

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
	
	private Logger logger = Logger.getLogger(Connections.class);

	@Inject @Property
	@SpringBean("net.onlinepresence.opos.domain.service.ApplicationManager")
	private ApplicationManager applications;

	@Inject
	@SpringBean("net.onlinepresence.opos.domain.service.UserManager")
	private UserManager userManager;

	@Property @SuppressWarnings("unused")
	private Application currentApplication;

	@SessionState
	private LoggedUser loggedUser;
	private boolean loggedUserExists;
	
	private RegistrationService registrationService;

	Object onActivate() {
		if (!loggedUserExists)
			return Login.class;
		
		// refreshing Hibernate session
		loggedUser.setUser(userManager.findUser(loggedUser.getUser().getUsername()));
		
		twitterAppSettings = new UserAppSettings(ApplicationNames.TWITTER);
		facebookAppSettings = new UserAppSettings(ApplicationNames.FACEBOOK);
		sparkAppSettings = new UserAppSettings(ApplicationNames.SPARK);
		foursquareAppSettings = new UserAppSettings(ApplicationNames.FOURSQUARE);
		moodleAppSettings = new UserAppSettings(ApplicationNames.MOODLE);
		
		loadMembershipInformation();

		registrationService = new RegistrationService();
		
		return null;
	}

	private void loadMembershipInformation() {
		
		Set<Membership> memberships = (Set<Membership>) loggedUser.getUser().getApplicationMemberships();
		
		for (Membership mem : memberships) {
			String appName = mem.getApplication().getName();
			
			if (appName.equals(ApplicationNames.TWITTER)) {
				configureUserAppSettings(twitterAppSettings, mem);
			} else if (appName.equals(ApplicationNames.FACEBOOK)) {
				configureUserAppSettings(facebookAppSettings, mem);
			} else if (appName.equals(ApplicationNames.SPARK)) {
				configureUserAppSettings(sparkAppSettings, mem);
			} else if (appName.equals(ApplicationNames.FOURSQUARE)) {
				configureUserAppSettings(foursquareAppSettings, mem);
			} else if (appName.equals(ApplicationNames.MOODLE)) {
				configureUserAppSettings(moodleAppSettings, mem);
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
		return registrationService.registerOnTwitter(twitter);
	}

	@OnEvent(component = "deleteTwitter")
	void deleteTwitter() {
		Membership m = loggedUser.getUser().deleteApplicationMembership(ApplicationNames.TWITTER);
		userManager.deleteApplicationMemberhsip(m);
		
		try {
			TwitterMediator.getInstance().shutDownProfileCheckerThread(m);
		} catch (OPOSException e) {
			e.printStackTrace();
		}
	}
	
	//Facebook
	@Property
	private UserAppSettings facebookAppSettings;
	
	@OnEvent(component = "facebookForm")
	URL onSubmitFromFacebookForm() {
		return registrationService.registerOnFacebook();
	}

	@OnEvent(component = "deleteFacebook")
	void deleteFacebook() {
		Membership m = loggedUser.getUser().deleteApplicationMembership(ApplicationNames.FACEBOOK);
		userManager.deleteApplicationMemberhsip(m);
		
		try {
			logger.debug("FacebookMediator.getInstance().shutDownProfileCheckerThread(m)");
			FacebookMediator.getInstance().shutDownProfileCheckerThread(m);
			logger.debug("FacebookMediator.getInstance().shutDownProfileCheckerThread(m) shut down.");
		} catch (OPOSException e) {
			e.printStackTrace();
		}
	}
	
	// Spark
	@Property
	private UserAppSettings sparkAppSettings;

	Object onSubmitFromSparkForm() {
		// refreshing Hibernate session
		loggedUser.setUser(userManager.findUser(loggedUser.getUser().getUsername()));
		Application sparkApp = applications.getApplication(ApplicationNames.SPARK);
		
		Membership memb = new Membership(
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
		
		Membership memb = new Membership(
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
		
		try {
			FoursquareMediator.getInstance().shutDownProfileCheckerThread(m);
		} catch (OPOSException e) {
			e.printStackTrace();
		}
	}
	
	// Moodle
	@Property
	private UserAppSettings moodleAppSettings;

	Object onSubmitFromMoodleForm() {
		// refreshing Hibernate session
		loggedUser.setUser(userManager.findUser(loggedUser.getUser().getUsername()));
		Application moodleApp = applications.getApplication(ApplicationNames.MOODLE);
		
		Membership memb = new Membership(
				moodleApp,
				loggedUser.getUser(), moodleAppSettings.getUsername(), null, moodleAppSettings.isSendDataToApp(),
				moodleAppSettings.isReceiveDataFromApp(), null, null);
		
		userManager.createOrUpdateNewMembership(loggedUser.getUser(), memb);
		
		return Connections.class;
	}
	
	@OnEvent(component = "deleteMoodle")
	void deleteMoodle() {
		Membership m = loggedUser.getUser().deleteApplicationMembership(ApplicationNames.MOODLE);
		userManager.deleteApplicationMemberhsip(m);
	}

	
}

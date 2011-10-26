package net.onlinepresence.opos.service;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.tapestry5.annotations.SessionState;

import net.onlinepresence.opos.config.Permission;
import net.onlinepresence.opos.config.Settings;
import net.onlinepresence.opos.domain.Application;
import net.onlinepresence.opos.domain.ApplicationNames;
import net.onlinepresence.opos.domain.LoggedUser;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.domain.pages.ExternalRegistrationData;
import net.onlinepresence.opos.domain.service.ApplicationManager;
import net.onlinepresence.opos.domain.service.UserManager;
import net.onlinepresence.opos.mediatorManagement.mediators.twitter.TwitterCommunication;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.RequestToken;

public class RegistrationService {
	
	private ApplicationManager applicationManager;
	private UserManager userManager;
	private LoggedUser loggedUser;
	
	@SessionState
	private Twitter twitter;
	
	/**
	 * @param applicationManager
	 * @param userManager
	 * @param loggedUser
	 */
	public RegistrationService(ApplicationManager applicationManager,
			UserManager userManager, LoggedUser loggedUser) {
		super();
		this.applicationManager = applicationManager;
		this.userManager = userManager;
		this.loggedUser = loggedUser;
	}
	
	/**
	 * 
	 * @param externalRegData
	 * @return
	 */
	public URL registerOnServices(ExternalRegistrationData externalRegData) {
		String applicationName = externalRegData.getNextSeviceToAuthenticateOn();
		
		if (applicationName != null) {
			applicationName = applicationName.toUpperCase();
			
			if (applicationName.equals(ApplicationNames.TWITTER)) {
				if (twitter == null)
					twitter = TwitterCommunication.getInstance().getTwitterFactory().getInstance();
				return registerOnTwitter(twitter);
			} else if (applicationName.equals(ApplicationNames.FACEBOOK)) {
				return registerOnFacebook();
			} else if (applicationName.equals(ApplicationNames.MOODLE)) {
				Application moodleApp = applicationManager.getApplication(ApplicationNames.MOODLE);
				
				Membership memb = new Membership(
						moodleApp,
						loggedUser.getUser(), externalRegData.getUsername(), null, false,
						false, null, null);
				
				userManager.createOrUpdateNewMembership(loggedUser.getUser(), memb);
				
				return registerOnServices(externalRegData);
			}
			
			URL callbackUrl = null;
			try {
				callbackUrl = new URL(externalRegData.getCallbackUrl());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			return callbackUrl;
		}
		return null;
	}

	public URL registerOnTwitter(Twitter twitter) {
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
	
	public URL registerOnFacebook() {
		StringBuffer autorizationUrlBuffer = new StringBuffer();
		autorizationUrlBuffer.append("https://www.facebook.com/dialog/oauth?client_id=");
		autorizationUrlBuffer.append(Settings.getInstance().config.facebookMediatorConfig.applicationId);
		autorizationUrlBuffer.append("&redirect_uri=");
		autorizationUrlBuffer.append(Settings.getInstance().config.facebookMediatorConfig.redirectUrl);
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
}

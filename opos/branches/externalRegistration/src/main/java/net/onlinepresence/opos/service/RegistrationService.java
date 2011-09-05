package net.onlinepresence.opos.service;

import java.net.MalformedURLException;
import java.net.URL;

import net.onlinepresence.opos.config.Permission;
import net.onlinepresence.opos.config.Settings;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.RequestToken;

public class RegistrationService {

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
}

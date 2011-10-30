package net.onlinepresence.opos.tapestry.pages.apps;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.onlinepresence.opos.config.Settings;
import net.onlinepresence.opos.core.spring.SpringBean;
import net.onlinepresence.opos.domain.Application;
import net.onlinepresence.opos.domain.ApplicationNames;
import net.onlinepresence.opos.domain.LoggedUser;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.domain.User;
import net.onlinepresence.opos.domain.pages.ExternalRegistrationData;
import net.onlinepresence.opos.domain.service.ApplicationManager;
import net.onlinepresence.opos.domain.service.UserManager;
import net.onlinepresence.opos.mediatorManagement.MediatorManager;
import net.onlinepresence.opos.mediatorManagement.mediators.facebook.FacebookMediator;
import net.onlinepresence.opos.exceptions.OPOSException;
import net.onlinepresence.opos.service.RegistrationService;
import net.onlinepresence.opos.tapestry.pages.Connections;
import net.onlinepresence.opos.tapestry.pages.Login;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;

public class FacebookApp {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Inject
	private Request request;
	
	@SessionState
	private LoggedUser loggedUser;
	private boolean loggedUserExists;

	@SuppressWarnings("unused")
	@Property
	@Persist("flash")
	private User user;

	@Inject
	@Property
	@SpringBean("net.onlinepresence.opos.domain.service.ApplicationManager")
	private ApplicationManager applicationManager;

	@Inject
	@SpringBean("net.onlinepresence.opos.domain.service.UserManager")
	private UserManager userManager;
	
	@SessionState
	private ExternalRegistrationData externalRegData;
	
	Object onActivate() {
		if (!loggedUserExists)
			return Login.class;
		
		loggedUser.setUser(userManager.findUser(loggedUser.getUser().getUsername()));
		loggedUser.getUser().getUsername();

		String accessToken = getAccessToken(request.getParameter("code"));
		
		if (accessToken != null) {
			FacebookMediator facebookMediator = (FacebookMediator) MediatorManager.getInstance().getMediator(ApplicationNames.FACEBOOK);

			Application facebookApplication = applicationManager.getApplication(ApplicationNames.FACEBOOK);
			
			FacebookClient facebookClient = new DefaultFacebookClient(accessToken);
			String id = facebookClient.fetchObject("me", com.restfb.types.User.class).getId();
			
			Membership memb = new Membership(
					facebookApplication,
					loggedUser.getUser(), id, null, true, true,
					accessToken, null);
			
			userManager.createOrUpdateNewMembership(loggedUser.getUser(), memb);
			
			try {
				facebookMediator.spawnAndAddNewProfileCheckerThread(memb);
			} catch (OPOSException e) {
				logger.error(e.getMessage());
			}
		}
		
		if (externalRegData != null) {
			RegistrationService registrationService = new RegistrationService(applicationManager, userManager, loggedUser);
			URL callbackUrl = registrationService.registerOnServices(externalRegData, null);
			
			if (callbackUrl != null)
				return callbackUrl;
		}
		
		return Connections.class;
	}
	
	private String getAccessToken(String code){
		if (code == null) {
//			flashMessager.setFailureMessage("No Oauth authentication code provided");
			logger.error("No Oauth authentication code provided");
			return null;
		}

		// String accessTokenUri = "https://graph.facebook.com/oauth/access_token?client_id=" + clientId
		// + "&redirect_uri=http://localhost:8080/oauth&client_secret=" + clientSecret + "&code=" + code;
		// String accessTokenUri = "https://graph.facebook.com/oauth/access_token";

		// String accessTokenUri = "https://graph.facebook.com/oauth/access_token?client_id&#61;" + clientId
		// + "&amp;redirect_uri&#61;http://localhost:8080/oauth&amp;client_secret&#61;" + clientSecret + "&amp;code&#61;" +
		// code;
		// https://graph.facebook.com/oauth/access_token?client_id=119507274749030&redirect_uri=http://localhost:8080/oauth&client_secret=d8b3b7dc6d5b6ddaebd68549002d643d&code=2._ViV33QBoP0Yhzmua_6gvA__.3600.1274994000-539598633|7_yFQgTwoBoD7DJYx8dqMhiXiP0.
		// logger.info("access token uri " + accessTokenUri);

		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("client_id", Settings.getInstance().config.facebookMediatorConfig.applicationId));
		qparams.add(new BasicNameValuePair("redirect_uri",  Settings.getInstance().config.facebookMediatorConfig.redirectUrl));
		qparams.add(new BasicNameValuePair("client_secret",  Settings.getInstance().config.facebookMediatorConfig.apiSecret));
		qparams.add(new BasicNameValuePair("code", code));
		HttpGet get = null;
		String accessToken = "";
		@SuppressWarnings("unused")
		long expires = 0L;
		try {
			URI uri = URIUtils
					.createURI("https", "graph.facebook.com", -1, "/oauth/access_token", URLEncodedUtils.format(qparams, "UTF-8"), null);
			get = new HttpGet(uri);

			// HttpGet get = new HttpGet(accessTokenUri);
			// NameValuePair[] queryString = new NameValuePair[4];
			// queryString[0] = new NameValuePair("client_id", clientId);
			// queryString[1] = new NameValuePair("redirect_uri", getOauthRedirectLink());
			// queryString[2] = new NameValuePair("client_secret", clientSecret);
			// queryString[3] = new NameValuePair("code", code);
			// get.setQueryString(queryString);
			// HttpClient httpClient = new DefaultHttpClient();
			HttpClient httpClient = new DefaultHttpClient();

			HttpResponse response = httpClient.execute(get);
			int status = response.getStatusLine().getStatusCode();
			if (HttpStatus.SC_OK != status) {
				logger.error("Facebook access_token request returned status code " + status);
				return null;
			}
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				long len = entity.getContentLength();
				if (len != -1 && len < 2048) accessToken = EntityUtils.toString(entity);
			}
		} catch (Exception e) {
			logger.error("Facebook access_token request failed because of:", e);
			return null;
		} finally {
			if (get != null) get.abort();
		}

		try {
			if (!accessToken.startsWith("access_token")) throw new IllegalArgumentException();
			// access_token is of form:
			// access_token=119507274749030|2.1AptZFp9__qW3k2PuG4bVA__.3600.1274914800-539598633|9aTyryhVl8vnn3ulLy2w6Txo92E.&expires=4059
			accessToken = accessToken.substring(accessToken.indexOf("=") + 1);
			if (accessToken.contains("=")) {
				expires = Long.valueOf(accessToken.substring(accessToken.lastIndexOf("=") + 1));
				accessToken = accessToken.substring(0, accessToken.indexOf("&expires"));
			}
		} catch (Exception e) {
			logger.error("access_token wasn't of right format");
			return null;
		}
		return accessToken;
	}
}

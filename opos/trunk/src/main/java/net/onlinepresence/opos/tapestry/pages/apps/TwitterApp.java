package net.onlinepresence.opos.tapestry.pages.apps;

import net.onlinepresence.opos.core.spring.SpringBean;
import net.onlinepresence.opos.domain.Application;
import net.onlinepresence.opos.domain.ApplicationNames;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.domain.User;
import net.onlinepresence.opos.domain.beans.LoggedUserBean;
import net.onlinepresence.opos.domain.beans.MembershipBean;
import net.onlinepresence.opos.domain.service.ApplicationManager;
import net.onlinepresence.opos.domain.service.UserManager;
import net.onlinepresence.opos.mediatorManagement.MediatorManager;
import net.onlinepresence.opos.mediatorManagement.mediators.twitter.TwitterMediator;
import net.onlinepresence.opos.exceptions.OPOSException;
import net.onlinepresence.opos.tapestry.pages.Connections;
import net.onlinepresence.opos.tapestry.pages.Login;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;

/**
 * Start page of application opos.
 */
public class TwitterApp {
	
	@SessionState
	private Twitter twitter;

	@SessionState
	private LoggedUserBean loggedUser;
	private boolean loggedUserExists;

	@SuppressWarnings("unused")
	@Property
	@Persist("flash")
	private User user;

	@Inject
	@Property
	@SpringBean("net.onlinepresence.opos.domain.service.ApplicationManager")
	private ApplicationManager applications;

	@Inject
	@SpringBean("net.onlinepresence.opos.domain.service.UserManager")
	private UserManager userManager;
	
	Object onActivate() {
		if (!loggedUserExists)
			return Login.class;
		
		loggedUser.setUser(userManager.findUser(loggedUser.getUser().getUsername()));

		AccessToken accessToken = null;

		try {
			accessToken = twitter.getOAuthAccessToken();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		if (accessToken != null) {
			Membership memb;
			try {
				Application twitterApplication = applications.getApplication(ApplicationNames.TWITTER);
				memb = new MembershipBean(
						twitterApplication,
						loggedUser.getUser(), twitter.getScreenName(), null, true, true,
						accessToken.getToken(), accessToken.getTokenSecret());
				
				userManager.createOrUpdateNewMembership(loggedUser.getUser(), memb);
				
				twitter.setOAuthAccessToken(accessToken);
				
				TwitterMediator twitterMediator = (TwitterMediator) MediatorManager.getInstance().getMediator(ApplicationNames.TWITTER);
				try {
					twitterMediator.spawnAndAddNewProfileCheckerThread(memb);
				} catch (OPOSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (TwitterException e) {
				e.printStackTrace();
			}
		}
		return Connections.class;
	}
}
package net.onlinepresence.opos.tapestry.pages.apps;

import net.onlinepresence.opos.core.spring.SpringBean;
import net.onlinepresence.opos.dataManager.MediatorManager;
import net.onlinepresence.opos.dataManager.mediators.Mediator;
import net.onlinepresence.opos.dataManager.mediators.MediatorNames;
import net.onlinepresence.opos.dataManager.mediators.twitter.TwitterMediator;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.domain.User;
import net.onlinepresence.opos.domain.beans.LoggedUserBean;
import net.onlinepresence.opos.domain.beans.MembershipBean;
import net.onlinepresence.opos.domain.service.Applications;
import net.onlinepresence.opos.domain.service.Users;
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
	private LoggedUserBean loggedUser;
	private boolean loggedUserExists;

	@SessionState
	private Twitter twitter;

	@SuppressWarnings("unused")
	@Property
	@Persist("flash")
	private User user;

	@Inject
	@Property
	@SpringBean("net.onlinepresence.opos.domain.service.Applications")
	private Applications applications;

	@Inject
	@SpringBean("net.onlinepresence.opos.domain.service.Users")
	private Users users;

	@SessionState
	private MediatorManager mediatorManager;

	Object onActivate() {
		if (!loggedUserExists)
			return Login.class;

		AccessToken accessToken = null;

		try {
			accessToken = twitter.getOAuthAccessToken();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (accessToken != null) {
			Membership memb;
			try {
				memb = new MembershipBean(
						applications.getApplication("http://www.twitter.com"),
						loggedUser.getUser(), twitter.getScreenName(), null, true, true,
						accessToken.getToken(), accessToken.getTokenSecret());

				// this must go before saving membership because if this is a first
				// call of SessionState object mediatorManager, it will instantiate
				// it, and thus it will instantiate TwitterMediator instance for
				// saved Membership instance, but the TwitterMediator instance won't
				// have Twitter object to work with
				Mediator twitterMediator = mediatorManager.getMediator(MediatorNames.Twitter);
				if (!loggedUser.getUser().hasMembership(memb)) {
					loggedUser.getUser().addApplicationMembership(memb);
					users.update(loggedUser.getUser());
				} else {
					loggedUser.getUser().updateMembership(memb);
					users.update(loggedUser.getUser());
				}
				twitter.setOAuthAccessToken(accessToken);
				((TwitterMediator) twitterMediator).spawnNewTwitterThread(memb,	twitter);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TwitterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return Connections.class;
	}

	// @SetupRender
	// public Object finishTwiiterConnection() {
	//
	// }

}

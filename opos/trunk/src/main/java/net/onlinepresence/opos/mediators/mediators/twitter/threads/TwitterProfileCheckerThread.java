package net.onlinepresence.opos.mediators.mediators.twitter.threads;

import twitter4j.Twitter;
import net.onlinepresence.ontmodel.opo.OnlinePresence;
import net.onlinepresence.opos.config.Settings;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.mediators.mediators.twitter.TwitterMediator;
import net.onlinepresence.opos.mediators.mediators.twitter.util.TwitterStuff;

public class TwitterProfileCheckerThread extends Thread {
	
//	private static final long TIMEOUT = Long.parseLong(PropertiesManager.instance().getProperty(OPOSConstants.TWITTER_PROFILE_CHECK_TIMEOUT));
	private static final long TIMEOUT = Settings.getInstance().config.twitterMediatorConfig.checkTimeout;
	private Membership user;
	private OnlinePresence onlinePresence;
	private TwitterMediator twitterMediator;
	private TwitterStuff twitterStuff;
	private Twitter twitter;
	private boolean wait = false;
	private boolean checking = false;
	
	public TwitterProfileCheckerThread(TwitterMediator twitterMediator, Membership userMembership, Twitter twitter2) {
		this.twitterMediator = twitterMediator;
		this.user = userMembership;
		
		twitter = twitter2;
//		System.out.println("TwitterProfileCheckerThread: getting Twitter instance for the user " +  userMembership.getUsername());
////		twitter = twf.getInstance(userMembership.getUsername(), userMembership.getPassword());
//		TwitterFactory twf = new TwitterFactory();
//	    twitter = twf.getInstance();
//	    Properties appProp = net.onlinepresence.opos.util.Util.loadPropertyFile("/mediator_params.properties");
//	    twitter.setOAuthConsumer(appProp.getProperty("twitter-app-API-key"), appProp.getProperty("twitter-app-API-secret"));
//	    AccessToken accessToken = Util.loadAccessToken(userMembership);
//	    twitter.setOAuthAccessToken(accessToken);
	}

	/**
	 * @return the user
	 */
	public Membership getUser() {
		return user;
	}

	/**
	 * @param user the person to set
	 */
	public void setUser(Membership user) {
		this.user = user;
	}
	
	/**
	 * @return the onlinePresence
	 */
	public OnlinePresence getOnlinePresence() {
		return onlinePresence;
	}

	/**
	 * @param onlinePresence the onlinePresence to set
	 */
	public void setOnlinePresence(OnlinePresence onlinePresence) {
		this.onlinePresence = onlinePresence;
	}

	/**
	 * @return the twitterStuff
	 */
	public TwitterStuff getTwitterStuff() {
		return twitterStuff;
	}

	/**
	 * @param twitterStuff the twitterStuff to set
	 */
	public void setTwitterStuff(TwitterStuff twitterStuff) {
		this.twitterStuff = twitterStuff;
	}

	public void setWait(boolean wait) {
		this.wait = wait;
	}

	/**
	 * @return the checking
	 */
	public boolean isChecking() {
		return checking;
	}

	/**
	 * @param checking the checking to set
	 */
	public void setChecking(boolean checking) {
		this.checking = checking;
	}

	@Override
	public void run() {
		System.out.println("TwitterProfileCheckerThread: started");
		
		while (true) {
			setChecking(true);
			if(!wait){
				CheckThread check = new CheckThread(this, twitter, onlinePresence);
				check.start();	
				
				try {
					setChecking(false);
					sleep(TIMEOUT);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void propagateOnlinePresence(OnlinePresence newOnlinePresence) {
		twitterMediator.propagateOnlinePresence(newOnlinePresence);
	}
}

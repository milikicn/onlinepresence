package net.onlinepresence.opos.mediators.mediators.twitter.threads;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import net.onlinepresence.ontmodel.opo.OnlinePresence;
import net.onlinepresence.opos.mediators.mediators.twitter.exceptions.TwitterOPOSException;
import net.onlinepresence.opos.mediators.mediators.twitter.service.builder.TwitterOnlinePresenceBuilder;
import net.onlinepresence.opos.mediators.mediators.twitter.util.TwitterOnlinePresenceUtil;
import net.onlinepresence.opos.mediators.mediators.twitter.util.TwitterStuff;

public class CheckThread extends Thread {

	private TwitterProfileCheckerThread tpcThread = null;
	private OnlinePresence currentOnlinePresence = null;
	private TwitterOnlinePresenceBuilder topBuilder = null;
	private TwitterStuff twitterStuff;
	
	/**
	 * @param tpcThread
	 * @param twitterStuff 
	 * @param onlinePresence
	 */
	public CheckThread(TwitterProfileCheckerThread tpcThread,
			Twitter twitter, OnlinePresence onlinePresence) {
		super();
		this.tpcThread = tpcThread;
		this.currentOnlinePresence = onlinePresence;
		
		try {
			twitterStuff = new TwitterStuff(twitter);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		topBuilder = new TwitterOnlinePresenceBuilder(twitterStuff);
	}


	@Override
	public void run() {
		OnlinePresence newOnlinePresence;
		
		try {
			newOnlinePresence = topBuilder.build();
			
			if (currentOnlinePresence != null) {
				if(!TwitterOnlinePresenceUtil.equalOnlinePresenceData(currentOnlinePresence, newOnlinePresence)){
					tpcThread.propagateOnlinePresence(newOnlinePresence);
					tpcThread.setOnlinePresence(newOnlinePresence);
					tpcThread.setTwitterStuff(twitterStuff);
				}
			}else {
				tpcThread.setOnlinePresence(newOnlinePresence);
				tpcThread.setTwitterStuff(twitterStuff);
			}
			
		} catch (TwitterOPOSException e) {
			e.printStackTrace();
		}
	}
}

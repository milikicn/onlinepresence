package net.onlinepresence.opos.mediators.mediators.twitter.threads;

import twitter4j.Twitter;
import net.onlinepresence.ontmodel.opo.OnlinePresence;
import net.onlinepresence.opos.mediators.mediators.twitter.service.submitter.TwitterProfileSubmitter;
import net.onlinepresence.opos.mediators.mediators.twitter.service.submitter.TwitterStatusSubmitter;
import net.onlinepresence.opos.mediators.mediators.twitter.util.TwitterOnlinePresenceUtil;

public class TwitterProfileSubmitThread extends Thread {

	private OnlinePresence newOnlinePresence;
	private TwitterProfileCheckerThread tpcThread;
	private Twitter twitter;

	/**
	 * @param newOnlinePresence
	 * @param twitterStuf
	 */
	public TwitterProfileSubmitThread(OnlinePresence newOnlinePresence,
			TwitterProfileCheckerThread tpcThread) {
		System.out.println("Inicijalizujem TwitterProfileSubmitThread");
		this.newOnlinePresence = newOnlinePresence;
		this.tpcThread = tpcThread;
		this.twitter = tpcThread.getTwitterStuff().getTwitter();
	}

	@Override
	public void run() {
		while (tpcThread.isChecking()) {
			
		}
		tpcThread.setWait(true);
		
		System.out.println("Pokrecem run() na TwitterProfileSubmitThread");

		boolean profileSubmited = false;
		while (!profileSubmited)
			profileSubmited = new TwitterProfileSubmitter(newOnlinePresence,
					twitter).submit();

		boolean statusSubmited = false;
		while (!statusSubmited)
			statusSubmited = new TwitterStatusSubmitter(newOnlinePresence,
					twitter).submit();

		OnlinePresence oldOP = tpcThread.getOnlinePresence();
		OnlinePresence newOP = TwitterOnlinePresenceUtil.updateWithTwitterRelatedData(oldOP, newOnlinePresence);
		tpcThread.setOnlinePresence(newOP);
		
		tpcThread.setWait(false);
	}
}

package net.onlinepresence.opos.mediatorManagement.mediators.twitter;

import twitter4j.Twitter;
import net.onlinepresence.jopo.ontmodel.opo.OnlinePresence;
import net.onlinepresence.opos.mediatorManagement.mediators.twitter.submitter.TwitterProfileSubmitter;
import net.onlinepresence.opos.mediatorManagement.mediators.twitter.submitter.TwitterStatusSubmitter;
import net.onlinepresence.opos.mediatorManagement.mediators.twitter.util.OnlinePresenceTwitterUtil;

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

		OnlinePresence oldOP = tpcThread.getCurrentOnlinePresence();
		OnlinePresence newOP = OnlinePresenceTwitterUtil.updateWithTwitterRelatedData(oldOP, newOnlinePresence);
		tpcThread.setCurrentOnlinePresence(newOP);
		
		tpcThread.setWait(false);
	}
}

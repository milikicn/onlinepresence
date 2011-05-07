package net.onlinepresence.opos.mediatorManagement.mediators.twitter.submitter;

import net.onlinepresence.jopo.ontmodel.opo.OnlinePresence;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class TwitterStatusSubmitter {
	
	private OnlinePresence onlinePresence;
	private Twitter twitter;
	private String text;

	public TwitterStatusSubmitter(OnlinePresence onlinePresence,
			Twitter twitter) {
		 this.onlinePresence = onlinePresence;
		 this.twitter = twitter;
	}

	public boolean submit() {
		init();
		System.out.println("submit na TwitterStatusSubmitter");
		try {
			if(text != null){
				twitter.updateStatus(text);
				System.out.println("updateStatus na TwitterStatusSubmitter");
			}
			return true;
		} catch (TwitterException e) {
			return false;
//			throw new TwitterOPOSException("Error when updating user status.");
		}
	}
	
	private void init() {
		System.out.println("init na TwitterStatusSubmitter");
		if(onlinePresence.getStatusMessage()!= null)
			text = onlinePresence.getStatusMessage().getContent();
	}

}

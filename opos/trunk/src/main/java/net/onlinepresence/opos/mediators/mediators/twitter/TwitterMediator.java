package net.onlinepresence.opos.mediators.mediators.twitter;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import twitter4j.Twitter;

import net.onlinepresence.ontmodel.opo.OnlinePresence;
import net.onlinepresence.opos.domain.ApplicationNames;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.mediators.MediatorManager;
import net.onlinepresence.opos.mediators.mediators.Mediator;
import net.onlinepresence.opos.mediators.mediators.twitter.threads.TwitterProfileCheckerThread;
import net.onlinepresence.opos.mediators.mediators.twitter.threads.TwitterProfileSubmitThread;

public class TwitterMediator implements Mediator {
	
	private List<TwitterProfileCheckerThread> twitterProfileCheckers = new LinkedList<TwitterProfileCheckerThread>();
	private List<Membership> users = null;
	private MediatorManager mm;

	public TwitterMediator(MediatorManager mm, List<Membership> userss) {
		this.mm = mm;
		this.users = userss;
		
		initThreadsForTwitterUsers(users);
	}

	private void initThreadsForTwitterUsers(List<Membership> users) {
		Iterator<Membership> iterator = users.iterator();

		while (iterator.hasNext()) {
			Membership userMembership = (Membership) iterator.next();
			spawnNewTwitterThread(userMembership, null);
		}
	}
	
	public void spawnNewTwitterThread(Membership userMembership, Twitter twitter){
		System.out.println("TwitterMediator: instantiating TwitterProfileCheckerThread for username " + userMembership.getUsername());
		TwitterProfileCheckerThread tpc = new TwitterProfileCheckerThread(this, userMembership, twitter);
		tpc.start();
		twitterProfileCheckers.add(tpc);
	}

	public ApplicationNames getMediatorName() {
		return ApplicationNames.TWITTER;
	}
	
	public void sendOnlinePresenceToUser(OnlinePresence op, Membership membership) {
		System.out.println("sendOnlinePresenceToUser");
		TwitterProfileCheckerThread tpcThread = findTwitterProfileCheckerThread(membership);
		
		System.out.println("Pokrecem TwitterProfileSubmitThread");
		new TwitterProfileSubmitThread(op, tpcThread).start();
	}

	public void propagateOnlinePresence(OnlinePresence onlinePresence) {
		System.out.println("||||||||Propagating OP");
		mm.propagateOnlinePresence(onlinePresence);
	}
	
	private TwitterProfileCheckerThread findTwitterProfileCheckerThread(Membership membership){
		
		Iterator<TwitterProfileCheckerThread> iterator = twitterProfileCheckers.iterator();
		
		while (iterator.hasNext()) {
			TwitterProfileCheckerThread twitterProfileCheckerThread = (TwitterProfileCheckerThread) iterator
					.next();
			
			if(twitterProfileCheckerThread.getUser().equals(membership)){
				System.out.println("Pronasao sam twitterProfileCheckerThread koji ima usera " + membership);
				System.out.println("a twitterStuff mu je: " + twitterProfileCheckerThread.getTwitterStuff());
				return twitterProfileCheckerThread;
			}
		}
		
		return null;
	}
}

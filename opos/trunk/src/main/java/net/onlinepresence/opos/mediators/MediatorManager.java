package net.onlinepresence.opos.mediators;

import java.util.LinkedList;
import java.util.List;

import net.onlinepresence.ontmodel.opo.OnlinePresence;
import net.onlinepresence.opos.core.spring.ApplicationContextProviderSingleton;
import net.onlinepresence.opos.domain.ApplicationNames;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.domain.service.ApplicationManager;
import net.onlinepresence.opos.domain.service.UserManager;
import net.onlinepresence.opos.domain.service.beans.UserManagerBean;
import net.onlinepresence.opos.mediators.mediators.Mediator;
import net.onlinepresence.opos.mediators.mediators.facebook.FacebookMediator;
import net.onlinepresence.opos.mediators.mediators.spark.SparkMediator;
import net.onlinepresence.opos.mediators.mediators.twitter.TwitterMediator;
import net.onlinepresence.opos.semanticstuff.services.OnlinePresenceService;

public class MediatorManager {
	
	private UserManager personsService;
	private ApplicationManager applicationsService;
	private OnlinePresenceService rdfPersistance;	
	private LinkedList<Mediator> mediators;
	
	public static MediatorManager INSTANCE;
	
	private MediatorManager(){ 
		init();
	}
	
	public static MediatorManager getInstance() {
		if (INSTANCE == null)
			INSTANCE = new MediatorManager();
		
		return INSTANCE;
	}

	public void init(){
		System.out.println("MediatorManager initialized");
		ApplicationContextProviderSingleton s = new ApplicationContextProviderSingleton();
		s.createContext();
		personsService = (UserManagerBean) s.getContext().getBean(UserManager.class.getName());
		applicationsService = (ApplicationManager) s.getContext().getBean(ApplicationManager.class.getName());
		rdfPersistance = new OnlinePresenceService();
		mediators = new LinkedList<Mediator>();
		initializeMediators();
	}
	
	//  mozda bolje preko springa da se nekako istanciraju svi medijatori
	private void initializeMediators() {
		Mediator spark = new SparkMediator(this);
		mediators.add(spark);
		
		List<Membership> twitterMemberships = applicationsService.getAllApplicationMemberships(ApplicationNames.TWITTER);
		Mediator twitter = new TwitterMediator(twitterMemberships);
		mediators.add(twitter);
		
		List<Membership> facebookMemberships = applicationsService.getAllApplicationMemberships(ApplicationNames.FACEBOOK);
		Mediator facebook = new FacebookMediator(facebookMemberships);
		mediators.add(facebook);
	}

	// reimplementirati
	public void propagateOnlinePresence(OnlinePresence onlinePresence){
		
		//storing OP into the repository
		try {
			rdfPersistance.saveResource(onlinePresence, true);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String username = onlinePresence.getUserAccount().getAccountName();
		String app = onlinePresence.getUserAccount().getAccountServiceHomepage().toString();
		
		// memberships should be refreshed if a new one is added in the mean time
		List<Membership> list = personsService.getAllMemberships(username, app);
		
		for (Membership membership : list) {
			if(membership.getApplication().getWebAddress().equals(app))
				if(membership.isReceiveFrom() == false)
					return;
		}

		for (Membership membership : list) {
			if(membership.getApplication().getWebAddress().equals(app)){
				System.out.println("|||||Preskocio sam membership" + membership.toString());
				continue;
			}
			
			if(membership.isSendTo() == true){
				Mediator med = null;
				try {
					System.out.println("Proveravam membership: " + membership.toString());
					med = getMediator(membership.getApplication().getName());
					med.sendOnlinePresenceToUser(onlinePresence, membership);
				} catch (NullPointerException e) {
					System.out.println("Ne postoji medijator: " + membership.getApplication().getName());
					//e.printStackTrace();
				}
			}
		}
	}
	
	public Mediator getMediator(ApplicationNames name){
		System.out.println("Looking for mediator: " + name);
		for (Mediator med : mediators) {
			System.out.println("Proveravam medijator: " +med.getMediatorName());
			if(med.getMediatorName().equals(name))
				return med;
		}
		return null;
	}
	
}

package net.onlinepresence.opos.mediatorManagement;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import net.onlinepresence.jopo.ontmodel.opo.OnlinePresence;
import net.onlinepresence.opos.core.spring.ApplicationContextProviderSingleton;
import net.onlinepresence.opos.domain.ApplicationName;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.domain.service.ApplicationManager;
import net.onlinepresence.opos.domain.service.UserManager;
import net.onlinepresence.opos.domain.service.beans.UserManagerBean;
import net.onlinepresence.opos.exceptions.OPOSException;
import net.onlinepresence.opos.mediatorManagement.mediators.Mediator;
import net.onlinepresence.opos.mediatorManagement.mediators.facebook.FacebookMediator;
import net.onlinepresence.opos.mediatorManagement.mediators.foursquare.FoursquareMediator;
import net.onlinepresence.opos.mediatorManagement.mediators.spark.SparkMediator;
import net.onlinepresence.opos.mediatorManagement.mediators.twitter.TwitterMediator;
import net.onlinepresence.opos.semanticstuff.services.OnlinePresenceService;

import org.apache.log4j.Logger;

public class MediatorManager {
	private Logger logger = Logger.getLogger(MediatorManager.class);
	
	private UserManager personManager;
	private ApplicationManager applicationManager;
	private OnlinePresenceService rdfPersistance = new OnlinePresenceService();	
	private List<Mediator> mediators = new ArrayList<Mediator>();
	
	private boolean initialized = false;
	
	private static MediatorManager INSTANCE;
	
	private MediatorManager(){ 
		init();
	}
	
	public static MediatorManager getInstance() {
		if (INSTANCE == null)
			INSTANCE = new MediatorManager();
		
		return INSTANCE;
	}

	// spring should inject this
	public void init(){
		if (!initialized) {
			logger.debug("Initializing MediatorManager.");
			ApplicationContextProviderSingleton s = new ApplicationContextProviderSingleton();
			personManager = (UserManagerBean) s.getContext().getBean(UserManager.class.getName());
			applicationManager = (ApplicationManager) s.getContext().getBean(ApplicationManager.class.getName());
			initializeMediators();
			initialized = true;
		}
	}
	
	//  mozda bolje preko springa da se nekako istanciraju svi medijatori
	private void initializeMediators() {
		logger.debug("Initializing SparkMediator.");
		SparkMediator spark = new SparkMediator();
		mediators.add(spark);
		
		// TWITTER mediator
		logger.debug("Initializing TwitterMediator.");
		List<Membership> twitterMemberships = applicationManager.getAllApplicationMemberships(ApplicationName.TWITTER);
		try {
			if (twitterMemberships != null && !twitterMemberships.isEmpty()) {
				TwitterMediator.getInstance().init(twitterMemberships);
			}
		} catch (OPOSException e) {
			logger.error(e.getMessage());
		}
		mediators.add(TwitterMediator.getInstance());
		
		// FACEBOOK mediator
		logger.debug("Initializing FacebookMediator.");
		List<Membership> facebookMemberships = applicationManager.getAllApplicationMemberships(ApplicationName.FACEBOOK);
		try {
			if (facebookMemberships != null && !facebookMemberships.isEmpty()) {
				FacebookMediator.getInstance().init(facebookMemberships);
			}
		} catch (OPOSException e) {
			logger.error(e.getMessage());
		}
		mediators.add(FacebookMediator.getInstance());
		
		// FOURSQUARE mediator
		logger.debug("Initializing FoursquareMediator.");
		List<Membership> foursquareMemberships = applicationManager.getAllApplicationMemberships(ApplicationName.FOURSQUARE);
		try {
			if (foursquareMemberships != null && !foursquareMemberships.isEmpty()) {
				FoursquareMediator.getInstance().init(foursquareMemberships);
			}
		} catch (OPOSException e) {
			logger.error(e.getMessage());
		}
		mediators.add(FoursquareMediator.getInstance());
	}

	// TODO: to revise
	public void propagateOnlinePresence(OnlinePresence onlinePresence){
		//storing OP into the repository
		try {
			rdfPersistance.saveOnlinePresence(onlinePresence);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		String username = onlinePresence.getUserAccount().getAccountName();
		String appHomepage = onlinePresence.getUserAccount().getAccountServiceHomepage().toString();
		
		// memberships should be refreshed if a new one is added in the mean time
		List<Membership> list = personManager.getAllMemberships(username);
		
		for (Membership membership : list) {
			if(membership.getApplication().getWebAddress().equals(appHomepage))
				if(membership.isReceiveFrom() == false)
					return;
		}

		for (Membership membership : list) {
			if(membership.getApplication().getWebAddress().equals(appHomepage)){
				logger.debug("|||||Preskocio sam membership" + membership.toString());
				continue;
			}
			
			if(membership.isSendTo() == true){
				Mediator med = null;
				try {
					logger.debug("Proveravam membership: " + membership.toString());
					med = getMediator(membership.getApplication().getName());
					med.sendOnlinePresenceToUser(onlinePresence, membership);
				} catch (NullPointerException e) {
					logger.error("Ne postoji medijator: " + membership.getApplication().getName());
					//e.printStackTrace();
				}
			}
		}
		
		// putting into the cache
		LatestPresenceCache.getInstance().addNewOnlinePresence(
				onlinePresence.getAgent().getUri().toString(), 
				appHomepage, 
				onlinePresence);
	}
	
	public Mediator getMediator(String name){
		logger.debug("Looking for mediator " + name);
		for (Mediator med : mediators) {
			if(med.getMediatorName().equals(name))
				return med;
		}
		return null;
	}
	
}

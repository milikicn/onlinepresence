package net.onlinepresence.opos.mediatorManagement;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import net.onlinepresence.jopo.ontmodel.opo.OnlinePresence;
import net.onlinepresence.opos.core.spring.ApplicationContextProviderSingleton;
import net.onlinepresence.opos.domain.ApplicationNames;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.domain.service.ApplicationManager;
import net.onlinepresence.opos.domain.service.UserManager;
import net.onlinepresence.opos.domain.service.beans.UserManagerBean;
import net.onlinepresence.opos.mediatorManagement.mediators.Mediator;
import net.onlinepresence.opos.mediatorManagement.mediators.spark.SparkMediator;
import net.onlinepresence.opos.mediatorManagement.mediators.twitter.TwitterMediator;
import net.onlinepresence.opos.exceptions.OPOSException;
import net.onlinepresence.opos.semanticstuff.services.OnlinePresenceService;

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
		
		logger.debug("Initializing TwitterMediator.");
		List<Membership> twitterMemberships = applicationManager.getAllApplicationMemberships(ApplicationNames.TWITTER);
		try {
			TwitterMediator.getInstance().init(twitterMemberships);
		} catch (OPOSException e) {
			logger.error(e.getMessage());
		}
		mediators.add(TwitterMediator.getInstance());
		
//		List<Membership> facebookMemberships = applicationManager.getAllApplicationMemberships(ApplicationNames.FACEBOOK);
//		FacebookMediator facebook = new FacebookMediator(facebookMemberships);
//		mediators.add(facebook);
	}

	// reimplementirati
	public void propagateOnlinePresence(OnlinePresence onlinePresence){
		//storing OP into the repository
		try {
			rdfPersistance.saveResource(onlinePresence, true);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		String username = onlinePresence.getUserAccount().getAccountName();
		String app = onlinePresence.getUserAccount().getAccountServiceHomepage().toString();
		
		// memberships should be refreshed if a new one is added in the mean time
		List<Membership> list = personManager.getAllMemberships(username, app);
		
		for (Membership membership : list) {
			if(membership.getApplication().getWebAddress().equals(app))
				if(membership.isReceiveFrom() == false)
					return;
		}

		for (Membership membership : list) {
			if(membership.getApplication().getWebAddress().equals(app)){
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
	}
	
	public Mediator getMediator(ApplicationNames name){
		logger.debug("Looking for mediator " + name);
		for (Mediator med : mediators) {
			if(med.getMediatorName().equals(name))
				return med;
		}
		return null;
	}
	
}

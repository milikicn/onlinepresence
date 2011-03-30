package net.onlinepresence.opos.dataManager;

import java.util.LinkedList;
import java.util.List;

import net.onlinepresence.ontmodel.opo.OnlinePresence;
import net.onlinepresence.opos.core.spring.ApplicationContextProviderSingleton;
import net.onlinepresence.opos.dataManager.mediators.Mediator;
import net.onlinepresence.opos.dataManager.mediators.MediatorNames;
import net.onlinepresence.opos.dataManager.mediators.spark.SparkMediator;
import net.onlinepresence.opos.dataManager.mediators.twitter.TwitterMediator;
import net.onlinepresence.opos.dataManager.util.properties.OPOSConstants;
import net.onlinepresence.opos.dataManager.util.properties.PropertiesManager;
import net.onlinepresence.opos.datacentral.rdfpersistance.DefaultRdfPersistanceService;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.domain.service.Applications;
import net.onlinepresence.opos.domain.service.Users;
import net.onlinepresence.opos.domain.service.beans.UsersBean;
import net.onlinepresence.services.FileOPOService;

public class MediatorManager {
	
	private static final String PATH = PropertiesManager.instance().getProperty(OPOSConstants.FILE_REPOSITORY);
	
	private Users personsService;
	private Applications applicationsService;
	private DefaultRdfPersistanceService rdfPersistance;	
	private LinkedList<Mediator> mediators;

	public MediatorManager(){
		ApplicationContextProviderSingleton s = new ApplicationContextProviderSingleton();
		s.createContext();
		personsService = (UsersBean) s.getContext().getBean(Users.class.getName());
		applicationsService = (Applications) s.getContext().getBean(Applications.class.getName());
		rdfPersistance = new DefaultRdfPersistanceService();
		mediators = new LinkedList<Mediator>();
		initializeMediators();
	}
	
	
	//  mozda bolje preko springa da se nekako istanciraju svi medijatori
	private void initializeMediators() {
		Mediator spark = new SparkMediator(this);
		mediators.add(spark);
		
		List<Membership> memberships = applicationsService.getAllApplicationMemberships("http://www.twitter.com");
		Mediator twitter = new TwitterMediator(this, memberships);
		mediators.add(twitter);
	}


	// reimplementirati
	public void propagateOnlinePresence(OnlinePresence onlinePresence){
		
		//storin OP into the repository
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
					med = getMediator(MediatorNames.valueOf(membership.getApplication().getName()));
					med.sendOnlinePresenceToUser(onlinePresence, membership);
				} catch (NullPointerException e) {
					System.out.println("Ne postoji medijator: " + membership.getApplication().getName());
					//e.printStackTrace();
				}
				
			}
		}
	}
	
	
	public Mediator getMediator(MediatorNames name){
		System.out.println("Looking for mediator: " + name);
		for (Mediator med : mediators) {
			System.out.println("Proveravam medijator: " +med.getMediatorName());
			if(med.getMediatorName().equals(name))
				return med;
		}
		return null;
	}
	
}

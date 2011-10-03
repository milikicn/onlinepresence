package net.onlinepresence.opos.domain.service.beans;

import java.util.List;

import net.onlinepresence.jopo.ontmodel.foaf.Person;
import net.onlinepresence.jopo.ontmodel.sioc.UserAccount;
import net.onlinepresence.jopo.services.spring.ResourceFactory;
import net.onlinepresence.opos.core.spring.SpringBean;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.domain.User;
import net.onlinepresence.opos.domain.service.UserManager;
import net.onlinepresence.opos.semanticstuff.services.OnlinePresenceService;
import net.onlinepresence.opos.service.crud.impl.DeleteCommand;

import org.apache.log4j.Logger;
import org.apache.tapestry5.ioc.annotations.Inject;

public class UserManagerBean 
	extends AbstractManagerBean<User>
	implements UserManager {
	
	private Logger logger = Logger.getLogger(UserManager.class);
	
	@Inject
	@SpringBean("net.onlinepresence.opos.service.crud.impl.DeleteCommand")
	private DeleteCommand<Membership> membershipDeleter;
	
	public UserManagerBean() { }

	/* (non-Javadoc)
	 * @see opos.service.Users#getAll()
	 */
	public List<User> getUsers() {
		getReader().setClazz(User.class);
		return getReader().execute();
	}
	
	public void addUser(User u){
		getWriter().setToSave(u);
		getWriter().execute();
	}

	public void removeUser(User u){
		getDeleter().setToDelete(u);
		getDeleter().execute();
	}

	/* (non-Javadoc)
	 * @see opos.service.Persons#getPerson(java.lang.String)
	 */
	public User findUser(String usernameOrEmail) {
		List<User> users = getUsers();
		
		if(usernameOrEmail.contains("@")) {
			for (User u : users) {
				if(u.getEmail().equals(usernameOrEmail))
					return u;						
			}
		} else{
			for (User u : users) {
				if(u.getUsername().equals(usernameOrEmail))
					return u;						
			}
		}
		
		return null;
	}
	
	public void update(User u){
		getUpdater().setToUpdate(u);
		getUpdater().execute();
	}
	
	public void deleteApplicationMemberhsip(Membership mem){
		membershipDeleter.setToDelete(mem);
		membershipDeleter.execute();
	}
	
	public boolean existsUser(String username){
		User u = findUser(username);
		if(u != null)
			return true;
		return false;
	}
	
	/*
	 * for a given username and app of membership, finds its user and list all his memberships
	 */
	@SuppressWarnings("unchecked")
	public List<Membership> getAllMemberships(String username, String app){
		return (List<Membership>) getReader().executeQuery(
				"from Membership " +
				"where user = (" +
					"select user.username " +
					"from Membership " +
					"where application = '" + app +"'" + "and " +
							"				username = '" + username +"')");
	}
	
	public Membership createOrUpdateNewMembership(User user, Membership membership) {
		if (!user.hasMembership(membership)) {
			user.addApplicationMembership(membership);
			update(user);
			
			OnlinePresenceService opService = new OnlinePresenceService();
			// check if there is sioc:UserAccount instance representring this account (this 
			// will occur if user once added this account to the OPOS and disconected this 
			// application afterwards)
			UserAccount userAccount = opService.getUserAccount(membership);
			
			if (userAccount == null) {
				//save new UserAccount information into the RDF repository
				ResourceFactory resourceFactory = new ResourceFactory();
				
				userAccount = (UserAccount) resourceFactory.createResource(UserAccount.class);
				userAccount.setAccountName(membership.getUsername());
				userAccount.setAccountServiceHomepage(membership.getApplication().getWebAddress());
				
				try {
					userAccount = opService.saveResource(userAccount, false);
					logger.debug("Saved in the repostiory new UserAccount for user with username '"
							+membership.getUsername()+"' on the service '"
							+membership.getApplication().getWebAddress()+"'.");
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
			
			try {
				Person person = opService.getPersonInstance(user);
				person.addAccount(userAccount);
				opService.updateResource(person, false);
				
				logger.debug("Added sioc:UserAccount instance with username '"
						+membership.getUsername()+"' on the service '"
						+membership.getApplication().getWebAddress()+"' " +
						"to the foaf:Person "+person.getUri()+".");
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		} else {
			user.updateMembership(membership);
			update(user);
		}
		return null;
	}

	public DeleteCommand<Membership> getMembershipDeleter() {
		return membershipDeleter;
	}

	public void setMembershipDeleter(DeleteCommand<Membership> membershipDeleter) {
		this.membershipDeleter = membershipDeleter;
	}
}

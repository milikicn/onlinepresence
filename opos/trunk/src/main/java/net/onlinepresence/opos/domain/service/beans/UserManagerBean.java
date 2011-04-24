package net.onlinepresence.opos.domain.service.beans;

import java.util.List;

import net.onlinepresence.opos.core.spring.SpringBean;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.domain.User;
import net.onlinepresence.opos.domain.service.UserManager;
import net.onlinepresence.opos.service.crud.impl.DeleteCommand;

import org.apache.tapestry5.ioc.annotations.Inject;

public class UserManagerBean 
	extends AbstractManagerBean<User>
	implements UserManager {
	
	@Inject
	@SpringBean("net.onlinepresence.opos.service.crud.impl.DeleteCommand")
	private DeleteCommand<Membership> membershipDeleter;
	
	public UserManagerBean() {
	}

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
		return (List<Membership>) getReader().getEagerMemberships("from MembershipBean where user=(select user.username from MembershipBean where application='" + 
				app +"'" + "and username='" + username +"')");
	}

	public DeleteCommand<Membership> getMembershipDeleter() {
		return membershipDeleter;
	}

	public void setMembershipDeleter(DeleteCommand<Membership> membershipDeleter) {
		this.membershipDeleter = membershipDeleter;
	}
}

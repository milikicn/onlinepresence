package net.onlinepresence.opos.domain.beans;

import java.util.HashSet;
import java.util.Set;

import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.domain.User;

public class UserBean implements User {
	
	private String name;
	private String email;
	private String username;
	private String password;
	private boolean activated;
	
	private Set<Membership> applicationMemberships = new HashSet<Membership>();

	/**
	 * @param name
	 * @param email
	 * @param username
	 */
	public UserBean(String name, String email, String username, String password, boolean activated) {
		super();
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.activated = activated;
	}
	
	/**
	 */
	public UserBean() {}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		if(name==null)
			throw new IllegalArgumentException("Name is not valid");
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		if(email==null)
			throw new IllegalArgumentException("Email is not valid");
		this.email = email;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		if(username==null || username.length() < 4)
			throw new IllegalArgumentException("Username is not valid");
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		if(password == null || password.length() < 4)
			throw new IllegalArgumentException("Password is not valid");
		this.password = password;
	}
	
	/**
	 * @return the activated
	 */
	public boolean isActivated() {
		return activated;
	}

	/**
	 * @param activated the activated to set
	 */
	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	/**
	 * @return the association
	 */
	public Set<Membership> getApplicationMemberships() {
		return applicationMemberships;
	}
	
	public void setApplicationMemberships(Set<Membership> applicationMemberships) {
		this.applicationMemberships = applicationMemberships;
	}

	/**
	 * @param association the association to add to the list of users associations
	 */
	public boolean addApplicationMembership(Membership membership) {
		if (hasMembership(membership))
				return false;
		return applicationMemberships.add(membership);
	}
	
//	public LinkedList<Application> getApplications(){
//		LinkedList<Application> app = new LinkedList<Application>();
//		for (Membership ass : applicationMemberships) {
//			app.add(ass.getApplication());
//		}
//		return app;
//	}
	
//	public Set<Membership> getApplicationMemberships(String appUrl){
//		LinkedList<Membership> membs = new LinkedList<Membership>();
//		for (Membership m : getApplicationMemberships()) {
//			if(m.getApplication().getWebAdress().toString().equals(appUrl))
//				membs.add(m);
//		}
//		return membs;
//	}
	
	public Membership deleteApplicationMembership(String appUrl){
		for (Membership membership : applicationMemberships) {
			if (membership.getApplication().getWebAddress().equals(appUrl)){
				applicationMemberships.remove(membership);
				return membership;
			}
		}
		return null;
	}
	
	public boolean hasMembership(Membership membership){
//		if(applicationMemberships.contains(membership))
//			return true;
//		return false;
		
		for (Membership m : getApplicationMemberships()) {
			if(m.getApplication().getWebAddress().equals(membership.getApplication().getWebAddress())
					&& m.getUser().getUsername().equals(membership.getUser().getUsername()))
				return true;
		}
		return false;
	}
	
//	public boolean hasApplication(Application application){
//		for (Membership m : getApplicationMemberships()) {
//			if(m.getApplication().getWebAdress().equals(application.getWebAdress()))
//				return true;
//		}
//		return false;
//	}

	public void updateMembership(Membership membership) {
		for (Membership m : applicationMemberships) {
			if (m.getApplication().getWebAddress().toString().equals(membership.getApplication().getWebAddress().toString())){
				m.setUsername(membership.getUsername());
				m.setReceiveFrom(membership.isReceiveFrom());
				m.setSendTo(membership.isSendTo());
				m.setPassword(membership.getPassword());
			}
		}	
	}

	public void deleteApplicationMembership(Membership membership) {
		applicationMemberships.remove(membership);
		
	}


}

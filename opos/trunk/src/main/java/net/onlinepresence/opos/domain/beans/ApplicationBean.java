package net.onlinepresence.opos.domain.beans;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import net.onlinepresence.opos.domain.Application;
import net.onlinepresence.opos.domain.ApplicationNames;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.domain.User;

public class ApplicationBean implements Application {
	
	private ApplicationNames name;
	private String webAddress;
	private Set<Membership> userMemberships = new HashSet<Membership>();
	
	/**
	 */
	public ApplicationBean() {}
	
	/**
	 * @param name
	 * @param webAdress
	 */
	public ApplicationBean(ApplicationNames name, String webAdress) {
		this.name = name;
		this.webAddress = webAdress;
	}
	
	/**
	 * @return the name
	 */
	public ApplicationNames getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(ApplicationNames name) {
		if(name==null)
			throw new IllegalArgumentException("Name is not valid");
		this.name = name;
	}

	/**
	 * @return the webAdress
	 */
	public String getWebAddress() {
		return webAddress;
	}

	/**
	 * @param webAdress the webAdress to set
	 */
	public void setWebAddress(String webAdress) {
		if(webAdress==null)
			throw new IllegalArgumentException("Web adress is not valid");
		this.webAddress = webAdress;
	}

	/**
	 * @return the user memberships
	 */
	public Set<Membership> getUserMemberships() {
		return userMemberships;
	}

	/**
	 * @param MS-10342 MSI the user memberships to set
	 */
	public void setUserMemberships(Set<Membership> userMemberships) {
		this.userMemberships = userMemberships;
	}

	/**
	 * @param association the association to set
	 */
	public boolean addUserMembership(Membership membership) {
		if (hasMembership(membership))
			return false;
		return userMemberships.add(membership);
	}
	
	public void deleteMembership(User user){
		for (Membership ass : userMemberships) {
			if (ass.getUser().getUsername().equals(user.getUsername())){
				userMemberships.remove(ass);
				return;
			}
		}
	}
	
	public boolean hasMembership(Membership membership){
		if(userMemberships.contains(membership))
			return true;
		return false;
	}
	
	public boolean hasUser(User user){
		for (Membership m : getUserMemberships()){
			if(m.getUser().getUsername().equals(user.getUsername()))
				return true;
		}
		return false;
	}
}

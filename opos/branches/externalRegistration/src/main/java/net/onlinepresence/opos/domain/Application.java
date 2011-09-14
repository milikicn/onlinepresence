package net.onlinepresence.opos.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Application implements Serializable  {
	
	private static final long serialVersionUID = -8135353809885897235L;
	
	private String name;
	private String webAddress;
	private Set<Membership> userMemberships = new HashSet<Membership>();
	
	public Application() {}
	
	/**
	 * @param name
	 * @param webAdress
	 */
	public Application(String name, String webAdress) {
		this.name = name;
		this.webAddress = webAdress;
	}
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((webAddress == null) ? 0 : webAddress.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
        if ( !(other instanceof Application) ) return false;

        final Application app = (Application) other;

        if ( !app.getName().equals( getName() ) ) return false;
        if ( !app.getWebAddress().equals( getWebAddress() ) ) return false;

        return true;
	}
}

package net.onlinepresence.opos.domain;

import java.util.Set;

public interface User {

	String getName();
	void setName(String name);
	
	String getEmail();
	void setEmail(String email);
	
	String getUsername();
	void setUsername(String username);
	
	String getPassword();
	void setPassword(String password);
	
	boolean isActivated();
	void setActivated(boolean activated);
	
	Set<Membership> getApplicationMemberships();	
	void setApplicationMemberships(Set<Membership> memberships);
	boolean addApplicationMembership(Membership membership);
	void deleteApplicationMembership(Membership membership);
	Membership deleteApplicationMembership(ApplicationNames applicationName);
	void updateMembership(Membership membership);
	boolean hasMembership(Membership memb);
}

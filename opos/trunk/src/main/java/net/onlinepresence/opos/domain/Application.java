package net.onlinepresence.opos.domain;

import java.util.List;
import java.util.Set;

public interface Application {

	ApplicationNames getName();
	void setName(ApplicationNames name);
	
	String getWebAddress();
	void setWebAddress(String webAdress);
	
	Set<Membership> getUserMemberships();
	void setUserMemberships(Set<Membership> userMembership);
	
	boolean addUserMembership(Membership membership);
	void deleteMembership(User user);
}

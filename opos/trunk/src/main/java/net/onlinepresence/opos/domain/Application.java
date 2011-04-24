package net.onlinepresence.opos.domain;

import java.util.List;

public interface Application {

	ApplicationNames getName();
	void setName(ApplicationNames name);
	
	String getWebAddress();
	void setWebAddress(String webAdress);
	
	List<Membership> getUserMemberships();
	void setUserMemberships(List<Membership> userMembership);
	
	boolean addUserMembership(Membership membership);
	void deleteMembership(User user);
}

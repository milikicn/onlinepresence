package net.onlinepresence.opos.domain.service;

import java.util.List;

import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.domain.User;

public interface UserManager {

	void update(User user);
	
	void addUser(User p);
	
	void removeUser(User user);
	
	User findUser(String usernameOrEmail);
	
	List<User> getUsers();
	
	void deleteApplicationMemberhsip(Membership mem);
	
	boolean existsUser(String username);
	
	List<Membership> getAllMemberships(String username);
	
	Membership createOrUpdateNewMembership(User user, Membership membership);
	
	String getUsernameOnApplication(String requestedAppName, String knownAppName, String knownUsername);
}

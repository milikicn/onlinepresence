/**
 * @author: Nikola Milikic
 * e-mail: nikola.milikic@gmail.com
 * @date: Mar 13, 2009
 * @version: 0.1
 */
package net.onlinepresence.opos.util;

import net.onlinepresence.opos.domain.Administrator;
import net.onlinepresence.opos.domain.Key;
import net.onlinepresence.opos.domain.User;
import net.onlinepresence.opos.domain.service.AdministratorManager;
import net.onlinepresence.opos.domain.service.KeyManager;
import net.onlinepresence.opos.domain.service.UserManager;

/**
 * @author Nikola Milikic
 * 
 */
public class Authentication {

	private UserManager persons;
	private KeyManager keys;
	private AdministratorManager administrators;
	private boolean userExists;

	public Authentication(UserManager p) {
		 this.persons = p;
	}
	
	public Authentication(KeyManager k) {
		 this.keys = k;
	}
	
	public Authentication(AdministratorManager a) {
		 this.administrators = a;
	}

	public User authenticateUser(String usernameOrEmail, String password) {
		
		User user = persons.findUser(usernameOrEmail);
		 
		if(user == null){
			setUserExists(false);
			return null;
		}
		
		if (user.getPassword().equals(password))
			return (User)user;
		
		setUserExists(true);
		return null;
	}
	
	public boolean authenticateKey(String email, String key){
		Key dbKey = keys.getKey(email);
		if(dbKey == null)
			return false;
		
		if(dbKey.getKey().equals(key))
			return true;
		return false;
	}
	
	public Administrator authenticateAdmin(String username, String password) {
		
		Administrator admin = administrators.getAdministrator(username);
		 
		if(admin == null){
			return null;
		}
		
		if (admin.getPassword().equals(password))
			return (Administrator)admin;
		return null;
	}

	/**
	 * @return the persons
	 */
	public UserManager getPersons() {
		return persons;
	}

	/**
	 * @param persons
	 *            the persons to set
	 */
	public void setPersons(UserManager persons) {
		this.persons = persons;
	}

	public KeyManager getKeys() {
		return keys;
	}

	public void setKeys(KeyManager keys) {
		this.keys = keys;
	}

	public AdministratorManager getAdministrators() {
		return administrators;
	}

	public void setAdministrators(AdministratorManager administrators) {
		this.administrators = administrators;
	}

	public boolean isUserExists() {
		return userExists;
	}

	public void setUserExists(boolean userExists) {
		this.userExists = userExists;
	}

}

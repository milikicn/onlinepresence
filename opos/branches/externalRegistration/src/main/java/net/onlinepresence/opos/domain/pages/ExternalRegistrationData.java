package net.onlinepresence.opos.domain.pages;

import net.onlinepresence.opos.domain.User;

public class ExternalRegistrationData {

	private String name;
	private String email;
	private String username;
	private String password;
	private String callbackUrl;
	private String registerTo;
	
	/**
	 * @param name
	 * @param email
	 * @param username
	 * @param password
	 * @param callbackUrl
	 * @param registerTo
	 */
	public ExternalRegistrationData(String name, String email, String username,
			String password, String callbackUrl, String registerTo) {
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.callbackUrl = callbackUrl;
		this.registerTo = registerTo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getRegisterTo() {
		return registerTo;
	}

	public void setRegisterTo(String registerTo) {
		this.registerTo = registerTo;
	}
	
	@Override
	public String toString() {
		return "ExternalRegistrationData [name=" + name + ", email=" + email
				+ ", username=" + username + ", password=" + password
				+ ", callbackUrl=" + callbackUrl + ", registerTo=" + registerTo
				+ "]";
	}

	/**
	 * Returns true if there is enough data for registration process to 
	 * be conducted. Only registerTo is optional.
	 * 
	 * @return
	 */
	public boolean hasEnoughDataForRegistration() {
		return 
			getName() != null &&
			getEmail() != null &&
			getUsername() != null &&
			getPassword() != null &&
			getCallbackUrl() != null;
	}
	
	public User getUser(User user) {
		user.setName(name);
		user.setEmail(email);
		user.setUsername(username);
		user.setPassword(password);
		
		return user;
	}
}

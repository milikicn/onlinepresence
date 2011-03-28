package net.onlinepresence.opos.domain.beans;

import net.onlinepresence.opos.domain.Administrator;

public class AdministratorBean implements Administrator {

	private String username;
	
	private String password;
	
	public AdministratorBean() {
	}

	public AdministratorBean(String username, String password) {
		this.username = username;
		this.password = password;
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
	
	


}

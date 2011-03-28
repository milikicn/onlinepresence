package net.onlinepresence.opos.domain.beans;

import net.onlinepresence.opos.domain.Key;

public class KeyBean implements Key{

	private String email;
	
	private String key;
	
	public KeyBean() {
	}

	public KeyBean(String email, String key) {
		this.email = email;
		this.key = key;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	
}

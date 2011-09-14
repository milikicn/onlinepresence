/**
* @author: Nikola Milikic
* e-mail: nikola.milikic@gmail.com
* @date: Apr 6, 2009
* @version: 0.1
*/
package net.onlinepresence.opos.domain;

import java.io.Serializable;

/**
 * @author Nikola Milikic
 *
 */
public class Membership implements Serializable {

	private static final long serialVersionUID = 7000155982915301422L;
	
	private Application application;
	private User user;
	private String username;
	private String password;
	private boolean sendTo;
	private boolean receiveFrom;
	private String accessToken;
	private String secretToken;
	
	public Membership() {
		super();
	}
	
	/**
	 * @param application
	 * @param user
	 * @param username
	 */
	public Membership(Application application, 
			User user,
			String username, 
			String password, 
			boolean sendTo, 
			boolean receiveFrom, 
			String accessToken, 
			String secretToken) {
		super();
		this.application = application;
		this.user = user;
		this.username = username;
		this.sendTo = sendTo;
		this.receiveFrom = receiveFrom;
		this.password = password;
		this.accessToken = accessToken;
		this.secretToken = secretToken;
	}
	
	/**
	 * @return the application
	 */
	public Application getApplication() {
		return application;
	}
	/**
	 * @param application the application to set
	 */
	public void setApplication(Application application) {
		this.application = application;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isSendTo() {
		return sendTo;
	}

	public void setSendTo(boolean sendTo) {
		this.sendTo = sendTo;
	}

	public boolean isReceiveFrom() {
		return receiveFrom;
	}

	public void setReceiveFrom(boolean receiveFrom) {
		this.receiveFrom = receiveFrom;
	}

	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}
	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	/**
	 * @return the secretToken
	 */
	public String getSecretToken() {
		return secretToken;
	}
	/**
	 * @param secretToken the secretToken to set
	 */
	public void setSecretToken(String secretToken) {
		this.secretToken = secretToken;
	}
	
	@Override
	public String toString() {
		return "user: " + user.getUsername() + ", app: " + application.getName() + ", username: " + username;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accessToken == null) ? 0 : accessToken.hashCode());
		result = prime * result
				+ ((application == null) ? 0 : application.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + (receiveFrom ? 1231 : 1237);
		result = prime * result
				+ ((secretToken == null) ? 0 : secretToken.hashCode());
		result = prime * result + (sendTo ? 1231 : 1237);
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Membership))
			return false;
		Membership other = (Membership) obj;
		if (accessToken == null) {
			if (other.accessToken != null)
				return false;
		} else if (!accessToken.equals(other.accessToken))
			return false;
		if (application == null) {
			if (other.application != null)
				return false;
		} else if (!application.equals(other.application))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (receiveFrom != other.receiveFrom)
			return false;
		if (secretToken == null) {
			if (other.secretToken != null)
				return false;
		} else if (!secretToken.equals(other.secretToken))
			return false;
		if (sendTo != other.sendTo)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
}

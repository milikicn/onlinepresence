/**
* @author: Nikola Milikic
* e-mail: nikola.milikic@gmail.com
* @date: Apr 6, 2009
* @version: 0.1
*/
package net.onlinepresence.opos.domain.beans;

import net.onlinepresence.opos.domain.Application;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.domain.User;

/**
 * @author Nikola Milikic
 *
 */
public class MembershipBean implements Membership{

	private static final long serialVersionUID = 966655609199967347L;
	private Application application;
	private User user;
	private String username;
	private String password;
	private boolean sendTo;
	private boolean receiveFrom;
	private String accessToken;
	private String secretToken;
	
	public MembershipBean() {
		super();
	}
	
	/**
	 * @param application
	 * @param user
	 * @param username
	 */
	public MembershipBean(Application application, 
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
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		
		if (obj instanceof Membership){
			Membership mem = (Membership) obj;
			if(getUser().getUsername().equals(mem.getUser().getUsername()) && 
					getApplication().getWebAddress().equals(mem.getApplication().getWebAddress()))
				return true;
			else
				return false;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "user: " + user.getUsername() + ", app: " + application.getName() + ", username: " + username;
	}
}

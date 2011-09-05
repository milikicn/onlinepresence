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
public interface Membership extends Serializable{

	Application getApplication();
	void setApplication(Application application);
	
	User getUser();
	void setUser(User user);
	
	String getUsername();
	void setUsername(String username);
	
	String getPassword();
	void setPassword(String password);
	
	boolean isSendTo();
	void setSendTo(boolean sendTo);
	
	boolean isReceiveFrom();
	void setReceiveFrom(boolean receiveFrom);
	
	String getAccessToken();
	void setAccessToken(String accessToken);
	
	String getSecretToken();
	void setSecretToken(String ecretToken);
}

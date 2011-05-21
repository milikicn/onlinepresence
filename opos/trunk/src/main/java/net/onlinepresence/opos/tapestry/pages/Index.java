package net.onlinepresence.opos.tapestry.pages;

import net.onlinepresence.opos.domain.beans.LoggedAdminBean;
import net.onlinepresence.opos.domain.beans.LoggedUserBean;

import org.apache.tapestry5.annotations.SessionState;

/**
 * Start page of application opos.
 */
public class Index {

	@SuppressWarnings("unused")
	@SessionState
	private LoggedUserBean loggerUser;
	private boolean loggedUserExists;
	
	@SuppressWarnings("unused")
	@SessionState
	private LoggedAdminBean loggedAdmin;
	
	Object onActivate() {
		if (loggedUserExists)
			return Connections.class;
		
		return null;
	}
	
}

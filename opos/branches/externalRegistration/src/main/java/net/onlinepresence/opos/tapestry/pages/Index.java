package net.onlinepresence.opos.tapestry.pages;

import net.onlinepresence.opos.domain.LoggedAdmin;
import net.onlinepresence.opos.domain.LoggedUser;

import org.apache.tapestry5.annotations.SessionState;

/**
 * Start page of application opos.
 */
public class Index {

	@SuppressWarnings("unused")
	@SessionState
	private LoggedUser loggerUser;
	private boolean loggedUserExists;
	
	@SuppressWarnings("unused")
	@SessionState
	private LoggedAdmin loggedAdmin;
	
	Object onActivate() {
		if (loggedUserExists)
			return Connections.class;
		
		return null;
	}
	
}

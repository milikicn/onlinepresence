package net.onlinepresence.opos.tapestry.pages;

import java.util.Date;

import net.onlinepresence.opos.domain.User;
import net.onlinepresence.opos.domain.beans.LoggedAdminBean;
import net.onlinepresence.opos.domain.beans.LoggedUserBean;
import net.onlinepresence.opos.domain.beans.UserBean;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.annotations.SetupRender;

/**
 * Start page of application opos.
 */
public class Index {

	public Date getCurrentTime() {
		return new Date();
	}
	
	
	@SuppressWarnings("unused")
	@SessionState
	private LoggedUserBean loggerUser;
	
	@SuppressWarnings("unused")
	@SessionState
	private LoggedAdminBean loggedAdmin;

	@SuppressWarnings("unused")
	@Property
	@Persist("flash")
	private User user;


	@SetupRender
	public void createObject() {
		user = new UserBean();
	}

	
}

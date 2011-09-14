package net.onlinepresence.opos.tapestry.pages;

import net.onlinepresence.opos.core.spring.SpringBean;
import net.onlinepresence.opos.domain.LoggedUser;
import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.domain.service.ApplicationManager;
import net.onlinepresence.opos.domain.service.UserManager;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

@Import(library= { 
		"context:js/jquery.min.js", 
		"context:js/login.js",
		"context:js/jquery.fancybox-1.3.0.js",
		"context:js/jquery.fancybox-1.3.0.activation.js" },
		stylesheet= { 
//			"context:css/style.css", 
//			"context:css/front.css",
			"context:css/jquery.fancybox-1.3.0.css" })
public class Account {

	@Property
	@SessionState
	private LoggedUser loggedUser;
	private boolean loggedUserExists;
	
	@Inject
	@SpringBean("net.onlinepresence.opos.domain.service.UserManager")
	private UserManager persons;
	
	@SuppressWarnings("unused")
	@Inject
	@SpringBean("net.onlinepresence.opos.domain.service.ApplicationManager")
	private ApplicationManager apps;
	
	@SuppressWarnings("unused")
	@Property
	private Membership currentMembership;
	
	@Property
	private String passwordConfirmation;	
	@Property
	private String password;

	Object onActivate() {
		if (!loggedUserExists)
			return Login.class;
		return null;
	}
	
	Object onSubmitFromUserDetailsForm(){
		if(!password.equals(passwordConfirmation))
			return null;		
		
		loggedUser.getUser().setPassword(password);
		persons.update(loggedUser.getUser());		
		
		return null;
	}
	
	Object onSubmitFromDeleteAccount(){
		persons.removeUser(loggedUser.getUser());
		loggedUser = null;
		return Index.class;		
	}
	
}

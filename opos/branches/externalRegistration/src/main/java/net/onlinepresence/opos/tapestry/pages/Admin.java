package net.onlinepresence.opos.tapestry.pages;

import net.onlinepresence.opos.core.spring.SpringBean;
import net.onlinepresence.opos.domain.Administrator;
import net.onlinepresence.opos.domain.LoggedAdmin;
import net.onlinepresence.opos.domain.service.AdministratorManager;
import net.onlinepresence.opos.util.Authentication;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

public class Admin {
	
	@Inject
	@SpringBean("net.onlinepresence.opos.domain.service.AdministratorManager")
	private AdministratorManager administrators;

	@Property
	private String username;
	
	@Property
	private String password;
	
	@SessionState
	private LoggedAdmin loggedAdmin;
	
	Object onSubmitFromLoginForm(){
		Authentication auth = new Authentication(administrators);

		Administrator a = auth.authenticateAdmin(username, password);

		if (a != null) {
			loggedAdmin.setAdmin(a);
			return Invitations.class;
		} else {
			return Index.class;
		}
	}
}

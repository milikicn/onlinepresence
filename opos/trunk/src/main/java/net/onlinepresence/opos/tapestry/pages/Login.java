package net.onlinepresence.opos.tapestry.pages;

import net.onlinepresence.opos.core.spring.SpringBean;
import net.onlinepresence.opos.domain.User;
import net.onlinepresence.opos.domain.beans.LoggedUserBean;
import net.onlinepresence.opos.domain.service.Users;
import net.onlinepresence.opos.util.Authentication;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;

public class Login {

	@Property
	private String usernameOrEmail;

	@Property
	private String password;

	@Inject
	@SpringBean("net.onlinepresence.opos.domain.service.Users")
	private Users persons;

	@SessionState
	private LoggedUserBean loggedUser;

	Object onSubmitFromLoginForm() {
		
		Authentication auth = new Authentication(persons);

		User u = auth.authenticateUser(usernameOrEmail, password);


		if (u != null) {
			loggedUser.setUser(u);
			return Connections.class;
		} else {
			if (!auth.isUserExists())
				return Registration.class;
			else
				return null;
		}
	}

}

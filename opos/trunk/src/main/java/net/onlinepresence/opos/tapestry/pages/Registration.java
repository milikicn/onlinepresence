package net.onlinepresence.opos.tapestry.pages;

import net.onlinepresence.opos.core.spring.SpringBean;
import net.onlinepresence.opos.domain.User;
import net.onlinepresence.opos.domain.beans.LoggedUserBean;
import net.onlinepresence.opos.domain.beans.UserBean;
import net.onlinepresence.opos.domain.service.KeyManager;
import net.onlinepresence.opos.domain.service.UserManager;
import net.onlinepresence.opos.semanticstuff.services.OnlinePresenceService;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.Field;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PasswordField;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.annotations.Inject;

public class Registration {

	@Component(id = "registrationForm")
	private Form registrationForm;

	@Component(id = "username")
	private TextField usernameF;
	
	@Component(id = "password")
	private PasswordField passwordF;
	
//	@InjectComponent(value = "key")
//	private Field keyF;

	@SuppressWarnings("unused")
	@Component(id = "passwordConfirmation")
	private PasswordField passwordConfirmationF;
	
	@Property
	@Persist("flash")
	private User user;

	@Inject
	@SpringBean("net.onlinepresence.opos.domain.service.UserManager")
	private UserManager userManager;
	
	@Inject
	@SpringBean("net.onlinepresence.opos.domain.service.KeyManager")
	@SuppressWarnings("unused")
	private KeyManager keyManager;

	@SessionState
	private LoggedUserBean loggedUser;

	@Property
	private String passwordConfirmation;
	
//	@Property
//	private String enteredKey;
	
	@SetupRender
	public void createObject() {
		user = new UserBean();
	}
	
	@OnEvent(value = EventConstants.VALIDATE, component = "registrationForm")
	public void onValidate() {
		if (user.getUsername() != null && user.getPassword() != null && user.getUsername().trim().compareToIgnoreCase(user.getPassword().trim()) == 0) {
			registrationForm.recordError(passwordF, "User password cannot be its username");
		}
		// Do a first check
		if (user.getUsername() != null && userManager.existsUser(user.getUsername())) {
			registrationForm.recordError(usernameF, "User already exists");
		}
		
//		if (enteredKey == null || enteredKey.length() < 6) {
//			registrationForm.recordError(keyF, "Incorect key");
//		}
		
		if (user.getPassword() != null && passwordConfirmation != null && user.getPassword().trim().compareToIgnoreCase(passwordConfirmation.trim()) != 0) {
			registrationForm.recordError(usernameF, "Confirm password is not correct");
		}
	}

	@OnEvent(component = "registrationForm")
	Object onSubmitFromRegistrationForm() {
//		Authentication auth = new Authentication(keyManager);
		
		// disabled for now for easier development
//		if (auth.authenticateKey(user.getEmail(), enteredKey))
			return registerUser();
		
//		return null;
	}
	
	Object registerUser(){
		if(!passwordConfirmation.equals(user.getPassword()))
			return null;

		if (!userManager.existsUser(user.getUsername())) {
			userManager.addUser(user);
			loggedUser.setUser(user);
//			keyManager.removeKey(user.getEmail());
			
			OnlinePresenceService opService = new OnlinePresenceService();
			opService.registerUserAndOposAccount(user);
			
			return Connections.class;
		} else
			return Registration.class;
	}

}

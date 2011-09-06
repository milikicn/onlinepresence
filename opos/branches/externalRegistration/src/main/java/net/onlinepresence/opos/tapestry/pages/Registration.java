package net.onlinepresence.opos.tapestry.pages;

import java.net.URL;

import net.onlinepresence.opos.core.spring.SpringBean;
import net.onlinepresence.opos.domain.ApplicationNames;
import net.onlinepresence.opos.domain.User;
import net.onlinepresence.opos.domain.beans.LoggedUserBean;
import net.onlinepresence.opos.domain.beans.UserBean;
import net.onlinepresence.opos.domain.pages.ExternalRegistrationData;
import net.onlinepresence.opos.domain.service.KeyManager;
import net.onlinepresence.opos.domain.service.UserManager;
import net.onlinepresence.opos.mediatorManagement.mediators.twitter.TwitterCommunication;
import net.onlinepresence.opos.semanticstuff.services.OnlinePresenceService;
import net.onlinepresence.opos.service.RegistrationService;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.PasswordField;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;

import twitter4j.Twitter;

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
	
//	@SetupRender
//	public void createObject() {
//		user = new UserBean();
//	}
	
	@Inject
	private Request request;
	
	@SessionState
	private ExternalRegistrationData externalRegData;
	
	@SessionState
	private Twitter twitter;
	
	public URL onActivate() {
		user = new UserBean();

		// if external registration is initiated
		externalRegData = new ExternalRegistrationData(
											request.getParameter("name"), 
											request.getParameter("email"), 
											request.getParameter("username"), 
											request.getParameter("password"), 
											request.getParameter("callbackUrl"), 
											request.getParameter("registerTo"));
		
		
		if (externalRegData.hasEnoughDataForRegistration()){
			user = externalRegData.getUser(user);
			
			if (!userManager.existsUser(request.getParameter("username")))
				registerUser();
			
			loggedUser.setUser(user);
			
			if (externalRegData.getRegisterTo() != null) {
				RegistrationService registrationService = new RegistrationService();
				ApplicationNames applicationName = ApplicationNames.valueOf(externalRegData.getRegisterTo().toUpperCase());
				
				if (applicationName != null) {
					switch (applicationName) {
						case TWITTER:
							twitter = TwitterCommunication.getInstance().getTwitterFactory().getInstance();
							return registrationService.registerOnTwitter(twitter);
						case FACEBOOK:
							return registrationService.registerOnFacebook();
					}
				}
			}
		}
		
		return null;
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
		if(!passwordConfirmation.equals(user.getPassword()))
			return null;
		
		if (!userManager.existsUser(user.getUsername())) {
			boolean registrationSuccessful = registerUser();
			loggedUser.setUser(user);
			
			if (registrationSuccessful)
				return Connections.class;
		}
			
		return Registration.class;
	}
	
	private boolean registerUser(){
		userManager.addUser(user);
//		keyManager.removeKey(user.getEmail());
		
		OnlinePresenceService opService = new OnlinePresenceService();
		opService.registerUserAndOposAccount(user);
		return true;
	}

}

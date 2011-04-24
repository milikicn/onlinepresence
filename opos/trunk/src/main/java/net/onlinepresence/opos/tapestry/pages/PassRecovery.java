package net.onlinepresence.opos.tapestry.pages;

import net.onlinepresence.opos.core.spring.SpringBean;
import net.onlinepresence.opos.domain.service.UserManager;

import org.apache.tapestry5.Field;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;

public class PassRecovery {
	
	@InjectComponent
	private Form passRecoveryForm;
	
	@InjectComponent(value = "email")
	private Field emailF;

	@Inject
	@SpringBean("net.onlinepresence.opos.domain.service.UserManager")
	private UserManager users;
	
	@Property
	private String email;
	
	Object onSubmitFromPassRecoveryForm(){		
		try {
			//TODO send pass recovery email
			String password = users.findUser(email).getPassword();
		} catch (NullPointerException e) {
			passRecoveryForm.recordError(emailF, "Wrong email");
			return null;
		}		
		return Index.class;		
	}

}

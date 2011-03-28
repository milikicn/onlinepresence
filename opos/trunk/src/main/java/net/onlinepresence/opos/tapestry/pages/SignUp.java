package net.onlinepresence.opos.tapestry.pages;

import net.onlinepresence.opos.core.spring.SpringBean;
import net.onlinepresence.opos.domain.Key;
import net.onlinepresence.opos.domain.beans.KeyBean;
import net.onlinepresence.opos.domain.service.Keys;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

public class SignUp {

	@Property
	private String email;
	
	@Inject
	@SpringBean("net.onlinepresence.opos.domain.service.Keys")
	private Keys keys;
	
	@Property
	private String bot;
	
	Object onSubmitFromSignUpForm(){
		if(bot==null || !bot.equals("opos"))
			return null;
		Key k = new KeyBean(email, null);
		keys.addKey(k);
		return Index.class;
	}
}

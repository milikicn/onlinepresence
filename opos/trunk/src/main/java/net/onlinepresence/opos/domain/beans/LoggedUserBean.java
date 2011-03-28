package net.onlinepresence.opos.domain.beans;

import org.apache.tapestry5.ioc.annotations.Inject;

import net.onlinepresence.opos.core.spring.SpringBean;
import net.onlinepresence.opos.domain.User;
import net.onlinepresence.opos.domain.service.Users;

public class LoggedUserBean {

	private User user;
	
	@Inject
	@SpringBean("net.onlinepresence.opos.domain.service.Users")
	private Users service;

	public User getUser() {
		return service.getUser(user.getUsername());
	}

	public void setUser(User user) {
		this.user = user;
	}

//	public Membership deleteApplicationMembership(String mem) {
//		return person.deleteApplicationMembership(mem);
//	}
//
//	public void setMemberships() {
//		for (Membership m : getPerson().getApplicationMemberships()) {
//			m.getApplication();
//			m.getPerson();
//		}
//		
//	}
	
}

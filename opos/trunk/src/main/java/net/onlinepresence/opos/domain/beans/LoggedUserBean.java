package net.onlinepresence.opos.domain.beans;

import org.apache.tapestry5.ioc.annotations.Inject;

import net.onlinepresence.opos.core.spring.SpringBean;
import net.onlinepresence.opos.domain.User;
import net.onlinepresence.opos.domain.service.UserManager;

public class LoggedUserBean {

	private User user;
	
	@Inject
	@SpringBean("net.onlinepresence.opos.domain.service.UserManager")
	private UserManager service;

	public User getUser() {
		return service.findUser(user.getUsername());
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof LoggedUserBean))
			return false;
		LoggedUserBean other = (LoggedUserBean) obj;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
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

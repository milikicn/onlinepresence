package net.onlinepresence.opos.domain.beans;

import net.onlinepresence.opos.domain.Administrator;

public class LoggedAdminBean {

	private Administrator admin;

	public Administrator getAdmin() {
		return admin;
	}

	public void setAdmin(Administrator admin) {
		this.admin = admin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((admin == null) ? 0 : admin.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof LoggedAdminBean))
			return false;
		LoggedAdminBean other = (LoggedAdminBean) obj;
		if (admin == null) {
			if (other.admin != null)
				return false;
		} else if (!admin.equals(other.admin))
			return false;
		return true;
	}
	
}

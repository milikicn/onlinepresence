package net.onlinepresence.opos.domain.service.beans;

import java.util.List;

import net.onlinepresence.opos.domain.Administrator;
import net.onlinepresence.opos.domain.service.AdministratorManager;

public class AdministratorManagerBean 
	extends AbstractManagerBean<Administrator>
	implements AdministratorManager {

	public Administrator getAdministrator() {
		return null;
	}

	public Administrator getAdministrator(String username) {
		List<Administrator> admins = getAdministrators();		
		for (Administrator administrator : admins) {
			if(administrator.getUsername().equals(username))
				return administrator;
		}
		return null;
	}

	public List<Administrator> getAdministrators() {
		getReader().setClazz(Administrator.class);
		return getReader().execute();
	}
}

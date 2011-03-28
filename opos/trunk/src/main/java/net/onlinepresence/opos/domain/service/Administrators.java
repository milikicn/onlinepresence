package net.onlinepresence.opos.domain.service;

import java.util.List;

import net.onlinepresence.opos.domain.Administrator;

public interface Administrators {

	Administrator getAdministrator(String username);
	
	List<Administrator> getAdministrators();
}

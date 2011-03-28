package net.onlinepresence.opos.domain.service.beans;

import java.util.List;

import net.onlinepresence.opos.core.spring.SpringBean;
import net.onlinepresence.opos.domain.Administrator;
import net.onlinepresence.opos.domain.service.Administrators;
import net.onlinepresence.opos.service.crud.impl.ReadCommand;

import org.apache.tapestry5.ioc.annotations.Inject;

public class AdministratorsBean implements Administrators{

	@Inject
	@SpringBean("net.onlinepresence.opos.service.crud.impl.ReadCommand")
	private ReadCommand<Administrator> reader;

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
		reader.setClazz(Administrator.class);
		return reader.execute();
	}

	public ReadCommand<Administrator> getReader() {
		return reader;
	}

	public void setReader(ReadCommand<Administrator> reader) {
		this.reader = reader;
	}
}

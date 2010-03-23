package net.onlinepresence.domainmodel.sioc.interfaces;

import java.net.URI;

import net.onlinepresence.domainmodel.general.ResourceBean;

public interface UserBean extends ResourceBean{

	URI getAccountServiceHomepage();
	void setAccountServiceHomepage(URI accountServiceHomepage);
	void setAccountServiceHomepage(String accountServiceHomepage);
	
	String getAccountName();
	void setAccountName(String accountName);
}

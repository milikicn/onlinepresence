package net.onlinepresence.domainmodel.sioc.interfaces;

import net.onlinepresence.domainmodel.foaf.interfaces.DocumentBean;
import net.onlinepresence.domainmodel.general.ResourceBean;

public interface UserBean extends ResourceBean{

	DocumentBean getAccountServiceHomepage();
	void setAccountServiceHomepage(DocumentBean accountServiceHomepage);
	void setAccountServiceHomepage(String accountServiceHomepage);
	
	String getAccountName();
	void setAccountName(String accountName);
}

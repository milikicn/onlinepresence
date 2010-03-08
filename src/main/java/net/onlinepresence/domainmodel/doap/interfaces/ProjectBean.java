package net.onlinepresence.domainmodel.doap.interfaces;

import net.onlinepresence.domainmodel.foaf.interfaces.DocumentBean;
import net.onlinepresence.domainmodel.general.ResourceBean;

public interface ProjectBean extends ResourceBean {
	
	DocumentBean getHomepage();
	void setHomepage(DocumentBean homepage);

	String getName();
	void setName(String name);

	String getDateCreated();
	void setDateCreated(String dateCreated);

	String getShortDescription();
	void setShortDescription(String shortDescription);
	
}

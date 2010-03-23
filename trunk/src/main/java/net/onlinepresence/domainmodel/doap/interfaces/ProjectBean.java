package net.onlinepresence.domainmodel.doap.interfaces;

import java.net.URI;

import net.onlinepresence.domainmodel.general.ResourceBean;

public interface ProjectBean extends ResourceBean {
	
	URI getHomepage();
	void setHomepage(URI homepage);
	void setHomepage(String homepage);

	String getName();
	void setName(String name);

	String getDateCreated();
	void setDateCreated(String dateCreated);

	String getShortDescription();
	void setShortDescription(String shortDescription);
	
}

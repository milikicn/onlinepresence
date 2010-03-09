package net.onlinepresence.domainmodel.foaf.interfaces;

import java.net.URI;

import net.onlinepresence.domainmodel.general.ResourceBean;

public interface ThingBean extends ResourceBean{

	String getName();
	void setName(String name);
	
	URI getHomepage();
	void setHomepage(URI homepage);
	void setHomepage(String homepage);

	URI getIsPrimaryTopicOf();
	void setIsPrimaryTopicOf(URI isPrimaryTopicOf);
	void setIsPrimaryTopicOf(String isPrimaryTopicOf);
}

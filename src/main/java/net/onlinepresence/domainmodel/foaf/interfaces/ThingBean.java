package net.onlinepresence.domainmodel.foaf.interfaces;

import net.onlinepresence.domainmodel.general.ResourceBean;

public interface ThingBean extends ResourceBean{

	String getName();
	void setName(String name);
	
	DocumentBean getHomepage();
	void setHomepage(DocumentBean homepage);
	void setHomepage(String homepage);

	DocumentBean getIsPrimaryTopicOf();
	void setIsPrimaryTopicOf(DocumentBean isPrimaryTopicOf);
	void setIsPrimaryTopicOf(String isPrimaryTopicOf);
}

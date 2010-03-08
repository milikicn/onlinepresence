package net.onlinepresence.domainmodel.foaf.interfaces;

import java.net.URL;

public interface ThingBean {

	String getName();
	void setName(String name);
	
	URL getHomepage();
	void setHomepage(URL homepage);

	DocumentBean getIsPrimaryTopicOf();
	void setIsPrimaryTopicOf(DocumentBean isPrimaryTopicOf);
}

package net.onlinepresence.domainmodel.interfaces;

import net.onlinepresence.domainmodel.general.ResourceBean;

public interface SourceOfPublishingBean extends ResourceBean{

	public void setName(String sourceName);
	public String getName();
}

package net.onlinepresence.domainmodel.opo.interfaces;

import net.onlinepresence.domainmodel.general.ResourceBean;

/**
 * The source that can publish Online Presence data.
 *
 */
public interface SourceOfPublishingBean extends ResourceBean{

	public void setName(String sourceName);
	public String getName();
}

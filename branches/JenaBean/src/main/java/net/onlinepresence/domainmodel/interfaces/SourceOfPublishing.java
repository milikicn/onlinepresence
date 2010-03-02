package net.onlinepresence.domainmodel.interfaces;

import net.onlinepresence.domainmodel.general.Resource;

public interface SourceOfPublishing extends Resource{

	public void setName(String sourceName);
	public String getName();
}

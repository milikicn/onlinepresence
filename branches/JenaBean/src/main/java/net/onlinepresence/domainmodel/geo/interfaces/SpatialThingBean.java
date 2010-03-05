package net.onlinepresence.domainmodel.geo.interfaces;

import net.onlinepresence.domainmodel.general.ResourceBean;

public interface SpatialThingBean extends ResourceBean{

	String getLatitude();
	void setLatitude(String latitude);
	
	String getLongitude();
	void setLongitude(String longitude);
}

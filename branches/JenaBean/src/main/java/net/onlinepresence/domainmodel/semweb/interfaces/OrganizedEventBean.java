package net.onlinepresence.domainmodel.semweb.interfaces;

import net.onlinepresence.domainmodel.foaf.interfaces.PersonBean;
import net.onlinepresence.domainmodel.general.ResourceBean;
import net.onlinepresence.domainmodel.geo.interfaces.SpatialThingBean;

public interface OrganizedEventBean extends ResourceBean{

	public PersonBean getAttendee();
	public void setAttendee(PersonBean attendee);
	
	public SpatialThingBean getLocation();
	public void setLocation(SpatialThingBean location);
}

package net.onlinepresence.domainmodel.opo.interfaces;

import java.net.URI;

import net.onlinepresence.domainmodel.foaf.interfaces.GroupBean;
import net.onlinepresence.domainmodel.foaf.interfaces.PersonBean;
import net.onlinepresence.domainmodel.geo.interfaces.SpatialThingBean;
import net.onlinepresence.domainmodel.purl.interfaces.EventBean;

/**
 * A group of people belonging to a space for sharing online psresence data - 
 * the intended audience of data.
 *
 */
public interface SharingSpaceBean extends GroupBean{

	public void setEvent(EventBean event);
	public EventBean getEvent();
	
	public void setNearLocation(SpatialThingBean base);
	public SpatialThingBean getNearLocation();
	
	public void setFriend(PersonBean friend);
	public PersonBean getFriend();
	
	public void setCommonInterest(URI commonInterest);
	public void setCommonInterest(String commonInterest);
	public URI getCommonInterest();
	
	public void setCurrentLocation(SpatialThingBean base);
	public SpatialThingBean getCurrentLocation();
	
	public void setFamily(PersonBean family);
	public PersonBean getFamily();
	
	public void setSchoolHomepage(URI schoolHomepage);
	public void setSchoolHomepage(String schoolHomepage);
	public URI getSchoolHomepage();
	
	public void setWorkplaceHomepage(URI workplaceHomepage);
	public void setWorkplaceHomepage(String workplaceHomepage);
	public URI getWorkplaceHomepage();
}

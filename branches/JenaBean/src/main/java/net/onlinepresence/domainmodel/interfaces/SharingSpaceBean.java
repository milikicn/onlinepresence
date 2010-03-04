package net.onlinepresence.domainmodel.interfaces;

import net.onlinepresence.domainmodel.foaf.interfaces.DocumentBean;
import net.onlinepresence.domainmodel.foaf.interfaces.GroupBean;
import net.onlinepresence.domainmodel.foaf.interfaces.PersonBean;
import net.onlinepresence.domainmodel.geo.interfaces.SpatialThingBean;
import net.onlinepresence.domainmodel.purl.interfaces.EventBean;

public interface SharingSpaceBean extends GroupBean{

	public void setEvent(EventBean event);
	public EventBean getEvent();
	
	public void setNearLocation(SpatialThingBean base);
	public SpatialThingBean getNearLocation();
	
	public void setFriend(PersonBean friend);
	public PersonBean getFriend();
	
	public void setCommonInterest(DocumentBean commonInterest);
	public DocumentBean getCommonInterest();
	
	public void setCurrentLocation(SpatialThingBean base);
	public SpatialThingBean getCurrentLocation();
	
	public void setFamily(PersonBean family);
	public PersonBean getFamily();
	
	public void setSchoolHomepage(DocumentBean schoolHomepage);
	public DocumentBean getSchoolHomepage();
	
	public void setWorkplaceHomepage(DocumentBean workplaceHomepage);
	public DocumentBean getWorkplaceHomepage();
}

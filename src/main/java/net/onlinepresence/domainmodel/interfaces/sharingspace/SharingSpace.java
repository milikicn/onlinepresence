package net.onlinepresence.domainmodel.interfaces.sharingspace;

import net.onlinepresence.domainmodel.foaf.interfaces.Document;
import net.onlinepresence.domainmodel.foaf.interfaces.Group;
import net.onlinepresence.domainmodel.foaf.interfaces.Person;
import net.onlinepresence.domainmodel.interfaces.geo.SpatialThing;
import net.onlinepresence.domainmodel.purl.interfaces.Event;

public interface SharingSpace extends Group{

	public void setEvent(Event event);
	public Event getEvent();
	
	public void setNearLocation(SpatialThing base);
	public SpatialThing getNearLocation();
	
	public void setFriend(Person friend);
	public Person getFriend();
	
	public void setCommonInterest(Document commonInterest);
	public Document getCommonInterest();
	
	public void setCurrentLocation(SpatialThing base);
	public SpatialThing getCurrentLocation();
	
	public void setFamily(Person family);
	public Person getFamily();
	
	public void setSchoolHomepage(Document schoolHomepage);
	public Document getSchoolHomepage();
	
	public void setWorkplaceHomepage(Document workplaceHomepage);
	public Document getWorkplaceHomepage();
}

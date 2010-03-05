package net.onlinepresence.domainmodel.opo.pojos;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.foaf.interfaces.DocumentBean;
import net.onlinepresence.domainmodel.foaf.interfaces.PersonBean;
import net.onlinepresence.domainmodel.foaf.pojos.Group;
import net.onlinepresence.domainmodel.geo.interfaces.SpatialThingBean;
import net.onlinepresence.domainmodel.opo.interfaces.SharingSpaceBean;
import net.onlinepresence.domainmodel.purl.interfaces.EventBean;

@Namespace("http://online-presence.net/opo/ns#")
@RdfType("SharingSpace")
public class SharingSpace extends Group implements SharingSpaceBean{

	private EventBean event;
	
	private SpatialThingBean nearLocation;
	
	private SpatialThingBean currentLocation;
	
	private PersonBean friend;
	
	private PersonBean family;
	
	private DocumentBean commonInterest;
	
	private DocumentBean schoolHomepage;
	
	private DocumentBean workplaceHomepage;

	@RdfProperty("http://online-presence.net/opo/ns#attendedOrganisedEvent")
	public EventBean getEvent() {
		return event;
	}

	public void setEvent(EventBean event) {
		if(event != null)
			this.event = event;
	}

	@RdfProperty("http://online-presence.net/opo/ns#basedNear")
	public SpatialThingBean getNearLocation() {
		return nearLocation;
	}

	public void setNearLocation(SpatialThingBean nearLocation) {
		if(nearLocation != null)
			this.nearLocation = nearLocation;
	}

	@RdfProperty("http://online-presence.net/opo/ns#currentlyIn")
	public SpatialThingBean getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(SpatialThingBean currentLocation) {
		if(currentLocation != null)
			this.currentLocation = currentLocation;
	}

	@RdfProperty("http://online-presence.net/opo/ns#closestFriendsOf")
	public PersonBean getFriend() {
		return friend;
	}

	public void setFriend(PersonBean friend) {
		if(friend != null)
			this.friend = friend;
	}

	@RdfProperty("http://online-presence.net/opo/ns#familyOf")
	public PersonBean getFamily() {
		return family;
	}

	public void setFamily(PersonBean family) {
		if(family != null)
			this.family = family;
	}

	@RdfProperty("http://online-presence.net/opo/ns#commonInterest")
	public DocumentBean getCommonInterest() {
		return commonInterest;
	}

	public void setCommonInterest(DocumentBean commonInterest) {
		if(commonInterest != null)
			this.commonInterest = commonInterest;
	}

	@RdfProperty("http://online-presence.net/opo/ns#schoolHomepage")
	public DocumentBean getSchoolHomepage() {
		return schoolHomepage;
	}

	public void setSchoolHomepage(DocumentBean schoolHomepage) {
		if(schoolHomepage != null)
			this.schoolHomepage = schoolHomepage;
	}

	@RdfProperty("http://online-presence.net/opo/ns#workplaceHomepage")
	public DocumentBean getWorkplaceHomepage() {
		return workplaceHomepage;
	}

	public void setWorkplaceHomepage(DocumentBean workplaceHomepage) {
		if(workplaceHomepage != null)
			this.workplaceHomepage = workplaceHomepage;
	}
			
}

package net.onlinepresence.domainmodel.opo.pojos;

import java.net.URI;
import java.net.URISyntaxException;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
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
	private URI commonInterest;
	private URI schoolHomepage;
	private URI workplaceHomepage;

	public SharingSpace() {
		super();
	}
	
	public SharingSpace(String uri) {
		super(uri);
	}
	
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
	public URI getCommonInterest() {
		return commonInterest;
	}

	public void setCommonInterest(URI commonInterest) {
		if(commonInterest != null)
			this.commonInterest = commonInterest;
	}
	
	public void setCommonInterest(String commonInterest) {
		if(commonInterest != null)
			try {
				setCommonInterest(new URI(commonInterest));
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
	}

	@RdfProperty("http://online-presence.net/opo/ns#schoolHomepage")
	public URI getSchoolHomepage() {
		return schoolHomepage;
	}

	public void setSchoolHomepage(URI schoolHomepage) {
		if(schoolHomepage != null)
			this.schoolHomepage = schoolHomepage;
	}
	
	public void setSchoolHomepage(String schoolHomepage) {
		if(schoolHomepage != null)
			try {
				setSchoolHomepage(new URI(schoolHomepage));
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
	}

	@RdfProperty("http://online-presence.net/opo/ns#workplaceHomepage")
	public URI getWorkplaceHomepage() {
		return workplaceHomepage;
	}

	public void setWorkplaceHomepage(URI workplaceHomepage) {
		if(workplaceHomepage != null)
			this.workplaceHomepage = workplaceHomepage;
	}
	
	public void setWorkplaceHomepage(String workplaceHomepage) {
		if(workplaceHomepage != null)
			try {
				setWorkplaceHomepage(new URI(workplaceHomepage));
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
	}
			
}

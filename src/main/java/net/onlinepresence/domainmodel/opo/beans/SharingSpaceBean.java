/**
 *  Java OPO library
 *  Copyright (C) 2010  Filip Radulovic, Nikola Milikic
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see http://www.gnu.org/licenses/.
 *  
 *  You any further questions regarding usage of this software you can 
 *  find appropriate contacts on the OPO Prject website 
 *  http://online-presence.net.
 */
package net.onlinepresence.domainmodel.opo.beans;

import java.net.URI;
import java.net.URISyntaxException;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.foaf.Person;
import net.onlinepresence.domainmodel.foaf.beans.GroupBean;
import net.onlinepresence.domainmodel.geo.SpatialThing;
import net.onlinepresence.domainmodel.opo.SharingSpace;
import net.onlinepresence.domainmodel.purl.Event;

@Namespace("http://online-presence.net/opo/ns#")
@RdfType("SharingSpace")
public class SharingSpaceBean extends GroupBean implements SharingSpace{

	private static final long serialVersionUID = 1445987656012017340L;
	private Event event;
	private SpatialThing nearLocation;
	private SpatialThing currentLocation;
	private Person friend;
	private Person family;
	private URI commonInterest;
	private URI schoolHomepage;
	private URI workplaceHomepage;

	@Deprecated
	public SharingSpaceBean() {
		super();
	}
	
	@Deprecated
	public SharingSpaceBean(String uri) {
		super(uri);
	}
	
	@RdfProperty("http://online-presence.net/opo/ns#attendedOrganisedEvent")
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		if(event != null)
			this.event = event;
	}

	@RdfProperty("http://online-presence.net/opo/ns#basedNear")
	public SpatialThing getNearLocation() {
		return nearLocation;
	}

	public void setNearLocation(SpatialThing nearLocation) {
		if(nearLocation != null)
			this.nearLocation = nearLocation;
	}

	@RdfProperty("http://online-presence.net/opo/ns#currentlyIn")
	public SpatialThing getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(SpatialThing currentLocation) {
		if(currentLocation != null)
			this.currentLocation = currentLocation;
	}

	@RdfProperty("http://online-presence.net/opo/ns#closestFriendsOf")
	public Person getFriend() {
		return friend;
	}

	public void setFriend(Person friend) {
		if(friend != null)
			this.friend = friend;
	}

	@RdfProperty("http://online-presence.net/opo/ns#familyOf")
	public Person getFamily() {
		return family;
	}

	public void setFamily(Person family) {
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
		
	@Override
	public boolean equals(Object o) {
		if (o instanceof SharingSpaceBean){

			SharingSpaceBean sharSpace = (SharingSpaceBean) (o);
	
			if (getEvent().equals(sharSpace.getEvent()) &&
					getNearLocation().equals(sharSpace.getNearLocation()) &&
					getCurrentLocation().equals(sharSpace.getCurrentLocation()) &&
					getFriend().equals(sharSpace.getFriend()) &&
					getFamily().equals(sharSpace.getFamily()) &&
					getCommonInterest().equals(sharSpace.getCommonInterest()) &&
					getSchoolHomepage().equals(sharSpace.getSchoolHomepage()) &&
					getWorkplaceHomepage().equals(sharSpace.getWorkplaceHomepage()))
	
				return true;
			else
				return false;
		}
		return false;
	}
}

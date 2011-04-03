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
package net.onlinepresence.ontmodel.opo.beans;

import java.net.URI;
import java.net.URISyntaxException;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.ontmodel.foaf.Person;
import net.onlinepresence.ontmodel.foaf.beans.GroupBean;
import net.onlinepresence.ontmodel.geo.SpatialThing;
import net.onlinepresence.ontmodel.opo.SharingSpace;
import net.onlinepresence.ontmodel.purl.Event;
import net.onlinepresence.util.Constants;
import net.onlinepresence.util.EqualsUtil;

@Namespace(Constants.OPO_NS)
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
	
	@RdfProperty(Constants.OPO_NS + "attendedOrganisedEvent")
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		if(event != null)
			this.event = event;
	}

	@RdfProperty(Constants.OPO_NS + "basedNear")
	public SpatialThing getNearLocation() {
		return nearLocation;
	}

	public void setNearLocation(SpatialThing nearLocation) {
		if(nearLocation != null)
			this.nearLocation = nearLocation;
	}

	@RdfProperty(Constants.OPO_NS + "currentlyIn")
	public SpatialThing getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(SpatialThing currentLocation) {
		if(currentLocation != null)
			this.currentLocation = currentLocation;
	}

	@RdfProperty(Constants.OPO_NS + "closestFriendsOf")
	public Person getFriend() {
		return friend;
	}

	public void setFriend(Person friend) {
		if(friend != null)
			this.friend = friend;
	}

	@RdfProperty(Constants.OPO_NS + "familyOf")
	public Person getFamily() {
		return family;
	}

	public void setFamily(Person family) {
		if(family != null)
			this.family = family;
	}

	@RdfProperty(Constants.OPO_NS + "commonInterest")
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

	@RdfProperty(Constants.OPO_NS + "schoolHomepage")
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

	@RdfProperty(Constants.OPO_NS + "workplaceHomepage")
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
		if(this == o)
			return true;
		
		if (!(o instanceof SharingSpaceBean))
			return false;

		SharingSpaceBean sharSpace = (SharingSpaceBean) (o);
		
		return
			EqualsUtil.areEqual(getEvent(), sharSpace.getEvent()) &&
			EqualsUtil.areEqual(getNearLocation(), sharSpace.getNearLocation()) &&
			EqualsUtil.areEqual(getCurrentLocation(), sharSpace.getCurrentLocation()) &&
			EqualsUtil.areEqual(getFriend(), sharSpace.getFriend()) &&
			EqualsUtil.areEqual(getFamily(), sharSpace.getFamily()) &&
			EqualsUtil.areEqual(getCommonInterest(), sharSpace.getCommonInterest()) &&
			EqualsUtil.areEqual(getSchoolHomepage(), sharSpace.getSchoolHomepage()) &&
			EqualsUtil.areEqual(getWorkplaceHomepage(), sharSpace.getWorkplaceHomepage()) &&
			super.equals(sharSpace);

	}
}

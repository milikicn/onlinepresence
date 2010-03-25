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

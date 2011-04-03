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
package net.onlinepresence.ontmodel.opo;

import java.net.URI;

import net.onlinepresence.ontmodel.foaf.Group;
import net.onlinepresence.ontmodel.foaf.Person;
import net.onlinepresence.ontmodel.geo.SpatialThing;
import net.onlinepresence.ontmodel.purl.Event;

/**
 * A group of people belonging to a space for sharing online psresence data - 
 * the intended audience of data.
 *
 */
public interface SharingSpace extends Group{

	void setEvent(Event event);
	Event getEvent();
	
	void setNearLocation(SpatialThing base);
	SpatialThing getNearLocation();
	
	void setFriend(Person friend);
	Person getFriend();
	
	void setCommonInterest(URI commonInterest);
	void setCommonInterest(String commonInterest);
	URI getCommonInterest();
	
	void setCurrentLocation(SpatialThing base);
	SpatialThing getCurrentLocation();
	
	void setFamily(Person family);
	Person getFamily();
	
	void setSchoolHomepage(URI schoolHomepage);
	void setSchoolHomepage(String schoolHomepage);
	URI getSchoolHomepage();
	
	void setWorkplaceHomepage(URI workplaceHomepage);
	void setWorkplaceHomepage(String workplaceHomepage);
	URI getWorkplaceHomepage();
}

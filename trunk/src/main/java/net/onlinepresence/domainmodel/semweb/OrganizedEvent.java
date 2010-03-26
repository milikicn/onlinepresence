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
package net.onlinepresence.domainmodel.semweb;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.foaf.beans.PersonBean;
import net.onlinepresence.domainmodel.general.Resource;
import net.onlinepresence.domainmodel.geo.beans.SpatialThingBean;
import net.onlinepresence.domainmodel.semweb.beans.OrganizedEventBean;

@Namespace("http://data.semanticweb.org/ns/swc/ontology#")
@RdfType("OrganizedEvent")
public class OrganizedEvent extends Resource implements OrganizedEventBean{

	private PersonBean attendee;
	private SpatialThingBean location;

	public OrganizedEvent() {
		super();
	}
	
	public OrganizedEvent(String uri) {
		super(uri);
	}
	
	@RdfProperty("http://data.semanticweb.org/ns/swc/ontology#hasAttendee")
	public PersonBean getAttendee() {
		return attendee;
	}

	public void setAttendee(PersonBean attendee) {
		this.attendee = attendee;
	}

	@RdfProperty("http://data.semanticweb.org/ns/swc/ontology#hasLocation")
	public SpatialThingBean getLocation() {
		return location;
	}

	public void setLocation(SpatialThingBean location) {
		this.location = location;
	}
	
}

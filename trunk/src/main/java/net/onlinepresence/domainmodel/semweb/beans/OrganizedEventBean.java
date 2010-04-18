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
package net.onlinepresence.domainmodel.semweb.beans;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.foaf.Person;
import net.onlinepresence.domainmodel.general.ResourceBean;
import net.onlinepresence.domainmodel.geo.SpatialThing;
import net.onlinepresence.domainmodel.semweb.OrganizedEvent;

@Namespace("http://data.semanticweb.org/ns/swc/ontology#")
@RdfType("OrganizedEvent")
public class OrganizedEventBean extends ResourceBean implements OrganizedEvent{

	private Person attendee;
	private SpatialThing location;

	@Deprecated
	public OrganizedEventBean() {
		super();
	}
	
	@Deprecated
	public OrganizedEventBean(String uri) {
		super(uri);
	}
	
	@RdfProperty("http://data.semanticweb.org/ns/swc/ontology#hasAttendee")
	public Person getAttendee() {
		return attendee;
	}

	public void setAttendee(Person attendee) {
		this.attendee = attendee;
	}

	@RdfProperty("http://data.semanticweb.org/ns/swc/ontology#hasLocation")
	public SpatialThing getLocation() {
		return location;
	}

	public void setLocation(SpatialThing location) {
		this.location = location;
	}
	
}

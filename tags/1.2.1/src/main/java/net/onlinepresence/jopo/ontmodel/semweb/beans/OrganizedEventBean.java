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
package net.onlinepresence.jopo.ontmodel.semweb.beans;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.jopo.ontmodel.foaf.Person;
import net.onlinepresence.jopo.ontmodel.general.ResourceBean;
import net.onlinepresence.jopo.ontmodel.geo.SpatialThing;
import net.onlinepresence.jopo.ontmodel.semweb.OrganizedEvent;
import net.onlinepresence.jopo.util.Constants;
import net.onlinepresence.jopo.util.EqualsUtil;

@Namespace(Constants.SWC_NS)
@RdfType("OrganizedEvent")
public class OrganizedEventBean extends ResourceBean implements OrganizedEvent{

	private static final long serialVersionUID = 6426836405445334449L;
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
	
	@RdfProperty(Constants.SWC_NS + "hasAttendee")
	public Person getAttendee() {
		return attendee;
	}

	public void setAttendee(Person attendee) {
		this.attendee = attendee;
	}

	@RdfProperty(Constants.SWC_NS + "hasLocation")
	public SpatialThing getLocation() {
		return location;
	}

	public void setLocation(SpatialThing location) {
		this.location = location;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if (!(o instanceof OrganizedEventBean))
			return false;

		OrganizedEventBean orgEvent = (OrganizedEventBean) (o);
			
		return
			EqualsUtil.areEqual(getAttendee(), orgEvent.getAttendee()) &&
			EqualsUtil.areEqual(getLocation(), orgEvent.getLocation());
	}
}

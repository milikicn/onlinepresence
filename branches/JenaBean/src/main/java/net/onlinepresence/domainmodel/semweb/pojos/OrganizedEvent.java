package net.onlinepresence.domainmodel.semweb.pojos;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.foaf.interfaces.PersonBean;
import net.onlinepresence.domainmodel.general.Resource;
import net.onlinepresence.domainmodel.geo.interfaces.SpatialThingBean;
import net.onlinepresence.domainmodel.semweb.interfaces.OrganizedEventBean;

@Namespace("http://data.semanticweb.org/ns/swc/ontology#")
@RdfType("OrganizedEvent")
public class OrganizedEvent extends Resource implements OrganizedEventBean{

	private PersonBean attendee;
	
	private SpatialThingBean location;

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

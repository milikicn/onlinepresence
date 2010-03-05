package net.onlinepresence.domainmodel.geo.pojos;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.general.Resource;
import net.onlinepresence.domainmodel.geo.interfaces.SpatialThingBean;

@Namespace("http://www.w3.org/2003/01/geo/wgs84_pos#")
@RdfType("SpatialThing")
public class SpatialThing extends Resource implements SpatialThingBean {

	private String latitude;
	private String longitude;

	@RdfProperty("http://www.w3.org/2003/01/geo/wgs84_pos#lat")
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		if(latitude != null)
			this.latitude = latitude;
	}

	@RdfProperty("http://www.w3.org/2003/01/geo/wgs84_pos#long")
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		if(longitude != null)
			this.longitude = longitude;
	}

}

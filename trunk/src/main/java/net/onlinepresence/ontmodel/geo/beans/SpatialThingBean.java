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
package net.onlinepresence.ontmodel.geo.beans;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.ontmodel.general.ResourceBean;
import net.onlinepresence.ontmodel.geo.SpatialThing;
import net.onlinepresence.util.Constants;
import net.onlinepresence.util.EqualsUtil;

@Namespace(Constants.WSG84_POS_NS)
@RdfType("SpatialThing")
public class SpatialThingBean extends ResourceBean implements SpatialThing {

	private static final long serialVersionUID = -8705685396142943896L;
	private String latitude;
	private String longitude;

	@Deprecated
	public SpatialThingBean() {
		super();
	}

	@Deprecated
	public SpatialThingBean(String uri) {
		super(uri);
	}

	@RdfProperty(Constants.WSG84_POS_NS + "lat")
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		if (latitude != null)
			this.latitude = latitude;
	}

	@RdfProperty(Constants.WSG84_POS_NS + "long")
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		if (longitude != null)
			this.longitude = longitude;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if (!(o instanceof SpatialThingBean))
			return false;

		SpatialThingBean st = (SpatialThingBean) (o);
	
		return 
			EqualsUtil.areEqual(getLatitude(), st.getLatitude()) &&
			EqualsUtil.areEqual(getLongitude(), st.getLongitude());
	}
}

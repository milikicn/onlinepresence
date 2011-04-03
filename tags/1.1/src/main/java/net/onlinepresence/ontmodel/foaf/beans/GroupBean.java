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
package net.onlinepresence.ontmodel.foaf.beans;

import net.onlinepresence.ontmodel.foaf.Group;
import net.onlinepresence.util.Constants;
import thewebsemantic.Namespace;
import thewebsemantic.RdfType;

@Namespace(Constants.FOAF_NS)
@RdfType("Group")
public class GroupBean extends ThingBean implements Group {

	private static final long serialVersionUID = -6661802367888286003L;

	@Deprecated
	public GroupBean() {
		super();
	}

	@Deprecated
	public GroupBean(String uri) {
		super(uri);
	}

	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if (!(o instanceof GroupBean))
			return false;
		
		GroupBean group = (GroupBean) o;
		
		return super.equals(group);
	}
}

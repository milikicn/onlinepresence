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

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.ontmodel.general.ResourceBean;
import net.onlinepresence.ontmodel.opo.SourceOfPublishing;
import net.onlinepresence.util.Constants;
import net.onlinepresence.util.EqualsUtil;

@Namespace(Constants.OPO_NS)
@RdfType("SourceOfPublishing")
public class SourceOfPublishingBean extends ResourceBean implements SourceOfPublishing{

	private static final long serialVersionUID = -1775769547311907246L;
	private String name;

	@Deprecated
	public SourceOfPublishingBean() {
		super();
	}
	
	@Deprecated
	public SourceOfPublishingBean(String uri) {
		super(uri);
	}
	
	@RdfProperty(Constants.OPO_NS + "sourceName")
	public String getName() {
		return name;
	}

	public void setName(String sourceName) {
		if(sourceName != null)
			this.name = sourceName;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if (!(o instanceof SourceOfPublishingBean))
			return false;
			
		SourceOfPublishingBean sourceOfPub = (SourceOfPublishingBean) (o);
	
		return
			EqualsUtil.areEqual(getName(), sourceOfPub.getName()) &&
			super.equals(sourceOfPub);
	}
}

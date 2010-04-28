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
package net.onlinepresence.domainmodel.sioc.beans;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.general.ResourceBean;
import net.onlinepresence.domainmodel.sioc.Item;
import net.onlinepresence.util.EqualsUtil;

@Namespace("http://rdfs.org/sioc/ns#")
@RdfType("Item")
public class ItemBean extends ResourceBean implements Item {

	private static final long serialVersionUID = 3472817818726592420L;
	private String content;

	@Deprecated
	public ItemBean() {
		super();
	}
	
	@Deprecated
	public ItemBean(String uri) {
		super(uri);
	}
	
	@RdfProperty("http://rdfs.org/sioc/ns#content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		if(content != null)
			this.content = content;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if (!(o instanceof ItemBean))
			return false;

		ItemBean item = (ItemBean) (o);
		
		return
			EqualsUtil.areEqual(getContent(), item.getContent());
	}
}

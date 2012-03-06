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
package net.onlinepresence.jopo.ontmodel.opo;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.jopo.ontmodel.general.Resource;
import net.onlinepresence.jopo.ontmodel.opo.presencecomponents.OnlineStatus;
import net.onlinepresence.jopo.util.Constants;
import net.onlinepresence.jopo.util.EqualsUtil;

@Namespace(Constants.OPO_NS)
@RdfType("OnlineStatusComponent")
public class OnlineStatusComponent extends Resource {

	private static final long serialVersionUID = 7830921848384682441L;
	private OnlineStatus component;

	public OnlineStatusComponent() {
		super();
	}
	
	public OnlineStatusComponent(String uri) {
		super(uri);
	}
	
	@RdfProperty(Constants.OPO_NS + "isStatusComponentOf")
	public OnlineStatus getComponent() {
		return component;
	}

	public void setComponent(OnlineStatus component) {
		if(component != null)
			this.component = component;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if (!(o instanceof OnlineStatusComponent))
			return false;

		OnlineStatusComponent osComp = (OnlineStatusComponent) (o);
			
		return
			EqualsUtil.areEqual(getComponent(), osComp.getComponent());
	}
}

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
import net.onlinepresence.ontmodel.opo.OnlineStatusComponent;
import net.onlinepresence.ontmodel.opo.presencecomponents.OnlineStatus;
import net.onlinepresence.util.Constants;
import net.onlinepresence.util.EqualsUtil;

@Namespace(Constants.OPO_NS)
@RdfType("OnlineStatusComponent")
public class OnlineStatusComponentBean extends ResourceBean implements OnlineStatusComponent{

	private static final long serialVersionUID = 7830921848384682441L;
	private OnlineStatus component;

	@Deprecated
	public OnlineStatusComponentBean() {
		super();
	}
	
	@Deprecated
	public OnlineStatusComponentBean(String uri) {
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
		
		if (!(o instanceof OnlineStatusComponentBean))
			return false;

		OnlineStatusComponentBean osComp = (OnlineStatusComponentBean) (o);
			
		return
			EqualsUtil.areEqual(getComponent(), osComp.getComponent());
	}
}

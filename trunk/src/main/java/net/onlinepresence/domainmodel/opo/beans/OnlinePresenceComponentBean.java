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
package net.onlinepresence.domainmodel.opo.beans;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.general.ResourceBean;
import net.onlinepresence.domainmodel.opo.OnlinePresence;
import net.onlinepresence.domainmodel.opo.OnlinePresenceComponent;

@Namespace("http://online-presence.net/opo/ns#")
@RdfType("OnlinePresenceComponent")
public class OnlinePresenceComponentBean extends ResourceBean implements OnlinePresenceComponent {

	private static final long serialVersionUID = -4683853452711452930L;
	private OnlinePresence component;

	@Deprecated
	public OnlinePresenceComponentBean() {
		super();
	}
	
	@Deprecated
	public OnlinePresenceComponentBean(String uri) {
		super(uri);
	}
	
	@RdfProperty("http://online-presence.net/opo/ns#isPresenceComponentOf")
	public OnlinePresence getComponent() {
		return component;
	}

	public void setComponent(OnlinePresence component) {
		if(component != null)
			this.component = component;
	}	

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof OnlinePresenceComponentBean))
			throw new RuntimeException("Equals exception");

		OnlinePresenceComponentBean opComp = (OnlinePresenceComponentBean) (o);

		if (getComponent().equals(opComp.getComponent()))

			return true;
		else
			return false;
	}
}

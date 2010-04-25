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
package net.onlinepresence.domainmodel.opo.beans.statuscomponents;

import thewebsemantic.Namespace;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.opo.beans.OnlineStatusComponentBean;
import net.onlinepresence.domainmodel.opo.statuscomponents.Visibility;

@Namespace("http://online-presence.net/opo/ns#")
@RdfType("Visibility")
public class VisibilityBean extends OnlineStatusComponentBean implements Visibility{

	private static final long serialVersionUID = 2636377129612139629L;

	/**
	 * The Agent's OnlineStatus is visible to other Agents.
	 */
	public static VisibilityBean VISIBLE = new VisibilityBean("http://online-presence.net/opo/ns#Visible");

	/**
	 * The Agent's OnlineStatus is not visible to other Agents.
	 */
	public static VisibilityBean INVISIBLE = new VisibilityBean("http://online-presence.net/opo/ns#Invisible");
	
	@Deprecated
	public VisibilityBean(){
		super();
	}
	
	@Deprecated
	public VisibilityBean(String uri) {
		super(uri);
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof VisibilityBean))
			throw new RuntimeException("Equals exception");

//		VisibilityBean vis = (VisibilityBean) (o);

			return true;
//		else
//			return false;
	}
}

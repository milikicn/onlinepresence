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
package net.onlinepresence.domainmodel.opo.beans.presencecomponents;

import thewebsemantic.Namespace;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.opo.beans.OnlinePresenceComponentBean;
import net.onlinepresence.domainmodel.opo.presencecomponents.Findability;

@Namespace("http://online-presence.net/opo/ns#")
@RdfType("Findability")
public class FindabilityBean extends OnlinePresenceComponentBean implements Findability{

	private static final long serialVersionUID = 581378566485291228L;

	/**
	 * The Agent's contact details cannot be found by anyone on the Web, but the
	 * findability is controled by some rules/policies.
	 */
	public static FindabilityBean CONSTRAINED_FINDABILITY = new FindabilityBean("http://online-presence.net/opo/ns#ConstrainedFindability");

	/**
	 * The Agent's contact details can be found by anyone on the Web.
	 */
	public static FindabilityBean PUBLICLY_FINDABLE = new FindabilityBean("http://online-presence.net/opo/ns#PubliclyFindable");

	@Deprecated
	public FindabilityBean() {
		super();
	}
	
	@Deprecated
	public FindabilityBean(String uri) {
		super(uri);
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if (o instanceof FindabilityBean){

//		FindabilityBean find = (FindabilityBean) (o);

			return true;
//		else
//			return false;
		}
		return false;
	}
}

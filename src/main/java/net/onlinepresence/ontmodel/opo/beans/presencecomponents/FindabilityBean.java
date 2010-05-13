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
package net.onlinepresence.ontmodel.opo.beans.presencecomponents;

import thewebsemantic.Namespace;
import thewebsemantic.RdfType;
import net.onlinepresence.ontmodel.opo.beans.OnlinePresenceComponentBean;
import net.onlinepresence.ontmodel.opo.presencecomponents.Findability;
import net.onlinepresence.util.Constants;

@Namespace(Constants.OPO_NS)
@RdfType("Findability")
public class FindabilityBean extends OnlinePresenceComponentBean implements Findability{

	private static final long serialVersionUID = 581378566485291228L;

	/**
	 * The Agent's contact details cannot be found by anyone on the Web, but the
	 * findability is controled by some rules/policies.
	 */
	public static FindabilityBean CONSTRAINED_FINDABILITY = new FindabilityBean(Constants.OPO_NS + "ConstrainedFindability");

	/**
	 * The Agent's contact details can be found by anyone on the Web.
	 */
	public static FindabilityBean PUBLICLY_FINDABLE = new FindabilityBean(Constants.OPO_NS + "PubliclyFindable");

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
		
		if (!(o instanceof FindabilityBean))
			return false;
		
		FindabilityBean find = (FindabilityBean) o;
		
		return super.equals(find);
	}
}

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
package net.onlinepresence.domainmodel.opo.presencecomponents;

import thewebsemantic.Namespace;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.opo.OnlinePresenceComponent;
import net.onlinepresence.domainmodel.opo.beans.presencecomponents.FindabilityBean;

@Namespace("http://online-presence.net/opo/ns#")
@RdfType("Findability")
public class Findability extends OnlinePresenceComponent implements FindabilityBean{

	/**
	 * The Agent's contact details cannot be found by anyone on the Web, but the
	 * findability is controled by some rules/policies.
	 */
	public static Findability CONSTRAINED_FINDABILITY = new Findability("http://online-presence.net/opo/ns#ConstrainedFindability");

	/**
	 * The Agent's contact details can be found by anyone on the Web.
	 */
	public static Findability PUBLICLY_FINDABLE = new Findability("http://online-presence.net/opo/ns#PubliclyFindable");

	public Findability() {
		super();
	}
	
	public Findability(String uri) {
		super(uri);
	}
	
}

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
package net.onlinepresence.domainmodel.opo.statuscomponents;

import thewebsemantic.Namespace;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.opo.OnlineStatusComponent;
import net.onlinepresence.domainmodel.opo.beans.statuscomponents.ContactabilityBean;

@Namespace("http://online-presence.net/opo/ns#")
@RdfType("Contactability")
public class Contactability extends OnlineStatusComponent implements ContactabilityBean{

	/**
	 * The Agent can be contacted by anyone on the Web.
	 */
	public static Contactability FREELY_CONTACTABLE = new Contactability("http://online-presence.net/opo/ns#FreelyContactable");

	/**
	 * The Agent cannot be contated by anyone on the Web, but the contactability
	 * is controled by some rules/policies.
	 */
	public static Contactability CONSTRAINED_CONTACTABILITY = new Contactability("http://online-presence.net/opo/ns#ConstrainedContactability");

	public Contactability() {
		super();
	}
	
	public Contactability(String uri) {
		super(uri);
	}
	
}

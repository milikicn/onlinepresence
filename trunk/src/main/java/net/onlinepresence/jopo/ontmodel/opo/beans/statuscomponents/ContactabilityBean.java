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
package net.onlinepresence.jopo.ontmodel.opo.beans.statuscomponents;

import thewebsemantic.Namespace;
import thewebsemantic.RdfType;
import net.onlinepresence.jopo.ontmodel.opo.beans.OnlineStatusComponentBean;
import net.onlinepresence.jopo.ontmodel.opo.statuscomponents.Contactability;
import net.onlinepresence.jopo.util.Constants;

@Namespace(Constants.OPO_NS)
@RdfType("Contactability")
public class ContactabilityBean extends OnlineStatusComponentBean implements Contactability{

	private static final long serialVersionUID = -984851302164133674L;

	/**
	 * The Agent can be contacted by anyone on the Web.
	 */
	public static ContactabilityBean FREELY_CONTACTABLE = new ContactabilityBean(Constants.OPO_NS + "FreelyContactable");

	/**
	 * The Agent cannot be contated by anyone on the Web, but the contactability
	 * is controled by some rules/policies.
	 */
	public static ContactabilityBean CONSTRAINED_CONTACTABILITY = new ContactabilityBean(Constants.OPO_NS + "ConstrainedContactability");

	@Deprecated
	public ContactabilityBean() {
		super();
	}
	
	@Deprecated
	public ContactabilityBean(String uri) {
		super(uri);
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if (!(o instanceof ContactabilityBean))
			return false;
		
		ContactabilityBean cont = (ContactabilityBean) o;

		return super.equals(cont);
	}
}

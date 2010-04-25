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
import net.onlinepresence.domainmodel.opo.statuscomponents.Disturbability;

@Namespace("http://online-presence.net/opo/ns#")
@RdfType("Disturbability")
public class DisturbabilityBean extends OnlineStatusComponentBean implements Disturbability{

	private static final long serialVersionUID = 4037280413218081014L;

	/**
	 * The agent is not busy and therefore available for contatct by other
	 * Agents.
	 */
	public static DisturbabilityBean AVAILABLE = new DisturbabilityBean("http://online-presence.net/opo/ns#Available");

	/**
	 * The Agent is busy and does not want to be contacted by other Agents.
	 */
	public static DisturbabilityBean DO_NOT_DISTURB = new DisturbabilityBean("http://online-presence.net/opo/ns#DoNotDisturb");

	@Deprecated
	public DisturbabilityBean() {
		super();
	}
	
	@Deprecated
	public DisturbabilityBean(String uri) {
		super(uri);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof DisturbabilityBean){

//		DisturbabilityBean aist = (DisturbabilityBean) (o);

			return true;
//		else
//			return false;
		}
		return false;
	}
}

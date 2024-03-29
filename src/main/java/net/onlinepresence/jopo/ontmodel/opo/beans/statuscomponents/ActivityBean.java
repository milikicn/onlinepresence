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
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.jopo.ontmodel.opo.beans.OnlineStatusComponentBean;
import net.onlinepresence.jopo.ontmodel.opo.statuscomponents.Activity;
import net.onlinepresence.jopo.util.Constants;
import net.onlinepresence.jopo.util.EqualsUtil;

@Namespace(Constants.OPO_NS)
@RdfType("Activity")
public class ActivityBean extends OnlineStatusComponentBean implements Activity{

	private static final long serialVersionUID = 5150629630646563079L;

	private int inactivityPeriod;

	/**
	 * The Agent is active on the system.
	 */
	public static ActivityBean ACTIVE = new ActivityBean(Constants.OPO_NS + "Active");

	/**
	 * The Agent is inactive for at least 5 min.
	 */
	public static ActivityBean INACTIVE = new ActivityBean(Constants.OPO_NS + "Inactive");

	/**
	 * The Agent is inactive for at least 20 min.
	 */
	public static ActivityBean PROLONGED_INACTIVE = new ActivityBean(Constants.OPO_NS + "ProlongedInactive");


	@Deprecated
	public ActivityBean() {
		super();
	}
	
	@Deprecated
	public ActivityBean(String uri) {
		super(uri);
	}

	@RdfProperty(Constants.OPO_NS + "inactivityPeriod")
	public int getInactivityPeriod() {
		return inactivityPeriod;
	}

	public void setInactivityPeriod(int inactivityPeriod) {
		if(inactivityPeriod > 0)
			this.inactivityPeriod = inactivityPeriod;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if (!(o instanceof ActivityBean))
			return false;

		ActivityBean act = (ActivityBean) (o);

		return
			EqualsUtil.areEqual(getInactivityPeriod(), act.getInactivityPeriod()) &&
			super.equals(act);
	}
}

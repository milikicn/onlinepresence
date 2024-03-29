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
package net.onlinepresence.jopo.ontmodel.opo.beans.presencecomponents;

import thewebsemantic.Namespace;
import thewebsemantic.RdfType;
import net.onlinepresence.jopo.ontmodel.opo.beans.OnlinePresenceComponentBean;
import net.onlinepresence.jopo.ontmodel.opo.presencecomponents.Notifiability;
import net.onlinepresence.jopo.util.Constants;

@Namespace(Constants.OPO_NS)
@RdfType("Notifiability")
public class NotifiabilityBean extends OnlinePresenceComponentBean implements Notifiability{

	private static final long serialVersionUID = 7965121524681069212L;

	/**
	 * Agent can receive notifications from applications.
	 */
	public static NotifiabilityBean ALL_NOTIFICATIONS_PASS = new NotifiabilityBean(Constants.OPO_NS + "AllNotificationsPass");

	/**
	 * The acceptance of notifications from applications is somehow constrained
	 * (by using some rules or policies).
	 */
	public static NotifiabilityBean NOTIFICATIONS_CONSTRAINED = new NotifiabilityBean(Constants.OPO_NS + "NotificationsConstrained");

	/**
	 * Agent cannot receive any notification from applications.
	 */
	public static NotifiabilityBean NOTIFICATIONS_PROHIBITED = new NotifiabilityBean(Constants.OPO_NS + "NotificationsProhibited");

	@Deprecated
	public NotifiabilityBean() {
		super();
	}
	
	@Deprecated
	public NotifiabilityBean(String uri) {
		super(uri);
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if (!(o instanceof NotifiabilityBean))
			return false;
		
		NotifiabilityBean notifiability = (NotifiabilityBean) o;
		
		return super.equals(notifiability);
	}
}

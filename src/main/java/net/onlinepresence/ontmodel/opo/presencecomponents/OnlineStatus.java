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
package net.onlinepresence.ontmodel.opo.presencecomponents;

import java.util.Collection;

import net.onlinepresence.ontmodel.opo.OnlinePresenceComponent;
import net.onlinepresence.ontmodel.opo.OnlineStatusComponent;
import net.onlinepresence.ontmodel.opo.statuscomponents.Activity;
import net.onlinepresence.ontmodel.opo.statuscomponents.Contactability;
import net.onlinepresence.ontmodel.opo.statuscomponents.Disturbability;
import net.onlinepresence.ontmodel.opo.statuscomponents.Visibility;

/**
 * The OnlinePresenceComponent used to represent the attitude of an Agent towards 
 * the possibility of communication with other Agents. The OnlineStatus is defined 
 * by its components.
 *
 */
public interface OnlineStatus extends OnlinePresenceComponent{

	void setStatusComponents(Collection<OnlineStatusComponent> statusComponents);
	Collection<OnlineStatusComponent> getStatusComponents();
	void addStatusComponent(OnlineStatusComponent statusComponent);
	
	void setName(String statusName);
	String getName();
	
	Visibility retrieveVisibility();
	void setVisibility(Visibility visibility);

	Disturbability retrieveDisturbability();
	void setDisturbability(Disturbability disturbability);
	
	Activity retrieveActivity();
	void setActivity(Activity activity);

	Contactability retrieveContactability();
	void setContactability(Contactability contactability);
}

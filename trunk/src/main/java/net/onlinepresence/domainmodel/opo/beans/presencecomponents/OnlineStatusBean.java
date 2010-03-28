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

import java.util.Collection;

import net.onlinepresence.domainmodel.opo.beans.OnlinePresenceComponentBean;
import net.onlinepresence.domainmodel.opo.beans.OnlineStatusComponentBean;
import net.onlinepresence.domainmodel.opo.beans.statuscomponents.ActivityBean;
import net.onlinepresence.domainmodel.opo.beans.statuscomponents.ContactabilityBean;
import net.onlinepresence.domainmodel.opo.beans.statuscomponents.DisturbabilityBean;
import net.onlinepresence.domainmodel.opo.beans.statuscomponents.VisibilityBean;

/**
 * The OnlinePresenceComponent used to represent the attitude of an Agent towards 
 * the possibility of communication with other Agents. The OnlineStatus is defined 
 * by its components.
 *
 */
public interface OnlineStatusBean extends OnlinePresenceComponentBean{

	public void setOnlineStatusComponents(Collection<OnlineStatusComponentBean> statusComponents);
	public Collection<OnlineStatusComponentBean> getOnlineStatusComponents();
	void addStatusComponent(OnlineStatusComponentBean statusComponent);
	
	public void setName(String statusName);
	public String getName();
	
	public VisibilityBean getVisibility();
	public DisturbabilityBean getDisturbability();
	public ActivityBean getActivity();
	public ContactabilityBean getContactability();
	
	public  void setVisibility(VisibilityBean visibility);
	public  void setDisturbability(DisturbabilityBean disturbability);
	public  void setActivity(ActivityBean activity);
	public  void setContactability(ContactabilityBean contactability);
}

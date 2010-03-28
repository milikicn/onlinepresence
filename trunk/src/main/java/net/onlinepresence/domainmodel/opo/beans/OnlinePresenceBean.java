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
package net.onlinepresence.domainmodel.opo.beans;

import java.util.Collection;

import net.onlinepresence.domainmodel.foaf.beans.AgentBean;
import net.onlinepresence.domainmodel.foaf.beans.ImageBean;
import net.onlinepresence.domainmodel.general.ResourceBean;
import net.onlinepresence.domainmodel.geo.beans.SpatialThingBean;
import net.onlinepresence.domainmodel.opo.beans.presencecomponents.FindabilityBean;
import net.onlinepresence.domainmodel.opo.beans.presencecomponents.NotifiabilityBean;
import net.onlinepresence.domainmodel.opo.beans.presencecomponents.OnlineStatusBean;
import net.onlinepresence.domainmodel.sioc.beans.ItemBean;
import net.onlinepresence.domainmodel.sioc.beans.UserAccountBean;

/**
 * OnlinePresence, described in termes of various OnlinePresenceComponents to 
 * represent the attitude of an Agent towards interaction with other Agents and 
 * Applications.
 *
 */
public interface OnlinePresenceBean extends ResourceBean{

	public void setAgent(AgentBean agent);
	public AgentBean getAgent();
	
	public void setAvatar(ImageBean avatar);
	public ImageBean getAvatar();
	
	public void setAction(ActionBean action);
	public ActionBean getAction();
	
	public void setLocation(SpatialThingBean location);
	public SpatialThingBean getLocation();
	
	public void setStatusMessage(ItemBean statusMessage);
	public ItemBean getStatusMessage();
	
	public void setUserAccount(UserAccountBean user);
	public UserAccountBean getUserAccount();
	
	public void setPresenceComponents(Collection<OnlinePresenceComponentBean> presenceComponents);
	public Collection<OnlinePresenceComponentBean> getPresenceComponents();
	public void addPresenceComponent(OnlinePresenceComponentBean presenceComponent);
	
	public void setIntendentFor(SharingSpaceBean intendentFor);
	public SharingSpaceBean getIntendentFor();
	
	public void setSource(SourceOfPublishingBean source);
	public SourceOfPublishingBean getSource();
	
	public void setDuration(String duration);
	public String getDuration();
	
	public void setStartTime(String startTime);
	public String getStartTime();
	
	public FindabilityBean getFindability();
	public NotifiabilityBean getNotifiability();
	public OnlineStatusBean getOnlineStatus();
	
	public void setFindability(FindabilityBean findability);
	public void setNotifiability(NotifiabilityBean notifiability);
	public void setOnlineStatus(OnlineStatusBean onlineStatus);
}

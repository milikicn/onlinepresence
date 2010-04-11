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
package net.onlinepresence.domainmodel.opo;

import java.util.Collection;

import net.onlinepresence.domainmodel.foaf.Agent;
import net.onlinepresence.domainmodel.foaf.Image;
import net.onlinepresence.domainmodel.general.Resource;
import net.onlinepresence.domainmodel.geo.SpatialThing;
import net.onlinepresence.domainmodel.opo.presencecomponents.Findability;
import net.onlinepresence.domainmodel.opo.presencecomponents.Notifiability;
import net.onlinepresence.domainmodel.opo.presencecomponents.OnlineStatus;
import net.onlinepresence.domainmodel.sioc.Item;
import net.onlinepresence.domainmodel.sioc.UserAccount;

/**
 * OnlinePresence, described in termes of various OnlinePresenceComponents to 
 * represent the attitude of an Agent towards interaction with other Agents and 
 * Applications.
 *
 */
public interface OnlinePresence extends Resource{

	public void setAgent(Agent agent);
	public Agent getAgent();
	
	public void setAvatar(Image avatar);
	public Image getAvatar();
	
	public void setAction(Action action);
	public Action getAction();
	
	public void setLocation(SpatialThing location);
	public SpatialThing getLocation();
	
	public void setStatusMessage(Item statusMessage);
	public Item getStatusMessage();
	
	public void setUserAccount(UserAccount user);
	public UserAccount getUserAccount();
	
	public void setPresenceComponents(Collection<OnlinePresenceComponent> presenceComponents);
	public Collection<OnlinePresenceComponent> getPresenceComponents();
	public void addPresenceComponent(OnlinePresenceComponent presenceComponent);
	
	public void setIntendentFor(SharingSpace intendentFor);
	public SharingSpace getIntendentFor();
	
	public void setSource(SourceOfPublishing source);
	public SourceOfPublishing getSource();
	
	public void setDuration(String duration);
	public String getDuration();
	
	public void setStartTime(String startTime);
	public String getStartTime();
	
	public Findability getFindability();
	public Notifiability getNotifiability();
	public OnlineStatus getOnlineStatus();
	
	public void setFindability(Findability findability);
	public void setNotifiability(Notifiability notifiability);
	public void setOnlineStatus(OnlineStatus onlineStatus);
}

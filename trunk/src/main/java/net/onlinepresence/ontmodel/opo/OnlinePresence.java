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
package net.onlinepresence.ontmodel.opo;

import java.util.Collection;

import net.onlinepresence.ontmodel.foaf.Agent;
import net.onlinepresence.ontmodel.foaf.Image;
import net.onlinepresence.ontmodel.general.Resource;
import net.onlinepresence.ontmodel.geo.SpatialThing;
import net.onlinepresence.ontmodel.sioc.Item;
import net.onlinepresence.ontmodel.sioc.UserAccount;

/**
 * OnlinePresence, described in termes of various OnlinePresenceComponents to 
 * represent the attitude of an Agent towards interaction with other Agents and 
 * Applications.
 *
 */
public interface OnlinePresence extends Resource{

	void setAgent(Agent agent);
	Agent getAgent();
	
	void setAvatar(Image avatar);
	Image getAvatar();
	
	void setAction(Action action);
	Action getAction();
	
	void setLocation(SpatialThing location);
	SpatialThing getLocation();
	
	void setStatusMessage(Item statusMessage);
	Item getStatusMessage();
	
	void setUserAccount(UserAccount user);
	UserAccount getUserAccount();
	
	void setPresenceComponents(Collection<OnlinePresenceComponent> presenceComponents);
	Collection<OnlinePresenceComponent> getPresenceComponents();
	void addPresenceComponent(OnlinePresenceComponent presenceComponent);
	
	void setIntendedFor(SharingSpace intendedFor);
	SharingSpace getIntendedFor();
	
	void setSource(SourceOfPublishing source);
	SourceOfPublishing getSource();
	
	void setDuration(String duration);
	String getDuration();
	
	void setStartTime(String startTime);
	String getStartTime();
}

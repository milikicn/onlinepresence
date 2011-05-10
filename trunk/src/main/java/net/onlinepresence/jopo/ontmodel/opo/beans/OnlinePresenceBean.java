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
package net.onlinepresence.jopo.ontmodel.opo.beans;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;

import net.onlinepresence.jopo.ontmodel.foaf.Agent;
import net.onlinepresence.jopo.ontmodel.foaf.Image;
import net.onlinepresence.jopo.ontmodel.general.ResourceBean;
import net.onlinepresence.jopo.ontmodel.geo.SpatialThing;
import net.onlinepresence.jopo.ontmodel.opo.Action;
import net.onlinepresence.jopo.ontmodel.opo.OnlinePresence;
import net.onlinepresence.jopo.ontmodel.opo.OnlinePresenceComponent;
import net.onlinepresence.jopo.ontmodel.opo.SharingSpace;
import net.onlinepresence.jopo.ontmodel.opo.SourceOfPublishing;
import net.onlinepresence.jopo.ontmodel.opo.presencecomponents.Findability;
import net.onlinepresence.jopo.ontmodel.opo.presencecomponents.Notifiability;
import net.onlinepresence.jopo.ontmodel.opo.presencecomponents.OnlineStatus;
import net.onlinepresence.jopo.ontmodel.sioc.Item;
import net.onlinepresence.jopo.ontmodel.sioc.UserAccount;
import net.onlinepresence.jopo.util.Constants;
import net.onlinepresence.jopo.util.EqualsUtil;
import net.onlinepresence.jopo.util.Util;

@Namespace(Constants.OPO_NS)
@RdfType("OnlinePresence")
public class OnlinePresenceBean extends ResourceBean implements OnlinePresence {

	private static final long serialVersionUID = -377623284014131162L;
	public Action action;
	public Agent agent;
	public Image avatar;
	public String duration;
	public SharingSpace intendedFor;
	public SpatialThing location;
	public Collection<OnlinePresenceComponent> presenceComponents = new LinkedList<OnlinePresenceComponent>();
	public SourceOfPublishing source;
	public String startTime;
	public Item statusMessage;
	public UserAccount userAccount;
	
	@Deprecated
	public OnlinePresenceBean() {
		super();
		setStartTime(Util.getTime());
	}
	
	@Deprecated
	public OnlinePresenceBean(String uri) {
		super(uri);
		setStartTime(Util.getTime());
	}
	
	@RdfProperty(Constants.OPO_NS + "currentAction")
	public Action getAction() {
		return action;
	}
	
	public void setAction(Action action) {
		if(action != null)
			this.action = action;
	}
	
	@RdfProperty(Constants.OPO_NS + "declaredBy")
	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		if(agent != null)
			this.agent = agent;
	}

	@RdfProperty(Constants.OPO_NS + "avatar")
	public Image getAvatar() {
		return avatar;
	}

	public void setAvatar(Image avatar) {
		if(avatar != null)
			this.avatar = avatar;
	}

	@RdfProperty(Constants.OPO_NS + "duration")
	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		if(duration != null)
			this.duration = duration;
	}

	@RdfProperty(Constants.OPO_NS + "intendedFor")
	public SharingSpace getIntendedFor() {
		return intendedFor;
	}

	public void setIntendedFor(SharingSpace intendedFor) {
		if(intendedFor != null)
			this.intendedFor = intendedFor;
	}

	@RdfProperty(Constants.OPO_NS + "currentLocation")
	public SpatialThing getLocation() {
		return location;
	}

	public void setLocation(SpatialThing location) {
		if(location != null)
			this.location = location;
	}

	@RdfProperty(Constants.OPO_NS + "hasPresenceComponent")
	public Collection<OnlinePresenceComponent> getPresenceComponents() {
		return presenceComponents;
	}

	public void setPresenceComponents(
			Collection<OnlinePresenceComponent> presenceComponents) {
		if(presenceComponents != null)
			this.presenceComponents = presenceComponents;
	}

	public void addPresenceComponent(
			OnlinePresenceComponent presenceComponent) {
		
		if(presenceComponent != null){
			OnlinePresenceComponent pc = findPresenceComponent(presenceComponent.getClass());
			
			if(pc != null)
				getPresenceComponents().remove(pc);
			
			getPresenceComponents().add(presenceComponent);
		}
	}

	@RdfProperty(Constants.OPO_NS + "publishedFrom")
	public SourceOfPublishing getSource() {
		return source;
	}

	public void setSource(SourceOfPublishing source) {
		if(source != null)
			this.source = source;
	}

	@RdfProperty(Constants.OPO_NS + "startTime")
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		if(startTime != null)
			this.startTime = startTime;
	}

	@RdfProperty(Constants.OPO_NS + "customMessage")
	public Item getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(Item statusMessage) {
		if(statusMessage != null)
			this.statusMessage = statusMessage;
	}

	@RdfProperty(Constants.OPO_NS + "declaredOn")
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount user) {
		if(user != null)
			this.userAccount = user;
	}
	
	public Findability retrieveFindability() {
		OnlinePresenceComponent find = findPresenceComponent(Findability.class);
		if(find != null)
			return (Findability) find;
		
		return null;
	}

	public void setFindability(Findability findability){
		addPresenceComponent(findability);
	}
	
	public Notifiability retrieveNotifiability() {
		OnlinePresenceComponent not = findPresenceComponent(Notifiability.class);
		if(not != null)
			return (Notifiability) not;
		
		return null;
	}

	public void setNotifiability(Notifiability notifiability){
		addPresenceComponent(notifiability);
	}

	public OnlineStatus retrieveOnlineStatus() {
		OnlinePresenceComponent onlineStatus = findPresenceComponent(OnlineStatus.class);
		if(onlineStatus != null)
			return (OnlineStatus) onlineStatus;
		
		return null;
	}

	public void setOnlineStatus(OnlineStatus onlineStatus){
		addPresenceComponent(onlineStatus);
	}
	
	private OnlinePresenceComponent findPresenceComponent(
			Class<? extends OnlinePresenceComponent> clazz) {
		
		Iterator<OnlinePresenceComponent> iterator = presenceComponents.iterator();
		
		while (iterator.hasNext()) {
			OnlinePresenceComponent onlinePresenceComponent = (OnlinePresenceComponent) iterator.next();
			
			try {
				// check if it is a subclass
				onlinePresenceComponent.getClass().asSubclass(clazz);
				return onlinePresenceComponent;
			} catch (Exception e) {
				
			}
		}
		return null;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if (!(o instanceof OnlinePresenceBean))
			return false;

		OnlinePresenceBean onlinePresence = (OnlinePresenceBean) (o);
			
		return
			EqualsUtil.areEqual(getAction(), onlinePresence.getAction()) &&
			EqualsUtil.areEqual(getAgent(), onlinePresence.getAgent()) &&
			EqualsUtil.areEqual(getAvatar(), onlinePresence.getAvatar()) &&
			EqualsUtil.areEqual(getDuration(), onlinePresence.getDuration()) &&
			EqualsUtil.areEqual(getIntendedFor(), onlinePresence.getIntendedFor()) &&
			EqualsUtil.areEqual(getLocation(), onlinePresence.getLocation()) &&
			EqualsUtil.areEqual(getPresenceComponents(), onlinePresence.getPresenceComponents()) &&
			EqualsUtil.areEqual(getSource(), onlinePresence.getSource()) &&
			EqualsUtil.areEqual(getStatusMessage(), onlinePresence.getStatusMessage()) &&
			EqualsUtil.areEqual(getUserAccount(), onlinePresence.getUserAccount());		
	}
}

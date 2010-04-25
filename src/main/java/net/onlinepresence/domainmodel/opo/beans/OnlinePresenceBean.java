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
import java.util.LinkedList;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;

import net.onlinepresence.domainmodel.foaf.Agent;
import net.onlinepresence.domainmodel.foaf.Image;
import net.onlinepresence.domainmodel.general.ResourceBean;
import net.onlinepresence.domainmodel.geo.SpatialThing;
import net.onlinepresence.domainmodel.opo.Action;
import net.onlinepresence.domainmodel.opo.OnlinePresence;
import net.onlinepresence.domainmodel.opo.OnlinePresenceComponent;
import net.onlinepresence.domainmodel.opo.SharingSpace;
import net.onlinepresence.domainmodel.opo.SourceOfPublishing;
import net.onlinepresence.domainmodel.opo.presencecomponents.Findability;
import net.onlinepresence.domainmodel.opo.presencecomponents.Notifiability;
import net.onlinepresence.domainmodel.opo.presencecomponents.OnlineStatus;
import net.onlinepresence.domainmodel.sioc.Item;
import net.onlinepresence.domainmodel.sioc.UserAccount;
import net.onlinepresence.util.Util;

@Namespace("http://online-presence.net/opo/ns#")
@RdfType("OnlinePresence")
public class OnlinePresenceBean extends ResourceBean implements OnlinePresence {

	private static final long serialVersionUID = -377623284014131162L;
	public Action action;
	public Agent agent;
	public Image avatar;
	public String duration;
	public SharingSpace intendentFor;
	public SpatialThing location;
	public Collection<OnlinePresenceComponent> presenceComponents = new LinkedList<OnlinePresenceComponent>();
	public SourceOfPublishing source;
	public String startTime;
	public Item statusMessage;
	public UserAccount userAccount;
	
	protected Findability findability;
	protected Notifiability notifiability;
	protected OnlineStatus onlineStatus;
	
	
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
	
	@RdfProperty("http://online-presence.net/opo/ns#currentAction")
	public Action getAction() {
		return action;
	}
	
	public void setAction(Action action) {
		if(action != null)
			this.action = action;
	}
	
	@RdfProperty("http://online-presence.net/opo/ns#declaredBy")
	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		if(agent != null)
			this.agent = agent;
	}

	@RdfProperty("http://online-presence.net/opo/ns#avatar")
	public Image getAvatar() {
		return avatar;
	}

	public void setAvatar(Image avatar) {
		if(avatar != null)
			this.avatar = avatar;
	}

	@RdfProperty("http://online-presence.net/opo/ns#duration")
	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		if(duration != null)
			this.duration = duration;
	}

	@RdfProperty("http://online-presence.net/opo/ns#intendedFor")
	public SharingSpace getIntendentFor() {
		return intendentFor;
	}

	public void setIntendentFor(SharingSpace intendentFor) {
		if(intendentFor != null)
			this.intendentFor = intendentFor;
	}

	@RdfProperty("http://online-presence.net/opo/ns#currentLocation")
	public SpatialThing getLocation() {
		return location;
	}

	public void setLocation(SpatialThing location) {
		if(location != null)
			this.location = location;
	}

	@RdfProperty("http://online-presence.net/opo/ns#hasPresenceComponent")
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
		if(presenceComponent != null)
			getPresenceComponents().add(presenceComponent);
	}

	@RdfProperty("http://online-presence.net/opo/ns#publishedFrom")
	public SourceOfPublishing getSource() {
		return source;
	}

	public void setSource(SourceOfPublishing source) {
		if(source != null)
			this.source = source;
	}

	@RdfProperty("http://online-presence.net/opo/ns#startTime")
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		if(startTime != null)
			this.startTime = startTime;
	}

	@RdfProperty("http://online-presence.net/opo/ns#customMessage")
	public Item getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(Item statusMessage) {
		if(statusMessage != null)
			this.statusMessage = statusMessage;
	}

	@RdfProperty("http://rdfs.org/sioc/ns#has_creator")
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount user) {
		if(user != null)
			this.userAccount = user;
	}
	
	public void setFindability(Findability findability){
		this.findability = findability;
		getPresenceComponents().add(findability);
	}
	
	public void setNotifiability(Notifiability notifiability){
		this.notifiability = notifiability;
		getPresenceComponents().add(notifiability);
	}
		
	public void setOnlineStatus(OnlineStatus onlineStatus){
		this.onlineStatus = onlineStatus;
		getPresenceComponents().add(onlineStatus);
	}

	public Findability getFindability() {
		return findability;
	}

	public Notifiability getNotifiability() {
		return notifiability;
	}

	public OnlineStatus getOnlineStatus() {
		return onlineStatus;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof OnlinePresenceBean))
			throw new RuntimeException("Equals exception");

		OnlinePresenceBean onlinePresence = (OnlinePresenceBean) (o);

		if (getAction().equals(onlinePresence.getAction()) &&
				getAgent().equals(onlinePresence.getAgent()) &&
				getAvatar().equals(onlinePresence.getAvatar()) &&
				getDuration().equals(onlinePresence.getDuration()) &&
				getIntendentFor().equals(onlinePresence.getIntendentFor()) &&
				getLocation().equals(onlinePresence.getLocation()) &&
				getPresenceComponents().equals(getPresenceComponents()) &&
				getSource().equals(onlinePresence.getSource()) &&
				getStartTime().equals(onlinePresence.getStartTime()) &&
				getStatusMessage().equals(onlinePresence.getStatusMessage()) &&
				getUserAccount().equals(onlinePresence.getUserAccount()))

			return true;
		else
			return false;
	}
}

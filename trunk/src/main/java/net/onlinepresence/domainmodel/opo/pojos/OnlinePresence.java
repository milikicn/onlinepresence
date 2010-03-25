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
package net.onlinepresence.domainmodel.opo.pojos;

import java.util.Collection;
import java.util.LinkedList;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;

import net.onlinepresence.domainmodel.foaf.interfaces.AgentBean;
import net.onlinepresence.domainmodel.foaf.interfaces.ImageBean;
import net.onlinepresence.domainmodel.general.Resource;
import net.onlinepresence.domainmodel.geo.interfaces.SpatialThingBean;
import net.onlinepresence.domainmodel.opo.interfaces.ActionBean;
import net.onlinepresence.domainmodel.opo.interfaces.OnlinePresenceBean;
import net.onlinepresence.domainmodel.opo.interfaces.OnlinePresenceComponentBean;
import net.onlinepresence.domainmodel.opo.interfaces.SharingSpaceBean;
import net.onlinepresence.domainmodel.opo.interfaces.SourceOfPublishingBean;
import net.onlinepresence.domainmodel.sioc.interfaces.ItemBean;
import net.onlinepresence.domainmodel.sioc.interfaces.UserAccountBean;
import net.onlinepresence.util.Util;

@Namespace("http://online-presence.net/opo/ns#")
@RdfType("OnlinePresence")
public class OnlinePresence extends Resource implements OnlinePresenceBean {

	public ActionBean action;
	public AgentBean agent;
	public ImageBean avatar;
	public String duration;
	public SharingSpaceBean intendentFor;
	public SpatialThingBean location;
	public Collection<OnlinePresenceComponentBean> presenceComponents = new LinkedList<OnlinePresenceComponentBean>();
	public SourceOfPublishingBean source;
	public String startTime;
	public ItemBean statusMessage;
	public UserAccountBean userAccount;

	public OnlinePresence() {
		super();
		setStartTime(Util.getTime());
	}
	
	public OnlinePresence(String uri) {
		super(uri);
		setStartTime(Util.getTime());
	}
	
	@RdfProperty("http://online-presence.net/opo/ns#currentAction")
	public ActionBean getAction() {
		return action;
	}
	
	public void setAction(ActionBean action) {
		if(action != null)
			this.action = action;
	}
	
	@RdfProperty("http://online-presence.net/opo/ns#declaredBy")
	public AgentBean getAgent() {
		return agent;
	}

	public void setAgent(AgentBean agent) {
		if(agent != null)
			this.agent = agent;
	}

	@RdfProperty("http://online-presence.net/opo/ns#avatar")
	public ImageBean getAvatar() {
		return avatar;
	}

	public void setAvatar(ImageBean avatar) {
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
	public SharingSpaceBean getIntendentFor() {
		return intendentFor;
	}

	public void setIntendentFor(SharingSpaceBean intendentFor) {
		if(intendentFor != null)
			this.intendentFor = intendentFor;
	}

	@RdfProperty("http://online-presence.net/opo/ns#currentLocation")
	public SpatialThingBean getLocation() {
		return location;
	}

	public void setLocation(SpatialThingBean location) {
		if(location != null)
			this.location = location;
	}

	@RdfProperty("http://online-presence.net/opo/ns#hasPresenceComponent")
	public Collection<OnlinePresenceComponentBean> getPresenceComponents() {
		return presenceComponents;
	}

	public void setPresenceComponents(
			Collection<OnlinePresenceComponentBean> presenceComponents) {
		if(presenceComponents != null)
			this.presenceComponents = presenceComponents;
	}

	public void addPresenceComponent(
			OnlinePresenceComponentBean presenceComponent) {
		if(presenceComponent != null)
			getPresenceComponents().add(presenceComponent);
	}

	@RdfProperty("http://online-presence.net/opo/ns#publishedFrom")
	public SourceOfPublishingBean getSource() {
		return source;
	}

	public void setSource(SourceOfPublishingBean source) {
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
	public ItemBean getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(ItemBean statusMessage) {
		if(statusMessage != null)
			this.statusMessage = statusMessage;
	}

	@RdfProperty("http://rdfs.org/sioc/ns#has_creator")
	public UserAccountBean getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccountBean user) {
		if(user != null)
			this.userAccount = user;
	}
}

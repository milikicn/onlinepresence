package net.onlinepresence.domainmodel.pojos;

import java.util.Collection;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;

import net.onlinepresence.domainmodel.foaf.interfaces.ImageBean;
import net.onlinepresence.domainmodel.general.Resource;
import net.onlinepresence.domainmodel.geo.interfaces.SpatialThingBean;
import net.onlinepresence.domainmodel.interfaces.AgentBean;
import net.onlinepresence.domainmodel.interfaces.OnlinePresenceBean;
import net.onlinepresence.domainmodel.interfaces.OnlinePresenceComponentBean;
import net.onlinepresence.domainmodel.interfaces.SharingSpaceBean;
import net.onlinepresence.domainmodel.interfaces.SourceOfPublishingBean;
import net.onlinepresence.domainmodel.interfaces.actions.ActionBean;
import net.onlinepresence.domainmodel.sioc.interfaces.ItemBean;
import net.onlinepresence.domainmodel.sioc.interfaces.UserBean;

@Namespace("http://online-presence.net/opo/ns#")
@RdfType("OnlinePresence")
public class OnlinePresence extends Resource implements OnlinePresenceBean {

	public ActionBean action;
	public AgentBean agent;
	public ImageBean avatar;
	public int duration;
	public SharingSpaceBean intendentFor;
	public SpatialThingBean location;
	public Collection<OnlinePresenceComponentBean> presenceComponents;
	public SourceOfPublishingBean source;
	public String startTime;
	public ItemBean statusMessage;
	public UserBean user;
	
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
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		if(duration > 0)
			this.duration = duration;
	}

	@RdfProperty("http://online-presence.net/opo/ns#intendedFor")
	public SharingSpaceBean getIntendentFor() {
		return intendentFor;
	}

	public void setIntendentFor(SharingSpaceBean intendentFor) {
		if(action != null)
		this.intendentFor = intendentFor;
	}

	@RdfProperty("http://online-presence.net/opo/ns#currentLocation")
	public SpatialThingBean getLocation() {
		return location;
	}

	public void setLocation(SpatialThingBean location) {
		if(action != null)
		this.location = location;
	}

	@RdfProperty("http://online-presence.net/opo/ns#hasPresenceComponent")
	public Collection<OnlinePresenceComponentBean> getPresenceComponents() {
		return presenceComponents;
	}

	public void setPresenceComponents(
			Collection<OnlinePresenceComponentBean> presenceComponents) {
		if(action != null)
		this.presenceComponents = presenceComponents;
	}

	public void addPresenceComponent(
			OnlinePresenceComponentBean presenceComponent) {
		if(action != null)
		getPresenceComponents().add(presenceComponent);
	}

	@RdfProperty("http://online-presence.net/opo/ns#publishedFrom")
	public SourceOfPublishingBean getSource() {
		return source;
	}

	public void setSource(SourceOfPublishingBean source) {
		if(action != null)
		this.source = source;
	}

	@RdfProperty("http://online-presence.net/opo/ns#startTime")
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		if(action != null)
		this.startTime = startTime;
	}

	@RdfProperty("http://online-presence.net/opo/ns#customMessage")
	public ItemBean getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(ItemBean statusMessage) {
		if(action != null)
		this.statusMessage = statusMessage;
	}

	@RdfProperty("http://rdfs.org/sioc/ns#has_creator")
	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		if(action != null)
		this.user = user;
	}
}

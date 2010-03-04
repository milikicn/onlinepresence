package net.onlinepresence.domainmodel.pojos;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.general.Resource;
import net.onlinepresence.domainmodel.interfaces.AgentBean;
import net.onlinepresence.domainmodel.interfaces.OnlinePresenceBean;

@Namespace("http://online-presence.net/opo/ns#")
@RdfType("Agent")
public class Agent extends Resource implements AgentBean{

	private OnlinePresenceBean onlinePresence;

	@RdfProperty("http://online-presence.net/opo/ns#declaresOnlinePresence")
	public OnlinePresenceBean getOnlinePresence() {
		return onlinePresence;
	}

	public void setOnlinePresence(OnlinePresenceBean onlinePresence) {
		this.onlinePresence = onlinePresence;
	}
	
	
}

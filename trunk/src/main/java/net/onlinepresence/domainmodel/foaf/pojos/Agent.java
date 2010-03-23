package net.onlinepresence.domainmodel.foaf.pojos;

import java.net.URI;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.foaf.interfaces.AgentBean;
import net.onlinepresence.domainmodel.opo.interfaces.OnlinePresenceBean;

@Namespace("http://online-presence.net/opo/ns#")
@RdfType("Agent")
public class Agent extends Thing implements AgentBean{

	private String nick;
	private OnlinePresenceBean onlinePresence;
	private URI mbox;

	public Agent() {
		super();
	}
	
	public Agent(String uri) {
		super(uri);
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		if(nick != null)
			this.nick = nick;
	}

	@RdfProperty("http://online-presence.net/opo/ns#declaresOnlinePresence")
	public OnlinePresenceBean getOnlinePresence() {
		return onlinePresence;
	}

	public void setOnlinePresence(OnlinePresenceBean onlinePresence) {
		if(onlinePresence != null)
			this.onlinePresence = onlinePresence;
	}

	@RdfProperty("http://xmlns.com/foaf/0.1/mbox")
	public URI getMbox() {
		return mbox;
	}

	public void setMbox(URI mbox) {
		this.mbox = mbox;
	}

}

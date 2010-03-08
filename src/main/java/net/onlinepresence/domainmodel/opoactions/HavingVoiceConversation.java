package net.onlinepresence.domainmodel.opoactions;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.foaf.interfaces.AgentBean;
import net.onlinepresence.domainmodel.opoactions.interfaces.HavingVoiceConversationBean;

@Namespace("http://online-presence.net/opo-actions/ns#")
@RdfType("HavingVoiceConversation")
public class HavingVoiceConversation extends HavingConversation implements
		HavingVoiceConversationBean {

	private AgentBean speaker;

	public HavingVoiceConversation() {
		super();
	}
	
	public HavingVoiceConversation(String uri) {
		super(uri);
	}
	
	@RdfProperty("http://online-presence.net/opo-actions/ns#speaker")
	public AgentBean getSpeaker() {
		return speaker;
	}

	public void setSpeaker(AgentBean speaker) {
		if(speaker != null)
			this.speaker = speaker;
	}
}

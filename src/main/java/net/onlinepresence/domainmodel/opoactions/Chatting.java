package net.onlinepresence.domainmodel.opoactions;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.foaf.interfaces.AgentBean;
import net.onlinepresence.domainmodel.opoactions.interfaces.ChattingBean;

@Namespace("http://online-presence.net/opo-actions/ns#")
@RdfType("Chatting")
public class Chatting extends HavingConversation implements ChattingBean {

	private AgentBean chatBuddy;

	public Chatting() {

	}
	
	public Chatting(String uri) {
		super(uri);
	}
	
	@RdfProperty("http://online-presence.net/opo-actions/ns#chatBuddy")
	public AgentBean getChatBuddy() {
		return chatBuddy;
	}

	public void setChatBuddy(AgentBean chatBuddy) {
		if(chatBuddy != null)
			this.chatBuddy = chatBuddy;
	}
	
}

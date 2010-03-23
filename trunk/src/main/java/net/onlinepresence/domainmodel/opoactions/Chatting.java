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
		super();
	}
	
	public Chatting(String uri) {
		super(uri);
	}
	
	public Chatting(AgentBean chatBuddy) {
		this();
		setChatBuddy(chatBuddy);
	}
	
	public Chatting(String uri, AgentBean chatBuddy) {
		this(uri);
		setChatBuddy(chatBuddy);
	}
	
	@RdfProperty("http://online-presence.net/opo-actions/ns#chatBuddy")
	public AgentBean getChatBuddy() {
		return chatBuddy;
	}

	public void setChatBuddy(AgentBean chatBuddy) {
		if(chatBuddy != null){
			chatBuddy.setURI(chatBuddy.getUri().replaceFirst("Agent", "ChatBuddy"));
			this.chatBuddy = chatBuddy;
		}
	}
	
}

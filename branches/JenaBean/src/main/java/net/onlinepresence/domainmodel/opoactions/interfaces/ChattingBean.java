package net.onlinepresence.domainmodel.opoactions.interfaces;

import net.onlinepresence.domainmodel.foaf.interfaces.AgentBean;

/**
 * An action of chatting (exchanging instant messages) with somebody.
 *
 */
public interface ChattingBean extends HavingConversationBean {

	AgentBean getChatBuddy();
	void setChatBuddy(AgentBean chatBuddy);
}

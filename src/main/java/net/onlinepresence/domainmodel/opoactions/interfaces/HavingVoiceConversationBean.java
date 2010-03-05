package net.onlinepresence.domainmodel.opoactions.interfaces;

import net.onlinepresence.domainmodel.foaf.interfaces.AgentBean;

/**
 * An action of voice talking with somebody.
 *
 */
public interface HavingVoiceConversationBean extends HavingConversationBean {

	AgentBean getSpeaker();
	void setSpeaker(AgentBean speaker);
}

package net.onlinepresence.domainmodel.opoactions;

import thewebsemantic.Namespace;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.opoactions.interfaces.HavingConversationBean;

@Namespace("http://online-presence.net/opo-actions/ns#")
@RdfType("HavingConversation")
public class HavingConversation extends BeingWith implements
		HavingConversationBean {

	public HavingConversation() {
		super();
	}
	
	public HavingConversation(String uri) {
		super(uri);
	}
	
}

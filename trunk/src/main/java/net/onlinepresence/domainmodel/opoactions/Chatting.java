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

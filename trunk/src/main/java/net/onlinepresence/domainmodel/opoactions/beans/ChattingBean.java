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
package net.onlinepresence.domainmodel.opoactions.beans;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.foaf.Agent;
import net.onlinepresence.domainmodel.opoactions.Chatting;

@Namespace("http://online-presence.net/opo-actions/ns#")
@RdfType("Chatting")
public class ChattingBean extends HavingConversationBean implements Chatting {

	private Agent chatBuddy;

	public ChattingBean() {
		super();
	}
	
	public ChattingBean(String uri) {
		super(uri);
	}
	
	public ChattingBean(Agent chatBuddy) {
		this();
		setChatBuddy(chatBuddy);
	}
	
	public ChattingBean(String uri, Agent chatBuddy) {
		this(uri);
		setChatBuddy(chatBuddy);
	}
	
	@RdfProperty("http://online-presence.net/opo-actions/ns#chatBuddy")
	public Agent getChatBuddy() {
		return chatBuddy;
	}

	public void setChatBuddy(Agent chatBuddy) {
		if(chatBuddy != null){
			chatBuddy.setURI(chatBuddy.getUri().replaceFirst("Agent", "ChatBuddy"));
			this.chatBuddy = chatBuddy;
		}
	}
	
}

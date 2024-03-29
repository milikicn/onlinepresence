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
package net.onlinepresence.jopo.ontmodel.opoactions.beans;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.jopo.ontmodel.foaf.Agent;
import net.onlinepresence.jopo.ontmodel.opoactions.Chatting;
import net.onlinepresence.jopo.util.Constants;
import net.onlinepresence.jopo.util.EqualsUtil;

@Namespace(Constants.OPO_ACTIONS_NS)
@RdfType("Chatting")
public class ChattingBean extends HavingConversationBean implements Chatting {

	private static final long serialVersionUID = 1008649956975198556L;
	private Agent chatBuddy;

	@Deprecated
	public ChattingBean() {
		super();
	}
	
	@Deprecated
	public ChattingBean(String uri) {
		super(uri);
	}
	
	@Deprecated
	public ChattingBean(Agent chatBuddy) {
		this();
		setChatBuddy(chatBuddy);
	}
	
	@Deprecated
	public ChattingBean(String uri, Agent chatBuddy) {
		this(uri);
		setChatBuddy(chatBuddy);
	}
	
	@RdfProperty(Constants.OPO_ACTIONS_NS + "chatBuddy")
	public Agent getChatBuddy() {
		return chatBuddy;
	}

	public void setChatBuddy(Agent chatBuddy) {
		if(chatBuddy != null){
			chatBuddy.setUri(chatBuddy.getUri().toString().replaceFirst("Agent", "ChatBuddy"));
			this.chatBuddy = chatBuddy;
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if (!(o instanceof ChattingBean))
			return  false;
			
		ChattingBean chat = (ChattingBean) (o);
			
		return
			EqualsUtil.areEqual(getChatBuddy(), chat.getChatBuddy()) &&
			super.equals(chat);
	}
}

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
		if(speaker != null){
			speaker.setURI(speaker.getUri().replaceFirst("Agent", "Speaker"));
			this.speaker = speaker;
		}
	}
}

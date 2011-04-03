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
package net.onlinepresence.ontmodel.opoactions.beans;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.ontmodel.foaf.Agent;
import net.onlinepresence.ontmodel.opoactions.HavingVoiceConversation;
import net.onlinepresence.util.Constants;
import net.onlinepresence.util.EqualsUtil;

@Namespace(Constants.OPO_ACTIONS_NS)
@RdfType("HavingVoiceConversation")
public class HavingVoiceConversationBean extends HavingConversationBean implements
		HavingVoiceConversation {

	private static final long serialVersionUID = 4759904896863266051L;
	private Agent speaker;

	@Deprecated
	public HavingVoiceConversationBean() {
		super();
	}
	
	@Deprecated
	public HavingVoiceConversationBean(String uri) {
		super(uri);
	}
	
	@RdfProperty(Constants.OPO_ACTIONS_NS + "speaker")
	public Agent getSpeaker() {
		return speaker;
	}

	public void setSpeaker(Agent speaker) {
		if(speaker != null){
			speaker.setUri(speaker.getUri().toString().replaceFirst("Agent", "Speaker"));
			this.speaker = speaker;
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if (!(o instanceof HavingVoiceConversationBean))
			return false;

		HavingVoiceConversationBean hvc = (HavingVoiceConversationBean) (o);
			
		return
			EqualsUtil.areEqual(getSpeaker(), hvc.getSpeaker()) &&
			super.equals(hvc);
	}
}

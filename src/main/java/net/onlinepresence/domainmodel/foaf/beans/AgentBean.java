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
package net.onlinepresence.domainmodel.foaf.beans;

import java.net.URI;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.foaf.Agent;
import net.onlinepresence.domainmodel.opo.OnlinePresence;

@Namespace("http://xmlns.com/foaf/0.1/")
@RdfType("Agent")
public class AgentBean extends ThingBean implements Agent {

	private static final long serialVersionUID = 4248456427067038592L;
	private String nick;
	private OnlinePresence onlinePresence;
	private URI mbox;

	@Deprecated
	public AgentBean() {
		super();
	}

	@Deprecated
	public AgentBean(String uri) {
		super(uri);
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		if (nick != null)
			this.nick = nick;
	}

	@RdfProperty("http://online-presence.net/opo/ns#declaresOnlinePresence")
	public OnlinePresence getOnlinePresence() {
		return onlinePresence;
	}

	public void setOnlinePresence(OnlinePresence onlinePresence) {
		if (onlinePresence != null)
			this.onlinePresence = onlinePresence;
	}

	@RdfProperty("http://xmlns.com/foaf/0.1/mbox")
	public URI getMbox() {
		return mbox;
	}

	public void setMbox(URI mbox) {
		this.mbox = mbox;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if (o instanceof AgentBean){

			AgentBean ag = (AgentBean) (o);
			
			if(getOnlinePresence() == null || ag.getOnlinePresence() == null)
				return false;
	
			if (getNick().equals(ag.getName()) &&
					getOnlinePresence().equals(ag.getOnlinePresence()) &&
					getMbox().equals(ag.getMbox()))
	
				return true;
			else
				return false;
		}
		return false;
	}
}

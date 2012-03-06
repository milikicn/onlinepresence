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
package net.onlinepresence.jopo.ontmodel.foaf;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.jopo.ontmodel.opo.OnlinePresence;
import net.onlinepresence.jopo.ontmodel.sioc.UserAccount;
import net.onlinepresence.jopo.util.Constants;
import net.onlinepresence.jopo.util.EqualsUtil;

@Namespace(Constants.FOAF_NS)
@RdfType("Agent")
public class Agent extends Thing {

	private static final long serialVersionUID = 4248456427067038592L;
	private String nick;
	private OnlinePresence onlinePresence;
	private URI mbox;
	private Collection<UserAccount> accounts;

	public Agent() {
		super();
		accounts = new ArrayList<UserAccount>();
	}

	public Agent(String uri) {
		super(uri);
		accounts = new ArrayList<UserAccount>();
	}

	
	public String getNick() {
		return nick;
	}

	@RdfProperty(Constants.FOAF_NS + "nick")
	public void setNick(String nick) {
		if (nick != null)
			this.nick = nick;
	}

	@RdfProperty(Constants.OPO_NS + "declaresOnlinePresence")
	public OnlinePresence getOnlinePresence() {
		return onlinePresence;
	}

	public void setOnlinePresence(OnlinePresence onlinePresence) {
		if (onlinePresence != null)
			this.onlinePresence = onlinePresence;
	}

	@RdfProperty(Constants.FOAF_NS + "mbox")
	public URI getMbox() {
		return mbox;
	}

	public void setMbox(URI mbox) {
		this.mbox = mbox;
	}
	
	/**
	 * @return the accounts
	 */
	@RdfProperty(Constants.FOAF_NS + "holdsAccount")
	public Collection<UserAccount> getAccounts() {
		return accounts;
	}

	/**
	 * @param accounts the accounts to set
	 */
	public void setAccounts(Collection<UserAccount> accounts) {
		if (null != accounts) {
			this.accounts = accounts;
		} else {
			throw new RuntimeException("accounts can not be null.");
		}
	}
	
	public void addAccount(UserAccount account) {
		if (account != null) {
			if (!getAccounts().contains(account)){
				getAccounts().add(account);
			}
		} else
			throw new RuntimeException("account must not be null.");
	}

	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if (!(o instanceof Agent))
			return false;

		Agent ag = (Agent) (o);
			
		return 
			EqualsUtil.areEqual(getNick(), ag.getNick()) &&
			EqualsUtil.areEqual(getOnlinePresence(), ag.getOnlinePresence()) &&
			EqualsUtil.areEqual(getMbox(), ag.getMbox()) &&
			super.equals(ag);
	}
}

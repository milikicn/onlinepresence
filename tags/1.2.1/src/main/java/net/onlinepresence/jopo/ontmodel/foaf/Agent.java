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
import java.util.Collection;

import net.onlinepresence.jopo.ontmodel.opo.OnlinePresence;
import net.onlinepresence.jopo.ontmodel.sioc.UserAccount;

public interface Agent extends Thing{
	
	String getNick();
	void setNick(String nick);

	void setOnlinePresence(OnlinePresence onlinePresence);
	OnlinePresence getOnlinePresence();
	
	URI getMbox();
	void setMbox(URI mbox);
	
	Collection<UserAccount> getAccounts();
	void setAccounts(Collection<UserAccount> accounts);
	void addAccount(UserAccount account);
}

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
package net.onlinepresence.domainmodel.sioc;

import java.net.URI;
import java.net.URISyntaxException;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;

import net.onlinepresence.domainmodel.general.Resource;
import net.onlinepresence.domainmodel.sioc.beans.UserAccountBean;

@Namespace("http://rdfs.org/sioc/ns#")
@RdfType("UserAccount")
public class UserAccount extends Resource implements UserAccountBean {

	private String accountName;
	private URI accountServiceHomepage;
	
	public UserAccount() {
		super();
	}

	public UserAccount(String uri) {
		super(uri);
	}

	@RdfProperty("http://xmlns.com/foaf/0.1/accountName")
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		if(accountName != null)
			this.accountName = accountName;
	}

	@RdfProperty("http://xmlns.com/foaf/0.1/accountServiceHomepage")
	public URI getAccountServiceHomepage() {
		return accountServiceHomepage;
	}

	public void setAccountServiceHomepage(URI accountServiceHomepage) {
		if(accountServiceHomepage != null)
			this.accountServiceHomepage = accountServiceHomepage;
	}
	
	public void setAccountServiceHomepage(String accountServiceHomepage) {
		if(accountServiceHomepage != null)
			try {
				setAccountServiceHomepage(new URI(accountServiceHomepage));
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
	}

}

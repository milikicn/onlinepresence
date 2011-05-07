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
package net.onlinepresence.jopo.ontmodel.sioc.beans;

import java.net.URI;
import java.net.URISyntaxException;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;

import net.onlinepresence.jopo.ontmodel.general.ResourceBean;
import net.onlinepresence.jopo.ontmodel.sioc.UserAccount;
import net.onlinepresence.jopo.util.Constants;
import net.onlinepresence.jopo.util.EqualsUtil;

@Namespace(Constants.SIOC_NS)
@RdfType("UserAccount")
public class UserAccountBean extends ResourceBean implements UserAccount {

	private static final long serialVersionUID = 5001849648664311372L;
	private String accountName;
	private URI accountServiceHomepage;
	
	@Deprecated
	public UserAccountBean() {
		super();
	}

	@Deprecated
	public UserAccountBean(String uri) {
		super(uri);
	}

	@RdfProperty(Constants.FOAF_NS + "accountName")
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		if(accountName != null)
			this.accountName = accountName;
	}

	@RdfProperty(Constants.FOAF_NS + "accountServiceHomepage")
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

	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if (!(o instanceof UserAccountBean))
			return false;

		UserAccountBean userAccount = (UserAccountBean) (o);
			
		return 
			EqualsUtil.areEqual(getAccountName(), userAccount.getAccountName()) &&
			EqualsUtil.areEqual(getAccountServiceHomepage(), userAccount.getAccountServiceHomepage());
	}
}

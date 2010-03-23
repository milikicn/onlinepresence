package net.onlinepresence.domainmodel.sioc.pojos;

import java.net.URI;
import java.net.URISyntaxException;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;

import net.onlinepresence.domainmodel.general.Resource;
import net.onlinepresence.domainmodel.sioc.interfaces.UserBean;

@Namespace("http://rdfs.org/sioc/ns#")
@RdfType("User")
public class User extends Resource implements UserBean {

	private String accountName;
	private URI accountServiceHomepage;
	
	public User() {
		super();
	}

	public User(String uri) {
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

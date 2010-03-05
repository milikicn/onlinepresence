package net.onlinepresence.domainmodel.sioc.pojos;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.foaf.interfaces.DocumentBean;
import net.onlinepresence.domainmodel.general.Resource;
import net.onlinepresence.domainmodel.sioc.interfaces.UserBean;

@Namespace("http://rdfs.org/sioc/ns#")
@RdfType("User")
public class User extends Resource implements UserBean {

	private String accountName;
	private DocumentBean accountServiceHomepage;

	@RdfProperty("http://xmlns.com/foaf/0.1/accountName")
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		if(accountName != null)
			this.accountName = accountName;
	}

	@RdfProperty("http://xmlns.com/foaf/0.1/accountServiceHomepage")
	public DocumentBean getAccountServiceHomepage() {
		return accountServiceHomepage;
	}

	public void setAccountServiceHomepage(DocumentBean accountServiceHomepage) {
		if(accountServiceHomepage != null)
			this.accountServiceHomepage = accountServiceHomepage;
	}

}

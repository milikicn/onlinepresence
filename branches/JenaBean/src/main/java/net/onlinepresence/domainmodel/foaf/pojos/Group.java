package net.onlinepresence.domainmodel.foaf.pojos;

import thewebsemantic.Namespace;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.foaf.interfaces.GroupBean;

@Namespace("http://xmlns.com/foaf/0.1/")
@RdfType("Group")
public class Group extends Thing implements GroupBean {

	public Group() {
		super();
	}

	public Group(String uri) {
		super(uri);
	}

}

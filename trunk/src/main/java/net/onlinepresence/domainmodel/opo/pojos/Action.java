package net.onlinepresence.domainmodel.opo.pojos;

import thewebsemantic.Namespace;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.general.Resource;
import net.onlinepresence.domainmodel.opo.interfaces.ActionBean;

@Namespace("http://online-presence.net/opo/ns#")
@RdfType("Action")
public class Action extends Resource implements ActionBean {

	public Action() {
		super();
	}
	
	public Action(String uri) {
		super(uri);
	}
	
}

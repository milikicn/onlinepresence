package net.onlinepresence.domainmodel.opo.pojos;

import net.onlinepresence.domainmodel.general.Resource;
import net.onlinepresence.domainmodel.opo.interfaces.ActionBean;

public class Action extends Resource implements ActionBean {

	public Action() {

	}
	
	public Action(String uri) {
		super(uri);
	}
	
}

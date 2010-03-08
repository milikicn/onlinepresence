package net.onlinepresence.domainmodel.opoactions;

import thewebsemantic.Namespace;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.opo.pojos.Action;
import net.onlinepresence.domainmodel.opoactions.interfaces.WorkingBean;

@Namespace("http://online-presence.net/opo-actions/ns#")
@RdfType("Working")
public class Working extends Action implements WorkingBean {

	public Working() {
		super();
	}
	
	public Working(String uri) {
		super(uri);
	}
	
}

package net.onlinepresence.domainmodel.pojos;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.general.Resource;
import net.onlinepresence.domainmodel.interfaces.OnlinePresenceBean;
import net.onlinepresence.domainmodel.interfaces.OnlinePresenceComponentBean;

@Namespace("http://online-presence.net/opo/ns#")
@RdfType("OnlinePresenceComponent")
public class OnlinePresenceComponent extends Resource implements OnlinePresenceComponentBean {

	private OnlinePresenceBean component;

	@RdfProperty("http://online-presence.net/opo/ns#isPresenceComponentOf")
	public OnlinePresenceBean getComponent() {
		return component;
	}

	public void setComponent(OnlinePresenceBean component) {
		if(component != null)
			this.component = component;
	}
	
	

}

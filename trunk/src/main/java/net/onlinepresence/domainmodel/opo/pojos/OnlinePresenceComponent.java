package net.onlinepresence.domainmodel.opo.pojos;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.general.Resource;
import net.onlinepresence.domainmodel.opo.interfaces.OnlinePresenceBean;
import net.onlinepresence.domainmodel.opo.interfaces.OnlinePresenceComponentBean;

@Namespace("http://online-presence.net/opo/ns#")
@RdfType("OnlinePresenceComponent")
public class OnlinePresenceComponent extends Resource implements OnlinePresenceComponentBean {

	private OnlinePresenceBean component;

	public OnlinePresenceComponent() {
		super();
	}
	
	public OnlinePresenceComponent(String uri) {
		super(uri);
	}
	
	@RdfProperty("http://online-presence.net/opo/ns#isPresenceComponentOf")
	public OnlinePresenceBean getComponent() {
		return component;
	}

	public void setComponent(OnlinePresenceBean component) {
		if(component != null)
			this.component = component;
	}	

}

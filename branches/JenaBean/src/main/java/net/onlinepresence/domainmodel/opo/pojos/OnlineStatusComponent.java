package net.onlinepresence.domainmodel.opo.pojos;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.general.Resource;
import net.onlinepresence.domainmodel.opo.interfaces.OnlineStatusComponentBean;
import net.onlinepresence.domainmodel.opo.interfaces.presencecomponents.OnlineStatusBean;

@Namespace("http://online-presence.net/opo/ns#")
@RdfType("OnlineStatusComponent")
public class OnlineStatusComponent extends Resource implements OnlineStatusComponentBean{
	
	private OnlineStatusBean component;

	@RdfProperty("http://online-presence.net/opo/ns#isStatusComponentOf")
	public OnlineStatusBean getComponent() {
		return component;
	}

	public void setComponent(OnlineStatusBean component) {
		if(component != null)
			this.component = component;
	}

}

package net.onlinepresence.domainmodel.pojos.presencecomponents;

import java.util.Collection;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.interfaces.OnlineStatusComponentBean;
import net.onlinepresence.domainmodel.interfaces.presencecomponents.OnlineStatusBean;
import net.onlinepresence.domainmodel.pojos.OnlinePresenceComponent;

@Namespace("http://online-presence.net/opo/ns#")
@RdfType("OnlineStatus")
public class OnlineStatus extends OnlinePresenceComponent implements OnlineStatusBean{

	private Collection<OnlineStatusComponentBean> statusComponents;
	
	private String name;

	@RdfProperty("http://online-presence.net/opo/ns#hasStatusComponent")
	public Collection<OnlineStatusComponentBean> getOnlineStatusComponents() {
		return statusComponents;
	}

	public void setOnlineStatusComponents(
			Collection<OnlineStatusComponentBean> statusComponents) {
		if(statusComponents != null)
			this.statusComponents = statusComponents;
	}
	
	public void addStatusComponent(OnlineStatusComponentBean statusComponent){
		if(statusComponent != null)
			getOnlineStatusComponents().add(statusComponent);
	}

	@RdfProperty("http://online-presence.net/opo/ns#onlineStatusName")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name != null)
			this.name = name;
	}
	
}

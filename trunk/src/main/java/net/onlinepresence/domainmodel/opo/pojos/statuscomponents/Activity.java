package net.onlinepresence.domainmodel.opo.pojos.statuscomponents;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.opo.interfaces.statuscomponents.ActivityBean;
import net.onlinepresence.domainmodel.opo.pojos.OnlineStatusComponent;

@Namespace("http://online-presence.net/opo/ns#")
@RdfType("Activity")
public class Activity extends OnlineStatusComponent implements ActivityBean{

	private int inactivityPeriod;

	/**
	 * The Agent is active on the system.
	 */
	public static Activity ACTIVE = new Activity("http://online-presence.net/opo/ns#Active");

	/**
	 * The Agent is inactive for at least 5 min.
	 */
	public static Activity INACTIVE = new Activity("http://online-presence.net/opo/ns#Inactive");

	/**
	 * The Agent is inactive for at least 20 min.
	 */
	public static Activity PROLONGED_INACTIVE = new Activity("http://online-presence.net/opo/ns#ProlongedInactive");


	public Activity() {
		super();
	}
	
	public Activity(String uri) {
		super(uri);
	}

	@RdfProperty("http://online-presence.net/opo/ns#inactivityPeriod")
	public int getInactivityPeriod() {
		return inactivityPeriod;
	}

	public void setInactivityPeriod(int inactivityPeriod) {
		if(inactivityPeriod > 0)
			this.inactivityPeriod = inactivityPeriod;
	}
	
}

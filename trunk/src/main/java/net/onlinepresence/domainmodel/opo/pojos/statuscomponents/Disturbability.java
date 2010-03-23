package net.onlinepresence.domainmodel.opo.pojos.statuscomponents;

import thewebsemantic.Namespace;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.opo.interfaces.statuscomponents.DisturbabilityBean;
import net.onlinepresence.domainmodel.opo.pojos.OnlineStatusComponent;

@Namespace("http://online-presence.net/opo/ns#")
@RdfType("Disturbability")
public class Disturbability extends OnlineStatusComponent implements DisturbabilityBean{

	/**
	 * The agent is not busy and therefore available for contatct by other
	 * Agents.
	 */
	public static Disturbability AVAILABLE = new Disturbability("http://online-presence.net/opo/ns#Available");

	/**
	 * The Agent is busy and does not want to be contacted by other Agents.
	 */
	public static Disturbability DO_NOT_DISTURB = new Disturbability("http://online-presence.net/opo/ns#DoNotDisturb");

	public Disturbability() {
		super();
	}
	
	public Disturbability(String uri) {
		super(uri);
	}
}

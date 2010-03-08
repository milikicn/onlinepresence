package net.onlinepresence.domainmodel.opo.pojos.presencecomponents;

import thewebsemantic.Namespace;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.opo.interfaces.presencecomponents.FindabilityBean;
import net.onlinepresence.domainmodel.opo.pojos.OnlinePresenceComponent;

@Namespace("http://online-presence.net/opo/ns#")
@RdfType("Findability")
public class Findability extends OnlinePresenceComponent implements FindabilityBean{

	/**
	 * The Agent's contact details cannot be found by anyone on the Web, but the
	 * findability is controled by some rules/policies.
	 */
	public static Findability CONSTRAINED_FINDABILITY = new Findability();

	/**
	 * The Agent's contact details can be found by anyone on the Web.
	 */
	public static Findability PUBLICLY_FINDABLE = new Findability();

	/**
	 * Sets URIs for static instances of the class.
	 */
	static {
		CONSTRAINED_FINDABILITY
				.setURI("http://online-presence.net/opo/ns#ConstrainedFindability");
		PUBLICLY_FINDABLE
				.setURI("http://online-presence.net/opo/ns#PubliclyFindable");
	}

	public Findability() {

	}
	
	public Findability(String uri) {
		super(uri);
	}
	
}

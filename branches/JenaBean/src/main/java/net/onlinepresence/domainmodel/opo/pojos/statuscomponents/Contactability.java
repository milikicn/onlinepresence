package net.onlinepresence.domainmodel.opo.pojos.statuscomponents;

import thewebsemantic.Namespace;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.opo.interfaces.statuscomponents.ContactabilityBean;
import net.onlinepresence.domainmodel.opo.pojos.OnlineStatusComponent;

@Namespace("http://online-presence.net/opo/ns#")
@RdfType("Contactability")
public class Contactability extends OnlineStatusComponent implements ContactabilityBean{

	/**
	 * The Agent can be contacted by anyone on the Web.
	 */
	public static Contactability FREELY_CONTACTABLE = new Contactability();

	/**
	 * The Agent cannot be contated by anyone on the Web, but the contactability
	 * is controled by some rules/policies.
	 */
	public static Contactability CONSTRAINED_CONTACTABILITY = new Contactability();

	/**
	 * Sets URIs for static instances of the class.
	 */
	static {
		FREELY_CONTACTABLE
				.setURI("http://online-presence.net/opo/ns#FreelyContactable");
		CONSTRAINED_CONTACTABILITY
				.setURI("http://online-presence.net/opo/ns#ConstrainedContactability");
	}

}

/**
 * @author: Nikola Milikic
 * e-mail: nikola.milikic@gmail.com
 * @date: Jul 24, 2008
 * @version: 0.1
 */
package presenceComponents;

/**
 * @author Nikola Milikic
 * 
 */
public class Findability extends OnlinePresenceComponent {

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
				.setURI("http://ggg.milanstankovic.org/opo/ns#ConstrainedContactability");
		PUBLICLY_FINDABLE
				.setURI("http://ggg.milanstankovic.org/opo/ns#PubliclyFindable");
	}

	/* (non-Javadoc)
	 * @see presenceComponents.OnlinePresenceComponent#getInstance(java.lang.String)
	 */
	@Override
	public OnlinePresenceComponent getStaticInstance(String name) {
		if (name.equalsIgnoreCase("constrainedFindability"))
			return CONSTRAINED_FINDABILITY;
		else
			return PUBLICLY_FINDABLE;
	}


}

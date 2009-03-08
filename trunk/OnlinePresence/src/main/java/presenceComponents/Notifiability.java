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
public class Notifiability extends OnlinePresenceComponent {

	/**
	 * Agent can receive notifications from applications.
	 */
	public static Notifiability ALL_NOTIFICATIONS_PASS = new Notifiability();

	/**
	 * The acceptance of notifications from applications is somehow constrained
	 * (by using some rules or policies).
	 */
	public static Notifiability NOTIFICATIONS_CONSTRAINED = new Notifiability();

	/**
	 * Agent cannot receive any notification from applications.
	 */
	public static Notifiability NOTIFICATIONS_PROHIBITED = new Notifiability();

	/**
	 * Sets URIs for static instances of the class.
	 */
	static {
		ALL_NOTIFICATIONS_PASS.setURI("http://ggg.milanstankovic.org/opo/ns#AllNotificationsPass");
		NOTIFICATIONS_CONSTRAINED.setURI("http://ggg.milanstankovic.org/opo/ns#NotificationsConstrained");
		NOTIFICATIONS_PROHIBITED.setURI("http://ggg.milanstankovic.org/opo/ns#NotificationsProhibited");
	}

	/* (non-Javadoc)
	 * @see presenceComponents.OnlinePresenceComponent#getInstance(java.lang.String)
	 */
	@Override
	public OnlinePresenceComponent getStaticInstance(String name) {
		if (name.equalsIgnoreCase("allNotificationsPass"))
			return ALL_NOTIFICATIONS_PASS;
		else if (name.equalsIgnoreCase("notificationsConstrained"))
			return NOTIFICATIONS_CONSTRAINED;
		else
			return NOTIFICATIONS_PROHIBITED;
	}


}

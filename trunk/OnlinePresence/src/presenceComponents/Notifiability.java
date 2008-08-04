/**
 * @author: Nikola Milikic
 * e-mail: nikola.milikic@gmail.com
 * @date: Jul 24, 2008
 * @version: 0.1
 */
package presenceComponents;

import java.net.URI;

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
		ALL_NOTIFICATIONS_PASS
				.setURI(URI
						.create("http://ggg.milanstankovic.org/opo/ns#AllNotificationsPass"));
		NOTIFICATIONS_CONSTRAINED
				.setURI(URI
						.create("http://ggg.milanstankovic.org/opo/ns#NotificationsConstrained"));
		NOTIFICATIONS_PROHIBITED
				.setURI(URI
						.create("http://ggg.milanstankovic.org/opo/ns#NotificationsProhibited"));
	}

	
	 public static Notifiability getInstance(String name){
	 if(name.equalsIgnoreCase("allNotificationsPass"))
	 return ALL_NOTIFICATIONS_PASS;
	 else if(name.equalsIgnoreCase("notificationsConstrained"))
	 return NOTIFICATIONS_CONSTRAINED;
	 else
	 return NOTIFICATIONS_PROHIBITED;
	 }
}

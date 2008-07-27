/**
* @autor: Nikola Milikic
* e-mail: nikola.milikic@gmail.com
* @date: Jul 24, 2008
* @version: 0.1
*/
package presenceComponents;

import java.net.URI;

import presence.OntologyConcept;

/**
 * @author Nikola Milikic
 *
 */
public class Notifiability extends OntologyConcept implements OnlinePresenceComponent{
	
	/**
	 * Agent can receive notifications from applications.
	 */
	public static Notifiability ALLNOTIFICATIONSPASS = new Notifiability();
	
	/**
	 * The acceptance of notifications from applications is somehow constrained (by using some rules or policies).
	 */
	public static Notifiability NOTIFICATIONSCONSTRAINED = new Notifiability();
	
	/**
	 * Agent cannot receive any notification from applications.
	 */
	public static Notifiability NOTIFICATIONSPROHIBITED = new Notifiability();

	/**
	 * Sets URIs for static instances of the class.
	 */
	static {
		ALLNOTIFICATIONSPASS.setUri(URI.create("http://ggg.milanstankovic.org/opo/ns#AllNotificationsPass"));
		NOTIFICATIONSCONSTRAINED.setUri(URI.create("http://ggg.milanstankovic.org/opo/ns#NotificationsConstrained"));
		NOTIFICATIONSPROHIBITED.setUri(URI.create("http://ggg.milanstankovic.org/opo/ns#NotificationsProhibited"));
	}
}

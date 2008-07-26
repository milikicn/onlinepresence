/**
* @autor: Nikola Milikic
* e-mail: nikola.milikic@gmail.com
* @date: Jul 24, 2008
* @version: 0.1
*/
package presenceComponents;

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
}

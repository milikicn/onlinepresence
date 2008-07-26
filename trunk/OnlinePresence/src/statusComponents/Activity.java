/**
* @autor: Nikola Milikic
* e-mail: nikola.milikic@gmail.com
* @date: Jul 24, 2008
* @version: 0.1
*/
package statusComponents;

import presence.OntologyConcept;

/**
 * @author Nikola Milikic
 *
 */
public class Activity extends OntologyConcept implements OnlineStatusComponent {
		
	/**
	 * The Agent is active on the system.
	 */
	public static Activity ACTIVE = new Activity();
	
	/**
	 * The Agent is inactive for at least 5 min.
	 */
	public static Activity INACTIVE = new Activity();
	
	/**
	 * The Agent is inactive for at least 20 min.
	 */
	public static Activity PROLONGEDINACTIVE = new Activity();

}

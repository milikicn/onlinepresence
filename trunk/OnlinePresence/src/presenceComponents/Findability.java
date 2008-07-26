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
public class Findability extends OntologyConcept implements OnlinePresenceComponent{

	/**
	 * The Agent's contact details cannot be found by anyone on the Web, but the findability is controled by some rules/policies.
	 */
	public static Findability CONSTRAINEDFINDABILITY = new Findability();
	
	/**
	 * The Agent's contact details can be found by anyone on the Web.
	 */
	public static Findability PUBLICLYFINDABLE = new Findability();
}

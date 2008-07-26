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

public class Contactability extends OntologyConcept implements OnlineStatusComponent{
	
	/**
	 * The Agent can be contacted by anyone on the Web.
	 */
	public static Contactability FREELYCONTACTABLE = new Contactability();
	
	/**
	 * The Agent cannot be contated by anyone on the Web, but the contactability is 
	 * controled by some rules/policies.
	 */
	public static Contactability CONSTRAINEDCONTACTABILITY = new Contactability();

}

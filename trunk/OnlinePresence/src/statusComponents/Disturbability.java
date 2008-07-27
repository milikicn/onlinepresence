/**
* @author: Nikola Milikic
* e-mail: nikola.milikic@gmail.com
* @date: Jul 24, 2008
* @version: 0.1
*/
package statusComponents;

import java.net.URI;

import presence.OntologyConcept;

/**
 * @author Nikola Milikic
 *
 */
public class Disturbability extends OntologyConcept implements OnlineStatusComponent{
	
	/**
	 * The agent is not busy and therefore available for contatct by other Agents.
	 */
	public static Disturbability AVAILABLE = new Disturbability();
	
	/**
	 * The Agent is busy and does not want to be contacted by other Agents.
	 */
	public static Disturbability DONOTDISTURB = new Disturbability();

	/**
	 * Sets URIs for static instances of the class.
	 */
	static {
		AVAILABLE.setUri(URI.create("http://ggg.milanstankovic.org/opo/ns#Available"));
		DONOTDISTURB.setUri(URI.create("http://ggg.milanstankovic.org/opo/ns#DoNotDistrub"));
	}
}

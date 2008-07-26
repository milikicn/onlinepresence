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
public class Disturbability extends OntologyConcept implements OnlineStatusComponent{
	
	//The agent is not busy and therefore available 
	//for contatct by other Agents
	public static Disturbability AVAILABLE = new Disturbability();
	
	//The Agent is busy and does not want to be 
	//contacted by other Agents
	public static Disturbability DONOTDISTURB = new Disturbability();
}

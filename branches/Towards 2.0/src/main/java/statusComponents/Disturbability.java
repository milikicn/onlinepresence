/**
 * @author: Nikola Milikic
 * e-mail: nikola.milikic@gmail.com
 * @date: Jul 24, 2008
 * @version: 0.1
 */
package statusComponents;

/**
 * @author Nikola Milikic
 * 
 */
public class Disturbability extends OnlineStatusComponent {

	/**
	 * The agent is not busy and therefore available for contatct by other
	 * Agents.
	 */
	public static Disturbability AVAILABLE = new Disturbability();

	/**
	 * The Agent is busy and does not want to be contacted by other Agents.
	 */
	public static Disturbability DO_NOT_DISTURB = new Disturbability();

	/**
	 * Sets URIs for static instances of the class.
	 */
	static {
		AVAILABLE.setURI("http://ggg.milanstankovic.org/opo/ns#Available");
		DO_NOT_DISTURB.setURI("http://ggg.milanstankovic.org/opo/ns#DoNotDistrub");
	}
	
	 public OnlineStatusComponent getStaticInstance(String name){
	 if(name.equalsIgnoreCase("Available"))
	 return AVAILABLE;
	 else
	 return DO_NOT_DISTURB;
	 }
}
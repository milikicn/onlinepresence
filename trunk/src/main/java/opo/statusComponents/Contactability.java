/**
 * @author: Nikola Milikic
 * e-mail: nikola.milikic@gmail.com
 * @date: Jul 24, 2008
 * @version: 0.1
 */
package opo.statusComponents;

/**
 * @author Nikola Milikic
 * 
 */

public class Contactability extends OnlineStatusComponent {

	/**
	 * The Agent can be contacted by anyone on the Web.
	 */
	public static Contactability FREELY_CONTACTABLE = new Contactability();

	/**
	 * The Agent cannot be contated by anyone on the Web, but the contactability
	 * is controled by some rules/policies.
	 */
	public static Contactability CONSTRAINED_CONTACTABILITY = new Contactability();

	/**
	 * Sets URIs for static instances of the class.
	 */
	static {
		FREELY_CONTACTABLE
				.setURI("http://ggg.milanstankovic.org/opo/ns#FreelyContactable");
		CONSTRAINED_CONTACTABILITY
				.setURI("http://ggg.milanstankovic.org/opo/ns#ConstrainedContactability");
	}
	
	 public OnlineStatusComponent getStaticInstance(String name){
	 if(name.equalsIgnoreCase("freelyContactable"))
	 return FREELY_CONTACTABLE;
	 else
	 return CONSTRAINED_CONTACTABILITY;
	 }
}

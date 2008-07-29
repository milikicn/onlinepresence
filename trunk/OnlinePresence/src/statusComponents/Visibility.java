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
public class Visibility extends OnlineStatusComponent{
	
	/**
	 * The Agent's OnlineStatus is visible to other Agents.
	 */
	public static Visibility VISIBLE = new Visibility();
	 
	/**
	 * The Agent's OnlineStatus is not visible to other Agents.
	 */
	public static Visibility INVISIBLE = new Visibility();

	/**
	 * Sets URIs for static instances of the class.
	 */
	static {
		VISIBLE.setURI(URI.create("http://ggg.milanstankovic.org/opo/ns#Visible"));
		INVISIBLE.setURI(URI.create("http://ggg.milanstankovic.org/opo/ns#Invisible"));
	}
	
	public static Visibility getInstance(String name){
		if(name.equalsIgnoreCase("Visible"))
			return VISIBLE;
		else
			return INVISIBLE;
	}
}

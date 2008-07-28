/**
 * @author: Filip Radulovic
 * e-mail: filiprd@gmail.com
 * @date: Jul 26, 2008
 * @version: 0.1
 */
package agent;

import java.net.URI;
import java.net.URISyntaxException;

import presence.OnlinePresence;
import presence.OntologyConcept;

/**
 * @author Filip Radulovic
 * 
 */
public class Agent extends OntologyConcept {

	OnlinePresence onlinePresence;

	public static Agent AGENT = new Agent();

	public URI getClassURI() {
		try {
			return new URI("http://xmlns.com/foaf/0.1/Agent");
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Sets Agent's OnlinePresence properties.
	 * 
	 * @param OnlinePresence
	 */
	public void setOnlinePresence(OnlinePresence OnlinePresence) {
		this.onlinePresence = OnlinePresence;
	}
}
